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

        //Костыль для избежания рекурсии, ибо далее вызывается метод шаха, он вызывает метод ходьбы от каждой
        //фигуры, в том числе и короля, который в свою очередь опять вызывает метод шаха
        board.positions[newPosition]?.let{
            if(it is King) return (position - newPosition) in avaliableMoves
        }

        if(!board.istSchach() && !board.hasMovedFrom(4)) {
            if (colour) {
                if (newPosition == 2 || newPosition == 0) {
                    if (!board.schach(4, 3) &&
                        !board.schach(4, 2) &&
                        !board.hasMovedFrom(0) &&
                        !board.isThereFigure(3) &&
                        !board.isThereFigure(2) &&
                        !board.isThereFigure(1)
                    ) board.isCastled = true
                } else if (newPosition == 6 || newPosition == 7) {
                    if (!board.schach(4, 5) &&
                        !board.schach(4, 6) &&
                        !board.hasMovedFrom(7) &&
                        !board.isThereFigure(5) &&
                        !board.isThereFigure(6)
                    ) board.isCastled = true
                }
            } else {
                if (newPosition == 58 || newPosition == 56) {
                    if (!board.schach(60, 59) &&
                        !board.schach(60, 58) &&
                        !board.hasMovedFrom(56) &&
                        !board.isThereFigure(59) &&
                        !board.isThereFigure(58) &&
                        !board.isThereFigure(57)
                    ) board.isCastled = true
                } else if (newPosition == 62 || newPosition == 63) {
                    if (!board.schach(60, 61) &&
                        !board.schach(60, 62) &&
                        !board.hasMovedFrom(63) &&
                        !board.isThereFigure(61) &&
                        !board.isThereFigure(62)
                    ) board.isCastled = true
                }
            }

            if(board.isCastled) return true
        }

        if( (position / 8) == 7) avaliableMoves.removeAll(listOf(7,8,9))
        else if( (position / 8) == 0) avaliableMoves.removeAll(listOf(-7, -8, -9))

        if( (position % 8) == 7) avaliableMoves.removeAll(listOf(9, 1, -7))
        else if( (position % 8) == 0) avaliableMoves.removeAll(listOf(7, -1, -9))

        if( (newPosition - position) !in avaliableMoves) return false

        if(board.isThereFigure(newPosition) && board.positions[newPosition]?.colour == colour) return false

        return true
    }

}