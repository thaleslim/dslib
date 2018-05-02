import org.scalatest._

import mylib.mutable._
import mylib.modifications._
import mylib.exceptions._

class testHashTable extends FlatSpec with Matchers {

    "A HashTable" should "have size = 0 after initialized" in {
        val myHashTable = HashTable[String, Int](100)
        myHashTable.size should be (0)
    }
    it should "increase size after inserting" in {
        val myHashTable = HashTable[String, Int](100)
        myHashTable.size should be (0)

        myHashTable.insert("one" -> 1)
        myHashTable.size should be (1)
        
        myHashTable.insert("two" -> 2, "three" -> 3, "four" -> 4)
        myHashTable.size should be (4)
    } 
    it should "give the correct value when get() is used" in {
        val myHashTable = HashTable[String, Int](100)
        myHashTable.insert("one" -> 1, "two" -> 2, "three" -> 3, "four" -> 4)

        myHashTable.get("one")   should be (Some(1))
        myHashTable.get("two")   should be (Some(2))
        myHashTable.get("three") should be (Some(3))
        myHashTable.get("four")  should be (Some(4))
    }
    it should "give None when get() is used with a key not previously inserted in the HashTable" in {
        val myHashTable = HashTable[String, Int](100)
        myHashTable.insert("one" -> 1, "two" -> 2, "three" -> 3, "four" -> 4)

        myHashTable.get("five") should be (None)
    }
    it should "work when initialized with a custom hash function" in {
        val myHashTable = HashTable[String, Int](100, (_: String).length)
        myHashTable.size should be (0)

        myHashTable.insert("one" -> 1)
        myHashTable.size should be (1)
        
        myHashTable.insert("two" -> 2, "three" -> 3, "four" -> 4)
        myHashTable.size should be (4)
        
        myHashTable.get("one")   should be (Some(1))
        myHashTable.get("two")   should be (Some(2))
        myHashTable.get("three") should be (Some(3))
        myHashTable.get("four")  should be (Some(4))
    }
    it should "work when initialized with initial key/value pairs" in {
        val myHashTable = HashTable[String, Int](100, "one" -> 1, "two" -> 2)
        myHashTable.size should be (2)

        myHashTable.insert("three" -> 3, "four" -> 4)
        myHashTable.size should be (4)
        
        myHashTable.get("one")   should be (Some(1))
        myHashTable.get("two")   should be (Some(2))
        myHashTable.get("three") should be (Some(3))
        myHashTable.get("four")  should be (Some(4))
    }
    it should "work when initialized with a custom hash function and initial key/value pairs" in {
        val myHashTable = HashTable[String, Int](100, (_: String).length, "one" -> 1, "two" -> 2)
        myHashTable.size should be (2)

        myHashTable.insert("three" -> 3, "four" -> 4)
        myHashTable.size should be (4)
        
        myHashTable.get("one")   should be (Some(1))
        myHashTable.get("two")   should be (Some(2))
        myHashTable.get("three") should be (Some(3))
        myHashTable.get("four")  should be (Some(4))
    }
    it should "deal with colision" in {
        val myHashTable = HashTable[String, Int](100, (_: String) => 5)
        myHashTable.size should be (0)

        myHashTable.insert("one" -> 1, "two" -> 2, "three" -> 3)
        myHashTable.size should be (3)
        
        myHashTable.get("one")   should be (Some(1))
        myHashTable.get("two")   should be (Some(2))
        myHashTable.get("three") should be (Some(3))
    }
    it should "work with the hasKey method" in {
        val myHashTable = HashTable[String, Int](100)
        myHashTable.insert("one" -> 1, "two" -> 2, "three" -> 3, "four" -> 4)
        
        myHashTable.hasKey("zero")  should be (false)
        myHashTable.hasKey("one")   should be (true)
        myHashTable.hasKey("two")   should be (true)
        myHashTable.hasKey("three") should be (true)
        myHashTable.hasKey("four")  should be (true)
        myHashTable.hasKey("five")  should be (false)
    }
    it should "work with the hasValue method" in {
        val myHashTable = HashTable[String, Int](100)
        myHashTable.insert("one" -> 1, "two" -> 2, "three" -> 3, "four" -> 4)
        
        myHashTable.hasValue(0)  should be (false)
        myHashTable.hasValue(1)  should be (true)
        myHashTable.hasValue(2)  should be (true)
        myHashTable.hasValue(3)  should be (true)
        myHashTable.hasValue(4)  should be (true)
        myHashTable.hasValue(5)  should be (false)
    }
}