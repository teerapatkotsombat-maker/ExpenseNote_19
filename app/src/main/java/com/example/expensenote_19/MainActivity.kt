package com.example.expensenote_19

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etAmount: EditText
    private lateinit var etNote: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnclear: Button
    private lateinit var tvLastItem: TextView
    private lateinit var tvTotal: TextView

    private var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etAmount = findViewById(R.id.TfAmount)
        etNote = findViewById(R.id.TfNote)
        btnAdd = findViewById(R.id.TfAdd)
        btnclear = findViewById(R.id.TfClear)
        tvLastItem = findViewById(R.id.TfLastItem)
        tvTotal = findViewById(R.id.TfTotal)

        btnAdd.setOnClickListener {
            val amountText = etAmount.text.toString().trim()
            val noteText = etNote.text.toString().trim()

            if (amountText.isEmpty()) {
                Toast.makeText(this, "กรุณากรอกจำนวนเงิน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(this, "กรุณากรอกจำนวนเป็นตัวเลข", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            total += amount
            val shownote = if (noteText.isNotEmpty()) noteText else "ไม่ระบุ"
            tvLastItem.text = "รายการล่าสุด: $shownote - %.2f บาท".format(amount)
            tvTotal.text = "ยอดรวมวันนี้: %.2f บาท".format(total)

            etAmount.text.clear()
            etNote.text.clear()
        }

        btnclear.setOnClickListener {
            total = 0.0
            tvLastItem.text = "รายการล่าสุด: -"
            tvTotal.text = "ยอดรวมวันนี้: 0.00 บาท"
            etAmount.text.clear()
            etNote.text.clear()
            Toast.makeText(this, "เคลียร์ข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show()
        }
    }
}
