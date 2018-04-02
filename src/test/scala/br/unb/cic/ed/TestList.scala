package br.unb.cic.ed

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

trait FiguraGeometrica {
  def area(): Double
}

case class Circulo(val r: Double) extends FiguraGeometrica {
  override def area(): Double = r * r * 3.14
}

case class Retangulo(val b: Double, a: Double) extends FiguraGeometrica {
  override def area(): Double = b * a 
}

class TestList extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A List"

  var list: br.unb.cic.ed.List[Int] = _ 

  before {
    list = new br.unb.cic.ed.LinkedList[Int]()
  }

  it should "have size == 0 before inserting any element" in {
    list.size() should be (0)
  }

  it should "have size == 1 after inserting one element" in {
    list.insert(0, 5)

    list.size() should be (1)
    list.elementAt(0) should be (Some(5))
  }

  it should "have size == 3 after inserting three elements" in {
    list.insert(0, 5)
    list.insert(1, 6)
    list.insert(2, 7)

    list.size() should be (3)
  }

  it should "should keep the list of inserted values" in {
    list.insert(0, 1)
    list.insert(1, 2)
    list.insert(2, 3)

    list.size() should be (3)
    list.elementAt(0) should be (Some(1))
    list.elementAt(1) should be (Some(2))
    list.elementAt(2) should be (Some(3))
  }

  it should "return Some(1) when we call find(1000) in the list [100, 1000, 10000]" in {
    list.insert(0, 100)
    list.insert(1, 1000)
    list.insert(2, 10000)

    list.find(1000) should be (Some(1))
  }

  it should "throw InvalidArgument when we call insert(1, 1) on an empty list" in {
    intercept[InvalidArgument] {
      list.insert(1, 1)
    }
  }
  it should "throw InvalidArgument when we call insert(4, 4) on a list with values [1,3,4]" in {
    list.insert(0, 1)
    list.insert(1, 2)
    list.insert(2, 3)

    intercept[InvalidArgument] {
      list.insert(4,4)
    }

  }
  it should "shift the elements of a list. that is [10,30,40].insert(1,20) = [10,20,30,40]" in {
    list.insert(0, 10)
    list.insert(1, 30)
    list.insert(2, 40)

    list.size() should be (3)
    list.insert(1, 20)

    list.size() should be (4)
    list.elementAt(0) should be (Some(10))
    list.elementAt(1) should be (Some(20))
    list.elementAt(2) should be (Some(30))
    list.elementAt(3) should be (Some(40))
  }

  it should "have size == 2 and shift the elements after removing a element of a list. that is [1,2,88,3].remove(2) = [1,2,3]" in {
    list.insert(0,  1)
    list.insert(1,  2)
    list.insert(2, 88)
    list.insert(3,  3)

    list.size() should be (4)

    list.remove(2)

    list.elementAt(0) should be (Some(1))
    list.elementAt(1) should be (Some(2))
    list.elementAt(2) should be (Some(3))

    list.size() should be (3)
  }

  it should "have the value [10, 30] after removing the second element (idx = 1) in the list [10, 20, 30]" in {
    list.insert(0, 10)
    list.insert(1, 20)
    list.insert(2, 30)

    list.remove(1)

    list.size() should be (2)

    list.elementAt(0) should be (Some(10))
    list.elementAt(1) should be (Some(30)) 
  }

  it should "the value 30 should not appear after removing the third element (idx = 2) in the list [10, 20, 30]" in {
    list.insert(0, 10)
    list.insert(1, 20)
    list.insert(2, 30)

    intercept[InvalidArgument] {
       list.remove(3)
    }    
  }

  it should "lead to a list [3,4,5,6,7,8] when we call [3, 4, 5].addAll([6, 7, 8])" in {
    val list1 = new br.unb.cic.ed.ArrayList[Int](6)
    val list2 = new br.unb.cic.ed.ArrayList[Int]()

    list1.insert(0, 3)
    list1.insert(1, 4)
    list1.insert(2, 5)

    list2.insert(0, 6)
    list2.insert(1, 7)
    list2.insert(2, 8)

    list1.addAll(list2)

    list1.size should be (6)
    list2.size should be (3)

    list1.elementAt(5) should be (Some(8)) 
  }

  it should "lead to a list of [c1, r1, c2, c3, c4] when we call [c1, r1, c2].addAll[c3, c4]]" in {
    val list1 = new br.unb.cic.ed.ArrayList[FiguraGeometrica]()
    val list2 = new br.unb.cic.ed.ArrayList[Circulo]()

    list1.insert(0, Circulo(3.5))
    list1.insert(1, Retangulo(3.0, 2.0))
    list1.insert(2, Circulo(4.0))

    list1.size should be (3) 

    list2.insert(0, Circulo(10))
    list2.insert(1, Circulo(30))

    list2.size should be (2)

    list1.addAll(list2)

    list1.size() should be (5)
  }
}
