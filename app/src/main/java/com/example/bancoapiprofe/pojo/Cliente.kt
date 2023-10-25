package com.example.bancoapiprofe.pojo

import java.io.Serializable

class Cliente : Serializable {
    private var id = 0
    private var nif: String? = null
    private var nombre: String? = null
    private var apellidos: String? = null
    private var claveSeguridad: String? = null
    private var email: String? = null
    private var listaCuentas: ArrayList<Cuenta>? = null

    constructor(
        id: Int,
        nif: String?,
        nombre: String?,
        apellidos: String?,
        claveSeguridad: String?,
        email: String?
    ) {
        this.id = id
        this.nif = nif
        this.nombre = nombre
        this.apellidos = apellidos
        this.claveSeguridad = claveSeguridad
        this.email = email
        listaCuentas = ArrayList()
    }

    constructor() : super() {}

    override fun toString(): String {
        return """
            id: ${id}
            nif: ${nif}
            nombre: ${nombre}
            apellidos: ${apellidos}
            """.trimIndent()
    }

    fun getId(): Int {
        return this.id
    }

    fun getNif(): String? {
        return nif
    }

    fun getNombre(): String? {
        return nombre
    }

    fun getApellidos(): String? {
        return apellidos
    }

    fun getClaveSeguridad(): String? {
        return claveSeguridad
    }

    fun getEmail(): String? {
        return email
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setNif(nif: String?) {
        this.nif = nif
    }

    fun setNombre(nombre: String?) {
        this.nombre = nombre
    }

    fun setApellidos(apellidos: String?) {
        this.apellidos = apellidos
    }

    fun setClaveSeguridad(claveSeguridad: String?) {
        this.claveSeguridad = claveSeguridad
    }

    fun setEmail(email: String?) {
        this.email = email
    }
}
