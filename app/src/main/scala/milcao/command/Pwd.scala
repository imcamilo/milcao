package milcao.command

import milcao.fs.State

class Pwd extends Command   {

  override def apply(state: State): State = state.setMessage(state.workingDirectory.path)

}
