package com.example.mykotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var btnAdd: Button
    lateinit var btnSub: Button
    lateinit var btnMultiply: Button
    lateinit var btnDivision: Button
    lateinit var btn0: Button
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var btn5: Button
    lateinit var btn6: Button
    lateinit var btn7: Button
    lateinit var btn8: Button
    lateinit var btn9: Button
    lateinit var btnDot: Button
    lateinit var btnEnter: Button
    lateinit var resultTv: TextView

    var inputA: String = ""
    var inputB: String = ""
    var currentOperation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btn_add)
        btnSub = findViewById(R.id.btn_substraction)
        btnMultiply = findViewById(R.id.btn_multiplication)
        btnDivision = findViewById(R.id.btn_division)
        btn0 = findViewById(R.id.btn_0)
        btn1 = findViewById(R.id.btn_1)
        btn2 = findViewById(R.id.btn_2)
        btn3 = findViewById(R.id.btn_3)
        btn4 = findViewById(R.id.btn_4)
        btn5 = findViewById(R.id.btn_5)
        btn6 = findViewById(R.id.btn_6)
        btn7 = findViewById(R.id.btn_7)
        btn8 = findViewById(R.id.btn_8)
        btn9 = findViewById(R.id.btn_9)
        btnDot = findViewById(R.id.btn_dot)
        btnEnter = findViewById(R.id.btn_enter)
        resultTv = findViewById(R.id.result_tv)

        btnAdd.setOnClickListener(this)
        btnSub.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnDivision.setOnClickListener(this)
        btnEnter.setOnClickListener(this)
        btnDot.setOnClickListener(this)

        val numberButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        for (btn in numberButtons) {
            btn.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9 -> {
                val button = v as Button
                val value = button.text.toString()
                if (currentOperation == null) {
                    inputA += value
                    resultTv.text = inputA
                } else {
                    inputB += value
                    resultTv.text = inputB
                }
            }
            R.id.btn_dot -> {
                if (currentOperation == null) {
                    if (!inputA.contains(".")) {
                        inputA += "."
                        resultTv.text = inputA
                    }
                } else {
                    if (!inputB.contains(".")) {
                        inputB += "."
                        resultTv.text = inputB
                    }
                }
            }
            R.id.btn_add -> setOperation("+")
            R.id.btn_substraction -> setOperation("-")
            R.id.btn_multiplication -> setOperation("*")
            R.id.btn_division -> setOperation("/")
            R.id.btn_enter -> calculate()
        }
    }

    private fun setOperation(operation: String) {
        if (inputA.isNotEmpty() && inputB.isEmpty()) {
            currentOperation = operation
            resultTv.text = operation
        }
    }

    private fun calculate() {
        if (inputA.isNotEmpty() && inputB.isNotEmpty() && currentOperation != null) {
            val a = inputA.toDouble()
            val b = inputB.toDouble()
            val result = when (currentOperation) {
                "+" -> a + b
                "-" -> a - b
                "*" -> a * b
                "/" -> a / b
                else -> 0.0
            }
            resultTv.text = result.toString().removeSuffix(".0")
            reset()
        }
    }

    private fun reset() {
        inputA = ""
        inputB = ""
        currentOperation = null
    }
}