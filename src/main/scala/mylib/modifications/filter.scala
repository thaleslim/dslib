package mylib
package modifications
import contracts.EstLin

/**
 *  Trait que define a habilidade de usar filter()
 * em uma Estrutura de Dados linear. Usando filter()
 * se pode filtrar uma Estrutura Linear para criar
 * e retornar uma nova Estrutura de Dados linear do
 * mesmo tipo, contendo somente os valores da primeira
 * que forem aceitos pela função passada como argumento
 * 
 * @author Rafael G. de Paulo
 */
// T: o tipo de dado guardado pela Estrutura de Dados Linear
// EstImpl: o tipo de Estrutura de dados linear que será retornado por filter()
trait filter[T, EstImpl <: instanceable[T, EstImpl]] extends instanceable[T, EstImpl] {
  def filter(foo: (T) => Boolean): EstImpl = {            // realiza o comportamento descrito acima
    val estLin = instantiate()                            // instancia uma nova EstImpl
    foreach {
      (value: T) => if (foo(value)) estLin.push(value)    // enche a nova estrutura linear com os valores adequados
    }   
    estLin                                                // retorna ela
  }
}