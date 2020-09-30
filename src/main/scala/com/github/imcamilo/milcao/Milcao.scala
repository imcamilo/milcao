package com.github.imcamilo.milcao

import java.util.Scanner

import com.github.imcamilo.milcao.command.Command
import com.github.imcamilo.milcao.file.Directory
import com.github.imcamilo.milcao.fs.State

object Milcao extends FileSystem with App {

  run()

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
