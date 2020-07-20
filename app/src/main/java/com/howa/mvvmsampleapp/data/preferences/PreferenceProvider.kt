package com.howa.mvvmsampleapp.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val KEY_SAVE_AT = "saveAt"

class PreferenceProvider(context: Context) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(
            appContext
        )

    fun saveLastSavedAt(saveAt: String) {
        preference.edit().putString(KEY_SAVE_AT, saveAt).apply()
    }

    private fun getLastSaveAt(): String? {
        return preference.getString(KEY_SAVE_AT, null)
    }
}