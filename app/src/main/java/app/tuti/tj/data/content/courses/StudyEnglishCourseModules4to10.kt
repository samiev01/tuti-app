package app.tuti.tj.data.content.courses

import app.tuti.tj.data.content.Dialogue
import app.tuti.tj.data.content.DialogueLine
import app.tuti.tj.data.content.Exercise
import app.tuti.tj.data.content.ExerciseType
import app.tuti.tj.data.content.GrammarTip
import app.tuti.tj.data.content.Lesson
import app.tuti.tj.data.content.Module
import app.tuti.tj.data.content.WordItem

// ═══════════════════════════════════════════════════
//  MODULE 4 · ИЛМҲОИ ТАБИӢ (Natural Sciences)
// ═══════════════════════════════════════════════════

// ── Lesson 13: Математика ─────────────────────────

internal val esM4L1 = Lesson(
    id = "es_m4_l1", moduleId = "es_m4",
    title = "Математика", description = "Рақамҳо ва амалҳои математикӣ",
    emoji = "\u2795", orderIndex = 0,
    dialogue = Dialogue(
        "Дар синфи математика",
        listOf(
            DialogueLine("Teacher", "Two plus three equals five.", "Ду ба се ҷамъ панҷ мешавад."),
            DialogueLine("Firuz", "How do we subtract seven from ten?", "Чӣ тавр аз даҳ ҳафтро тарҳ мекунем?"),
            DialogueLine("Teacher", "Ten minus seven equals three.", "Даҳ минус ҳафт се мешавад."),
            DialogueLine("Firuz", "I can multiply four by two, then divide eight by four.", "Ман чорро ба ду зарб карда метавонам, баъд ҳаштро ба чор тақсим мекунам."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w13_1", "Number", "Рақам", "Num-ber", "Write the number on the board", "Рақамро ба тахта нависед", "es_m4_l1"),
        WordItem("es_w13_2", "Add", "Ҷамъ кардан", "Add", "Add two and three", "Ду ва се ҷамъ кунед", "es_m4_l1"),
        WordItem("es_w13_3", "Subtract", "Тарҳ кардан", "Sub-tract", "Subtract five from ten", "Аз даҳ панҷро тарҳ кунед", "es_m4_l1"),
        WordItem("es_w13_4", "Multiply", "Зарб кардан", "Mul-ti-ply", "Multiply four by two", "Чорро ба ду зарб кунед", "es_m4_l1"),
        WordItem("es_w13_5", "Divide", "Тақсим кардан", "Di-vide", "Divide ten by two", "Даҳро ба ду тақсим кунед", "es_m4_l1"),
        WordItem("es_w13_6", "Equal", "Баробар", "E-qual", "Five equals five", "Панҷ баробари панҷ аст", "es_m4_l1"),
        WordItem("es_w13_7", "Problem", "Масъала", "Prob-lem", "Solve this math problem", "Ин масъалаи математикиро ҳал кунед", "es_m4_l1"),
        WordItem("es_w13_8", "Solution", "Ҳал", "So-lu-tion", "Find the correct solution", "Ҳали дурустро ёбед", "es_m4_l1"),
    ),
    grammarTip = GrammarTip(
        "Two plus three equals five / Math expressions",
        "Барои амалҳои математикӣ аз «plus», «minus», «times», «divided by», «equals» истифода баред.",
        listOf("Two plus three equals five.", "Ten minus four equals six.", "Four times two equals eight."),
    ),
    exercises = listOf(
        Exercise("es_e13_1", ExerciseType.MULTIPLE_CHOICE, "«Subtract» чӣ маъно дорад?", "Subtract = ...", listOf("Зарб кардан", "Тарҳ кардан", "Ҷамъ кардан", "Тақсим кардан"), "Тарҳ кардан", 1, "Subtract — Тарҳ кардан"),
        Exercise("es_e13_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Three _____ two equals five.", listOf("minus", "times", "plus", "divided"), "plus", 2, "Three plus two equals five"),
        Exercise("es_e13_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Two equals three", "Two plus two equals four", "Two minus four", "Two divide two"), "Two plus two equals four", 1, "Ду ба ду ҷамъ чор мешавад"),
        Exercise("es_e13_4", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Add" to "Ҷамъ кардан", "Divide" to "Тақсим кардан", "Equal" to "Баробар", "Problem" to "Масъала")),
        Exercise("es_e13_5", ExerciseType.TYPE_ANSWER, "«Зарб кардан»-ро ба англисӣ нависед:", "Зарб кардан = ?", null, "Multiply", null, "Multiply — Зарб кардан"),
        Exercise("es_e13_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Ten minus three equals seven", null, "Ҷумлаи математикӣ", words = listOf("seven", "equals", "Ten", "three", "minus")),
        Exercise("es_e13_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTeacher: What is two plus three?\nFiruz: _____", null, listOf("It is a problem.", "Two plus three equals five.", "I subtract numbers."), "Two plus three equals five.", 1, "Ҷавоби дуруст бо equals"),
        Exercise("es_e13_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A So-lu-tion", listOf("Subtract", "Problem", "Solution", "Number"), "Solution", 2, "Solution — Ҳал"),
    ),
)

// ── Lesson 14: Илм ───────────────────────────────

internal val esM4L2 = Lesson(
    id = "es_m4_l2", moduleId = "es_m4",
    title = "Илм", description = "Таҷриба, тадқиқот ва натиҷа",
    emoji = "\uD83E\uDDEA", orderIndex = 1,
    dialogue = Dialogue(
        "Дар лаборатория",
        listOf(
            DialogueLine("Anna", "We will do an experiment today.", "Имрӯз мо таҷриба мекунем."),
            DialogueLine("Firuz", "What is your theory about the result?", "Назарияи шумо дар бораи натиҷа чист?"),
            DialogueLine("Anna", "The experiment shows that heat expands metal.", "Таҷриба нишон медиҳад, ки гармӣ металлро васеъ мекунад."),
            DialogueLine("Firuz", "We need more data to prove it.", "Барои исбот ба мо маълумоти бештар лозим аст."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w14_1", "Experiment", "Таҷриба", "Ex-per-i-ment", "We did an experiment", "Мо таҷриба кардем", "es_m4_l2"),
        WordItem("es_w14_2", "Research", "Тадқиқот", "Re-search", "Scientific research takes time", "Тадқиқоти илмӣ вақт мегирад", "es_m4_l2"),
        WordItem("es_w14_3", "Laboratory", "Лаборатория", "Lab-o-ra-to-ry", "Meet me in the laboratory", "Дар лаборатория шинос шавем", "es_m4_l2"),
        WordItem("es_w14_4", "Theory", "Назария", "The-o-ry", "This is a new theory", "Ин назарияи нав аст", "es_m4_l2"),
        WordItem("es_w14_5", "Discover", "Кашф кардан", "Dis-cov-er", "Scientists discover new facts", "Олимон ҳақиқатҳои нав кашф мекунанд", "es_m4_l2"),
        WordItem("es_w14_6", "Prove", "Исбот кардан", "Prove", "Can you prove your idea?", "Метавонед фикри худро исбот кунед?", "es_m4_l2"),
        WordItem("es_w14_7", "Data", "Маълумот", "Da-ta", "Collect the data carefully", "Маълумотро бодиққат ҷамъ кунед", "es_m4_l2"),
        WordItem("es_w14_8", "Result", "Натиҷа", "Re-sult", "What is the result?", "Натиҷа чист?", "es_m4_l2"),
    ),
    grammarTip = GrammarTip(
        "The experiment shows that... / Scientific phrases",
        "Барои натиҷаи илмӣ аз «The experiment shows that...» истифода баред.",
        listOf("The experiment shows that water boils at 100°C.", "The data supports our theory.", "We need to prove the result."),
    ),
    exercises = listOf(
        Exercise("es_e14_1", ExerciseType.MULTIPLE_CHOICE, "«Laboratory» чӣ маъно дорад?", "Laboratory = ...", listOf("Масъала", "Лаборатория", "Натиҷа", "Назария"), "Лаборатория", 1, "Laboratory — Лаборатория"),
        Exercise("es_e14_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Scientists _____ new things.", listOf("prove", "discover", "ignore", "forget"), "discover", 1, "Discover — кашф кардан"),
        Exercise("es_e14_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("The theory is wrong", "The experiment shows that it works", "The laboratory is closed", "The result is a number"), "The experiment shows that it works", 1, "The experiment shows that..."),
        Exercise("es_e14_4", ExerciseType.TYPE_ANSWER, "«Маълумот»-ро ба англисӣ нависед:", "Маълумот = ?", null, "Data", null, "Data — Маълумот"),
        Exercise("es_e14_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Research" to "Тадқиқот", "Theory" to "Назария", "Prove" to "Исбот кардан", "Result" to "Натиҷа")),
        Exercise("es_e14_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "We need more data", null, "We need + more + data", words = listOf("data", "more", "need", "We")),
        Exercise("es_e14_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: What is the result?\nFiruz: _____", null, listOf("The laboratory is big.", "The result supports our theory.", "I like math."), "The result supports our theory.", 1, "Ҷавоб дар бораи натиҷа"),
        Exercise("es_e14_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Re-search", listOf("Research", "Result", "Theory", "Experiment"), "Research", 0, "Research — Тадқиқот"),
    ),
)

// ── Lesson 15: Компютер ──────────────────────────

internal val esM4L3 = Lesson(
    id = "es_m4_l3", moduleId = "es_m4",
    title = "Компютер", description = "Барнома, шабака ва пойгоҳ",
    emoji = "\uD83D\uDCBB", orderIndex = 2,
    dialogue = Dialogue(
        "Дар компютер",
        listOf(
            DialogueLine("Tom", "Please download the software from the website.", "Лутфан нармафзорро аз сайт боргирӣ кунед."),
            DialogueLine("Firuz", "What is the password for the network?", "Рамзи шабака чист?"),
            DialogueLine("Tom", "Save the file and upload it to the database.", "Файлро захира кунед ва ба пойгоҳ боргузорӣ кунед."),
            DialogueLine("Firuz", "The hardware looks new.", "Сахтафзор нав ба назар мерасад."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w15_1", "Program", "Барнома", "Pro-gram", "Install this program", "Ин барномаро насб кунед", "es_m4_l3"),
        WordItem("es_w15_2", "Software", "Нармафзор", "Soft-ware", "Update your software", "Нармафзоратонро навсозӣ кунед", "es_m4_l3"),
        WordItem("es_w15_3", "Hardware", "Сахтафзор", "Hard-ware", "Check the hardware", "Сахтафзорро санҷед", "es_m4_l3"),
        WordItem("es_w15_4", "Download", "Боргирӣ", "Down-load", "Click download", "Боргирӣро пахш кунед", "es_m4_l3"),
        WordItem("es_w15_5", "Upload", "Боргузорӣ", "Up-load", "Upload your homework", "Вазифаатонро боргузорӣ кунед", "es_m4_l3"),
        WordItem("es_w15_6", "Password", "Рамз", "Pass-word", "Enter your password", "Рамзатонро ворид кунед", "es_m4_l3"),
        WordItem("es_w15_7", "Network", "Шабака", "Net-work", "Connect to the network", "Ба шабака пайваст шавед", "es_m4_l3"),
        WordItem("es_w15_8", "Database", "Пойгоҳ", "Da-ta-base", "Save data in the database", "Маълумотро дар пойгоҳ захира кунед", "es_m4_l3"),
    ),
    grammarTip = GrammarTip(
        "Click on... / Save the file",
        "Барои дастурҳои техникӣ аз «Click on...», «Save the file», «Upload the file» истифода баред.",
        listOf("Click on the icon.", "Save the file before you close.", "Upload the document to the cloud."),
    ),
    exercises = listOf(
        Exercise("es_e15_1", ExerciseType.MULTIPLE_CHOICE, "«Software» чӣ маъно дорад?", "Software = ...", listOf("Сахтафзор", "Нармафзор", "Шабака", "Рамз"), "Нармафзор", 1, "Software — Нармафзор"),
        Exercise("es_e15_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Please _____ the file from the site.", listOf("upload", "save", "download", "print"), "download", 2, "Download — боргирӣ"),
        Exercise("es_e15_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Close the network", "Save the file before you close", "Delete the hardware", "Open the password"), "Save the file before you close", 1, "Save the file"),
        Exercise("es_e15_4", ExerciseType.TYPE_ANSWER, "«Шабака»-ро ба англисӣ нависед:", "Шабака = ?", null, "Network", null, "Network — Шабака"),
        Exercise("es_e15_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Program" to "Барнома", "Upload" to "Боргузорӣ", "Password" to "Рамз", "Database" to "Пойгоҳ")),
        Exercise("es_e15_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Enter your password", null, "Enter your password", words = listOf("password", "Enter", "your")),
        Exercise("es_e15_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTom: Save the file.\nFiruz: _____", null, listOf("I will upload it to the database.", "I forgot the hardware.", "The program is music."), "I will upload it to the database.", 0, "Ҷавоби мувофиқ"),
        Exercise("es_e15_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Hard-ware", listOf("Software", "Hardware", "Network", "Download"), "Hardware", 1, "Hardware — Сахтафзор"),
    ),
)

// ── Lesson 16: Технология ────────────────────────

internal val esM4L4 = Lesson(
    id = "es_m4_l4", moduleId = "es_m4",
    title = "Технология", description = "Дастгоҳ, экран ва пайваст",
    emoji = "\uD83D\uDCF1", orderIndex = 3,
    dialogue = Dialogue(
        "Телефон ва дастгоҳ",
        listOf(
            DialogueLine("Anna", "My phone battery is low.", "Батареяи телефони ман кам аст."),
            DialogueLine("Firuz", "How do I connect to wireless Wi‑Fi?", "Чӣ тавр ба Wi‑Fi бесим пайваст шавам?"),
            DialogueLine("Anna", "Open settings and install the update.", "Танзимотро кушоед ва навсозиро насб кунед."),
            DialogueLine("Firuz", "This smart device has a large screen.", "Ин дастгоҳи ақлона экрани калон дорад."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w16_1", "Device", "Дастгоҳ", "De-vice", "Turn on the device", "Дастгоҳро фаъол кунед", "es_m4_l4"),
        WordItem("es_w16_2", "Screen", "Экран", "Screen", "The screen is bright", "Экран равшан аст", "es_m4_l4"),
        WordItem("es_w16_3", "Battery", "Батарея", "Bat-te-ry", "Charge the battery", "Батареяро пур кунед", "es_m4_l4"),
        WordItem("es_w16_4", "Update", "Навсозӣ", "Up-date", "My phone needs an update", "Ба телефони ман навсозӣ лозим аст", "es_m4_l4"),
        WordItem("es_w16_5", "Install", "Насб кардан", "In-stall", "Install the app", "Барномаро насб кунед", "es_m4_l4"),
        WordItem("es_w16_6", "Connect", "Пайваст кардан", "Con-nect", "Connect the cable", "Кабелро пайваст кунед", "es_m4_l4"),
        WordItem("es_w16_7", "Wireless", "Бесим", "Wire-less", "Wireless headphones", "Гӯшмонакҳои бесим", "es_m4_l4"),
        WordItem("es_w16_8", "Smart", "Ақлона", "Smart", "A smart watch", "Соати ақлона", "es_m4_l4"),
    ),
    grammarTip = GrammarTip(
        "How do I connect...? / My phone needs an update",
        "Барои кӯмак аз «How do I connect...?» ва барои навсозӣ аз «needs an update» истифода баред.",
        listOf("How do I connect to Wi‑Fi?", "My phone needs an update.", "The screen is too bright."),
    ),
    exercises = listOf(
        Exercise("es_e16_1", ExerciseType.MULTIPLE_CHOICE, "«Wireless» чӣ маъно дорад?", "Wireless = ...", listOf("Ақлона", "Бесим", "Экран", "Батарея"), "Бесим", 1, "Wireless — Бесим"),
        Exercise("es_e16_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "My phone needs an _____.", listOf("screen", "battery", "update", "device"), "update", 2, "needs an update"),
        Exercise("es_e16_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("How do I print the battery?", "How do I connect to Wi‑Fi?", "How do I eat the screen?", "How do I close the smart?"), "How do I connect to Wi‑Fi?", 1, "How do I connect..."),
        Exercise("es_e16_4", ExerciseType.TYPE_ANSWER, "«Экран»-ро ба англисӣ нависед:", "Экран = ?", null, "Screen", null, "Screen — Экран"),
        Exercise("es_e16_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Battery" to "Батарея", "Install" to "Насб кардан", "Connect" to "Пайваст кардан", "Smart" to "Ақлона")),
        Exercise("es_e16_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "This smart device has a large screen", null, "This smart device...", words = listOf("screen", "large", "a", "has", "device", "smart", "This")),
        Exercise("es_e16_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: My battery is low.\nFiruz: _____", null, listOf("Buy a new device.", "Charge the battery.", "Delete the screen."), "Charge the battery.", 1, "Ҷавоби мантиқӣ"),
        Exercise("es_e16_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A De-vice", listOf("Battery", "Device", "Update", "Wireless"), "Device", 1, "Device — Дастгоҳ"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 5 · НАВИШТАН ВА ХОНДАН (Writing & Reading)
// ═══════════════════════════════════════════════════

// ── Lesson 17: Иншо ──────────────────────────────

internal val esM5L1 = Lesson(
    id = "es_m5_l1", moduleId = "es_m5",
    title = "Иншо", description = "Сохтори иншо: муқаддима, банд, хулоса",
    emoji = "\uD83D\uDCDD", orderIndex = 0,
    dialogue = Dialogue(
        "Навиштани иншо",
        listOf(
            DialogueLine("Teacher", "Firstly, write a clear introduction.", "Аввалан, муқаддимаи равшан нависед."),
            DialogueLine("Firuz", "Secondly, I will add arguments with evidence.", "Дуюм, ман далелҳоро бо далел илова мекунам."),
            DialogueLine("Teacher", "Your conclusion should summarize the essay.", "Хулоса бояд иншоро ҷамъбаст кунад."),
            DialogueLine("Firuz", "I will check the structure before I submit.", "Пеш аз супоридан сохторро санҷидам."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w17_1", "Essay", "Иншо", "Es-say", "Write a strong essay", "Иншои қавӣ нависед", "es_m5_l1"),
        WordItem("es_w17_2", "Paragraph", "Банд", "Par-a-graph", "Each paragraph has one idea", "Ҳар банд як фикр дорад", "es_m5_l1"),
        WordItem("es_w17_3", "Introduction", "Муқаддима", "In-tro-duc-tion", "Start with an introduction", "Аз муқаддима оғоз кунед", "es_m5_l1"),
        WordItem("es_w17_4", "Conclusion", "Хулоса", "Con-clu-sion", "End with a conclusion", "Бо хулоса анҷом диҳед", "es_m5_l1"),
        WordItem("es_w17_5", "Argument", "Далел", "Ar-gu-ment", "Support your argument", "Далели худро дастгирӣ кунед", "es_m5_l1"),
        WordItem("es_w17_6", "Opinion", "Фикр", "O-pin-ion", "State your opinion clearly", "Фикри худро равшан бигӯед", "es_m5_l1"),
        WordItem("es_w17_7", "Evidence", "Далел", "Ev-i-dence", "Use evidence from books", "Аз китобҳо далел оваред", "es_m5_l1"),
        WordItem("es_w17_8", "Structure", "Сохтор", "Struc-ture", "Follow the essay structure", "Аз сохтори иншо пайравӣ кунед", "es_m5_l1"),
    ),
    grammarTip = GrammarTip(
        "Firstly... Secondly... In conclusion...",
        "Барои пайвастани бандҳо аз «Firstly», «Secondly», «Finally / In conclusion» истифода баред.",
        listOf("Firstly, I will explain the problem.", "Secondly, we discuss solutions.", "In conclusion, education matters."),
    ),
    exercises = listOf(
        Exercise("es_e17_1", ExerciseType.MULTIPLE_CHOICE, "«Introduction» чӣ маъно дорад?", "Introduction = ...", listOf("Хулоса", "Муқаддима", "Банд", "Далел"), "Муқаддима", 1, "Introduction — Муқаддима"),
        Exercise("es_e17_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Secondly, we discuss the _____.", listOf("introduction", "structure", "main idea", "title"), "main idea", 2, "Secondly..."),
        Exercise("es_e17_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I hate essays", "In conclusion, this is important", "The paragraph is a conclusion", "Firstly is wrong"), "In conclusion, this is important", 1, "In conclusion..."),
        Exercise("es_e17_4", ExerciseType.TYPE_ANSWER, "«Сохтор»-ро ба англисӣ нависед:", "Сохтор = ?", null, "Structure", null, "Structure — Сохтор"),
        Exercise("es_e17_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Essay" to "Иншо", "Opinion" to "Фикр", "Evidence" to "Далел", "Paragraph" to "Банд")),
        Exercise("es_e17_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "State your opinion clearly", null, "State your opinion", words = listOf("clearly", "your", "opinion", "State")),
        Exercise("es_e17_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTeacher: How do you finish?\nFiruz: _____", null, listOf("Firstly, I start.", "I end with a conclusion.", "Secondly, I delete."), "I end with a conclusion.", 1, "Хулоса"),
        Exercise("es_e17_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ar-gu-ment", listOf("Evidence", "Argument", "Essay", "Opinion"), "Argument", 1, "Argument — Далел"),
    ),
)

// ── Lesson 18: Хондани матн ─────────────────────

internal val esM5L2 = Lesson(
    id = "es_m5_l2", moduleId = "es_m5",
    title = "Хондани матн", description = "Фикри асосӣ ва тафсилот",
    emoji = "\uD83D\uDCD6", orderIndex = 1,
    dialogue = Dialogue(
        "Дар синф",
        listOf(
            DialogueLine("Teacher", "What is the main idea of the text?", "Фикри асосии матн чист?"),
            DialogueLine("Firuz", "According to the text, climate change is serious.", "Бо мувофиқи матн, тағйири иқлим ҷиддӣ аст."),
            DialogueLine("Teacher", "Find a detail that supports the summary.", "Тафсилотеро ёбед, ки хулосаро дастгирӣ мекунад."),
            DialogueLine("Firuz", "The meaning depends on the context.", "Маъно аз мазмун вобаста аст."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w18_1", "Text", "Матн", "Text", "Read the text carefully", "Матнро бодиққат хонед", "es_m5_l2"),
        WordItem("es_w18_2", "Understand", "Фаҳмидан", "Un-der-stand", "I understand the lesson", "Ман дарсро фаҳмидам", "es_m5_l2"),
        WordItem("es_w18_3", "Main idea", "Фикри асосӣ", "Main i-dea", "Find the main idea", "Фикри асосиро ёбед", "es_m5_l2"),
        WordItem("es_w18_4", "Detail", "Тафсилот", "De-tail", "Notice important details", "Тафсилоти муҳимро диққат кунед", "es_m5_l2"),
        WordItem("es_w18_5", "Summary", "Хулоса", "Sum-ma-ry", "Write a short summary", "Хулосаи кӯтоҳ нависед", "es_m5_l2"),
        WordItem("es_w18_6", "Meaning", "Маънӣ", "Mean-ing", "What is the meaning?", "Маъно чист?", "es_m5_l2"),
        WordItem("es_w18_7", "Context", "Мазмун", "Con-text", "Use context clues", "Аз ишораҳои мазмун истифода баред", "es_m5_l2"),
        WordItem("es_w18_8", "Vocabulary", "Луғат", "Vo-cab-u-lar-y", "Learn new vocabulary", "Луғати нав омӯзед", "es_m5_l2"),
    ),
    grammarTip = GrammarTip(
        "The main idea is... / According to the text...",
        "Барои хулоса аз «The main idea is...» ва барои иқтибос аз «According to the text...» истифода баред.",
        listOf("The main idea is freedom.", "According to the text, prices rose.", "The vocabulary is difficult."),
    ),
    exercises = listOf(
        Exercise("es_e18_1", ExerciseType.MULTIPLE_CHOICE, "«Vocabulary» чӣ маъно дорад?", "Vocabulary = ...", listOf("Матн", "Маъно", "Луғат", "Мазмун"), "Луғат", 2, "Vocabulary — Луғат"),
        Exercise("es_e18_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "According to the _____, prices went up.", listOf("summary", "text", "title", "teacher"), "text", 1, "According to the text"),
        Exercise("es_e18_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("The detail is the main idea", "The main idea is clear", "The text is a summary", "The meaning is vocabulary"), "The main idea is clear", 1, "The main idea is..."),
        Exercise("es_e18_4", ExerciseType.TYPE_ANSWER, "«Тафсилот»-ро ба англисӣ нависед:", "Тафсилот = ?", null, "Detail", null, "Detail — Тафсилот"),
        Exercise("es_e18_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Understand" to "Фаҳмидан", "Summary" to "Хулоса", "Context" to "Мазмун", "Meaning" to "Маънӣ")),
        Exercise("es_e18_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I understand the text", null, "I understand...", words = listOf("text", "the", "understand", "I")),
        Exercise("es_e18_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTeacher: What is the main idea?\nFiruz: _____", null, listOf("The vocabulary is long.", "According to the text, education is key.", "The context is a book."), "According to the text, education is key.", 1, "According to the text..."),
        Exercise("es_e18_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Sum-ma-ry", listOf("Summary", "Detail", "Text", "Meaning"), "Summary", 0, "Summary — Хулоса"),
    ),
)

// ── Lesson 19: Грамматика ────────────────────────

internal val esM5L3 = Lesson(
    id = "es_m5_l3", moduleId = "es_m5",
    title = "Грамматика", description = "Исм, феъл, замонҳо",
    emoji = "\uD83D\uDCDA", orderIndex = 2,
    dialogue = Dialogue(
        "Қоидаҳои асосӣ",
        listOf(
            DialogueLine("Teacher", "A noun names a person or thing.", "Исм шахс ё чизро ном мебарад."),
            DialogueLine("Firuz", "Verbs show action, and adjectives describe nouns.", "Феъл амалро нишон медиҳад, сифат исмро тавсиф мекунад."),
            DialogueLine("Teacher", "Use the present simple for habits.", "Барои одатҳо аз Present Simple истифода баред."),
            DialogueLine("Firuz", "I am reading now uses present continuous.", "«I am reading» ҳозираи давомдор аст."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w19_1", "Noun", "Исм", "Noun", "Student is a noun", "Student — исм аст", "es_m5_l3"),
        WordItem("es_w19_2", "Verb", "Феъл", "Verb", "Run is a verb", "Run — феъл аст", "es_m5_l3"),
        WordItem("es_w19_3", "Adjective", "Сифат", "Ad-jec-tive", "Happy is an adjective", "Happy — сифат аст", "es_m5_l3"),
        WordItem("es_w19_4", "Adverb", "Зарф", "Ad-verb", "Quickly is an adverb", "Quickly — зарф аст", "es_m5_l3"),
        WordItem("es_w19_5", "Tense", "Замон", "Tense", "Choose the correct tense", "Замони дурустро интихоб кунед", "es_m5_l3"),
        WordItem("es_w19_6", "Singular", "Танҳо", "Sin-gu-lar", "One book is singular", "Як китоб — танҳо", "es_m5_l3"),
        WordItem("es_w19_7", "Plural", "Ҷамъ", "Plu-ral", "Two books are plural", "Ду китоб — ҷамъ", "es_m5_l3"),
        WordItem("es_w19_8", "Sentence", "Ҷумла", "Sen-tence", "Write a full sentence", "Ҷумлаи пурра нависед", "es_m5_l3"),
    ),
    grammarTip = GrammarTip(
        "Present Simple vs Present Continuous",
        "Одатҳо: I work every day. Амал ҳозира: I am working now.",
        listOf("I study English every day.", "I am studying now.", "She reads in the evening."),
    ),
    exercises = listOf(
        Exercise("es_e19_1", ExerciseType.MULTIPLE_CHOICE, "Кадом ҷумла Present Continuous аст?", null, listOf("I work every day", "I am working now", "She works hard", "We study at night"), "I am working now", 1, "am + -ing"),
        Exercise("es_e19_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "She _____ English every morning.", listOf("is studying", "studies", "studying", "study"), "studies", 1, "Present simple барои одат"),
        Exercise("es_e19_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Ду китоб — ҷамъ = Two books are plural", "Ду китоб — ҷамъ = Two books is singular", "Ду китоб — ҷамъ = One book is plural", "Ду китоб — ҷамъ = Two book"), "Two books are plural", 0, "Plural — ҷамъ"),
        Exercise("es_e19_4", ExerciseType.TYPE_ANSWER, "«Феъл»-ро ба англисӣ нависед:", "Феъл = ?", null, "Verb", null, "Verb — Феъл"),
        Exercise("es_e19_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Noun" to "Исм", "Adjective" to "Сифат", "Adverb" to "Зарф", "Tense" to "Замон")),
        Exercise("es_e19_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Write a full sentence", null, "Write a full sentence", words = listOf("sentence", "full", "a", "Write")),
        Exercise("es_e19_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTeacher: What is an adjective?\nFiruz: _____", null, listOf("It is a tense.", "It describes a noun.", "It is a plural."), "It describes a noun.", 1, "Adjective"),
        Exercise("es_e19_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Plu-ral", listOf("Singular", "Plural", "Sentence", "Noun"), "Plural", 1, "Plural — Ҷамъ"),
    ),
)

// ── Lesson 20: Талаффуз ──────────────────────────

internal val esM5L4 = Lesson(
    id = "es_m5_l4", moduleId = "es_m5",
    title = "Талаффуз", description = "Овоз, ударие ва садои th",
    emoji = "\uD83D\uDDE3\uFE0F", orderIndex = 3,
    dialogue = Dialogue(
        "Машқи талаффуз",
        listOf(
            DialogueLine("Teacher", "Pronounce each syllable slowly.", "Ҳар ҳиҷоро оҳиста талаффуз кунед."),
            DialogueLine("Firuz", "Where is the stress in «important»?", "Ударие дар «important» дар куҷост?"),
            DialogueLine("Teacher", "Practice the «th» sound: think, three, thank.", "Садои «th»-ро машқ кунед: think, three, thank."),
            DialogueLine("Firuz", "Vowels and consonants work together in every word.", "Садонокиҳо ва ҳамсадаҳо дар ҳар калима якҷояанд."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w20_1", "Pronounce", "Талаффуз кардан", "Pro-nounce", "Pronounce clearly", "Равшан талаффуз кунед", "es_m5_l4"),
        WordItem("es_w20_2", "Sound", "Овоз", "Sound", "This sound is hard", "Ин овоз сахт аст", "es_m5_l4"),
        WordItem("es_w20_3", "Stress", "Ударение", "Stress", "Word stress matters", "Ударие муҳим аст", "es_m5_l4"),
        WordItem("es_w20_4", "Syllable", "Ҳиҷо", "Syl-la-ble", "Count the syllables", "Ҳиҷоҳоро шуморед", "es_m5_l4"),
        WordItem("es_w20_5", "Accent", "Лаҳҷа", "Ac-cent", "She has a British accent", "Вай лаҳҷаи бритониёӣ дорад", "es_m5_l4"),
        WordItem("es_w20_6", "Vowel", "Садоноки", "Vow-el", "A, E, I are vowels", "A, E, I садонокиҳо", "es_m5_l4"),
        WordItem("es_w20_7", "Consonant", "Ҳамсадо", "Con-so-nant", "B and T are consonants", "B ва T ҳамсадаҳо", "es_m5_l4"),
        WordItem("es_w20_8", "Practice", "Машқ", "Prac-tice", "Practice every morning", "Ҳар субҳ машқ кунед", "es_m5_l4"),
    ),
    grammarTip = GrammarTip(
        "Word stress / «th» sound",
        "«th»-и ларзонро дар think, thank машқ кунед; ударие дар калимаҳои дароз муҳим аст.",
        listOf("think, three, thank", "this, that, the", "important — stress on the second syllable"),
    ),
    exercises = listOf(
        Exercise("es_e20_1", ExerciseType.MULTIPLE_CHOICE, "Кадом калима садои th дорад?", null, listOf("Ship", "Think", "Sing", "Cook"), "Think", 1, "Think — th"),
        Exercise("es_e20_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Please _____ this word again.", listOf("stress", "pronounce", "accent", "vowel"), "pronounce", 1, "Pronounce"),
        Exercise("es_e20_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Count the accent", "Count the syllables", "Count the vowel only", "Count the teacher"), "Count the syllables", 1, "Syllables — ҳиҷо"),
        Exercise("es_e20_4", ExerciseType.TYPE_ANSWER, "«Ҳамсадо»-ро ба англисӣ нависед:", "Ҳамсадо = ?", null, "Consonant", null, "Consonant — Ҳамсадо"),
        Exercise("es_e20_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Stress" to "Ударение", "Vowel" to "Садоноки", "Sound" to "Овоз", "Practice" to "Машқ")),
        Exercise("es_e20_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Practice the th sound", null, "Practice the th sound", words = listOf("sound", "th", "the", "Practice")),
        Exercise("es_e20_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTeacher: How is your accent?\nFiruz: _____", null, listOf("I have a British accent.", "I have three accents.", "My accent is a vowel."), "I have a British accent.", 0, "Accent"),
        Exercise("es_e20_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Syl-la-ble", listOf("Stress", "Syllable", "Consonant", "Vowel"), "Syllable", 1, "Syllable — Ҳиҷо"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 6 · МУОШИРАТИ ҲАРРӮЗА (Daily Communication)
// ═══════════════════════════════════════════════════

// ── Lesson 21: Дар мағоза ────────────────────────

internal val esM6L1 = Lesson(
    id = "es_m6_l1", moduleId = "es_m6",
    title = "Дар мағоза", description = "Нарх, тахфиф ва пардохт",
    emoji = "\uD83D\uDECD\uFE0F", orderIndex = 0,
    dialogue = Dialogue(
        "Дар мағоза",
        listOf(
            DialogueLine("Firuz", "How much does this shirt cost?", "Ин курта чанд пул аст?"),
            DialogueLine("Clerk", "The price is twenty dollars, but there is a discount.", "Нарх бист доллар, аммо тахфиф ҳаст."),
            DialogueLine("Firuz", "Can I pay by card?", "Метавонам бо корт пардохт кунам?"),
            DialogueLine("Clerk", "Yes. Here is your receipt and your change in a bag.", "Ҳа. Ин чек ва хурди шумо дар сумка."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w21_1", "Store", "Мағоза", "Store", "I go to the store", "Ман ба мағоза меравам", "es_m6_l1"),
        WordItem("es_w21_2", "Price", "Нарх", "Price", "What is the price?", "Нарх чист?", "es_m6_l1"),
        WordItem("es_w21_3", "Discount", "Тахфиф", "Dis-count", "There is a ten percent discount", "Даҳ фоиз тахфиф ҳаст", "es_m6_l1"),
        WordItem("es_w21_4", "Receipt", "Чек", "Re-ceipt", "Keep your receipt", "Чекро нигоҳ доред", "es_m6_l1"),
        WordItem("es_w21_5", "Cash", "Нақд", "Cash", "I pay in cash", "Бо нақд пардохт мекунам", "es_m6_l1"),
        WordItem("es_w21_6", "Card", "Корт", "Card", "Pay by card", "Бо корт пардохт кунед", "es_m6_l1"),
        WordItem("es_w21_7", "Change", "Хурд", "Change", "Here is your change", "Ин хурди шумо", "es_m6_l1"),
        WordItem("es_w21_8", "Bag", "Сумка", "Bag", "Put it in the bag", "Дар сумка гузоред", "es_m6_l1"),
    ),
    grammarTip = GrammarTip(
        "How much does this cost? / Can I pay by card?",
        "Барои нарх аз «How much does it cost?» ва барои корт аз «Can I pay by card?» истифода баред.",
        listOf("How much does this cost?", "Can I pay by card?", "Is there a discount?"),
    ),
    exercises = listOf(
        Exercise("es_e21_1", ExerciseType.MULTIPLE_CHOICE, "«Receipt» чӣ маъно дорад?", "Receipt = ...", listOf("Нарх", "Чек", "Сумка", "Тахфиф"), "Чек", 1, "Receipt — Чек"),
        Exercise("es_e21_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Can I pay by _____?", listOf("cash", "card", "price", "receipt"), "card", 1, "Can I pay by card"),
        Exercise("es_e21_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("How much is the bag?", "How much does this cost?", "How much is the store?", "How much is your card?"), "How much does this cost?", 1, "How much does this cost?"),
        Exercise("es_e21_4", ExerciseType.TYPE_ANSWER, "«Тахфиф»-ро ба англисӣ нависед:", "Тахфиф = ?", null, "Discount", null, "Discount — Тахфиф"),
        Exercise("es_e21_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Cash" to "Нақд", "Change" to "Хурд", "Price" to "Нарх", "Bag" to "Сумка")),
        Exercise("es_e21_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Put it in the bag", null, "Put it in the bag", words = listOf("bag", "the", "in", "Put", "it")),
        Exercise("es_e21_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Can I pay by card?\nClerk: _____", null, listOf("The store is closed.", "Yes, you can.", "The price is a bag."), "Yes, you can.", 1, "Ҷавоби иҷозат"),
        Exercise("es_e21_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Dis-count", listOf("Receipt", "Discount", "Change", "Store"), "Discount", 1, "Discount — Тахфиф"),
    ),
)

// ── Lesson 22: Дар ресторан ─────────────────────

internal val esM6L2 = Lesson(
    id = "es_m6_l2", moduleId = "es_m6",
    title = "Дар ресторан", description = "Меню, фармоиш ва инъом",
    emoji = "\uD83C\uDF7D\uFE0F", orderIndex = 1,
    dialogue = Dialogue(
        "Шом дар ресторан",
        listOf(
            DialogueLine("Firuz", "I'd like to reserve a table for two.", "Ман миз барои ду нафар захира кардан мехоҳам."),
            DialogueLine("Host", "Here is the menu. Would you like an appetizer?", "Ин меню. Пешхӯрок мехоҳед?"),
            DialogueLine("Firuz", "I'll order the main course. It looks delicious.", "Хӯроки асосиро фармоиш мекунам. Болаззат ба назар мерасад."),
            DialogueLine("Host", "Service was good; a tip is welcome.", "Хизмат хуб буд; инъом хуш омадааст."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w22_1", "Reserve", "Захира кардан", "Re-serve", "Reserve a table", "Миз захира кунед", "es_m6_l2"),
        WordItem("es_w22_2", "Table", "Миз", "Ta-ble", "A table for four", "Миз барои чор нафар", "es_m6_l2"),
        WordItem("es_w22_3", "Menu", "Меню", "Men-u", "Could I have the menu?", "Меню медиҳед?", "es_m6_l2"),
        WordItem("es_w22_4", "Dish", "Ғизо", "Dish", "This dish is spicy", "Ин ғизо тунд аст", "es_m6_l2"),
        WordItem("es_w22_5", "Appetizer", "Пешхӯрок", "Ap-pe-ti-zer", "We ordered an appetizer", "Мо пешхӯрок фармоиш кардем", "es_m6_l2"),
        WordItem("es_w22_6", "Main course", "Хӯроки асосӣ", "Main course", "The main course was great", "Хӯроки асосӣ аъло буд", "es_m6_l2"),
        WordItem("es_w22_7", "Tip", "Инъом", "Tip", "Leave a small tip", "Инъоми хурд гузоред", "es_m6_l2"),
        WordItem("es_w22_8", "Delicious", "Болаззат", "De-li-cious", "The soup is delicious", "Шӯрбо болаззат аст", "es_m6_l2"),
    ),
    grammarTip = GrammarTip(
        "I'd like to reserve... / Could I have the menu?",
        "Дар ресторан аз «I'd like to reserve a table» ва «Could I have the menu?» истифода баред.",
        listOf("I'd like to reserve a table for two.", "Could I have the menu, please?", "The main course was delicious."),
    ),
    exercises = listOf(
        Exercise("es_e22_1", ExerciseType.MULTIPLE_CHOICE, "«Appetizer» чӣ маъно дорад?", "Appetizer = ...", listOf("Хӯроки асосӣ", "Пешхӯрок", "Меню", "Инъом"), "Пешхӯрок", 1, "Appetizer — Пешхӯрок"),
        Exercise("es_e22_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Could I have the _____?", listOf("tip", "table", "menu", "dish"), "menu", 2, "Could I have the menu"),
        Exercise("es_e22_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I'd like a tip for two", "I'd like to reserve a table for two", "I'd like a menu table", "I'd like a dish appetizer"), "I'd like to reserve a table for two", 1, "I'd like to reserve..."),
        Exercise("es_e22_4", ExerciseType.TYPE_ANSWER, "«Болаззат»-ро ба англисӣ нависед:", "Болаззат = ?", null, "Delicious", null, "Delicious — Болаззат"),
        Exercise("es_e22_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Reserve" to "Захира кардан", "Main course" to "Хӯроки асосӣ", "Tip" to "Инъом", "Dish" to "Ғизо")),
        Exercise("es_e22_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "The main course was delicious", null, "The main course...", words = listOf("delicious", "was", "course", "main", "The")),
        Exercise("es_e22_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nHost: Here is the menu.\nFiruz: _____", null, listOf("I want a tip.", "I'd like an appetizer.", "The table is delicious."), "I'd like an appetizer.", 1, "Фармоиш"),
        Exercise("es_e22_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A De-li-cious", listOf("Menu", "Delicious", "Reserve", "Table"), "Delicious", 1, "Delicious — Болаззат"),
    ),
)

// ── Lesson 23: Нақлиёт ───────────────────────────

internal val esM6L3 = Lesson(
    id = "es_m6_l3", moduleId = "es_m6",
    title = "Нақлиёт", description = "Поезд, чипта ва ҷадвал",
    emoji = "\uD83D\uDE8C", orderIndex = 2,
    dialogue = Dialogue(
        "Дар истгоҳ",
        listOf(
            DialogueLine("Anna", "What time does the train leave?", "Поезд дар чӣ вақт меравад?"),
            DialogueLine("Firuz", "According to the schedule, it departs at six.", "Бо ҷадвал, дар соати шаш меравад."),
            DialogueLine("Anna", "Is this the right platform?", "Ин саккуи дуруст аст?"),
            DialogueLine("Firuz", "There is a short delay, but we will arrive on time.", "Таъхири кӯтоҳ ҳаст, аммо ба вақт мерасем."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w23_1", "Bus", "Автобус", "Bus", "Take the bus downtown", "Ба марказ бо автобус равед", "es_m6_l3"),
        WordItem("es_w23_2", "Train", "Поезд", "Train", "The train is late", "Поезд дер аст", "es_m6_l3"),
        WordItem("es_w23_3", "Ticket", "Чипта", "Tick-et", "Buy a ticket online", "Чиптаро онлайн харед", "es_m6_l3"),
        WordItem("es_w23_4", "Platform", "Сакку", "Plat-form", "Wait on platform two", "Дар саккуи ду интизор шавед", "es_m6_l3"),
        WordItem("es_w23_5", "Schedule", "Ҷадвал", "Sched-ule", "Check the train schedule", "Ҷадвали поездро санҷед", "es_m6_l3"),
        WordItem("es_w23_6", "Delay", "Таъхир", "De-lay", "Sorry for the delay", "Бубахшед барои таъхир", "es_m6_l3"),
        WordItem("es_w23_7", "Arrive", "Расидан", "Ar-rive", "We arrive at nine", "Мо дар соати нӯҳ мерасем", "es_m6_l3"),
        WordItem("es_w23_8", "Depart", "Рафтан", "De-part", "The bus departs soon", "Автобус ба зудӣ меравад", "es_m6_l3"),
    ),
    grammarTip = GrammarTip(
        "What time does the train leave? / Is this the right platform?",
        "Барои вақт аз «What time does... leave?» ва барои сакку аз «Is this the right platform?» истифода баред.",
        listOf("What time does the train leave?", "Is this the right platform?", "There is a delay on the line."),
    ),
    exercises = listOf(
        Exercise("es_e23_1", ExerciseType.MULTIPLE_CHOICE, "«Platform» чӣ маъно дорад?", "Platform = ...", listOf("Чипта", "Сакку", "Таъхир", "Ҷадвал"), "Сакку", 1, "Platform — Сакку"),
        Exercise("es_e23_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "What time does the train _____?", listOf("arrive", "delay", "leave", "schedule"), "leave", 2, "What time does the train leave"),
        Exercise("es_e23_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Is this the right ticket?", "Is this the right platform?", "Is this the right bus schedule?", "Is this the right delay?"), "Is this the right platform?", 1, "Right platform"),
        Exercise("es_e23_4", ExerciseType.TYPE_ANSWER, "«Таъхир»-ро ба англисӣ нависед:", "Таъхир = ?", null, "Delay", null, "Delay — Таъхир"),
        Exercise("es_e23_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Ticket" to "Чипта", "Train" to "Поезд", "Arrive" to "Расидан", "Depart" to "Рафтан")),
        Exercise("es_e23_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "There is a short delay", null, "There is a short delay", words = listOf("delay", "short", "a", "is", "There")),
        Exercise("es_e23_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Is this the right platform?\nFiruz: _____", null, listOf("The train is a bus.", "Yes, platform two.", "The ticket is late."), "Yes, platform two.", 1, "Platform"),
        Exercise("es_e23_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Sched-ule", listOf("Schedule", "Platform", "Delay", "Ticket"), "Schedule", 0, "Schedule — Ҷадвал"),
    ),
)

// ── Lesson 24: Бонк ва пул ───────────────────────

internal val esM6L4 = Lesson(
    id = "es_m6_l4", moduleId = "es_m6",
    title = "Бонк ва пул", description = "Ҳисоб, интиқол ва асъор",
    emoji = "\uD83C\uDFE6", orderIndex = 3,
    dialogue = Dialogue(
        "Дар бонк",
        listOf(
            DialogueLine("Firuz", "I'd like to open an account.", "Ман ҳисоб кушодан мехоҳам."),
            DialogueLine("Banker", "You can deposit cash or transfer money online.", "Шумо нақд гузошта метавонед ё пулро онлайн интиқол диҳед."),
            DialogueLine("Firuz", "What's the exchange rate for this currency?", "Нархи асъор барои ин асъор чист?"),
            DialogueLine("Banker", "The interest on the loan depends on your history.", "Фоизи қарз аз таърихи шумо вобаста аст."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w24_1", "Bank", "Бонк", "Bank", "Meet me at the bank", "Дар бонк шинос шавем", "es_m6_l4"),
        WordItem("es_w24_2", "Account", "Ҳисоб", "Ac-count", "Open a savings account", "Ҳисоби пасандоз кушоед", "es_m6_l4"),
        WordItem("es_w24_3", "Transfer", "Интиқол", "Trans-fer", "Transfer money safely", "Пулро бехатар интиқол диҳед", "es_m6_l4"),
        WordItem("es_w24_4", "Deposit", "Пасандоз", "De-posit", "Make a deposit", "Пасандоз кунед", "es_m6_l4"),
        WordItem("es_w24_5", "Withdraw", "Гирифтан", "With-draw", "Withdraw cash", "Нақд гиред", "es_m6_l4"),
        WordItem("es_w24_6", "Interest", "Фоиз", "In-ter-est", "The interest rate is low", "Фоиз паст аст", "es_m6_l4"),
        WordItem("es_w24_7", "Loan", "Қарз", "Loan", "Apply for a loan", "Барои қарз ариза диҳед", "es_m6_l4"),
        WordItem("es_w24_8", "Currency", "Асъор", "Cur-ren-cy", "Exchange currency here", "Инҷо асъор иваз кунед", "es_m6_l4"),
    ),
    grammarTip = GrammarTip(
        "I'd like to open an account / What's the exchange rate?",
        "Барои хизматҳои бонкӣ аз ин ҷумлаҳо истифода баред.",
        listOf("I'd like to open an account.", "What's the exchange rate?", "I want to withdraw some cash."),
    ),
    exercises = listOf(
        Exercise("es_e24_1", ExerciseType.MULTIPLE_CHOICE, "«Withdraw» дар ин контекст чӣ маъно дорад?", null, listOf("Пасандоз кардан", "Гирифтан (нақд)", "Интиқол додан", "Фоиз гирифтан"), "Гирифтан (нақд)", 1, "Withdraw — гирифтан"),
        Exercise("es_e24_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "What's the _____ rate?", listOf("loan", "bank", "exchange", "account"), "exchange", 2, "exchange rate"),
        Exercise("es_e24_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I want to close a currency", "I'd like to open an account", "I'd like to open a loan rate", "I want a bank ticket"), "I'd like to open an account", 1, "I'd like to open an account"),
        Exercise("es_e24_4", ExerciseType.TYPE_ANSWER, "«Қарз»-ро ба англисӣ нависед:", "Қарз = ?", null, "Loan", null, "Loan — Қарз"),
        Exercise("es_e24_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Deposit" to "Пасандоз", "Transfer" to "Интиқол", "Interest" to "Фоиз", "Currency" to "Асъор")),
        Exercise("es_e24_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Transfer money online", null, "Transfer money online", words = listOf("online", "money", "Transfer")),
        Exercise("es_e24_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: What's the exchange rate?\nBanker: _____", null, listOf("The bank is closed.", "It is 1.12 for this currency.", "Your loan is a deposit."), "It is 1.12 for this currency.", 1, "Exchange rate"),
        Exercise("es_e24_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ac-count", listOf("Account", "Currency", "Loan", "Interest"), "Account", 0, "Account — Ҳисоб"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 7 · КАСБ ВА КОР (Career)
// ═══════════════════════════════════════════════════

// ── Lesson 25: Резюме ────────────────────────────

internal val esM7L1 = Lesson(
    id = "es_m7_l1", moduleId = "es_m7",
    title = "Резюме", description = "CV: таҷриба, маълумот, ҳадаф",
    emoji = "\uD83D\uDCC4", orderIndex = 0,
    dialogue = Dialogue(
        "Тайёр кардани CV",
        listOf(
            DialogueLine("Firuz", "My resume lists my education and work experience.", "Резюмеи ман таҳсил ва таҷрибаи корро нишон медиҳад."),
            DialogueLine("Advisor", "Add your skills and one professional reference.", "Маҳоратҳо ва як тавсияи касбӣ илова кунед."),
            DialogueLine("Firuz", "My objective is to get a job in IT.", "Ҳадафи ман гирифтани кор дар IT аст."),
            DialogueLine("Advisor", "Mention your qualifications and key achievements.", "Ихтисос ва дастоварди асосиро зикр кунед."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w25_1", "Resume", "Резюме", "Re-sume", "Update your resume", "Резюмеро нав кунед", "es_m7_l1"),
        WordItem("es_w25_2", "Experience", "Таҷриба", "Ex-pe-ri-ence", "I have five years of experience", "Ман панҷ сол таҷриба дорам", "es_m7_l1"),
        WordItem("es_w25_3", "Education", "Маълумот", "Ed-u-ca-tion", "Education section on the CV", "Бахши маълумот дар CV", "es_m7_l1"),
        WordItem("es_w25_4", "Skill", "Маҳорат", "Skill", "Computer skills", "Маҳоратҳои компютерӣ", "es_m7_l1"),
        WordItem("es_w25_5", "Reference", "Тавсия", "Ref-er-ence", "Provide a reference", "Тавсия пешниҳод кунед", "es_m7_l1"),
        WordItem("es_w25_6", "Qualification", "Ихтисос", "Qual-i-fi-ca-tion", "What are your qualifications?", "Ихтисоси шумо чист?", "es_m7_l1"),
        WordItem("es_w25_7", "Objective", "Ҳадаф", "Ob-jec-tive", "State your career objective", "Ҳадафи касбии худро бигӯед", "es_m7_l1"),
        WordItem("es_w25_8", "Achievement", "Дастоварди", "A-chieve-ment", "List your achievements", "Дастовардиҳоро нависед", "es_m7_l1"),
    ),
    grammarTip = GrammarTip(
        "I have experience in... / I graduated from...",
        "Барои таҷриба аз «I have experience in...» ва барои таҳсил аз «I graduated from...» истифода баред.",
        listOf("I have experience in teaching.", "I graduated from the university in 2024.", "My objective is to grow."),
    ),
    exercises = listOf(
        Exercise("es_e25_1", ExerciseType.MULTIPLE_CHOICE, "«Reference» дар CV чӣ маъно дорад?", null, listOf("Маҳорат", "Тавсия", "Ҳадаф", "Ихтисос"), "Тавсия", 1, "Reference — Тавсия"),
        Exercise("es_e25_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I have _____ in customer service.", listOf("education", "experience", "objective", "resume"), "experience", 1, "I have experience in..."),
        Exercise("es_e25_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I graduated from a skill", "I graduated from the university", "I graduated from my resume", "I graduated from a reference"), "I graduated from the university", 1, "I graduated from..."),
        Exercise("es_e25_4", ExerciseType.TYPE_ANSWER, "«Дастоварди»-ро ба англисӣ нависед:", "Дастоварди = ?", null, "Achievement", null, "Achievement — Дастоварди"),
        Exercise("es_e25_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Resume" to "Резюме", "Qualification" to "Ихтисос", "Skill" to "Маҳорат", "Objective" to "Ҳадаф")),
        Exercise("es_e25_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "List your key achievements", null, "List your achievements", words = listOf("achievements", "your", "key", "List")),
        Exercise("es_e25_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAdvisor: What is your objective?\nFiruz: _____", null, listOf("I need a reference.", "To work in IT and grow my skills.", "My resume is education."), "To work in IT and grow my skills.", 1, "Objective"),
        Exercise("es_e25_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ex-pe-ri-ence", listOf("Education", "Experience", "Achievement", "Skill"), "Experience", 1, "Experience — Таҷриба"),
    ),
)

// ── Lesson 26: Мусоҳиба ──────────────────────────

internal val esM7L2 = Lesson(
    id = "es_m7_l2", moduleId = "es_m7",
    title = "Мусоҳиба", description = "Саволҳои кор ва маош",
    emoji = "\uD83E\uDD1D", orderIndex = 1,
    dialogue = Dialogue(
        "Мусоҳибаи кор",
        listOf(
            DialogueLine("Manager", "Tell me about yourself and this position.", "Дар бораи худ ва ин вазифа нақл кунед."),
            DialogueLine("Firuz", "My strength is teamwork; I am working on public speaking.", "Ҷанбаи қавии ман кори дастаӣ; дар суханронии оммавӣ кор мекунам."),
            DialogueLine("Manager", "What salary and benefits do you expect?", "Маош ва кадом имтиёзро интизор мешавед?"),
            DialogueLine("Firuz", "I hope you will hire me, not reject my application.", "Умедворам маро ба кор қабул мекунед, на аризаро рад мекунед."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w26_1", "Interview", "Мусоҳиба", "In-ter-view", "The interview is tomorrow", "Мусоҳиба фардо аст", "es_m7_l2"),
        WordItem("es_w26_2", "Position", "Вазифа", "Po-si-tion", "Apply for this position", "Барои ин вазифа ариза диҳед", "es_m7_l2"),
        WordItem("es_w26_3", "Salary", "Маош", "Sal-a-ry", "Discuss salary politely", "Дар бораи маош муҳтано гап занед", "es_m7_l2"),
        WordItem("es_w26_4", "Benefit", "Имтиёз", "Ben-e-fit", "Health benefits", "Имтиёзҳои тандурустӣ", "es_m7_l2"),
        WordItem("es_w26_5", "Strength", "Ҷанбаи қавӣ", "Strength", "What is your greatest strength?", "Қувваи асосии шумо чист?", "es_m7_l2"),
        WordItem("es_w26_6", "Weakness", "Ҷанбаи заиф", "Weak-ness", "Mention a real weakness", "Заифии воқеиро бигӯед", "es_m7_l2"),
        WordItem("es_w26_7", "Hire", "Ба кор қабул кардан", "Hire", "We want to hire you", "Мо шуморо ба кор мехоҳем", "es_m7_l2"),
        WordItem("es_w26_8", "Reject", "Рад кардан", "Re-ject", "They may reject late applications", "Аризаҳои дерро рад карда метавонанд", "es_m7_l2"),
    ),
    grammarTip = GrammarTip(
        "Tell me about yourself / Why should we hire you?",
        "Дар мусоҳиба ба саволҳои стандартӣ кӯтоҳ ва равшан ҷавоб диҳед.",
        listOf("Tell me about yourself.", "Why should we hire you?", "What are your strengths?"),
    ),
    exercises = listOf(
        Exercise("es_e26_1", ExerciseType.MULTIPLE_CHOICE, "«Benefit» дар кор чӣ маъно дорад?", null, listOf("Маош", "Имтиёз", "Вазифа", "Заифӣ"), "Имтиёз", 1, "Benefit — Имтиёз"),
        Exercise("es_e26_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "We would like to _____ you for this role.", listOf("reject", "interview", "hire", "weakness"), "hire", 2, "We would like to hire you"),
        Exercise("es_e26_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Tell me about your salary", "Tell me about yourself", "Tell me about your weakness only", "Tell me about the position hire"), "Tell me about yourself", 1, "Tell me about yourself"),
        Exercise("es_e26_4", ExerciseType.TYPE_ANSWER, "«Ҷанбаи заиф»-ро ба англисӣ нависед:", "Ҷанбаи заиф = ?", null, "Weakness", null, "Weakness — Ҷанбаи заиф"),
        Exercise("es_e26_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Salary" to "Маош", "Position" to "Вазифа", "Strength" to "Ҷанбаи қавӣ", "Reject" to "Рад кардан")),
        Exercise("es_e26_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Why should we hire you", null, "Why should we hire you", words = listOf("you", "hire", "we", "should", "Why")),
        Exercise("es_e26_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nManager: What salary do you expect?\nFiruz: _____", null, listOf("Please reject me.", "I expect a fair salary and benefits.", "My weakness is salary."), "I expect a fair salary and benefits.", 1, "Salary"),
        Exercise("es_e26_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Sal-a-ry", listOf("Benefit", "Salary", "Interview", "Position"), "Salary", 1, "Salary — Маош"),
    ),
)

// ── Lesson 27: Дар офис ───────────────────────────

internal val esM7L3 = Lesson(
    id = "es_m7_l3", moduleId = "es_m7",
    title = "Дар офис", description = "Гузориш, мӯҳлат ва ҳамкор",
    emoji = "\uD83C\uDFE2", orderIndex = 2,
    dialogue = Dialogue(
        "Рӯзи корӣ",
        listOf(
            DialogueLine("Anna", "Could you send me the report by email?", "Метавонед гузоришро бо почта фиристед?"),
            DialogueLine("Tom", "Yes, I will print it from the printer near my desk.", "Ҳа, аз чопгар назди мизи ман чоп мекунам."),
            DialogueLine("Anna", "The deadline is Friday; we may need overtime.", "Мӯҳлат рӯзи ҷумъа; шояд иловакорӣ лозим шавад."),
            DialogueLine("Tom", "I will ask a colleague to review the document.", "Аз ҳамкор хоҳиш мекунам, ҳуҷҷатро санҷад."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w27_1", "Office", "Офис", "Of-fice", "I work in an office", "Ман дар офис кор мекунам", "es_m7_l3"),
        WordItem("es_w27_2", "Desk", "Миз", "Desk", "Clean your desk", "Мизро тоза нигоҳ доред", "es_m7_l3"),
        WordItem("es_w27_3", "Printer", "Чопгар", "Print-er", "The printer is broken", "Чопгар вайрон аст", "es_m7_l3"),
        WordItem("es_w27_4", "Document", "Ҳуҷҷат", "Doc-u-ment", "Sign this document", "Ин ҳуҷҷатро имзо кунед", "es_m7_l3"),
        WordItem("es_w27_5", "Report", "Гузориш", "Re-port", "Finish the monthly report", "Гузориши моҳонаро анҷом диҳед", "es_m7_l3"),
        WordItem("es_w27_6", "Deadline", "Мӯҳлат", "Dead-line", "The deadline is strict", "Мӯҳлат сахт аст", "es_m7_l3"),
        WordItem("es_w27_7", "Overtime", "Иловакорӣ", "O-ver-time", "I worked overtime", "Ман иловакорӣ кардам", "es_m7_l3"),
        WordItem("es_w27_8", "Colleague", "Ҳамкор", "Col-league", "My colleague helps me", "Ҳамкор ба ман кӯмак мекунад", "es_m7_l3"),
    ),
    grammarTip = GrammarTip(
        "Could you send me the report? / The deadline is...",
        "Барои дархост аз «Could you send me...?» ва барои мӯҳлат аз «The deadline is...» истифода баред.",
        listOf("Could you send me the report?", "The deadline is Friday at five.", "I need to finish the document."),
    ),
    exercises = listOf(
        Exercise("es_e27_1", ExerciseType.MULTIPLE_CHOICE, "«Colleague» чӣ маъно дорад?", "Colleague = ...", listOf("Чопгар", "Ҳамкор", "Гузориш", "Мӯҳлат"), "Ҳамкор", 1, "Colleague — Ҳамкор"),
        Exercise("es_e27_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "The _____ is Friday at five.", listOf("office", "desk", "deadline", "printer"), "deadline", 2, "The deadline is..."),
        Exercise("es_e27_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Could you delete the report?", "Could you send me the report?", "Could you print the deadline?", "Could you desk the document?"), "Could you send me the report?", 1, "Could you send me..."),
        Exercise("es_e27_4", ExerciseType.TYPE_ANSWER, "«Иловакорӣ»-ро ба англисӣ нависед:", "Иловакорӣ = ?", null, "Overtime", null, "Overtime — Иловакорӣ"),
        Exercise("es_e27_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Printer" to "Чопгар", "Document" to "Ҳуҷҷат", "Report" to "Гузориш", "Office" to "Офис")),
        Exercise("es_e27_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I need to finish the document", null, "finish the document", words = listOf("document", "the", "finish", "to", "need", "I")),
        Exercise("es_e27_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: The deadline is Friday.\nTom: _____", null, listOf("I will delete the office.", "I may need overtime.", "The desk is Friday."), "I may need overtime.", 1, "Overtime"),
        Exercise("es_e27_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Print-er", listOf("Report", "Printer", "Deadline", "Colleague"), "Printer", 1, "Printer — Чопгар"),
    ),
)

// ── Lesson 28: Бизнес ────────────────────────────

internal val esM7L4 = Lesson(
    id = "es_m7_l4", moduleId = "es_m7",
    title = "Бизнес", description = "Ширкат, бозор ва муштарӣ",
    emoji = "\uD83D\uDCC8", orderIndex = 3,
    dialogue = Dialogue(
        "Ҳамкории тиҷоратӣ",
        listOf(
            DialogueLine("Firuz", "Our company specializes in language apps.", "Ширкати мо дар барномаҳои забонӣ ихтисос дорад."),
            DialogueLine("Client", "We offer a new service for small businesses.", "Мо хизмати нав барои тиҷоратҳои хурд пешниҳод мекунем."),
            DialogueLine("Firuz", "The market is competitive, but profit is possible.", "Бозор рақобатӣ аст, аммо фоида мумкин аст."),
            DialogueLine("Client", "We need smart investment against strong competition.", "Барои рақобати сахт сармоягузории ақлона лозим аст."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w28_1", "Company", "Ширкат", "Com-pa-ny", "A large company", "Ширкати калон", "es_m7_l4"),
        WordItem("es_w28_2", "Client", "Муштарӣ", "Cli-ent", "The client is happy", "Муштарӣ хушҳол аст", "es_m7_l4"),
        WordItem("es_w28_3", "Product", "Маҳсулот", "Prod-uct", "Sell a new product", "Маҳсулоти нав фурӯшед", "es_m7_l4"),
        WordItem("es_w28_4", "Service", "Хизмат", "Ser-vice", "Customer service", "Хизмати муштариён", "es_m7_l4"),
        WordItem("es_w28_5", "Market", "Бозор", "Mar-ket", "Enter the market", "Ба бозор ворид шавед", "es_m7_l4"),
        WordItem("es_w28_6", "Profit", "Фоида", "Prof-it", "We need more profit", "Ба мо фоидаи бештар лозим аст", "es_m7_l4"),
        WordItem("es_w28_7", "Investment", "Сармоягузорӣ", "In-vest-ment", "Make a safe investment", "Сармоягузории бехатар кунед", "es_m7_l4"),
        WordItem("es_w28_8", "Competition", "Рақобат", "Com-pe-ti-tion", "Competition is high", "Рақобат баланд аст", "es_m7_l4"),
    ),
    grammarTip = GrammarTip(
        "Our company specializes in... / We offer...",
        "Барои тавсифи ширкат аз «Our company specializes in...» ва барои хизмат аз «We offer...» истифода баред.",
        listOf("Our company specializes in education.", "We offer free training.", "The market is growing fast."),
    ),
    exercises = listOf(
        Exercise("es_e28_1", ExerciseType.MULTIPLE_CHOICE, "«Competition» чӣ маъно дорад?", "Competition = ...", listOf("Фоида", "Рақобат", "Бозор", "Сармоя"), "Рақобат", 1, "Competition — Рақобат"),
        Exercise("es_e28_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "We offer a new _____ for small businesses.", listOf("profit", "market", "service", "competition"), "service", 2, "We offer a service"),
        Exercise("es_e28_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Our company specializes in music food", "Our company specializes in language apps", "Our company specializes in clients only", "Our company specializes competition"), "Our company specializes in language apps", 1, "specializes in..."),
        Exercise("es_e28_4", ExerciseType.TYPE_ANSWER, "«Маҳсулот»-ро ба англисӣ нависед:", "Маҳсулот = ?", null, "Product", null, "Product — Маҳсулот"),
        Exercise("es_e28_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Client" to "Муштарӣ", "Profit" to "Фоида", "Investment" to "Сармоягузорӣ", "Product" to "Маҳсулот")),
        Exercise("es_e28_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "The market is competitive", null, "The market is competitive", words = listOf("competitive", "is", "market", "The")),
        Exercise("es_e28_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: We need more profit.\nClient: _____", null, listOf("The product is a market.", "Consider a new investment.", "Competition is a service."), "Consider a new investment.", 1, "Investment"),
        Exercise("es_e28_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Prof-it", listOf("Market", "Profit", "Company", "Service"), "Profit", 1, "Profit — Фоида"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 8 · ФАРҲАНГ (Culture & Media)
// ═══════════════════════════════════════════════════

// ── Lesson 29: Кино ва мусиқӣ ───────────────────

internal val esM8L1 = Lesson(
    id = "es_m8_l1", moduleId = "es_m8",
    title = "Кино ва мусиқӣ", description = "Филм, жанр ва консерт",
    emoji = "\uD83C\uDFAC", orderIndex = 0,
    dialogue = Dialogue(
        "Охири ҳафта",
        listOf(
            DialogueLine("Anna", "Have you seen the new movie by this director?", "Шумо фили нави ин коргардонро дидаед?"),
            DialogueLine("Firuz", "My favorite genre is drama, not horror.", "Жанри дӯстдоштаи ман драма аст, на даҳшат."),
            DialogueLine("Anna", "The actor won an award at the concert last week.", "Актёр ҳафтаи гузашта дар консерт ҷоиза гирифт."),
            DialogueLine("Firuz", "I bought the album online.", "Ман албомро онлайн хариддам."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w29_1", "Movie", "Филм", "Mov-ie", "Watch a movie", "Филм тамошо кунед", "es_m8_l1"),
        WordItem("es_w29_2", "Genre", "Жанр", "Genre", "Choose a genre", "Жанр интихоб кунед", "es_m8_l1"),
        WordItem("es_w29_3", "Director", "Коргардон", "Di-rec-tor", "A famous director", "Коргардони шинохта", "es_m8_l1"),
        WordItem("es_w29_4", "Actor", "Актёр", "Ac-tor", "The actor is talented", "Актёр истеъдод дорад", "es_m8_l1"),
        WordItem("es_w29_5", "Song", "Суруд", "Song", "My favorite song", "Суруди дӯстдошта", "es_m8_l1"),
        WordItem("es_w29_6", "Concert", "Концерт", "Con-cert", "Go to a concert", "Ба консерт равед", "es_m8_l1"),
        WordItem("es_w29_7", "Album", "Албом", "Al-bum", "A new album", "Албоми нав", "es_m8_l1"),
        WordItem("es_w29_8", "Award", "Ҷоиза", "A-ward", "Win an award", "Ҷоиза гиред", "es_m8_l1"),
    ),
    grammarTip = GrammarTip(
        "Have you seen...? / My favorite genre is...",
        "Барои тавсия аз «Have you seen...?» ва барои жанр аз «My favorite genre is...» истифода баред.",
        listOf("Have you seen this film?", "My favorite genre is comedy.", "The concert was amazing."),
    ),
    exercises = listOf(
        Exercise("es_e29_1", ExerciseType.MULTIPLE_CHOICE, "«Genre» чӣ маъно дорад?", "Genre = ...", listOf("Филм", "Жанр", "Ҷоиза", "Актёр"), "Жанр", 1, "Genre — Жанр"),
        Exercise("es_e29_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Have you _____ the new movie?", listOf("won", "seen", "sung", "awarded"), "seen", 1, "Have you seen..."),
        Exercise("es_e29_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("My favorite album is horror", "My favorite genre is drama", "My favorite director is a song", "My favorite concert is genre"), "My favorite genre is drama", 1, "My favorite genre is..."),
        Exercise("es_e29_4", ExerciseType.TYPE_ANSWER, "«Концерт»-ро ба англисӣ нависед:", "Концерт = ?", null, "Concert", null, "Concert — Концерт"),
        Exercise("es_e29_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Director" to "Коргардон", "Actor" to "Актёр", "Song" to "Суруд", "Award" to "Ҷоиза")),
        Exercise("es_e29_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I bought the album online", null, "I bought the album online", words = listOf("online", "album", "the", "bought", "I")),
        Exercise("es_e29_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Have you seen this film?\nFiruz: _____", null, listOf("My genre is a concert.", "Not yet, but I want to.", "The actor is an album."), "Not yet, but I want to.", 1, "Have you seen..."),
        Exercise("es_e29_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Di-rec-tor", listOf("Actor", "Director", "Genre", "Movie"), "Director", 1, "Director — Коргардон"),
    ),
)

// ── Lesson 30: Китоб ва адабиёт ─────────────────

internal val esM8L2 = Lesson(
    id = "es_m8_l2", moduleId = "es_m8",
    title = "Китоб ва адабиёт", description = "Роман, муаллиф ва сюжет",
    emoji = "\uD83D\uDCD5", orderIndex = 1,
    dialogue = Dialogue(
        "Дар китобхона",
        listOf(
            DialogueLine("Firuz", "I'm reading a novel about space travel.", "Ман роман дар бораи саёҳати кайҳонӣ мехонам."),
            DialogueLine("Librarian", "The author published a new chapter online.", "Муаллиф боби навро онлайн нашр кард."),
            DialogueLine("Firuz", "The plot is exciting and the characters are deep.", "Сюжет ҷолиб аст ва қаҳрамонҳо чуқуранд."),
            DialogueLine("Librarian", "Do you prefer fiction or poetry?", "Бадеӣ ё шеърро афзал медоред?"),
        ),
    ),
    newWords = listOf(
        WordItem("es_w30_1", "Novel", "Роман", "Nov-el", "Read a long novel", "Романи дароз хонед", "es_m8_l2"),
        WordItem("es_w30_2", "Author", "Муаллиф", "Au-thor", "The author writes well", "Муаллиф хуб менависад", "es_m8_l2"),
        WordItem("es_w30_3", "Chapter", "Боб", "Chap-ter", "Chapter one", "Боби якум", "es_m8_l2"),
        WordItem("es_w30_4", "Character", "Қаҳрамон", "Char-ac-ter", "Main character", "Қаҳрамони асосӣ", "es_m8_l2"),
        WordItem("es_w30_5", "Plot", "Сюжет", "Plot", "The plot is surprising", "Сюжет тааҷҷубовар аст", "es_m8_l2"),
        WordItem("es_w30_6", "Fiction", "Бадеӣ", "Fic-tion", "Science fiction", "Илми бадеӣ", "es_m8_l2"),
        WordItem("es_w30_7", "Poetry", "Шеър", "Po-e-try", "She writes poetry", "Вай шеър менависад", "es_m8_l2"),
        WordItem("es_w30_8", "Publish", "Нашр кардан", "Pub-lish", "Publish a book", "Китоб нашр кунед", "es_m8_l2"),
    ),
    grammarTip = GrammarTip(
        "I'm reading a book about... / The story is about...",
        "Барои мавзӯъ аз «I'm reading a book about...» ва «The story is about...» истифода баред.",
        listOf("I'm reading a book about history.", "The story is about friendship.", "The author is famous."),
    ),
    exercises = listOf(
        Exercise("es_e30_1", ExerciseType.MULTIPLE_CHOICE, "«Plot» чӣ маъно дорад?", "Plot = ...", listOf("Қаҳрамон", "Сюжет", "Боб", "Муаллиф"), "Сюжет", 1, "Plot — Сюжет"),
        Exercise("es_e30_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "The author will _____ a new chapter.", listOf("read", "publish", "plot", "fiction"), "publish", 1, "publish a chapter"),
        Exercise("es_e30_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("The story is about a library", "The story is about friendship", "The story is about poetry only", "The story is a novel author"), "The story is about friendship", 1, "The story is about..."),
        Exercise("es_e30_4", ExerciseType.TYPE_ANSWER, "«Қаҳрамон»-ро ба англисӣ нависед:", "Қаҳрамон = ?", null, "Character", null, "Character — Қаҳрамон"),
        Exercise("es_e30_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Novel" to "Роман", "Chapter" to "Боб", "Fiction" to "Бадеӣ", "Poetry" to "Шеър")),
        Exercise("es_e30_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I'm reading a novel about space", null, "I'm reading a novel about...", words = listOf("space", "novel", "a", "about", "reading", "I'm")),
        Exercise("es_e30_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nLibrarian: Fiction or poetry?\nFiruz: _____", null, listOf("The plot is an author.", "I prefer fiction this month.", "My chapter is poetry."), "I prefer fiction this month.", 1, "Fiction or poetry"),
        Exercise("es_e30_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Nov-el", listOf("Novel", "Publish", "Author", "Plot"), "Novel", 0, "Novel — Роман"),
    ),
)

// ── Lesson 31: Ахбор ва медиа ────────────────────

internal val esM8L3 = Lesson(
    id = "es_m8_l3", moduleId = "es_m8",
    title = "Ахбор ва медиа", description = "Мақола, манбаъ ва пахш",
    emoji = "\uD83D\uDCF0", orderIndex = 2,
    dialogue = Dialogue(
        "Хабарҳои имрӯза",
        listOf(
            DialogueLine("Tom", "According to the news, prices will rise.", "Бо мувофиқи ахбор, нархҳо боло мераванд."),
            DialogueLine("Anna", "I read an article by a famous journalist.", "Ман мақолаи рӯзноманигори шинохтаро хондам."),
            DialogueLine("Tom", "The headline was shocking; check your sources.", "Сарлавҳа тааҷҷубовар буд; манбаъҳоро санҷед."),
            DialogueLine("Anna", "The broadcast showed a live interview.", "Пахш мусоҳибаи мустақим нишон дод."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w31_1", "News", "Ахбор", "News", "Watch the news", "Ахбор тамошо кунед", "es_m8_l3"),
        WordItem("es_w31_2", "Article", "Мақола", "Ar-ti-cle", "Read the article", "Мақоларо хонед", "es_m8_l3"),
        WordItem("es_w31_3", "Journalist", "Рӯзноманигор", "Jour-nal-ist", "A brave journalist", "Рӯзноманигори ҷасур", "es_m8_l3"),
        WordItem("es_w31_4", "Report", "Гузориш", "Re-port", "A detailed report", "Гузориши тафсилотӣ", "es_m8_l3"),
        WordItem("es_w31_5", "Source", "Манбаъ", "Source", "Trust your sources", "Ба манбаъҳои худ бовар кунед", "es_m8_l3"),
        WordItem("es_w31_6", "Headline", "Сарлавҳа", "Head-line", "The headline says...", "Сарлавҳа мегӯяд...", "es_m8_l3"),
        WordItem("es_w31_7", "Interview", "Мусоҳиба", "In-ter-view", "Watch the interview", "Мусоҳибаро тамошо кунед", "es_m8_l3"),
        WordItem("es_w31_8", "Broadcast", "Пахш", "Broad-cast", "Live broadcast", "Пахши мустақим", "es_m8_l3"),
    ),
    grammarTip = GrammarTip(
        "According to the news... / It was reported that...",
        "Барои хабарҳо аз «According to the news...» ва «It was reported that...» истифода баред.",
        listOf("According to the news, it will rain.", "It was reported that the team won.", "The article was long."),
    ),
    exercises = listOf(
        Exercise("es_e31_1", ExerciseType.MULTIPLE_CHOICE, "«Headline» чӣ маъно дорад?", "Headline = ...", listOf("Манбаъ", "Сарлавҳа", "Пахш", "Мақола"), "Сарлавҳа", 1, "Headline — Сарлавҳа"),
        Exercise("es_e31_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "It was _____ that the team won.", listOf("headline", "article", "reported", "broadcast"), "reported", 2, "It was reported that..."),
        Exercise("es_e31_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("According to the news, the sun is a headline", "According to the news, prices will rise", "According to the news, I am a journalist", "According to the article, news is broadcast"), "According to the news, prices will rise", 1, "According to the news..."),
        Exercise("es_e31_4", ExerciseType.TYPE_ANSWER, "«Манбаъ»-ро ба англисӣ нависед:", "Манбаъ = ?", null, "Source", null, "Source — Манбаъ"),
        Exercise("es_e31_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Journalist" to "Рӯзноманигор", "Article" to "Мақола", "Broadcast" to "Пахш", "Interview" to "Мусоҳиба")),
        Exercise("es_e31_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Trust your sources", null, "Trust your sources", words = listOf("sources", "your", "Trust")),
        Exercise("es_e31_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTom: Is this story true?\nAnna: _____", null, listOf("The live is headline.", "Check multiple sources.", "The broadcast is an article."), "Check multiple sources.", 1, "Sources"),
        Exercise("es_e31_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Jour-nal-ist", listOf("Article", "Journalist", "News", "Report"), "Journalist", 1, "Journalist — Рӯзноманигор"),
    ),
)

// ── Lesson 32: Шабакаҳои иҷтимоӣ ─────────────────

internal val esM8L4 = Lesson(
    id = "es_m8_l4", moduleId = "es_m8",
    title = "Шабакаҳои иҷтимоӣ", description = "Нашр, пайравӣ ва мундариҷа",
    emoji = "\uD83D\uDCF1", orderIndex = 3,
    dialogue = Dialogue(
        "Онлайн",
        listOf(
            DialogueLine("Firuz", "I posted about my trip yesterday.", "Ман дар бораи саёҳатам дирӯз нашр кардам."),
            DialogueLine("Anna", "How many followers do you have on this app?", "Дар ин барнома чанд пайрав доред?"),
            DialogueLine("Firuz", "Please like and share my comment.", "Лутфан писанд кунед ва шарҳи маро мубодила кунед."),
            DialogueLine("Anna", "Many influencers create educational content.", "Таъсиргузорони зиёд мундариҷаи таълимӣ месозанд."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w32_1", "Post", "Нашр", "Post", "Write a post", "Нашр нависед", "es_m8_l4"),
        WordItem("es_w32_2", "Follow", "Пайравӣ", "Fol-low", "Follow my page", "Ба саҳифаи ман пайравӣ кунед", "es_m8_l4"),
        WordItem("es_w32_3", "Like", "Писанд", "Like", "Like this photo", "Ин аксро писанд кунед", "es_m8_l4"),
        WordItem("es_w32_4", "Share", "Мубодила", "Share", "Share with friends", "Бо дӯстон мубодила кунед", "es_m8_l4"),
        WordItem("es_w32_5", "Comment", "Шарҳ", "Com-ment", "Leave a comment", "Шарҳ гузоред", "es_m8_l4"),
        WordItem("es_w32_6", "Subscribe", "Обуна", "Sub-scribe", "Subscribe to the channel", "Ба канал обуна шавед", "es_m8_l4"),
        WordItem("es_w32_7", "Content", "Мундариҷа", "Con-tent", "Original content", "Мундариҷаи аслӣ", "es_m8_l4"),
        WordItem("es_w32_8", "Influencer", "Таъсиргузор", "In-flu-en-cer", "A popular influencer", "Таъсиргузори маъмул", "es_m8_l4"),
    ),
    grammarTip = GrammarTip(
        "I posted about... / How many followers do you have?",
        "Барои шабакаҳои иҷтимоӣ аз ин саволҳо истифода баред.",
        listOf("I posted about the concert.", "How many followers do you have?", "Please subscribe to my channel."),
    ),
    exercises = listOf(
        Exercise("es_e32_1", ExerciseType.MULTIPLE_CHOICE, "«Subscribe» чӣ маъно дорад?", "Subscribe = ...", listOf("Писанд", "Обуна", "Нашр", "Шарҳ"), "Обуна", 1, "Subscribe — Обуна"),
        Exercise("es_e32_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "How many _____ do you have?", listOf("posts", "likes", "followers", "comments"), "followers", 2, "followers"),
        Exercise("es_e32_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I posted about your subscribe", "I posted about my trip yesterday", "I posted about a follower like", "I posted comment content"), "I posted about my trip yesterday", 1, "I posted about..."),
        Exercise("es_e32_4", ExerciseType.TYPE_ANSWER, "«Мундариҷа»-ро ба англисӣ нависед:", "Мундариҷа = ?", null, "Content", null, "Content — Мундариҷа"),
        Exercise("es_e32_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Like" to "Писанд", "Share" to "Мубодила", "Comment" to "Шарҳ", "Follow" to "Пайравӣ")),
        Exercise("es_e32_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Please subscribe to my channel", null, "Please subscribe...", words = listOf("channel", "my", "to", "subscribe", "Please")),
        Exercise("es_e32_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Do you like this content?\nFiruz: _____", null, listOf("I am an influencer post.", "Yes, please like and share.", "My followers are comments."), "Yes, please like and share.", 1, "Like and share"),
        Exercise("es_e32_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A In-flu-en-cer", listOf("Content", "Influencer", "Subscribe", "Post"), "Influencer", 1, "Influencer — Таъсиргузор"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 9 · МАҲОРАТИ ПЕШРАФТА (Advanced Skills)
// ═══════════════════════════════════════════════════

// ── Lesson 33: Баҳсу мунозира ────────────────────

internal val esM9L1 = Lesson(
    id = "es_m9_l1", moduleId = "es_m9",
    title = "Баҳсу мунозира", description = "Далел, розӣ ва норозӣ",
    emoji = "\u2696\uFE0F", orderIndex = 0,
    dialogue = Dialogue(
        "Клуби баҳс",
        listOf(
            DialogueLine("Firuz", "I strongly believe education should be free.", "Ман қавӣ бовар дорам, ки таълим бояд ройгон бошад."),
            DialogueLine("Anna", "On the other hand, schools need money.", "Аз тарафи дигар, ба мактабҳо пул лозим аст."),
            DialogueLine("Firuz", "Let me support my point with data.", "Бигузор маълумот нуктаи маро дастгирӣ кунад."),
            DialogueLine("Anna", "I disagree, but I will try to convince you politely.", "Ман норозӣ, аммо кӯшиш мекунам шуморо муҳтано қонеъ кунам."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w33_1", "Debate", "Баҳс", "De-bate", "Join the debate", "Ба баҳс ширкат кунед", "es_m9_l1"),
        WordItem("es_w33_2", "Argue", "Далел овардан", "Ar-gue", "Argue with facts", "Бо далелҳо баҳс кунед", "es_m9_l1"),
        WordItem("es_w33_3", "Agree", "Розӣ шудан", "A-gree", "I agree with you", "Ман бо шумо розӣ", "es_m9_l1"),
        WordItem("es_w33_4", "Disagree", "Норозӣ будан", "Dis-a-gree", "I disagree politely", "Ман муҳтано норозӣ", "es_m9_l1"),
        WordItem("es_w33_5", "Point", "Нукта", "Point", "Make your main point", "Нуктаи асосии худро бигӯед", "es_m9_l1"),
        WordItem("es_w33_6", "Support", "Дастгирӣ", "Sup-port", "Support your idea", "Фикри худро дастгирӣ кунед", "es_m9_l1"),
        WordItem("es_w33_7", "Against", "Зидди", "A-gainst", "I am against this plan", "Ман зидди ин нақша", "es_m9_l1"),
        WordItem("es_w33_8", "Convince", "Қонеъ кардан", "Con-vince", "Try to convince them", "Кӯшиш кунед қонеъ кунед", "es_m9_l1"),
    ),
    grammarTip = GrammarTip(
        "I strongly believe... / On the other hand...",
        "Барои баҳс аз «I strongly believe...» барои муқобилат аз «On the other hand...» истифода баред.",
        listOf("I strongly believe in fairness.", "On the other hand, costs are real.", "I agree with your point."),
    ),
    exercises = listOf(
        Exercise("es_e33_1", ExerciseType.MULTIPLE_CHOICE, "«Against» чӣ маъно дорад?", "Against = ...", listOf("Дастгирӣ", "Зидди", "Нукта", "Баҳс"), "Зидди", 1, "Against — Зидди"),
        Exercise("es_e33_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____, we must think about money.", listOf("I agree", "On the other hand", "I strongly believe", "I convince"), "On the other hand", 1, "On the other hand"),
        Exercise("es_e33_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I strongly believe in sleep", "I strongly believe education matters", "I strongly point against agree", "I strongly debate your support"), "I strongly believe education matters", 1, "I strongly believe..."),
        Exercise("es_e33_4", ExerciseType.TYPE_ANSWER, "«Қонеъ кардан»-ро ба англисӣ нависед:", "Қонеъ кардан = ?", null, "Convince", null, "Convince — Қонеъ кардан"),
        Exercise("es_e33_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Debate" to "Баҳс", "Agree" to "Розӣ шудан", "Disagree" to "Норозӣ будан", "Argue" to "Далел овардан")),
        Exercise("es_e33_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I disagree with this plan", null, "I disagree...", words = listOf("plan", "this", "with", "disagree", "I")),
        Exercise("es_e33_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Do you agree?\nAnna: _____", null, listOf("I am against facts.", "Not really, I disagree.", "I convince the debate."), "Not really, I disagree.", 1, "Disagree"),
        Exercise("es_e33_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Con-vince", listOf("Convince", "Point", "Support", "Against"), "Convince", 0, "Convince — Қонеъ кардан"),
    ),
)

// ── Lesson 34: Муаммо ва ҳал ─────────────────────

internal val esM9L2 = Lesson(
    id = "es_m9_l2", moduleId = "es_m9",
    title = "Муаммо ва ҳал", description = "Таҳлил ва стратегия",
    emoji = "\uD83E\uDDE9", orderIndex = 1,
    dialogue = Dialogue(
        "Коргоҳи ҳал",
        listOf(
            DialogueLine("Tom", "The main problem is unclear communication.", "Муаммои асосӣ муоширати но равшан аст."),
            DialogueLine("Anna", "We could solve this by weekly meetings.", "Мо инро бо ҷамъомади ҳафтаина ҳал карда метавонем."),
            DialogueLine("Tom", "First, analyze the cause and the effect.", "Аввал сабаб ва натиҷаро таҳлил кунед."),
            DialogueLine("Anna", "Then evaluate the strategy and improve the process.", "Баъд стратегияро арзёбӣ кунед ва равандро беҳтар кунед."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w34_1", "Problem", "Муаммо", "Prob-lem", "Define the problem", "Муамморо муайян кунед", "es_m9_l2"),
        WordItem("es_w34_2", "Solution", "Роҳи ҳал", "So-lu-tion", "Find a solution", "Роҳи ҳал ёбед", "es_m9_l2"),
        WordItem("es_w34_3", "Analyze", "Таҳлил кардан", "An-a-lyze", "Analyze the data", "Маълумотро таҳлил кунед", "es_m9_l2"),
        WordItem("es_w34_4", "Cause", "Сабаб", "Cause", "Find the root cause", "Сабаби решаиро ёбед", "es_m9_l2"),
        WordItem("es_w34_5", "Effect", "Натиҷа", "Ef-fect", "Understand the effect", "Натиҷаро фаҳмед", "es_m9_l2"),
        WordItem("es_w34_6", "Strategy", "Стратегия", "Strat-e-gy", "Choose a strategy", "Стратегия интихоб кунед", "es_m9_l2"),
        WordItem("es_w34_7", "Improve", "Беҳтар кардан", "Im-prove", "Improve quality", "Сифатро беҳтар кунед", "es_m9_l2"),
        WordItem("es_w34_8", "Evaluate", "Арзёбӣ кардан", "E-val-u-ate", "Evaluate the results", "Натиҷаҳоро арзёбӣ кунед", "es_m9_l2"),
    ),
    grammarTip = GrammarTip(
        "The main problem is... / We could solve this by...",
        "Барои масъала ва ҳал аз ин сохторҳо истифода баред.",
        listOf("The main problem is time.", "We could solve this by planning.", "Let's analyze the causes."),
    ),
    exercises = listOf(
        Exercise("es_e34_1", ExerciseType.MULTIPLE_CHOICE, "«Cause and effect» чӣ маъно дорад?", null, listOf("Стратегия ва ҳал", "Сабаб ва натиҷа", "Муаммо ва стратегия", "Таҳлил ва арзёбӣ"), "Сабаб ва натиҷа", 1, "Cause and effect"),
        Exercise("es_e34_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "We need to _____ the results carefully.", listOf("cause", "evaluate", "effect", "problem"), "evaluate", 1, "evaluate the results"),
        Exercise("es_e34_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("The main problem is unclear communication", "The main solution is unclear communication", "The main strategy is a problem", "The main effect is cause"), "The main problem is unclear communication", 0, "The main problem is..."),
        Exercise("es_e34_4", ExerciseType.TYPE_ANSWER, "«Стратегия»-ро ба англисӣ нависед:", "Стратегия = ?", null, "Strategy", null, "Strategy — Стратегия"),
        Exercise("es_e34_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Analyze" to "Таҳлил кардан", "Solution" to "Роҳи ҳал", "Improve" to "Беҳтар кардан", "Problem" to "Муаммо")),
        Exercise("es_e34_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "We could solve this by planning", null, "We could solve this by...", words = listOf("planning", "by", "this", "solve", "could", "We")),
        Exercise("es_e34_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTom: What should we do first?\nAnna: _____", null, listOf("Let's ignore the effect.", "First, analyze the cause.", "The strategy is a problem."), "First, analyze the cause.", 1, "Analyze the cause"),
        Exercise("es_e34_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Strat-e-gy", listOf("Strategy", "Effect", "Cause", "Evaluate"), "Strategy", 0, "Strategy — Стратегия"),
    ),
)

// ── Lesson 35: Ҳамкорӣ ────────────────────────────

internal val esM9L3 = Lesson(
    id = "es_m9_l3", moduleId = "es_m9",
    title = "Ҳамкорӣ", description = "Даста, нақш ва ҳавасмандӣ",
    emoji = "\uD83D\uDC65", orderIndex = 2,
    dialogue = Dialogue(
        "Лоиҳаи дастаӣ",
        listOf(
            DialogueLine("Leader", "Let's work together on this goal.", "Биёед дар ин ҳадаф якҷоя кор кунем."),
            DialogueLine("Firuz", "Who is responsible for the budget in our team?", "Дар дастаи мо барои буҷет кӣ масъул аст?"),
            DialogueLine("Leader", "Everyone should contribute ideas and communicate clearly.", "Ҳар кас бояд фикрҳо саҳм гузорад ва равшан муоширот кунад."),
            DialogueLine("Firuz", "Good leaders motivate people when work is hard.", "Роҳбарони хуб одамонро ҳавасманд мекунанд, вақте кор сахт аст."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w35_1", "Team", "Дастаи", "Team", "Our team is strong", "Дастаи мо қавӣ аст", "es_m9_l3"),
        WordItem("es_w35_2", "Leader", "Роҳбар", "Lead-er", "She is the team leader", "Вай роҳбари даста аст", "es_m9_l3"),
        WordItem("es_w35_3", "Cooperate", "Ҳамкорӣ", "Co-op-er-ate", "We cooperate well", "Мо хуб ҳамкорӣ мекунем", "es_m9_l3"),
        WordItem("es_w35_4", "Role", "Нақш", "Role", "What is your role?", "Нақши шумо чист?", "es_m9_l3"),
        WordItem("es_w35_5", "Contribute", "Саҳм гузоштан", "Con-trib-ute", "Contribute your skills", "Маҳоратҳои худро саҳм гузоред", "es_m9_l3"),
        WordItem("es_w35_6", "Communicate", "Муоширот", "Com-mu-ni-cate", "Communicate often", "Зиёд муоширот кунед", "es_m9_l3"),
        WordItem("es_w35_7", "Goal", "Ҳадаф", "Goal", "Reach the goal", "Ба ҳадаф расед", "es_m9_l3"),
        WordItem("es_w35_8", "Motivate", "Ҳавасманд кардан", "Mo-ti-vate", "Motivate the team", "Дастаро ҳавасманд кунед", "es_m9_l3"),
    ),
    grammarTip = GrammarTip(
        "Let's work together on... / Who is responsible for...?",
        "Барои ҳамкорӣ аз «Let's work together on...» ва барои масъулият аз «Who is responsible for...?» истифода баред.",
        listOf("Let's work together on the report.", "Who is responsible for the budget?", "We share the same goal."),
    ),
    exercises = listOf(
        Exercise("es_e35_1", ExerciseType.MULTIPLE_CHOICE, "«Contribute» чӣ маъно дорад?", "Contribute = ...", listOf("Ҳадаф", "Саҳм гузоштан", "Нақш", "Роҳбар"), "Саҳм гузоштан", 1, "Contribute — Саҳм гузоштан"),
        Exercise("es_e35_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Who is responsible for the _____?", listOf("leader", "team", "budget", "goal"), "budget", 2, "responsible for the budget"),
        Exercise("es_e35_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Let's work alone on the goal", "Let's work together on this goal", "Let's work communicate on the team", "Let's work motivate the role"), "Let's work together on this goal", 1, "Let's work together on..."),
        Exercise("es_e35_4", ExerciseType.TYPE_ANSWER, "«Ҳавасманд кардан»-ро ба англисӣ нависед:", "Ҳавасманд кардан = ?", null, "Motivate", null, "Motivate — Ҳавасманд кардан"),
        Exercise("es_e35_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Team" to "Дастаи", "Leader" to "Роҳбар", "Role" to "Нақш", "Goal" to "Ҳадаф")),
        Exercise("es_e35_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Communicate with your team", null, "Communicate with your team", words = listOf("team", "your", "with", "Communicate")),
        Exercise("es_e35_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nLeader: We need motivation.\nFiruz: _____", null, listOf("Let's change the goal.", "Good leaders motivate people.", "Who is the team budget?"), "Good leaders motivate people.", 1, "Motivate"),
        Exercise("es_e35_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Co-op-er-ate", listOf("Communicate", "Cooperate", "Contribute", "Motivate"), "Cooperate", 1, "Cooperate — Ҳамкорӣ"),
    ),
)

// ── Lesson 36: Тақдимот ──────────────────────────

internal val esM9L4 = Lesson(
    id = "es_m9_l4", moduleId = "es_m9",
    title = "Тақдимот", description = "Омор, график ва бозхӯрд",
    emoji = "\uD83D\uDCCA", orderIndex = 3,
    dialogue = Dialogue(
        "Пешниҳоди лоиҳа",
        listOf(
            DialogueLine("Firuz", "As you can see from the graph, sales rose.", "Чи тавр аз график мебинед, фурӯш боло рафт."),
            DialogueLine("Anna", "The visual slide uses clear statistics.", "Слайди намоишӣ омори равшан истифода мекунад."),
            DialogueLine("Firuz", "In summary, we should engage the audience more.", "Ба хулоса, бояд шунавандагонро бештар ҷалб кунем."),
            DialogueLine("Teacher", "Thank you; your feedback will help us improve.", "Ташаккур; бозхӯрди шумо ба мо барои такмил кӯмак мекунад."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w36_1", "Present", "Пешниҳод", "Pre-sent", "Present your ideas", "Фикрҳои худро пешниҳод кунед", "es_m9_l4"),
        WordItem("es_w36_2", "Visual", "Намоишӣ", "Vis-u-al", "Use visual aids", "Алатҳои намоишӣ истифода баред", "es_m9_l4"),
        WordItem("es_w36_3", "Statistics", "Омор", "Sta-tis-tics", "Read the statistics", "Оморро хонед", "es_m9_l4"),
        WordItem("es_w36_4", "Graph", "График", "Graph", "Explain the graph", "Графикро шарҳ диҳед", "es_m9_l4"),
        WordItem("es_w36_5", "Conclusion", "Хулоса", "Con-clu-sion", "State your conclusion", "Хулосаи худро бигӯед", "es_m9_l4"),
        WordItem("es_w36_6", "Engage", "Ҷалб кардан", "En-gage", "Engage the audience", "Шунавандагонро ҷалб кунед", "es_m9_l4"),
        WordItem("es_w36_7", "Feedback", "Бозхӯрд", "Feed-back", "We need feedback", "Ба мо бозхӯрд лозим аст", "es_m9_l4"),
        WordItem("es_w36_8", "Improve", "Такмил додан", "Im-prove", "Improve the slides", "Слайдҳоро такмил диҳед", "es_m9_l4"),
    ),
    grammarTip = GrammarTip(
        "As you can see from the graph... / In summary...",
        "Барои нишон додани рақамҳо аз «As you can see from the graph...» ва барои охир аз «In summary...» истифода баред.",
        listOf("As you can see from the graph, we grew.", "In summary, thank you for listening.", "The statistics look positive."),
    ),
    exercises = listOf(
        Exercise("es_e36_1", ExerciseType.MULTIPLE_CHOICE, "«Statistics» чӣ маъно дорад?", "Statistics = ...", listOf("График", "Омор", "Бозхӯрд", "Намоишӣ"), "Омор", 1, "Statistics — Омор"),
        Exercise("es_e36_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "In _____, we finished the main points.", listOf("graph", "summary", "visual", "engage"), "summary", 1, "In summary"),
        Exercise("es_e36_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("As you can see from the graph, sales rose", "As you can see from the feedback, graph rose", "As you can see from the visual, statistics engage", "As you can improve from the conclusion"), "As you can see from the graph, sales rose", 0, "As you can see from the graph..."),
        Exercise("es_e36_4", ExerciseType.TYPE_ANSWER, "«Бозхӯрд»-ро ба англисӣ нависед:", "Бозхӯрд = ?", null, "Feedback", null, "Feedback — Бозхӯрд"),
        Exercise("es_e36_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Graph" to "График", "Engage" to "Ҷалб кардан", "Improve" to "Такмил додан", "Visual" to "Намоишӣ")),
        Exercise("es_e36_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Engage the audience during the talk", null, "Engage the audience", words = listOf("the", "talk", "during", "audience", "Engage")),
        Exercise("es_e36_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTeacher: Any final words?\nFiruz: _____", null, listOf("The graph is feedback.", "In summary, thank you for listening.", "Statistics are visual."), "In summary, thank you for listening.", 1, "In summary"),
        Exercise("es_e36_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Feed-back", listOf("Conclusion", "Feedback", "Graph", "Present"), "Feedback", 1, "Feedback — Бозхӯрд"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 10 · ИМТИҲОНИ НИҲОӢ (Final Assessment)
// ═══════════════════════════════════════════════════

// ── Lesson 37: Такрори грамматика ────────────────

internal val esM10L1 = Lesson(
    id = "es_m10_l1", moduleId = "es_m10",
    title = "Такрори грамматика", description = "Ҷамъбасти қоидаҳо аз модули 1–9",
    emoji = "\u2705", orderIndex = 0,
    dialogue = Dialogue(
        "Машқи қавӣ",
        listOf(
            DialogueLine("Teacher", "Choose the correct tense: I study every day — which rule?", "Замони дурустро интихоб кунед: I study every day — кадом қоида?"),
            DialogueLine("Firuz", "Present simple — habits and facts.", "Ҳозираи одӣ — одатҳо ва ҳақиқатҳо."),
            DialogueLine("Teacher", "Now: I am studying right now.", "Акнун: I am studying right now."),
            DialogueLine("Firuz", "Present continuous — action happening now.", "Ҳозираи давомдор — амали ҳозира."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w37_1", "Review", "Такрор", "Re-view", "Grammar review week", "Ҳафтаи такрори грамматика", "es_m10_l1"),
        WordItem("es_w37_2", "Tense", "Замон", "Tense", "Mixed tenses", "Замонҳои омехта", "es_m10_l1"),
        WordItem("es_w37_3", "Habit", "Одат", "Hab-it", "Daily habit", "Одати ҳаррӯза", "es_m10_l1"),
        WordItem("es_w37_4", "Action", "Амал", "Ac-tion", "Action in progress", "Амали ҷорӣ", "es_m10_l1"),
        WordItem("es_w37_5", "Correct", "Дуруст", "Cor-rect", "Choose the correct form", "Шакли дурустро интихоб кунед", "es_m10_l1"),
        WordItem("es_w37_6", "Error", "Хато", "Er-ror", "Find the error", "Хаторо ёбед", "es_m10_l1"),
        WordItem("es_w37_7", "Rule", "Қоида", "Rule", "Remember the rule", "Қоидаро дар хотир доред", "es_m10_l1"),
        WordItem("es_w37_8", "Practice", "Машқ", "Prac-tice", "Challenging practice", "Машқи сахт", "es_m10_l1"),
    ),
    grammarTip = GrammarTip(
        "Mixed grammar from all modules",
        "Ҳозираи одӣ барои одатҳо; ҳозираи давомдор барои амалҳои ҳозира; гузашта ва оянда аз модулҳои қаблӣ.",
        listOf("I work every day.", "I am working now.", "She has finished her task."),
    ),
    exercises = listOf(
        Exercise("es_e37_1", ExerciseType.MULTIPLE_CHOICE, "Кадом ҷумла дуруст аст?", null, listOf("I am knowing the answer", "I know the answer", "I knowing the answer", "I knows the answer"), "I know the answer", 1, "know — дар ҳозираи одӣ шакли махсус"),
        Exercise("es_e37_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "She _____ English for three years. (experience)", listOf("studies", "has studied", "is studying", "study"), "has studied", 1, "Present perfect барои давра"),
        Exercise("es_e37_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("They goes to school", "They go to school every day", "They going to school", "They is going school"), "They go to school every day", 1, "Present simple, ҷамъ"),
        Exercise("es_e37_4", ExerciseType.TYPE_ANSWER, "Хатои ҷумларо ислоҳ кунед — ҷумлаи дурустро нависед:", "He don't like math → ?", null, "He doesn't like math", null, "He doesn't — сеюм шахс якум"),
        Exercise("es_e37_5", ExerciseType.MATCH_PAIRS, "Замонро бо мисол мувофиқ кунед", null, null, "", null, "Ҳар як замон мисоли худро дорад", pairs = listOf("Present simple" to "I work every day", "Present continuous" to "I am working now", "Past simple" to "I worked yesterday", "Future" to "I will work tomorrow")),
        Exercise("es_e37_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I have finished my homework", null, "Present perfect", words = listOf("homework", "my", "finished", "have", "I")),
        Exercise("es_e37_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTeacher: Are you busy?\nFiruz: _____", null, listOf("Yes, I work on my project right now.", "Yes, I work every year now.", "Yes, I am work."), "Yes, I work on my project right now.", 0, "Present continuous"),
        Exercise("es_e37_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Prac-tice", listOf("Practice", "Error", "Rule", "Tense"), "Practice", 0, "Practice — Машқ"),
    ),
)

// ── Lesson 38: Такрори луғат ────────────────────

internal val esM10L2 = Lesson(
    id = "es_m10_l2", moduleId = "es_m10",
    title = "Такрори луғат", description = "Калимаҳои калидӣ аз ҳамаи модулҳо",
    emoji = "\uD83D\uDD24", orderIndex = 1,
    dialogue = Dialogue(
        "Санҷиши луғат",
        listOf(
            DialogueLine("Anna", "Match academic words with daily words you learned.", "Калимаҳои академиро бо калимаҳои ҳаррӯза мувофиқ кунед."),
            DialogueLine("Firuz", "University, career, and project are still important.", "Донишгоҳ, касб ва лоиҳа ҳанӯз муҳиманд."),
            DialogueLine("Anna", "Use context: bank, store, and interview in sentences.", "Аз мазмун истифода баред: бонк, мағоза, мусоҳиба дар ҷумла."),
            DialogueLine("Firuz", "I will build sentences with eight random words.", "Ман бо ҳашт калимаи тасодуфӣ ҷумла месозам."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w38_1", "Vocabulary", "Луғат", "Vo-cab-u-lar-y", "Expand vocabulary", "Луғатро васеъ кунед", "es_m10_l2"),
        WordItem("es_w38_2", "Recall", "Ба ёд овардан", "Re-call", "Recall key terms", "Истилоҳҳои асосиро ба ёд оваред", "es_m10_l2"),
        WordItem("es_w38_3", "Context", "Мазмун", "Con-text", "Guess from context", "Аз мазмун пешгӯӣ кунед", "es_m10_l2"),
        WordItem("es_w38_4", "Collocation", "Ҳамҷоягӣ", "Col-lo-ca-tion", "Learn collocations", "Ҳамҷоягиҳоро омӯзед", "es_m10_l2"),
        WordItem("es_w38_5", "Synonym", "Маъношабоҳ", "Syn-o-nym", "Find synonyms", "Маъношабоҳҳоро ёбед", "es_m10_l2"),
        WordItem("es_w38_6", "Antonym", "Зидматънос", "Ant-o-nym", "Opposite meanings", "Маъноҳои муқобил", "es_m10_l2"),
        WordItem("es_w38_7", "Phrase", "Ибора", "Phrase", "Useful phrases", "Ибораҳои фоиданок", "es_m10_l2"),
        WordItem("es_w38_8", "Mix", "Омехта", "Mix", "Mixed review", "Такрори омехта", "es_m10_l2"),
    ),
    grammarTip = GrammarTip(
        "Key words from all modules",
        "Калимаҳои асосии курсро дар ҷумлаҳо такрор кунед: таҳсил, кор, илм, харид, мусоҳиба.",
        listOf("education, career, experiment", "store, discount, receipt", "debate, evidence, conclusion"),
    ),
    exercises = listOf(
        Exercise("es_e38_1", ExerciseType.MULTIPLE_CHOICE, "Кадом калима ба «begin» маъношабоҳ аст?", null, listOf("End", "Start", "Stop", "Finish"), "Start", 1, "Start ≈ begin"),
        Exercise("es_e38_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "We made significant _____ in English. (прогресс)", listOf("project", "progress", "store", "ticket"), "progress", 1, "progress — прогресс"),
        Exercise("es_e38_3", ExerciseType.TRANSLATE_SENTENCE, "Мазмунро хонед ва ҷавоби дурустро интихоб кунед: «Ба ман стипендия лозим аст»", null, listOf("I need a scholarship", "I need a station", "I need a store", "I need a schedule"), "I need a scholarship", 0, "scholarship — стипендия"),
        Exercise("es_e38_4", ExerciseType.TYPE_ANSWER, "Зидматъности «cheap»-ро нависед:", "cheap → ?", null, "expensive", null, "expensive — зидди cheap"),
        Exercise("es_e38_5", ExerciseType.MATCH_PAIRS, "Калимаҳоро бо мавзӯъ мувофиқ кунед", null, null, "", null, "Гурӯҳбандӣ", pairs = listOf("Experiment" to "Science", "Receipt" to "Store", "Interview" to "Career", "Platform" to "Transport")),
        Exercise("es_e38_6", ExerciseType.BUILD_SENTENCE, "Аз калимаҳо ҷумла созед:", null, null, "Our team finished the project", null, "Collocation: finish the project", words = listOf("project", "the", "finished", "team", "Our")),
        Exercise("es_e38_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: I forgot the word for «таъхир».\nAnna: _____", null, listOf("It means library.", "It is «delay».", "It is «discount»."), "It is «delay».", 1, "delay — таъхир"),
        Exercise("es_e38_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Vo-cab-u-lar-y", listOf("Vocabulary", "Context", "Phrase", "Mix"), "Vocabulary", 0, "Vocabulary — Луғат"),
    ),
)

// ── Lesson 39: Мулоқоти комплексӣ ────────────────

internal val esM10L3 = Lesson(
    id = "es_m10_l3", moduleId = "es_m10",
    title = "Мулоқоти комплексӣ", description = "Суҳбати чандмавзӯӣ",
    emoji = "\uD83D\uDCAC", orderIndex = 2,
    dialogue = Dialogue(
        "Суҳбати чандмавзӯӣ",
        listOf(
            DialogueLine("Firuz", "After class I went to the store, then the bank.", "Баъди дарс ба мағоза, баъд ба бонк рафтам."),
            DialogueLine("Anna", "Busy day! Did you reserve a table for dinner?", "Рӯзи серкор! Миз барои шом захира кардед?"),
            DialogueLine("Firuz", "Yes, and I posted photos on social media later.", "Ҳа, ва дертар аксҳо дар шабака нашр кардам."),
            DialogueLine("Anna", "Great — you used English in many real situations.", "Аъло — шумо англисиро дар вазъиятҳои воқеӣ истифода бурдед."),
        ),
    ),
    newWords = listOf(
        WordItem("es_w39_1", "Complex", "Мураккаб", "Com-plex", "A complex conversation", "Суҳбати мураккаб", "es_m10_l3"),
        WordItem("es_w39_2", "Situation", "Вазъият", "Sit-u-a-tion", "Real-life situation", "Вазъияти воқеӣ", "es_m10_l3"),
        WordItem("es_w39_3", "Combine", "Якҷоя кардан", "Com-bine", "Combine grammar and vocabulary", "Грамматика ва луғатро якҷоя кунед", "es_m10_l3"),
        WordItem("es_w39_4", "Fluency", "Суфтагӣ", "Flu-en-cy", "Build fluency", "Суфтагӣ бисозед", "es_m10_l3"),
        WordItem("es_w39_5", "Topic", "Мавзӯъ", "Top-ic", "Switch topics smoothly", "Мавзӯъҳоро осон иваз кунед", "es_m10_l3"),
        WordItem("es_w39_6", "Sequence", "Пайдарпайӣ", "Se-quence", "Tell events in order", "Воқеаҳоро ба тартиб нақл кунед", "es_m10_l3"),
        WordItem("es_w39_7", "Detail", "Тафсилот", "De-tail", "Add useful details", "Тафсилоти фоиданок илова кунед", "es_m10_l3"),
        WordItem("es_w39_8", "Confidence", "Эътимод", "Con-fi-dence", "Speak with confidence", "Бо эътимод гап занед", "es_m10_l3"),
    ),
    grammarTip = GrammarTip(
        "Advanced mixed dialogue",
        "Барои суҳбати мураккаб аз пайдарпайии воқеаҳо ва гузариши мавзӯъ истифода баред.",
        listOf("First I studied, then I worked.", "I used new vocabulary in context.", "The conversation felt natural."),
    ),
    exercises = listOf(
        Exercise("es_e39_1", ExerciseType.MULTIPLE_CHOICE, "Кадом ҷумла пайдарпайии дуруст дорад?", null, listOf("Then I went to class after the bank.", "After class I went to the store, then the bank.", "I banked the class after the store.", "Store class bank after."), "After class I went to the store, then the bank.", 1, "Sequence"),
        Exercise("es_e39_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I posted photos on social _____ .", listOf("store", "media", "bank", "ticket"), "media", 1, "social media"),
        Exercise("es_e39_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Did you reserve a book for dinner?", "Did you reserve a table for dinner?", "Did you reserve a bank for dinner?", "Did you reserve media for dinner?"), "Did you reserve a table for dinner?", 1, "reserve a table"),
        Exercise("es_e39_4", ExerciseType.TYPE_ANSWER, "Ба англисӣ нависед: «бо эътимод гап задан»", "...", null, "speak with confidence", null, "speak with confidence"),
        Exercise("es_e39_5", ExerciseType.MATCH_PAIRS, "Мавзӯъро бо ҷумла мувофиқ кунед", null, null, "", null, "Фаҳмиши матн", pairs = listOf("Store" to "How much does it cost?", "Interview" to "Tell me about yourself", "Science" to "The experiment shows...", "Social media" to "How many followers?")),
        Exercise("es_e39_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "First I studied then I relaxed", null, "Sequence", words = listOf("relaxed", "I", "then", "studied", "First")),
        Exercise("es_e39_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: How was your day?\nFiruz: _____", null, listOf("The fluency is complex.", "Busy — class, shopping, and banking.", "My topic is a situation."), "Busy — class, shopping, and banking.", 1, "Complex answer"),
        Exercise("es_e39_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Flu-en-cy", listOf("Confidence", "Fluency", "Sequence", "Detail"), "Fluency", 1, "Fluency — Суфтагӣ"),
    ),
)

// ── Lesson 40: Имтиҳони ниҳоӣ ───────────────────

internal val esM10L4 = Lesson(
    id = "es_m10_l4", moduleId = "es_m10",
    title = "Имтиҳони ниҳоӣ", description = "Омехтаи ҳамаи намудҳои машқ",
    emoji = "\uD83C\uDF93", orderIndex = 3,
    dialogue = Dialogue(
        "Охирин санҷиш",
        listOf(
            DialogueLine("Teacher", "Welcome to the final exam section.", "Ба бахши имтиҳони ниҳоӣ хуш омадед."),
            DialogueLine("Firuz", "I will answer the hardest mixed questions.", "Ба саволҳои омехтаи сахт ҷавоб медиҳам."),
            DialogueLine("Teacher", "Show your best English: grammar, words, and logic.", "Беҳтарин англисии худро нишон диҳед: грамматика, калима ва мантиқ."),
            DialogueLine("Firuz", "Thank you for this course — I am ready!", "Ташаккур барои ин курс — ман омодаам!"),
        ),
    ),
    newWords = listOf(
        WordItem("es_w40_1", "Final", "Ниҳоӣ", "Fi-nal", "Final exam day", "Рӯзи имтиҳони ниҳоӣ", "es_m10_l4"),
        WordItem("es_w40_2", "Challenge", "Мушкилӣ", "Chal-lenge", "The biggest challenge", "Бузургтарин мушкилӣ", "es_m10_l4"),
        WordItem("es_w40_3", "Mastery", "Икром", "Mas-tery", "Path to mastery", "Роҳ ба икром", "es_m10_l4"),
        WordItem("es_w40_4", "Assessment", "Санҷиш", "As-sess-ment", "Complete assessment", "Санҷишро анҷом диҳед", "es_m10_l4"),
        WordItem("es_w40_5", "Score", "Натиҷа", "Score", "Aim for a high score", "Барои натиҷаи баланд кӯшиш кунед", "es_m10_l4"),
        WordItem("es_w40_6", "Focus", "Тамаркуз", "Fo-cus", "Stay focused", "Тамаркуз нигоҳ доред", "es_m10_l4"),
        WordItem("es_w40_7", "Effort", "Кӯшиш", "Ef-fort", "Your effort matters", "Кӯшиши шумо муҳим аст", "es_m10_l4"),
        WordItem("es_w40_8", "Success", "Муваффақият", "Suc-cess", "Wish you success", "Барои муваффақият орзу мекунем", "es_m10_l4"),
    ),
    grammarTip = GrammarTip(
        "Hardest mixed skills",
        "Ин бахш ҳамаи намудҳои машқро омехта мекунад: интихоб, тарҷума, ҷумласозӣ.",
        listOf("Read carefully.", "Think before you choose.", "Good luck!"),
    ),
    exercises = listOf(
        Exercise("es_e40_1", ExerciseType.MULTIPLE_CHOICE, "Кадом ҷумла дар сатҳи C1 наздиктар аст?", null, listOf("I go yesterday", "Not only did I finish, but I also revised", "I not only go but also", "Not only I finish"), "Not only did I finish, but I also revised", 1, "Not only... but also — инверсия"),
        Exercise("es_e40_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "If I _____ you, I would apply earlier. (шартии дуюм)", listOf("am", "was", "were", "be"), "were", 2, "If I were you — шартии дуюм"),
        Exercise("es_e40_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("She suggested me to leave", "She suggested leaving early", "She suggested to left", "She suggested leave"), "She suggested leaving early", 1, "suggest + -ing"),
        Exercise("es_e40_4", ExerciseType.TYPE_ANSWER, "Ишоракунандаи дурустро нависед: «ин китобҳо» (this + ҷамъ)", "...", null, "these books", null, "these + ҷамъ"),
        Exercise("es_e40_5", ExerciseType.MATCH_PAIRS, "Хато ва ислоҳ", null, null, "", null, "Мувофиқат", pairs = listOf("He don't like" to "He doesn't like", "I am agree" to "I agree", "She can to swim" to "She can swim", "They was here" to "They were here")),
        Exercise("es_e40_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Despite the rain we arrived on time", null, "Despite + сифатнома", words = listOf("time", "on", "arrived", "we", "the", "rain", "Despite")),
        Exercise("es_e40_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTeacher: Ready for the final task?\nFiruz: _____", null, listOf("My challenge is success.", "Yes — I have focused and I am ready.", "The assessment is effort."), "Yes — I have focused and I am ready.", 1, "Confidence"),
        Exercise("es_e40_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Suc-cess", listOf("Success", "Score", "Mastery", "Challenge"), "Success", 0, "Success — Муваффақият"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE & COURSE (modules 4–10)
// ═══════════════════════════════════════════════════

internal val esModule4 = Module(
    id = "es_m4", courseId = "study_english",
    title = "Илмҳои табиӣ",
    description = "Математика, илм, компютер ва технология",
    emoji = "\uD83D\uDD2C", orderIndex = 3,
    lessons = listOf(esM4L1, esM4L2, esM4L3, esM4L4),
)

internal val esModule5 = Module(
    id = "es_m5", courseId = "study_english",
    title = "Навиштан ва хондан",
    description = "Иншо, хондани матн, грамматика ва талаффуз",
    emoji = "\u270D\uFE0F", orderIndex = 4,
    lessons = listOf(esM5L1, esM5L2, esM5L3, esM5L4),
)

internal val esModule6 = Module(
    id = "es_m6", courseId = "study_english",
    title = "Муоширати ҳаррӯза",
    description = "Мағоза, ресторан, нақлиёт ва бонк",
    emoji = "\uD83D\uDED2", orderIndex = 5,
    lessons = listOf(esM6L1, esM6L2, esM6L3, esM6L4),
)

internal val esModule7 = Module(
    id = "es_m7", courseId = "study_english",
    title = "Касб ва кор",
    description = "Резюме, мусоҳиба, офис ва бизнес",
    emoji = "\uD83D\uDCBC", orderIndex = 6,
    lessons = listOf(esM7L1, esM7L2, esM7L3, esM7L4),
)

internal val esModule8 = Module(
    id = "es_m8", courseId = "study_english",
    title = "Фарҳанг",
    description = "Кино, адабиёт, ахбор ва шабакаҳои иҷтимоӣ",
    emoji = "\uD83C\uDFAC", orderIndex = 7,
    lessons = listOf(esM8L1, esM8L2, esM8L3, esM8L4),
)

internal val esModule9 = Module(
    id = "es_m9", courseId = "study_english",
    title = "Маҳорати пешрафта",
    description = "Баҳс, ҳалли масъала, ҳамкорӣ ва тақдимот",
    emoji = "\uD83C\uDFAF", orderIndex = 8,
    lessons = listOf(esM9L1, esM9L2, esM9L3, esM9L4),
)

internal val esModule10 = Module(
    id = "es_m10", courseId = "study_english",
    title = "Имтиҳони ниҳоӣ",
    description = "Такрори грамматика ва луғат, суҳбат ва санҷиши ниҳоӣ",
    emoji = "\uD83C\uDFC6", orderIndex = 9,
    lessons = listOf(esM10L1, esM10L2, esM10L3, esM10L4),
)
