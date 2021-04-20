package milcao

import java.util.Scanner
import milcao.command.Command
import milcao.file.Directory
import milcao.fs.State

object Milcao extends FileSystem {

  def main(args: Array[String]): Unit = {
    println(run())
  }

}

trait FileSystem {

  val root: Directory = Directory.ROOT
  val scanner = new Scanner(System.in)
  var state: State = State(root, root)

  def run(): Unit = {
    while (true) {
      state.show()
      val input = scanner.nextLine()
      state = Command.from(input).apply(state)
    }
  }

}
