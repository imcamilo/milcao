package com.github.imcamilo.milcao

object Hello extends UtilMsg with App {
  println(greet)
}

trait UtilMsg {
  lazy val greet: String = "Hi"
}
