package figures

import Board.Board

class Pawn(override val colour: Boolean, override val position: Int, override val board: Board): Figure{
    init {
        board.positions[position] = this
    }

    var noMoved: Boolean = true

    override fun isThereFigure(location: Int): Boolean {
        return location in board.positions
    }

    override fun move(newPosition: Int): Boolean {
        if(newPosition - position == 8) {
            if(isThereFigure(newPosition)) return false
        }
        else if(newPosition - position == 16) {
            if(!noMoved || isThereFigure(newPosition) || isThereFigure(newPosition - 8)) return false
        }
        else if(newPosition - position == 7 && newPosition - position == 9) {
            if(!isThereFigure(newPosition) || board.positions[newPosition]?.colour == this.colour) return false
        }
        else return false
        board.positions[newPosition] = this
        board.positions.remove(position)
        return true
    }


}