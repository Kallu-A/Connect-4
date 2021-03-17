package connect4.util

private const val reset = "\u001B[0m"
private const val open = "\u001B[37m["
private const val close = "\u001B[37m] "
@JvmOverloads
fun tagError(message: String = "") = "$open\u001B[31mERROR$close$reset$message"
@JvmOverloads
fun tagWarning(message: String = "") = "$open\u001B[33mWARNING$close$reset$message"
@JvmOverloads //work better with print
fun tagInput(message: String = "") = "$open\u001B[35mINPUT$close$message \n \u001B[35m--> $reset"
@JvmOverloads
fun tagEnd(message: String = "") = "$open\u001B[36mEND$close\u001B[31m$message"