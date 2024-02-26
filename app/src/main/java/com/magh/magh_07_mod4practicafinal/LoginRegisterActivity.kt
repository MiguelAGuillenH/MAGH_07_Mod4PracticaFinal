package com.magh.magh_07_mod4practicafinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.magh.magh_07_mod4practicafinal.databinding.ActivityLoginRegisterBinding

class LoginRegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer,LoginFragment.newInstance())
            .commit()
    }
}