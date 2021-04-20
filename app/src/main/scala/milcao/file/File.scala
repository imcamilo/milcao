package milcao.file

import milcao.fs.MilcaoFileSystemException

class File(override val parentPath: String, override val name: String, val contents: String)
  extends EntryDirectory(parentPath, name) {

  override def asDirectory: Directory =
    throw new MilcaoFileSystemException("file cannot be converted to a directory")

  override def asFile: File = this

  override def getType: String = "file"

}

object File {

  def empty(parenthPath: String, fileName: String): File = new File(parenthPath, fileName, "")

}