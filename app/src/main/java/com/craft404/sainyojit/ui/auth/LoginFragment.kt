package com.craft404.sainyojit.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.FragmentLoginBinding
import com.craft404.sainyojit.ui.MainActivity
import com.craft404.sainyojit.ui.base.CoreSainyojitFragment
import com.craft404.sainyojit.util.Result
import com.craft404.sainyojit.util.isEmpty
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginFragment : CoreSainyojitFragment() {

    private lateinit var binding: FragmentLoginBinding


    private val viewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        addObservers()
    }

    override fun initUI() {
        binding.init()
    }

    override fun addObservers() {
        viewModel.result.observe(viewLifecycleOwner) {
            binding.login.isEnabled = (it is Result.Loading).not()
            when (it) {
                is Result.Error -> {
                    hideLoading()
                    Log.e("TAG", "addObservers: ", it.exception)
                }
                is Result.Loading -> {
                    Log.d("LoginFragment.kt", "YASH => addObservers:48 loading")
                    showLoading()
                }
                is Result.Success -> startActivity(Intent(requireActivity(), MainActivity::class.java)).also {
                    hideLoading()
                    requireActivity().finish()
                }
            }
        }
    }

    private fun FragmentLoginBinding.init() {
        signUp.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Contact Admin")
                .setMessage("Please contact admin@aicte.org to get your credentials to sign in to this portal")
                .setPositiveButton("Dismiss") { d, _ ->
                    d.dismiss()
                }
                .show()
        }

        editEmail.doAfterTextChanged { text ->
            layoutEmail.error =
                if (text.isNullOrEmpty()) "Username is required"
                else null
        }
        editPassword.doAfterTextChanged { text ->
            layoutPassword.error =
                if (text.isNullOrEmpty()) "Password is required"
                else null
        }

        login.setOnClickListener {
            if (editEmail.isEmpty()) {
                Toast.makeText(requireContext(), "Email cannot be empty!", Toast.LENGTH_SHORT).show()
            } else if (editPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Password cannot be empty!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(editEmail.text.toString(), editPassword.text.toString())
            }
        }
    }
}