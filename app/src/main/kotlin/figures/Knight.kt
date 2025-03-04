package figures
import Board.Board

class Knight(override var position: Int, override val colour: Boolean, override val board: Board): Figure {
    init {
        board.positions[position] = this
    }

    /*override fun isThereFigure(location: Int): Boolean {
        return location in board.positions
    }*/

    override fun move(newPosition: Int): Boolean {
        val flag = if(newPosition - position == 15 || position - newPosition == 15) true
        else if(newPosition - position == 17 || position - newPosition == 17) true
        else if(newPosition - position == 10 || position - newPosition == 10) true
        else if(newPosition - position == 6 || position - newPosition == 6) true
        else return false
        if(board.isThereFigure(newPosition) && board.positions[newPosition]?.colour == colour) return false
        board.positions[newPosition] = this
        board.positions.remove(position)
        return true
    }

}