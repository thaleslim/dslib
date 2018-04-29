package mylib
package mutable

import mylib.modifications._
import mylib.contracts._
import mylib.exceptions._
import scala.reflect.ClassTag
 /**
  * Implementação encadeada de Stack
  *
  * @author Lucas Vinicius
  */
 
case class LStackNode[T](
  val _value: T,
  var next:   Option[LStackNode[T]] = None
) extends Node[T, LStackNode[T]]
 
abstract case class LStack[T](values: T*) extends Stack[T]
with Linked[T, LStackNode[T]]
with Filter[T]
with Foreach[T]
with Mappable[T]
with Reduce[T]
{
  private var _head: Option[LStackNode[T]] = None
  private var _tail: Option[LStackNode[T]] = None
  private var _size: Int                   = 0

	// def instantiate[A: ClassTag](inc: Int): LStack[A] = new LStack[A]	
	def size = _size
	def isEmpty = size == 0
  def head: Option[T] = _head match {
    case None       => None
    case Some(node) => Some(node.value)
  }
	def tail: Option[T] = _tail match {
		case None       => None
		case Some(node) => Some(node.value)
	}
	def pop(): Option[T] = _head match {
    case None       => None
    case Some(node) => {
      _head = node.next
   		_size -= 1
      Some(node.value)
		}
  }
	def push(value: T) {
		_head match{
    	case Some(node) => {
				_head = Some(LStackNode[T](value, Some(node)))
			}
			case None       => {
				_head = Some(LStackNode[T](value, None))
				_tail = _head
			}
			_size += 1
		}  
	}
	def firstNode: Option[LStackNode[T]] = _head
	def lastNode:  Option[LStackNode[T]] = _tail
	def next(node: LStackNode[T])        = node.next

}

