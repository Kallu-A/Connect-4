package power4

class Box( var color: Color? = null) {

    /** return if the box is Yellow*/
    fun isYellow() = color == Color.YELLOW

    /** return if the box is Red*/
    fun isRed() = color == Color.RED
}