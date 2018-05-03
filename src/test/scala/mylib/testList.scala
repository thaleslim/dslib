import org.scalatest.{Filter => _, _}
import mylib.immutable

import mylib.exceptions._

class testList extends FlatSpec with Matchers {
    "A List" should "have size = 0 and be empty after initialized with no arguments" in {
        val myList = immutable.List()
        myList.size should be (0)
        myList.isEmpty should be (true)
    }
    it should "have size 3 and not be empty after initializing with 3 elements" in {
        val myList = immutable.List(1, 2, 3)
        myList.size should be (3)
        myList.isEmpty should be (false)
    }
    it should "work with apply" in {
        val myList = immutable.List(1, 2, 3)
        
        myList(0) should be (1)
        myList(1) should be (2)
        myList(2) should be (3)
    }
    it should "throw negative index exception when apply is used with negative argument" in {
        val myList = immutable.List(1, 2, 3)

        intercept[NegativeIndex] {
            myList(-1)
        }
    }
    it should "throw out of bounds exception when apply is used with argument greater than or equal to size" in {
        val myList = immutable.List(1, 2, 3)

        intercept[IndexOutOfBounds] {
            myList(3)
        }
        intercept[IndexOutOfBounds] {
            myList(4)
        }
    }
    it should "work with the :: operator" in {

        val myList  = immutable.List(2, 3)
        val myList2 = 1 :: myList

        myList2.isEmpty should be (false)
        myList2.size should be (3)
        myList2(0) should be (1)
        myList2(1) should be (2)
        myList2(2) should be (3)

        val myList3: immutable.List[Int] = 1 :: 2 :: 3 :: 4 :: immutable.EmptyList

        myList3.isEmpty should be (false)
        myList3.size should be (4)
        myList3(0) should be (1)
        myList3(1) should be (2)
        myList3(2) should be (3)
        myList3(3) should be (4)
    }
    it should "work with the invert operator" in {
        val myList  = immutable.List(1, 2, 3)
        val invList = myList invert

        invList.isEmpty should be (false)
        invList.size should be (3)
        invList(0) should be (3)
        invList(1) should be (2)
        invList(2) should be (1)
    }
    it should "work with the ::: operator" in {
        val myList1 = immutable.List(1, 2)
        val myList2 = immutable.List(3, 4)
        val myList3 = myList1 ::: myList2

        myList3.isEmpty should be (false)
        myList3.size should be (4)
        myList3(0) should be (1)
        myList3(1) should be (2)
        myList3(2) should be (3)
        myList3(3) should be (4)
    }
    it should "work with the hasValue() method" in {
        val myList = immutable.List(1, 2, 3)

        myList.hasValue(0) should be (false)
        myList.hasValue(1) should be (true)
        myList.hasValue(2) should be (true)
        myList.hasValue(3) should be (true)
        myList.hasValue(4) should be (false)
    }
    it should "have a working toSting method" in {

        immutable.List()       .toString should be ("List()")
        immutable.EmptyList    .toString should be ("List()")
        immutable.List(1)      .toString should be ("List(1)")
        immutable.List(1, 2)   .toString should be ("List(1, 2)")
        immutable.List(1, 2, 3).toString should be ("List(1, 2, 3)")
    }
    it should "work with iterators" in {
        val myList     = immutable.List(1, 2, 3)
        val myIterator = myList.getIterator()

        myIterator.value   should be (1)
        myIterator.hasNext should be (true)
        
        myIterator.next()
        
        myIterator.value   should be (2)
        myIterator.hasNext should be (true)
        
        myIterator.next()
        
        myIterator.value   should be (3)
        myIterator.hasNext should be (false)
        
        intercept[IteratorOutOfBounds] {
            myIterator.next()
        }
    }
    it should "work with the foreach() method" in {
        var str = ""
        immutable.List(1, 2, 3, 4) foreach {
            (num: Int) => str = str + "-" + num
        }

        str should be ("-1-2-3-4")
    }
}