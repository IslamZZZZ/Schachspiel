package figures
import Board.Board
import kotlin.math.sign

class Rook(override var position: Int, override val colour: Boolean, override val board: Board): Figure {
    init {
        board.positions[position] = this
    }

    /*override fun isThereFigure(location: Int): Boolean {
        return location in board.positions
    }*/

    private fun isThereObstacle(start: Int, end: Int): Boolean {
        val sign = (end - start).sign
        //sign of this operation
        val jump = if(end - start % 8 == 0) 8 else 1
        //we jump to the forward(8) or to the backward(1)
        var i = 1
        while(start + sign * i * jump != end) if(board.isThereFigure(start + sign * i++ * jump)) return false
        //If we face obstacle, we can't move
        return true
    }

    override fun move(newPosition: Int): Boolean {
        if(newPosition - position % 8 == 0 || newPosition / 8 == position / 8) {
            if (!isThereObstacle(position, newPosition)) {
                if (board.isThereFigure(newPosition) && board.positions[newPosition]?.colour == colour) return false
                board.positions[newPosition] = this
                board.positions.remove(position)
                return true
            }
        }
        return false
    }

}