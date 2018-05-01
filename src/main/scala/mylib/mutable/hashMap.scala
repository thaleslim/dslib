package mylib
package mutable

import mylib.contracts.Map
import scala.reflect.ClassTag

/**
 *   Classe que define o funcionamento de um
 *  HashMap.
 *
 *   Nessa implementação de Mapa, quando uma
 *  colisão ocorre, o valor antigo é removido,
 *  e substituido pelo novo valor inserido.
 * 
 * @author Rafael G. de Paulo
 */
class HashMap[A, B](
  val maxSize: Int = 1000,
      hashFoo: (A) => Int = (_: A).hashCode()
) extends Map[A,B] {

  val values = Array.fill[Option[Pair[A, B]]](maxSize){None}
  protected val hashFunc: (A) => Int = hashFoo(_) % maxSize

  def this(pairs: (A, B)*) {
    this(1000)
    pairs foreach { insert(_) }
  }

  def insert(pairs: (A, B)*) =
    for (pair <- pairs) {
      values(hashFunc(pair._1)) = Some(Pair[A, B](pair._1, pair._2))
    }

  def get(key: A): Option[B] = values(hashFunc(key)) match {
    case None       => None
    case Some(pair) => Some(pair.value)
  }
  
  def size(): Int = {
    var _size = 0
    values foreach {
      _ match {
        case Some(a) => _size += 1
        case None    =>
      }
    }
    _size
  }

  def hasKey(key: A): Boolean = values(hashFunc(key)) match {
    case None       => false //
    case Some(pair) => if (pair.key == key) true else false
  }

  def hasObject(obj: B): Boolean = {
    values foreach {
      _ match {
        case Some(pair) => if (pair.value == obj) return true
        case None      => //       
      }
    }
    false
  }
}