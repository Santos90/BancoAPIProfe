package com.example.bancoapiprofe

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoapiprofe.bd.MiBancoOperacional
import com.example.bancoapiprofe.databinding.ActivityMainBinding
import com.example.bancoapiprofe.pojo.Cliente

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var txtdatos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializamos el textView
        txtdatos = binding.textView1

        val mbo: MiBancoOperacional = MiBancoOperacional.getInstance(this)!!

        // Introducimos los datos como si fuera la pantalla inicial
        Log.e(this.componentName.className, "Creando el cliente a")
        var a = Cliente()
        a.setNif("11111111A")
        a.setClaveSeguridad("1234")

        // Logueamos al cliente
        a = mbo.login(a)!!

        txtdatos.append("Datos del cliente logueado\n")
        txtdatos.append("-----------------------------------------\n")
        txtdatos.append("$a")
        txtdatos.append("\n")


        // Cambiamos la password
        txtdatos.append("Cambiamos la password del cliente\n")
        txtdatos.append("-----------------------------------------\n")
        txtdatos.append("\n")
        a.setClaveSeguridad("12345")
        val p = mbo.changePassword(a)
        txtdatos.append("Hemos obtenido tras cambiar un $p")
        txtdatos.append("\n")
        txtdatos.append("Password cambiada a 12345.\n")
        txtdatos.append("\n")


    }
}