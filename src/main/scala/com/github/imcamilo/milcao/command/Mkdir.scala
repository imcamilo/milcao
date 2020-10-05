package com.github.imcamilo.milcao.command

import com.github.imcamilo.milcao.file.Directory
import com.github.imcamilo.milcao.fs.State

class Mkdir(val name: String) extends Command {

  override def apply(state: State): State = {
    //receive wd
    val wd = state.workingDirectory
    if (wd.hasEntry(name)) {
      state.setMessage("entry " + name + "already created")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(name + "must not contains separators")
    } else if (checkIllegal(name)) {
      state.setMessage(name + "illegal entry name")
    } else {
      doMkdir(state, name)
    }
  }

  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  def doMkdir(state: State, name: String): State = {
    ???
  }

}
