import java.io.File

class Packager {
    fun pack(input: String, output: String) {
        val writer = File("input/$output.txt").bufferedWriter()
        val str = File("input/$input.txt").readText()
        var newStr = ""
        var i = 0
        while (i != str.length) {
            var count = 1
            while (i != str.length - 1 && str[i] == str[i + 1]) {
                count++
                i++
            }
            newStr += if (count != 1) str[i].toString().repeat(2) + count
            else str[i]
            i++
        }
        writer.write(newStr)
        writer.close()
    }

    fun unpack(input: String, output: String) {
        val writer = File("input/$output.txt").bufferedWriter()
        val str = File("input/$input.txt").readText()
        var newStr = ""
        var i = 0
        while (i != str.length) {
            if (str[i] == str[i + 1]) {
                newStr += "${str[i]}".repeat(str[i + 2].toInt() - 48)
                if (str.length - i != 3) i += 3
                else break // переделать
            } else {
                newStr += str[i]
                i++
                //if (i == str.length - 2) newStr += str.last()
            }
        }
        writer.write(newStr)
        writer.close()
    }
}