package com.example.conversor233342

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private enum class Tipo { LONGITUD, TEMPERATURA, PESO, VOLUMEN }

    private lateinit var btnLong: Button
    private lateinit var btnTemp: Button
    private lateinit var btnPeso: Button
    private lateinit var btnVol: Button
    private lateinit var etValor: EditText
    private lateinit var btnConvertir: Button
    private lateinit var tvResultado: TextView

    private var tipoSeleccionado: Tipo = Tipo.LONGITUD

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       btnLong = findViewById(R.id.longitudBoton)
       btnTemp = findViewById(R.id.tempBoton)
       btnPeso = findViewById(R.id.pesoBoton)
       btnVol = findViewById(R.id.volBoton)
       etValor = findViewById(R.id.textoConversor)
       btnConvertir = findViewById(R.id.botonConversor)
       tvResultado = findViewById(R.id.resultadoConversion)
       // Elegir tipo de conversión
       btnLong.setOnClickListener { tipoSeleccionado = Tipo.LONGITUD; tvResultado.text = "Convertir Km a millas" }
       btnTemp.setOnClickListener { tipoSeleccionado = Tipo.TEMPERATURA; tvResultado.text = "Convertir °C a °F" }
       btnPeso.setOnClickListener { tipoSeleccionado = Tipo.PESO; tvResultado.text = "Convertir Kg a libras" }
       btnVol.setOnClickListener { tipoSeleccionado = Tipo.VOLUMEN; tvResultado.text = "Convertir L a onzas" }

       // Botón convertir
       btnConvertir.setOnClickListener {
           val valor = etValor.text.toString().toDoubleOrNull()
           if (valor == null) {
               etValor.error = "Ingresa un número primero"
               return@setOnClickListener
           }

           val resultado = when (tipoSeleccionado) {
               Tipo.LONGITUD -> "${valor} km = ${redondear(valor * 0.621371)} mi"
               Tipo.TEMPERATURA -> "${valor} °C = ${redondear((valor * 9/5) + 32)} °F"
               Tipo.PESO -> "${valor} kg = ${redondear(valor * 2.20462)} lb"
               Tipo.VOLUMEN -> "${valor} L = ${redondear(valor * 33.814)} oz"
           }

           tvResultado.text = resultado
       }

   }
    private fun redondear(num: Double): String {
        return String.format("%.2f", num)
    }
   }





