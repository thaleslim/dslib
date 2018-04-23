package mylib
package mutable

import mylib.modifications._
import mylib.contracts._
import mylib.exceptions._
import scala.reflect.ClassTag

/**
 * Implementação duplamente encadeada de de Stack
 *
 * @author Vinicius
 */
 
case class LStackNode[T](
  val _value: T,
  var next:   Option[LStackNode[T]]
) extends Node[T, LStackNode[T]]
 
class LStack[T] extends Stack[T]
with Linked[T, LStackNode[T]]
with Filter[T]
with Foreach[T]
with Map[T]
with Reduce[T]
{
  var _head: Option[LStackNode[T]] = None
  var _tail: Option[LstackNode[T]] = None
  var _size: Int                   = 0


  def head: Option[T] = _head match {
    case None       => None
    case Some(node) => Some(node.value)
  }

  def tail: 

  def pop(): Option[T] =_head match {
    case None       => None
    case Some(node) => {
      _head = node.next
      Some(node.value)
    }
  }

  def push(val: T) {
    _head = Some(LStackNode[T](val, _head))
  }



}