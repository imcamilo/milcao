package com.github.imcamilo.milcao.command

import com.github.imcamilo.milcao.file.{Directory, EntryDirectory}
import com.github.imcamilo.milcao.fs.State

class Mkdir(val name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): EntryDirectory =
    Directory.empty(state.workingDirectory.path, name)

}
