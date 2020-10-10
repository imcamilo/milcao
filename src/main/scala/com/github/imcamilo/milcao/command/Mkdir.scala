package com.github.imcamilo.milcao.command

import com.github.imcamilo.milcao.file.{Directory, EntryDirectory}
import com.github.imcamilo.milcao.fs.State

class Mkdir(val name: String) extends Command {

  override def apply(state: State): State = {
    val wd = state.workingDirectory
    if (wd.hasEntry(name)) {
      state.setMessage("entry " + name + " already created")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(name + " must not contains separators")
    } else if (checkIllegal(name)) {
      state.setMessage(name + " illegal entry name")
    } else {
      doMkdir(state, name)
    }
  }

  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  def doMkdir(state: State, name: String): State = {

    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: EntryDirectory): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head)
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry.asDirectory, path.tail, newEntry))
      }
    }

    val wd = state.workingDirectory
    val allDirsInPath = wd.getAllFoldersInPath
    val newDirectory = Directory.empty(wd.path, name)
    val newRoot = updateStructure(state.root, allDirsInPath, newDirectory)
    val newWorkingDirectory = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWorkingDirectory)
  }

}
