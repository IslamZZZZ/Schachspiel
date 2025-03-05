import Board.Board
import figures.Knight
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

class KnightTest {
    @Test
    fun moveFromZentrum() {
        val board = Board()

        for(colour in listOf(true, false)) {

            for (startPos in listOf(42, 36, 27, 37, 18, 21, 20)) {

                for (endPos in listOf(-6, 6, -10, 10, -15, 15, -17, 17)) {
                    val knight = Knight(startPos, colour, board)

                    assertTrue(knight.move(startPos + endPos))
                    assertNull(board.positions[startPos])
                    assertEquals(knight, board.positions[startPos + endPos])

                    board.clean()
                }

            }

        }
    }

    @Test
    fun taking() {
        val board = Board()

        for(colour in listOf(true, false)) {

            for (startPos in listOf(42, 36, 27, 37, 18, 21, 20)) {

                for (endPos in listOf(-6, 6, -10, 10, -15, 15, -17, 17)) {
                    val knight = Knight(startPos, colour, board)
                    val enemyKnight = Knight(startPos + endPos, !colour, board)

                    assertTrue(knight.move(startPos + endPos))
                    assertNull(board.positions[startPos])
                    assertNotEquals(enemyKnight, board.positions[startPos + endPos])
                    assertEquals(knight, board.positions[startPos + endPos])

                    board.clean()
                }

            }

        }
    }

    @Test
    fun obstacle() {
        val board = Board()

        for(colour in listOf(true, false)) {

            for (startPos in listOf(42, 36, 27, 37, 18, 21, 20)) {

                for (endPos in listOf(-6, 6, -10, 10, -15, 15, -17, 17)) {
                    val knight = Knight(startPos, colour, board)
                    val allyKnight = Knight(startPos + endPos, colour, board)

                    assertFalse(knight.move(startPos + endPos))
                    assertEquals(knight, board.positions[startPos])
                    assertEquals(allyKnight, board.positions[startPos + endPos])

                    board.clean()
                }

            }

        }
    }


    @Nested
    inner class Columns {
        @Test
        fun moveFromColumnEins() {
            val board = Board()

            for (colour in listOf(true, false)) {

                for (startPos in listOf(16, 24, 32, 40)) {

                    for (endPos in listOf(-6, 6, -10, 10, -15, 15, -17, 17)) {
                        val knight = Knight(startPos, colour, board)

                        if (endPos in listOf(6, 15, -17, -10)) {
                            assertFalse(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos + endPos])
                            assertEquals(knight, board.positions[startPos])
                        } else {
                            assertTrue(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos])
                            assertEquals(knight, board.positions[startPos + endPos])
                        }

                        board.clean()
                    }

                }

            }
        }

        @Test
        fun moveFromColumnZwei() {
            val board = Board()

            for (colour in listOf(true, false)) {

                for (startPos in listOf(17, 25, 33, 41)) {

                    for (endPos in listOf(-6, 6, -10, 10, -15, 15, -17, 17)) {
                        val knight = Knight(startPos, colour, board)

                        if (endPos in listOf(6, -10)) {
                            assertFalse(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos + endPos])
                            assertEquals(knight, board.positions[startPos])
                        } else {
                            assertTrue(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos])
                            assertEquals(knight, board.positions[startPos + endPos])
                        }

                        board.clean()
                    }

                }

            }
        }

        @Test
        fun moveFromColumnSieben() {
            val board = Board()

            for (colour in listOf(true, false)) {

                for (startPos in listOf(22, 30, 38, 46)) {

                    for (endPos in listOf(-6, 6, -10, 10, -15, 15, -17, 17)) {
                        val knight = Knight(startPos, colour, board)

                        if (endPos in listOf(-6, 10)) {
                            assertFalse(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos + endPos])
                            assertEquals(knight, board.positions[startPos])
                        } else {
                            assertTrue(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos])
                            assertEquals(knight, board.positions[startPos + endPos])
                        }

                        board.clean()
                    }

                }

            }
        }

        @Test
        fun moveFromColumnAcht() {
            val board = Board()

            for (colour in listOf(true, false)) {

                for (startPos in listOf(23, 31, 39, 47)) {

                    for (endPos in listOf(-6, 6, -10, 10, -15, 15, -17, 17)) {
                        val knight = Knight(startPos, colour, board)

                        if (endPos in listOf(-6, -15, 17, 10)) {
                            assertFalse(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos + endPos])
                            assertEquals(knight, board.positions[startPos])
                        } else {
                            assertTrue(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos])
                            assertEquals(knight, board.positions[startPos + endPos])
                        }

                        board.clean()
                    }

                }

            }
        }
    }


    @Nested
    inner class Rows {
        @Test
        fun moveFromRowEins() {
            val board = Board()

            for (colour in listOf(true, false)) {

                for (startPos in listOf(2, 3, 4, 5)) {

                    for (endPos in listOf(-6, 6, -10, 10, -15, 15, -17, 17)) {
                        val knight = Knight(startPos, colour, board)

                        if (endPos < 0) {
                            assertFalse(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos + endPos])
                            assertEquals(knight, board.positions[startPos])
                        } else {
                            assertTrue(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos])
                            assertEquals(knight, board.positions[startPos + endPos])
                        }

                        board.clean()
                    }

                }

            }
        }

        @Test
        fun moveFromRowZwei() {
            val board = Board()

            for (colour in listOf(true, false)) {

                for (startPos in listOf(10, 11, 12, 13)) {

                    for (endPos in listOf(-6, 6, -10, 10, -15, 15, -17, 17)) {
                        val knight = Knight(startPos, colour, board)

                        if (endPos in listOf(-15, -17)) {
                            assertFalse(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos + endPos])
                            assertEquals(knight, board.positions[startPos])
                        } else {
                            assertTrue(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos])
                            assertEquals(knight, board.positions[startPos + endPos])
                        }

                        board.clean()
                    }

                }

            }
        }

        @Test
        fun moveFromRowSieben() {
            val board = Board()

            for (colour in listOf(true, false)) {

                for (startPos in listOf(50, 51, 52, 53)) {

                    for (endPos in listOf(-6, 6, -10, 10, -15, 15, -17, 17)) {
                        val knight = Knight(startPos, colour, board)

                        if (endPos in listOf(15, 17)) {
                            assertFalse(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos + endPos])
                            assertEquals(knight, board.positions[startPos])
                        } else {
                            assertTrue(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos])
                            assertEquals(knight, board.positions[startPos + endPos])
                        }

                        board.clean()
                    }

                }

            }
        }

        @Test
        fun moveFromRowAcht() {
            val board = Board()

            for (colour in listOf(true, false)) {

                for (startPos in listOf(58, 59, 60, 61)) {

                    for (endPos in listOf(-6, 6, -10, 10, -15, 15, -17, 17)) {
                        val knight = Knight(startPos, colour, board)

                        if (endPos > 0) {
                            assertFalse(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos + endPos])
                            assertEquals(knight, board.positions[startPos])
                        } else {
                            assertTrue(knight.move(startPos + endPos))

                            assertNull(board.positions[startPos])
                            assertEquals(knight, board.positions[startPos + endPos])
                        }

                        board.clean()
                    }

                }

            }
        }
    }
}