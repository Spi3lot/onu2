package cards

class WildCard : Card {

    override fun canBePlacedOn(card: Card): Boolean {
        return true
    }

    override fun toString(): String {
        return "WC"
    }

}