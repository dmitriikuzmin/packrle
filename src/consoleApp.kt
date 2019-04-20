import org.kohsuke.args4j.*

class PackRle {
    @Option(name = "-z", usage = "Choose packing or unpacking")
    var z: Boolean = false

    @Option(name = "-u", usage = "Choose packing or unpacking")
    var u: Boolean = false

    @Option(name = "-out", usage = "Output file name")
    var output: String = ""

    @Argument(required = true, metaVar = "-in", usage = "Input file name")
    var input: String = ""

    fun launch(args: Array<String>) {
        val parser = CmdLineParser(this)

        try {
            parser.parseArgument(*args)
        } catch (e: CmdLineException) {
            println(e.message)
            println("Try again")
            println(parser.printUsage(System.err))
            return
        }

        val out = when {
            output.isEmpty() && u -> "$input.u"
            output.isEmpty() && z -> "$input.z"
            else -> output
        }

        if (z) {
            Packager().pack(input, out)
        } else {
            Packager().unpack(input, out)
        }
    }
}


fun main(args: Array<String>) {
    PackRle().launch(args)
}














