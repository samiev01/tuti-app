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
//  MODULE 4 · САЛОМАТӢ ВА ВАРЗИШ (Health & Fitness)
// ═══════════════════════════════════════════════════

internal val epM4L1 = Lesson(
    id = "ep_m4_l1", moduleId = "ep_m4",
    title = "Варзиш", description = "Тамрин, мушак ва истироҳат",
    emoji = "\uD83C\uDFCB\uFE0F", orderIndex = 0,
    dialogue = Dialogue(
        "Дар варзишгоҳ",
        listOf(
            DialogueLine("Trainer", "How often do you exercise?", "Чанд вақт якбар варзиш мекунед?"),
            DialogueLine("Firuz", "I work out every morning. I run and do push-ups.", "Ман ҳар субҳ тамрин мекунам. Медавам ва шинов мекунам."),
            DialogueLine("Trainer", "Good. Don't forget to stretch and warm up first.", "Хуб. Фаромӯш накунед, ки аввал кашед ва гарм кунед."),
            DialogueLine("Firuz", "I rest on Sundays — my muscles need it.", "Рӯзи якшанбе истироҳат мекунам — мушакҳоям лозим аст."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w13_1", "Gym", "Варзишгоҳ", "Gym", "I go to the gym", "Ман ба варзишгоҳ меравам", "ep_m4_l1"),
        WordItem("ep_w13_2", "Run", "Давидан", "Run", "I run three times a week", "Ман се маротиба дар ҳафта медавам", "ep_m4_l1"),
        WordItem("ep_w13_3", "Push-up", "Шинов", "Push-up", "Ten push-ups", "Даҳ шинов", "ep_m4_l1"),
        WordItem("ep_w13_4", "Stretch", "Кашидан", "Stretch", "Stretch your legs", "Поҳоятонро кашед", "ep_m4_l1"),
        WordItem("ep_w13_5", "Muscle", "Мушак", "Mus-cle", "Strong muscles", "Мушакҳои қавӣ", "ep_m4_l1"),
        WordItem("ep_w13_6", "Workout", "Тамрин", "Work-out", "A hard workout", "Тамрини сахт", "ep_m4_l1"),
        WordItem("ep_w13_7", "Warm-up", "Гармкунӣ", "Warm-up", "Warm-up for five minutes", "Панҷ дақиқа гарм кунед", "ep_m4_l1"),
        WordItem("ep_w13_8", "Rest", "Истироҳат", "Rest", "Rest between sets", "Байни маҷмӯъаҳо истироҳат кунед", "ep_m4_l1"),
    ),
    grammarTip = GrammarTip(
        "I work out every... / How often do you exercise?",
        "Барои одат аз «I work out every...» ва барои такрор аз «How often do you exercise?» истифода баред.",
        listOf("I work out every day.", "How often do you exercise?", "I run three times a week."),
    ),
    exercises = listOf(
        Exercise("ep_e13_1", ExerciseType.MULTIPLE_CHOICE, "«Workout» чӣ маъно дорад?", "Workout = ...", listOf("Истироҳат", "Тамрин", "Мушак", "Гармкунӣ"), "Тамрин", 1, "Workout — Тамрин"),
        Exercise("ep_e13_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "How often do you _____?", listOf("rest", "stretch", "exercise", "muscle"), "exercise", 2, "How often do you exercise"),
        Exercise("ep_e13_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I work out never", "I work out every morning", "I work out muscle push", "I warm up rest gym"), "I work out every morning", 1, "I work out every..."),
        Exercise("ep_e13_4", ExerciseType.TYPE_ANSWER, "«Шинов»-ро ба англисӣ нависед:", "Шинов = ?", null, "Push-up", null, "Push-up — Шинов"),
        Exercise("ep_e13_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Run" to "Давидан", "Stretch" to "Кашидан", "Gym" to "Варзишгоҳ", "Rest" to "Истироҳат")),
        Exercise("ep_e13_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Don't forget to warm up", null, "warm up", words = listOf("up", "warm", "to", "forget", "Don't")),
        Exercise("ep_e13_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nTrainer: Ready? Let's start.\nFiruz: _____", null, listOf("My muscle is a gym.", "Wait, I need to stretch first.", "I never rest."), "Wait, I need to stretch first.", 1, "Stretch"),
        Exercise("ep_e13_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Mus-cle", listOf("Muscle", "Workout", "Run", "Push-up"), "Muscle", 0, "Muscle — Мушак"),
    ),
)

internal val epM4L2 = Lesson(
    id = "ep_m4_l2", moduleId = "ep_m4",
    title = "Ғизои солим", description = "Парҳез, сафеда ва мувозинат",
    emoji = "\uD83E\uDD57", orderIndex = 1,
    dialogue = Dialogue(
        "Дар ошхона",
        listOf(
            DialogueLine("Anna", "I try to eat more protein and vitamins.", "Ман кӯшиш мекунам сафеда ва витаминҳои бештар хӯрам."),
            DialogueLine("Firuz", "You should avoid too many empty calories.", "Бояд аз калорияҳои холии зиёд канора шавед."),
            DialogueLine("Anna", "I buy organic vegetables when I can.", "Вақте ки мешавад, сабзавоти табиӣ мехарам."),
            DialogueLine("Firuz", "Balance is key — a little sweet food is OK sometimes.", "Мувозинат муҳим аст — гоҳе ширинии кам мумкин аст."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w14_1", "Healthy", "Солим", "Health-y", "Healthy food", "Хӯроки солим", "ep_m4_l2"),
        WordItem("ep_w14_2", "Diet", "Парҳез", "Di-et", "A balanced diet", "Парҳези мувозин", "ep_m4_l2"),
        WordItem("ep_w14_3", "Protein", "Сафеда", "Pro-tein", "Food rich in protein", "Хӯроки бой аз сафеда", "ep_m4_l2"),
        WordItem("ep_w14_4", "Vitamin", "Витамин", "Vit-a-min", "Vitamins and minerals", "Витаминҳо ва минералҳо", "ep_m4_l2"),
        WordItem("ep_w14_5", "Calorie", "Калория", "Cal-o-rie", "Low in calories", "Камкалория", "ep_m4_l2"),
        WordItem("ep_w14_6", "Organic", "Табиӣ", "Or-gan-ic", "Organic fruit", "Меваи табиӣ", "ep_m4_l2"),
        WordItem("ep_w14_7", "Avoid", "Канорагирӣ", "A-void", "Avoid sugar", "Аз шакар канора шавед", "ep_m4_l2"),
        WordItem("ep_w14_8", "Balance", "Мувозинат", "Bal-ance", "Keep a balance", "Мувозинат нигоҳ доред", "ep_m4_l2"),
    ),
    grammarTip = GrammarTip(
        "I try to eat... / You should avoid...",
        "Барои кӯшиш аз «I try to eat...» ва барои канорагирӣ аз «You should avoid...» истифода баред.",
        listOf("I try to eat more vegetables.", "You should avoid fast food every day.", "Balance is important."),
    ),
    exercises = listOf(
        Exercise("ep_e14_1", ExerciseType.MULTIPLE_CHOICE, "«Organic» чӣ маъно дорад?", "Organic = ...", listOf("Калория", "Табиӣ", "Солим", "Парҳез"), "Табиӣ", 1, "Organic — Табиӣ"),
        Exercise("ep_e14_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "You should _____ sugary drinks.", listOf("eat", "balance", "avoid", "vitamin"), "avoid", 2, "You should avoid"),
        Exercise("ep_e14_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I try to avoid protein", "I try to eat more vegetables", "I try vitamin calorie", "I try diet organic"), "I try to eat more vegetables", 1, "I try to eat..."),
        Exercise("ep_e14_4", ExerciseType.TYPE_ANSWER, "«Мувозинат»-ро ба англисӣ нависед:", "Мувозинат = ?", null, "Balance", null, "Balance — Мувозинат"),
        Exercise("ep_e14_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Protein" to "Сафеда", "Vitamin" to "Витамин", "Calorie" to "Калория", "Diet" to "Парҳез")),
        Exercise("ep_e14_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "You should avoid junk food", null, "You should avoid...", words = listOf("food", "junk", "avoid", "should", "You")),
        Exercise("ep_e14_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Is pizza OK?\nFiruz: _____", null, listOf("Avoid all protein.", "Sometimes — balance matters.", "Organic has no calories."), "Sometimes — balance matters.", 1, "Balance"),
        Exercise("ep_e14_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Pro-tein", listOf("Vitamin", "Protein", "Healthy", "Avoid"), "Protein", 1, "Protein — Сафеда"),
    ),
)

internal val epM4L3 = Lesson(
    id = "ep_m4_l3", moduleId = "ep_m4",
    title = "Духтур", description = "Аломат, дору ва нусха",
    emoji = "\uD83E\uDE7A", orderIndex = 2,
    dialogue = Dialogue(
        "Дар клиника",
        listOf(
            DialogueLine("Firuz", "I have a headache and a cough. I also had a fever last night.", "Сардард ва сулфа дорам. Шабона таб низ доштам."),
            DialogueLine("Doctor", "How long have you felt this pain?", "Ин дардро чанд вақт эҳсос мекунед?"),
            DialogueLine("Firuz", "Since Monday. I need medicine and maybe a prescription.", "Аз душанбе. Ба ман дору ва шояд нусха лозим аст."),
            DialogueLine("Doctor", "Book a follow-up appointment next week.", "Вохӯрии навбатиро барои ҳафтаи оянда захира кунед."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w15_1", "Doctor", "Духтур", "Doc-tor", "See a doctor", "Ба духтур равед", "ep_m4_l3"),
        WordItem("ep_w15_2", "Appointment", "Вохӯрӣ", "Ap-point-ment", "Make an appointment", "Вохӯрӣ гузоред", "ep_m4_l3"),
        WordItem("ep_w15_3", "Symptom", "Аломат", "Symp-tom", "What are your symptoms?", "Аломатҳои шумо чист?", "ep_m4_l3"),
        WordItem("ep_w15_4", "Medicine", "Дору", "Med-i-cine", "Take this medicine", "Ин дору бинӯшед", "ep_m4_l3"),
        WordItem("ep_w15_5", "Pain", "Дард", "Pain", "I have chest pain", "Дарди сина дорам", "ep_m4_l3"),
        WordItem("ep_w15_6", "Fever", "Таб", "Fe-ver", "High fever", "Таби баланд", "ep_m4_l3"),
        WordItem("ep_w15_7", "Cough", "Сулфа", "Cough", "A dry cough", "Сулфаи хушк", "ep_m4_l3"),
        WordItem("ep_w15_8", "Prescription", "Нусха", "Pre-scrip-tion", "Get a prescription", "Нусха гиред", "ep_m4_l3"),
    ),
    grammarTip = GrammarTip(
        "I have a headache / How long have you felt...?",
        "Барои аломат аз «I have a headache» ва барои муддат аз «How long have you felt...?» истифода баред.",
        listOf("I have a headache.", "How long have you felt this pain?", "Take this medicine twice a day."),
    ),
    exercises = listOf(
        Exercise("ep_e15_1", ExerciseType.MULTIPLE_CHOICE, "«Symptom» чӣ маъно дорад?", "Symptom = ...", listOf("Дору", "Аломат", "Нусха", "Таб"), "Аломат", 1, "Symptom — Аломат"),
        Exercise("ep_e15_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I have a _____.", listOf("prescription", "fever", "appointment", "doctor"), "fever", 1, "I have a fever"),
        Exercise("ep_e15_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("How long have you felt this medicine?", "How long have you felt this pain?", "How long symptom cough?", "How long doctor headache?"), "How long have you felt this pain?", 1, "How long have you felt"),
        Exercise("ep_e15_4", ExerciseType.TYPE_ANSWER, "«Сулфа»-ро ба англисӣ нависед:", "Сулфа = ?", null, "Cough", null, "Cough — Сулфа"),
        Exercise("ep_e15_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Doctor" to "Духтур", "Medicine" to "Дору", "Pain" to "Дард", "Prescription" to "Нусха")),
        Exercise("ep_e15_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I have a headache", null, "I have a headache", words = listOf("headache", "a", "have", "I")),
        Exercise("ep_e15_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nDoctor: Any symptoms?\nFiruz: _____", null, listOf("My prescription is fever.", "I have a cough and a fever.", "The appointment is pain."), "I have a cough and a fever.", 1, "Symptoms"),
        Exercise("ep_e15_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Pre-scrip-tion", listOf("Medicine", "Prescription", "Symptom", "Cough"), "Prescription", 1, "Prescription — Нусха"),
    ),
)

internal val epM4L4 = Lesson(
    id = "ep_m4_l4", moduleId = "ep_m4",
    title = "Саломатии рӯҳӣ", description = "Стресс, хоб ва оромӣ",
    emoji = "\uD83E\uDDE0", orderIndex = 3,
    dialogue = Dialogue(
        "Оромӣ",
        listOf(
            DialogueLine("Anna", "I feel stressed because of work — I need to relax.", "Аз кор стресс эҳсос мекунам — бояд ором гирам."),
            DialogueLine("Firuz", "Try meditation before sleep. Breathe slowly.", "Пеш аз хоб мурокиба кунед. Оҳиста нафас кашед."),
            DialogueLine("Anna", "My anxiety is lower when I have enough energy.", "Изтиробам пасттар аст, вақте ки энергияи кофӣ дорам."),
            DialogueLine("Firuz", "Stay calm — even a short walk helps.", "Ором бимонед — ҳатто сайри кӯтоҳ кӯмак мекунад."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w16_1", "Stress", "Стресс", "Stress", "Reduce stress", "Стрессро кам кунед", "ep_m4_l4"),
        WordItem("ep_w16_2", "Relax", "Ором гирифтан", "Re-lax", "I need to relax", "Ба ман ором гирифтан лозим", "ep_m4_l4"),
        WordItem("ep_w16_3", "Meditation", "Мурокиба", "Med-i-ta-tion", "Daily meditation", "Мурокибаи ҳаррӯза", "ep_m4_l4"),
        WordItem("ep_w16_4", "Sleep", "Хоб", "Sleep", "I need more sleep", "Ба ман хоби бештар лозим", "ep_m4_l4"),
        WordItem("ep_w16_5", "Anxiety", "Изтироб", "Anx-i-e-ty", "Social anxiety", "Изтироби иҷтимоӣ", "ep_m4_l4"),
        WordItem("ep_w16_6", "Energy", "Энергия", "En-er-gy", "Low energy", "Энергияи паст", "ep_m4_l4"),
        WordItem("ep_w16_7", "Breathe", "Нафас кашидан", "Breathe", "Breathe deeply", "Чуқур нафас кашед", "ep_m4_l4"),
        WordItem("ep_w16_8", "Calm", "Оромӣ", "Calm", "Stay calm", "Ором бимонед", "ep_m4_l4"),
    ),
    grammarTip = GrammarTip(
        "I feel stressed because... / I need to relax",
        "Барои сабаб аз «I feel stressed because...» ва барои оромӣ аз «I need to relax» истифода баред.",
        listOf("I feel stressed because of exams.", "I need to relax tonight.", "Meditation helps me sleep."),
    ),
    exercises = listOf(
        Exercise("ep_e16_1", ExerciseType.MULTIPLE_CHOICE, "«Anxiety» чӣ маъно дорад?", "Anxiety = ...", listOf("Хоб", "Изтироб", "Стресс", "Энергия"), "Изтироб", 1, "Anxiety — Изтироб"),
        Exercise("ep_e16_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I need to _____.", listOf("stress", "anxiety", "relax", "meditation"), "relax", 2, "I need to relax"),
        Exercise("ep_e16_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I feel stressed because of sleep", "I feel stressed because of work", "I feel meditation because calm", "I feel energy breathe"), "I feel stressed because of work", 1, "I feel stressed because..."),
        Exercise("ep_e16_4", ExerciseType.TYPE_ANSWER, "«Нафас кашидан»-ро ба англисӣ нависед:", "...", null, "Breathe", null, "Breathe"),
        Exercise("ep_e16_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Stress" to "Стресс", "Sleep" to "Хоб", "Energy" to "Энергия", "Calm" to "Оромӣ")),
        Exercise("ep_e16_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I need to relax and sleep", null, "relax and sleep", words = listOf("sleep", "and", "relax", "to", "need", "I")),
        Exercise("ep_e16_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: I can't sleep.\nFiruz: _____", null, listOf("Your stress is anxiety.", "Try breathing slowly before bed.", "Meditation has no calm."), "Try breathing slowly before bed.", 1, "Breathe"),
        Exercise("ep_e16_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Med-i-ta-tion", listOf("Meditation", "Relax", "Stress", "Sleep"), "Meditation", 0, "Meditation — Мурокиба"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 5 · САФАР ВА ҶАҲОН (Travel & World)
// ═══════════════════════════════════════════════════

internal val epM5L1 = Lesson(
    id = "ep_m5_l1", moduleId = "ep_m5",
    title = "Кишварҳо", description = "Пойтахт, фарҳанг ва забон",
    emoji = "\uD83C\uDF0D", orderIndex = 0,
    dialogue = Dialogue(
        "Дар синф",
        listOf(
            DialogueLine("Anna", "I'm from Tajikistan. Our capital is Dushanbe.", "Ман аз Тоҷикистон. Пойтахти мо Душанбе аст."),
            DialogueLine("Firuz", "Have you ever been to France? The culture is amazing.", "Оё ҳангоме ба Фаронса рафтаед? Фарҳанг аҷоиб аст."),
            DialogueLine("Anna", "Not yet. Which continent is it on?", "Ҳанӯз не. Дар кадом қитъа аст?"),
            DialogueLine("Firuz", "Europe — and the flag is blue, white, and red.", "Аврупо — парчамаш кабуд, сафед ва сурх аст."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w17_1", "Country", "Кишвар", "Coun-try", "My country is beautiful", "Кишварам зебо аст", "ep_m5_l1"),
        WordItem("ep_w17_2", "Capital", "Пойтахт", "Cap-i-tal", "The capital is big", "Пойтахт калон аст", "ep_m5_l1"),
        WordItem("ep_w17_3", "Population", "Аҳолӣ", "Pop-u-la-tion", "A large population", "Аҳолии калон", "ep_m5_l1"),
        WordItem("ep_w17_4", "Language", "Забон", "Lan-guage", "English is a global language", "Англисӣ забони ҷаҳонӣ аст", "ep_m5_l1"),
        WordItem("ep_w17_5", "Flag", "Парчам", "Flag", "The national flag", "Парчами миллӣ", "ep_m5_l1"),
        WordItem("ep_w17_6", "Border", "Сарҳад", "Bor-der", "We crossed the border", "Мо сарҳадро убур кардем", "ep_m5_l1"),
        WordItem("ep_w17_7", "Continent", "Қитъа", "Con-ti-nent", "Asia is a big continent", "Осиё қитъаи калон аст", "ep_m5_l1"),
        WordItem("ep_w17_8", "Culture", "Фарҳанг", "Cul-ture", "I love local culture", "Ман фарҳанги маҳаллиро дӯст медорам", "ep_m5_l1"),
    ),
    grammarTip = GrammarTip(
        "I'm from... / Have you ever been to...?",
        "Барои асл аз «I'm from...» ва барои таҷриба аз «Have you ever been to...?» истифода баред.",
        listOf("I'm from Tajikistan.", "Have you ever been to Paris?", "The culture is very rich."),
    ),
    exercises = listOf(
        Exercise("ep_e17_1", ExerciseType.MULTIPLE_CHOICE, "«Continent» чӣ маъно дорад?", "Continent = ...", listOf("Парчам", "Сарҳад", "Қитъа", "Забон"), "Қитъа", 2, "Continent — Қитъа"),
        Exercise("ep_e17_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I'm _____ Tajikistan.", listOf("on", "to", "from", "at"), "from", 2, "I'm from"),
        Exercise("ep_e17_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Have you ever been to culture?", "Have you ever been to Italy?", "Have you ever border flag?", "Have you ever population capital?"), "Have you ever been to Italy?", 1, "Have you ever been to..."),
        Exercise("ep_e17_4", ExerciseType.TYPE_ANSWER, "«Пойтахт»-ро ба англисӣ нависед:", "Пойтахт = ?", null, "Capital", null, "Capital — Пойтахт"),
        Exercise("ep_e17_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Country" to "Кишвар", "Language" to "Забон", "Flag" to "Парчам", "Border" to "Сарҳад")),
        Exercise("ep_e17_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I'm from Dushanbe", null, "I'm from + ҷой", words = listOf("Dushanbe", "from", "I'm")),
        Exercise("ep_e17_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Where are you from?\nFiruz: _____", null, listOf("The border is Europe.", "I'm from Tajikistan.", "The flag is culture."), "I'm from Tajikistan.", 1, "I'm from"),
        Exercise("ep_e17_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Cul-ture", listOf("Capital", "Culture", "Country", "Population"), "Culture", 1, "Culture — Фарҳанг"),
    ),
)

internal val epM5L2 = Lesson(
    id = "ep_m5_l2", moduleId = "ep_m5",
    title = "Фурудгоҳ", description = "Парвоз, паспорт ва гумрук",
    emoji = "\u2708\uFE0F", orderIndex = 1,
    dialogue = Dialogue(
        "Дар фурудгоҳ",
        listOf(
            DialogueLine("Firuz", "My flight has been delayed. Where is gate B12?", "Парвозам таъхир хӯрд. Дарвозаи B12 дар куҷост?"),
            DialogueLine("Staff", "Show your passport at customs after landing.", "Паспортро дар гумрук пас аз фуруд нишон диҳед."),
            DialogueLine("Firuz", "Is boarding soon? I have heavy luggage.", "Нишастан зуд аст? Бори вазнин дорам."),
            DialogueLine("Staff", "Yes — please wait near the gate.", "Ҳа — назди дарвоза интизор шавед."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w18_1", "Flight", "Парвоз", "Flight", "My flight is on time", "Парвозам ба вақт аст", "ep_m5_l2"),
        WordItem("ep_w18_2", "Boarding", "Нишастан", "Board-ing", "Boarding starts now", "Нишастан оғоз шуд", "ep_m5_l2"),
        WordItem("ep_w18_3", "Passport", "Паспорт", "Pass-port", "Don't forget your passport", "Паспортро фаромӯш накунед", "ep_m5_l2"),
        WordItem("ep_w18_4", "Gate", "Дарвоза", "Gate", "Gate 5 is here", "Дарвозаи 5 ин ҷост", "ep_m5_l2"),
        WordItem("ep_w18_5", "Luggage", "Бор", "Lug-gage", "Check your luggage", "Боратонро санҷед", "ep_m5_l2"),
        WordItem("ep_w18_6", "Delay", "Таъхир", "De-lay", "Sorry for the delay", "Бубахшед барои таъхир", "ep_m5_l2"),
        WordItem("ep_w18_7", "Landing", "Фурудомадан", "Land-ing", "Smooth landing", "Фурудомадани осон", "ep_m5_l2"),
        WordItem("ep_w18_8", "Customs", "Гумрук", "Cus-toms", "Go through customs", "Аз гумрук гузаред", "ep_m5_l2"),
    ),
    grammarTip = GrammarTip(
        "My flight has been delayed / Where is gate...?",
        "Барои таъхир аз «My flight has been delayed» ва барои дарвоза аз «Where is gate...?» истифода баред.",
        listOf("My flight has been delayed.", "Where is gate B12?", "I need to check my luggage."),
    ),
    exercises = listOf(
        Exercise("ep_e18_1", ExerciseType.MULTIPLE_CHOICE, "«Luggage» чӣ маъно дорад?", "Luggage = ...", listOf("Паспорт", "Бор", "Таъхир", "Парвоз"), "Бор", 1, "Luggage — Бор"),
        Exercise("ep_e18_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "My flight has been _____.", listOf("boarding", "delayed", "landing", "gate"), "delayed", 1, "My flight has been delayed"),
        Exercise("ep_e18_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Where is customs passport?", "Where is gate B12?", "Where is flight luggage delay?", "Where is boarding heavy?"), "Where is gate B12?", 1, "Where is gate"),
        Exercise("ep_e18_4", ExerciseType.TYPE_ANSWER, "«Гумрук»-ро ба англисӣ нависед:", "Гумрук = ?", null, "Customs", null, "Customs — Гумрук"),
        Exercise("ep_e18_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Flight" to "Парвоз", "Passport" to "Паспорт", "Gate" to "Дарвоза", "Delay" to "Таъхир")),
        Exercise("ep_e18_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Show your passport", null, "Show your passport", words = listOf("passport", "your", "Show")),
        Exercise("ep_e18_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: My flight has been delayed.\nAnna: _____", null, listOf("The gate is customs.", "I'm sorry to hear that.", "Your luggage is boarding."), "I'm sorry to hear that.", 1, "Empathy"),
        Exercise("ep_e18_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Land-ing", listOf("Landing", "Boarding", "Delay", "Gate"), "Landing", 0, "Landing — Фурудомадан"),
    ),
)

internal val epM5L3 = Lesson(
    id = "ep_m5_l3", moduleId = "ep_m5",
    title = "Меҳмонхона", description = "Хона, лифт ва наҳорӣ",
    emoji = "\uD83C\uDFE8", orderIndex = 2,
    dialogue = Dialogue(
        "Дар меҳмонхона",
        listOf(
            DialogueLine("Firuz", "I'd like to book a room for two nights.", "Ман мехоҳам хонаро барои ду шаб захира кунам."),
            DialogueLine("Receptionist", "We have a double room on the third floor.", "Мо хонаи дугона дар ошёнаи се дорам."),
            DialogueLine("Firuz", "Does it include breakfast? Is there a pool?", "Оё наҳорӣ дохил аст? Ҳавз ҳаст?"),
            DialogueLine("Receptionist", "Yes — breakfast is from seven to ten. The elevator is over there.", "Ҳа — наҳорӣ аз ҳафт то даҳ. Лифт он ҷост."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w19_1", "Room", "Хона", "Room", "A quiet room", "Хонаи ором", "ep_m5_l3"),
        WordItem("ep_w19_2", "Book", "Захира кардан", "Book", "Book a room online", "Хонаро онлайн захира кунед", "ep_m5_l3"),
        WordItem("ep_w19_3", "Single", "Якка", "Sin-gle", "A single room", "Хонаи якка", "ep_m5_l3"),
        WordItem("ep_w19_4", "Double", "Дугона", "Dou-ble", "A double bed", "Катҳои дугона", "ep_m5_l3"),
        WordItem("ep_w19_5", "Floor", "Ошёна", "Floor", "Third floor", "Ошёнаи сеюм", "ep_m5_l3"),
        WordItem("ep_w19_6", "Elevator", "Лифт", "El-e-va-tor", "Take the elevator", "Ба лифт савор шавед", "ep_m5_l3"),
        WordItem("ep_w19_7", "Pool", "Ҳавз", "Pool", "Swimming pool", "Ҳавзи шиноварӣ", "ep_m5_l3"),
        WordItem("ep_w19_8", "Breakfast", "Наҳорӣ", "Break-fast", "Breakfast is included", "Наҳорӣ дохил аст", "ep_m5_l3"),
    ),
    grammarTip = GrammarTip(
        "I'd like to book a room / Does it include breakfast?",
        "Барои захира аз «I'd like to book a room» ва барои наҳорӣ аз «Does it include breakfast?» истифода баред.",
        listOf("I'd like to book a room.", "Does it include breakfast?", "Is there a pool?"),
    ),
    exercises = listOf(
        Exercise("ep_e19_1", ExerciseType.MULTIPLE_CHOICE, "«Elevator» чӣ маъно дорад?", "Elevator = ...", listOf("Ҳавз", "Лифт", "Ошёна", "Наҳорӣ"), "Лифт", 1, "Elevator — Лифт"),
        Exercise("ep_e19_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Does it include _____?", listOf("floor", "pool", "breakfast", "elevator"), "breakfast", 2, "Does it include breakfast"),
        Exercise("ep_e19_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I'd like to book a elevator", "I'd like to book a room", "I'd like single double floor", "I'd like pool breakfast hotel"), "I'd like to book a room", 1, "I'd like to book a room"),
        Exercise("ep_e19_4", ExerciseType.TYPE_ANSWER, "«Дугона»-ро ба англисӣ нависед (хона):", "...", null, "Double", null, "Double — Дугона"),
        Exercise("ep_e19_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Single" to "Якка", "Double" to "Дугона", "Floor" to "Ошёна", "Pool" to "Ҳавз")),
        Exercise("ep_e19_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Is there a swimming pool", null, "Is there a pool", words = listOf("pool", "swimming", "a", "there", "Is")),
        Exercise("ep_e19_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: I'd like to book a room.\nReceptionist: _____", null, listOf("The elevator is breakfast.", "For how many nights?", "Your pool is double."), "For how many nights?", 1, "Booking"),
        Exercise("ep_e19_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Break-fast", listOf("Book", "Breakfast", "Room", "Floor"), "Breakfast", 1, "Breakfast — Наҳорӣ"),
    ),
)

internal val epM5L4 = Lesson(
    id = "ep_m5_l4", moduleId = "ep_m5",
    title = "Саргармӣ", description = "Сайёҳӣ, ёдгорӣ ва манзара",
    emoji = "\uD83D\uDDBC\uFE0F", orderIndex = 3,
    dialogue = Dialogue(
        "Дар шаҳр",
        listOf(
            DialogueLine("Anna", "Is there a guided tour of the old castle?", "Оё сайёҳии роҳбалад дар бораи қалъаи кӯҳна ҳаст?"),
            DialogueLine("Firuz", "Yes — the guide knows every monument and bridge.", "Ҳа — роҳбалад ҳар ёдгорӣ ва пулро медонад."),
            DialogueLine("Anna", "What a beautiful view from the park!", "Чӣ манзараи зебо аз боғ!"),
            DialogueLine("Firuz", "This city is amazing at sunset.", "Ин шаҳр дар ғуруби офтоб аҷоиб аст."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w20_1", "Tour", "Сайёҳӣ", "Tour", "A city tour", "Сайёҳии шаҳр", "ep_m5_l4"),
        WordItem("ep_w20_2", "Guide", "Роҳбалад", "Guide", "Our guide is friendly", "Роҳбалади мо дӯстдор аст", "ep_m5_l4"),
        WordItem("ep_w20_3", "Monument", "Ёдгорӣ", "Mon-u-ment", "A historic monument", "Ёдгории таърихӣ", "ep_m5_l4"),
        WordItem("ep_w20_4", "Castle", "Қалъа", "Cas-tle", "The castle is old", "Қалъа кӯҳна аст", "ep_m5_l4"),
        WordItem("ep_w20_5", "Bridge", "Пул", "Bridge", "Cross the bridge", "Пулро убур кунед", "ep_m5_l4"),
        WordItem("ep_w20_6", "Park", "Боғ", "Park", "Walk in the park", "Дар боғ гардед", "ep_m5_l4"),
        WordItem("ep_w20_7", "View", "Манзара", "View", "A great view", "Манзараи аъло", "ep_m5_l4"),
        WordItem("ep_w20_8", "Amazing", "Аҷоиб", "A-maz-ing", "An amazing place", "Ҷои аҷоиб", "ep_m5_l4"),
    ),
    grammarTip = GrammarTip(
        "Is there a guided tour? / What a beautiful view!",
        "Барои пурсидан «Is there a guided tour?» ва барои ҳайрат «What a beautiful view!» истифода баред.",
        listOf("Is there a guided tour today?", "What a beautiful view!", "This bridge is amazing."),
    ),
    exercises = listOf(
        Exercise("ep_e20_1", ExerciseType.MULTIPLE_CHOICE, "«Monument» чӣ маъно дорад?", "Monument = ...", listOf("Пул", "Боғ", "Ёдгорӣ", "Манзара"), "Ёдгорӣ", 2, "Monument — Ёдгорӣ"),
        Exercise("ep_e20_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "What a beautiful _____!", listOf("guide", "tour", "view", "castle"), "view", 2, "What a beautiful view"),
        Exercise("ep_e20_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Is there a bridge park?", "Is there a guided tour?", "Is there amazing monument view?", "Is there castle guide?"), "Is there a guided tour?", 1, "Is there a guided tour"),
        Exercise("ep_e20_4", ExerciseType.TYPE_ANSWER, "«Қалъа»-ро ба англисӣ нависед:", "Қалъа = ?", null, "Castle", null, "Castle — Қалъа"),
        Exercise("ep_e20_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Guide" to "Роҳбалад", "Bridge" to "Пул", "Park" to "Боғ", "Amazing" to "Аҷоиб")),
        Exercise("ep_e20_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "This place is amazing", null, "amazing place", words = listOf("amazing", "is", "place", "This")),
        Exercise("ep_e20_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Look at the river!\nFiruz: _____", null, listOf("The monument is a bridge.", "What a beautiful view!", "Our guide is a park."), "What a beautiful view!", 1, "Exclamation"),
        Exercise("ep_e20_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A A-maz-ing", listOf("View", "Amazing", "Tour", "Castle"), "Amazing", 1, "Amazing — Аҷоиб"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 6 · ТЕХНОЛОГИЯ (Technology & Digital)
// ═══════════════════════════════════════════════════

internal val epM6L1 = Lesson(
    id = "ep_m6_l1", moduleId = "ep_m6",
    title = "Смартфон", description = "Барнома, хотира ва скриншот",
    emoji = "\uD83D\uDCF1", orderIndex = 0,
    dialogue = Dialogue(
        "Дар бораи телефон",
        listOf(
            DialogueLine("Anna", "Can you download this app for me?", "Метавонед ин барномаро барои ман боргирӣ кунед?"),
            DialogueLine("Firuz", "Sure — but my phone is out of storage.", "Албатта — аммо телефонам хотира надорад."),
            DialogueLine("Anna", "Delete old photos or turn off notifications in settings.", "Аксҳои кӯҳнаро нест кунед ё огоҳиномаҳоро дар танзимот хомӯш кунед."),
            DialogueLine("Firuz", "OK — I'll update the system and take a screenshot.", "Хуб — системаро навсозӣ мекунам ва скриншот мегирам."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w21_1", "App", "Барнома", "App", "Download an app", "Барнома боргирӣ кунед", "ep_m6_l1"),
        WordItem("ep_w21_2", "Download", "Боргирӣ", "Down-load", "Download a file", "Файлро боргирӣ кунед", "ep_m6_l1"),
        WordItem("ep_w21_3", "Update", "Навсозӣ", "Up-date", "Update your phone", "Телефонро навсозӣ кунед", "ep_m6_l1"),
        WordItem("ep_w21_4", "Notification", "Огоҳинома", "No-ti-fi-ca-tion", "Turn off notifications", "Огоҳиномаҳоро хомӯш кунед", "ep_m6_l1"),
        WordItem("ep_w21_5", "Setting", "Танзимот", "Set-ting", "Open settings", "Танзимотро кушоед", "ep_m6_l1"),
        WordItem("ep_w21_6", "Storage", "Хотира", "Stor-age", "Not enough storage", "Хотира кофӣ нест", "ep_m6_l1"),
        WordItem("ep_w21_7", "Screenshot", "Скриншот", "Screen-shot", "Take a screenshot", "Скриншот гиред", "ep_m6_l1"),
        WordItem("ep_w21_8", "Delete", "Нест кардан", "De-lete", "Delete the file", "Файлро нест кунед", "ep_m6_l1"),
    ),
    grammarTip = GrammarTip(
        "Can you download this app? / My phone is out of storage",
        "Барои хоҳиш аз «Can you download...?» ва барои хотира аз «My phone is out of storage» истифода баред.",
        listOf("Can you download this app?", "My phone is out of storage.", "I need to delete old files."),
    ),
    exercises = listOf(
        Exercise("ep_e21_1", ExerciseType.MULTIPLE_CHOICE, "«Storage» дар ин дарс чӣ маъно дорад?", "Storage = ...", listOf("Танзимот", "Хотира", "Огоҳинома", "Скриншот"), "Хотира", 1, "Storage — Хотира"),
        Exercise("ep_e21_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Can you _____ this app?", listOf("delete", "update", "download", "screenshot"), "download", 2, "Can you download"),
        Exercise("ep_e21_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("My phone is out of notifications", "My phone is out of storage", "My phone is out of settings app", "My phone is delete update"), "My phone is out of storage", 1, "out of storage"),
        Exercise("ep_e21_4", ExerciseType.TYPE_ANSWER, "«Навсозӣ»-ро ба англисӣ нависед:", "...", null, "Update", null, "Update — Навсозӣ"),
        Exercise("ep_e21_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("App" to "Барнома", "Download" to "Боргирӣ", "Notification" to "Огоҳинома", "Delete" to "Нест кардан")),
        Exercise("ep_e21_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Take a screenshot", null, "Take a screenshot", words = listOf("screenshot", "a", "Take")),
        Exercise("ep_e21_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: The app won't install.\nFiruz: _____", null, listOf("Your notification is storage.", "Maybe you need more storage.", "Delete the screenshot settings."), "Maybe you need more storage.", 1, "Storage"),
        Exercise("ep_e21_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A No-ti-fi-ca-tion", listOf("Update", "Notification", "App", "Delete"), "Notification", 1, "Notification — Огоҳинома"),
    ),
)

internal val epM6L2 = Lesson(
    id = "ep_m6_l2", moduleId = "ep_m6",
    title = "Интернет", description = "Сайт, браузер ва ҳисоб",
    emoji = "\uD83C\uDF10", orderIndex = 1,
    dialogue = Dialogue(
        "Онлайн",
        listOf(
            DialogueLine("Firuz", "Can you send me the link to that website?", "Метавонед пайванд ба он сайтро фиристед?"),
            DialogueLine("Anna", "Sure — open your browser and click here.", "Албатта — браузерро кушоед ва ин ҷо пахш кунед."),
            DialogueLine("Firuz", "I can't log in — wrong password.", "Ворид шуда наметавонам — пароли нодуруст."),
            DialogueLine("Anna", "Try again — then log out and reset your account.", "Боз кӯшиш кунед — пас бароед ва ҳисобро нав кунед."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w22_1", "Website", "Сайт", "Web-site", "Visit our website", "Ба сайти мо равед", "ep_m6_l2"),
        WordItem("ep_w22_2", "Search", "Ҷустуҷӯ", "Search", "Search online", "Онлайн ҷустуҷӯ кунед", "ep_m6_l2"),
        WordItem("ep_w22_3", "Browser", "Браузер", "Brows-er", "Use a safe browser", "Браузери бехатар истифода баред", "ep_m6_l2"),
        WordItem("ep_w22_4", "Link", "Пайванд", "Link", "Click the link", "Пайвандро пахш кунед", "ep_m6_l2"),
        WordItem("ep_w22_5", "Click", "Пахш кардан", "Click", "Click here", "Ин ҷо пахш кунед", "ep_m6_l2"),
        WordItem("ep_w22_6", "Login", "Ворид шудан", "Log-in", "I can't log in", "Ворид шуда наметавонам", "ep_m6_l2"),
        WordItem("ep_w22_7", "Logout", "Баромадан", "Log-out", "Please log out", "Лутфан бароед", "ep_m6_l2"),
        WordItem("ep_w22_8", "Account", "Ҳисоб", "Ac-count", "Create an account", "Ҳисоб эҷод кунед", "ep_m6_l2"),
    ),
    grammarTip = GrammarTip(
        "Can you send me the link? / I can't log in",
        "Барои пайванд аз «Can you send me the link?» ва барои воридшавӣ аз «I can't log in» истифода баред.",
        listOf("Can you send me the link?", "I can't log in.", "Please log out when you finish."),
    ),
    exercises = listOf(
        Exercise("ep_e22_1", ExerciseType.MULTIPLE_CHOICE, "«Browser» чӣ маъно дорад?", "Browser = ...", listOf("Сайт", "Ҳисоб", "Браузер", "Пайванд"), "Браузер", 2, "Browser — Браузер"),
        Exercise("ep_e22_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I can't _____ in.", listOf("search", "link", "log", "click"), "log", 2, "I can't log in"),
        Exercise("ep_e22_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Can you send me the browser?", "Can you send me the link?", "Can you send website logout?", "Can you click account search?"), "Can you send me the link?", 1, "Can you send me the link"),
        Exercise("ep_e22_4", ExerciseType.TYPE_ANSWER, "«Ҳисоб»-ро ба англисӣ нависед:", "Ҳисоб = ?", null, "Account", null, "Account — Ҳисоб"),
        Exercise("ep_e22_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Website" to "Сайт", "Search" to "Ҷустуҷӯ", "Link" to "Пайванд", "Click" to "Пахш кардан")),
        Exercise("ep_e22_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Open your browser", null, "Open your browser", words = listOf("browser", "your", "Open")),
        Exercise("ep_e22_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: I can't log in.\nAnna: _____", null, listOf("Your link is a website.", "Check your password and try again.", "Click the logout browser."), "Check your password and try again.", 1, "Login help"),
        Exercise("ep_e22_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Ac-count", listOf("Search", "Account", "Login", "Link"), "Account", 1, "Account — Ҳисоб"),
    ),
)

internal val epM6L3 = Lesson(
    id = "ep_m6_l3", moduleId = "ep_m6",
    title = "Шабакаҳои иҷтимоӣ", description = "Нашр, ҳикоя ва профил",
    emoji = "\uD83D\uDCF2", orderIndex = 2,
    dialogue = Dialogue(
        "Онлайн",
        listOf(
            DialogueLine("Anna", "Follow me on this app — I post photos every day.", "Маро дар ин барнома пайравӣ кунед — ҳар рӯз акс нашр мекунам."),
            DialogueLine("Firuz", "Did you see my story yesterday?", "Ҳикояи маро дирӯз дидед?"),
            DialogueLine("Anna", "Yes — I liked it and left a comment.", "Ҳа — писанд кардам ва шарҳ гузоштам."),
            DialogueLine("Firuz", "Thanks! Don't share it without asking — someone blocked me.", "Ташаккур! Бе иҷозат мубодила накунед — касе маро бастааст."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w23_1", "Post", "Нашр", "Post", "Share a post", "Нашр мубодила кунед", "ep_m6_l3"),
        WordItem("ep_w23_2", "Follow", "Пайравӣ", "Fol-low", "Follow my page", "Саҳифаи маро пайравӣ кунед", "ep_m6_l3"),
        WordItem("ep_w23_3", "Story", "Ҳикоя", "Sto-ry", "Watch my story", "Ҳикояи маро тамошо кунед", "ep_m6_l3"),
        WordItem("ep_w23_4", "Like", "Писанд", "Like", "Like this photo", "Ин аксро писанд кунед", "ep_m6_l3"),
        WordItem("ep_w23_5", "Comment", "Шарҳ", "Com-ment", "Write a comment", "Шарҳ нависед", "ep_m6_l3"),
        WordItem("ep_w23_6", "Share", "Мубодила", "Share", "Share with friends", "Бо дӯстон мубодила кунед", "ep_m6_l3"),
        WordItem("ep_w23_7", "Profile", "Профил", "Pro-file", "Update your profile", "Профилро нав кунед", "ep_m6_l3"),
        WordItem("ep_w23_8", "Block", "Баста кардан", "Block", "Block a user", "Корбарро баста кунед", "ep_m6_l3"),
    ),
    grammarTip = GrammarTip(
        "Follow me on... / Did you see my post?",
        "Барои пайравӣ аз «Follow me on...» ва барои пурсидан аз «Did you see my post?» истифода баред.",
        listOf("Follow me on Instagram.", "Did you see my post?", "Please don't share private photos."),
    ),
    exercises = listOf(
        Exercise("ep_e23_1", ExerciseType.MULTIPLE_CHOICE, "«Story» дар шабака чӣ маъно дорад?", "Story = ...", listOf("Профил", "Шарҳ", "Ҳикоя", "Нашр"), "Ҳикоя", 2, "Story — Ҳикоя"),
        Exercise("ep_e23_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Did you see my _____?", listOf("follow", "block", "post", "profile"), "post", 2, "Did you see my post"),
        Exercise("ep_e23_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Follow me on comment", "Follow me on this app", "Follow me story like share", "Follow me block profile"), "Follow me on this app", 1, "Follow me on"),
        Exercise("ep_e23_4", ExerciseType.TYPE_ANSWER, "«Шарҳ»-ро ба англисӣ нависед:", "...", null, "Comment", null, "Comment — Шарҳ"),
        Exercise("ep_e23_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Like" to "Писанд", "Share" to "Мубодила", "Follow" to "Пайравӣ", "Block" to "Баста кардан")),
        Exercise("ep_e23_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I liked your comment", null, "liked your comment", words = listOf("comment", "your", "liked", "I")),
        Exercise("ep_e23_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Did you see my post?\nFiruz: _____", null, listOf("I blocked your story.", "Yes — I liked it!", "Your profile is a comment."), "Yes — I liked it!", 1, "Reaction"),
        Exercise("ep_e23_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Pro-file", listOf("Post", "Profile", "Story", "Share"), "Profile", 1, "Profile — Профил"),
    ),
)

internal val epM6L4 = Lesson(
    id = "ep_m6_l4", moduleId = "ep_m6",
    title = "Бозиҳо", description = "Бозигар, сатҳ ва онлайн",
    emoji = "\uD83C\uDFAE", orderIndex = 3,
    dialogue = Dialogue(
        "Бозӣ",
        listOf(
            DialogueLine("Firuz", "What games do you play online?", "Кадом бозиҳоро онлайн бозӣ мекунед?"),
            DialogueLine("Anna", "I use a controller — I reached level 20 last week.", "Ман контролер истифода мебарам — ҳафтаи гузашта ба сатҳи 20 расидем."),
            DialogueLine("Firuz", "Nice! My score is still low — I always lose.", "Хуб! Холи ман ҳанӯз паст аст — ҳамеша мебозам."),
            DialogueLine("Anna", "Practice more — then you will win.", "Бештар тамрин кунед — пас ғалаба мекунед."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w24_1", "Game", "Бозӣ", "Game", "A fun game", "Бозии ҷолиб", "ep_m6_l4"),
        WordItem("ep_w24_2", "Player", "Бозигар", "Play-er", "A skilled player", "Бозигари моҳир", "ep_m6_l4"),
        WordItem("ep_w24_3", "Level", "Сатҳ", "Lev-el", "Level ten", "Сатҳи даҳ", "ep_m6_l4"),
        WordItem("ep_w24_4", "Score", "Хол", "Score", "A high score", "Холи баланд", "ep_m6_l4"),
        WordItem("ep_w24_5", "Win", "Ғалаба", "Win", "I want to win", "Ман мехоҳам ғалаба кунам", "ep_m6_l4"),
        WordItem("ep_w24_6", "Lose", "Шикаст", "Lose", "Don't lose hope", "Умедро гум накунед", "ep_m6_l4"),
        WordItem("ep_w24_7", "Online", "Онлайн", "On-line", "Play online", "Онлайн бозӣ кунед", "ep_m6_l4"),
        WordItem("ep_w24_8", "Controller", "Контролер", "Con-trol-ler", "Use the controller", "Контролерро истифода баред", "ep_m6_l4"),
    ),
    grammarTip = GrammarTip(
        "What games do you play? / I reached level...",
        "Барои пурсидан «What games do you play?» ва барои пешрафт аз «I reached level...» истифода баред.",
        listOf("What games do you play?", "I reached level 15 last night.", "My score is higher now."),
    ),
    exercises = listOf(
        Exercise("ep_e24_1", ExerciseType.MULTIPLE_CHOICE, "«Score» чӣ маъно дорад?", "Score = ...", listOf("Сатҳ", "Бозигар", "Хол", "Бозӣ"), "Хол", 2, "Score — Хол"),
        Exercise("ep_e24_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I reached _____ 10.", listOf("score", "win", "level", "lose"), "level", 2, "I reached level"),
        Exercise("ep_e24_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("What games do you lose?", "What games do you play?", "What controller score online?", "What level player win?"), "What games do you play?", 1, "What games do you play"),
        Exercise("ep_e24_4", ExerciseType.TYPE_ANSWER, "«Контролер»-ро ба англисӣ нависед:", "...", null, "Controller", null, "Controller — Контролер"),
        Exercise("ep_e24_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Win" to "Ғалаба", "Lose" to "Шикаст", "Player" to "Бозигар", "Online" to "Онлайн")),
        Exercise("ep_e24_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I won the game", null, "won the game", words = listOf("game", "the", "won", "I")),
        Exercise("ep_e24_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: I always lose.\nAnna: _____", null, listOf("Your score is a controller.", "Keep practicing — you will improve.", "Online games have no level."), "Keep practicing — you will improve.", 1, "Encouragement"),
        Exercise("ep_e24_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A On-line", listOf("Level", "Online", "Win", "Lose"), "Online", 1, "Online — Онлайн"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 7 · МУНОСИБАТҲО (Relationships)
// ═══════════════════════════════════════════════════

internal val epM7L1 = Lesson(
    id = "ep_m7_l1", moduleId = "ep_m7",
    title = "Дӯстӣ", description = "Боварӣ, дастгирӣ ва сир",
    emoji = "\uD83E\uDD1D", orderIndex = 0,
    dialogue = Dialogue(
        "Дар кафе",
        listOf(
            DialogueLine("Anna", "We've been friends since school — you know all my secrets.", "Мо аз мактаб дӯст ҳастем — ҳамаи сирҳоямро медонед."),
            DialogueLine("Firuz", "A true friend is honest and loyal.", "Дӯсти ростқавл ва вафодор ҳаст."),
            DialogueLine("Anna", "You always support me when I need help.", "Ҳамеша дастгирӣ мекунед, вақте ки кӯмак лозим аст."),
            DialogueLine("Firuz", "Let's hang out this weekend — just us.", "Ин ҳафтаи охир вақтгузаронӣ кунем — танҳо мо."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w25_1", "Friend", "Дӯст", "Friend", "A good friend", "Дӯсти хуб", "ep_m7_l1"),
        WordItem("ep_w25_2", "Best friend", "Дӯсти наздик", "Best friend", "She is my best friend", "Вай дӯсти наздики ман аст", "ep_m7_l1"),
        WordItem("ep_w25_3", "Trust", "Боварӣ", "Trust", "I trust you", "Ба шумо боварӣ дорам", "ep_m7_l1"),
        WordItem("ep_w25_4", "Loyal", "Вафодор", "Loy-al", "A loyal friend", "Дӯсти вафодор", "ep_m7_l1"),
        WordItem("ep_w25_5", "Support", "Дастгирӣ", "Sup-port", "Emotional support", "Дастгирӣи эҳсосӣ", "ep_m7_l1"),
        WordItem("ep_w25_6", "Hang out", "Вақтгузаронӣ", "Hang out", "Let's hang out", "Биёед вақтгузаронӣ кунем", "ep_m7_l1"),
        WordItem("ep_w25_7", "Secret", "Сир", "Se-cret", "Keep a secret", "Сирро нигоҳ доред", "ep_m7_l1"),
        WordItem("ep_w25_8", "Honest", "Ростқавл", "Hon-est", "Be honest with me", "Бо ман рост бошед", "ep_m7_l1"),
    ),
    grammarTip = GrammarTip(
        "We've been friends since... / A true friend is...",
        "Барои муддат аз «We've been friends since...» ва барои тавсиф аз «A true friend is...» истифода баред.",
        listOf("We've been friends since 2015.", "A true friend is loyal.", "I trust my best friend."),
    ),
    exercises = listOf(
        Exercise("ep_e25_1", ExerciseType.MULTIPLE_CHOICE, "«Loyal» чӣ маъно дорад?", "Loyal = ...", listOf("Сир", "Ростқавл", "Вафодор", "Дастгирӣ"), "Вафодор", 2, "Loyal — Вафодор"),
        Exercise("ep_e25_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "We've been friends _____ school.", listOf("on", "to", "since", "for"), "since", 2, "since school"),
        Exercise("ep_e25_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("A true friend is a secret", "A true friend is honest and loyal", "A true friend hang out support", "A true friend trust best"), "A true friend is honest and loyal", 1, "A true friend is"),
        Exercise("ep_e25_4", ExerciseType.TYPE_ANSWER, "«Боварӣ»-ро ба англисӣ нависед:", "...", null, "Trust", null, "Trust — Боварӣ"),
        Exercise("ep_e25_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Friend" to "Дӯст", "Secret" to "Сир", "Support" to "Дастгирӣ", "Honest" to "Ростқавл")),
        Exercise("ep_e25_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Let's hang out tonight", null, "hang out", words = listOf("tonight", "out", "hang", "Let's")),
        Exercise("ep_e25_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Can I tell you something private?\nFiruz: _____", null, listOf("I don't have secrets.", "Of course — I won't tell anyone.", "Honest friends are loyal hang out."), "Of course — I won't tell anyone.", 1, "Trust"),
        Exercise("ep_e25_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Hon-est", listOf("Loyal", "Honest", "Trust", "Secret"), "Honest", 1, "Honest — Ростқавл"),
    ),
)

internal val epM7L2 = Lesson(
    id = "ep_m7_l2", moduleId = "ep_m7",
    title = "Ҳамсоя ва ҷамъият", description = "Кӯмак, садо ва эҳтиром",
    emoji = "\uD83C\uDFE1", orderIndex = 1,
    dialogue = Dialogue(
        "Дар маҳалла",
        listOf(
            DialogueLine("Firuz", "Could I borrow your ladder for an hour?", "Оё нардбонро барои як соат қарз мегиред?"),
            DialogueLine("Neighbor", "Sure — and would you like to come over for tea?", "Албатта — ва барои чой меохед?"),
            DialogueLine("Firuz", "Thanks! I'll try to keep the noise down.", "Ташаккур! Кӯшиш мекунам садо кам кунам."),
            DialogueLine("Neighbor", "We share tools in this community — with respect.", "Мо дар ин ҷамъият асбобҳоро мубодила мекунем — бо эҳтиром."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w26_1", "Neighbor", "Ҳамсоя", "Neigh-bor", "A kind neighbor", "Ҳамсояи меҳрубон", "ep_m7_l2"),
        WordItem("ep_w26_2", "Community", "Ҷамъият", "Com-mu-ni-ty", "Our local community", "Ҷамъияти маҳаллии мо", "ep_m7_l2"),
        WordItem("ep_w26_3", "Help", "Кӯмак", "Help", "Ask for help", "Кӯмак хоҳед", "ep_m7_l2"),
        WordItem("ep_w26_4", "Invite", "Даъват", "In-vite", "Invite friends", "Дӯстонро даъват кунед", "ep_m7_l2"),
        WordItem("ep_w26_5", "Noise", "Садо", "Noise", "Too much noise", "Садои зиёд", "ep_m7_l2"),
        WordItem("ep_w26_6", "Share", "Тақсим", "Share", "Share food", "Хӯрокро тақсим кунед", "ep_m7_l2"),
        WordItem("ep_w26_7", "Borrow", "Қарз гирифтан", "Bor-row", "Borrow a book", "Китоб қарз гиред", "ep_m7_l2"),
        WordItem("ep_w26_8", "Respect", "Эҳтиром", "Re-spect", "Show respect", "Эҳтиром нишон диҳед", "ep_m7_l2"),
    ),
    grammarTip = GrammarTip(
        "Could I borrow...? / Would you like to come over?",
        "Барои қарз аз «Could I borrow...?» ва барои даъват аз «Would you like to come over?» истифода баред.",
        listOf("Could I borrow your charger?", "Would you like to come over tonight?", "Please respect quiet hours."),
    ),
    exercises = listOf(
        Exercise("ep_e26_1", ExerciseType.MULTIPLE_CHOICE, "«Community» чӣ маъно дорад?", "Community = ...", listOf("Садо", "Ҷамъият", "Даъват", "Қарз"), "Ҷамъият", 1, "Community — Ҷамъият"),
        Exercise("ep_e26_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Could I _____ your pen?", listOf("invite", "noise", "borrow", "share"), "borrow", 2, "Could I borrow"),
        Exercise("ep_e26_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Would you like to come over help?", "Would you like to come over for coffee?", "Would you like neighbor noise respect?", "Would you like share borrow community?"), "Would you like to come over for coffee?", 1, "Would you like to come over"),
        Exercise("ep_e26_4", ExerciseType.TYPE_ANSWER, "«Эҳтиром»-ро ба англисӣ нависед:", "...", null, "Respect", null, "Respect — Эҳтиром"),
        Exercise("ep_e26_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Neighbor" to "Ҳамсоя", "Help" to "Кӯмак", "Noise" to "Садо", "Invite" to "Даъват")),
        Exercise("ep_e26_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "We share tools", null, "We share tools", words = listOf("tools", "share", "We")),
        Exercise("ep_e26_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: The music is loud.\nNeighbor: _____", null, listOf("Borrow my respect.", "Sorry — I'll turn it down.", "Our community is noise."), "Sorry — I'll turn it down.", 1, "Noise"),
        Exercise("ep_e26_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Bor-row", listOf("Share", "Borrow", "Help", "Respect"), "Borrow", 1, "Borrow — Қарз гирифтан"),
    ),
)

internal val epM7L3 = Lesson(
    id = "ep_m7_l3", moduleId = "ep_m7",
    title = "Муоширати мушкил", description = "Узр, бахшиш ва созиш",
    emoji = "\uD83E\uDEE1", orderIndex = 2,
    dialogue = Dialogue(
        "Созиш",
        listOf(
            DialogueLine("Anna", "I'm sorry for the misunderstanding — let me explain.", "Барои нодуруст фаҳмидан узр — иҷозат диҳед шарҳ диҳам."),
            DialogueLine("Firuz", "I didn't mean to blame you — I was impatient.", "Намехостам шуморо гунаҳкор кунам — сабр надоштам."),
            DialogueLine("Anna", "Let's find a compromise and resolve this calmly.", "Биёед созиш ёбем ва инро ором ҳал кунем."),
            DialogueLine("Firuz", "I forgive you. Patience is important.", "Мебахшам. Сабр муҳим аст."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w27_1", "Apologize", "Узр хостан", "Apol-o-gize", "I want to apologize", "Ман мехоҳам узр хоҳам", "ep_m7_l3"),
        WordItem("ep_w27_2", "Forgive", "Бахшидан", "For-give", "Please forgive me", "Лутфан маро бахшед", "ep_m7_l3"),
        WordItem("ep_w27_3", "Misunderstand", "Нодуруст фаҳмидан", "Mis-un-der-stand", "Don't misunderstand me", "Маро нодуруст фаҳмо накунед", "ep_m7_l3"),
        WordItem("ep_w27_4", "Explain", "Шарҳ додан", "Ex-plain", "Let me explain", "Иҷозат диҳед шарҳ диҳам", "ep_m7_l3"),
        WordItem("ep_w27_5", "Blame", "Гунаҳкор кардан", "Blame", "Don't blame others", "Дигаронро гунаҳкор накунед", "ep_m7_l3"),
        WordItem("ep_w27_6", "Compromise", "Созиш", "Com-pro-mise", "Reach a compromise", "Ба созиш расед", "ep_m7_l3"),
        WordItem("ep_w27_7", "Patience", "Сабр", "Pa-tience", "Have patience", "Сабр дошта бошед", "ep_m7_l3"),
        WordItem("ep_w27_8", "Resolve", "Ҳал кардан", "Re-solve", "Resolve the problem", "Мушкилро ҳал кунед", "ep_m7_l3"),
    ),
    grammarTip = GrammarTip(
        "I'm sorry for... / I didn't mean to...",
        "Барои узр аз «I'm sorry for...» ва барои ният аз «I didn't mean to...» истифода баред.",
        listOf("I'm sorry for being late.", "I didn't mean to hurt you.", "Let's resolve this together."),
    ),
    exercises = listOf(
        Exercise("ep_e27_1", ExerciseType.MULTIPLE_CHOICE, "«Compromise» чӣ маъно дорад?", "Compromise = ...", listOf("Сабр", "Гунаҳкор кардан", "Созиш", "Узр"), "Созиш", 2, "Compromise — Созиш"),
        Exercise("ep_e27_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I'm sorry _____ the delay.", listOf("to", "for", "at", "on"), "for", 1, "I'm sorry for"),
        Exercise("ep_e27_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I didn't mean to apologize", "I didn't mean to upset you", "I didn't mean blame compromise", "I didn't mean patience resolve"), "I didn't mean to upset you", 1, "I didn't mean to"),
        Exercise("ep_e27_4", ExerciseType.TYPE_ANSWER, "«Бахшидан»-ро ба англисӣ нависед:", "...", null, "Forgive", null, "Forgive — Бахшидан"),
        Exercise("ep_e27_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Apologize" to "Узр хостан", "Explain" to "Шарҳ додан", "Blame" to "Гунаҳкор кардан", "Patience" to "Сабр")),
        Exercise("ep_e27_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Let's resolve this problem", null, "resolve this problem", words = listOf("problem", "this", "resolve", "Let's")),
        Exercise("ep_e27_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: I misunderstood you.\nFiruz: _____", null, listOf("I blame your patience.", "It's OK — thanks for explaining.", "Compromise is misunderstanding."), "It's OK — thanks for explaining.", 1, "Resolution"),
        Exercise("ep_e27_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A For-give", listOf("Apologize", "Forgive", "Blame", "Explain"), "Forgive", 1, "Forgive — Бахшидан"),
    ),
)

internal val epM7L4 = Lesson(
    id = "ep_m7_l4", moduleId = "ep_m7",
    title = "Тӯҳфа ва табрик", description = "Зодрӯз, ҷашн ва орзу",
    emoji = "\uD83C\uDF81", orderIndex = 3,
    dialogue = Dialogue(
        "Зодрӯз",
        listOf(
            DialogueLine("Firuz", "Happy birthday! I got you something small.", "Зодрӯз муборак! Чизи хурде барои шумо овардам."),
            DialogueLine("Anna", "Thank you! You didn't have to buy a gift.", "Ташаккур! Набояд тӯҳфа мехаридед."),
            DialogueLine("Firuz", "I love parties — the cake was a surprise.", "Ман ҷашнҳоро дӯст медорам — кейк ҳайратангез буд."),
            DialogueLine("Anna", "Congratulations on your new job too! Best wishes.", "Табрик барои кори нав низ! Беҳтарин орзуҳо."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w28_1", "Gift", "Тӯҳфа", "Gift", "A birthday gift", "Тӯҳфаи зодрӯз", "ep_m7_l4"),
        WordItem("ep_w28_2", "Birthday", "Зодрӯз", "Birth-day", "Happy birthday!", "Зодрӯз муборак!", "ep_m7_l4"),
        WordItem("ep_w28_3", "Party", "Ҷашн", "Par-ty", "A fun party", "Ҷашни ҷолиб", "ep_m7_l4"),
        WordItem("ep_w28_4", "Surprise", "Ҳайрат", "Sur-prise", "What a surprise!", "Чӣ ҳайрат!", "ep_m7_l4"),
        WordItem("ep_w28_5", "Congratulations", "Табрик", "Con-grat-u-la-tions", "Congratulations!", "Табрик!", "ep_m7_l4"),
        WordItem("ep_w28_6", "Wrap", "Печондан", "Wrap", "Wrap the gift", "Тӯҳфаро печонед", "ep_m7_l4"),
        WordItem("ep_w28_7", "Card", "Корт", "Card", "A birthday card", "Корти зодрӯз", "ep_m7_l4"),
        WordItem("ep_w28_8", "Wish", "Орзу", "Wish", "Best wishes", "Беҳтарин орзуҳо", "ep_m7_l4"),
    ),
    grammarTip = GrammarTip(
        "Happy birthday! / I got you something",
        "Барои табрик аз «Happy birthday!» ва барои тӯҳфа аз «I got you something» истифода баред.",
        listOf("Happy birthday!", "I got you something special.", "Congratulations on your success!"),
    ),
    exercises = listOf(
        Exercise("ep_e28_1", ExerciseType.MULTIPLE_CHOICE, "«Congratulations» чӣ маъно дорад?", "Congratulations = ...", listOf("Орзу", "Ҳайрат", "Табрик", "Тӯҳфа"), "Табрик", 2, "Congratulations — Табрик"),
        Exercise("ep_e28_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Happy _____!", listOf("wish", "party", "birthday", "gift"), "birthday", 2, "Happy birthday"),
        Exercise("ep_e28_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I got you something surprise party", "I got you something small", "I got you card wrap congratulations", "I got you birthday best wishes"), "I got you something small", 1, "I got you something"),
        Exercise("ep_e28_4", ExerciseType.TYPE_ANSWER, "«Корт»-ро ба англисӣ нависед:", "...", null, "Card", null, "Card — Корт"),
        Exercise("ep_e28_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Gift" to "Тӯҳфа", "Party" to "Ҷашн", "Surprise" to "Ҳайрат", "Wish" to "Орзу")),
        Exercise("ep_e28_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Best wishes to you", null, "Best wishes", words = listOf("you", "to", "wishes", "Best")),
        Exercise("ep_e28_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: It's my birthday today!\nFiruz: _____", null, listOf("I wrap your card.", "Happy birthday! I hope you have a great day.", "Congratulations is a surprise gift."), "Happy birthday! I hope you have a great day.", 1, "Greeting"),
        Exercise("ep_e28_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Birth-day", listOf("Wish", "Birthday", "Party", "Gift"), "Birthday", 1, "Birthday — Зодрӯз"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 8 · ХОНА ВА ЗИНДАГӢ (Home & Living)
// ═══════════════════════════════════════════════════

internal val epM8L1 = Lesson(
    id = "ep_m8_l1", moduleId = "ep_m8",
    title = "Пухтупаз", description = "Рецепт, печ ва буридан",
    emoji = "\uD83C\uDF73", orderIndex = 0,
    dialogue = Dialogue(
        "Дар ошхона",
        listOf(
            DialogueLine("Anna", "First, you need to wash the vegetables and cut them.", "Аввал сабзавотро шуста буридан лозим."),
            DialogueLine("Firuz", "Then boil water and mix the sauce in a bowl.", "Пас об ҷӯшонед ва соусро дар коса омехта кунед."),
            DialogueLine("Anna", "Fry the onions — then bake the dish in the oven.", "Пиёзро бирён кунед — пас ғизоро дар печ бирезед."),
            DialogueLine("Firuz", "This recipe is easy — just follow the steps.", "Ин рецепт осон аст — танҳо қадамҳоро иҷро кунед."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w29_1", "Cook", "Пухтан", "Cook", "I love to cook", "Ман пухтанро дӯст медорам", "ep_m8_l1"),
        WordItem("ep_w29_2", "Recipe", "Рецепт", "Rec-i-pe", "Follow the recipe", "Рецептро иҷро кунед", "ep_m8_l1"),
        WordItem("ep_w29_3", "Boil", "Ҷӯшондан", "Boil", "Boil the pasta", "Макаронро ҷӯшонед", "ep_m8_l1"),
        WordItem("ep_w29_4", "Fry", "Бирён кардан", "Fry", "Fry the eggs", "Тухмҳоро бирён кунед", "ep_m8_l1"),
        WordItem("ep_w29_5", "Bake", "Пухтан", "Bake", "Bake a cake", "Кейк бирезед", "ep_m8_l1"),
        WordItem("ep_w29_6", "Mix", "Омехтан", "Mix", "Mix the ingredients", "Маводро омехта кунед", "ep_m8_l1"),
        WordItem("ep_w29_7", "Cut", "Буридан", "Cut", "Cut the bread", "Нонро буред", "ep_m8_l1"),
        WordItem("ep_w29_8", "Oven", "Печ", "Ov-en", "Preheat the oven", "Печро пешакӣ гарм кунед", "ep_m8_l1"),
    ),
    grammarTip = GrammarTip(
        "First, you need to... / Add a tablespoon of...",
        "Барои қадамҳо аз «First, you need to...» ва барои миқдор аз «Add a tablespoon of...» истифода баред.",
        listOf("First, you need to chop the onions.", "Add a tablespoon of oil.", "Bake for twenty minutes."),
    ),
    exercises = listOf(
        Exercise("ep_e29_1", ExerciseType.MULTIPLE_CHOICE, "«Oven» чӣ маъно дорад?", "Oven = ...", listOf("Буридан", "Печ", "Рецепт", "Ҷӯшондан"), "Печ", 1, "Oven — Печ"),
        Exercise("ep_e29_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "First, you need to _____ the onions.", listOf("bake", "boil", "cut", "mix"), "cut", 2, "First, you need to cut"),
        Exercise("ep_e29_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Add a tablespoon of oven", "Add a tablespoon of salt", "Add a tablespoon recipe fry", "Add a tablespoon mix bake"), "Add a tablespoon of salt", 1, "Add a tablespoon of"),
        Exercise("ep_e29_4", ExerciseType.TYPE_ANSWER, "«Бирён кардан»-ро ба англисӣ нависед:", "...", null, "Fry", null, "Fry — Бирён кардан"),
        Exercise("ep_e29_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Boil" to "Ҷӯшондан", "Bake" to "Пухтан", "Mix" to "Омехтан", "Cut" to "Буридан")),
        Exercise("ep_e29_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Preheat the oven", null, "Preheat the oven", words = listOf("oven", "the", "Preheat")),
        Exercise("ep_e29_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: What's next?\nFiruz: _____", null, listOf("The recipe is a bowl.", "Now fry the onions gently.", "Cut the oven mix."), "Now fry the onions gently.", 1, "Cooking steps"),
        Exercise("ep_e29_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Rec-i-pe", listOf("Cook", "Recipe", "Oven", "Boil"), "Recipe", 1, "Recipe — Рецепт"),
    ),
)

internal val epM8L2 = Lesson(
    id = "ep_m8_l2", moduleId = "ep_m8",
    title = "Тозакунӣ", description = "Чангкашак, ҷомашӯӣ ва тартиб",
    emoji = "\uD83E\uDDF9", orderIndex = 1,
    dialogue = Dialogue(
        "Рӯзи тоза",
        listOf(
            DialogueLine("Firuz", "I need to clean the kitchen — it's a mess.", "Бояд ошхонаро тоза кунам — бетартибӣ аст."),
            DialogueLine("Anna", "I'll vacuum the floor and take out the trash.", "Ман ошёнаро чангкашак мекунам ва ахлотро мебарорам."),
            DialogueLine("Firuz", "Can you help me tidy up the living room?", "Метавонед ба ман дар тоза кардани толор кӯмак кунед?"),
            DialogueLine("Anna", "Sure — I'll do the laundry after we organize the shelves.", "Албатта — пас аз тартиби рафҳо ҷомашӯӣ мекунам."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w30_1", "Clean", "Тоза кардан", "Clean", "Clean the windows", "Тирезаҳоро тоза кунед", "ep_m8_l2"),
        WordItem("ep_w30_2", "Wash", "Шустан", "Wash", "Wash the dishes", "Зарфҳоро шуед", "ep_m8_l2"),
        WordItem("ep_w30_3", "Vacuum", "Чангкашак", "Vac-u-um", "Vacuum the carpet", "Фаршро чангкашак кунед", "ep_m8_l2"),
        WordItem("ep_w30_4", "Laundry", "Ҷомашӯӣ", "Laun-dry", "Do the laundry", "Ҷомашӯӣ кунед", "ep_m8_l2"),
        WordItem("ep_w30_5", "Trash", "Ахлот", "Trash", "Take out the trash", "Ахлотро бароред", "ep_m8_l2"),
        WordItem("ep_w30_6", "Organize", "Тартиб додан", "Or-gan-ize", "Organize the closet", "Анбӯҳро тартиб диҳед", "ep_m8_l2"),
        WordItem("ep_w30_7", "Tidy", "Озода", "Ti-dy", "Keep the room tidy", "Утоқро озода нигоҳ доред", "ep_m8_l2"),
        WordItem("ep_w30_8", "Mess", "Бетартибӣ", "Mess", "What a mess!", "Чӣ бетартибӣ!", "ep_m8_l2"),
    ),
    grammarTip = GrammarTip(
        "I need to clean... / Can you help me tidy up?",
        "Барои зарурат аз «I need to clean...» ва барои кӯмак аз «Can you help me tidy up?» истифода баред.",
        listOf("I need to clean the bathroom.", "Can you help me tidy up?", "This room is a mess."),
    ),
    exercises = listOf(
        Exercise("ep_e30_1", ExerciseType.MULTIPLE_CHOICE, "«Laundry» чӣ маъно дорад?", "Laundry = ...", listOf("Ахлот", "Ҷомашӯӣ", "Чангкашак", "Бетартибӣ"), "Ҷомашӯӣ", 1, "Laundry — Ҷомашӯӣ"),
        Exercise("ep_e30_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Can you help me _____ up?", listOf("wash", "trash", "tidy", "mess"), "tidy", 2, "tidy up"),
        Exercise("ep_e30_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I need to clean the vacuum", "I need to clean the bathroom", "I need to laundry organize trash", "I need to mess tidy wash"), "I need to clean the bathroom", 1, "I need to clean"),
        Exercise("ep_e30_4", ExerciseType.TYPE_ANSWER, "«Ахлот»-ро ба англисӣ нависед:", "...", null, "Trash", null, "Trash — Ахлот"),
        Exercise("ep_e30_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Clean" to "Тоза кардан", "Wash" to "Шустан", "Vacuum" to "Чангкашак", "Organize" to "Тартиб додан")),
        Exercise("ep_e30_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Take out the trash", null, "Take out the trash", words = listOf("trash", "the", "out", "Take")),
        Exercise("ep_e30_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nFiruz: The house is messy.\nAnna: _____", null, listOf("Trash is laundry.", "Let's tidy up together.", "Vacuum the tidy mess."), "Let's tidy up together.", 1, "Tidy up"),
        Exercise("ep_e30_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Vac-u-um", listOf("Wash", "Vacuum", "Clean", "Mess"), "Vacuum", 1, "Vacuum — Чангкашак"),
    ),
)

internal val epM8L3 = Lesson(
    id = "ep_m8_l3", moduleId = "ep_m8",
    title = "Таъмир", description = "Шикаста, чакидан ва сантехник",
    emoji = "\uD83D\uDD27", orderIndex = 2,
    dialogue = Dialogue(
        "Мушкил дар хона",
        listOf(
            DialogueLine("Anna", "The sink is leaking — water everywhere!", "Мойка чакидааст — об ҳар ҷо!"),
            DialogueLine("Firuz", "I'll fix the pipe with these tools — pass me the hammer.", "Лӯларо бо ин асбобҳо таъмир мекунам — болғаро диҳед."),
            DialogueLine("Anna", "The light bulb is broken too. We need paint for the wall.", "Лампа низ шикастааст. Барои девор ранг лозим."),
            DialogueLine("Firuz", "If it's serious, I need to call a plumber.", "Агар ҷиддӣ бошад, бояд сантехникро занг занам."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w31_1", "Fix", "Таъмир кардан", "Fix", "Fix the door", "Дарро таъмир кунед", "ep_m8_l3"),
        WordItem("ep_w31_2", "Broken", "Шикаста", "Bro-ken", "A broken window", "Тирезаи шикаста", "ep_m8_l3"),
        WordItem("ep_w31_3", "Leak", "Чакидан", "Leak", "The roof is leaking", "Бом чакидааст", "ep_m8_l3"),
        WordItem("ep_w31_4", "Bulb", "Лампа", "Bulb", "Change the bulb", "Лампаро иваз кунед", "ep_m8_l3"),
        WordItem("ep_w31_5", "Tool", "Асбоб", "Tool", "Use the right tool", "Асбоби дурустро истифода баред", "ep_m8_l3"),
        WordItem("ep_w31_6", "Hammer", "Болға", "Ham-mer", "Hit with a hammer", "Бо болға задед", "ep_m8_l3"),
        WordItem("ep_w31_7", "Paint", "Рангкунӣ", "Paint", "Paint the wall", "Деворро ранг кунед", "ep_m8_l3"),
        WordItem("ep_w31_8", "Plumber", "Сантехник", "Plum-mer", "Call a plumber", "Сантехникро занг занед", "ep_m8_l3"),
    ),
    grammarTip = GrammarTip(
        "The sink is leaking / I need to call a plumber",
        "Барои чакидан аз «The sink is leaking» ва барои кас аз «I need to call a plumber» истифода баред.",
        listOf("The sink is leaking.", "The bulb is broken.", "I need to call a plumber."),
    ),
    exercises = listOf(
        Exercise("ep_e31_1", ExerciseType.MULTIPLE_CHOICE, "«Plumber» чӣ маъно дорад?", "Plumber = ...", listOf("Болға", "Сантехник", "Лампа", "Асбоб"), "Сантехник", 1, "Plumber — Сантехник"),
        Exercise("ep_e31_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "The sink is _____.", listOf("broken", "hammer", "leaking", "paint"), "leaking", 2, "The sink is leaking"),
        Exercise("ep_e31_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I need to call a bulb", "I need to call a plumber", "I need to call fix tool hammer", "I need to call paint leak broken"), "I need to call a plumber", 1, "I need to call a plumber"),
        Exercise("ep_e31_4", ExerciseType.TYPE_ANSWER, "«Шикаста»-ро ба англисӣ нависед:", "...", null, "Broken", null, "Broken — Шикаста"),
        Exercise("ep_e31_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Fix" to "Таъмир кардан", "Leak" to "Чакидан", "Tool" to "Асбоб", "Hammer" to "Болға")),
        Exercise("ep_e31_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Change the light bulb", null, "Change the light bulb", words = listOf("bulb", "light", "the", "Change")),
        Exercise("ep_e31_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Water is on the floor!\nFiruz: _____", null, listOf("The hammer is broken.", "The sink is leaking — I'll check it.", "Paint the plumber tool."), "The sink is leaking — I'll check it.", 1, "Leak"),
        Exercise("ep_e31_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Plum-mer", listOf("Tool", "Plumber", "Fix", "Bulb"), "Plumber", 1, "Plumber — Сантехник"),
    ),
)

internal val epM8L4 = Lesson(
    id = "ep_m8_l4", moduleId = "ep_m8",
    title = "Ҳайвонот", description = "Саг, гурба ва ветеринар",
    emoji = "\uD83D\uDC36", orderIndex = 3,
    dialogue = Dialogue(
        "Ҳайвони хонагӣ",
        listOf(
            DialogueLine("Anna", "I have a pet dog — he loves long walks.", "Саги хонагӣ дорам — сайрҳои дарозро дӯст медорад."),
            DialogueLine("Firuz", "I feed my cat twice a day and clean the litter box.", "Гурбаро рӯзе ду маротиба хӯрок медиҳам ва ҷӯро тоза мекунам."),
            DialogueLine("Anna", "We should visit the vet for a check-up.", "Бояд ба ветеринар барои санҷиш равем."),
            DialogueLine("Firuz", "I want to adopt a small dog from the shelter.", "Ман мехоҳам саги хурдро аз панаҳгоҳ қабул кунам."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w32_1", "Pet", "Ҳайвони хонагӣ", "Pet", "A cute pet", "Ҳайвони хонагии ширин", "ep_m8_l4"),
        WordItem("ep_w32_2", "Dog", "Саг", "Dog", "Walk the dog", "Сагро гардонед", "ep_m8_l4"),
        WordItem("ep_w32_3", "Cat", "Гурба", "Cat", "The cat sleeps a lot", "Гурба зиёд мехобад", "ep_m8_l4"),
        WordItem("ep_w32_4", "Feed", "Хӯрок додан", "Feed", "Feed the dog", "Сагро хӯрок диҳед", "ep_m8_l4"),
        WordItem("ep_w32_5", "Walk", "Гардондан", "Walk", "Walk in the park", "Дар боғ гардед", "ep_m8_l4"),
        WordItem("ep_w32_6", "Vet", "Ветеринар", "Vet", "See the vet", "Ба ветеринар равед", "ep_m8_l4"),
        WordItem("ep_w32_7", "Train", "Тарбия кардан", "Train", "Train your dog", "Сагро тарбия кунед", "ep_m8_l4"),
        WordItem("ep_w32_8", "Adopt", "Қабул кардан", "A-dopt", "Adopt a pet", "Ҳайвони хонагӣ қабул кунед", "ep_m8_l4"),
    ),
    grammarTip = GrammarTip(
        "I have a pet... / My dog loves to...",
        "Барои доштан аз «I have a pet...» ва барои завқ аз «My dog loves to...» истифода баред.",
        listOf("I have a pet cat.", "My dog loves to run.", "We adopted a puppy last year."),
    ),
    exercises = listOf(
        Exercise("ep_e32_1", ExerciseType.MULTIPLE_CHOICE, "«Adopt» чӣ маъно дорад?", "Adopt = ...", listOf("Хӯрок додан", "Қабул кардан", "Гардондан", "Тарбия кардан"), "Қабул кардан", 1, "Adopt — Қабул кардан"),
        Exercise("ep_e32_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "My dog loves to _____ in the park.", listOf("feed", "vet", "walk", "adopt"), "walk", 2, "loves to walk"),
        Exercise("ep_e32_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I have a pet train vet", "I have a pet hamster", "I have a pet walk feed cat", "I have a pet adopt dog"), "I have a pet hamster", 1, "I have a pet"),
        Exercise("ep_e32_4", ExerciseType.TYPE_ANSWER, "«Гурба»-ро ба англисӣ нависед:", "...", null, "Cat", null, "Cat — Гурба"),
        Exercise("ep_e32_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Dog" to "Саг", "Feed" to "Хӯрок додан", "Walk" to "Гардондан", "Vet" to "Ветеринар")),
        Exercise("ep_e32_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Feed the cat", null, "Feed the cat", words = listOf("cat", "the", "Feed")),
        Exercise("ep_e32_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Is your dog OK?\nFiruz: _____", null, listOf("We walk the adopt.", "We're going to the vet tomorrow.", "Train the pet feed."), "We're going to the vet tomorrow.", 1, "Vet"),
        Exercise("ep_e32_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A A-dopt", listOf("Train", "Adopt", "Pet", "Walk"), "Adopt", 1, "Adopt — Қабул кардан"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 9 · ОРЗУҲО ВА ОЯНДА (Dreams & Future)
// ═══════════════════════════════════════════════════

internal val epM9L1 = Lesson(
    id = "ep_m9_l1", moduleId = "ep_m9",
    title = "Ҳадафгузорӣ", description = "Нақша, қадам ва одат",
    emoji = "\uD83C\uDFAF", orderIndex = 0,
    dialogue = Dialogue(
        "Нақша",
        listOf(
            DialogueLine("Firuz", "My goal is to finish this course by June — I track my progress weekly.", "Ҳадафам ин аст, ки ин курсро то июн анҷом диҳам — пешрафтамро ҳафтае ченкунӣ мекунам."),
            DialogueLine("Anna", "I plan small steps — each habit matters.", "Ман қадамҳои хурдро нақша мекунам — ҳар одат муҳим аст."),
            DialogueLine("Firuz", "The deadline is strict, but I can adjust my plan.", "Мӯҳлат сахт аст, аммо нақшаро тағйир дода метавонам."),
            DialogueLine("Anna", "Measure your results — celebrate every win.", "Натиҷаҳоро чен кунед — ҳар ғалабаро ҷашн гиред."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w33_1", "Goal", "Ҳадаф", "Goal", "Set a clear goal", "Ҳадафи равшан гузоред", "ep_m9_l1"),
        WordItem("ep_w33_2", "Plan", "Нақша", "Plan", "Make a plan", "Нақша созед", "ep_m9_l1"),
        WordItem("ep_w33_3", "Achieve", "Ноил шудан", "A-chieve", "Achieve your dreams", "Ба орзуҳоятон ноил шавед", "ep_m9_l1"),
        WordItem("ep_w33_4", "Deadline", "Мӯҳлат", "Dead-line", "Meet the deadline", "Ба мӯҳлат расед", "ep_m9_l1"),
        WordItem("ep_w33_5", "Step", "Қадам", "Step", "One step at a time", "Як қадам дар як вақт", "ep_m9_l1"),
        WordItem("ep_w33_6", "Progress", "Пешрафт", "Prog-ress", "Good progress", "Пешрафти хуб", "ep_m9_l1"),
        WordItem("ep_w33_7", "Measure", "Ченкунӣ", "Meas-ure", "Measure your time", "Вақтро чен кунед", "ep_m9_l1"),
        WordItem("ep_w33_8", "Habit", "Одат", "Hab-it", "Build a good habit", "Одати хуб созед", "ep_m9_l1"),
    ),
    grammarTip = GrammarTip(
        "My goal is to... / I plan to achieve this by...",
        "Барои ҳадаф аз «My goal is to...» ва барои мӯҳлат аз «I plan to achieve this by...» истифода баред.",
        listOf("My goal is to learn English.", "I plan to achieve this by summer.", "Track your progress every week."),
    ),
    exercises = listOf(
        Exercise("ep_e33_1", ExerciseType.MULTIPLE_CHOICE, "«Deadline» чӣ маъно дорад?", "Deadline = ...", listOf("Қадам", "Нақша", "Мӯҳлат", "Одат"), "Мӯҳлат", 2, "Deadline — Мӯҳлат"),
        Exercise("ep_e33_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "My goal is to _____ fluent English.", listOf("measure", "deadline", "achieve", "habit"), "achieve", 2, "achieve fluent"),
        Exercise("ep_e33_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("My goal is to plan progress", "My goal is to travel more", "My goal is deadline habit step", "My goal is measure achieve"), "My goal is to travel more", 1, "My goal is to"),
        Exercise("ep_e33_4", ExerciseType.TYPE_ANSWER, "«Пешрафт»-ро ба англисӣ нависед:", "...", null, "Progress", null, "Progress — Пешрафт"),
        Exercise("ep_e33_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Plan" to "Нақша", "Step" to "Қадам", "Habit" to "Одат", "Measure" to "Ченкунӣ")),
        Exercise("ep_e33_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I plan to achieve this by summer", null, "I plan to achieve", words = listOf("summer", "by", "this", "achieve", "to", "plan", "I")),
        Exercise("ep_e33_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Are you making progress?\nFiruz: _____", null, listOf("My deadline is a habit.", "Yes — I finished two units this week.", "My goal is measure step."), "Yes — I finished two units this week.", 1, "Progress"),
        Exercise("ep_e33_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Hab-it", listOf("Goal", "Habit", "Plan", "Step"), "Habit", 1, "Habit — Одат"),
    ),
)

internal val epM9L2 = Lesson(
    id = "ep_m9_l2", moduleId = "ep_m9",
    title = "Касб ва оянда", description = "Орзу, муваффақият ва соҳибкор",
    emoji = "\uD83D\uDCBC", orderIndex = 1,
    dialogue = Dialogue(
        "Оянда",
        listOf(
            DialogueLine("Anna", "I dream of becoming a teacher — I love helping people learn.", "Ман орзу дорам, омӯзгор шавам — кӯмак ба омӯхтанро дӯст медорам."),
            DialogueLine("Firuz", "Some day I want to start my own business — I'm an entrepreneur at heart.", "Рӯзе мехоҳам тиҷорати худро оғоз кунам — дар дил соҳибкорам."),
            DialogueLine("Anna", "Success takes time — look for every opportunity.", "Муваффақият вақт мегирад — ҳар имкониятро ҷӯед."),
            DialogueLine("Firuz", "I'll build skills step by step.", "Маҳоратҳоро қадам ба қадам месозам."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w34_1", "Dream", "Орзу", "Dream", "Follow your dream", "Ба орзувон пайравӣ кунед", "ep_m9_l2"),
        WordItem("ep_w34_2", "Career", "Касб", "Ca-reer", "A stable career", "Касби устувор", "ep_m9_l2"),
        WordItem("ep_w34_3", "Ambition", "Ҷоҳталабӣ", "Am-bi-tion", "Strong ambition", "Ҷоҳталабии қавӣ", "ep_m9_l2"),
        WordItem("ep_w34_4", "Opportunity", "Имконият", "Op-por-tu-ni-ty", "A great opportunity", "Имконияти аъло", "ep_m9_l2"),
        WordItem("ep_w34_5", "Success", "Муваффақият", "Suc-cess", "Wish you success", "Барои муваффақият орзӯ мекунем", "ep_m9_l2"),
        WordItem("ep_w34_6", "Entrepreneur", "Соҳибкор", "En-tre-pre-neur", "Young entrepreneurs", "Соҳибкорони ҷавон", "ep_m9_l2"),
        WordItem("ep_w34_7", "Start", "Оғоз кардан", "Start", "Start today", "Имрӯз оғоз кунед", "ep_m9_l2"),
        WordItem("ep_w34_8", "Build", "Сохтан", "Build", "Build your future", "Ояндаатонро созед", "ep_m9_l2"),
    ),
    grammarTip = GrammarTip(
        "I dream of becoming... / Some day I want to...",
        "Барои орзу аз «I dream of becoming...» ва барои оянда аз «Some day I want to...» истифода баред.",
        listOf("I dream of becoming a doctor.", "Some day I want to travel abroad.", "Success needs hard work."),
    ),
    exercises = listOf(
        Exercise("ep_e34_1", ExerciseType.MULTIPLE_CHOICE, "«Entrepreneur» чӣ маъно дорад?", "Entrepreneur = ...", listOf("Касб", "Муваффақият", "Соҳибкор", "Имконият"), "Соҳибкор", 2, "Entrepreneur — Соҳибкор"),
        Exercise("ep_e34_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I dream _____ becoming a writer.", listOf("to", "of", "for", "at"), "of", 1, "dream of becoming"),
        Exercise("ep_e34_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Some day I want to ambition career", "Some day I want to move abroad", "Some day I want entrepreneur success", "Some day I want build opportunity"), "Some day I want to move abroad", 1, "Some day I want to"),
        Exercise("ep_e34_4", ExerciseType.TYPE_ANSWER, "«Муваффақият»-ро ба англисӣ нависед:", "...", null, "Success", null, "Success — Муваффақият"),
        Exercise("ep_e34_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Career" to "Касб", "Dream" to "Орзу", "Opportunity" to "Имконият", "Ambition" to "Ҷоҳталабӣ")),
        Exercise("ep_e34_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Build your skills every day", null, "Build your skills", words = listOf("day", "every", "skills", "your", "Build")),
        Exercise("ep_e34_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: What's your career dream?\nFiruz: _____", null, listOf("Opportunity is ambition.", "I dream of starting my own company.", "Success is entrepreneur build."), "I dream of starting my own company.", 1, "Career dream"),
        Exercise("ep_e34_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Opp-or-tu-ni-ty", listOf("Success", "Opportunity", "Career", "Dream"), "Opportunity", 1, "Opportunity — Имконият"),
    ),
)

internal val epM9L3 = Lesson(
    id = "ep_m9_l3", moduleId = "ep_m9",
    title = "Сафарҳои орзуӣ", description = "Макони сафар, таҷриба ва ҷаннат",
    emoji = "\u2708\uFE0F", orderIndex = 2,
    dialogue = Dialogue(
        "Орзуҳо",
        listOf(
            DialogueLine("Anna", "I've always wanted to visit Japan — rich culture and amazing food.", "Ҳамеша мехостам ба Япония равам — фарҳанги бой ва ғизои аҷоиб."),
            DialogueLine("Firuz", "It would be amazing to explore the islands — a real adventure.", "Кашф кардани ҷазираҳо аҷоиб мебуд — саргузашти воқеӣ."),
            DialogueLine("Anna", "It's on my bucket list — paradise beaches!", "Дар рӯйхати орзуҳои ман аст — соҳилҳои ҷаннатӣ!"),
            DialogueLine("Firuz", "Every journey teaches you something new.", "Ҳар сафар чизи нав омӯзонад."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w35_1", "Destination", "Макони сафар", "Des-ti-na-tion", "Choose a destination", "Макони сафарро интихоб кунед", "ep_m9_l3"),
        WordItem("ep_w35_2", "Explore", "Кашф кардан", "Ex-plore", "Explore the city", "Шаҳрро кашф кунед", "ep_m9_l3"),
        WordItem("ep_w35_3", "Adventure", "Саргузашт", "Ad-ven-ture", "A fun adventure", "Саргузашти ҷолиб", "ep_m9_l3"),
        WordItem("ep_w35_4", "Bucket list", "Рӯйхати орзуҳо", "Buck-et list", "It's on my bucket list", "Дар рӯйхати орзуҳои ман аст", "ep_m9_l3"),
        WordItem("ep_w35_5", "Culture", "Фарҳанг", "Cul-ture", "Learn about culture", "Дар бораи фарҳанг омӯзед", "ep_m9_l3"),
        WordItem("ep_w35_6", "Experience", "Таҷриба", "Ex-pe-ri-ence", "A new experience", "Таҷрибаи нав", "ep_m9_l3"),
        WordItem("ep_w35_7", "Paradise", "Ҷаннат", "Par-a-dise", "A tropical paradise", "Ҷаннати тропикӣ", "ep_m9_l3"),
        WordItem("ep_w35_8", "Journey", "Сафар", "Jour-ney", "Enjoy the journey", "Аз сафар лаззат баред", "ep_m9_l3"),
    ),
    grammarTip = GrammarTip(
        "I've always wanted to visit... / It would be amazing to...",
        "Барои орзуи дерина аз «I've always wanted to visit...» ва барои ҳайрат аз «It would be amazing to...» истифода баред.",
        listOf("I've always wanted to visit Italy.", "It would be amazing to see the Northern Lights.", "Travel is the best teacher."),
    ),
    exercises = listOf(
        Exercise("ep_e35_1", ExerciseType.MULTIPLE_CHOICE, "«Paradise» дар ин маъно чӣ?", "Paradise = ...", listOf("Сафар", "Ҷаннат", "Таҷриба", "Фарҳанг"), "Ҷаннат", 1, "Paradise — Ҷаннат"),
        Exercise("ep_e35_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I've always wanted to _____ Paris.", listOf("explore", "visit", "adventure", "journey"), "visit", 1, "visit Paris"),
        Exercise("ep_e35_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("It would be amazing to bucket list", "It would be amazing to swim there", "It would be amazing culture paradise", "It would be amazing destination experience"), "It would be amazing to swim there", 1, "It would be amazing to"),
        Exercise("ep_e35_4", ExerciseType.TYPE_ANSWER, "«Саргузашт»-ро ба англисӣ нависед:", "...", null, "Adventure", null, "Adventure — Саргузашт"),
        Exercise("ep_e35_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Explore" to "Кашф кардан", "Journey" to "Сафар", "Experience" to "Таҷриба", "Culture" to "Фарҳанг")),
        Exercise("ep_e35_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Explore a new culture", null, "Explore a new culture", words = listOf("culture", "new", "a", "Explore")),
        Exercise("ep_e35_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: Where do you want to go?\nFiruz: _____", null, listOf("My journey is paradise.", "I've always wanted to visit Greece.", "Adventure is bucket list."), "I've always wanted to visit Greece.", 1, "Dream destination"),
        Exercise("ep_e35_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Des-ti-na-tion", listOf("Journey", "Destination", "Adventure", "Explore"), "Destination", 1, "Destination — Макони сафар"),
    ),
)

internal val epM9L4 = Lesson(
    id = "ep_m9_l4", moduleId = "ep_m9",
    title = "Худшиносӣ", description = "Рушд, боварӣ ва илҳом",
    emoji = "\uD83D\uDCDA", orderIndex = 3,
    dialogue = Dialogue(
        "Рушд",
        listOf(
            DialogueLine("Firuz", "I'm working on my English — it's a daily challenge.", "Барои англисӣ кор мекунам — ин имтиҳони ҳаррӯза аст."),
            DialogueLine("Anna", "I believe I can grow if I learn new skills.", "Ман боварӣ дорам, ки агар маҳоратҳои нав омӯзам, рушд мекунам."),
            DialogueLine("Firuz", "Small wins help you overcome fear.", "Ғалабаҳои хурд ба ғалаба бар тарс кӯмак мекунанд."),
            DialogueLine("Anna", "You inspire me — keep going!", "Шумо маро илҳом мебахшед — идома диҳед!"),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w36_1", "Learn", "Омӯхтан", "Learn", "Learn every day", "Ҳар рӯз омӯзед", "ep_m9_l4"),
        WordItem("ep_w36_2", "Grow", "Рушд кардан", "Grow", "Grow as a person", "Ҳамчун шахс рушд кунед", "ep_m9_l4"),
        WordItem("ep_w36_3", "Confident", "Боваринок", "Con-fi-dent", "Feel confident", "Боваринок ҳис кунед", "ep_m9_l4"),
        WordItem("ep_w36_4", "Skill", "Маҳорат", "Skill", "Practice a skill", "Маҳоратро тамрин кунед", "ep_m9_l4"),
        WordItem("ep_w36_5", "Challenge", "Имтиҳон", "Chal-lenge", "A big challenge", "Имтиҳони калон", "ep_m9_l4"),
        WordItem("ep_w36_6", "Overcome", "Ғалаба кардан", "O-ver-come", "Overcome difficulties", "Бар мушкилӣ ғалаба кунед", "ep_m9_l4"),
        WordItem("ep_w36_7", "Believe", "Боварӣ", "Be-lieve", "Believe in yourself", "Ба худ боварӣ кунед", "ep_m9_l4"),
        WordItem("ep_w36_8", "Inspire", "Илҳом бахшидан", "In-spire", "You inspire me", "Шумо маро илҳом мебахшед", "ep_m9_l4"),
    ),
    grammarTip = GrammarTip(
        "I'm working on... / I believe I can...",
        "Барои кор аз «I'm working on...» ва барои боварӣ аз «I believe I can...» истифода баред.",
        listOf("I'm working on my pronunciation.", "I believe I can improve.", "Never stop learning."),
    ),
    exercises = listOf(
        Exercise("ep_e36_1", ExerciseType.MULTIPLE_CHOICE, "«Challenge» дар ин дарс чӣ маъно дорад?", "Challenge = ...", listOf("Маҳорат", "Имтиҳон", "Боварӣ", "Рушд"), "Имтиҳон", 1, "Challenge — Имтиҳон"),
        Exercise("ep_e36_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I believe I can _____ this.", listOf("learn", "overcome", "inspire", "skill"), "overcome", 1, "overcome this"),
        Exercise("ep_e36_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("I'm working on confident grow", "I'm working on my listening skills", "I'm working on challenge believe inspire", "I'm working on overcome learn"), "I'm working on my listening skills", 1, "I'm working on"),
        Exercise("ep_e36_4", ExerciseType.TYPE_ANSWER, "«Илҳом бахшидан»-ро ба англисӣ нависед:", "...", null, "Inspire", null, "Inspire — Илҳом бахшидан"),
        Exercise("ep_e36_5", ExerciseType.MATCH_PAIRS, "Калимаҳои мувофиқро пайваст кунед", null, null, "", null, "Ҳар як калима тарҷумаи худро дорад", pairs = listOf("Learn" to "Омӯхтан", "Grow" to "Рушд кардан", "Skill" to "Маҳорат", "Believe" to "Боварӣ")),
        Exercise("ep_e36_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I believe in myself", null, "I believe in myself", words = listOf("myself", "in", "believe", "I")),
        Exercise("ep_e36_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: This is hard.\nFiruz: _____", null, listOf("Challenge is skill.", "You can overcome it — I believe in you.", "Grow inspire confident learn."), "You can overcome it — I believe in you.", 1, "Support"),
        Exercise("ep_e36_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Con-fi-dent", listOf("Challenge", "Confident", "Grow", "Skill"), "Confident", 1, "Confident — Боваринок"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE 10 · ИМТИҲОНИ НИҲОӢ (Final Assessment)
// ═══════════════════════════════════════════════════

internal val epM10L1 = Lesson(
    id = "ep_m10_l1", moduleId = "ep_m10",
    title = "Такрори грамматика", description = "Ҷамъбасти қоидаҳо аз 1–9 модул",
    emoji = "\uD83D\uDCDD", orderIndex = 0,
    dialogue = Dialogue(
        "Аз модулҳои гузашта",
        listOf(
            DialogueLine("Teacher", "Combine present habits with future plans: I work out every day, and I want to travel next year.", "Одатҳои ҳозираро бо нақшаҳои оянда як кунед: ҳар рӯз тамрин мекунам ва мехоҳам соли оянда сафар кунам."),
            DialogueLine("Anna", "If you use 'Have you ever...?', you ask about life experience.", "Агар «Have you ever...?» гӯед, дар бораи таҷрибаи зиндагӣ мепурсед."),
            DialogueLine("Firuz", "Modal verbs: You should avoid sugar; I can't log in; Could I borrow your pen?", "Феълҳои модалӣ: бояд аз шакар канора шавед; ворид шуда наметавонам; метавонед қаламро қарз диҳед?"),
            DialogueLine("Teacher", "Now try complex sentences in the exercises below.", "Акнун ҷумлаҳои мураккабро дар машқҳои зерин санҷед."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w37_1", "Present simple", "Замони ҳозираи оддӣ", "Pre-sent sim-ple", "I work every day", "Ман ҳар рӯз кор мекунам", "ep_m10_l1"),
        WordItem("ep_w37_2", "Present perfect", "Замони ҳозираи комил", "Pre-sent per-fect", "I have visited Paris", "Ман ба Париж рафтаам", "ep_m10_l1"),
        WordItem("ep_w37_3", "Modal verb", "Феъли модалӣ", "Mo-dal verb", "You should rest", "Бояд истироҳат кунед", "ep_m10_l1"),
        WordItem("ep_w37_4", "Conditional", "Шартӣ", "Con-di-tion-al", "If it rains, I stay home", "Агар борон борад, дар хона мемонам", "ep_m10_l1"),
        WordItem("ep_w37_5", "Question form", "Шакли савол", "Ques-tion form", "How often do you study?", "Чанд вақт якбар омӯзиш мекунед?", "ep_m10_l1"),
        WordItem("ep_w37_6", "Imperative", "Фармони феълӣ", "Im-per-a-tive", "Open the door", "Дарро кушоед", "ep_m10_l1"),
        WordItem("ep_w37_7", "Reported speech", "Нақли ҷумла", "Re-port-ed speech", "He said he was tired", "Гуфт, ки хаста аст", "ep_m10_l1"),
        WordItem("ep_w37_8", "Linking words", "Пайвандкунандаҳо", "Link-ing words", "First, then, finally", "Аввал, баъд, охиран", "ep_m10_l1"),
    ),
    grammarTip = GrammarTip(
        "Review: tenses, modals, questions, and linking words",
        "Замонҳо, феълҳои модалӣ, саволҳо ва пайвандҳоро такрор кунед — дар машқҳо истифода баред.",
        listOf("How often do you exercise?", "Have you ever been to Italy?", "I'm sorry for being late."),
    ),
    exercises = listOf(
        Exercise("ep_e37_1", ExerciseType.MULTIPLE_CHOICE, "Кадом ҷумла «present perfect»-ро дуруст истифода мебарад?", "...", listOf("I go to Paris last year", "I have been to Paris twice", "I am go Paris", "I visiting Paris"), "I have been to Paris twice", 1, "Have been — таҷриба"),
        Exercise("ep_e37_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "You _____ avoid fast food every day.", listOf("can", "should", "is", "are"), "should", 1, "should avoid"),
        Exercise("ep_e37_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("How often do you exercise?", "How often you exercise?", "How do often exercise you?", "How you exercise often?"), "How often do you exercise?", 0, "Question word order"),
        Exercise("ep_e37_4", ExerciseType.TYPE_ANSWER, "Ба англисӣ: «Ман ҳангоме ба Фаронса рафтаам»", "...", null, "I have been to France", null, "Present perfect for experience"),
        Exercise("ep_e37_5", ExerciseType.MATCH_PAIRS, "Кадом қоида ба мисол мувофиқ аст?", null, null, "", null, "Ҷуфтҳо", pairs = listOf("Modal advice" to "You should...", "Apology" to "I'm sorry for...", "Frequency" to "How often...?", "Experience" to "Have you ever...?")),
        Exercise("ep_e37_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед (узр):", null, null, "I'm sorry for the misunderstanding", null, "I'm sorry for", words = listOf("misunderstanding", "the", "for", "sorry", "I'm")),
        Exercise("ep_e37_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nA: _____ \nB: Twice a week.", null, listOf("Where are you from?", "How often do you exercise?", "Have you ever been to Japan?"), "How often do you exercise?", 1, "Frequency question"),
        Exercise("ep_e37_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Should", listOf("Could", "Should", "Might", "Will"), "Should", 1, "Should — маслиҳат"),
    ),
)

internal val epM10L2 = Lesson(
    id = "ep_m10_l2", moduleId = "ep_m10",
    title = "Такрори луғат", description = "Калимаҳои калидӣ аз мавзӯъҳои гуногун",
    emoji = "\uD83D\uDCD1", orderIndex = 1,
    dialogue = Dialogue(
        "Луғат",
        listOf(
            DialogueLine("Anna", "At the airport: gate, passport, delay, luggage.", "Дар фурудгоҳ: дарвоза, паспорт, таъхир, бор."),
            DialogueLine("Firuz", "At home: vacuum, laundry, leak, plumber.", "Дар хона: чангкашак, ҷомашӯӣ, чакидан, сантехник."),
            DialogueLine("Anna", "Online: post, profile, link, log in.", "Онлайн: нашр, профил, пайванд, ворид шудан."),
            DialogueLine("Firuz", "Health: symptom, prescription, relax, balance.", "Саломатӣ: аломат, нусха, оромӣ, мувозинат."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w38_1", "Travel set", "Маҷмӯаи сафар", "Trav-el set", "Airport vocabulary", "Луғати фурудгоҳ", "ep_m10_l2"),
        WordItem("ep_w38_2", "Home set", "Маҷмӯаи хона", "Home set", "Cleaning vocabulary", "Луғати тозакунӣ", "ep_m10_l2"),
        WordItem("ep_w38_3", "Tech set", "Маҷмӯаи техника", "Tech set", "Digital vocabulary", "Луғати рақамӣ", "ep_m10_l2"),
        WordItem("ep_w38_4", "Health set", "Маҷмӯаи саломатӣ", "Health set", "Body and care", "Бадан ва нигоҳубин", "ep_m10_l2"),
        WordItem("ep_w38_5", "People set", "Маҷмӯаи муносибат", "Peo-ple set", "Friends and community", "Дӯстон ва ҷамъият", "ep_m10_l2"),
        WordItem("ep_w38_6", "Phrase", "Ибора", "Phrase", "Useful phrases", "Ибораҳои фоиданок", "ep_m10_l2"),
        WordItem("ep_w38_7", "Collocation", "Ҷуфткалима", "Col-lo-ca-tion", "Strong collocations", "Ҷуфткалимаҳои қавӣ", "ep_m10_l2"),
        WordItem("ep_w38_8", "Context", "Муҳит", "Con-text", "Guess from context", "Аз муҳит пеш буред", "ep_m10_l2"),
    ),
    grammarTip = GrammarTip(
        "Group words by topic — recall faster",
        "Калимаҳоро ба мавзӯъ ҷудо кунед — баъд тезтар ба ёд меоред.",
        listOf("Make a mind map for travel words.", "Review ten words every night.", "Use new words in a sentence."),
    ),
    exercises = listOf(
        Exercise("ep_e38_1", ExerciseType.MULTIPLE_CHOICE, "Кадом калима ба «фурудгоҳ» мансуб аст?", "...", listOf("Plumber", "Gate", "Meditation", "Recipe"), "Gate", 1, "Airport lex"),
        Exercise("ep_e38_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "I need to call a _____.", listOf("gate", "browser", "plumber", "post"), "plumber", 2, "plumber"),
        Exercise("ep_e38_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Turn off notifications", "Turn off the elevator", "Turn off passport", "Turn off culture"), "Turn off notifications", 0, "Tech + home"),
        Exercise("ep_e38_4", ExerciseType.TYPE_ANSWER, "Ба англисӣ нависед: «Пешрафт»", "...", null, "Progress", null, "Progress"),
        Exercise("ep_e38_5", ExerciseType.MATCH_PAIRS, "Мавзӯъро калима мувофиқ кунед", null, null, "", null, "Гурӯҳбандӣ", pairs = listOf("Passport" to "Travel", "Vacuum" to "Home", "Screenshot" to "Tech", "Symptom" to "Health")),
        Exercise("ep_e38_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "Follow me on this app", null, "Social media phrase", words = listOf("app", "this", "on", "me", "Follow")),
        Exercise("ep_e38_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nA: My sink is leaking.\nB: _____", null, listOf("Book a flight.", "You should call a plumber.", "Download an app."), "You should call a plumber.", 1, "Home repair vocab"),
        Exercise("ep_e38_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Symp-tom", listOf("Prescription", "Symptom", "Luggage", "Elevator"), "Symptom", 1, "Symptom — Аломат"),
    ),
)

internal val epM10L3 = Lesson(
    id = "ep_m10_l3", moduleId = "ep_m10",
    title = "Муколамаи комплексӣ", description = "Гуфтугӯи пешрафта дар бораи ҳаёт",
    emoji = "\uD83D\uDCAC", orderIndex = 2,
    dialogue = Dialogue(
        "Як рӯзи пур",
        listOf(
            DialogueLine("Anna", "I'm stressed about work, but I want to stay healthy — I run and try to eat well.", "Аз кор стресс дорам, аммо мехоҳам солим бимонам — медавам ва кӯшиш мекунам хуб хӯрам."),
            DialogueLine("Firuz", "Me too. Next month I fly abroad — I booked a hotel with breakfast.", "Ман ҳам. Моҳи оянда ба хориҷ мепарвам — меҳмонхона бо наҳорӣ захира кардам."),
            DialogueLine("Anna", "Amazing! Send me the link to your photos — I'll follow your trip online.", "Аҷоиб! Пайванд ба аксҳоятон фиристед — сафаратонро онлайн пайравӣ мекунам."),
            DialogueLine("Firuz", "Deal — and when I'm back, let's hang out and celebrate.", "Хуб — вақте бозам омад, биёед вақтгузаронӣ ва ҷашн гирем."),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w39_1", "Balance life", "Мувозинати ҳаёт", "Bal-ance life", "Work-life balance", "Мувозинати кор ва ҳаёт", "ep_m10_l3"),
        WordItem("ep_w39_2", "Trip", "Сафар", "Trip", "A short trip", "Сафари кӯтоҳ", "ep_m10_l3"),
        WordItem("ep_w39_3", "Abroad", "Берун аз кишвар", "A-broad", "Study abroad", "Дар хориҷ омӯзед", "ep_m10_l3"),
        WordItem("ep_w39_4", "Stay in touch", "Дар алоқа мондан", "Stay in touch", "Let's stay in touch", "Биёед дар алоқа бимонем", "ep_m10_l3"),
        WordItem("ep_w39_5", "Plan ahead", "Пешакӣ нақша кардан", "Plan a-head", "Plan ahead for travel", "Барои сафар пешакӣ нақша кунед", "ep_m10_l3"),
        WordItem("ep_w39_6", "Catch up", "Ахбор гирифтан", "Catch up", "Let's catch up soon", "Биёед зуд ахбор гирем", "ep_m10_l3"),
        WordItem("ep_w39_7", "Make time", "Вақт ҷудо кардан", "Make time", "Make time for friends", "Барои дӯстон вақт ҷудо кунед", "ep_m10_l3"),
        WordItem("ep_w39_8", "Keep going", "Идома додан", "Keep go-ing", "Keep going — you got this!", "Идома диҳед — шумо мешавад!", "ep_m10_l3"),
    ),
    grammarTip = GrammarTip(
        "Link ideas: health + travel + friends",
        "Саломатӣ, сафар ва дӯстонро дар як гуфтугӯ пайваст кунед — мислҳои дарсро истифода баред.",
        listOf("I'm stressed, but I exercise.", "I booked a room with breakfast.", "Let's catch up after my trip."),
    ),
    exercises = listOf(
        Exercise("ep_e39_1", ExerciseType.MULTIPLE_CHOICE, "Дар муколама «I booked a hotel with breakfast» чӣ маъно дорад?", "...", listOf("Breakfast is free at the hotel", "The hotel is only breakfast", "I ate breakfast on the plane", "I hate hotels"), "Breakfast is free at the hotel", 0, "include breakfast"),
        Exercise("ep_e39_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "Send me the _____ to the photos.", listOf("gate", "link", "plumber", "symptom"), "link", 1, "send the link"),
        Exercise("ep_e39_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Let's hang out when you return", "Let's hang out the airport gate", "Let's hang out prescription plumber", "Let's hang out vacuum laundry"), "Let's hang out when you return", 0, "Social plan"),
        Exercise("ep_e39_4", ExerciseType.TYPE_ANSWER, "Ба англисӣ: «Дар алоқа бимонем» (кӯтоҳ)", "...", null, "Stay in touch", null, "Stay in touch"),
        Exercise("ep_e39_5", ExerciseType.MATCH_PAIRS, "Ибора ва маъно", null, null, "", null, "Phrases", pairs = listOf("Work-life balance" to "Кор ва ҳаёт", "Study abroad" to "Дар хориҷ", "Catch up" to "Ахбор гирифтан", "Plan ahead" to "Пешакӣ нақша")),
        Exercise("ep_e39_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "I try to eat healthy food", null, "Healthy habits", words = listOf("food", "healthy", "eat", "to", "try", "I")),
        Exercise("ep_e39_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nAnna: I'm flying tomorrow.\nFiruz: _____", null, listOf("Your sink is leaking.", "Safe trip — send me a message when you land!", "Download the gate."), "Safe trip — send me a message when you land!", 1, "Travel reply"),
        Exercise("ep_e39_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A A-broad", listOf("Abroad", "Board", "Broad", "Abroad's"), "Abroad", 0, "Abroad — берун аз кишвар"),
    ),
)

internal val epM10L4 = Lesson(
    id = "ep_m10_l4", moduleId = "ep_m10",
    title = "Имтиҳони ниҳоӣ", description = "Машқҳои душвор — ҳама намудҳо",
    emoji = "\uD83C\uDFC6", orderIndex = 3,
    dialogue = Dialogue(
        "Омода?",
        listOf(
            DialogueLine("Teacher", "This final exam mixes grammar, vocabulary, and real situations.", "Ин имтиҳони ниҳоӣ грамматика, луғат ва вазъиятҳои воқеиро омехта мекунад."),
            DialogueLine("Anna", "I'll read each question twice — no rush.", "Ҳар саволро ду маротиба мехонам — шитоб нест."),
            DialogueLine("Firuz", "If I don't know a word, I'll use context — like in the dialogue.", "Агар калимае намедонам, аз муҳит истифода мекунам — мисли муколама."),
            DialogueLine("Teacher", "Good luck — show everything you've learned!", "Муваффақ бошед — ҳар чизеро нишон диҳед, ки омӯхтаед!"),
        ),
    ),
    newWords = listOf(
        WordItem("ep_w40_1", "Exam", "Имтиҳон", "Ex-am", "Final exam", "Имтиҳони ниҳоӣ", "ep_m10_l4"),
        WordItem("ep_w40_2", "Strategy", "Стратегия", "Strat-e-gy", "A clear strategy", "Стратегияи равшан", "ep_m10_l4"),
        WordItem("ep_w40_3", "Time limit", "Маҳдудияти вақт", "Time lim-it", "Watch the time limit", "Маҳдудияти вақтро назорат кунед", "ep_m10_l4"),
        WordItem("ep_w40_4", "Double-check", "Ду маротиба санҷидан", "Dou-ble-check", "Double-check your answers", "Ҷавобҳоро ду маротиба санҷед", "ep_m10_l4"),
        WordItem("ep_w40_5", "Confidence", "Боварӣ ба худ", "Con-fi-dence", "Speak with confidence", "Бо боварӣ ба худ гӯед", "ep_m10_l4"),
        WordItem("ep_w40_6", "Mistake", "Хато", "Mis-take", "Learn from mistakes", "Аз хатоҳо омӯзед", "ep_m10_l4"),
        WordItem("ep_w40_7", "Improvement", "Беҳбудӣ", "Im-prove-ment", "Clear improvement", "Беҳбудии равшан", "ep_m10_l4"),
        WordItem("ep_w40_8", "Certificate", "Гувоҳӣ", "Cer-tif-i-cate", "Course certificate", "Гувоҳии курс", "ep_m10_l4"),
    ),
    grammarTip = GrammarTip(
        "Final tips: read, plan, answer, review",
        "Хонед, нақша кунед, ҷавоб диҳед, санҷед — ҳамон услуб, ки дар тамоми курс омӯхтед.",
        listOf("Underline key words in the prompt.", "Eliminate wrong answers first.", "Say the sentence aloud if it helps."),
    ),
    exercises = listOf(
        Exercise("ep_e40_1", ExerciseType.MULTIPLE_CHOICE, "Кадом ҷумла грамматикӣ дуруст аст?", "...", listOf("She don't like coffee", "She doesn't like coffee", "She not like coffee", "She doesn't likes coffee"), "She doesn't like coffee", 1, "Third person"),
        Exercise("ep_e40_2", ExerciseType.FILL_BLANK, "Ҷои холиро пур кунед:", "If it rains, I _____ at home.", listOf("stay", "stays", "staying", "stayed"), "stay", 0, "First conditional"),
        Exercise("ep_e40_3", ExerciseType.TRANSLATE_SENTENCE, "Тарҷумаи дурустро интихоб кунед:", null, listOf("Could I borrow your pen, please?", "Could I borrowed your pen?", "Could borrow I your pen?", "I could borrow your pen please?"), "Could I borrow your pen, please?", 0, "Polite request"),
        Exercise("ep_e40_4", ExerciseType.TYPE_ANSWER, "Ба англисӣ: «Ман ҳадафам ин аст, ки англисӣ беҳтар кунам»", "...", null, "My goal is to improve my English", null, "Goal sentence"),
        Exercise("ep_e40_5", ExerciseType.MATCH_PAIRS, "Кадом ҷуфт дуруст аст?", null, null, "", null, "Advanced pairs", pairs = listOf("I'm sorry for" to "Apologizing", "Have you ever" to "Life experience", "I'd like to book" to "Hotel", "I can't log in" to "Tech problem")),
        Exercise("ep_e40_6", ExerciseType.BUILD_SENTENCE, "Ин ҷумларо созед:", null, null, "What a beautiful view from here", null, "Exclamation", words = listOf("here", "from", "view", "beautiful", "a", "What")),
        Exercise("ep_e40_7", ExerciseType.DIALOGUE_COMPLETE, "Ҷои холиро пур кунед:\nA: I feel stressed because of exams.\nB: _____", null, listOf("Book a double room.", "Take breaks and breathe — you can do this.", "Your flight is delayed."), "Take breaks and breathe — you can do this.", 1, "Empathy + advice"),
        Exercise("ep_e40_8", ExerciseType.LISTEN_CHOOSE, "Кадом калима дуруст аст?", "\uD83D\uDD0A Im-prove-ment", listOf("Certificate", "Improvement", "Strategy", "Mistake"), "Improvement", 1, "Improvement — Беҳбудӣ"),
    ),
)

// ═══════════════════════════════════════════════════
//  MODULE DEFINITIONS (4–10)
// ═══════════════════════════════════════════════════

internal val epModule4 = Module(
    id = "ep_m4", courseId = "personal_english",
    title = "Саломатӣ ва варзиш",
    description = "Варзиш, ғизо, духтур ва саломатии рӯҳӣ",
    emoji = "\uD83C\uDFCB\uFE0F", orderIndex = 3,
    lessons = listOf(epM4L1, epM4L2, epM4L3, epM4L4),
)

internal val epModule5 = Module(
    id = "ep_m5", courseId = "personal_english",
    title = "Сафар ва ҷаҳон",
    description = "Кишварҳо, фурудгоҳ, меҳмонхона ва сайёҳӣ",
    emoji = "\uD83C\uDF0D", orderIndex = 4,
    lessons = listOf(epM5L1, epM5L2, epM5L3, epM5L4),
)

internal val epModule6 = Module(
    id = "ep_m6", courseId = "personal_english",
    title = "Технология",
    description = "Смартфон, интернет, шабакаҳо ва бозиҳо",
    emoji = "\uD83D\uDCF1", orderIndex = 5,
    lessons = listOf(epM6L1, epM6L2, epM6L3, epM6L4),
)

internal val epModule7 = Module(
    id = "ep_m7", courseId = "personal_english",
    title = "Муносибатҳо",
    description = "Дӯстӣ, ҳамсоя, муоширати мушкил ва ҷашнҳо",
    emoji = "\uD83E\uDD1D", orderIndex = 6,
    lessons = listOf(epM7L1, epM7L2, epM7L3, epM7L4),
)

internal val epModule8 = Module(
    id = "ep_m8", courseId = "personal_english",
    title = "Хона ва зиндагӣ",
    description = "Пухтупаз, тозакунӣ, таъмир ва ҳайвонот",
    emoji = "\uD83C\uDFE0", orderIndex = 7,
    lessons = listOf(epM8L1, epM8L2, epM8L3, epM8L4),
)

internal val epModule9 = Module(
    id = "ep_m9", courseId = "personal_english",
    title = "Орзуҳо ва оянда",
    description = "Ҳадафҳо, касб, сафарҳои орзуӣ ва худшиносӣ",
    emoji = "\uD83C\uDF1F", orderIndex = 8,
    lessons = listOf(epM9L1, epM9L2, epM9L3, epM9L4),
)

internal val epModule10 = Module(
    id = "ep_m10", courseId = "personal_english",
    title = "Имтиҳони ниҳоӣ",
    description = "Такрори грамматика ва луғат, муколама ва имтиҳон",
    emoji = "\uD83C\uDFC6", orderIndex = 9,
    lessons = listOf(epM10L1, epM10L2, epM10L3, epM10L4),
)
