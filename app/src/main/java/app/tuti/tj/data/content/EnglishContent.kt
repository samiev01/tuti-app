package app.tuti.tj.data.content

// ═══════════════════════════════════════════════════
//  TOPIC 1: GREETINGS  (Салом!)
// ═══════════════════════════════════════════════════

val enGreetingsWords = listOf(
    WordItem("eg1", "Hello", "Салом", "Hel-lo", "Hello, how are you?", "Салом, чӣ ҳолед?", "en_greetings"),
    WordItem("eg2", "Good morning", "Субҳ ба хайр", "Good morn-ing", "Good morning, everyone!", "Субҳ ба хайр, ҳама!", "en_greetings"),
    WordItem("eg3", "Good evening", "Бегоҳ ба хайр", "Good eve-ning", "Good evening, sir", "Бегоҳ ба хайр, ҷаноб", "en_greetings"),
    WordItem("eg4", "Goodbye", "Хайр", "Good-bye", "Goodbye, see you tomorrow!", "Хайр, то пагоҳ!", "en_greetings"),
    WordItem("eg5", "Thank you", "Ташаккур/Раҳмат", "Thank you", "Thank you for your help", "Барои кӯмак раҳмат", "en_greetings"),
    WordItem("eg6", "Please", "Лутфан/Марҳамат", "Please", "Please sit down", "Лутфан, шинед", "en_greetings"),
    WordItem("eg7", "Yes", "Ҳа/Бале", "Yes", "Yes, I agree", "Ҳа, ман розӣ ҳастам", "en_greetings"),
    WordItem("eg8", "No", "Не/Нест", "No", "No, thank you", "Не, раҳмат", "en_greetings"),
    WordItem("eg9", "Excuse me", "Бубахшед", "Ex-cuse me", "Excuse me, where is the bus stop?", "Бубахшед, истгоҳи автобус куҷост?", "en_greetings"),
    WordItem("eg10", "How are you?", "Чӣ ҳолед?", "How are you?", "Hi! How are you?", "Салом! Чӣ ҳолед?", "en_greetings"),
)

val enGreetingsQuiz = listOf(
    QuizQuestion("egq1", "en_greetings", QuestionType.TRANSLATE, "«Hello» чӣ маъно дорад?", "Hello = ...", listOf("Хайр", "Салом", "Раҳмат", "Бубахшед"), 1, "Hello — ин Салом аст"),
    QuizQuestion("egq2", "en_greetings", QuestionType.CHOOSE_TRANSLATION, "«Раҳмат»-ро бо англисӣ чӣ тавр мегӯянд?", "Раҳмат → ?", listOf("Hello", "Goodbye", "Thank you", "Excuse me"), 2, "Раҳмат бо англисӣ — Thank you"),
    QuizQuestion("egq3", "en_greetings", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____, how are you?", listOf("Goodbye", "Hello", "Thank you", "No"), 1, "Hello — салом"),
    QuizQuestion("egq4", "en_greetings", QuestionType.TRANSLATE, "«Goodbye» чӣ маъно дорад?", "Goodbye = ...", listOf("Салом", "Раҳмат", "Хайр", "Лутфан"), 2, "Goodbye — хайр"),
    QuizQuestion("egq5", "en_greetings", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Лутфан = ?", listOf("Thank you", "Please", "Excuse me", "Hello"), 1, "Лутфан бо англисӣ — Please"),
    QuizQuestion("egq6", "en_greetings", QuestionType.CHOOSE_TRANSLATION, "«Бубахшед»-ро бо англисӣ чӣ тавр мегӯянд?", "Бубахшед → ?", listOf("Thank you", "Good morning", "Excuse me", "Please"), 2, "Бубахшед бо англисӣ — Excuse me"),
    QuizQuestion("egq7", "en_greetings", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____, I agree", listOf("No", "Yes", "Goodbye", "Excuse me"), 1, "Yes — ҳа/бале"),
    QuizQuestion("egq8", "en_greetings", QuestionType.TRANSLATE, "«Please» чӣ маъно дорад?", "Please = ...", listOf("Салом", "Хайр", "Лутфан", "Бале"), 2, "Please — лутфан/марҳамат"),
    QuizQuestion("egq9", "en_greetings", QuestionType.LISTEN, "Кадом калима дуруст аст?", "\uD83D\uDD0A Thank you", listOf("Hello", "Excuse me", "Thank you", "Please"), 2, "Thank you — раҳмат"),
    QuizQuestion("egq10", "en_greetings", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Не/Нест = ?", listOf("Yes", "No", "Hello", "Goodbye"), 1, "Не/Нест бо англисӣ — No"),
)

// ═══════════════════════════════════════════════════
//  TOPIC 2: NUMBERS  (Рақамҳо)
// ═══════════════════════════════════════════════════

val enNumbersWords = listOf(
    WordItem("en1", "One", "Як", "One", "One ticket, please", "Як билет, лутфан", "en_numbers"),
    WordItem("en2", "Two", "Ду", "Two", "Two cups of tea", "Ду пиёла чой", "en_numbers"),
    WordItem("en3", "Three", "Се", "Three", "Three days", "Се рӯз", "en_numbers"),
    WordItem("en4", "Four", "Чор", "Four", "Four people", "Чор нафар", "en_numbers"),
    WordItem("en5", "Five", "Панҷ", "Five", "Five minutes", "Панҷ дақиқа", "en_numbers"),
    WordItem("en6", "Six", "Шаш", "Six", "Six hours", "Шаш соат", "en_numbers"),
    WordItem("en7", "Seven", "Ҳафт", "Sev-en", "Seven days a week", "Ҳафт рӯзи ҳафта", "en_numbers"),
    WordItem("en8", "Eight", "Ҳашт", "Eight", "Eight o'clock", "Соати ҳашт", "en_numbers"),
    WordItem("en9", "Nine", "Нӯҳ", "Nine", "Nine in the morning", "Нӯҳи субҳ", "en_numbers"),
    WordItem("en10", "Ten", "Даҳ", "Ten", "Ten dollars", "Даҳ доллар", "en_numbers"),
)

val enNumbersQuiz = listOf(
    QuizQuestion("enq1", "en_numbers", QuestionType.TRANSLATE, "«One» чӣ маъно дорад?", "One = ...", listOf("Ду", "Се", "Як", "Чор"), 2, "One — як"),
    QuizQuestion("enq2", "en_numbers", QuestionType.CHOOSE_TRANSLATION, "«Панҷ»-ро бо англисӣ чӣ тавр мегӯянд?", "Панҷ → ?", listOf("Three", "Five", "Seven", "Nine"), 1, "Панҷ бо англисӣ — Five"),
    QuizQuestion("enq3", "en_numbers", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____ minutes", listOf("Hello", "Please", "Five", "Thank you"), 2, "Five minutes — панҷ дақиқа"),
    QuizQuestion("enq4", "en_numbers", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Ҳафт = ?", listOf("Six", "Eight", "Seven", "Nine"), 2, "Ҳафт бо англисӣ — Seven"),
    QuizQuestion("enq5", "en_numbers", QuestionType.TRANSLATE, "«Ten» чӣ маъно дорад?", "Ten = ...", listOf("Нӯҳ", "Ҳашт", "Шаш", "Даҳ"), 3, "Ten — даҳ"),
    QuizQuestion("enq6", "en_numbers", QuestionType.LISTEN, "Кадом рақам дуруст аст?", "\uD83D\uDD0A Eight", listOf("Six", "Seven", "Eight", "Nine"), 2, "Eight — ҳашт"),
    QuizQuestion("enq7", "en_numbers", QuestionType.CHOOSE_TRANSLATION, "«Се»-ро бо англисӣ чӣ тавр мегӯянд?", "Се → ?", listOf("Two", "Three", "Four", "Five"), 1, "Се бо англисӣ — Three"),
    QuizQuestion("enq8", "en_numbers", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____ people", listOf("One", "Two", "Four", "Ten"), 2, "Four people — чор нафар"),
    QuizQuestion("enq9", "en_numbers", QuestionType.TRANSLATE, "«Six» чӣ маъно дорад?", "Six = ...", listOf("Панҷ", "Шаш", "Ҳафт", "Ҳашт"), 1, "Six — шаш"),
    QuizQuestion("enq10", "en_numbers", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Ду = ?", listOf("One", "Three", "Two", "Four"), 2, "Ду бо англисӣ — Two"),
)

// ═══════════════════════════════════════════════════
//  TOPIC 3: FOOD & DRINKS  (Хӯрок)
// ═══════════════════════════════════════════════════

val enFoodWords = listOf(
    WordItem("ef1", "Bread", "Нон", "Bread", "Give me bread, please", "Нон диҳед, лутфан", "en_food"),
    WordItem("ef2", "Water", "Об", "Wa-ter", "A glass of water", "Як стакан об", "en_food"),
    WordItem("ef3", "Tea", "Чой", "Tea", "Tea with sugar", "Чой бо қанд", "en_food"),
    WordItem("ef4", "Meat", "Гӯшт", "Meat", "Meat with rice", "Гӯшт бо биринҷ", "en_food"),
    WordItem("ef5", "Rice", "Биринҷ", "Rice", "Rice and chicken", "Биринҷ ва мурғ", "en_food"),
    WordItem("ef6", "Milk", "Шир", "Milk", "A glass of milk", "Як стакан шир", "en_food"),
    WordItem("ef7", "Sugar", "Қанд/Шакар", "Sug-ar", "Two sugars", "Ду қанд", "en_food"),
    WordItem("ef8", "Apple", "Себ", "Ap-ple", "A red apple", "Себи сурх", "en_food"),
    WordItem("ef9", "Soup", "Шӯрбо", "Soup", "Hot soup", "Шӯрбои гарм", "en_food"),
    WordItem("ef10", "Butter", "Равған", "But-ter", "Butter and bread", "Равған ва нон", "en_food"),
)

val enFoodQuiz = listOf(
    QuizQuestion("efq1", "en_food", QuestionType.TRANSLATE, "«Bread» чӣ маъно дорад?", "Bread = ...", listOf("Об", "Нон", "Чой", "Гӯшт"), 1, "Bread — нон"),
    QuizQuestion("efq2", "en_food", QuestionType.CHOOSE_TRANSLATION, "«Шир»-ро бо англисӣ чӣ тавр мегӯянд?", "Шир → ?", listOf("Water", "Tea", "Milk", "Soup"), 2, "Шир бо англисӣ — Milk"),
    QuizQuestion("efq3", "en_food", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____ with sugar", listOf("Bread", "Meat", "Tea", "Rice"), 2, "Tea with sugar — чой бо қанд"),
    QuizQuestion("efq4", "en_food", QuestionType.TRANSLATE, "«Water» чӣ маъно дорад?", "Water = ...", listOf("Нон", "Шир", "Об", "Чой"), 2, "Water — об"),
    QuizQuestion("efq5", "en_food", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Гӯшт = ?", listOf("Rice", "Meat", "Soup", "Bread"), 1, "Гӯшт бо англисӣ — Meat"),
    QuizQuestion("efq6", "en_food", QuestionType.LISTEN, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ap-ple", listOf("Bread", "Water", "Apple", "Sugar"), 2, "Apple — себ"),
    QuizQuestion("efq7", "en_food", QuestionType.CHOOSE_TRANSLATION, "«Биринҷ»-ро бо англисӣ чӣ тавр мегӯянд?", "Биринҷ → ?", listOf("Bread", "Rice", "Meat", "Soup"), 1, "Биринҷ бо англисӣ — Rice"),
    QuizQuestion("efq8", "en_food", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "Hot _____", listOf("Bread", "Apple", "Butter", "Soup"), 3, "Hot soup — шӯрбои гарм"),
    QuizQuestion("efq9", "en_food", QuestionType.TRANSLATE, "«Sugar» чӣ маъно дорад?", "Sugar = ...", listOf("Нон", "Қанд", "Себ", "Равған"), 1, "Sugar — қанд/шакар"),
    QuizQuestion("efq10", "en_food", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Равған = ?", listOf("Milk", "Sugar", "Butter", "Apple"), 2, "Равған бо англисӣ — Butter"),
)

// ═══════════════════════════════════════════════════
//  TOPIC 4: FAMILY  (Оила)
// ═══════════════════════════════════════════════════

val enFamilyWords = listOf(
    WordItem("efm1", "Mother", "Модар", "Moth-er", "My mother is kind", "Модари ман меҳрубон аст", "en_family"),
    WordItem("efm2", "Father", "Падар", "Fa-ther", "My father works a lot", "Падари ман зиёд кор мекунад", "en_family"),
    WordItem("efm3", "Brother", "Бародар", "Broth-er", "My brother is older", "Бародари ман калонтар аст", "en_family"),
    WordItem("efm4", "Sister", "Хоҳар", "Sis-ter", "My sister is a student", "Хоҳари ман донишҷӯ аст", "en_family"),
    WordItem("efm5", "Son", "Писар", "Son", "My son is five years old", "Писари ман панҷсола аст", "en_family"),
    WordItem("efm6", "Daughter", "Духтар", "Daugh-ter", "My daughter goes to school", "Духтари ман ба мактаб меравад", "en_family"),
    WordItem("efm7", "Husband", "Шавҳар", "Hus-band", "My husband is a doctor", "Шавҳари ман духтур аст", "en_family"),
    WordItem("efm8", "Wife", "Зан", "Wife", "My wife is a teacher", "Зани ман муаллим аст", "en_family"),
    WordItem("efm9", "Child", "Кӯдак", "Child", "The child is playing", "Кӯдак бозӣ мекунад", "en_family"),
    WordItem("efm10", "Family", "Оила", "Fam-i-ly", "I love my family", "Ман оилаи худро дӯст медорам", "en_family"),
)

val enFamilyQuiz = listOf(
    QuizQuestion("efmq1", "en_family", QuestionType.TRANSLATE, "«Mother» чӣ маъно дорад?", "Mother = ...", listOf("Хоҳар", "Модар", "Духтар", "Зан"), 1, "Mother — модар"),
    QuizQuestion("efmq2", "en_family", QuestionType.CHOOSE_TRANSLATION, "«Падар»-ро бо англисӣ чӣ тавр мегӯянд?", "Падар → ?", listOf("Brother", "Father", "Son", "Husband"), 1, "Падар бо англисӣ — Father"),
    QuizQuestion("efmq3", "en_family", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "My _____ is a student", listOf("father", "mother", "sister", "son"), 2, "My sister is a student — хоҳари ман донишҷӯ"),
    QuizQuestion("efmq4", "en_family", QuestionType.TRANSLATE, "«Brother» чӣ маъно дорад?", "Brother = ...", listOf("Падар", "Писар", "Бародар", "Шавҳар"), 2, "Brother — бародар"),
    QuizQuestion("efmq5", "en_family", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Духтар = ?", listOf("Son", "Daughter", "Wife", "Sister"), 1, "Духтар бо англисӣ — Daughter"),
    QuizQuestion("efmq6", "en_family", QuestionType.LISTEN, "Кадом калима дуруст аст?", "\uD83D\uDD0A Fam-i-ly", listOf("Father", "Brother", "Family", "Mother"), 2, "Family — оила"),
    QuizQuestion("efmq7", "en_family", QuestionType.CHOOSE_TRANSLATION, "«Шавҳар»-ро бо англисӣ чӣ тавр мегӯянд?", "Шавҳар → ?", listOf("Father", "Brother", "Son", "Husband"), 3, "Шавҳар бо англисӣ — Husband"),
    QuizQuestion("efmq8", "en_family", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "My _____ is five years old", listOf("daughter", "mother", "son", "wife"), 2, "My son is five years old — писари ман панҷсола"),
    QuizQuestion("efmq9", "en_family", QuestionType.TRANSLATE, "«Wife» чӣ маъно дорад?", "Wife = ...", listOf("Модар", "Хоҳар", "Зан", "Духтар"), 2, "Wife — зан"),
    QuizQuestion("efmq10", "en_family", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Кӯдак = ?", listOf("Child", "Son", "Daughter", "Brother"), 0, "Кӯдак бо англисӣ — Child"),
)

// ═══════════════════════════════════════════════════
//  TOPIC 5: CITY  (Шаҳр)
// ═══════════════════════════════════════════════════

val enCityWords = listOf(
    WordItem("ec1", "City", "Шаҳр", "Ci-ty", "I live in a big city", "Ман дар шаҳри калон зиндагӣ мекунам", "en_city"),
    WordItem("ec2", "Street", "Кӯча", "Street", "The street is long", "Кӯча дароз аст", "en_city"),
    WordItem("ec3", "Shop", "Мағоза", "Shop", "The shop is open", "Мағоза кушода аст", "en_city"),
    WordItem("ec4", "Hospital", "Беморхона", "Hos-pi-tal", "Where is the hospital?", "Беморхона куҷост?", "en_city"),
    WordItem("ec5", "School", "Мактаб", "School", "My children go to school", "Кӯдаконам ба мактаб мераванд", "en_city"),
    WordItem("ec6", "Bank", "Бонк", "Bank", "I need to go to the bank", "Ман бояд ба бонк равам", "en_city"),
    WordItem("ec7", "Restaurant", "Тарабхона", "Res-tau-rant", "A good restaurant", "Тарабхонаи хуб", "en_city"),
    WordItem("ec8", "Park", "Боғ", "Park", "Let's go to the park", "Биёед ба боғ равем", "en_city"),
    WordItem("ec9", "Bus stop", "Истгоҳи автобус", "Bus stop", "Where is the bus stop?", "Истгоҳи автобус куҷост?", "en_city"),
    WordItem("ec10", "Market", "Бозор", "Mar-ket", "The market is big", "Бозор калон аст", "en_city"),
)

val enCityQuiz = listOf(
    QuizQuestion("ecq1", "en_city", QuestionType.TRANSLATE, "«City» чӣ маъно дорад?", "City = ...", listOf("Кӯча", "Шаҳр", "Мағоза", "Боғ"), 1, "City — шаҳр"),
    QuizQuestion("ecq2", "en_city", QuestionType.CHOOSE_TRANSLATION, "«Мағоза»-ро бо англисӣ чӣ тавр мегӯянд?", "Мағоза → ?", listOf("Bank", "Market", "Shop", "School"), 2, "Мағоза бо англисӣ — Shop"),
    QuizQuestion("ecq3", "en_city", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "Where is the _____?", listOf("city", "street", "hospital", "park"), 2, "Where is the hospital — беморхона куҷост"),
    QuizQuestion("ecq4", "en_city", QuestionType.TRANSLATE, "«Street» чӣ маъно дорад?", "Street = ...", listOf("Шаҳр", "Боғ", "Кӯча", "Бозор"), 2, "Street — кӯча"),
    QuizQuestion("ecq5", "en_city", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Бонк = ?", listOf("Shop", "Bank", "School", "Market"), 1, "Бонк бо англисӣ — Bank"),
    QuizQuestion("ecq6", "en_city", QuestionType.LISTEN, "Кадом калима дуруст аст?", "\uD83D\uDD0A Res-tau-rant", listOf("Hospital", "School", "Restaurant", "Market"), 2, "Restaurant — тарабхона"),
    QuizQuestion("ecq7", "en_city", QuestionType.CHOOSE_TRANSLATION, "«Мактаб»-ро бо англисӣ чӣ тавр мегӯянд?", "Мактаб → ?", listOf("Hospital", "School", "Bank", "Park"), 1, "Мактаб бо англисӣ — School"),
    QuizQuestion("ecq8", "en_city", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "Let's go to the _____", listOf("street", "city", "park", "bank"), 2, "Let's go to the park — биёед ба боғ равем"),
    QuizQuestion("ecq9", "en_city", QuestionType.TRANSLATE, "«Market» чӣ маъно дорад?", "Market = ...", listOf("Мағоза", "Бозор", "Бонк", "Кӯча"), 1, "Market — бозор"),
    QuizQuestion("ecq10", "en_city", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Истгоҳи автобус = ?", listOf("Bus stop", "Market", "Street", "Park"), 0, "Истгоҳи автобус бо англисӣ — Bus stop"),
)

// ═══════════════════════════════════════════════════
//  TOPIC 6: COLORS  (Рангҳо)
// ═══════════════════════════════════════════════════

val enColorsWords = listOf(
    WordItem("ecl1", "Red", "Сурх", "Red", "A red apple", "Себи сурх", "en_colors"),
    WordItem("ecl2", "Blue", "Кабуд", "Blue", "Blue sky", "Осмони кабуд", "en_colors"),
    WordItem("ecl3", "Green", "Сабз", "Green", "Green grass", "Алафи сабз", "en_colors"),
    WordItem("ecl4", "Yellow", "Зард", "Yel-low", "Yellow sun", "Офтоби зард", "en_colors"),
    WordItem("ecl5", "White", "Сафед", "White", "White snow", "Барфи сафед", "en_colors"),
    WordItem("ecl6", "Black", "Сиёҳ", "Black", "Black cat", "Гурбаи сиёҳ", "en_colors"),
    WordItem("ecl7", "Orange", "Норанҷӣ", "Or-ange", "Orange juice", "Шарбати норанҷӣ", "en_colors"),
    WordItem("ecl8", "Pink", "Гулобӣ", "Pink", "Pink flowers", "Гулҳои гулобӣ", "en_colors"),
    WordItem("ecl9", "Brown", "Қаҳваранг", "Brown", "Brown bread", "Нони қаҳваранг", "en_colors"),
    WordItem("ecl10", "Gray", "Хокистарӣ", "Gray", "Gray clouds", "Абрҳои хокистарӣ", "en_colors"),
)

val enColorsQuiz = listOf(
    QuizQuestion("eclq1", "en_colors", QuestionType.TRANSLATE, "«Red» чӣ маъно дорад?", "Red = ...", listOf("Кабуд", "Сурх", "Сабз", "Зард"), 1, "Red — сурх"),
    QuizQuestion("eclq2", "en_colors", QuestionType.CHOOSE_TRANSLATION, "«Кабуд»-ро бо англисӣ чӣ тавр мегӯянд?", "Кабуд → ?", listOf("Green", "Blue", "Black", "White"), 1, "Кабуд бо англисӣ — Blue"),
    QuizQuestion("eclq3", "en_colors", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____ grass", listOf("Red", "Blue", "Green", "Yellow"), 2, "Green grass — алафи сабз"),
    QuizQuestion("eclq4", "en_colors", QuestionType.TRANSLATE, "«Yellow» чӣ маъно дорад?", "Yellow = ...", listOf("Сафед", "Зард", "Сиёҳ", "Сурх"), 1, "Yellow — зард"),
    QuizQuestion("eclq5", "en_colors", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Сафед = ?", listOf("Black", "White", "Gray", "Pink"), 1, "Сафед бо англисӣ — White"),
    QuizQuestion("eclq6", "en_colors", QuestionType.LISTEN, "Кадом ранг дуруст аст?", "\uD83D\uDD0A Or-ange", listOf("Pink", "Orange", "Brown", "Yellow"), 1, "Orange — норанҷӣ"),
    QuizQuestion("eclq7", "en_colors", QuestionType.CHOOSE_TRANSLATION, "«Сиёҳ»-ро бо англисӣ чӣ тавр мегӯянд?", "Сиёҳ → ?", listOf("White", "Gray", "Black", "Brown"), 2, "Сиёҳ бо англисӣ — Black"),
    QuizQuestion("eclq8", "en_colors", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____ flowers", listOf("Brown", "Gray", "Pink", "Black"), 2, "Pink flowers — гулҳои гулобӣ"),
    QuizQuestion("eclq9", "en_colors", QuestionType.TRANSLATE, "«Brown» чӣ маъно дорад?", "Brown = ...", listOf("Норанҷӣ", "Қаҳваранг", "Хокистарӣ", "Гулобӣ"), 1, "Brown — қаҳваранг"),
    QuizQuestion("eclq10", "en_colors", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Хокистарӣ = ?", listOf("Brown", "Gray", "Black", "White"), 1, "Хокистарӣ бо англисӣ — Gray"),
)

// ═══════════════════════════════════════════════════
//  TOPIC 7: TIME  (Вақт)
// ═══════════════════════════════════════════════════

val enTimeWords = listOf(
    WordItem("et1", "Hour", "Соат", "Hour", "One hour", "Як соат", "en_time"),
    WordItem("et2", "Minute", "Дақиқа", "Min-ute", "Five minutes", "Панҷ дақиқа", "en_time"),
    WordItem("et3", "Morning", "Субҳ", "Morn-ing", "Good morning!", "Субҳ ба хайр!", "en_time"),
    WordItem("et4", "Evening", "Бегоҳ", "Eve-ning", "Good evening!", "Бегоҳ ба хайр!", "en_time"),
    WordItem("et5", "Night", "Шаб", "Night", "Good night!", "Шаби хуш!", "en_time"),
    WordItem("et6", "Today", "Имрӯз", "To-day", "Today is Monday", "Имрӯз душанбе аст", "en_time"),
    WordItem("et7", "Tomorrow", "Пагоҳ", "To-mor-row", "See you tomorrow", "То пагоҳ", "en_time"),
    WordItem("et8", "Yesterday", "Дирӯз", "Yes-ter-day", "I came yesterday", "Ман дирӯз омадам", "en_time"),
    WordItem("et9", "Week", "Ҳафта", "Week", "One week", "Як ҳафта", "en_time"),
    WordItem("et10", "Month", "Моҳ", "Month", "This month", "Ҳамин моҳ", "en_time"),
)

val enTimeQuiz = listOf(
    QuizQuestion("etq1", "en_time", QuestionType.TRANSLATE, "«Hour» чӣ маъно дорад?", "Hour = ...", listOf("Дақиқа", "Соат", "Ҳафта", "Моҳ"), 1, "Hour — соат"),
    QuizQuestion("etq2", "en_time", QuestionType.CHOOSE_TRANSLATION, "«Субҳ»-ро бо англисӣ чӣ тавр мегӯянд?", "Субҳ → ?", listOf("Evening", "Night", "Morning", "Today"), 2, "Субҳ бо англисӣ — Morning"),
    QuizQuestion("etq3", "en_time", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "Good _____!", listOf("today", "week", "evening", "month"), 2, "Good evening — бегоҳ ба хайр"),
    QuizQuestion("etq4", "en_time", QuestionType.TRANSLATE, "«Tomorrow» чӣ маъно дорад?", "Tomorrow = ...", listOf("Имрӯз", "Пагоҳ", "Дирӯз", "Ҳафта"), 1, "Tomorrow — пагоҳ"),
    QuizQuestion("etq5", "en_time", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Шаб = ?", listOf("Morning", "Evening", "Night", "Today"), 2, "Шаб бо англисӣ — Night"),
    QuizQuestion("etq6", "en_time", QuestionType.LISTEN, "Кадом калима дуруст аст?", "\uD83D\uDD0A Yes-ter-day", listOf("Today", "Tomorrow", "Yesterday", "Week"), 2, "Yesterday — дирӯз"),
    QuizQuestion("etq7", "en_time", QuestionType.CHOOSE_TRANSLATION, "«Ҳафта»-ро бо англисӣ чӣ тавр мегӯянд?", "Ҳафта → ?", listOf("Month", "Hour", "Week", "Minute"), 2, "Ҳафта бо англисӣ — Week"),
    QuizQuestion("etq8", "en_time", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "_____ is Monday", listOf("Yesterday", "Tomorrow", "Today", "Night"), 2, "Today is Monday — имрӯз душанбе"),
    QuizQuestion("etq9", "en_time", QuestionType.TRANSLATE, "«Minute» чӣ маъно дорад?", "Minute = ...", listOf("Соат", "Дақиқа", "Моҳ", "Ҳафта"), 1, "Minute — дақиқа"),
    QuizQuestion("etq10", "en_time", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Моҳ = ?", listOf("Week", "Month", "Hour", "Minute"), 1, "Моҳ бо англисӣ — Month"),
)

// ═══════════════════════════════════════════════════
//  TOPIC 8: ANIMALS  (Ҳайвонот)
// ═══════════════════════════════════════════════════

val enAnimalsWords = listOf(
    WordItem("ea1", "Cat", "Гурба", "Cat", "The cat is sleeping", "Гурба хоб аст", "en_animals"),
    WordItem("ea2", "Dog", "Саг", "Dog", "A big dog", "Саги калон", "en_animals"),
    WordItem("ea3", "Bird", "Парранда", "Bird", "The bird is singing", "Парранда суруд мехонад", "en_animals"),
    WordItem("ea4", "Fish", "Моҳӣ", "Fish", "Fresh fish", "Моҳии тоза", "en_animals"),
    WordItem("ea5", "Horse", "Асп", "Horse", "A white horse", "Аспи сафед", "en_animals"),
    WordItem("ea6", "Cow", "Гов", "Cow", "The cow gives milk", "Гов шир медиҳад", "en_animals"),
    WordItem("ea7", "Sheep", "Гӯсфанд", "Sheep", "Many sheep", "Гӯсфандони зиёд", "en_animals"),
    WordItem("ea8", "Chicken", "Мурғ", "Chick-en", "Chicken and rice", "Мурғ ва биринҷ", "en_animals"),
    WordItem("ea9", "Lion", "Шер", "Li-on", "The lion is strong", "Шер пурзӯр аст", "en_animals"),
    WordItem("ea10", "Rabbit", "Харгӯш", "Rab-bit", "A small rabbit", "Харгӯши хурд", "en_animals"),
)

val enAnimalsQuiz = listOf(
    QuizQuestion("eaq1", "en_animals", QuestionType.TRANSLATE, "«Cat» чӣ маъно дорад?", "Cat = ...", listOf("Саг", "Гурба", "Моҳӣ", "Парранда"), 1, "Cat — гурба"),
    QuizQuestion("eaq2", "en_animals", QuestionType.CHOOSE_TRANSLATION, "«Саг»-ро бо англисӣ чӣ тавр мегӯянд?", "Саг → ?", listOf("Cat", "Dog", "Horse", "Cow"), 1, "Саг бо англисӣ — Dog"),
    QuizQuestion("eaq3", "en_animals", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "The _____ is singing", listOf("cat", "dog", "bird", "fish"), 2, "The bird is singing — парранда суруд мехонад"),
    QuizQuestion("eaq4", "en_animals", QuestionType.TRANSLATE, "«Horse» чӣ маъно дорад?", "Horse = ...", listOf("Гов", "Гӯсфанд", "Асп", "Шер"), 2, "Horse — асп"),
    QuizQuestion("eaq5", "en_animals", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Моҳӣ = ?", listOf("Bird", "Fish", "Chicken", "Rabbit"), 1, "Моҳӣ бо англисӣ — Fish"),
    QuizQuestion("eaq6", "en_animals", QuestionType.LISTEN, "Кадом ҳайвон дуруст аст?", "\uD83D\uDD0A Chick-en", listOf("Sheep", "Chicken", "Rabbit", "Lion"), 1, "Chicken — мурғ"),
    QuizQuestion("eaq7", "en_animals", QuestionType.CHOOSE_TRANSLATION, "«Гов»-ро бо англисӣ чӣ тавр мегӯянд?", "Гов → ?", listOf("Horse", "Sheep", "Cow", "Dog"), 2, "Гов бо англисӣ — Cow"),
    QuizQuestion("eaq8", "en_animals", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "The _____ is strong", listOf("rabbit", "sheep", "lion", "cat"), 2, "The lion is strong — шер пурзӯр аст"),
    QuizQuestion("eaq9", "en_animals", QuestionType.TRANSLATE, "«Sheep» чӣ маъно дорад?", "Sheep = ...", listOf("Мурғ", "Гӯсфанд", "Харгӯш", "Гов"), 1, "Sheep — гӯсфанд"),
    QuizQuestion("eaq10", "en_animals", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Харгӯш = ?", listOf("Lion", "Rabbit", "Cat", "Dog"), 1, "Харгӯш бо англисӣ — Rabbit"),
)

// ═══════════════════════════════════════════════════
//  TOPIC 9: CLOTHES  (Либосҳо)
// ═══════════════════════════════════════════════════

val enClothesWords = listOf(
    WordItem("ecw1", "Shirt", "Куртаи мардона", "Shirt", "A white shirt", "Куртаи сафед", "en_clothes"),
    WordItem("ecw2", "Pants", "Шим", "Pants", "Black pants", "Шими сиёҳ", "en_clothes"),
    WordItem("ecw3", "Dress", "Либос/Курта", "Dress", "A beautiful dress", "Либоси зебо", "en_clothes"),
    WordItem("ecw4", "Shoes", "Пойафзол", "Shoes", "New shoes", "Пойафзоли нав", "en_clothes"),
    WordItem("ecw5", "Hat", "Кулоҳ", "Hat", "A warm hat", "Кулоҳи гарм", "en_clothes"),
    WordItem("ecw6", "Jacket", "Куртка", "Jack-et", "A leather jacket", "Курткаи чармӣ", "en_clothes"),
    WordItem("ecw7", "Socks", "Ҷуроб", "Socks", "Warm socks", "Ҷуроби гарм", "en_clothes"),
    WordItem("ecw8", "Scarf", "Рӯймол", "Scarf", "A silk scarf", "Рӯймоли абрешимӣ", "en_clothes"),
    WordItem("ecw9", "Coat", "Палто", "Coat", "A winter coat", "Палтои зимистонӣ", "en_clothes"),
    WordItem("ecw10", "Gloves", "Дастпӯшак", "Gloves", "Leather gloves", "Дастпӯшаки чармӣ", "en_clothes"),
)

val enClothesQuiz = listOf(
    QuizQuestion("ecwq1", "en_clothes", QuestionType.TRANSLATE, "«Shirt» чӣ маъно дорад?", "Shirt = ...", listOf("Шим", "Куртаи мардона", "Либос", "Пойафзол"), 1, "Shirt — куртаи мардона"),
    QuizQuestion("ecwq2", "en_clothes", QuestionType.CHOOSE_TRANSLATION, "«Шим»-ро бо англисӣ чӣ тавр мегӯянд?", "Шим → ?", listOf("Shirt", "Pants", "Dress", "Coat"), 1, "Шим бо англисӣ — Pants"),
    QuizQuestion("ecwq3", "en_clothes", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "A beautiful _____", listOf("shirt", "pants", "dress", "hat"), 2, "A beautiful dress — либоси зебо"),
    QuizQuestion("ecwq4", "en_clothes", QuestionType.TRANSLATE, "«Shoes» чӣ маъно дорад?", "Shoes = ...", listOf("Кулоҳ", "Ҷуроб", "Пойафзол", "Дастпӯшак"), 2, "Shoes — пойафзол"),
    QuizQuestion("ecwq5", "en_clothes", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Кулоҳ = ?", listOf("Scarf", "Hat", "Gloves", "Coat"), 1, "Кулоҳ бо англисӣ — Hat"),
    QuizQuestion("ecwq6", "en_clothes", QuestionType.LISTEN, "Кадом калима дуруст аст?", "\uD83D\uDD0A Jack-et", listOf("Coat", "Jacket", "Shirt", "Pants"), 1, "Jacket — куртка"),
    QuizQuestion("ecwq7", "en_clothes", QuestionType.CHOOSE_TRANSLATION, "«Ҷуроб»-ро бо англисӣ чӣ тавр мегӯянд?", "Ҷуроб → ?", listOf("Shoes", "Socks", "Gloves", "Scarf"), 1, "Ҷуроб бо англисӣ — Socks"),
    QuizQuestion("ecwq8", "en_clothes", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "A winter _____", listOf("shirt", "hat", "coat", "scarf"), 2, "A winter coat — палтои зимистонӣ"),
    QuizQuestion("ecwq9", "en_clothes", QuestionType.TRANSLATE, "«Gloves» чӣ маъно дорад?", "Gloves = ...", listOf("Ҷуроб", "Рӯймол", "Дастпӯшак", "Кулоҳ"), 2, "Gloves — дастпӯшак"),
    QuizQuestion("ecwq10", "en_clothes", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Рӯймол = ?", listOf("Scarf", "Hat", "Socks", "Coat"), 0, "Рӯймол бо англисӣ — Scarf"),
)

// ═══════════════════════════════════════════════════
//  TOPIC 10: BODY  (Бадан)
// ═══════════════════════════════════════════════════

val enBodyWords = listOf(
    WordItem("eb1", "Head", "Сар", "Head", "My head hurts", "Сарам дард мекунад", "en_body"),
    WordItem("eb2", "Hand", "Даст", "Hand", "Wash your hands", "Дастонатро шӯй", "en_body"),
    WordItem("eb3", "Eye", "Чашм", "Eye", "Blue eyes", "Чашмони кабуд", "en_body"),
    WordItem("eb4", "Ear", "Гӯш", "Ear", "I can hear with my ears", "Ман бо гӯшам мешунавам", "en_body"),
    WordItem("eb5", "Nose", "Бинӣ", "Nose", "A big nose", "Бинии калон", "en_body"),
    WordItem("eb6", "Mouth", "Даҳон", "Mouth", "Open your mouth", "Даҳонатро кушо", "en_body"),
    WordItem("eb7", "Leg", "Пой/Линг", "Leg", "My leg hurts", "Поям дард мекунад", "en_body"),
    WordItem("eb8", "Heart", "Дил", "Heart", "A kind heart", "Дили меҳрубон", "en_body"),
    WordItem("eb9", "Tooth", "Дандон", "Tooth", "Brush your teeth", "Дандонҳоятро тоза кун", "en_body"),
    WordItem("eb10", "Back", "Пушт/Камар", "Back", "My back hurts", "Камарам дард мекунад", "en_body"),
)

val enBodyQuiz = listOf(
    QuizQuestion("ebq1", "en_body", QuestionType.TRANSLATE, "«Head» чӣ маъно дорад?", "Head = ...", listOf("Даст", "Сар", "Пой", "Камар"), 1, "Head — сар"),
    QuizQuestion("ebq2", "en_body", QuestionType.CHOOSE_TRANSLATION, "«Даст»-ро бо англисӣ чӣ тавр мегӯянд?", "Даст → ?", listOf("Leg", "Head", "Hand", "Eye"), 2, "Даст бо англисӣ — Hand"),
    QuizQuestion("ebq3", "en_body", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "Blue _____", listOf("ears", "nose", "eyes", "mouth"), 2, "Blue eyes — чашмони кабуд"),
    QuizQuestion("ebq4", "en_body", QuestionType.TRANSLATE, "«Ear» чӣ маъно дорад?", "Ear = ...", listOf("Чашм", "Гӯш", "Бинӣ", "Даҳон"), 1, "Ear — гӯш"),
    QuizQuestion("ebq5", "en_body", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Бинӣ = ?", listOf("Mouth", "Nose", "Ear", "Eye"), 1, "Бинӣ бо англисӣ — Nose"),
    QuizQuestion("ebq6", "en_body", QuestionType.LISTEN, "Кадом калима дуруст аст?", "\uD83D\uDD0A Heart", listOf("Head", "Heart", "Hand", "Tooth"), 1, "Heart — дил"),
    QuizQuestion("ebq7", "en_body", QuestionType.CHOOSE_TRANSLATION, "«Пой»-ро бо англисӣ чӣ тавр мегӯянд?", "Пой → ?", listOf("Hand", "Back", "Leg", "Head"), 2, "Пой бо англисӣ — Leg"),
    QuizQuestion("ebq8", "en_body", QuestionType.FILL_BLANK, "Ҷои холиро пур кунед:", "Open your _____", listOf("eye", "ear", "nose", "mouth"), 3, "Open your mouth — даҳонатро кушо"),
    QuizQuestion("ebq9", "en_body", QuestionType.TRANSLATE, "«Tooth» чӣ маъно дорад?", "Tooth = ...", listOf("Дил", "Даст", "Дандон", "Камар"), 2, "Tooth — дандон"),
    QuizQuestion("ebq10", "en_body", QuestionType.MATCH_WORD, "Калимаи мувофиқро интихоб кунед:", "Камар = ?", listOf("Leg", "Back", "Head", "Hand"), 1, "Камар бо англисӣ — Back"),
)
