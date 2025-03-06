package figures
import Board.Board
import kotlin.math.sign

class Queen(override var position: Int, override val colour: Boolean, override val board: Board): Figure {
    init {
        board.positions[position] = this
    }

    private fun isThereObstacle_BISHOP(start: Int, end: Int): Boolean {
        val dif = end - start
        val sign = dif.sign
        //sign of this operation

        if( ( ( (sign == 1) && (dif % 7 == 0) ) ||
                    ( (sign == -1) && (dif % 9 == 0) ) ) &&
            (end % 8 >= start % 8)) return true
        else if( ( ( (sign == -1) && (dif % 7 == 0) ) ||
                    ( (sign == 1) && (dif % 9 == 0) ) ) &&
            (end % 8 <= start % 8)) return true

        val jump = if(end - start % 9 == 0) 9 else 7
        //we jump to the right(9/-7) or to the left(7/-9)
        var i = 1
        while(start + sign * i * jump != end) if(board.isThereFigure(start + sign * i++ * jump)) return true
        //If we face obstacle, we can't move
        return false
    }

    private fun isThereObstacle_ROOK(start: Int, end: Int): Boolean {
        val sign = (end - start).sign
        //sign of this operation
        val jump = if( (end - start) % 8 == 0) 8 else 1

        var i = 1
        while(start + sign * i * jump != end) if(board.isThereFigure(start + sign * i++ * jump)) return true
        //If we face obstacle, we can't move
        return false
    }

    override fun move(newPosition: Int): Boolean {
        if( (newPosition - position) % 8 == 0 || ( (newPosition / 8) == (position / 8) ) ) {
            if (!isThereObstacle_ROOK(position, newPosition)) {
                if (board.isThereFigure(newPosition) && board.positions[newPosition]?.colour == colour) return false

                board.positions[newPosition] = this
                board.positions.remove(position)
                this.position = newPosition

                return true
            }
        }

        else if( ((newPosition - position) % 7 == 0) || ((newPosition - position) % 9 == 0) ) {
            if (!isThereObstacle_BISHOP(position, newPosition)) {
                if (board.isThereFigure(newPosition) && board.positions[newPosition]?.colour == colour) return false

                board.positions[newPosition] = this
                board.positions.remove(position)
                this.position = newPosition

                return true
            }
        }

        return false
    }

}