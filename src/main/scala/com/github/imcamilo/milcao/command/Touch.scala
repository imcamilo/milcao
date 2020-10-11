package com.github.imcamilo.milcao.command

import com.github.imcamilo.milcao.file.{EntryDirectory, File}
import com.github.imcamilo.milcao.fs.State

class Touch(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): EntryDirectory =
    File.empty(state.workingDirectory.path, name)

}
