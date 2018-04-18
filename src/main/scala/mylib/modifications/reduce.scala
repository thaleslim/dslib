package mylib
package modifications
import contracts.EstLin

/**
 *  Trait que define a habilidade de usar reduce()
 * em uma Estrutura de Dados linear. reduce() aplica
 * uma função A, T => A em todos os valores da estrutura
 * linear, usando o retorno da última chamada (ou o valor
 * inicial, se for a primeira chamada) e o valor interno
 * atual da Estrutura Linear como parâmetros, iterando em
 * toda a estrutura linear, até chegar a um valor único,
 * que é retornado.
 * 
 * @author Rafael G. de Paulo
 *
 * @param T: o tipo de dado guardado pela Estrutura de Dados Linear
 * @param EstImpl: o tipo de Estrutura de dados linear que será retornado por reduce()
 */
 
trait reduce[T] extends EstLin[T] {
  def reduce[A](foo: (A, T) => A, initVal: A): A = {
    var reduced: A = initVal
    foreach { (curVal: T) => reduced = foo(reduced, curVal) }
    reduced
  }
}