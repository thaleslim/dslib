package mylib
package contracts

/**
 * Classe abstrata que define o contrato de uma fila (Queue).
 *
 * @author Rafael G. de Paulo
 */
abstract class Queue[T, QueueImpl <: Queue[T, QueueImpl]] extends EstLin[T] { this: QueueImpl =>
  // override def instantiate[A, QImpl <: EstLin[A, QImpl]](inc: Int): QImpl
  def push(value: T): Boolean // adiciona um valor a fila. retorna false se a fila não tem espaço pra ele (se não for encadeada)
  def pop(): Option[T]        // retira um elemento da fila, e retorna seu valor. caso esteja vazia, não altera ela e retorna None
  def head: Option[T]         // retorna o valor do elemento "inicial", "primeiro" ou "cabeça" da fila
  def tail: Option[T]         // retorna o valor do elemento "final", "último" ou "cauda" da fila
  def size: Int               // retorna a quantidade de elementos atualmente na fila
}