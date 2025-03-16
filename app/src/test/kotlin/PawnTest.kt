import Board.Board
import figures.Pawn
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PawnTest {

    @Nested
    inner class Blacks {
        @Test
        fun backwardOneStep_FromTheStart() {
            val board = Board()

            val pawn_ein = Pawn(48, false, board)
            val pawn_zwei = Pawn(52, false, board)
            val pawn_drei = Pawn(55, false, board)

            assertTrue(pawn_ein.canMove(40))
            assertTrue(pawn_zwei.canMove(44))
            assertTrue(pawn_drei.canMove(47))

//            assertNull(board.positions[48])
//            assertNull(board.positions[52])
//            assertNull(board.positions[55])
//
//            assertEquals(pawn_ein, board.positions[40])
//            assertEquals(pawn_zwei, board.positions[44])
//            assertEquals(pawn_drei, board.positions[47])
        }

        @Test
        fun backwardTwoStep_FromTheStart() {
            val board = Board()

            val pawn_ein = Pawn(48, false, board)
            val pawn_zwei = Pawn(52, false, board)
            val pawn_drei = Pawn(55, false, board)

            assertTrue(pawn_ein.canMove(32))
            assertTrue(pawn_zwei.canMove(36))
            assertTrue(pawn_drei.canMove(39))

//            assertNull(board.positions[48])
//            assertNull(board.positions[52])
//            assertNull(board.positions[55])
//
//            assertEquals(pawn_ein, board.positions[32])
//            assertEquals(pawn_zwei, board.positions[36])
//            assertEquals(pawn_drei, board.positions[39])
        }

        @Test
        fun backwardOneStep_FromSomewhere() {
            val board = Board()

            val pawn_ein = Pawn(34, false, board)
            val pawn_zwei = Pawn(28, false, board)
            val pawn_drei = Pawn(22, false, board)

            assertTrue(pawn_ein.canMove(26))
            assertTrue(pawn_zwei.canMove(20))
            assertTrue(pawn_drei.canMove(14))

//            assertNull(board.positions[34])
//            assertNull(board.positions[28])
//            assertNull(board.positions[22])
//
//            assertEquals(pawn_ein, board.positions[26])
//            assertEquals(pawn_zwei, board.positions[20])
//            assertEquals(pawn_drei, board.positions[14])
        }

        @Test
        fun backwardTwoStep_FromSomewhere() {
            val board = Board()

            val pawn_ein = Pawn(34, false, board)
            val pawn_zwei = Pawn(28, false, board)
            val pawn_drei = Pawn(40, false, board)

            assertFalse(pawn_ein.canMove(18))
            assertFalse(pawn_zwei.canMove(12))
            assertFalse(pawn_drei.canMove(24))

//            assertNull(board.positions[26])
//            assertNull(board.positions[12])
//            assertNull(board.positions[24])
//
//            assertEquals(pawn_ein, board.positions[34])
//            assertEquals(pawn_zwei, board.positions[28])
//            assertEquals(pawn_drei, board.positions[40])
        }
    }


    @Nested
    inner class Whites {
        @Test
        fun forwardOneStep_FromTheStart() {
            val board = Board()

            val pawn_ein = Pawn(10, true, board)
            val pawn_zwei = Pawn(8, true, board)
            val pawn_drei = Pawn(12, true, board)

            assertTrue(pawn_ein.canMove(18))
            assertTrue(pawn_zwei.canMove(16))
            assertTrue(pawn_drei.canMove(20))

//            assertNull(board.positions[10])
//            assertNull(board.positions[8])
//            assertNull(board.positions[12])
//
//            assertEquals(pawn_ein, board.positions[18])
//            assertEquals(pawn_zwei, board.positions[16])
//            assertEquals(pawn_drei, board.positions[20])
        }

        @Test
        fun forwardTwoStep_FromTheStart() {
            val board = Board()

            val pawn_ein = Pawn(10, true, board)
            val pawn_zwei = Pawn(8, true, board)
            val pawn_drei = Pawn(12, true, board)

            assertTrue(pawn_ein.canMove(26))
            assertTrue(pawn_zwei.canMove(24))
            assertTrue(pawn_drei.canMove(28))

//            assertNull(board.positions[10])
//            assertNull(board.positions[8])
//            assertNull(board.positions[12])
//
//            assertEquals(pawn_ein, board.positions[26])
//            assertEquals(pawn_zwei, board.positions[24])
//            assertEquals(pawn_drei, board.positions[28])
        }

        @Test
        fun forwardOneStep_FromSomewhere() {
            val board = Board()

            val pawn_ein = Pawn(34, true, board)
            val pawn_zwei = Pawn(28, true, board)
            val pawn_drei = Pawn(22, true, board)

            assertTrue(pawn_ein.canMove(42))
            assertTrue(pawn_zwei.canMove(36))
            assertTrue(pawn_drei.canMove(30))

//            assertNull(board.positions[34])
//            assertNull(board.positions[28])
//            assertNull(board.positions[22])
//
//            assertEquals(pawn_ein, board.positions[42])
//            assertEquals(pawn_zwei, board.positions[36])
//            assertEquals(pawn_drei, board.positions[30])
        }

        @Test
        fun forwardTwoStep_FromSomewhere() {
            val board = Board()

            val pawn_ein = Pawn(16, true, board)
            val pawn_zwei = Pawn(30, true, board)
            val pawn_drei = Pawn(34, true, board)

            assertFalse(pawn_ein.canMove(16))
            assertFalse(pawn_zwei.canMove(30))
            assertFalse(pawn_drei.canMove(34))

//            assertNull(board.positions[32])
//            assertNull(board.positions[46])
//            assertNull(board.positions[50])
//
//            assertEquals(pawn_ein, board.positions[16])
//            assertEquals(pawn_zwei, board.positions[30])
//            assertEquals(pawn_drei, board.positions[34])
        }
    }

    @Nested
    inner class BlackTaking {

        @Test
        fun take_left_black() {
            val board = Board()

            val pawn_ein = Pawn(52, false, board)
            val pawn_zwei = Pawn(45, true, board)
//            val pawn_drei = Pawn(38, true, board)

            assertEquals(true, pawn_ein.canMove(45))
//            assertNull(board.positions[52])

//            assertEquals(true, pawn_ein.canMove(38))
//            assertNull(board.positions[45])

//            assertEquals(pawn_ein, board.positions[38])
//            assertEquals(false, board.positions[38]?.colour)
        }


        @Test
        fun take_right_black() {
            val board = Board()

            val pawn_ein = Pawn(52, false, board)
            val pawn_zwei = Pawn(43, true, board)
            val pawn_drei = Pawn(34, true, board)

            assertEquals(true, pawn_ein.canMove(43))
//            assertNull(board.positions[52])
//
//            assertEquals(true, pawn_ein.canMove(34))
//            assertNull(board.positions[43])
//
//            assertEquals(pawn_ein, board.positions[34])
//            assertEquals(false, board.positions[34]?.colour)
        }


        @Test
        fun unavaliable_take_left_black() {
            val board = Board()

            val pawn_ein = Pawn(47, false, board)
            val pawn_zwei = Pawn(40, true, board)

            assertEquals(false, pawn_ein.canMove(40))

//            assertEquals(pawn_ein, board.positions[47])
//            assertEquals(pawn_zwei, board.positions[40])
        }


        @Test
        fun unavaliable_take_right_black() {
            val board = Board()

            val pawn_ein = Pawn(24, false, board)
            val pawn_zwei = Pawn(15, true, board)

            assertEquals(false, pawn_ein.canMove(15))

//            assertEquals(pawn_ein, board.positions[24])
//            assertEquals(pawn_zwei, board.positions[15])
        }
    }

    @Nested
    inner class WhiteTaking {
        fun take_left_white() {
            val board = Board()

            val pawn_ein = Pawn(30, true, board)
            val pawn_zwei = Pawn(37, false, board)
            val pawn_drei = Pawn(44, false, board)

            assertEquals(true, pawn_ein.canMove(37))
//            assertNull(board.positions[30])
//
//            assertEquals(true, pawn_ein.canMove(44))
//            assertNull(board.positions[37])
//
//            assertEquals(pawn_ein, board.positions[44])
//            assertEquals(true, board.positions[44]?.colour)
        }

        @Test
        fun take_right_white() {
            val board = Board()

            val pawn_ein = Pawn(28, true, board)
            val pawn_zwei = Pawn(37, false, board)
            val pawn_drei = Pawn(46, false, board)

            assertEquals(true, pawn_ein.canMove(37))
//            assertNull(board.positions[28])
//
//            assertEquals(true, pawn_ein.canMove(46))
//            assertNull(board.positions[37])
//
//            assertEquals(pawn_ein, board.positions[46])
//            assertEquals(true, board.positions[46]?.colour)
        }

        @Test
        fun unavaliable_take_left_white() {
            val board = Board()

            val pawn_ein = Pawn(32, true, board)
            val pawn_zwei = Pawn(39, false, board)

            assertEquals(false, pawn_ein.canMove(39))

//            assertEquals(pawn_ein, board.positions[32])
//            assertEquals(pawn_zwei, board.positions[39])
        }

        @Test
        fun unavaliable_take_right_white() {
            val board = Board()

            val pawn_ein = Pawn(31, true, board)
            val pawn_zwei = Pawn(40, false, board)

            assertEquals(false, pawn_ein.canMove(40))

//            assertEquals(pawn_ein, board.positions[31])
//            assertEquals(pawn_zwei, board.positions[40])
        }
    }

    @Nested
    inner class Obstacles {

        @Test
        fun forwardTwoStep_Ein() {
            val board = Board()

            val pawn_ein = Pawn(10, true, board)
            val pawn_zwei = Pawn(18, false, board)

            assertEquals(false, pawn_ein.canMove(26))

//            assertNull(board.positions[26])
//
//            assertEquals(pawn_ein, board.positions[10])
//            assertEquals(pawn_zwei, board.positions[18])
        }

        @Test
        fun forwardTwoStep_Zwei() {
            val board = Board()

            val pawn_ein = Pawn(10, true, board)
            val pawn_zwei = Pawn(18, true, board)

            assertEquals(false, pawn_ein.canMove(26))

//            assertNull(board.positions[26])
//
//            assertEquals(pawn_ein, board.positions[10])
//            assertEquals(pawn_zwei, board.positions[18])
        }

        @Test
        fun forwardTwoStep_Drei() {
            val board = Board()

            val pawn_ein = Pawn(10, true, board)
            val pawn_zwei = Pawn(26, false, board)

            assertEquals(false, pawn_ein.canMove(26))
//
//            assertEquals(pawn_ein, board.positions[10])
//            assertEquals(pawn_zwei, board.positions[26])
        }

        @Test
        fun forwardTwoStep_Vier() {
            val board = Board()

            val pawn_ein = Pawn(10, true, board)
            val pawn_zwei = Pawn(26, true, board)

            assertEquals(false, pawn_ein.canMove(26))

//            assertEquals(pawn_ein, board.positions[10])
//            assertEquals(pawn_zwei, board.positions[26])
        }

        @Test
        fun forwardOneStep_Ein() {
            val board = Board()

            val pawn_ein = Pawn(20, true, board)
            val pawn_zwei = Pawn(28, false, board)

            assertEquals(false, pawn_ein.canMove(28))

//            assertEquals(pawn_ein, board.positions[20])
//            assertEquals(pawn_zwei, board.positions[28])
        }

        @Test
        fun forwardOneStep_Zwei() {
            val board = Board()

            val pawn_ein = Pawn(20, true, board)
            val pawn_zwei = Pawn(28, true, board)

            assertEquals(false, pawn_ein.canMove(28))

//            assertEquals(pawn_ein, board.positions[20])
//            assertEquals(pawn_zwei, board.positions[28])
        }
    }
}