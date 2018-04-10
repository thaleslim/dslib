package mylib

/**
  * Implementação encadeada de de Queue(fila).
  *
  * @author Rafael G. de Paulo
  */

case class LQueueNode[T](
    override val _value: T,
    var            prev: Option[LQueueNode[T]] = None,
    override var   next: Option[LQueueNode[T]] = None
) extends Node[T, LQueueNode[T]]

class LQueue[T] extends Queue[T] {
    private var _size: Int = 0
    private var _head: Option[LQueueNode[T]] = None
    private var _tail: Option[LQueueNode[T]] = None

    def size = _size

    def push(value: T) {
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
        println("valor in: "+value)
    }

    def pop(): Option[T] = _head match {
        case Some(node) => {
            node.prev match {
                case Some(aNode) => aNode.next = None
                case None => // nada a fazer
            }
            _head = node.prev
            _size -= 1
            println("valor out: "+node.value)
            Some(node.value)
        }
        case None => {
            None
        }
    }

    def head: Option[T] = _head match { case Some(v) => Some(v.value) case None => None}
    def tail: Option[T] = _tail match { case Some(v) => Some(v.value) case None => None}
}