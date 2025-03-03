package figures

import Board.Board

class Pawn(override val colour: Boolean, override val position: Int, override val board: Board): Figure{
    var noMoved: Boolean = true

    override fun move(newPosition: Int) {
        if(newPosition - position % 8 == 0) {
            TODO()
        }
    }


}