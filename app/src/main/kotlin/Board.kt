package Board

import figures.*

class Board {
    val positions: MutableMap<Int, Figure> = mutableMapOf()
    val whiteMoves: MutableMap<Pair<Int, Int>, String> = mutableMapOf()
    val blackMoves: MutableMap<Pair<Int, Int>, String> = mutableMapOf()
    var turn: Boolean = true
    var isChecked: Boolean = false

    fun isThereFigure(position: Int): Boolean {
        return position in positions
    }

    fun move(startPosition: Int, finalPosition: Int): Boolean {
        if(!isThereFigure(startPosition)) return false
        if(turn == positions[startPosition]?.colour) return positions[startPosition]?.move(finalPosition) ?: false
        return positions[startPosition]?.move(finalPosition) ?: false
    }

    /*init {
        for(i in 8..15) Pawn(i, true, this)
        for(i in 0..7) {
            if(i == 0 || i == 7) Rook(i, true, this)
            else if(i == 1 || i == 6) Knight(i, true, this)
            else if(i == 2 || i == 5) Bishop(i, true, this)
            else if(i == 4) Queen(i, true, this)
            else King(i, true, this)
        }

        for(i in 48..55) Pawn(i, false, this)
        for(i in 56..63) {
            if(i == 56 || i == 63) Rook(i, true, this)
            else if(i == 57 || i == 62) Knight(i, true, this)
            else if(i == 58 || i == 61) Bishop(i, true, this)
            else if(i == 59) Queen(i, true, this)
            else King(i, true, this)
        }
    }*/
}

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