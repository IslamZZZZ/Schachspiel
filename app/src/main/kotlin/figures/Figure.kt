package figures

import Board.Board

interface Figure{
    var position: Int
    val colour: Boolean
    val board: Board
    val figure: String

    // fun isThereFigure(location: Int): Boolean
    fun canMove(newPosition: Int): Boolean
}