package br.unb.cic.ed

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter


class TestStack extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A Stack"

  var stack : br.unb.cic.ed.Stack[Int] = _

  

  it should "have size == 0 before stacking any element" in {
    stack.size() should be (0)
  }

  it should "have size == 3 after stacking 3 elements" in {
    stack.size() should be (0)

    stack.push(3)
    stack.push(2)
    stack.push(1)

    stack.size() should be (3)
  }

  it should "return Some(10) after stacking the following elements: 30,20,10; and popping once" in {
    stack.size() should be (0)

    stack.push(30)
    stack.push(20)
    stack.push(10)

    stack.size() should be (3)

    stack.pop() should be (Some(10))

    stack.size() should be (2)

    stack.pop() should be (Some(20))
    stack.pop() should be (Some(30))

    stack.size() should be (0) 
  }


  it should "should return None when we call pop() on an empty stack" in {
    stack.pop() should be (None)
  }

  it should "throw InvalidArgument when we call push(1) on an full stack" in {
    val smallStack = new br.unb.cic.ed.StackImpl[Int](new ArrayList[Int](1))

    smallStack.push(1)

    intercept[InvalidArgument] {
      smallStack.push(1)
    }
  }

  before {
    stack = new br.unb.cic.ed.StackImpl[Int](new ArrayList[Int]())
  }

}
