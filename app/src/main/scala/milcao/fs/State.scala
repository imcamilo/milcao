package milcao.fs

import milcao.file.Directory

class State(val root: Directory, val workingDirectory: Directory, val output: String) {

  def show(): Unit = {
    if (!output.isEmpty) println(output)
    print(State.SHELL_TOKEN)
  }

  def setMessage(msg: String): State = State(root, workingDirectory, msg) //.apply

}

object State {

  val SHELL_TOKEN = "milcao> "

  def apply(root: Directory, workingDirectory: Directory, output: String = ""): State =
    new State(root, workingDirectory, output)

}