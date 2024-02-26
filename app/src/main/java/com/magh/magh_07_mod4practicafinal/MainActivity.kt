package com.magh.magh_07_mod4practicafinal

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.magh.magh_07_mod4practicafinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var user : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
            intent.getSerializableExtra("EXTRA_USER", User::class.java)
        }else{
            intent.getSerializableExtra("EXTRA_USER") as User
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer,UserDataFragment.newInstance(user))
            .commit()
    }
}