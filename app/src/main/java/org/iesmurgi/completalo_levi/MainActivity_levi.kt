package org.iesmurgi.completalo_levi

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.*
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.example.completalo_levi.R


class MainActivity_levi : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_levi)

        var boton: Button = findViewById(R.id.comenzarJuego)
        boton.setOnClickListener(){
            startPlay()
            Toast.makeText(this, "pulsado", Toast.LENGTH_SHORT).show()
        }

        var textoTramas: TextView = findViewById(R.id.textoTramas)
        var textoFilas: TextView = findViewById(R.id.textoFilas)
        var textoColumnas: TextView = findViewById(R.id.textoColumnas)

        var tramas: SeekBar = findViewById(R.id.tramas)
        var filas: SeekBar = findViewById(R.id.filas)
        var columnas: SeekBar = findViewById(R.id.columnas)


        tramas.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textoTramas.text = progress.toString()
                updateColores(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        filas.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textoFilas.text = progress.toString()
                updateXTiles(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        columnas.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textoColumnas.text = progress.toString()
                updateYTiles(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }

    fun startPlay(){
        var enviar: Intent = Intent(this, GameField_levi::class.java)

        var textoTramas: TextView = findViewById(R.id.textoTramas)
        var textoFilas: TextView = findViewById(R.id.textoFilas)
        var textoColumnas: TextView = findViewById(R.id.textoColumnas)

        var color: RadioButton = findViewById(R.id.colores)
        var numero: RadioButton = findViewById(R.id.numeros)

        var sonido: CheckBox = findViewById(R.id.sonido)
        var vibracion: CheckBox = findViewById(R.id.vibracion)

        var choice: String
        if(numero.isChecked){
            choice = "numbers"
        }else{
            choice = "colors"
        }
        
        if(textoColumnas.text.toString() == "Columns" || textoColumnas.text.toString() == "Columnas" || textoColumnas.text.toString().toInt() < 3){
            textoColumnas.text = "3";
        }
        if(textoFilas.text.toString() == "Rows" || textoFilas.text.toString() == "Filas" || textoFilas.text.toString().toInt() < 3){
            textoFilas.text = "3";
        }
        if(textoTramas.text.toString() == "Plots" || textoTramas.text.toString() == "Tramas" || textoTramas.text.toString().toInt() < 2){
            textoTramas.text = "2";
        }

        enviar.putExtra("tramas", textoTramas?.text.toString().toInt())
        enviar.putExtra("filas", textoFilas?.text.toString().toInt())
        enviar.putExtra("columnas", textoColumnas?.text.toString().toInt())
        enviar.putExtra("choice", choice)
        enviar.putExtra("sonido", sonido?.text.toString())
        enviar.putExtra("vibracion", vibracion?.text.toString())

        startActivity(enviar);
    }

    fun updateXTiles(progreso: Int){

    }

    fun updateYTiles(progreso: Int){

    }

    fun updateColores(progreso: Int){

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_levi, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.configuracion -> {
                //item.setIcon(R.mipmap.ic_player)
                Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT ).show()
                return true
            }
            R.id.ayuda -> {
                //item.setIcon(R.mipmap.ic_howto)
                Toast.makeText(this, "Ayuda", Toast.LENGTH_SHORT ).show()
                mostrarHowTo()
                return true
            }
            R.id.acercade -> {
                //item.setIcon(R.mipmap.ic_about)
                Toast.makeText(this, "Acerca de", Toast.LENGTH_SHORT ).show()
                mostrarAbout()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    fun mostrarPlayer(){

    }

    fun mostrarHowTo(){
        var enviar: Intent = Intent(this, HowTo_levi::class.java)
        startActivity(enviar);
    }

    fun mostrarAbout(){
        var enviar: Intent = Intent(this, About_levi::class.java)
        startActivity(enviar);
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        TODO("Not yet implemented")
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        TODO("Not yet implemented")
    }

}



