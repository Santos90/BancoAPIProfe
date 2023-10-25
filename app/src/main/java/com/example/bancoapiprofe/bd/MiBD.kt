package com.example.bancoapiprofe.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.bancoapiprofe.dao.ClienteDAO
import com.example.bancoapiprofe.dao.CuentaDAO
import com.example.bancoapiprofe.dao.MovimientoDAO

class MiBD
/**
 * Constructor de clase
 */
protected constructor(context: Context?) :
    SQLiteOpenHelper(context, database, null, version) {
    //Instrucción SQL para crear la tabla de Clientes
    private val sqlCreacionClientes =
        "CREATE TABLE clientes ( id INTEGER PRIMARY KEY AUTOINCREMENT, nif STRING, nombre STRING, " +
                "apellidos STRING, claveSeguridad STRING, email STRING);"

    //Instruccion SQL para crear la tabla de Cuentas
    private val sqlCreacionCuentas =
        "CREATE TABLE cuentas ( id INTEGER PRIMARY KEY AUTOINCREMENT, banco STRING, sucursal STRING, " +
                "dc STRING, numerocuenta STRING, saldoactual FLOAT, idcliente INTEGER);"

    //Instruccion SQL para crear la tabla de movimientos
    private val sqlCreacionMovimientos =
        "CREATE TABLE movimientos ( id INTEGER PRIMARY KEY AUTOINCREMENT, tipo INTEGER, fechaoperacion LONG," +
                " descripcion STRING, importe FLOAT, idcuentaorigen INTEGER, idcuentadestino INTEGER);"
    val clienteDAO: ClienteDAO?
        get() = Companion.clienteDAO
    val cuentaDAO: CuentaDAO?
        get() = Companion.cuentaDAO
    val movimientoDAO: MovimientoDAO?
        get() = Companion.movimientoDAO




    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sqlCreacionClientes)
        db.execSQL(sqlCreacionCuentas)
        db.execSQL(sqlCreacionMovimientos)
        insercionDatos(db)
        Log.i("SQLite", "Se crea la base de datos " + database + " version " + version)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.i(
            "SQLite",
            "Control de versiones: Old Version=$oldVersion New Version= $newVersion"
        )
        if (newVersion > oldVersion) {
            //elimina tabla
            db.execSQL("DROP TABLE IF EXISTS clientes")
            db.execSQL("DROP TABLE IF EXISTS cuentas")
            db.execSQL("DROP TABLE IF EXISTS movimientos")
            //y luego creamos la nueva tabla
            db.execSQL(sqlCreacionClientes)
            db.execSQL(sqlCreacionCuentas)
            db.execSQL(sqlCreacionMovimientos)
            insercionDatos(db)
            Log.i(
                "SQLite",
                "Se actualiza versión de la base de datos, New version= $newVersion"
            )
        }
    }

    private fun insercionDatos(db: SQLiteDatabase) {
        // Insertamos los clientes
        db.execSQL("INSERT INTO clientes(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (1, '11111111A', 'Filemón', 'Pí', '1234', 'filemon.pi@tia.es');")
        db.execSQL("INSERT INTO clientes(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (2, '22222222B', 'Mortadelo', 'Ibáñez', '1234', 'mortadelo.ibanez@tia.es');")
        db.execSQL("INSERT INTO clientes(id, nif, nombre, apellidos, claveSeguridad, email) VALUES (3, '33333333C', 'Vicente', 'Mondragón', '1234', 'vicente.mondragon@tia.es');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, '44444444D', 'Ayrton', 'Senna', '1234', 'ayrton.senna@f1.es');")
        db.execSQL("INSERT INTO clientes(rowid, id, nif, nombre, apellidos, claveSeguridad, email)VALUES(null, null, 'B1111111A', 'Ibertrola', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B2222222B', 'Gas Natural', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B3333333C', 'Telefónica', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B4444444D', 'Aguas de Valencia', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B5555555E', 'Audi', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B6666666F', 'BMW', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B7777777G', 'PayPal', '-', '1234', '-');")
        db.execSQL("INSERT INTO clientes (rowid, id, nif, nombre, apellidos, claveSeguridad, email) VALUES (null, null, 'B8888888H', 'Ayuntamiento de Valencia', '-', '1234', '-');")

        // Insertamos las cuentas
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '1001', '11', '1000000001', 1500, 1);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '1001', '11', '1000000003', -1200, 1);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '1001', '11', '1000000002', 3500, 1);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '1001', '11', '1000000004', 15340, 2);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '1001', '11', '1000000005', 23729.23, 1);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '1001', '11', '1000000006', 6500, 1);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '1001', '11', '1000000007', 9500, 2);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '1001', '11', '1000000008', 7500, 3);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '1001', '11', '1000000009', 24650, 1);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '1001', '11', '1000000010', -3500, 3);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '2001', '22', '2000000001', 7530543.75, 5);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '2001', '22', '2000000002', 15890345.87, 6);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '2001', '22', '2000000003', 19396420.30, 7);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '2001', '22', '2000000004', 1250345.23, 8);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '2001', '22', '2000000005', 24387299.23, 9);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '2001', '22', '2000000006', 15904387.45, 10);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '2001', '22', '2000000007', 156398452.87, 18);")
        db.execSQL("INSERT INTO cuentas (rowid, id, banco, sucursal, dc, numerocuenta, saldoactual, idcliente) VALUES (null, null, '1001', '2001', '22', '2000000008', 2389463.98, 19);")

        // Insertamos los movimientos
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1420153380000, 'Recibo Iberdrola Diciembre 2014', -73.87, 1, 5);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1420153380000, 'Recibo Iberdrola Diciembre 2014', -186.86, 2, 5);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1420153380000, 'Recibo Iberdrola Diciembre 2014', -96.36, 3, 5);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1420153380000, 'Recibo Iberdrola Diciembre 2014', -84.84, 4, 5);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1420326180000, 'Recibo Gas Natural Diciembre 2014', -340.39, 1, 6);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 1, 1420326180000, 'Devolución Telefónica Diciembre 2014', 30.76, 19, 1);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1420412580000, 'Recibo BMW Diciembre 2014', -256.65, 1, 10);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 2, 1420498980000, 'Reintegro cajero', -70, 1, -1);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 1, 1420498980000, 'Ingreso Nómina Ayuntamiento Valencia Diciembre 2014', 2150.50, 19, 1);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1420585380000, 'Recibo Telefónica Diciembre 2014', -96.32, 1, 7);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1421103780000, 'Reintegro cajero', -150, 1, -1);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1421362980000, 'Pago Paypal. ID: 2379646853', -98.47, 1, 18);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1421622180000, 'Reintegro cajero', -150, 1, -1);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1422054180000, 'Recibo IBI Ayuntamiento Valencia 2015', -358.36, 1, 18);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1422486180000, 'Reintegro cajero', -70, 1, -1);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1422831780000, 'Recibo Iberdrola Enero 2015', -95.34, 1, 5);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1423004580000, 'Recibo Gas Natural Enero 2015', -65.34, 1, 6);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1423090980000, 'Recibo BMW Enero 2015', -256.65, 1, 10);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1423263780000, 'Reintegro cajero', -70, 1, -1);")
        db.execSQL("INSERT INTO movimientos (rowid, id, tipo, fechaoperacion, descripcion, importe, idcuentaorigen, idcuentadestino) VALUES (null, null, 0, 1423263780000, 'Ingreso Nómina Ayuntamiento Valencia Enero 2015', 2150.5, 19, 1);")
    }




    companion object {
        var dB: SQLiteDatabase? = null
            private set

        //nombre de la base de datos
        private const val database = "MiBanco"

        //versión de la base de datos
        private const val version = 11
        private var instance: MiBD? = null
        private var clienteDAO: ClienteDAO? = null
        private var cuentaDAO: CuentaDAO? = null
        private var movimientoDAO: MovimientoDAO? = null

        fun getInstance(context: Context?): MiBD? {
            if (instance == null) {
                instance = MiBD(context)
                dB = instance!!.writableDatabase
                clienteDAO = ClienteDAO()
                cuentaDAO = CuentaDAO()
                movimientoDAO = MovimientoDAO()
            }
            return instance
        }



        /*protected fun MiBD(context: Context?): MiBD? {
            super(context, database, null, version)
        }*/

        fun closeDB() {
            dB!!.close()
        }

        /*fun getDB(): SQLiteDatabase? {
            return dB
        }*/
    }
}