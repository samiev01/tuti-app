package app.tuti.tj.data.content.courses

import app.tuti.tj.data.content.Course
import app.tuti.tj.data.content.Dialogue
import app.tuti.tj.data.content.DialogueLine
import app.tuti.tj.data.content.Exercise
import app.tuti.tj.data.content.ExerciseType
import app.tuti.tj.data.content.GrammarTip
import app.tuti.tj.data.content.Lesson
import app.tuti.tj.data.content.Module
import app.tuti.tj.data.content.WordItem

// ═══════════════════════════════════════════════════
//  MODULE 1 · ДАР ДОНИШГОҲ  (At University)
// ═══════════════════════════════════════════════════

// ── Lesson 1: Рӯзи аввал (First Day) ────────────

private val esM1L1 = Lesson(
    id = "es_m1_l1", moduleId = "es_m1",
    title = "Рӯзи аввал", description = "Рӯзи аввал дар донишгоҳ",
    emoji = "\uD83C\uDF93", orderIndex = 0,
    dialogue = Dialogue(
        "Дар синфхона",
        listOf(
            DialogueLine("Teacher", "Welcome to the class! What is your name?", "Хуш омадед ба синф! Номи шумо чист?"),
            DialogueLine("Firuz", "My name is Firuz. I am from Tajikistan.", "Номи ман Фирӯз. Ман аз Тоҷикистон."),
            DialogueLine("Teacher", "Nice to meet you! Please take a seat.", "Аз шиносоӣ хурсандам! Лутфан шинед."),
            DialogueLine("Firuz", "Thank you!", "Ташаккур!"),
        ),
    ),
    newWords = listOf(
        WordItem("es_w1_1", "University", "Донишгоҳ", "Uni-ver-si-ty", "I study at the university", "Ман дар донишгоҳ мехонам", "es_m1_l1"),
        WordItem("es_w1_2", "Class", "Синф", "Class", "Welcome to the class", "Хуш омадед ба синф", "es_m1_l1"),
        WordItem("es_w1_3", "Student", "Донишҷӯ", "Stu-dent", "I am a student", "Ман донишҷӯ ҳастам", "es_m1_l1"),
        WordItem("es_w1_4", "Teacher", "Муаллим", "Tea-cher", "The teacher is kind", "Муаллим меҳрубон аст", "es_m1_l1"),
        WordItem("es_w1_5", "Book", "Китоб", "Book", "Open your book", "Китобатонро кушоед", "es_m1_l1"),
        WordItem("es_w1_6", "Pen", "Қалам", "Pen", "I need a pen", "Ба ман қалам лозим", "es_m1_l1"),
        WordItem("es_w1_7", "Desk", "Миз", "Desk", "Sit at the desk", "Дар сари миз шинед", "es_m1_l1"),
        WordItem("es_w1_8", "Homework", "Вазифа", "Home-work", "Do your homework", "Вазифаатонро иҷро кунед", "es_m1_l1"),
    ),
    grammarTip = GrammarTip(
        "I am a student / I study at...",
        "Барои гуфтани касби худ аз «I am a student» ва барои ҷои таҳсил аз «I study at...» истифода баред.",
        listOf("I am a student.", "I study at the university.", "I am from Tajikistan."),
    ),
    exercises = listOf(
        Exercise("es_e1_1", ExerciseType.MULTIPLE_CHOICE, "«University» чӣ маъно дорад?", "University = ...", listOf("Мактаб", "Донишгоҳ", "Синф", "Китобхона"), "Донишгоҳ", 1, "University — Донишгоҳ"),
        Exercise("es_e1_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I am a _____.", listOf("teacher", "book", "student", "desk"), "student", 2, "I am a student — Ман донишҷӯ ҳастам"),
        Exercise("es_e1_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман дар донишгоҳ мехонам»-ро интихоб кунед:", null, listOf("I work at the university", "I study at the university", "I live at the university", "I teach at the university"), "I study at the university", 1, "Ман дар донишгоҳ мехонам = I study at the university"),
        Exercise("es_e1_4", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Student" to "Донишҷӯ", "Teacher" to "Муаллим", "Book" to "Китоб", "Pen" to "Қалам")),
        Exercise("es_e1_5", ExerciseType.TYPE_ANSWER, "«Вазифа»-ро ба англисӣ нависед:", "Вазифа = ?", null, "Homework", null, "Вазифа — Homework"),
        Exercise("es_e1_6", ExerciseType.MULTIPLE_CHOICE, "«Desk» чӣ маъно дорад?", "Desk = ...", listOf("Китоб", "Қалам", "Миз", "Синф"), "Миз", 2, "Desk — Миз"),
        Exercise("es_e1_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман донишҷӯ ҳастам»", null, null, "I am a student", null, "I am a + касб", words = listOf("student", "a", "am", "I")),
        Exercise("es_e1_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTeacher: Welcome! What is your name?\nFiruz: _____", null, listOf("Thank you!", "My name is Firuz.", "Open the book."), "My name is Firuz.", 1, "Бо «My name is + ном» ҷавоб медиҳем"),
    ),
)

// ── Lesson 2: Дарсҳо (Classes) ──────────────────

private val esM1L2 = Lesson(
    id = "es_m1_l2", moduleId = "es_m1",
    title = "Дарсҳо", description = "Дар бораи дарсҳо ва ҷадвал",
    emoji = "\uD83D\uDCDA", orderIndex = 1,
    dialogue = Dialogue(
        "Ҷадвали дарсҳо",
        listOf(
            DialogueLine("Firuz", "What time is the English class?", "Дарси англисӣ дар чӣ вақт аст?"),
            DialogueLine("Anna", "It is at nine o'clock.", "Дар соати нӯҳ аст."),
            DialogueLine("Firuz", "What subjects do you have today?", "Имрӯз кадом фанҳо доред?"),
            DialogueLine("Anna", "I have math and history.", "Ман математика ва таърих дорам."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w2_1", "Schedule", "Ҷадвал", "Sched-ule", "Check the schedule", "Ҷадвалро тафтиш кунед", "es_m1_l2"),
        WordItem("es_w2_2", "Lesson", "Дарс", "Les-son", "The lesson starts at nine", "Дарс дар соати нӯҳ оғоз мешавад", "es_m1_l2"),
        WordItem("es_w2_3", "Subject", "Фан", "Sub-ject", "What is your favorite subject?", "Фани дӯстдоштаи шумо чист?", "es_m1_l2"),
        WordItem("es_w2_4", "Math", "Математика", "Math", "I like math", "Ман математикаро дӯст медорам", "es_m1_l2"),
        WordItem("es_w2_5", "Science", "Илм", "Sci-ence", "Science is interesting", "Илм ҷолиб аст", "es_m1_l2"),
        WordItem("es_w2_6", "History", "Таърих", "His-to-ry", "I study history", "Ман таърих мехонам", "es_m1_l2"),
        WordItem("es_w2_7", "English", "Англисӣ", "Eng-lish", "I learn English", "Ман англисӣ меомӯзам", "es_m1_l2"),
        WordItem("es_w2_8", "Exam", "Имтиҳон", "Ex-am", "The exam is next week", "Имтиҳон ҳафтаи оянда аст", "es_m1_l2"),
    ),
    grammarTip = GrammarTip(
        "What time is the class? / I have a lesson at...",
        "Барои пурсидани вақти дарс аз «What time is the...?» ва барои ҷавоб аз «I have a lesson at...» истифода баред.",
        listOf("What time is the English class?", "I have a lesson at nine.", "The exam is on Monday."),
    ),
    exercises = listOf(
        Exercise("es_e2_1", ExerciseType.MULTIPLE_CHOICE, "«Schedule» чӣ маъно дорад?", "Schedule = ...", listOf("Дарс", "Ҷадвал", "Имтиҳон", "Фан"), "Ҷадвал", 1, "Schedule — Ҷадвал"),
        Exercise("es_e2_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I have a _____ at nine.", listOf("exam", "lesson", "subject", "schedule"), "lesson", 1, "I have a lesson at — Ман дарс дорам дар"),
        Exercise("es_e2_3", ExerciseType.TYPE_ANSWER, "«Имтиҳон»-ро ба англисӣ нависед:", "Имтиҳон = ?", null, "Exam", null, "Имтиҳон — Exam"),
        Exercise("es_e2_4", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман математикаро дӯст медорам»-ро интихоб кунед:", null, listOf("I study math", "I like math", "I need math", "I have math"), "I like math", 1, "Ман математикаро дӯст медорам = I like math"),
        Exercise("es_e2_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Math" to "Математика", "History" to "Таърих", "Science" to "Илм", "English" to "Англисӣ")),
        Exercise("es_e2_6", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Sched-ule", listOf("Subject", "Schedule", "Science", "Student"), "Schedule", 1, "Schedule — Ҷадвал"),
        Exercise("es_e2_7", ExerciseType.MULTIPLE_CHOICE, "«Subject» чӣ маъно дорад?", "Subject = ...", listOf("Дарс", "Китоб", "Фан", "Имтиҳон"), "Фан", 2, "Subject — Фан"),
        Exercise("es_e2_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Дарси англисӣ дар чӣ вақт аст?»", null, null, "What time is the English class?", null, "What time is the...?", words = listOf("class?", "English", "the", "is", "time", "What")),
    ),
)

// ── Lesson 3: Китобхона (Library) ────────────────

private val esM1L3 = Lesson(
    id = "es_m1_l3", moduleId = "es_m1",
    title = "Китобхона", description = "Дар китобхона",
    emoji = "\uD83D\uDCDA", orderIndex = 2,
    dialogue = Dialogue(
        "Дар китобхона",
        listOf(
            DialogueLine("Firuz", "Where is the library?", "Китобхона дар куҷост?"),
            DialogueLine("Anna", "It is next to the cafeteria.", "Дар паҳлӯи ошхона аст."),
            DialogueLine("Firuz", "Can I borrow this book?", "Метавонам ин китобро қарз гирам?"),
            DialogueLine("Librarian", "Yes, please return it in two weeks.", "Ҳа, лутфан дар ду ҳафта баргардонед."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w3_1", "Library", "Китобхона", "Li-bra-ry", "I go to the library", "Ман ба китобхона меравам", "es_m1_l3"),
        WordItem("es_w3_2", "Read", "Хондан", "Read", "I read every day", "Ман ҳар рӯз мехонам", "es_m1_l3"),
        WordItem("es_w3_3", "Write", "Навиштан", "Write", "Please write your name", "Лутфан номатонро нависед", "es_m1_l3"),
        WordItem("es_w3_4", "Study", "Омӯхтан", "Stud-y", "I study in the library", "Ман дар китобхона меомӯзам", "es_m1_l3"),
        WordItem("es_w3_5", "Borrow", "Қарз гирифтан", "Bor-row", "Can I borrow this?", "Метавонам инро қарз гирам?", "es_m1_l3"),
        WordItem("es_w3_6", "Return", "Баргардондан", "Re-turn", "Return the book please", "Лутфан китобро баргардонед", "es_m1_l3"),
        WordItem("es_w3_7", "Quiet", "Ором", "Qui-et", "Please be quiet", "Лутфан ором бошед", "es_m1_l3"),
        WordItem("es_w3_8", "Computer", "Компютер", "Com-pu-ter", "I use the computer", "Ман компютер истифода мебарам", "es_m1_l3"),
    ),
    grammarTip = GrammarTip(
        "Can I borrow...? / Where is the...?",
        "Барои иҷозат хостан аз «Can I borrow...?» ва барои пурсидани ҷой аз «Where is the...?» истифода баред.",
        listOf("Can I borrow this book?", "Where is the library?", "Please return it in two weeks."),
    ),
    exercises = listOf(
        Exercise("es_e3_1", ExerciseType.MULTIPLE_CHOICE, "«Library» чӣ маъно дорад?", "Library = ...", listOf("Синф", "Ошхона", "Китобхона", "Хобгоҳ"), "Китобхона", 2, "Library — Китобхона"),
        Exercise("es_e3_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Can I _____ this book?", listOf("read", "write", "borrow", "return"), "borrow", 2, "Can I borrow — Метавонам қарз гирам"),
        Exercise("es_e3_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Китобхона дар куҷост?»-ро интихоб кунед:", null, listOf("Where is the class?", "Where is the library?", "Where is the book?", "Where is the desk?"), "Where is the library?", 1, "Китобхона дар куҷост? = Where is the library?"),
        Exercise("es_e3_4", ExerciseType.TYPE_ANSWER, "«Хондан»-ро ба англисӣ нависед:", "Хондан = ?", null, "Read", null, "Хондан — Read"),
        Exercise("es_e3_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Read" to "Хондан", "Write" to "Навиштан", "Borrow" to "Қарз гирифтан", "Return" to "Баргардондан")),
        Exercise("es_e3_6", ExerciseType.MULTIPLE_CHOICE, "«Quiet» чӣ маъно дорад?", "Quiet = ...", listOf("Баланд", "Тез", "Ором", "Тоза"), "Ором", 2, "Quiet — Ором"),
        Exercise("es_e3_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Com-pu-ter", listOf("Library", "Computer", "Quiet", "Return"), "Computer", 1, "Computer — Компютер"),
        Exercise("es_e3_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Can I borrow this book?\nLibrarian: _____", null, listOf("Please be quiet.", "Yes, return it in two weeks.", "The library is closed."), "Yes, return it in two weeks.", 1, "Ҷавоби иҷозат додан"),
    ),
)

// ── Lesson 4: Имтиҳон (Exams) ───────────────────

private val esM1L4 = Lesson(
    id = "es_m1_l4", moduleId = "es_m1",
    title = "Имтиҳон", description = "Тайёрӣ ба имтиҳон",
    emoji = "\uD83D\uDCDD", orderIndex = 3,
    dialogue = Dialogue(
        "Тайёрӣ ба имтиҳон",
        listOf(
            DialogueLine("Firuz", "I need to study for the exam.", "Ман бояд барои имтиҳон тайёрӣ бинам."),
            DialogueLine("Anna", "When is the exam?", "Имтиҳон кай аст?"),
            DialogueLine("Firuz", "It is on Friday. I must prepare.", "Рӯзи ҷумъа. Ман бояд тайёр шавам."),
            DialogueLine("Anna", "Don't forget to practice!", "Машқ карданро фаромӯш накунед!"),
        ),
    ),
    newWords = listOf(
        WordItem("es_w4_1", "Test", "Тест", "Test", "We have a test today", "Имрӯз тест дорем", "es_m1_l4"),
        WordItem("es_w4_2", "Grade", "Баҳо", "Grade", "I got a good grade", "Ман баҳои хуб гирифтам", "es_m1_l4"),
        WordItem("es_w4_3", "Pass", "Гузаштан", "Pass", "I want to pass the exam", "Ман мехоҳам имтиҳонро гузарам", "es_m1_l4"),
        WordItem("es_w4_4", "Fail", "Нагузаштан", "Fail", "I don't want to fail", "Ман намехоҳам нагузарам", "es_m1_l4"),
        WordItem("es_w4_5", "Prepare", "Тайёр шудан", "Pre-pare", "I need to prepare", "Ман бояд тайёр шавам", "es_m1_l4"),
        WordItem("es_w4_6", "Remember", "Дар хотир доштан", "Re-mem-ber", "Remember the rules", "Қоидаҳоро дар хотир доред", "es_m1_l4"),
        WordItem("es_w4_7", "Forget", "Фаромӯш кардан", "For-get", "Don't forget!", "Фаромӯш накунед!", "es_m1_l4"),
        WordItem("es_w4_8", "Practice", "Машқ кардан", "Prac-tice", "Practice every day", "Ҳар рӯз машқ кунед", "es_m1_l4"),
    ),
    grammarTip = GrammarTip(
        "I need to study for... / The exam is on...",
        "Барои зарурат аз «I need to...» ва барои санаи имтиҳон аз «The exam is on + рӯз» истифода баред.",
        listOf("I need to study for the exam.", "The exam is on Friday.", "Don't forget to practice!"),
    ),
    exercises = listOf(
        Exercise("es_e4_1", ExerciseType.MULTIPLE_CHOICE, "«Pass» чӣ маъно дорад?", "Pass = ...", listOf("Нагузаштан", "Тайёр шудан", "Гузаштан", "Фаромӯш кардан"), "Гузаштан", 2, "Pass — Гузаштан"),
        Exercise("es_e4_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I need to _____ for the exam.", listOf("forget", "fail", "prepare", "pass"), "prepare", 2, "I need to prepare — Ман бояд тайёр шавам"),
        Exercise("es_e4_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман баҳои хуб гирифтам»-ро интихоб кунед:", null, listOf("I passed the exam", "I need to study", "I got a good grade", "I forgot the test"), "I got a good grade", 2, "Ман баҳои хуб гирифтам = I got a good grade"),
        Exercise("es_e4_4", ExerciseType.TYPE_ANSWER, "«Машқ кардан»-ро ба англисӣ нависед:", "Машқ кардан = ?", null, "Practice", null, "Машқ кардан — Practice"),
        Exercise("es_e4_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Pass" to "Гузаштан", "Fail" to "Нагузаштан", "Remember" to "Дар хотир доштан", "Forget" to "Фаромӯш кардан")),
        Exercise("es_e4_6", ExerciseType.MULTIPLE_CHOICE, "«Grade» чӣ маъно дорад?", "Grade = ...", listOf("Тест", "Баҳо", "Имтиҳон", "Машқ"), "Баҳо", 1, "Grade — Баҳо"),
        Exercise("es_e4_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Имтиҳон рӯзи ҷумъа аст»", null, null, "The exam is on Friday", null, "The exam is on + рӯз", words = listOf("Friday", "on", "is", "exam", "The")),
        Exercise("es_e4_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Prac-tice", listOf("Prepare", "Practice", "Pass", "Forget"), "Practice", 1, "Practice — Машқ кардан"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 2 · ҲАЁТИ ДОНИШҶӮӢ  (Student Life)
// ═══════════════════════════════════════════════════

// ── Lesson 5: Хобгоҳ (Dormitory) ────────────────

private val esM2L5 = Lesson(
    id = "es_m2_l5", moduleId = "es_m2",
    title = "Хобгоҳ", description = "Ҳаёт дар хобгоҳ",
    emoji = "\uD83C\uDFE0", orderIndex = 0,
    dialogue = Dialogue(
        "Дар хобгоҳ",
        listOf(
            DialogueLine("Firuz", "This is my room. I share it with a roommate.", "Ин хонаи ман аст. Бо ҳамхона тақсим мекунам."),
            DialogueLine("Anna", "Is there a kitchen?", "Ошхона ҳаст?"),
            DialogueLine("Firuz", "Yes, there is a kitchen and a bathroom.", "Ҳа, ошхона ва ванна ҳаст."),
            DialogueLine("Anna", "Don't forget the rules!", "Қоидаҳоро фаромӯш накунед!"),
        ),
    ),
    newWords = listOf(
        WordItem("es_w5_1", "Room", "Хона", "Room", "My room is small", "Хонаи ман хурд аст", "es_m2_l5"),
        WordItem("es_w5_2", "Roommate", "Ҳамхона", "Room-mate", "My roommate is friendly", "Ҳамхонаи ман дӯстона аст", "es_m2_l5"),
        WordItem("es_w5_3", "Bed", "Кат", "Bed", "The bed is comfortable", "Кат қулай аст", "es_m2_l5"),
        WordItem("es_w5_4", "Kitchen", "Ошхона", "Kitch-en", "I cook in the kitchen", "Ман дар ошхона мепазам", "es_m2_l5"),
        WordItem("es_w5_5", "Bathroom", "Ванна", "Bath-room", "The bathroom is clean", "Ванна тоза аст", "es_m2_l5"),
        WordItem("es_w5_6", "Clean", "Тоза", "Clean", "Keep the room clean", "Хонаро тоза нигоҳ доред", "es_m2_l5"),
        WordItem("es_w5_7", "Rules", "Қоидаҳо", "Rules", "Follow the rules", "Қоидаҳоро риоя кунед", "es_m2_l5"),
        WordItem("es_w5_8", "Key", "Калид", "Key", "Don't lose the key", "Калидро гум накунед", "es_m2_l5"),
    ),
    grammarTip = GrammarTip(
        "My room is on the... / I share a room with...",
        "Барои тасвири хона аз «My room is...» ва барои гуфтани ҳамхона аз «I share a room with...» истифода баред.",
        listOf("My room is on the second floor.", "I share a room with my friend.", "There is a kitchen."),
    ),
    exercises = listOf(
        Exercise("es_e5_1", ExerciseType.MULTIPLE_CHOICE, "«Room» чӣ маъно дорад?", "Room = ...", listOf("Кат", "Хона", "Калид", "Ванна"), "Хона", 1, "Room — Хона"),
        Exercise("es_e5_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I _____ a room with my friend.", listOf("clean", "cook", "share", "lose"), "share", 2, "I share a room — Ман хонаро тақсим мекунам"),
        Exercise("es_e5_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ванна тоза аст»-ро интихоб кунед:", null, listOf("The kitchen is clean", "The bathroom is clean", "The room is clean", "The bed is clean"), "The bathroom is clean", 1, "Ванна тоза аст = The bathroom is clean"),
        Exercise("es_e5_4", ExerciseType.TYPE_ANSWER, "«Калид»-ро ба англисӣ нависед:", "Калид = ?", null, "Key", null, "Калид — Key"),
        Exercise("es_e5_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Room" to "Хона", "Kitchen" to "Ошхона", "Bed" to "Кат", "Clean" to "Тоза")),
        Exercise("es_e5_6", ExerciseType.MULTIPLE_CHOICE, "«Roommate» чӣ маъно дорад?", "Roommate = ...", listOf("Дӯст", "Ҳамхона", "Муаллим", "Хона"), "Ҳамхона", 1, "Roommate — Ҳамхона"),
        Exercise("es_e5_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Хонаро тоза нигоҳ доред»", null, null, "Keep the room clean", null, "Keep + чиз + clean", words = listOf("clean", "room", "the", "Keep")),
        Exercise("es_e5_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Kitch-en", listOf("Bathroom", "Kitchen", "Bedroom", "Key"), "Kitchen", 1, "Kitchen — Ошхона"),
    ),
)

// ── Lesson 6: Хӯроки донишҷӯӣ (Student Food) ───

private val esM2L6 = Lesson(
    id = "es_m2_l6", moduleId = "es_m2",
    title = "Хӯроки донишҷӯӣ", description = "Хӯрок ва ошхона",
    emoji = "\uD83C\uDF5D", orderIndex = 1,
    dialogue = Dialogue(
        "Дар ошхона",
        listOf(
            DialogueLine("Firuz", "Where can I eat?", "Дар куҷо хӯрок хӯрда метавонам?"),
            DialogueLine("Anna", "There is a cafeteria near the library.", "Дар наздикии китобхона ошхона ҳаст."),
            DialogueLine("Firuz", "How much does lunch cost?", "Хӯроки рӯзона чанд пул аст?"),
            DialogueLine("Anna", "It is cheap. About three dollars.", "Арзон аст. Тақрибан се доллар."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w6_1", "Cafeteria", "Ошхона", "Caf-e-te-ri-a", "I eat in the cafeteria", "Ман дар ошхона хӯрок мехӯрам", "es_m2_l6"),
        WordItem("es_w6_2", "Breakfast", "Наҳорӣ", "Break-fast", "I eat breakfast at seven", "Ман дар соати ҳафт наҳорӣ мекунам", "es_m2_l6"),
        WordItem("es_w6_3", "Lunch", "Хӯроки рӯзона", "Lunch", "Lunch is at twelve", "Хӯроки рӯзона дар соати дувоздаҳ", "es_m2_l6"),
        WordItem("es_w6_4", "Dinner", "Хӯроки шом", "Din-ner", "Dinner is at seven", "Хӯроки шом дар соати ҳафт", "es_m2_l6"),
        WordItem("es_w6_5", "Cheap", "Арзон", "Cheap", "The food is cheap", "Хӯрок арзон аст", "es_m2_l6"),
        WordItem("es_w6_6", "Hungry", "Гурусна", "Hun-gry", "I am hungry", "Ман гурусна ҳастам", "es_m2_l6"),
        WordItem("es_w6_7", "Cook", "Пухтан", "Cook", "I can cook", "Ман пухтан метавонам", "es_m2_l6"),
        WordItem("es_w6_8", "Order", "Фармоиш додан", "Or-der", "I want to order food", "Ман хӯрок фармоиш додан мехоҳам", "es_m2_l6"),
    ),
    grammarTip = GrammarTip(
        "Where can I eat? / How much does it cost?",
        "Барои ҷои хӯрок аз «Where can I eat?» ва барои нарх аз «How much does it cost?» истифода баред.",
        listOf("Where can I eat?", "How much does lunch cost?", "I am hungry."),
    ),
    exercises = listOf(
        Exercise("es_e6_1", ExerciseType.MULTIPLE_CHOICE, "«Hungry» чӣ маъно дорад?", "Hungry = ...", listOf("Хаста", "Гурусна", "Арзон", "Тоза"), "Гурусна", 1, "Hungry — Гурусна"),
        Exercise("es_e6_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "How much does it _____?", listOf("eat", "cook", "cost", "order"), "cost", 2, "How much does it cost — Чанд пул аст"),
        Exercise("es_e6_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман гурусна ҳастам»-ро интихоб кунед:", null, listOf("I am tired", "I am hungry", "I am cold", "I am happy"), "I am hungry", 1, "Ман гурусна ҳастам = I am hungry"),
        Exercise("es_e6_4", ExerciseType.TYPE_ANSWER, "«Арзон»-ро ба англисӣ нависед:", "Арзон = ?", null, "Cheap", null, "Арзон — Cheap"),
        Exercise("es_e6_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Breakfast" to "Наҳорӣ", "Lunch" to "Хӯроки рӯзона", "Dinner" to "Хӯроки шом", "Cook" to "Пухтан")),
        Exercise("es_e6_6", ExerciseType.MULTIPLE_CHOICE, "«Order» чӣ маъно дорад?", "Order = ...", listOf("Пухтан", "Хӯрдан", "Фармоиш додан", "Гурусна"), "Фармоиш додан", 2, "Order — Фармоиш додан"),
        Exercise("es_e6_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: How much does lunch cost?\nAnna: _____", null, listOf("I am hungry.", "It is cheap, about three dollars.", "I can cook."), "It is cheap, about three dollars.", 1, "Ҷавоб дар бораи нарх"),
        Exercise("es_e6_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман дар ошхона хӯрок мехӯрам»", null, null, "I eat in the cafeteria", null, "I eat in the + ҷой", words = listOf("cafeteria", "the", "in", "eat", "I")),
    ),
)

// ── Lesson 7: Дӯстон (Friends) ──────────────────

private val esM2L7 = Lesson(
    id = "es_m2_l7", moduleId = "es_m2",
    title = "Дӯстон", description = "Бо дӯстон вақт гузарондан",
    emoji = "\uD83E\uDD1D", orderIndex = 2,
    dialogue = Dialogue(
        "Бо дӯстон",
        listOf(
            DialogueLine("Firuz", "Let's go to the movies!", "Биёед ба кино равем!"),
            DialogueLine("Anna", "That sounds fun!", "Ин ҷолиб аст!"),
            DialogueLine("Firuz", "Do you want to invite Tom?", "Мехоҳед Томро даъват кунед?"),
            DialogueLine("Anna", "Yes! We can go together.", "Ҳа! Мо якҷоя рафта метавонем."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w7_1", "Friend", "Дӯст", "Friend", "She is my friend", "Вай дӯсти ман аст", "es_m2_l7"),
        WordItem("es_w7_2", "Together", "Якҷоя", "To-geth-er", "Let's go together", "Биёед якҷоя равем", "es_m2_l7"),
        WordItem("es_w7_3", "Fun", "Завқ", "Fun", "That was fun", "Ин завқовар буд", "es_m2_l7"),
        WordItem("es_w7_4", "Party", "Базм", "Par-ty", "There is a party tonight", "Имшаб базм ҳаст", "es_m2_l7"),
        WordItem("es_w7_5", "Movie", "Филм", "Mov-ie", "Let's watch a movie", "Биёед филм тамошо кунем", "es_m2_l7"),
        WordItem("es_w7_6", "Music", "Мусиқӣ", "Mu-sic", "I like music", "Ман мусиқиро дӯст медорам", "es_m2_l7"),
        WordItem("es_w7_7", "Weekend", "Охири ҳафта", "Week-end", "What are you doing this weekend?", "Охири ҳафта чӣ кор мекунед?", "es_m2_l7"),
        WordItem("es_w7_8", "Invite", "Даъват кардан", "In-vite", "I want to invite my friends", "Ман дӯстонамро даъват кардан мехоҳам", "es_m2_l7"),
    ),
    grammarTip = GrammarTip(
        "Let's go to... / Do you want to...?",
        "Барои пешниҳод аз «Let's go to...» ва барои пурсидан аз «Do you want to...?» истифода баред.",
        listOf("Let's go to the movies!", "Do you want to come?", "We can go together."),
    ),
    exercises = listOf(
        Exercise("es_e7_1", ExerciseType.MULTIPLE_CHOICE, "«Together» чӣ маъно дорад?", "Together = ...", listOf("Танҳо", "Якҷоя", "Завқ", "Дӯст"), "Якҷоя", 1, "Together — Якҷоя"),
        Exercise("es_e7_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____ go to the movies!", listOf("Do", "Can", "Let's", "Want"), "Let's", 2, "Let's go — Биёед равем"),
        Exercise("es_e7_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Мехоҳед Томро даъват кунед?»-ро интихоб кунед:", null, listOf("Let's invite Tom", "Do you want to invite Tom?", "Tom is invited", "Can Tom come?"), "Do you want to invite Tom?", 1, "Мехоҳед даъват кунед? = Do you want to invite?"),
        Exercise("es_e7_4", ExerciseType.TYPE_ANSWER, "«Филм»-ро ба англисӣ нависед:", "Филм = ?", null, "Movie", null, "Филм — Movie"),
        Exercise("es_e7_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Friend" to "Дӯст", "Party" to "Базм", "Music" to "Мусиқӣ", "Weekend" to "Охири ҳафта")),
        Exercise("es_e7_6", ExerciseType.MULTIPLE_CHOICE, "«Invite» чӣ маъно дорад?", "Invite = ...", listOf("Рафтан", "Омадан", "Даъват кардан", "Гуфтан"), "Даъват кардан", 2, "Invite — Даъват кардан"),
        Exercise("es_e7_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Week-end", listOf("Movie", "Music", "Weekend", "Party"), "Weekend", 2, "Weekend — Охири ҳафта"),
        Exercise("es_e7_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Биёед якҷоя равем»", null, null, "Let's go together", null, "Let's go + тарз", words = listOf("together", "go", "Let's")),
    ),
)

// ── Lesson 8: Варзиш (Sports) ───────────────────

private val esM2L8 = Lesson(
    id = "es_m2_l8", moduleId = "es_m2",
    title = "Варзиш", description = "Варзиш ва бозӣ",
    emoji = "\u26BD", orderIndex = 3,
    dialogue = Dialogue(
        "Дар варзишгоҳ",
        listOf(
            DialogueLine("Firuz", "Do you like sports?", "Шумо варзишро дӯст медоред?"),
            DialogueLine("Tom", "Yes! I play football.", "Ҳа! Ман футбол бозӣ мекунам."),
            DialogueLine("Firuz", "Where is the gym?", "Варзишгоҳ дар куҷост?"),
            DialogueLine("Tom", "It is behind the dormitory. Let's go!", "Дар пушти хобгоҳ. Биёед равем!"),
        ),
    ),
    newWords = listOf(
        WordItem("es_w8_1", "Sport", "Варзиш", "Sport", "I like sport", "Ман варзишро дӯст медорам", "es_m2_l8"),
        WordItem("es_w8_2", "Play", "Бозӣ кардан", "Play", "I play football", "Ман футбол бозӣ мекунам", "es_m2_l8"),
        WordItem("es_w8_3", "Run", "Давидан", "Run", "I run every morning", "Ман ҳар субҳ медавам", "es_m2_l8"),
        WordItem("es_w8_4", "Swim", "Шино кардан", "Swim", "I can swim", "Ман шино карда метавонам", "es_m2_l8"),
        WordItem("es_w8_5", "Football", "Футбол", "Foot-ball", "Football is popular", "Футбол маъмул аст", "es_m2_l8"),
        WordItem("es_w8_6", "Gym", "Варзишгоҳ", "Gym", "I go to the gym", "Ман ба варзишгоҳ меравам", "es_m2_l8"),
        WordItem("es_w8_7", "Team", "Дастаи", "Team", "I am on the team", "Ман дар даста ҳастам", "es_m2_l8"),
        WordItem("es_w8_8", "Win", "Ғалаба кардан", "Win", "We want to win", "Мо ғалаба кардан мехоҳем", "es_m2_l8"),
    ),
    grammarTip = GrammarTip(
        "I play football / Do you like sports?",
        "Барои гуфтани варзиш аз «I play + варзиш» ва барои пурсидан аз «Do you like sports?» истифода баред.",
        listOf("I play football.", "Do you like sports?", "I go to the gym every day."),
    ),
    exercises = listOf(
        Exercise("es_e8_1", ExerciseType.MULTIPLE_CHOICE, "«Gym» чӣ маъно дорад?", "Gym = ...", listOf("Мактаб", "Варзишгоҳ", "Китобхона", "Хобгоҳ"), "Варзишгоҳ", 1, "Gym — Варзишгоҳ"),
        Exercise("es_e8_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I _____ football.", listOf("run", "swim", "play", "win"), "play", 2, "I play football — Ман футбол бозӣ мекунам"),
        Exercise("es_e8_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман ҳар субҳ медавам»-ро интихоб кунед:", null, listOf("I swim every morning", "I play every morning", "I run every morning", "I win every morning"), "I run every morning", 2, "Ман ҳар субҳ медавам = I run every morning"),
        Exercise("es_e8_4", ExerciseType.TYPE_ANSWER, "«Ғалаба кардан»-ро ба англисӣ нависед:", "Ғалаба кардан = ?", null, "Win", null, "Ғалаба кардан — Win"),
        Exercise("es_e8_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Play" to "Бозӣ кардан", "Run" to "Давидан", "Swim" to "Шино кардан", "Win" to "Ғалаба кардан")),
        Exercise("es_e8_6", ExerciseType.MULTIPLE_CHOICE, "«Team» чӣ маъно дорад?", "Team = ...", listOf("Бозӣ", "Ғалаба", "Дастаи", "Варзиш"), "Дастаи", 2, "Team — Дастаи"),
        Exercise("es_e8_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Do you like sports?\nTom: _____", null, listOf("I am hungry.", "Yes! I play football.", "The gym is closed."), "Yes! I play football.", 1, "Ҷавоб дар бораи варзиш"),
        Exercise("es_e8_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Foot-ball", listOf("Football", "Gym", "Team", "Sport"), "Football", 0, "Football — Футбол"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 3 · МАҲОРАТИ ТАҲСИЛӢ  (Academic Skills)
// ═══════════════════════════════════════════════════

// ── Lesson 9: Навиштани эссе (Writing Essays) ───

private val esM3L9 = Lesson(
    id = "es_m3_l9", moduleId = "es_m3",
    title = "Навиштани эссе", description = "Навиштани иншо",
    emoji = "\u270D\uFE0F", orderIndex = 0,
    dialogue = Dialogue(
        "Навиштани иншо",
        listOf(
            DialogueLine("Teacher", "Today we will write an essay.", "Имрӯз мо иншо менависем."),
            DialogueLine("Firuz", "What is the topic?", "Мавзӯъ чист?"),
            DialogueLine("Teacher", "Write about your opinion on education.", "Дар бораи фикри худ оид ба таълим нависед."),
            DialogueLine("Firuz", "Should I write a conclusion?", "Хулоса навишта метавонам?"),
            DialogueLine("Teacher", "Yes, and give examples.", "Ҳа, ва мисолҳо оваред."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w9_1", "Essay", "Иншо", "Es-say", "Write an essay", "Иншо нависед", "es_m3_l9"),
        WordItem("es_w9_2", "Write", "Навиштан", "Write", "I write every day", "Ман ҳар рӯз менависам", "es_m3_l9"),
        WordItem("es_w9_3", "Topic", "Мавзӯъ", "Top-ic", "Choose a topic", "Мавзӯъро интихоб кунед", "es_m3_l9"),
        WordItem("es_w9_4", "Introduction", "Муқаддима", "In-tro-duc-tion", "Start with an introduction", "Аз муқаддима оғоз кунед", "es_m3_l9"),
        WordItem("es_w9_5", "Conclusion", "Хулоса", "Con-clu-sion", "Write a conclusion", "Хулоса нависед", "es_m3_l9"),
        WordItem("es_w9_6", "Paragraph", "Банд", "Par-a-graph", "Write three paragraphs", "Се банд нависед", "es_m3_l9"),
        WordItem("es_w9_7", "Opinion", "Фикр", "O-pin-ion", "In my opinion...", "Ба фикри ман...", "es_m3_l9"),
        WordItem("es_w9_8", "Example", "Мисол", "Ex-am-ple", "For example...", "Масалан...", "es_m3_l9"),
    ),
    grammarTip = GrammarTip(
        "In my opinion... / For example...",
        "Дар иншо барои фикри худ аз «In my opinion...» ва барои мисол аз «For example...» истифода баред.",
        listOf("In my opinion, education is important.", "For example, students learn new skills.", "In conclusion, I believe..."),
    ),
    exercises = listOf(
        Exercise("es_e9_1", ExerciseType.MULTIPLE_CHOICE, "«Essay» чӣ маъно дорад?", "Essay = ...", listOf("Мисол", "Иншо", "Мавзӯъ", "Банд"), "Иншо", 1, "Essay — Иншо"),
        Exercise("es_e9_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "In my _____, education is important.", listOf("example", "conclusion", "opinion", "paragraph"), "opinion", 2, "In my opinion — Ба фикри ман"),
        Exercise("es_e9_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Мавзӯъро интихоб кунед»-ро интихоб кунед:", null, listOf("Write a topic", "Read the topic", "Choose a topic", "Finish the topic"), "Choose a topic", 2, "Мавзӯъро интихоб кунед = Choose a topic"),
        Exercise("es_e9_4", ExerciseType.TYPE_ANSWER, "«Хулоса»-ро ба англисӣ нависед:", "Хулоса = ?", null, "Conclusion", null, "Хулоса — Conclusion"),
        Exercise("es_e9_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Essay" to "Иншо", "Topic" to "Мавзӯъ", "Opinion" to "Фикр", "Example" to "Мисол")),
        Exercise("es_e9_6", ExerciseType.MULTIPLE_CHOICE, "«Paragraph» чӣ маъно дорад?", "Paragraph = ...", listOf("Муқаддима", "Хулоса", "Банд", "Фикр"), "Банд", 2, "Paragraph — Банд"),
        Exercise("es_e9_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Аз муқаддима оғоз кунед»", null, null, "Start with an introduction", null, "Start with + чиз", words = listOf("introduction", "an", "with", "Start")),
        Exercise("es_e9_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Con-clu-sion", listOf("Introduction", "Conclusion", "Opinion", "Paragraph"), "Conclusion", 1, "Conclusion — Хулоса"),
    ),
)

// ── Lesson 10: Презентатсия (Presentations) ─────

private val esM3L10 = Lesson(
    id = "es_m3_l10", moduleId = "es_m3",
    title = "Презентатсия", description = "Баромад дар назди синф",
    emoji = "\uD83D\uDCCA", orderIndex = 1,
    dialogue = Dialogue(
        "Презентатсия",
        listOf(
            DialogueLine("Firuz", "Today I will talk about education in Tajikistan.", "Имрӯз ман дар бораи таълим дар Тоҷикистон гап мезанам."),
            DialogueLine("Firuz", "This slide shows the main points.", "Ин слайд нуктаҳои асосиро нишон медиҳад."),
            DialogueLine("Firuz", "Are there any questions?", "Саволе ҳаст?"),
            DialogueLine("Anna", "Can you explain this point?", "Метавонед ин нуктаро шарҳ диҳед?"),
        ),
    ),
    newWords = listOf(
        WordItem("es_w10_1", "Present", "Баромад кардан", "Pre-sent", "I will present today", "Ман имрӯз баромад мекунам", "es_m3_l10"),
        WordItem("es_w10_2", "Slide", "Слайд", "Slide", "Next slide please", "Слайди навбатӣ лутфан", "es_m3_l10"),
        WordItem("es_w10_3", "Audience", "Шунавандагон", "Au-di-ence", "The audience is listening", "Шунавандагон гӯш медиҳанд", "es_m3_l10"),
        WordItem("es_w10_4", "Explain", "Шарҳ додан", "Ex-plain", "Can you explain?", "Метавонед шарҳ диҳед?", "es_m3_l10"),
        WordItem("es_w10_5", "Point", "Нукта", "Point", "This is the main point", "Ин нуктаи асосӣ аст", "es_m3_l10"),
        WordItem("es_w10_6", "Question", "Савол", "Ques-tion", "Do you have a question?", "Савол доред?", "es_m3_l10"),
        WordItem("es_w10_7", "Answer", "Ҷавоб", "An-swer", "I will answer your question", "Ман ба саволи шумо ҷавоб медиҳам", "es_m3_l10"),
        WordItem("es_w10_8", "Clear", "Равшан", "Clear", "Is that clear?", "Ин равшан аст?", "es_m3_l10"),
    ),
    grammarTip = GrammarTip(
        "Today I will talk about... / Are there any questions?",
        "Дар оғози презентатсия аз «Today I will talk about...» ва дар охир аз «Are there any questions?» истифода баред.",
        listOf("Today I will talk about education.", "Are there any questions?", "Let me explain this point."),
    ),
    exercises = listOf(
        Exercise("es_e10_1", ExerciseType.MULTIPLE_CHOICE, "«Explain» чӣ маъно дорад?", "Explain = ...", listOf("Пурсидан", "Шарҳ додан", "Нишон додан", "Гӯш кардан"), "Шарҳ додан", 1, "Explain — Шарҳ додан"),
        Exercise("es_e10_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Are there any _____?", listOf("slides", "points", "questions", "answers"), "questions", 2, "Are there any questions? — Саволе ҳаст?"),
        Exercise("es_e10_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ин нуктаи асосӣ аст»-ро интихоб кунед:", null, listOf("This is the answer", "This is the main point", "This is the question", "This is clear"), "This is the main point", 1, "Ин нуктаи асосӣ аст = This is the main point"),
        Exercise("es_e10_4", ExerciseType.TYPE_ANSWER, "«Шунавандагон»-ро ба англисӣ нависед:", "Шунавандагон = ?", null, "Audience", null, "Шунавандагон — Audience"),
        Exercise("es_e10_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Explain" to "Шарҳ додан", "Question" to "Савол", "Answer" to "Ҷавоб", "Clear" to "Равшан")),
        Exercise("es_e10_6", ExerciseType.MULTIPLE_CHOICE, "«Slide» чӣ маъно дорад?", "Slide = ...", listOf("Нукта", "Савол", "Слайд", "Ҷавоб"), "Слайд", 2, "Slide — Слайд"),
        Exercise("es_e10_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Имрӯз ман дар бораи таълим гап мезанам»", null, null, "Today I will talk about education", null, "Today I will talk about + мавзӯъ", words = listOf("education", "about", "talk", "will", "I", "Today")),
        Exercise("es_e10_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Are there any questions?\nAnna: _____", null, listOf("The slide is clear.", "Can you explain this point?", "Thank you for the presentation."), "Can you explain this point?", 1, "Савол пурсидан"),
    ),
)

// ── Lesson 11: Кор дар гурӯҳ (Group Work) ──────

private val esM3L11 = Lesson(
    id = "es_m3_l11", moduleId = "es_m3",
    title = "Кор дар гурӯҳ", description = "Кор дар гурӯҳ бо ҳамсинфон",
    emoji = "\uD83D\uDC65", orderIndex = 2,
    dialogue = Dialogue(
        "Лоиҳаи гурӯҳӣ",
        listOf(
            DialogueLine("Firuz", "We need to finish the project by Friday.", "Мо бояд лоиҳаро то ҷумъа тамом кунем."),
            DialogueLine("Anna", "Who is responsible for the slides?", "Кӣ барои слайдҳо масъул аст?"),
            DialogueLine("Tom", "I will do the slides. Let's discuss the plan.", "Ман слайдҳоро мекунам. Биёед нақшаро муҳокима кунем."),
            DialogueLine("Firuz", "I agree. Let's share the work.", "Ман розӣ. Биёед корро тақсим кунем."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w11_1", "Group", "Гурӯҳ", "Group", "Work in a group", "Дар гурӯҳ кор кунед", "es_m3_l11"),
        WordItem("es_w11_2", "Project", "Лоиҳа", "Pro-ject", "The project is due Friday", "Мӯҳлати лоиҳа рӯзи ҷумъа аст", "es_m3_l11"),
        WordItem("es_w11_3", "Share", "Тақсим кардан", "Share", "Let's share the work", "Биёед корро тақсим кунем", "es_m3_l11"),
        WordItem("es_w11_4", "Responsible", "Масъул", "Re-spon-si-ble", "Who is responsible?", "Кӣ масъул аст?", "es_m3_l11"),
        WordItem("es_w11_5", "Deadline", "Мӯҳлат", "Dead-line", "The deadline is Friday", "Мӯҳлат рӯзи ҷумъа аст", "es_m3_l11"),
        WordItem("es_w11_6", "Discuss", "Муҳокима кардан", "Dis-cuss", "Let's discuss the plan", "Биёед нақшаро муҳокима кунем", "es_m3_l11"),
        WordItem("es_w11_7", "Agree", "Розӣ шудан", "A-gree", "I agree with you", "Ман бо шумо розӣ", "es_m3_l11"),
        WordItem("es_w11_8", "Finish", "Тамом кардан", "Fin-ish", "We need to finish today", "Мо бояд имрӯз тамом кунем", "es_m3_l11"),
    ),
    grammarTip = GrammarTip(
        "We need to finish by... / Who is responsible for...?",
        "Барои мӯҳлат аз «We need to finish by + вақт» ва барои масъулият аз «Who is responsible for...?» истифода баред.",
        listOf("We need to finish by Friday.", "Who is responsible for the slides?", "Let's share the work."),
    ),
    exercises = listOf(
        Exercise("es_e11_1", ExerciseType.MULTIPLE_CHOICE, "«Project» чӣ маъно дорад?", "Project = ...", listOf("Гурӯҳ", "Лоиҳа", "Мӯҳлат", "Масъул"), "Лоиҳа", 1, "Project — Лоиҳа"),
        Exercise("es_e11_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Who is _____ for the slides?", listOf("agree", "share", "responsible", "finish"), "responsible", 2, "Who is responsible — Кӣ масъул аст"),
        Exercise("es_e11_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Биёед корро тақсим кунем»-ро интихоб кунед:", null, listOf("Let's discuss the work", "Let's finish the work", "Let's share the work", "Let's start the work"), "Let's share the work", 2, "Биёед корро тақсим кунем = Let's share the work"),
        Exercise("es_e11_4", ExerciseType.TYPE_ANSWER, "«Мӯҳлат»-ро ба англисӣ нависед:", "Мӯҳлат = ?", null, "Deadline", null, "Мӯҳлат — Deadline"),
        Exercise("es_e11_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Group" to "Гурӯҳ", "Share" to "Тақсим кардан", "Discuss" to "Муҳокима кардан", "Finish" to "Тамом кардан")),
        Exercise("es_e11_6", ExerciseType.MULTIPLE_CHOICE, "«Agree» чӣ маъно дорад?", "Agree = ...", listOf("Тамом кардан", "Розӣ шудан", "Муҳокима кардан", "Тақсим кардан"), "Розӣ шудан", 1, "Agree — Розӣ шудан"),
        Exercise("es_e11_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Мо бояд то ҷумъа тамом кунем»", null, null, "We need to finish by Friday", null, "We need to finish by + вақт", words = listOf("Friday", "by", "finish", "to", "need", "We")),
        Exercise("es_e11_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Who is responsible for the slides?\nTom: _____", null, listOf("I agree.", "I will do the slides.", "The deadline is Friday."), "I will do the slides.", 1, "Масъулиятро қабул кардан"),
    ),
)

// ── Lesson 12: Оянда (Future Plans) ─────────────

private val esM3L12 = Lesson(
    id = "es_m3_l12", moduleId = "es_m3",
    title = "Оянда", description = "Нақшаҳои оянда",
    emoji = "\uD83C\uDF1F", orderIndex = 3,
    dialogue = Dialogue(
        "Нақшаҳои оянда",
        listOf(
            DialogueLine("Teacher", "What do you want to become after graduation?", "Баъди хатм чӣ шудан мехоҳед?"),
            DialogueLine("Firuz", "I want to become an engineer.", "Ман муҳандис шудан мехоҳам."),
            DialogueLine("Teacher", "That's a great dream! Do you plan to apply for a scholarship?", "Ин орзуи бузург аст! Барои стипендия ариза медиҳед?"),
            DialogueLine("Firuz", "Yes, I want to achieve my goal.", "Ҳа, ман мехоҳам ба ҳадафи худ ноил шавам."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w12_1", "Graduate", "Хатм кардан", "Grad-u-ate", "I will graduate next year", "Ман соли оянда хатм мекунам", "es_m3_l12"),
        WordItem("es_w12_2", "Degree", "Дараҷа", "De-gree", "I want to get a degree", "Ман дараҷа гирифтан мехоҳам", "es_m3_l12"),
        WordItem("es_w12_3", "Career", "Касб", "Ca-reer", "I want a good career", "Ман касби хуб мехоҳам", "es_m3_l12"),
        WordItem("es_w12_4", "Apply", "Ариза додан", "Ap-ply", "I will apply to the university", "Ман ба донишгоҳ ариза медиҳам", "es_m3_l12"),
        WordItem("es_w12_5", "Scholarship", "Стипендия", "Schol-ar-ship", "I got a scholarship", "Ман стипендия гирифтам", "es_m3_l12"),
        WordItem("es_w12_6", "Dream", "Орзу", "Dream", "My dream is to be a doctor", "Орзуи ман духтур шудан аст", "es_m3_l12"),
        WordItem("es_w12_7", "Plan", "Нақша", "Plan", "What is your plan?", "Нақшаи шумо чист?", "es_m3_l12"),
        WordItem("es_w12_8", "Achieve", "Ноил шудан", "A-chieve", "I want to achieve my goal", "Ман мехоҳам ба ҳадафам расам", "es_m3_l12"),
    ),
    grammarTip = GrammarTip(
        "I want to become... / After graduation I will...",
        "Барои касби оянда аз «I want to become...» ва барои нақшаҳо аз «After graduation I will...» истифода баред.",
        listOf("I want to become an engineer.", "After graduation I will work.", "I plan to apply for a scholarship."),
    ),
    exercises = listOf(
        Exercise("es_e12_1", ExerciseType.MULTIPLE_CHOICE, "«Graduate» чӣ маъно дорад?", "Graduate = ...", listOf("Ариза додан", "Хатм кардан", "Орзу дидан", "Ноил шудан"), "Хатм кардан", 1, "Graduate — Хатм кардан"),
        Exercise("es_e12_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I want to _____ my goal.", listOf("plan", "dream", "achieve", "apply"), "achieve", 2, "I want to achieve — Ман мехоҳам ноил шавам"),
        Exercise("es_e12_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман стипендия гирифтам»-ро интихоб кунед:", null, listOf("I applied for a scholarship", "I got a scholarship", "I need a scholarship", "I want a scholarship"), "I got a scholarship", 1, "Ман стипендия гирифтам = I got a scholarship"),
        Exercise("es_e12_4", ExerciseType.TYPE_ANSWER, "«Орзу»-ро ба англисӣ нависед:", "Орзу = ?", null, "Dream", null, "Орзу — Dream"),
        Exercise("es_e12_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Graduate" to "Хатм кардан", "Career" to "Касб", "Scholarship" to "Стипендия", "Achieve" to "Ноил шудан")),
        Exercise("es_e12_6", ExerciseType.MULTIPLE_CHOICE, "«Apply» чӣ маъно дорад?", "Apply = ...", listOf("Хатм кардан", "Нақша кашидан", "Ариза додан", "Орзу дидан"), "Ариза додан", 2, "Apply — Ариза додан"),
        Exercise("es_e12_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман муҳандис шудан мехоҳам»", null, null, "I want to become an engineer", null, "I want to become + касб", words = listOf("engineer", "an", "become", "to", "want", "I")),
        Exercise("es_e12_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTeacher: What do you want to become?\nFiruz: _____", null, listOf("I got a scholarship.", "I want to become an engineer.", "My dream is to graduate."), "I want to become an engineer.", 1, "Ҷавоб дар бораи касби оянда"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE & COURSE DEFINITIONS
// ═══════════════════════════════════════════════════

private val esModule1 = Module(
    id = "es_m1", courseId = "study_english",
    title = "Дар донишгоҳ",
    description = "Рӯзи аввал, дарсҳо, китобхона ва имтиҳон",
    emoji = "\uD83C\uDF93", orderIndex = 0,
    lessons = listOf(esM1L1, esM1L2, esM1L3, esM1L4),
)

private val esModule2 = Module(
    id = "es_m2", courseId = "study_english",
    title = "Ҳаёти донишҷӯӣ",
    description = "Хобгоҳ, хӯрок, дӯстон ва варзиш",
    emoji = "\uD83C\uDFE0", orderIndex = 1,
    lessons = listOf(esM2L5, esM2L6, esM2L7, esM2L8),
)

private val esModule3 = Module(
    id = "es_m3", courseId = "study_english",
    title = "Маҳорати таҳсилӣ",
    description = "Иншо, презентатсия, кори гурӯҳӣ ва оянда",
    emoji = "\uD83D\uDCDD", orderIndex = 2,
    lessons = listOf(esM3L9, esM3L10, esM3L11, esM3L12),
)

val studyEnglishCourse = Course(
    id = "study_english",
    title = "Англисӣ барои таҳсил",
    description = "Забони англисӣ барои донишгоҳ: дарсҳо, ҳаёти донишҷӯӣ, маҳорат",
    emoji = "\uD83C\uDF93",
    goalType = "study",
    language = "english",
    modules = listOf(
        esModule1, esModule2, esModule3,
        esModule4, esModule5, esModule6, esModule7, esModule8, esModule9, esModule10,
    ),
)
