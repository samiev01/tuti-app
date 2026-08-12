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
//  MODULE 1 · ШИНОСОӢ ДАР КОР  (Introduction at Work)
// ═══════════════════════════════════════════════════

// ── Lesson 1: Саломдиҳӣ (Greetings) ─────────────

private val engM1L1 = Lesson(
    id = "ew_m1_l1", moduleId = "ew_m1",
    title = "Саломдиҳӣ", description = "Саломдиҳӣ дар муҳити корӣ",
    emoji = "\uD83D\uDC4B", orderIndex = 0,
    dialogue = Dialogue(
        "Дар офис",
        listOf(
            DialogueLine("John", "Hello! Nice to meet you.", "Салом! Аз вохӯрӣ хурсандам."),
            DialogueLine("Firuz", "Hello! Nice to meet you too.", "Салом! Ман ҳам хурсандам."),
            DialogueLine("John", "What is your name?", "Номи шумо чист?"),
            DialogueLine("Firuz", "My name is Firuz.", "Номи ман Фирӯз."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w1_1", "Hello", "Салом", "Hel-lo", "Hello, everyone!", "Салом, ҳама!", "ew_m1_l1"),
        WordItem("ew_w1_2", "Name", "Ном", "Name", "My name is Firuz", "Номи ман Фирӯз", "ew_m1_l1"),
        WordItem("ew_w1_3", "Meet", "Вохӯрдан", "Meet", "Nice to meet you", "Аз вохӯрӣ хурсандам", "ew_m1_l1"),
        WordItem("ew_w1_4", "Nice", "Хуб", "Nice", "That is nice", "Ин хуб аст", "ew_m1_l1"),
        WordItem("ew_w1_5", "Please", "Лутфан", "Please", "Please sit down", "Лутфан шинед", "ew_m1_l1"),
        WordItem("ew_w1_6", "Thank you", "Ташаккур", "Thank you", "Thank you very much", "Хеле ташаккур", "ew_m1_l1"),
        WordItem("ew_w1_7", "Yes", "Ҳа", "Yes", "Yes, I agree", "Ҳа, ман розӣ", "ew_m1_l1"),
        WordItem("ew_w1_8", "No", "Не", "No", "No, thank you", "Не, ташаккур", "ew_m1_l1"),
    ),
    grammarTip = GrammarTip(
        "My name is...",
        "Барои муаррифии худ аз «My name is» + номи худ истифода баред. «Nice to meet you» барои саломдиҳӣ истифода мешавад.",
        listOf("My name is Firuz.", "Nice to meet you.", "What is your name?"),
    ),
    exercises = listOf(
        Exercise("ew_e1_1", ExerciseType.MULTIPLE_CHOICE, "«Hello» чӣ маъно дорад?", "Hello = ...", listOf("Хайр", "Салом", "Ташаккур", "Лутфан"), "Салом", 1, "Hello — Салом"),
        Exercise("ew_e1_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "My _____ is Firuz.", listOf("job", "name", "meet", "nice"), "name", 1, "My name is — Номи ман"),
        Exercise("ew_e1_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Аз вохӯрӣ хурсандам»-ро интихоб кунед:", null, listOf("Thank you very much", "Nice to meet you", "What is your name?", "Please sit down"), "Nice to meet you", 1, "Аз вохӯрӣ хурсандам = Nice to meet you"),
        Exercise("ew_e1_4", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Hello" to "Салом", "Thank you" to "Ташаккур", "Yes" to "Ҳа", "No" to "Не")),
        Exercise("ew_e1_5", ExerciseType.TYPE_ANSWER, "«Ташаккур»-ро ба англисӣ нависед:", "Ташаккур = ?", null, "Thank you", null, "Ташаккур — Thank you"),
        Exercise("ew_e1_6", ExerciseType.MULTIPLE_CHOICE, "«Please» чӣ маъно дорад?", "Please = ...", listOf("Не", "Ҳа", "Лутфан", "Салом"), "Лутфан", 2, "Please — Лутфан"),
        Exercise("ew_e1_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Номи ман Фирӯз»", null, null, "My name is Firuz", null, "My name is + ном", words = listOf("Firuz", "is", "name", "My")),
        Exercise("ew_e1_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро дар муколама пур кунед:\nJohn: Hello! What is your name?\nFiruz: _____", null, listOf("Nice to meet you!", "My name is Firuz.", "Thank you!"), "My name is Firuz.", 1, "Бо «My name is + ном» ҷавоб медиҳем"),
    ),
)

// ── Lesson 2: Кори ман (My Job) ──────────────────

private val engM1L2 = Lesson(
    id = "ew_m1_l2", moduleId = "ew_m1",
    title = "Кори ман", description = "Дар бораи касби худ нақл кунед",
    emoji = "\uD83D\uDCBC", orderIndex = 1,
    dialogue = Dialogue(
        "Дар бораи кор",
        listOf(
            DialogueLine("Sarah", "Where do you work?", "Шумо дар куҷо кор мекунед?"),
            DialogueLine("Firuz", "I work at a company.", "Ман дар як ширкат кор мекунам."),
            DialogueLine("Sarah", "What is your job?", "Касби шумо чист?"),
            DialogueLine("Firuz", "I am an employee in the office.", "Ман дар офис коргар ҳастам."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w2_1", "Work", "Кор", "Work", "I work here", "Ман дар инҷо кор мекунам", "ew_m1_l2"),
        WordItem("ew_w2_2", "Job", "Касб", "Job", "I like my job", "Ман касби худро дӯст медорам", "ew_m1_l2"),
        WordItem("ew_w2_3", "Office", "Офис", "Of-fice", "The office is big", "Офис калон аст", "ew_m1_l2"),
        WordItem("ew_w2_4", "Boss", "Роҳбар", "Boss", "My boss is kind", "Роҳбари ман меҳрубон аст", "ew_m1_l2"),
        WordItem("ew_w2_5", "Team", "Гурӯҳ", "Team", "Our team is good", "Гурӯҳи мо хуб аст", "ew_m1_l2"),
        WordItem("ew_w2_6", "Company", "Ширкат", "Com-pa-ny", "A big company", "Ширкати калон", "ew_m1_l2"),
        WordItem("ew_w2_7", "Employee", "Коргар", "Em-ploy-ee", "He is an employee", "Вай коргар аст", "ew_m1_l2"),
        WordItem("ew_w2_8", "Salary", "Маош", "Sal-a-ry", "Good salary", "Маоши хуб", "ew_m1_l2"),
    ),
    grammarTip = GrammarTip(
        "I work at... / I am a...",
        "Барои гуфтани ҷои кор аз «I work at» истифода баред. Барои гуфтани касб аз «I am a/an» истифода баред.",
        listOf("I work at a company.", "I am an engineer.", "I am a teacher."),
    ),
    exercises = listOf(
        Exercise("ew_e2_1", ExerciseType.MULTIPLE_CHOICE, "«Work» чӣ маъно дорад?", "Work = ...", listOf("Касб", "Кор", "Офис", "Маош"), "Кор", 1, "Work — Кор"),
        Exercise("ew_e2_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I work _____ a company.", listOf("in", "on", "at", "to"), "at", 2, "I work at — Ман кор мекунам дар"),
        Exercise("ew_e2_3", ExerciseType.TYPE_ANSWER, "«Офис»-ро ба англисӣ нависед:", "Офис = ?", null, "Office", null, "Офис — Office"),
        Exercise("ew_e2_4", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман дар инҷо кор мекунам»-ро интихоб кунед:", null, listOf("I am here", "I work here", "I like here", "I go here"), "I work here", 1, "Ман дар инҷо кор мекунам = I work here"),
        Exercise("ew_e2_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Boss" to "Роҳбар", "Team" to "Гурӯҳ", "Salary" to "Маош", "Company" to "Ширкат")),
        Exercise("ew_e2_6", ExerciseType.MULTIPLE_CHOICE, "«Employee» чӣ маъно дорад?", "Employee = ...", listOf("Роҳбар", "Ширкат", "Коргар", "Гурӯҳ"), "Коргар", 2, "Employee — Коргар"),
        Exercise("ew_e2_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман дар офис кор мекунам»", null, null, "I work at the office", null, "I work at + ҷой", words = listOf("office", "the", "work", "at", "I")),
        Exercise("ew_e2_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nSarah: What is your job?\nFiruz: _____", null, listOf("My name is Firuz.", "I am an employee.", "Thank you."), "I am an employee.", 1, "Бо «I am a/an + касб» ҷавоб медиҳем"),
    ),
)

// ── Lesson 3: Рӯзи корӣ (Work Day) ──────────────

private val engM1L3 = Lesson(
    id = "ew_m1_l3", moduleId = "ew_m1",
    title = "Рӯзи корӣ", description = "Тартиби рӯзи корӣ",
    emoji = "\u2600\uFE0F", orderIndex = 2,
    dialogue = Dialogue(
        "Рӯзи корӣ",
        listOf(
            DialogueLine("Firuz", "I start work in the morning.", "Ман субҳ корро оғоз мекунам."),
            DialogueLine("Sarah", "Do you have a meeting today?", "Имрӯз ҷаласа доред?"),
            DialogueLine("Firuz", "Yes, I have a meeting at ten.", "Ҳа, дар соати даҳ ҷаласа дорам."),
            DialogueLine("Sarah", "I finish work at five.", "Ман дар соати панҷ корро тамом мекунам."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w3_1", "Morning", "Субҳ", "Morn-ing", "Good morning!", "Субҳ ба хайр!", "ew_m1_l3"),
        WordItem("ew_w3_2", "Start", "Оғоз", "Start", "I start at eight", "Ман дар соати ҳашт оғоз мекунам", "ew_m1_l3"),
        WordItem("ew_w3_3", "Finish", "Тамом", "Fin-ish", "I finish at five", "Ман дар соати панҷ тамом мекунам", "ew_m1_l3"),
        WordItem("ew_w3_4", "Meeting", "Ҷаласа", "Meet-ing", "We have a meeting", "Мо ҷаласа дорем", "ew_m1_l3"),
        WordItem("ew_w3_5", "Break", "Танаффус", "Break", "Time for a break", "Вақти танаффус", "ew_m1_l3"),
        WordItem("ew_w3_6", "Lunch", "Хӯроки нисфирӯзӣ", "Lunch", "Lunch at twelve", "Хӯроки нисфирӯзӣ дар соати дувоздаҳ", "ew_m1_l3"),
        WordItem("ew_w3_7", "Email", "Почта", "E-mail", "I check my email", "Ман почтаро тафтиш мекунам", "ew_m1_l3"),
        WordItem("ew_w3_8", "Phone", "Телефон", "Phone", "Answer the phone", "Ба телефон ҷавоб диҳед", "ew_m1_l3"),
    ),
    grammarTip = GrammarTip(
        "I start/finish work at...",
        "Барои гуфтани вақти кор аз «I start/finish work at + вақт» истифода баред.",
        listOf("I start work at eight.", "I finish work at five.", "I have a meeting at ten."),
    ),
    exercises = listOf(
        Exercise("ew_e3_1", ExerciseType.MULTIPLE_CHOICE, "«Morning» чӣ маъно дорад?", "Morning = ...", listOf("Шаб", "Бегоҳ", "Субҳ", "Рӯз"), "Субҳ", 2, "Morning — Субҳ"),
        Exercise("ew_e3_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I _____ work at eight.", listOf("finish", "have", "start", "go"), "start", 2, "I start work at — Ман корро оғоз мекунам дар"),
        Exercise("ew_e3_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Мо ҷаласа дорем»-ро интихоб кунед:", null, listOf("We start work", "We have a meeting", "We finish work", "We have lunch"), "We have a meeting", 1, "Мо ҷаласа дорем = We have a meeting"),
        Exercise("ew_e3_4", ExerciseType.TYPE_ANSWER, "«Танаффус»-ро ба англисӣ нависед:", "Танаффус = ?", null, "Break", null, "Танаффус — Break"),
        Exercise("ew_e3_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Start" to "Оғоз", "Finish" to "Тамом", "Lunch" to "Хӯроки нисфирӯзӣ", "Phone" to "Телефон")),
        Exercise("ew_e3_6", ExerciseType.MULTIPLE_CHOICE, "«Email» чӣ маъно дорад?", "Email = ...", listOf("Телефон", "Почта", "Ҷаласа", "Танаффус"), "Почта", 1, "Email — Почта"),
        Exercise("ew_e3_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман дар соати панҷ корро тамом мекунам»", null, null, "I finish work at five", null, "I finish work at + вақт", words = listOf("five", "at", "finish", "work", "I")),
        Exercise("ew_e3_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Meet-ing", listOf("Morning", "Meeting", "Lunch", "Email"), "Meeting", 1, "Meeting — Ҷаласа"),
    ),
)

// ── Lesson 4: Ҳамкорон (Colleagues) ─────────────

private val engM1L4 = Lesson(
    id = "ew_m1_l4", moduleId = "ew_m1",
    title = "Ҳамкорон", description = "Бо ҳамкорон гап задан",
    emoji = "\uD83E\uDD1D", orderIndex = 3,
    dialogue = Dialogue(
        "Бо ҳамкорон",
        listOf(
            DialogueLine("Firuz", "Can you help me?", "Шумо метавонед ба ман кӯмак кунед?"),
            DialogueLine("Tom", "Of course! What do you need?", "Албатта! Чӣ лозим?"),
            DialogueLine("Firuz", "I don't understand this.", "Ман инро намефаҳмам."),
            DialogueLine("Tom", "Let me explain. Listen carefully.", "Бигзоред шарҳ диҳам. Бодиққат гӯш кунед."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w4_1", "Colleague", "Ҳамкор", "Col-league", "My colleague is helpful", "Ҳамкори ман кӯмакрасон аст", "ew_m1_l4"),
        WordItem("ew_w4_2", "Friend", "Дӯст", "Friend", "He is my friend", "Вай дӯсти ман аст", "ew_m1_l4"),
        WordItem("ew_w4_3", "Help", "Кӯмак", "Help", "Can you help me?", "Метавонед кӯмак кунед?", "ew_m1_l4"),
        WordItem("ew_w4_4", "Ask", "Пурсидан", "Ask", "I want to ask", "Ман пурсидан мехоҳам", "ew_m1_l4"),
        WordItem("ew_w4_5", "Answer", "Ҷавоб", "An-swer", "Please answer me", "Лутфан ба ман ҷавоб диҳед", "ew_m1_l4"),
        WordItem("ew_w4_6", "Understand", "Фаҳмидан", "Un-der-stand", "I understand now", "Ман ҳоло мефаҳмам", "ew_m1_l4"),
        WordItem("ew_w4_7", "Speak", "Гап задан", "Speak", "Please speak slowly", "Лутфан оҳиста гап занед", "ew_m1_l4"),
        WordItem("ew_w4_8", "Listen", "Гӯш кардан", "Lis-ten", "Listen carefully", "Бодиққат гӯш кунед", "ew_m1_l4"),
    ),
    grammarTip = GrammarTip(
        "Can you help me? / I don't understand",
        "Барои дархости кӯмак аз «Can you help me?» ва барои нафаҳмидан аз «I don't understand» истифода баред.",
        listOf("Can you help me?", "I don't understand.", "Please speak slowly."),
    ),
    exercises = listOf(
        Exercise("ew_e4_1", ExerciseType.MULTIPLE_CHOICE, "«Colleague» чӣ маъно дорад?", "Colleague = ...", listOf("Дӯст", "Роҳбар", "Ҳамкор", "Коргар"), "Ҳамкор", 2, "Colleague — Ҳамкор"),
        Exercise("ew_e4_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I don't _____ this.", listOf("speak", "help", "ask", "understand"), "understand", 3, "I don't understand — Ман намефаҳмам"),
        Exercise("ew_e4_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Шумо метавонед ба ман кӯмак кунед?»-ро интихоб кунед:", null, listOf("Can I help you?", "Can you help me?", "Do you understand?", "Please listen"), "Can you help me?", 1, "Шумо метавонед ба ман кӯмак кунед? = Can you help me?"),
        Exercise("ew_e4_4", ExerciseType.TYPE_ANSWER, "«Гӯш кардан»-ро ба англисӣ нависед:", "Гӯш кардан = ?", null, "Listen", null, "Гӯш кардан — Listen"),
        Exercise("ew_e4_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Help" to "Кӯмак", "Ask" to "Пурсидан", "Speak" to "Гап задан", "Listen" to "Гӯш кардан")),
        Exercise("ew_e4_6", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Col-league", listOf("Friend", "Colleague", "Answer", "Help"), "Colleague", 1, "Colleague — Ҳамкор"),
        Exercise("ew_e4_7", ExerciseType.MULTIPLE_CHOICE, "«Speak» чӣ маъно дорад?", "Speak = ...", listOf("Гӯш кардан", "Фаҳмидан", "Пурсидан", "Гап задан"), "Гап задан", 3, "Speak — Гап задан"),
        Exercise("ew_e4_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Can you help me?\nTom: _____", null, listOf("I don't understand.", "Of course!", "Goodbye!"), "Of course!", 1, "Ҷавоби дархости кӯмак"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 2 · МУОШИРАТИ КОРӢ  (Work Communication)
// ═══════════════════════════════════════════════════

// ── Lesson 5: Телефонӣ гап задан (Phone Calls) ──

private val engM2L5 = Lesson(
    id = "ew_m2_l5", moduleId = "ew_m2",
    title = "Телефонӣ гап задан", description = "Дар телефон гап задан",
    emoji = "\uD83D\uDCDE", orderIndex = 0,
    dialogue = Dialogue(
        "Занги телефонӣ",
        listOf(
            DialogueLine("Firuz", "Hello, can I speak to Mr. Smith?", "Салом, метавонам бо ҷаноби Смит гап занам?"),
            DialogueLine("Secretary", "Please hold.", "Лутфан интизор шавед."),
            DialogueLine("Secretary", "Sorry, he is not here. Can I take a message?", "Бубахшед, вай нест. Паём гузорам?"),
            DialogueLine("Firuz", "Yes, please. My number is 555-1234.", "Ҳа, лутфан. Рақами ман 555-1234."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w5_1", "Call", "Занг", "Call", "I need to make a call", "Ман бояд занг занам", "ew_m2_l5"),
        WordItem("ew_w5_2", "Answer", "Ҷавоб додан", "An-swer", "Please answer the phone", "Лутфан ба телефон ҷавоб диҳед", "ew_m2_l5"),
        WordItem("ew_w5_3", "Hold", "Интизор", "Hold", "Please hold", "Лутфан интизор шавед", "ew_m2_l5"),
        WordItem("ew_w5_4", "Message", "Паём", "Mes-sage", "Leave a message", "Паём гузоред", "ew_m2_l5"),
        WordItem("ew_w5_5", "Number", "Рақам", "Num-ber", "What is your number?", "Рақами шумо чист?", "ew_m2_l5"),
        WordItem("ew_w5_6", "Sorry", "Бубахшед", "Sor-ry", "Sorry, I can't hear you", "Бубахшед, шуморо намешунавам", "ew_m2_l5"),
        WordItem("ew_w5_7", "Repeat", "Такрор", "Re-peat", "Could you repeat that?", "Метавонед такрор кунед?", "ew_m2_l5"),
        WordItem("ew_w5_8", "Slowly", "Оҳиста", "Slow-ly", "Please speak slowly", "Лутфан оҳиста гап занед", "ew_m2_l5"),
    ),
    grammarTip = GrammarTip(
        "Can I speak to...? / Could you repeat?",
        "Барои гап задан бо касе аз «Can I speak to...?» ва барои дархости такрор аз «Could you repeat that?» истифода баред.",
        listOf("Can I speak to Mr. Smith?", "Could you repeat that?", "Please speak slowly."),
    ),
    exercises = listOf(
        Exercise("ew_e5_1", ExerciseType.MULTIPLE_CHOICE, "«Call» чӣ маъно дорад?", "Call = ...", listOf("Паём", "Рақам", "Занг", "Интизор"), "Занг", 2, "Call — Занг"),
        Exercise("ew_e5_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Could you _____ that?", listOf("hold", "call", "repeat", "answer"), "repeat", 2, "Could you repeat — Метавонед такрор кунед"),
        Exercise("ew_e5_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Лутфан интизор шавед»-ро интихоб кунед:", null, listOf("Please call me", "Please hold", "Please repeat", "Please answer"), "Please hold", 1, "Лутфан интизор шавед = Please hold"),
        Exercise("ew_e5_4", ExerciseType.TYPE_ANSWER, "«Паём»-ро ба англисӣ нависед:", "Паём = ?", null, "Message", null, "Паём — Message"),
        Exercise("ew_e5_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Call" to "Занг", "Sorry" to "Бубахшед", "Slowly" to "Оҳиста", "Number" to "Рақам")),
        Exercise("ew_e5_6", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Mes-sage", listOf("Number", "Message", "Answer", "Call"), "Message", 1, "Message — Паём"),
        Exercise("ew_e5_7", ExerciseType.MULTIPLE_CHOICE, "«Slowly» чӣ маъно дорад?", "Slowly = ...", listOf("Тез", "Оҳиста", "Баланд", "Паст"), "Оҳиста", 1, "Slowly — Оҳиста"),
        Exercise("ew_e5_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Лутфан оҳиста гап занед»", null, null, "Please speak slowly", null, "Please speak slowly", words = listOf("slowly", "speak", "Please")),
    ),
)

// ── Lesson 6: Почтаи электронӣ (Email) ──────────

private val engM2L6 = Lesson(
    id = "ew_m2_l6", moduleId = "ew_m2",
    title = "Почтаи электронӣ", description = "Навиштани почтаи электронӣ",
    emoji = "\uD83D\uDCE7", orderIndex = 1,
    dialogue = Dialogue(
        "Почтаи электронӣ",
        listOf(
            DialogueLine("Firuz", "I need to send an email.", "Ман бояд почта фиристам."),
            DialogueLine("Sarah", "What is the subject?", "Мавзӯъ чист?"),
            DialogueLine("Firuz", "It is about the meeting.", "Дар бораи ҷаласа аст."),
            DialogueLine("Sarah", "Don't forget to attach the file.", "Замимаи файлро фаромӯш накунед."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w6_1", "Send", "Фиристодан", "Send", "Send the email now", "Ҳоло почтаро фиристед", "ew_m2_l6"),
        WordItem("ew_w6_2", "Receive", "Гирифтан", "Re-ceive", "I received your email", "Ман почтаи шуморо гирифтам", "ew_m2_l6"),
        WordItem("ew_w6_3", "Subject", "Мавзӯъ", "Sub-ject", "What is the subject?", "Мавзӯъ чист?", "ew_m2_l6"),
        WordItem("ew_w6_4", "Dear", "Эҳтиромона", "Dear", "Dear Mr. Smith", "Ҷаноби муҳтарам Смит", "ew_m2_l6"),
        WordItem("ew_w6_5", "Regards", "Эҳтиром", "Re-gards", "Best regards", "Бо эҳтиром", "ew_m2_l6"),
        WordItem("ew_w6_6", "Attach", "Замима", "At-tach", "Please attach the file", "Лутфан файлро замима кунед", "ew_m2_l6"),
        WordItem("ew_w6_7", "Reply", "Ҷавоб", "Re-ply", "Please reply soon", "Лутфан зуд ҷавоб диҳед", "ew_m2_l6"),
        WordItem("ew_w6_8", "Forward", "Равон кардан", "For-ward", "Forward the email", "Почтаро равон кунед", "ew_m2_l6"),
    ),
    grammarTip = GrammarTip(
        "Dear Mr./Ms... / Best regards",
        "Дар оғози почта аз «Dear Mr./Ms. + ном» ва дар анҷом аз «Best regards» истифода баред.",
        listOf("Dear Mr. Smith,", "Best regards, Firuz", "Please find attached..."),
    ),
    exercises = listOf(
        Exercise("ew_e6_1", ExerciseType.MULTIPLE_CHOICE, "«Send» чӣ маъно дорад?", "Send = ...", listOf("Гирифтан", "Фиристодан", "Замима", "Ҷавоб"), "Фиристодан", 1, "Send — Фиристодан"),
        Exercise("ew_e6_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____ Mr. Smith,", listOf("Hello", "Dear", "Best", "Good"), "Dear", 1, "Dear + ном дар оғози почта"),
        Exercise("ew_e6_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Лутфан файлро замима кунед»-ро интихоб кунед:", null, listOf("Please send the file", "Please attach the file", "Please forward the file", "Please reply to the file"), "Please attach the file", 1, "Лутфан файлро замима кунед = Please attach the file"),
        Exercise("ew_e6_4", ExerciseType.TYPE_ANSWER, "«Мавзӯъ»-ро ба англисӣ нависед:", "Мавзӯъ = ?", null, "Subject", null, "Мавзӯъ — Subject"),
        Exercise("ew_e6_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Send" to "Фиристодан", "Receive" to "Гирифтан", "Reply" to "Ҷавоб", "Forward" to "Равон кардан")),
        Exercise("ew_e6_6", ExerciseType.MULTIPLE_CHOICE, "«Attach» чӣ маъно дорад?", "Attach = ...", listOf("Равон кардан", "Ҷавоб", "Замима", "Фиристодан"), "Замима", 2, "Attach — Замима"),
        Exercise("ew_e6_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Бо эҳтиром, Фирӯз»", null, null, "Best regards, Firuz", null, "Best regards дар анҷоми почта", words = listOf("Firuz", "regards,", "Best")),
        Exercise("ew_e6_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nSarah: Don't forget to attach the file.\nFiruz: _____", null, listOf("Best regards", "I will attach it now.", "Dear Mr. Smith"), "I will attach it now.", 1, "Ҷавоб дар бораи замима"),
    ),
)

// ── Lesson 7: Дар ҷаласа (In a Meeting) ─────────

private val engM2L7 = Lesson(
    id = "ew_m2_l7", moduleId = "ew_m2",
    title = "Дар ҷаласа", description = "Дар ҷаласа иштирок кардан",
    emoji = "\uD83D\uDCCB", orderIndex = 2,
    dialogue = Dialogue(
        "Дар ҷаласа",
        listOf(
            DialogueLine("Manager", "Let's discuss the plan.", "Биёед нақшаро муҳокима кунем."),
            DialogueLine("Firuz", "I have an idea.", "Ман як фикр дорам."),
            DialogueLine("Manager", "I agree. That is a good idea.", "Ман розӣ. Ин фикри хуб аст."),
            DialogueLine("Tom", "What is the decision?", "Қарор чист?"),
            DialogueLine("Manager", "We will follow the plan.", "Мо аз нақша пайравӣ мекунем."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w7_1", "Agenda", "Рӯйхат", "A-gen-da", "What is the agenda?", "Рӯйхат чист?", "ew_m2_l7"),
        WordItem("ew_w7_2", "Discuss", "Муҳокима", "Dis-cuss", "Let's discuss this", "Биёед инро муҳокима кунем", "ew_m2_l7"),
        WordItem("ew_w7_3", "Agree", "Розӣ", "A-gree", "I agree with you", "Ман бо шумо розӣ", "ew_m2_l7"),
        WordItem("ew_w7_4", "Disagree", "Норозӣ", "Dis-a-gree", "I disagree", "Ман норозӣ", "ew_m2_l7"),
        WordItem("ew_w7_5", "Idea", "Фикр", "I-de-a", "I have an idea", "Ман як фикр дорам", "ew_m2_l7"),
        WordItem("ew_w7_6", "Plan", "Нақша", "Plan", "What is the plan?", "Нақша чист?", "ew_m2_l7"),
        WordItem("ew_w7_7", "Decision", "Қарор", "De-ci-sion", "We need a decision", "Ба мо қарор лозим", "ew_m2_l7"),
        WordItem("ew_w7_8", "Result", "Натиҷа", "Re-sult", "The result is good", "Натиҷа хуб аст", "ew_m2_l7"),
    ),
    grammarTip = GrammarTip(
        "I think... / I agree/disagree",
        "Дар ҷаласа барои изҳори фикр аз «I think...» ва барои розигӣ/норозигӣ аз «I agree/disagree» истифода баред.",
        listOf("I think this is good.", "I agree with you.", "I disagree."),
    ),
    exercises = listOf(
        Exercise("ew_e7_1", ExerciseType.MULTIPLE_CHOICE, "«Agree» чӣ маъно дорад?", "Agree = ...", listOf("Норозӣ", "Фикр", "Розӣ", "Нақша"), "Розӣ", 2, "Agree — Розӣ"),
        Exercise("ew_e7_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I have an _____.", listOf("plan", "result", "idea", "agenda"), "idea", 2, "I have an idea — Ман як фикр дорам"),
        Exercise("ew_e7_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Биёед инро муҳокима кунем»-ро интихоб кунед:", null, listOf("Let's agree on this", "Let's discuss this", "Let's plan this", "Let's decide this"), "Let's discuss this", 1, "Биёед муҳокима кунем = Let's discuss this"),
        Exercise("ew_e7_4", ExerciseType.TYPE_ANSWER, "«Қарор»-ро ба англисӣ нависед:", "Қарор = ?", null, "Decision", null, "Қарор — Decision"),
        Exercise("ew_e7_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Agree" to "Розӣ", "Disagree" to "Норозӣ", "Plan" to "Нақша", "Result" to "Натиҷа")),
        Exercise("ew_e7_6", ExerciseType.MULTIPLE_CHOICE, "«Discuss» чӣ маъно дорад?", "Discuss = ...", listOf("Қарор", "Натиҷа", "Муҳокима", "Рӯйхат"), "Муҳокима", 2, "Discuss — Муҳокима"),
        Exercise("ew_e7_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A A-gen-da", listOf("Idea", "Decision", "Agenda", "Result"), "Agenda", 2, "Agenda — Рӯйхат"),
        Exercise("ew_e7_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман бо шумо розӣ ҳастам»", null, null, "I agree with you", null, "I agree with + кас", words = listOf("you", "with", "agree", "I")),
    ),
)

// ── Lesson 8: Мушкилот (Problems) ───────────────

private val engM2L8 = Lesson(
    id = "ew_m2_l8", moduleId = "ew_m2",
    title = "Мушкилот", description = "Дар бораи мушкилот гап задан",
    emoji = "\u26A0\uFE0F", orderIndex = 3,
    dialogue = Dialogue(
        "Мушкилот дар кор",
        listOf(
            DialogueLine("Firuz", "There is a problem with the project.", "Дар лоиҳа мушкилӣ ҳаст."),
            DialogueLine("Sarah", "What is the problem?", "Мушкилӣ чист?"),
            DialogueLine("Firuz", "We are late. The deadline is tomorrow.", "Мо дер кардем. Мӯҳлат пагоҳ аст."),
            DialogueLine("Sarah", "We need to fix it. It is urgent.", "Мо бояд ислоҳ кунем. Ин фаврӣ аст."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w8_1", "Problem", "Мушкилӣ", "Prob-lem", "There is a problem", "Мушкилӣ ҳаст", "ew_m2_l8"),
        WordItem("ew_w8_2", "Solution", "Ҳал", "So-lu-tion", "We need a solution", "Ба мо ҳал лозим", "ew_m2_l8"),
        WordItem("ew_w8_3", "Fix", "Ислоҳ", "Fix", "We need to fix it", "Мо бояд ислоҳ кунем", "ew_m2_l8"),
        WordItem("ew_w8_4", "Mistake", "Хато", "Mis-take", "I made a mistake", "Ман хато кардам", "ew_m2_l8"),
        WordItem("ew_w8_5", "Late", "Дер", "Late", "I am late", "Ман дер кардам", "ew_m2_l8"),
        WordItem("ew_w8_6", "Deadline", "Мӯҳлат", "Dead-line", "The deadline is Friday", "Мӯҳлат рӯзи ҷумъа аст", "ew_m2_l8"),
        WordItem("ew_w8_7", "Urgent", "Фаврӣ", "Ur-gent", "This is urgent", "Ин фаврӣ аст", "ew_m2_l8"),
        WordItem("ew_w8_8", "Important", "Муҳим", "Im-por-tant", "This is very important", "Ин хеле муҳим аст", "ew_m2_l8"),
    ),
    grammarTip = GrammarTip(
        "There is a problem with... / We need to fix...",
        "Барои гуфтани мушкилӣ аз «There is a problem with...» ва барои ислоҳ аз «We need to fix...» истифода баред.",
        listOf("There is a problem with the system.", "We need to fix this.", "It is urgent."),
    ),
    exercises = listOf(
        Exercise("ew_e8_1", ExerciseType.MULTIPLE_CHOICE, "«Problem» чӣ маъно дорад?", "Problem = ...", listOf("Ҳал", "Хато", "Мушкилӣ", "Ислоҳ"), "Мушкилӣ", 2, "Problem — Мушкилӣ"),
        Exercise("ew_e8_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "We need to _____ it.", listOf("late", "urgent", "fix", "problem"), "fix", 2, "We need to fix — Мо бояд ислоҳ кунем"),
        Exercise("ew_e8_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ин хеле муҳим аст»-ро интихоб кунед:", null, listOf("This is very late", "This is very urgent", "This is very important", "This is a big mistake"), "This is very important", 2, "Ин хеле муҳим аст = This is very important"),
        Exercise("ew_e8_4", ExerciseType.TYPE_ANSWER, "«Мӯҳлат»-ро ба англисӣ нависед:", "Мӯҳлат = ?", null, "Deadline", null, "Мӯҳлат — Deadline"),
        Exercise("ew_e8_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Problem" to "Мушкилӣ", "Solution" to "Ҳал", "Mistake" to "Хато", "Urgent" to "Фаврӣ")),
        Exercise("ew_e8_6", ExerciseType.MULTIPLE_CHOICE, "«Late» чӣ маъно дорад?", "Late = ...", listOf("Фаврӣ", "Дер", "Муҳим", "Хато"), "Дер", 1, "Late — Дер"),
        Exercise("ew_e8_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Dead-line", listOf("Problem", "Solution", "Deadline", "Mistake"), "Deadline", 2, "Deadline — Мӯҳлат"),
        Exercise("ew_e8_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: There is a problem.\nSarah: _____", null, listOf("Best regards.", "What is the problem?", "Goodbye."), "What is the problem?", 1, "Савол дар бораи мушкилӣ"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 3 · МАҲОРАТИ ПЕШРАФТА  (Advanced Skills)
// ═══════════════════════════════════════════════════

// ── Lesson 9: Мусоҳиба (Job Interview) ──────────

private val engM3L9 = Lesson(
    id = "ew_m3_l9", moduleId = "ew_m3",
    title = "Мусоҳиба", description = "Дар мусоҳибаи корӣ",
    emoji = "\uD83C\uDFAF", orderIndex = 0,
    dialogue = Dialogue(
        "Мусоҳибаи корӣ",
        listOf(
            DialogueLine("HR", "Tell me about your experience.", "Дар бораи таҷрибаи худ гӯед."),
            DialogueLine("Firuz", "I have five years of experience.", "Ман панҷ сол таҷриба дорам."),
            DialogueLine("HR", "What are your strengths?", "Қувватҳои шумо чист?"),
            DialogueLine("Firuz", "I am hardworking and I learn fast.", "Ман серкор ва тезомӯз ҳастам."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w9_1", "Interview", "Мусоҳиба", "In-ter-view", "I have a job interview", "Ман мусоҳибаи корӣ дорам", "ew_m3_l9"),
        WordItem("ew_w9_2", "Experience", "Таҷриба", "Ex-pe-ri-ence", "I have experience", "Ман таҷриба дорам", "ew_m3_l9"),
        WordItem("ew_w9_3", "Skill", "Маҳорат", "Skill", "I have many skills", "Ман маҳоратҳои зиёд дорам", "ew_m3_l9"),
        WordItem("ew_w9_4", "Strength", "Қувват", "Strength", "What are your strengths?", "Қувватҳои шумо чист?", "ew_m3_l9"),
        WordItem("ew_w9_5", "Education", "Маълумот", "Ed-u-ca-tion", "I have higher education", "Ман маълумоти олӣ дорам", "ew_m3_l9"),
        WordItem("ew_w9_6", "Resume", "Резюме", "Re-su-me", "Here is my resume", "Ана резюмеи ман", "ew_m3_l9"),
        WordItem("ew_w9_7", "Hire", "Қабул кардан", "Hire", "We want to hire you", "Мо шуморо қабул кардан мехоҳем", "ew_m3_l9"),
        WordItem("ew_w9_8", "Position", "Мансаб", "Po-si-tion", "What position is this?", "Ин кадом мансаб аст?", "ew_m3_l9"),
    ),
    grammarTip = GrammarTip(
        "I have experience in... / My strengths are...",
        "Дар мусоҳиба барои гуфтани таҷриба аз «I have experience in...» ва барои қувватҳо аз «My strengths are...» истифода баред.",
        listOf("I have experience in marketing.", "My strengths are teamwork and communication.", "I have a degree in engineering."),
    ),
    exercises = listOf(
        Exercise("ew_e9_1", ExerciseType.MULTIPLE_CHOICE, "«Interview» чӣ маъно дорад?", "Interview = ...", listOf("Таҷриба", "Маҳорат", "Мусоҳиба", "Мансаб"), "Мусоҳиба", 2, "Interview — Мусоҳиба"),
        Exercise("ew_e9_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I have five years of _____.", listOf("skill", "strength", "experience", "education"), "experience", 2, "I have experience — Ман таҷриба дорам"),
        Exercise("ew_e9_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ана резюмеи ман»-ро интихоб кунед:", null, listOf("Here is my interview", "Here is my resume", "Here is my position", "Here is my skill"), "Here is my resume", 1, "Ана резюмеи ман = Here is my resume"),
        Exercise("ew_e9_4", ExerciseType.TYPE_ANSWER, "«Маҳорат»-ро ба англисӣ нависед:", "Маҳорат = ?", null, "Skill", null, "Маҳорат — Skill"),
        Exercise("ew_e9_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Interview" to "Мусоҳиба", "Experience" to "Таҷриба", "Resume" to "Резюме", "Hire" to "Қабул кардан")),
        Exercise("ew_e9_6", ExerciseType.MULTIPLE_CHOICE, "«Position» чӣ маъно дорад?", "Position = ...", listOf("Қувват", "Резюме", "Маълумот", "Мансаб"), "Мансаб", 3, "Position — Мансаб"),
        Exercise("ew_e9_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ex-pe-ri-ence", listOf("Education", "Experience", "Interview", "Strength"), "Experience", 1, "Experience — Таҷриба"),
        Exercise("ew_e9_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nHR: What are your strengths?\nFiruz: _____", null, listOf("Here is my resume.", "I am hardworking and I learn fast.", "I have a job interview."), "I am hardworking and I learn fast.", 1, "Ҷавоб дар бораи қувватҳо"),
    ),
)

// ── Lesson 10: Презентатсия (Presentation) ──────

private val engM3L10 = Lesson(
    id = "ew_m3_l10", moduleId = "ew_m3",
    title = "Презентатсия", description = "Презентатсия пешниҳод кардан",
    emoji = "\uD83D\uDCCA", orderIndex = 1,
    dialogue = Dialogue(
        "Презентатсия",
        listOf(
            DialogueLine("Firuz", "Let me show you the data.", "Бигзоред маълумотро нишон диҳам."),
            DialogueLine("Firuz", "This chart shows our results.", "Ин диаграмма натиҷаҳои моро нишон медиҳад."),
            DialogueLine("Manager", "Can you explain this slide?", "Метавонед ин слайдро шарҳ диҳед?"),
            DialogueLine("Firuz", "Of course. Any questions?", "Албатта. Саволе ҳаст?"),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w10_1", "Present", "Пешниҳод", "Pre-sent", "I will present today", "Ман имрӯз пешниҳод мекунам", "ew_m3_l10"),
        WordItem("ew_w10_2", "Slide", "Слайд", "Slide", "Next slide, please", "Слайди навбатӣ, лутфан", "ew_m3_l10"),
        WordItem("ew_w10_3", "Show", "Нишон додан", "Show", "Let me show you", "Бигзоред нишон диҳам", "ew_m3_l10"),
        WordItem("ew_w10_4", "Explain", "Шарҳ додан", "Ex-plain", "Can you explain?", "Метавонед шарҳ диҳед?", "ew_m3_l10"),
        WordItem("ew_w10_5", "Question", "Савол", "Ques-tion", "Any questions?", "Саволе ҳаст?", "ew_m3_l10"),
        WordItem("ew_w10_6", "Chart", "Диаграмма", "Chart", "Look at the chart", "Ба диаграмма нигаред", "ew_m3_l10"),
        WordItem("ew_w10_7", "Data", "Маълумот", "Da-ta", "The data shows growth", "Маълумот афзоишро нишон медиҳад", "ew_m3_l10"),
        WordItem("ew_w10_8", "Audience", "Шунавандагон", "Au-di-ence", "The audience is big", "Шунавандагон зиёданд", "ew_m3_l10"),
    ),
    grammarTip = GrammarTip(
        "Let me show you... / Any questions?",
        "Дар презентатсия барои нишон додан аз «Let me show you...» ва барои саволҳо аз «Any questions?» истифода баред.",
        listOf("Let me show you the data.", "Any questions?", "As you can see on this chart..."),
    ),
    exercises = listOf(
        Exercise("ew_e10_1", ExerciseType.MULTIPLE_CHOICE, "«Show» чӣ маъно дорад?", "Show = ...", listOf("Шарҳ додан", "Нишон додан", "Пурсидан", "Пешниҳод"), "Нишон додан", 1, "Show — Нишон додан"),
        Exercise("ew_e10_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Let me _____ you the chart.", listOf("explain", "show", "ask", "present"), "show", 1, "Let me show you — Бигзоред нишон диҳам"),
        Exercise("ew_e10_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Саволе ҳаст?»-ро интихоб кунед:", null, listOf("What is the question?", "I have a question", "Any questions?", "No questions"), "Any questions?", 2, "Саволе ҳаст? = Any questions?"),
        Exercise("ew_e10_4", ExerciseType.TYPE_ANSWER, "«Диаграмма»-ро ба англисӣ нависед:", "Диаграмма = ?", null, "Chart", null, "Диаграмма — Chart"),
        Exercise("ew_e10_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Show" to "Нишон додан", "Explain" to "Шарҳ додан", "Question" to "Савол", "Data" to "Маълумот")),
        Exercise("ew_e10_6", ExerciseType.MULTIPLE_CHOICE, "«Audience» чӣ маъно дорад?", "Audience = ...", listOf("Слайд", "Маълумот", "Шунавандагон", "Савол"), "Шунавандагон", 2, "Audience — Шунавандагон"),
        Exercise("ew_e10_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Бигзоред маълумотро нишон диҳам»", null, null, "Let me show you the data", null, "Let me show you + чиз", words = listOf("data", "the", "you", "show", "me", "Let")),
        Exercise("ew_e10_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Pre-sent", listOf("Explain", "Present", "Question", "Chart"), "Present", 1, "Present — Пешниҳод"),
    ),
)

// ── Lesson 11: Музокирот (Negotiations) ─────────

private val engM3L11 = Lesson(
    id = "ew_m3_l11", moduleId = "ew_m3",
    title = "Музокирот", description = "Музокира бурдан",
    emoji = "\uD83E\uDD1D", orderIndex = 2,
    dialogue = Dialogue(
        "Музокирот",
        listOf(
            DialogueLine("Client", "What can you offer?", "Шумо чӣ пешниҳод карда метавонед?"),
            DialogueLine("Firuz", "We can offer a good price.", "Мо нархи хуб пешниҳод карда метавонем."),
            DialogueLine("Client", "I accept these conditions.", "Ман ин шартҳоро қабул мекунам."),
            DialogueLine("Firuz", "Great! Let's sign the contract.", "Олӣ! Биёед шартномаро имзо кунем."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w11_1", "Negotiate", "Музокира", "Ne-go-ti-ate", "Let's negotiate", "Биёед музокира кунем", "ew_m3_l11"),
        WordItem("ew_w11_2", "Offer", "Пешниҳод", "Of-fer", "We can offer this", "Мо инро пешниҳод карда метавонем", "ew_m3_l11"),
        WordItem("ew_w11_3", "Accept", "Қабул", "Ac-cept", "I accept the offer", "Ман пешниҳодро қабул мекунам", "ew_m3_l11"),
        WordItem("ew_w11_4", "Reject", "Рад", "Re-ject", "I reject this price", "Ман ин нархро рад мекунам", "ew_m3_l11"),
        WordItem("ew_w11_5", "Price", "Нарх", "Price", "What is the price?", "Нарх чист?", "ew_m3_l11"),
        WordItem("ew_w11_6", "Contract", "Шартнома", "Con-tract", "Sign the contract", "Шартномаро имзо кунед", "ew_m3_l11"),
        WordItem("ew_w11_7", "Condition", "Шарт", "Con-di-tion", "These are our conditions", "Ин шартҳои мо аст", "ew_m3_l11"),
        WordItem("ew_w11_8", "Deal", "Созиш", "Deal", "We have a deal", "Мо созиш дорем", "ew_m3_l11"),
    ),
    grammarTip = GrammarTip(
        "We can offer... / That's acceptable",
        "Дар музокирот барои пешниҳод аз «We can offer...» ва барои қабулӣ аз «That's acceptable» истифода баред.",
        listOf("We can offer a good price.", "That's acceptable.", "Let's sign the contract."),
    ),
    exercises = listOf(
        Exercise("ew_e11_1", ExerciseType.MULTIPLE_CHOICE, "«Offer» чӣ маъно дорад?", "Offer = ...", listOf("Қабул", "Рад", "Пешниҳод", "Созиш"), "Пешниҳод", 2, "Offer — Пешниҳод"),
        Exercise("ew_e11_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I _____ these conditions.", listOf("reject", "offer", "accept", "negotiate"), "accept", 2, "I accept — Ман қабул мекунам"),
        Exercise("ew_e11_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Нарх чист?»-ро интихоб кунед:", null, listOf("What is the deal?", "What is the contract?", "What is the condition?", "What is the price?"), "What is the price?", 3, "Нарх чист? = What is the price?"),
        Exercise("ew_e11_4", ExerciseType.TYPE_ANSWER, "«Шартнома»-ро ба англисӣ нависед:", "Шартнома = ?", null, "Contract", null, "Шартнома — Contract"),
        Exercise("ew_e11_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Offer" to "Пешниҳод", "Accept" to "Қабул", "Reject" to "Рад", "Deal" to "Созиш")),
        Exercise("ew_e11_6", ExerciseType.MULTIPLE_CHOICE, "«Contract» чӣ маъно дорад?", "Contract = ...", listOf("Нарх", "Шартнома", "Шарт", "Созиш"), "Шартнома", 1, "Contract — Шартнома"),
        Exercise("ew_e11_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Мо нархи хуб пешниҳод мекунем»", null, null, "We can offer a good price", null, "We can offer + чиз", words = listOf("price", "good", "a", "offer", "can", "We")),
        Exercise("ew_e11_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nClient: What can you offer?\nFiruz: _____", null, listOf("I reject this.", "We can offer a good price.", "Goodbye."), "We can offer a good price.", 1, "Пешниҳоди нархи хуб"),
    ),
)

// ── Lesson 12: Муваффақият (Success) ────────────

private val engM3L12 = Lesson(
    id = "ew_m3_l12", moduleId = "ew_m3",
    title = "Муваффақият", description = "Дар бораи муваффақият",
    emoji = "\uD83C\uDFC6", orderIndex = 3,
    dialogue = Dialogue(
        "Муваффақият дар кор",
        listOf(
            DialogueLine("Manager", "Congratulations on your promotion!", "Табрик бо баландшавии шумо!"),
            DialogueLine("Firuz", "Thank you! I achieved my goal.", "Ташаккур! Ман ба ҳадафи худ расидам."),
            DialogueLine("Manager", "You improved a lot this year.", "Шумо имсол хеле беҳтар шудед."),
            DialogueLine("Firuz", "I want to learn more and grow.", "Ман мехоҳам бештар омӯзам ва афзоиш ёбам."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w12_1", "Success", "Муваффақият", "Suc-cess", "Hard work brings success", "Кори сахт муваффақият меоварад", "ew_m3_l12"),
        WordItem("ew_w12_2", "Promotion", "Баландшавӣ", "Pro-mo-tion", "I got a promotion", "Ман баландшавӣ гирифтам", "ew_m3_l12"),
        WordItem("ew_w12_3", "Raise", "Зиёдшавӣ", "Raise", "I asked for a raise", "Ман зиёдшавии маош хостам", "ew_m3_l12"),
        WordItem("ew_w12_4", "Goal", "Ҳадаф", "Goal", "I achieved my goal", "Ман ба ҳадафи худ расидам", "ew_m3_l12"),
        WordItem("ew_w12_5", "Achieve", "Ноил шудан", "A-chieve", "I want to achieve more", "Ман мехоҳам бештар ноил шавам", "ew_m3_l12"),
        WordItem("ew_w12_6", "Improve", "Беҳтар кардан", "Im-prove", "I want to improve", "Ман мехоҳам беҳтар шавам", "ew_m3_l12"),
        WordItem("ew_w12_7", "Learn", "Омӯхтан", "Learn", "I learn every day", "Ман ҳар рӯз меомӯзам", "ew_m3_l12"),
        WordItem("ew_w12_8", "Grow", "Афзудан", "Grow", "I want to grow", "Ман мехоҳам афзоиш ёбам", "ew_m3_l12"),
    ),
    grammarTip = GrammarTip(
        "I achieved my goal / I want to improve",
        "Барои гуфтани муваффақият аз «I achieved...» ва барои орзуҳо аз «I want to...» истифода баред.",
        listOf("I achieved my goal.", "I want to improve my skills.", "I want to learn more."),
    ),
    exercises = listOf(
        Exercise("ew_e12_1", ExerciseType.MULTIPLE_CHOICE, "«Success» чӣ маъно дорад?", "Success = ...", listOf("Баландшавӣ", "Ҳадаф", "Муваффақият", "Зиёдшавӣ"), "Муваффақият", 2, "Success — Муваффақият"),
        Exercise("ew_e12_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I _____ my goal.", listOf("improved", "learned", "achieved", "grew"), "achieved", 2, "I achieved my goal — Ман ба ҳадафам расидам"),
        Exercise("ew_e12_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман мехоҳам беҳтар шавам»-ро интихоб кунед:", null, listOf("I want to learn", "I want to grow", "I want to improve", "I want to achieve"), "I want to improve", 2, "Ман мехоҳам беҳтар шавам = I want to improve"),
        Exercise("ew_e12_4", ExerciseType.TYPE_ANSWER, "«Ҳадаф»-ро ба англисӣ нависед:", "Ҳадаф = ?", null, "Goal", null, "Ҳадаф — Goal"),
        Exercise("ew_e12_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Success" to "Муваффақият", "Promotion" to "Баландшавӣ", "Goal" to "Ҳадаф", "Learn" to "Омӯхтан")),
        Exercise("ew_e12_6", ExerciseType.MULTIPLE_CHOICE, "«Promotion» чӣ маъно дорад?", "Promotion = ...", listOf("Зиёдшавӣ", "Баландшавӣ", "Ҳадаф", "Омӯхтан"), "Баландшавӣ", 1, "Promotion — Баландшавӣ"),
        Exercise("ew_e12_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман ҳар рӯз меомӯзам»", null, null, "I learn every day", null, "I learn every day", words = listOf("day", "every", "learn", "I")),
        Exercise("ew_e12_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nManager: You improved a lot this year.\nFiruz: _____", null, listOf("Goodbye!", "I want to learn more and grow.", "I have a problem."), "I want to learn more and grow.", 1, "Ҷавоб дар бораи омӯзиш"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 4 · ҲУҶҶАТҲО ВА РАСМИЁТ (Documents & Formalities)
// ═══════════════════════════════════════════════════

// ── Lesson 13: Ҳуҷҷатҳо (Documents) ─────────────

private val engM4L13 = Lesson(
    id = "ew_m4_l13", moduleId = "ew_m4",
    title = "Ҳуҷҷатҳо", description = "Ҳуҷҷатҳои корӣ ва расмӣ",
    emoji = "\uD83D\uDCC4", orderIndex = 0,
    dialogue = Dialogue(
        "Дар идора",
        listOf(
            DialogueLine("Firuz", "I need to fill out this form.", "Ман бояд ин бланкро пур кунам."),
            DialogueLine("Clerk", "Please bring your passport and visa.", "Лутфан паспорт ва визаи худро биёред."),
            DialogueLine("Firuz", "Here are my documents.", "Ана ҳуҷҷатҳои ман."),
            DialogueLine("Clerk", "Please sign at the bottom.", "Лутфан дар поён имзо кунед."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w13_1", "Document", "Ҳуҷҷат", "Doc-u-ment", "Bring your documents", "Ҳуҷҷатҳои худро биёред", "ew_m4_l13"),
        WordItem("ew_w13_2", "Passport", "Паспорт", "Pass-port", "Show your passport", "Паспорти худро нишон диҳед", "ew_m4_l13"),
        WordItem("ew_w13_3", "Visa", "Виза", "Vi-sa", "I need a work visa", "Ман визаи корӣ лозим", "ew_m4_l13"),
        WordItem("ew_w13_4", "Form", "Бланк", "Form", "Fill out the form", "Бланкро пур кунед", "ew_m4_l13"),
        WordItem("ew_w13_5", "Sign", "Имзо кардан", "Sign", "Please sign here", "Лутфан дар инҷо имзо кунед", "ew_m4_l13"),
        WordItem("ew_w13_6", "Copy", "Нусха", "Co-py", "I need a copy", "Ман нусха лозим", "ew_m4_l13"),
        WordItem("ew_w13_7", "Original", "Аслӣ", "O-rig-i-nal", "Bring the original", "Нусхаи аслиро биёред", "ew_m4_l13"),
        WordItem("ew_w13_8", "Stamp", "Мӯҳр", "Stamp", "We need a stamp", "Ба мо мӯҳр лозим", "ew_m4_l13"),
    ),
    grammarTip = GrammarTip(
        "I need to... / Please bring...",
        "Барои дархост аз «I need to + феъл» ва «Please bring + чиз» истифода баред.",
        listOf("I need to fill out the form.", "Please bring your passport.", "Please sign here."),
    ),
    exercises = listOf(
        Exercise("ew_e13_1", ExerciseType.MULTIPLE_CHOICE, "«Document» чӣ маъно дорад?", "Document = ...", listOf("Бланк", "Ҳуҷҷат", "Нусха", "Мӯҳр"), "Ҳуҷҷат", 1, "Document — Ҳуҷҷат"),
        Exercise("ew_e13_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Please _____ at the bottom.", listOf("stamp", "copy", "sign", "form"), "sign", 2, "Please sign — Лутфан имзо кунед"),
        Exercise("ew_e13_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман визаи корӣ лозим»-ро интихоб кунед:", null, listOf("I have a work visa", "I need a work visa", "I lost my work visa", "I found a work visa"), "I need a work visa", 1, "Ман визаи корӣ лозим = I need a work visa"),
        Exercise("ew_e13_4", ExerciseType.TYPE_ANSWER, "«Паспорт»-ро ба англисӣ нависед:", "Паспорт = ?", null, "Passport", null, "Паспорт — Passport"),
        Exercise("ew_e13_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Document" to "Ҳуҷҷат", "Visa" to "Виза", "Sign" to "Имзо кардан", "Copy" to "Нусха")),
        Exercise("ew_e13_6", ExerciseType.MULTIPLE_CHOICE, "«Stamp» чӣ маъно дорад?", "Stamp = ...", listOf("Имзо", "Нусха", "Мӯҳр", "Бланк"), "Мӯҳр", 2, "Stamp — Мӯҳр"),
        Exercise("ew_e13_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Лутфан паспорти худро биёред»", null, null, "Please bring your passport", null, "Please bring + чиз", words = listOf("passport", "your", "bring", "Please")),
        Exercise("ew_e13_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nClerk: Please bring your passport.\nFiruz: _____", null, listOf("Goodbye.", "Here are my documents.", "I have a meeting."), "Here are my documents.", 1, "Пешниҳоди ҳуҷҷатҳо"),
    ),
)

// ── Lesson 14: Дар бонк (At the Bank) ───────────

private val engM4L14 = Lesson(
    id = "ew_m4_l14", moduleId = "ew_m4",
    title = "Дар бонк", description = "Хизматҳои бонкӣ",
    emoji = "\uD83C\uDFE6", orderIndex = 1,
    dialogue = Dialogue(
        "Дар бонк",
        listOf(
            DialogueLine("Firuz", "I want to open a bank account.", "Ман мехоҳам ҳисоби бонкӣ кушоям."),
            DialogueLine("Teller", "Do you have your ID?", "Шиносномаи худро доред?"),
            DialogueLine("Firuz", "Yes. I also want to transfer money.", "Ҳа. Ман инчунин мехоҳам пул интиқол диҳам."),
            DialogueLine("Teller", "Please fill in the amount.", "Лутфан маблағро нависед."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w14_1", "Bank", "Бонк", "Bank", "I go to the bank", "Ман ба бонк меравам", "ew_m4_l14"),
        WordItem("ew_w14_2", "Account", "Ҳисоб", "Ac-count", "Open an account", "Ҳисоб кушоед", "ew_m4_l14"),
        WordItem("ew_w14_3", "Money", "Пул", "Mon-ey", "I need money", "Ман пул лозим", "ew_m4_l14"),
        WordItem("ew_w14_4", "Transfer", "Интиқол", "Trans-fer", "Transfer the money", "Пулро интиқол диҳед", "ew_m4_l14"),
        WordItem("ew_w14_5", "Deposit", "Гузоштан", "De-pos-it", "I want to deposit", "Ман мехоҳам пул гузорам", "ew_m4_l14"),
        WordItem("ew_w14_6", "Withdraw", "Гирифтан", "With-draw", "I want to withdraw cash", "Ман мехоҳам нақд гирам", "ew_m4_l14"),
        WordItem("ew_w14_7", "Amount", "Маблағ", "A-mount", "What is the amount?", "Маблағ чанд аст?", "ew_m4_l14"),
        WordItem("ew_w14_8", "Card", "Корт", "Card", "My bank card", "Корти бонкии ман", "ew_m4_l14"),
    ),
    grammarTip = GrammarTip(
        "I want to open... / I want to transfer...",
        "Барои хизматҳои бонкӣ аз «I want to + феъл» истифода баред.",
        listOf("I want to open an account.", "I want to transfer money.", "I want to withdraw cash."),
    ),
    exercises = listOf(
        Exercise("ew_e14_1", ExerciseType.MULTIPLE_CHOICE, "«Account» чӣ маъно дорад?", "Account = ...", listOf("Пул", "Бонк", "Ҳисоб", "Корт"), "Ҳисоб", 2, "Account — Ҳисоб"),
        Exercise("ew_e14_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I want to _____ money.", listOf("open", "sign", "transfer", "fill"), "transfer", 2, "I want to transfer — Ман мехоҳам интиқол диҳам"),
        Exercise("ew_e14_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман мехоҳам ҳисоб кушоям»-ро интихоб кунед:", null, listOf("I want to close an account", "I want to open an account", "I want to transfer money", "I want to deposit money"), "I want to open an account", 1, "Ман мехоҳам ҳисоб кушоям = I want to open an account"),
        Exercise("ew_e14_4", ExerciseType.TYPE_ANSWER, "«Пул»-ро ба англисӣ нависед:", "Пул = ?", null, "Money", null, "Пул — Money"),
        Exercise("ew_e14_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Bank" to "Бонк", "Transfer" to "Интиқол", "Deposit" to "Гузоштан", "Withdraw" to "Гирифтан")),
        Exercise("ew_e14_6", ExerciseType.MULTIPLE_CHOICE, "«Amount» чӣ маъно дорад?", "Amount = ...", listOf("Корт", "Маблағ", "Ҳисоб", "Пул"), "Маблағ", 1, "Amount — Маблағ"),
        Exercise("ew_e14_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман мехоҳам пул интиқол диҳам»", null, null, "I want to transfer money", null, "I want to + феъл", words = listOf("money", "transfer", "to", "want", "I")),
        Exercise("ew_e14_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ac-count", listOf("Amount", "Account", "Card", "Bank"), "Account", 1, "Account — Ҳисоб"),
    ),
)

// ── Lesson 15: Шартнома (Contract) ──────────────

private val engM4L15 = Lesson(
    id = "ew_m4_l15", moduleId = "ew_m4",
    title = "Шартнома", description = "Фаҳмидани шартнома",
    emoji = "\uD83D\uDCDD", orderIndex = 2,
    dialogue = Dialogue(
        "Шартномаи корӣ",
        listOf(
            DialogueLine("HR", "Here is your employment contract.", "Ана шартномаи корӣ."),
            DialogueLine("Firuz", "What is the salary?", "Маош чанд аст?"),
            DialogueLine("HR", "It is stated on page two. Read the terms.", "Дар саҳифаи дуюм навишта шудааст. Шартҳоро хонед."),
            DialogueLine("Firuz", "I understand. Where do I sign?", "Мефаҳмам. Дар куҷо имзо кунам?"),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w15_1", "Employment", "Шуғл", "Em-ploy-ment", "Employment contract", "Шартномаи шуғл", "ew_m4_l15"),
        WordItem("ew_w15_2", "Term", "Шарт", "Term", "Read the terms", "Шартҳоро хонед", "ew_m4_l15"),
        WordItem("ew_w15_3", "Period", "Мӯҳлат", "Pe-ri-od", "The trial period", "Мӯҳлати санҷиш", "ew_m4_l15"),
        WordItem("ew_w15_4", "Page", "Саҳифа", "Page", "Go to page two", "Ба саҳифаи дуюм равед", "ew_m4_l15"),
        WordItem("ew_w15_5", "Clause", "Банд", "Clause", "Read clause three", "Банди сеюмро хонед", "ew_m4_l15"),
        WordItem("ew_w15_6", "Agreement", "Мувофиқат", "A-gree-ment", "Sign the agreement", "Мувофиқатро имзо кунед", "ew_m4_l15"),
        WordItem("ew_w15_7", "Date", "Сана", "Date", "Write the date", "Санаро нависед", "ew_m4_l15"),
        WordItem("ew_w15_8", "Valid", "Эътиборнок", "Val-id", "The contract is valid", "Шартнома эътиборнок аст", "ew_m4_l15"),
    ),
    grammarTip = GrammarTip(
        "Where do I sign? / What does this mean?",
        "Барои саволҳо дар бораи ҳуҷҷатҳо аз «Where do I...?» ва «What does this mean?» истифода баред.",
        listOf("Where do I sign?", "What does this clause mean?", "When does the contract start?"),
    ),
    exercises = listOf(
        Exercise("ew_e15_1", ExerciseType.MULTIPLE_CHOICE, "«Term» чӣ маъно дорад?", "Term = ...", listOf("Сана", "Банд", "Шарт", "Саҳифа"), "Шарт", 2, "Term — Шарт"),
        Exercise("ew_e15_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Read the _____ carefully.", listOf("page", "date", "terms", "salary"), "terms", 2, "Read the terms — Шартҳоро хонед"),
        Exercise("ew_e15_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Дар куҷо имзо кунам?»-ро интихоб кунед:", null, listOf("Where is the page?", "Where do I sign?", "Where is the date?", "Where is the contract?"), "Where do I sign?", 1, "Дар куҷо имзо кунам? = Where do I sign?"),
        Exercise("ew_e15_4", ExerciseType.TYPE_ANSWER, "«Мувофиқат»-ро ба англисӣ нависед:", "Мувофиқат = ?", null, "Agreement", null, "Мувофиқат — Agreement"),
        Exercise("ew_e15_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Term" to "Шарт", "Period" to "Мӯҳлат", "Page" to "Саҳифа", "Date" to "Сана")),
        Exercise("ew_e15_6", ExerciseType.MULTIPLE_CHOICE, "«Valid» чӣ маъно дорад?", "Valid = ...", listOf("Нодуруст", "Эътиборнок", "Кӯҳна", "Нав"), "Эътиборнок", 1, "Valid — Эътиборнок"),
        Exercise("ew_e15_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A A-gree-ment", listOf("Employment", "Agreement", "Document", "Period"), "Agreement", 1, "Agreement — Мувофиқат"),
        Exercise("ew_e15_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Шартномаро бодиққат хонед»", null, null, "Read the contract carefully", null, "Read + чиз + carefully", words = listOf("carefully", "contract", "the", "Read")),
    ),
)

// ── Lesson 16: Бехатарӣ (Safety) ────────────────

private val engM4L16 = Lesson(
    id = "ew_m4_l16", moduleId = "ew_m4",
    title = "Бехатарӣ", description = "Қоидаҳои бехатарӣ дар кор",
    emoji = "\u26D1\uFE0F", orderIndex = 3,
    dialogue = Dialogue(
        "Бехатарии корӣ",
        listOf(
            DialogueLine("Manager", "Always wear your safety helmet.", "Ҳамеша кулоҳи бехатариро пӯшед."),
            DialogueLine("Firuz", "Where is the exit?", "Баромад дар куҷост?"),
            DialogueLine("Manager", "The emergency exit is over there.", "Баромади фаврӣ дар он ҷост."),
            DialogueLine("Firuz", "I understand the safety rules.", "Ман қоидаҳои бехатариро мефаҳмам."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w16_1", "Safety", "Бехатарӣ", "Safe-ty", "Safety first", "Аввал бехатарӣ", "ew_m4_l16"),
        WordItem("ew_w16_2", "Helmet", "Кулоҳ", "Hel-met", "Wear a helmet", "Кулоҳ пӯшед", "ew_m4_l16"),
        WordItem("ew_w16_3", "Exit", "Баромад", "Ex-it", "Where is the exit?", "Баромад куҷост?", "ew_m4_l16"),
        WordItem("ew_w16_4", "Emergency", "Фаврӣ", "E-mer-gen-cy", "Call emergency", "Ба хизмати фаврӣ занг занед", "ew_m4_l16"),
        WordItem("ew_w16_5", "Rule", "Қоида", "Rule", "Follow the rules", "Аз қоидаҳо пайравӣ кунед", "ew_m4_l16"),
        WordItem("ew_w16_6", "Danger", "Хатар", "Dan-ger", "Danger! Do not enter.", "Хатар! Надароед.", "ew_m4_l16"),
        WordItem("ew_w16_7", "Warning", "Огоҳӣ", "Warn-ing", "Read the warning", "Огоҳиро хонед", "ew_m4_l16"),
        WordItem("ew_w16_8", "Careful", "Эҳтиёт", "Care-ful", "Be careful!", "Эҳтиёт бошед!", "ew_m4_l16"),
    ),
    grammarTip = GrammarTip(
        "Always wear... / Be careful!",
        "Барои дастурҳои бехатарӣ аз «Always + феъл» ва «Be careful» истифода баред.",
        listOf("Always wear a helmet.", "Be careful!", "Follow the safety rules."),
    ),
    exercises = listOf(
        Exercise("ew_e16_1", ExerciseType.MULTIPLE_CHOICE, "«Safety» чӣ маъно дорад?", "Safety = ...", listOf("Хатар", "Бехатарӣ", "Огоҳӣ", "Қоида"), "Бехатарӣ", 1, "Safety — Бехатарӣ"),
        Exercise("ew_e16_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Always _____ a helmet.", listOf("sign", "read", "wear", "open"), "wear", 2, "Always wear — Ҳамеша пӯшед"),
        Exercise("ew_e16_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Баромад дар куҷост?»-ро интихоб кунед:", null, listOf("Where is the danger?", "Where is the exit?", "Where is the rule?", "Where is the helmet?"), "Where is the exit?", 1, "Баромад куҷост? = Where is the exit?"),
        Exercise("ew_e16_4", ExerciseType.TYPE_ANSWER, "«Хатар»-ро ба англисӣ нависед:", "Хатар = ?", null, "Danger", null, "Хатар — Danger"),
        Exercise("ew_e16_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Safety" to "Бехатарӣ", "Danger" to "Хатар", "Exit" to "Баромад", "Warning" to "Огоҳӣ")),
        Exercise("ew_e16_6", ExerciseType.MULTIPLE_CHOICE, "«Careful» чӣ маъно дорад?", "Careful = ...", listOf("Хатар", "Фаврӣ", "Эҳтиёт", "Қоида"), "Эҳтиёт", 2, "Careful — Эҳтиёт"),
        Exercise("ew_e16_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A E-mer-gen-cy", listOf("Exit", "Emergency", "Warning", "Safety"), "Emergency", 1, "Emergency — Фаврӣ"),
        Exercise("ew_e16_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Аз қоидаҳо пайравӣ кунед»", null, null, "Follow the safety rules", null, "Follow + чиз", words = listOf("rules", "safety", "the", "Follow")),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 5 · ҲАЁТИ ҲАРРӮЗА (Daily Life)
// ═══════════════════════════════════════════════════

// ── Lesson 17: Дар мағоза (Shopping) ────────────

private val engM5L17 = Lesson(
    id = "ew_m5_l17", moduleId = "ew_m5",
    title = "Дар мағоза", description = "Харидорӣ кардан",
    emoji = "\uD83D\uDED2", orderIndex = 0,
    dialogue = Dialogue(
        "Дар мағоза",
        listOf(
            DialogueLine("Firuz", "How much does this cost?", "Ин чанд пул аст?"),
            DialogueLine("Seller", "It costs ten pounds.", "Даҳ фунт аст."),
            DialogueLine("Firuz", "Can I pay by card?", "Метавонам бо корт пардохт кунам?"),
            DialogueLine("Seller", "Yes, of course. Here is your receipt.", "Ҳа, албатта. Ана чеки шумо."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w17_1", "Shop", "Мағоза", "Shop", "I go to the shop", "Ман ба мағоза меравам", "ew_m5_l17"),
        WordItem("ew_w17_2", "Buy", "Харидан", "Buy", "I want to buy this", "Ман инро харидан мехоҳам", "ew_m5_l17"),
        WordItem("ew_w17_3", "Cost", "Арзиш", "Cost", "How much does it cost?", "Ин чанд пул аст?", "ew_m5_l17"),
        WordItem("ew_w17_4", "Pay", "Пардохтан", "Pay", "I will pay now", "Ман ҳоло пардохт мекунам", "ew_m5_l17"),
        WordItem("ew_w17_5", "Receipt", "Чек", "Re-ceipt", "Keep the receipt", "Чекро нигоҳ доред", "ew_m5_l17"),
        WordItem("ew_w17_6", "Cheap", "Арзон", "Cheap", "This is cheap", "Ин арзон аст", "ew_m5_l17"),
        WordItem("ew_w17_7", "Expensive", "Гарон", "Ex-pen-sive", "That is expensive", "Он гарон аст", "ew_m5_l17"),
        WordItem("ew_w17_8", "Change", "Баргардон", "Change", "Here is your change", "Ана баргардони шумо", "ew_m5_l17"),
    ),
    grammarTip = GrammarTip(
        "How much does it cost? / Can I pay by...?",
        "Барои пурсидани нарх аз «How much does it cost?» ва барои тарзи пардохт аз «Can I pay by...?» истифода баред.",
        listOf("How much does it cost?", "Can I pay by card?", "Can I pay in cash?"),
    ),
    exercises = listOf(
        Exercise("ew_e17_1", ExerciseType.MULTIPLE_CHOICE, "«Buy» чӣ маъно дорад?", "Buy = ...", listOf("Фурӯхтан", "Харидан", "Пардохтан", "Нигоҳ доштан"), "Харидан", 1, "Buy — Харидан"),
        Exercise("ew_e17_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "How much does it _____?", listOf("pay", "buy", "cost", "change"), "cost", 2, "How much does it cost — Ин чанд пул аст"),
        Exercise("ew_e17_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Метавонам бо корт пардохт кунам?»-ро интихоб кунед:", null, listOf("Can I pay by card?", "Can I buy a card?", "Can I get a receipt?", "Can I get change?"), "Can I pay by card?", 1, "Бо корт пардохт кардан = Can I pay by card?"),
        Exercise("ew_e17_4", ExerciseType.TYPE_ANSWER, "«Арзон»-ро ба англисӣ нависед:", "Арзон = ?", null, "Cheap", null, "Арзон — Cheap"),
        Exercise("ew_e17_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Buy" to "Харидан", "Pay" to "Пардохтан", "Cheap" to "Арзон", "Expensive" to "Гарон")),
        Exercise("ew_e17_6", ExerciseType.MULTIPLE_CHOICE, "«Receipt» чӣ маъно дорад?", "Receipt = ...", listOf("Корт", "Чек", "Пул", "Нарх"), "Чек", 1, "Receipt — Чек"),
        Exercise("ew_e17_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман инро харидан мехоҳам»", null, null, "I want to buy this", null, "I want to + феъл", words = listOf("this", "buy", "to", "want", "I")),
        Exercise("ew_e17_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ex-pen-sive", listOf("Cheap", "Expensive", "Receipt", "Change"), "Expensive", 1, "Expensive — Гарон"),
    ),
)

// ── Lesson 18: Нақлиёт (Transport) ─────────────

private val engM5L18 = Lesson(
    id = "ew_m5_l18", moduleId = "ew_m5",
    title = "Нақлиёт", description = "Истифодаи нақлиёт",
    emoji = "\uD83D\uDE8C", orderIndex = 1,
    dialogue = Dialogue(
        "Дар нақлиёт",
        listOf(
            DialogueLine("Firuz", "How do I get to the office?", "Чӣ тавр ба офис расам?"),
            DialogueLine("Tom", "Take the bus. The station is nearby.", "Автобус гиред. Истгоҳ наздик аст."),
            DialogueLine("Firuz", "How long does it take?", "Чанд вақт мегирад?"),
            DialogueLine("Tom", "About twenty minutes by bus.", "Тақрибан бист дақиқа бо автобус."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w18_1", "Bus", "Автобус", "Bus", "Take the bus", "Автобус гиред", "ew_m5_l18"),
        WordItem("ew_w18_2", "Train", "Қатора", "Train", "The train is fast", "Қатора тез аст", "ew_m5_l18"),
        WordItem("ew_w18_3", "Taxi", "Такси", "Tax-i", "Call a taxi", "Такси даъват кунед", "ew_m5_l18"),
        WordItem("ew_w18_4", "Station", "Истгоҳ", "Sta-tion", "Where is the station?", "Истгоҳ куҷост?", "ew_m5_l18"),
        WordItem("ew_w18_5", "Ticket", "Билет", "Tick-et", "Buy a ticket", "Билет харед", "ew_m5_l18"),
        WordItem("ew_w18_6", "Stop", "Истгоҳ", "Stop", "The next stop", "Истгоҳи навбатӣ", "ew_m5_l18"),
        WordItem("ew_w18_7", "Far", "Дур", "Far", "Is it far?", "Дур аст?", "ew_m5_l18"),
        WordItem("ew_w18_8", "Near", "Наздик", "Near", "It is near", "Наздик аст", "ew_m5_l18"),
    ),
    grammarTip = GrammarTip(
        "How do I get to...? / How long does it take?",
        "Барои пурсидани роҳ аз «How do I get to...?» ва барои вақт аз «How long does it take?» истифода баред.",
        listOf("How do I get to the station?", "How long does it take?", "Take the bus to the center."),
    ),
    exercises = listOf(
        Exercise("ew_e18_1", ExerciseType.MULTIPLE_CHOICE, "«Ticket» чӣ маъно дорад?", "Ticket = ...", listOf("Истгоҳ", "Автобус", "Билет", "Такси"), "Билет", 2, "Ticket — Билет"),
        Exercise("ew_e18_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "How do I _____ to the office?", listOf("take", "get", "go", "stop"), "get", 1, "How do I get to — Чӣ тавр расам ба"),
        Exercise("ew_e18_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Истгоҳ наздик аст»-ро интихоб кунед:", null, listOf("The station is far", "The station is nearby", "The station is big", "The station is closed"), "The station is nearby", 1, "Истгоҳ наздик аст = The station is nearby"),
        Exercise("ew_e18_4", ExerciseType.TYPE_ANSWER, "«Қатора»-ро ба англисӣ нависед:", "Қатора = ?", null, "Train", null, "Қатора — Train"),
        Exercise("ew_e18_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Bus" to "Автобус", "Train" to "Қатора", "Far" to "Дур", "Near" to "Наздик")),
        Exercise("ew_e18_6", ExerciseType.MULTIPLE_CHOICE, "«Station» чӣ маъно дорад?", "Station = ...", listOf("Билет", "Такси", "Истгоҳ", "Роҳ"), "Истгоҳ", 2, "Station — Истгоҳ"),
        Exercise("ew_e18_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Автобус гиред ба марказ»", null, null, "Take the bus to the center", null, "Take + нақлиёт", words = listOf("center", "the", "to", "bus", "the", "Take")),
        Exercise("ew_e18_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: How do I get to the office?\nTom: _____", null, listOf("It is expensive.", "Take the bus.", "Goodbye!"), "Take the bus.", 1, "Маслиҳат дар бораи нақлиёт"),
    ),
)

// ── Lesson 19: Хона (Home) ─────────────────────

private val engM5L19 = Lesson(
    id = "ew_m5_l19", moduleId = "ew_m5",
    title = "Хона", description = "Дар бораи хона гап задан",
    emoji = "\uD83C\uDFE0", orderIndex = 2,
    dialogue = Dialogue(
        "Дар бораи хона",
        listOf(
            DialogueLine("Tom", "Where do you live?", "Шумо дар куҷо зиндагӣ мекунед?"),
            DialogueLine("Firuz", "I rent a flat near the office.", "Ман дар наздикии офис квартира иҷора мекунам."),
            DialogueLine("Tom", "How many rooms does it have?", "Чанд хона дорад?"),
            DialogueLine("Firuz", "Two rooms, a kitchen and a bathroom.", "Ду хона, ошхона ва ҳаммом."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w19_1", "Flat", "Квартира", "Flat", "I live in a flat", "Ман дар квартира зиндагӣ мекунам", "ew_m5_l19"),
        WordItem("ew_w19_2", "Rent", "Иҷора", "Rent", "I rent a flat", "Ман квартира иҷора мекунам", "ew_m5_l19"),
        WordItem("ew_w19_3", "Room", "Хона", "Room", "A big room", "Хонаи калон", "ew_m5_l19"),
        WordItem("ew_w19_4", "Kitchen", "Ошхона", "Kitch-en", "The kitchen is small", "Ошхона хурд аст", "ew_m5_l19"),
        WordItem("ew_w19_5", "Bathroom", "Ҳаммом", "Bath-room", "Where is the bathroom?", "Ҳаммом куҷост?", "ew_m5_l19"),
        WordItem("ew_w19_6", "Address", "Суроға", "Ad-dress", "What is your address?", "Суроғаи шумо чист?", "ew_m5_l19"),
        WordItem("ew_w19_7", "Furniture", "Мебел", "Fur-ni-ture", "New furniture", "Мебели нав", "ew_m5_l19"),
        WordItem("ew_w19_8", "Neighbour", "Ҳамсоя", "Neigh-bour", "My neighbour is friendly", "Ҳамсояи ман дӯстона аст", "ew_m5_l19"),
    ),
    grammarTip = GrammarTip(
        "Where do you live? / I live in...",
        "Барои пурсидани ҷои зист аз «Where do you live?» ва барои ҷавоб аз «I live in + ҷой» истифода баред.",
        listOf("Where do you live?", "I live in a flat.", "I rent a room near the center."),
    ),
    exercises = listOf(
        Exercise("ew_e19_1", ExerciseType.MULTIPLE_CHOICE, "«Rent» чӣ маъно дорад?", "Rent = ...", listOf("Харидан", "Иҷора", "Фурӯхтан", "Сохтан"), "Иҷора", 1, "Rent — Иҷора"),
        Exercise("ew_e19_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I _____ in a flat.", listOf("work", "rent", "live", "buy"), "live", 2, "I live in — Ман зиндагӣ мекунам дар"),
        Exercise("ew_e19_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ошхона хурд аст»-ро интихоб кунед:", null, listOf("The kitchen is big", "The kitchen is small", "The kitchen is new", "The kitchen is old"), "The kitchen is small", 1, "Ошхона хурд аст = The kitchen is small"),
        Exercise("ew_e19_4", ExerciseType.TYPE_ANSWER, "«Ҳамсоя»-ро ба англисӣ нависед:", "Ҳамсоя = ?", null, "Neighbour", null, "Ҳамсоя — Neighbour"),
        Exercise("ew_e19_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Flat" to "Квартира", "Room" to "Хона", "Kitchen" to "Ошхона", "Bathroom" to "Ҳаммом")),
        Exercise("ew_e19_6", ExerciseType.MULTIPLE_CHOICE, "«Address» чӣ маъно дорад?", "Address = ...", listOf("Мебел", "Суроға", "Хона", "Иҷора"), "Суроға", 1, "Address — Суроға"),
        Exercise("ew_e19_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман квартира иҷора мекунам»", null, null, "I rent a flat", null, "I rent + чиз", words = listOf("flat", "a", "rent", "I")),
        Exercise("ew_e19_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Fur-ni-ture", listOf("Bathroom", "Furniture", "Kitchen", "Address"), "Furniture", 1, "Furniture — Мебел"),
    ),
)

// ── Lesson 20: Тандурустӣ (Health) ─────────────

private val engM5L20 = Lesson(
    id = "ew_m5_l20", moduleId = "ew_m5",
    title = "Тандурустӣ", description = "Дар бораи тандурустӣ гап задан",
    emoji = "\uD83C\uDFE5", orderIndex = 3,
    dialogue = Dialogue(
        "Назди духтур",
        listOf(
            DialogueLine("Doctor", "How are you feeling?", "Ҳоли шумо чӣ тавр?"),
            DialogueLine("Firuz", "I have a headache and a fever.", "Сарам дард мекунад ва тоб дорам."),
            DialogueLine("Doctor", "Take this medicine twice a day.", "Ин доруро ду маротиба дар рӯз хӯред."),
            DialogueLine("Firuz", "When will I feel better?", "Кай беҳтар мешавам?"),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w20_1", "Doctor", "Духтур", "Doc-tor", "I need a doctor", "Ман духтур лозим", "ew_m5_l20"),
        WordItem("ew_w20_2", "Hospital", "Беморхона", "Hos-pi-tal", "Go to the hospital", "Ба беморхона равед", "ew_m5_l20"),
        WordItem("ew_w20_3", "Medicine", "Дору", "Med-i-cine", "Take your medicine", "Доруи худро хӯред", "ew_m5_l20"),
        WordItem("ew_w20_4", "Pain", "Дард", "Pain", "I have pain here", "Дар инҷо дард дорам", "ew_m5_l20"),
        WordItem("ew_w20_5", "Fever", "Тоб", "Fe-ver", "I have a fever", "Ман тоб дорам", "ew_m5_l20"),
        WordItem("ew_w20_6", "Sick", "Бемор", "Sick", "I feel sick", "Ман бемор ҳастам", "ew_m5_l20"),
        WordItem("ew_w20_7", "Better", "Беҳтар", "Bet-ter", "I feel better now", "Ман ҳоло беҳтарам", "ew_m5_l20"),
        WordItem("ew_w20_8", "Rest", "Истироҳат", "Rest", "You need rest", "Шумо истироҳат лозим", "ew_m5_l20"),
    ),
    grammarTip = GrammarTip(
        "I have a headache / I feel sick",
        "Барои гуфтани дард аз «I have a + дард» ва барои ҳолат аз «I feel + сифат» истифода баред.",
        listOf("I have a headache.", "I feel sick.", "I need to see a doctor."),
    ),
    exercises = listOf(
        Exercise("ew_e20_1", ExerciseType.MULTIPLE_CHOICE, "«Medicine» чӣ маъно дорад?", "Medicine = ...", listOf("Духтур", "Дору", "Дард", "Тоб"), "Дору", 1, "Medicine — Дору"),
        Exercise("ew_e20_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I have a _____ and a fever.", listOf("rest", "medicine", "headache", "doctor"), "headache", 2, "I have a headache — Сарам дард мекунад"),
        Exercise("ew_e20_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман духтур лозим»-ро интихоб кунед:", null, listOf("I am a doctor", "I need a doctor", "I see a doctor", "I like the doctor"), "I need a doctor", 1, "Ман духтур лозим = I need a doctor"),
        Exercise("ew_e20_4", ExerciseType.TYPE_ANSWER, "«Беморхона»-ро ба англисӣ нависед:", "Беморхона = ?", null, "Hospital", null, "Беморхона — Hospital"),
        Exercise("ew_e20_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Doctor" to "Духтур", "Medicine" to "Дору", "Sick" to "Бемор", "Rest" to "Истироҳат")),
        Exercise("ew_e20_6", ExerciseType.MULTIPLE_CHOICE, "«Fever» чӣ маъно дорад?", "Fever = ...", listOf("Дард", "Тоб", "Бемор", "Беҳтар"), "Тоб", 1, "Fever — Тоб"),
        Exercise("ew_e20_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Доруи худро хӯред»", null, null, "Take your medicine", null, "Take + чиз", words = listOf("medicine", "your", "Take")),
        Exercise("ew_e20_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nDoctor: How are you feeling?\nFiruz: _____", null, listOf("I am fine.", "I have a headache.", "Goodbye."), "I have a headache.", 1, "Ҷавоб дар бораи ҳолат"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 6 · ТЕХНОЛОГИЯ (Technology)
// ═══════════════════════════════════════════════════

// ── Lesson 21: Компютер (Computer) ─────────────

private val engM6L21 = Lesson(
    id = "ew_m6_l21", moduleId = "ew_m6",
    title = "Компютер", description = "Истифодаи компютер дар кор",
    emoji = "\uD83D\uDCBB", orderIndex = 0,
    dialogue = Dialogue(
        "Дар офис бо компютер",
        listOf(
            DialogueLine("Firuz", "My computer is not working.", "Компютери ман кор намекунад."),
            DialogueLine("IT", "Did you restart it?", "Шумо аз нав оғоз кардед?"),
            DialogueLine("Firuz", "Yes. The screen is still black.", "Ҳа. Экран ҳанӯз сиёҳ аст."),
            DialogueLine("IT", "Let me check the keyboard and mouse.", "Бигзоред клавиатура ва маусро тафтиш кунам."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w21_1", "Computer", "Компютер", "Com-pu-ter", "Turn on the computer", "Компютерро равшан кунед", "ew_m6_l21"),
        WordItem("ew_w21_2", "Screen", "Экран", "Screen", "The screen is big", "Экран калон аст", "ew_m6_l21"),
        WordItem("ew_w21_3", "Keyboard", "Клавиатура", "Key-board", "Type on the keyboard", "Дар клавиатура нависед", "ew_m6_l21"),
        WordItem("ew_w21_4", "Mouse", "Маус", "Mouse", "Click the mouse", "Маусро пахш кунед", "ew_m6_l21"),
        WordItem("ew_w21_5", "Printer", "Принтер", "Print-er", "The printer is broken", "Принтер вайрон аст", "ew_m6_l21"),
        WordItem("ew_w21_6", "File", "Файл", "File", "Save the file", "Файлро нигоҳ доред", "ew_m6_l21"),
        WordItem("ew_w21_7", "Folder", "Ҷузвдон", "Fold-er", "Open the folder", "Ҷузвдонро кушоед", "ew_m6_l21"),
        WordItem("ew_w21_8", "Restart", "Аз нав оғоз", "Re-start", "Restart the computer", "Компютерро аз нав оғоз кунед", "ew_m6_l21"),
    ),
    grammarTip = GrammarTip(
        "My computer is not working / Did you restart it?",
        "Барои мушкилоти техникӣ аз «... is not working» ва барои савол аз «Did you + феъл?» истифода баред.",
        listOf("My computer is not working.", "Did you restart it?", "The printer is broken."),
    ),
    exercises = listOf(
        Exercise("ew_e21_1", ExerciseType.MULTIPLE_CHOICE, "«Screen» чӣ маъно дорад?", "Screen = ...", listOf("Клавиатура", "Экран", "Маус", "Файл"), "Экран", 1, "Screen — Экран"),
        Exercise("ew_e21_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "My computer is not _____.", listOf("saving", "working", "printing", "typing"), "working", 1, "is not working — кор намекунад"),
        Exercise("ew_e21_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Файлро нигоҳ доред»-ро интихоб кунед:", null, listOf("Delete the file", "Save the file", "Open the file", "Send the file"), "Save the file", 1, "Файлро нигоҳ доред = Save the file"),
        Exercise("ew_e21_4", ExerciseType.TYPE_ANSWER, "«Принтер»-ро ба англисӣ нависед:", "Принтер = ?", null, "Printer", null, "Принтер — Printer"),
        Exercise("ew_e21_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Screen" to "Экран", "Keyboard" to "Клавиатура", "Mouse" to "Маус", "Printer" to "Принтер")),
        Exercise("ew_e21_6", ExerciseType.MULTIPLE_CHOICE, "«Folder» чӣ маъно дорад?", "Folder = ...", listOf("Файл", "Ҷузвдон", "Экран", "Компютер"), "Ҷузвдон", 1, "Folder — Ҷузвдон"),
        Exercise("ew_e21_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Key-board", listOf("Screen", "Keyboard", "Mouse", "Printer"), "Keyboard", 1, "Keyboard — Клавиатура"),
        Exercise("ew_e21_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Компютерро аз нав оғоз кунед»", null, null, "Restart the computer", null, "Restart + чиз", words = listOf("computer", "the", "Restart")),
    ),
)

// ── Lesson 22: Интернет (Internet) ─────────────

private val engM6L22 = Lesson(
    id = "ew_m6_l22", moduleId = "ew_m6",
    title = "Интернет", description = "Истифодаи интернет",
    emoji = "\uD83C\uDF10", orderIndex = 1,
    dialogue = Dialogue(
        "Дар бораи интернет",
        listOf(
            DialogueLine("Firuz", "What is the Wi-Fi password?", "Пароли Wi-Fi чист?"),
            DialogueLine("Tom", "It is on the wall. The connection is fast.", "Дар девор навишта. Пайвастшавӣ тез аст."),
            DialogueLine("Firuz", "I cannot open this website.", "Ман ин сомонаро кушода наметавонам."),
            DialogueLine("Tom", "Try to refresh the page.", "Саҳифаро нав кунед."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w22_1", "Internet", "Интернет", "In-ter-net", "I use the internet", "Ман интернет истифода мекунам", "ew_m6_l22"),
        WordItem("ew_w22_2", "Website", "Сомона", "Web-site", "Open the website", "Сомонаро кушоед", "ew_m6_l22"),
        WordItem("ew_w22_3", "Password", "Парол", "Pass-word", "Enter the password", "Паролро ворид кунед", "ew_m6_l22"),
        WordItem("ew_w22_4", "Download", "Боргирӣ", "Down-load", "Download the file", "Файлро боргирӣ кунед", "ew_m6_l22"),
        WordItem("ew_w22_5", "Upload", "Боргузорӣ", "Up-load", "Upload the photo", "Аксро боргузорӣ кунед", "ew_m6_l22"),
        WordItem("ew_w22_6", "Search", "Ҷустуҷӯ", "Search", "Search on Google", "Дар Google ҷустуҷӯ кунед", "ew_m6_l22"),
        WordItem("ew_w22_7", "Link", "Пайванд", "Link", "Click the link", "Пайвандро пахш кунед", "ew_m6_l22"),
        WordItem("ew_w22_8", "Connect", "Пайваст шудан", "Con-nect", "Connect to Wi-Fi", "Ба Wi-Fi пайваст шавед", "ew_m6_l22"),
    ),
    grammarTip = GrammarTip(
        "What is the password? / I cannot open...",
        "Барои пурсидани парол аз «What is the password?» ва барои мушкилот аз «I cannot + феъл» истифода баред.",
        listOf("What is the Wi-Fi password?", "I cannot open this website.", "I cannot connect to the internet."),
    ),
    exercises = listOf(
        Exercise("ew_e22_1", ExerciseType.MULTIPLE_CHOICE, "«Password» чӣ маъно дорад?", "Password = ...", listOf("Пайванд", "Парол", "Сомона", "Ҷустуҷӯ"), "Парол", 1, "Password — Парол"),
        Exercise("ew_e22_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I cannot _____ this website.", listOf("download", "search", "open", "upload"), "open", 2, "I cannot open — Ман кушода наметавонам"),
        Exercise("ew_e22_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Файлро боргирӣ кунед»-ро интихоб кунед:", null, listOf("Upload the file", "Download the file", "Delete the file", "Open the file"), "Download the file", 1, "Файлро боргирӣ кунед = Download the file"),
        Exercise("ew_e22_4", ExerciseType.TYPE_ANSWER, "«Ҷустуҷӯ»-ро ба англисӣ нависед:", "Ҷустуҷӯ = ?", null, "Search", null, "Ҷустуҷӯ — Search"),
        Exercise("ew_e22_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Download" to "Боргирӣ", "Upload" to "Боргузорӣ", "Search" to "Ҷустуҷӯ", "Link" to "Пайванд")),
        Exercise("ew_e22_6", ExerciseType.MULTIPLE_CHOICE, "«Website» чӣ маъно дорад?", "Website = ...", listOf("Парол", "Интернет", "Сомона", "Пайванд"), "Сомона", 2, "Website — Сомона"),
        Exercise("ew_e22_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ба Wi-Fi пайваст шавед»", null, null, "Connect to Wi-Fi", null, "Connect to + чиз", words = listOf("Wi-Fi", "to", "Connect")),
        Exercise("ew_e22_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Down-load", listOf("Upload", "Download", "Connect", "Search"), "Download", 1, "Download — Боргирӣ"),
    ),
)

// ── Lesson 23: Барнома (Software) ──────────────

private val engM6L23 = Lesson(
    id = "ew_m6_l23", moduleId = "ew_m6",
    title = "Барнома", description = "Барномаҳои корӣ",
    emoji = "\uD83D\uDCF1", orderIndex = 2,
    dialogue = Dialogue(
        "Барномаҳои корӣ",
        listOf(
            DialogueLine("IT", "You need to install this software.", "Шумо бояд ин барномаро насб кунед."),
            DialogueLine("Firuz", "How do I update the application?", "Чӣ тавр барномаро навсозӣ кунам?"),
            DialogueLine("IT", "Click Settings and then Update.", "Танзимотро пахш кунед ва баъд Навсозиро."),
            DialogueLine("Firuz", "The program crashed. What should I do?", "Барнома аз кор баромад. Чӣ кор кунам?"),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w23_1", "Software", "Барнома", "Soft-ware", "Install the software", "Барномаро насб кунед", "ew_m6_l23"),
        WordItem("ew_w23_2", "Application", "Замима", "Ap-pli-ca-tion", "Open the application", "Замимаро кушоед", "ew_m6_l23"),
        WordItem("ew_w23_3", "Install", "Насб кардан", "In-stall", "Install the program", "Барномаро насб кунед", "ew_m6_l23"),
        WordItem("ew_w23_4", "Update", "Навсозӣ", "Up-date", "Update your software", "Барномаи худро навсозӣ кунед", "ew_m6_l23"),
        WordItem("ew_w23_5", "Settings", "Танзимот", "Set-tings", "Open Settings", "Танзимотро кушоед", "ew_m6_l23"),
        WordItem("ew_w23_6", "Crash", "Аз кор баромадан", "Crash", "The program crashed", "Барнома аз кор баромад", "ew_m6_l23"),
        WordItem("ew_w23_7", "Bug", "Хато", "Bug", "There is a bug", "Хато ҳаст", "ew_m6_l23"),
        WordItem("ew_w23_8", "Click", "Пахш кардан", "Click", "Click the button", "Тугмаро пахш кунед", "ew_m6_l23"),
    ),
    grammarTip = GrammarTip(
        "You need to install... / How do I update...?",
        "Барои дастур аз «You need to + феъл» ва барои дархости кӯмак аз «How do I + феъл?» истифода баред.",
        listOf("You need to install this.", "How do I update the app?", "The program crashed."),
    ),
    exercises = listOf(
        Exercise("ew_e23_1", ExerciseType.MULTIPLE_CHOICE, "«Install» чӣ маъно дорад?", "Install = ...", listOf("Навсозӣ", "Насб кардан", "Пахш кардан", "Кушодан"), "Насб кардан", 1, "Install — Насб кардан"),
        Exercise("ew_e23_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "The program _____. What should I do?", listOf("updated", "installed", "crashed", "clicked"), "crashed", 2, "The program crashed — Барнома аз кор баромад"),
        Exercise("ew_e23_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Танзимотро кушоед»-ро интихоб кунед:", null, listOf("Close Settings", "Open Settings", "Update Settings", "Install Settings"), "Open Settings", 1, "Танзимотро кушоед = Open Settings"),
        Exercise("ew_e23_4", ExerciseType.TYPE_ANSWER, "«Навсозӣ»-ро ба англисӣ нависед:", "Навсозӣ = ?", null, "Update", null, "Навсозӣ — Update"),
        Exercise("ew_e23_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Install" to "Насб кардан", "Update" to "Навсозӣ", "Crash" to "Аз кор баромадан", "Click" to "Пахш кардан")),
        Exercise("ew_e23_6", ExerciseType.MULTIPLE_CHOICE, "«Bug» чӣ маъно дорад?", "Bug = ...", listOf("Барнома", "Хато", "Танзимот", "Тугма"), "Хато", 2, "Bug — Хато"),
        Exercise("ew_e23_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Soft-ware", listOf("Software", "Settings", "Update", "Application"), "Software", 1, "Software — Барнома"),
        Exercise("ew_e23_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Барномаро насб кунед»", null, null, "Install the software", null, "Install + чиз", words = listOf("software", "the", "Install")),
    ),
)

// ── Lesson 24: Видеозанг (Video Call) ──────────

private val engM6L24 = Lesson(
    id = "ew_m6_l24", moduleId = "ew_m6",
    title = "Видеозанг", description = "Видеоконференсия",
    emoji = "\uD83D\uDCF9", orderIndex = 3,
    dialogue = Dialogue(
        "Видеозанг",
        listOf(
            DialogueLine("Firuz", "Can you hear me?", "Шумо маро мешунавед?"),
            DialogueLine("Sarah", "Yes, but your camera is off.", "Ҳа, аммо камераи шумо хомӯш аст."),
            DialogueLine("Firuz", "Let me turn it on. Can you see me now?", "Бигзоред равшан кунам. Ҳоло маро мебинед?"),
            DialogueLine("Sarah", "Yes. Let me share my screen.", "Ҳа. Бигзоред экрани худро нишон диҳам."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w24_1", "Camera", "Камера", "Cam-e-ra", "Turn on the camera", "Камераро равшан кунед", "ew_m6_l24"),
        WordItem("ew_w24_2", "Microphone", "Микрофон", "Mi-cro-phone", "Mute the microphone", "Микрофонро хомӯш кунед", "ew_m6_l24"),
        WordItem("ew_w24_3", "Share", "Нишон додан", "Share", "Share your screen", "Экрани худро нишон диҳед", "ew_m6_l24"),
        WordItem("ew_w24_4", "Mute", "Хомӯш", "Mute", "You are on mute", "Шумо хомӯш ҳастед", "ew_m6_l24"),
        WordItem("ew_w24_5", "Hear", "Шунидан", "Hear", "Can you hear me?", "Маро мешунавед?", "ew_m6_l24"),
        WordItem("ew_w24_6", "See", "Дидан", "See", "Can you see me?", "Маро мебинед?", "ew_m6_l24"),
        WordItem("ew_w24_7", "Join", "Пайваст шудан", "Join", "Join the meeting", "Ба ҷаласа пайваст шавед", "ew_m6_l24"),
        WordItem("ew_w24_8", "Record", "Сабт кардан", "Re-cord", "Record the meeting", "Ҷаласаро сабт кунед", "ew_m6_l24"),
    ),
    grammarTip = GrammarTip(
        "Can you hear me? / Let me share...",
        "Дар видеозанг барои санҷидан аз «Can you hear/see me?» ва барои пешниҳод аз «Let me + феъл» истифода баред.",
        listOf("Can you hear me?", "Can you see me?", "Let me share my screen."),
    ),
    exercises = listOf(
        Exercise("ew_e24_1", ExerciseType.MULTIPLE_CHOICE, "«Mute» чӣ маъно дорад?", "Mute = ...", listOf("Равшан", "Хомӯш", "Баланд", "Тез"), "Хомӯш", 1, "Mute — Хомӯш"),
        Exercise("ew_e24_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Can you _____ me?", listOf("see", "share", "mute", "join"), "hear", 1, "Can you hear me — Маро мешунавед?"),
        Exercise("ew_e24_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ба ҷаласа пайваст шавед»-ро интихоб кунед:", null, listOf("Leave the meeting", "Join the meeting", "Record the meeting", "Share the meeting"), "Join the meeting", 1, "Ба ҷаласа пайваст шавед = Join the meeting"),
        Exercise("ew_e24_4", ExerciseType.TYPE_ANSWER, "«Камера»-ро ба англисӣ нависед:", "Камера = ?", null, "Camera", null, "Камера — Camera"),
        Exercise("ew_e24_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Camera" to "Камера", "Microphone" to "Микрофон", "Hear" to "Шунидан", "See" to "Дидан")),
        Exercise("ew_e24_6", ExerciseType.MULTIPLE_CHOICE, "«Record» чӣ маъно дорад?", "Record = ...", listOf("Пайваст шудан", "Хомӯш кардан", "Сабт кардан", "Нишон додан"), "Сабт кардан", 2, "Record — Сабт кардан"),
        Exercise("ew_e24_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Экрани худро нишон диҳед»", null, null, "Share your screen", null, "Share + чиз", words = listOf("screen", "your", "Share")),
        Exercise("ew_e24_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Can you hear me?\nSarah: _____", null, listOf("Yes, but your camera is off.", "Goodbye.", "I need a doctor."), "Yes, but your camera is off.", 1, "Ҷавоб дар видеозанг"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 7 · САФАРИ КОРӢ (Business Travel)
// ═══════════════════════════════════════════════════

// ── Lesson 25: Фурудгоҳ (Airport) ─────────────

private val engM7L25 = Lesson(
    id = "ew_m7_l25", moduleId = "ew_m7",
    title = "Фурудгоҳ", description = "Дар фурудгоҳ",
    emoji = "\u2708\uFE0F", orderIndex = 0,
    dialogue = Dialogue(
        "Дар фурудгоҳ",
        listOf(
            DialogueLine("Staff", "May I see your boarding pass?", "Метавонам билети парвозро бинам?"),
            DialogueLine("Firuz", "Here it is. Which gate is it?", "Ана. Кадом дарвоза аст?"),
            DialogueLine("Staff", "Gate twelve. Boarding starts at three.", "Дарвозаи дувоздаҳ. Нишастан дар соати се оғоз мешавад."),
            DialogueLine("Firuz", "Where is the baggage claim?", "Ҷои гирифтани борҳо куҷост?"),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w25_1", "Airport", "Фурудгоҳ", "Air-port", "Go to the airport", "Ба фурудгоҳ равед", "ew_m7_l25"),
        WordItem("ew_w25_2", "Flight", "Парвоз", "Flight", "My flight is at ten", "Парвози ман дар соати даҳ", "ew_m7_l25"),
        WordItem("ew_w25_3", "Gate", "Дарвоза", "Gate", "Go to gate five", "Ба дарвозаи панҷ равед", "ew_m7_l25"),
        WordItem("ew_w25_4", "Boarding pass", "Билети парвоз", "Board-ing pass", "Show your boarding pass", "Билети парвозро нишон диҳед", "ew_m7_l25"),
        WordItem("ew_w25_5", "Luggage", "Бор", "Lug-gage", "Check your luggage", "Бори худро супоред", "ew_m7_l25"),
        WordItem("ew_w25_6", "Delay", "Таъхир", "De-lay", "The flight is delayed", "Парвоз таъхир дорад", "ew_m7_l25"),
        WordItem("ew_w25_7", "Arrival", "Омадан", "Ar-ri-val", "Arrivals hall", "Толори омадан", "ew_m7_l25"),
        WordItem("ew_w25_8", "Departure", "Рафтан", "De-par-ture", "Departure time", "Вақти рафтан", "ew_m7_l25"),
    ),
    grammarTip = GrammarTip(
        "Which gate is it? / Where is the...?",
        "Дар фурудгоҳ барои пурсидани ҷой аз «Which gate?» ва «Where is the...?» истифода баред.",
        listOf("Which gate is it?", "Where is the baggage claim?", "When does the flight depart?"),
    ),
    exercises = listOf(
        Exercise("ew_e25_1", ExerciseType.MULTIPLE_CHOICE, "«Flight» чӣ маъно дорад?", "Flight = ...", listOf("Фурудгоҳ", "Парвоз", "Дарвоза", "Бор"), "Парвоз", 1, "Flight — Парвоз"),
        Exercise("ew_e25_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "The flight is _____.", listOf("boarding", "delayed", "arriving", "departing"), "delayed", 1, "The flight is delayed — Парвоз таъхир дорад"),
        Exercise("ew_e25_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Билети парвозро нишон диҳед»-ро интихоб кунед:", null, listOf("Show your passport", "Show your boarding pass", "Show your ticket", "Show your luggage"), "Show your boarding pass", 1, "Билети парвозро нишон диҳед = Show your boarding pass"),
        Exercise("ew_e25_4", ExerciseType.TYPE_ANSWER, "«Фурудгоҳ»-ро ба англисӣ нависед:", "Фурудгоҳ = ?", null, "Airport", null, "Фурудгоҳ — Airport"),
        Exercise("ew_e25_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Flight" to "Парвоз", "Gate" to "Дарвоза", "Luggage" to "Бор", "Delay" to "Таъхир")),
        Exercise("ew_e25_6", ExerciseType.MULTIPLE_CHOICE, "«Departure» чӣ маъно дорад?", "Departure = ...", listOf("Омадан", "Рафтан", "Таъхир", "Парвоз"), "Рафтан", 1, "Departure — Рафтан"),
        Exercise("ew_e25_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ба дарвозаи панҷ равед»", null, null, "Go to gate five", null, "Go to + ҷой", words = listOf("five", "gate", "to", "Go")),
        Exercise("ew_e25_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ar-ri-val", listOf("Departure", "Arrival", "Delay", "Flight"), "Arrival", 1, "Arrival — Омадан"),
    ),
)

// ── Lesson 26: Меҳмонхона (Hotel) ─────────────

private val engM7L26 = Lesson(
    id = "ew_m7_l26", moduleId = "ew_m7",
    title = "Меҳмонхона", description = "Дар меҳмонхона",
    emoji = "\uD83C\uDFE8", orderIndex = 1,
    dialogue = Dialogue(
        "Дар меҳмонхона",
        listOf(
            DialogueLine("Firuz", "I have a reservation under Firuz.", "Ман ба номи Фирӯз ҷой фармоиш додаам."),
            DialogueLine("Receptionist", "Yes, room 305. Here is your key.", "Ҳа, хонаи 305. Ана калиди шумо."),
            DialogueLine("Firuz", "What time is breakfast?", "Субҳона дар кадом вақт?"),
            DialogueLine("Receptionist", "From seven to ten. The restaurant is on the first floor.", "Аз ҳафт то даҳ. Тарабхона дар ошёнаи якум."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w26_1", "Hotel", "Меҳмонхона", "Ho-tel", "Book a hotel", "Меҳмонхона фармоиш диҳед", "ew_m7_l26"),
        WordItem("ew_w26_2", "Reservation", "Фармоиш", "Res-er-va-tion", "I have a reservation", "Ман фармоиш дорам", "ew_m7_l26"),
        WordItem("ew_w26_3", "Key", "Калид", "Key", "Here is your key", "Ана калиди шумо", "ew_m7_l26"),
        WordItem("ew_w26_4", "Breakfast", "Субҳона", "Break-fast", "Breakfast is ready", "Субҳона тайёр аст", "ew_m7_l26"),
        WordItem("ew_w26_5", "Floor", "Ошёна", "Floor", "The first floor", "Ошёнаи якум", "ew_m7_l26"),
        WordItem("ew_w26_6", "Check in", "Бақайдгирӣ", "Check in", "Check in at two", "Бақайдгирӣ дар соати ду", "ew_m7_l26"),
        WordItem("ew_w26_7", "Check out", "Баромадан", "Check out", "Check out by eleven", "Баромадан то соати ёздаҳ", "ew_m7_l26"),
        WordItem("ew_w26_8", "Towel", "Сачоқ", "Tow-el", "I need a towel", "Ман сачоқ лозим", "ew_m7_l26"),
    ),
    grammarTip = GrammarTip(
        "I have a reservation / What time is...?",
        "Дар меҳмонхона барои фармоиш аз «I have a reservation» ва барои вақт аз «What time is...?» истифода баред.",
        listOf("I have a reservation.", "What time is breakfast?", "What time is check out?"),
    ),
    exercises = listOf(
        Exercise("ew_e26_1", ExerciseType.MULTIPLE_CHOICE, "«Reservation» чӣ маъно дорад?", "Reservation = ...", listOf("Калид", "Фармоиш", "Субҳона", "Ошёна"), "Фармоиш", 1, "Reservation — Фармоиш"),
        Exercise("ew_e26_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "What time is _____?", listOf("key", "floor", "breakfast", "towel"), "breakfast", 2, "What time is breakfast — Субҳона кай?"),
        Exercise("ew_e26_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман фармоиш дорам»-ро интихоб кунед:", null, listOf("I need a room", "I have a reservation", "I want a key", "I like the hotel"), "I have a reservation", 1, "Ман фармоиш дорам = I have a reservation"),
        Exercise("ew_e26_4", ExerciseType.TYPE_ANSWER, "«Калид»-ро ба англисӣ нависед:", "Калид = ?", null, "Key", null, "Калид — Key"),
        Exercise("ew_e26_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Hotel" to "Меҳмонхона", "Key" to "Калид", "Breakfast" to "Субҳона", "Floor" to "Ошёна")),
        Exercise("ew_e26_6", ExerciseType.MULTIPLE_CHOICE, "«Towel» чӣ маъно дорад?", "Towel = ...", listOf("Калид", "Сачоқ", "Субҳона", "Хона"), "Сачоқ", 1, "Towel — Сачоқ"),
        Exercise("ew_e26_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман ба номи Фирӯз фармоиш дорам»", null, null, "I have a reservation under Firuz", null, "I have a reservation under + ном", words = listOf("Firuz", "under", "reservation", "a", "have", "I")),
        Exercise("ew_e26_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: What time is breakfast?\nReceptionist: _____", null, listOf("Room 305.", "From seven to ten.", "Here is your key."), "From seven to ten.", 1, "Вақти субҳона"),
    ),
)

// ── Lesson 27: Такси (Taxi) ───────────────────

private val engM7L27 = Lesson(
    id = "ew_m7_l27", moduleId = "ew_m7",
    title = "Такси", description = "Такси гирифтан",
    emoji = "\uD83D\uDE95", orderIndex = 2,
    dialogue = Dialogue(
        "Дар такси",
        listOf(
            DialogueLine("Firuz", "Can you take me to the conference center?", "Метавонед маро ба маркази конференсия баред?"),
            DialogueLine("Driver", "Sure. It will take about fifteen minutes.", "Албатта. Тақрибан понздаҳ дақиқа мегирад."),
            DialogueLine("Firuz", "How much is the fare?", "Нархаш чанд аст?"),
            DialogueLine("Driver", "About twelve pounds. Keep the change.", "Тақрибан дувоздаҳ фунт. Баргардонро нигоҳ доред."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w27_1", "Driver", "Ронанда", "Driv-er", "The taxi driver", "Ронандаи такси", "ew_m7_l27"),
        WordItem("ew_w27_2", "Fare", "Нарх", "Fare", "What is the fare?", "Нархаш чанд?", "ew_m7_l27"),
        WordItem("ew_w27_3", "Destination", "Мақсад", "Des-ti-na-tion", "What is your destination?", "Мақсади шумо куҷо?", "ew_m7_l27"),
        WordItem("ew_w27_4", "Route", "Роҳ", "Route", "Take the fastest route", "Роҳи тезтаринро гиред", "ew_m7_l27"),
        WordItem("ew_w27_5", "Traffic", "Роҳбандӣ", "Traf-fic", "There is heavy traffic", "Роҳбандии сахт ҳаст", "ew_m7_l27"),
        WordItem("ew_w27_6", "Turn", "Гардидан", "Turn", "Turn left here", "Дар инҷо ба чап гардед", "ew_m7_l27"),
        WordItem("ew_w27_7", "Left", "Чап", "Left", "Turn left", "Ба чап гардед", "ew_m7_l27"),
        WordItem("ew_w27_8", "Right", "Рост", "Right", "Turn right", "Ба рост гардед", "ew_m7_l27"),
    ),
    grammarTip = GrammarTip(
        "Can you take me to...? / Turn left/right",
        "Дар такси барои гуфтани мақсад аз «Can you take me to...?» ва барои роҳ аз «Turn left/right» истифода баред.",
        listOf("Can you take me to the hotel?", "Turn left here.", "Turn right at the corner."),
    ),
    exercises = listOf(
        Exercise("ew_e27_1", ExerciseType.MULTIPLE_CHOICE, "«Fare» чӣ маъно дорад?", "Fare = ...", listOf("Роҳ", "Нарх", "Мақсад", "Ронанда"), "Нарх", 1, "Fare — Нарх"),
        Exercise("ew_e27_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "There is heavy _____.", listOf("route", "fare", "traffic", "turn"), "traffic", 2, "heavy traffic — роҳбандии сахт"),
        Exercise("ew_e27_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ба чап гардед»-ро интихоб кунед:", null, listOf("Turn right", "Go straight", "Turn left", "Stop here"), "Turn left", 1, "Ба чап гардед = Turn left"),
        Exercise("ew_e27_4", ExerciseType.TYPE_ANSWER, "«Ронанда»-ро ба англисӣ нависед:", "Ронанда = ?", null, "Driver", null, "Ронанда — Driver"),
        Exercise("ew_e27_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Driver" to "Ронанда", "Traffic" to "Роҳбандӣ", "Left" to "Чап", "Right" to "Рост")),
        Exercise("ew_e27_6", ExerciseType.MULTIPLE_CHOICE, "«Destination» чӣ маъно дорад?", "Destination = ...", listOf("Роҳ", "Нарх", "Мақсад", "Такси"), "Мақсад", 2, "Destination — Мақсад"),
        Exercise("ew_e27_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Маро ба меҳмонхона баред»", null, null, "Take me to the hotel", null, "Take me to + ҷой", words = listOf("hotel", "the", "to", "me", "Take")),
        Exercise("ew_e27_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Des-ti-na-tion", listOf("Driver", "Destination", "Traffic", "Route"), "Destination", 1, "Destination — Мақсад"),
    ),
)

// ── Lesson 28: Барнома ва ҷадвал (Schedule) ───

private val engM7L28 = Lesson(
    id = "ew_m7_l28", moduleId = "ew_m7",
    title = "Ҷадвал", description = "Ба нақша гирифтан",
    emoji = "\uD83D\uDCC5", orderIndex = 3,
    dialogue = Dialogue(
        "Ҷадвали сафар",
        listOf(
            DialogueLine("Firuz", "What is the schedule for tomorrow?", "Ҷадвали пагоҳ чӣ гуна аст?"),
            DialogueLine("Sarah", "The conference starts at nine.", "Конференсия дар соати нӯҳ оғоз мешавад."),
            DialogueLine("Firuz", "When is the dinner?", "Хӯроки шом кай аст?"),
            DialogueLine("Sarah", "Dinner is at seven in the evening.", "Хӯроки шом дар соати ҳафти бегоҳ."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w28_1", "Schedule", "Ҷадвал", "Sched-ule", "Check the schedule", "Ҷадвалро тафтиш кунед", "ew_m7_l28"),
        WordItem("ew_w28_2", "Conference", "Конференсия", "Con-fer-ence", "I have a conference", "Ман конференсия дорам", "ew_m7_l28"),
        WordItem("ew_w28_3", "Tomorrow", "Пагоҳ", "To-mor-row", "See you tomorrow", "То пагоҳ мебинам", "ew_m7_l28"),
        WordItem("ew_w28_4", "Today", "Имрӯз", "To-day", "What is today's plan?", "Нақшаи имрӯз чист?", "ew_m7_l28"),
        WordItem("ew_w28_5", "Dinner", "Хӯроки шом", "Din-ner", "Dinner at seven", "Хӯроки шом дар ҳафт", "ew_m7_l28"),
        WordItem("ew_w28_6", "Evening", "Бегоҳ", "Eve-ning", "Good evening", "Бегоҳ ба хайр", "ew_m7_l28"),
        WordItem("ew_w28_7", "Plan", "Нақша", "Plan", "What is the plan?", "Нақша чист?", "ew_m7_l28"),
        WordItem("ew_w28_8", "Cancel", "Бекор кардан", "Can-cel", "Cancel the meeting", "Ҷаласаро бекор кунед", "ew_m7_l28"),
    ),
    grammarTip = GrammarTip(
        "What is the schedule? / When is...?",
        "Барои пурсидани ҷадвал аз «What is the schedule?» ва барои вақт аз «When is...?» истифода баред.",
        listOf("What is the schedule for today?", "When is the dinner?", "The meeting starts at nine."),
    ),
    exercises = listOf(
        Exercise("ew_e28_1", ExerciseType.MULTIPLE_CHOICE, "«Schedule» чӣ маъно дорад?", "Schedule = ...", listOf("Нақша", "Ҷадвал", "Конференсия", "Шом"), "Ҷадвал", 1, "Schedule — Ҷадвал"),
        Exercise("ew_e28_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "The conference starts at _____.", listOf("seven", "nine", "twelve", "three"), "nine", 1, "starts at nine — дар соати нӯҳ оғоз"),
        Exercise("ew_e28_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ҷаласаро бекор кунед»-ро интихоб кунед:", null, listOf("Start the meeting", "Join the meeting", "Cancel the meeting", "Plan the meeting"), "Cancel the meeting", 2, "Ҷаласаро бекор кунед = Cancel the meeting"),
        Exercise("ew_e28_4", ExerciseType.TYPE_ANSWER, "«Пагоҳ»-ро ба англисӣ нависед:", "Пагоҳ = ?", null, "Tomorrow", null, "Пагоҳ — Tomorrow"),
        Exercise("ew_e28_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Tomorrow" to "Пагоҳ", "Today" to "Имрӯз", "Evening" to "Бегоҳ", "Dinner" to "Хӯроки шом")),
        Exercise("ew_e28_6", ExerciseType.MULTIPLE_CHOICE, "«Cancel» чӣ маъно дорад?", "Cancel = ...", listOf("Оғоз кардан", "Бекор кардан", "Нақша кашидан", "Тамом кардан"), "Бекор кардан", 1, "Cancel — Бекор кардан"),
        Exercise("ew_e28_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Con-fer-ence", listOf("Schedule", "Conference", "Dinner", "Cancel"), "Conference", 1, "Conference — Конференсия"),
        Exercise("ew_e28_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Хӯроки шом дар ҳафт аст»", null, null, "Dinner is at seven", null, "чиз + is at + вақт", words = listOf("seven", "at", "is", "Dinner")),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 8 · МОЛИЯ (Finance)
// ═══════════════════════════════════════════════════

// ── Lesson 29: Маош (Salary) ──────────────────

private val engM8L29 = Lesson(
    id = "ew_m8_l29", moduleId = "ew_m8",
    title = "Маош", description = "Дар бораи маош гап задан",
    emoji = "\uD83D\uDCB0", orderIndex = 0,
    dialogue = Dialogue(
        "Дар бораи маош",
        listOf(
            DialogueLine("Firuz", "When do we get paid?", "Кай маош мегирем?"),
            DialogueLine("HR", "Salary is paid on the last day of the month.", "Маош дар рӯзи охири моҳ пардохт мешавад."),
            DialogueLine("Firuz", "Is there overtime pay?", "Барои кори изофагӣ пул медиҳанд?"),
            DialogueLine("HR", "Yes, overtime is paid extra.", "Ҳа, барои изофакорӣ иловагӣ пардохт мешавад."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w29_1", "Salary", "Маош", "Sal-a-ry", "My salary is good", "Маоши ман хуб аст", "ew_m8_l29"),
        WordItem("ew_w29_2", "Paid", "Пардохт шуда", "Paid", "I got paid today", "Имрӯз маош гирифтам", "ew_m8_l29"),
        WordItem("ew_w29_3", "Overtime", "Изофакорӣ", "O-ver-time", "I worked overtime", "Ман изофа кор кардам", "ew_m8_l29"),
        WordItem("ew_w29_4", "Bonus", "Мукофот", "Bo-nus", "I got a bonus", "Ман мукофот гирифтам", "ew_m8_l29"),
        WordItem("ew_w29_5", "Tax", "Андоз", "Tax", "Pay your taxes", "Андози худро пардохт кунед", "ew_m8_l29"),
        WordItem("ew_w29_6", "Income", "Даромад", "In-come", "My monthly income", "Даромади моҳонаи ман", "ew_m8_l29"),
        WordItem("ew_w29_7", "Payslip", "Варақаи маош", "Pay-slip", "Check your payslip", "Варақаи маошро тафтиш кунед", "ew_m8_l29"),
        WordItem("ew_w29_8", "Deduction", "Кам кардан", "De-duc-tion", "There are deductions", "Кам кардаҳо ҳастанд", "ew_m8_l29"),
    ),
    grammarTip = GrammarTip(
        "When do we get paid? / Is there...?",
        "Барои маош аз «When do we get paid?» ва барои пурсиш аз «Is there + чиз?» истифода баред.",
        listOf("When do we get paid?", "Is there a bonus?", "Is there overtime pay?"),
    ),
    exercises = listOf(
        Exercise("ew_e29_1", ExerciseType.MULTIPLE_CHOICE, "«Tax» чӣ маъно дорад?", "Tax = ...", listOf("Маош", "Андоз", "Мукофот", "Даромад"), "Андоз", 1, "Tax — Андоз"),
        Exercise("ew_e29_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "When do we get _____?", listOf("bonus", "tax", "paid", "income"), "paid", 2, "get paid — маош гирифтан"),
        Exercise("ew_e29_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман мукофот гирифтам»-ро интихоб кунед:", null, listOf("I got a salary", "I got a bonus", "I got paid", "I got a deduction"), "I got a bonus", 1, "Ман мукофот гирифтам = I got a bonus"),
        Exercise("ew_e29_4", ExerciseType.TYPE_ANSWER, "«Даромад»-ро ба англисӣ нависед:", "Даромад = ?", null, "Income", null, "Даромад — Income"),
        Exercise("ew_e29_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Salary" to "Маош", "Tax" to "Андоз", "Bonus" to "Мукофот", "Income" to "Даромад")),
        Exercise("ew_e29_6", ExerciseType.MULTIPLE_CHOICE, "«Overtime» чӣ маъно дорад?", "Overtime = ...", listOf("Маош", "Андоз", "Изофакорӣ", "Мукофот"), "Изофакорӣ", 2, "Overtime — Изофакорӣ"),
        Exercise("ew_e29_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Андози худро пардохт кунед»", null, null, "Pay your taxes", null, "Pay + чиз", words = listOf("taxes", "your", "Pay")),
        Exercise("ew_e29_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A De-duc-tion", listOf("Bonus", "Deduction", "Income", "Salary"), "Deduction", 1, "Deduction — Кам кардан"),
    ),
)

// ── Lesson 30: Буҷет (Budget) ─────────────────

private val engM8L30 = Lesson(
    id = "ew_m8_l30", moduleId = "ew_m8",
    title = "Буҷет", description = "Идоракунии буҷет",
    emoji = "\uD83D\uDCB3", orderIndex = 1,
    dialogue = Dialogue(
        "Дар бораи буҷет",
        listOf(
            DialogueLine("Manager", "We need to review the budget.", "Мо бояд буҷетро баррасӣ кунем."),
            DialogueLine("Firuz", "Our expenses are too high.", "Хароҷоти мо хеле зиёд аст."),
            DialogueLine("Manager", "We need to save money.", "Мо бояд пул сарфа кунем."),
            DialogueLine("Firuz", "I will prepare a report on spending.", "Ман гузориш дар бораи харҷ тайёр мекунам."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w30_1", "Budget", "Буҷет", "Budg-et", "Plan the budget", "Буҷетро нақша кашед", "ew_m8_l30"),
        WordItem("ew_w30_2", "Expense", "Хароҷот", "Ex-pense", "Reduce expenses", "Хароҷотро кам кунед", "ew_m8_l30"),
        WordItem("ew_w30_3", "Save", "Сарфа кардан", "Save", "Save money", "Пул сарфа кунед", "ew_m8_l30"),
        WordItem("ew_w30_4", "Spend", "Харҷ кардан", "Spend", "Don't spend too much", "Зиёд харҷ накунед", "ew_m8_l30"),
        WordItem("ew_w30_5", "Profit", "Фоида", "Prof-it", "The profit is high", "Фоида зиёд аст", "ew_m8_l30"),
        WordItem("ew_w30_6", "Loss", "Зарар", "Loss", "We had a loss", "Мо зарар доштем", "ew_m8_l30"),
        WordItem("ew_w30_7", "Report", "Гузориш", "Re-port", "Write a report", "Гузориш нависед", "ew_m8_l30"),
        WordItem("ew_w30_8", "Review", "Баррасӣ", "Re-view", "Review the numbers", "Рақамҳоро баррасӣ кунед", "ew_m8_l30"),
    ),
    grammarTip = GrammarTip(
        "We need to save... / Our expenses are...",
        "Барои буҷет аз «We need to + феъл» ва барои тавсиф аз «Our + исм + is/are + сифат» истифода баред.",
        listOf("We need to save money.", "Our expenses are too high.", "The profit is good."),
    ),
    exercises = listOf(
        Exercise("ew_e30_1", ExerciseType.MULTIPLE_CHOICE, "«Expense» чӣ маъно дорад?", "Expense = ...", listOf("Фоида", "Хароҷот", "Буҷет", "Гузориш"), "Хароҷот", 1, "Expense — Хароҷот"),
        Exercise("ew_e30_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "We need to _____ money.", listOf("spend", "lose", "save", "report"), "save", 2, "save money — пул сарфа кардан"),
        Exercise("ew_e30_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Фоида зиёд аст»-ро интихоб кунед:", null, listOf("The loss is high", "The profit is high", "The budget is high", "The expense is high"), "The profit is high", 1, "Фоида зиёд аст = The profit is high"),
        Exercise("ew_e30_4", ExerciseType.TYPE_ANSWER, "«Гузориш»-ро ба англисӣ нависед:", "Гузориш = ?", null, "Report", null, "Гузориш — Report"),
        Exercise("ew_e30_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Save" to "Сарфа кардан", "Spend" to "Харҷ кардан", "Profit" to "Фоида", "Loss" to "Зарар")),
        Exercise("ew_e30_6", ExerciseType.MULTIPLE_CHOICE, "«Budget» чӣ маъно дорад?", "Budget = ...", listOf("Буҷет", "Хароҷот", "Зарар", "Фоида"), "Буҷет", 1, "Budget — Буҷет"),
        Exercise("ew_e30_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Гузориш нависед»", null, null, "Write a report", null, "Write + чиз", words = listOf("report", "a", "Write")),
        Exercise("ew_e30_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nManager: Our expenses are too high.\nFiruz: _____", null, listOf("I got a bonus.", "We need to save money.", "Goodbye."), "We need to save money.", 1, "Ҷавоб дар бораи сарфа"),
    ),
)

// ── Lesson 31: Суғурта (Insurance) ────────────

private val engM8L31 = Lesson(
    id = "ew_m8_l31", moduleId = "ew_m8",
    title = "Суғурта", description = "Суғуртаи тиббӣ ва корӣ",
    emoji = "\uD83D\uDEE1\uFE0F", orderIndex = 2,
    dialogue = Dialogue(
        "Дар бораи суғурта",
        listOf(
            DialogueLine("HR", "Do you have health insurance?", "Шумо суғуртаи тиббӣ доред?"),
            DialogueLine("Firuz", "No, I need to get one.", "Не, ман бояд гирам."),
            DialogueLine("HR", "The company provides insurance for employees.", "Ширкат барои коргарон суғурта медиҳад."),
            DialogueLine("Firuz", "What does it cover?", "Чиро фаро мегирад?"),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w31_1", "Insurance", "Суғурта", "In-sur-ance", "Health insurance", "Суғуртаи тиббӣ", "ew_m8_l31"),
        WordItem("ew_w31_2", "Health", "Тандурустӣ", "Health", "Health is important", "Тандурустӣ муҳим аст", "ew_m8_l31"),
        WordItem("ew_w31_3", "Cover", "Фаро гирифтан", "Cov-er", "It covers dental care", "Духтури дандонро фаро мегирад", "ew_m8_l31"),
        WordItem("ew_w31_4", "Claim", "Даъво", "Claim", "File a claim", "Даъво пешниҳод кунед", "ew_m8_l31"),
        WordItem("ew_w31_5", "Policy", "Полис", "Pol-i-cy", "Read the policy", "Полисро хонед", "ew_m8_l31"),
        WordItem("ew_w31_6", "Premium", "Пардохти моҳона", "Pre-mi-um", "Pay the premium", "Пардохти моҳонаро диҳед", "ew_m8_l31"),
        WordItem("ew_w31_7", "Benefit", "Имтиёз", "Ben-e-fit", "Employee benefits", "Имтиёзҳои коргарон", "ew_m8_l31"),
        WordItem("ew_w31_8", "Provide", "Додан", "Pro-vide", "The company provides this", "Ширкат инро медиҳад", "ew_m8_l31"),
    ),
    grammarTip = GrammarTip(
        "Do you have...? / The company provides...",
        "Барои пурсиши доштан аз «Do you have...?» ва барои хизматрасонӣ аз «The company provides...» истифода баред.",
        listOf("Do you have insurance?", "The company provides health insurance.", "What does it cover?"),
    ),
    exercises = listOf(
        Exercise("ew_e31_1", ExerciseType.MULTIPLE_CHOICE, "«Insurance» чӣ маъно дорад?", "Insurance = ...", listOf("Тандурустӣ", "Суғурта", "Имтиёз", "Полис"), "Суғурта", 1, "Insurance — Суғурта"),
        Exercise("ew_e31_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "The company _____ insurance.", listOf("claims", "covers", "provides", "needs"), "provides", 2, "company provides — ширкат медиҳад"),
        Exercise("ew_e31_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Шумо суғуртаи тиббӣ доред?»-ро интихоб кунед:", null, listOf("Do you have health insurance?", "Do you need health insurance?", "Do you want health insurance?", "Do you like health insurance?"), "Do you have health insurance?", 1, "Суғуртаи тиббӣ доред? = Do you have health insurance?"),
        Exercise("ew_e31_4", ExerciseType.TYPE_ANSWER, "«Имтиёз»-ро ба англисӣ нависед:", "Имтиёз = ?", null, "Benefit", null, "Имтиёз — Benefit"),
        Exercise("ew_e31_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Insurance" to "Суғурта", "Health" to "Тандурустӣ", "Claim" to "Даъво", "Benefit" to "Имтиёз")),
        Exercise("ew_e31_6", ExerciseType.MULTIPLE_CHOICE, "«Policy» чӣ маъно дорад?", "Policy = ...", listOf("Полис", "Даъво", "Пардохт", "Имтиёз"), "Полис", 1, "Policy — Полис"),
        Exercise("ew_e31_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A In-sur-ance", listOf("Insurance", "Benefit", "Premium", "Health"), "Insurance", 1, "Insurance — Суғурта"),
        Exercise("ew_e31_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Даъво пешниҳод кунед»", null, null, "File a claim", null, "File + чиз", words = listOf("claim", "a", "File")),
    ),
)

// ── Lesson 32: Сармоягузорӣ (Investment) ──────

private val engM8L32 = Lesson(
    id = "ew_m8_l32", moduleId = "ew_m8",
    title = "Сармоягузорӣ", description = "Сармоягузорӣ ва пасандоз",
    emoji = "\uD83D\uDCC8", orderIndex = 3,
    dialogue = Dialogue(
        "Дар бораи сармоягузорӣ",
        listOf(
            DialogueLine("Tom", "Do you invest your money?", "Шумо пули худро сармоягузорӣ мекунед?"),
            DialogueLine("Firuz", "I save some money every month.", "Ман ҳар моҳ каме пул сарфа мекунам."),
            DialogueLine("Tom", "You should think about investing.", "Шумо бояд дар бораи сармоягузорӣ фикр кунед."),
            DialogueLine("Firuz", "What is the risk?", "Хатар чист?"),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w32_1", "Invest", "Сармоягузорӣ", "In-vest", "Invest your money", "Пули худро сармоягузорӣ кунед", "ew_m8_l32"),
        WordItem("ew_w32_2", "Savings", "Пасандоз", "Sav-ings", "I have savings", "Ман пасандоз дорам", "ew_m8_l32"),
        WordItem("ew_w32_3", "Risk", "Хатар", "Risk", "There is a risk", "Хатар ҳаст", "ew_m8_l32"),
        WordItem("ew_w32_4", "Interest", "Фоиз", "In-ter-est", "The interest rate", "Меъёри фоиз", "ew_m8_l32"),
        WordItem("ew_w32_5", "Loan", "Қарз", "Loan", "Take a loan", "Қарз гиред", "ew_m8_l32"),
        WordItem("ew_w32_6", "Debt", "Қарздорӣ", "Debt", "Pay off the debt", "Қарздориро пардохт кунед", "ew_m8_l32"),
        WordItem("ew_w32_7", "Value", "Арзиш", "Val-ue", "The value went up", "Арзиш боло рафт", "ew_m8_l32"),
        WordItem("ew_w32_8", "Growth", "Афзоиш", "Growth", "Economic growth", "Афзоиши иқтисодӣ", "ew_m8_l32"),
    ),
    grammarTip = GrammarTip(
        "You should think about... / What is the risk?",
        "Барои маслиҳат аз «You should + феъл» ва барои пурсиш аз «What is the + исм?» истифода баред.",
        listOf("You should invest.", "What is the risk?", "You should save more money."),
    ),
    exercises = listOf(
        Exercise("ew_e32_1", ExerciseType.MULTIPLE_CHOICE, "«Risk» чӣ маъно дорад?", "Risk = ...", listOf("Фоиз", "Хатар", "Қарз", "Арзиш"), "Хатар", 1, "Risk — Хатар"),
        Exercise("ew_e32_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I _____ some money every month.", listOf("invest", "spend", "save", "lose"), "save", 2, "save money — пул сарфа кардан"),
        Exercise("ew_e32_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Қарздориро пардохт кунед»-ро интихоб кунед:", null, listOf("Take a loan", "Pay off the debt", "Save your money", "Invest your money"), "Pay off the debt", 1, "Қарздориро пардохт кунед = Pay off the debt"),
        Exercise("ew_e32_4", ExerciseType.TYPE_ANSWER, "«Пасандоз»-ро ба англисӣ нависед:", "Пасандоз = ?", null, "Savings", null, "Пасандоз — Savings"),
        Exercise("ew_e32_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Invest" to "Сармоягузорӣ", "Loan" to "Қарз", "Debt" to "Қарздорӣ", "Growth" to "Афзоиш")),
        Exercise("ew_e32_6", ExerciseType.MULTIPLE_CHOICE, "«Interest» чӣ маъно дорад?", "Interest = ...", listOf("Қарз", "Фоиз", "Арзиш", "Хатар"), "Фоиз", 1, "Interest — Фоиз"),
        Exercise("ew_e32_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Шумо бояд пул сарфа кунед»", null, null, "You should save money", null, "You should + феъл", words = listOf("money", "save", "should", "You")),
        Exercise("ew_e32_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A In-vest", listOf("Interest", "Invest", "Savings", "Growth"), "Invest", 1, "Invest — Сармоягузорӣ"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 9 · ФАРҲАНГИ АНГЛИСӢ (English Culture)
// ═══════════════════════════════════════════════════

// ── Lesson 33: Идҳо (Holidays) ────────────────

private val engM9L33 = Lesson(
    id = "ew_m9_l33", moduleId = "ew_m9",
    title = "Идҳо", description = "Идҳо ва анъанаҳо",
    emoji = "\uD83C\uDF89", orderIndex = 0,
    dialogue = Dialogue(
        "Дар бораи идҳо",
        listOf(
            DialogueLine("Tom", "What is your favourite holiday?", "Иди дӯстдоштаи шумо кадом аст?"),
            DialogueLine("Firuz", "I like New Year and Christmas.", "Ман Соли нав ва Рождестворо дӯст медорам."),
            DialogueLine("Tom", "We celebrate Thanksgiving in November.", "Мо Рӯзи Шукргузориро дар ноябр ҷашн мегирем."),
            DialogueLine("Firuz", "In Tajikistan we celebrate Navruz.", "Дар Тоҷикистон мо Наврӯзро ҷашн мегирем."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w33_1", "Holiday", "Ид", "Hol-i-day", "Happy holiday!", "Иди муборак!", "ew_m9_l33"),
        WordItem("ew_w33_2", "Celebrate", "Ҷашн гирифтан", "Cel-e-brate", "We celebrate together", "Мо якҷоя ҷашн мегирем", "ew_m9_l33"),
        WordItem("ew_w33_3", "Christmas", "Рождество", "Christ-mas", "Merry Christmas!", "Рождествои муборак!", "ew_m9_l33"),
        WordItem("ew_w33_4", "Gift", "Тӯҳфа", "Gift", "I bought a gift", "Ман тӯҳфа харидам", "ew_m9_l33"),
        WordItem("ew_w33_5", "Festival", "Фестивал", "Fes-ti-val", "A music festival", "Фестивали мусиқӣ", "ew_m9_l33"),
        WordItem("ew_w33_6", "Tradition", "Анъана", "Tra-di-tion", "An old tradition", "Анъанаи кӯҳна", "ew_m9_l33"),
        WordItem("ew_w33_7", "Party", "Зиёфат", "Par-ty", "Come to the party", "Ба зиёфат биёед", "ew_m9_l33"),
        WordItem("ew_w33_8", "Invitation", "Даъватнома", "In-vi-ta-tion", "Thank you for the invitation", "Барои даъватнома ташаккур", "ew_m9_l33"),
    ),
    grammarTip = GrammarTip(
        "We celebrate... / What is your favourite...?",
        "Барои ид аз «We celebrate + ид» ва барои пурсиш аз «What is your favourite...?» истифода баред.",
        listOf("We celebrate Christmas.", "What is your favourite holiday?", "I celebrate Navruz."),
    ),
    exercises = listOf(
        Exercise("ew_e33_1", ExerciseType.MULTIPLE_CHOICE, "«Celebrate» чӣ маъно дорад?", "Celebrate = ...", listOf("Тӯҳфа додан", "Ҷашн гирифтан", "Даъват кардан", "Омадан"), "Ҷашн гирифтан", 1, "Celebrate — Ҷашн гирифтан"),
        Exercise("ew_e33_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "What is your favourite _____?", listOf("gift", "party", "holiday", "tradition"), "holiday", 2, "favourite holiday — иди дӯстдошта"),
        Exercise("ew_e33_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ба зиёфат биёед»-ро интихоб кунед:", null, listOf("Thank you for the party", "Come to the party", "I like the party", "Leave the party"), "Come to the party", 1, "Ба зиёфат биёед = Come to the party"),
        Exercise("ew_e33_4", ExerciseType.TYPE_ANSWER, "«Тӯҳфа»-ро ба англисӣ нависед:", "Тӯҳфа = ?", null, "Gift", null, "Тӯҳфа — Gift"),
        Exercise("ew_e33_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Holiday" to "Ид", "Gift" to "Тӯҳфа", "Tradition" to "Анъана", "Party" to "Зиёфат")),
        Exercise("ew_e33_6", ExerciseType.MULTIPLE_CHOICE, "«Invitation» чӣ маъно дорад?", "Invitation = ...", listOf("Зиёфат", "Тӯҳфа", "Даъватнома", "Анъана"), "Даъватнома", 2, "Invitation — Даъватнома"),
        Exercise("ew_e33_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Cel-e-brate", listOf("Holiday", "Celebrate", "Festival", "Tradition"), "Celebrate", 1, "Celebrate — Ҷашн гирифтан"),
        Exercise("ew_e33_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Мо Рождестворо ҷашн мегирем»", null, null, "We celebrate Christmas", null, "We celebrate + ид", words = listOf("Christmas", "celebrate", "We")),
    ),
)

// ── Lesson 34: Таом (Food) ────────────────────

private val engM9L34 = Lesson(
    id = "ew_m9_l34", moduleId = "ew_m9",
    title = "Таом", description = "Таомҳои англисӣ",
    emoji = "\uD83C\uDF54", orderIndex = 1,
    dialogue = Dialogue(
        "Дар тарабхона",
        listOf(
            DialogueLine("Waiter", "What would you like to order?", "Шумо чӣ фармоиш медиҳед?"),
            DialogueLine("Firuz", "I would like fish and chips, please.", "Ман моҳӣ ва чипсҳо мехоҳам, лутфан."),
            DialogueLine("Waiter", "Would you like a drink?", "Нӯшокӣ мехоҳед?"),
            DialogueLine("Firuz", "A cup of tea, please.", "Як пиёла чой, лутфан."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w34_1", "Order", "Фармоиш", "Or-der", "I want to order", "Ман фармоиш додан мехоҳам", "ew_m9_l34"),
        WordItem("ew_w34_2", "Menu", "Меню", "Men-u", "May I see the menu?", "Менюро нишон диҳед?", "ew_m9_l34"),
        WordItem("ew_w34_3", "Drink", "Нӯшокӣ", "Drink", "What would you like to drink?", "Чӣ нӯшокӣ мехоҳед?", "ew_m9_l34"),
        WordItem("ew_w34_4", "Tea", "Чой", "Tea", "A cup of tea", "Як пиёла чой", "ew_m9_l34"),
        WordItem("ew_w34_5", "Coffee", "Қаҳва", "Cof-fee", "Black coffee", "Қаҳваи сиёҳ", "ew_m9_l34"),
        WordItem("ew_w34_6", "Bill", "Ҳисоб", "Bill", "The bill, please", "Ҳисобро лутфан", "ew_m9_l34"),
        WordItem("ew_w34_7", "Waiter", "Пешхизмат", "Wait-er", "Call the waiter", "Пешхизматро даъват кунед", "ew_m9_l34"),
        WordItem("ew_w34_8", "Delicious", "Болаззат", "De-li-cious", "The food is delicious", "Таом болаззат аст", "ew_m9_l34"),
    ),
    grammarTip = GrammarTip(
        "I would like... / May I have...?",
        "Дар тарабхона барои фармоиш аз «I would like + чиз» ва «May I have...?» истифода баред.",
        listOf("I would like fish and chips.", "May I have the bill?", "I would like a cup of tea."),
    ),
    exercises = listOf(
        Exercise("ew_e34_1", ExerciseType.MULTIPLE_CHOICE, "«Menu» чӣ маъно дорад?", "Menu = ...", listOf("Ҳисоб", "Фармоиш", "Меню", "Нӯшокӣ"), "Меню", 2, "Menu — Меню"),
        Exercise("ew_e34_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I would _____ fish and chips.", listOf("want", "like", "order", "have"), "like", 1, "I would like — Ман мехоҳам"),
        Exercise("ew_e34_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ҳисобро лутфан»-ро интихоб кунед:", null, listOf("The menu, please", "The bill, please", "The drink, please", "The food, please"), "The bill, please", 1, "Ҳисобро лутфан = The bill, please"),
        Exercise("ew_e34_4", ExerciseType.TYPE_ANSWER, "«Болаззат»-ро ба англисӣ нависед:", "Болаззат = ?", null, "Delicious", null, "Болаззат — Delicious"),
        Exercise("ew_e34_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Order" to "Фармоиш", "Tea" to "Чой", "Coffee" to "Қаҳва", "Bill" to "Ҳисоб")),
        Exercise("ew_e34_6", ExerciseType.MULTIPLE_CHOICE, "«Waiter» чӣ маъно дорад?", "Waiter = ...", listOf("Ронанда", "Пешхизмат", "Фурӯшанда", "Духтур"), "Пешхизмат", 1, "Waiter — Пешхизмат"),
        Exercise("ew_e34_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Як пиёла чой, лутфан»", null, null, "A cup of tea, please", null, "A cup of + чиз", words = listOf("please", "tea,", "of", "cup", "A")),
        Exercise("ew_e34_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nWaiter: What would you like to order?\nFiruz: _____", null, listOf("The bill, please.", "I would like fish and chips.", "Goodbye."), "I would like fish and chips.", 1, "Фармоиши таом"),
    ),
)

// ── Lesson 35: Обу ҳаво (Weather) ─────────────

private val engM9L35 = Lesson(
    id = "ew_m9_l35", moduleId = "ew_m9",
    title = "Обу ҳаво", description = "Дар бораи обу ҳаво гап задан",
    emoji = "\u2600\uFE0F", orderIndex = 2,
    dialogue = Dialogue(
        "Дар бораи обу ҳаво",
        listOf(
            DialogueLine("Tom", "How is the weather today?", "Имрӯз обу ҳаво чӣ тавр?"),
            DialogueLine("Firuz", "It is cold and rainy.", "Хунук ва борон аст."),
            DialogueLine("Tom", "You should bring an umbrella.", "Шумо бояд чатр гиред."),
            DialogueLine("Firuz", "In summer it is usually warm and sunny.", "Дар тобистон одатан гарм ва офтобӣ аст."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w35_1", "Weather", "Обу ҳаво", "Weath-er", "The weather is nice", "Обу ҳаво хуб аст", "ew_m9_l35"),
        WordItem("ew_w35_2", "Cold", "Хунук", "Cold", "It is cold today", "Имрӯз хунук аст", "ew_m9_l35"),
        WordItem("ew_w35_3", "Warm", "Гарм", "Warm", "It is warm outside", "Берун гарм аст", "ew_m9_l35"),
        WordItem("ew_w35_4", "Rain", "Борон", "Rain", "It is raining", "Борон меборад", "ew_m9_l35"),
        WordItem("ew_w35_5", "Snow", "Барф", "Snow", "It is snowing", "Барф меборад", "ew_m9_l35"),
        WordItem("ew_w35_6", "Sunny", "Офтобӣ", "Sun-ny", "A sunny day", "Рӯзи офтобӣ", "ew_m9_l35"),
        WordItem("ew_w35_7", "Wind", "Шамол", "Wind", "The wind is strong", "Шамол сахт аст", "ew_m9_l35"),
        WordItem("ew_w35_8", "Umbrella", "Чатр", "Um-brel-la", "Bring an umbrella", "Чатр гиред", "ew_m9_l35"),
    ),
    grammarTip = GrammarTip(
        "It is cold/warm / It is raining",
        "Барои обу ҳаво аз «It is + сифат» ва барои ҳодиса аз «It is + феъл-ing» истифода баред.",
        listOf("It is cold today.", "It is raining.", "It will be sunny tomorrow."),
    ),
    exercises = listOf(
        Exercise("ew_e35_1", ExerciseType.MULTIPLE_CHOICE, "«Weather» чӣ маъно дорад?", "Weather = ...", listOf("Борон", "Обу ҳаво", "Шамол", "Офтоб"), "Обу ҳаво", 1, "Weather — Обу ҳаво"),
        Exercise("ew_e35_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "It is _____ and rainy.", listOf("warm", "sunny", "cold", "windy"), "cold", 2, "cold and rainy — хунук ва борон"),
        Exercise("ew_e35_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Борон меборад»-ро интихоб кунед:", null, listOf("It is snowing", "It is raining", "It is sunny", "It is windy"), "It is raining", 1, "Борон меборад = It is raining"),
        Exercise("ew_e35_4", ExerciseType.TYPE_ANSWER, "«Чатр»-ро ба англисӣ нависед:", "Чатр = ?", null, "Umbrella", null, "Чатр — Umbrella"),
        Exercise("ew_e35_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Cold" to "Хунук", "Warm" to "Гарм", "Rain" to "Борон", "Snow" to "Барф")),
        Exercise("ew_e35_6", ExerciseType.MULTIPLE_CHOICE, "«Sunny» чӣ маъно дорад?", "Sunny = ...", listOf("Хунук", "Офтобӣ", "Борон", "Шамол"), "Офтобӣ", 1, "Sunny — Офтобӣ"),
        Exercise("ew_e35_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Имрӯз хунук аст»", null, null, "It is cold today", null, "It is + сифат + вақт", words = listOf("today", "cold", "is", "It")),
        Exercise("ew_e35_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Um-brel-la", listOf("Weather", "Umbrella", "Wind", "Snow"), "Umbrella", 1, "Umbrella — Чатр"),
    ),
)

// ── Lesson 36: Одоби англисӣ (English Manners) ─

private val engM9L36 = Lesson(
    id = "ew_m9_l36", moduleId = "ew_m9",
    title = "Одоби англисӣ", description = "Одоб ва рафтори фарҳангӣ",
    emoji = "\uD83C\uDDEC\uD83C\uDDE7", orderIndex = 3,
    dialogue = Dialogue(
        "Одоби англисӣ",
        listOf(
            DialogueLine("Tom", "In England we say 'please' and 'thank you' a lot.", "Дар Англия мо зиёд 'please' ва 'thank you' мегӯем."),
            DialogueLine("Firuz", "That is very polite.", "Ин хеле боодоб аст."),
            DialogueLine("Tom", "We also queue and wait our turn.", "Мо инчунин дар навбат меистем."),
            DialogueLine("Firuz", "I will remember that. Excuse me is also important.", "Ман дар хотир мемонам. 'Excuse me' ҳам муҳим аст."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w36_1", "Polite", "Боодоб", "Po-lite", "Be polite", "Боодоб бошед", "ew_m9_l36"),
        WordItem("ew_w36_2", "Rude", "Беодоб", "Rude", "Don't be rude", "Беодоб набошед", "ew_m9_l36"),
        WordItem("ew_w36_3", "Excuse me", "Бубахшед", "Ex-cuse me", "Excuse me, can you help?", "Бубахшед, кӯмак карда метавонед?", "ew_m9_l36"),
        WordItem("ew_w36_4", "Queue", "Навбат", "Queue", "Please queue here", "Лутфан дар инҷо навбат истед", "ew_m9_l36"),
        WordItem("ew_w36_5", "Respect", "Эҳтиром", "Re-spect", "Show respect", "Эҳтиром нишон диҳед", "ew_m9_l36"),
        WordItem("ew_w36_6", "Apology", "Узр", "A-pol-o-gy", "I owe you an apology", "Ман аз шумо узр мепурсам", "ew_m9_l36"),
        WordItem("ew_w36_7", "Greet", "Салом кардан", "Greet", "Greet your colleagues", "Бо ҳамкорон салом кунед", "ew_m9_l36"),
        WordItem("ew_w36_8", "Manner", "Одоб", "Man-ner", "Good manners", "Одоби хуб", "ew_m9_l36"),
    ),
    grammarTip = GrammarTip(
        "Excuse me / Could you please...?",
        "Барои одоби хуб аз «Excuse me» ва «Could you please + феъл?» истифода баред.",
        listOf("Excuse me, where is the exit?", "Could you please help me?", "Thank you very much."),
    ),
    exercises = listOf(
        Exercise("ew_e36_1", ExerciseType.MULTIPLE_CHOICE, "«Polite» чӣ маъно дорад?", "Polite = ...", listOf("Беодоб", "Боодоб", "Хунук", "Тез"), "Боодоб", 1, "Polite — Боодоб"),
        Exercise("ew_e36_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Don't be _____.", listOf("polite", "rude", "kind", "nice"), "rude", 1, "Don't be rude — Беодоб набошед"),
        Exercise("ew_e36_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Эҳтиром нишон диҳед»-ро интихоб кунед:", null, listOf("Show respect", "Be polite", "Say thank you", "Queue here"), "Show respect", 1, "Эҳтиром нишон диҳед = Show respect"),
        Exercise("ew_e36_4", ExerciseType.TYPE_ANSWER, "«Навбат»-ро ба англисӣ нависед:", "Навбат = ?", null, "Queue", null, "Навбат — Queue"),
        Exercise("ew_e36_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Polite" to "Боодоб", "Rude" to "Беодоб", "Respect" to "Эҳтиром", "Manner" to "Одоб")),
        Exercise("ew_e36_6", ExerciseType.MULTIPLE_CHOICE, "«Apology» чӣ маъно дорад?", "Apology = ...", listOf("Салом", "Узр", "Одоб", "Навбат"), "Узр", 1, "Apology — Узр"),
        Exercise("ew_e36_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Бубахшед, кӯмак карда метавонед?»", null, null, "Excuse me, can you help?", null, "Excuse me + савол", words = listOf("help?", "you", "can", "me,", "Excuse")),
        Exercise("ew_e36_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Po-lite", listOf("Rude", "Polite", "Queue", "Manner"), "Polite", 1, "Polite — Боодоб"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 10 · МУСТАҚИЛИЯТ (Independence)
// ═══════════════════════════════════════════════════

// ── Lesson 37: Озодона гап задан (Free Speaking) ─

private val engM10L37 = Lesson(
    id = "ew_m10_l37", moduleId = "ew_m10",
    title = "Озодона гап задан", description = "Гуфтугӯи озод",
    emoji = "\uD83D\uDDE3\uFE0F", orderIndex = 0,
    dialogue = Dialogue(
        "Дар кор бо забони озод",
        listOf(
            DialogueLine("Firuz", "I think our project is going well.", "Ман фикр мекунам лоиҳаи мо хуб пеш меравад."),
            DialogueLine("Sarah", "I agree. The team has worked hard.", "Ман розӣ. Гурӯҳ сахт кор кард."),
            DialogueLine("Firuz", "However, we need more time for testing.", "Вале, барои санҷиш вақти бештар лозим."),
            DialogueLine("Sarah", "That is a good point. Let me talk to the manager.", "Ин фикри хуб аст. Бигзоред бо роҳбар гап занам."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w37_1", "Opinion", "Фикр", "O-pin-ion", "In my opinion", "Ба фикри ман", "ew_m10_l37"),
        WordItem("ew_w37_2", "However", "Вале", "How-ev-er", "However, I disagree", "Вале, ман норозӣ", "ew_m10_l37"),
        WordItem("ew_w37_3", "Although", "Гарчанде", "Al-though", "Although it is hard", "Гарчанде сахт аст", "ew_m10_l37"),
        WordItem("ew_w37_4", "Therefore", "Аз ин рӯ", "There-fore", "Therefore, we need time", "Аз ин рӯ, вақт лозим", "ew_m10_l37"),
        WordItem("ew_w37_5", "Suggest", "Пешниҳод кардан", "Sug-gest", "I suggest we meet", "Ман пешниҳод мекунам вохӯрем", "ew_m10_l37"),
        WordItem("ew_w37_6", "Consider", "Фикр кардан", "Con-sid-er", "Consider this option", "Ин имконро фикр кунед", "ew_m10_l37"),
        WordItem("ew_w37_7", "Conclusion", "Хулоса", "Con-clu-sion", "In conclusion", "Дар хулоса", "ew_m10_l37"),
        WordItem("ew_w37_8", "Furthermore", "Илова бар ин", "Fur-ther-more", "Furthermore, we need resources", "Илова бар ин, захираҳо лозим", "ew_m10_l37"),
    ),
    grammarTip = GrammarTip(
        "In my opinion... / However... / Therefore...",
        "Барои гуфтугӯи пешрафта аз калимаҳои пайвасткунанда: «However», «Therefore», «Furthermore», «In conclusion» истифода баред.",
        listOf("In my opinion, this is good.", "However, we need more time.", "Therefore, I suggest a meeting."),
    ),
    exercises = listOf(
        Exercise("ew_e37_1", ExerciseType.MULTIPLE_CHOICE, "«However» чӣ маъно дорад?", "However = ...", listOf("Аз ин рӯ", "Вале", "Илова", "Хулоса"), "Вале", 1, "However — Вале"),
        Exercise("ew_e37_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "In my _____, this is a good idea.", listOf("conclusion", "opinion", "suggestion", "point"), "opinion", 1, "In my opinion — Ба фикри ман"),
        Exercise("ew_e37_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман пешниҳод мекунам вохӯрем»-ро интихоб кунед:", null, listOf("I think we should meet", "I suggest we meet", "I want to meet", "I need to meet"), "I suggest we meet", 1, "Пешниҳод мекунам = I suggest"),
        Exercise("ew_e37_4", ExerciseType.TYPE_ANSWER, "«Хулоса»-ро ба англисӣ нависед:", "Хулоса = ?", null, "Conclusion", null, "Хулоса — Conclusion"),
        Exercise("ew_e37_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("However" to "Вале", "Therefore" to "Аз ин рӯ", "Although" to "Гарчанде", "Furthermore" to "Илова бар ин")),
        Exercise("ew_e37_6", ExerciseType.MULTIPLE_CHOICE, "«Suggest» чӣ маъно дорад?", "Suggest = ...", listOf("Розӣ шудан", "Пешниҳод кардан", "Рад кардан", "Фикр кардан"), "Пешниҳод кардан", 2, "Suggest — Пешниҳод кардан"),
        Exercise("ew_e37_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Аз ин рӯ, ба мо вақт лозим»", null, null, "Therefore, we need more time", null, "Therefore + ҷумла", words = listOf("time", "more", "need", "we", "Therefore,")),
        Exercise("ew_e37_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Fur-ther-more", listOf("However", "Furthermore", "Therefore", "Although"), "Furthermore", 1, "Furthermore — Илова бар ин"),
    ),
)

// ── Lesson 38: Нақл кардан (Storytelling) ────

private val engM10L38 = Lesson(
    id = "ew_m10_l38", moduleId = "ew_m10",
    title = "Нақл кардан", description = "Ҳикояи худро нақл кунед",
    emoji = "\uD83D\uDCD6", orderIndex = 1,
    dialogue = Dialogue(
        "Ҳикояи Фирӯз",
        listOf(
            DialogueLine("Firuz", "When I first came to England, I didn't speak English.", "Вақте аввал ба Англия омадам, англисӣ намедонистам."),
            DialogueLine("Firuz", "I studied every day after work.", "Ман ҳар рӯз пас аз кор мехондам."),
            DialogueLine("Sarah", "That is amazing! How long did it take?", "Олӣ! Чанд вақт гирифт?"),
            DialogueLine("Firuz", "About one year. Now I can communicate freely.", "Тақрибан як сол. Ҳоло ман озодона муошират мекунам."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w38_1", "Story", "Ҳикоя", "Sto-ry", "Tell me your story", "Ҳикояи худро гӯед", "ew_m10_l38"),
        WordItem("ew_w38_2", "Journey", "Сафар", "Jour-ney", "My journey was long", "Сафари ман дароз буд", "ew_m10_l38"),
        WordItem("ew_w38_3", "Challenge", "Мушкилӣ", "Chal-lenge", "It was a big challenge", "Ин мушкилии калон буд", "ew_m10_l38"),
        WordItem("ew_w38_4", "Overcome", "Ғалаба кардан", "O-ver-come", "I overcame the challenge", "Ман аз мушкилӣ ғалаба кардам", "ew_m10_l38"),
        WordItem("ew_w38_5", "Remember", "Дар хотир", "Re-mem-ber", "I remember that day", "Ман он рӯзро дар хотир дорам", "ew_m10_l38"),
        WordItem("ew_w38_6", "Experience", "Таҷриба", "Ex-pe-ri-ence", "A great experience", "Таҷрибаи олӣ", "ew_m10_l38"),
        WordItem("ew_w38_7", "Communicate", "Муошират", "Com-mu-ni-cate", "I can communicate well", "Ман хуб муошират мекунам", "ew_m10_l38"),
        WordItem("ew_w38_8", "Proud", "Ифтихор", "Proud", "I am proud of myself", "Ман аз худ ифтихор дорам", "ew_m10_l38"),
    ),
    grammarTip = GrammarTip(
        "When I first... / I used to... / Now I can...",
        "Барои нақли гузашта аз «When I first + феъл» ва барои ҳозира аз «Now I can + феъл» истифода баред.",
        listOf("When I first came here, I was shy.", "I used to study every day.", "Now I can communicate freely."),
    ),
    exercises = listOf(
        Exercise("ew_e38_1", ExerciseType.MULTIPLE_CHOICE, "«Challenge» чӣ маъно дорад?", "Challenge = ...", listOf("Таҷриба", "Сафар", "Мушкилӣ", "Ифтихор"), "Мушкилӣ", 2, "Challenge — Мушкилӣ"),
        Exercise("ew_e38_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I _____ the challenge.", listOf("remembered", "overcame", "communicated", "experienced"), "overcame", 1, "I overcame — Ман ғалаба кардам"),
        Exercise("ew_e38_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман аз худ ифтихор дорам»-ро интихоб кунед:", null, listOf("I am happy with myself", "I am proud of myself", "I know myself", "I like myself"), "I am proud of myself", 1, "Ман аз худ ифтихор дорам = I am proud of myself"),
        Exercise("ew_e38_4", ExerciseType.TYPE_ANSWER, "«Муошират»-ро ба англисӣ нависед:", "Муошират = ?", null, "Communicate", null, "Муошират — Communicate"),
        Exercise("ew_e38_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Story" to "Ҳикоя", "Journey" to "Сафар", "Overcome" to "Ғалаба кардан", "Proud" to "Ифтихор")),
        Exercise("ew_e38_6", ExerciseType.MULTIPLE_CHOICE, "«Remember» чӣ маъно дорад?", "Remember = ...", listOf("Фаромӯш кардан", "Дар хотир", "Нақл кардан", "Дидан"), "Дар хотир", 1, "Remember — Дар хотир"),
        Exercise("ew_e38_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Com-mu-ni-cate", listOf("Challenge", "Communicate", "Remember", "Overcome"), "Communicate", 1, "Communicate — Муошират"),
        Exercise("ew_e38_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ҳоло ман озодона гап зада метавонам»", null, null, "Now I can speak freely", null, "Now I can + феъл", words = listOf("freely", "speak", "can", "I", "Now")),
    ),
)

// ── Lesson 39: Нақша (Planning) ──────────────

private val engM10L39 = Lesson(
    id = "ew_m10_l39", moduleId = "ew_m10",
    title = "Нақша", description = "Нақшаи оянда кашидан",
    emoji = "\uD83D\uDDFA\uFE0F", orderIndex = 2,
    dialogue = Dialogue(
        "Нақшаи оянда",
        listOf(
            DialogueLine("Firuz", "I am planning to start my own business.", "Ман нақша дорам тиҷорати худро оғоз кунам."),
            DialogueLine("Sarah", "That sounds great! What kind of business?", "Ин олӣ аст! Чӣ навъ тиҷорат?"),
            DialogueLine("Firuz", "I want to open an export company.", "Ман мехоҳам ширкати содиротӣ кушоям."),
            DialogueLine("Sarah", "You will need a good business plan.", "Шумо нақшаи хуби тиҷоратӣ лозим."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w39_1", "Future", "Оянда", "Fu-ture", "In the future", "Дар оянда", "ew_m10_l39"),
        WordItem("ew_w39_2", "Business", "Тиҷорат", "Busi-ness", "Start a business", "Тиҷоратро оғоз кунед", "ew_m10_l39"),
        WordItem("ew_w39_3", "Export", "Содирот", "Ex-port", "Export goods", "Молро содирот кунед", "ew_m10_l39"),
        WordItem("ew_w39_4", "Import", "Воридот", "Im-port", "Import materials", "Масолеҳро ворид кунед", "ew_m10_l39"),
        WordItem("ew_w39_5", "Partner", "Шарик", "Part-ner", "Find a partner", "Шарик ёбед", "ew_m10_l39"),
        WordItem("ew_w39_6", "Client", "Муштарӣ", "Cli-ent", "Meet the client", "Бо муштарӣ вохӯред", "ew_m10_l39"),
        WordItem("ew_w39_7", "Strategy", "Стратегия", "Strat-e-gy", "Plan a strategy", "Стратегия нақша кашед", "ew_m10_l39"),
        WordItem("ew_w39_8", "Opportunity", "Имконият", "Op-por-tu-ni-ty", "A great opportunity", "Имконияти олӣ", "ew_m10_l39"),
    ),
    grammarTip = GrammarTip(
        "I am planning to... / I will need...",
        "Барои нақша аз «I am planning to + феъл» ва барои эҳтиёҷ аз «I will need + чиз» истифода баред.",
        listOf("I am planning to start a business.", "I will need a partner.", "This is a great opportunity."),
    ),
    exercises = listOf(
        Exercise("ew_e39_1", ExerciseType.MULTIPLE_CHOICE, "«Opportunity» чӣ маъно дорад?", "Opportunity = ...", listOf("Стратегия", "Имконият", "Тиҷорат", "Шарик"), "Имконият", 2, "Opportunity — Имконият"),
        Exercise("ew_e39_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I am planning to _____ a business.", listOf("close", "find", "start", "export"), "start", 2, "start a business — тиҷоратро оғоз кардан"),
        Exercise("ew_e39_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Шарик ёбед»-ро интихоб кунед:", null, listOf("Meet a client", "Find a partner", "Start a business", "Plan a strategy"), "Find a partner", 1, "Шарик ёбед = Find a partner"),
        Exercise("ew_e39_4", ExerciseType.TYPE_ANSWER, "«Муштарӣ»-ро ба англисӣ нависед:", "Муштарӣ = ?", null, "Client", null, "Муштарӣ — Client"),
        Exercise("ew_e39_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Business" to "Тиҷорат", "Export" to "Содирот", "Import" to "Воридот", "Partner" to "Шарик")),
        Exercise("ew_e39_6", ExerciseType.MULTIPLE_CHOICE, "«Strategy» чӣ маъно дорад?", "Strategy = ...", listOf("Нақша", "Стратегия", "Имконият", "Тиҷорат"), "Стратегия", 1, "Strategy — Стратегия"),
        Exercise("ew_e39_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ин имконияти олӣ аст»", null, null, "This is a great opportunity", null, "This is a + сифат + исм", words = listOf("opportunity", "great", "a", "is", "This")),
        Exercise("ew_e39_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nSarah: What kind of business?\nFiruz: _____", null, listOf("I want to open an export company.", "I have a meeting.", "Goodbye."), "I want to open an export company.", 1, "Нақшаи тиҷоратӣ"),
    ),
)

// ── Lesson 40: Имтиҳони ниҳоӣ (Final Exam) ──

private val engM10L40 = Lesson(
    id = "ew_m10_l40", moduleId = "ew_m10",
    title = "Имтиҳони ниҳоӣ", description = "Санҷиши ниҳоии дониш",
    emoji = "\uD83C\uDF93", orderIndex = 3,
    dialogue = Dialogue(
        "Рӯзи муваффақият",
        listOf(
            DialogueLine("Manager", "Firuz, you have made great progress!", "Фирӯз, шумо пешрафти олӣ доштед!"),
            DialogueLine("Firuz", "Thank you! I have learned so much.", "Ташаккур! Ман хеле зиёд омӯхтам."),
            DialogueLine("Manager", "You can now communicate confidently in English.", "Шумо ҳоло бо боварӣ дар англисӣ муошират карда метавонед."),
            DialogueLine("Firuz", "I will keep learning and improving.", "Ман ба омӯзиш ва беҳтаршавӣ идома медиҳам."),
        ),
    ),
    newWords = listOf(
        WordItem("ew_w40_1", "Progress", "Пешрафт", "Prog-ress", "Great progress!", "Пешрафти олӣ!", "ew_m10_l40"),
        WordItem("ew_w40_2", "Confident", "Боварӣ", "Con-fi-dent", "I feel confident", "Ман боварӣ ҳис мекунам", "ew_m10_l40"),
        WordItem("ew_w40_3", "Fluent", "Равон", "Flu-ent", "I want to be fluent", "Ман мехоҳам равон гап занам", "ew_m10_l40"),
        WordItem("ew_w40_4", "Practice", "Машқ", "Prac-tice", "Practice every day", "Ҳар рӯз машқ кунед", "ew_m10_l40"),
        WordItem("ew_w40_5", "Vocabulary", "Луғат", "Vo-cab-u-la-ry", "Build your vocabulary", "Луғати худро зиёд кунед", "ew_m10_l40"),
        WordItem("ew_w40_6", "Grammar", "Грамматика", "Gram-mar", "Study grammar", "Грамматика омӯзед", "ew_m10_l40"),
        WordItem("ew_w40_7", "Certificate", "Сертификат", "Cer-tif-i-cate", "I got a certificate", "Ман сертификат гирифтам", "ew_m10_l40"),
        WordItem("ew_w40_8", "Congratulations", "Табрик", "Con-grat-u-la-tions", "Congratulations!", "Табрик!", "ew_m10_l40"),
    ),
    grammarTip = GrammarTip(
        "I have learned... / I will keep...",
        "Барои натиҷа аз «I have learned + чиз» ва барои оянда аз «I will keep + феъл-ing» истифода баред.",
        listOf("I have learned a lot.", "I will keep practicing.", "I am proud of my progress."),
    ),
    exercises = listOf(
        Exercise("ew_e40_1", ExerciseType.MULTIPLE_CHOICE, "«Fluent» чӣ маъно дорад?", "Fluent = ...", listOf("Оҳиста", "Тез", "Равон", "Хуб"), "Равон", 2, "Fluent — Равон"),
        Exercise("ew_e40_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I have _____ so much.", listOf("practiced", "learned", "improved", "studied"), "learned", 1, "I have learned — Ман омӯхтам"),
        Exercise("ew_e40_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ҳар рӯз машқ кунед»-ро интихоб кунед:", null, listOf("Study every day", "Practice every day", "Learn every day", "Work every day"), "Practice every day", 1, "Ҳар рӯз машқ кунед = Practice every day"),
        Exercise("ew_e40_4", ExerciseType.TYPE_ANSWER, "«Табрик»-ро ба англисӣ нависед:", "Табрик = ?", null, "Congratulations", null, "Табрик — Congratulations"),
        Exercise("ew_e40_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Progress" to "Пешрафт", "Confident" to "Боварӣ", "Fluent" to "Равон", "Certificate" to "Сертификат")),
        Exercise("ew_e40_6", ExerciseType.MULTIPLE_CHOICE, "«Vocabulary» чӣ маъно дорад?", "Vocabulary = ...", listOf("Грамматика", "Луғат", "Машқ", "Пешрафт"), "Луғат", 1, "Vocabulary — Луғат"),
        Exercise("ew_e40_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман ба омӯзиш идома медиҳам»", null, null, "I will keep learning", null, "I will keep + феъл-ing", words = listOf("learning", "keep", "will", "I")),
        Exercise("ew_e40_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nManager: You have made great progress!\nFiruz: _____", null, listOf("I have a problem.", "Thank you! I have learned so much.", "What is the plan?"), "Thank you! I have learned so much.", 1, "Ташаккур барои пешрафт"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE & COURSE DEFINITIONS
// ═══════════════════════════════════════════════════

private val ewModule1 = Module(
    id = "ew_m1", courseId = "work_english",
    title = "Шиносоӣ дар кор",
    description = "Саломдиҳӣ, касб, рӯзи корӣ ва ҳамкорон",
    emoji = "\uD83D\uDC4B", orderIndex = 0,
    lessons = listOf(engM1L1, engM1L2, engM1L3, engM1L4),
)

private val ewModule2 = Module(
    id = "ew_m2", courseId = "work_english",
    title = "Муоширати корӣ",
    description = "Телефон, почта, ҷаласа ва мушкилот",
    emoji = "\uD83D\uDCDE", orderIndex = 1,
    lessons = listOf(engM2L5, engM2L6, engM2L7, engM2L8),
)

private val ewModule3 = Module(
    id = "ew_m3", courseId = "work_english",
    title = "Маҳорати пешрафта",
    description = "Мусоҳиба, презентатсия, музокирот ва муваффақият",
    emoji = "\uD83C\uDFC6", orderIndex = 2,
    lessons = listOf(engM3L9, engM3L10, engM3L11, engM3L12),
)

private val ewModule4 = Module(
    id = "ew_m4", courseId = "work_english",
    title = "Ҳуҷҷатҳо ва расмиёт",
    description = "Паспорт, бонк, шартнома ва бехатарӣ",
    emoji = "\uD83D\uDCCE", orderIndex = 3,
    lessons = listOf(engM4L13, engM4L14, engM4L15, engM4L16),
)

private val ewModule5 = Module(
    id = "ew_m5", courseId = "work_english",
    title = "Ҳаёти ҳаррӯза",
    description = "Мағоза, нақлиёт, хона ва тандурустӣ",
    emoji = "\uD83C\uDFD8", orderIndex = 4,
    lessons = listOf(engM5L17, engM5L18, engM5L19, engM5L20),
)

private val ewModule6 = Module(
    id = "ew_m6", courseId = "work_english",
    title = "Технология",
    description = "Компютер, интернет, барнома ва видеозанг",
    emoji = "\uD83D\uDCBB", orderIndex = 5,
    lessons = listOf(engM6L21, engM6L22, engM6L23, engM6L24),
)

private val ewModule7 = Module(
    id = "ew_m7", courseId = "work_english",
    title = "Сафари корӣ",
    description = "Фурудгоҳ, меҳмонхона, такси ва ҷадвал",
    emoji = "\u2708\uFE0F", orderIndex = 6,
    lessons = listOf(engM7L25, engM7L26, engM7L27, engM7L28),
)

private val ewModule8 = Module(
    id = "ew_m8", courseId = "work_english",
    title = "Молия",
    description = "Маош, буҷет, суғурта ва сармоягузорӣ",
    emoji = "\uD83D\uDCB0", orderIndex = 7,
    lessons = listOf(engM8L29, engM8L30, engM8L31, engM8L32),
)

private val ewModule9 = Module(
    id = "ew_m9", courseId = "work_english",
    title = "Фарҳанги англисӣ",
    description = "Идҳо, таом, обу ҳаво ва одоб",
    emoji = "\uD83C\uDDEC\uD83C\uDDE7", orderIndex = 8,
    lessons = listOf(engM9L33, engM9L34, engM9L35, engM9L36),
)

private val ewModule10 = Module(
    id = "ew_m10", courseId = "work_english",
    title = "Мустақилият",
    description = "Озодона гап задан, нақл, нақша ва имтиҳон",
    emoji = "\uD83C\uDF93", orderIndex = 9,
    lessons = listOf(engM10L37, engM10L38, engM10L39, engM10L40),
)

val workEnglishCourse = Course(
    id = "work_english",
    title = "Англисӣ барои кор",
    description = "Забони англисӣ барои муҳити корӣ: шиносоӣ, муошират, маҳорат",
    emoji = "\uD83D\uDCBC",
    goalType = "work",
    language = "english",
    modules = listOf(
        ewModule1, ewModule2, ewModule3, ewModule4, ewModule5,
        ewModule6, ewModule7, ewModule8, ewModule9, ewModule10,
    ),
)
