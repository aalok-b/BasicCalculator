package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var lastNumeric = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {


        tvAns.append((view as Button).text)
        lastNumeric = true

    }

    fun onClear(view: View) {

        tvAns.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        if( lastNumeric && !lastDot) {

            tvAns.append(".")

            lastNumeric = false
            lastDot = true
        }
    }

    private fun isOperatorAdded(value : String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("+") || value.contains("-")
                    || value.contains("/") || value.contains("*")
                    || value.contains("%")
        }
    }

    fun onOperator( view: View){


        if(lastNumeric && !isOperatorAdded(tvAns.text.toString())){
            tvAns.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onEqual(view: View) {


        if(lastNumeric){
             var tvValue = tvAns.text.toString()
             var prefix = ""

            try {
                // check if the no is starting with " - "
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var firstNum = splitValue[0]
                    var secondNum = splitValue[1]

                    if(!prefix.isEmpty()){
                        firstNum = prefix + firstNum
                    }

                    tvAns.text = (firstNum.toDouble() - secondNum.toDouble()).toString()
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var firstNum = splitValue[0]
                    var secondNum = splitValue[1]

                    if(!prefix.isEmpty()){
                        firstNum = prefix + firstNum
                    }

                    tvAns.text = (firstNum.toDouble() + secondNum.toDouble()).toString()
                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var firstNum = splitValue[0]
                    var secondNum = splitValue[1]

                    if(!prefix.isEmpty()){
                        firstNum = prefix + firstNum
                    }

                    tvAns.text = (firstNum.toDouble() / secondNum.toDouble()).toString()
                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var firstNum = splitValue[0]
                    var secondNum = splitValue[1]

                    if(!prefix.isEmpty()){
                        firstNum = prefix + firstNum
                    }

                    tvAns.text = (firstNum.toDouble() * secondNum.toDouble()).toString()
                }else if(tvValue.contains("%")){
                    val splitValue = tvValue.split("%")
                    var firstNum = splitValue[0]
                    var secondNum = splitValue[1]

                    if(!prefix.isEmpty()){
                        firstNum = prefix + firstNum
                    }
                    var ans = (firstNum.toDouble()/100) * secondNum.toDouble()

                    tvAns.text = (ans).toString()
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }


        }

    }
}