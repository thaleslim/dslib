package mylib
package modifications
import contracts.EstLin
import scala.reflect.ClassTag

/**
 *  Trait que define a habilidade de usar filter()
 * em uma Estrutura de Dados linear. 
 * 
 * @author Rafael G. de Paulo
 *
 * @param T: o tipo de dado guardado pela Estrutura de Dados Linear.
 * @param EstImpl: o tipo de Estrutura de dados linear que será retornado por filter().
 */
// T: o tipo de dado guardado pela Estrutura de Dados Linear
trait Filter[T] extends EstLin[T] {
  def filter(foo: (T) => Boolean)(implicit ev: ClassTag[T]): EstLin[T] = {            // realiza o comportamento descrito acima
    val estLin = instantiate[T]()                            // instancia uma nova EstImpl
    foreach (
      (value: T) => if (foo(value)) estLin.push(value)    // enche a nova estrutura linear com os valores adequados
    )
    estLin                                                // retorna ela
  }
}