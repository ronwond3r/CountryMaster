// CountryInfoStorage.kt
package com.example.countrymaster.data

import android.content.Context
import android.content.SharedPreferences
import com.example.countrymaster.networking.CountryInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CountryInfoStorage {
    private const val PREFS_NAME = "CountryInfoPrefs"
    private const val KEY_COUNTRY_NAME = "country_name"
    private const val KEY_COUNTRY_INFO = "country_info"

    fun saveCountryInfo(context: Context, countryInfo: CountryInfo) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()

        editor.putString(KEY_COUNTRY_NAME, countryInfo.officialName)

        // Convert country info to JSON and store as a string
        val countryInfoJson = Gson().toJson(countryInfo)
        editor.putString(KEY_COUNTRY_INFO, countryInfoJson)

        editor.apply()
    }

    fun getSavedCountryInfo(context: Context): CountryInfo? {
        val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val countryName = prefs.getString(KEY_COUNTRY_NAME, null)
        val countryInfoJson = prefs.getString(KEY_COUNTRY_INFO, null)

        return if (countryName != null && countryInfoJson != null) {
            // Convert JSON string back to object
            val countryInfoType = object : TypeToken<CountryInfo>() {}.type
            val countryInfo = Gson().fromJson<CountryInfo>(countryInfoJson, countryInfoType)

            // Check if the stored country name matches the retrieved country name
            if (countryName == countryInfo.officialName) {
                countryInfo
            } else {
                null
            }
        } else {
            null
        }
    }
}
