package power4

class Box( var color: Color? = null) {

    /** return if the box is Red*/
    fun isRed() = color == Color.RED
}