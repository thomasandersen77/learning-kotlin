package org.andtho.kotlin.function

import org.junit.Test
import java.io.File
import javax.swing.JDialog
import javax.swing.JOptionPane
import kotlin.concurrent.thread

class FunctionCallbackTest {

    fun showMessageDialog(text: String) : () -> JDialog = {
        val pane = JOptionPane()
        pane.messageType = JOptionPane.PLAIN_MESSAGE
        pane.message = text
        val dialog = pane.createDialog(text)
        thread(start = true){
            Thread.sleep(300)
            dialog.dispose()
        }
        dialog.isVisible = true
        dialog
    }

    @Test
    fun `use handler to show message dialog`() {
        Handler.execute(showMessageDialog("Hello World!!"), object : FunctionCallback() {
            override fun onSuccess() {
                println("execute onSuccess")
            }

            override fun onFailure() {
                println("execute onFailure")
            }
        })
    }

    fun writeFile(content : String, filename : String) : () -> Unit = {
        File(filename).writeText(content)
    }

    @Test
    fun `write file via highorder function`() {
        Handler.execute(writeFile("Kotlin rules", "target/textfile.txt"), object : FunctionCallback() {
            override fun onSuccess() {
                println("execute onSuccess")
            }

            override fun onFailure() {
                println("execute onFailure")
            }
        })

    }
}