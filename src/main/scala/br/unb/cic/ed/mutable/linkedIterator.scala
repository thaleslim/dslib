package br.unb.cic.ed
package mutable

import contracts._
import modifications._
import exceptions._

/** Classe que define e implementa o comportamento
 * de um iterator para uma Estrutura de Dados Linear
 * Encadeada.
 *
 * @author Rafael G. de Paulo
 */
class LinkedIterator[T, NodeImp <: Node[T, NodeImp]](
  protected val estLin: Linked[T, NodeImp],
  protected var node:   NodeImp
) extends Iterator[T](estLin) {
  /** @return Valor do node. */
  def value: T = {
    node.value
  }
  /**	@return True caso haja um elemento seguinte. */
  def hasNext: Boolean = node.next match {
    case Some(aNode) => true
    case None        => false
  }
  /**	@return O elemento seguinte. */
  def next() {
    node.next match {
      case Some(nextNode) => node = nextNode;
      case None           => throw new IteratorOutOfBounds("tentando dar next() em um iterator no ultimo elemento")
    }
  }
}
