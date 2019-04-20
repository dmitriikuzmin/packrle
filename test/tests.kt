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
        Packager().pack("input/packthis.txt","input/goodbye.txt")
        assertFileContent("input/goodbye.txt", """hh6ww9ww2jj5pij""")
        File("input/goodbye.txt").delete()
    }

    @Test
    fun unpack() {
        Packager().unpack("input/unpackthis.txt","input/hello.txt")
        assertFileContent("input/hello.txt", """hheeeejjjjjop""")
        File("input/hello.txt").delete()
    }

    @Test
    fun app() {
        main(arrayOf("-z","input/packthis.txt"))
        assertFileContent("input/packthis.txt.z","hh6ww9ww2jj5pij")
        main(arrayOf("-u","input/packthis.txt.z"))
        assertFileContent("input/packthis.txt.z.u","hhhhhhwwwwwwwwwwwjjjjjpij")
        File("input/packthis.txt.z").delete()
        File("input/packthis.txt.z.u").delete()
    }
}