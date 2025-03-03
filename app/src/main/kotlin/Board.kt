package Board

import figures.*

class Board {
    val positions: MutableMap<Int, Figure> = mutableMapOf()
    val whiteMoves: MutableMap<Int, Figure> = mutableMapOf()
    val blackMoves: MutableMap<Int, Figure> = mutableMapOf()
    var turn: Boolean = true

    init {
        for(i in 8..15) positions[i] = Pawn(true, i, this)
        for(i in 48..55) positions[i] = Pawn(false, i, this)
    }
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