package com.github.imcamilo.milcao.command
import com.github.imcamilo.milcao.fs.State

class Pwd extends Command   {

  override def apply(state: State): State = state.setMessage(state.workingDirectory.path)

}
