package figures
import Board.Board
import kotlin.math.sign

class Bishop(override var position: Int, override val colour: Boolean, override val board: Board) : Figure {
    init {
        board.positions[position] = this
    }

    override val figure = if(colour) "whiteBishop" else "blackBishop"

    /*override fun isThereFigure(location: Int): Boolean {
        return location in board.positions
    }*/

    private fun isThereObstacle(start: Int, end: Int): Boolean {
        val dif = end - start
        val sign = dif.sign
        //sign of this operation

        if( ( ( (sign == 1) && (dif % 7 == 0) ) ||
                    ( (sign == -1) && (dif % 9 == 0) ) ) &&
                        (end % 8 >= start % 8)) return true
        else if( ( ( (sign == -1) && (dif % 7 == 0) ) ||
                    ( (sign == 1) && (dif % 9 == 0) ) ) &&
                        (end % 8 <= start % 8)) return true

        val jump = if( (end - start) % 9 == 0) 9 else 7
        //we jump to the right(9/-7) or to the left(7/-9)
        var i = 1
        while(start + sign * i * jump != end) if(board.isThereFigure(start + sign * i++ * jump)) return true
        //If we face obstacle, we can't canMove
        return false
    }

    override fun canMove(newPosition: Int): Boolean {
        if( ((newPosition - position) % 7 == 0) || ((newPosition - position) % 9 == 0) ) {
            if (!isThereObstacle(position, newPosition)) {
                return !(board.isThereFigure(newPosition) && board.positions[newPosition]?.colour == colour)
            }
        }
        return false
    }

}