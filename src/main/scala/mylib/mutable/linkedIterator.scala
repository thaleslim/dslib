package mylib
package mutable

import mylib.contracts._
import mylib.modifications._
import mylib.exceptions._

/**
 *  Classe que define e implementa o comportamento
 * de um iterator para uma Estrutura de Dados Linear
 * Encadeada.
 *
 * @author Rafael G. de Paulo
 */
// T: O tipo de dado que a Estrutura Linear Guarda
// NodeImp: O tipo do Node que a Estrutura Linear usa
class LinkedIterator[T, NodeImp <: Node[T, NodeImp]](
  private val estLin: Linked[T, NodeImp],
  private var node:   NodeImp
) extends Iterator[T](estLin) {
  
  def value: T = node.value
  def getNode: NodeImp = node
  
  def hasNext: Boolean = getNode.next match {
    case Some(aNode) => true
    case None        => false
  }
  
  def next() {
    node.next match {
      case Some(nextNode) => node = nextNode;
      case None           => throw new IteratorOutOfBounds("tentando dar next() em um iterator no ultimo elemento")
    }
  }
}