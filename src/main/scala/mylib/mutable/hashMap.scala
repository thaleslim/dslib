package mylib
package mutable

import mylib.contracts.Map
import scala.reflect.ClassTag

/**
 *   Classe que define o funcionamento de um
 *  HashMap.
 *
 *   Um mapa é parametrizado em A e B. A é
 * o tipo da Chave, e B o do Valor. Pares
 * A -> B podem ser inseridos no mapa, e 
 * depois recuperados, inserindo a chave
 * A no mapa, que retorna o valor B.
 * 
 *   Dependendo da implementação do Mapa,
 * podem ocorrer colisões, em que uma chave
 * é ligada a mais de um valor. A implementação
 * então decide como lidar com as colisões.
 * 
 * @author Rafael G. de Paulo
 */
class HashMap[A, B](
  val maxSize: Int = 1000,
  protected val hashFunc: (A) => Int = (_: A).hashCode()) extends Map[A,B]{
  var keys: List[A] = Nil
  val values = Array.fill[Option[B]](maxSize){None}

  def this(pairs: (A, B)*) {
    this(1000)
    pairs foreach { insert(_) }
  }

  def insert(pairs: (A, B)*) =
    for (pair <- pairs) {
      values(hashFunc(pair._1) % maxSize) = Some(pair._2)
      keys = pair._1 :: keys
    }
  def get(key: A): Option[B] = values(hashFunc(key))
  
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

  def hasKey(key: A) = keys contains(key)
  def hasObject(obj: B): Boolean = {
    values foreach {
      _ match {
        case Some(a) => if (a == obj) return true
        case None      => //       
      }
    }
    false
  }
}