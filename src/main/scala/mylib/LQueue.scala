package mylib

/**
  * Implementação encadeada de de Queue(fila).
  *
  * @author Rafael G. de Paulo
  */

case class NodeLQueue[T](val value: T, var prev: Option[NodeLQueue[T]] = None, var next: Option[NodeLQueue[T]] = None)

class LQueue[T] extends Queue[T] {
    private var _size: Int = 0
    var _head: Option[NodeLQueue[T]] = None
    var _tail: Option[NodeLQueue[T]] = None

    def size = _size

    def push(value: T) {
        _tail match {
            // non-empty Queue
            case Some(node) => {
                node.prev = Some(NodeLQueue[T](value, _tail))
                _tail = node.prev
            }
            // empty Queue
            case None => {
                _tail = Some(NodeLQueue[T](value))
            }
        }
        _size += 1
        if (size == 1)
            _head = _tail
        println("valor in: "+value)
    }

    def pop(): Option[T] = _head match {
        case Some(node) => {
            node.prev match { case Some(aNode) => aNode.next = None case None => {}}
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