package com.github.imcamilo.milcao.file

class Directory(override val parentPath: String, override val name: String, val contents: List[EntryDirectory])
  extends EntryDirectory(parentPath, name) {

}

object Directory {

  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("","")

  def empty(parenthPath: String, name: String): Directory = new Directory(parenthPath, name, List())

}