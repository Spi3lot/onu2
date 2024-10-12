package cards

/**
 *  @since 12.10.2024, Sa.
 *  @author Emilio Zottel
 */
class NumberCard(val color: Color, val value: Int) : Card {

    override fun canBePlacedOn(card: Card): Boolean {
        return when (card) {
            is NumberCard -> color == card.color || value == card.value
            is SpecialCard -> color == card.color
            is WildCard -> true
            else -> false
        }
    }

    override fun toString(): String {
        return "${color.letter}$value"
    }

}