package br.unb.cic.ed
package mutable

import contracts.Map

// import scala.reflect.ClassTag

/**  Classe que define o funcionamento de uma
 *  HashTable.
 *
 *   Nessa implementação de Mapa, quando uma
 *  colisão ocorre, o novo valor é inserido em
 *  uma lista, que contém todos os valores das
 *  colisões anteriores. em vez de retornar B
 * 
 * @author Rafael G. de Paulo
 */
protected class HashTable[A, B](
  val maxSize: Int
)
extends Map[A,B] {

	/**	A função de Hash original, que pode levar a qualquer Int. */
  private var hashFoo: (A) => Int = (_: A).hashCode
	/**	A função de Hash usada, que leva até qualquer inteiro entre 0 e maxSize -1. */
  protected def hashFunc: (A) => Int = hashFoo(_) % maxSize
  
  /**	Array que guarda todos os pares Chave/Valor da HashTable, cada índice da HashTable contendo uma Lista de Pares. */
  val values = Array.fill[immutable.List[Pair[A, B]]](maxSize) {immutable.EmptyList}

  /**	A função que insere pares Chave/Valor na HashTable. */
  def insert(pairs: (A, B)*) {
    for (pair <- pairs) {
      values(hashFunc(pair._1)) = Pair[A, B](pair._1, pair._2) :: values(hashFunc(pair._1))
    }
  }

  /**	Retorna a quantidade de pares Chave/Valor que a HashTable tem. */
  def size: Int = {
    var counter = 0
    values foreach (counter += _.size)
    counter
  }

  /**	Retorna o Valor correspondente, se inserida uma chave que foi inserida como parte de um Par anteriormente,
	 * e se esse par não foi removido em uma colisão.
	 */
  def get(key: A): Option[B] = {
    if (values(hashFunc(key)) isEmpty) None
    else {
      values(hashFunc(key)) foreach {
        (value: Pair[A, B]) => if (value.key == key) return Some(value.value)
      }
      None
    }
  }
  
  /**	Checa se uma Chave está dentro do HashTable. */
  def hasKey(key: A): Boolean = {
    if (values(hashFunc(key)) isEmpty) false
    else {
      values(hashFunc(key)) foreach {
        (value: Pair[A, B]) => if (value.key == key) return true
      }
      false
    }
  }

  /**	Checa se um Valor(Objeto) está dentro da HashTable. */
  def hasObject(value: B): Boolean = {
    values foreach {
      _ foreach {
        (pair: Pair[A, B]) => if (pair.value == value) return true
      }
    }
    false
  }

}

/**	Objeto auxiliar, usado para instanciar HashTable's. */
object HashTable {

  /**	Se usando uma função de Hash customizada. */
  def apply[A, B](mSize: Int, hFoo: (A) => Int, pairs: (A, B)*): HashTable[A, B]  = {
    val hTable = new HashTable[A, B](mSize)
    hTable.hashFoo = hFoo
    hTable.insert(pairs: _*)
    hTable
  }
  
  /**	Se usando a função de Hash padrão. */
  def apply[A, B](mSize: Int, pairs: (A, B)*): HashTable[A, B] = {
    val hTable = new HashTable[A, B](mSize)
    hTable.insert(pairs: _*)
    hTable
  }
}
