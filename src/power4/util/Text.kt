private const val reset = "\u001B[0m"
private const val open = "\u001B[37m["
private const val close = "\u001B[37m] "

@JvmOverloads
fun tagError(message: String = "") = "$open\u001B[31mERROR$close$reset$message"
@JvmOverloads
fun tagWarning(message: String = "") = "$open\u001B[33mWARNING$close$reset$message"
@JvmOverloads
fun tagData(message: String = "") = "$open\u001B[35mDATA$close$reset$message"
@JvmOverloads
fun tagInfo(message: String = "") = "$open\u001B[36mINFO$close$reset$message"
@JvmOverloads
fun tagDeadly(message: String = "") = "$open\u001B[31mDEADLY$close\u001B[31m$message"
@JvmOverloads
fun tagComment(message: String = "") = "${open}COMMENT$close$message"
@JvmOverloads //work better with print
fun tagInput(message: String = "") = "$open\u001B[35mINPUT$close$message \n \u001B[35m--> $reset"
@JvmOverloads
fun tagEnd(message: String = "") = "$open\u001B[35mEND$close\u001B[31m$message"