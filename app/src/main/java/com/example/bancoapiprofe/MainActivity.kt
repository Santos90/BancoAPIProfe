package com.example.bancoapiprofe

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bancoapiprofe.bd.MiBancoOperacional
import com.example.bancoapiprofe.databinding.ActivityMainBinding
import com.example.bancoapiprofe.pojo.Cliente
import com.example.bancoapiprofe.pojo.Cuenta
import com.example.bancoapiprofe.pojo.Movimiento


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var txtdatos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializamos el textView
        txtdatos = binding.textView1

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        // Introducimos los datos como si fuera la pantalla inicial
        Log.e(this.componentName.className, "Creando el cliente a")
        var a = Cliente()
        a.setNif("11111111A")
        a.setClaveSeguridad("1234")

        // Logueamos al cliente
        val resultado = mbo?.login(a) ?: -1
        if(resultado == -1) {
            txtdatos.append("Datos erróneos, el cliente no se ha podido loguear\n")
        }else{
            txtdatos.append("Datos del cliente logueado\n")
            txtdatos.append("-----------------------------------------\n")
            txtdatos.append("$a")
            txtdatos.append("\n")
        }



        // Cambiamos la password
        txtdatos.append("Cambiamos la password del cliente\n")
        txtdatos.append("-----------------------------------------\n")
        txtdatos.append("\n")
        a.setClaveSeguridad("12345")
        val p = mbo?.changePassword(a)
        txtdatos.append("Hemos obtenido tras cambiar un $p")
        txtdatos.append("\n")
        txtdatos.append("Password cambiada a 12345.\n")
        txtdatos.append("\n")


        // Creamos un nuevo cliente e intentamos loguearnos con la clave anterior
        txtdatos.append("Intentamos loguear el cliente con la password inicial, que era 1234\n")
        txtdatos.append("---------------------------------------------------------------------------------\n")
        txtdatos.append("\n")
        var b: Cliente? = Cliente()
        b?.setNif("11111111A")
        b?.setClaveSeguridad("1234")

        val resultado2 = b?.let { mbo?.login(it) } ?: -1
        if(resultado2 == -1) {
            txtdatos.append("Datos erróneos, el cliente no se ha podido loguear\n")
        }else{
            txtdatos.append("Datos del cliente logueado\n")
            txtdatos.append("-----------------------------------------\n")
            txtdatos.append("$b")
            txtdatos.append("\n")
        }



        // Volvemos a dejar la password como estaba y nos logueamos de nuevo
        txtdatos.append("Volvemos a dejar la password como estaba y nos logueamos de nuevo\n")
        txtdatos.append("---------------------------------------------------------------------------------\n")
        txtdatos.append("\n")
        var c: Cliente? = Cliente()
        c?.setNif("11111111A")
        c?.setClaveSeguridad("12345")
        Log.e("CLIENTE", c?.getClaveSeguridad().toString())


        val resultado3 = c?.let { mbo?.login(it) } ?: -1
        if(resultado3 == -1) {
            txtdatos.append("Datos erróneos, el cliente no se ha podido loguear\n")
        }else{
            txtdatos.append("Datos del cliente logueado\n")
            txtdatos.append("-----------------------------------------\n")
            txtdatos.append("$c")
            txtdatos.append("\n")
        }


        c?.setClaveSeguridad("1234")
        mbo?.changePassword(c)
        txtdatos.append("Password cambiada a 1234. Nos logueamos de nuevo.\n")
        txtdatos.append("\n")

        var d: Cliente? = Cliente()
        d?.setNif("11111111A")
        d?.setClaveSeguridad("1234")

        // logueamos al cliente

        // logueamos al cliente
        d = d?.let { mbo?.login(it) }

        txtdatos.append("Datos del cliente logueado\n")
        txtdatos.append("-----------------------------------------\n")
        txtdatos.append(
            """
                ${d.toString()}
                
                """.trimIndent()
        )
        txtdatos.append("\n")
        Log.e("CLIENTELOGIN", d.toString())

        txtdatos.append("Obtenemos la lista de cuentas del cliente logueado.\n")
        txtdatos.append("------------------------------------------------------------------------------\n")
        val listaCuentas: ArrayList<Cuenta>? = mbo?.getCuentas(d) as ArrayList<Cuenta>?
        Log.e("LISTACUENTAS", listaCuentas?.size.toString())
        // Verificar si la lista no es nula
        if (listaCuentas != null) {
            // Usar un bucle for para recorrer la lista
            for (cuenta in listaCuentas) {
                // Aquí puedes acceder a las propiedades de la cuenta y mostrar su contenido
                txtdatos.append("Número de cuenta: ${cuenta.getNumeroCuenta()} ")
                txtdatos.append(" Saldo: ${cuenta.getSaldoActual()}\n\n")

            }
        } else {
            println("La lista de cuentas es nula")
        }
        txtdatos.append("\n")
        Log.e("LISTACUENTA", listaCuentas?.get(0).toString())

        txtdatos.append("Obtenemos la lista de movimientos de la primera cuenta del cliente logueado.\n")
        txtdatos.append("----------------------------------------------------------------------------------------------------\n")
        val listaMovimientos: ArrayList<Movimiento>? = mbo?.getMovimientos(listaCuentas?.get(0)) as ArrayList<Movimiento>?
        Log.e("LISTAMOVIMIENTOS", listaMovimientos?.size.toString())
        if (listaMovimientos != null) {
            // Usar un bucle for para recorrer la lista
            for (movimiento in listaMovimientos) {
                // Aquí puedes acceder a las propiedades de la cuenta y mostrar su contenido
                txtdatos.append("Movimiento: ${movimiento.toString()}\n\n")
            }
        } else {
            println("La lista de movimientos es nula")
        }



    }
}