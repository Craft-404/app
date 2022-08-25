package com.craft404.sainyojit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.ActivityAuthBinding
import com.craft404.sainyojit.ui.auth.LoginFragment

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, LoginFragment.newInstance()).commit()
    }
}