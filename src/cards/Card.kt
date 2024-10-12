package cards

/**
 *  @since 12.10.2024, Sa.
 *  @author Emilio Zottel
 */
interface Card {

    companion object {

        fun random(): NumberCard {
            val color = Color.entries.random()
            val value = (0..9).random()
            return NumberCard(color, value)
        }

    }

    fun canBePlacedOn(card: Card): Boolean

    override fun toString(): String

}