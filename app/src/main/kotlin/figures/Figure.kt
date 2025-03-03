package figures

import Board.Board

interface Figure{
    val position: Int
    val colour: Boolean
    val board: Board


    fun move(newPosition: Int)
}