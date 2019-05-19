data class TextRange(val start: Int, val endExclusive: Int) {
    fun pretty(text: String): String = "${toString()} ${text.substring(start, endExclusive)}"

    override fun toString(): String = "[$start, $endExclusive)"
}


/**
 * Returns a text range corresponding to [marker] ignoring whitespaces.
 * Leading and trailing whitespaces from [text] are not included.
 * Returns null if [marker] is blank or not found in [text].
 */
fun findMarkerIgnoringSpace(text: String, marker: String): TextRange? {
    if (text.isEmpty()) return null

    val spacelessMarker = marker.filter { !it.isWhitespace() }
    if (spacelessMarker.isEmpty()) return null

    outer@
    for (textIndex in 0 until text.length - spacelessMarker.length + 1) {
        if (text[textIndex].isWhitespace()) continue
        var current: Int = textIndex - 1

        for (markerIndex in spacelessMarker.indices) {
            current = getNextNonSpaceIndex(current, text)
            if (current == -1) return null

            if (text[current] != spacelessMarker[markerIndex]) continue@outer
        }
        return TextRange(textIndex, current + 1)
    }
    return null
}

/**
 * Returns index, that is strictly greater than [startIndex] corresponding to first non whitespace char
 */
fun getNextNonSpaceIndex(startIndex: Int, text: String): Int {
    for (i in startIndex + 1 until text.length) {
        if (!text[i].isWhitespace()) {
            return i
        }
    }
    return -1
}
