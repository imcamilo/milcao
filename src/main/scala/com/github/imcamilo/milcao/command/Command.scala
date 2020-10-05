package com.github.imcamilo.milcao.command

import com.github.imcamilo.milcao.fs.State

trait Command {

  def apply(state: State): State

}

object Command {

  val MKDIR = "mkdir"

  def emptyCommand: Command = new Command {
    override def apply(state: State): State = state
  }

  def incompleteCommand(name: String): Command = new Command {
    override def apply(state: State): State = state.setMessage(name + ": incomplete command")
  }

  def from(input: String): Command = {
    val command: Array[String] = input.split(" ")

    if (input.isEmpty || command(0).isEmpty) emptyCommand
    else if (MKDIR.equals(command(0))) {
      if (command.length < 2) incompleteCommand(MKDIR)
      else new Mkdir(command(1))
    } else new UnknowCommand

  }

}