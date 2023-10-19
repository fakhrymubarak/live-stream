package com.fakhry.livestream

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fakhry.livestream.service.ApiService
import com.fakhry.livestream.service.response.CreateActivityResult
import com.fakhry.livestream.utils.CanonicalRequest
import com.fakhry.livestream.utils.createJsonRequestBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val service: ApiService) : ViewModel() {

    private val _liveStreamActivityState = MutableSharedFlow<UiResult<CreateActivityResult>>()
    val liveStreamActivityState = _liveStreamActivityState.asSharedFlow()

    init {
        createActivity()
    }

    private fun createActivity() {
        viewModelScope.launch(Dispatchers.IO) {
            _liveStreamActivityState.emit(UiResult.Loading)

            val canonicalHeaders = CanonicalRequest().generateByteDanceHeaders()
            Timber.w(canonicalHeaders)
            try {
                val requestBody = createJsonRequestBody(
                    "Name" to "Live Room A",
                    "LiveTime" to "1642668850",
                )
                val apiResult = service.createActivityApiV2(params = requestBody)
                _liveStreamActivityState.emit(UiResult.Success(apiResult.createActivityResult))
            } catch (e: Exception) {
                _liveStreamActivityState.emit(UiResult.Failure(e.message))
                Timber.e(e.message.toString())
            }
        }
    }

    companion object {
        val FACTORY: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel(
                    ApiService.createApiService()
                )
            }
        }
    }
}

sealed class UiResult<out T> {
    data class Success<out T>(val data: T) : UiResult<T>()
    data class Failure(val message: String?) : UiResult<Nothing>()
    data object Loading : UiResult<Nothing>()
}
