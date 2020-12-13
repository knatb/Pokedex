package com.example.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import com.easyfingerprint.EasyFingerPrint
import com.example.pokedex.Entidades.Usuario
import com.example.pokedex.Modelo.DbUsuarios
import kotlinx.android.synthetic.main.activity_main.*

var datosUsuario = Usuario(0, "", "", "")

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View) {
        val datasource = DbUsuarios(this)
        val cursor =  datasource.login(txtusername.text.toString(), txtpassword.text.toString())
        if(cursor.count > 0) {
            while(cursor.moveToNext()){
                datosUsuario = Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3))
            }
            Toast.makeText(applicationContext,"Bienvenido " + datosUsuario._username,
                    Toast.LENGTH_SHORT).show()
            var intent = Intent(this, ListadoPokemon::class.java)
            startActivity(intent)
        }
        else {
            Toast.makeText(applicationContext,"No existe el usuario",
                    Toast.LENGTH_SHORT).show()
        }
    }

    fun goToRegister(view: View) {
        val intent = Intent(this, Registro::class.java)
        startActivity(intent)
    }
}