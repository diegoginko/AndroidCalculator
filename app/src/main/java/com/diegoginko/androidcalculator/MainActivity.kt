package com.diegoginko.androidcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var resultado : EditText
    private lateinit var numeroNuevo : EditText
    private val mostrarOperacion by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.tvOperacion) }

    private var operador1 : Double? = null
    private var operador2 : Double = 0.0
    private var operacionPendiente : String = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //seteo el toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        //habilito el home
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        resultado = findViewById(R.id.etResultado)
        numeroNuevo = findViewById(R.id.etCalculo)

        //Botones numericos

        val btnNro0 : Button = findViewById(R.id.btnNro0)
        val btnNro1 : Button = findViewById(R.id.btnNro1)
        val btnNro2 : Button = findViewById(R.id.btnNro2)
        val btnNro3 : Button = findViewById(R.id.btnNro3)
        val btnNro4 : Button = findViewById(R.id.btnNro4)
        val btnNro5 : Button = findViewById(R.id.btnNro5)
        val btnNro6 : Button = findViewById(R.id.btnNro6)
        val btnNro7 : Button = findViewById(R.id.btnNro7)
        val btnNro8 : Button = findViewById(R.id.btnNro8)
        val btnNro9 : Button = findViewById(R.id.btnNro9)
        val btnPuntoDecimal : Button = findViewById(R.id.btnPuntoDecimal)

        //Botones de operaciones

        val btnResultado = findViewById<Button>(R.id.btnResultado)
        val btnSuma = findViewById<Button>(R.id.btnSuma)
        val btnResta = findViewById<Button>(R.id.btnResta)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val btnMultiplica = findViewById<Button>(R.id.btnMultiplica)

        val listener = View.OnClickListener { view ->
            val b : Button = view as Button //casteo el view como si fuera un boton para que si o si tenga getText
            numeroNuevo.append(b.text) //Hace getText al boton para saber le nombre

        }

        btnNro0.setOnClickListener(listener)
        btnNro1.setOnClickListener(listener)
        btnNro2.setOnClickListener(listener)
        btnNro3.setOnClickListener(listener)
        btnNro4.setOnClickListener(listener)
        btnNro5.setOnClickListener(listener)
        btnNro6.setOnClickListener(listener)
        btnNro7.setOnClickListener(listener)
        btnNro8.setOnClickListener(listener)
        btnNro9.setOnClickListener(listener)
        btnPuntoDecimal.setOnClickListener(listener)

        val opListener = View.OnClickListener { view ->
            val operacion = (view as Button).text.toString()

            try{
                val valor = numeroNuevo.text.toString().toDouble()
                //Hacer operacion
                realizarCalculo(valor, operacion)
            }catch (e : NumberFormatException){
                numeroNuevo.setText("")
            }
            operacionPendiente = operacion
            mostrarOperacion.text = operacionPendiente
        }

        btnSuma.setOnClickListener(opListener)
        btnResta.setOnClickListener(opListener)
        btnMultiplica.setOnClickListener(opListener)
        btnDivide.setOnClickListener(opListener)
        btnResultado.setOnClickListener(opListener)

    }

    private fun realizarCalculo( valor : Double, operacion : String){
        //Asigno el valor a alguna de las variables
        if(operador1 == null){
            operador1 = valor
        }else{
            operador2 = valor
        }

        //Seteo la operacion
        if(operacionPendiente == "="){
            operacionPendiente = operacion
        }

        //realizo la accion segun la operacion
        when(operacionPendiente){
            //El !! es para saltar la verificacion de nulo.

            "=" -> operador1 = operador2
            "/" -> if(operador2 == 0.0){
                    operador1 = Double.NaN //No s epuede dividir por cero
                }else{
                     operador1 = operador1!! / operador2
                }
            "*" -> operador1 = operador1!! * operador2
            "+" -> operador1 = operador1!! + operador2
            "-" -> operador1 = operador1!! - operador2

        }

        etResultado.setText(operador1.toString())
        numeroNuevo.setText("")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.configurar -> {
            Toast.makeText(this,"Configurar",Toast.LENGTH_LONG).show()
            true
        }
        android.R.id.home ->{
            Toast.makeText(this,"Home", Toast.LENGTH_LONG).show()
            true
        }

        else -> {
            // Si se llega aca es por que no se reconoce la accion, invoco el default para que lo maneje
            super.onOptionsItemSelected(item)
        }
    }
}
