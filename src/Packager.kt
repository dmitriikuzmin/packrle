import java.io.File

class Packager {
    fun pack(input: String, output: String) {
        val writer = File(output).bufferedWriter()
        val str = File(input).readText()
        val sb = StringBuilder()
        var i = 0

        while (i < str.length) {
            var count = 1
            while (i != str.length - 1 && str[i] == str[i + 1] && count != 9) {
                count++
                i++
            }
            if (count != 1) sb.append(str[i]).append(str[i]).append(count)
            else sb.append(str[i])
            i++
        }

        writer.write(sb.toString())
        writer.close()
    }

    fun unpack(input: String, output: String) {
        val str = File(input).readText()
        val writer = File(output).bufferedWriter()
        val sb = StringBuilder()
        var i = 0

        while (i < str.length - 1) {
            if (str[i] == str[i + 1]) {
                sb.append("${str[i]}".repeat("${str[i + 2]}".toInt()))
                i += 3
            } else {
                sb.append(str[i])
                i++
            }
        }
        if (i == str.length - 1) sb.append(str.last())

        writer.write(sb.toString())
        writer.close()
    }
}