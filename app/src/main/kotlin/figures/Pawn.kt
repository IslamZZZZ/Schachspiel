package figures

import Board.Board
import kotlin.math.abs

class Pawn(override var position: Int, override val colour: Boolean, override val board: Board): Figure{
    init {
        board.positions[position] = this
    }

    override val figure = if(colour) "whitePawn" else "blackPawn"

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
            if(board.positions[newPosition]?.colour == !board.turn) return true
            if(!board.isThereFigure(newPosition)) {
                if(board.turn && (position / 8 == 4)) {
                    if(board.blackMoves.last().first.first == newPosition + 8 &&
                        board.blackMoves.last().second == "Pawn") board.enpassant = true
                    else return false
                }
                else if(!board.turn && (position / 8 == 3)) {
                    if(board.whiteMoves.last().first.first == newPosition - 8 &&
                        board.whiteMoves.last().second == "Pawn") board.enpassant = true
                    else return false
                }
                else return false
            }
            if(board.positions[newPosition]?.colour == this.colour) return false
        }
        else return false


        return true
    }


}