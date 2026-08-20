package app.tuti.tj.ui.i18n

// ════════════════════════════════════════════════════════════════
//  РУССКИЙ — второй язык интерфейса.
//
//  Переводы держатся ближе к смыслу, чем к дословности: подписи
//  кнопок в макете короткие, и длинная калька просто не влезает.
//  Реплики маскота на главном — не длиннее ~16 символов, иначе
//  пузырь наезжает на счётчик очков.
// ════════════════════════════════════════════════════════════════

/**
 * Русские числительные требуют согласования: «1 слово», «2 слова»,
 * «5 слов». В таджикском такого нет, поэтому помощник живёт только
 * здесь и применяется во всех строках со счётчиками.
 */
private fun plural(count: Int, one: String, few: String, many: String): String {
    val mod100 = count % 100
    if (mod100 in 11..14) return many
    return when (count % 10) {
        1 -> one
        2, 3, 4 -> few
        else -> many
    }
}

object RuStrings : TutiStrings {
    override val language = AppLanguage.RUSSIAN
    override val common = RuCommon
    override val nav = RuNav
    override val onboarding = RuOnboarding
    override val finalStep = RuFinalStep
    override val ageGate = RuAgeGate
    override val home = RuHome
    override val lessons = RuLessons
    override val lessonFlow = RuLessonFlow
    override val practice = RuPractice
    override val profile = RuProfile
    override val achievements = RuAchievements
    override val leaderboard = RuLeaderboard
    override val plus = RuPlus
    override val chat = RuChat
    override val notifications = RuNotifications
    override val tooltips = RuTooltips
    override val auth = RuAuth
    override val cities = RuCities
}

object RuCommon : CommonStrings {
    override val back = "← Назад"
    override val backArrow = "←"
    override val previousArrow = "← Назад"
    override val continueShort = "Далее"
    override val continueLong = "Продолжить"
    override val check = "Проверить"
    override val skip = "Пропустить"
    override val skipArrow = "Пропустить →"
    override val startAction = "Начать!"
    override val retry = "Попробуйте ещё раз"
    override val tryAgain = "Ещё раз"
    override val loading = "Секунду…"
    override val restart = "Заново"
    override val toHome = "На главную"
    override val next = "Дальше"
    override val result = "Итог"
    override val exit = "Выйти"
    override val cancel = "Отмена"
    override val points = "баллы"
    override val stars = "звёзды"
    override val accuracy = "точность"
    override val lessonsLabel = "Уроки"
    override val wordsLabel = "Слова"
    override val streakLabel = "Серия"
    override val rankLabel = "Место"
    override val done = "Готово"
    override val user = "Пользователь"
    override val correctTitle = "Отлично! Правильно!"
    override val wrongTitle = "Неверно…"
    override val resultExcellent = "Отлично! 🎉"
    override val resultGood = "Хорошо! 👍"
    override val resultTryHarder = "Ещё немного! 💪"

    override fun xp(amount: Int) =
        "+$amount ${plural(amount, "балл", "балла", "баллов")}"
    override fun correctAnswer(answer: String) = "Правильный ответ: $answer"
    override fun ofCount(current: Int, total: Int) = "$current из $total"
}

object RuNav : NavStrings {
    override val home = "Главная"
    override val lessons = "Уроки"
    override val practice = "Практика"
    override val profile = "Профиль"
    override val leaderboard = "Рейтинг"
}

object RuOnboarding : OnboardingStrings {
    override val pickLanguageTitle = "Выберите язык приложения"
    override val pickLanguageAction = "Приложение на русском языке"
    override val pickLanguageHint = "Потом можно поменять в профиле"

    override val tagline = "Учите языки легко"
    override val googleSignIn = "Войти через Google"
    override val terms = "Входя, вы принимаете условия использования\n" +
        "и политику конфиденциальности"
    override val privacyPolicy = "Политика конфиденциальности"
    override val cloudRestored = "☁️ Ваши данные восстановлены!"

    override val languageTitle = "Какой язык?"
    override val languageSubtitle = "Какой язык хотите изучать?"
    override val levelTitle = "Ваш уровень?"
    override val levelSubtitleEnglish = "Насколько хорошо вы знаете английский?"
    override val levelSubtitleRussian = "Насколько хорошо вы знаете русский?"
    override val goalTitle = "Ваша цель?"
    override val goalSubtitle = "Зачем вы учите язык?"
    override val timeTitle = "Время на учёбу?"
    override val timeSubtitle = "Сколько времени в день готовы заниматься?"
    override val cityTitle = "Ваш город?"
    override val citySubtitle = "Для рейтинга городов"

    override val optionRussian = "Русский язык"
    override val optionRussianHint = "Учить русский"
    override val optionEnglish = "English"
    override val optionEnglishHint = "Учить английский"

    override val levelBeginner = "Начальный"
    override val levelBeginnerHint = "Я новичок"
    override val levelIntermediate = "Средний"
    override val levelIntermediateHint = "Немного знаю"
    override val levelAdvanced = "Продвинутый"
    override val levelAdvancedHint = "Знаю хорошо"

    override val goalWork = "Для работы"
    override val goalWorkHint = "Работа в России или за рубежом"
    override val goalStudy = "Для учёбы"
    override val goalStudyHint = "Университет или школа"
    override val goalTravel = "Для поездок"
    override val goalTravelHint = "Туризм и переезд"
    override val goalPersonal = "Для себя"
    override val goalPersonalHint = "Личное развитие"

    override val timeCalm = "Спокойно"
    override val timeModerate = "Умеренно"
    override val timeSerious = "Серьёзно"
    override val timeMax = "Максимум"

    override val readyTitle = "Всё готово! 🎉"
    override val readySubtitle = "Tuti подготовил ваш курс.\nНачнём!"

    override val offlineTitle = "Нет интернета"
    override val offlineMessage =
        "Чтобы начать, нужно подключение к сети.\nЭто нужно только один раз."

    override fun minutes(count: Int) =
        "$count ${plural(count, "минута", "минуты", "минут")}"
}

object RuAgeGate : AgeGateStrings {
    override val title = "Ваша дата рождения"
    override val subtitle = "Чтобы продолжить, укажите дату рождения"

    override val dayLabel = "День"
    override val monthLabel = "Месяц"
    override val yearLabel = "Год"
    override val invalidDate = "Такой даты не существует"

    override val blockedTitle = "Доступ ограничен"
    override val blockedMessage = "Приложение Tuti доступно с 13 лет."
    override val blockedContactHint = "Есть вопрос? Напишите нам:"
    override val supportEmail = "tutiapp08@gmail.com"
}

object RuFinalStep : FinalStepStrings {
    override val subtitle = "Ваш курс готов. Начнём!"

    override val summaryTitle = "Ваш выбор"
    override val labelLanguage = "Язык"
    override val labelGoal = "Цель"
    override val labelLevel = "Уровень"
    override val labelCity = "Город"
    override val labelDailyTime = "Каждый день"
}

object RuCities : CityStrings {
    /**
     * В базе и в Firestore город всегда лежит по-таджикски —
     * иначе рейтинг разъехался бы на две несовместимые группы.
     * Переводится только то, что показывается.
     */
    private val names = mapOf(
        "Душанбе" to "Душанбе",
        "Хуҷанд" to "Худжанд",
        "Бохтар" to "Бохтар",
        "Кӯлоб" to "Куляб",
        "Истаравшан" to "Истаравшан",
        "Конибодом" to "Канибадам",
        "Турсунзода" to "Турсунзаде",
        "Пенҷикент" to "Пенджикент",
        "Ғафуров" to "Гафуров",
        "Ваҳдат" to "Вахдат",
        "Исфара" to "Исфара",
        "Норак" to "Нурек",
        "Ёвон" to "Яван",
        "Дигар" to "Другой",
    )

    override fun name(dbValue: String) = names[dbValue] ?: dbValue

    override val regionCapital = "Столица"
    override val regionNorth = "Север"
    override val regionSouth = "Юг"
    override val regionKhatlon = "Хатлон"
    override val regionSughd = "Согд"
    override val regionCentral = "РРП"
    override val regionOther = "Другой город"
}

object RuHome : HomeStrings {
    override val weekDays = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

    override fun greetings(streak: Int, isPlus: Boolean) = listOf(
        "Привет! 👋",
        if (streak > 0) "🔥 $streak дн." else "Начнём! 🚀",
        "Вы готовы?",
        "Один урок ✨",
        "Что учим?",
        "Пора учиться! ⏰",
        if (isPlus) "Plus активен ⭐" else "Это несложно!",
        "Отлично идёте! 👏",
        "Я вас жду 🦜",
        "Каждый день шаг",
        "Поболтаем? 💬",
        "Не сдавайтесь! 💪",
    )

    override val switchLanguage = "Сменить"
    override val russianLanguage = "Русский язык"
    override val englishLanguage = "English"
    override val bothLanguages = "Русский и английский"
    override val russianLanguageHint = "Учить русский"
    override val englishLanguageHint = "Учить английский"

    override val levelBeginner = "Новичок"
    override val levelElementary = "Начальный"
    override val levelIntermediate = "Средний"
    override val levelAdvanced = "Продвинутый"

    override val weeklyStreak = "Неделя"
    override val chooseLanguageTitle = "Какой язык?"
    override val chooseLanguageMessage = "Выберите язык изучения"
    override val chooseLanguageConfirm = "Выбрать"

    override val yourCourse = "Ваш курс"
    override val seeAll = "Все →"
    override val dailyLimit = "Лимит на сегодня"
    override val getPlus = "Купить Plus"
    override val freeTopics = "Свободные темы"
    override val finished = "Готово"

    override fun daysThisWeek(done: Int) = "$done из 7 дней"
    override fun lessonsProgress(done: Int, total: Int) =
        "$done из $total ${plural(total, "урока", "уроков", "уроков")}"
    override fun continueLesson(title: String) = "Продолжить урок: $title"
    override fun topicsCount(count: Int) = "$count ${plural(count, "тема", "темы", "тем")}"
}

object RuLessons : LessonsStrings {
    override val noQuestionsTitle = "Вопросы не найдены"
    override val noQuestionsMessage = "В этой теме пока нет вопросов."

    override val typeTranslate = "Перевод"
    override val typeChoose = "Выбор"
    override val typeFillBlank = "Заполните"
    override val typeMatch = "Соответствие"
    override val typeListen = "Аудирование"

    override val title = "Уроки"
    override val chooseTopic = "Выберите тему"
    override val yourProgress = "Ваш прогресс"
    override val courseNotFoundTitle = "Курс не найден"
    override val courseNotFoundMessage = "Этот курс недоступен. Загляните в свободные темы."
    override val startCourse = "Начать →"
    override val restartTopic = "Заново 🔄"
    override val startTopic = "Начать →"
    override val continueTopic = "Продолжить →"

    override val wordsTitle = "Слова"
    override val toQuiz = "К тесту →"
    override val startQuiz = "Начать тест"
    override val tapForTranslation = "👆 Нажмите, чтобы увидеть перевод"

    override fun topicsDone(done: Int, total: Int) =
        "$done из $total ${plural(total, "темы", "тем", "тем")} пройдено"
    override fun wordsCount(count: Int) =
        "$count ${plural(count, "слово", "слова", "слов")}"
    override fun questionsCount(count: Int) =
        "$count ${plural(count, "вопрос", "вопроса", "вопросов")}"
    override fun percentDone(percent: Int) = "$percent% пройдено"
    override fun lessonsDone(done: Int, total: Int) =
        "$done из $total ${plural(total, "урока", "уроков", "уроков")} пройдено"
    override fun correctOf(correct: Int, total: Int) =
        "Вы ответили правильно на $correct из $total!"
}

object RuLessonFlow : LessonFlowStrings {
    override val notFoundTitle = "Урок не найден"
    override val notFoundMessage = "Этот урок недоступен. Вернитесь к списку уроков."
    override val exitTitle = "Выйти?"
    override val exitMessage = "Прогресс в этом уроке не сохранится."
    override val exitStay = "Нет, останусь"
    override val exitConfirm = "Да, выйду"

    override val praise = "Отлично! 🎉 У вас здорово получается! Продолжайте!"
    override val encourage = "Ошиблись — это не страшно! На ошибках учатся! 💪"

    override val dialogueHint = "Сначала прочитайте диалог! Это живая речь 📖"
    override val dialogueTapForTranslation = "Нажмите на фразу, чтобы увидеть перевод"
    override val newWordsButton = "Новые слова"
    override val translationArrow = "перевод →"
    override val learnNewWords = "Запомните новые слова! ⭐"
    override val newWords = "Новые слова"
    override val nextWord = "Следующее"

    override val grammarHint = "Это правило грамматики! Прочитайте внимательно 📝"
    override val grammarTitle = "Грамматика"
    override val grammarUnderstood = "Понятно!"

    override val noExerciseTitle = "Упражнение не найдено"
    override val exerciseHint = "Выберите вариант и нажмите «Проверить»! 🎯"

    override val congratsFirstLesson = "Поздравляем! Первый урок пройден! Забирайте звёзды! 🌟"
    override val correctLabel = "Верно"
    override val wordLabel = "Слова"

    override val heartsOverTitle = "Жизни закончились"
    override val heartsOverMessage =
        "Ничего страшного — все учатся на ошибках.\nПопробуйте сначала!"

    override fun exerciseCounter(current: Int, total: Int) = "Упражнение $current из $total"
}

object RuPractice : PracticeStrings {
    override val title = "Практика"
    override val chooseMode = "Выберите вид практики"

    override val chatTitle = "Учитель Tuti"
    override val chatDescription = "Общайтесь с ИИ и тренируйтесь"
    override val flashcardsTitle = "Карточки слов"
    override val flashcardsDescription = "Интервальное повторение"
    override val listeningTitle = "Аудирование"
    override val listeningDescription = "Тренировка на слух"
    override val writingTitle = "Письмо"
    override val writingDescription = "Тренировка правописания"

    override val limitReached = "Лимит исчерпан"
    override val unlimited = "Без лимита"

    override val preparingCards = "Готовим карточки…"
    override val preparingPractice = "Готовим упражнения…"

    override val learnFirstTitle = "Сначала пройдите урок!"
    override val learnFirstMessage =
        "После изучения слов их можно будет повторять здесь."
    override val toLessons = "Перейти к урокам"
    override val notEnoughWordsTitle = "Сначала выучите слова!"

    override val flashcardsHint =
        "Нажмите на карточку, чтобы увидеть перевод! Затем выберите «Знаю» или «Не знаю» 🃏"
    override val dontKnow = "Не знаю"
    override val know = "Знаю"
    override val tapForTranslation = "👆 Нажмите, чтобы увидеть перевод"
    override val knownWords = "слов знаете"
    override val needRepeat = "нужно повторить"

    override val listenAndWrite = "Послушайте и напишите слово"
    override val writeWordPlaceholder = "Напишите слово…"
    override val wrongTryAgain = "Неверно! Попробуйте ещё раз"

    override val listenAndChoose = "Послушайте и выберите правильный перевод"

    override val correctAnswerLabel = "написано верно"
    override val wrongAnswerLabel = "с ошибкой"

    override val exerciseUnavailable = "Это упражнение недоступно"
    override val noOptionsError = "Ошибка: нет вариантов ответа"
    override val tapWordsPlaceholder = "Нажимайте на слова…"
    override val fillTheBlank = "Заполните пропуск:"
    override val writeAnswerPlaceholder = "Напишите ответ…"

    override val resultPerfect = "Превосходно! 🏆"
    override val resultExcellent = "Отлично! 🎉"
    override val resultGood = "Хорошо! 👍"
    override val resultOk = "Неплохо! 💪"
    override val resultRetry = "Попробуйте ещё раз 🌱"
    override val resultSubtitleGood = "Вы хорошо знаете эту тему."
    override val resultSubtitleOk = "Немного повторения — и результат будет лучше."
    override val resultSubtitleRetry = "Повторение — мать учения. Попробуйте ещё раз!"

    override fun remaining(left: Int, max: Int) = "$left/$max осталось"
    override fun attempts(current: Int, max: Int) = "Попытка $current/$max"
    override fun notEnoughWordsMessage(has: Int, need: Int) =
        "У вас $has ${plural(has, "слово", "слова", "слов")}. " +
            "Нужно минимум $need ${plural(need, "слово", "слова", "слов")}."
    override fun correctWithTranslation(answer: String, translation: String) =
        "«$answer» — $translation"
    override fun translationOf(translation: String) = "Перевод: $translation"
    override fun rightAnswerIs(answer: String) = "Правильный ответ: $answer"
}

object RuProfile : ProfileStrings {
    override val streakStat = "Дней подряд"
    override val wordsStat = "Слова"
    override val lessonsStat = "Уроки"

    override val signOut = "Выйти из аккаунта"
    override val signIn = "Войти через Google"

    override val plusActive = "Tuti Plus активен"
    override val plusGet = "Купить Tuti Plus"
    override val plusPromo = "Уроки без лимита и многое другое!"

    override val themeGroup = "Тема 🎨"
    override val themeSystem = "Системная"
    override val themeLight = "Светлая"
    override val themeDark = "Тёмная"

    override val languageGroup = "Язык приложения 🌐"
    override val languageRow = "Язык интерфейса"
    override val languageRowSubtitle = "Язык текстов в приложении"

    override val soundsGroup = "Звуки 🔊"
    override val soundsRow = "Звуки приложения"
    override val soundsOn = "Звуки включены"
    override val soundsOff = "Звуки выключены"

    override val notificationsGroup = "Уведомления 🔔"
    override val permissionTitle = "Разрешение на уведомления"
    override val permissionSubtitle = "Разрешите, чтобы получать напоминания"
    override val permissionAction = "Разрешить →"
    override val permissionNeeded = "Для напоминаний нужно разрешение на уведомления"
    override val dailyReminder = "Ежедневное напоминание"
    override val dailyReminderSubtitle = "Tuti напомнит вам про занятия"
    override val reminderOff = "Напоминания выключены"
    override val reminderTime = "Время напоминания"
    override val reminderTimeSubtitle = "Вечернее напоминание"

    override val helpGroup = "Помощь 💡"
    override val tipsRow = "Подсказки по приложению"
    override val tipsSubtitle = "Показать все подсказки Tuti заново"
    override val tipsAction = "Вернуть →"
    override val tipsRestored = "Подсказки снова будут показаны 💡"

    override fun memberSince(month: String) = "С Tuti с $month 🦜"
    override fun plusDaysLeft(days: Int) = "Осталось $days дн."
    override fun reminderSetAt(time: String) = "Напоминание поставлено на $time 🔔"
}

object RuAchievements : AchievementsStrings {
    override val title = "Достижения 🎖️"
    override val unlockedTitle = "Достижение открыто!"
    override val unlockedButton = "Отлично!"
    override val badgeLabel = "значок"
    override val overallProgress = "Общий прогресс"
    override val hiddenSubtitle = "Продолжайте учиться, чтобы открыть!"

    override val streak3 = "Серия 3 дня"
    override val streak3Desc = "3 дня подряд занимались"
    override val streak7 = "Серия 7 дней"
    override val streak7Desc = "7 дней подряд занимались"
    override val streak30 = "Серия 30 дней"
    override val streak30Desc = "30 дней подряд занимались"
    override val words50 = "50 слов"
    override val words50Desc = "Выучили 50 новых слов"
    override val words100 = "100 слов"
    override val words100Desc = "Выучили 100 новых слов"
    override val words500 = "500 слов"
    override val words500Desc = "Выучили 500 новых слов"
    override val lessons5 = "5 уроков"
    override val lessons5Desc = "Прошли 5 уроков"
    override val lessons20 = "20 уроков"
    override val lessons20Desc = "Прошли 20 уроков"
    override val lessons50 = "50 уроков"
    override val lessons50Desc = "Прошли 50 уроков"
    override val chat10 = "Собеседник"
    override val chat10Desc = "10 разговоров с Tuti"
    override val chat50 = "Мастер беседы"
    override val chat50Desc = "50 разговоров с Tuti"
    override val perfect5 = "Без ошибок"
    override val perfect5Desc = "Прошли 5 уроков без ошибок"
    override val module1 = "Первый модуль"
    override val module1Desc = "Завершили модуль 1"
    override val modules5 = "5 модулей"
    override val modules5Desc = "Завершили 5 модулей"
    override val modules10 = "Мастер"
    override val modules10Desc = "Завершили 10 модулей"
    override val langs2 = "Два языка"
    override val langs2Desc = "Начните оба языка"

    override fun unlockedCount(done: Int, total: Int) = "$done из $total открыто"
    override fun hiddenTeaser(count: Int) =
        "Ещё $count ${plural(count, "скрытое достижение", "скрытых достижения", "скрытых достижений")}"
}

object RuLeaderboard : LeaderboardStrings {
    override val title = "Рейтинг 🏆"
    override val emptyTitle = "Пока никого нет"
    override val emptyMessageCity = "В вашем городе пока никого нет. Станьте первым!"
    override val emptyMessageGlobal = "Станьте первым в рейтинге!"
    override val filterAll = "🌍 Все"
    override val filterMyCity = "🏛️ Мой город"
    override val you = "Вы"
    override val offline = "Офлайн — показаны последние данные"
    override val signInTitle = "Войдите, чтобы видеть рейтинг"
    override val signInMessage =
        "Войдите, чтобы видеть общий рейтинг и соревноваться с другими."
}

object RuPlus : PlusStrings {
    override val title = "Tuti Plus ⭐"
    override val subtitleUnlimited = "Все возможности без ограничений!"
    override val benefitsTitle = "Преимущества Plus"
    override val benefitLessons = "Уроки без дневного лимита"
    override val benefitChat = "Общение с Tuti AI без лимита"
    override val benefitFlashcards = "Карточки слов без лимита"
    override val benefitListening = "Аудирование без лимита"
    override val benefitCall = "Голосовой звонок с Tuti"
    override val benefitModules = "Все модули открыты"
    override val benefitNoAds = "Без рекламы"

    override val contactUs = "Свяжитесь с нами"

    override val promoTitle = "Промокод"
    override val promoActivate = "Активировать"
    override val promoInvalid = "Промокод неверный"
    override val promoUsed = "Этот промокод уже использован"
    override val promoError = "Произошла ошибка. Попробуйте ещё раз"

    override val freeBadge = "Бесплатно"

    override val paywallTitle = "Лимит на сегодня исчерпан"
    override val paywallBenefitsTitle = "С Plus вы получите:"
    override val paywallLessons = "Уроки без лимита"
    override val paywallChat = "Чат с Tuti без лимита"
    override val paywallListening = "Аудирование"
    override val paywallCall = "Голосовой звонок"
    override val paywallPrimary = "Купить Tuti Plus!"
    override val paywallSecondary = "Продолжу завтра →"

    override fun activeDaysLeft(days: Int) = "Активен · осталось $days дн."
    override fun plusDaysBadge(days: Int) = "Plus · $days дн."
    override fun paywallMessage(used: Int, max: Int) =
        "Сегодня вы прошли $used из $max ${plural(max, "урока", "уроков", "уроков")}. " +
            "Продолжите завтра или купите Plus!"
    override fun promoSuccess(days: Int) =
        "🎉 Поздравляем! Tuti Plus активен на $days ${plural(days, "день", "дня", "дней")}!"
}

object RuChat : ChatStrings {
    override val title = "Учитель Tuti"
    override val online = "онлайн"
    override val typing = "печатает…"
    override val welcomeTitle = "Привет! Я Tuti 🦜"
    override val welcomeSubtitle = "Поговорите со мной! Я помогу и исправлю ошибки."
    override val greetingBubble =
        "Поговорите со мной! Пишите по-русски или по-таджикски — я помогу! 🦜"
    override val inputPlaceholder = "Пишите по-русски или по-таджикски…"
    override val retry = "Повторить"
    override val networkError = "Извините, связь прервалась. Попробуйте ещё раз."
    override val quickHello = "Привет, как дела?"
    override val quickShop = "Что сказать в магазине?"
    override val quickGrammar = "Объясни грамматику"

    override val reportAction = "Пожаловаться"
    override val reportTitle = "Жалоба на ответ"
    override val reportSubtitle = "Что было не так?"
    override val reasonWrongAnswer = "Неверный ответ"
    override val reasonOffensive = "Оскорбительное содержание"
    override val reasonOther = "Другое"
    override val commentPlaceholder = "Комментарий (необязательно)"
    override val reportSend = "Отправить"
    override val reportSentTitle = "Спасибо! ✅"
    override val reportSentMessage = "Жалоба принята."
}

object RuNotifications : NotificationStrings {
    override val channelName = "Напоминания Tuti"
    override val channelDescription = "Ежедневные напоминания Tuti про занятия"

    override val reminders = listOf(
        "🦜 Tuti вас ждёт!" to "Сегодня вы ещё не занимались!",
        "🔥 Ваша серия под угрозой!" to "Давайте позанимаемся!",
        "📚 Хватит и 5 минут!" to "Сохраните серию!",
        "🎯 Пройдите урок сегодня!" to "Этого достаточно!",
        "💪 Каждый день по шагу!" to "Не пропускайте сегодня!",
    )
    override val streakRiskTitle = "🔥 Серия под угрозой!"
    override val goalTitle = "💪 Совсем немного!"
    override val goalAlmostTitle = "🎯 Цель уже близко!"
    override val goalAlmostText = "Ещё одно упражнение — и цель на сегодня выполнена!"

    override fun streakRiskText(streak: Int) =
        "Если сегодня не позаниматься, серия в $streak дн. прервётся!"
    override fun goalProgressText(studied: Int, remaining: Int) =
        "Вы занимались $studied ${plural(studied, "минуту", "минуты", "минут")}. " +
            "Ещё $remaining ${plural(remaining, "минута", "минуты", "минут")} до цели!"
}

object RuTooltips : TooltipStrings {
    override val xp = "Это ваши баллы и прогресс до следующей цели! 💎"
    override val streak = "Серия за неделю! Занимайтесь каждый день и держите её! 🔥"
    override val course = "Ваш основной курс! Нажмите, чтобы начать или продолжить! 👆"
    override val topics = "Бесплатные темы! Начните учить слова прямо сейчас! 🎉"
    override val practice = "В разделе «Практика» поговорите с Tuti и учите карточки! 🦜"
    override val finish = "Отлично! Вы готовы! Давайте учить язык! 🚀"
    override val skip = "Пропустить"
    override val next = "Далее"
    override val start = "Начать!"

    override val splashTagline = "Учите языки легко"
    override val splashRussian = "Русский"
    override val splashEnglish = "Английский"
}

object RuAuth : AuthStrings {
    override val signInError = "Ошибка при входе"
    override val unsupportedAccount = "Такой тип аккаунта не поддерживается"
    override val noGoogleAccount = "Аккаунт Google не найден. Сначала добавьте аккаунт."
    override val genericRetry = "Попробуйте ещё раз"

    override fun errorWithMessage(message: String) = "Ошибка: $message"

    override val errorNoNetwork = "Нет интернета. Проверьте подключение."
    override val errorPlayServices =
        "Сервисы Google недоступны на этом телефоне."
    override val errorUnknown = "Не получилось войти. Попробуйте ещё раз."

    override val help = "Помощь"

    override val restoredTitle = "С возвращением! 🎉"
    override val restoredMessage = "Ваш прогресс восстановлен."
}
