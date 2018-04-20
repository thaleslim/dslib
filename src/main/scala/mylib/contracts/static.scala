package mylib
package modifications
import contracts.EstLin

/**
 *  Trait que define o comportamento estático
 * que uma Estrutura de Dados Linear pode ter.
 *
 * OBS: se uma EslLin[T] não mixar "static", ela DEVE mixar "linked", e vice-versa
 *  @see mylib/contracts/linked.scala
 *
 * @author Rafael G. de Paulo
 */
trait Static[T] extends EstLin[T] {
  def maxSize: Int  // retorna o tamanho máximo da Estrutura Linear
}