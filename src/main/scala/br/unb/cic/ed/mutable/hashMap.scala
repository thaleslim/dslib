package br.unb.cic.ed
package mutable

import contracts.Map

import scala.reflect.ClassTag

/**  Classe que define o funcionamento de um
 *  HashMap.
 *
 *   Nessa implementação de Mapa, quando uma
 *  colisão ocorre, o valor antigo é removido,
 *  e substituido pelo novo valor inserido.
 * 
 * @author Rafael G. de Paulo
 */
protected class HashMap[A, B](
  val maxSize: Int
) extends Map[A,B] {

  /** Função de Hash original, que pode levar qualquer Int. */
  private var hashFoo: (A) => Int = (_: A).hashCode
  
  /** Função de Hash usada, que leva até qualquer inteiro entre 0 e maxSize -1 */
  protected def hashFunc: (A) => Int = hashFoo(_) % maxSize


  /** Array que guarda todos os pares chave/valor do mapa. */ 
  val values = Array.fill[Option[Pair[A, B]]](maxSize){ None }
  // protected val hashFunc: (A) => Int = hashFoo(_) % maxSize

  /** Insere pares Chave/Valor na HashTable. */
  def insert(pairs: (A, B)*) =
    for (pair <- pairs) {
      values(hashFunc(pair._1)) = Some(Pair[A, B](pair._1, pair._2))
    }

  /** Retorna o Valor correpondente, se inserida uma Chave que foi inserida como parte de um Par anteriormente, e se esse par não foi
	 * removido em uma colisão.
	 */
  def get(key: A): Option[B] = values(hashFunc(key)) match {
    case None       => None
    case Some(pair) => if (pair.key == key) Some(pair.value) else None
  }
  
  /** Retorna a quantidade de pares Chave/Valor que o HashMap tem. */
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

  /** Checa se uma Chave está dentro do HashMap. */
  def hasKey(key: A): Boolean = values(hashFunc(key)) match {
    case None       => false //
    case Some(pair) => if (pair.key == key) true else false
  }

  /** Checa se um Valor(Objeto) está dentro do HashMap. */
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


  /** Objeto auxiliar, usado para instanciar HashMap's. */ 
object HashMap {

  /** Se usando uma função de Hash customizada. */ 
  def apply[A, B](mSize: Int, hFoo: (A) => Int, pairs: (A, B)*): HashMap[A, B] = {
    val hMap = new HashMap[A, B](mSize)
    hMap.hashFoo = hFoo
    hMap.insert(pairs: _*)
    hMap
  }
  
  /**	Se usando a função de Hash padrão. */
  def apply[A, B](mSize: Int, pairs: (A, B)*): HashMap[A, B] = {
    val hMap = new HashMap[A, B](mSize)
    hMap.insert(pairs: _*)
    hMap
  }
}
