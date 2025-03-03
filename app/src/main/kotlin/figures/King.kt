package figures
import Board.Board
import kotlin.math.abs

class King(override val position: Int, override val colour: Boolean, override val board: Board): Figure {
    var noMoved = true

    override fun isThereFigure(location: Int): Boolean {
        return location in board.positions
    }

    override fun move(newPosition: Int): Boolean {
        /*if(this.noMoved && abs(newPosition - position) == 2) {
            if(0 in board.positions && board.positions[0]?.noMoved)
        }*/
        val flag = if(abs(newPosition - position) == 1) true
        else if(abs(newPosition - position) == 9) true
        else if(abs(newPosition - position) == 7) true
        else if(abs(newPosition - position) == 8) true
        else return false
        if(isThereFigure(newPosition) && board.positions[newPosition]?.colour == colour) return false
        board.positions[newPosition] = this
        board.positions.remove(position)
        return false
    }

}