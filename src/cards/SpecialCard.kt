package cards

abstract class SpecialCard(val color: Color) : Card {

    override fun canBePlacedOn(card: Card): Boolean {
        return when (card) {
            is NumberCard -> color == card.color
            is SpecialCard -> color == card.color
            is WildCard -> true
            else -> false
        }
    }

}