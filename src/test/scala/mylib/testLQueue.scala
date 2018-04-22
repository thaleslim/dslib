import org.scalatest.{Filter => _, _}
import mylib.mutable.LQueue
import mylib.modifications._
import mylib.exceptions._

class testQueue extends FlatSpec with Matchers {
    "A Queue" should "have size = 0 after initialized" in {
        val myQueue = LQueue[Int]()
        myQueue.size should be (0)
    }
    it should "have size 3 after pushing 1, 2 and 3" in {
        val myQueue = LQueue[Int]()
        myQueue.push(1)
        myQueue.push(2)
        myQueue.push(3)
        myQueue.size should be (3)
    }
    it should "pop 1, 2, 3 after being initialized with LQueue[Int](1, 2, 3)" in {
        val myQueue = LQueue[Int](1, 2, 3)
        
        myQueue.pop() should be (Some(1))
        myQueue.pop() should be (Some(2))
        myQueue.pop() should be (Some(3))
    }
    it should "return Some(1), Some(2), Some(3) and have size == 0 after initializing as LQueue[Int](1, 2, 3) and popping 3 times (FIFO behavior)" in {
        val myQueue = LQueue[Int](1, 2, 3)

        myQueue.pop() should be (Some(1))
        myQueue.pop() should be (Some(2))
        myQueue.pop() should be (Some(3))
        myQueue.size should be (0)
    }
    it should "return None on the fourth pop() after initializing as LQueue[Int](1, 2, 3) and popping 4 times" in {
        val myQueue = LQueue[Int](1, 2, 3)

        myQueue.pop() should be (Some(1))
        myQueue.pop() should be (Some(2))
        myQueue.pop() should be (Some(3))
        myQueue.pop() should be (None)
    }
    it should "return Some(1) and Some(3) and have size == 3 after initializing as LQueue[Int](1, 2, 3) and calling head and tail" in {
        val myQueue = LQueue[Int](1, 2, 3)
        
        myQueue.head should be (Some(1))
        myQueue.tail should be (Some(3))
        myQueue.size should be (3)
    }
    it should "result in 6 after initializing as LQueue[Int](1, 2, 3) and calling foreach(sum += _)" in {
        val myQueue = LQueue[Int](1, 2, 3)
        var sum: Int = 0

        myQueue.foreach { sum += _ }
        sum should be (6)
    }
    it should "result in a LQueue that pops 2, 4, 6 after initializing as LQueue[Int](1, 2, 3) and calling map(_ * 2) in a LQueue" in {
        val myQueue = LQueue[Int](1, 2, 3)
        val mappedQueue = myQueue.map(_ * 2)

        mappedQueue.pop() should be (Some(2))
        mappedQueue.pop() should be (Some(4))
        mappedQueue.pop() should be (Some(6))
    }
    it should "result in a LQueue that pops \"|.2.|\", \"|.4.|\", \"|.6.|\" after initializing as LQueue[Int](1, 2, 3) and calling map[String](\"|.\" + 2*_ + \".|\") in a LQueue" in {
        val myQueue = LQueue[Int](1, 2, 3)
        val mappedQueue = myQueue.map[String]("|." + 2*_ + ".|")

        mappedQueue.pop() should be (Some("|.2.|"))
        mappedQueue.pop() should be (Some("|.4.|"))
        mappedQueue.pop() should be (Some("|.6.|"))
    }
    it should "result in a LQueue that pops 1, 3 after initializing as LQueue[Int](1, 2, 3, 4) and calling filter(_ % 2 == 1) in a LQueue" in {
        val myQueue = LQueue[Int](1, 2, 3)

        val filterQueue = myQueue.filter(_ % 2 == 1)

        filterQueue.pop() should be (Some(1))
        filterQueue.pop() should be (Some(3))
    }
    it should "throw EmptyEDIterator exception when creating iterator for empty LQueue" in {
        val myQueue    = LQueue[Int]()
        intercept[EmptyEDIterator]{
            val myIterator = myQueue.getIterator()
        }
    }
    it should "result in \"1-2-3-\" after initializing as LQueue[Int](1, 2, 3) and calling reduce(\"\"){ _ + _ + \"-\"}" in {
        val myQueue = LQueue[Int](1, 2, 3)

        myQueue.reduce[String](""){ _ + _ + "-" } should be ("1-2-3-")
    }
    it should "work with iterators" in {
        val myQueue    = LQueue[Int](1, 2, 3)
        var myIterator = myQueue.getIterator()

        myIterator.value should be (1)
        myIterator.hasNext should be (true)
        myIterator.hasPrev should be (false)

        myIterator.next match {
            case Some(iterator) => myIterator = iterator
            case None           => 
        }
        
        myIterator.value should be (2)
        myIterator.hasNext should be (true)
        myIterator.hasPrev should be (true)

        myIterator.next match {
            case Some(iterator) => myIterator = iterator
            case None           => 
        }
        
        myIterator.value should be (3)
        myIterator.hasNext should be (false)
        myIterator.hasPrev should be (true)
    }
}