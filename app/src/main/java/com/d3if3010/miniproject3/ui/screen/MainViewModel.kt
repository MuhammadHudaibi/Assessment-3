package com.d3if3010.miniproject3.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if3010.miniproject3.model.Makanan
import com.d3if3010.miniproject3.network.ApiStatus
import com.d3if3010.miniproject3.network.MakananApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MakananViewModel : ViewModel() {

    private val _status = MutableStateFlow(ApiStatus.LOADING)
    val status: StateFlow<ApiStatus> = _status

    private val _makanan = MutableStateFlow(emptyList<Makanan>())
    val makanan: StateFlow<List<Makanan>> = _makanan

    init {
        getMakananList()
    }

    fun getMakananList() {
        viewModelScope.launch(Dispatchers.IO) {
            _status.value = ApiStatus.LOADING
            try {
                val response = MakananApi.service.getMakanan()
                _makanan.value = response
                _status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = ApiStatus.FAILED
                _makanan.value = emptyList()
            }
        }
    }

    fun addMakanan(makanan: Makanan) {
        viewModelScope.launch {
            try {
                MakananApi.service.addMakanan(makanan)
                getMakananList()
            } catch (e: Exception) {
                _status.value = ApiStatus.FAILED
            }
        }
    }

    fun deleteMakanan(id: String) {
        viewModelScope.launch {
            try {
                MakananApi.service.deleteMakanan(id)
                getMakananList()
            } catch (e: Exception) {
                _status.value = ApiStatus.FAILED
            }
        }
    }
}