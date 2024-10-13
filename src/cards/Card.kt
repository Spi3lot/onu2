package cards

/**
 *  @since 12.10.2024, Sa.
 *  @author Emilio Zottel
 */
data class Card(val color: Color?, val value: Int?) : Comparable<Card> {


    companion object {

        fun readUntilValid(placableCards: Collection<Card>): Card? {
            print("Choose a card to play or press ENTER to draw: ")

            while (true) {
                try {
                    val card = fromStringOrNull(readln())

                    if (card == null || card in placableCards) {
                        return card
                    }

                    print("Please choose one of the options: ")
                } catch (e: IllegalArgumentException) {
                    print("Invalid card, try again: ")
                }
            }
        }

        private fun fromStringOrNull(string: String): Card? {
            if (string.isEmpty()) {
                return null
            }

            require(string.length == 2)
            val color = Color.fromLetter(string[0])
            val value = "${string[1]}".toIntOrNull()
            return Card(color, value)
        }

        fun random(): Card {
            val colors = Color.entries.toMutableList<Color?>()
            val values = (0..9).toMutableList<Int?>()
            colors.add(null)
            values.add(null)
            val color = colors.random()
            val value = values.random()
            return Card(color, value)
        }

    }

    fun canBePlacedOn(card: Card): Boolean {
        return color == null ||
                value == null ||
                (card.color == null && card.value == null) ||
                (card.color != null && card.value == null && color == card.color) ||
                (card.color == null && card.value != null && value == card.value) ||
                (card.color != null && card.value != null && (color == card.color || value == card.value))
    }

    override fun compareTo(other: Card): Int {
        return compareValuesBy(this, other, { it.color }, { it.value })
    }

    override fun toString(): String {
        return when {
            color == null && value == null -> "??"
            color == null -> "?$value"
            value == null -> "${color.letter}?"
            else -> "${color.letter}$value"
        }
    }

}