// CountryVM.kt

package com.example.countrymaster

import CountryDataSource
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrymaster.networking.CountryInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.countrymaster.data.Result

interface CountryViewModel {
    val countryInfo: StateFlow<Result<CountryInfo>>
    val countryName: StateFlow<String?>

    fun fetchCountryInfoByName(name: String)
}

class CountryVM(application: Application) : AndroidViewModel(application), CountryViewModel {

    private val context: Context = application.applicationContext

    private val _countryInfo = MutableStateFlow<Result<CountryInfo>>(Result.Loading)
    override val countryInfo: StateFlow<Result<CountryInfo>> get() = _countryInfo.asStateFlow()

    private val countryDataSource = CountryDataSource

    private val _countryName = MutableStateFlow<String?>(null)
    override val countryName: StateFlow<String?> = _countryName.asStateFlow()

    init {
        // You can load a default country or leave it blank based on your use case
    }

    override fun fetchCountryInfoByName(name: String) {
        viewModelScope.launch {
            _countryInfo.value = Result.Loading

            try {
                val result = countryDataSource.getCountryInfoByName(name)
                if (result is Result.Success) {
                    val countryInfo = result.data
                    _countryName.value = countryInfo.officialName
                    _countryInfo.value = Result.Success(countryInfo)
                    // Save country information to storage if needed
                    // CountryInfoStorage.saveCountryInfo(context, countryInfo)
                } else {
                    _countryInfo.value = Result.Error(Exception("Failed to fetch country information"))
                }
            } catch (e: Exception) {
                _countryInfo.value = Result.Error(e)
                Log.e("FetchCountryInfo", "Error fetching information for country: $name", e)
            }
        }
    }
}
