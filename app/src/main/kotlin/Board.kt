package Board

import figures.*
import kotlin.math.abs

class Board {
    val positions: MutableMap<Int, Figure> = mutableMapOf()
    val whiteMoves: MutableMap<Pair<Int, Int>, String> = mutableMapOf()
    val blackMoves: MutableMap<Pair<Int, Int>, String> = mutableMapOf()
    var turn: Boolean = true
    var isChecked: Boolean = false
    var gameWinner: Int = 0

    fun isThereFigure(position: Int): Boolean {
        return position in positions
    }

    fun move(startPosition: Int, finalPosition: Int): Boolean {
        if(!isThereFigure(startPosition)) return false

        val copiedPositions = positions.toMutableMap()
        copiedPositions[startPosition]?.let { copiedPositions[finalPosition] = it }
        copiedPositions.remove(startPosition)
        if(schach(copiedPositions)) return false

        if(turn == positions[startPosition]?.colour && (positions[startPosition]?.canMove(finalPosition) == true)) {
            positions[startPosition]?.let{ positions[finalPosition] = it }
            positions.remove(startPosition)


            positions[startPosition]?.let{
                val figure = if(it is Bishop) "Bishop"
                else if(it is King) "King"
                else if(it is Knight) "Knight"
                else if(it is Pawn) "Pawn"
                else if(it is Queen) "Queen"
                else "Rook"

                if(turn) whiteMoves[Pair(startPosition, finalPosition)] = figure
                else blackMoves[Pair(startPosition, finalPosition)] = figure
            }

            this.turn = !turn

            return true
        }

        return false
    }

    fun schach(copiedPositions: MutableMap<Int, Figure>): Boolean {
        val king = copiedPositions.values.find { it is King && it.colour == this.turn}

        king?.let {
            for(fig in positions.values) if(fig.colour != it.colour && fig.canMove(it.position)) return true
        }

        //Как сделать для коня? Надо заменить canMove на canMove(а то у нас проблема, что вызов функции вызывает само
        //движение, хотя порой нам нужно знать лишь может ль фигура туда пойти)
        //перенести последние строки перемещения в Board(они все одинаковые)
        //далее ищем всех вражеских коней, и в canMove ставим положение короля, если true
        //то очевидно, что шах, а иначе шаха от коня нету
        //Так наверное можно для всех фигур противоположного цвета сделать
        //и по идее это не должно быть ресурснозатратно относительно того, что я уже сделал

        /*king?.let {
            var pos = it.position + 8
            while(pos < 64) {
                if(pos in copiedPositions) {
                    val fig = copiedPositions[pos]
                    if(abs(pos - it.position) == 8 && fig is King) return true
                    if(fig?.colour == this.turn || fig is Pawn || fig is Knight || fig is Bishop) break
                    else return true
                }
                pos += 8
            }
        }

        king?.let {
            var pos = it.position - 8
            while(pos > -1) {
                if(pos in copiedPositions) {
                    val fig = copiedPositions[pos]
                    if(abs(pos - it.position) == 8 && fig is King) return true
                    if(fig?.colour == this.turn || fig is Pawn || fig is Knight || fig is Bishop) break
                    else return true
                }
                pos -= 8
            }
        }

        king?.let {
            var pos = it.position + 1
            while( (pos / 8) == (it.position / 8) ) {
                if(pos in copiedPositions) {
                    val fig = copiedPositions[pos]
                    if(abs(pos - it.position) == 1 && fig is King) return true
                    if(fig?.colour == this.turn || fig is Pawn || fig is Knight || fig is Bishop) break
                    else return true
                }
                pos += 1
            }
        }

        king?.let {
            var pos = it.position - 1
            while( (pos / 8) == (it.position / 8) ) {
                if(pos in copiedPositions) {
                    val fig = copiedPositions[pos]
                    if(abs(pos - it.position) == 1 && fig is King) return true
                    if(fig?.colour == this.turn || fig is Pawn || fig is Knight || fig is Bishop) break
                    else return true
                }
                pos -= 1
            }
        }

        king?.let {
            var pos = it.position + 9
            while( (pos % 8) > (it.position % 8) ) {
                if(pos in copiedPositions) {
                    val fig = copiedPositions[pos]
                    if(abs(pos - it.position) == 9 && (fig is Pawn || fig is King)) return true
                    if(fig?.colour == this.turn || fig is Pawn || fig is Knight || fig is Rook) break
                    else return true
                }
                pos += 9
            }
        }

        king?.let {
            var pos = it.position - 9
            while( (pos % 8) < (it.position % 8) ) {
                if(pos in copiedPositions) {
                    val fig = copiedPositions[pos]
                    if(abs(pos - it.position) == 9 && (fig is Pawn || fig is King)) return true
                    if(fig?.colour == this.turn || fig is Pawn || fig is Knight || fig is Rook) break
                    else return true
                }
                pos -= 9
            }
        }

        king?.let {
            var pos = it.position + 7
            while( (pos % 8) < (it.position % 8) ) {
                if(pos in copiedPositions) {
                    val fig = copiedPositions[pos]
                    if(abs(pos - it.position) == 7 && (fig is Pawn || fig is King)) return true
                    if(fig?.colour == this.turn || fig is Pawn || fig is Knight || fig is Rook) break
                    else return true
                }
                pos += 7
            }
        }

        king?.let {
            var pos = it.position - 7
            while( (pos % 8) > (it.position % 8) ) {
                if(pos in copiedPositions) {
                    val fig = copiedPositions[pos]
                    if(abs(pos - it.position) == 7 && (fig is Pawn || fig is King)) return true
                    if(fig?.colour == this.turn || fig is Pawn || fig is Knight || fig is Rook) break
                    else return true
                }
                pos -= 7
            }
        }*/

        return false
    }

    fun clean() {
        this.positions.clear()
    }

    fun setup() {
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