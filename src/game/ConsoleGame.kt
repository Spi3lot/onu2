package game

import cards.Card
import cards.Hand

/**
 *  @since 13.10.2024, So.
 *  @author Emilio Zottel
 */
class ConsoleGame {

    private lateinit var topOfStack: Card

    private lateinit var botHand: Hand

    private lateinit var yourHand: Hand

    init {
        reset()
    }

    private fun reset() {
        topOfStack = Card.random()
        botHand = Hand.random(7)
        yourHand = Hand.random(7)
    }

    fun play() {
        while (!botHand.isEmpty && !yourHand.isEmpty) {
            val yourOptions = yourHand.findPlacableCards(topOfStack)
            println("Top of stack: $topOfStack\n")

            if (yourOptions.isEmpty()) {
                print("You: No options, draw a card...")
                readln()
                yourHand.add(Card.random())
            } else {
                println("Your hand (${yourHand.size}): $yourHand")
                println("Options (${yourOptions.size}): $yourOptions")
                val card = Card.readUntilValid(yourOptions)
                yourHand.remove(card)
                topOfStack = card
            }

            println()
            val botOptions = botHand.findPlacableCards(topOfStack)

            if (botOptions.isEmpty()) {
                botHand.add(Card.random())
                println("Bot: No options, drawing a card...")
            } else {
                val card = botOptions.random()
                botHand.remove(card)
                topOfStack = card
                println("Bot: Playing $card")
            }

            println("Bot: ${botHand.size} cards left\n")
        }

        if (yourHand.isEmpty) {
            println("You win!")
        } else {
            println("Bot wins!")
        }
    }

}