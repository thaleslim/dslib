package mylib
package mutable

import mylib.contracts._
import mylib.modifications._
import mylib.exceptions._

/**
 *  Classe que define e implementa o comportamento
 * de um iterator para uma Estrutura de Dados Linear
 * Duplamente Encadeada.
 *
 * @author Rafael G. de Paulo
 */
// T: O tipo de dado que a Estrutura Linear Guarda
// NodeImp: O tipo do Node que a Estrutura Linear usa
class DLinkedIterator[T, NodeImp <: DNode[T, NodeImp]](
  private val estLin: DLinked[T, NodeImp],
  private var node:   NodeImp
) extends LinkedIterator[T, NodeImp](estLin, node) {

  def hasPrev: Boolean = getNode.prev match {
    case Some(aNode) => true
    case None        => false
  }

  def prev() {
    node.prev match {
      case Some(prevNode) => node = prevNode
      case None           => throw new IteratorOutOfBounds("tentando dar prev() em um iterator no primeiro elemento")
    }
  }
}