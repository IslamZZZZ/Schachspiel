package figures

import Board.Board
import kotlin.math.abs

class Pawn(override var position: Int, override val colour: Boolean, override val board: Board): Figure{
    init {
        board.positions[position] = this
    }

    /*override fun isThereFigure(location: Int): Boolean {
        TODO()
    }*/

    override fun canMove(newPosition: Int): Boolean {
        val sign = if(colour) 1 else -1
        val dif = newPosition - position

        if(abs(dif) == 8) {
            if(board.isThereFigure(newPosition)) return false
            //Is there a figure - we can't canMove
        }
        else if(abs(dif) == 16) {
            //if(position / 8 != 1 || board.isThereFigure(newPosition) || board.isThereFigure(newPosition - 8)) return false
            if( (colour && position / 8 != 1) || (!colour && position / 8 != 6)) return false
            if(board.isThereFigure(newPosition) || board.isThereFigure(newPosition - 8 * sign)) return false
            //We've not moved or there's obstacle right before our canMove - we can't canMove
        }
        else if( (abs(dif) == 8 - sign && position % 8 != 0) || (abs(dif) == 8 + sign && position % 8 != 7) ) {
            if(!board.isThereFigure(newPosition) || board.positions[newPosition]?.colour == this.colour) return false
            //There's no figure, or it's ours - we can't canMove
        }
        else return false


        return true
    }


}