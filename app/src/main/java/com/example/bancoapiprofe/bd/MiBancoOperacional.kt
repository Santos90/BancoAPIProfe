package com.example.bancoapiprofe.bd

import android.content.Context
import com.example.bancoapiprofe.pojo.Cliente
import com.example.bancoapiprofe.pojo.Cuenta
import com.example.bancoapiprofe.pojo.Movimiento

class MiBancoOperacional protected constructor(context: Context?) {
    private val miBD: MiBD?

    init {
        miBD = MiBD.getInstance(context)
    }

    // Operacion Login: Verifica que el cliente existe y que su contraseña es correcta. Recibira un cliente
    // que solo contendrá el nif y la password.
    fun login(c: Cliente): Cliente? {
        val aux: Cliente = miBD?.clienteDAO!!.search(c) as Cliente
        return if (aux == null) {
            null
        } else if (aux.getClaveSeguridad().equals(c.getClaveSeguridad())) {
            aux
        } else {
            null
        }
    }

    // Operacion changePassword: Cambia la password del cliente. Recibirá el cliente de la aplicación con la password cambiada.
    // Si devuelve un 1 es que ha verificado el cambio de password como correcto y todo ha ido bien, mientras que si devuelve
    // mientras que si devuelve un 0 no ha verificado el cambio de password como correcto y ha habido un error al cambiarlo.
    fun changePassword(c: Cliente?): Int {
        val resultado: Int = miBD?.clienteDAO!!.update(c)!!
        return if (resultado == 0) {
            0
        } else {
            1
        }
    }

    // Operacion getCuentas: Obtiene un ArrayList de las cuentas de un cliente que recibe como parámetro
    fun getCuentas(c: Cliente?): ArrayList<*> {
        return miBD?.cuentaDAO!!.getCuentas(c)!!
    }

    // Operacion getMovimientos: Obtiene un ArrayList de los movimientos de una cuenta que recibe como parámetro
    fun getMovimientos(c: Cuenta?): ArrayList<*> {
        return miBD?.movimientoDAO!!.getMovimientos(c) !!
    }

    /* Operacion transferencia: Desde una cuenta hace transferencia a otra cuenta, siempre que en la cuenta origen haya dinero disponible.

       Restricciones:

         - La comprobacion de la existencia de la cuenta destino se realizará dentro del método. La cuenta de origen existe por defecto, ya que el alumno ha de poder seleccionarla.
         - En caso de no existir la cuenta destino se devolvera como entero un 1.
         - La fecha de la operación será la fecha del sistema. Recordar que es almacenada como un long.
         - No se permitirá realizar una transferencia si la cuenta se queda en negativo. En este caso se devolvera como entero un 2.
         - Solo se permiten movimiento en las cuentas locales al banco, por lo que ambas cuentas deben existir.
         - La operación se ha de ver reflejada en las dos cuentas: el descuento en una y el ingreso en otra.
         - El campo tipo en la tabla de movimientos indica como es el movimiento. 0 indica que es un descuento, 1 indica que es un ingreso y 2 indica que es un reintegro por un cajero.
         - El movimiento que viene como parametro en el metodo, que viene en la variable movimientoTransferencia ha de ser de tipo 0.
         - Si la operacion es correcta se devuelve un 0
    */
    fun transferencia(movimientoTransferencia: Movimiento?): Int {
        return 0
    }

    // Operacion getMovimientosTipo: Obtiene un ArrayList de los movimientos de un tipo específico de una cuenta que recibe como parámetro
    fun getMovimientosTipo(c: Cuenta?, tipo: Int): ArrayList<*> {
        return miBD?.movimientoDAO!!.getMovimientosTipo(c, tipo)!!
    }

    companion object {
        private var instance: MiBancoOperacional? = null

        //***************************************
        // Interfaz publica de la API del banco
        //***************************************
        // Constructor del banco. Obtiene una instancia del mismo para operar
        fun getInstance(context: Context?): MiBancoOperacional? {
            if (instance == null) {
                instance = MiBancoOperacional(context)
            }
            return instance
        }
    }
}
