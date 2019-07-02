package subproject

import scopt.OptionParser

case class Config(verbose: Boolean = false)

object Main extends App {
  val parser = new OptionParser[Config]("cli") {
    head("cli", "0.x")

    opt[Boolean]('v', "verbose")
      .action((x, c) => c.copy(verbose = x))
      .text("not much here...")
  }

  parser.parse(args, Config()) match {
    case Some(config) =>
      println(s"verbose is set to ${config.verbose}")
    case None => // failure state, prints usage
  }
}
