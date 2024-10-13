package game

import cards.Card
import cards.Hand

/**
 *  @since 13.10.2024, So.
 *  @author Emilio Zottel
 */
class ConsoleGame(private var drawUntilValid: Boolean) {

    private lateinit var topOfStack: Card

    private lateinit var botHand: Hand

    private lateinit var yourHand: Hand

    init {
        reset()
    }

    fun reset() {
        topOfStack = Card.random()
        botHand = Hand.random(7)
        yourHand = Hand.random(7)
    }

    fun play() {
        var yourTurn = true

        while (!botHand.isEmpty && !yourHand.isEmpty) {
            if (yourTurn) {
                println("---------- YOU ----------")
                yourTurn()
            } else {
                println("---------- BOT ----------")
                botTurn()
            }

            yourTurn = !yourTurn
        }

        printWinner()
    }

    private fun yourTurn() {
        val yourOptions = yourHand.findPlacableCards(topOfStack)
        println("Top of stack: $topOfStack")

        if (yourOptions.isEmpty()) {
            print("No options, draw a card...\n")
            readln()
            yourHand.add(Card.random())

            if (drawUntilValid) {
                yourTurn()
            }
        } else {
            println("\n${yourHand.size} cards remaining: $yourHand")
            println("Options (${yourOptions.size}): $yourOptions")
            val card = Card.readUntilValid(yourOptions)

            if (card == null) {
                println("Drawing a card...")
                yourHand.add(Card.random())
            } else {
                println("Playing $card")
                yourHand.remove(card)
                topOfStack = card
            }

            println()
        }
    }

    private fun botTurn() {
        val botOptions = botHand.findPlacableCards(topOfStack)

        if (botOptions.isEmpty()) {
            botHand.add(Card.random())
            println("No options, drawing a card...")

            if (drawUntilValid) {
                botTurn()
            }
        } else {
            val card = botOptions.random()
            botHand.remove(card)
            topOfStack = card
            println("Playing $card")
            println("${botHand.size} cards remaining\n")
        }

    }

    private fun printWinner() {
        if (yourHand.isEmpty) {
            println("You win!")
        } else if (botHand.isEmpty) {
            println("Bot wins!")
        } else {
            error("No winner yet")
        }
    }

}