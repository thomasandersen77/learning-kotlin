package org.andtho.kotlin.web.restkotlin.callback

abstract class FunctionCallback {
    open fun onSuccess() {}
    open fun onFailure() {}
}

class Handler {
    fun <T> execute(function : () -> T, callback: FunctionCallback) : T? {
        println("------ START MESSAGE ------")
        return try {
            val result = function.invoke()
            callback.onSuccess()
            result
        } catch (e: Exception) {
            callback.onFailure()
            null
        } finally {
            println("------ END MESSAGE --------")
        }
    }
}
