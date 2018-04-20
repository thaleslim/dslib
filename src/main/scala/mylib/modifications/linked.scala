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
  /**
  * Pega o primeiro nó da estrutura
  */
  def firstNode: Option[ImpNode]
  /**
  * Segue a estrutura na ordem que ela preferir
  */
  def next(node: ImpNode): Option[ImpNode]
}