package mylib
package modifications
import contracts.EstLin

/**
 *  Trait que define a habilidade de usar filter()
 * em uma Estrutura de Dados linear. 
 * 
 * @author Rafael G. de Paulo
 *
 * @param T: o tipo de dado guardado pela Estrutura de Dados Linear.
 * @param EstImpl: o tipo de Estrutura de dados linear que será retornado por filter().
 */

trait filter[T, EstImpl <: instanceable[T, EstImpl]] extends instanceable[T, EstImpl] {
  /**
  *  Usando filter() se pode filtrar uma Estrutura Linear para criar
  * e retornar uma nova Estrutura de Dados linear do
  * mesmo tipo, contendo somente os valores da primeira
  * que forem aceitos pela função passada como argumento.
  */
  def filter(foo: (T) => Boolean): EstImpl = {
    /**
    * Instância uma nova EstImpl.
    */
    val estLin = instantiate()
    foreach {
      /**
      * Enche a nova estrutura linear com os valores adequados
      */
      (value: T) => if (foo(value)) estLin.push(value)
    }   
    /**
    * Retorna a nova estrutura linear
    */
    estLin
  }
}