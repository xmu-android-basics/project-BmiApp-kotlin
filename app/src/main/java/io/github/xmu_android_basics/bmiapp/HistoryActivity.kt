package io.github.xmu_android_basics.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView



class HistoryActivity : AppCompatActivity() {
    private var historyView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        historyView = findViewById(R.id.bmi_history)

        val bmiRecord = intent.getStringExtra(Intent.EXTRA_TEXT)

        historyView!!.text = bmiRecord
    }
}
