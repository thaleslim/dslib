package mylib
package mutable

import mylib.contracts.Map
import mylib.immutable._
// import scala.reflect.ClassTag

/**
 *   Classe que define o funcionamento de uma
 *  HashTable.
 *
 *   Nessa implementação de Mapa, quando uma
 *  colisão ocorre, o novo valor é inserido em
 *  uma lista, que contém todos os valores das
 *  colisões anteriores. em vez de retornar B
 * 
 * @author Rafael G. de Paulo
 */
class HashTable[A, B](
  val maxSize: Int
)
// extends Map[A,B] {
{
  private var hashFoo: (A) => Int = (_: A).hashCode
  protected def hashFunc: (A) => Int = hashFoo(_) % maxSize
  val values = Array.fill[List[Pair[A, B]]](maxSize) {EmptyList}

  def insert(pairs: (A, B)*) {
    for (pair <- pairs) {
      values(hashFunc(pair._1)) = Pair[A, B](pair._1, pair._2) :: values(hashFunc(pair._1))
    }
  }

  def size: Int = {
    var counter = 0
    values foreach (counter += _.size)
    counter
  }

  def get(key: A): Option[B] = {
    if (values(hashFunc(key)) isEmpty) None
    else {
      values(hashFunc(key)) foreach {
        (value: Pair[A, B]) => if (value.key == key) return Some(value.value)
      }
      None
    }
  }
  
  def hasKey(key: A): Boolean = {
    if (values(hashFunc(key)) isEmpty) false
    else {
      values(hashFunc(key)) foreach {
        (value: Pair[A, B]) => if (value.key == key) return true
      }
      false
    }
  }

  def hasValue(value: B): Boolean = {
    values foreach {
      _ foreach {
        (pair: Pair[A, B]) => if (pair.value == value) return true
      }
    }
    false
  }

}

object HashTable {

  def apply[A, B](mSize: Int, hFoo: (A) => Int, pairs: (A, B)*) = {
    val hTable = new HashTable[A, B](mSize)
    hTable.hashFoo = hFoo
    hTable.insert(pairs: _*)
    hTable
  }
  // def apply[A, B](mSize: Int, hFoo: (A) => Int): HashTable[A, B] = {
  //   val hTable = new HashTable[A, B](mSize)
  //   hTable.hashFoo = hFoo
  //   hTable
  // }
  def apply[A, B](mSize: Int, pairs: (A, B)*): HashTable[A, B] = {
    val hTable = new HashTable[A, B](mSize)
    hTable.insert(pairs: _*)
    hTable
  }
  // def apply[A, B](mSize: Int): HashTable[A, B] = new HashTable(mSize)
  
}