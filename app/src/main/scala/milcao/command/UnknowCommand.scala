package milcao.command

import milcao.fs.State

class UnknowCommand extends Command {

  override def apply(state: State): State = state.setMessage("command not found")

}
