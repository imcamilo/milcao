package milcao.command

import milcao.file.{EntryDirectory, File}
import milcao.fs.State

class Touch(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): EntryDirectory =
    File.empty(state.workingDirectory.path, name)

}
