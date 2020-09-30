package com.github.imcamilo.milcao.command

import com.github.imcamilo.milcao.fs.State

class UnknowCommand extends Command {

  override def apply(state: State): State = state.setMessage("Command not found!")

}
