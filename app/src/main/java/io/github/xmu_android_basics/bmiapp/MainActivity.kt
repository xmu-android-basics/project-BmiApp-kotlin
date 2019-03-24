package io.github.xmu_android_basics.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.net.Uri


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
        val weightInput = weightWidget!!.text.toString()

        startHistoryActivity(weightInput.toDouble(), bmi);
    }

    fun onShareClick(view: View) {

        if (!bmiResult.isEmpty()) {
            // TODO 实验1.1
//            sendText(bmiResult);

            // TODO 实验1.2
//            sendTextWithSubject(bmiResult);

            // TODO 实验1.3
            sendTextAsMail(bmiResult);

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

    private fun sendText(text: String) {
        // 实验1.1：使用隐式 Intent 发送文本
        // 使用 ACTION_SEND
        // 附加 EXTRA_TEXT
        // 设置 Type 为 "text/plain"
        //
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        intent.type = "text/plain"

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "没有应用支持这个 Intent", Toast.LENGTH_SHORT).show()
        }

    }

    private fun sendTextWithSubject(text: String) {
        // 实验1.2：使用隐式 Intent 发送带标题的文本
        // 使用 ACTION_SEND
        // 附加 EXTRA_SUBJECT 和 EXTRA_TEXT
        // 设置 Type 为 "text/plain"
        //
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_SUBJECT, "BMI result of " + whatDateIsToday())
        intent.putExtra(Intent.EXTRA_TEXT, text)
        intent.type = "text/plain"

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "没有应用支持这个 Intent", Toast.LENGTH_SHORT).show()
        }

    }

    private fun sendTextAsMail(text: String) {
        // 实验1.3：使用隐式 Intent 发送邮件
        // 使用 ACTION_SEND
        // 附加 EXTRA_SUBJECT 和 EXTRA_TEXT
        // 可选附加 EXTRA_MAIL, Intent.EXTRA_CC, Intent.EXTRA_BCC
        //
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_SUBJECT, "BMI result of " + whatDateIsToday())
        intent.putExtra(Intent.EXTRA_TEXT, text)

        if (intent.resolveActivity(packageManager) != null) {
            Toast.makeText(this, "start", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        } else {
            Toast.makeText(this, "没有应用支持这个 Intent", Toast.LENGTH_SHORT).show()
        }

    }

    private fun startHistoryActivity(weight: Double, bmi: Double) {
        // TODO 实验2 打开 HistoryActivity.class
        val historyIntent = Intent(this, HistoryActivity::class.java)
        historyIntent.putExtra("BMI_DATE", whatDateIsToday());
        historyIntent.putExtra("BMI_WEIGHT", weight);
        historyIntent.putExtra("BMI_BMI", bmi);

        startActivity(historyIntent)
    }
}
