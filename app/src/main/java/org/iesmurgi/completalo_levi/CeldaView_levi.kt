package org.iesmurgi.completalo_levi

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Button

@SuppressLint("AppCompatCustomView")
class CeldaView_levi(context: Context,  x: Int,  y: Int,  topElementos: Int,  index: Int,  background: Int):
    Button(context) {
    
    private var x: Int = 0
    private var y: Int = 0
    private var index: Int = 0
    private var topElementos: Int = 0

    init {
        this.x = x
        this.y = y
        this.topElementos = topElementos
        this.index = index
        this.setBackgroundResource(background)
    }

    fun getNewIndex(): Int{
        index++

        if (index == topElementos){
            index = 0
        }

        return index
    }

}
