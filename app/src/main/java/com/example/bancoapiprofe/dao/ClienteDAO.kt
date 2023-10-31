package com.example.bancoapiprofe.dao

import android.content.ContentValues
import android.database.Cursor
import android.text.TextUtils
import com.example.bancoapiprofe.bd.MiBD
import com.example.bancoapiprofe.pojo.Cliente
import java.lang.String
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.arrayOf

class ClienteDAO : PojoDAO {

    override fun add(obj: Any?): Long {
        val contentValues = ContentValues()
        val c: Cliente = obj as Cliente
        contentValues.put("nif", c.getNif())
        contentValues.put("nombre", c.getNombre())
        contentValues.put("apellidos", c.getApellidos())
        contentValues.put("claveSeguridad", c.getClaveSeguridad())
        contentValues.put("email", c.getEmail())
        return MiBD.dB?.insert("clientes", null, contentValues) ?: -1
    }

    override fun update(obj: Any?): Int? {
        val contentValues = ContentValues()
        val c: Cliente = obj as Cliente
        contentValues.put("nif", c.getNif())
        contentValues.put("nombre", c.getNombre())
        contentValues.put("apellidos", c.getApellidos())
        contentValues.put("claveSeguridad", c.getClaveSeguridad())
        contentValues.put("email", c.getEmail())
        val condicion = if (TextUtils.isEmpty(c.getNif())) {
            "id=" + String.valueOf(c.getId())
        } else {
            "nif=" + "'" + c.getNif() + "'"
        }
        return MiBD.dB?.update("clientes", contentValues, condicion, null)
    }

    override fun delete(obj: Any?) {
        val c: Cliente = obj as Cliente
        val condicion = "id=" + String.valueOf(c.getId())

        //Se borra el cliente indicado en el campo de texto
        MiBD.dB?.delete("clientes", condicion, null)
    }

    override fun search(obj: Any?): Any? {
        val c: Cliente = obj as Cliente
        var condicion = ""
        condicion = if (TextUtils.isEmpty(c.getNif())) {
            "id=" + String.valueOf(c.getId())
        } else {
            "nif=" + "'" + c.getNif() + "'"
        }
        val columnas = arrayOf(
            "id", "nif", "nombre", "apellidos", "claveseguridad", "email"
        )
        val cursor: Cursor? =
            MiBD.dB?.query("clientes", columnas, condicion, null, null, null, null) ?:null
        var nuevoCliente: Cliente? = null
        if (cursor?.moveToFirst() == true) {
            nuevoCliente = Cliente()
            nuevoCliente.setId(cursor.getInt(0))
            nuevoCliente.setNif(cursor.getString(1))
            nuevoCliente.setNombre(cursor.getString(2))
            nuevoCliente.setApellidos(cursor.getString(3))
            nuevoCliente.setClaveSeguridad(cursor.getString(4))
            nuevoCliente.setEmail(cursor.getString(5))

            // Obtenemos la lista de cuentas que tiene el cliente
            //c.setListaCuentas(MiBD.getInstance(null).getCuentaDAO().getCuentas(c));
        }
        return nuevoCliente
    }//c.setListaCuentas(MiBD.getInstance(null).getCuentaDAO().getCuentas(c));



    //Recorremos el cursor hasta que no haya más registros
    override fun getAll(): ArrayList<*>? {
        val listaClientes = ArrayList<Cliente>()
        val columnas = arrayOf("id", "nif", "nombre", "apellidos", "claveseguridad", "email")
        val cursor = MiBD.dB?.query("clientes", columnas, null, null, null, null, null)
        val cuentaDAO = CuentaDAO()

        if (cursor?.moveToFirst() == true) {
            do {
                val c = Cliente()
                cursor?.let { c.setId(it.getInt(0)) }
                c.setNif(cursor?.getString(1))
                c.setNombre(cursor?.getString(2))
                c.setApellidos(cursor?.getString(3))
                c.setClaveSeguridad(cursor?.getString(4))
                c.setClaveSeguridad(cursor?.getString(5))
                // c.listaCuentas = MiBD.getInstance(null).getCuentaDAO().getCuentas(c)
                listaClientes.add(c)
            } while (cursor?.moveToNext() == true)
        }
        cursor?.close()
        return listaClientes
    }

}
