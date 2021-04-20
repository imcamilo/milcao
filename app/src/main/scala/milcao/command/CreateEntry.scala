package milcao.command

import milcao.file.{Directory, EntryDirectory}
import milcao.fs.State

abstract class CreateEntry(name: String) extends Command {

  override def apply(state: State): State = {
    val wd = state.workingDirectory
    if (wd.hasEntry(name)) {
      state.setMessage("entry " + name + " already created")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(name + " must not contains separators")
    } else if (checkIllegal(name)) {
      state.setMessage(name + " illegal entry name")
    } else {
      doCreateEntry(state, name)
    }
  }

  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  def doCreateEntry(state: State, name: String): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: EntryDirectory): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head)
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry.asDirectory, path.tail, newEntry))
      }
    }
    val wd = state.workingDirectory
    val allDirsInPath = wd.getAllFoldersInPath

    val newEntry: EntryDirectory = createSpecificEntry(state)

    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)
    val newWorkingDirectory = newRoot.findDescendant(allDirsInPath)
    State(newRoot, newWorkingDirectory)
  }

  def createSpecificEntry(state: State): EntryDirectory

}
