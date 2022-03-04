package com.example.completalo_levi

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.DisplayMetrics
import android.view.View
import android.widget.Chronometer
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.ArrayList

open class GameField_levi : AppCompatActivity() {
    
    private val colores = arrayOf(R.drawable.ic_1c, R.drawable.ic_2c, R.drawable.ic_3c, R.drawable.ic_4c, R.drawable.ic_5c, R.drawable.ic_6c)
    private val numeros = arrayOf(R.drawable.ic_1n, R.drawable.ic_2n, R.drawable.ic_3n, R.drawable.ic_4n, R.drawable.ic_5n, R.drawable.ic_6n)
    
    private lateinit var dibujo: Array<Int>
    private var topTileX: Int = 0
    private var topTileY: Int = 0
    private var topElement: Int = 0
    
    private var sonido: String? = null
    private var vibracion: String? = null

    private var contador: Int = 0
    private lateinit var mp: MediaPlayer
    private lateinit var vs: Vibrator
    private lateinit var recuentoClicks: TextView
    private var choice: String? = ""
    private lateinit var cronometro: Chronometer
    private lateinit var tablero: LinearLayout
    private var trama: Int = 0
    
    private lateinit var celdaAux: CeldaView_levi
    private var aux: Int = 0
    private lateinit var secundaryLayout: LinearLayout
    private var alturaMarcador: Int = 0
    private lateinit var dm: DisplayMetrics
    private var altura: Int = 0

    private lateinit var ids: MutableList<MutableList<Int>>
    private lateinit var values: MutableList<MutableList<Int>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.celdaview_levi)

        val miBundle = intent.extras

        topTileX = miBundle!!.getInt("filas")
        topTileY = miBundle!!.getInt("columnas")
        topElement = miBundle!!.getInt("tramas")
        choice = miBundle?.getString("choice")
        sonido = miBundle!!.getString("sonido")
        vibracion = miBundle!!.getString("vibracion")
        tablero = findViewById(R.id.tablero)
        cronometro = findViewById(R.id.cronometro)
        recuentoClicks = findViewById(R.id.recuentoClicks)
        

        if (choice.equals("colors")){
            dibujo = colores
        } else{
            dibujo = numeros
        }

        cronometro.start()

        ids= mutableListOf(
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0))
        
        values= mutableListOf(
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0),
            mutableListOf(0,0,0,0,0,0))



        alturaMarcador = dpToPx(175)

        dm = resources.displayMetrics

        var virtualY = topTileY
        if(topTileY == 0){
            virtualY = 1
        }

        altura = (dm.heightPixels - alturaMarcador) / virtualY

        for (i in 0 until topTileY!!){
            secundaryLayout = LinearLayout(this)
            secundaryLayout.orientation = LinearLayout.HORIZONTAL

            for(j in 0 until topTileX!!) {
                trama = random()
                values[j][i] = trama
                celdaAux = CeldaView_levi(this, j, i, topElement!!, trama, dibujo[trama])
                aux++
                celdaAux.id = aux
                ids[j][i] = aux
                celdaAux.layoutParams = LinearLayout.LayoutParams(0, altura, 1f)
                celdaAux.setOnClickListener { hasClick(j, i) }
                secundaryLayout.addView(celdaAux)
            }
            tablero.addView(secundaryLayout)
        }
    }

    private fun hasClick(x:Int, y:Int){

        if(sonido != null){
            mp = MediaPlayer.create(this, R.raw.touch)
            mp?.start()
        }

        if(vibracion != null) {
            vs = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if(vs.hasVibrator()){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vs.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
                }else{
                    vs.vibrate(100)
                }
            }
        }

        changeView(x, y)

        if(x == 0 && y == 0){

        }
        else if(x == 0 && y == topTileY!! - 1){
            changeView(0,topTileY!!-2)
            changeView(1,topTileY!!-2)
            changeView(1,topTileY!!-1)

        }
        else if(x == topTileX!! - 1 && y == 0){
            changeView(topTileX!!-2,0)
            changeView(topTileX!!-2,1)
            changeView(topTileX!!-1,1)
        }
        else if(x == topTileX!! - 1 && y == topTileY!! - 1 ){
            changeView(topTileX!!-2,topTileY!!-1)
            changeView(topTileX!!-2,topTileY!!-2)
            changeView(topTileX!!-1,topTileY!!-2)
        }

        else if(x == 0){
            changeView(x,y-1)
            changeView(x,y+1)
            changeView(x+1,y)
        }
        else if(y == 0){
            changeView(x-1,y)
            changeView(x+1,y)
            changeView(x,y+1)
        }
        else if(x == topTileX!! - 1){
            changeView(x,y-1)
            changeView(x,y+1)
            changeView(x-1,y)
        }
        else if(y == topTileY!! - 1){
            changeView(x-1,y)
            changeView(x+1,y)
            changeView(x,y-1)
        }
        else{
            changeView(x-1,y)
            changeView(x+1,y)
            changeView(x,y-1)
            changeView(x,y+1)
        }

        contador++
        recuentoClicks.text = contador!!.toString()

        checkIfFinished()
    }

    private fun changeView(x: Int, y: Int) {
        val celda = findViewById<CeldaView_levi>(ids[x][y])

        val newIndex = celda.getNewIndex()
        values[x][y] = newIndex

        celda.setBackgroundResource(dibujo[newIndex])
        celda.invalidate()
    }

    private fun checkIfFinished() {
        val valor = values[0][0]

        for(i in 0 until topTileY!!){
            for(j in 0 until topTileX!!){
                if(values[j][i] != valor){
                    return
                }
            }
        }

        val resultIntent = Intent(this, Winner::class.java)

        resultIntent.putExtra("clicks", contador)
        startActivity(resultIntent);
        setResult(RESULT_OK,resultIntent)
        finish()
    }

    private fun random(): Int {
        val r = Random()
        return r.nextInt(topElement!!)
    }

    fun Context.dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

}