import Board.Board
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import figures.*


data class Language(val code: String, val name: String)

enum class Screen {
    START,
    GAME
}


val availableLanguages = listOf(
    Language("ru", "Русский"),
    Language("en", "English"),
    Language("de", "Deutsch")
)



// Список доступных языков
fun main() = application {
    var currentScreen by remember { mutableStateOf(Screen.START) }
    val board = remember { Board() }
    board.setup()

    Window(onCloseRequest = ::exitApplication, title = "Das Schachspiel") {
        MaterialTheme {
                when(currentScreen) {
                    Screen.START -> StartScreen( { currentScreen = Screen.GAME } )
                    Screen.GAME -> GameScreen( { currentScreen = Screen.START }, board )
                }
        }
    }
}

@Composable
fun standartButton(onButtonClick: () -> Unit, text: String, colour: Color) {
    Button(
        modifier = Modifier.height(80.dp),
        onClick = onButtonClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = colour)
    ) {
        Text(
            text,
            color = Color.White,
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            letterSpacing = 0.sp
        )
    }
}


@Composable
fun dropLangMenu() {
    // Текущий выбранный язык
    var selectedLanguage by remember { mutableStateOf(availableLanguages[0]) }

    // Состояние для выпадающего меню
    var dropdownExpanded by remember { mutableStateOf(false) }

    Button(
        onClick = { dropdownExpanded = true },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5D4E96)),
        modifier = Modifier.size(width = 180.dp, height = 40.dp)
    ) {
        Text("Язык: ${selectedLanguage.code.uppercase()}", color = Color.White)
    }

    // Выпадающее меню с языками
    DropdownMenu(
        expanded = dropdownExpanded,
        onDismissRequest = { dropdownExpanded = false },
        modifier = Modifier.width(180.dp).background(Color.White)
    ) {
        availableLanguages.forEach { language ->
            DropdownMenuItem(
                onClick = {
                    selectedLanguage = language
                    dropdownExpanded = false
                    // Здесь можно добавить код для смены языка в приложении
                    println("Выбран язык: ${language.name} (${language.code})")
                }
            ) {
                Text(
                    "${language.code.uppercase()} - ${language.name}",
                    fontWeight = if (language == selectedLanguage) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun StartScreen(onGameClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFffdfdf))) {

        Box(
            modifier = Modifier
                .background(Color(0xFF3d3d3d))
                .height(80.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    "ШАХМАТЫ",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Box(modifier = Modifier.align(Alignment.CenterEnd).padding(vertical = 20.dp)) { dropLangMenu() }
        }

        Box(modifier = Modifier.align(Alignment.CenterStart)) { bokovayaPanel(false) }

        Box(modifier = Modifier.align(Alignment.CenterEnd)) { bokovayaPanel(true) }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            standartButton(onGameClick, "Начать игру", Color.Green)

            Spacer(modifier = Modifier.padding(20.dp))

            standartButton({}, "Загрузить игру", Color.Blue)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomStart)
                .background(Color(0xFF3d3d3d))
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 15.dp)
            ) {
                Text("Выход", fontSize = 18.sp)
            }

            Text(
                "Версия: Бета 1.0",
                modifier = Modifier.align(Alignment.Center),
                color = Color(0xFFc7c0c0).copy(alpha = 0.5f),
                fontSize = 20.sp
            )
        }


    }
}

@Composable
fun bokovayaPanel(colour: Boolean) {
    val figure = if(colour) "white" else "black"
        Image(
            painter = painterResource("${figure}-pieces-panel.svg"),
            contentDescription = "${figure} figure",
            modifier = Modifier
                .padding(40.dp)
                .fillMaxHeight(0.7f)
                .fillMaxWidth(0.25f),
            contentScale = ContentScale.FillBounds
        )
}

@Composable
fun GameScreen(onStartClick: () -> Unit, board: Board) {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFffdfdf))) {


        if(board.gameWinner != 0) {
            Dialog(
                onDismissRequest = {},
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                )
            ) {
                Card() {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if(board.gameWinner == 1) Text("YOU WON!!!",
                            fontSize = 23.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                            )
                        else if(board.gameWinner == -1) Text("YOU LOST(((",
                            fontSize = 23.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )

                        Button(
                            onClick = { board.reset() },
                            modifier = Modifier.width(250.dp).height(60.dp).padding(10.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                        ) {
                            Text("Обновить игру", fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }


        if(board.isPromoted) {
            Dialog(
                onDismissRequest = {},
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                )
            ) {
                Card(

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val colour = if(!board.turn) "white" else "black"

                        Button(
                            onClick = {board.promotingPawn("Queen")},
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(vertical = 20.dp, horizontal = 10.dp)
                                .border(width = 3.dp, color = Color.Black)
                        ) {
                            ChessImage("${colour}Queen")
                        }

                        Button(
                            onClick = {board.promotingPawn("Rook")},
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(vertical = 20.dp, horizontal = 10.dp)
                                .border(width = 3.dp, color = Color.Black)
                        ) {
                            ChessImage("${colour}Rook")
                        }

                        Button(
                            onClick = {board.promotingPawn("Knight")},
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(vertical = 20.dp, horizontal = 10.dp)
                                .border(width = 3.dp, color = Color.Black)
                        ) {
                            ChessImage("${colour}Knight")
                        }

                        Button(
                            onClick = {board.promotingPawn("Bishop")},
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(vertical = 20.dp, horizontal = 10.dp)
                                .border(width = 3.dp, color = Color.Black)
                        ) {
                            ChessImage("${colour}Bishop")
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .background(Color(0xFF3d3d3d))
                .height(80.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.align(Alignment.CenterStart)) {
                Text(
                    "Шахматная Партия",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
            Box(modifier = Modifier.align(Alignment.CenterEnd).padding(vertical = 20.dp)) { dropLangMenu() }

        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxSize(0.45f)
                .aspectRatio(1f)
                .padding(start = 120.dp, top = 50.dp)
                .border(width = 1.dp, color = Color.Blue)
        ) {
            var selectedPosition by remember { mutableStateOf<Int?>(null) }

            for(rows in 1..8) {

                Row(
                    modifier = Modifier
                        .weight(0.125f)
                        .fillMaxWidth()
                ) {
                    for(sq in 0..7) {
                        val square = 64 - rows * 8 + sq
                        val colour = if( ( (square / 8) + (square % 8) ) % 2 == 1) Color.Red else Color.Blue
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(if(selectedPosition == square) Color.Green
                                else if(board.isThereFigure(square) && (board.positions[square]?.colour == board.turn)
                                    && board.positions[square] is King && board.composeChecked) Color(0xFFf5fba7)
                                else if(board.isThereFigure(square) && (board.positions[square]?.colour == board.turn)
                                    && board.positions[square] is King && board.gameWinner != 0) Color.White
                                else colour)
                                .weight(0.125f)
                                .fillMaxSize()
                                .clickable(onClick = {
                                    if(selectedPosition == null && board.isThereFigure(square)) {
                                        selectedPosition = square
                                    }
                                    else if(selectedPosition != null) {
                                        selectedPosition?.let{
                                            board.move(it, square)
                                        }
                                        selectedPosition = null
                                    }
                                })
                        ) {
                            if(square in board.composePositions.value.keys) {
                                board.composePositions.value[square]?.let {
                                    ChessImage(it.figure)
                                }

                            }
                            Text(board.fromDigToNot(square))
                        }
                    }
                }
            }

        }


        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .align(Alignment.CenterEnd)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .align(Alignment.CenterStart)
            ) {
                Column(
                    modifier = Modifier
                        .border(width = 3.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                        .fillMaxWidth().padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Player 1", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text("Rating: 1500")
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column(
                        modifier = Modifier
                            .size(width = 120.dp, height = 40.dp)
                            .background(Color.Yellow)
                    ) {

                    }
                }



                Spacer(modifier = Modifier.padding(10.dp))


                Column(
                    modifier = Modifier
                        .border(width = 3.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                        .fillMaxWidth().padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Player 2", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text("Rating: 1500")
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column(
                        modifier = Modifier
                            .size(width = 120.dp, height = 40.dp)
                            .background(Color.Yellow)
                    ) {

                    }
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Button(
                    onClick = { board.reset() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                ) {
                    Text("Обновить игру", fontSize = 23.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Button(
                    onClick = { board.gameWinner = if(board.turn) -1 else 1 },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
                ) {
                    Text("Сдаться", fontSize = 23.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Button(
                    onClick = {  },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                ) {
                    Text("Предложить ничью", fontSize = 23.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Button(
                    onClick = {  },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                ) {
                    Text("Сохранить игру", fontSize = 23.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Text("Turn: ${board.turn}", fontSize = 20.sp, modifier = Modifier
                    .border(width = 3.dp, color = Color.Black)
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.Cyan)
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.padding(20.dp))

                Button (
                    onClick = onStartClick,
                    modifier = Modifier.fillMaxWidth().height(60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                ) {
                    Text("Назад", fontSize = 23.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxWidth(0.45f)
            ) {
                Card(
                    elevation = 4.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Column {
                        Row(modifier = Modifier.height(40.dp).background(Color(0xFFd5d5d5)),
                            verticalAlignment = Alignment.CenterVertically) {
                            Text("Запись ходов",
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }

                        val listState = rememberLazyListState()
                        LaunchedEffect(board.whiteMoves.size) {
                            if(board.whiteMoves.size > 0) {
                                listState.animateScrollToItem(board.whiteMoves.size - 1)
                            }
                        }

                        LazyColumn(
                            modifier = Modifier
                                .heightIn(max = 600.dp)
                                .fillMaxWidth(),
                            state = listState
                        ) {
                            items(count = board.whiteMoves.size) { item ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(45.dp)
                                        .border(0.15.dp, color = Color.Black)
                                        .background(Color(0xFFfff6f6))
                                ) {
                                    Text(
                                        "${item + 1}.",
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .weight(0.2f)
                                            .border(0.15.dp, color = Color.Black)
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                                            .clickable {},
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )

                                    Text(
                                        board.whiteMoves[item],
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .weight(0.4f)
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                            .wrapContentWidth(align = Alignment.CenterHorizontally),
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )

                                    Text(
                                        if(board.blackMoves.size > item) board.blackMoves[item] else "",
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .weight(0.4f)
                                            .wrapContentHeight(align = Alignment.CenterVertically)
                                            .wrapContentWidth(align = Alignment.CenterHorizontally),
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }


                }
            }
        }
            }
}


@Composable
fun ChessImage(figure: String) {
    when(figure) {
        "whitePawn" -> Image(painter = painterResource("white-pawn.svg"), contentDescription = "White Pawn")
        "blackPawn" -> Image(painter = painterResource("black-pawn.svg"), contentDescription = "Black Pawn")
        "whiteRook" -> Image(painter = painterResource("white-rook.svg"), contentDescription = "White Rook")
        "blackRook" -> Image(painter = painterResource("black-rook.svg"), contentDescription = "Black Rook")
        "whiteKnight" -> Image(painter = painterResource("white-knight.svg"), contentDescription = "White Knight")
        "blackKnight" -> Image(painter = painterResource("black-knight.svg"), contentDescription = "Black Knight")
        "whiteBishop" -> Image(painter = painterResource("white-bishop.svg"), contentDescription = "White Bishop")
        "blackBishop" -> Image(painter = painterResource("black-bishop.svg"), contentDescription = "Black Bishop")
        "whiteKing" -> Image(painter = painterResource("white-king.svg"), contentDescription = "White King")
        "blackKing" -> Image(painter = painterResource("black-king.svg"), contentDescription = "Black King")
        "whiteQueen" -> Image(painter = painterResource("white-queen.svg"), contentDescription = "White Queen")
        "blackQueen" -> Image(painter = painterResource("black-queen.svg"), contentDescription = "Black Queen")
        else -> Image(painter = painterResource("white-pawn.svg"), contentDescription = "White Pawn")
    }
}

