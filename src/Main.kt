import cards.Card
import cards.Hand

/**
 *  @since 08.10.2024, Di.
 *  @author Emilio Zottel
 */
fun main() {
    val hand = Hand.random(7)
    val topOfStack = Card.fromString("R3")

    while (!hand.isEmpty) {
        val options = hand.scanPlaceOptions(topOfStack)

        if (options.isEmpty()) {
            println("No options, drawing a card...")
            hand.add(Card.random())
        } else {
            val card = options.random()
            println("Playing $card")
            hand.remove(card)
        }
    }
}