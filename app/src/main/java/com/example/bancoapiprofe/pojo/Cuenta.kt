package com.example.bancoapiprofe.pojo

import java.io.Serializable

class Cuenta : Serializable {
    private var id = 0
    private var banco: String? = null
    private var sucursal: String? = null
    private var dc: String? = null
    private var numeroCuenta: String? = null
    private var saldoActual = 0f
    private var cliente: Cliente? = null
    private var listaMovimientos: ArrayList<Movimiento>? = null

    constructor(
        id: Int,
        banco: String?,
        sucursal: String?,
        dc: String?,
        numeroCuenta: String?,
        cliente: Cliente?,
        saldoActual: Float
    ) {
        this.id = id
        this.banco = banco
        this.sucursal = sucursal
        this.dc = dc
        this.numeroCuenta = numeroCuenta
        this.cliente = cliente
        this.saldoActual = saldoActual
        listaMovimientos = ArrayList()
    }

    constructor() : super() {}

    override fun toString(): String {
        return """
               id: ${id}
               banco: ${banco}
               sucursal: ${sucursal}
               dc: ${dc}
               numero cuenta: ${numeroCuenta}
               id cliente: ${cliente!!.getId()}
               saldo actual: ${saldoActual}
               """.trimIndent()
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getBanco(): String? {
        return banco
    }

    fun getSucursal(): String? {
        return sucursal
    }

    fun getDc(): String? {
        return dc
    }

    fun getNumeroCuenta(): String? {
        return numeroCuenta
    }

    fun getCliente(): Cliente? {
        return cliente
    }

    fun getSaldoActual(): Float? {
        return saldoActual
    }

    fun setBanco(banco: String?) {
        this.banco = banco
    }

    fun setSucursal(sucursal: String?) {
        this.sucursal = sucursal
    }

    fun setDc(dc: String?) {
        this.dc = dc
    }

    fun setNumeroCuenta(numeroCuenta: String?) {
        this.numeroCuenta = numeroCuenta
    }

    fun setSaldoActual(saldoActual: Float) {
        this.saldoActual = saldoActual
    }

    fun setCliente(cliente: Cliente?){
        this.cliente = cliente
    }
}