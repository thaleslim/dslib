package mylib

/**
  * Especificação de Queue(fila).
  *
  * @author Rafael G. de Paulo
  */

trait Queue[T] {
  def push(value: T): Unit
  def pop(): Option[T]
  def head: Option[T]
  def tail: Option[T]
  def size: Int 
  
}