package com.example.bancoapiprofe.dao

import android.content.ContentValues
import android.database.Cursor
import android.text.TextUtils
import com.example.bancoapiprofe.bd.MiBD
import com.example.bancoapiprofe.pojo.Cliente
import com.example.bancoapiprofe.pojo.Cuenta
import java.lang.String
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.arrayOf

class CuentaDAO : PojoDAO {
    override fun add(obj: Any?): Long {
        val contentValues = ContentValues()
        val c: Cuenta = obj as Cuenta
        contentValues.put("banco", c.getBanco())
        contentValues.put("sucursal", c.getSucursal())
        contentValues.put("dc", c.getDc())
        contentValues.put("numerocuenta", c.getNumeroCuenta())
        contentValues.put("idcliente", c.getCliente()!!.getId())
        contentValues.put("saldoactual", c.getSaldoActual())
        return MiBD.dB!!.insert("cuentas", null, contentValues)
    }

    override fun update(obj: Any?): Int {
        val contentValues = ContentValues()
        val c: Cuenta = obj as Cuenta
        contentValues.put("banco", c.getBanco())
        contentValues.put("sucursal", c.getSucursal())
        contentValues.put("dc", c.getDc())
        contentValues.put("numerocuenta", c.getNumeroCuenta())
        contentValues.put("saldoactual", c.getSaldoActual())
        contentValues.put("idcliente", c.getCliente()!!.getId())
        val condicion = "id=" + String.valueOf(c.getId())
        return MiBD.dB!!.update("cuentas", contentValues, condicion, null)
    }

    override fun delete(obj: Any?) {
        val c: Cuenta = obj as Cuenta
        val condicion = "id=" + String.valueOf(c.getId())

        //Se borra el producto indicado en el campo de texto
        MiBD.dB?.delete("cuentas", condicion, null)
    }

    override fun search(obj: Any?): Any? {
        val c: Cuenta = obj as Cuenta
        var condicion = ""
        condicion = if (TextUtils.isEmpty(c.getBanco())) {
            "id=" + String.valueOf(c.getId())
        } else {
            "banco=" + String.valueOf(c.getBanco()) + " AND sucursal = " + String.valueOf(c.getSucursal()) +
                    " AND dc = " + String.valueOf(c.getDc()) + " AND numerocuenta = " + String.valueOf(
                c.getNumeroCuenta()
            )
        }
        val columnas = arrayOf(
            "id", "banco", "sucursal", "dc", "numerocuenta", "saldoactual", "idcliente"
        )
        val cursor: Cursor =
            MiBD.dB!!.query("cuentas", columnas, condicion, null, null, null, null)
        return if (cursor.moveToFirst()) {
            c.setId(cursor.getInt(0))
            c.setBanco(cursor.getString(1))
            c.setSucursal(cursor.getString(2))
            c.setDc(cursor.getString(3))
            c.setNumeroCuenta(cursor.getString(4))
            c.setSaldoActual(cursor.getFloat(5))

            // Obtenemos el cliente y lo asignamos
            var a = Cliente()
            a.setId(cursor.getInt(6))
            a = MiBD.getInstance(null)!!.clienteDAO!!.search(a) as Cliente
            c.setCliente(a)

            // Obtenemos la lista de movimientos y los asignamos
            //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));
            c
        } else {
            null
        }
    }

    override fun getAll(): ArrayList<*> {
        val listaCuentas: ArrayList<Cuenta> = ArrayList<Cuenta>()
        val columnas = arrayOf(
            "id", "banco", "sucursal", "dc", "numerocuenta", "saldoactual", "idcliente"
        )
        val cursor: Cursor = MiBD.dB!!.query("cuentas", columnas, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                val c = Cuenta()
                c.setId(cursor.getInt(0))
                c.setBanco(cursor.getString(1))
                c.setSucursal(cursor.getString(2))
                c.setDc(cursor.getString(3))
                c.setNumeroCuenta(cursor.getString(4))
                c.setSaldoActual(cursor.getFloat(5))

                // Obtenemos el cliente y lo asignamos
                var a = Cliente()
                a.setId(cursor.getInt(6))
                a = MiBD.getInstance(null)!!.clienteDAO!!.search(a) as Cliente
                c.setCliente(a)

                // Obtenemos la lista de movimientos y los asignamos
                //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));
                listaCuentas.add(c)
            } while (cursor.moveToNext())
        }
        return listaCuentas
    }

    fun getCuentas(cliente: Cliente?): ArrayList<*> {
        val listaCuentas: ArrayList<Cuenta> = ArrayList<Cuenta>()
        val condicion = "idcliente=" + String.valueOf(cliente!!.getId())
        val columnas = arrayOf(
            "id", "banco", "sucursal", "dc", "numerocuenta", "saldoactual", "idcliente"
        )
        val cursor: Cursor =
            MiBD.dB!!.query("cuentas", columnas, condicion, null, null, null, null)
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                val c = Cuenta()
                c.setId(cursor.getInt(0))
                c.setBanco(cursor.getString(1))
                c.setSucursal(cursor.getString(2))
                c.setDc(cursor.getString(3))
                c.setNumeroCuenta(cursor.getString(4))
                c.setSaldoActual(cursor.getFloat(5))
                c.setCliente(cliente)

                // Obtenemos la lista de movimientos y los asignamos
                //c.setListaMovimientos(MiBD.getInstance(null).getMovimientoDAO().getMovimientos(c));
                listaCuentas.add(c)
            } while (cursor.moveToNext())
        }
        return listaCuentas
    }
}
