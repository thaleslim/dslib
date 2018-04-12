import org.scalatest._
import mylib._

class testQueue extends FlatSpec with Matchers {
    "A Queue" should "have size = 0 after initialized" in {
        val myQueue = new LQueue[Int]()
        myQueue.size should be (0)
    }
    it should "have size 3 after pushing 1, 2 and 3" in {
        val myQueue = new LQueue[Int]()
        myQueue.push(1)
        myQueue.push(2)
        myQueue.push(3)
        myQueue.size should be (3)
    }
    it should "return Some(1), Some(2), Some(3) and have size == 0 after pushing 1, 2 and 3 and popping 3 times (FIFO behavior)" in {
        val myQueue = new LQueue[Int]()
        myQueue.push(1)
        myQueue.push(2)
        myQueue.push(3)

        myQueue.pop() should be (Some(1))
        myQueue.pop() should be (Some(2))
        myQueue.pop() should be (Some(3))
        myQueue.size should be (0)
    }
    it should "return None on the fourth pop() after pushing 1, 2 and 3 and popping 4 times" in {
        val myQueue = new LQueue[Int]()
        myQueue.push(1)
        myQueue.push(2)
        myQueue.push(3)
        myQueue.pop() should be (Some(1))
        myQueue.pop() should be (Some(2))
        myQueue.pop() should be (Some(3))
        myQueue.pop() should be (None)
    }
    it should "return Some(1) and Some(3) and have size == 3 after pushing 1, 2 and 3 and calling head and tail" in {
        val myQueue = new LQueue[Int]()
        myQueue.push(1)
        myQueue.push(2)
        myQueue.push(3)

        myQueue.head should be (Some(1))
        myQueue.tail should be (Some(3))
        myQueue.size should be (3)
    }
    it should "result in 6 after pushing 1, 2 and 3 and calling foreach(sum += _)" in {
        val myQueue = new LQueue[Int]()
        var sum: Int = 0
        myQueue.push(1)
        myQueue.push(2)
        myQueue.push(3)

        myQueue.foreach {
            sum += (_: Int)
        }
        sum should be (6)
    }
}