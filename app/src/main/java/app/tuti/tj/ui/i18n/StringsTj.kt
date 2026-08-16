package app.tuti.tj.ui.i18n

// ════════════════════════════════════════════════════════════════
//  ТАДЖИКСКИЙ — язык приложения по умолчанию.
//
//  Строки перенесены из экранов как есть: это тот текст, который
//  пользователи видели до появления второго языка, и менять его
//  вместе с локализацией не нужно.
// ════════════════════════════════════════════════════════════════

object TjStrings : TutiStrings {
    override val language = AppLanguage.TAJIK
    override val common = TjCommon
    override val nav = TjNav
    override val onboarding = TjOnboarding
    override val finalStep = TjFinalStep
    override val home = TjHome
    override val lessons = TjLessons
    override val lessonFlow = TjLessonFlow
    override val practice = TjPractice
    override val profile = TjProfile
    override val achievements = TjAchievements
    override val leaderboard = TjLeaderboard
    override val plus = TjPlus
    override val chat = TjChat
    override val notifications = TjNotifications
    override val tooltips = TjTooltips
    override val auth = TjAuth
    override val cities = TjCities
}

object TjCommon : CommonStrings {
    override val back = "← Бозгашт"
    override val backArrow = "←"
    override val previousArrow = "← Пеш"
    override val continueShort = "Давом"
    override val continueLong = "Давом додан"
    override val check = "Санҷидан"
    override val skip = "Гузаштан"
    override val skipArrow = "Гузаштан →"
    override val startAction = "Оғоз кардан!"
    override val retry = "Аз нав кӯшиш кунед"
    override val tryAgain = "Боз кӯшиш"
    override val loading = "Лаҳзае сабр кунед…"
    override val restart = "Аз нав"
    override val toHome = "Ба асосӣ"
    override val next = "Идома"
    override val result = "Натиҷа"
    override val exit = "Баромадан"
    override val cancel = "Бекор кардан"
    override val points = "очки"
    override val stars = "ситора"
    override val accuracy = "дақиқӣ"
    override val lessonsLabel = "Дарсҳо"
    override val wordsLabel = "Калимаҳо"
    override val streakLabel = "Серия"
    override val rankLabel = "Ҷойгоҳ"
    override val done = "Тамом"
    override val user = "Корбар"
    override val correctTitle = "Офарин! Дуруст!"
    override val wrongTitle = "Нодуруст…"
    override val resultExcellent = "Аъло! 🎉"
    override val resultGood = "Хуб! 👍"
    override val resultTryHarder = "Кӯшиш кунед! 💪"

    override fun xp(amount: Int) = "+$amount очки"
    override fun correctAnswer(answer: String) = "Ҷавоби дуруст: $answer"
    override fun ofCount(current: Int, total: Int) = "$current аз $total"
}

object TjNav : NavStrings {
    override val home = "Асосӣ"
    override val lessons = "Дарсҳо"
    override val practice = "Машқ"
    override val profile = "Профил"
    override val leaderboard = "Рейтинг"
}

object TjOnboarding : OnboardingStrings {
    override val pickLanguageTitle = "Забони барномаро интихоб кунед"
    override val pickLanguageAction = "Барнома бо забони тоҷикӣ"
    override val pickLanguageHint = "Баъдтар дар профил иваз мешавад"

    override val tagline = "Забонҳоро осон омӯзед"
    override val googleSignIn = "Бо Google ворид шавед"
    override val terms = "Бо ворид шудан шумо шартҳои истифода\n" +
        "ва сиёсати махфиятро қабул мекунед"
    override val cloudRestored = "☁️ Маълумоти шумо барқарор шуд!"

    override val languageTitle = "Кадом забон?"
    override val languageSubtitle = "Кадом забонро омӯхтан мехоҳед?"
    override val levelTitle = "Сатҳи шумо?"
    override val levelSubtitleEnglish = "Забони англисиро чӣ қадар медонед?"
    override val levelSubtitleRussian = "Забони русиро чӣ қадар медонед?"
    override val goalTitle = "Ҳадафи шумо?"
    override val goalSubtitle = "Барои чӣ забон меомӯзед?"
    override val timeTitle = "Вақти омӯзиш?"
    override val timeSubtitle = "Дар як рӯз чанд вақт омӯхтан мехоҳед?"
    override val cityTitle = "Шаҳри шумо?"
    override val citySubtitle = "Барои рейтинги шаҳрҳо"

    override val optionRussian = "Русский язык"
    override val optionRussianHint = "Забони русӣ"
    override val optionEnglish = "English"
    override val optionEnglishHint = "Забони англисӣ"

    override val levelBeginner = "Ибтидоӣ"
    override val levelBeginnerHint = "Ман навам"
    override val levelIntermediate = "Миёна"
    override val levelIntermediateHint = "Каме медонам"
    override val levelAdvanced = "Пешрафта"
    override val levelAdvancedHint = "Хуб медонам"

    override val goalWork = "Барои кор"
    override val goalWorkHint = "Кор дар Русия/хориҷа"
    override val goalStudy = "Барои таҳсил"
    override val goalStudyHint = "Донишгоҳ/мактаб"
    override val goalTravel = "Барои сафар"
    override val goalTravelHint = "Сайёҳӣ ва муҳоҷират"
    override val goalPersonal = "Барои худам"
    override val goalPersonalHint = "Рушди шахсӣ"

    override val timeCalm = "Оҳиста"
    override val timeModerate = "Мӯътадил"
    override val timeSerious = "Ҷиддӣ"
    override val timeMax = "Максимум"

    override val readyTitle = "Ҳама чиз тайёр! 🎉"
    override val readySubtitle = "Tuti курси шуморо тайёр кард.\nБиёед оғоз кунем!"

    override val offlineTitle = "Интернет нест"
    override val offlineMessage =
        "Барои оғоз пайвасти интернет лозим аст.\nҲамагӣ як бор."

    override fun minutes(count: Int) = "$count дақиқа"
}

object TjFinalStep : FinalStepStrings {
    override val subtitle =
        "Пешрафти худро нигоҳ доред — ҳангоми иваз кардани телефон\nҳама чиз бо шумо мемонад."

    override val summaryTitle = "Интихоби шумо"
    override val labelLanguage = "Забон"
    override val labelGoal = "Ҳадаф"
    override val labelLevel = "Сатҳ"
    override val labelCity = "Шаҳр"
    override val labelDailyTime = "Ҳар рӯз"

    override val saveWithGoogle = "Бо Google нигоҳ доштан"
    override val help = "Кӯмак"

    override val restoredTitle = "Хуш омадед бозгашт! 🎉"
    override val restoredMessage = "Пешрафти шумо баргардонида шуд."

    override val errorNoNetwork = "Интернет нест. Пайвастро санҷед."
    override val errorPlayServices =
        "Хидматҳои Google дар ин телефон дастрас нестанд."
    override val errorAccountConflict =
        "Ин аккаунт аллакай истифода мешавад. Пешрафти шуморо бармегардонем."
    override val errorUnknown = "Ворид шудан нашуд. Дубора кӯшиш кунед."
}

object TjCities : CityStrings {
    override fun name(dbValue: String) = dbValue

    override val regionCapital = "Пойтахт"
    override val regionNorth = "Шимол"
    override val regionSouth = "Ҷануб"
    override val regionKhatlon = "Хатлон"
    override val regionSughd = "Суғд"
    override val regionCentral = "НТМ"
    override val regionOther = "Шаҳри дигар"
}

object TjHome : HomeStrings {
    override val weekDays = listOf("Дш", "Сш", "Чш", "Пш", "Ҷм", "Шн", "Яш")

    override fun greetings(streak: Int, isPlus: Boolean) = listOf(
        "Салом! 👋",
        if (streak > 0) "🔥 $streak рӯз" else "Оғоз кунем! 🚀",
        "Омода ҳастед?",
        "Як дарс кофӣ ✨",
        "Имрӯз чӣ омӯзем?",
        "Вақти машқ! ⏰",
        if (isPlus) "Plus фаъол ⭐" else "Забон осон аст!",
        "Аъло меравед! 👏",
        "Ман интизорам 🦜",
        "Ҳар рӯз як қадам",
        "Сӯҳбат кунем? 💬",
        "Давом диҳед! 💪",
    )

    override val switchLanguage = "Иваз кун"
    override val russianLanguage = "Русский язык"
    override val englishLanguage = "English"
    override val bothLanguages = "Русӣ ва Англисӣ"
    override val russianLanguageHint = "Забони русӣ"
    override val englishLanguageHint = "Забони англисӣ"

    override val levelBeginner = "Навомӯз"
    override val levelElementary = "Ибтидоӣ"
    override val levelIntermediate = "Миёна"
    override val levelAdvanced = "Пешрафта"

    override val weeklyStreak = "Ҳафта"
    override val chooseLanguageTitle = "Кадом забон?"
    override val chooseLanguageMessage = "Забони омӯзишро интихоб кунед"
    override val chooseLanguageConfirm = "Интихоб кардан"

    override val yourCourse = "Курси шумо"
    override val seeAll = "Ҳама →"
    override val dailyLimit = "Лимити имрӯза"
    override val getPlus = "Plus гиред"
    override val freeTopics = "Мавзуъҳои озод"
    override val finished = "Тамом"

    override fun daysThisWeek(done: Int) = "$done аз 7 рӯз"
    override fun lessonsProgress(done: Int, total: Int) = "$done аз $total дарс"
    override fun continueLesson(title: String) = "Давоми дарс: $title"
    override fun topicsCount(count: Int) = "$count мавзуъ"
}

object TjLessons : LessonsStrings {
    override val noQuestionsTitle = "Саволҳо ёфт нашуданд"
    override val noQuestionsMessage = "Ин мавзӯъ ҳоло саволҳо надорад."

    override val typeTranslate = "Тарҷума"
    override val typeChoose = "Интихоб"
    override val typeFillBlank = "Пур кунед"
    override val typeMatch = "Мувофиқ"
    override val typeListen = "Гӯш кунед"

    override val title = "Дарсҳо"
    override val chooseTopic = "Мавзӯъро интихоб кунед"
    override val yourProgress = "Пешрафти шумо"
    override val courseNotFoundTitle = "Курс ёфт нашуд"
    override val courseNotFoundMessage = "Ин курс дастрас нест. Мавзӯъҳои озодро санҷед."
    override val startCourse = "Оғоз кунед →"
    override val restartTopic = "Аз нав 🔄"
    override val startTopic = "Оғоз →"
    override val continueTopic = "Давом →"

    override val wordsTitle = "Калимаҳо"
    override val toQuiz = "Санҷиш →"
    override val startQuiz = "Санҷишро оғоз кунед"
    override val tapForTranslation = "👆 Барои дидани тарҷума зер кунед"

    override fun topicsDone(done: Int, total: Int) = "$done аз $total мавзӯъ тамом шуд"
    override fun wordsCount(count: Int) = "$count калима"
    override fun questionsCount(count: Int) = "$count савол"
    override fun percentDone(percent: Int) = "$percent% тамом шуд"
    override fun lessonsDone(done: Int, total: Int) = "$done аз $total дарс тамом шуд"
    override fun correctOf(correct: Int, total: Int) =
        "Шумо $correct аз $total дуруст ҷавоб додед!"
}

object TjLessonFlow : LessonFlowStrings {
    override val notFoundTitle = "Дарс ёфт нашуд"
    override val notFoundMessage = "Ин дарс дастрас нест. Ба рӯйхати дарсҳо баргардед."
    override val exitTitle = "Баромадан?"
    override val exitMessage = "Пешрафти шумо дар ин дарс захира намешавад."
    override val exitStay = "Не, мемонам"
    override val exitConfirm = "Ҳа, мебароям"

    override val praise = "Офарин! 🎉 Шумо аъло кор мекунед! Давом диҳед!"
    override val encourage = "Хато кардед — ин муҳим нест! Аз хатоҳо меомӯзем! 💪"

    override val dialogueHint = "Аввал диалогро хонед! Ин суҳбати воқеӣ аст 📖"
    override val dialogueTapForTranslation = "Барои тарҷума ба ҷумла зер кунед"
    override val newWordsButton = "Калимаҳои нав"
    override val translationArrow = "тарҷума →"
    override val learnNewWords = "Калимаҳои навро ёд гиред! ⭐"
    override val newWords = "Калимаҳои нав"
    override val nextWord = "Баъдӣ"

    override val grammarHint = "Ин қоидаи грамматика аст! Хуб хонед 📝"
    override val grammarTitle = "Грамматика"
    override val grammarUnderstood = "Фаҳмидам!"

    override val noExerciseTitle = "Машқ ёфт нашуд"
    override val exerciseHint = "Вариантро интихоб кунед ва «Санҷидан»-ро пахш кунед! 🎯"

    override val congratsFirstLesson = "Табрик! Дарси аввалро тамом кардед! Ситораҳо гиред! 🌟"
    override val correctLabel = "Дуруст"
    override val wordLabel = "Калима"

    override val heartsOverTitle = "Дилҳо тамом шуданд"
    override val heartsOverMessage =
        "Ин муҳим нест — ҳама аз хатоҳо меомӯзанд.\nАз нав оғоз кунед!"

    override fun exerciseCounter(current: Int, total: Int) = "Машқи $current аз $total"
}

object TjPractice : PracticeStrings {
    override val title = "Машқ"
    override val chooseMode = "Навъи машқро интихоб кунед"

    override val chatTitle = "Муаллими Tuti"
    override val chatDescription = "Бо AI сӯҳбат кунед ва машқ кунед"
    override val flashcardsTitle = "Корти калимаҳо"
    override val flashcardsDescription = "Такрори интервалӣ"
    override val listeningTitle = "Гӯш кунед"
    override val listeningDescription = "Машқи шунавоӣ"
    override val writingTitle = "Навиштан"
    override val writingDescription = "Машқи имло"

    override val limitReached = "Лимит тамом"
    override val unlimited = "Бемаҳдуд"

    override val preparingCards = "Кортҳоро тайёр мекунем…"
    override val preparingPractice = "Машқро тайёр мекунем…"

    override val learnFirstTitle = "Аввал дарсро гузаред!"
    override val learnFirstMessage =
        "Пас аз омӯхтани калимаҳо онҳоро дар ин ҷо такрор карда метавонед."
    override val toLessons = "Ба дарсҳо рафтан"
    override val notEnoughWordsTitle = "Аввал калимаҳоро омӯзед!"

    override val flashcardsHint =
        "Кортро пахш кунед барои дидани тарҷума! «Медонам» ё «Намедонам» интихоб кунед 🃏"
    override val dontKnow = "Намедонам"
    override val know = "Медонам"
    override val tapForTranslation = "👆 Барои дидани тарҷума зер кунед"
    override val knownWords = "калима донистед"
    override val needRepeat = "такрор лозим"

    override val listenAndWrite = "Гӯш кунед ва калимаро нависед"
    override val writeWordPlaceholder = "Калимаро нависед…"
    override val wrongTryAgain = "Нодуруст! Боз кӯшиш кунед"

    override val listenAndChoose = "Гӯш кунед ва тарҷумаи дурустро интихоб кунед"

    override val correctAnswerLabel = "дуруст навишт"
    override val wrongAnswerLabel = "хато"

    override val exerciseUnavailable = "Ин машқ дастрас нест"
    override val noOptionsError = "Хатогӣ: варианти ҷавоб нест"
    override val tapWordsPlaceholder = "Калимаҳоро зер кунед…"
    override val fillTheBlank = "Ҷои холиро пур кунед:"
    override val writeAnswerPlaceholder = "Ҷавобро нависед…"

    override val resultPerfect = "Олӣ! 🏆"
    override val resultExcellent = "Аъло! 🎉"
    override val resultGood = "Хуб! 👍"
    override val resultOk = "Мешавад! 💪"
    override val resultRetry = "Аз нав кӯшиш кунед 🌱"
    override val resultSubtitleGood = "Шумо ин мавзӯъро хуб медонед."
    override val resultSubtitleOk = "Каме такрор — ва натиҷа беҳтар мешавад."
    override val resultSubtitleRetry = "Такрор модари таълим аст. Боз як бор кӯшиш кунед!"

    override fun remaining(left: Int, max: Int) = "$left/$max боқӣ"
    override fun attempts(current: Int, max: Int) = "Кӯшиши $current/$max"
    override fun notEnoughWordsMessage(has: Int, need: Int) =
        "Шумо $has калима доред. Ҳадди ақал $need калима лозим аст."
    override fun correctWithTranslation(answer: String, translation: String) =
        "«$answer» — $translation"
    override fun translationOf(translation: String) = "Тарҷума: $translation"
    override fun rightAnswerIs(answer: String) = "Ҷавоби дуруст: $answer"
}

object TjProfile : ProfileStrings {
    override val streakStat = "Рӯзи серия"
    override val wordsStat = "Калимаҳо"
    override val lessonsStat = "Дарсҳо"

    override val signOut = "Баромадан аз аккаунт"
    override val signIn = "Бо Google ворид шавед"

    override val plusActive = "Tuti Plus фаъол"
    override val plusGet = "Tuti Plus гиред"
    override val plusPromo = "Дарсҳои бемаҳдуд ва бисёр аз ин зиёд!"

    override val themeGroup = "Мавзӯъ 🎨"
    override val themeSystem = "Системавӣ"
    override val themeLight = "Рӯшан"
    override val themeDark = "Торик"

    override val languageGroup = "Забони барнома 🌐"
    override val languageRow = "Забони интерфейс"
    override val languageRowSubtitle = "Забони матнҳои барнома"

    override val soundsGroup = "Садоҳо 🔊"
    override val soundsRow = "Садои барнома"
    override val soundsOn = "Садоҳо фаъол ҳастанд"
    override val soundsOff = "Садоҳо хомӯш ҳастанд"

    override val notificationsGroup = "Огоҳиномаҳо 🔔"
    override val permissionTitle = "Иҷозати огоҳинома"
    override val permissionSubtitle = "Барои ёдоварӣ иҷозат диҳед"
    override val permissionAction = "Иҷозат →"
    override val permissionNeeded = "Барои ёдоварӣ иҷозати огоҳинома лозим аст"
    override val dailyReminder = "Ёдоварии ҳаррӯза"
    override val dailyReminderSubtitle = "Tuti ба шумо дар бораи омӯзиш ёдоварӣ мекунад"
    override val reminderOff = "Ёдоварӣ хомӯш шуд"
    override val reminderTime = "Вақти ёдоварӣ"
    override val reminderTimeSubtitle = "Ёдоварии бегоҳӣ"

    override val helpGroup = "Кӯмак 💡"
    override val tipsRow = "Роҳнамои барнома"
    override val tipsSubtitle = "Ҳамаи маслиҳатҳои Tuti-ро аз нав нишон диҳед"
    override val tipsAction = "Барқарор →"
    override val tipsRestored = "Роҳнамоҳо аз нав нишон дода мешаванд 💡"

    override fun memberSince(month: String) = "Аз моҳи $month бо Tuti 🦜"
    override fun plusDaysLeft(days: Int) = "$days рӯз боқӣ"
    override fun reminderSetAt(time: String) = "Ёдоварӣ барои соати $time гузошта шуд 🔔"
}

object TjAchievements : AchievementsStrings {
    override val title = "Дастовардҳо 🎖️"
    override val unlockedTitle = "Дастовард кушода шуд!"
    override val unlockedButton = "Аъло!"
    override val badgeLabel = "нишон"
    override val overallProgress = "Пешрафти умумӣ"
    override val hiddenSubtitle = "Омӯзишро давом диҳед барои кушодан!"

    override val streak3 = "Серияи 3 рӯза"
    override val streak3Desc = "3 рӯз пай дар пай омӯхтед"
    override val streak7 = "Серияи 7 рӯза"
    override val streak7Desc = "7 рӯз пай дар пай омӯхтед"
    override val streak30 = "Серияи 30 рӯза"
    override val streak30Desc = "30 рӯз пай дар пай омӯхтед"
    override val words50 = "50 калима"
    override val words50Desc = "50 калимаи нав омӯхтед"
    override val words100 = "100 калима"
    override val words100Desc = "100 калимаи нав омӯхтед"
    override val words500 = "500 калима"
    override val words500Desc = "500 калимаи нав омӯхтед"
    override val lessons5 = "5 дарс"
    override val lessons5Desc = "5 дарс тамом кардед"
    override val lessons20 = "20 дарс"
    override val lessons20Desc = "20 дарс тамом кардед"
    override val lessons50 = "50 дарс"
    override val lessons50Desc = "50 дарс тамом кардед"
    override val chat10 = "Гуфтугӯчӣ"
    override val chat10Desc = "10 суҳбат бо Tuti"
    override val chat50 = "Сӯҳбатдон"
    override val chat50Desc = "50 суҳбат бо Tuti"
    override val perfect5 = "Бехато"
    override val perfect5Desc = "5 дарс бе хато тамом кардед"
    override val module1 = "Модули аввал"
    override val module1Desc = "Модули 1-ро тамом кардед"
    override val modules5 = "5 модул"
    override val modules5Desc = "5 модулро тамом кардед"
    override val modules10 = "Устод"
    override val modules10Desc = "10 модулро тамом кардед"
    override val langs2 = "Ду забон"
    override val langs2Desc = "Ҳар 2 забонро оғоз кунед"

    override fun unlockedCount(done: Int, total: Int) = "$done аз $total кушода шуд"
    override fun hiddenTeaser(count: Int) = "Боз $count дастоварди пинҳон"
}

object TjLeaderboard : LeaderboardStrings {
    override val title = "Рейтинг 🏆"
    override val emptyTitle = "Ҳоло корбарон нестанд"
    override val emptyMessageCity = "Дар шаҳри шумо ҳоло касе нест. Аввалин шавед!"
    override val emptyMessageGlobal = "Аввалин нафари рейтинг шавед!"
    override val filterAll = "🌍 Ҳама"
    override val filterMyCity = "🏛️ Шаҳри ман"
    override val you = "Шумо"
    override val offline = "Офлайн — охирин маълумот нишон дода мешавад"
    override val signInTitle = "Ворид шавед барои рейтинг"
    override val signInMessage =
        "Барои дидани рейтинги глобалӣ ва мусобиқа бо дигарон ворид шавед."
    override val topOne = "🏆 Шумо рақами 1 дар рейтинг ҳастед!"
    override val motivation = "Имрӯз як дарс хонед ва боло равед! 💪"

    override fun toNextRank(rank: Int, diff: Int) =
        "⚡ То ҷойгоҳи $rank ҳамагӣ $diff очки монд!"
}

object TjPlus : PlusStrings {
    override val title = "Tuti Plus ⭐"
    override val subtitleUnlimited = "Ҳама имконият бе маҳдудият!"
    override val benefitsTitle = "Бартариятҳои Plus"
    override val benefitLessons = "Дарсҳои бемаҳдуд дар як рӯз"
    override val benefitChat = "Суҳбати бемаҳдуд бо Tuti AI"
    override val benefitFlashcards = "Корти калимаҳои бемаҳдуд"
    override val benefitListening = "Машқи шунавоии бемаҳдуд"
    override val benefitCall = "Занги овозӣ бо Tuti"
    override val benefitModules = "Ҳамаи модулҳо кушода"
    override val benefitNoAds = "Бе реклама"

    override val pricesTitle = "Нархҳо"
    override val monthly = "Моҳона"
    override val yearly = "Солона"
    override val monthlyPeriod = "30 рӯз"
    override val yearlyPeriod = "365 рӯз"
    override val saveBadge = "Сарфа 57%"
    override val currency = "сомонӣ"

    override val howToBuy = "Чӣ тавр харидан"
    override val stepTelegram = "Ба @tuti_support дар Telegram нависед"
    override val stepPay = "Маблағро гузаронед"
    override val stepGetCode = "Промокод мегиред"
    override val stepEnterCode = "Дар поён ворид кунед"

    override val promoTitle = "Промокод"
    override val promoActivate = "Фаъол кардан"
    override val promoInvalid = "Промокод нодуруст аст"
    override val promoUsed = "Ин промокод аллакай истифода шудааст"
    override val promoError = "Хатогӣ рӯй дод. Боз кӯшиш кунед"

    override val freeBadge = "Ройгон"

    override val paywallTitle = "Имрӯз лимит тамом шуд"
    override val paywallBenefitsTitle = "Бо Plus шумо мегиред:"
    override val paywallLessons = "Дарсҳои бемаҳдуд"
    override val paywallChat = "Чати бемаҳдуд бо Tuti"
    override val paywallListening = "Машқи шунавоӣ"
    override val paywallCall = "Занги овозӣ"
    override val paywallPrimary = "Tuti Plus гиред!"
    override val paywallSecondary = "Пагоҳ давом медиҳам →"

    override fun activeDaysLeft(days: Int) = "Фаъол · $days рӯз боқӣ"
    override fun plusDaysBadge(days: Int) = "Plus · $days рӯз"
    override fun paywallMessage(used: Int, max: Int) =
        "Шумо имрӯз $used аз $max дарс хондед. Пагоҳ давом диҳед ё Plus гиред!"
    override fun promoSuccess(days: Int) = "🎉 Табрик! Tuti Plus барои $days рӯз фаъол шуд!"
}

object TjChat : ChatStrings {
    override val title = "Муаллими Tuti"
    override val online = "онлайн"
    override val typing = "менависад…"
    override val welcomeTitle = "Салом! Ман Tuti ҳастам 🦜"
    override val welcomeSubtitle = "Бо ман гап занед! Ман кӯмак мекунам ва хатоҳоро ислоҳ мекунам."
    override val greetingBubble =
        "Бо ман гап занед! Ба русӣ ё тоҷикӣ нависед — ман кӯмак мекунам! 🦜"
    override val inputPlaceholder = "Ба русӣ ё тоҷикӣ нависед…"
    override val retry = "Такрор"
    override val networkError = "Бубахшед, алоқа қатъ шуд. Боз кӯшиш кунед."
    override val quickHello = "Привет, как дела?"
    override val quickShop = "Дар мағоза чӣ гӯям?"
    override val quickGrammar = "Грамматикаро шарҳ деҳ"
}

object TjNotifications : NotificationStrings {
    override val channelName = "Ёдоварии Tuti"
    override val channelDescription = "Ёдовариҳои ҳаррӯзаи Tuti барои омӯзиш"

    override val reminders = listOf(
        "🦜 Tuti интизори шумост!" to "Имрӯз ҳанӯз дарс нахондед!",
        "🔥 Серияи шумо дар хатар аст!" to "Биёед омӯзед!",
        "📚 5 дақиқа кифоя аст!" to "Серияро нигоҳ доред!",
        "🎯 Имрӯз як дарс хонед!" to "Кифоя аст!",
        "💪 Ҳар рӯз як қадам!" to "Имрӯзро аз даст надиҳед!",
    )
    override val streakRiskTitle = "🔥 Серия дар хатар!"
    override val goalTitle = "💪 Каме монд!"
    override val goalAlmostTitle = "🎯 Ҳадафатон наздик аст!"
    override val goalAlmostText = "Як машқи дигар ва ҳадафи имрӯза иҷро мешавад!"

    override fun streakRiskText(streak: Int) =
        "Агар имрӯз наомӯзед, серияи $streak рӯзаатон қатъ мешавад!"
    override fun goalProgressText(studied: Int, remaining: Int) =
        "Шумо $studied дақиқа хондед. Боз $remaining дақиқа то ҳадаф!"
}

object TjTooltips : TooltipStrings {
    override val xp = "Ин очкоҳои шумо ва пешрафт то ҳадафи навбатӣ! 💎"
    override val streak = "Серияи ҳафтаина! Ҳар рӯз хонед ва серияро нигоҳ доред! 🔥"
    override val course = "Курси асосии шумо! Барои оғоз ё давом пахш кунед! 👆"
    override val topics = "Мавзӯъҳои ройгон! Бе пардохт калимаҳо омӯзед! 🎉"
    override val practice = "Дар бахши Машқ бо Tuti гап занед ва корти калимаҳо омӯзед! 🦜"
    override val finish = "Офарин! Шумо тайёред! Биёед забон омӯзем! 🚀"
    override val skip = "Гузаштан"
    override val next = "Давом"
    override val start = "Оғоз кардан!"

    override val splashTagline = "Забонҳоро осон омӯзед"
    override val splashRussian = "Русӣ"
    override val splashEnglish = "Англисӣ"
}

object TjAuth : AuthStrings {
    override val signInError = "Хатогӣ дар ворид шудан"
    override val unsupportedAccount = "Навъи аккаунт дастгирӣ намешавад"
    override val noGoogleAccount = "Аккаунти Google ёфт нашуд. Аввал аккаунт илова кунед."
    override val genericRetry = "Дубора кӯшиш кунед"

    override fun errorWithMessage(message: String) = "Хатогӣ: $message"
}
