import Board.Board
import figures.Bishop
import figures.Queen
import figures.Pawn
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

class QueenTest {
    @Test
    fun severalMoves() {
        val board = Board()
        val queen = Queen(10, true, board)

        assertTrue(queen.move(28))
        assertNull(board.positions[10])
        assertEquals(queen, board.positions[28])

        assertTrue(queen.move(42))
        assertNull(board.positions[28])
        assertEquals(queen, board.positions[42])

        assertTrue(queen.move(24))
        assertNull(board.positions[42])
        assertEquals(queen, board.positions[24])

        assertTrue(queen.move(3))
        assertNull(board.positions[24])
        assertEquals(queen, board.positions[3])

        assertTrue(queen.move(30))
        assertNull(board.positions[3])
        assertEquals(queen, board.positions[30])
    }


    @Nested
    inner class Obstacles {
        @Test
        fun obstacle_ein() {
            val board = Board()

            val queen = Queen(27, true, board)
            val obstacle = Pawn(54, false, board)

            assertFalse(queen.move(63))
            assertEquals(obstacle, board.positions[54])
            assertEquals(queen, board.positions[27])
            assertNull(board.positions[63])
        }

        @Test
        fun obstacle_zwei() {
            val board = Board()

            val queen = Queen(27, true, board)
            val obstacle = Pawn(41, false, board)

            assertFalse(queen.move(48))
            assertEquals(obstacle, board.positions[41])
            assertEquals(queen, board.positions[27])
            assertNull(board.positions[48])
        }

        @Test
        fun obstacle_drei() {
            val board = Board()

            val queen = Queen(27, true, board)
            val obstacle = Pawn(13, true, board)

            assertFalse(queen.move(6))
            assertEquals(obstacle, board.positions[13])
            assertEquals(queen, board.positions[27])
            assertNull(board.positions[6])
        }

        @Test
        fun obstacle_vier() {
            val board = Board()

            val queen = Queen(27, true, board)
            val obstacle = Pawn(18, false, board)

            assertFalse(queen.move(0))
            assertEquals(obstacle, board.positions[18])
            assertEquals(queen, board.positions[27])
            assertNull(board.positions[0])
        }

        @Test
        fun obstacle_fuenf() {
            val board = Board()

            val queen = Queen(27, true, board)
            val obstacle = Pawn(9, true, board)

            assertFalse(queen.move(9))
            assertEquals(obstacle, board.positions[9])
            assertEquals(queen, board.positions[27])
        }
    }

    @org.junit.jupiter.api.Test
    fun moveOnRow() {
        val board = Board()

        for(newPos in listOf(16,17,19,21,22,23)) {
            val rook = Queen(20, true, board)

            kotlin.test.assertTrue(rook.move(newPos))
            assertEquals(rook, board.positions[newPos])
            assertNull(board.positions[20])

            board.clean()
        }

        var curPos = 20
        val rook = Queen(curPos, true, board)
        for(newPos in listOf(16,17,20,18,23,21,22)) {
            kotlin.test.assertTrue(rook.move(newPos))
            assertEquals(rook, board.positions[newPos])
            assertNull(board.positions[curPos])
            curPos = newPos
        }
    }

    @org.junit.jupiter.api.Test
    fun moveOnColumn() {
        val board = Board()

        for(newPos in listOf(4,12,28,36,44,52,60)) {
            val rook = Queen(20, true, board)

            kotlin.test.assertTrue(rook.move(newPos))
            assertEquals(rook, board.positions[newPos])
            assertNull(board.positions[20])

            board.clean()
        }

        var curPos = 20
        val rook = Queen(curPos, true, board)
        for(newPos in listOf(4,28,20,12,36,60,52,44)) {
            kotlin.test.assertTrue(rook.move(newPos))
            assertEquals(rook, board.positions[newPos])
            assertNull(board.positions[curPos])
            curPos = newPos
        }
    }

    @org.junit.jupiter.api.Test
    fun unavaliableMoves() {
        val board = Board()

        for(newPos in listOf(8, 10, 14, 63, 50, 42, 32, 31, 0, 1, 24, 15, 59, 61)) {
            val rook = Queen(20, true, board)

            Assertions.assertFalse(rook.move(newPos))
            assertNull(board.positions[newPos])
            assertEquals(rook, board.positions[20])

            board.clean()
        }
    }

    @org.junit.jupiter.api.Test
    fun obstaclesUNDtaking() {
        @org.junit.jupiter.api.Test
        fun moveOnRow() {
            val board = Board()
            val rook = Queen(20, true, board)
            val obstacle_ein = Pawn(44, true, board)
            var obstacle_zwei = Bishop(18, false, board)

            Assertions.assertFalse(rook.move(16))
            Assertions.assertFalse(rook.move(17))
            Assertions.assertFalse(rook.move(52))
            Assertions.assertFalse(rook.move(60))

            Assertions.assertFalse(rook.move(44))
            kotlin.test.assertTrue(rook.move(18))
        }
    }
}