package mylib

/**
  * Especificação de Queue(fila).
  *
  * @author Rafael G. de Paulo
  */

trait Queue[T] extends EstLinEnc[T] {
  def push(value: T): Unit  // adiciona um valor a fila
  def pop(): Option[T]      // retira um elemento da fila, e retorna seu valor. caso esteja vazia, não altera ela e retorna None
  def head: Option[T]       // retorna o valor do elemento "inicial", "primeiro" ou "cabeça" da fila
  def tail: Option[T]       // retorna o valor do elemento "final", "último" ou "cauda" da fila
  def size: Int             // retorna a quantidade de elementos atualmente na fila
}