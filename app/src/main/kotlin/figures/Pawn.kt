package figures

import Board.Board

class Pawn(override val position: Int, override val colour: Boolean, override val board: Board): Figure{
    init {
        board.positions[position] = this
    }

    override fun isThereFigure(location: Int): Boolean {
        return location in board.positions
    }

    override fun move(newPosition: Int): Boolean {
        if(newPosition - position == 8) {
            if(isThereFigure(newPosition)) return false
            //Is there a figure - we can't move
        }
        else if(newPosition - position == 16) {
            if(position / 8 == 1 || isThereFigure(newPosition) || isThereFigure(newPosition - 8)) return false
            //We've not moved or there's obstacle right before our move - we can't move
        }
        else if(newPosition - position == 7 && newPosition - position == 9) {
            if(!isThereFigure(newPosition) || board.positions[newPosition]?.colour == this.colour) return false
            //There's no figure, or it's ours - we can't move
        }
        else return false

        board.positions[newPosition] = this
        board.positions.remove(position)
        //We change new position with our figure and remove our previous position

        board.turn = !colour
        //We also change turn to another colour

        if(colour) board.whiteMoves[Pair(position, newPosition)] = "Pawn"
        else board.blackMoves[Pair(position, newPosition)] = "Pawn"

        return true
    }


}