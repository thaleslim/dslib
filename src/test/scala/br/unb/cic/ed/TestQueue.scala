import org.scalatest._
import mylib._

class testQueue extends FlatSpec with Matchers {
    "A Queue" should "have size = 0 after initialized" in {
        val myQueue = new LQueue[Int]()
        myQueue.size should be (0)
    }
    it should "" in {
        myQueue.pop() match {
            case Some(value) => {

            }
            case None => {

            }
        }
    }
}