import org.junit.Test
import kotlin.test.assertEquals

class GetNextNonSpaceIndexTest {

    @Test
    fun `general case`() {
        assertEquals(getNextNonSpaceIndex(-1, "123"), 0)
    }

    @Test
    fun `skip starting spaces`() {
        assertEquals(getNextNonSpaceIndex(0, "  123"), 2)
    }

    @Test
    fun `skip non starting`() {
        assertEquals(getNextNonSpaceIndex(1, "1  23"), 3)
    }

    @Test
    fun `incorrect index`() {
        assertEquals(getNextNonSpaceIndex(1, "1"), -1)
    }

    @Test
    fun `empty string`() {
        assertEquals(getNextNonSpaceIndex(0, ""), -1)
    }

    @Test
    fun `skipping tail spaces`() {
        assertEquals(getNextNonSpaceIndex(1, "1  "), -1)
    }
}