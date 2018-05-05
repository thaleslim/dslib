package br.unb.cic.ed
package mutable

import scala.reflect.ClassTag

import modifications._
import contracts._
import exceptions._

/** Implementação duplamente encadeada de de Queue(fila).
 *
 * @author Rafael G. de Paulo
 */ 
case class LQueue[T](values: T*) extends Queue[T]
with DLinked[T, LQueueNode[T]]
with Foreach[T]
with Reduce[T]
with Filter[T]
with Mappable[T]
{
	/**	O tamanho da Fila. */
  private var _size: Int = 0
	/**	Referencia à "cabeça" da Fila. */
  private var _head: Option[LQueueNode[T]] = None
	/**	Referência à "cauda" da Fila. */
  private var _tail: Option[LQueueNode[T]] = None


	/**	Coloca todos os argumentos para a LQueue. */  
	values foreach { this.push(_) }

  def instantiate[A: ClassTag](inc: Int = 0): LQueue[A] = new LQueue[A]
	/**	@return Int tamanho da fila. */
  def size = _size
	/**	@return True se a Fila estiver vazia. */
  def isEmpty = size == 0
	/**	Põe um valor em um elemento, e adiciona ele à cauda da Fila. */
  def push(value: T) {
    _tail match {
      case Some(node) => {
        node.next = Some(LQueueNode[T](value, _tail, None))
        _tail = node.next
      }
      case None => {
        _head = Some(LQueueNode[T](value))
        _tail = _head 
      }
    }
    _size += 1
  }

	/**	Retira um elemento da Fila, caso seja possível
	 * @return Valor do elemento "cabeça" da Fila.
	 */
  def pop(): Option[T] = _head match {
    case None       => None
    case Some(node) => {
      _head = node.next
      _head match { case Some(anode) => anode.prev = None case None => }
      _size -= 1
      Some(node.value)
    }
  }

	/**	@return Valor do elemento da "cabeça", se houver. */
  def head: Option[T] = _head match {
		case Some(v) => Some(v.value)
		case None => None
	}
	/**	@return Valor do elemento da "cauda", se houver. */
  def tail: Option[T] = _tail match {
		case Some(v) => Some(v.value)
		case None => None
	}
  /**	@return Proximo Node. */
  def next(node: LQueueNode[T])        = node.next
	/**	@return Node Anterior. */
  def prev(node: LQueueNode[T])        = node.prev
	/**	@return "cabeça" da Fila (assim foreach itera de cabeça a cauda). */
  def firstNode: Option[LQueueNode[T]] = _head
	/**	@return "cauda" da Fila. */
  def lastNode:  Option[LQueueNode[T]] = _tail

  override def getIterator(ind: Int): LQueueIterator[T] = {
    def getCorrectNode(i: Int, node: LQueueNode[T], fwd: Boolean): LQueueNode[T] = {
      if (i < 0)
        getCorrectNode(size + (i % size), node, fwd)
      else
        (i % size compare 0) match {
          case -1 => throw new PoorlyImplemented("LQueue.getIterator() poorly implemented.")
          case  0 => node
          case  1 => node.next match {
            case Some(nextNode) => getCorrectNode(i - 1, nextNode, fwd)
            case None => throw new PoorlyImplemented("LQueue.getIterator() poorly implemented.")
          }
        }}

    _head match {
      case None       => throw new EmptyEDIterator("tentando criar Iterator para um LQueue vazio")
      case Some(node) => LQueueIterator[T](this, getCorrectNode(ind, node, true))
    }
  }

  override def copy(implicit ev: ClassTag[T]): EstLin[T] = {
    val queue = instantiate[T](0)
    foreach { queue.push(_) }
    queue
  }
}
/**	Classe expressando um Node de Fila.
 * @param Value, valor [T] guardado pelo Node.
 * @param Prev, ponteiro para Node anterior.
 * @param Next, ponteiro para próximo Node.
 */
case class LQueueNode[T](
  val _value: T,
  var   prev: Option[LQueueNode[T]] = None,
  var   next: Option[LQueueNode[T]] = None
) extends DNode[T, LQueueNode[T]]

case class LQueueIterator[T](
  queue: LQueue[T],
  startNode:  LQueueNode[T]
) extends DLinkedIterator[T, LQueueNode[T]](queue, startNode)
