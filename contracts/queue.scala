package mylib
package contracts

/**
 * Classe abstrata que define o contrato de uma fila (Queue).
 *
 * @author Rafael G. de Paulo
 */
abstract class Queue[T] extends EstLin[T] {
  /**
  * Adiciona um valor a fila. retorna false se a fila não tem espaço pra ele (se não for encadeada)
  */
  def push(value: T): Boolean
  /**
  * Retira um elemento da fila, e retorna seu valor. caso esteja vazia, não altera ela e retorna None
  */
  def pop(): Option[T]
  /**
  * Retorna o valor do elemento "inicial", "primeiro" ou "cabeça" da fila
  */
  def head: Option[T]
  /**
  * Retorna o valor do elemento "final", "último" ou "cauda" da fila
  */
  def tail: Option[T]
  /**
  * Retorna a quantidade de elementos atualmente na fila
  */
  def size: Int
}