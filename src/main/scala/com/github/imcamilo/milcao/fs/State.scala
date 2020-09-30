package com.github.imcamilo.milcao.fs

import com.github.imcamilo.milcao.file.Directory

class State(val root: Directory, val workingDirectory: Directory, val output: String) {

  def show(): Unit =
    print(output)
    print(State.SHELL_TOKEN)

  def setMessage(msg: String): State = State(root, workingDirectory, msg) //.apply

}

object State {

  val SHELL_TOKEN = "milcao>"

  def apply(root: Directory, workingDirectory: Directory, output: String = ""): State =
    new State(root, workingDirectory, output)

}