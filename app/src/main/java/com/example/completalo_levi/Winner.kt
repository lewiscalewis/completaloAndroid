package com.example.completalo_levi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Winner : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        val miBundle = intent.extras

        var clicks = miBundle!!.getInt("clicks")
        var ganador: TextView = findViewById(R.id.ganador)

        var texto: String = "" + ganador.text + " " + clicks + " clicks!"
        ganador.text = texto
    }
}