import Board.Board
import figures.King
import figures.Knight
import figures.Pawn
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class KingTest {
    @Test
    fun canMove() {
        val board = Board()

        for(newPos in listOf(-1, 1, -7, 7, -8, 8, -9, 9)) {
            val king = King(28, true, board)

            assertTrue(king.canMove(28 + newPos))
//            assertNull(board.positions[28])
//            assertEquals(king, board.positions[28 + newPos])

            board.clean()
        }
    }

    @Test
    fun moveOnRow_8_1() {
        val board = Board()

        for(newPos in listOf(-1, 1, -7, 7, -8, 8, -9, 9)) {
            val king = King(60, true, board)

            if(newPos in listOf(7, 8, 9)) {
                assertFalse(king.canMove(60 + newPos))
//                assertNull(board.positions[60 + newPos])
//                assertEquals(king, board.positions[60])
            }
            else {
                assertTrue(king.canMove(60 + newPos))
//                assertNull(board.positions[60])
//                assertEquals(king, board.positions[60 + newPos])
            }

            board.clean()
        }

        for(newPos in listOf(-1, 1, -7, 7, -8, 8, -9, 9)) {
            val king = King(4, true, board)

            if(newPos in listOf(-7, -8, -9)) {
                assertFalse(king.canMove(4 + newPos))
//                assertNull(board.positions[4 + newPos])
//                assertEquals(king, board.positions[4])
            }
            else {
                assertTrue(king.canMove(4 + newPos))
//                assertNull(board.positions[4])
//                assertEquals(king, board.positions[4 + newPos])
            }

            board.clean()
        }
    }

    @Test
    fun moveOnCol_8_1() {
        val board = Board()

        for(newPos in listOf(-1, 1, -7, 7, -8, 8, -9, 9)) {
            val king = King(32, true, board)

            if(newPos in listOf(7, -1, -9)) {
                assertFalse(king.canMove(32 + newPos))
//                assertNull(board.positions[32 + newPos])
//                assertEquals(king, board.positions[32])
            }
            else {
                assertTrue(king.canMove(32 + newPos))
//                assertNull(board.positions[32])
//                assertEquals(king, board.positions[32 + newPos])
            }

            board.clean()
        }

        for(newPos in listOf(-1, 1, -7, 7, -8, 8, -9, 9)) {
            val king = King(31, true, board)

            if(newPos in listOf(-7, 1, 9)) {
                assertFalse(king.canMove(31 + newPos))
//                assertNull(board.positions[31 + newPos])
//                assertEquals(king, board.positions[31])
            }
            else {
                assertTrue(king.canMove(31 + newPos))
//                assertNull(board.positions[31])
//                assertEquals(king, board.positions[31 + newPos])
            }

            board.clean()
        }
    }

    @Test
    fun moveOnRowCol_8_1() {
        val board = Board()

        for(newPos in listOf(-1, 1, -7, 7, -8, 8, -9, 9)) {
            val startPos = 0
            val king = King(startPos, true, board)

            if(newPos in listOf(7, -1, -9, -8, -7)) {
                assertFalse(king.canMove(startPos + newPos))
//                assertNull(board.positions[startPos + newPos])
//                assertEquals(king, board.positions[startPos])
            }
            else {
                assertTrue(king.canMove(startPos + newPos))
//                assertNull(board.positions[startPos])
//                assertEquals(king, board.positions[startPos + newPos])
            }

            board.clean()
        }

        for(newPos in listOf(-1, 1, -7, 7, -8, 8, -9, 9)) {
            val startPos = 56
            val king = King(startPos, true, board)

            if(newPos in listOf(7, -1, -9, 8, 9)) {
                assertFalse(king.canMove(startPos + newPos))
//                assertNull(board.positions[startPos + newPos])
//                assertEquals(king, board.positions[startPos])
            }
            else {
                assertTrue(king.canMove(startPos + newPos))
//                assertNull(board.positions[startPos])
//                assertEquals(king, board.positions[startPos + newPos])
            }

            board.clean()
        }

        for(newPos in listOf(-1, 1, -7, 7, -8, 8, -9, 9)) {
            val startPos = 63
            val king = King(startPos, true, board)

            if(newPos in listOf(7, 1, -7, 8, 9)) {
                assertFalse(king.canMove(startPos + newPos))
//                assertNull(board.positions[startPos + newPos])
//                assertEquals(king, board.positions[startPos])
            }
            else {
                assertTrue(king.canMove(startPos + newPos))
//                assertNull(board.positions[startPos])
//                assertEquals(king, board.positions[startPos + newPos])
            }

            board.clean()
        }

        for(newPos in listOf(-1, 1, -7, 7, -8, 8, -9, 9)) {
            val startPos = 7
            val king = King(startPos, true, board)

            if(newPos in listOf(-7, 1, -9, -8, 9)) {
                assertFalse(king.canMove(startPos + newPos))
//                assertNull(board.positions[startPos + newPos])
//                assertEquals(king, board.positions[startPos])
            }
            else {
                assertTrue(king.canMove(startPos + newPos))
//                assertNull(board.positions[startPos])
//                assertEquals(king, board.positions[startPos + newPos])
            }

            board.clean()
        }
    }

    @Test
    fun obstacle_taking() {
        val board = Board()
        val king = King(28, true, board)

        val pawn = Pawn(27, false, board)
        val knight = Knight(35, true, board)

        assertFalse(king.canMove(35))
        assertTrue(king.canMove(27))
//        assertEquals(king, board.positions[27])
//        assertNull(board.positions[28])
    }
}