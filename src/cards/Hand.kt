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
        val index = cards.binarySearch(card)

        if (index < 0) {
            cards.add(index.inv(), card)
        } else {
            cards.add(index, card)
        }
    }

    fun remove(card: Card) {
        cards.remove(card)
    }

    fun findPlacableCards(topOfStack: Card): Set<Card> {
        return cards.filter { it.canBePlacedOn(topOfStack) }
            .toSet()
    }

    override fun toString(): String {
        return cards.joinToString(" ")
    }

}