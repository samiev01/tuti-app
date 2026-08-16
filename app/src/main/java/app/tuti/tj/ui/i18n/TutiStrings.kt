package app.tuti.tj.ui.i18n

// ════════════════════════════════════════════════════════════════
//  СЛОВАРЬ ИНТЕРФЕЙСА
//
//  Раньше подписи жили прямо в разметке экранов. Теперь у каждой
//  из них есть имя, а перевод хранится в TjStrings/RuStrings —
//  компилятор сам следит, чтобы ни одна строка не осталась без
//  второго языка.
//
//  Правила, по которым это разложено:
//    • одна строка = одно свойство; там, где в текст подставляются
//      числа или названия, вместо свойства стоит функция;
//    • эмодзи остаются в разметке, если они самостоятельная
//      иконка, и входят в строку, если они часть фразы;
//    • контент курсов (слова, предложения, переводы уроков) сюда
//      НЕ переносится: это учебный материал, а не интерфейс.
// ════════════════════════════════════════════════════════════════

interface TutiStrings {
    val language: AppLanguage
    val common: CommonStrings
    val nav: NavStrings
    val onboarding: OnboardingStrings
    val finalStep: FinalStepStrings
    val home: HomeStrings
    val lessons: LessonsStrings
    val lessonFlow: LessonFlowStrings
    val practice: PracticeStrings
    val profile: ProfileStrings
    val achievements: AchievementsStrings
    val leaderboard: LeaderboardStrings
    val plus: PlusStrings
    val chat: ChatStrings
    val notifications: NotificationStrings
    val tooltips: TooltipStrings
    val auth: AuthStrings
    val cities: CityStrings
}

// ─────────────────────────────────────────────
//  Общие подписи: кнопки, состояния, единицы
// ─────────────────────────────────────────────

interface CommonStrings {
    val back: String
    val backArrow: String
    val previousArrow: String
    val continueShort: String
    val continueLong: String
    val check: String
    val skip: String
    val skipArrow: String
    val startAction: String
    val retry: String
    val tryAgain: String
    val loading: String
    val restart: String
    val toHome: String
    val next: String
    val result: String
    val exit: String
    val cancel: String
    val points: String
    val stars: String
    val accuracy: String
    val lessonsLabel: String
    val wordsLabel: String
    val streakLabel: String
    val rankLabel: String
    val done: String
    val user: String
    val correctTitle: String
    val wrongTitle: String
    val resultExcellent: String
    val resultGood: String
    val resultTryHarder: String

    fun xp(amount: Int): String
    fun correctAnswer(answer: String): String
    fun ofCount(current: Int, total: Int): String
}

interface NavStrings {
    val home: String
    val lessons: String
    val practice: String
    val profile: String
    val leaderboard: String
}

// ─────────────────────────────────────────────
//  Онбординг
// ─────────────────────────────────────────────

interface OnboardingStrings {
    // Экран первого запуска. Показывается на обоих языках сразу,
    // поэтому фразы держатся короткими: две подписи вместо одной.
    val pickLanguageTitle: String
    val pickLanguageAction: String
    val pickLanguageHint: String

    val tagline: String
    val googleSignIn: String
    val terms: String
    val cloudRestored: String

    val languageTitle: String
    val languageSubtitle: String
    val levelTitle: String
    val levelSubtitleEnglish: String
    val levelSubtitleRussian: String
    val goalTitle: String
    val goalSubtitle: String
    val timeTitle: String
    val timeSubtitle: String
    val cityTitle: String
    val citySubtitle: String

    val optionRussian: String
    val optionRussianHint: String
    val optionEnglish: String
    val optionEnglishHint: String

    val levelBeginner: String
    val levelBeginnerHint: String
    val levelIntermediate: String
    val levelIntermediateHint: String
    val levelAdvanced: String
    val levelAdvancedHint: String

    val goalWork: String
    val goalWorkHint: String
    val goalStudy: String
    val goalStudyHint: String
    val goalTravel: String
    val goalTravelHint: String
    val goalPersonal: String
    val goalPersonalHint: String

    val timeCalm: String
    val timeModerate: String
    val timeSerious: String
    val timeMax: String

    val readyTitle: String
    val readySubtitle: String

    // Анонимный вход требует сети: без неё первый запуск дальше
    // не идёт, и это нужно объяснить словами, а не спиннером.
    val offlineTitle: String
    val offlineMessage: String

    fun minutes(count: Int): String
}

// ─────────────────────────────────────────────
//  Финальный шаг онбординга: сводка и вход
// ─────────────────────────────────────────────

interface FinalStepStrings {
    /** Не «войдите», а «сохраните»: человек защищает своё, а не выполняет требование. */
    val subtitle: String

    val summaryTitle: String
    val labelLanguage: String
    val labelGoal: String
    val labelLevel: String
    val labelCity: String
    val labelDailyTime: String

    val saveWithGoogle: String

    /** Единственный выход из тупика, раз кнопки «потом» нет. */
    val help: String

    // Вошли старым аккаунтом после переустановки: прогресс вернулся.
    val restoredTitle: String
    val restoredMessage: String

    // Причина плюс действие. Кодов ошибок на экране нет: человеку
    // нечего с ними делать, а доверия они не прибавляют.
    val errorNoNetwork: String
    val errorPlayServices: String
    val errorAccountConflict: String
    val errorUnknown: String
}

// ─────────────────────────────────────────────
//  Города: в базе они хранятся по-таджикски,
//  а показываются на языке интерфейса
// ─────────────────────────────────────────────

interface CityStrings {
    /** @param dbValue значение из tuti_prefs/Firestore (всегда таджикское). */
    fun name(dbValue: String): String

    val regionCapital: String
    val regionNorth: String
    val regionSouth: String
    val regionKhatlon: String
    val regionSughd: String
    val regionCentral: String
    val regionOther: String
}

// ─────────────────────────────────────────────
//  Главный экран
// ─────────────────────────────────────────────

interface HomeStrings {
    val weekDays: List<String>

    /**
     * Реплики маскота. Их набор зависит от состояния, поэтому это
     * функция, а не список: в разных языках короткая фраза строится
     * по-разному, и подстановку лучше держать рядом с переводом.
     * Каждая реплика — не длиннее ~16 символов, иначе не влезает
     * в пузырь рядом с очками.
     */
    fun greetings(streak: Int, isPlus: Boolean): List<String>

    val switchLanguage: String
    val russianLanguage: String
    val englishLanguage: String
    val bothLanguages: String
    val russianLanguageHint: String
    val englishLanguageHint: String

    val levelBeginner: String
    val levelElementary: String
    val levelIntermediate: String
    val levelAdvanced: String

    val weeklyStreak: String
    val chooseLanguageTitle: String
    val chooseLanguageMessage: String
    val chooseLanguageConfirm: String

    val yourCourse: String
    val seeAll: String
    val dailyLimit: String
    val getPlus: String
    val freeTopics: String
    val finished: String

    fun daysThisWeek(done: Int): String
    fun lessonsProgress(done: Int, total: Int): String
    fun continueLesson(title: String): String
    fun topicsCount(count: Int): String
}

// ─────────────────────────────────────────────
//  Свободные темы: список, слова, викторина
// ─────────────────────────────────────────────

interface LessonsStrings {
    val noQuestionsTitle: String
    val noQuestionsMessage: String

    val typeTranslate: String
    val typeChoose: String
    val typeFillBlank: String
    val typeMatch: String
    val typeListen: String

    val title: String
    val chooseTopic: String
    val yourProgress: String
    val courseNotFoundTitle: String
    val courseNotFoundMessage: String
    val startCourse: String
    val restartTopic: String
    val startTopic: String
    val continueTopic: String

    val wordsTitle: String
    val toQuiz: String
    val startQuiz: String
    val tapForTranslation: String

    fun topicsDone(done: Int, total: Int): String
    fun wordsCount(count: Int): String
    fun questionsCount(count: Int): String
    fun percentDone(percent: Int): String
    fun lessonsDone(done: Int, total: Int): String
    fun correctOf(correct: Int, total: Int): String
}

// ─────────────────────────────────────────────
//  Урок курса
// ─────────────────────────────────────────────

interface LessonFlowStrings {
    val notFoundTitle: String
    val notFoundMessage: String
    val exitTitle: String
    val exitMessage: String
    val exitStay: String
    val exitConfirm: String

    val praise: String
    val encourage: String

    val dialogueHint: String
    val dialogueTapForTranslation: String
    val newWordsButton: String
    val translationArrow: String
    val learnNewWords: String
    val newWords: String
    val nextWord: String

    val grammarHint: String
    val grammarTitle: String
    val grammarUnderstood: String

    val noExerciseTitle: String
    val exerciseHint: String

    val congratsFirstLesson: String
    val correctLabel: String
    val wordLabel: String

    val heartsOverTitle: String
    val heartsOverMessage: String

    fun exerciseCounter(current: Int, total: Int): String
}

// ─────────────────────────────────────────────
//  Практика: карточки, аудирование, письмо, упражнения
// ─────────────────────────────────────────────

interface PracticeStrings {
    val title: String
    val chooseMode: String

    val chatTitle: String
    val chatDescription: String
    val flashcardsTitle: String
    val flashcardsDescription: String
    val listeningTitle: String
    val listeningDescription: String
    val writingTitle: String
    val writingDescription: String

    val limitReached: String
    val unlimited: String

    val preparingCards: String
    val preparingPractice: String

    val learnFirstTitle: String
    val learnFirstMessage: String
    val toLessons: String
    val notEnoughWordsTitle: String

    val flashcardsHint: String
    val dontKnow: String
    val know: String
    val tapForTranslation: String
    val knownWords: String
    val needRepeat: String

    val listenAndWrite: String
    val writeWordPlaceholder: String
    val wrongTryAgain: String

    val listenAndChoose: String

    val correctAnswerLabel: String
    val wrongAnswerLabel: String

    val exerciseUnavailable: String
    val noOptionsError: String
    val tapWordsPlaceholder: String
    val fillTheBlank: String
    val writeAnswerPlaceholder: String

    val resultPerfect: String
    val resultExcellent: String
    val resultGood: String
    val resultOk: String
    val resultRetry: String
    val resultSubtitleGood: String
    val resultSubtitleOk: String
    val resultSubtitleRetry: String

    fun remaining(left: Int, max: Int): String
    fun attempts(current: Int, max: Int): String
    fun notEnoughWordsMessage(has: Int, need: Int): String
    fun correctWithTranslation(answer: String, translation: String): String
    fun translationOf(translation: String): String
    fun rightAnswerIs(answer: String): String
}

// ─────────────────────────────────────────────
//  Профиль
// ─────────────────────────────────────────────

interface ProfileStrings {
    val streakStat: String
    val wordsStat: String
    val lessonsStat: String

    val signOut: String
    val signIn: String

    val plusActive: String
    val plusGet: String
    val plusPromo: String

    val themeGroup: String
    val themeSystem: String
    val themeLight: String
    val themeDark: String

    val languageGroup: String
    val languageRow: String
    val languageRowSubtitle: String

    val soundsGroup: String
    val soundsRow: String
    val soundsOn: String
    val soundsOff: String

    val notificationsGroup: String
    val permissionTitle: String
    val permissionSubtitle: String
    val permissionAction: String
    val permissionNeeded: String
    val dailyReminder: String
    val dailyReminderSubtitle: String
    val reminderOff: String
    val reminderTime: String
    val reminderTimeSubtitle: String

    val helpGroup: String
    val tipsRow: String
    val tipsSubtitle: String
    val tipsAction: String
    val tipsRestored: String

    fun memberSince(month: String): String
    fun plusDaysLeft(days: Int): String
    fun reminderSetAt(time: String): String
}

// ─────────────────────────────────────────────
//  Достижения
// ─────────────────────────────────────────────

interface AchievementsStrings {
    val title: String
    val unlockedTitle: String
    val unlockedButton: String
    val badgeLabel: String
    val overallProgress: String
    val hiddenSubtitle: String

    val streak3: String
    val streak3Desc: String
    val streak7: String
    val streak7Desc: String
    val streak30: String
    val streak30Desc: String
    val words50: String
    val words50Desc: String
    val words100: String
    val words100Desc: String
    val words500: String
    val words500Desc: String
    val lessons5: String
    val lessons5Desc: String
    val lessons20: String
    val lessons20Desc: String
    val lessons50: String
    val lessons50Desc: String
    val chat10: String
    val chat10Desc: String
    val chat50: String
    val chat50Desc: String
    val perfect5: String
    val perfect5Desc: String
    val module1: String
    val module1Desc: String
    val modules5: String
    val modules5Desc: String
    val modules10: String
    val modules10Desc: String
    val langs2: String
    val langs2Desc: String

    fun unlockedCount(done: Int, total: Int): String
    fun hiddenTeaser(count: Int): String
}

// ─────────────────────────────────────────────
//  Рейтинг
// ─────────────────────────────────────────────

interface LeaderboardStrings {
    val title: String
    val emptyTitle: String
    val emptyMessageCity: String
    val emptyMessageGlobal: String
    val filterAll: String
    val filterMyCity: String
    val you: String
    val offline: String
    val signInTitle: String
    val signInMessage: String
    val topOne: String
    val motivation: String

    fun toNextRank(rank: Int, diff: Int): String
}

// ─────────────────────────────────────────────
//  Tuti Plus и пейволл
// ─────────────────────────────────────────────

interface PlusStrings {
    val title: String
    val subtitleUnlimited: String
    val benefitsTitle: String
    val benefitLessons: String
    val benefitChat: String
    val benefitFlashcards: String
    val benefitListening: String
    val benefitCall: String
    val benefitModules: String
    val benefitNoAds: String

    val pricesTitle: String
    val monthly: String
    val yearly: String
    val monthlyPeriod: String
    val yearlyPeriod: String
    val saveBadge: String
    val currency: String

    val howToBuy: String
    val stepTelegram: String
    val stepPay: String
    val stepGetCode: String
    val stepEnterCode: String

    val promoTitle: String
    val promoActivate: String
    val promoInvalid: String
    val promoUsed: String
    val promoError: String

    val freeBadge: String

    val paywallTitle: String
    val paywallBenefitsTitle: String
    val paywallLessons: String
    val paywallChat: String
    val paywallListening: String
    val paywallCall: String
    val paywallPrimary: String
    val paywallSecondary: String

    fun activeDaysLeft(days: Int): String
    fun plusDaysBadge(days: Int): String
    fun paywallMessage(used: Int, max: Int): String
    fun promoSuccess(days: Int): String
}

// ─────────────────────────────────────────────
//  Чат с Tuti
// ─────────────────────────────────────────────

interface ChatStrings {
    val title: String
    val online: String
    val typing: String
    val welcomeTitle: String
    val welcomeSubtitle: String
    val greetingBubble: String
    val inputPlaceholder: String
    val retry: String
    val networkError: String
    val quickHello: String
    val quickShop: String
    val quickGrammar: String
}

// ─────────────────────────────────────────────
//  Уведомления
// ─────────────────────────────────────────────

interface NotificationStrings {
    val channelName: String
    val channelDescription: String

    val reminders: List<Pair<String, String>>
    val streakRiskTitle: String
    val goalTitle: String
    val goalAlmostTitle: String
    val goalAlmostText: String

    fun streakRiskText(streak: Int): String
    fun goalProgressText(studied: Int, remaining: Int): String
}

// ─────────────────────────────────────────────
//  Подсказки поверх главного экрана и сплэш
// ─────────────────────────────────────────────

interface TooltipStrings {
    val xp: String
    val streak: String
    val course: String
    val topics: String
    val practice: String
    val finish: String
    val skip: String
    val next: String
    val start: String

    val splashTagline: String
    val splashRussian: String
    val splashEnglish: String
}

// ─────────────────────────────────────────────
//  Вход через Google
// ─────────────────────────────────────────────

interface AuthStrings {
    val signInError: String
    val unsupportedAccount: String
    val noGoogleAccount: String
    val genericRetry: String

    fun errorWithMessage(message: String): String
}
