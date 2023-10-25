package com.example.bancoapiprofe.pojo

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date

class Movimiento : Serializable {
    private var id = 0
    private var tipo = 0
    private var fechaOperacion: Date? = null
    private var descripcion: String? = null
    private var importe = 0f
    private var cuentaOrigen: Cuenta? = null
    private var cuentaDestino: Cuenta? = null

    constructor(
        id: Int,
        tipo: Int,
        fechaOperacion: Date?,
        descripcion: String?,
        importe: Float,
        cuentaOrigen: Cuenta?,
        cuentaDestino: Cuenta?
    ) {
        this.id = id
        this.tipo = tipo
        this.fechaOperacion = fechaOperacion
        this.descripcion = descripcion
        this.importe = importe
        this.cuentaOrigen = cuentaOrigen
        this.cuentaDestino = cuentaDestino
    }

    constructor() : super() {}

    override fun toString(): String {
        val formateador = SimpleDateFormat("dd/MM/yyyy")
        return """
            id: ${id}
            tipo: ${tipo}
            fecha operacion: ${formateador.format(fechaOperacion)}
            descripcion: ${descripcion}
            importe: ${importe}
            id cuenta origen: ${cuentaOrigen!!.getId()}
            id cuenta destino: ${cuentaDestino!!.getId()}
            """.trimIndent()
    }

    fun getTipo(): Int {
        return tipo;
    }

    fun getFechaOperacion(): Date? {
        return fechaOperacion
    }

    fun getDescripcion(): String? {
        return descripcion
    }

    fun getImporte(): Float {
        return importe
    }

    fun getCuentaOrigen(): Cuenta? {
        return cuentaOrigen
    }

    fun getCuentaDestino(): Cuenta? {
        return cuentaDestino
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setTipo(tipo: Int) {
        this.tipo = tipo
    }

    fun setFechaOperacion(fechaOperacion: Date) {
        this.fechaOperacion = fechaOperacion
    }

    fun setDescripcion(desc: String?) {
        this.descripcion = desc
    }

    fun setImporte(importe: Float) {
        this.importe = importe
    }

    fun setCuentaOrigen(cuenta: Cuenta?) {
        this.cuentaOrigen = cuenta
    }

    fun setCuentaDestino(cuenta: Cuenta) {
        this.cuentaDestino = cuenta
    }

}