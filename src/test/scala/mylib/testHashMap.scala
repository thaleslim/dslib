import org.scalatest._

import mylib.mutable._
import mylib.modifications._
import mylib.exceptions._

class testHashMap extends FlatSpec with Matchers {

    "A HashMap" should "have size = 0 after initialized" in {
        val myHashMap = new HashMap[String, Int](100)
        myHashMap.size should be (0)
    }
    it should "have size 4 after storing 4 pairs" in {
        val myHashMap = new HashMap[String, Int](100)

        myHashMap.insert("one"   -> 1)
        myHashMap.insert("two"   -> 2)
        myHashMap.insert("three" -> 3)
        myHashMap.insert("four"  -> 4)
        
        myHashMap.size should be (4)
    }
    it should "work with repeated arguments" in {
        val myHashMap = new HashMap[String, Int](100)

        myHashMap.insert("one" -> 1, "two" -> 2, "three" -> 3)
        
        myHashMap.size should be (3)
    }
    it should "work with custom hash functions" in {
        val myHashMap = new HashMap[String, Int](100, _.size)

        myHashMap.insert("um"   -> 1)
        myHashMap.insert("dois" -> 2)

        myHashMap.size should be (2)

        myHashMap.insert("tres" -> 3)       // dando override na entrada anterior ("dois" -> 2)
        
        myHashMap.size should be (2)
    }
    it should "be able to be initialized with pairs" in {
        val myHashMap = new HashMap[String, Int]("one" -> 1, "two" -> 2)
        
        myHashMap.size should be (2)
    }
    it should "work with hasKey() and hasObject" in {
        val myHashMap = new HashMap[String, Int]("one" -> 1, "two" -> 2, "three" -> 3)
        
        myHashMap.hasKey("one")   should be (true)
        myHashMap.hasKey("two")   should be (true)
        myHashMap.hasKey("three") should be (true)
        myHashMap.hasKey("four")  should be (false)

        myHashMap.hasObject(1) should be (true)
        myHashMap.hasObject(2) should be (true)
        myHashMap.hasObject(3) should be (true)
        myHashMap.hasObject(4) should be (false)
    }
}