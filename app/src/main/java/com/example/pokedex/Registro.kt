package com.example.pokedex

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.ablanco.imageprovider.ImageProvider
import com.ablanco.imageprovider.ImageSource
import com.example.pokedex.Entidades.Usuario
import com.example.pokedex.Modelo.DbUsuarios
//import com.example.pokedex.Services.ImageService
import kotlinx.android.synthetic.main.registro_usuarios.*
import kotlinx.android.synthetic.main.registro_usuarios.txtUserName

class Registro: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_usuarios)
    }

    fun register(view: View) {
        if(txtPassword2.text.toString().isEmpty() || txtPassword3.text.toString().isEmpty() || txtUserName.text.toString().isEmpty() || txtemail.text.toString().isEmpty()) {
            Toast.makeText(applicationContext,"Campos incompletos",
                    Toast.LENGTH_SHORT).show()
        }
        else {
            if(txtPassword2.text.toString().equals(txtPassword3.text.toString())){
                val datasource = DbUsuarios(this)
                val cursor =  datasource.getUsuario(txtUserName.text.toString())
                if(cursor.count > 0) {
                    Toast.makeText(applicationContext,"Ya existe",
                            Toast.LENGTH_SHORT).show()
                }
                else {
                    var user = Usuario(0, txtUserName.text.toString(), txtemail.text.toString(), txtPassword2.text.toString())
                    datasource.newUsuario(user._username, user._email, user._password)
                    datosUsuario = user
                    var intent = Intent(this, ListadoPokemon::class.java)
                    startActivity(intent)
                }
            }
            else {
                Toast.makeText(applicationContext,"No coinciden",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }
}