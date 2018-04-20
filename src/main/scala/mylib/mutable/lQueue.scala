package mylib
package mutable

import modifications._
import contracts.{EstLin, Node, Queue}
import scala.reflect.ClassTag

/**
 * Implementação encadeada de de Queue(fila).
 *
 * @author Rafael G. de Paulo
 */ 
case class LQueueNode[T](
  val _value: T,                              		// o valor do Node
  var   prev: Option[LQueueNode[T]] = None,				// referência ao Node anterior (mais próximo da cauda)
  var   next: Option[LQueueNode[T]] = None				// referência ao próximo Node  (mais próximo da cabeça)
) extends Node[T, LQueueNode[T]]

class LQueue[T](values: T*) extends Queue[T, LQueue[T]]
with linked[T, LQueueNode[T]]
with foreach[T, LQueueNode[T]]
with reduce[T]
{
  def instantiate[A: ClassTag](inc: Int): LQueue[A] = new LQueue[A]
  private var _size: Int = 0											// o tamanho da fila
  private var _head: Option[LQueueNode[T]] = None	// referência a cabeça da fila
  private var _tail: Option[LQueueNode[T]] = None	// referência a cauda da fila

  /** única parte do Construtor que faz alguma coisa **********************************************************************************************/
  values foreach { this.push(_) } // colocando todos os argumentos para a LQueue
  /** única parte do Construtor que faz alguma coisa **********************************************************************************************/

  def size = _size																// retorna o tamanho da fila
  def push(value: T): Boolean = {                 // coloca um valor em um elemento, e adiciona ele à cauda da fila
    _tail match {
      case Some(node) => {                        // fila não-vazia
        node.prev = Some(LQueueNode[T](value, None, _tail))
        _tail = node.prev
      }
      case None => {                              // fila vazia
        _head = Some(LQueueNode[T](value))
        _tail = _head 
      }
    }
    _size += 1
    true;
  }
  def pop(): Option[T] = _head match {            // retira um elemento da fila, se possível, e retorna o seu valor interno
    case None       => None                       // fila vazia
    case Some(node) => {                          // fila não vazia
      _head = node.prev
      _head match { case Some(anode) => anode.next = None case None => }
      _size -= 1
      Some(node.value)
    }
  }
  def head: Option[T] = _head match {							// retorna o valor do elemento da cabeça, se existe
		case Some(v) => Some(v.value)
		case None => None
	}
  def tail: Option[T] = _tail match {							// retorna o valor do elemento da cauda, se existe
		case Some(v) => Some(v.value)
		case None => None
	}
  
  // def instantiate(): LQueue[T] = new LQueue[T]      // instancia um LQueue[T] e retorna ele
  def next(node: LQueueNode[T])        = node.prev  // retorna o próximo node
  def firstNode: Option[LQueueNode[T]] = _head      // retorna a cauda da fila (assim foreach itera nela da cabeça a cauda)
}