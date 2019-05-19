import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class FindMarkerIgnoringSpaceTest {
    @Test
    fun `from example`() = testMarker(
        "Text [1299bba / 0 0 0 0 1] from David",
        "[1299 bba/00001]",
        "[5, 26) [1299bba / 0 0 0 0 1]"
    )

    @Test
    fun `no occurrence`() = testMarker(
        "b",
        "a",
        null
    )

    @Test
    fun `empty marker`() = testMarker(
        "123",
        "",
        null
    )

    @Test
    fun `empty text`() = testMarker(
        "",
        "a",
        null
    )

    @Test
    fun `empty text and marker`() = testMarker(
        "",
        "",
        null
    )

    @Test
    fun `marker longer than text`() = testMarker(
        "aa",
        "aaa",
        null
    )

    @Test
    fun `text contains only spaces`() = testMarker(
        "  ",
        "a",
        null
    )

    @Test
    fun `marker contains only spaces`() = testMarker(
        "aaaaa",
        "  ",
        null
    )

    @Test
    fun `text and marker contains only spaces`() = testMarker(
        "   ",
        " ",
        null
    )

    @Test
    fun `new line`() = testMarker(
        "1ab\ncd",
        "ab\nc",
        "[1, 5) ab\nc"
    )

    @Test
    fun `leading spaces not included`() = testMarker(
        "  ffasd",
        "ff",
        "[2, 4) ff"
    )

    @Test
    fun `trailing spaces not included`() = testMarker(
        "ff  ",
        "ff",
        "[0, 2) ff"
    )


    private fun testMarker(text: String, marker: String, expected: String?) {
        val range = findMarkerIgnoringSpace(text, marker)
        if (range == null) {
            if (expected == null) return
            fail("Expected $expected, but not found")
        }
        val actual = range.pretty(text)
        assertEquals(expected, actual)
    }
}