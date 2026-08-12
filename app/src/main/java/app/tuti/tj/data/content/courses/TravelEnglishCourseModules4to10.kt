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
//  MODULE 4 · ХӮРОК ДАР САФАР (Food & Dining)
// ═══════════════════════════════════════════════════

internal val etM4L1 = Lesson(
    id = "et_m4_l1", moduleId = "et_m4",
    title = "Фастфуд", description = "Бургер, комбо ва бо худ",
    emoji = "\uD83C\uDF54", orderIndex = 0,
    dialogue = Dialogue(
        "Дар фастфуд",
        listOf(
            DialogueLine("Cashier", "For here or to go?", "Ин ҷо мехӯред ё бо худ?"),
            DialogueLine("Firuz", "To go, please. I'll have a burger and fries.", "Бо худ, лутфан. Бургер ва картошкаи бирён мехоҳам."),
            DialogueLine("Cashier", "Do you want a drink with the combo?", "Бо маҷмӯа нӯшокӣ мехоҳед?"),
            DialogueLine("Firuz", "Yes, and ketchup and a straw, please.", "Ҳа, ва кетчуп ва найча, лутфан."),
            DialogueLine("Cashier", "Here is your meal and a napkin.", "Ин хӯрок ва дастмол."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w13_1", "Burger", "Бургер", "Bur-ger", "I'll have a burger", "Бургер мехоҳам", "et_m4_l1"),
        WordItem("et_w13_2", "Fries", "Картошкаи бирён", "Fries", "Large fries, please", "Картошкаи бирёни калон, лутфан", "et_m4_l1"),
        WordItem("et_w13_3", "Drink", "Нӯшокӣ", "Drink", "A cold drink", "Нӯшокии хунук", "et_m4_l1"),
        WordItem("et_w13_4", "Combo", "Маҷмӯа", "Com-bo", "I'll take the combo meal", "Маҷмӯаи хӯрок мегирам", "et_m4_l1"),
        WordItem("et_w13_5", "Takeaway", "Бо худ", "Take-a-way", "Is this takeaway?", "Ин бо худ аст?", "et_m4_l1"),
        WordItem("et_w13_6", "Ketchup", "Кетчуп", "Ketch-up", "Extra ketchup, please", "Кетчупи иловагӣ, лутфан", "et_m4_l1"),
        WordItem("et_w13_7", "Straw", "Найча", "Straw", "Can I have a straw?", "Метавонам найча гирам?", "et_m4_l1"),
        WordItem("et_w13_8", "Napkin", "Дастмол", "Nap-kin", "I need a napkin", "Ба ман дастмол лозим", "et_m4_l1"),
    ),
    grammarTip = GrammarTip(
        "I'll have a... / For here or to go?",
        "Барои фармоиш аз «I'll have a...» ва барои ин ҷо ё бо худ аз «For here or to go?» истифода баред.",
        listOf("I'll have a burger and fries.", "For here or to go?", "I'd like the combo, please."),
    ),
    exercises = listOf(
        Exercise("et_e13_1", ExerciseType.MULTIPLE_CHOICE, "«Combo» чӣ маъно дорад?", "Combo = ...", listOf("Найча", "Маҷмӯа", "Дастмол", "Кетчуп"), "Маҷмӯа", 1, "Combo — Маҷмӯа"),
        Exercise("et_e13_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____ or to go?", listOf("For here", "I'll have", "Takeaway", "Ketchup"), "For here", 0, "For here or to go?"),
        Exercise("et_e13_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I will go a burger", "I'll have a burger, please", "I have burger to go for here", "Burger is a napkin"), "I'll have a burger, please", 1, "I'll have a..."),
        Exercise("et_e13_4", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Fries" to "Картошкаи бирён", "Ketchup" to "Кетчуп", "Straw" to "Найча", "Napkin" to "Дастмол")),
        Exercise("et_e13_5", ExerciseType.TYPE_ANSWER, "«Бо худ»-ро ба англисӣ нависед (як калима):", "Бо худ = ?", null, "Takeaway", null, "Takeaway — Бо худ"),
        Exercise("et_e13_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "For here or to go", null, "For here or to go", words = listOf("go", "to", "or", "here", "For")),
        Exercise("et_e13_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nCashier: For here or to go?\nFiruz: _____", null, listOf("I'll have a napkin.", "To go, please.", "I want ketchup combo."), "To go, please.", 1, "To go"),
        Exercise("et_e13_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Bur-ger", listOf("Drink", "Burger", "Combo", "Fries"), "Burger", 1, "Burger — Бургер"),
    ),
)

internal val etM4L2 = Lesson(
    id = "et_m4_l2", moduleId = "et_m4",
    title = "Кафе", description = "Қаҳва, шир ва рамзи вайфай",
    emoji = "\u2615", orderIndex = 1,
    dialogue = Dialogue(
        "Дар кафе",
        listOf(
            DialogueLine("Firuz", "Can I get a large latte with milk, please?", "Метавонам латтеи калон бо шир гирам, лутфан?"),
            DialogueLine("Barista", "Sure. Do you want sugar in it?", "Албатта. Шакар мехоҳед?"),
            DialogueLine("Firuz", "Just a little. And I'd like an espresso for my friend.", "Каме. Ва барои дӯстам як эспрессо мехоҳам."),
            DialogueLine("Barista", "The wifi password is on the board.", "Рамзи вайфай дар тахта аст."),
            DialogueLine("Firuz", "Great. I'll also take a croissant and a piece of cake.", "Аъло. Круассан ва як кулча низ мегирам."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w14_1", "Coffee", "Қаҳва", "Cof-fee", "I need coffee", "Ба ман қаҳва лозим", "et_m4_l2"),
        WordItem("et_w14_2", "Latte", "Латте", "Lat-te", "A hot latte", "Латтеи гарм", "et_m4_l2"),
        WordItem("et_w14_3", "Espresso", "Эспрессо", "Es-pres-so", "Double espresso", "Эспрессои дукарата", "et_m4_l2"),
        WordItem("et_w14_4", "Milk", "Шир", "Milk", "With milk", "Бо шир", "et_m4_l2"),
        WordItem("et_w14_5", "Sugar", "Шакар", "Su-gar", "No sugar, please", "Бе шакар, лутфан", "et_m4_l2"),
        WordItem("et_w14_6", "Cake", "Кулча", "Cake", "A slice of cake", "Як қисми кулча", "et_m4_l2"),
        WordItem("et_w14_7", "Croissant", "Круассан", "Crois-sant", "A butter croissant", "Круассани равғанӣ", "et_m4_l2"),
        WordItem("et_w14_8", "Wifi password", "Рамзи вайфай", "Wi-fi pass-word", "What's the wifi password?", "Рамзи вайфай чист?", "et_m4_l2"),
    ),
    grammarTip = GrammarTip(
        "Can I get a large latte? / What's the wifi password?",
        "Барои фармоиш аз «Can I get a...?» ва барои вайфай аз «What's the wifi password?» истифода баред.",
        listOf("Can I get a large latte?", "What's the wifi password?", "No sugar, please."),
    ),
    exercises = listOf(
        Exercise("et_e14_1", ExerciseType.MULTIPLE_CHOICE, "«Espresso» чӣ маъно дорад?", "Espresso = ...", listOf("Латте", "Эспрессо", "Шир", "Шакар"), "Эспрессо", 1, "Espresso — Эспрессо"),
        Exercise("et_e14_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Can I get a large _____?", listOf("cake", "wifi", "latte", "sugar"), "latte", 2, "Can I get a large latte"),
        Exercise("et_e14_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("What is the milk password?", "What's the wifi password?", "What is croissant wifi?", "Where is the latte password?"), "What's the wifi password?", 1, "wifi password"),
        Exercise("et_e14_4", ExerciseType.TYPE_ANSWER, "«Шакар»-ро ба англисӣ нависед:", "Шакар = ?", null, "Sugar", null, "Sugar — Шакар"),
        Exercise("et_e14_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Coffee" to "Қаҳва", "Milk" to "Шир", "Cake" to "Кулча", "Croissant" to "Круассан")),
        Exercise("et_e14_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Can I get a large latte", null, "Can I get...", words = listOf("latte", "large", "a", "get", "Can", "I")),
        Exercise("et_e14_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nBarista: Do you want sugar?\nFiruz: _____", null, listOf("The wifi is a cake.", "Just a little, please.", "I need a croissant password."), "Just a little, please.", 1, "Sugar"),
        Exercise("et_e14_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Lat-te", listOf("Espresso", "Latte", "Coffee", "Milk"), "Latte", 1, "Latte — Латте"),
    ),
)

internal val etM4L3 = Lesson(
    id = "et_m4_l3", moduleId = "et_m4",
    title = "Ресторани расмӣ", description = "Захира, шароб ва ҳассосият",
    emoji = "\uD83C\uDF7D\uFE0F", orderIndex = 2,
    dialogue = Dialogue(
        "Шоми расмӣ",
        listOf(
            DialogueLine("Firuz", "I have a reservation under the name Firuz.", "Ман захира бо номи Фирӯз дорам."),
            DialogueLine("Host", "Welcome. Your table is ready for four courses.", "Хуш омадед. Мизи шумо барои чор навбат омода аст."),
            DialogueLine("Firuz", "I'm allergic to nuts. Can the chef recommend a vegetarian appetizer?", "Ба ман ҷавз ҳассосият аст. Ошпаз метавонад пешхӯроки гиёҳхор тавсия диҳад?"),
            DialogueLine("Host", "Of course. We have wine that pairs well with it.", "Албатта. Мо шароб дорем, ки хуб мемонад."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w15_1", "Reservation", "Захира", "Res-er-va-tion", "I have a reservation", "Ман захира дорам", "et_m4_l3"),
        WordItem("et_w15_2", "Course", "Навбат", "Course", "The main course", "Хӯроки асосӣ", "et_m4_l3"),
        WordItem("et_w15_3", "Wine", "Шароб", "Wine", "A glass of wine", "Як шиша шароб", "et_m4_l3"),
        WordItem("et_w15_4", "Appetizer", "Пешхӯрок", "Ap-pe-ti-zer", "A light appetizer", "Пешхӯроки сабук", "et_m4_l3"),
        WordItem("et_w15_5", "Chef", "Ошпаз", "Chef", "Ask the chef", "Аз ошпаз бипурсед", "et_m4_l3"),
        WordItem("et_w15_6", "Recommend", "Тавсия", "Rec-om-mend", "What do you recommend?", "Чӣ тавсия медиҳед?", "et_m4_l3"),
        WordItem("et_w15_7", "Allergic", "Ҳассосият", "Al-ler-gic", "I'm allergic to peanuts", "Ба ман арахис ҳассосият аст", "et_m4_l3"),
        WordItem("et_w15_8", "Vegetarian", "Гиёҳхор", "Ve-gi-ta-ri-an", "I am vegetarian", "Ман гиёҳхор ҳастам", "et_m4_l3"),
    ),
    grammarTip = GrammarTip(
        "I have a reservation under... / I'm allergic to...",
        "Барои захира аз «I have a reservation under...» ва барои ҳассосият аз «I'm allergic to...» истифода баред.",
        listOf("I have a reservation under Smith.", "I'm allergic to shellfish.", "Could the chef recommend a dish?"),
    ),
    exercises = listOf(
        Exercise("et_e15_1", ExerciseType.MULTIPLE_CHOICE, "«Vegetarian» чӣ маъно дорад?", "Vegetarian = ...", listOf("Ошпаз", "Гиёҳхор", "Шароб", "Навбат"), "Гиёҳхор", 1, "Vegetarian — Гиёҳхор"),
        Exercise("et_e15_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I'm _____ to nuts.", listOf("vegetarian", "chef", "allergic", "wine"), "allergic", 2, "I'm allergic to"),
        Exercise("et_e15_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I have a reservation under the wine", "I have a reservation under Firuz", "I have a course under reservation", "I allergic reservation"), "I have a reservation under Firuz", 1, "reservation under..."),
        Exercise("et_e15_4", ExerciseType.TYPE_ANSWER, "«Ошпаз»-ро ба англисӣ нависед:", "Ошпаз = ?", null, "Chef", null, "Chef — Ошпаз"),
        Exercise("et_e15_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Reservation" to "Захира", "Course" to "Навбат", "Wine" to "Шароб", "Appetizer" to "Пешхӯрок")),
        Exercise("et_e15_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "What do you recommend", null, "What do you recommend", words = listOf("recommend", "you", "do", "What")),
        Exercise("et_e15_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: I need a vegetarian dish.\nHost: _____", null, listOf("You are allergic to wine.", "The chef can prepare a special plate.", "Your reservation is a course."), "The chef can prepare a special plate.", 1, "Chef"),
        Exercise("et_e15_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Al-ler-gic", listOf("Allergic", "Recommend", "Vegetarian", "Appetizer"), "Allergic", 0, "Allergic — Ҳассосият"),
    ),
)

internal val etM4L4 = Lesson(
    id = "et_m4_l4", moduleId = "et_m4",
    title = "Бозори маҳаллӣ", description = "Тоза, табиӣ ва чонакашӣ",
    emoji = "\uD83E\uDED6", orderIndex = 3,
    dialogue = Dialogue(
        "Дар бозор",
        listOf(
            DialogueLine("Firuz", "Can I try this? Is it locally made?", "Метавонам инро чашидам? Маҳаллӣ сохта шудааст?"),
            DialogueLine("Vendor", "Yes, it's homemade with fresh organic spices.", "Ҳа, хонагӣ бо масолаҳои табиӣ ва тоза аст."),
            DialogueLine("Firuz", "The taste is amazing. Can I get a small sample?", "Мазза аҷоиб аст. Метавонам як намӯнаи хурд гирам?"),
            DialogueLine("Vendor", "Sure. If you buy two, we can bargain a little.", "Албатта. Агар ду то харед, каме чонакашӣ мекунем."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w16_1", "Fresh", "Тоза", "Fresh", "Fresh fruit", "Меваи тоза", "et_m4_l4"),
        WordItem("et_w16_2", "Organic", "Табиӣ", "Or-gan-ic", "Organic vegetables", "Сабзавоти табиӣ", "et_m4_l4"),
        WordItem("et_w16_3", "Taste", "Мазза", "Taste", "I love the taste", "Ман маззаро дӯст медорам", "et_m4_l4"),
        WordItem("et_w16_4", "Sample", "Намуна", "Sam-ple", "Can I try a sample?", "Метавонам намӯна чашидам?", "et_m4_l4"),
        WordItem("et_w16_5", "Homemade", "Хонагӣ", "Home-made", "Homemade bread", "Нони хонагӣ", "et_m4_l4"),
        WordItem("et_w16_6", "Spice", "Масолеҳ", "Spice", "Spices and herbs", "Масолаҳо ва сабзиҳо", "et_m4_l4"),
        WordItem("et_w16_7", "Bargain", "Чонакашӣ", "Bar-gain", "We can bargain", "Мо метавонем чонакашӣ кунем", "et_m4_l4"),
        WordItem("et_w16_8", "Local", "Маҳаллӣ", "Lo-cal", "Local products", "Маҳсулоти маҳаллӣ", "et_m4_l4"),
    ),
    grammarTip = GrammarTip(
        "Can I try this? / Is this locally made?",
        "Барои чашидан аз «Can I try this?» ва барои маҳаллӣ будан аз «Is this locally made?» истифода баред.",
        listOf("Can I try this?", "Is this locally made?", "Is this organic?"),
    ),
    exercises = listOf(
        Exercise("et_e16_1", ExerciseType.MULTIPLE_CHOICE, "«Organic» чӣ маъно дорад?", "Organic = ...", listOf("Тоза", "Табиӣ", "Маҳаллӣ", "Хонагӣ"), "Табиӣ", 1, "Organic — Табиӣ"),
        Exercise("et_e16_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Is this _____ made?", listOf("fresh", "sample", "locally", "spice"), "locally", 2, "locally made"),
        Exercise("et_e16_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Can I try this bargain?", "Can I try this?", "Can I taste locally?", "Can I sample organic homemade?"), "Can I try this?", 1, "Can I try this?"),
        Exercise("et_e16_4", ExerciseType.TYPE_ANSWER, "«Чонакашӣ»-ро ба англисӣ нависед:", "Чонакашӣ = ?", null, "Bargain", null, "Bargain — Чонакашӣ"),
        Exercise("et_e16_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Fresh" to "Тоза", "Taste" to "Мазза", "Sample" to "Намуна", "Local" to "Маҳаллӣ")),
        Exercise("et_e16_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Is this locally made", null, "Is this locally made", words = listOf("made", "locally", "this", "Is")),
        Exercise("et_e16_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Can I try this?\nVendor: _____", null, listOf("It is not local.", "Yes, it's homemade and fresh.", "The bargain is organic."), "Yes, it's homemade and fresh.", 1, "Homemade"),
        Exercise("et_e16_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Home-made", listOf("Homemade", "Bargain", "Spice", "Sample"), "Homemade", 0, "Homemade — Хонагӣ"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 5 · ИСТИРОҲАТ ВА ЗАВҚ (Leisure & Fun)
// ═══════════════════════════════════════════════════

internal val etM5L1 = Lesson(
    id = "et_m5_l1", moduleId = "et_m5",
    title = "Соҳил", description = "Шино, крем ва ғуруби офтоб",
    emoji = "\uD83C\uDF0A", orderIndex = 0,
    dialogue = Dialogue(
        "Дар соҳил",
        listOf(
            DialogueLine("Firuz", "Is it safe to swim here?", "Ин ҷо шино кардан бехатар аст?"),
            DialogueLine("Lifeguard", "Yes, but stay between the flags. Use sunscreen.", "Ҳа, аммо байни парчам бимонед. Крем истифода баред."),
            DialogueLine("Firuz", "Where can I rent a towel? The sand is hot.", "Дар куҷо сочиқ иҷора карда метавонам? Рег гарм аст."),
            DialogueLine("Lifeguard", "The waves are calm today. Enjoy the sunset!", "Имрӯз мавҷҳо ороманд. Аз ғуруби офтоб лаззат баред!"),
        ),
    ),
    newWords = listOf(
        WordItem("et_w17_1", "Beach", "Соҳил", "Beach", "Let's go to the beach", "Биёед ба соҳил равем", "et_m5_l1"),
        WordItem("et_w17_2", "Swim", "Шино кардан", "Swim", "I love to swim", "Ман шино карданро дӯст медорам", "et_m5_l1"),
        WordItem("et_w17_3", "Sunscreen", "Крем", "Sun-screen", "Apply sunscreen", "Крем молед", "et_m5_l1"),
        WordItem("et_w17_4", "Towel", "Сочиқ", "Tow-el", "Bring a towel", "Сочиқ биёред", "et_m5_l1"),
        WordItem("et_w17_5", "Wave", "Мавҷ", "Wave", "Big waves today", "Имрӯз мавҷҳо калонанд", "et_m5_l1"),
        WordItem("et_w17_6", "Sand", "Рег", "Sand", "White sand", "Реги сафед", "et_m5_l1"),
        WordItem("et_w17_7", "Sunset", "Ғуруб", "Sun-set", "Watch the sunset", "Ғуруби офтобро тамошо кунед", "et_m5_l1"),
        WordItem("et_w17_8", "Lifeguard", "Наҷотдиҳанда", "Life-guard", "Ask the lifeguard", "Аз наҷотдиҳанда бипурсед", "et_m5_l1"),
    ),
    grammarTip = GrammarTip(
        "Is it safe to swim here? / Where can I rent...",
        "Барои бехатарӣ аз «Is it safe to swim here?» ва барои иҷора аз «Where can I rent...?» истифода баред.",
        listOf("Is it safe to swim here?", "Where can I rent a towel?", "The waves are strong today."),
    ),
    exercises = listOf(
        Exercise("et_e17_1", ExerciseType.MULTIPLE_CHOICE, "«Lifeguard» чӣ маъно дорад?", "Lifeguard = ...", listOf("Мавҷ", "Наҷотдиҳанда", "Ғуруб", "Сочиқ"), "Наҷотдиҳанда", 1, "Lifeguard — Наҷотдиҳанда"),
        Exercise("et_e17_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Is it safe to _____ here?", listOf("sand", "sunset", "swim", "towel"), "swim", 2, "safe to swim"),
        Exercise("et_e17_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Where is the beach swim?", "Where can I rent a towel?", "Where can I wave sunscreen?", "Where is sunset lifeguard?"), "Where can I rent a towel?", 1, "Where can I rent..."),
        Exercise("et_e17_4", ExerciseType.TYPE_ANSWER, "«Рег»-ро ба англисӣ нависед:", "Рег = ?", null, "Sand", null, "Sand — Рег"),
        Exercise("et_e17_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Beach" to "Соҳил", "Wave" to "Мавҷ", "Sunset" to "Ғуруб", "Sunscreen" to "Крем")),
        Exercise("et_e17_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Is it safe to swim here", null, "Is it safe to swim here", words = listOf("here", "swim", "to", "safe", "it", "Is")),
        Exercise("et_e17_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: The sand is hot.\nLifeguard: _____", null, listOf("The wave is a towel.", "Wear shoes and use sunscreen.", "Swim is sunset."), "Wear shoes and use sunscreen.", 1, "Sunscreen"),
        Exercise("et_e17_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Sun-set", listOf("Sunset", "Swim", "Beach", "Sand"), "Sunset", 0, "Sunset — Ғуруб"),
    ),
)

internal val etM5L2 = Lesson(
    id = "et_m5_l2", moduleId = "et_m5",
    title = "Кӯҳнавардӣ", description = "Роҳ, кӯҳ ва обшор",
    emoji = "\u26F0\uFE0F", orderIndex = 1,
    dialogue = Dialogue(
        "Дар кӯҳ",
        listOf(
            DialogueLine("Firuz", "How long is the trail to the waterfall?", "То обшор роҳ чӣ қадар дароз аст?"),
            DialogueLine("Guide", "About three hours. This trail is moderate, not too difficult.", "Тақрибан се соат. Ин роҳ миёнавӣ аст, чандон сахт нест."),
            DialogueLine("Firuz", "I'll put on my boots and check my backpack.", "Мӯзаҳоро мепӯшам ва борхалтаро санҷидам."),
            DialogueLine("Guide", "The valley view is beautiful. We can camp there tonight.", "Манзараи водӣ зебо аст. Шабон дар он ҷо хайма мезанем."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w18_1", "Trail", "Роҳ", "Trail", "Follow the trail", "По роҳ биравед", "et_m5_l2"),
        WordItem("et_w18_2", "Hike", "Пиёдаравӣ", "Hike", "We will hike tomorrow", "Фардо пиёдаравӣ мекунем", "et_m5_l2"),
        WordItem("et_w18_3", "Mountain", "Кӯҳ", "Moun-tain", "High mountains", "Кӯҳҳои баланд", "et_m5_l2"),
        WordItem("et_w18_4", "Valley", "Водӣ", "Val-ley", "A green valley", "Водии сабз", "et_m5_l2"),
        WordItem("et_w18_5", "Waterfall", "Обшор", "Wa-ter-fall", "A tall waterfall", "Обшори баланд", "et_m5_l2"),
        WordItem("et_w18_6", "Backpack", "Борхалта", "Back-pack", "Pack your backpack", "Борхалтаро бандед", "et_m5_l2"),
        WordItem("et_w18_7", "Boot", "Мӯза", "Boot", "Hiking boots", "Мӯзаҳои кӯҳнавардӣ", "et_m5_l2"),
        WordItem("et_w18_8", "Camp", "Хаймагоҳ", "Camp", "We camp by the river", "Мо дар канори дарё хайма мезанем", "et_m5_l2"),
    ),
    grammarTip = GrammarTip(
        "How long is the trail? / Is this trail difficult?",
        "Барои дарозии роҳ аз «How long is the trail?» ва барои сахтӣ аз «Is this trail difficult?» истифода баред.",
        listOf("How long is the trail?", "Is this trail difficult?", "The hike takes five hours."),
    ),
    exercises = listOf(
        Exercise("et_e18_1", ExerciseType.MULTIPLE_CHOICE, "«Waterfall» чӣ маъно дорад?", "Waterfall = ...", listOf("Водӣ", "Обшор", "Кӯҳ", "Роҳ"), "Обшор", 1, "Waterfall — Обшор"),
        Exercise("et_e18_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Is this trail _____?", listOf("long", "difficult", "waterfall", "valley"), "difficult", 1, "Is this trail difficult?"),
        Exercise("et_e18_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("How long is the mountain?", "How long is the trail?", "How long is the boot?", "How long is camp hike?"), "How long is the trail?", 1, "How long is the trail?"),
        Exercise("et_e18_4", ExerciseType.TYPE_ANSWER, "«Борхалта»-ро ба англисӣ нависед:", "Борхалта = ?", null, "Backpack", null, "Backpack — Борхалта"),
        Exercise("et_e18_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Hike" to "Пиёдаравӣ", "Mountain" to "Кӯҳ", "Valley" to "Водӣ", "Boot" to "Мӯза")),
        Exercise("et_e18_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "We can camp in the valley", null, "We can camp...", words = listOf("valley", "the", "in", "camp", "can", "We")),
        Exercise("et_e18_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Is the trail hard?\nGuide: _____", null, listOf("The waterfall is a boot.", "It's moderate, not too difficult.", "The backpack is long."), "It's moderate, not too difficult.", 1, "difficult"),
        Exercise("et_e18_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Back-pack", listOf("Trail", "Backpack", "Camp", "Hike"), "Backpack", 1, "Backpack — Борхалта"),
    ),
)

internal val etM5L3 = Lesson(
    id = "et_m5_l3", moduleId = "et_m5",
    title = "Музей ва галерея", description = "Намоишгоҳ, санъат ва чипта",
    emoji = "\uD83C\uDFBC", orderIndex = 2,
    dialogue = Dialogue(
        "Дар музей",
        listOf(
            DialogueLine("Firuz", "When does the museum close?", "Музей кай мепӯшад?"),
            DialogueLine("Staff", "At six. You can take photos in the garden, but not near the paintings.", "Дар соати шаш. Дар боғ акс гиред, аммо назди расмҳо не."),
            DialogueLine("Firuz", "Is there a guide for the ancient sculpture collection?", "Барои маҷмӯаи ҳайкалҳои қадимӣ роҳбаласт ҳаст?"),
            DialogueLine("Staff", "Yes, the exhibition of modern art is on the second floor.", "Ҳа, намоишгоҳи санъати муосир дар ошёнаи дуюм аст."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w19_1", "Exhibition", "Намоишгоҳ", "Ex-hi-bi-tion", "A new exhibition", "Намоишгоҳи нав", "et_m5_l3"),
        WordItem("et_w19_2", "Art", "Санъат", "Art", "Modern art", "Санъати муосир", "et_m5_l3"),
        WordItem("et_w19_3", "Painting", "Расм", "Paint-ing", "Famous paintings", "Расмҳои шинохта", "et_m5_l3"),
        WordItem("et_w19_4", "Sculpture", "Ҳайкал", "Sculp-ture", "Ancient sculpture", "Ҳайкали қадимӣ", "et_m5_l3"),
        WordItem("et_w19_5", "Ticket", "Чипта", "Tick-et", "Museum ticket", "Чиптаи музей", "et_m5_l3"),
        WordItem("et_w19_6", "Guide", "Роҳбалад", "Guide", "A tour guide", "Роҳбалади экскурсия", "et_m5_l3"),
        WordItem("et_w19_7", "Ancient", "Қадимӣ", "An-cient", "Ancient history", "Таърихи қадимӣ", "et_m5_l3"),
        WordItem("et_w19_8", "Collection", "Маҷмӯа", "Col-lec-tion", "A private collection", "Маҷмӯаи шахсӣ", "et_m5_l3"),
    ),
    grammarTip = GrammarTip(
        "When does the museum close? / Can I take photos?",
        "Барои вақти пӯшидан аз «When does the museum close?» ва барои акс аз «Can I take photos?» истифода баред.",
        listOf("When does the museum close?", "Can I take photos here?", "Is there a guided tour?"),
    ),
    exercises = listOf(
        Exercise("et_e19_1", ExerciseType.MULTIPLE_CHOICE, "«Sculpture» чӣ маъно дорад?", "Sculpture = ...", listOf("Расм", "Ҳайкал", "Чипта", "Маҷмӯа"), "Ҳайкал", 1, "Sculpture — Ҳайкал"),
        Exercise("et_e19_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "When does the museum _____?", listOf("open", "guide", "close", "ticket"), "close", 2, "museum close"),
        Exercise("et_e19_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Can I take the museum?", "Can I take photos here?", "Can I close painting?", "Can I guide ancient?"), "Can I take photos here?", 1, "Can I take photos?"),
        Exercise("et_e19_4", ExerciseType.TYPE_ANSWER, "«Намоишгоҳ»-ро ба англисӣ нависед:", "Намоишгоҳ = ?", null, "Exhibition", null, "Exhibition — Намоишгоҳ"),
        Exercise("et_e19_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Art" to "Санъат", "Painting" to "Расм", "Guide" to "Роҳбалад", "Ancient" to "Қадимӣ")),
        Exercise("et_e19_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "When does the museum close", null, "When does the museum close", words = listOf("close", "museum", "the", "does", "When")),
        Exercise("et_e19_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Can I use flash?\nStaff: _____", null, listOf("The ticket is ancient.", "Not near the paintings, please.", "The exhibition is a guide."), "Not near the paintings, please.", 1, "Photos"),
        Exercise("et_e19_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Col-lec-tion", listOf("Collection", "Exhibition", "Sculpture", "Art"), "Collection", 0, "Collection — Маҷмӯа"),
    ),
)

internal val etM5L4 = Lesson(
    id = "et_m5_l4", moduleId = "et_m5",
    title = "Шаби зиндагӣ", description = "Бар, клуб ва мусиқии зинда",
    emoji = "\uD83C\uDFA4", orderIndex = 3,
    dialogue = Dialogue(
        "Шабона",
        listOf(
            DialogueLine("Firuz", "What time does the show start? Is there a cover charge?", "Намоиш дар чӣ вақт оғоз мешавад? Пардаи даромад ҳаст?"),
            DialogueLine("Anna", "At nine. You get one drink with the entrance ticket.", "Дар соати нӯҳ. Бо чиптаи даромад як нӯшидан мегиред."),
            DialogueLine("Firuz", "Great! I love live music and dancing.", "Аъло! Ман мусиқии зинда ва рақсро дӯст медорам."),
            DialogueLine("Anna", "The club next door has a concert after midnight.", "Клуби ҳамсоя баъди нисфи шаб консерт дорад."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w20_1", "Bar", "Бар", "Bar", "Meet me at the bar", "Дар бар шинос шавем", "et_m5_l4"),
        WordItem("et_w20_2", "Club", "Клуб", "Club", "A busy club", "Клуби серодам", "et_m5_l4"),
        WordItem("et_w20_3", "Concert", "Концерт", "Con-cert", "A live concert", "Консерти зинда", "et_m5_l4"),
        WordItem("et_w20_4", "Show", "Намоиш", "Show", "The show starts at nine", "Намоиш дар соати нӯҳ аст", "et_m5_l4"),
        WordItem("et_w20_5", "Dance", "Рақс", "Dance", "I want to dance", "Ман мехоҳам рақс кунам", "et_m5_l4"),
        WordItem("et_w20_6", "Entrance", "Даромад", "En-trance", "Entrance fee", "Пардаи даромад", "et_m5_l4"),
        WordItem("et_w20_7", "Drink", "Нӯшидан", "Drink", "One drink included", "Як нӯшидан дохил аст", "et_m5_l4"),
        WordItem("et_w20_8", "Live music", "Мусиқии зинда", "Live mu-sic", "I enjoy live music", "Ман мусиқии зинда меписандем", "et_m5_l4"),
    ),
    grammarTip = GrammarTip(
        "What time does the show start? / Is there a cover charge?",
        "Барои вақт аз «What time does the show start?» ва барои парда аз «Is there a cover charge?» истифода баред.",
        listOf("What time does the show start?", "Is there a cover charge?", "The concert was amazing."),
    ),
    exercises = listOf(
        Exercise("et_e20_1", ExerciseType.MULTIPLE_CHOICE, "«Entrance» дар ин контекст чӣ маъно дорад?", null, listOf("Намоиш", "Даромад (парда)", "Рақс", "Нӯшокӣ"), "Даромад (парда)", 1, "Entrance fee"),
        Exercise("et_e20_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "What time does the _____ start?", listOf("club", "entrance", "show", "bar"), "show", 2, "show start"),
        Exercise("et_e20_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Is there a dance cover?", "Is there a cover charge?", "Is there a live bar?", "Is there concert entrance?"), "Is there a cover charge?", 1, "cover charge"),
        Exercise("et_e20_4", ExerciseType.TYPE_ANSWER, "«Мусиқии зинда»-ро ба англисӣ нависед:", "Мусиқии зинда = ?", null, "Live music", null, "Live music"),
        Exercise("et_e20_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Bar" to "Бар", "Club" to "Клуб", "Concert" to "Концерт", "Dance" to "Рақс")),
        Exercise("et_e20_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I love live music", null, "I love live music", words = listOf("music", "live", "love", "I")),
        Exercise("et_e20_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: What time does it start?\nAnna: _____", null, listOf("The bar is a club.", "At nine o'clock.", "The dance is entrance."), "At nine o'clock.", 1, "Time"),
        Exercise("et_e20_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Con-cert", listOf("Show", "Concert", "Drink", "Live music"), "Concert", 1, "Concert — Концерт"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 6 · МАСЪАЛАҲОИ АМАЛӢ (Practical Issues)
// ═══════════════════════════════════════════════════

internal val etM6L1 = Lesson(
    id = "et_m6_l1", moduleId = "et_m6",
    title = "Телефон ва интернет", description = "Сим-корт, роуминг ва ҳотспот",
    emoji = "\uD83D\uDCF1", orderIndex = 0,
    dialogue = Dialogue(
        "Дастгоҳи мобилӣ",
        listOf(
            DialogueLine("Firuz", "Where can I buy a SIM card with data?", "Дар куҷо сим-корт бо интернет харидан мумкин аст?"),
            DialogueLine("Clerk", "We have roaming plans or local data only.", "Мо нақшаҳои роуминг ё танҳо интернети маҳаллӣ дорем."),
            DialogueLine("Firuz", "Is there free wifi at the hotel? I need an adapter for the outlet.", "Дар меҳмонхона вайфайи ройгон ҳаст? Барои розетка адаптор лозим аст."),
            DialogueLine("Clerk", "Your phone shows a weak signal; try a hotspot.", "Телефон сигнали слаб нишон медиҳад; ҳотспотро санҷед."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w21_1", "SIM card", "Сим-корт", "SIM card", "Buy a prepaid SIM card", "Сим-корти пешакӣ харед", "et_m6_l1"),
        WordItem("et_w21_2", "Data", "Интернет", "Da-ta", "Mobile data package", "Бастаи интернети мобилӣ", "et_m6_l1"),
        WordItem("et_w21_3", "Roaming", "Роуминг", "Roam-ing", "Data roaming is expensive", "Интернети роуминг гарон аст", "et_m6_l1"),
        WordItem("et_w21_4", "Charge", "Пур кардан", "Charge", "Charge your phone", "Телефонро пур кунед", "et_m6_l1"),
        WordItem("et_w21_5", "Outlet", "Розетка", "Out-let", "Plug into the outlet", "Ба розетка васл кунед", "et_m6_l1"),
        WordItem("et_w21_6", "Adapter", "Адаптор", "A-dap-ter", "Travel adapter", "Адаптори саёҳат", "et_m6_l1"),
        WordItem("et_w21_7", "Hotspot", "Ҳотспот", "Hot-spot", "Use my phone as a hotspot", "Телефонамро ҳотспот истифода баред", "et_m6_l1"),
        WordItem("et_w21_8", "Signal", "Сигнал", "Sig-nal", "No signal here", "Ин ҷо сигнал нест", "et_m6_l1"),
    ),
    grammarTip = GrammarTip(
        "Where can I buy a SIM card? / Is there free wifi?",
        "Барои сим-корт аз «Where can I buy a SIM card?» ва барои вайфай аз «Is there free wifi?» истифода баред.",
        listOf("Where can I buy a SIM card?", "Is there free wifi?", "My phone needs charging."),
    ),
    exercises = listOf(
        Exercise("et_e21_1", ExerciseType.MULTIPLE_CHOICE, "«Roaming» чӣ маъно дорад?", "Roaming = ...", listOf("Сигнал", "Роуминг", "Ҳотспот", "Адаптор"), "Роуминг", 1, "Roaming — Роуминг"),
        Exercise("et_e21_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Is there free _____?", listOf("data", "signal", "wifi", "SIM"), "wifi", 2, "free wifi"),
        Exercise("et_e21_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Where can I buy a roaming hotel?", "Where can I buy a SIM card?", "Where can I charge outlet?", "Where is adapter data?"), "Where can I buy a SIM card?", 1, "SIM card"),
        Exercise("et_e21_4", ExerciseType.TYPE_ANSWER, "«Сигнал»-ро ба англисӣ нависед:", "Сигнал = ?", null, "Signal", null, "Signal — Сигнал"),
        Exercise("et_e21_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Data" to "Интернет", "Charge" to "Пур кардан", "Outlet" to "Розетка", "Hotspot" to "Ҳотспот")),
        Exercise("et_e21_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I need a travel adapter", null, "travel adapter", words = listOf("adapter", "travel", "a", "need", "I")),
        Exercise("et_e21_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: No signal.\nClerk: _____", null, listOf("Buy a SIM outlet.", "Try turning on a hotspot.", "The wifi is roaming."), "Try turning on a hotspot.", 1, "Hotspot"),
        Exercise("et_e21_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Hot-spot", listOf("Signal", "Hotspot", "Roaming", "Data"), "Hotspot", 1, "Hotspot — Ҳотспот"),
    ),
)

internal val etM6L2 = Lesson(
    id = "et_m6_l2", moduleId = "et_m6",
    title = "Пул ва иваз", description = "Нарх, комиссия ва PIN",
    emoji = "\uD83D\uDCB1", orderIndex = 1,
    dialogue = Dialogue(
        "Ивази пул",
        listOf(
            DialogueLine("Firuz", "What's the exchange rate today? Is there a commission?", "Нархи иваз имрӯз чанд аст? Комиссия ҳаст?"),
            DialogueLine("Clerk", "The rate is on the screen. Withdrawal has a small fee.", "Нарх дар экран аст. Баровардан пардаи хурд дорад."),
            DialogueLine("Firuz", "The ATM ate my card! I remember my PIN, but the machine kept it.", "Бонкомат кортамро хӯрд! Рамзи PIN-ро дар ёд дорам, аммо дастгоҳ нигоҳ дошт."),
            DialogueLine("Clerk", "There is a daily limit on this currency. Go to the bank.", "Барои ин асъор ҳадди ҳаррӯза ҳаст. Ба бонк равед."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w22_1", "Exchange", "Иваз", "Ex-change", "Money exchange office", "Ивазгоҳи пул", "et_m6_l2"),
        WordItem("et_w22_2", "Rate", "Нарх", "Rate", "The exchange rate", "Нархи иваз", "et_m6_l2"),
        WordItem("et_w22_3", "Commission", "Комиссия", "Com-mis-sion", "No commission", "Бе комиссия", "et_m6_l2"),
        WordItem("et_w22_4", "ATM", "Бонкомат", "A-T-M", "Use the ATM", "Бонкоматро истифода баред", "et_m6_l2"),
        WordItem("et_w22_5", "Withdraw", "Баровардан", "With-draw", "Withdraw cash", "Нақд бароред", "et_m6_l2"),
        WordItem("et_w22_6", "Limit", "Ҳад", "Lim-it", "Daily limit", "Ҳадди ҳаррӯза", "et_m6_l2"),
        WordItem("et_w22_7", "PIN", "Рамзи PIN", "PIN", "Enter your PIN", "Рамзи PIN-ро ворид кунед", "et_m6_l2"),
        WordItem("et_w22_8", "Currency", "Асъор", "Cur-ren-cy", "Foreign currency", "Асъори хориҷӣ", "et_m6_l2"),
    ),
    grammarTip = GrammarTip(
        "What's the exchange rate? / The ATM ate my card",
        "Барои нарх аз «What's the exchange rate?» дар ҳолати мушкил «The ATM ate my card» истифода баред.",
        listOf("What's the exchange rate?", "The ATM ate my card.", "Is there a commission?"),
    ),
    exercises = listOf(
        Exercise("et_e22_1", ExerciseType.MULTIPLE_CHOICE, "«Commission» чӣ маъно дорад?", "Commission = ...", listOf("Нарх", "Комиссия", "Ҳад", "Асъор"), "Комиссия", 1, "Commission — Комиссия"),
        Exercise("et_e22_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "What's the exchange _____?", listOf("ATM", "rate", "PIN", "limit"), "rate", 1, "exchange rate"),
        Exercise("et_e22_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("The ATM ate my lunch", "The ATM ate my card", "The ATM ate my rate", "The ATM commission my PIN"), "The ATM ate my card", 1, "The ATM ate my card"),
        Exercise("et_e22_4", ExerciseType.TYPE_ANSWER, "«Баровардан (пул)»-ро ба англисӣ нависед:", "...", null, "Withdraw", null, "Withdraw"),
        Exercise("et_e22_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Exchange" to "Иваз", "Withdraw" to "Баровардан", "Limit" to "Ҳад", "Currency" to "Асъор")),
        Exercise("et_e22_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Enter your PIN", null, "Enter your PIN", words = listOf("PIN", "your", "Enter")),
        Exercise("et_e22_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: I need euros.\nClerk: _____", null, listOf("The PIN is an ATM.", "Check today's rate on the screen.", "Your card is commission."), "Check today's rate on the screen.", 1, "Rate"),
        Exercise("et_e22_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Cur-ren-cy", listOf("Rate", "Currency", "Commission", "Limit"), "Currency", 1, "Currency — Асъор"),
    ),
)

internal val etM6L3 = Lesson(
    id = "et_m6_l3", moduleId = "et_m6",
    title = "Почта ва бастаҳо", description = "Фиристодан, марка ва пайгирӣ",
    emoji = "\u2709\uFE0F", orderIndex = 2,
    dialogue = Dialogue(
        "Дар почта",
        listOf(
            DialogueLine("Firuz", "I'd like to send this package to Tajikistan.", "Ман ин бастаро ба Тоҷикистон фиристодан мехоҳам."),
            DialogueLine("Clerk", "Write the address clearly. You need stamps by weight.", "Суроҳаро равшан нависед. Бо вазн марка лозим аст."),
            DialogueLine("Firuz", "How long will delivery take? Can I track it online?", "Расонидан чанд вақт мегирад? Метавонам онлайн пайгирӣ кунам?"),
            DialogueLine("Clerk", "Yes, use this code on the website.", "Ҳа, ин кодро дар сайт истифода баред."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w23_1", "Post office", "Почта", "Post of-fice", "At the post office", "Дар почта", "et_m6_l3"),
        WordItem("et_w23_2", "Send", "Фиристодан", "Send", "Send a letter", "Нома фиристед", "et_m6_l3"),
        WordItem("et_w23_3", "Package", "Баста", "Pack-age", "A heavy package", "Бастаи вазнин", "et_m6_l3"),
        WordItem("et_w23_4", "Stamp", "Марка", "Stamp", "Buy stamps", "Марка харед", "et_m6_l3"),
        WordItem("et_w23_5", "Address", "Суроға", "Ad-dress", "Full address", "Суроғаи пурра", "et_m6_l3"),
        WordItem("et_w23_6", "Delivery", "Расонидан", "De-liv-er-y", "Fast delivery", "Расонидани тез", "et_m6_l3"),
        WordItem("et_w23_7", "Track", "Пайгирӣ", "Track", "Track your package", "Бастаро пайгирӣ кунед", "et_m6_l3"),
        WordItem("et_w23_8", "Weight", "Вазн", "Weight", "By weight", "Бо вазн", "et_m6_l3"),
    ),
    grammarTip = GrammarTip(
        "I'd like to send this to... / How long will it take?",
        "Барои фиристодан аз «I'd like to send this to...» ва барои вақт аз «How long will it take?» истифода баред.",
        listOf("I'd like to send this to London.", "How long will it take?", "Can I track the package?"),
    ),
    exercises = listOf(
        Exercise("et_e23_1", ExerciseType.MULTIPLE_CHOICE, "«Delivery» чӣ маъно дорад?", "Delivery = ...", listOf("Марка", "Расонидан", "Суроға", "Вазн"), "Расонидан", 1, "Delivery — Расонидан"),
        Exercise("et_e23_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "How long will it _____?", listOf("send", "weight", "take", "stamp"), "take", 2, "How long will it take"),
        Exercise("et_e23_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I'd like to send this package", "I'd like to stamp this office", "I'd like address post", "I'd like weight track"), "I'd like to send this package", 0, "I'd like to send..."),
        Exercise("et_e23_4", ExerciseType.TYPE_ANSWER, "«Пайгирӣ»-ро ба англисӣ нависед:", "Пайгирӣ = ?", null, "Track", null, "Track — Пайгирӣ"),
        Exercise("et_e23_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Send" to "Фиристодан", "Package" to "Баста", "Stamp" to "Марка", "Address" to "Суроға")),
        Exercise("et_e23_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Can I track the package online", null, "Track the package", words = listOf("online", "package", "the", "track", "Can", "I")),
        Exercise("et_e23_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: How much is postage?\nClerk: _____", null, listOf("The delivery is an address.", "It depends on the weight.", "The stamp is a package."), "It depends on the weight.", 1, "Weight"),
        Exercise("et_e23_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Post of-fice", listOf("Package", "Post office", "Delivery", "Send"), "Post office", 1, "Post office — Почта"),
    ),
)

internal val etM6L4 = Lesson(
    id = "et_m6_l4", moduleId = "et_m6",
    title = "Иҷора ва ҷойгиркунӣ", description = "Квартира, хостел ва чек-ин",
    emoji = "\uD83C\uDFE8", orderIndex = 3,
    dialogue = Dialogue(
        "Ҷойи истиқомат",
        listOf(
            DialogueLine("Firuz", "What time is check-in? Is breakfast included?", "Воридшавӣ дар чӣ вақт аст? Наҳорӣ дохил аст?"),
            DialogueLine("Host", "You can check in at two. I left a good review on Airbnb.", "Дар соати ду ворид шудан метавонед. Дар Эйрбиэнби шарҳи хуб гузоштам."),
            DialogueLine("Firuz", "I'd like to rent a small apartment, not a noisy hostel.", "Ман квартираи хурд иҷора мехоҳам, на хостели садодор."),
            DialogueLine("Host", "Check-out is at eleven. Please meet the host at the door.", "Баромад дар соати ёздаҳ. Лутфан дар дарвоза бо мезбон шинос шавед."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w24_1", "Rent", "Иҷора", "Rent", "Rent a car", "Мошин иҷора кунед", "et_m6_l4"),
        WordItem("et_w24_2", "Apartment", "Квартира", "A-part-ment", "A small apartment", "Квартираи хурд", "et_m6_l4"),
        WordItem("et_w24_3", "Hostel", "Хостел", "Hos-tel", "A cheap hostel", "Хостели арзон", "et_m6_l4"),
        WordItem("et_w24_4", "Airbnb", "Эйрбиэнби", "Air-b-n-b", "Book on Airbnb", "Дар Эйрбиэнби захира кунед", "et_m6_l4"),
        WordItem("et_w24_5", "Host", "Мезбон", "Host", "The host is friendly", "Мезбон дӯстона аст", "et_m6_l4"),
        WordItem("et_w24_6", "Check-in", "Воридшавӣ", "Check-in", "Early check-in", "Воридшавии барвақт", "et_m6_l4"),
        WordItem("et_w24_7", "Check-out", "Баромадан", "Check-out", "Late check-out", "Баромади дер", "et_m6_l4"),
        WordItem("et_w24_8", "Review", "Шарҳ", "Re-view", "Write a review", "Шарҳ нависед", "et_m6_l4"),
    ),
    grammarTip = GrammarTip(
        "What time is check-in? / Is breakfast included?",
        "Барои воридшавӣ аз «What time is check-in?» ва барои наҳорӣ аз «Is breakfast included?» истифода баред.",
        listOf("What time is check-in?", "Is breakfast included?", "I'd like to rent an apartment."),
    ),
    exercises = listOf(
        Exercise("et_e24_1", ExerciseType.MULTIPLE_CHOICE, "«Hostel» чӣ маъно дорад?", "Hostel = ...", listOf("Мезбон", "Хостел", "Квартира", "Шарҳ"), "Хостел", 1, "Hostel — Хостел"),
        Exercise("et_e24_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Is breakfast _____?", listOf("rented", "included", "checked", "reviewed"), "included", 1, "breakfast included"),
        Exercise("et_e24_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("What time is check-out breakfast?", "What time is check-in?", "What time is hostel Airbnb?", "What time is rent review?"), "What time is check-in?", 1, "What time is check-in?"),
        Exercise("et_e24_4", ExerciseType.TYPE_ANSWER, "«Мезбон»-ро ба англисӣ нависед:", "Мезбон = ?", null, "Host", null, "Host — Мезбон"),
        Exercise("et_e24_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Rent" to "Иҷора", "Apartment" to "Квартира", "Airbnb" to "Эйрбиэнби", "Review" to "Шарҳ")),
        Exercise("et_e24_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Is breakfast included", null, "Is breakfast included", words = listOf("included", "breakfast", "Is")),
        Exercise("et_e24_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: When do I leave?\nHost: _____", null, listOf("Check-in is at eleven.", "Check-out is at eleven.", "The hostel is breakfast."), "Check-out is at eleven.", 1, "Check-out"),
        Exercise("et_e24_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Re-view", listOf("Review", "Rent", "Host", "Apartment"), "Review", 0, "Review — Шарҳ"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 7 · САЁҲАТИ ФАРҲАНГӢ (Cultural Travel)
// ═══════════════════════════════════════════════════

internal val etM7L1 = Lesson(
    id = "et_m7_l1", moduleId = "et_m7",
    title = "Маъбадҳо ва масҷидҳо", description = "Намоз, либос ва эҳтиром",
    emoji = "\uD83D\uDD4C", orderIndex = 0,
    dialogue = Dialogue(
        "Дар ҷойи муқаддас",
        listOf(
            DialogueLine("Firuz", "Do I need to cover my head? Is photography allowed?", "Бояд сарамро пӯшонам? Аксбардорӣ иҷозат аст?"),
            DialogueLine("Guide", "In the mosque, remove your shoes. The temple has a strict dress code.", "Дар масҷид пойафзол кашед. Дар маъбад либоси сахт муносиб аст."),
            DialogueLine("Firuz", "I will respect prayer time in the church.", "Ман вақти намозро дар калисо эҳтиром мекунам."),
            DialogueLine("Guide", "This place is holy — please speak quietly.", "Ин ҷо муқаддас аст — лутфан оҳиста гап занед."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w25_1", "Temple", "Маъбад", "Tem-ple", "Visit a Buddhist temple", "Ба маъбади будоӣ равед", "et_m7_l1"),
        WordItem("et_w25_2", "Mosque", "Масҷид", "Mosque", "The mosque is beautiful", "Масҷид зебо аст", "et_m7_l1"),
        WordItem("et_w25_3", "Church", "Калисо", "Church", "An old church", "Калисои қадимӣ", "et_m7_l1"),
        WordItem("et_w25_4", "Prayer", "Намоз", "Pray-er", "Prayer time", "Вақти намоз", "et_m7_l1"),
        WordItem("et_w25_5", "Holy", "Муқаддас", "Ho-ly", "A holy place", "Ҷои муқаддас", "et_m7_l1"),
        WordItem("et_w25_6", "Remove shoes", "Пойафзол кашидан", "Re-move shoes", "Please remove your shoes", "Лутфан пойафзол кашед", "et_m7_l1"),
        WordItem("et_w25_7", "Dress code", "Либоси муносиб", "Dress code", "Follow the dress code", "Аз либоси муносиб пайравӣ кунед", "et_m7_l1"),
        WordItem("et_w25_8", "Respect", "Эҳтиром", "Re-spect", "Show respect", "Эҳтиром нишон диҳед", "et_m7_l1"),
    ),
    grammarTip = GrammarTip(
        "Do I need to cover my head? / Is photography allowed?",
        "Барои либос аз «Do I need to cover my head?» ва барои акс аз «Is photography allowed?» истифода баред.",
        listOf("Do I need to cover my head?", "Is photography allowed?", "Please remove your shoes."),
    ),
    exercises = listOf(
        Exercise("et_e25_1", ExerciseType.MULTIPLE_CHOICE, "«Mosque» чӣ маъно дорад?", "Mosque = ...", listOf("Калисо", "Масҷид", "Маъбад", "Намоз"), "Масҷид", 1, "Mosque — Масҷид"),
        Exercise("et_e25_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Is photography _____?", listOf("holy", "allowed", "respect", "dress"), "allowed", 1, "photography allowed"),
        Exercise("et_e25_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Do I need to cover my shoes?", "Do I need to cover my head?", "Do I need prayer church?", "Do I remove holy temple?"), "Do I need to cover my head?", 1, "cover my head"),
        Exercise("et_e25_4", ExerciseType.TYPE_ANSWER, "«Эҳтиром»-ро ба англисӣ нависед:", "Эҳтиром = ?", null, "Respect", null, "Respect — Эҳтиром"),
        Exercise("et_e25_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Temple" to "Маъбад", "Church" to "Калисо", "Prayer" to "Намоз", "Holy" to "Муқаддас")),
        Exercise("et_e25_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Please remove your shoes", null, "Please remove your shoes", words = listOf("shoes", "your", "remove", "Please")),
        Exercise("et_e25_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Can I take photos?\nGuide: _____", null, listOf("The dress is holy.", "Only outside, not inside.", "Your prayer is a mosque."), "Only outside, not inside.", 1, "Photography"),
        Exercise("et_e25_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Dress code", listOf("Respect", "Dress code", "Temple", "Mosque"), "Dress code", 1, "Dress code — Либоси муносиб"),
    ),
)

internal val etM7L2 = Lesson(
    id = "et_m7_l2", moduleId = "et_m7",
    title = "Фестивалҳо", description = "Ҷашн, парад ва оташбозӣ",
    emoji = "\uD83C\uDF89", orderIndex = 1,
    dialogue = Dialogue(
        "Фестивал",
        listOf(
            DialogueLine("Anna", "When is the festival? What's the tradition behind the dance?", "Фестивал кай аст? Анъанаи рақс чист?"),
            DialogueLine("Firuz", "People celebrate with costumes and a parade in the street.", "Одамон бо либос ва парад дар кӯча ҷашн мегиранд."),
            DialogueLine("Anna", "Will there be fireworks at night?", "Шабона оташбозӣ ҳаст?"),
            DialogueLine("Firuz", "Yes, it's a national holiday with live music.", "Ҳа, ин иди миллӣ бо мусиқии зинда аст."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w26_1", "Festival", "Фестивал", "Fes-ti-val", "A music festival", "Фестивали мусиқӣ", "et_m7_l2"),
        WordItem("et_w26_2", "Celebrate", "Ҷашн гирифтан", "Cel-e-brate", "We celebrate together", "Мо якҷоя ҷашн мегирем", "et_m7_l2"),
        WordItem("et_w26_3", "Tradition", "Анъана", "Tra-di-tion", "Local tradition", "Анъанаи маҳаллӣ", "et_m7_l2"),
        WordItem("et_w26_4", "Costume", "Либос", "Cos-tume", "Traditional costume", "Либоси анъанавӣ", "et_m7_l2"),
        WordItem("et_w26_5", "Parade", "Парад", "Pa-rade", "Watch the parade", "Парадро тамошо кунед", "et_m7_l2"),
        WordItem("et_w26_6", "Fireworks", "Оташбозӣ", "Fire-works", "Fireworks at midnight", "Оташбозӣ дар нимаи шаб", "et_m7_l2"),
        WordItem("et_w26_7", "Holiday", "Ид", "Hol-i-day", "Public holiday", "Иди расмӣ", "et_m7_l2"),
        WordItem("et_w26_8", "Dance", "Рақс", "Dance", "Street dance", "Рақси кӯча", "et_m7_l2"),
    ),
    grammarTip = GrammarTip(
        "When is the festival? / What's the tradition behind...?",
        "Барои вақт аз «When is the festival?» ва барои анъана аз «What's the tradition behind...?» истифода баред.",
        listOf("When is the festival?", "What's the tradition behind this dance?", "We celebrate every spring."),
    ),
    exercises = listOf(
        Exercise("et_e26_1", ExerciseType.MULTIPLE_CHOICE, "«Fireworks» чӣ маъно дорад?", "Fireworks = ...", listOf("Парад", "Оташбозӣ", "Ид", "Рақс"), "Оташбозӣ", 1, "Fireworks — Оташбозӣ"),
        Exercise("et_e26_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "When is the _____?", listOf("tradition", "holiday", "festival", "costume"), "festival", 2, "When is the festival"),
        Exercise("et_e26_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("What's the tradition behind my holiday?", "What's the tradition behind this dance?", "What's the parade behind fireworks?", "What's the costume behind celebration?"), "What's the tradition behind this dance?", 1, "tradition behind"),
        Exercise("et_e26_4", ExerciseType.TYPE_ANSWER, "«Ҷашн гирифтан»-ро ба англисӣ нависед:", "...", null, "Celebrate", null, "Celebrate"),
        Exercise("et_e26_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Festival" to "Фестивал", "Parade" to "Парад", "Holiday" to "Ид", "Dance" to "Рақс")),
        Exercise("et_e26_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "We celebrate with fireworks", null, "We celebrate...", words = listOf("fireworks", "with", "celebrate", "We")),
        Exercise("et_e26_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Is there a parade?\nAnna: _____", null, listOf("The festival is a costume.", "Yes, at ten in the morning.", "The tradition is holiday."), "Yes, at ten in the morning.", 1, "Parade"),
        Exercise("et_e26_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Tra-di-tion", listOf("Tradition", "Celebrate", "Festival", "Costume"), "Tradition", 0, "Tradition — Анъана"),
    ),
)

internal val etM7L3 = Lesson(
    id = "et_m7_l3", moduleId = "et_m7",
    title = "Хӯроки маҳаллӣ", description = "Таоми хос, масолеҳ ва чашидан",
    emoji = "\uD83C\uDF72", orderIndex = 2,
    dialogue = Dialogue(
        "Дар ошхона",
        listOf(
            DialogueLine("Firuz", "What's the local specialty? How is this dish made?", "Таоми хоси маҳаллӣ чист? Ин ғизо чӣ тавр пухта мешавад?"),
            DialogueLine("Chef", "It's a traditional recipe with sweet and sour ingredients.", "Ин рецепти анъанавӣ бо масолаҳои ширин ва турш аст."),
            DialogueLine("Firuz", "Is it bitter? I don't like very bitter food.", "Талх аст? Хӯроки хеле талхро дӯст намедорам."),
            DialogueLine("Chef", "No, taste it — you can cook the same at home.", "Не, чашед — дар хона ҳаминро пухта метавонед."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w27_1", "Traditional", "Анъанавӣ", "Tra-di-tion-al", "Traditional food", "Хӯроки анъанавӣ", "et_m7_l3"),
        WordItem("et_w27_2", "Recipe", "Рецепт", "Rec-i-pe", "A family recipe", "Рецепти хонаводагӣ", "et_m7_l3"),
        WordItem("et_w27_3", "Ingredient", "Масолеҳ", "In-gre-di-ent", "Fresh ingredients", "Масолаҳои тоза", "et_m7_l3"),
        WordItem("et_w27_4", "Cook", "Пухтан", "Cook", "Learn to cook", "Пухтанро омӯзед", "et_m7_l3"),
        WordItem("et_w27_5", "Taste", "Чашидан", "Taste", "Taste before you buy", "Пеш аз харид чашед", "et_m7_l3"),
        WordItem("et_w27_6", "Sweet", "Ширин", "Sweet", "Too sweet", "Хеле ширин", "et_m7_l3"),
        WordItem("et_w27_7", "Sour", "Турш", "Sour", "Sour lemon", "Лимӯи турш", "et_m7_l3"),
        WordItem("et_w27_8", "Bitter", "Талх", "Bit-ter", "Bitter medicine", "Дори талх", "et_m7_l3"),
    ),
    grammarTip = GrammarTip(
        "What's the local specialty? / How is this made?",
        "Барои тахсиси маҳаллӣ аз «What's the local specialty?» ва барои тарзи омодагӣ аз «How is this made?» истифода баред.",
        listOf("What's the local specialty?", "How is this made?", "It tastes sweet and sour."),
    ),
    exercises = listOf(
        Exercise("et_e27_1", ExerciseType.MULTIPLE_CHOICE, "«Ingredient» чӣ маъно дорад?", "Ingredient = ...", listOf("Рецепт", "Масолеҳ", "Талх", "Ширин"), "Масолеҳ", 1, "Ingredient — Масолеҳ"),
        Exercise("et_e27_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "What's the local _____?", listOf("taste", "bitter", "specialty", "sour"), "specialty", 2, "local specialty"),
        Exercise("et_e27_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("How is this recipe made?", "How is this dish made?", "How is cook traditional?", "How is sweet ingredient?"), "How is this dish made?", 1, "How is this made?"),
        Exercise("et_e27_4", ExerciseType.TYPE_ANSWER, "«Турш»-ро ба англисӣ нависед:", "Турш = ?", null, "Sour", null, "Sour — Турш"),
        Exercise("et_e27_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Traditional" to "Анъанавӣ", "Recipe" to "Рецепт", "Cook" to "Пухтан", "Taste" to "Чашидан")),
        Exercise("et_e27_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "It tastes sweet and sour", null, "sweet and sour", words = listOf("sour", "and", "sweet", "tastes", "It")),
        Exercise("et_e27_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Is it spicy?\nChef: _____", null, listOf("The bitter is sweet.", "It's more sour than spicy.", "The recipe is traditional."), "It's more sour than spicy.", 1, "Sour"),
        Exercise("et_e27_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Bit-ter", listOf("Sweet", "Bitter", "Sour", "Cook"), "Bitter", 1, "Bitter — Талх"),
    ),
)

internal val etM7L4 = Lesson(
    id = "et_m7_l4", moduleId = "et_m7",
    title = "Одоби маҳаллӣ", description = "Адаб, урф ва имо",
    emoji = "\uD83E\uDD1D", orderIndex = 3,
    dialogue = Dialogue(
        "Одоб дар сафар",
        listOf(
            DialogueLine("Guide", "Is it polite to tip here? Some customs are different.", "Ин ҷо инъом додан боадабона аст? Баъзе урфҳо фарқ мекунанд."),
            DialogueLine("Firuz", "I will bow if a handshake is not appropriate.", "Агар дастдиҳӣ мувофиқ набошад, хам мешавам."),
            DialogueLine("Guide", "A small gift can be taboo in some places — ask first.", "Тӯҳфаи хурд дар баъзе ҷойҳо мамнӯъ аст — аввал бипурсед."),
            DialogueLine("Firuz", "I'll use polite gestures and watch local people.", "Имои боадабона истифода мекунам ва ба мардуми маҳаллӣ нигоҳ мекунам."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w28_1", "Polite", "Боадабона", "Po-lite", "Be polite", "Боадабона бошед", "et_m7_l4"),
        WordItem("et_w28_2", "Rude", "Бесавод", "Rude", "Don't be rude", "Бесавод набошед", "et_m7_l4"),
        WordItem("et_w28_3", "Custom", "Урф", "Cus-tom", "Local custom", "Урфи маҳаллӣ", "et_m7_l4"),
        WordItem("et_w28_4", "Gesture", "Имо", "Ges-ture", "A polite gesture", "Имои боадабона", "et_m7_l4"),
        WordItem("et_w28_5", "Bow", "Хам шудан", "Bow", "Bow slightly", "Каме хам шавед", "et_m7_l4"),
        WordItem("et_w28_6", "Handshake", "Дастдиҳӣ", "Hand-shake", "A firm handshake", "Дастдиҳии қавӣ", "et_m7_l4"),
        WordItem("et_w28_7", "Gift", "Тӯҳфа", "Gift", "Bring a small gift", "Тӯҳфаи хурд биёред", "et_m7_l4"),
        WordItem("et_w28_8", "Taboo", "Мамнӯъ", "Ta-boo", "It is taboo here", "Ин ҷо мамнӯъ аст", "et_m7_l4"),
    ),
    grammarTip = GrammarTip(
        "Is it polite to...? / Should I tip?",
        "Барои одоб аз «Is it polite to...?» ва барои инъом аз «Should I tip?» истифода баред.",
        listOf("Is it polite to point?", "Should I tip in this restaurant?", "Follow local customs."),
    ),
    exercises = listOf(
        Exercise("et_e28_1", ExerciseType.MULTIPLE_CHOICE, "«Taboo» чӣ маъно дорад?", "Taboo = ...", listOf("Урф", "Мамнӯъ", "Тӯҳфа", "Имо"), "Мамнӯъ", 1, "Taboo — Мамнӯъ"),
        Exercise("et_e28_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Should I _____ the waiter?", listOf("gift", "taboo", "tip", "bow"), "tip", 2, "Should I tip"),
        Exercise("et_e28_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Is it polite to be rude?", "Is it polite to speak loudly?", "Is it polite custom gesture?", "Is handshake taboo polite?"), "Is it polite to speak loudly?", 1, "Is it polite to..."),
        Exercise("et_e28_4", ExerciseType.TYPE_ANSWER, "«Дастдиҳӣ»-ро ба англисӣ нависед:", "Дастдиҳӣ = ?", null, "Handshake", null, "Handshake — Дастдиҳӣ"),
        Exercise("et_e28_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Polite" to "Боадабона", "Rude" to "Бесавод", "Custom" to "Урф", "Gesture" to "Имо")),
        Exercise("et_e28_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Follow local customs", null, "Follow local customs", words = listOf("customs", "local", "Follow")),
        Exercise("et_e28_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Can I give this gift?\nGuide: _____", null, listOf("It is always rude.", "Ask first — it can be taboo.", "The bow is polite."), "Ask first — it can be taboo.", 1, "Taboo"),
        Exercise("et_e28_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Hand-shake", listOf("Bow", "Handshake", "Gift", "Polite"), "Handshake", 1, "Handshake — Дастдиҳӣ"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 8 · БЕХАТАРӢ (Safety & Security)
// ═══════════════════════════════════════════════════

internal val etM8L1 = Lesson(
    id = "et_m8_l1", moduleId = "et_m8",
    title = "Бехатарии шахсӣ", description = "Хатар, эҳтиёт ва фиреб",
    emoji = "\uD83D\uDD12", orderIndex = 0,
    dialogue = Dialogue(
        "Дар шаҳр",
        listOf(
            DialogueLine("Anna", "Is this area safe at night?", "Ин ноҳия шабона бехатар аст?"),
            DialogueLine("Firuz", "Watch out for pickpockets near the station. I stay aware.", "Аз ҷебтарошҳо назди истгоҳ эҳтиёт кунед. Ман огоҳ мемонам."),
            DialogueLine("Anna", "There was an online scam about cheap tickets — avoid it.", "Дар бораи чиптаҳои арзон фиреби онлайн буд — канора гиред."),
            DialogueLine("Firuz", "I only trust official offices and careful plans.", "Ман танҳо ба дафтарҳои расмӣ боварӣ мекунам ва нақшаҳои эҳтиёткорона."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w29_1", "Safe", "Бехатар", "Safe", "Stay safe", "Бехатар бимонед", "et_m8_l1"),
        WordItem("et_w29_2", "Danger", "Хатар", "Dan-ger", "There is danger", "Хатар ҳаст", "et_m8_l1"),
        WordItem("et_w29_3", "Careful", "Эҳтиёт", "Care-ful", "Be careful", "Эҳтиёт бошед", "et_m8_l1"),
        WordItem("et_w29_4", "Avoid", "Канорагирӣ", "A-void", "Avoid dark streets", "Аз кӯчаҳои торик канора шавед", "et_m8_l1"),
        WordItem("et_w29_5", "Scam", "Фиреб", "Scam", "A tourist scam", "Фиреби сайёҳон", "et_m8_l1"),
        WordItem("et_w29_6", "Pickpocket", "Ҷебтарош", "Pick-pocket", "Beware of pickpockets", "Аз ҷебтарошҳо ҳушдор", "et_m8_l1"),
        WordItem("et_w29_7", "Trust", "Боварӣ", "Trust", "Don't trust strangers", "Ба бегонаҳо боварӣ накунед", "et_m8_l1"),
        WordItem("et_w29_8", "Aware", "Огоҳ", "A-ware", "Stay aware", "Огоҳ бимонед", "et_m8_l1"),
    ),
    grammarTip = GrammarTip(
        "Is this area safe? / Watch out for...",
        "Барои бехатарӣ аз «Is this area safe?» ва барои огоҳӣ аз «Watch out for...» истифода баред.",
        listOf("Is this area safe at night?", "Watch out for pickpockets.", "Avoid empty streets."),
    ),
    exercises = listOf(
        Exercise("et_e29_1", ExerciseType.MULTIPLE_CHOICE, "«Pickpocket» чӣ маъно дорад?", "Pickpocket = ...", listOf("Фиреб", "Ҷебтарош", "Хатар", "Эҳтиёт"), "Ҷебтарош", 1, "Pickpocket — Ҷебтарош"),
        Exercise("et_e29_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Watch out for _____.", listOf("safe", "trust", "pickpockets", "aware"), "pickpockets", 2, "Watch out for pickpockets"),
        Exercise("et_e29_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Is this area danger?", "Is this area safe?", "Is this scam aware?", "Is this pickpocket careful?"), "Is this area safe?", 1, "Is this area safe?"),
        Exercise("et_e29_4", ExerciseType.TYPE_ANSWER, "«Фиреб (схема)»-ро ба англисӣ нависед:", "...", null, "Scam", null, "Scam — Фиреб"),
        Exercise("et_e29_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Safe" to "Бехатар", "Danger" to "Хатар", "Careful" to "Эҳтиёт", "Avoid" to "Канорагирӣ")),
        Exercise("et_e29_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Stay aware of your bags", null, "Stay aware", words = listOf("bags", "your", "of", "aware", "Stay")),
        Exercise("et_e29_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Is it dangerous?\nFiruz: _____", null, listOf("Trust the scam.", "At night, yes — be careful.", "The area is pickpocket."), "At night, yes — be careful.", 1, "Careful"),
        Exercise("et_e29_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A A-ware", listOf("Aware", "Trust", "Scam", "Safe"), "Aware", 0, "Aware — Огоҳ"),
    ),
)

internal val etM8L2 = Lesson(
    id = "et_m8_l2", moduleId = "et_m8",
    title = "Гум шудан", description = "GPS, самт ва нишона",
    emoji = "\uD83E\uDDED", orderIndex = 1,
    dialogue = Dialogue(
        "Гум шудан",
        listOf(
            DialogueLine("Firuz", "I'm lost. Can you help me? I need the direction to the station.", "Ман гум шудам. Метавонед кӯмак кунед? Ба самти истгоҳ лозим аст."),
            DialogueLine("Local", "Look for that landmark — the tall clock tower.", "Ба ин нишона нигоҳ кунед — барҷи соати баланд."),
            DialogueLine("Firuz", "My GPS says turn left, but the signal is weak.", "Ҷипиэс мегӯяд чап кунед, аммо сигнал слаб аст."),
            DialogueLine("Local", "Walk straight; the hotel is nearby. You can return the same way.", "Ростро равед; меҳмонхона наздик аст. Ҳамин роҳ бозгаштан метавонед."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w30_1", "Lost", "Гумшуда", "Lost", "I am lost", "Ман гум шудам", "et_m8_l2"),
        WordItem("et_w30_2", "Direction", "Самт", "Di-rec-tion", "Which direction?", "Кадом самт?", "et_m8_l2"),
        WordItem("et_w30_3", "Landmark", "Нишона", "Land-mark", "A famous landmark", "Нишонаи шинохта", "et_m8_l2"),
        WordItem("et_w30_4", "GPS", "Ҷипиэс", "G-P-S", "Use GPS on your phone", "Ҷипиэсро дар телефон истифода баред", "et_m8_l2"),
        WordItem("et_w30_5", "Navigate", "Роҳёбӣ", "Nav-i-gate", "Navigate with a map", "Бо харита роҳёбӣ кунед", "et_m8_l2"),
        WordItem("et_w30_6", "Ask", "Пурсидан", "Ask", "Ask a local person", "Аз шаҳрванди маҳаллӣ бипурсед", "et_m8_l2"),
        WordItem("et_w30_7", "Nearby", "Наздик", "Near-by", "The cafe is nearby", "Кафе наздик аст", "et_m8_l2"),
        WordItem("et_w30_8", "Return", "Бозгаштан", "Re-turn", "Return to the hotel", "Ба меҳмонхона бозгардед", "et_m8_l2"),
    ),
    grammarTip = GrammarTip(
        "I'm lost. Can you help me? / How do I get back to...?",
        "Барои кӯмак аз «I'm lost. Can you help me?» ва барои бозгашт аз «How do I get back to...?» истифода баред.",
        listOf("I'm lost. Can you help me?", "How do I get back to the hotel?", "Is it nearby?"),
    ),
    exercises = listOf(
        Exercise("et_e30_1", ExerciseType.MULTIPLE_CHOICE, "«Landmark» чӣ маъно дорад?", "Landmark = ...", listOf("GPS", "Нишона", "Самт", "Гумшуда"), "Нишона", 1, "Landmark — Нишона"),
        Exercise("et_e30_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I'm _____. Can you help me?", listOf("nearby", "lost", "return", "navigate"), "lost", 1, "I'm lost"),
        Exercise("et_e30_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("How do I get back to the GPS?", "How do I get back to the hotel?", "How do I lost landmark?", "How do I navigate ask?"), "How do I get back to the hotel?", 1, "get back to"),
        Exercise("et_e30_4", ExerciseType.TYPE_ANSWER, "«Роҳёбӣ»-ро ба англисӣ нависед:", "...", null, "Navigate", null, "Navigate"),
        Exercise("et_e30_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Direction" to "Самт", "GPS" to "Ҷипиэс", "Ask" to "Пурсидан", "Nearby" to "Наздик")),
        Exercise("et_e30_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I am lost can you help me", null, "I'm lost. Can you help me", words = listOf("me", "help", "you", "Can", "lost", "am", "I")),
        Exercise("et_e30_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Where is the museum?\nLocal: _____", null, listOf("Your GPS is lost.", "Go straight — it is nearby.", "The landmark is direction."), "Go straight — it is nearby.", 1, "Nearby"),
        Exercise("et_e30_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Nav-i-gate", listOf("Navigate", "Return", "Lost", "Direction"), "Navigate", 0, "Navigate — Роҳёбӣ"),
    ),
)

internal val etM8L3 = Lesson(
    id = "et_m8_l3", moduleId = "et_m8",
    title = "Ҳодисаҳо", description = "Ҷароҳат, суғурта ва гузориш",
    emoji = "\uD83D\uDE91", orderIndex = 2,
    dialogue = Dialogue(
        "Дар ҳолати фавқулодда",
        listOf(
            DialogueLine("Firuz", "There's been an accident — someone needs an ambulance!", "Ҳодиса рӯй дод — ба касе таъҷилӣ лозим аст!"),
            DialogueLine("Bystander", "I saw it. I can be a witness for your report.", "Ман дидам. Барои гузориши шумо шоҳид мешавам."),
            DialogueLine("Firuz", "I have travel insurance. How do I file a claim?", "Ман суғуртаи саёҳатӣ дорам. Чӣ тавр даъво пешниҳод кунам?"),
            DialogueLine("Officer", "Go to the hospital first for any injury, then to the police.", "Аввал ба беморхона барои ҷароҳат, баъд ба полис равед."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w31_1", "Accident", "Ҳодиса", "Ac-ci-dent", "A car accident", "Ҳодисаи мошин", "et_m8_l3"),
        WordItem("et_w31_2", "Injury", "Ҷароҳат", "In-ju-ry", "A serious injury", "Ҷароҳати ҷиддӣ", "et_m8_l3"),
        WordItem("et_w31_3", "Ambulance", "Таъҷилӣ", "Am-bu-lance", "Call an ambulance", "Ба таъҷилӣ занг занед", "et_m8_l3"),
        WordItem("et_w31_4", "Insurance", "Суғурта", "In-sur-ance", "Travel insurance", "Суғуртаи саёҳат", "et_m8_l3"),
        WordItem("et_w31_5", "Claim", "Даъво", "Claim", "File an insurance claim", "Даъвои суғуртавӣ пешниҳод кунед", "et_m8_l3"),
        WordItem("et_w31_6", "Witness", "Шоҳид", "Wit-ness", "A witness statement", "Изҳори шоҳид", "et_m8_l3"),
        WordItem("et_w31_7", "Report", "Гузориш", "Re-port", "Police report", "Гузориши полис", "et_m8_l3"),
        WordItem("et_w31_8", "Hospital", "Беморхона", "Hos-pi-tal", "Take me to the hospital", "Маро ба беморхона баред", "et_m8_l3"),
    ),
    grammarTip = GrammarTip(
        "There's been an accident / I need to file a report",
        "Барои ҳодиса аз «There's been an accident» ва барои гузориш аз «I need to file a report» истифода баред.",
        listOf("There's been an accident.", "I need to file a report.", "I have travel insurance."),
    ),
    exercises = listOf(
        Exercise("et_e31_1", ExerciseType.MULTIPLE_CHOICE, "«Ambulance» чӣ маъно дорад?", "Ambulance = ...", listOf("Беморхона", "Таъҷилӣ", "Ҷароҳат", "Даъво"), "Таъҷилӣ", 1, "Ambulance — Таъҷилӣ"),
        Exercise("et_e31_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I need to file a _____.", listOf("injury", "witness", "claim", "hospital"), "claim", 2, "file a claim"),
        Exercise("et_e31_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("There has been insurance", "There's been an accident", "There is report witness", "There accident hospital"), "There's been an accident", 1, "There's been an accident"),
        Exercise("et_e31_4", ExerciseType.TYPE_ANSWER, "«Шоҳид»-ро ба англисӣ нависед:", "Шоҳид = ?", null, "Witness", null, "Witness — Шоҳид"),
        Exercise("et_e31_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Accident" to "Ҳодиса", "Injury" to "Ҷароҳат", "Insurance" to "Суғурта", "Report" to "Гузориш")),
        Exercise("et_e31_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Call an ambulance now", null, "Call an ambulance", words = listOf("now", "ambulance", "an", "Call")),
        Exercise("et_e31_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Someone is hurt!\nBystander: _____", null, listOf("File a claim.", "I'll call an ambulance.", "The witness is insurance."), "I'll call an ambulance.", 1, "Ambulance"),
        Exercise("et_e31_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A In-sur-ance", listOf("Insurance", "Claim", "Accident", "Hospital"), "Insurance", 0, "Insurance — Суғурта"),
    ),
)

internal val etM8L4 = Lesson(
    id = "et_m8_l4", moduleId = "et_m8",
    title = "Сафорат", description = "Виза, паспорт ва вохӯрӣ",
    emoji = "\uD83C\uDFDB\uFE0F", orderIndex = 3,
    dialogue = Dialogue(
        "Дар сафорат",
        listOf(
            DialogueLine("Firuz", "I've lost my passport. I need to replace it and extend my visa.", "Паспортамро гум кардам. Бояд иваз кунам ва визаро дароз кунам."),
            DialogueLine("Officer", "Bring all documents to the consulate. Book an appointment online.", "Ҳамаи ҳуҷҷатҳоро ба консулгарӣ биёред. Вохӯрӣро онлайн захира кунед."),
            DialogueLine("Firuz", "The embassy opens at nine. Is my photo OK?", "Сафорат дар соати нӯҳ кушода мешавад. Акси ман мувофиқ аст?"),
            DialogueLine("Officer", "Yes. Fill this form for a temporary passport.", "Ҳа. Ин варақаро барои паспорти муваққатӣ пур кунед."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w32_1", "Embassy", "Сафорат", "Em-bas-sy", "Go to the embassy", "Ба сафорат равед", "et_m8_l4"),
        WordItem("et_w32_2", "Consulate", "Консулгарӣ", "Con-su-late", "The consulate is closed", "Консулгарӣ пӯшид аст", "et_m8_l4"),
        WordItem("et_w32_3", "Visa", "Виза", "Vi-sa", "Extend your visa", "Визаро дароз кунед", "et_m8_l4"),
        WordItem("et_w32_4", "Passport", "Паспорт", "Pass-port", "Replace your passport", "Паспортро иваз кунед", "et_m8_l4"),
        WordItem("et_w32_5", "Extend", "Дароз кардан", "Ex-tend", "Extend my stay", "Мондани худро дароз кунед", "et_m8_l4"),
        WordItem("et_w32_6", "Replace", "Иваз кардан", "Re-place", "Replace a lost card", "Корти гумшударо иваз кунед", "et_m8_l4"),
        WordItem("et_w32_7", "Document", "Ҳуҷҷат", "Doc-u-ment", "Official documents", "Ҳуҷҷатҳои расмӣ", "et_m8_l4"),
        WordItem("et_w32_8", "Appointment", "Вохӯрӣ", "Ap-point-ment", "Make an appointment", "Вохӯрӣ гузоред", "et_m8_l4"),
    ),
    grammarTip = GrammarTip(
        "I've lost my passport / I need to extend my visa",
        "Барои гум шудан аз «I've lost my passport» ва барои виза аз «I need to extend my visa» истифода баред.",
        listOf("I've lost my passport.", "I need to extend my visa.", "Book an appointment at the embassy."),
    ),
    exercises = listOf(
        Exercise("et_e32_1", ExerciseType.MULTIPLE_CHOICE, "«Consulate» чӣ маъно дорад?", "Consulate = ...", listOf("Сафорат", "Консулгарӣ", "Виза", "Паспорт"), "Консулгарӣ", 1, "Consulate — Консулгарӣ"),
        Exercise("et_e32_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I need to _____ my visa.", listOf("lose", "replace", "extend", "embassy"), "extend", 2, "extend my visa"),
        Exercise("et_e32_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I've lost my appointment", "I've lost my passport", "I've lost my extend", "I've lost document consulate"), "I've lost my passport", 1, "I've lost my passport"),
        Exercise("et_e32_4", ExerciseType.TYPE_ANSWER, "«Вохӯрӣ (аз назди расмӣ)»-ро ба англисӣ нависед:", "...", null, "Appointment", null, "Appointment"),
        Exercise("et_e32_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Embassy" to "Сафорат", "Visa" to "Виза", "Extend" to "Дароз кардан", "Document" to "Ҳуҷҷат")),
        Exercise("et_e32_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Book an appointment online", null, "Book an appointment", words = listOf("online", "appointment", "an", "Book")),
        Exercise("et_e32_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: My visa expires soon.\nOfficer: _____", null, listOf("Replace the embassy.", "You should apply to extend it.", "Your passport is a consulate."), "You should apply to extend it.", 1, "Extend"),
        Exercise("et_e32_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Pass-port", listOf("Visa", "Passport", "Appointment", "Replace"), "Passport", 1, "Passport — Паспорт"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 9 · ХОТИРАҲОИ САФАР (Travel Memories)
// ═══════════════════════════════════════════════════

internal val etM9L1 = Lesson(
    id = "et_m9_l1", moduleId = "et_m9",
    title = "Аксбардорӣ", description = "Камера, манзара ва филтр",
    emoji = "\uD83D\uDCF8", orderIndex = 0,
    dialogue = Dialogue(
        "Акс гирифтан",
        listOf(
            DialogueLine("Firuz", "Could you take a photo of me with this landscape?", "Метавонед акси маро бо ин манзара бигиред?"),
            DialogueLine("Anna", "Sure! Try a lower angle — the light is better.", "Албатта! Кунҷи пасттарро санҷед — рӯшноӣ беҳтар аст."),
            DialogueLine("Firuz", "I'll use portrait mode and a warm filter.", "Режими портрет ва филтри гармро истифода мекунам."),
            DialogueLine("Anna", "The view is amazing from here!", "Аз ин ҷо манзара аҷоиб аст!"),
        ),
    ),
    newWords = listOf(
        WordItem("et_w33_1", "Camera", "Камера", "Cam-er-a", "Charge your camera", "Камераро пур кунед", "et_m9_l1"),
        WordItem("et_w33_2", "Photo", "Акс", "Pho-to", "Take a photo", "Акс бигиред", "et_m9_l1"),
        WordItem("et_w33_3", "Selfie", "Селфӣ", "Sel-fie", "Take a selfie", "Селфӣ бигиред", "et_m9_l1"),
        WordItem("et_w33_4", "Angle", "Кунҷ", "An-gle", "Find a good angle", "Кунҷи хуб ёбед", "et_m9_l1"),
        WordItem("et_w33_5", "Light", "Рӯшноӣ", "Light", "Natural light", "Рӯшноии табиӣ", "et_m9_l1"),
        WordItem("et_w33_6", "Landscape", "Манзара", "Land-scape", "A beautiful landscape", "Манзараи зебо", "et_m9_l1"),
        WordItem("et_w33_7", "Portrait", "Портрет", "Por-trait", "Portrait mode", "Режими портрет", "et_m9_l1"),
        WordItem("et_w33_8", "Filter", "Филтр", "Fil-ter", "Use a soft filter", "Филтри нарм истифода баред", "et_m9_l1"),
    ),
    grammarTip = GrammarTip(
        "Could you take a photo of me? / The view is amazing!",
        "Барои хоҳиш аз «Could you take a photo of me?» ва барои таассурот аз «The view is amazing!» истифода баред.",
        listOf("Could you take a photo of me?", "The view is amazing!", "Try a different angle."),
    ),
    exercises = listOf(
        Exercise("et_e33_1", ExerciseType.MULTIPLE_CHOICE, "«Landscape» чӣ маъно дорад?", "Landscape = ...", listOf("Портрет", "Манзара", "Филтр", "Селфӣ"), "Манзара", 1, "Landscape — Манзара"),
        Exercise("et_e33_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Could you take a _____ of me?", listOf("light", "filter", "photo", "angle"), "photo", 2, "take a photo"),
        Exercise("et_e33_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("The view is a camera", "The view is amazing", "The view is portrait", "The light is selfie"), "The view is amazing", 1, "The view is amazing"),
        Exercise("et_e33_4", ExerciseType.TYPE_ANSWER, "«Кунҷ»-ро ба англисӣ нависед:", "Кунҷ = ?", null, "Angle", null, "Angle — Кунҷ"),
        Exercise("et_e33_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Camera" to "Камера", "Selfie" to "Селфӣ", "Light" to "Рӯшноӣ", "Filter" to "Филтр")),
        Exercise("et_e33_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Could you take a photo of us", null, "Could you take a photo", words = listOf("us", "of", "photo", "a", "take", "you", "Could")),
        Exercise("et_e33_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Is this angle OK?\nAnna: _____", null, listOf("The filter is landscape.", "Yes, the light is perfect.", "Your selfie is portrait."), "Yes, the light is perfect.", 1, "Light"),
        Exercise("et_e33_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Land-scape", listOf("Portrait", "Landscape", "Photo", "Angle"), "Landscape", 1, "Landscape — Манзара"),
    ),
)

internal val etM9L2 = Lesson(
    id = "et_m9_l2", moduleId = "et_m9",
    title = "Блогнависӣ", description = "Ҳикоя, рейтинг ва маслиҳат",
    emoji = "\uD83D\uDCDD", orderIndex = 1,
    dialogue = Dialogue(
        "Блоги саёҳат",
        listOf(
            DialogueLine("Firuz", "I'll post a story about this trip on my blog.", "Дар бораи ин сафар ҳикоя дар блогам нашр мекунам."),
            DialogueLine("Anna", "I highly recommend this hotel — five-star rating in my review.", "Ин меҳмонхонаро қавӣ тавсия медиҳам — дар шарҳам панҷ ситора."),
            DialogueLine("Firuz", "The best part was the local experience with friendly people.", "Қисми беҳтарин таҷрибаи маҳаллӣ бо одамони дӯст буд."),
            DialogueLine("Anna", "Add a travel tip: book tickets early.", "Маслиҳат илова кунед: чиптаҳоро барвақт захира кунед."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w34_1", "Blog", "Блог", "Blog", "Read my travel blog", "Блоги саёҳати маро хонед", "et_m9_l2"),
        WordItem("et_w34_2", "Post", "Нашр", "Post", "Post online", "Онлайн нашр кунед", "et_m9_l2"),
        WordItem("et_w34_3", "Story", "Ҳикоя", "Sto-ry", "Share your story", "Ҳикояи худро мубодила кунед", "et_m9_l2"),
        WordItem("et_w34_4", "Recommend", "Тавсия", "Rec-om-mend", "I recommend this place", "Ин ҷоро тавсия медиҳам", "et_m9_l2"),
        WordItem("et_w34_5", "Experience", "Таҷриба", "Ex-pe-ri-ence", "A great experience", "Таҷрибаи аъло", "et_m9_l2"),
        WordItem("et_w34_6", "Rating", "Рейтинг", "Rat-ing", "High rating", "Рейтинги баланд", "et_m9_l2"),
        WordItem("et_w34_7", "Review", "Шарҳ", "Re-view", "Write a review", "Шарҳ нависед", "et_m9_l2"),
        WordItem("et_w34_8", "Tip", "Маслиҳат", "Tip", "A useful tip", "Маслиҳати фоиданок", "et_m9_l2"),
    ),
    grammarTip = GrammarTip(
        "I highly recommend... / The best part was...",
        "Барои тавсия аз «I highly recommend...» ва барои таассуроти асосӣ аз «The best part was...» истифода баред.",
        listOf("I highly recommend this tour.", "The best part was the food.", "I wrote a review online."),
    ),
    exercises = listOf(
        Exercise("et_e34_1", ExerciseType.MULTIPLE_CHOICE, "«Rating» чӣ маъно дорад?", "Rating = ...", listOf("Шарҳ", "Рейтинг", "Блог", "Ҳикоя"), "Рейтинг", 1, "Rating — Рейтинг"),
        Exercise("et_e34_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I highly _____ this museum.", listOf("post", "rate", "recommend", "tip"), "recommend", 2, "I highly recommend"),
        Exercise("et_e34_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("The best part was my blog", "The best part was the sunset", "The best part was rating tip", "The best story was post"), "The best part was the sunset", 1, "The best part was..."),
        Exercise("et_e34_4", ExerciseType.TYPE_ANSWER, "«Маслиҳат»-ро ба англисӣ нависед (дар маънои advice):", "...", null, "Tip", null, "Tip"),
        Exercise("et_e34_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Blog" to "Блог", "Story" to "Ҳикоя", "Experience" to "Таҷриба", "Review" to "Шарҳ")),
        Exercise("et_e34_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I highly recommend this place", null, "I highly recommend...", words = listOf("place", "this", "recommend", "highly", "I")),
        Exercise("et_e34_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: How was the tour?\nAnna: _____", null, listOf("My tip is five stars.", "The best part was the guide.", "The blog is a rating."), "The best part was the guide.", 1, "The best part was"),
        Exercise("et_e34_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Re-view", listOf("Review", "Post", "Story", "Blog"), "Review", 0, "Review — Шарҳ"),
    ),
)

internal val etM9L3 = Lesson(
    id = "et_m9_l3", moduleId = "et_m9",
    title = "Сувенирҳо", description = "Ёдгорӣ, дастӣ ва фиристодан",
    emoji = "\uD83C\uDF81", orderIndex = 2,
    dialogue = Dialogue(
        "Хариди ёдгорӣ",
        listOf(
            DialogueLine("Firuz", "Is this handmade? It looks unique and authentic.", "Дастӣ аст? Беназир ва аслӣ ба назар мерасад."),
            DialogueLine("Vendor", "Yes, and it's fragile — we can wrap it as a gift.", "Ҳа, ва шикастанӣ аст — ҳамчун тӯҳфа мепечонем."),
            DialogueLine("Firuz", "Can you ship it to Tajikistan? I need the box strong.", "Метавонед ба Тоҷикистон фиристед? Баста қавӣ лозим аст."),
            DialogueLine("Vendor", "We add padding so it won't break.", "Мо пуркунӣ илова мекунем, то нашиканад."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w35_1", "Souvenir", "Ёдгорӣ", "Sou-ve-nir", "Buy a souvenir", "Ёдгорӣ харед", "et_m9_l3"),
        WordItem("et_w35_2", "Gift", "Тӯҳфа", "Gift", "A gift for my family", "Тӯҳфа барои хонавода", "et_m9_l3"),
        WordItem("et_w35_3", "Handmade", "Дастӣ", "Hand-made", "Handmade crafts", "Корҳои дастӣ", "et_m9_l3"),
        WordItem("et_w35_4", "Unique", "Беназир", "U-nique", "A unique design", "Тарҳи беназир", "et_m9_l3"),
        WordItem("et_w35_5", "Wrap", "Печондан", "Wrap", "Wrap it nicely", "Хуб печонед", "et_m9_l3"),
        WordItem("et_w35_6", "Ship", "Фиристодан", "Ship", "Ship abroad", "Ба хориҷ фиристед", "et_m9_l3"),
        WordItem("et_w35_7", "Fragile", "Шикастанӣ", "Frag-ile", "Fragile — handle with care", "Шикастанӣ — бо эҳтиёт", "et_m9_l3"),
        WordItem("et_w35_8", "Authentic", "Аслӣ", "Au-then-tic", "Authentic product", "Маҳсули аслӣ", "et_m9_l3"),
    ),
    grammarTip = GrammarTip(
        "Can you wrap this as a gift? / Is this handmade?",
        "Барои печондан аз «Can you wrap this as a gift?» ва барои дастӣ аз «Is this handmade?» истифода баред.",
        listOf("Can you wrap this as a gift?", "Is this handmade?", "Is it authentic?"),
    ),
    exercises = listOf(
        Exercise("et_e35_1", ExerciseType.MULTIPLE_CHOICE, "«Fragile» чӣ маъно дорад?", "Fragile = ...", listOf("Аслӣ", "Шикастанӣ", "Беназир", "Дастӣ"), "Шикастанӣ", 1, "Fragile — Шикастанӣ"),
        Exercise("et_e35_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Is this _____?", listOf("wrap", "ship", "handmade", "fragile"), "handmade", 2, "Is this handmade?"),
        Exercise("et_e35_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Can you wrap this as a souvenir?", "Can you wrap this as a gift?", "Can you ship handmade authentic?", "Can you unique fragile?"), "Can you wrap this as a gift?", 1, "wrap as a gift"),
        Exercise("et_e35_4", ExerciseType.TYPE_ANSWER, "«Беназир»-ро ба англисӣ нависед:", "...", null, "Unique", null, "Unique"),
        Exercise("et_e35_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Souvenir" to "Ёдгорӣ", "Gift" to "Тӯҳфа", "Wrap" to "Печондан", "Ship" to "Фиристодан")),
        Exercise("et_e35_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "This is an authentic souvenir", null, "authentic souvenir", words = listOf("souvenir", "authentic", "an", "is", "This")),
        Exercise("et_e35_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: Will it break?\nVendor: _____", null, listOf("It is not unique.", "It's fragile — I'll wrap it well.", "The gift is handmade."), "It's fragile — I'll wrap it well.", 1, "Fragile"),
        Exercise("et_e35_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Au-then-tic", listOf("Authentic", "Handmade", "Souvenir", "Unique"), "Authentic", 0, "Authentic — Аслӣ"),
    ),
)

internal val etM9L4 = Lesson(
    id = "et_m9_l4", moduleId = "et_m9",
    title = "Видоъ", description = "Хайр, ваъда ва алоқа",
    emoji = "\uD83D\uDC4B", orderIndex = 3,
    dialogue = Dialogue(
        "Хайрбод",
        listOf(
            DialogueLine("Anna", "Thank you for everything — this trip was special.", "Ташаккур барои ҳама чиз — ин сафар махсус буд."),
            DialogueLine("Firuz", "I'll remember these days. I promise I'll return next year.", "Ин рӯзҳоро дар ёд медорам. Ваъда медиҳам, соли оянда боз мегардам."),
            DialogueLine("Anna", "I'll miss you! Stay in contact as friends.", "Ман шуморо соғӣ мекунам! Ҳамчун дӯстон дар алоқа бимонед."),
            DialogueLine("Firuz", "Goodbye! I hope we meet again.", "Хайр! Умедворам боз мулоқот мекунем."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w36_1", "Goodbye", "Хайр", "Good-bye", "Say goodbye", "Хайр бигӯед", "et_m9_l4"),
        WordItem("et_w36_2", "Thank", "Ташаккур", "Thank", "Thank you for everything", "Ташаккур барои ҳама чиз", "et_m9_l4"),
        WordItem("et_w36_3", "Remember", "Дар ёд доштан", "Re-mem-ber", "I will remember", "Ман дар ёд медорам", "et_m9_l4"),
        WordItem("et_w36_4", "Promise", "Ваъда", "Prom-ise", "I promise to return", "Ваъда медиҳам, ки боз мегардам", "et_m9_l4"),
        WordItem("et_w36_5", "Return", "Бозгаштан", "Re-turn", "Return soon", "Ба зудӣ бозгардед", "et_m9_l4"),
        WordItem("et_w36_6", "Miss", "Соғинч", "Miss", "I will miss you", "Ман шуморо соғӣ мекунам", "et_m9_l4"),
        WordItem("et_w36_7", "Contact", "Алоқа", "Con-tact", "Keep in contact", "Дар алоқа бимонед", "et_m9_l4"),
        WordItem("et_w36_8", "Friend", "Дӯст", "Friend", "My new friend", "Дӯсти нави ман", "et_m9_l4"),
    ),
    grammarTip = GrammarTip(
        "Thank you for everything / I'll definitely come back",
        "Барои миннатдорӣ аз «Thank you for everything» ва барои бозгашт аз «I'll definitely come back» истифода баред.",
        listOf("Thank you for everything.", "I'll definitely come back.", "Keep in touch!"),
    ),
    exercises = listOf(
        Exercise("et_e36_1", ExerciseType.MULTIPLE_CHOICE, "«Contact» дар ин маъно чӣ аст?", null, listOf("Дӯст", "Алоқа", "Ваъда", "Соғинч"), "Алоқа", 1, "Keep in contact"),
        Exercise("et_e36_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Thank you for _____.", listOf("friend", "miss", "everything", "goodbye"), "everything", 2, "Thank you for everything"),
        Exercise("et_e36_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I'll definitely come back tomorrow", "I'll definitely come back", "I'll promise contact goodbye", "I'll remember miss friend"), "I'll definitely come back", 1, "I'll definitely come back"),
        Exercise("et_e36_4", ExerciseType.TYPE_ANSWER, "«Ваъда»-ро ба англисӣ нависед:", "Ваъда = ?", null, "Promise", null, "Promise — Ваъда"),
        Exercise("et_e36_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Goodbye" to "Хайр", "Remember" to "Дар ёд доштан", "Return" to "Бозгаштан", "Friend" to "Дӯст")),
        Exercise("et_e36_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I will miss you my friend", null, "I will miss you", words = listOf("friend", "my", "you", "miss", "will", "I")),
        Exercise("et_e36_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: I'm leaving today.\nAnna: _____", null, listOf("Thank you for contact.", "Goodbye — stay in contact!", "I promise a miss."), "Goodbye — stay in contact!", 1, "Goodbye"),
        Exercise("et_e36_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Prom-ise", listOf("Promise", "Remember", "Return", "Thank"), "Promise", 0, "Promise — Ваъда"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 10 · ИМТИҲОНИ НИҲОӢ (Final Assessment)
// ═══════════════════════════════════════════════════

internal val etM10L1 = Lesson(
    id = "et_m10_l1", moduleId = "et_m10",
    title = "Такрори грамматика", description = "Қоидаҳо аз модули 1–9",
    emoji = "\u2705", orderIndex = 0,
    dialogue = Dialogue(
        "Машқи қавӣ",
        listOf(
            DialogueLine("Teacher", "Choose the correct form: I _____ here every summer.", "Шакли дурустро интихоб кунед: I ... here every summer."),
            DialogueLine("Firuz", "Present simple — I travel here every summer.", "Ҳозираи одӣ — I travel here every summer."),
            DialogueLine("Teacher", "Now: I _____ staying at this hotel right now.", "Акнун: I ... staying at this hotel right now."),
            DialogueLine("Firuz", "Present continuous — I am staying here now.", "Ҳозираи давомдор — I am staying here now."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w37_1", "Review", "Такрор", "Re-view", "Grammar review", "Такрори грамматика", "et_m10_l1"),
        WordItem("et_w37_2", "Pattern", "Намуна", "Pat-tern", "Useful patterns", "Намунаҳои фоиданок", "et_m10_l1"),
        WordItem("et_w37_3", "Tense", "Замон", "Tense", "Choose the tense", "Замонро интихоб кунед", "et_m10_l1"),
        WordItem("et_w37_4", "Habit", "Одат", "Hab-it", "Travel habit", "Одати сафар", "et_m10_l1"),
        WordItem("et_w37_5", "Now", "Акнун", "Now", "Right now", "Дар ҳамин лаҳза", "et_m10_l1"),
        WordItem("et_w37_6", "Correct", "Дуруст", "Cor-rect", "Correct answer", "Ҷавоби дуруст", "et_m10_l1"),
        WordItem("et_w37_7", "Rule", "Қоида", "Rule", "Remember the rule", "Қоидаро дар хотир доред", "et_m10_l1"),
        WordItem("et_w37_8", "Practice", "Машқ", "Prac-tice", "Extra practice", "Машқи иловагӣ", "et_m10_l1"),
    ),
    grammarTip = GrammarTip(
        "Mixed grammar from modules 1–9",
        "Ҳозираи одӣ барои одатҳо; ҳозираи давомдор барои амалҳои ҳозира дар сафар.",
        listOf("I fly every month.", "I am flying now.", "I have visited three countries."),
    ),
    exercises = listOf(
        Exercise("et_e37_1", ExerciseType.MULTIPLE_CHOICE, "Кадом ҷумла дуруст аст?", null, listOf("She don't like travel", "She doesn't like flying", "She not like flying", "She doesn't likes fly"), "She doesn't like flying", 1, "doesn't + шакли асосӣ"),
        Exercise("et_e37_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "We _____ to the museum yesterday.", listOf("go", "went", "going", "gone"), "went", 1, "Past simple"),
        Exercise("et_e37_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I am preferring window seats", "I prefer a window seat", "I preferring window seat", "I am prefer window"), "I prefer a window seat", 1, "prefer + ном"),
        Exercise("et_e37_4", ExerciseType.TYPE_ANSWER, "Ислоҳ кунед: «He go to airport» → ?", "→ ?", null, "He goes to the airport", null, "He goes — сеюм шахс"),
        Exercise("et_e37_5", ExerciseType.MATCH_PAIRS, "Замонро бо мисол мувофиқ кунед", null, null, "", null, "Мувофиқат", pairs = listOf("Present simple" to "I travel every year", "Present continuous" to "I am checking in", "Past simple" to "I landed yesterday", "Future" to "I will book a ticket")),
        Exercise("et_e37_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "They have already boarded", null, "Present perfect", words = listOf("boarded", "already", "have", "They")),
        Exercise("et_e37_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nStaff: Your passport, please.\nFiruz: _____", null, listOf("Here is it.", "Here it is.", "Here are they."), "Here it is.", 1, "Here it is"),
        Exercise("et_e37_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Prac-tice", listOf("Practice", "Pattern", "Tense", "Rule"), "Practice", 0, "Practice — Машқ"),
    ),
)

internal val etM10L2 = Lesson(
    id = "et_m10_l2", moduleId = "et_m10",
    title = "Такрори луғат", description = "Калимаҳои калидӣ аз курс",
    emoji = "\uD83D\uDD24", orderIndex = 1,
    dialogue = Dialogue(
        "Санҷиши луғат",
        listOf(
            DialogueLine("Anna", "Match airport words with city words you learned.", "Калимаҳои фурудгоҳро бо калимаҳои шаҳр мувофиқ кунед."),
            DialogueLine("Firuz", "Gate, luggage, then hotel, map, and restaurant.", "Дарвоза, бор, баъд меҳмонхона, харита ва ресторан."),
            DialogueLine("Anna", "Use food words: combo, reservation, organic.", "Аз калимаҳои хӯрок истифода баред: маҷмӯа, захира, табиӣ."),
            DialogueLine("Firuz", "Safety words too: scam, lost, embassy.", "Калимаҳои бехатарӣ низ: фиреб, гумшуда, сафорат."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w38_1", "Vocabulary", "Луғат", "Vo-cab-u-lar-y", "Travel vocabulary", "Луғати саёҳат", "et_m10_l2"),
        WordItem("et_w38_2", "Recall", "Ба ёд овардан", "Re-call", "Recall phrases", "Ибораҳоро ба ёд оваред", "et_m10_l2"),
        WordItem("et_w38_3", "Context", "Мазмун", "Con-text", "Guess from context", "Аз мазмун пешгӯӣ кунед", "et_m10_l2"),
        WordItem("et_w38_4", "Phrase", "Ибора", "Phrase", "Useful phrases", "Ибораҳои фоиданок", "et_m10_l2"),
        WordItem("et_w38_5", "Topic", "Мавзӯъ", "Top-ic", "Switch topics", "Мавзӯъҳоро иваз кунед", "et_m10_l2"),
        WordItem("et_w38_6", "Collocation", "Ҳамҷоягӣ", "Col-lo-ca-tion", "Learn collocations", "Ҳамҷоягиҳоро омӯзед", "et_m10_l2"),
        WordItem("et_w38_7", "Mix", "Омехта", "Mix", "Mixed review", "Такрори омехта", "et_m10_l2"),
        WordItem("et_w38_8", "Review", "Такрор", "Re-view", "Vocabulary review", "Такрори луғат", "et_m10_l2"),
    ),
    grammarTip = GrammarTip(
        "Key vocabulary from all modules",
        "Калимаҳои асосии курсро дар ҷумлаҳо такрор кунед: нақлиёт, хӯрок, бехатарӣ, фарҳанг.",
        listOf("boarding pass, gate, luggage", "reservation, allergy, tip", "lost, embassy, insurance"),
    ),
    exercises = listOf(
        Exercise("et_e38_1", ExerciseType.MULTIPLE_CHOICE, "Кадом калима ба «begin» наздиктар аст?", null, listOf("Finish", "Start", "End", "Stop"), "Start", 1, "Start ≈ begin"),
        Exercise("et_e38_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I'd like to make a _____. (дар меҳмонхона)", listOf("map", "reservation", "scam", "wave"), "reservation", 1, "make a reservation"),
        Exercise("et_e38_3", ExerciseType.TRANSLATE_SENTENCE, "Мазмунро хонед ва ҷавоби дурустро интихоб кунед: «Ба ман виза лозим аст»", null, listOf("I need a visa", "I need a pizza", "I need a visa card only", "I need visa restaurant"), "I need a visa", 0, "I need a visa"),
        Exercise("et_e38_4", ExerciseType.TYPE_ANSWER, "Зидди «cheap»-ро нависед:", "...", null, "expensive", null, "expensive"),
        Exercise("et_e38_5", ExerciseType.MATCH_PAIRS, "Калимаро бо мавзӯъ мувофиқ кунед", null, null, "", null, "Гурӯҳбандӣ", pairs = listOf("Boarding pass" to "Airport", "Allergic" to "Restaurant", "Pickpocket" to "Safety", "Festival" to "Culture")),
        Exercise("et_e38_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Where is the nearest ATM", null, "nearest ATM", words = listOf("ATM", "nearest", "the", "is", "Where")),
        Exercise("et_e38_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: I forgot the word for «баста».\nAnna: _____", null, listOf("It is «stamp».", "It is «package».", "It is «post office»."), "It is «package».", 1, "package — баста"),
        Exercise("et_e38_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Vo-cab-u-lar-y", listOf("Vocabulary", "Phrase", "Topic", "Mix"), "Vocabulary", 0, "Vocabulary — Луғат"),
    ),
)

internal val etM10L3 = Lesson(
    id = "et_m10_l3", moduleId = "et_m10",
    title = "Муколамаи комплексӣ", description = "Чанд сенария дар як суҳбат",
    emoji = "\uD83D\uDCAC", orderIndex = 2,
    dialogue = Dialogue(
        "Рӯзи пурсафар",
    listOf(
            DialogueLine("Firuz", "I landed at six, took a taxi, then checked in at the hotel.", "Ман дар соати шаш фуруд омадам, такси гирифтам, баъд дар меҳмонхона сабт шудам."),
            DialogueLine("Anna", "Busy! Did you find a good place for dinner?", "Серкор! Ҷои хуб барои шом ёфтед?"),
            DialogueLine("Firuz", "Yes — I had a reservation. Tomorrow I'll visit a museum.", "Ҳа — захира доштам. Фардо ба музей меравам."),
            DialogueLine("Anna", "Perfect — you used English in real travel situations.", "Аъло — шумо англисиро дар вазъиятҳои воқеии сафар истифода бурдед."),
        ),
    ),
    newWords = listOf(
        WordItem("et_w39_1", "Complex", "Мураккаб", "Com-plex", "A complex trip", "Сафари мураккаб", "et_m10_l3"),
        WordItem("et_w39_2", "Situation", "Вазъият", "Sit-u-a-tion", "Real travel situation", "Вазъияти воқеии сафар", "et_m10_l3"),
        WordItem("et_w39_3", "Sequence", "Пайдарпайӣ", "Se-quence", "Tell events in order", "Воқеаҳоро ба тартиб нақл кунед", "et_m10_l3"),
        WordItem("et_w39_4", "Connect", "Пайваст кардан", "Con-nect", "Connect ideas", "Фикрҳоро пайваст кунед", "et_m10_l3"),
        WordItem("et_w39_5", "Detail", "Тафсилот", "De-tail", "Add details", "Тафсилот илова кунед", "et_m10_l3"),
        WordItem("et_w39_6", "Fluency", "Суфтагӣ", "Flu-en-cy", "Build fluency", "Суфтагӣ бисозед", "et_m10_l3"),
        WordItem("et_w39_7", "Scenario", "Сенария", "Sce-nar-io", "Multiple scenarios", "Чанд сенария", "et_m10_l3"),
        WordItem("et_w39_8", "Confidence", "Эътимод", "Con-fi-dence", "Speak with confidence", "Бо эътимод гап занед", "et_m10_l3"),
    ),
    grammarTip = GrammarTip(
        "Advanced mixed travel dialogue",
        "Барои суҳбати мураккаб аз пайдарпайии воқеаҳо ва гузариши мавзӯъ истифода баред.",
        listOf("First I arrived, then I took a taxi.", "After check-in, I looked for food.", "The next day I visited museums."),
    ),
    exercises = listOf(
        Exercise("et_e39_1", ExerciseType.MULTIPLE_CHOICE, "Кадом ҷумла пайдарпайии дуруст дорад?", null, listOf("I checked in then I landed", "I landed, then I took a taxi to the hotel", "I taxi landed hotel", "I hotel then landed"), "I landed, then I took a taxi to the hotel", 1, "Sequence"),
        Exercise("et_e39_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I had a _____ at the restaurant.", listOf("museum", "taxi", "reservation", "flight"), "reservation", 2, "reservation"),
        Exercise("et_e39_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Tomorrow I'll visit a taxi", "Tomorrow I'll visit a museum", "Tomorrow I'll hotel dinner", "Tomorrow I'll landed reservation"), "Tomorrow I'll visit a museum", 1, "visit a museum"),
        Exercise("et_e39_4", ExerciseType.TYPE_ANSWER, "Ба англисӣ нависед: «бо эътимод гап задан»", "...", null, "speak with confidence", null, "speak with confidence"),
        Exercise("et_e39_5", ExerciseType.MATCH_PAIRS, "Сенариро бо ҷумла мувофиқ кунед", null, null, "", null, "Фаҳм", pairs = listOf("Airport" to "Where is gate five?", "Hotel" to "What time is check-in?", "Restaurant" to "I'd like a table", "Emergency" to "Please call an ambulance")),
        Exercise("et_e39_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "First I checked in then I rested", null, "First... then...", words = listOf("rested", "I", "then", "in", "checked", "First")),
        Exercise("et_e39_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: How was your day?\nFiruz: _____", null, listOf("The fluency is complex.", "Busy — flight, taxi, hotel, dinner.", "My scenario is situation."), "Busy — flight, taxi, hotel, dinner.", 1, "Complex answer"),
        Exercise("et_e39_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Flu-en-cy", listOf("Confidence", "Fluency", "Scenario", "Detail"), "Fluency", 1, "Fluency — Суфтагӣ"),
    ),
)

internal val etM10L4 = Lesson(
    id = "et_m10_l4", moduleId = "et_m10",
    title = "Имтиҳони ниҳоӣ", description = "Омехтаи ҳамаи намудҳои машқ",
    emoji = "\uD83C\uDF93", orderIndex = 3,
    dialogue = Dialogue(
        "Охирин санҷиш",
        listOf(
            DialogueLine("Teacher", "Welcome to the final travel English test.", "Ба санҷиши ниҳоии англисии сафар хуш омадед."),
            DialogueLine("Firuz", "I'm ready for the hardest mixed questions.", "Барои саволҳои омехтаи сахт омодаам."),
            DialogueLine("Teacher", "Show grammar, vocabulary, and clear communication.", "Грамматика, луғат ва муоширати равшан нишон диҳед."),
            DialogueLine("Firuz", "Thank you — this course helped my confidence!", "Ташаккур — ин курс ба эътимоди ман кӯмак кард!"),
        ),
    ),
    newWords = listOf(
        WordItem("et_w40_1", "Final", "Ниҳоӣ", "Fi-nal", "Final test", "Санҷиши ниҳоӣ", "et_m10_l4"),
        WordItem("et_w40_2", "Challenge", "Мушкилӣ", "Chal-lenge", "The biggest challenge", "Бузургтарин мушкилӣ", "et_m10_l4"),
        WordItem("et_w40_3", "Score", "Натиҷа", "Score", "A high score", "Натиҷаи баланд", "et_m10_l4"),
        WordItem("et_w40_4", "Focus", "Тамаркуз", "Fo-cus", "Stay focused", "Тамаркуз нигоҳ доред", "et_m10_l4"),
        WordItem("et_w40_5", "Effort", "Кӯшиш", "Ef-fort", "Great effort", "Кӯшиши бузург", "et_m10_l4"),
        WordItem("et_w40_6", "Success", "Муваффақият", "Suc-cess", "Wish you success", "Барои муваффақият орзу мекунем", "et_m10_l4"),
        WordItem("et_w40_7", "Assessment", "Санҷиш", "As-sess-ment", "Complete the assessment", "Санҷишро анҷом диҳед", "et_m10_l4"),
        WordItem("et_w40_8", "Certificate", "Гувоҳӣ", "Cer-tif-i-cate", "A course certificate", "Гувоҳии курс", "et_m10_l4"),
    ),
    grammarTip = GrammarTip(
        "Hardest mixed travel skills",
        "Ин бахш ҳамаи намудҳои машқро омехта мекунад.",
        listOf("Read each question twice.", "Choose the most natural answer.", "Good luck on your trips!"),
    ),
    exercises = listOf(
        Exercise("et_e40_1", ExerciseType.MULTIPLE_CHOICE, "Кадом ҷумла табиӣтар аст?", null, listOf("Could you to help me with my luggage?", "Could you help me with my luggage?", "Could you helping me luggage?", "You could help me my luggage?"), "Could you help me with my luggage?", 1, "Could you + феъли асосӣ"),
        Exercise("et_e40_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "If I _____ you, I'd leave earlier.", listOf("am", "was", "were", "be"), "were", 2, "If I were you"),
        Exercise("et_e40_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("She suggested me to book", "She suggested booking early", "She suggested to booking", "She suggest book"), "She suggested booking early", 1, "suggest + -ing"),
        Exercise("et_e40_4", ExerciseType.TYPE_ANSWER, "Ишоракунандаи дурустро нависед: «ин чиптаҳо»", "...", null, "these tickets", null, "these + ҷамъ"),
        Exercise("et_e40_5", ExerciseType.MATCH_PAIRS, "Хато ва ислоҳ", null, null, "", null, "Мувофиқат", pairs = listOf("He don't have" to "He doesn't have", "I am agree" to "I agree", "Where is the toilet at?" to "Where is the toilet?", "Can you to tell me?" to "Can you tell me?")),
        Exercise("et_e40_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Despite the delay we arrived safely", null, "Despite + сифатнома", words = listOf("safely", "arrived", "we", "delay", "the", "Despite")),
        Exercise("et_e40_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTeacher: Ready for the last question?\nFiruz: _____", null, listOf("My certificate is focus.", "Yes — I've focused and I'm ready.", "The challenge is effort."), "Yes — I've focused and I'm ready.", 1, "Ready"),
        Exercise("et_e40_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Suc-cess", listOf("Success", "Score", "Challenge", "Final"), "Success", 0, "Success — Муваффақият"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULES 4–10
// ═══════════════════════════════════════════════════

internal val etModule4 = Module(
    id = "et_m4", courseId = "travel_english",
    title = "Хӯрок дар сафар",
    description = "Фастфуд, кафе, ресторани расмӣ ва бозори маҳаллӣ",
    emoji = "\uD83C\uDF5D", orderIndex = 3,
    lessons = listOf(etM4L1, etM4L2, etM4L3, etM4L4),
)

internal val etModule5 = Module(
    id = "et_m5", courseId = "travel_english",
    title = "Истироҳат ва завқ",
    description = "Соҳил, кӯҳнавардӣ, музей ва шаби зиндагӣ",
    emoji = "\uD83C\uDFDD\uFE0F", orderIndex = 4,
    lessons = listOf(etM5L1, etM5L2, etM5L3, etM5L4),
)

internal val etModule6 = Module(
    id = "et_m6", courseId = "travel_english",
    title = "Масъалаҳои амалӣ",
    description = "Телефон, ивази пул, почта ва иҷора",
    emoji = "\uD83D\uDD27", orderIndex = 5,
    lessons = listOf(etM6L1, etM6L2, etM6L3, etM6L4),
)

internal val etModule7 = Module(
    id = "et_m7", courseId = "travel_english",
    title = "Саёҳати фарҳангӣ",
    description = "Маъбадҳо, фестивалҳо, хӯроки маҳаллӣ ва одоб",
    emoji = "\uD83C\uDFDB\uFE0F", orderIndex = 6,
    lessons = listOf(etM7L1, etM7L2, etM7L3, etM7L4),
)

internal val etModule8 = Module(
    id = "et_m8", courseId = "travel_english",
    title = "Бехатарӣ",
    description = "Бехатарии шахсӣ, гум шудан, ҳодисаҳо ва сафорат",
    emoji = "\uD83D\uDD12", orderIndex = 7,
    lessons = listOf(etM8L1, etM8L2, etM8L3, etM8L4),
)

internal val etModule9 = Module(
    id = "et_m9", courseId = "travel_english",
    title = "Хотираҳои сафар",
    description = "Акс, блог, сувенир ва видоъ",
    emoji = "\uD83D\uDCF8", orderIndex = 8,
    lessons = listOf(etM9L1, etM9L2, etM9L3, etM9L4),
)

internal val etModule10 = Module(
    id = "et_m10", courseId = "travel_english",
    title = "Имтиҳони ниҳоӣ",
    description = "Такрор, муколамаи комплексӣ ва санҷиши ниҳоӣ",
    emoji = "\uD83C\uDFC6", orderIndex = 9,
    lessons = listOf(etM10L1, etM10L2, etM10L3, etM10L4),
)
