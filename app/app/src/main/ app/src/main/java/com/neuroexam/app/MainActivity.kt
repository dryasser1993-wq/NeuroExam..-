package com.neuroexam.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*

class MainActivity : Activity() {

    private var english = false
    private lateinit var content: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun showHome() {
        content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
            setBackgroundColor(0xFFF4F7FA.toInt())
        }

        val scroll = ScrollView(this)
        scroll.addView(content)
        setContentView(scroll)

        val title = TextView(this).apply {
            text = "NeuroExam"
            textSize = 28f
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
            setTextColor(0xFF174A70.toInt())
        }
        content.addView(title)

        addButton(if (english) "العربية" else "English") {
            english = !english
            showHome()
        }

        addText(if (english) "Patient information" else "بيانات المريض", 22f)

        val name = addField(if (english) "Patient name" else "اسم المريض")
        val file = addField(if (english) "File number" else "رقم الملف")
        val age = addField(if (english) "Age" else "العمر")
        val gender = addField(if (english) "Gender" else "الجنس")

        addButton(if (english) "Save patient" else "حفظ المريض") {
            if (name.text.isBlank() || file.text.isBlank()) {
                Toast.makeText(
                    this,
                    if (english) "Enter name and file number" else "أدخل الاسم ورقم الملف",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                getSharedPreferences("NeuroExam", MODE_PRIVATE)
                    .edit()
                    .putString("patient_${file.text}", 
                        "${name.text}|${age.text}|${gender.text}")
                    .apply()

                Toast.makeText(
                    this,
                    if (english) "Patient saved" else "تم حفظ المريض",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        addButton(if (english) "Search patient" else "البحث عن مريض") {
            val saved = getSharedPreferences("NeuroExam", MODE_PRIVATE)
                .getString("patient_${file.text}", null)

            if (saved != null) {
                val parts = saved.split("|")
                name.setText(parts.getOrElse(0) { "" })
                age.setText(parts.getOrElse(1) { "" })
                gender.setText(parts.getOrElse(2) { "" })
            } else {
                Toast.makeText(
                    this,
                    if (english) "Patient not found" else "لم يتم العثور على المريض",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        addText(
            if (english) "Neurological examination" else "الفحص العصبي",
            22f
        )

        val sections = listOf(
            "Chief complaint|الشكوى الرئيسية",
            "Medical history|التاريخ المرضي",
            "Cranial nerves I-XII|الأعصاب القحفية",
            "Muscle tone|التوتر العضلي",
            "Muscle power|القوة العضلية",
            "Involuntary movements|الحركات اللاإرادية",
            "Reflexes|المنعكسات",
            "Sensation|فحص الحس",
            "Coordination and gait|التناسق والمشي",
            "Additional notes|ملاحظات إضافية"
        )

        sections.forEach {
            addField(if (english) it.substringBefore("|")
                else it.substringAfter("|"))
        }
    }

    private fun addText(value: String, size: Float) {
        val view = TextView(this).apply {
            text = value
            textSize = size
            setTextColor(0xFF174A70.toInt())
            setPadding(0, 24, 0, 12)
        }
        content.addView(view)
    }

    private fun addField(hintText: String): EditText {
        val field = EditText(this).apply {
            hint = hintText
            textSize = 16f
            setSingleLine(false)
            minHeight = 55
            setPadding(12, 8, 12, 8)
        }
        content.addView(field)
        return field
    }

    private fun addButton(label: String, action: () -> Unit) {
        val button = Button(this).apply {
            text = label
            setOnClickListener { action() }
        }
        content.addView(
            button,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
    }
}
