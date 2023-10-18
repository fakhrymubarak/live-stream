package com.fakhry.livestream

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fakhry.livestream.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    private fun initEvent() {
        binding.btnLiveStreaming.setOnClickListener {

        }
    }
}