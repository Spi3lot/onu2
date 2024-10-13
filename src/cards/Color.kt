package cards

/**
 *  @since 12.10.2024, Sa.
 *  @author Emilio Zottel
 */
enum class Color {

    RED, GREEN, BLUE, YELLOW;

    val letter: Char
        get() = name[0]

    companion object {

        fun fromLetter(letter: Char): Color? {
            return when (letter.uppercaseChar()) {
                'R' -> RED
                'G' -> GREEN
                'B' -> BLUE
                'Y' -> YELLOW
                '?' -> null
                else -> throw IllegalArgumentException("Invalid color letter: $letter")
            }
        }

    }

}