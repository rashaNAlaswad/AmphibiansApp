package com.rns.amphibiansapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rns.amphibiansapplication.network.Amphibian
import com.rns.amphibiansapplication.network.AmphibiansApi
import kotlinx.coroutines.launch

enum class AmphibianApiStatus { LOADING, ERROR, DONE }

class AmphibianViewModel : ViewModel() {
    private var _status = MutableLiveData<AmphibianApiStatus>()
    val status: LiveData<AmphibianApiStatus> get() = _status

    private var _amphibians = MutableLiveData<List<Amphibian>>()
    val amphibians: LiveData<List<Amphibian>> get() = _amphibians

    private var _amphibian = MutableLiveData<Amphibian>()
    val amphibian: LiveData<Amphibian> get() = _amphibian

    init {
        getAmphibianList()
    }

    private fun getAmphibianList() {
        viewModelScope.launch {
            _status.value = AmphibianApiStatus.LOADING
            try {
                _status.value = AmphibianApiStatus.DONE
                _amphibians.value = AmphibiansApi.retrofitService.getAmphibians()
            } catch (e: Exception) {
                _status.value = AmphibianApiStatus.ERROR
                _amphibians.value = listOf()
            }
        }
    }

    fun onAmphibianClicked(amphibian: Amphibian) {
        _amphibian.value = amphibian
    }
}
