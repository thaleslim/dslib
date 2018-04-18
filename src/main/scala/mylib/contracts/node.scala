package mylib
package contracts

/**
 *  Classe abstrata que define o contrato de um
 * Node que contém um valor de T e uma referencia ao próximo Node
 *
 * @author Rafael G. de Paulo
 */
abstract class Node[T, NodeType <: Node[T, NodeType]] {
  /**
  * Referência ao próximo Node
  */
  var next: Option[NodeType]	// 
  /**
  * O valor que o Node contém
  */
  protected val _value: T
  /**
  * Retorna o valor contido no Node
  */
  def value: T = _value
}
