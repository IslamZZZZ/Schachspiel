package figures
import Board.Board
import kotlin.math.sign

class Queen(override val position: Int, override val colour: Boolean, override val board: Board): Figure {
    override fun isThereFigure(location: Int): Boolean {
        return location in board.positions
    }

    private fun isThereObstacle(start: Int, end: Int): Boolean {
        val sign = (end - start).sign
        //sign of this operation
        val jump = when {
            (end - start) % 9 == 0 -> 9
            (end - start) % 8 == 0 -> 8
            (end - start) % 7 == 0 -> 7
            else -> 1
        }
        //we jump to the forward(8) or to the backward(1)
        var i = 1
        while(start + sign * i * jump != end) if(isThereFigure(start + sign * i++ * jump)) return false
        //If we face obstacle, we can't move
        return true
    }

    override fun move(newPosition: Int): Boolean {
        if(newPosition - position % 8 == 0 || newPosition / 8 == position / 8) {
            if (!isThereObstacle(position, newPosition)) {
                if (isThereFigure(newPosition) && board.positions[newPosition]?.colour == colour) return false
                board.positions[newPosition] = this
                board.positions.remove(position)
                return true
            }
        }
        else if(newPosition - position % 7 == 0 || newPosition - position % 9 == 0) {
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