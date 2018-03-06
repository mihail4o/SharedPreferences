package com.balivo.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    companion object {
        val MY_PREF = "THIS_IS_MY_CODE"
    }

    private lateinit var btnSave: Button
    private lateinit var btnRetrieve: Button
    private lateinit var etValue: EditText
    private lateinit var etKey: EditText
    private lateinit var etRetrieve: EditText
    private lateinit var USER_KEY: String
    private lateinit var USER_VALUE: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// setup Views
        btnSave = findViewById(R.id.btn_save)
        btnRetrieve = findViewById(R.id.btn_retrieve)
        etKey = findViewById(R.id.et_name_key)
        etValue = findViewById(R.id.et_name_value)
        etRetrieve = findViewById(R.id.et_name_retrieve)
// SharedPreferences instance
        val pref = getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)
// Editor instance
        val editor = pref.edit()
// Save button listener
        btnSave.setOnClickListener { saveValue(editor) }
// Retrieve button listener
        btnRetrieve.setOnClickListener { retrieveValue(pref) }
    }
    // This method will save the value in the SharedPreferences file
    private fun saveValue(editor: SharedPreferences.Editor) {
        USER_KEY = etKey.text.toString()
        USER_VALUE = etValue.text.toString()
        if (!TextUtils.isEmpty(USER_KEY) && !TextUtils.isEmpty(USER_VALUE)) {
            editor.putString(USER_KEY, USER_VALUE)
            editor.apply()
            Toast.makeText(this@MainActivity, "Saved", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MainActivity, "Please type a name", Toast.LENGTH_SHORT).show()
        }
    }
    // This method will retrieve the value from SharedPreferences file
    private fun retrieveValue(pref: SharedPreferences) {
        val defaultName = "balivo"
        USER_KEY = etRetrieve.text.toString()
        if (!TextUtils.isEmpty(USER_KEY)) {
            USER_VALUE = pref.getString(USER_KEY, defaultName)
            Toast.makeText(this@MainActivity, USER_VALUE, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MainActivity, "Please type in a key", Toast.LENGTH_SHORT).show()
        }
    }
}