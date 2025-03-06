import Board.Board
import figures.Bishop
import figures.Pawn
import figures.Rook
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RookTest {
    @Test
    fun moveOnRow() {
        val board = Board()

        for(newPos in listOf(16,17,19,21,22,23)) {
            val rook = Rook(20, true, board)

            assertTrue(rook.move(newPos))
            assertEquals(rook, board.positions[newPos])
            assertNull(board.positions[20])

            board.clean()
        }

        var curPos = 20
        val rook = Rook(curPos, true, board)
        for(newPos in listOf(16,17,20,18,23,21,22)) {
            assertTrue(rook.move(newPos))
            assertEquals(rook, board.positions[newPos])
            assertNull(board.positions[curPos])
            curPos = newPos
        }
    }

    @Test
    fun moveOnColumn() {
        val board = Board()

        for(newPos in listOf(4,12,28,36,44,52,60)) {
            val rook = Rook(20, true, board)

            assertTrue(rook.move(newPos))
            assertEquals(rook, board.positions[newPos])
            assertNull(board.positions[20])

            board.clean()
        }

        var curPos = 20
        val rook = Rook(curPos, true, board)
        for(newPos in listOf(4,28,20,12,36,60,52,44)) {
            assertTrue(rook.move(newPos))
            assertEquals(rook, board.positions[newPos])
            assertNull(board.positions[curPos])
            curPos = newPos
        }
    }

    @Test
    fun unavaliableMoves() {
        val board = Board()

        for(newPos in listOf(8, 10, 14, 63, 50, 42, 32, 31, 0, 1, 24, 15, 59, 61)) {
            val rook = Rook(20, true, board)

            assertFalse(rook.move(newPos))
            assertNull(board.positions[newPos])
            assertEquals(rook, board.positions[20])

            board.clean()
        }
    }

    @Test
    fun obstaclesUNDtaking() {
        @Test
        fun moveOnRow() {
            val board = Board()
            val rook = Rook(20, true, board)
            val obstacle_ein = Pawn(44, true, board)
            var obstacle_zwei = Bishop(18, false, board)

            assertFalse(rook.move(16))
            assertFalse(rook.move(17))
            assertFalse(rook.move(52))
            assertFalse(rook.move(60))

            assertFalse(rook.move(44))
            assertTrue(rook.move(18))
        }
    }
}