// CardSearch model for search results
data class CardSearch(
    val cardId: String,
    val dbfId: Int,
    val name: String,
    val cardSet: String,
    val text: String?,
    val spellSchool: String?,
    val health: Int?,
    val artist: String?,
    val collectible: Boolean?,
    val cost: Int?,
    val attack: Int?,
    val flavor: String?,
    val img: String?,
    val imgGold: String?,
    val elite: Boolean?,
    val howToGet: String?,
    val howToGetGold: String?,
    val multiClassGroup: String?,
    val classes: List<String>?,
    val durability: Int?,
    val howToGetSignature: String?
)