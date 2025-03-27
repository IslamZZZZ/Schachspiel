package Board

import androidx.compose.runtime.*
import figures.*
import kotlin.math.abs
import kotlin.math.sign

class Board {
    var positions: MutableMap<Int, Figure> = mutableMapOf()
    val whiteMoves: MutableList<String> = mutableListOf()
    val blackMoves: MutableList<String> = mutableListOf()
    val allMoves: MutableList< Pair< Pair<Int, Int>, String > > = mutableListOf()
    var turn by mutableStateOf(true)
    var isCastled: Boolean = false
    var enpassant: Boolean = false
    var isChecked: Boolean = false
    var composeChecked by mutableStateOf(false)
    var isPromoted by mutableStateOf(false)
    var promotedPosition by mutableStateOf(-1)
    var threat: Int = -1
    var gameWinner by mutableStateOf(0)

    val composePositions = mutableStateOf(emptyMap<Int, Figure>())

    fun clean() {
        this.positions.clear()
        this.composePositions.value = emptyMap<Int, Figure>()
        gameWinner = 0
        composeChecked = false
        isPromoted = false
        isCastled = false
        enpassant = false
        isChecked = false
        whiteMoves.clear()
        blackMoves.clear()
        allMoves.clear()
        threat = -1
        this.turn = true
        updateComposePosition()
    }

    /*init {
        setup()
    }*/

    fun isThereFigure(position: Int): Boolean {
        return position in positions
    }

    fun move(startPosition: Int, finalPosition: Int): Boolean {
        if(gameWinner != 0) return false

        if(!isThereFigure(startPosition)) return false

        if(schach(startPosition, finalPosition)) return false


        if(turn == positions[startPosition]?.colour && (positions[startPosition]?.canMove(finalPosition) == true)) {
            if(composeChecked) composeChecked = false

            if(!isCastled) {
                positions[startPosition]?.let {
                   if( ( (turn && (finalPosition / 8) == 7) || (!turn && (finalPosition / 8) == 0) ) && it is Pawn) {
                        isPromoted = true
                        promotedPosition = finalPosition

                        positions.remove(startPosition)
                        updateComposePosition()

                        this.turn = !turn
                        return true
                   }


                    positions[finalPosition] = it
                    it.position = finalPosition

                    if (enpassant) {
                        if (it.colour) positions.remove(finalPosition - 8)
                        else positions.remove(finalPosition + 8)

                        enpassant = false
                    }
                }
            }
            else{
                if(startPosition == 4 && (finalPosition == 2 || finalPosition == 0)) {
                    positions[4]?.let {
                        positions[2] = it
                        it.position = 2
                    }

                    positions[0]?.let{
                        positions[3] = it
                        it.position = 3
                    }

                    positions.remove(0)
                    positions.remove(4)

                    whiteMoves.add("0-0-0")
                }
                else if(startPosition == 4 && (finalPosition == 6 || finalPosition == 7)) {
                    positions[4]?.let {
                        positions[6] = it
                        it.position = 6
                    }

                    positions[7]?.let{
                        positions[5] = it
                        it.position = 5
                    }

                    positions.remove(7)
                    positions.remove(4)

                    whiteMoves.add("0-0")
                }
                else if(startPosition == 60 && (finalPosition == 58 || finalPosition == 56)) {
                    positions[60]?.let {
                        positions[58] = it
                        it.position = 58
                    }

                    positions[56]?.let{
                        positions[59] = it
                        it.position = 59
                    }

                    positions.remove(56)
                    positions.remove(60)

                    blackMoves.add("0-0-0")
                }
                else if(startPosition == 60 && (finalPosition == 62 || finalPosition == 63)) {
                    positions[60]?.let {
                        positions[62] = it
                        it.position = 62
                    }

                    positions[63]?.let{
                        positions[61] = it
                        it.position = 61
                    }

                    positions.remove(63)
                    positions.remove(60)

                    blackMoves.add("0-0")
                }
                else{isCastled = false
                return false}
                isCastled = false
                updateComposePosition()
                this.turn = !turn
                return true
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

                if(turn) whiteMoves.add("$figure: ${fromDigToNot(startPosition)} - ${fromDigToNot(finalPosition)}")
                else blackMoves.add("$figure: ${fromDigToNot(startPosition)} - ${fromDigToNot(finalPosition)}")

                allMoves.add(Pair(Pair(startPosition, finalPosition), figure))

                println("$figure has moved from $startPosition to $finalPosition")
            }

            positions.remove(startPosition)

            this.turn = !turn

            updateComposePosition()


            threat = -1
            val checks = istSchach()
            println("threat: $threat checks: $checks")
            if(checkMate(checks)) gameWinner = if(turn) -1 else 1
            else if(checks != 0) composeChecked = true


            return true
        }

        return false
    }


    fun checkMate(checks: Int): Boolean {
        if(checks == 0) return false
        val king = positions.values.find { (it is King) && (it.colour == this.turn) }

        king?.let {
            for(avalMove in listOf(-1, 1, -7, 7, -8, 8, -9, 9)) {
                if(it.canMove(it.position + avalMove) &&
                    !schach(it.position, it.position + avalMove)) return false
            }

            if(checks == 1) {
                val dif = threat - it.position
                val per = if ((threat - it.position) % 9 == 0) dif.sign * 9
                else if ((threat - it.position) % 7 == 0) dif.sign * 7
                else if ((threat - it.position) % 8 == 0) dif.sign * 8
                else if ( (threat / 8) == (it.position / 8) ) dif.sign * 1
                else 0

                println("dif: $dif per: $per")
                if (per == 0) {
                    for (fig in positions.values) if (fig.colour == turn) {
                        if (fig.canMove(threat) && !schach(fig.position, threat)) return false
                    }
                }

                else {
                    var newPosition = it.position + per
                    while (dif.sign * newPosition <= threat * dif.sign) {
                        println("newPosition: $newPosition")
                        for (fig in positions.values) if (fig.colour == turn && fig !is King) {
                            if (fig.canMove(newPosition) && !schach(fig.position, newPosition)) return false
                        }

                        newPosition += per
                    }
                }
            }
        }

        return true
    }

    fun promotingPawn(promotedFigure: String) {
        when(promotedFigure) {
            "Queen" -> Queen(promotedPosition, !turn, this)
            "Rook" -> Rook(promotedPosition, !turn, this)
            "Knight" -> Knight(promotedPosition, !turn, this)
            "Bishop" -> Bishop(promotedPosition, !turn, this)
        }
        updateComposePosition()
        isPromoted = false
        if(!turn) whiteMoves.add("Pawn $promotedFigure ${fromDigToNot(promotedPosition)}")
        else blackMoves.add("Pawn $promotedFigure ${fromDigToNot(promotedPosition)}")
    }

    fun fromDigToNot(square: Int): String {
        val letter = when(square % 8) {
            0 -> "a"
            1 -> "b"
            2 -> "c"
            3 -> "d"
            4 -> "e"
            5 -> "f"
            6 -> "g"
            else -> "h"
        }

        return "${letter}${ (square / 8) + 1 }"
    }

    fun schach(startPosition: Int, finalPosition: Int): Boolean {
        positions[startPosition]?.let {
            positions[finalPosition] = it
            it.position = finalPosition
        }

        positions.remove(startPosition)


        if(istSchach() != 0) isChecked = true

        positions[finalPosition]?.let{ it.position = startPosition }
        positions = composePositions.value.toMutableMap()

        if(isChecked) {
            isChecked = false
            return true
        }

        return false
    }

    fun istSchach(): Int {
        var count = 0
        val king = positions.values.find { (it is King) && (it.colour == this.turn) }

        king?.let {
            for(fig in positions.values) if( (fig.colour != it.colour) && (fig.canMove(it.position))) {
                count++
                if(threat == -1) threat = fig.position
            }
        }

        return count
    }


    fun reset() {
        this.clean()
        this.setup()
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

    fun hasMovedFrom(startPosition: Int): Boolean {
        for(pos in allMoves) if(pos.first.first == startPosition) return true
        return false
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