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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility =  View.GONE

        //Lista de usuarios
        var objUsuario = Usuario()
        objUsuario.usuario = "felipe"
        objUsuario.password = "fe"

        listaUsuario.add(objUsuario)

        //crear la logica del login
        btnAccesar.setOnClickListener {
            progressBar.visibility =  View.VISIBLE

            Handler().postDelayed({
                progressBar.visibility =  View.GONE

                for( i :Usuario in listaUsuario){
                    if (txtUsuario.text.toString().equals(i.usuario) && txtPassword.text.toString().equals(i.password)){
                        var intent = Intent(this,Principal::class.java) //getClass()
                        startActivity(intent)
                    }
                }
            },5000)
        }
        btnRegistrar.setOnClickListener {
            var intent =  Intent(this,RegistroPersonas::class.java)
            startActivity(intent)
        }
    }

    fun abrirDialogo(view: View){
        var entro =  false
        for( i :Usuario in listaUsuario){
            if (txtUsuario.text.toString().equals(i.usuario)){
                if(i.autenticarHuella){
                    entro =  true
                }
            }
        }
        if(!entro){
            Toast.makeText(applicationContext,
                "Este usuario no esta habilitado para el uso de la huella",
                Toast.LENGTH_SHORT).show()
            return
        }

        EasyFingerPrint(this)
            .setTittle("Sign in")
            .setSubTittle("account@account.com.br")
            .setDescription("In order to use the Fingerprint sensor we need your authorization first.e")
            .setColorPrimary(R.color.colorPrimary)
            .setIcon(ContextCompat.getDrawable(this,R.mipmap.ic_launcher_round))
            .setListern(object : EasyFingerPrint.ResultFingerPrintListern{
                override fun onError(mensage: String, code: Int) {

                    when(code){
                        EasyFingerPrint.CODE_ERRO_CANCEL -> { } // TO DO
                        EasyFingerPrint.CODE_ERRO_GREATER_ANDROID_M -> { } // TO DO
                        EasyFingerPrint.CODE_ERRO_HARDWARE_NOT_SUPPORTED -> { } // TO DO
                        EasyFingerPrint.CODE_ERRO_NOT_ABLED -> { } // TO DO
                        EasyFingerPrint.CODE_ERRO_NOT_FINGERS -> { } // TO DO
                        EasyFingerPrint.CODE_NOT_PERMISSION_BIOMETRIC -> { } // TO DO
                    }
                    Toast.makeText(this@MainActivity,"Error: $mensage / $code", Toast.LENGTH_SHORT).show()
                }

                override fun onSucess(cryptoObject: FingerprintManagerCompat.CryptoObject?) {
                progressBar.visibility =  View.VISIBLE
                    Handler().postDelayed({
                        progressBar.visibility =  View.GONE
                        var intent = Intent(this@MainActivity,Principal::class.java) //getClass()
                        startActivity(intent)
                    },5000)
                }
            })
            .startScan()
    }
}