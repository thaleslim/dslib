package mylib

import modifications._
import contracts.{EstLin, Node, Queue}

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

class LQueue[T] extends Queue[T] with linked[T, LQueueNode[T]]{
  private var _size: Int = 0											// o tamanho da fila
  private var _head: Option[LQueueNode[T]] = None	// referência a cabeça da fila
  private var _tail: Option[LQueueNode[T]] = None	// referência a cauda da fila

  def size = _size																// retorna o tamanho da fila

  def push(value: T) {														// encapsula um valor em um Node, e coloca ele na cauda
    _tail match {
      // non-empty Queue
      case Some(node) => {
        node.prev = Some(LQueueNode[T](value, _tail))
        _tail = node.prev
      }
      // empty Queue
      case None => {
        _tail = Some(LQueueNode[T](value))
      }
    }
    _size += 1
    if (size == 1)
      _head = _tail
  }
	def pop(): Option[T] = _head match {						// retira um Node da cabeça, e retorna o valor dele
			case Some(node) => {
				node.prev match {
						case Some(aNode) => aNode.next = None
						case None => // nada a fazer
				}
				_head = node.prev
				_size -= 1
				Some(node.value)
			}
			case None => {
				None
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
  def foreach(foo: (T) => _) {                    // chama a função dada em todos os valores da fila, da cabeça a cauda

    // definindo subrotina que itera em todos os nós e chama foo
    def exec(node: LQueueNode[T], foo: (T) => _) {
      foo(node.value)
      node.prev match {
        case Some(nextNode) => exec(nextNode, foo)  // se o Node existe, siga com o próximo
        case None           =>                      // não precisa fazer nada
      }
    }

    // se a fila não está vazia, itera nela
    _head match {
      case Some(aNode) => exec(aNode, foo)
      case None        => // fila vazia, não faça nada
    }
    
  } 
}