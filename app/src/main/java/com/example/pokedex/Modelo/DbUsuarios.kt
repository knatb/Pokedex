package com.example.pokedex.Modelo

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.pokedex.Entidades.Usuario

class DbUsuarios (context: Context){
    private val openHelper: DbOpenHelper = DbOpenHelper(context)
    private val database: SQLiteDatabase

    init {
        database =  openHelper.writableDatabase
    }

    fun getAllUsarios(): Cursor {
        return database.rawQuery(
                """
            select * from tblUsuarios 
        """.trimIndent(), null
        )
        database.close()
    }

    fun getUsuario(userName: String): Cursor {
        return database.rawQuery(
                """
            select * from tblUsuarios 
                        where username = '$userName'
        """.trimIndent(), null
        )
        database.close()
    }

    fun login(userName: String, password: String): Cursor {
        return database.rawQuery("""
            select id, username, email, password 
              from tblUsuarios
                where username =  '$userName' and 
                password = '$password'
        """.trimIndent(),null)
        database.close()
    }

    fun newUsuario(userName: String, email:String ,password: String){
        database.execSQL("""
            insert into tblUsuarios values 
                        (null, '$userName', '$email', '$password')
        """.trimIndent())
        database.close()
    }

    fun updateUsuario(user: Usuario){
        database.execSQL(
                """
            update tblUsuarios set 
                        username = '${user._username}', email = '${user._email}', password = '${user._password}' 
                        where id = ${user._id}
        """.trimIndent()
        )
        database.close()
    }

    fun deleteUsuario(id: Int){
        database.execSQL(
                """
            delete from tblUsuarios 
                        where id = $id
        """.trimIndent()
        )
        database.close()
    }
}