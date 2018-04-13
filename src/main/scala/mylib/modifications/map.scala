package mylib
package modifications
import contracts.EstLin

/**
 *  Trait que define a habilidade de usar map()
 * em uma Estrutura de Dados linear. map() aplica
 * uma função T => T em todos os valores da estrutura
 * linear, e retorna uma nova instancia da estrutura,
 * com os valores originais trocados pelo resultado
 * de obtido chamando a função inserida como argumento
 * aplicada aos valores guardados na Estrutura Linear
 *
 * PS: por enquanto só é possível mapear T => T porque, apesar de ter passado 15+
 * horas pesquisando como fazer funcionar, eu ainda não descobri uma forma de fazer
 * mapeamento(ou redução) em T => A, com A sendo um tipo parametrizado em map()
 * 
 * @author Rafael G. de Paulo
 */
// T: o tipo de dado guardado pela Estrutura de Dados Linear
// EstImpl: o tipo de Estrutura de dados linear que será retornado por map()
trait map[T, EstImpl <: instanceable[T, EstImpl]] extends instanceable[T, EstImpl] {
  def map(foo: (T) => T): EstImpl = {                     // retorna uma EstImpl, com o resultado de foo() aplicada em cada valor
    val estLin = instantiate()                            // instancia uma nova EstImpl
    foreach { (value: T) => estLin.push( foo(value) ) }   // enche a nova estrutura linear com os valores adequados
    estLin                                                // retorna ela
  }
}