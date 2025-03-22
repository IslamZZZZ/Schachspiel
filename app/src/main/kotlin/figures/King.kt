package figures
import Board.Board
import kotlin.math.abs

class King(override var position: Int, override val colour: Boolean, override val board: Board): Figure {
    init {
        board.positions[position] = this
    }

    override val figure = if(colour) "whiteKing" else "blackKing"

    override fun canMove(newPosition: Int): Boolean {
        val avaliableMoves: MutableList<Int> = mutableListOf(-1, 1, -7, 7, -8, 8, -9, 9)
        /*if( (newPosition in listOf(2,6) && colour) || (newPosition in listOf(58,62) && !colour) ) {
            if(colour && position != 4) TODO()
            if(!colour && position != 60) TODO()
            if(colour) {
                if(newPosition == 2) {
                    if(board.isThereFigure(1) || board.isThereFigure(2) ||
                        board.isThereFigure(3)) TODO()

                }
            }
        }*/

        if( (position / 8) == 7) avaliableMoves.removeAll(listOf(7,8,9))
        else if( (position / 8) == 0) avaliableMoves.removeAll(listOf(-7, -8, -9))

        if( (position % 8) == 7) avaliableMoves.removeAll(listOf(9, 1, -7))
        else if( (position % 8) == 0) avaliableMoves.removeAll(listOf(7, -1, -9))

        if( (newPosition - position) !in avaliableMoves) return false

        if(board.isThereFigure(newPosition) && board.positions[newPosition]?.colour == colour) return false

        return true
    }

}