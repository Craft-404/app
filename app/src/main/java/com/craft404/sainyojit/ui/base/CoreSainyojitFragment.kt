package com.craft404.sainyojit.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class CoreSainyojitFragment : Fragment() {
    private lateinit var progressDialog: ProgressDialog

    fun showLoading() {
//        if (this::progressDialog.isInitialized)
//            progressDialog.show()
    }

    fun hideLoading() {
//        if (this::progressDialog.isInitialized)
//            progressDialog.hide()
    }

    /*override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ProgressDialog(context).apply {
            setTitle("Please wait…")
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }*/

    abstract fun initUI()

    abstract fun addObservers()
}