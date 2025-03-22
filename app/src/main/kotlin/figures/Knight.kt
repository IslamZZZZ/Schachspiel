package figures
import Board.Board
import kotlin.math.abs

class Knight(override var position: Int, override val colour: Boolean, override val board: Board): Figure {
    init {
        board.positions[position] = this
    }

    override val figure = if(colour) "whiteKnight" else "blackKnight"

    /*override fun isThereFigure(location: Int): Boolean {
        return location in board.positions
    }*/

    override fun canMove(newPosition: Int): Boolean {
        val flag = if(abs(newPosition - position) == 15) true
        else if(abs(newPosition - position) == 17) true
        else if(abs(newPosition - position) == 10) true
        else if(abs(newPosition - position) == 6) true
        else return false

        val avaliableMoves: MutableList<Int> = mutableListOf(-6,6,-10,10,-15,15,-17,17)

        if(position / 8 == 7) avaliableMoves.removeIf { it > 0 }
        else if(position / 8 == 6) avaliableMoves.removeAll(listOf(15, 17))
        else if(position / 8 == 1) avaliableMoves.removeAll(listOf(-15, -17))
        else if(position / 8 == 0) avaliableMoves.removeIf {it < 0}

        if(position % 8 == 0) avaliableMoves.removeAll(listOf(6, 15, -10, -17))
        else if(position % 8 == 1) avaliableMoves.removeAll(listOf(6, -10))
        else if(position % 8 == 6) avaliableMoves.removeAll(listOf(10, -6))
        else if(position % 8 == 7) avaliableMoves.removeAll(listOf(-6, -15, 10, 17))

        if(newPosition - position !in avaliableMoves) return false

        if(board.isThereFigure(newPosition) && board.positions[newPosition]?.colour == colour) return false

        return true
    }

}