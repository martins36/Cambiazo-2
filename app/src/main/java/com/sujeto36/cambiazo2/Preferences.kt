package com.sujeto36.cambiazo2

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat
import android.widget.TextView

class Preferences(context: Context) {

    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val context2: Context = context

    fun getCoinValue(): Float {
        return sharedPreferences.getString(COIN_VALUE, "1").toFloat()
    }

    fun setCoinName(textView: TextView){
        val name: String? = sharedPreferences.getString(COIN_NAME, null)
        textView.text = name
    }

    fun setColor(textView: TextView) {
        val color: String? = sharedPreferences.getString(COLORS, "2")
        when (color) {
            "0" -> {
                textView.setTextColor(ContextCompat.getColor(context2, R.color.yellow))
            }
            "1" -> {
                textView.setTextColor(ContextCompat.getColor(context2, R.color.orange))
            }
            "2" -> {
                textView.setTextColor(ContextCompat.getColor(context2, R.color.red))
            }
            "3" -> {
                textView.setTextColor(ContextCompat.getColor(context2, R.color.green))
            }
        }
    }

    fun isThemeDark(): Boolean {
        return sharedPreferences.getBoolean(DARK_THEME, false)
    }

    companion object {
        const val COIN_VALUE = "preference_coin_value"
        const val COIN_NAME = "preference_coin_name"
        const val DARK_THEME = "preference_dark_theme"
        const val COLORS = "preference_colors"
    }
}