import Board.Board
import figures.Bishop
import figures.Pawn
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

class BishopTest {
    @Test
    fun severalMoves() {
        val board = Board()
        val bishop = Bishop(10, true, board)

        assertTrue(bishop.move(28))
        assertNull(board.positions[10])
        assertEquals(bishop, board.positions[28])

        assertTrue(bishop.move(42))
        assertNull(board.positions[28])
        assertEquals(bishop, board.positions[42])

        assertTrue(bishop.move(24))
        assertNull(board.positions[42])
        assertEquals(bishop, board.positions[24])

        assertTrue(bishop.move(3))
        assertNull(board.positions[24])
        assertEquals(bishop, board.positions[3])

        assertTrue(bishop.move(30))
        assertNull(board.positions[3])
        assertEquals(bishop, board.positions[30])
    }


    @Nested
    @DisplayName("Так как при этом тесте нужно так же проверять и все правильные ходы, то они по сути" +
            "так же являются часть этого подкласса, чтоб отдельно их не расписывать")
    inner class Unavaliable_Moves {
        @Test
        fun notOnTheLine() {
            val board = Board()
            val bishop = Bishop(27, true, board)

            for(newPos in listOf(42, 12, 2, 24, 60, 46, 1, 2, 3, 4, 5)) {
                assertFalse(bishop.move(newPos))
                assertEquals(bishop, board.positions[27])
                assertNull(board.positions[newPos])
            }
        }

        @Test
        @DisplayName("Когда лежит на линии, но если б она продолжалась, но у нас" +
                "есть проблема, что доска 8х8, и линия как бы продолжается, но" +
                "в другом месте, чего нам не надо")
        fun onTheLine_Plus7_Minus9() {
            val board = Board()

            //EIN BEISPIEL MIT SIEBEN(7)
            for(newPos in listOf(17, 24, 31, 38, 45, 52, 59)) {
                val bishop = Bishop(10, true, board)
                if(newPos > 24) {
                    assertFalse(bishop.move(newPos))
                    assertNull(board.positions[newPos])
                    assertEquals(bishop, board.positions[10])
                }
                else {
                    assertTrue(bishop.move(newPos))
                    assertEquals(bishop, board.positions[newPos])
                    assertNull(board.positions[10])
                }

                board.clean()
            }

            //EIN BEISPIEL MIT SIEBEN(7)
            for(newPos in listOf(7, 14, 21, 28, 35, 42, 49, 56)) {
                val bishop = Bishop(0, true, board)
                if(newPos > 0) {
                    assertFalse(bishop.move(newPos))
                    assertNull(board.positions[newPos])
                    assertEquals(bishop, board.positions[0])
                }
                else {
                    assertTrue(bishop.move(newPos))
                    assertEquals(bishop, board.positions[newPos])
                    assertNull(board.positions[0])
                }

                board.clean()
            }

            //EIN BEISPIEL MIT NEUN(9)
            for(newPos in listOf(41, 32, 23, 14, 5)) {
                val bishop = Bishop(50, true, board)
                if(newPos < 32) {
                    assertFalse(bishop.move(newPos))
                    assertNull(board.positions[newPos])
                    assertEquals(bishop, board.positions[50])
                }
                else {
                    assertTrue(bishop.move(newPos))
                    assertEquals(bishop, board.positions[newPos])
                    assertNull(board.positions[50])
                }

                board.clean()
            }

            //EIN BEISPIEL MIT NEUN(9)
            for(newPos in listOf(47, 38, 29, 20, 11, 2)) {
                val bishop = Bishop(56, true, board)
                if(newPos < 56) {
                    assertFalse(bishop.move(newPos))
                    assertNull(board.positions[newPos])
                    assertEquals(bishop, board.positions[56])
                }
                else {
                    assertTrue(bishop.move(newPos))
                    assertEquals(bishop, board.positions[newPos])
                    assertNull(board.positions[56])
                }

                board.clean()
            }
        }

        @Test
        fun onTheLine_Plus9_Minus7() {
            val board = Board()

            //EIN BEISPIEL MIT SIEBEN(7)
            for(newPos in listOf(39, 32, 25, 18, 11, 4)) {
                val bishop = Bishop(46, true, board)
                if(newPos < 39) {
                    assertFalse(bishop.move(newPos))
                    assertNull(board.positions[newPos])
                    assertEquals(bishop, board.positions[46])
                }
                else {
                    assertTrue(bishop.move(newPos))
                    assertEquals(bishop, board.positions[newPos])
                    assertNull(board.positions[46])
                }

                board.clean()
            }

            //EIN BEISPIEL MIT SIEBEN(7)
            for(newPos in listOf(56, 49, 42, 35, 28, 21, 14, 7, 0)) {
                val bishop = Bishop(63, true, board)
                if(newPos < 63) {
                    assertFalse(bishop.move(newPos))
                    assertNull(board.positions[newPos])
                    assertEquals(bishop, board.positions[63])
                }
                else {
                    assertTrue(bishop.move(newPos))
                    assertEquals(bishop, board.positions[newPos])
                    assertNull(board.positions[63])
                }

                board.clean()
            }

            //EIN BEISPIEL MIT NEUN(9)
            for(newPos in listOf(22, 31, 40, 49, 58)) {
                val bishop = Bishop(13, true, board)
                if(newPos > 31) {
                    assertFalse(bishop.move(newPos))
                    assertNull(board.positions[newPos])
                    assertEquals(bishop, board.positions[13])
                }
                else {
                    assertTrue(bishop.move(newPos))
                    assertEquals(bishop, board.positions[newPos])
                    assertNull(board.positions[13])
                }

                board.clean()
            }

            //EIN BEISPIEL MIT NEUN(9)
            for(newPos in listOf(16, 25, 34, 43, 52, 61)) {
                val bishop = Bishop(7, true, board)
                if(newPos > 7) {
                    assertFalse(bishop.move(newPos))
                    assertNull(board.positions[newPos])
                    assertEquals(bishop, board.positions[7])
                }
                else {
                    assertTrue(bishop.move(newPos))
                    assertEquals(bishop, board.positions[newPos])
                    assertNull(board.positions[7])
                }

                board.clean()
            }
        }
    }

    @Nested
    inner class Obstacles {
        @Test
        fun obstacle_ein() {
            val board = Board()

            val bishop = Bishop(27, true, board)
            val obstacle = Pawn(54, false, board)

            assertFalse(bishop.move(63))
            assertEquals(obstacle, board.positions[54])
            assertEquals(bishop, board.positions[27])
            assertNull(board.positions[63])
        }

        @Test
        fun obstacle_zwei() {
            val board = Board()

            val bishop = Bishop(27, true, board)
            val obstacle = Pawn(41, false, board)

            assertFalse(bishop.move(48))
            assertEquals(obstacle, board.positions[41])
            assertEquals(bishop, board.positions[27])
            assertNull(board.positions[48])
        }

        @Test
        fun obstacle_drei() {
            val board = Board()

            val bishop = Bishop(27, true, board)
            val obstacle = Pawn(13, true, board)

            assertFalse(bishop.move(6))
            assertEquals(obstacle, board.positions[13])
            assertEquals(bishop, board.positions[27])
            assertNull(board.positions[6])
        }

        @Test
        fun obstacle_vier() {
            val board = Board()

            val bishop = Bishop(27, true, board)
            val obstacle = Pawn(18, false, board)

            assertFalse(bishop.move(0))
            assertEquals(obstacle, board.positions[18])
            assertEquals(bishop, board.positions[27])
            assertNull(board.positions[0])
        }

        @Test
        fun obstacle_fuenf() {
            val board = Board()

            val bishop = Bishop(27, true, board)
            val obstacle = Pawn(9, true, board)

            assertFalse(bishop.move(9))
            assertEquals(obstacle, board.positions[9])
            assertEquals(bishop, board.positions[27])
        }
    }

}