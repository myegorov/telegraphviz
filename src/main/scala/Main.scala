package cli

import java.io.File
import java.nio.file.{Paths, Files}
import scopt.OParser

case class Config(
  mode: String = "",
  fromDot: File = new File("."),
  toDot: File = new File("."),
  outDot: File = new File(".")
)

object Main extends App {
  val builder = OParser.builder[Config]
  val parser = {
    import builder._
    OParser.sequence(
      programName("telegraphviz"),
      head("telegraphviz", "0.1"),
      cmd("diff")
        .action((_, c) => c.copy(mode = "diff"))
        .text("diff 2 DOT files")
        .children(
          opt[String]('f', "from")
            .required()
            .valueName("<dot-file>")
            .validate(x =>
                if (Files.isRegularFile(Paths.get(x))) success
                else failure(s"$x must be a regular file."))
            .action((x, c) => c.copy(fromDot = new File(x)))
            .text("Graphviz .dot file to diff from"),
          opt[String]('t', "to")
            .required()
            .valueName("<dot-file>")
            .validate(x =>
                if (Files.isRegularFile(Paths.get(x))) success
                else failure(s"$x must be a regular file."))
            .action((x, c) => c.copy(toDot = new File(x)))
            .text("Graphviz .dot file to diff to"),
          opt[String]('o', "out")
            .required()
            .valueName("<dot-file>")
            .action((x, c) => c.copy(outDot = new File(x)))
            .text("Graphviz .dot file to write to")
        )
    )
  }

  OParser.parse(parser, args, Config()) match {
    case Some(config) =>
      println(s"config: $config")
    case None => // failure state, prints usage
  }
}
