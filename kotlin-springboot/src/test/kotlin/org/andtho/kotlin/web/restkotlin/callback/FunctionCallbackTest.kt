package org.andtho.kotlin.web.restkotlin.callback

import org.junit.Test
import javax.swing.JOptionPane

class FunctionCallbackTest {
    @Test
    fun `use handler to show message dialog`() {
        val handler = Handler()
        fun showMessageDialog(text : String) : () -> Unit = {
            JOptionPane.showMessageDialog(null, text)
        }

        val result = handler.execute(showMessageDialog("Hello World!!"), object : FunctionCallback() {
            override fun onSuccess() {
                println("onSuccess Callbakc")
            }
            override fun onFailure() {
                println("execute onFailure")
            }
        })

        println(result)
    }
}