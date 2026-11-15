
package com.neuroexam.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private val blue = Color.rgb(13, 65, 125)
    private val lightBlue = Color.rgb(235, 243, 252)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scroll = ScrollView(this)
        scroll.setBackgroundColor(Color.rgb(246, 249, 253))

        val main = LinearLayout(this)
        main.orientation = LinearLayout.VERTICAL
        main.setPadding(20, 28, 20, 24)
        main.layoutDirection = LinearLayout.LAYOUT_DIRECTION_RTL

        val header = TextView(this)
        header.text = "🧠  الجراحة العصبية\nNeurosurgery"
        header.textSize = 27f
        header.setTextColor(Color.WHITE)
        header.gravity = Gravity.CENTER
        header.setPadding(12, 35, 12, 35)
        header.setBackgroundColor(blue)
        main.addView(header)

        val subtitle = TextView(this)
        subtitle.text = "نظام إدارة المرضى والفحوصات والعمليات"
        subtitle.textSize = 16f
        subtitle.setTextColor(blue)
        subtitle.gravity = Gravity.CENTER
        subtitle.setPadding(8, 22, 8, 22)
        main.addView(subtitle)

        addSection(main, "إدارة المرضى")
        addCard(main, "👤  ملف المرضى", "إضافة المرضى والبحث عن ملفاتهم")
        addCard(main, "🩺  الفحص العصبي", "فحص عصبي تفصيلي عربي وإنجليزي")
        addCard(main, "📋  الزيارات والمتابعة", "سجل الزيارات والتطور السريري")

        addSection(main, "الجراحة والعمليات")
        addCard(main, "🏥  العمليات الجراحية", "تسجيل العمليات ومتابعة حالتها")
        addCard(main, "👨‍⚕️  الأخصائيون والمقيمون", "إدارة الفريق والمشاركين")
        addCard(main, "📊  الإحصائيات", "إحصائيات يومية وشهرية")

        addSection(main, "إدارة النظام")
        addCard(main, "📄  التقارير", "التقارير الطبية والجراحية")
        addCard(main, "☁️  الربط الجماعي", "حسابات المستخدمين والمزامنة")
        addCard(main, "⚙️  الإعدادات", "اللغة والنسخ الاحتياطي")

        val footer = TextView(this)
        footer.text = "Neurosurgery • Beta 1.0"
        footer.textSize = 13f
        footer.setTextColor(blue)
        footer.gravity = Gravity.CENTER
        footer.setPadding(10, 28, 10, 15)
        main.addView(footer)

        scroll.addView(main)
        setContentView(scroll)
    }

    private fun addSection(
        parent: LinearLayout,
        title: String
    ) {
        val text = TextView(this)
        text.text = title
        text.textSize = 20f
        text.setTextColor(blue)
        text.setPadding(5, 25, 5, 12)
        text.gravity = Gravity.RIGHT
        parent.addView(text)
    }

    private fun addCard(
        parent: LinearLayout,
        title: String,
        description: String
    ) {
        val card = LinearLayout(this)
        card.orientation = LinearLayout.VERTICAL
        card.setPadding(18, 17, 18, 17)
        card.setBackgroundColor(Color.WHITE)
        card.elevation = 3f

        val name = TextView(this)
        name.text = title
        name.textSize = 18f
        name.setTextColor(blue)

        val detail = TextView(this)
        detail.text = description
        detail.textSize = 14f
        detail.setTextColor(Color.DKGRAY)
        detail.setPadding(0, 8, 0, 0)

        card.addView(name)
        card.addView(detail)

        card.setOnClickListener {
            Toast.makeText(
                this,
                "سيتم تفعيل هذا القسم في المرحلة التالية",
                Toast.LENGTH_SHORT
            ).show()
        }

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 5, 0, 10)
        parent.addView(card, params)
    }
}
