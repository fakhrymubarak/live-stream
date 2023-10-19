package com.fakhry.livestream

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bytedance.live.push.TVUPushLiveRoom
import com.fakhry.livestream.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> { MainViewModel.FACTORY }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.liveStreamActivityState.collect { result ->
                when (result) {
                    UiResult.Loading -> binding.tvUiState.text = "LOADING"
                    is UiResult.Success -> initEvent(result.data.activityId)
                    is UiResult.Failure -> binding.tvUiState.text = result.message ?: "ERROR"
                }
            }
        }
    }

    private fun initEvent(activityId: Long) {
        binding.btnLiveStreaming.setOnClickListener {
            TVUPushLiveRoom.startPushLiveRoom(this@MainActivity, activityId, "SECRET_KEY")
        }
    }
}