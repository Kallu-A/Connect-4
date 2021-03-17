package power4

import tagEnd
import tagError
import tagInput
import tagWarning

class Board(private val sizeX: Int = 7, private val sizeY: Int = 6) {

    private val board = Array(sizeX) { Array(sizeY) { Box() } }
    private var color = Color.YELLOW



    /** launch the game*/
    fun launch(){
        do {
            showBoard()
            print(tagInput("Put the column you want to play"))
            tryPlay(getPlay())
        } while (isFinish())
    }

    /**show the board*/
    private fun showBoard(){
        for (i in 0 until sizeX){
            print("\u001B[37m |")
            for (j in 0 until sizeY)
                print(  (if (board[i][j].color?.string == null) "\u001B[37m -" else " " + board[i][j].color?.string)  + "\u001B[37m |")
            println()
        }
    }

    /** get the play*/
    private fun getPlay(): Int? {
        val column = readLine()
        val columnInt: Int?
        try {
            columnInt = column?.toInt()?.minus(1)
        } catch (e: Exception) {
            return null
        }

        return columnInt
    }

    /** try to make a play*/
    private fun tryPlay(column: Int?) {
        if (column == null) {
            println(tagError("Use an Integer"))
            return
        }
        if (isPlayValid(column)) {
            play(column)
            return
        }
        println(tagWarning("Not in the board"))
    }

    /** make the play*/
    private fun play(column: Int){
        for (i in sizeX-1 downTo -1)
            if (board[i][column].color == null) {
                board[i][column].color = color
                colorChange()
                return
            }
    }

    /** return if the play can be make*/
    private fun isPlayValid(column: Int) = column in 0 until sizeY && board[0][column].color == null


    /** change color when a play is did*/
    private fun colorChange(){
        color = if (color == Color.YELLOW)
            Color.RED
        else Color.YELLOW
    }

    /** return if the game is finish*/
    private  fun isFinish(): Boolean{
        val winner = isWin()
        if (winner == 0) return isDraw()
        else {
            if (winner == 1)
                println(tagEnd("The winner is the player red"))
            else
                println(tagEnd("The winner is the player yellow"))
        }
        return true
    }

    /** is game finish with draw*/
    private fun isDraw(): Boolean {
        for (i in 0 until sizeX)
            for (j in 0 until sizeY)
                if (board[i][j].color == null) return false
        println(tagEnd("It's a draw"))
        return true
    }

    /** return 0 if not win 1 for the red and 2 for the yellow*/
    private fun isWin(): Int {
        return 0
    }

}