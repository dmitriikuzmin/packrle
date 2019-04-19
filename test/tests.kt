import org.junit.Test
import org.junit.Assert.*
import java.io.File

class PackTest {
    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @Test
    fun pack() {
        Packager().pack("packthis","goodbye")
        assertFileContent("input/goodbye.txt", """hh6ww11jj5pij""")
    }

    @Test
    fun unpack() {
        Packager().unpack("unpackthis","hello")
        assertFileContent("input/hello.txt", """hheeeejjjjjop""")
    }
}