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
//  MODULE 1 · ДАР ФУРУДГОҲ ВА ҲАВОПАЙМО
//            (Airport & Flight)
// ═══════════════════════════════════════════════════

// ── Lesson 1: Дар фурудгоҳ (At the Airport) ─────

private val etM1L1 = Lesson(
    id = "et_m1_l1", moduleId = "et_m1",
    title = "Дар фурудгоҳ", description = "Дар фурудгоҳ — сабтнавӣ ва парвоз",
    emoji = "\u2708\uFE0F", orderIndex = 0,
    dialogue = Dialogue(
        "Дар фурудгоҳ",
        listOf(
            DialogueLine("Staff", "May I see your passport, please?", "Лутфан паспортатонро нишон диҳед?"),
            DialogueLine("Firuz", "Here is my passport.", "Ин паспорти ман."),
            DialogueLine("Staff", "Where are you flying to?", "Ба куҷо парвоз мекунед?"),
            DialogueLine("Firuz", "I am flying to London.", "Ман ба Лондон парвоз мекунам."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w1_1", "Airport", "Фурудгоҳ", "Air-port", "I am at the airport", "Ман дар фурудгоҳ ҳастам", "et_m1_l1"),
        WordItem("et_w1_2", "Passport", "Паспорт", "Pass-port", "Show your passport", "Паспортатонро нишон диҳед", "et_m1_l1"),
        WordItem("et_w1_3", "Ticket", "Билет", "Tick-et", "I have my ticket", "Ман билетамро дорам", "et_m1_l1"),
        WordItem("et_w1_4", "Gate", "Дарвоза", "Gate", "Go to gate five", "Ба дарвозаи панҷ равед", "et_m1_l1"),
        WordItem("et_w1_5", "Flight", "Парвоз", "Flight", "My flight is at ten", "Парвози ман дар соати даҳ", "et_m1_l1"),
        WordItem("et_w1_6", "Luggage", "Бор", "Lug-gage", "Where is my luggage?", "Бори ман куҷост?", "et_m1_l1"),
        WordItem("et_w1_7", "Check-in", "Сабтнавӣ", "Check-in", "Check-in is over there", "Сабтнавӣ дар он ҷост", "et_m1_l1"),
        WordItem("et_w1_8", "Boarding", "Нишастан", "Board-ing", "Boarding starts at nine", "Нишастан дар соати нӯҳ оғоз мешавад", "et_m1_l1"),
    ),
    grammarTip = GrammarTip(
        "Where is gate...? / My flight is at...",
        "Барои пурсидани дарвоза аз «Where is gate...?» ва барои вақти парвоз аз «My flight is at...» истифода баред.",
        listOf("Where is gate five?", "My flight is at ten o'clock.", "Here is my passport."),
    ),
    exercises = listOf(
        Exercise("et_e1_1", ExerciseType.MULTIPLE_CHOICE, "«Airport» чӣ маъно дорад?", "Airport = ...", listOf("Истгоҳ", "Фурудгоҳ", "Меҳмонхона", "Мағоза"), "Фурудгоҳ", 1, "Airport — Фурудгоҳ"),
        Exercise("et_e1_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "May I see your _____, please?", listOf("ticket", "passport", "luggage", "gate"), "passport", 1, "May I see your passport — Паспортатонро нишон диҳед"),
        Exercise("et_e1_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман ба Лондон парвоз мекунам»-ро интихоб кунед:", null, listOf("I am in London", "I am flying to London", "I want to go to London", "I live in London"), "I am flying to London", 1, "Ман ба Лондон парвоз мекунам = I am flying to London"),
        Exercise("et_e1_4", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Airport" to "Фурудгоҳ", "Ticket" to "Билет", "Flight" to "Парвоз", "Luggage" to "Бор")),
        Exercise("et_e1_5", ExerciseType.TYPE_ANSWER, "«Билет»-ро ба англисӣ нависед:", "Билет = ?", null, "Ticket", null, "Билет — Ticket"),
        Exercise("et_e1_6", ExerciseType.MULTIPLE_CHOICE, "«Gate» чӣ маъно дорад?", "Gate = ...", listOf("Парвоз", "Билет", "Дарвоза", "Бор"), "Дарвоза", 2, "Gate — Дарвоза"),
        Exercise("et_e1_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Парвози ман дар соати даҳ»", null, null, "My flight is at ten", null, "My flight is at + вақт", words = listOf("ten", "at", "is", "flight", "My")),
        Exercise("et_e1_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Board-ing", listOf("Luggage", "Boarding", "Check-in", "Passport"), "Boarding", 1, "Boarding — Нишастан"),
    ),
)

// ── Lesson 2: Дар ҳавопаймо (On the Plane) ──────

private val etM1L2 = Lesson(
    id = "et_m1_l2", moduleId = "et_m1",
    title = "Дар ҳавопаймо", description = "Дар дохили ҳавопаймо",
    emoji = "\uD83D\uDEEB", orderIndex = 1,
    dialogue = Dialogue(
        "Дар ҳавопаймо",
        listOf(
            DialogueLine("Firuz", "Excuse me, where is my seat?", "Бубахшед, ҷои ман куҷост?"),
            DialogueLine("Staff", "Your seat is 14A, by the window.", "Ҷои шумо 14A, дар паҳлӯи тиреза."),
            DialogueLine("Firuz", "Can I have some water, please?", "Лутфан, метавонам об гирам?"),
            DialogueLine("Staff", "Of course. And please fasten your seatbelt.", "Албатта. Ва лутфан камарбандро бандед."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w2_1", "Seat", "Ҷой", "Seat", "This is my seat", "Ин ҷои ман аст", "et_m1_l2"),
        WordItem("et_w2_2", "Window", "Тиреза", "Win-dow", "I want a window seat", "Ман ҷои тиреза мехоҳам", "et_m1_l2"),
        WordItem("et_w2_3", "Aisle", "Роҳ", "Aisle", "I prefer an aisle seat", "Ман ҷои роҳро афзал медонам", "et_m1_l2"),
        WordItem("et_w2_4", "Seatbelt", "Камарбанд", "Seat-belt", "Fasten your seatbelt", "Камарбандро бандед", "et_m1_l2"),
        WordItem("et_w2_5", "Water", "Об", "Wa-ter", "Can I have water?", "Метавонам об гирам?", "et_m1_l2"),
        WordItem("et_w2_6", "Food", "Хӯрок", "Food", "Is there food on the plane?", "Дар ҳавопаймо хӯрок ҳаст?", "et_m1_l2"),
        WordItem("et_w2_7", "Blanket", "Кӯрпа", "Blan-ket", "Can I have a blanket?", "Метавонам кӯрпа гирам?", "et_m1_l2"),
        WordItem("et_w2_8", "Landing", "Фуромадан", "Land-ing", "We are landing soon", "Мо ба зудӣ фуромад мекунем", "et_m1_l2"),
    ),
    grammarTip = GrammarTip(
        "Can I have...? / Excuse me, where is my seat?",
        "Барои хоҳиш кардан аз «Can I have...?» ва барои пурсидани ҷой аз «Excuse me, where is...?» истифода баред.",
        listOf("Can I have some water?", "Excuse me, where is my seat?", "Please fasten your seatbelt."),
    ),
    exercises = listOf(
        Exercise("et_e2_1", ExerciseType.MULTIPLE_CHOICE, "«Seat» чӣ маъно дорад?", "Seat = ...", listOf("Тиреза", "Роҳ", "Ҷой", "Камарбанд"), "Ҷой", 2, "Seat — Ҷой"),
        Exercise("et_e2_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Can I have some _____, please?", listOf("seat", "blanket", "water", "landing"), "water", 2, "Can I have some water — Метавонам об гирам"),
        Exercise("et_e2_3", ExerciseType.TYPE_ANSWER, "«Камарбанд»-ро ба англисӣ нависед:", "Камарбанд = ?", null, "Seatbelt", null, "Камарбанд — Seatbelt"),
        Exercise("et_e2_4", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Мо ба зудӣ фуромад мекунем»-ро интихоб кунед:", null, listOf("We are taking off", "We are landing soon", "We are flying now", "We are boarding"), "We are landing soon", 1, "Мо ба зудӣ фуромад мекунем = We are landing soon"),
        Exercise("et_e2_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Seat" to "Ҷой", "Window" to "Тиреза", "Water" to "Об", "Blanket" to "Кӯрпа")),
        Exercise("et_e2_6", ExerciseType.MULTIPLE_CHOICE, "«Landing» чӣ маъно дорад?", "Landing = ...", listOf("Парвоз", "Нишастан", "Фуромадан", "Хӯрок"), "Фуромадан", 2, "Landing — Фуромадан"),
        Exercise("et_e2_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Excuse me, where is my seat?\nStaff: _____", null, listOf("Fasten your seatbelt.", "Your seat is 14A, by the window.", "We are landing soon."), "Your seat is 14A, by the window.", 1, "Ҷавоб дар бораи ҷой"),
        Exercise("et_e2_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Камарбандро бандед»", null, null, "Fasten your seatbelt", null, "Fasten your + чиз", words = listOf("seatbelt", "your", "Fasten")),
    ),
)

// ── Lesson 3: Гумрук (Customs & Immigration) ────

private val etM1L3 = Lesson(
    id = "et_m1_l3", moduleId = "et_m1",
    title = "Гумрук", description = "Гузаштан аз гумрук",
    emoji = "\uD83D\uDEC3", orderIndex = 2,
    dialogue = Dialogue(
        "Дар гумрук",
        listOf(
            DialogueLine("Officer", "What is the purpose of your visit?", "Мақсади ташрифи шумо чист?"),
            DialogueLine("Firuz", "I am here for tourism.", "Ман барои сайёҳӣ омадаам."),
            DialogueLine("Officer", "How long will you stay?", "Чанд вақт мемонед?"),
            DialogueLine("Firuz", "I will stay for seven days.", "Ман ҳафт рӯз мемонам."),
            DialogueLine("Officer", "Do you have anything to declare?", "Чизе барои эълон доред?"),
            DialogueLine("Firuz", "No, nothing to declare.", "Не, ҳеҷ чиз нест."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w3_1", "Customs", "Гумрук", "Cus-toms", "Go through customs", "Аз гумрук гузаред", "et_m1_l3"),
        WordItem("et_w3_2", "Declare", "Эълон кардан", "De-clare", "Nothing to declare", "Ҳеҷ чиз барои эълон нест", "et_m1_l3"),
        WordItem("et_w3_3", "Visit", "Ташриф", "Vis-it", "I am here for a visit", "Ман барои ташриф омадаам", "et_m1_l3"),
        WordItem("et_w3_4", "Stay", "Мондан", "Stay", "I will stay for a week", "Ман як ҳафта мемонам", "et_m1_l3"),
        WordItem("et_w3_5", "Purpose", "Мақсад", "Pur-pose", "What is the purpose?", "Мақсад чист?", "et_m1_l3"),
        WordItem("et_w3_6", "Tourist", "Сайёҳ", "Tour-ist", "I am a tourist", "Ман сайёҳ ҳастам", "et_m1_l3"),
        WordItem("et_w3_7", "Visa", "Виза", "Vi-sa", "I have a visa", "Ман виза дорам", "et_m1_l3"),
        WordItem("et_w3_8", "Form", "Варақа", "Form", "Please fill in this form", "Лутфан ин варақаро пур кунед", "et_m1_l3"),
    ),
    grammarTip = GrammarTip(
        "I am here for tourism / I will stay for... days",
        "Барои мақсади сафар аз «I am here for...» ва барои муддат аз «I will stay for... days» истифода баред.",
        listOf("I am here for tourism.", "I will stay for seven days.", "Nothing to declare."),
    ),
    exercises = listOf(
        Exercise("et_e3_1", ExerciseType.MULTIPLE_CHOICE, "«Customs» чӣ маъно дорад?", "Customs = ...", listOf("Фурудгоҳ", "Гумрук", "Сафорат", "Полис"), "Гумрук", 1, "Customs — Гумрук"),
        Exercise("et_e3_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I am here for _____.", listOf("visit", "tourism", "stay", "form"), "tourism", 1, "I am here for tourism — Ман барои сайёҳӣ"),
        Exercise("et_e3_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман ҳафт рӯз мемонам»-ро интихоб кунед:", null, listOf("I stay seven days", "I will stay for seven days", "I stayed seven days", "I need seven days"), "I will stay for seven days", 1, "Ман ҳафт рӯз мемонам = I will stay for seven days"),
        Exercise("et_e3_4", ExerciseType.TYPE_ANSWER, "«Сайёҳ»-ро ба англисӣ нависед:", "Сайёҳ = ?", null, "Tourist", null, "Сайёҳ — Tourist"),
        Exercise("et_e3_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Customs" to "Гумрук", "Tourist" to "Сайёҳ", "Visa" to "Виза", "Form" to "Варақа")),
        Exercise("et_e3_6", ExerciseType.MULTIPLE_CHOICE, "«Purpose» чӣ маъно дорад?", "Purpose = ...", listOf("Мондан", "Мақсад", "Ташриф", "Эълон"), "Мақсад", 1, "Purpose — Мақсад"),
        Exercise("et_e3_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман барои сайёҳӣ омадаам»", null, null, "I am here for tourism", null, "I am here for + мақсад", words = listOf("tourism", "for", "here", "am", "I")),
        Exercise("et_e3_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nOfficer: What is the purpose of your visit?\nFiruz: _____", null, listOf("I have a visa.", "I am here for tourism.", "Nothing to declare."), "I am here for tourism.", 1, "Мақсади сафар"),
    ),
)

// ── Lesson 4: Такси ва нақлиёт (Taxi & Transport) ─

private val etM1L4 = Lesson(
    id = "et_m1_l4", moduleId = "et_m1",
    title = "Такси ва нақлиёт", description = "Нақлиёт аз фурудгоҳ",
    emoji = "\uD83D\uDE95", orderIndex = 3,
    dialogue = Dialogue(
        "Дар таксӣ",
        listOf(
            DialogueLine("Firuz", "Taxi! Take me to the Grand Hotel, please.", "Таксӣ! Лутфан маро ба Гранд Отел баред."),
            DialogueLine("Driver", "Sure. Do you have the address?", "Хуб. Суроға доред?"),
            DialogueLine("Firuz", "Yes, here it is. How far is it?", "Ҳа, ин аст. Чӣ қадар дур аст?"),
            DialogueLine("Driver", "About twenty minutes.", "Тақрибан бист дақиқа."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w4_1", "Taxi", "Таксӣ", "Tax-i", "I need a taxi", "Ба ман таксӣ лозим", "et_m1_l4"),
        WordItem("et_w4_2", "Driver", "Ронанда", "Driv-er", "The driver is friendly", "Ронанда дӯстона аст", "et_m1_l4"),
        WordItem("et_w4_3", "Hotel", "Меҳмонхона", "Ho-tel", "Take me to the hotel", "Маро ба меҳмонхона баред", "et_m1_l4"),
        WordItem("et_w4_4", "Address", "Суроға", "Ad-dress", "What is the address?", "Суроға чист?", "et_m1_l4"),
        WordItem("et_w4_5", "Far", "Дур", "Far", "Is it far?", "Дур аст?", "et_m1_l4"),
        WordItem("et_w4_6", "Near", "Наздик", "Near", "The hotel is near", "Меҳмонхона наздик аст", "et_m1_l4"),
        WordItem("et_w4_7", "How much", "Чанд", "How much", "How much is the fare?", "Кирояш чанд аст?", "et_m1_l4"),
        WordItem("et_w4_8", "Map", "Харита", "Map", "I have a map", "Ман харита дорам", "et_m1_l4"),
    ),
    grammarTip = GrammarTip(
        "Take me to... / How far is it?",
        "Барои хоҳиш ба ронанда аз «Take me to...» ва барои масофа аз «How far is it?» истифода баред.",
        listOf("Take me to the hotel, please.", "How far is it?", "How much is the fare?"),
    ),
    exercises = listOf(
        Exercise("et_e4_1", ExerciseType.MULTIPLE_CHOICE, "«Hotel» чӣ маъно дорад?", "Hotel = ...", listOf("Мағоза", "Ресторан", "Меҳмонхона", "Фурудгоҳ"), "Меҳмонхона", 2, "Hotel — Меҳмонхона"),
        Exercise("et_e4_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Take me to the _____, please.", listOf("map", "hotel", "taxi", "driver"), "hotel", 1, "Take me to the hotel — Маро ба меҳмонхона баред"),
        Exercise("et_e4_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Чӣ қадар дур аст?»-ро интихоб кунед:", null, listOf("Where is it?", "How much is it?", "How far is it?", "Is it near?"), "How far is it?", 2, "Чӣ қадар дур аст? = How far is it?"),
        Exercise("et_e4_4", ExerciseType.TYPE_ANSWER, "«Харита»-ро ба англисӣ нависед:", "Харита = ?", null, "Map", null, "Харита — Map"),
        Exercise("et_e4_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Taxi" to "Таксӣ", "Driver" to "Ронанда", "Far" to "Дур", "Near" to "Наздик")),
        Exercise("et_e4_6", ExerciseType.MULTIPLE_CHOICE, "«Address» чӣ маъно дорад?", "Address = ...", listOf("Нақша", "Суроға", "Ронанда", "Кироя"), "Суроға", 1, "Address — Суроға"),
        Exercise("et_e4_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Driv-er", listOf("Hotel", "Driver", "Address", "Taxi"), "Driver", 1, "Driver — Ронанда"),
        Exercise("et_e4_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ба ман таксӣ лозим»", null, null, "I need a taxi", null, "I need a + чиз", words = listOf("taxi", "a", "need", "I")),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 2 · ДАР ШАҲР  (In the City)
// ═══════════════════════════════════════════════════

// ── Lesson 5: Меҳмонхона (Hotel) ────────────────

private val etM2L5 = Lesson(
    id = "et_m2_l5", moduleId = "et_m2",
    title = "Меҳмонхона", description = "Дар меҳмонхона",
    emoji = "\uD83C\uDFE8", orderIndex = 0,
    dialogue = Dialogue(
        "Дар меҳмонхона",
        listOf(
            DialogueLine("Firuz", "Hello, I have a reservation.", "Салом, ман захира дорам."),
            DialogueLine("Reception", "What is your name?", "Номи шумо чист?"),
            DialogueLine("Firuz", "Firuz. What time is checkout?", "Фирӯз. Вақти баромадан кай аст?"),
            DialogueLine("Reception", "Checkout is at eleven. Here is your key. Room 305, third floor.", "Баромадан дар соати ёздаҳ. Ин калиди шумо. Хонаи 305, ошёнаи сеюм."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w5_1", "Room", "Хона", "Room", "I booked a room", "Ман хона захира кардам", "et_m2_l5"),
        WordItem("et_w5_2", "Book", "Захира кардан", "Book", "I want to book a room", "Ман хона захира кардан мехоҳам", "et_m2_l5"),
        WordItem("et_w5_3", "Key", "Калид", "Key", "Here is your key", "Ин калиди шумо", "et_m2_l5"),
        WordItem("et_w5_4", "Floor", "Ошёна", "Floor", "My room is on the third floor", "Хонаи ман дар ошёнаи сеюм", "et_m2_l5"),
        WordItem("et_w5_5", "Breakfast", "Наҳорӣ", "Break-fast", "Is breakfast included?", "Наҳорӣ дохил аст?", "et_m2_l5"),
        WordItem("et_w5_6", "Checkout", "Баромадан", "Check-out", "Checkout is at eleven", "Баромадан дар соати ёздаҳ", "et_m2_l5"),
        WordItem("et_w5_7", "Reception", "Пазироӣ", "Re-cep-tion", "Go to reception", "Ба пазироӣ равед", "et_m2_l5"),
        WordItem("et_w5_8", "WiFi", "Вайфай", "Wi-Fi", "What is the WiFi password?", "Рамзи вайфай чист?", "et_m2_l5"),
    ),
    grammarTip = GrammarTip(
        "I have a reservation / What time is checkout?",
        "Дар меҳмонхона барои захира аз «I have a reservation» ва барои вақт аз «What time is checkout?» истифода баред.",
        listOf("I have a reservation.", "What time is checkout?", "Is breakfast included?"),
    ),
    exercises = listOf(
        Exercise("et_e5_1", ExerciseType.MULTIPLE_CHOICE, "«Book» (дар меҳмонхона) чӣ маъно дорад?", "Book = ...", listOf("Хондан", "Захира кардан", "Навиштан", "Калид"), "Захира кардан", 1, "Book — Захира кардан"),
        Exercise("et_e5_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I have a _____.", listOf("key", "reservation", "floor", "WiFi"), "reservation", 1, "I have a reservation — Ман захира дорам"),
        Exercise("et_e5_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Наҳорӣ дохил аст?»-ро интихоб кунед:", null, listOf("Where is breakfast?", "Is breakfast included?", "I want breakfast", "Breakfast is at seven"), "Is breakfast included?", 1, "Наҳорӣ дохил аст? = Is breakfast included?"),
        Exercise("et_e5_4", ExerciseType.TYPE_ANSWER, "«Калид»-ро ба англисӣ нависед:", "Калид = ?", null, "Key", null, "Калид — Key"),
        Exercise("et_e5_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Room" to "Хона", "Key" to "Калид", "Floor" to "Ошёна", "Checkout" to "Баромадан")),
        Exercise("et_e5_6", ExerciseType.MULTIPLE_CHOICE, "«Reception» чӣ маъно дорад?", "Reception = ...", listOf("Хона", "Ошёна", "Пазироӣ", "Наҳорӣ"), "Пазироӣ", 2, "Reception — Пазироӣ"),
        Exercise("et_e5_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Вақти баромадан кай аст?»", null, null, "What time is checkout?", null, "What time is + чиз?", words = listOf("checkout?", "is", "time", "What")),
        Exercise("et_e5_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: I have a reservation.\nReception: _____", null, listOf("Here is your key.", "What is your name?", "Checkout is at eleven."), "What is your name?", 1, "Аввал номро мепурсанд"),
    ),
)

// ── Lesson 6: Ресторан (Restaurant) ─────────────

private val etM2L6 = Lesson(
    id = "et_m2_l6", moduleId = "et_m2",
    title = "Ресторан", description = "Фармоиши хӯрок",
    emoji = "\uD83C\uDF7D\uFE0F", orderIndex = 1,
    dialogue = Dialogue(
        "Дар ресторан",
        listOf(
            DialogueLine("Waiter", "Welcome! Here is the menu.", "Хуш омадед! Ин меню."),
            DialogueLine("Firuz", "I would like to order chicken, please.", "Лутфан ман мурғ фармоиш медиҳам."),
            DialogueLine("Waiter", "Would you like a drink?", "Нӯшокӣ мехоҳед?"),
            DialogueLine("Firuz", "Just water, thank you. Can I have the bill?", "Танҳо об, ташаккур. Метавонам ҳисобро гирам?"),
        ),
    ),
    newWords = listOf(
        WordItem("et_w6_1", "Menu", "Меню", "Men-u", "Can I see the menu?", "Метавонам менюро бинам?", "et_m2_l6"),
        WordItem("et_w6_2", "Order", "Фармоиш", "Or-der", "I would like to order", "Ман фармоиш додан мехоҳам", "et_m2_l6"),
        WordItem("et_w6_3", "Waiter", "Пешхизмат", "Wait-er", "Excuse me, waiter!", "Бубахшед, пешхизмат!", "et_m2_l6"),
        WordItem("et_w6_4", "Bill", "Ҳисоб", "Bill", "Can I have the bill?", "Метавонам ҳисобро гирам?", "et_m2_l6"),
        WordItem("et_w6_5", "Delicious", "Болаззат", "De-li-cious", "The food is delicious", "Хӯрок болаззат аст", "et_m2_l6"),
        WordItem("et_w6_6", "Spicy", "Тунд", "Spi-cy", "Not too spicy, please", "Лутфан на он қадар тунд", "et_m2_l6"),
        WordItem("et_w6_7", "Drink", "Нӯшокӣ", "Drink", "What would you like to drink?", "Чӣ нӯшидан мехоҳед?", "et_m2_l6"),
        WordItem("et_w6_8", "Dessert", "Ширинӣ", "Des-sert", "I want dessert", "Ман ширинӣ мехоҳам", "et_m2_l6"),
    ),
    grammarTip = GrammarTip(
        "I would like to order... / Can I have the bill?",
        "Барои фармоиш аз «I would like to order...» ва барои ҳисоб аз «Can I have the bill?» истифода баред.",
        listOf("I would like to order chicken.", "Can I have the bill?", "Not too spicy, please."),
    ),
    exercises = listOf(
        Exercise("et_e6_1", ExerciseType.MULTIPLE_CHOICE, "«Bill» чӣ маъно дорад?", "Bill = ...", listOf("Меню", "Фармоиш", "Ҳисоб", "Нӯшокӣ"), "Ҳисоб", 2, "Bill — Ҳисоб"),
        Exercise("et_e6_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I would like to _____ chicken.", listOf("eat", "order", "cook", "drink"), "order", 1, "I would like to order — Ман фармоиш медиҳам"),
        Exercise("et_e6_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Хӯрок болаззат аст»-ро интихоб кунед:", null, listOf("The food is spicy", "The food is cheap", "The food is delicious", "The food is hot"), "The food is delicious", 2, "Хӯрок болаззат аст = The food is delicious"),
        Exercise("et_e6_4", ExerciseType.TYPE_ANSWER, "«Пешхизмат»-ро ба англисӣ нависед:", "Пешхизмат = ?", null, "Waiter", null, "Пешхизмат — Waiter"),
        Exercise("et_e6_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Menu" to "Меню", "Bill" to "Ҳисоб", "Drink" to "Нӯшокӣ", "Dessert" to "Ширинӣ")),
        Exercise("et_e6_6", ExerciseType.MULTIPLE_CHOICE, "«Spicy» чӣ маъно дорад?", "Spicy = ...", listOf("Болаззат", "Тунд", "Ширин", "Хунук"), "Тунд", 1, "Spicy — Тунд"),
        Exercise("et_e6_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A De-li-cious", listOf("Dessert", "Delicious", "Drink", "Dinner"), "Delicious", 1, "Delicious — Болаззат"),
        Exercise("et_e6_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Метавонам ҳисобро гирам?»", null, null, "Can I have the bill?", null, "Can I have the + чиз?", words = listOf("bill?", "the", "have", "I", "Can")),
    ),
)

// ── Lesson 7: Роҳ пурсидан (Asking Directions) ──

private val etM2L7 = Lesson(
    id = "et_m2_l7", moduleId = "et_m2",
    title = "Роҳ пурсидан", description = "Пурсидани роҳ дар шаҳр",
    emoji = "\uD83E\uDDED", orderIndex = 2,
    dialogue = Dialogue(
        "Дар кӯча",
        listOf(
            DialogueLine("Firuz", "Excuse me, how do I get to the station?", "Бубахшед, ба истгоҳ чӣ тавр мерасам?"),
            DialogueLine("Woman", "Go straight, then turn left.", "Рост равед, баъд ба чап гардед."),
            DialogueLine("Firuz", "Is it far from here?", "Аз ин ҷо дур аст?"),
            DialogueLine("Woman", "No, about five minutes on foot.", "Не, тақрибан панҷ дақиқа пиёда."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w7_1", "Where", "Куҷо", "Where", "Where is the station?", "Истгоҳ куҷост?", "et_m2_l7"),
        WordItem("et_w7_2", "Left", "Чап", "Left", "Turn left", "Ба чап гардед", "et_m2_l7"),
        WordItem("et_w7_3", "Right", "Рост", "Right", "Turn right", "Ба рост гардед", "et_m2_l7"),
        WordItem("et_w7_4", "Straight", "Рост", "Straight", "Go straight ahead", "Рост равед", "et_m2_l7"),
        WordItem("et_w7_5", "Turn", "Гаштан", "Turn", "Turn at the corner", "Дар кунҷ гардед", "et_m2_l7"),
        WordItem("et_w7_6", "Street", "Кӯча", "Street", "What street is this?", "Ин кадом кӯча?", "et_m2_l7"),
        WordItem("et_w7_7", "Station", "Истгоҳ", "Sta-tion", "The station is near", "Истгоҳ наздик аст", "et_m2_l7"),
        WordItem("et_w7_8", "Walk", "Пиёда рафтан", "Walk", "I walk to school", "Ман пиёда ба мактаб меравам", "et_m2_l7"),
    ),
    grammarTip = GrammarTip(
        "How do I get to...? / Turn left/right",
        "Барои пурсидани роҳ аз «How do I get to...?» ва барои роҳнамоӣ аз «Turn left/right» истифода баред.",
        listOf("How do I get to the station?", "Turn left at the corner.", "Go straight ahead."),
    ),
    exercises = listOf(
        Exercise("et_e7_1", ExerciseType.MULTIPLE_CHOICE, "«Station» чӣ маъно дорад?", "Station = ...", listOf("Кӯча", "Истгоҳ", "Харита", "Мағоза"), "Истгоҳ", 1, "Station — Истгоҳ"),
        Exercise("et_e7_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Go straight, then turn _____.", listOf("walk", "street", "left", "far"), "left", 2, "Turn left — Ба чап гардед"),
        Exercise("et_e7_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ба истгоҳ чӣ тавр мерасам?»-ро интихоб кунед:", null, listOf("Where is the station?", "How do I get to the station?", "Is the station near?", "Take me to the station"), "How do I get to the station?", 1, "Ба истгоҳ чӣ тавр мерасам? = How do I get to the station?"),
        Exercise("et_e7_4", ExerciseType.TYPE_ANSWER, "«Кӯча»-ро ба англисӣ нависед:", "Кӯча = ?", null, "Street", null, "Кӯча — Street"),
        Exercise("et_e7_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Left" to "Чап", "Right" to "Рост", "Turn" to "Гаштан", "Walk" to "Пиёда рафтан")),
        Exercise("et_e7_6", ExerciseType.MULTIPLE_CHOICE, "«Straight» чӣ маъно дорад?", "Straight = ...", listOf("Чап", "Гаштан", "Рост (пеш)", "Кӯча"), "Рост (пеш)", 2, "Straight — Рост (пеш равед)"),
        Exercise("et_e7_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ба чап гардед»", null, null, "Turn left", null, "Turn + самт", words = listOf("left", "Turn")),
        Exercise("et_e7_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: How do I get to the station?\nWoman: _____", null, listOf("It is five minutes.", "Go straight, then turn left.", "The station is closed."), "Go straight, then turn left.", 1, "Роҳнамоӣ додан"),
    ),
)

// ── Lesson 8: Харид кардан (Shopping) ───────────

private val etM2L8 = Lesson(
    id = "et_m2_l8", moduleId = "et_m2",
    title = "Харид кардан", description = "Харид дар мағоза",
    emoji = "\uD83D\uDECD\uFE0F", orderIndex = 3,
    dialogue = Dialogue(
        "Дар мағоза",
        listOf(
            DialogueLine("Firuz", "How much is this?", "Ин чанд пул аст?"),
            DialogueLine("Seller", "It is twenty dollars.", "Бист доллар аст."),
            DialogueLine("Firuz", "Do you have a smaller size?", "Андозаи хурдтар доред?"),
            DialogueLine("Seller", "Yes, here you are. Cash or card?", "Ҳа, ин аст. Нақд ё корт?"),
        ),
    ),
    newWords = listOf(
        WordItem("et_w8_1", "Shop", "Мағоза", "Shop", "I like this shop", "Ман ин мағозаро дӯст медорам", "et_m2_l8"),
        WordItem("et_w8_2", "Buy", "Харидан", "Buy", "I want to buy this", "Ман инро харидан мехоҳам", "et_m2_l8"),
        WordItem("et_w8_3", "Price", "Нарх", "Price", "What is the price?", "Нарх чанд аст?", "et_m2_l8"),
        WordItem("et_w8_4", "Cheap", "Арзон", "Cheap", "This is cheap", "Ин арзон аст", "et_m2_l8"),
        WordItem("et_w8_5", "Expensive", "Қимат", "Ex-pen-sive", "That is too expensive", "Он хеле қимат аст", "et_m2_l8"),
        WordItem("et_w8_6", "Size", "Андоза", "Size", "What size do you need?", "Кадом андоза лозим?", "et_m2_l8"),
        WordItem("et_w8_7", "Color", "Ранг", "Col-or", "Do you have another color?", "Ранги дигар доред?", "et_m2_l8"),
        WordItem("et_w8_8", "Cash", "Нақд", "Cash", "I will pay cash", "Ман нақд мепардозам", "et_m2_l8"),
    ),
    grammarTip = GrammarTip(
        "How much is this? / Do you have a smaller size?",
        "Барои нарх аз «How much is this?» ва барои андоза аз «Do you have a smaller size?» истифода баред.",
        listOf("How much is this?", "Do you have a smaller size?", "I will pay cash."),
    ),
    exercises = listOf(
        Exercise("et_e8_1", ExerciseType.MULTIPLE_CHOICE, "«Expensive» чӣ маъно дорад?", "Expensive = ...", listOf("Арзон", "Қимат", "Нарх", "Нақд"), "Қимат", 1, "Expensive — Қимат"),
        Exercise("et_e8_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "How _____ is this?", listOf("far", "long", "much", "big"), "much", 2, "How much is this — Ин чанд пул аст"),
        Exercise("et_e8_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Андозаи хурдтар доред?»-ро интихоб кунед:", null, listOf("Do you have a bigger size?", "What size is this?", "Do you have a smaller size?", "I need a different size"), "Do you have a smaller size?", 2, "Андозаи хурдтар доред? = Do you have a smaller size?"),
        Exercise("et_e8_4", ExerciseType.TYPE_ANSWER, "«Мағоза»-ро ба англисӣ нависед:", "Мағоза = ?", null, "Shop", null, "Мағоза — Shop"),
        Exercise("et_e8_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Buy" to "Харидан", "Price" to "Нарх", "Cheap" to "Арзон", "Expensive" to "Қимат")),
        Exercise("et_e8_6", ExerciseType.MULTIPLE_CHOICE, "«Cash» чӣ маъно дорад?", "Cash = ...", listOf("Корт", "Ҳисоб", "Нақд", "Пул"), "Нақд", 2, "Cash — Нақд"),
        Exercise("et_e8_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ex-pen-sive", listOf("Expensive", "Cheap", "Price", "Size"), "Expensive", 0, "Expensive — Қимат"),
        Exercise("et_e8_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман нақд мепардозам»", null, null, "I will pay cash", null, "I will pay + тарз", words = listOf("cash", "pay", "will", "I")),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 3 · ҲОЛАТҲОИ МУҲИМ  (Important Situations)
// ═══════════════════════════════════════════════════

// ── Lesson 9: Саломатӣ (Health) ─────────────────

private val etM3L9 = Lesson(
    id = "et_m3_l9", moduleId = "et_m3",
    title = "Саломатӣ", description = "Дар беморхона ва дорухона",
    emoji = "\uD83C\uDFE5", orderIndex = 0,
    dialogue = Dialogue(
        "Дар дорухона",
        listOf(
            DialogueLine("Firuz", "I need a doctor. I feel sick.", "Ба ман духтур лозим. Ман бемор ҳастам."),
            DialogueLine("Pharmacist", "What is wrong? Where is the pain?", "Чӣ шуд? Дард дар куҷост?"),
            DialogueLine("Firuz", "I have a headache and a fever.", "Сарам дард мекунад ва тоб дорам."),
            DialogueLine("Pharmacist", "Take this medicine. If it gets worse, go to the hospital.", "Ин доруро гиред. Агар бадтар шавад, ба беморхона равед."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w9_1", "Doctor", "Духтур", "Doc-tor", "I need a doctor", "Ба ман духтур лозим", "et_m3_l9"),
        WordItem("et_w9_2", "Hospital", "Беморхона", "Hos-pi-tal", "Where is the hospital?", "Беморхона куҷост?", "et_m3_l9"),
        WordItem("et_w9_3", "Medicine", "Дору", "Med-i-cine", "Take the medicine", "Доруро гиред", "et_m3_l9"),
        WordItem("et_w9_4", "Sick", "Бемор", "Sick", "I feel sick", "Ман бемор ҳастам", "et_m3_l9"),
        WordItem("et_w9_5", "Pain", "Дард", "Pain", "I have a pain here", "Ин ҷо дард мекунад", "et_m3_l9"),
        WordItem("et_w9_6", "Pharmacy", "Дорухона", "Phar-ma-cy", "Is there a pharmacy near?", "Дорухона дар наздикӣ ҳаст?", "et_m3_l9"),
        WordItem("et_w9_7", "Help", "Кӯмак", "Help", "Please help me", "Лутфан ба ман кӯмак кунед", "et_m3_l9"),
        WordItem("et_w9_8", "Emergency", "Таъҷилӣ", "E-mer-gen-cy", "This is an emergency", "Ин ҳолати таъҷилӣ аст", "et_m3_l9"),
    ),
    grammarTip = GrammarTip(
        "I need a doctor / I feel sick",
        "Барои ёрии тиббӣ аз «I need a doctor» ва барои ҳолат аз «I feel sick» истифода баред.",
        listOf("I need a doctor.", "I feel sick.", "Where is the nearest pharmacy?"),
    ),
    exercises = listOf(
        Exercise("et_e9_1", ExerciseType.MULTIPLE_CHOICE, "«Medicine» чӣ маъно дорад?", "Medicine = ...", listOf("Духтур", "Дору", "Дард", "Беморхона"), "Дору", 1, "Medicine — Дору"),
        Exercise("et_e9_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I feel _____.", listOf("pain", "help", "sick", "doctor"), "sick", 2, "I feel sick — Ман бемор ҳастам"),
        Exercise("et_e9_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ба ман духтур лозим»-ро интихоб кунед:", null, listOf("I am a doctor", "I need a doctor", "Where is the doctor?", "Call the doctor"), "I need a doctor", 1, "Ба ман духтур лозим = I need a doctor"),
        Exercise("et_e9_4", ExerciseType.TYPE_ANSWER, "«Дорухона»-ро ба англисӣ нависед:", "Дорухона = ?", null, "Pharmacy", null, "Дорухона — Pharmacy"),
        Exercise("et_e9_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Doctor" to "Духтур", "Hospital" to "Беморхона", "Medicine" to "Дору", "Pain" to "Дард")),
        Exercise("et_e9_6", ExerciseType.MULTIPLE_CHOICE, "«Emergency» чӣ маъно дорад?", "Emergency = ...", listOf("Кӯмак", "Таъҷилӣ", "Бемор", "Дорухона"), "Таъҷилӣ", 1, "Emergency — Таъҷилӣ"),
        Exercise("et_e9_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Лутфан ба ман кӯмак кунед»", null, null, "Please help me", null, "Please help + кӣ", words = listOf("me", "help", "Please")),
        Exercise("et_e9_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nPharmacist: What is wrong?\nFiruz: _____", null, listOf("Where is the hospital?", "I have a headache.", "Take this medicine."), "I have a headache.", 1, "Дарди худро гуфтан"),
    ),
)

// ── Lesson 10: Пул ва бонк (Money & Bank) ───────

private val etM3L10 = Lesson(
    id = "et_m3_l10", moduleId = "et_m3",
    title = "Пул ва бонк", description = "Иваз кардани пул",
    emoji = "\uD83D\uDCB0", orderIndex = 1,
    dialogue = Dialogue(
        "Дар бонк",
        listOf(
            DialogueLine("Firuz", "Where can I exchange money?", "Дар куҷо пул иваз карда метавонам?"),
            DialogueLine("Clerk", "Right here. How much do you want to exchange?", "Дар ҳамин ҷо. Чанд иваз кардан мехоҳед?"),
            DialogueLine("Firuz", "Two hundred dollars. Do you accept cards?", "Дусад доллар. Корт қабул мекунед?"),
            DialogueLine("Clerk", "Yes, we do. Here is your receipt.", "Ҳа, мекунем. Ин квитансияи шумо."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w10_1", "Money", "Пул", "Mon-ey", "I need money", "Ба ман пул лозим", "et_m3_l10"),
        WordItem("et_w10_2", "Exchange", "Иваз кардан", "Ex-change", "I want to exchange money", "Ман пул иваз кардан мехоҳам", "et_m3_l10"),
        WordItem("et_w10_3", "ATM", "Бонкомат", "A-T-M", "Where is the ATM?", "Бонкомат куҷост?", "et_m3_l10"),
        WordItem("et_w10_4", "Dollar", "Доллар", "Dol-lar", "How many dollars?", "Чанд доллар?", "et_m3_l10"),
        WordItem("et_w10_5", "Card", "Корт", "Card", "Do you accept cards?", "Корт қабул мекунед?", "et_m3_l10"),
        WordItem("et_w10_6", "Pay", "Пардохтан", "Pay", "I will pay now", "Ман ҳозир мепардозам", "et_m3_l10"),
        WordItem("et_w10_7", "Change", "Хурд", "Change", "Keep the change", "Хурдро нигоҳ доред", "et_m3_l10"),
        WordItem("et_w10_8", "Receipt", "Квитансия", "Re-ceipt", "Can I have a receipt?", "Метавонам квитансия гирам?", "et_m3_l10"),
    ),
    grammarTip = GrammarTip(
        "Where can I exchange money? / Do you accept cards?",
        "Барои иваз кардан аз «Where can I exchange money?» ва барои корт аз «Do you accept cards?» истифода баред.",
        listOf("Where can I exchange money?", "Do you accept cards?", "Can I have a receipt?"),
    ),
    exercises = listOf(
        Exercise("et_e10_1", ExerciseType.MULTIPLE_CHOICE, "«Exchange» чӣ маъно дорад?", "Exchange = ...", listOf("Пардохтан", "Иваз кардан", "Харидан", "Гирифтан"), "Иваз кардан", 1, "Exchange — Иваз кардан"),
        Exercise("et_e10_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Do you accept _____?", listOf("money", "dollars", "cards", "change"), "cards", 2, "Do you accept cards — Корт қабул мекунед"),
        Exercise("et_e10_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Бонкомат куҷост?»-ро интихоб кунед:", null, listOf("I need an ATM", "Where is the ATM?", "Is the ATM working?", "Find the ATM"), "Where is the ATM?", 1, "Бонкомат куҷост? = Where is the ATM?"),
        Exercise("et_e10_4", ExerciseType.TYPE_ANSWER, "«Квитансия»-ро ба англисӣ нависед:", "Квитансия = ?", null, "Receipt", null, "Квитансия — Receipt"),
        Exercise("et_e10_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Money" to "Пул", "Exchange" to "Иваз кардан", "Pay" to "Пардохтан", "Card" to "Корт")),
        Exercise("et_e10_6", ExerciseType.MULTIPLE_CHOICE, "«Receipt» чӣ маъно дорад?", "Receipt = ...", listOf("Хурд", "Доллар", "Квитансия", "Бонкомат"), "Квитансия", 2, "Receipt — Квитансия"),
        Exercise("et_e10_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман пул иваз кардан мехоҳам»", null, null, "I want to exchange money", null, "I want to exchange + чиз", words = listOf("money", "exchange", "to", "want", "I")),
        Exercise("et_e10_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ex-change", listOf("Money", "Exchange", "Receipt", "Dollar"), "Exchange", 1, "Exchange — Иваз кардан"),
    ),
)

// ── Lesson 11: Ёрии таъҷилӣ (Emergency) ────────

private val etM3L11 = Lesson(
    id = "et_m3_l11", moduleId = "et_m3",
    title = "Ёрии таъҷилӣ", description = "Ҳолатҳои фавқулодда",
    emoji = "\uD83D\uDEA8", orderIndex = 2,
    dialogue = Dialogue(
        "Дар хиёбон",
        listOf(
            DialogueLine("Firuz", "Help! I lost my passport!", "Кӯмак! Ман паспортамро гум кардам!"),
            DialogueLine("Police", "Calm down. Where did you lose it?", "Ором бошед. Дар куҷо гум кардед?"),
            DialogueLine("Firuz", "I think near the station. Please call the embassy.", "Фикр мекунам дар наздикии истгоҳ. Лутфан ба сафорат занг занед."),
            DialogueLine("Police", "Don't worry, we will help you.", "Ташвиш накашед, мо кӯмак мекунем."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w11_1", "Police", "Полис", "Po-lice", "Call the police", "Ба полис занг занед", "et_m3_l11"),
        WordItem("et_w11_2", "Lost", "Гумшуда", "Lost", "I am lost", "Ман гум шудам", "et_m3_l11"),
        WordItem("et_w11_3", "Stolen", "Дуздидашуда", "Sto-len", "My bag was stolen", "Сумкаамро дуздиданд", "et_m3_l11"),
        WordItem("et_w11_4", "Embassy", "Сафорат", "Em-bas-sy", "Where is the embassy?", "Сафорат куҷост?", "et_m3_l11"),
        WordItem("et_w11_5", "Call", "Занг задан", "Call", "Call for help", "Барои кӯмак занг занед", "et_m3_l11"),
        WordItem("et_w11_6", "Dangerous", "Хатарнок", "Dan-ger-ous", "This area is dangerous", "Ин ноҳия хатарнок аст", "et_m3_l11"),
        WordItem("et_w11_7", "Safe", "Бехатар", "Safe", "Is it safe here?", "Ин ҷо бехатар аст?", "et_m3_l11"),
        WordItem("et_w11_8", "Accident", "Ҳодиса", "Ac-ci-dent", "There was an accident", "Ҳодиса рӯй дод", "et_m3_l11"),
    ),
    grammarTip = GrammarTip(
        "I lost my passport / Please call the police",
        "Дар ҳолати фавқулодда аз «I lost my...» ва «Please call the...» истифода баред.",
        listOf("I lost my passport.", "Please call the police.", "My bag was stolen."),
    ),
    exercises = listOf(
        Exercise("et_e11_1", ExerciseType.MULTIPLE_CHOICE, "«Stolen» чӣ маъно дорад?", "Stolen = ...", listOf("Гумшуда", "Дуздидашуда", "Хатарнок", "Бехатар"), "Дуздидашуда", 1, "Stolen — Дуздидашуда"),
        Exercise("et_e11_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I _____ my passport.", listOf("called", "found", "lost", "stole"), "lost", 2, "I lost my passport — Ман паспортамро гум кардам"),
        Exercise("et_e11_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Лутфан ба полис занг занед»-ро интихоб кунед:", null, listOf("I am calling the police", "The police is here", "Please call the police", "Where is the police?"), "Please call the police", 2, "Лутфан ба полис занг занед = Please call the police"),
        Exercise("et_e11_4", ExerciseType.TYPE_ANSWER, "«Сафорат»-ро ба англисӣ нависед:", "Сафорат = ?", null, "Embassy", null, "Сафорат — Embassy"),
        Exercise("et_e11_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Police" to "Полис", "Lost" to "Гумшуда", "Safe" to "Бехатар", "Accident" to "Ҳодиса")),
        Exercise("et_e11_6", ExerciseType.MULTIPLE_CHOICE, "«Dangerous» чӣ маъно дорад?", "Dangerous = ...", listOf("Бехатар", "Наздик", "Хатарнок", "Дур"), "Хатарнок", 2, "Dangerous — Хатарнок"),
        Exercise("et_e11_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Help! I lost my passport!\nPolice: _____", null, listOf("Is it safe here?", "There was an accident.", "Calm down. Where did you lose it?"), "Calm down. Where did you lose it?", 2, "Полис ором мекунад"),
        Exercise("et_e11_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ба полис занг занед»", null, null, "Call the police", null, "Call the + кӣ", words = listOf("police", "the", "Call")),
    ),
)

// ── Lesson 12: Хотираҳо (Memories & Goodbye) ───

private val etM3L12 = Lesson(
    id = "et_m3_l12", moduleId = "et_m3",
    title = "Хотираҳо", description = "Хотираҳо ва хайрбод",
    emoji = "\uD83D\uDCF8", orderIndex = 3,
    dialogue = Dialogue(
        "Рӯзи охирин",
        listOf(
            DialogueLine("Firuz", "This trip was amazing!", "Ин сафар аҷоиб буд!"),
            DialogueLine("Anna", "I really enjoyed it. Let me take a photo!", "Ман воқеан лаззат бурдам. Биёед акс гирем!"),
            DialogueLine("Firuz", "I will miss this place. I want to come back.", "Ман ин ҷоро ёд мекунам. Мехоҳам бозгардам."),
            DialogueLine("Anna", "Goodbye, Firuz! Have a safe flight!", "Хайр, Фирӯз! Парвози бехатар!"),
        ),
    ),
    newWords = listOf(
        WordItem("et_w12_1", "Photo", "Акс", "Pho-to", "Can I take a photo?", "Метавонам акс гирам?", "et_m3_l12"),
        WordItem("et_w12_2", "Beautiful", "Зебо", "Beau-ti-ful", "This place is beautiful", "Ин ҷо зебо аст", "et_m3_l12"),
        WordItem("et_w12_3", "Amazing", "Аҷоиб", "A-ma-zing", "The trip was amazing", "Сафар аҷоиб буд", "et_m3_l12"),
        WordItem("et_w12_4", "Remember", "Дар ёд доштан", "Re-mem-ber", "I will remember this", "Ман инро дар ёд медорам", "et_m3_l12"),
        WordItem("et_w12_5", "Enjoy", "Лаззат бурдан", "En-joy", "I really enjoyed the trip", "Ман аз сафар лаззат бурдам", "et_m3_l12"),
        WordItem("et_w12_6", "Return", "Бозгаштан", "Re-turn", "I want to return", "Ман мехоҳам бозгардам", "et_m3_l12"),
        WordItem("et_w12_7", "Miss", "Ёд кардан", "Miss", "I will miss you", "Ман шуморо ёд мекунам", "et_m3_l12"),
        WordItem("et_w12_8", "Goodbye", "Хайр", "Good-bye", "Goodbye, my friend!", "Хайр, дӯсти ман!", "et_m3_l12"),
    ),
    grammarTip = GrammarTip(
        "I really enjoyed... / I want to come back",
        "Барои хотираҳо аз «I really enjoyed...» ва барои бозгаштан аз «I want to come back» истифода баред.",
        listOf("I really enjoyed the trip.", "I want to come back.", "This place is beautiful."),
    ),
    exercises = listOf(
        Exercise("et_e12_1", ExerciseType.MULTIPLE_CHOICE, "«Amazing» чӣ маъно дорад?", "Amazing = ...", listOf("Зебо", "Аҷоиб", "Хуб", "Калон"), "Аҷоиб", 1, "Amazing — Аҷоиб"),
        Exercise("et_e12_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I really _____ the trip.", listOf("missed", "enjoyed", "remembered", "returned"), "enjoyed", 1, "I really enjoyed — Ман лаззат бурдам"),
        Exercise("et_e12_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман мехоҳам бозгардам»-ро интихоб кунед:", null, listOf("I will remember", "I miss this place", "I want to come back", "I enjoyed the trip"), "I want to come back", 2, "Ман мехоҳам бозгардам = I want to come back"),
        Exercise("et_e12_4", ExerciseType.TYPE_ANSWER, "«Зебо»-ро ба англисӣ нависед:", "Зебо = ?", null, "Beautiful", null, "Зебо — Beautiful"),
        Exercise("et_e12_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Photo" to "Акс", "Enjoy" to "Лаззат бурдан", "Return" to "Бозгаштан", "Goodbye" to "Хайр")),
        Exercise("et_e12_6", ExerciseType.MULTIPLE_CHOICE, "«Miss» (дар маънои ёд кардан) чӣ маъно дорад?", "Miss = ...", listOf("Дар ёд доштан", "Ёд кардан", "Бозгаштан", "Лаззат бурдан"), "Ёд кардан", 1, "Miss — Ёд кардан"),
        Exercise("et_e12_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ин ҷо зебо аст»", null, null, "This place is beautiful", null, "This place is + сифат", words = listOf("beautiful", "is", "place", "This")),
        Exercise("et_e12_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: I will miss this place.\nAnna: _____", null, listOf("Have a safe flight!", "Take a photo!", "The trip was amazing."), "Have a safe flight!", 0, "Хайрбодӣ гуфтан"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE & COURSE DEFINITIONS
// ═══════════════════════════════════════════════════

private val etModule1 = Module(
    id = "et_m1", courseId = "travel_english",
    title = "Дар фурудгоҳ ва ҳавопаймо",
    description = "Фурудгоҳ, ҳавопаймо, гумрук ва нақлиёт",
    emoji = "\u2708\uFE0F", orderIndex = 0,
    lessons = listOf(etM1L1, etM1L2, etM1L3, etM1L4),
)

private val etModule2 = Module(
    id = "et_m2", courseId = "travel_english",
    title = "Дар шаҳр",
    description = "Меҳмонхона, ресторан, роҳ ва харид",
    emoji = "\uD83C\uDFD9\uFE0F", orderIndex = 1,
    lessons = listOf(etM2L5, etM2L6, etM2L7, etM2L8),
)

private val etModule3 = Module(
    id = "et_m3", courseId = "travel_english",
    title = "Ҳолатҳои муҳим",
    description = "Саломатӣ, пул, ёрии таъҷилӣ ва хотираҳо",
    emoji = "\uD83D\uDEA8", orderIndex = 2,
    lessons = listOf(etM3L9, etM3L10, etM3L11, etM3L12),
)

val travelEnglishCourse = Course(
    id = "travel_english",
    title = "Англисӣ барои сафар",
    description = "Забони англисӣ барои сафар: фурудгоҳ, шаҳр, ҳолатҳои муҳим",
    emoji = "\u2708\uFE0F",
    goalType = "travel",
    language = "english",
    modules = listOf(
        etModule1, etModule2, etModule3,
        etModule4, etModule5, etModule6, etModule7, etModule8, etModule9, etModule10,
    ),
)
