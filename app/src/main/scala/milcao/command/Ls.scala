package milcao.command

import milcao.file.EntryDirectory
import milcao.fs.State

class Ls extends Command {

  override def apply(state: State): State = {
    val contents = state.workingDirectory.contents
    state.setMessage(createOutputMessage(contents))
  }

  def createOutputMessage(contents: List[EntryDirectory]): String = {
    if (contents.isEmpty) ""
    else {
      val entry = contents.head
      entry.name + "[" + entry.getType + "]\n" + createOutputMessage(contents.tail)
    }
  }

}
