package com.github.imcamilo.milcao.file

abstract class EntryDirectory(val parentPath: String, val name: String) {

  def asDirectory: Directory

  def getType: String

  def path: String = parentPath + Directory.SEPARATOR + name

}
