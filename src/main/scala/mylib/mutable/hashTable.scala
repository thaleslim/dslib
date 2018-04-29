package mylib
package mutable

import mylib.contracts.Map
import scala.reflect.ClassTag

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
abstract class HashTable[A, B](
  val maxSize: Int = 1000,
  protected val hashFunc: (A) => Int = (_: A).hashCode())
extends Map[A,B]{

  
}