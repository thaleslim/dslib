package mylib
package modifications
import contracts.{Node, EstLin}

/**
 *  Trait que define o comportamento de
 * encadeamento que uma Estrutura de Dados
 * linear pode ter.
 *
 * OBS: se uma EslLin[T] não mixar "linked", ela DEVE mixar "static", e vice-versa
 *  @see mylib/contracts/static.scala
 * @author Rafael G. de Paulo
 */
trait linked[T, ImpNode <: Node[T, ImpNode]] extends EstLin[T] {
  /**
   * Pega o primeiro nó da estrutura. a própria implementação da Estrutura
   *  define qual é o primeiro nó
   *
   * @return Some(ImpNode) se a EstLin[T] não estiver vazia
   * @return None          se a EstLin[T] estiver vazia
   */
  def firstNode: Option[ImpNode]

  /**
   * Pega o próximo elemento da estrutura
   *
   * @param node o nó do qual o próximo deve ser retornado
   *
   * @return Some(ImpNode) se o node não é o último da Estrutura Linear
   * @return None          se o node é o último da Estrutura Linear
   *   
   */
  def next(node: ImpNode): Option[ImpNode]  // segua a estrutura na ordem que ela preferir
}