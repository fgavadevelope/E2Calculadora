package com.paula.e2calculadora

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.paula.e2calculadora.databinding.ActivityCalculadoraBinding


class CalculadoraActivity : AppCompatActivity() {

    // Declaramos la variable del binding
    private lateinit var binding: ActivityCalculadoraBinding
    // Variables para calcular
    var currentInput = ""
    var previousInput = ""
    var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializamos el binding
        binding = ActivityCalculadoraBinding.inflate(layoutInflater)

        // Establecemos el contenido de la actividad utilizando el binding
        setContentView(binding.root)



        // Botones de números
        binding.button0.setOnClickListener { appendNumber("0") }
        binding.button1.setOnClickListener { appendNumber("1") }
        binding.button2.setOnClickListener { appendNumber("2") }
        binding.button3.setOnClickListener { appendNumber("3") }
        binding.button4.setOnClickListener { appendNumber("4") }
        binding.button5.setOnClickListener { appendNumber("5") }
        binding.button6.setOnClickListener { appendNumber("6") }
        binding.button7.setOnClickListener { appendNumber("7") }
        binding.button8.setOnClickListener { appendNumber("8") }
        binding.button9.setOnClickListener { appendNumber("9") }
        binding.buttonPunto.setOnClickListener { appendPunto() }

        // Botones de operaciones
        binding.buttonPlus.setOnClickListener { setOperador("+") }
        binding.buttonMinus.setOnClickListener { setOperador("-") }
        binding.buttonMultiply.setOnClickListener { setOperador("×") }
        binding.buttonDivide.setOnClickListener { setOperador("÷") }

        // Botón de igual
        binding.buttonEqual.setOnClickListener { calculateResult() }

        // Botón de borrar (C)
        binding.buttonClear.setOnClickListener {
            currentInput = ""
            previousInput = ""
            operator = null
            binding.display.text = "0"
        }
    }
        // Función para añadir números al display
        fun appendNumber(number: String) {
            currentInput += number
            binding.display.text = currentInput
        }
    private fun CalculadoraActivity.appendPunto() {
        if (!currentInput.contains(".")) { appendNumber(".")}
    }

        // Función para establecer el operador
        fun setOperador(op: String) {
            if (currentInput.isNotEmpty()) {
                previousInput = currentInput
                currentInput = ""
                operator = op
            }
        }

        // Función para calcular el resultado
        fun calculateResult() {
            if (previousInput.isNotEmpty() && currentInput.isNotEmpty() && operator != null) {
                val num1 = previousInput.toDouble()
                val num2 = currentInput.toDouble()
                val result = when (operator) {
                    "+" -> num1 + num2
                    "-" -> num1 - num2
                    "×" -> num1 * num2
                    "÷" -> if (num2 != 0.0) num1 / num2 else {
                        Toast.makeText(this, "Error: División por cero", Toast.LENGTH_SHORT).show()
                        return
                    }
                    else -> 0.0
                }
                binding.display.text = result.toString()
                currentInput = result.toString()
                previousInput = ""
                operator = null
            }
        }
    }






