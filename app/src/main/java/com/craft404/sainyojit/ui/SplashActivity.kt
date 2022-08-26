package com.craft404.sainyojit.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.ActivitySplashBinding
import com.craft404.sainyojit.ui.auth.AuthViewModel
import com.craft404.sainyojit.util.PrefManager
import com.craft404.sainyojit.util.PrefManager.API_TOKEN
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        lifecycleScope.launch {
            startActivity(
                Intent(
                    this@SplashActivity,
                    if (PrefManager.getString(API_TOKEN, "").isEmpty())
                        AuthActivity::class.java
                    else MainActivity::class.java
                )
            ).also {
                finish()
            }
        }
    }
}