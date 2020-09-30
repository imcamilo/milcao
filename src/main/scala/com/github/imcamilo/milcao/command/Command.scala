package com.github.imcamilo.milcao.command

import com.github.imcamilo.milcao.fs.State

trait Command {

  def apply(state: State): State

}

object Command {

  def from(input:String): Command = new UnknowCommand

}