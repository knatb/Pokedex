package com.example.pokedex.Modelo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val nombreDB = "pokedex.db"
val versionDB = 1

class DbOpenHelper(context: Context) : SQLiteOpenHelper(context, nombreDB, null, versionDB) {

    //adb -s A4N4C19318014465 uninstall com.example.proyecto3

    override fun onCreate(db: SQLiteDatabase?){
        db!!.execSQL("""
            create table tblUsuarios(
                id integer primary key autoincrement,
                username text not null,
                email text not null,
                password text not null,
            )
            """.trimIndent())

        db!!.execSQL("""
            create table tblFavorites(
                idUsuario integer,
                idPokemon integer,
            )
            """.trimIndent())

        db!!.execSQL("""
            insert into tblUsuarios values
            (null, 'cozmicat', 'deon.8@hotmail.com', 'password01'),
            (null, 'nezquik', 'ryzor@hotmail.com', 'holamundo')
            """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1:Int, p2: Int){
    }
}