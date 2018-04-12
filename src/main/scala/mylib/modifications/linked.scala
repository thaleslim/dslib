package mylib
package modifications
import contracts.{Node, EstLin}

/**
 *  Trait que define o comportamento de
 * encadeamento que uma Estrutura de Dados
 * linear pode ter.
 *
 * @author Rafael G. de Paulo
 */
trait linked[T, ImpNode <: Node[T, ImpNode]] extends EstLin[T] {
  def firstNode: Option[ImpNode]            // pega o primeiro nó da estrutura
  def next(node: ImpNode): Option[ImpNode]  // segua a estrutura na ordem que ela preferir
}