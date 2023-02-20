package com.example.practica7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var dato: EditText
    private lateinit var info: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dato = findViewById(R.id.et_dato)
        info = findViewById(R.id.tv_info)

        info.text = leer()

    }

    fun aceptar(view: View) {
        guardar(dato.text.toString())
        info.text = leer()
    }

    private fun guardar(texto: String) {
        try {
            val ruta: String? = baseContext.getExternalFilesDir(null)?.absolutePath
            val carpeta = File(ruta, "carpeta")
            if (!carpeta.exists()) {
                carpeta.mkdir()
            }
            val archivo = File(carpeta, "datos.txt")
            archivo.appendText(texto+"\n")
        } catch (e: Exception) {
            Toast.makeText(this, "Error de archivo", Toast.LENGTH_SHORT).show()
        }
    }

    private fun leer(): String {
        var texto = ""
        try {
            val ruta = baseContext.getExternalFilesDir(null)?.absolutePath
            val carpeta = File(ruta, "carpeta")
            val archivo = File(carpeta, "datos.txt")
            val buffer = BufferedReader(InputStreamReader(FileInputStream(archivo)))
            texto = buffer.use(BufferedReader::readText)
        } catch (e: Exception) {
            Toast.makeText(this, "Error de archivo", Toast.LENGTH_SHORT).show()
        }
        return texto
    }
}