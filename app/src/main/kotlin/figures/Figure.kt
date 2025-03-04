package figures

import Board.Board

interface Figure{
    var position: Int
    val colour: Boolean
    val board: Board

    // fun isThereFigure(location: Int): Boolean
    fun move(newPosition: Int): Boolean
}