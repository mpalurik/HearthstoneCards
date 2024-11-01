// CardDetail model for detailed card information
data class CardDetail(
    val cardId: String,
    val dbfId: Int,
    val name: String,
    val cardSet: String,
    val type: String,
    val faction: String?,
    val rarity: String?,
    val health: Int?,
    val armor: String?,
    val artist: String?,
    val playerClass: String?,
    val img: String?,
    val imgGold: String?,
    val locale: String?,
    val cost: Int?,
    val attack: Int?
)
