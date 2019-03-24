package io.github.xmu_android_basics.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private var weightWidget: EditText? = null
    private var heightWidget: EditText? = null
    private var resultWidget: TextView? = null

    private var bmi: Double = 0.0
    private var bmiResult = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weightWidget = findViewById(R.id.input_weight)
        heightWidget = findViewById(R.id.input_height)
        resultWidget = findViewById(R.id.output_result)

    }

    fun onComputeClick(view: View) {
        val weightInput = weightWidget!!.text.toString()
        val heightInput = heightWidget!!.text.toString()

        if (weightInput.isEmpty()) {
            Toast.makeText(this, R.string.input_weight_hint, Toast.LENGTH_SHORT).show()
            return
        }

        if (heightInput.isEmpty()) {
            Toast.makeText(this, R.string.input_height_hint, Toast.LENGTH_SHORT).show()
            return
        }

        val weight = weightInput.toDouble()
        val height = heightInput.toDouble()

        bmi = computeBmi(weight, height)

        bmiResult = generateResult(weight, bmi)

        resultWidget!!.text = bmiResult

    }

    fun onHistoryClick(view: View) {
        // TODO Show BMI record history
    }

    fun onShareClick(view: View) {

        if (! bmiResult.isEmpty()) {
            // TODO Share BMI things with...
        }

    }

    private fun computeBmi(weight: Double, height: Double): Double {
        return when {
            height.equals(0) -> 0.0
            else -> weight / (height * height)
        }
    }

    private fun whatDateIsToday(): String {
        return SimpleDateFormat.getDateTimeInstance().format(Date())
    }

    private fun generateResult(weight: Double?, bmi: Double?): String {
        return whatDateIsToday() + "，体重是：$weight，BMI 指数是：$bmi"
    }
}
