package com.craft404.sainyojit.ui.approval

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.FragmentVerifyDocumentsBinding
import com.craft404.sainyojit.repository.model.DocumentModel
import com.craft404.sainyojit.ui.base.CoreSainyojitFragment
import com.craft404.sainyojit.util.loadImage

class VerifyDocumentFragment : CoreSainyojitFragment() {
    companion object {
        fun newInstance(): VerifyDocumentFragment {
            return VerifyDocumentFragment()
        }
    }

    private lateinit var binding: FragmentVerifyDocumentsBinding
    private var list: List<DocumentModel> = emptyList()
    private var index = 0

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[ApprovalViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_verify_documents, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        addObservers()
        viewModel.fetchDocuments()
    }

    override fun initUI() {
        with(binding) {
            next.setOnClickListener {
                if (index < list.size) {
                    index++
                    updateUI()
                }
            }
            previous.setOnClickListener {
                if (index > 0) {
                    index--
                    updateUI()
                }
            }
        }
    }

    private fun updateUI() {
        with(binding) {
            if (index == 0) {
                previous.visibility = View.GONE
            } else {
                previous.visibility = View.VISIBLE
            }
            if (index == list.size - 1) {
                next.visibility = View.GONE
            } else {
                next.visibility = View.VISIBLE
            }
            description.text = list[index].description
            title.text = list[index].title
            documentImage.loadImage(list[index].documentLink)
        }
    }

    override fun addObservers() {
        viewModel.documentsResult.observe(requireActivity()) {
            list = it
        }
    }
}