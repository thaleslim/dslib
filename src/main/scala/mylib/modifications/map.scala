package mylib
package modifications
import contracts.EstLin

/**
 *  Trait que define a habilidade de usar map()
 * em uma Estrutura de Dados linear.
 * 
 * @author Rafael G. de Paulo
 */
// T: o tipo de dado guardado pela Estrutura de Dados Linear
// EstImpl: o tipo de Estrutura de dados linear que será retornado por map()
trait map[T, EstImpl <: instanceable[T, EstImpl]] extends instanceable[T, EstImpl] {
  def map(foo: (T) => T): EstImpl = {
    val estLin = instantiate()
    foreach { (value: T) => estLin.push( foo(value) ) }
    estLin
  }
}