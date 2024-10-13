package cards

/**
 *  @since 12.10.2024, Sa.
 *  @author Emilio Zottel
 */
data class Card(val color: Color?, val value: Int?) : Comparable<Card> {

    companion object {

        fun fromString(string: String): Card {
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
        return (color == card.color && value == card.value) ||
                (color == card.color && value == null) ||
                (color == null && value == card.value) ||
                (color == null && value == null)
    }

    override fun compareTo(other: Card): Int {
        return Comparator.nullsFirst(
            Comparator.comparing<Card, Color> { it.color!! }
                .thenComparingInt { it.value!! }
        ).compare(this, other)
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