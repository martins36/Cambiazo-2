package com.sujeto36.cambiazo2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences
    private var coinValue: Float = 1.toFloat()
    private var order: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = Preferences(this)
        coinValue = preferences.getCoinValue()

        preferences.setCoinName(text_view_coin)
        preferences.setColor(text_view_coin)

        themeColor()

        edit_text_coin.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                convertCoin()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        }
        )

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                finish()
                true
            }
            R.id.menu_swap -> {
                if (order) {
                    order = false
                    coinValue = 1 / preferences.getCoinValue()
                    preferences.setCoinName(text_view_arg)
                    preferences.setColor(text_view_arg)
                    text_view_coin.text = getText(R.string.text_arg)
                    text_view_coin.setTextColor(ContextCompat.getColor(this, R.color.colorArg))
                    edit_text_coin.setText("")
                    text_view_arg_val.text = getText(R.string.initial)
                }
                else {
                    order = true
                    coinValue = preferences.getCoinValue()
                    preferences.setCoinName(text_view_coin)
                    preferences.setColor(text_view_coin)
                    text_view_arg.text = getText(R.string.text_arg)
                    text_view_arg.setTextColor(ContextCompat.getColor(this, R.color.colorArg))
                    edit_text_coin.setText("")
                    text_view_arg_val.text = getText(R.string.initial)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun convertCoin(){
        if (edit_text_coin.text.toString() == "" || edit_text_coin.text.toString() == ".")
            text_view_arg_val.text = getText(R.string.initial)
        else
            text_view_arg_val.text = String.format("%.2f", coinValue * (edit_text_coin.text).toString().toFloat())
    }

    private fun themeColor(){
        if (preferences.isThemeDark()) {
            linear_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.blackThemeDark))
            symbol1.setTextColor(ContextCompat.getColor(this, R.color.blackText))
            text_view_arg_val.setTextColor(ContextCompat.getColor(this, R.color.blackText))
            symbol2.setTextColor(ContextCompat.getColor(this, R.color.blackText))
            edit_text_coin.setTextColor(ContextCompat.getColor(this, R.color.blackText))
        }
        else {
            linear_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            symbol1.setTextColor(ContextCompat.getColor(this, R.color.whiteText))
            text_view_arg_val.setTextColor(ContextCompat.getColor(this, R.color.whiteText))
            symbol2.setTextColor(ContextCompat.getColor(this, R.color.whiteText))
            edit_text_coin.setTextColor(ContextCompat.getColor(this, R.color.whiteText))
        }
    }
}
