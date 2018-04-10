package mylib
package modifications
import contracts.{Node, EstLin}
import scala.reflect._

/**
 *  Trait que define a habilidade de usar map()
 * em uma Estrutura de Dados linear.
 * 
 * @author Rafael G. de Paulo
 */
trait mappable[T, EstImpl <: EstLin[T]] extends EstLin[T] {

  protected def instanciate(): EstImpl

  def map(foo: (T) => T): EstImpl = {
    val estLin = instanciate()
    foreach { (value: T) => estLin.push( foo(value) ) }
    estLin
  }
}