package mylib
package modifications
import contracts.{Node, EstLin}

/**
 *  Trait que define a habilidade de usar map()
 * em uma Estrutura de Dados linear.
 * 
 * @author Rafael G. de Paulo
 */
trait mappable[T, NodeImpl <: Node[T, NodeImpl]] extends EstLin[T] {
  def map(foo: (T) => T)
}