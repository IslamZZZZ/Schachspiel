package Board

import androidx.compose.runtime.*
import figures.*
import kotlin.math.abs

class Board {
    val positions: MutableMap<Int, Figure> = mutableMapOf()
    val whiteMoves: MutableList<Pair< Pair<Int, Int>, String > > = mutableListOf()
    val blackMoves: MutableList<Pair< Pair<Int, Int>, String > > = mutableListOf()
    var turn by mutableStateOf(true)
    var isWhiteCastled: Boolean = false
    var isBlackCastled: Boolean = false
    var enpassant: Boolean = false
    var isChecked: Boolean = false
    var gameWinner: Int = 0

    val composePositions = mutableStateOf(emptyMap<Int, Figure>())

    /*init {
        setup()
    }*/

    fun isThereFigure(position: Int): Boolean {
        return position in positions
    }

    fun move(startPosition: Int, finalPosition: Int): Boolean {
        if(!isThereFigure(startPosition)) return false

        /*val copiedPositions = positions.toMutableMap()
        copiedPositions[startPosition]?.let { copiedPositions[finalPosition] = it }
        copiedPositions.remove(startPosition)
        if(schach(copiedPositions)) return false*/

        if(turn == positions[startPosition]?.colour && (positions[startPosition]?.canMove(finalPosition) == true)) {
            positions[startPosition]?.let{
                positions[finalPosition] = it
                it.position = finalPosition

                if(enpassant) {
                    if(it.colour) positions.remove(finalPosition - 8)
                    else positions.remove(finalPosition + 8)

                    enpassant = false
                }
            }


            positions[startPosition]?.let{
                val figure = when (it) {
                    is Bishop -> "Bishop"
                    is King -> "King"
                    is Knight -> "Knight"
                    is Pawn -> "Pawn"
                    is Queen -> "Queen"
                    else -> "Rook"
                }

                if(turn) whiteMoves.add(Pair(Pair(startPosition, finalPosition), figure))
                else blackMoves.add(Pair(Pair(startPosition, finalPosition), figure))
            }

            positions.remove(startPosition)

            this.turn = !turn

            updateComposePosition()
            println("has moved from $startPosition to $finalPosition")

            return true
        }

        return false
    }

    fun schach(copiedPositions: MutableMap<Int, Figure>): Boolean {
        val king = copiedPositions.values.find { it is King && it.colour == this.turn}

        king?.let {
            for(fig in positions.values) if(fig.colour != it.colour && fig.canMove(it.position)) return true
        }

        return false
    }

    fun clean() {
        this.positions.clear()
    }

    fun reset() {
        this.clean()
        this.setup()
        this.turn = true
        whiteMoves.clear()
        blackMoves.clear()
        updateComposePosition()
    }

    fun setup() {
        for(i in 8..15) Pawn(i, true, this)
        for(i in 0..7) {
            if(i == 0 || i == 7) Rook(i, true, this)
            else if(i == 1 || i == 6) Knight(i, true, this)
            else if(i == 2 || i == 5) Bishop(i, true, this)
            else if(i == 3) Queen(i, true, this)
            else King(i, true, this)
        }

        for(i in 48..55) Pawn(i, false, this)
        for(i in 56..63) {
            if(i == 56 || i == 63) Rook(i, false, this)
            else if(i == 57 || i == 62) Knight(i, false, this)
            else if(i == 58 || i == 61) Bishop(i, false, this)
            else if(i == 59) Queen(i, false, this)
            else King(i, false, this)
        }

        updateComposePosition()
    }

    fun updateComposePosition() {
        composePositions.value = positions.toMap()
    }
}


//BOARD//
/*
56 57 58 59 60 61 62 63 - 8
48 49 50 51 52 53 54 55 - 7
40 41 42 43 44 45 46 47 - 6
32 33 34 35 36 37 38 39 - 5
24 25 26 27 28 29 30 31 - 4
16 17 18 19 20 21 22 23 - 3
8  9  10 11 12 13 14 15 - 2
0  1  2  3  4  5  6  7  - 1
a  b  c  d  e  f  g  h
 */