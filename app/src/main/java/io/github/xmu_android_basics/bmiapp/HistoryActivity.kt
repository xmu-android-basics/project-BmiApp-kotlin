package io.github.xmu_android_basics.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView


class HistoryActivity : AppCompatActivity() {
    companion object {
        const val BMI_DATE = "BMI_DATE"
        const val BMI_WEIGHT = "BMI_WEIGHT"
        const val BMI_BMI = "BMI_BMI"
    }

    private var historyView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        historyView = findViewById(R.id.bmi_history)

        val bmiDate = intent.getStringExtra(Companion.BMI_DATE)
        val weight = intent.getDoubleExtra(Companion.BMI_WEIGHT, 0.0)
        val bmi = intent.getDoubleExtra(Companion.BMI_BMI, 0.0)

        historyView!!.text = "$bmiDate: ${weight}kg, BMI: $bmi"
    }
}
