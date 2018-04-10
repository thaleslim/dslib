package mylib

/**
  * Implementação encadeada de de Queue(fila).
  *
  * @author Rafael G. de Paulo
  */

case class LQueueNode[T](
  val _value: T,                              		// o valor do Node
  var   prev: Option[LQueueNode[T]] = None,				// referência ao Node anterior
  var   next: Option[LQueueNode[T]] = None				// referência ao próximo Node
) extends Node[T, LQueueNode[T]]

class LQueue[T] extends Queue[T] {
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
}