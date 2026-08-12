package app.tuti.tj.data.content.russian

import app.tuti.tj.data.content.Dialogue
import app.tuti.tj.data.content.DialogueLine
import app.tuti.tj.data.content.Exercise
import app.tuti.tj.data.content.ExerciseType
import app.tuti.tj.data.content.GrammarTip
import app.tuti.tj.data.content.Lesson
import app.tuti.tj.data.content.Module
import app.tuti.tj.data.content.WordItem

// Generated extended modules 11–20 for personal Russian course (lessons 41–80).

// ═══════════════════════════════════════════════════
// MODULE 11 — Санъат ва эҷодкорӣ
// ═══════════════════════════════════════════════════

internal val personalM11L41 = Lesson(
    id = "personal_m11_l41", moduleId = "personal_m11", title = "Мусиқӣ", description = "Суруд, консерт, асбоб",
    emoji = "\uD83C\uDFB5", orderIndex = 0,
    dialogue = Dialogue("Музыка", listOf(
        DialogueLine("Оля", "Ты умеешь играть на гитаре?", "Ту ба гитара мезанӣ?"),
        DialogueLine("Фирӯз", "Да, немного. А ты любишь слушать рок?", "Ҳа, каме. Ва ту рокро дӯст медорӣ?"),
        DialogueLine("Оля", "Мне нравится классика и концерты в театре.", "Ба ман классика ва консертҳо дар театр маъқуланд."),
        DialogueLine("Фирӯз", "Давай пойдём на концерт в субботу — барабан звучит отлично!", "Биёед шанбе ба консерт — барабан аъло садо медиҳад!"),
    )),
    newWords = listOf(
        WordItem("w_pm11l41_1", "Песня", "Суруд", "Пе-сня", "Красивая песня", "Суруди зебо", "personal_m11_l41"),
        WordItem("w_pm11l41_2", "Гитара", "Гитара", "Ги-та-ра", "Играть на гитаре", "Ба гитара задан", "personal_m11_l41"),
        WordItem("w_pm11l41_3", "Петь", "Хондан", "Петь", "Она любит петь", "Вай хонданро дӯст дорад", "personal_m11_l41"),
        WordItem("w_pm11l41_4", "Слушать", "Гӯш кардан", "Слу-шать", "Слушать музыку", "Мусиқӣ гӯш кардан", "personal_m11_l41"),
        WordItem("w_pm11l41_5", "Концерт", "Концерт", "Кон-церт", "Идти концерт", "Ба консерт рафтан", "personal_m11_l41"),
        WordItem("w_pm11l41_6", "Мелодия", "Оҳанг", "Ме-ло-дия", "Приятная мелодия", "Оҳанги хуш", "personal_m11_l41"),
        WordItem("w_pm11l41_7", "Инструмент", "Асбоб", "Ин-стру-мент", "Музыкальный инструмент", "Асбоби мусиқӣ", "personal_m11_l41"),
        WordItem("w_pm11l41_8", "Барабан", "Барабан", "Ба-ра-бан", "Играть на барабане", "Ба барабан задан", "personal_m11_l41"),
    ),
    grammarTip = GrammarTip("Я умею играть на... / Мне нравится слушать...", "«Я умею играть на + предложный падеж». «Мне нравится слушать + что».", listOf("Я умею играть на гитаре.", "Мне нравится слушать джаз.", "Она поёт красиво.")),
    exercises = listOf(
        Exercise("e_pm11l41_1", ExerciseType.MULTIPLE_CHOICE, "«Концерт» чӣ маъно дорад?", null, listOf("Суруд", "Консерт", "Асбоб", "Оҳанг"), "Консерт", 1, "Концерт — консерт"),
        Exercise("e_pm11l41_2", ExerciseType.MATCH_PAIRS, "Калимаҳоро мувофиқат кунед", null, null, "", null, "Мусиқӣ", pairs = listOf("Песня" to "Суруд", "Гитара" to "Гитара", "Мелодия" to "Оҳанг", "Барабан" to "Барабан")),
        Exercise("e_pm11l41_3", ExerciseType.TYPE_ANSWER, "«Гӯш кардан»-ро ба русӣ навишед:", "Гӯш кардан = ?", null, "Слушать", null, "Слушать"),
        Exercise("e_pm11l41_4", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Мне нравится _____ музыку.", listOf("петь", "играть", "слушать", "рисовать"), "слушать", 2, "Слушать музыку"),
        Exercise("e_pm11l41_5", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Я умею играть на гитаре", null, "умею играть", words = listOf("гитаре", "на", "играть", "умею", "Я")),
        Exercise("e_pm11l41_6", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Я люблю петь", "Мне нравится слушать джаз", "Я боюсь концерт", "Мы идём на барабан"), "Мне нравится слушать джаз", 1, "Нравится слушать"),
        Exercise("e_pm11l41_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ме-ло-дия", listOf("Инструмент", "Мелодия", "Песня", "Концерт"), "Мелодия", 1, "Мелодия — оҳанг"),
        Exercise("e_pm11l41_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nОля: Ты играешь на чём-нибудь?\nФирӯз: _____", null, listOf("Не знаю.", "Да, я умею играть на гитаре.", "Я боюсь театра."), "Да, я умею играть на гитаре.", 1, "Умею играть"),
    ),
)

internal val personalM11L42 = Lesson(
    id = "personal_m11_l42", moduleId = "personal_m11", title = "Кино", description = "Жанрҳо, актёр, чипта",
    emoji = "\uD83C\uDFAC", orderIndex = 1,
    dialogue = Dialogue("В кино", listOf(
        DialogueLine("Фирӯз", "Какой фильм посмотрим сегодня?", "Имрӯз кадом филмро тамошо мекунем?"),
        DialogueLine("Оля", "Давай комедию — мне не нравятся ужасы.", "Биёед комедия — ба ман тарсонак маъқул нест."),
        DialogueLine("Фирӯз", "Хорошо. Я куплю билеты и попкорн.", "Хуб. Ман чипта ва попкорн мехарам."),
        DialogueLine("Оля", "Актёр играет смешно! На большом экране здорово.", "Актёр хандаовар бозӣ мекунад! Дар экрани калон аъло."),
    )),
    newWords = listOf(
        WordItem("w_pm11l42_1", "Фильм", "Филм", "Фильм", "Интересный фильм", "Филми ҷолиб", "personal_m11_l42"),
        WordItem("w_pm11l42_2", "Актёр", "Актёр", "Ак-тёр", "Известный актёр", "Актёри маълум", "personal_m11_l42"),
        WordItem("w_pm11l42_3", "Режиссёр", "Коргардон", "Ре-жис-сёр", "Талантливый режиссёр", "Коргардони лаёқатманд", "personal_m11_l42"),
        WordItem("w_pm11l42_4", "Комедия", "Комедия", "Ко-ме-дия", "Смешная комедия", "Комедияи хандаовар", "personal_m11_l42"),
        WordItem("w_pm11l42_5", "Ужасы", "Тарсонак", "У-жас-ы", "Фильм ужасов", "Филми тарсонак", "personal_m11_l42"),
        WordItem("w_pm11l42_6", "Билет", "Чипта", "Би-лет", "Купить билет", "Чипта харидан", "personal_m11_l42"),
        WordItem("w_pm11l42_7", "Экран", "Экран", "Э-кран", "Большой экран", "Экрани калон", "personal_m11_l42"),
        WordItem("w_pm11l42_8", "Попкорн", "Попкорн", "Поп-корн", "Попкорн в кино", "Попкорн дар синамо", "personal_m11_l42"),
    ),
    grammarTip = GrammarTip("Давай посмотрим... / Какой фильм тебе нравится?", "«Давай посмотрим + винительный». Просьба о мнении: «Какой фильм тебе нравится?».", listOf("Давай посмотрим комедию!", "Какой фильм тебе нравится?", "Я боюсь ужасов.")),
    exercises = listOf(
        Exercise("e_pm11l42_1", ExerciseType.MULTIPLE_CHOICE, "«Режиссёр» чӣ маъно дорад?", null, listOf("Актёр", "Коргардон", "Чипта", "Экран"), "Коргардон", 1, "Режиссёр — коргардон"),
        Exercise("e_pm11l42_2", ExerciseType.MATCH_PAIRS, "Калимаҳоро мувофиқат кунед", null, null, "", null, "Кино", pairs = listOf("Фильм" to "Филм", "Комедия" to "Комедия", "Ужасы" to "Тарсонак", "Билет" to "Чипта")),
        Exercise("e_pm11l42_3", ExerciseType.TYPE_ANSWER, "«Экран»-ро ба русӣ навишед:", "Экран = ?", null, "Экран", null, "Экран"),
        Exercise("e_pm11l42_4", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Давай посмотрим _____!", listOf("актёра", "комедию", "режиссёра", "билет"), "комедию", 1, "Посмотрим комедию"),
        Exercise("e_pm11l42_5", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Какой фильм тебе нравится", null, "Какой фильм", words = listOf("нравится", "тебе", "фильм", "Какой")),
        Exercise("e_pm11l42_6", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Я люблю попкорн", "Давай посмотрим фильм", "Режиссёр на экране", "Ужасы смешные"), "Давай посмотрим фильм", 1, "Давай посмотрим"),
        Exercise("e_pm11l42_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ак-тёр", listOf("Режиссёр", "Актёр", "Комедия", "Попкорн"), "Актёр", 1, "Актёр"),
        Exercise("e_pm11l42_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nФирӯз: Тебе нравятся ужасы?\nОля: _____", null, listOf("Да, очень!", "Нет, мне страшно.", "Я режиссёр."), "Нет, мне страшно.", 1, "Ответ о жанре"),
    ),
)

internal val personalM11L43 = Lesson(
    id = "personal_m11_l43", moduleId = "personal_m11", title = "Расм кашидан", description = "Мӯйқалам, ранг, намоишгоҳ",
    emoji = "\uD83D\uDD8C\uFE0F", orderIndex = 2,
    dialogue = Dialogue("Рисование", listOf(
        DialogueLine("Оля", "Ты рисуешь маслом или акварелью?", "Бо ранги равғанӣ ё обӣ расм мекашӣ?"),
        DialogueLine("Фирӯз", "Люблю акварель. Вот мой холст и кисть.", "Акварелро дӯст медорам. Ин бум ва мӯйқалам."),
        DialogueLine("Оля", "Красиво! Это похоже на горы.", "Зебо! Ин ба кӯҳҳо монанд аст."),
        DialogueLine("Фирӯз", "Спасибо! На выставке будет моя картина.", "Раҳмат! Дар намоишгоҳ расми ман хоҳад буд."),
    )),
    newWords = listOf(
        WordItem("w_pm11l43_1", "Рисовать", "Расм кашидан", "Ри-со-вать", "Люблю рисовать", "Расм кашиданро дӯст дорам", "personal_m11_l43"),
        WordItem("w_pm11l43_2", "Кисть", "Мӯйқалам", "Кисть", "Мягкая кисть", "Мӯйқалами нарм", "personal_m11_l43"),
        WordItem("w_pm11l43_3", "Краска", "Ранг", "Крас-ка", "Яркая краска", "Ранги равшан", "personal_m11_l43"),
        WordItem("w_pm11l43_4", "Картина", "Расм", "Кар-ти-на", "Новая картина", "Расми нав", "personal_m11_l43"),
        WordItem("w_pm11l43_5", "Холст", "Бум", "Холст", "Большой холст", "Буми калон", "personal_m11_l43"),
        WordItem("w_pm11l43_6", "Портрет", "Портрет", "Пор-трет", "Портрет друга", "Портрети дӯст", "personal_m11_l43"),
        WordItem("w_pm11l43_7", "Пейзаж", "Манзара", "Пей-заж", "Горный пейзаж", "Манзараи кӯҳӣ", "personal_m11_l43"),
        WordItem("w_pm11l43_8", "Выставка", "Намоишгоҳ", "Вы-став-ка", "Идти на выставку", "Ба намоишгоҳ рафтан", "personal_m11_l43"),
    ),
    grammarTip = GrammarTip("Я нарисовал... / Это похоже на...", "Прошедшее время + объект. Сравнение: «Это похоже на + винительный».", listOf("Я нарисовал пейзаж.", "Это похоже на море.", "Она рисует акварелью.")),
    exercises = listOf(
        Exercise("e_pm11l43_1", ExerciseType.MULTIPLE_CHOICE, "«Пейзаж» чӣ маъно дорад?", null, listOf("Портрет", "Манзара", "Кисть", "Холст"), "Манзара", 1, "Пейзаж — манзара"),
        Exercise("e_pm11l43_2", ExerciseType.MATCH_PAIRS, "Калимаҳоро мувофиқат кунед", null, null, "", null, "Расм", pairs = listOf("Краска" to "Ранг", "Картина" to "Расм", "Холст" to "Бум", "Выставка" to "Намоишгоҳ")),
        Exercise("e_pm11l43_3", ExerciseType.TYPE_ANSWER, "«Мӯйқалам»-ро ба русӣ навишед:", "Мӯйқалам = ?", null, "Кисть", null, "Кисть"),
        Exercise("e_pm11l43_4", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Это похоже на _____.", listOf("кисть", "горы", "краску", "холст"), "горы", 1, "Похоже на горы"),
        Exercise("e_pm11l43_5", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Я нарисовал портрет", null, "нарисовал портрет", words = listOf("портрет", "нарисовал", "Я")),
        Exercise("e_pm11l43_6", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Я купил холст", "Я нарисовал пейзаж", "Она рисует билет", "Кисть на экране"), "Я нарисовал пейзаж", 1, "Нарисовал пейзаж"),
        Exercise("e_pm11l43_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Кар-ти-на", listOf("Краска", "Картина", "Пейзаж", "Холст"), "Картина", 1, "Картина — расм"),
        Exercise("e_pm11l43_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nОля: Где твоя картина?\nФирӯз: _____", null, listOf("На холсте дома.", "Она в театре.", "Это билет."), "На холсте дома.", 0, "Ответ о картине"),
    ),
)

internal val personalM11L44 = Lesson(
    id = "personal_m11_l44", moduleId = "personal_m11", title = "Аксбардорӣ", description = "Камера, рӯшноӣ, таҳрир",
    emoji = "\uD83D\uDCF7", orderIndex = 3,
    dialogue = Dialogue("Фото", listOf(
        DialogueLine("Фирӯз", "Можешь сфотографировать меня у фонтана?", "Маро назди фаввора акс бигир?"),
        DialogueLine("Оля", "Конечно! Свет сейчас идеальный.", "Албатта! Рӯшноӣ ҳоло идеалӣ аст."),
        DialogueLine("Фирӯз", "Какой красивый вид на заднем плане!", "Чӣ манзараи зебо дар замина!"),
        DialogueLine("Оля", "Потом отредактирую снимки и положу в альбом.", "Баъд расмҳоро таҳрир мекунам ва ба албом мегузорам."),
    )),
    newWords = listOf(
        WordItem("w_pm11l44_1", "Фотография", "Акс", "Фо-то-гра-фия", "Люблю фотографию", "Аксбардориро дӯст дорам", "personal_m11_l44"),
        WordItem("w_pm11l44_2", "Камера", "Камера", "Ка-ме-ра", "Новая камера", "Камераи нав", "personal_m11_l44"),
        WordItem("w_pm11l44_3", "Снимок", "Расм", "Сни-мок", "Хороший снимок", "Расми хуб", "personal_m11_l44"),
        WordItem("w_pm11l44_4", "Свет", "Рӯшноӣ", "Свет", "Мягкий свет", "Рӯшноии нарм", "personal_m11_l44"),
        WordItem("w_pm11l44_5", "Фон", "Замина", "Фон", "Размытый фон", "Заминаи тира", "personal_m11_l44"),
        WordItem("w_pm11l44_6", "Портрет", "Портрет", "Пор-трет", "Портрет на улице", "Портрет дар кӯча", "personal_m11_l44"),
        WordItem("w_pm11l44_7", "Редактировать", "Таҳрир кардан", "Ре-дак-ти-ро-вать", "Редактировать фото", "Аксро таҳрир кардан", "personal_m11_l44"),
        WordItem("w_pm11l44_8", "Альбом", "Албом", "Аль-бом", "Фотоальбом", "Албоми аксҳо", "personal_m11_l44"),
    ),
    grammarTip = GrammarTip("Можешь сфотографировать меня? / Какой красивый вид!", "Вежливая просьба: «Можешь + инфинитив?». Восклицание: «Какой + прилагательный + вид!».", listOf("Можешь сфотографировать меня?", "Какой красивый вид с моста!", "Я редактирую снимки вечером.")),
    exercises = listOf(
        Exercise("e_pm11l44_1", ExerciseType.MULTIPLE_CHOICE, "«Снимок» чӣ маъно дорад?", null, listOf("Камера", "Расм", "Альбом", "Свет"), "Расм", 1, "Снимок — расм"),
        Exercise("e_pm11l44_2", ExerciseType.MATCH_PAIRS, "Калимаҳоро мувофиқат кунед", null, null, "", null, "Фото", pairs = listOf("Камера" to "Камера", "Свет" to "Рӯшноӣ", "Фон" to "Замина", "Альбом" to "Албом")),
        Exercise("e_pm11l44_3", ExerciseType.TYPE_ANSWER, "«Таҳрир кардан»-ро ба русӣ навишед:", "Таҳрир = ?", null, "Редактировать", null, "Редактировать"),
        Exercise("e_pm11l44_4", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Какой красивый _____!", listOf("фон", "вид", "снимок", "альбом"), "вид", 1, "Красивый вид"),
        Exercise("e_pm11l44_5", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Можешь сфотографировать меня", null, "Можешь сфотографировать", words = listOf("меня", "сфотографировать", "Можешь")),
        Exercise("e_pm11l44_6", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Я сломал камеру", "Можешь сфотографировать меня", "Свет в альбоме", "Фон без фото"), "Можешь сфотографировать меня", 1, "Просьба сфотографировать"),
        Exercise("e_pm11l44_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ка-ме-ра", listOf("Снимок", "Камера", "Портрет", "Свет"), "Камера", 1, "Камера"),
        Exercise("e_pm11l44_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nФирӯз: Какой свет для фото?\nОля: _____", null, listOf("Тёмный фон.", "Сейчас идеальный!", "Я боюсь камеры."), "Сейчас идеальный!", 1, "Про свет"),
    ),
)

// ═══════════════════════════════════════════════════
// MODULE 12 — Сафар дар Русия
// ═══════════════════════════════════════════════════

internal val personalM12L45 = Lesson(
    id = "personal_m12_l45", moduleId = "personal_m12", title = "Москва", description = "Пойтахт, Кремл, осорхона",
    emoji = "\uD83C\uDDF7\uD83C\uDDFA", orderIndex = 0,
    dialogue = Dialogue("Москва", listOf(
        DialogueLine("Гид", "Я хочу посетить Кремль и Красную площадь.", "Ман хоҳам Кремл ва Майдони Сурхро бинам."),
        DialogueLine("Фирӯз", "Как добраться до музея от метро?", "Аз метро чӣ тавр ба осорхона рафтан мумкин аст?"),
        DialogueLine("Гид", "По мосту через парк — десять минут пешком.", "Бо пул аз болои боғ — даҳ дақиқа пиёда."),
        DialogueLine("Фирӯз", "Столица России впечатляет!", "Пойтахти Русия таъсир мегузорад!"),
    )),
    newWords = listOf(
        WordItem("w_pm12l45_1", "Столица", "Пойтахт", "Столи-ца", "Москва — столица", "Маскав — пойтахт", "personal_m12_l45"),
        WordItem("w_pm12l45_2", "Кремль", "Кремл", "Кремль", "Соборы Кремля", "Калисоҳои Кремл", "personal_m12_l45"),
        WordItem("w_pm12l45_3", "Метро", "Метро", "Ме-тро", "Ехать на метро", "Ба метро рафтан", "personal_m12_l45"),
        WordItem("w_pm12l45_4", "Площадь", "Майдон", "Пло-щадь", "Красная площадь", "Майдони Сурх", "personal_m12_l45"),
        WordItem("w_pm12l45_5", "Собор", "Калисо", "Со-бор", "Красивый собор", "Калисои зебо", "personal_m12_l45"),
        WordItem("w_pm12l45_6", "Мост", "Пул", "Мост", "Каменный мост", "Пули сангу", "personal_m12_l45"),
        WordItem("w_pm12l45_7", "Парк", "Боғ", "Парк", "Гулять в парке", "Дар боғ сайр кардан", "personal_m12_l45"),
        WordItem("w_pm12l45_8", "Музей", "Осорхона", "Му-зей", "Идти в музей", "Ба осорхона рафтан", "personal_m12_l45"),
    ),
    grammarTip = GrammarTip("Я хочу посетить... / Как добраться до...?", "«Хочу посетить + винительный». Вопрос пути: «Как добраться до + родительный?».", listOf("Я хочу посетить Эрмитаж.", "Как добраться до Кремля?", "Мы гуляем в парке.")),
    exercises = listOf(
        Exercise("e_pm12l45_1", ExerciseType.MULTIPLE_CHOICE, "«Площадь» чӣ маъно дорад?", null, listOf("Боғ", "Майдон", "Пул", "Метро"), "Майдон", 1, "Площадь — майдон"),
        Exercise("e_pm12l45_2", ExerciseType.MATCH_PAIRS, "Калимаҳоро мувофиқат кунед", null, null, "", null, "Москва", pairs = listOf("Столица" to "Пойтахт", "Мост" to "Пул", "Парк" to "Боғ", "Музей" to "Осорхона")),
        Exercise("e_pm12l45_3", ExerciseType.TYPE_ANSWER, "«Калисо»-ро ба русӣ навишед:", "Калисо = ?", null, "Собор", null, "Собор"),
        Exercise("e_pm12l45_4", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Как добраться до _____?", listOf("площадь", "Кремля", "метро", "парк"), "Кремля", 1, "До Кремля"),
        Exercise("e_pm12l45_5", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Я хочу посетить музей", null, "хочу посетить", words = listOf("музей", "посетить", "хочу", "Я")),
        Exercise("e_pm12l45_6", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Где метро", "Я хочу посетить Кремль", "Мост в соборе", "Парк без столицы"), "Я хочу посетить Кремль", 1, "Хочу посетить"),
        Exercise("e_pm12l45_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Му-зей", listOf("Площадь", "Музей", "Метро", "Мост"), "Музей", 1, "Музей"),
        Exercise("e_pm12l45_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nФирӯз: Куда сначала?\nГид: _____", null, listOf("Спим дома.", "Давайте на Красную площадь!", "Метро сломано."), "Давайте на Красную площадь!", 1, "Маршрут"),
    ),
)

internal val personalM12L46 = Lesson(
    id = "personal_m12_l46", moduleId = "personal_m12", title = "Санкт-Петербург", description = "Қаср, канал, Эрмитаж",
    emoji = "\uD83C\uDF06", orderIndex = 1,
    dialogue = Dialogue("Питер", listOf(
        DialogueLine("Оля", "Этот город знаменит дворцами и каналами.", "Ин шаҳр бо қасрҳо ва каналҳо шӯҳрат дорад."),
        DialogueLine("Фирӯз", "Здесь находится Эрмитаж — очень большой музей.", "Ин ҷо Эрмитаж ҳаст — осорхонаи хеле калон."),
        DialogueLine("Оля", "Летом белые ночи — мосты и фонтаны красивые.", "Тобистон шабҳои сафед — пулҳо ва фаввораҳо зебоанд."),
        DialogueLine("Фирӯз", "Вид с набережной на реку потрясающий!", "Манзара аз соҳил ба дарё таъсирбахш аст!"),
    )),
    newWords = listOf(
        WordItem("w_pm12l46_1", "Дворец", "Қаср", "Дво-рец", "Зимний дворец", "Қасри зимистона", "personal_m12_l46"),
        WordItem("w_pm12l46_2", "Канал", "Канал", "Ка-нал", "По каналу", "Аз рӯи канал", "personal_m12_l46"),
        WordItem("w_pm12l46_3", "Набережная", "Соҳил", "На-бе-реж-ная", "Прогулка по набережной", "Сайр дар соҳил", "personal_m12_l46"),
        WordItem("w_pm12l46_4", "Белые ночи", "Шабҳои сафед", "Бе-лые но-чи", "В июне белые ночи", "Дар июн шабҳои сафед", "personal_m12_l46"),
        WordItem("w_pm12l46_5", "Эрмитаж", "Эрмитаж", "Эр-ми-таж", "Идти в Эрмитаж", "Ба Эрмитаж рафтан", "personal_m12_l46"),
        WordItem("w_pm12l46_6", "Мост", "Пул", "Мост", "Разводной мост", "Пули кушодашаванда", "personal_m12_l46"),
        WordItem("w_pm12l46_7", "Фонтан", "Фаввора", "Фон-тан", "Фонтан в парке", "Фаввора дар боғ", "personal_m12_l46"),
        WordItem("w_pm12l46_8", "Река", "Дарё", "Ре-ка", "Широкая река", "Дарёи васеъ", "personal_m12_l46"),
    ),
    grammarTip = GrammarTip("Этот город знаменит... / Здесь находится...", "«Знаменит + творительный». «Здесь находится + именительный» для мест.", listOf("Этот город знаменит мостами.", "Здесь находится Эрмитаж.", "Набережная очень длинная.")),
    exercises = listOf(
        Exercise("e_pm12l46_1", ExerciseType.MULTIPLE_CHOICE, "«Набережная» чӣ маъно дорад?", null, listOf("Канал", "Соҳил", "Река", "Мост"), "Соҳил", 1, "Набережная — соҳил"),
        Exercise("e_pm12l46_2", ExerciseType.MATCH_PAIRS, "Калимаҳоро мувофиқат кунед", null, null, "", null, "Питер", pairs = listOf("Дворец" to "Қаср", "Канал" to "Канал", "Фонтан" to "Фаввора", "Река" to "Дарё")),
        Exercise("e_pm12l46_3", ExerciseType.TYPE_ANSWER, "«Фаввора»-ро ба русӣ навишед:", "Фаввора = ?", null, "Фонтан", null, "Фонтан"),
        Exercise("e_pm12l46_4", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Здесь _____ Эрмитаж.", listOf("идут", "находится", "был", "есть"), "находится", 1, "Здесь находится"),
        Exercise("e_pm12l46_5", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Этот город знаменит каналами", null, "знаменит каналами", words = listOf("каналами", "знаменит", "город", "Этот")),
        Exercise("e_pm12l46_6", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Мост спит", "Белые ночи летом", "Канал без реки", "Эрмитаж — это мост"), "Белые ночи летом", 1, "Белые ночи"),
        Exercise("e_pm12l46_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Эр-ми-таж", listOf("Дворец", "Эрмитаж", "Набережная", "Фонтан"), "Эрмитаж", 1, "Эрмитаж"),
        Exercise("e_pm12l46_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nФирӯз: Что посмотрим первым?\nОля: _____", null, listOf("Только реку.", "Давай Эрмитаж!", "Я боюсь канала."), "Давай Эрмитаж!", 1, "Выбор места"),
    ),
)

internal val personalM12L47 = Lesson(
    id = "personal_m12_l47", moduleId = "personal_m12", title = "Нақлиёт", description = "Поезд, ҳавопаймо, билет",
    emoji = "\uD83D\uDE82", orderIndex = 2,
    dialogue = Dialogue("Транспорт", listOf(
        DialogueLine("Фирӯз", "Когда отправляется поезд до Твери?", "Поезд ба Твер чӣ вақт меравад?"),
        DialogueLine("Кассир", "В десять двадцать — смотрите расписание.", "Дар даҳу бист — ҷадвалро бинед."),
        DialogueLine("Фирӯз", "Мне нужен билет до Санкт-Петербурга, плацкарт.", "Ба ман билет то Санкт-Петербург лозим, плацкарт."),
        DialogueLine("Кассир", "Пересадка в Москве, остановка у вокзала.", "Иваз дар Маскав, истгоҳ назди вокзал."),
    )),
    newWords = listOf(
        WordItem("w_pm12l47_1", "Поезд", "Поезд", "По-езд", "Сесть на поезд", "Ба поезд савор шудан", "personal_m12_l47"),
        WordItem("w_pm12l47_2", "Самолёт", "Ҳавопаймо", "Са-мо-лёт", "Лететь на самолёте", "Ба ҳавопаймо паридан", "personal_m12_l47"),
        WordItem("w_pm12l47_3", "Автобус", "Автобус", "Ав-то-бус", "Ехать на автобусе", "Ба автобус рафтан", "personal_m12_l47"),
        WordItem("w_pm12l47_4", "Билет", "Билет", "Би-лет", "Купить билет", "Билет харидан", "personal_m12_l47"),
        WordItem("w_pm12l47_5", "Вокзал", "Вокзал", "Вок-зал", "Выйти у вокзала", "Аз вокзал баромадан", "personal_m12_l47"),
        WordItem("w_pm12l47_6", "Остановка", "Истгоҳ", "О-ста-нов-ка", "Следующая остановка", "Истгоҳи навбатӣ", "personal_m12_l47"),
        WordItem("w_pm12l47_7", "Расписание", "Ҷадвал", "Рас-пи-са-ние", "Смотреть расписание", "Ҷадвалро дидан", "personal_m12_l47"),
        WordItem("w_pm12l47_8", "Пересадка", "Иваз кардан", "Пе-ре-сад-ка", "Пересадка в Москве", "Иваз дар Маскав", "personal_m12_l47"),
    ),
    grammarTip = GrammarTip("Когда отправляется поезд? / Мне нужен билет до...", "Время отправления. «Нужен билет до + родительный города».", listOf("Когда отправляется поезд?", "Мне нужен билет до Казани.", "Где остановка автобуса?")),
    exercises = listOf(
        Exercise("e_pm12l47_1", ExerciseType.MULTIPLE_CHOICE, "«Расписание» чӣ маъно дорад?", null, listOf("Билет", "Ҷадвал", "Вокзал", "Поезд"), "Ҷадвал", 1, "Расписание — ҷадвал"),
        Exercise("e_pm12l47_2", ExerciseType.MATCH_PAIRS, "Калимаҳоро мувофиқат кунед", null, null, "", null, "Транспорт", pairs = listOf("Поезд" to "Поезд", "Самолёт" to "Ҳавопаймо", "Автобус" to "Автобус", "Остановка" to "Истгоҳ")),
        Exercise("e_pm12l47_3", ExerciseType.TYPE_ANSWER, "«Иваз кардан (нақлиёт)»-ро ба русӣ навишед:", "Иваз = ?", null, "Пересадка", null, "Пересадка"),
        Exercise("e_pm12l47_4", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Мне нужен билет _____ Москвы.", listOf("на", "до", "от", "в"), "до", 1, "Билет до Москвы"),
        Exercise("e_pm12l47_5", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Когда отправляется поезд", null, "Когда отправляется", words = listOf("поезд", "отправляется", "Когда")),
        Exercise("e_pm12l47_6", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Где вокзал", "Когда отправляется поезд", "Я люблю расписание", "Пересадка в билете"), "Когда отправляется поезд", 1, "Когда отправляется"),
        Exercise("e_pm12l47_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Вок-зал", listOf("Остановка", "Вокзал", "Билет", "Поезд"), "Вокзал", 1, "Вокзал"),
        Exercise("e_pm12l47_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nФирӯз: Где купить билет?\nКассир: _____", null, listOf("На поезде.", "У кассы вокзала.", "В расписании."), "У кассы вокзала.", 1, "Где купить"),
    ),
)

internal val personalM12L48 = Lesson(
    id = "personal_m12_l48", moduleId = "personal_m12", title = "Обу ҳаво", description = "Барф, борон, ҳарорат",
    emoji = "\u2601\uFE0F", orderIndex = 3,
    dialogue = Dialogue("Погода в поездке", listOf(
        DialogueLine("Оля", "Сегодня на улице холодно и идёт снег.", "Имрӯз дар кӯча хунук ва барф меборад."),
        DialogueLine("Фирӯз", "Какая погода будет завтра в Казани?", "Фардо дар Қазон ҳаво чӣ гуна мешавад?"),
        DialogueLine("Оля", "Обещают ветер и минус пять градусов.", "Шамол ва минус панҷ дараҷаро ваъда медиҳанд."),
        DialogueLine("Фирӯз", "Жарко было вчера — как солнце!", "Дирӯз гарм буд — чӣ офтоб буд!"),
    )),
    newWords = listOf(
        WordItem("w_pm12l48_1", "Холодно", "Хунук", "Хо-лод-но", "Очень холодно", "Хеле хунук", "personal_m12_l48"),
        WordItem("w_pm12l48_2", "Жарко", "Гарм", "Жар-ко", "Летом жарко", "Тобистон гарм", "personal_m12_l48"),
        WordItem("w_pm12l48_3", "Снег", "Барф", "Снег", "Идёт снег", "Барф меборад", "personal_m12_l48"),
        WordItem("w_pm12l48_4", "Дождь", "Борон", "Дождь", "Сильный дождь", "Борони тунд", "personal_m12_l48"),
        WordItem("w_pm12l48_5", "Ветер", "Шамол", "Ве-тер", "Холодный ветер", "Шамоли сард", "personal_m12_l48"),
        WordItem("w_pm12l48_6", "Солнце", "Офтоб", "Солн-це", "Яркое солнце", "Офтоби равшан", "personal_m12_l48"),
        WordItem("w_pm12l48_7", "Облако", "Абр", "Об-ла-ко", "Серые облака", "Абрҳои хокистарӣ", "personal_m12_l48"),
        WordItem("w_pm12l48_8", "Температура", "Ҳарорат", "Тем-пе-ра-ту-ра", "Низкая температура", "Ҳарорати паст", "personal_m12_l48"),
    ),
    grammarTip = GrammarTip("Сегодня на улице... / Какая погода будет завтра?", "Описание: «На улице + наречие/идёт снег». Будущее: «будет + прилагательное».", listOf("Сегодня на улице холодно.", "Какая погода будет завтра?", "Вчера было жарко.")),
    exercises = listOf(
        Exercise("e_pm12l48_1", ExerciseType.MULTIPLE_CHOICE, "«Температура» чӣ маъно дорад?", null, listOf("Шамол", "Ҳарорат", "Абр", "Офтоб"), "Ҳарорат", 1, "Температура — ҳарорат"),
        Exercise("e_pm12l48_2", ExerciseType.MATCH_PAIRS, "Калимаҳоро мувофиқат кунед", null, null, "", null, "Обу ҳаво", pairs = listOf("Холодно" to "Хунук", "Жарко" to "Гарм", "Снег" to "Барф", "Дождь" to "Борон")),
        Exercise("e_pm12l48_3", ExerciseType.TYPE_ANSWER, "«Шамол»-ро ба русӣ навишед:", "Шамол = ?", null, "Ветер", null, "Ветер"),
        Exercise("e_pm12l48_4", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Сегодня на улице _____ и снег.", listOf("жарко", "холодно", "солнце", "облако"), "холодно", 1, "На улице холодно"),
        Exercise("e_pm12l48_5", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Какая погода будет завтра", null, "погода будет", words = listOf("завтра", "будет", "погода", "Какая")),
        Exercise("e_pm12l48_6", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Идёт жара", "Идёт снег", "Солнце дождь", "Ветер в облаке"), "Идёт снег", 1, "Идёт снег"),
        Exercise("e_pm12l48_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Об-ла-ко", listOf("Солнце", "Облако", "Снег", "Ветер"), "Облако", 1, "Облако — абр"),
        Exercise("e_pm12l48_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nФирӯз: Как на улице?\nОля: _____", null, listOf("Я на поезде.", "Холодно, идёт снег.", "Билет до ветра."), "Холодно, идёт снег.", 1, "О погоде"),
    ),
)

// ═══════════════════════════════════════════════════
// MODULE 13 — Идҳо
// ═══════════════════════════════════════════════════

internal val personalM13L49 = Lesson(
    id = "personal_m13_l49", moduleId = "personal_m13", title = "Соли нав", description = "Арча, тӯҳфа, оташбозӣ",
    emoji = "\uD83C\uDF84", orderIndex = 0,
    dialogue = Dialogue("Новый год", listOf(
        DialogueLine("Оля", "С Новым годом! Загадай желание в полночь!", "Соли нав муборак! Дар нисфишаб орзу кун!"),
        DialogueLine("Фирӯз", "Спасибо! Ёлка красивая, подарки под ней.", "Раҳмат! Арча зебост, зери он тӯҳфаҳо."),
        DialogueLine("Оля", "Дед Мороз принесёт сюрприз детям.", "Бобои барфӣ ба кӯдакон ҳайрат меорад."),
        DialogueLine("Фирӯз", "Фейерверк будет ярким — не забудь свечу!", "Оташбозӣ равшан мешавад — шамъро фаромӯш накун!"),
    )),
    newWords = listOf(
        WordItem("w_pm13l49_1", "Праздник", "Ид", "Праз-дник", "Новогодний праздник", "Иди соли нав", "personal_m13_l49"),
        WordItem("w_pm13l49_2", "Ёлка", "Арча", "Ёл-ка", "Нарядить ёлку", "Арчаро оро додан", "personal_m13_l49"),
        WordItem("w_pm13l49_3", "Подарок", "Тӯҳфа", "По-да-рок", "Дорогой подарок", "Тӯҳфаи қимат", "personal_m13_l49"),
        WordItem("w_pm13l49_4", "Дед Мороз", "Бобои барфӣ", "Дед Мо-роз", "Костюм Деда Мороза", "Либоси Бобои барфӣ", "personal_m13_l49"),
        WordItem("w_pm13l49_5", "Свеча", "Шамъ", "Све-ча", "Зажечь свечу", "Шамъ фурӯзондан", "personal_m13_l49"),
        WordItem("w_pm13l49_6", "Фейерверк", "Оташбозӣ", "Фей-ер-верк", "Красивый фейерверк", "Оташбозии зебо", "personal_m13_l49"),
        WordItem("w_pm13l49_7", "Загадать желание", "Орзу кардан", "За-га-дать же-ла-ние", "Я загадал желание", "Ман орзу кардам", "personal_m13_l49"),
        WordItem("w_pm13l49_8", "Полночь", "Нисфишаб", "Пол-ночь", "В полночь", "Дар нисфишаб", "personal_m13_l49"),
    ),
    grammarTip = GrammarTip("С Новым годом! / Я загадал желание...", "Поздравление с праздником. Прошедшее: «загадал желание» (орзу кардам).", listOf("С Новым годом!", "Я загадал желание о здоровье.", "В полночь запускают фейерверк.")),
    exercises = listOf(
        Exercise("e_pm13l49_1", ExerciseType.MULTIPLE_CHOICE, "«Ёлка» чӣ маъно дорад?", null, listOf("Тӯҳфа", "Арча", "Шамъ", "Ид"), "Арча", 1, "Ёлка — арча"),
        Exercise("e_pm13l49_2", ExerciseType.MATCH_PAIRS, "Калимаҳоро мувофиқат кунед", null, null, "", null, "Соли нав", pairs = listOf("Подарок" to "Тӯҳфа", "Свеча" to "Шамъ", "Полночь" to "Нисфишаб", "Праздник" to "Ид")),
        Exercise("e_pm13l49_3", ExerciseType.TYPE_ANSWER, "«Оташбозӣ»-ро ба русӣ навишед:", "Оташбозӣ = ?", null, "Фейерверк", null, "Фейерверк"),
        Exercise("e_pm13l49_4", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____ Новым годом!", listOf("С", "До", "От", "Про"), "С", 0, "С Новым годом"),
        Exercise("e_pm13l49_5", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Я загадал желание", null, "загадал желание", words = listOf("желание", "загадал", "Я")),
        Exercise("e_pm13l49_6", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Дед Мороз на ёлке", "С Новым годом!", "Полночь без свечи", "Подарок — это ветер"), "С Новым годом!", 1, "Поздравление"),
        Exercise("e_pm13l49_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A По-да-рок", listOf("Ёлка", "Подарок", "Свеча", "Полночь"), "Подарок", 1, "Подарок"),
        Exercise("e_pm13l49_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nОля: Когда желание?\nФирӯз: _____", null, listOf("Утром.", "В полночь!", "На ёлке."), "В полночь!", 1, "Полночь"),
    ),
)

// ═══════════════════════════════════════════════════
// MODULES 11–20 (lessons 41–80; lessons 50–80 in PersonalRussianCourseExtendedPart2.kt)
// ═══════════════════════════════════════════════════

internal val personalModule11 = Module(
    id = "personal_m11", courseId = "personal_russian", title = "Санъат ва эҷодкорӣ",
    description = "Мусиқӣ, кино, рассм, акс", emoji = "\uD83C\uDFA8",
    orderIndex = 10, lessons = listOf(personalM11L41, personalM11L42, personalM11L43, personalM11L44),
)

internal val personalModule12 = Module(
    id = "personal_m12", courseId = "personal_russian", title = "Сафар дар Русия",
    description = "Москва, Санкт-Петербург, нақлиёт, ҳаво", emoji = "\uD83D\uDDFD",
    orderIndex = 11, lessons = listOf(personalM12L45, personalM12L46, personalM12L47, personalM12L48),
)

internal val personalModule13 = Module(
    id = "personal_m13", courseId = "personal_russian", title = "Идҳо",
    description = "Соли нав, зодрӯз, Наврӯз, тӯй", emoji = "\uD83C\uDF89",
    orderIndex = 12, lessons = listOf(personalM13L49, personalM13L50, personalM13L51, personalM13L52),
)

internal val personalModule14 = Module(
    id = "personal_m14", courseId = "personal_russian", title = "Саломатӣ",
    description = "Духтур, варзиш, ғизо, дорухона", emoji = "\uD83E\uDE7A",
    orderIndex = 13, lessons = listOf(personalM14L53, personalM14L54, personalM14L55, personalM14L56),
)

internal val personalModule15 = Module(
    id = "personal_m15", courseId = "personal_russian", title = "Харидорӣ",
    description = "Мағоза, бозор, либос, техника", emoji = "\uD83D\uDED2",
    orderIndex = 14, lessons = listOf(personalM15L57, personalM15L58, personalM15L59, personalM15L60),
)

internal val personalModule16 = Module(
    id = "personal_m16", courseId = "personal_russian", title = "Кор ва касб",
    description = "Кор, касбҳо, мусоҳиба, бизнес", emoji = "\uD83D\uDCBC",
    orderIndex = 15, lessons = listOf(personalM16L61, personalM16L62, personalM16L63, personalM16L64),
)

internal val personalModule17 = Module(
    id = "personal_m17", courseId = "personal_russian", title = "Табиат",
    description = "Фаслҳо, ҳайвонот, кӯҳҳо, экология", emoji = "\uD83C\uDF33",
    orderIndex = 16, lessons = listOf(personalM17L65, personalM17L66, personalM17L67, personalM17L68),
)

internal val personalModule18 = Module(
    id = "personal_m18", courseId = "personal_russian", title = "Маълумот",
    description = "Мактаб, донишгоҳ, забонҳо, онлайн", emoji = "\uD83C\uDF93",
    orderIndex = 17, lessons = listOf(personalM18L69, personalM18L70, personalM18L71, personalM18L72),
)

internal val personalModule19 = Module(
    id = "personal_m19", courseId = "personal_russian", title = "Фарҳанг ва анъана",
    description = "Фарҳанги тоҷик ва русӣ, адабиёт, мусиқӣ", emoji = "\uD83C\uDFAD",
    orderIndex = 18, lessons = listOf(personalM19L73, personalM19L74, personalM19L75, personalM19L76),
)

internal val personalModule20 = Module(
    id = "personal_m20", courseId = "personal_russian", title = "Имтиҳони ҷамъбастӣ",
    description = "Такрор, муколама, имтиҳони ниҳоӣ", emoji = "\uD83C\uDFC6",
    orderIndex = 19, lessons = listOf(personalM20L77, personalM20L78, personalM20L79, personalM20L80),
)

object PersonalRussianCourseExtended {
    fun getModules(): List<Module> = listOf(
        personalModule11, personalModule12, personalModule13, personalModule14, personalModule15,
        personalModule16, personalModule17, personalModule18, personalModule19, personalModule20,
    )
}
