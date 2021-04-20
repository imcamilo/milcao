package milcao.command

import milcao.fs.State

trait Command {

  def apply(state: State): State

}

object Command {

  val MKDIR = "mkdir"
  val LS = "ls"
  val PWD = "pwd"
  val TOUCH = "touch"
  val CD = "cd"

  def emptyCommand: Command =
    (state: State) => state

  def incompleteCommand(name: String): Command =
    (state: State) => state.setMessage(name + ": incomplete command")

  def from(input: String): Command = {
    val command: Array[String] = input.split(" ")

    if (input.isEmpty || command(0).isEmpty) {
      emptyCommand
    } else if (MKDIR.equals(command(0))) {
      if (command.length < 2) incompleteCommand(MKDIR)
      else new Mkdir(command(1))
    } else if (LS.equals(command(0))) {
      new Ls
    } else if (PWD.equals(command(0))) {
      new Pwd
    } else if (TOUCH.equals(command(0))) {
      if (command.length < 2) incompleteCommand(TOUCH)
      else new Touch(command(1))
    } else {
      new UnknowCommand
    }

  }

}