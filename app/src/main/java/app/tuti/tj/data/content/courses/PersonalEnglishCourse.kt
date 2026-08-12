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
//  MODULE 1 · ҲАЁТИ ҲАРРӮЗА  (Everyday Life)
// ═══════════════════════════════════════════════════

// ── Lesson 1: Оила (Family) ──────────────────────

private val epM1L1 = Lesson(
    id = "ep_m1_l1", moduleId = "ep_m1",
    title = "Оила", description = "Дар бораи оила",
    emoji = "\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC67\u200D\uD83D\uDC66", orderIndex = 0,
    dialogue = Dialogue(
        "Оилаи ман",
        listOf(
            DialogueLine("Anna", "Do you have a big family?", "Оилаи калон доред?"),
            DialogueLine("Firuz", "Yes! I have two brothers and one sister.", "Ҳа! Ман ду бародар ва як хоҳар дорам."),
            DialogueLine("Anna", "That's nice! Where do they live?", "Хуб! Онҳо дар куҷо зиндагӣ мекунанд?"),
            DialogueLine("Firuz", "They live in Tajikistan.", "Онҳо дар Тоҷикистон зиндагӣ мекунанд."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w1_1", "Family", "Оила", "Fam-i-ly", "I love my family", "Ман оиламро дӯст медорам", "ep_m1_l1"),
        WordItem("ep_w1_2", "Mother", "Модар", "Moth-er", "My mother is kind", "Модарам меҳрубон аст", "ep_m1_l1"),
        WordItem("ep_w1_3", "Father", "Падар", "Fa-ther", "My father works hard", "Падарам сахт кор мекунад", "ep_m1_l1"),
        WordItem("ep_w1_4", "Brother", "Бародар", "Broth-er", "I have one brother", "Ман як бародар дорам", "ep_m1_l1"),
        WordItem("ep_w1_5", "Sister", "Хоҳар", "Sis-ter", "My sister is funny", "Хоҳарам хандаовар аст", "ep_m1_l1"),
        WordItem("ep_w1_6", "Son", "Писар", "Son", "He is my son", "Вай писари ман аст", "ep_m1_l1"),
        WordItem("ep_w1_7", "Daughter", "Духтар", "Daugh-ter", "My daughter is three years old", "Духтарам се сола аст", "ep_m1_l1"),
        WordItem("ep_w1_8", "Children", "Кӯдакон", "Chil-dren", "The children are playing", "Кӯдакон бозӣ мекунанд", "ep_m1_l1"),
    ),
    grammarTip = GrammarTip(
        "I have... / My mother's name is...",
        "Барои доштан аз «I have...» ва барои номи касе аз «My + нисбат + name is...» истифода баред.",
        listOf("I have two brothers.", "My mother's name is Maryam.", "They live in Tajikistan."),
    ),
    exercises = listOf(
        Exercise("ep_e1_1", ExerciseType.MULTIPLE_CHOICE, "«Family» чӣ маъно дорад?", "Family = ...", listOf("Дӯст", "Оила", "Хоҳар", "Кӯдакон"), "Оила", 1, "Family — Оила"),
        Exercise("ep_e1_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I have two _____ and one sister.", listOf("mothers", "fathers", "brothers", "daughters"), "brothers", 2, "I have two brothers — Ман ду бародар дорам"),
        Exercise("ep_e1_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Онҳо дар Тоҷикистон зиндагӣ мекунанд»-ро интихоб кунед:", null, listOf("They work in Tajikistan", "They live in Tajikistan", "They study in Tajikistan", "They are from Tajikistan"), "They live in Tajikistan", 1, "Онҳо дар Тоҷикистон зиндагӣ мекунанд = They live in Tajikistan"),
        Exercise("ep_e1_4", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Mother" to "Модар", "Father" to "Падар", "Brother" to "Бародар", "Sister" to "Хоҳар")),
        Exercise("ep_e1_5", ExerciseType.TYPE_ANSWER, "«Кӯдакон»-ро ба англисӣ нависед:", "Кӯдакон = ?", null, "Children", null, "Кӯдакон — Children"),
        Exercise("ep_e1_6", ExerciseType.MULTIPLE_CHOICE, "«Daughter» чӣ маъно дорад?", "Daughter = ...", listOf("Писар", "Духтар", "Хоҳар", "Оила"), "Духтар", 1, "Daughter — Духтар"),
        Exercise("ep_e1_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман як бародар дорам»", null, null, "I have one brother", null, "I have + шумора + чиз", words = listOf("brother", "one", "have", "I")),
        Exercise("ep_e1_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Do you have a big family?\nFiruz: _____", null, listOf("No, I live alone.", "Yes! I have two brothers and one sister.", "My family is far away."), "Yes! I have two brothers and one sister.", 1, "Ҷавоб дар бораи оила"),
    ),
)

// ── Lesson 2: Хона (Home) ────────────────────────

private val epM1L2 = Lesson(
    id = "ep_m1_l2", moduleId = "ep_m1",
    title = "Хона", description = "Тасвири хона",
    emoji = "\uD83C\uDFE0", orderIndex = 1,
    dialogue = Dialogue(
        "Дар бораи хона",
        listOf(
            DialogueLine("Anna", "Where do you live?", "Дар куҷо зиндагӣ мекунед?"),
            DialogueLine("Firuz", "I live in a big house. It has a garden.", "Ман дар хонаи калон зиндагӣ мекунам. Боғ дорад."),
            DialogueLine("Anna", "How many rooms does it have?", "Чанд хона дорад?"),
            DialogueLine("Firuz", "It has five rooms and a big kitchen.", "Панҷ хона ва ошхонаи калон дорад."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w2_1", "House", "Хона", "House", "This is my house", "Ин хонаи ман аст", "ep_m1_l2"),
        WordItem("ep_w2_2", "Room", "Утоқ", "Room", "How many rooms?", "Чанд утоқ?", "ep_m1_l2"),
        WordItem("ep_w2_3", "Kitchen", "Ошхона", "Kitch-en", "The kitchen is big", "Ошхона калон аст", "ep_m1_l2"),
        WordItem("ep_w2_4", "Door", "Дар", "Door", "Close the door", "Дарро пӯшед", "ep_m1_l2"),
        WordItem("ep_w2_5", "Window", "Тиреза", "Win-dow", "Open the window", "Тирезаро кушоед", "ep_m1_l2"),
        WordItem("ep_w2_6", "Garden", "Боғ", "Gar-den", "I have a garden", "Ман боғ дорам", "ep_m1_l2"),
        WordItem("ep_w2_7", "Big", "Калон", "Big", "The house is big", "Хона калон аст", "ep_m1_l2"),
        WordItem("ep_w2_8", "Small", "Хурд", "Small", "My room is small", "Утоқи ман хурд аст", "ep_m1_l2"),
    ),
    grammarTip = GrammarTip(
        "I live in... / My house has...",
        "Барои ҷои зиндагӣ аз «I live in...» ва барои тасвири хона аз «My house has...» истифода баред.",
        listOf("I live in a big house.", "My house has five rooms.", "It has a beautiful garden."),
    ),
    exercises = listOf(
        Exercise("ep_e2_1", ExerciseType.MULTIPLE_CHOICE, "«Garden» чӣ маъно дорад?", "Garden = ...", listOf("Тиреза", "Дар", "Боғ", "Ошхона"), "Боғ", 2, "Garden — Боғ"),
        Exercise("ep_e2_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I live in a _____ house.", listOf("small", "garden", "big", "room"), "big", 2, "I live in a big house — Ман дар хонаи калон зиндагӣ мекунам"),
        Exercise("ep_e2_3", ExerciseType.TYPE_ANSWER, "«Тиреза»-ро ба англисӣ нависед:", "Тиреза = ?", null, "Window", null, "Тиреза — Window"),
        Exercise("ep_e2_4", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Хона панҷ утоқ дорад»-ро интихоб кунед:", null, listOf("The house has a garden", "The house is big", "My house has five rooms", "I have five rooms"), "My house has five rooms", 2, "Хона панҷ утоқ дорад = My house has five rooms"),
        Exercise("ep_e2_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("House" to "Хона", "Kitchen" to "Ошхона", "Door" to "Дар", "Garden" to "Боғ")),
        Exercise("ep_e2_6", ExerciseType.MULTIPLE_CHOICE, "«Small» чӣ маъно дорад?", "Small = ...", listOf("Калон", "Зебо", "Хурд", "Кӯҳна"), "Хурд", 2, "Small — Хурд"),
        Exercise("ep_e2_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Kitch-en", listOf("Window", "Kitchen", "Garden", "Room"), "Kitchen", 1, "Kitchen — Ошхона"),
        Exercise("ep_e2_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман дар хонаи калон зиндагӣ мекунам»", null, null, "I live in a big house", null, "I live in a + сифат + чиз", words = listOf("house", "big", "a", "in", "live", "I")),
    ),
)

// ── Lesson 3: Хӯрок (Food) ──────────────────────

private val epM1L3 = Lesson(
    id = "ep_m1_l3", moduleId = "ep_m1",
    title = "Хӯрок", description = "Дар бораи хӯрок",
    emoji = "\uD83C\uDF5B", orderIndex = 2,
    dialogue = Dialogue(
        "Хӯроки дӯстдошта",
        listOf(
            DialogueLine("Anna", "What is your favorite food?", "Хӯроки дӯстдоштаи шумо чист?"),
            DialogueLine("Firuz", "I love rice and meat. What about you?", "Ман биринҷ ва гӯштро дӯст медорам. Шумо чӣ?"),
            DialogueLine("Anna", "I like fruit. I eat fruit every morning.", "Ман меваро дӯст медорам. Ҳар субҳ мева мехӯрам."),
            DialogueLine("Firuz", "That is healthy! I should eat more fruit.", "Ин фоиданок аст! Ман бояд бештар мева хӯрам."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w3_1", "Food", "Хӯрок", "Food", "I love this food", "Ман ин хӯрокро дӯст медорам", "ep_m1_l3"),
        WordItem("ep_w3_2", "Eat", "Хӯрдан", "Eat", "I eat breakfast", "Ман наҳорӣ мехӯрам", "ep_m1_l3"),
        WordItem("ep_w3_3", "Drink", "Нӯшидан", "Drink", "I drink water", "Ман об менӯшам", "ep_m1_l3"),
        WordItem("ep_w3_4", "Breakfast", "Наҳорӣ", "Break-fast", "Breakfast is ready", "Наҳорӣ тайёр аст", "ep_m1_l3"),
        WordItem("ep_w3_5", "Dinner", "Хӯроки шом", "Din-ner", "We have dinner together", "Мо якҷоя хӯроки шом мехӯрем", "ep_m1_l3"),
        WordItem("ep_w3_6", "Fruit", "Мева", "Fruit", "I like fresh fruit", "Ман мевои тоза дӯст медорам", "ep_m1_l3"),
        WordItem("ep_w3_7", "Meat", "Гӯшт", "Meat", "I eat meat every day", "Ман ҳар рӯз гӯшт мехӯрам", "ep_m1_l3"),
        WordItem("ep_w3_8", "Rice", "Биринҷ", "Rice", "Rice is my favorite", "Биринҷ дӯстдоштаи ман аст", "ep_m1_l3"),
    ),
    grammarTip = GrammarTip(
        "I like to eat... / My favorite food is...",
        "Барои хӯроки дӯстдошта аз «My favorite food is...» ва барои хӯрдан аз «I like to eat...» истифода баред.",
        listOf("My favorite food is rice.", "I like to eat fruit.", "I eat breakfast every morning."),
    ),
    exercises = listOf(
        Exercise("ep_e3_1", ExerciseType.MULTIPLE_CHOICE, "«Fruit» чӣ маъно дорад?", "Fruit = ...", listOf("Гӯшт", "Биринҷ", "Мева", "Хӯрок"), "Мева", 2, "Fruit — Мева"),
        Exercise("ep_e3_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "My favorite food is _____.", listOf("eat", "drink", "rice", "fruit"), "rice", 2, "My favorite food is rice — Хӯроки дӯстдоштаи ман биринҷ аст"),
        Exercise("ep_e3_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман биринҷ ва гӯштро дӯст медорам»-ро интихоб кунед:", null, listOf("I eat rice and meat", "I cook rice and meat", "I love rice and meat", "I buy rice and meat"), "I love rice and meat", 2, "Ман биринҷ ва гӯштро дӯст медорам = I love rice and meat"),
        Exercise("ep_e3_4", ExerciseType.TYPE_ANSWER, "«Наҳорӣ»-ро ба англисӣ нависед:", "Наҳорӣ = ?", null, "Breakfast", null, "Наҳорӣ — Breakfast"),
        Exercise("ep_e3_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Eat" to "Хӯрдан", "Drink" to "Нӯшидан", "Fruit" to "Мева", "Meat" to "Гӯшт")),
        Exercise("ep_e3_6", ExerciseType.MULTIPLE_CHOICE, "«Dinner» чӣ маъно дорад?", "Dinner = ...", listOf("Наҳорӣ", "Хӯроки нисфирӯзӣ", "Хӯроки шом", "Хӯрок"), "Хӯроки шом", 2, "Dinner — Хӯроки шом"),
        Exercise("ep_e3_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Break-fast", listOf("Dinner", "Breakfast", "Fruit", "Rice"), "Breakfast", 1, "Breakfast — Наҳорӣ"),
        Exercise("ep_e3_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман ҳар субҳ мева мехӯрам»", null, null, "I eat fruit every morning", null, "I eat + чиз + every morning", words = listOf("morning", "every", "fruit", "eat", "I")),
    ),
)

// ── Lesson 4: Рӯзи ман (My Day) ─────────────────

private val epM1L4 = Lesson(
    id = "ep_m1_l4", moduleId = "ep_m1",
    title = "Рӯзи ман", description = "Рӯзмарраи ман",
    emoji = "\uD83C\uDF05", orderIndex = 3,
    dialogue = Dialogue(
        "Рӯзмарра",
        listOf(
            DialogueLine("Anna", "What time do you usually wake up?", "Одатан дар чӣ вақт бедор мешавед?"),
            DialogueLine("Firuz", "I always wake up early, at six.", "Ман ҳамеша барвақт, дар соати шаш бедор мешавам."),
            DialogueLine("Anna", "And what time do you sleep?", "Дар чӣ вақт мехобед?"),
            DialogueLine("Firuz", "I sometimes sleep late, at midnight.", "Ман баъзан дер, дар нимашаб мехобам."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w4_1", "Wake up", "Бедор шудан", "Wake up", "I wake up at seven", "Ман дар соати ҳафт бедор мешавам", "ep_m1_l4"),
        WordItem("ep_w4_2", "Sleep", "Хобидан", "Sleep", "I sleep eight hours", "Ман ҳашт соат мехобам", "ep_m1_l4"),
        WordItem("ep_w4_3", "Morning", "Субҳ", "Morn-ing", "Good morning!", "Субҳ бахайр!", "ep_m1_l4"),
        WordItem("ep_w4_4", "Evening", "Бегоҳ", "Eve-ning", "Good evening!", "Бегоҳ бахайр!", "ep_m1_l4"),
        WordItem("ep_w4_5", "Early", "Барвақт", "Ear-ly", "I wake up early", "Ман барвақт бедор мешавам", "ep_m1_l4"),
        WordItem("ep_w4_6", "Late", "Дер", "Late", "I sleep late", "Ман дер мехобам", "ep_m1_l4"),
        WordItem("ep_w4_7", "Always", "Ҳамеша", "Al-ways", "I always eat breakfast", "Ман ҳамеша наҳорӣ мехӯрам", "ep_m1_l4"),
        WordItem("ep_w4_8", "Sometimes", "Баъзан", "Some-times", "I sometimes cook", "Ман баъзан мепазам", "ep_m1_l4"),
    ),
    grammarTip = GrammarTip(
        "I wake up at... / I usually...",
        "Барои вақт аз «I wake up at...» ва барои одат аз «I usually...» истифода баред.",
        listOf("I wake up at six.", "I usually eat breakfast at seven.", "I sometimes sleep late."),
    ),
    exercises = listOf(
        Exercise("ep_e4_1", ExerciseType.MULTIPLE_CHOICE, "«Early» чӣ маъно дорад?", "Early = ...", listOf("Дер", "Барвақт", "Ҳамеша", "Баъзан"), "Барвақт", 1, "Early — Барвақт"),
        Exercise("ep_e4_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I _____ wake up at six.", listOf("sometimes", "always", "early", "late"), "always", 1, "I always wake up — Ман ҳамеша бедор мешавам"),
        Exercise("ep_e4_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман баъзан дер мехобам»-ро интихоб кунед:", null, listOf("I always sleep late", "I never sleep late", "I sometimes sleep late", "I usually sleep early"), "I sometimes sleep late", 2, "Ман баъзан дер мехобам = I sometimes sleep late"),
        Exercise("ep_e4_4", ExerciseType.TYPE_ANSWER, "«Бедор шудан»-ро ба англисӣ нависед:", "Бедор шудан = ?", null, "Wake up", null, "Бедор шудан — Wake up"),
        Exercise("ep_e4_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Morning" to "Субҳ", "Evening" to "Бегоҳ", "Always" to "Ҳамеша", "Sometimes" to "Баъзан")),
        Exercise("ep_e4_6", ExerciseType.MULTIPLE_CHOICE, "«Sleep» чӣ маъно дорад?", "Sleep = ...", listOf("Бедор шудан", "Рафтан", "Хобидан", "Истодан"), "Хобидан", 2, "Sleep — Хобидан"),
        Exercise("ep_e4_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман дар соати шаш бедор мешавам»", null, null, "I wake up at six", null, "I wake up at + вақт", words = listOf("six", "at", "up", "wake", "I")),
        Exercise("ep_e4_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: What time do you usually wake up?\nFiruz: _____", null, listOf("I sleep late.", "I always wake up early, at six.", "Good morning!"), "I always wake up early, at six.", 1, "Вақти бедор шудан"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 2 · ШАВҚУ ҲАВАС  (Hobbies & Interests)
// ═══════════════════════════════════════════════════

// ── Lesson 5: Хобби (Hobbies) ───────────────────

private val epM2L5 = Lesson(
    id = "ep_m2_l5", moduleId = "ep_m2",
    title = "Хобби", description = "Машғулиятҳои дӯстдошта",
    emoji = "\uD83C\uDFA8", orderIndex = 0,
    dialogue = Dialogue(
        "Машғулияти озод",
        listOf(
            DialogueLine("Anna", "What is your hobby?", "Машғулияти шумо чист?"),
            DialogueLine("Firuz", "I enjoy reading books and cooking.", "Ман китоб хондан ва пухтанро дӯст медорам."),
            DialogueLine("Anna", "That's great! I love dancing.", "Хуб! Ман рақс карданро дӯст медорам."),
            DialogueLine("Firuz", "I would like to learn dancing too.", "Ман ҳам рақс карданро ёд гирифтан мехоҳам."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w5_1", "Hobby", "Машғулият", "Hob-by", "What is your hobby?", "Машғулияти шумо чист?", "ep_m2_l5"),
        WordItem("ep_w5_2", "Read", "Хондан", "Read", "I read every night", "Ман ҳар шаб мехонам", "ep_m2_l5"),
        WordItem("ep_w5_3", "Watch", "Тамошо кардан", "Watch", "I watch movies", "Ман филм тамошо мекунам", "ep_m2_l5"),
        WordItem("ep_w5_4", "Play", "Бозӣ кардан", "Play", "I play games", "Ман бозӣ мекунам", "ep_m2_l5"),
        WordItem("ep_w5_5", "Draw", "Расм кашидан", "Draw", "I draw pictures", "Ман расм мекашам", "ep_m2_l5"),
        WordItem("ep_w5_6", "Cook", "Пухтан", "Cook", "I love to cook", "Ман пухтанро дӯст медорам", "ep_m2_l5"),
        WordItem("ep_w5_7", "Dance", "Рақс кардан", "Dance", "I dance every day", "Ман ҳар рӯз рақс мекунам", "ep_m2_l5"),
        WordItem("ep_w5_8", "Sing", "Сурудхонӣ кардан", "Sing", "I sing in the shower", "Ман дар ванна суруд мехонам", "ep_m2_l5"),
    ),
    grammarTip = GrammarTip(
        "My hobby is... / I enjoy...",
        "Барои машғулият аз «My hobby is...» ва барои завқ доштан аз «I enjoy...» истифода баред.",
        listOf("My hobby is reading.", "I enjoy cooking.", "I would like to learn dancing."),
    ),
    exercises = listOf(
        Exercise("ep_e5_1", ExerciseType.MULTIPLE_CHOICE, "«Dance» чӣ маъно дорад?", "Dance = ...", listOf("Суруд хондан", "Рақс кардан", "Расм кашидан", "Бозӣ кардан"), "Рақс кардан", 1, "Dance — Рақс кардан"),
        Exercise("ep_e5_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "My hobby is _____.", listOf("dance", "cook", "reading", "sing"), "reading", 2, "My hobby is reading — Машғулияти ман китоб хондан аст"),
        Exercise("ep_e5_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман рақс карданро дӯст медорам»-ро интихоб кунед:", null, listOf("I want to dance", "I enjoy dancing", "I love dancing", "I can dance"), "I love dancing", 2, "Ман рақс карданро дӯст медорам = I love dancing"),
        Exercise("ep_e5_4", ExerciseType.TYPE_ANSWER, "«Расм кашидан»-ро ба англисӣ нависед:", "Расм кашидан = ?", null, "Draw", null, "Расм кашидан — Draw"),
        Exercise("ep_e5_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Read" to "Хондан", "Cook" to "Пухтан", "Dance" to "Рақс кардан", "Sing" to "Сурудхонӣ кардан")),
        Exercise("ep_e5_6", ExerciseType.MULTIPLE_CHOICE, "«Watch» чӣ маъно дорад?", "Watch = ...", listOf("Тамошо кардан", "Хондан", "Бозӣ кардан", "Пухтан"), "Тамошо кардан", 0, "Watch — Тамошо кардан"),
        Exercise("ep_e5_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Hob-by", listOf("Hobby", "Draw", "Sing", "Play"), "Hobby", 0, "Hobby — Машғулият"),
        Exercise("ep_e5_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман ҳар шаб китоб мехонам»", null, null, "I read every night", null, "I read every + вақт", words = listOf("night", "every", "read", "I")),
    ),
)

// ── Lesson 6: Мусиқӣ ва филм (Music & Movies) ───

private val epM2L6 = Lesson(
    id = "ep_m2_l6", moduleId = "ep_m2",
    title = "Мусиқӣ ва филм", description = "Дар бораи мусиқӣ ва филм",
    emoji = "\uD83C\uDFB5", orderIndex = 1,
    dialogue = Dialogue(
        "Дар бораи филм",
        listOf(
            DialogueLine("Anna", "Have you seen the new movie?", "Филми навро дидед?"),
            DialogueLine("Firuz", "Not yet. Is it interesting?", "Ҳанӯз не. Ҷолиб аст?"),
            DialogueLine("Anna", "Very! It is funny and exciting.", "Хеле! Хандаовар ва ҷолиб аст."),
            DialogueLine("Firuz", "I like funny movies. I will watch it tonight.", "Ман филмҳои хандаоварро дӯст медорам. Имшаб тамошо мекунам."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w6_1", "Song", "Суруд", "Song", "I like this song", "Ман ин суруди дӯст медорам", "ep_m2_l6"),
        WordItem("ep_w6_2", "Singer", "Хонанда", "Sing-er", "She is a famous singer", "Вай хонандаи маъмул аст", "ep_m2_l6"),
        WordItem("ep_w6_3", "Movie", "Филм", "Mov-ie", "Let's watch a movie", "Биёед филм тамошо кунем", "ep_m2_l6"),
        WordItem("ep_w6_4", "Actor", "Актёр", "Ac-tor", "He is a great actor", "Вай актёри хуб аст", "ep_m2_l6"),
        WordItem("ep_w6_5", "Funny", "Хандаовар", "Fun-ny", "The movie is funny", "Филм хандаовар аст", "ep_m2_l6"),
        WordItem("ep_w6_6", "Scary", "Тарсонак", "Scar-y", "The movie is scary", "Филм тарсонак аст", "ep_m2_l6"),
        WordItem("ep_w6_7", "Interesting", "Ҷолиб", "In-ter-est-ing", "This book is interesting", "Ин китоб ҷолиб аст", "ep_m2_l6"),
        WordItem("ep_w6_8", "Boring", "Зеҳнзада", "Bor-ing", "The lesson is boring", "Дарс зеҳнзада аст", "ep_m2_l6"),
    ),
    grammarTip = GrammarTip(
        "I like listening to... / Have you seen...?",
        "Барои пурсидан аз «Have you seen...?» ва барои мусиқӣ аз «I like listening to...» истифода баред.",
        listOf("Have you seen the new movie?", "I like listening to music.", "I enjoy funny movies."),
    ),
    exercises = listOf(
        Exercise("ep_e6_1", ExerciseType.MULTIPLE_CHOICE, "«Funny» чӣ маъно дорад?", "Funny = ...", listOf("Тарсонак", "Зеҳнзада", "Ҷолиб", "Хандаовар"), "Хандаовар", 3, "Funny — Хандаовар"),
        Exercise("ep_e6_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Have you _____ the new movie?", listOf("watched", "seen", "liked", "heard"), "seen", 1, "Have you seen — Дидед?"),
        Exercise("ep_e6_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Филм ҷолиб аст»-ро интихоб кунед:", null, listOf("The movie is funny", "The movie is boring", "The movie is interesting", "The movie is scary"), "The movie is interesting", 2, "Филм ҷолиб аст = The movie is interesting"),
        Exercise("ep_e6_4", ExerciseType.TYPE_ANSWER, "«Зеҳнзада»-ро ба англисӣ нависед:", "Зеҳнзада = ?", null, "Boring", null, "Зеҳнзада — Boring"),
        Exercise("ep_e6_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Song" to "Суруд", "Singer" to "Хонанда", "Movie" to "Филм", "Actor" to "Актёр")),
        Exercise("ep_e6_6", ExerciseType.MULTIPLE_CHOICE, "«Scary» чӣ маъно дорад?", "Scary = ...", listOf("Ҷолиб", "Хандаовар", "Тарсонак", "Зеҳнзада"), "Тарсонак", 2, "Scary — Тарсонак"),
        Exercise("ep_e6_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман филмҳои хандаоварро дӯст медорам»", null, null, "I like funny movies", null, "I like + сифат + чизҳо", words = listOf("movies", "funny", "like", "I")),
        Exercise("ep_e6_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Is the movie interesting?\nFiruz: _____", null, listOf("I don't know.", "Very! It is funny and exciting.", "I like scary movies."), "Very! It is funny and exciting.", 1, "Тавсиф додани филм"),
    ),
)

// ── Lesson 7: Варзиш (Sports) ───────────────────

private val epM2L7 = Lesson(
    id = "ep_m2_l7", moduleId = "ep_m2",
    title = "Варзиш", description = "Варзиш ва саломатӣ",
    emoji = "\u26BD", orderIndex = 2,
    dialogue = Dialogue(
        "Варзиш ва саломатӣ",
        listOf(
            DialogueLine("Firuz", "Do you play any sports?", "Ягон варзиш мекунед?"),
            DialogueLine("Anna", "Yes, I swim three times a week.", "Ҳа, ман ҳафтае се маротиба шино мекунам."),
            DialogueLine("Firuz", "My favorite sport is football.", "Варзиши дӯстдоштаи ман футбол аст."),
            DialogueLine("Anna", "Sport is good for health!", "Варзиш барои саломатӣ хуб аст!"),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w7_1", "Football", "Футбол", "Foot-ball", "I play football", "Ман футбол бозӣ мекунам", "ep_m2_l7"),
        WordItem("ep_w7_2", "Basketball", "Баскетбол", "Bas-ket-ball", "Basketball is popular", "Баскетбол маъмул аст", "ep_m2_l7"),
        WordItem("ep_w7_3", "Tennis", "Теннис", "Ten-nis", "I like tennis", "Ман теннисро дӯст медорам", "ep_m2_l7"),
        WordItem("ep_w7_4", "Swimming", "Шиноварӣ", "Swim-ming", "Swimming is healthy", "Шиноварӣ фоиданок аст", "ep_m2_l7"),
        WordItem("ep_w7_5", "Exercise", "Варзиш", "Ex-er-cise", "I exercise every day", "Ман ҳар рӯз варзиш мекунам", "ep_m2_l7"),
        WordItem("ep_w7_6", "Health", "Саломатӣ", "Health", "Health is important", "Саломатӣ муҳим аст", "ep_m2_l7"),
        WordItem("ep_w7_7", "Strong", "Қавӣ", "Strong", "He is very strong", "Вай хеле қавӣ аст", "ep_m2_l7"),
        WordItem("ep_w7_8", "Fast", "Тез", "Fast", "He runs fast", "Вай тез медавад", "ep_m2_l7"),
    ),
    grammarTip = GrammarTip(
        "I play... / My favorite sport is...",
        "Барои варзиш аз «I play...» ва барои варзиши дӯстдошта аз «My favorite sport is...» истифода баред.",
        listOf("I play football.", "My favorite sport is swimming.", "Exercise is good for health."),
    ),
    exercises = listOf(
        Exercise("ep_e7_1", ExerciseType.MULTIPLE_CHOICE, "«Swimming» чӣ маъно дорад?", "Swimming = ...", listOf("Футбол", "Теннис", "Шиноварӣ", "Баскетбол"), "Шиноварӣ", 2, "Swimming — Шиноварӣ"),
        Exercise("ep_e7_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "My favorite sport is _____.", listOf("fast", "strong", "health", "football"), "football", 3, "My favorite sport is football — Варзиши дӯстдоштаи ман футбол аст"),
        Exercise("ep_e7_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Варзиш барои саломатӣ хуб аст»-ро интихоб кунед:", null, listOf("Health is good for sport", "I like sport and health", "Sport is good for health", "Exercise makes you fast"), "Sport is good for health", 2, "Варзиш барои саломатӣ хуб аст = Sport is good for health"),
        Exercise("ep_e7_4", ExerciseType.TYPE_ANSWER, "«Қавӣ»-ро ба англисӣ нависед:", "Қавӣ = ?", null, "Strong", null, "Қавӣ — Strong"),
        Exercise("ep_e7_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Football" to "Футбол", "Tennis" to "Теннис", "Health" to "Саломатӣ", "Fast" to "Тез")),
        Exercise("ep_e7_6", ExerciseType.MULTIPLE_CHOICE, "«Exercise» чӣ маъно дорад?", "Exercise = ...", listOf("Тез", "Қавӣ", "Варзиш", "Саломатӣ"), "Варзиш", 2, "Exercise — Варзиш"),
        Exercise("ep_e7_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Bas-ket-ball", listOf("Football", "Tennis", "Basketball", "Swimming"), "Basketball", 2, "Basketball — Баскетбол"),
        Exercise("ep_e7_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман ҳар рӯз варзиш мекунам»", null, null, "I exercise every day", null, "I exercise every + вақт", words = listOf("day", "every", "exercise", "I")),
    ),
)

// ── Lesson 8: Табиат (Nature) ────────────────────

private val epM2L8 = Lesson(
    id = "ep_m2_l8", moduleId = "ep_m2",
    title = "Табиат", description = "Табиат ва муҳити зист",
    emoji = "\uD83C\uDF3F", orderIndex = 3,
    dialogue = Dialogue(
        "Табиат",
        listOf(
            DialogueLine("Anna", "Do you like nature?", "Табиатро дӯст медоред?"),
            DialogueLine("Firuz", "Yes! I love mountains and rivers.", "Ҳа! Ман кӯҳ ва дарёро дӯст медорам."),
            DialogueLine("Anna", "The weather today is beautiful.", "Имрӯз об-ҳаво зебо аст."),
            DialogueLine("Firuz", "Sunny days are my favorite!", "Рӯзҳои офтобӣ дӯстдоштаи ман!"),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w8_1", "Tree", "Дарахт", "Tree", "The tree is tall", "Дарахт баланд аст", "ep_m2_l8"),
        WordItem("ep_w8_2", "Flower", "Гул", "Flow-er", "I love flowers", "Ман гулҳоро дӯст медорам", "ep_m2_l8"),
        WordItem("ep_w8_3", "Mountain", "Кӯҳ", "Moun-tain", "The mountains are high", "Кӯҳҳо баланд ҳастанд", "ep_m2_l8"),
        WordItem("ep_w8_4", "River", "Дарё", "Riv-er", "The river is long", "Дарё дароз аст", "ep_m2_l8"),
        WordItem("ep_w8_5", "Sun", "Офтоб", "Sun", "The sun is bright", "Офтоб равшан аст", "ep_m2_l8"),
        WordItem("ep_w8_6", "Rain", "Борон", "Rain", "I like the rain", "Ман боронро дӯст медорам", "ep_m2_l8"),
        WordItem("ep_w8_7", "Snow", "Барф", "Snow", "I love snow", "Ман барфро дӯст медорам", "ep_m2_l8"),
        WordItem("ep_w8_8", "Beautiful", "Зебо", "Beau-ti-ful", "The view is beautiful", "Манзара зебо аст", "ep_m2_l8"),
    ),
    grammarTip = GrammarTip(
        "The weather is... / I love nature",
        "Барои об-ҳаво аз «The weather is...» ва барои табиат аз «I love nature» истифода баред.",
        listOf("The weather is beautiful today.", "I love mountains and rivers.", "The sun is bright."),
    ),
    exercises = listOf(
        Exercise("ep_e8_1", ExerciseType.MULTIPLE_CHOICE, "«Mountain» чӣ маъно дорад?", "Mountain = ...", listOf("Дарё", "Дарахт", "Кӯҳ", "Гул"), "Кӯҳ", 2, "Mountain — Кӯҳ"),
        Exercise("ep_e8_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "The weather is _____.", listOf("mountain", "river", "beautiful", "flower"), "beautiful", 2, "The weather is beautiful — Об-ҳаво зебо аст"),
        Exercise("ep_e8_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман кӯҳ ва дарёро дӯст медорам»-ро интихоб кунед:", null, listOf("I see mountains and rivers", "I love mountains and rivers", "Mountains and rivers are here", "I climb mountains"), "I love mountains and rivers", 1, "Ман кӯҳ ва дарёро дӯст медорам = I love mountains and rivers"),
        Exercise("ep_e8_4", ExerciseType.TYPE_ANSWER, "«Барф»-ро ба англисӣ нависед:", "Барф = ?", null, "Snow", null, "Барф — Snow"),
        Exercise("ep_e8_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Tree" to "Дарахт", "Flower" to "Гул", "River" to "Дарё", "Sun" to "Офтоб")),
        Exercise("ep_e8_6", ExerciseType.MULTIPLE_CHOICE, "«Rain» чӣ маъно дорад?", "Rain = ...", listOf("Барф", "Шамол", "Борон", "Офтоб"), "Борон", 2, "Rain — Борон"),
        Exercise("ep_e8_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: The weather today is beautiful.\nFiruz: _____", null, listOf("I like rain.", "Sunny days are my favorite!", "The mountain is tall."), "Sunny days are my favorite!", 1, "Дар бораи об-ҳаво"),
        Exercise("ep_e8_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Дарахт баланд аст»", null, null, "The tree is tall", null, "The + чиз + is + сифат", words = listOf("tall", "is", "tree", "The")),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 3 · МУОШИРАТИ ОЗОД  (Free Communication)
// ═══════════════════════════════════════════════════

// ── Lesson 9: Эҳсосот (Feelings) ────────────────

private val epM3L9 = Lesson(
    id = "ep_m3_l9", moduleId = "ep_m3",
    title = "Эҳсосот", description = "Эҳсосот ва ҳолат",
    emoji = "\uD83D\uDE0A", orderIndex = 0,
    dialogue = Dialogue(
        "Эҳсосот",
        listOf(
            DialogueLine("Anna", "How do you feel today?", "Имрӯз ҳолатон чӣ тавр аст?"),
            DialogueLine("Firuz", "I feel excited! We are going to the mountains.", "Ман ҳаяҷонзада ҳастам! Мо ба кӯҳ меравем."),
            DialogueLine("Anna", "Oh, I am scared of heights.", "Оҳ, ман аз баландӣ метарсам."),
            DialogueLine("Firuz", "Don't worry. I am happy you are coming with us.", "Ташвиш накаш. Ман хурсандам ки бо мо меоед."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w9_1", "Happy", "Хурсанд", "Hap-py", "I am happy today", "Ман имрӯз хурсанд ҳастам", "ep_m3_l9"),
        WordItem("ep_w9_2", "Sad", "Ғамгин", "Sad", "She looks sad", "Вай ғамгин ба назар мерасад", "ep_m3_l9"),
        WordItem("ep_w9_3", "Angry", "Хашмгин", "An-gry", "Don't be angry", "Хашмгин набош", "ep_m3_l9"),
        WordItem("ep_w9_4", "Tired", "Хаста", "Tired", "I am very tired", "Ман хеле хаста ҳастам", "ep_m3_l9"),
        WordItem("ep_w9_5", "Excited", "Ҳаяҷонзада", "Ex-cit-ed", "I am excited!", "Ман ҳаяҷонзада ҳастам!", "ep_m3_l9"),
        WordItem("ep_w9_6", "Scared", "Тарсон", "Scared", "I am scared", "Ман метарсам", "ep_m3_l9"),
        WordItem("ep_w9_7", "Love", "Дӯст доштан", "Love", "I love my family", "Ман оиламро дӯст медорам", "ep_m3_l9"),
        WordItem("ep_w9_8", "Miss", "Соғинч", "Miss", "I miss my home", "Ман ватанамро соғинч мекунам", "ep_m3_l9"),
    ),
    grammarTip = GrammarTip(
        "I feel... / I am happy because...",
        "Барои ҳолат аз «I feel...» ё «I am...» ва барои сабаб аз «I am happy because...» истифода баред.",
        listOf("I feel excited.", "I am happy because we are going.", "I miss my home."),
    ),
    exercises = listOf(
        Exercise("ep_e9_1", ExerciseType.MULTIPLE_CHOICE, "«Excited» чӣ маъно дорад?", "Excited = ...", listOf("Хурсанд", "Ғамгин", "Ҳаяҷонзада", "Хаста"), "Ҳаяҷонзада", 2, "Excited — Ҳаяҷонзада"),
        Exercise("ep_e9_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I am very _____.", listOf("happy", "tired", "love", "miss"), "tired", 1, "I am very tired — Ман хеле хаста ҳастам"),
        Exercise("ep_e9_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман имрӯз хурсанд ҳастам»-ро интихоб кунед:", null, listOf("I feel excited today", "I am happy today", "I feel good today", "I am not sad today"), "I am happy today", 1, "Ман имрӯз хурсанд ҳастам = I am happy today"),
        Exercise("ep_e9_4", ExerciseType.TYPE_ANSWER, "«Ғамгин»-ро ба англисӣ нависед:", "Ғамгин = ?", null, "Sad", null, "Ғамгин — Sad"),
        Exercise("ep_e9_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Happy" to "Хурсанд", "Angry" to "Хашмгин", "Scared" to "Тарсон", "Tired" to "Хаста")),
        Exercise("ep_e9_6", ExerciseType.MULTIPLE_CHOICE, "«Miss» (дар маънои соғинч) чӣ маъно дорад?", "Miss = ...", listOf("Дӯст доштан", "Соғинч кардан", "Тарсидан", "Хурсанд будан"), "Соғинч кардан", 1, "Miss — Соғинч кардан"),
        Exercise("ep_e9_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман хурсандам ки шумо меоед»", null, null, "I am happy you are coming", null, "I am happy + you are coming", words = listOf("coming", "are", "you", "happy", "am", "I")),
        Exercise("ep_e9_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: How do you feel today?\nFiruz: _____", null, listOf("I feel sad.", "I feel excited! We are going to the mountains.", "I am angry."), "I feel excited! We are going to the mountains.", 1, "Ҳолати худро гуфтан"),
    ),
)

// ── Lesson 10: Об-ҳаво (Weather) ─────────────────

private val epM3L10 = Lesson(
    id = "ep_m3_l10", moduleId = "ep_m3",
    title = "Об-ҳаво", description = "Гуфтугӯ дар бораи об-ҳаво",
    emoji = "\u2600\uFE0F", orderIndex = 1,
    dialogue = Dialogue(
        "Об-ҳаво",
        listOf(
            DialogueLine("Anna", "What's the weather like today?", "Имрӯз об-ҳаво чӣ тавр аст?"),
            DialogueLine("Firuz", "It's sunny and warm. Perfect for a walk.", "Офтобӣ ва гарм аст. Барои гардиш мувофиқ."),
            DialogueLine("Anna", "Yesterday it was cloudy and cold.", "Дирӯз абрнок ва хунук буд."),
            DialogueLine("Firuz", "I prefer sunny days. What about you?", "Ман рӯзҳои офтобиро афзал медонам. Шумо чӣ?"),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w10_1", "Weather", "Об-ҳаво", "Weath-er", "The weather is nice", "Об-ҳаво хуб аст", "ep_m3_l10"),
        WordItem("ep_w10_2", "Hot", "Гарм", "Hot", "It is very hot today", "Имрӯз хеле гарм аст", "ep_m3_l10"),
        WordItem("ep_w10_3", "Cold", "Хунук", "Cold", "It is cold outside", "Берун хунук аст", "ep_m3_l10"),
        WordItem("ep_w10_4", "Sunny", "Офтобӣ", "Sun-ny", "It's a sunny day", "Рӯзи офтобӣ", "ep_m3_l10"),
        WordItem("ep_w10_5", "Rainy", "Боронӣ", "Rain-y", "Today is rainy", "Имрӯз боронӣ аст", "ep_m3_l10"),
        WordItem("ep_w10_6", "Cloudy", "Абрнок", "Cloud-y", "The sky is cloudy", "Осмон абрнок аст", "ep_m3_l10"),
        WordItem("ep_w10_7", "Wind", "Шамол", "Wind", "The wind is strong", "Шамол тез аст", "ep_m3_l10"),
        WordItem("ep_w10_8", "Season", "Мавсим", "Sea-son", "What is your favorite season?", "Мавсими дӯстдоштаи шумо кадом?", "ep_m3_l10"),
    ),
    grammarTip = GrammarTip(
        "What's the weather like? / It's sunny today",
        "Барои пурсидан «What's the weather like?» ва барои ҷавоб «It's + сифати об-ҳаво + today» истифода баред.",
        listOf("What's the weather like today?", "It's sunny and warm.", "Yesterday it was cold."),
    ),
    exercises = listOf(
        Exercise("ep_e10_1", ExerciseType.MULTIPLE_CHOICE, "«Cloudy» чӣ маъно дорад?", "Cloudy = ...", listOf("Офтобӣ", "Боронӣ", "Абрнок", "Гарм"), "Абрнок", 2, "Cloudy — Абрнок"),
        Exercise("ep_e10_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "It's _____ and warm today.", listOf("cold", "rainy", "sunny", "windy"), "sunny", 2, "It's sunny and warm — Офтобӣ ва гарм аст"),
        Exercise("ep_e10_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Берун хунук аст»-ро интихоб кунед:", null, listOf("It is hot outside", "It is windy outside", "It is cold outside", "It is cloudy outside"), "It is cold outside", 2, "Берун хунук аст = It is cold outside"),
        Exercise("ep_e10_4", ExerciseType.TYPE_ANSWER, "«Шамол»-ро ба англисӣ нависед:", "Шамол = ?", null, "Wind", null, "Шамол — Wind"),
        Exercise("ep_e10_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Hot" to "Гарм", "Cold" to "Хунук", "Sunny" to "Офтобӣ", "Rainy" to "Боронӣ")),
        Exercise("ep_e10_6", ExerciseType.MULTIPLE_CHOICE, "«Season» чӣ маъно дорад?", "Season = ...", listOf("Об-ҳаво", "Шамол", "Мавсим", "Рӯз"), "Мавсим", 2, "Season — Мавсим"),
        Exercise("ep_e10_7", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Weath-er", listOf("Season", "Weather", "Cloudy", "Sunny"), "Weather", 1, "Weather — Об-ҳаво"),
        Exercise("ep_e10_8", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Об-ҳаво имрӯз хуб аст»", null, null, "The weather is nice today", null, "The weather is + сифат + today", words = listOf("today", "nice", "is", "weather", "The")),
    ),
)

// ── Lesson 11: Интернет (Internet & Social Media) ─

private val epM3L11 = Lesson(
    id = "ep_m3_l11", moduleId = "ep_m3",
    title = "Интернет", description = "Интернет ва шабакаҳои иҷтимоӣ",
    emoji = "\uD83D\uDCF1", orderIndex = 2,
    dialogue = Dialogue(
        "Интернет",
        listOf(
            DialogueLine("Anna", "Can you send me the photo?", "Метавонед аксро ба ман фиристед?"),
            DialogueLine("Firuz", "Sure! I will share it now.", "Албатта! Ман ҳозир мубодила мекунам."),
            DialogueLine("Anna", "I use my phone for everything.", "Ман телефонро барои ҳама чиз истифода мебарам."),
            DialogueLine("Firuz", "Me too. I watch videos and like posts.", "Ман ҳам. Видео тамошо мекунам ва постҳоро писанд мекунам."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w11_1", "Internet", "Интернет", "In-ter-net", "I use the internet", "Ман интернет истифода мебарам", "ep_m3_l11"),
        WordItem("ep_w11_2", "Phone", "Телефон", "Phone", "My phone is new", "Телефони ман нав аст", "ep_m3_l11"),
        WordItem("ep_w11_3", "Photo", "Акс", "Pho-to", "Send me the photo", "Аксро ба ман фиристед", "ep_m3_l11"),
        WordItem("ep_w11_4", "Video", "Видео", "Vid-e-o", "Watch this video", "Ин видеоро тамошо кунед", "ep_m3_l11"),
        WordItem("ep_w11_5", "Send", "Фиристодан", "Send", "Send me a message", "Паёме ба ман фиристед", "ep_m3_l11"),
        WordItem("ep_w11_6", "Share", "Мубодила кардан", "Share", "Share the link", "Линкро мубодила кунед", "ep_m3_l11"),
        WordItem("ep_w11_7", "Like", "Писандидан", "Like", "I liked the post", "Ман постро писандидам", "ep_m3_l11"),
        WordItem("ep_w11_8", "Follow", "Пайравӣ кардан", "Fol-low", "Follow me online", "Маро дар интернет пайравӣ кунед", "ep_m3_l11"),
    ),
    grammarTip = GrammarTip(
        "I use... / Can you send me...?",
        "Барои истифода аз «I use...» ва барои хоҳиш аз «Can you send me...?» истифода баред.",
        listOf("I use my phone every day.", "Can you send me the photo?", "I will share it now."),
    ),
    exercises = listOf(
        Exercise("ep_e11_1", ExerciseType.MULTIPLE_CHOICE, "«Share» чӣ маъно дорад?", "Share = ...", listOf("Писандидан", "Фиристодан", "Мубодила кардан", "Пайравӣ кардан"), "Мубодила кардан", 2, "Share — Мубодила кардан"),
        Exercise("ep_e11_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Can you _____ me the photo?", listOf("like", "follow", "send", "share"), "send", 2, "Can you send me — Метавонед фиристед"),
        Exercise("ep_e11_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман интернет истифода мебарам»-ро интихоб кунед:", null, listOf("I need the internet", "I have the internet", "I use the internet", "I like the internet"), "I use the internet", 2, "Ман интернет истифода мебарам = I use the internet"),
        Exercise("ep_e11_4", ExerciseType.TYPE_ANSWER, "«Пайравӣ кардан»-ро ба англисӣ нависед:", "Пайравӣ кардан = ?", null, "Follow", null, "Пайравӣ кардан — Follow"),
        Exercise("ep_e11_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Phone" to "Телефон", "Photo" to "Акс", "Video" to "Видео", "Send" to "Фиристодан")),
        Exercise("ep_e11_6", ExerciseType.MULTIPLE_CHOICE, "«Like» (дар интернет) чӣ маъно дорад?", "Like = ...", listOf("Фиристодан", "Тамошо кардан", "Писандидан", "Пайравӣ кардан"), "Писандидан", 2, "Like — Писандидан"),
        Exercise("ep_e11_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман видео тамошо мекунам»", null, null, "I watch videos", null, "I watch + чиз", words = listOf("videos", "watch", "I")),
        Exercise("ep_e11_8", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Can you send me the photo?\nFiruz: _____", null, listOf("I don't have a phone.", "Sure! I will share it now.", "Follow me online."), "Sure! I will share it now.", 1, "Ҷавоби хоҳиш"),
    ),
)

// ── Lesson 12: Орзуҳо (Dreams & Future) ─────────

private val epM3L12 = Lesson(
    id = "ep_m3_l12", moduleId = "ep_m3",
    title = "Орзуҳо", description = "Орзуҳо ва нақшаҳои оянда",
    emoji = "\uD83C\uDF1F", orderIndex = 3,
    dialogue = Dialogue(
        "Орзуҳои оянда",
        listOf(
            DialogueLine("Anna", "What is your dream?", "Орзуи шумо чист?"),
            DialogueLine("Firuz", "My dream is to travel the world.", "Орзуи ман сафари ҷаҳон аст."),
            DialogueLine("Anna", "That's amazing! I hope you achieve it.", "Аҷоиб! Умед дорам ноил шавед."),
            DialogueLine("Firuz", "I want to learn many languages and become a world citizen.", "Ман мехоҳам бисёр забонҳо ёд гирам ва шаҳрванди ҷаҳон шавам."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w12_1", "Dream", "Орзу", "Dream", "My dream is big", "Орзуи ман калон аст", "ep_m3_l12"),
        WordItem("ep_w12_2", "Future", "Оянда", "Fu-ture", "The future is bright", "Оянда равшан аст", "ep_m3_l12"),
        WordItem("ep_w12_3", "Want", "Хостан", "Want", "I want to travel", "Ман сафар кардан мехоҳам", "ep_m3_l12"),
        WordItem("ep_w12_4", "Hope", "Умед", "Hope", "I hope to succeed", "Ман умед дорам муваффақ шавам", "ep_m3_l12"),
        WordItem("ep_w12_5", "Travel", "Сафар кардан", "Trav-el", "I love to travel", "Ман сафар кардан дӯст медорам", "ep_m3_l12"),
        WordItem("ep_w12_6", "Learn", "Омӯхтан", "Learn", "I want to learn English", "Ман англисӣ омӯхтан мехоҳам", "ep_m3_l12"),
        WordItem("ep_w12_7", "Become", "Шудан", "Be-come", "I want to become a doctor", "Ман духтур шудан мехоҳам", "ep_m3_l12"),
        WordItem("ep_w12_8", "World", "Ҷаҳон", "World", "I want to see the world", "Ман мехоҳам ҷаҳонро бинам", "ep_m3_l12"),
    ),
    grammarTip = GrammarTip(
        "I want to... / My dream is to...",
        "Барои хоҳиш аз «I want to...» ва барои орзу аз «My dream is to...» истифода баред.",
        listOf("My dream is to travel the world.", "I want to learn many languages.", "I hope to achieve my dream."),
    ),
    exercises = listOf(
        Exercise("ep_e12_1", ExerciseType.MULTIPLE_CHOICE, "«Future» чӣ маъно дорад?", "Future = ...", listOf("Орзу", "Умед", "Оянда", "Ҷаҳон"), "Оянда", 2, "Future — Оянда"),
        Exercise("ep_e12_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "My dream is to _____ the world.", listOf("learn", "become", "travel", "want"), "travel", 2, "My dream is to travel — Орзуи ман сафар кардан аст"),
        Exercise("ep_e12_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи «Ман мехоҳам ҷаҳонро бинам»-ро интихоб кунед:", null, listOf("I dream of the world", "I hope to see the world", "I want to see the world", "The world is big"), "I want to see the world", 2, "Ман мехоҳам ҷаҳонро бинам = I want to see the world"),
        Exercise("ep_e12_4", ExerciseType.TYPE_ANSWER, "«Умед»-ро ба англисӣ нависед:", "Умед = ?", null, "Hope", null, "Умед — Hope"),
        Exercise("ep_e12_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Dream" to "Орзу", "Travel" to "Сафар кардан", "Learn" to "Омӯхтан", "World" to "Ҷаҳон")),
        Exercise("ep_e12_6", ExerciseType.MULTIPLE_CHOICE, "«Become» чӣ маъно дорад?", "Become = ...", listOf("Омӯхтан", "Сафар кардан", "Шудан", "Хостан"), "Шудан", 2, "Become — Шудан"),
        Exercise("ep_e12_7", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед: «Ман англисӣ омӯхтан мехоҳам»", null, null, "I want to learn English", null, "I want to + феъл + чиз", words = listOf("English", "learn", "to", "want", "I")),
        Exercise("ep_e12_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Fu-ture", listOf("Future", "Dream", "World", "Hope"), "Future", 0, "Future — Оянда"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE & COURSE DEFINITIONS
// ═══════════════════════════════════════════════════

private val epModule1 = Module(
    id = "ep_m1", courseId = "personal_english",
    title = "Ҳаёти ҳаррӯза",
    description = "Оила, хона, хӯрок ва рӯзмарра",
    emoji = "\uD83C\uDFE0", orderIndex = 0,
    lessons = listOf(epM1L1, epM1L2, epM1L3, epM1L4),
)

private val epModule2 = Module(
    id = "ep_m2", courseId = "personal_english",
    title = "Шавқу ҳавас",
    description = "Хобби, мусиқӣ, варзиш ва табиат",
    emoji = "\uD83C\uDFA8", orderIndex = 1,
    lessons = listOf(epM2L5, epM2L6, epM2L7, epM2L8),
)

private val epModule3 = Module(
    id = "ep_m3", courseId = "personal_english",
    title = "Муоширати озод",
    description = "Эҳсосот, об-ҳаво, интернет ва орзуҳо",
    emoji = "\uD83D\uDCAC", orderIndex = 2,
    lessons = listOf(epM3L9, epM3L10, epM3L11, epM3L12),
)

val personalEnglishCourse = Course(
    id = "personal_english",
    title = "Англисӣ барои худам",
    description = "Забони англисӣ барои ҳаёти ҳаррӯза: оила, хобби, эҳсосот ва орзуҳо",
    emoji = "\uD83E\uDDE0",
    goalType = "personal",
    language = "english",
    modules = listOf(
        epModule1, epModule2, epModule3,
        epModule4, epModule5, epModule6, epModule7, epModule8, epModule9, epModule10,
    ),
)
