package com.github.imcamilo.milcao.file

import com.github.imcamilo.milcao.fs.MilcaoFileSystemException

import scala.annotation.tailrec

class Directory(override val parentPath: String, override val name: String, val contents: List[EntryDirectory])
  extends EntryDirectory(parentPath, name) {

  def addEntry(newEntry: EntryDirectory): Directory = new Directory(parentPath, name, contents :+ newEntry)

  override def asDirectory: Directory = this

  override def asFile: File = throw new MilcaoFileSystemException("a directory cannot be converter to a file")

  def findDescendant(path: List[String]): Directory =
    if (path.isEmpty) this
    else findEntry(path.head).asDirectory.findDescendant(path.tail)

  def findEntry(entryName: String): EntryDirectory = {

    @tailrec
    def findEntryHelper(name:String, contentList: List[EntryDirectory]): EntryDirectory =
      if (contentList.isEmpty) null
      else if (contentList.head.name.equals(name)) contentList.head
      else findEntryHelper(name, contentList.tail)

    findEntryHelper(entryName, contents)
  }

  def hasEntry(name: String): Boolean = findEntry(name) != null

  def getAllFoldersInPath: List[String] =
    path.substring(1).split(Directory.SEPARATOR).toList.filter(p => !p.isEmpty)

  override def getType: String = "Directory"

  def replaceEntry(entryName: String, newEntry: EntryDirectory): Directory =
    new Directory(parentPath, entryName, contents.filter(p => !p.name.equals(entryName)) :+ newEntry)

}

object Directory {

  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parenthPath: String, name: String): Directory = new Directory(parenthPath, name, List())

}