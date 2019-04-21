import org.kohsuke.args4j.*
import java.lang.IllegalArgumentException

class PackRle {
    @Option( name = "-z", usage = "Choose packing or unpacking", forbids = ["-u"])
    var z: Boolean = false

    @Option(name = "-u", usage = "Choose packing or unpacking", forbids = ["-z"])
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

        if (!u && !z) {
            throw  IllegalArgumentException() // CmdLineException не кидается
        }

        val out = when {
            output.isEmpty() && u -> StringBuilder(input).insert(input.indexOf("."),"u").toString()
            output.isEmpty() && z -> StringBuilder(input).insert(input.indexOf("."),"z").toString()
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














