package cards

/**
 *  @since 12.10.2024, Sa.
 *  @author Emilio Zottel
 */
class Hand private constructor() {

    private val cards = arrayListOf<Card>()

    val size
        get() = cards.size

    val isEmpty
        get() = cards.isEmpty()

    companion object {

        fun random(size: Int): Hand {
            return Hand().apply {
                for (i in 0..<size) {
                    add(Card.random())
                }
            }
        }

    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun remove(card: Card) {
        cards.remove(card)
    }

    fun canPlaceOn(card: Card): Boolean {
        return cards.any { it.canBePlacedOn(card) }
    }

    override fun toString(): String {
        return cards.joinToString(" ")
    }

}