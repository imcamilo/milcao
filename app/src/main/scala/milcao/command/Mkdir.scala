package milcao.command

import milcao.file.{Directory, EntryDirectory}
import milcao.fs.State

class Mkdir(val name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): EntryDirectory =
    Directory.empty(state.workingDirectory.path, name)

}
