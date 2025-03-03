package figures
import Board.Board
import kotlin.math.sign

class Bishop(override val position: Int, override val colour: Boolean, override val board: Board) : Figure {
    override fun isThereFigure(location: Int): Boolean {
        return location in board.positions
    }

    private fun isThereObstacle(start: Int, end: Int): Boolean {
        val sign = (start - end).sign
        //sign of this operation
        val jump = if(end - start % 9 == 0) 9 else 7
        //we jump to the right(9) or to the left(7)
        var i = 1
        while(start + sign * i * jump != end) if(isThereFigure(start + sign * i++ * jump)) return false
        //If we face obstacle, we can't move
        return true
    }

    override fun move(newPosition: Int): Boolean {
        if(newPosition - position % 7 == 0 || newPosition - position % 9 == 0) {
            if (!isThereObstacle(position, newPosition)) {
                if (isThereFigure(newPosition) && board.positions[newPosition]?.colour == colour) return false
                board.positions[newPosition] = this
                board.positions.remove(position)
                return true
            }
        }
        return false
    }

}