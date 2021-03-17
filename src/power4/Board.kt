package power4

import power4.util.tagEnd
import power4.util.tagError
import power4.util.tagInput
import power4.util.tagWarning

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
        showBoard()
    }

    /**show the board*/
    private fun showBoard(){
        for (i in 0 until sizeX){
            print("\u001B[37m |")
            for (j in 0 until sizeY)
                print(  (if (board[i][j].color?.string == null) "\u001B[37m -" else " " + board[i][j].color?.string)  + "\u001B[37m |")
            println()
        }
        print(" |")
        for (i in 0 until sizeY) print(" ${i + 1} |")
        println()
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
        if (isPlayValidCol(column)) {
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
    private fun isPlayValidCol(column: Int) = column in 0 until sizeY && board[0][column].color == null

    /** return if the play can be make*/
    private fun isPlayRow(row: Int) = row in 0 until sizeX
    private fun isPlayCol(column: Int) = column in 0 until sizeY

    /** change color when a play is did*/
    private fun colorChange(){
        color = if (color == Color.YELLOW)
            Color.RED
        else Color.YELLOW
    }

    /** return if the game is finish*/
    private  fun isFinish(): Boolean{
        val winner = isWin()
        return if (winner == noOne) !isDraw()
        else {
            if (winner == winRed){
                println(tagEnd("The winner is the player red"))
                false
            } else{
                println(tagEnd("The winner is the player yellow"))
                false
            }
        }
    }

    /** is game finish with draw*/
    private fun isDraw(): Boolean {
        for (i in 0 until sizeX)
            for (j in 0 until sizeY)
                if (board[i][j].color == null) return false
        println(tagEnd("It's a draw"))
        return true
    }

    /** return noOne if not win winRed for the red and winYellow for the yellow*/
    private fun isWin(): Int {
        var isFinish: Int = isFinishRow()

        if (isFinish == noOne) {
            isFinish = isFinishCol()
            if (isFinish == noOne) isFinish = isFinishDiag()
        }

        return isFinish
    }

    /** return if the game is finish in line*/
    private fun isFinishRow(): Int{
        for (i in 0 until sizeX)
            for (j in 0 until sizeY)
                if (isPlayCol(j+3)){
                    if (board[i][j].color == board[i][j+1].color  && board[i][j+1].color ==  board[i][j+2].color
                        && board[i][j+2].color == board[i][j+3].color && board[i][j].color != null)
                        return if (board[i][j].isRed()) winRed else winYellow
                } else break
        return noOne
    }

    /** return if the game is finish in line*/
    private fun isFinishCol(): Int{
        for (i in 0 until sizeX)
            for (j in 0 until sizeY)
                if (isPlayRow(i+3)){
                    if (board[i][j].color == board[i+1][j].color && board[i+1][j].color == board[i+2][j].color
                        && board[i+2][j].color == board[i+3][j].color && board[i][j].color != null)
                        return if (board[i][j].isRed()) winRed else winYellow
                } else break
        return noOne
    }

    /** return if the game is finish in diagonal*/
    private fun isFinishDiag(): Int{
        for (row in 0 until sizeX)
            for (col in 0 until sizeY){
                // diag n°1
                if (isPlayCol(col + 3) && isPlayRow(row + 3))
                    if ( board[row][col].color == board[row+1][col+1].color && board[row+1][col+1].color == board[row+2][col+2].color &&
                        board[row+2][col+2].color == board[row+3][col+3].color && board[row][col].color != null )
                        return if (board[row][col].isRed()) winRed else winYellow
                // diag n°2
                if (isPlayCol(col - 3) && isPlayRow(row + 3)){
                    if ( board[row][col].color == board[row+1][col-1].color && board[row+1][col-1].color == board[row+2][col-2].color &&
                        board[row+2][col-2].color ==  board[row+3][col-3].color && board[row][col].color != null)
                        return if (board[row][col].isRed()) winRed else winYellow
                }
            }

        return noOne
    }

    companion object{
        private const val winRed = 1
        private const val winYellow = 2
        private const val noOne = 0
    }

}