package app.tuti.tj.data.user

// ════════════════════════════════════════════════════════════════
//  ГОРОДА
//
//  В профиль уходит код (`dushanbe`), а не подпись из интерфейса.
//  Причина простая: скоро в списке появятся российские города, а
//  рейтинг должен группироваться по устойчивому идентификатору.
//  Строка, которая зависит от языка интерфейса, для этого не
//  годится — один и тот же город разъехался бы на две группы.
//
//  [tajikName] остаётся здесь потому, что накопленные документы и
//  tuti_prefs хранят город именно так, и лидерборд считает по нему.
//  Код — новое поле рядом, а не замена старому.
// ════════════════════════════════════════════════════════════════

enum class CityRegion { CAPITAL, NORTH, SOUTH, KHATLON, SUGHD, CENTRAL, OTHER }

data class TutiCity(
    val code: String,
    /** Значение, которое лежит в tuti_prefs и в поле `city` документа. */
    val tajikName: String,
    val emoji: String,
    val region: CityRegion,
)

object CityCatalog {

    val all = listOf(
        TutiCity("dushanbe",    "Душанбе",    "🏛️", CityRegion.CAPITAL),
        TutiCity("khujand",     "Хуҷанд",     "🏔️", CityRegion.NORTH),
        TutiCity("bokhtar",     "Бохтар",     "☀️", CityRegion.SOUTH),
        TutiCity("kulob",       "Кӯлоб",      "🌿", CityRegion.KHATLON),
        TutiCity("istaravshan", "Истаравшан", "🏰", CityRegion.SUGHD),
        TutiCity("konibodom",   "Конибодом",  "🍑", CityRegion.SUGHD),
        TutiCity("tursunzoda",  "Турсунзода", "📚", CityRegion.CENTRAL),
        TutiCity("panjakent",   "Пенҷикент",  "🎨", CityRegion.SUGHD),
        TutiCity("gafurov",     "Ғафуров",    "🌾", CityRegion.SUGHD),
        TutiCity("vahdat",      "Ваҳдат",     "🏗️", CityRegion.CENTRAL),
        TutiCity("isfara",      "Исфара",     "🌄", CityRegion.SUGHD),
        TutiCity("norak",       "Норак",      "🔧", CityRegion.KHATLON),
        TutiCity("yovon",       "Ёвон",       "🏭", CityRegion.KHATLON),
        TutiCity("other",       "Дигар",      "🏘️", CityRegion.OTHER),
    )

    val default: TutiCity = all.first()

    fun byIndex(index: Int?): TutiCity = all.getOrElse(index ?: 0) { default }

    fun byCode(code: String?): TutiCity =
        all.firstOrNull { it.code == code } ?: default
}
