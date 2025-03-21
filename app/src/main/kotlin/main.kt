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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


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

    Window(onCloseRequest = ::exitApplication, title = "Das Schachspiel") {
        MaterialTheme {
                when(currentScreen) {
                    Screen.START -> StartScreen( { currentScreen = Screen.GAME } )
                    Screen.GAME -> GameScreen( { currentScreen = Screen.START } )
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
fun GameScreen(onStartClick: () -> Unit) {

    Box(
        modifier = Modifier.height(80.dp).background(Color.Cyan).fillMaxWidth()
    ) {
        Box(modifier = Modifier.align(Alignment.TopStart)) {
            standartButton(onStartClick, "Züruck", Color.Red)
        }

       Box(modifier = Modifier.align(Alignment.TopEnd)) {
            dropLangMenu()
       }
    }

}

/*data class Language(val code: String, val name: String)

val availableLanguages = listOf(
    Language("ru", "Русский"),
    Language("en", "English"),
    Language("de", "Deutsch"),
    Language("fr", "Français"),
    Language("es", "Español")
)

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Выбор языка") {
        MaterialTheme {
            StartScreenWithLanguageSelector()
        }
    }
}

@Composable
@Preview
fun StartScreenWithLanguageSelector() {
    // Текущий выбранный язык
    var selectedLanguage by remember { mutableStateOf(availableLanguages[0]) }

    // Состояние для выпадающего меню
    var dropdownExpanded by remember { mutableStateOf(false) }

    // Состояние для диалогового окна
    var dialogVisible by remember { mutableStateOf(false) }

    // Основной экран
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))) {

        // Верхняя панель
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFF3C3261))
        ) {
            Text(
                "ШАХМАТЫ",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )

            // 1. ВАРИАНТ С ВЫПАДАЮЩИМ МЕНЮ
            // Кнопка выбора языка, которая показывает/скрывает выпадающее меню
            /*Box(modifier = Modifier.align(Alignment.CenterEnd).padding(end = 16.dp)) {
                Button(
                    onClick = { dropdownExpanded = true },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5D4E96)),
                    modifier = Modifier.size(width = 120.dp, height = 40.dp)
                ) {
                    Text("Язык: ${selectedLanguage.code.uppercase()}", color = Color.White)
                }

                // Выпадающее меню с языками
                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false },
                    modifier = Modifier.width(120.dp).background(Color.White)
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
            }*/

            // 2. ВАРИАНТ С ДИАЛОГОВЫМ ОКНОМ
            // Кнопка для показа диалога выбора языка (закомментирована, чтобы не конфликтовать с предыдущим вариантом)

            Button(
                onClick = { dialogVisible = true },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF5D4E96)),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
                    .size(width = 120.dp, height = 40.dp)
            ) {
                Text("Язык: ${selectedLanguage.code.uppercase()}", color = Color.White)
            }

        }

        // Центральная часть - основные элементы меню
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Выберите действие",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Button(
                onClick = { /* Действие при нажатии на кнопку "Новая игра" */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)),
                modifier = Modifier.size(width = 240.dp, height = 60.dp).padding(vertical = 8.dp)
            ) {
                Text(
                    "НОВАЯ ИГРА",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = { /* Действие при нажатии на кнопку "Загрузить игру" */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3)),
                modifier = Modifier.size(width = 240.dp, height = 60.dp).padding(vertical = 8.dp)
            ) {
                Text(
                    "ЗАГРУЗИТЬ ИГРУ",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Нижняя панель
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(0xFF3C3261))
        ) {
            // Отображение текущего языка
            Text(
                "Текущий язык: ${selectedLanguage.name}",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Диалоговое окно выбора языка
        if (dialogVisible) {
            AlertDialog(
                onDismissRequest = { dialogVisible = false },
                title = { Text("Выберите язык") },
                text = {
                    Column {
                        availableLanguages.forEach { language ->
                            Button(
                                onClick = {
                                    selectedLanguage = language
                                    dialogVisible = false
                                    // Здесь можно добавить код для смены языка в приложении
                                    println("Выбран язык: ${language.name} (${language.code})")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = if (language == selectedLanguage)
                                        Color(0xFF5D4E96) else Color(0xFFE0E0E0)
                                )
                            ) {
                                Text(
                                    "${language.code.uppercase()} - ${language.name}",
                                    color = if (language == selectedLanguage) Color.White else Color.Black
                                )
                            }
                        }
                    }
                },
                confirmButton = {},
                dismissButton = {
                    Button(
                        onClick = { dialogVisible = false },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                    ) {
                        Text("Отмена", color = Color.White)
                    }
                }
            )
        }
    }
}

/*@Composable
@Preview
fun ChessApp() {
    // Состояние для отслеживания текущего экрана
    var currentScreen by remember { mutableStateOf(Screen.START) }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            // В зависимости от текущего экрана показываем нужный композабл
            when (currentScreen) {
                Screen.START -> StartScreen(onStartClick = { currentScreen = Screen.GAME })
                Screen.GAME -> GameScreen(onBackClick = { currentScreen = Screen.START })
            }
        }
    }
}

// Стартовый экран с кнопкой "Начать игру"
@Composable
fun StartScreen(onStartClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Шахматы",
            style = MaterialTheme.typography.h2,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = onStartClick,
            modifier = Modifier.size(width = 200.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,           // Основной цвет
                contentColor = Color.Black,             // Цвет текста
                disabledBackgroundColor = Color.Gray,   // Цвет при отключении
                disabledContentColor = Color.LightGray  // Цвет текста при отключении
            )
        ) {
            Text("Начать игру", fontSize = MaterialTheme.typography.h6.fontSize)
        }
    }
}

// Экран с игрой, шахматной доской и дополнительными элементами интерфейса
@Composable
fun GameScreen(onBackClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Верхняя панель с кнопкой "Назад"
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Шахматная игра")
                }
            },
            navigationIcon = {
                Button(
                    onClick = onBackClick,
                    modifier = Modifier
                        .width(100.dp)     // Явное управление шириной
                        .height(50.dp),    // Явное управление высотой
                    contentPadding = PaddingValues(0.dp)  // Убираем внутренние отступы
                ) {
                    Text(
                        text = "Назад",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        )

        // Основной контент - разделяем экран на несколько частей
        Row(modifier = Modifier.fillMaxSize()) {
            // Центральная часть - шахматная доска
            ChessBoard(modifier = Modifier.weight(0.7f))

            // Левая панель - информация об игре, ходах и т.д.
            GameInfoPanel(modifier = Modifier.weight(0.3f))
        }
    }
}

// Панель с информацией об игре
@Composable
fun GameInfoPanel(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp)
            .background(Color.LightGray.copy(alpha = 0.2f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Информация об игре",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Пример элементов панели информации
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Текущий ход: Белые", fontWeight = FontWeight.Bold)
                Text("Время: 00:05:32")
            }
        }

        // Список ходов
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "История ходов",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth() // Занимает всю ширину
                        .padding(bottom = 16.dp)
                        .wrapContentWidth(      // Центрирует контент
                            align = Alignment.CenterHorizontally
                        )
                )
                Text("1. e2-e4 e7-e5")
                Text("2. Ng1-f3 Nb8-c6")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Кнопки управления игрой
        Button(
            onClick = { /* Сохранить игру */ },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Text("Сохранить игру")
        }

        Button(
            onClick = { /* Сдаться */ },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red.copy(alpha = 0.7f))
        ) {
            Text("Сдаться", color = Color.White)
        }
    }
}

// Шахматная доска (в ней мы используем элементы из прошлого примера)
@Composable
fun ChessBoard(modifier: Modifier = Modifier) {
    var selectedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Доска
        Column(
            modifier = Modifier
                .aspectRatio(1f) // Поддерживаем квадратную форму
                .border(4.dp, Color.Blue)
        ) {
            for (row in 0 until 8) {
                Row(modifier = Modifier.weight(1f)) {
                    for (col in 0 until 8) {
                        val isSelected = selectedCell == Pair(row, col)
                        val cellColor = if ((row + col) % 2 == 0) Color(0xFFFF0000) else Color(0xFF000000)

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(color = if (isSelected) Color.Yellow else cellColor)
                                .clickable {
                                    selectedCell = Pair(row, col)
                                    println("Выбрана клетка: $row, $col")
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            // Отображение координат клетки (временно)
                            Text(
                                "${('a' + col)}${8 - row}",
                                color = if ((row + col) % 2 == 0) Color.DarkGray else Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}*/