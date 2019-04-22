import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class PackTest {
    // позаимствовал функцию из KotlinAsFirst2018/test/lesson7/task1/Tests.kt
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
    fun invalidArgs() {
        /**
         Тут должны были быть тесты на случаи введения 2 аргументов [-z/-u] и невведения не одного, но возник вопрос.
         Если пользоваться встроенной в args4j опцией для аргументов forbids, то можно сразу отсечь случай введения 2
         аргументов. Однако опцией на введение хотя бы одного вроде бы нету, что заставляет вводить допольнительную
         проверку вида:

         if (!u && !z) {
         throw  SomeException()
         }

         Но тогда встает вопрос: Что будет лучше по производительности и наглядности для пользователя - forbids, который
         не кидает никакого Exception и к которому все равно нужно дополнение вида !u && !z, или же простая проверка
         на u != z ?
         */
        assertThrows(IllegalArgumentException::class.java) {main("input/packthis.txt")}
        assertThrows(IllegalArgumentException::class.java) {main("")}
        main("-z -u input/packthis.txt")
    }

    @Test
    fun app() {
        main("-z input/packthis.txt")
        assertFileContent("input/packthisz.txt","hh6ww9ww2jj5pij")
        main("-u input/packthisz.txt")
        assertFileContent("input/packthiszu.txt","hhhhhhwwwwwwwwwwwjjjjjpij")
        File("input/packthisz.txt").delete()
        File("input/packthiszu.txt").delete()
    }
}