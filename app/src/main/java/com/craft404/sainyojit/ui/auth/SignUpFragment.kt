package com.craft404.sainyojit.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.FragmentSignUpBinding

/**
 * @author yashkasera
 * Created 18/08/22 at 11:26 PM
 */
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    companion object {
        private const val TAG = "SignUpFragment"
        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}