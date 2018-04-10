package mylib
package modifications

/**
 *  Trait que define o comportamento de
 * encadeamento que uma Estrutura de Dados
 * linear pode ter.
 *
 * @author Rafael G. de Paulo
 */
trait linked[T, ImpNode <: Node[T, ImpNode]] extends EstLin[T]{
  def next(node: ImpNode): Option[ImpNode] = node.next 
}