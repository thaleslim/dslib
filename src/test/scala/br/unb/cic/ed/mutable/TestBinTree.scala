package br.unb.cic.ed.mutable

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

trait FG extends FiguraGeometrica with Comparable[FG] {
   override def compareTo(that: FG) = this.area().compareTo(that.area())
}

class TestBinTree extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A tree"

  it should "return true if we call exist(3) in a tree with values 7, 5, 3, 6, 8" in {
    val bt: br.unb.cic.ed.mutable.BinTree[Integer] = new br.unb.cic.ed.mutable.BinTreeImpl[Integer]()

    bt.insert(7)
    bt.insert(5)
    bt.insert(3)
    bt.insert(6)
    bt.insert(8)

    bt.exist(3) should be (true)
  }

  it should "accept FGs " in {
    val bt: br.unb.cic.ed.mutable.BinTree[FG]  = new br.unb.cic.ed.mutable.BinTreeImpl[FG]()

    val c1 = new Circulo(4.5) with FG
    val c2 = new Circulo(3)   with FG
    val c3 = new Circulo(5)   with FG
    bt.insert(c1)
    bt.insert(c2)
    bt.insert(c3)

    bt.exist(new Circulo(5) with FG) should be (true)

  }

}
