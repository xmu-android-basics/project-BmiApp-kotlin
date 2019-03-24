package io.github.xmu_android_basics.bmiapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {
    companion object {
        const val BMI_DATE = "BMI_DATE"
        const val BMI_WEIGHT = "BMI_WEIGHT"
        const val BMI_BMI = "BMI_BMI"
        val history: MutableList<String> = ArrayList()
    }

    private var historyView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        historyView = findViewById(R.id.bmi_history)

        val bundle = intent.extras
        val bmiDate = bundle.getString(BMI_DATE)
        val weight = bundle.getDouble(BMI_WEIGHT, 0.0)
        val bmi = bundle.getDouble(BMI_BMI, 0.0)

        history.add(bmiDate + ": " + weight + "kg, BMI: " + bmi);

        renderHistory()
    }

    private fun renderHistory() {
        val buffer = StringBuffer();

        for (bmiRecord in history) {
            buffer.insert(0, bmiRecord + "\n");
        }

        historyView!!.text = buffer
    }
}
