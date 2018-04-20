package mylib
package modifications
import contracts.EstLin
import scala.reflect.ClassTag

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
trait filter[T] extends EstLin[T] {
  def filter(foo: (T) => Boolean)(implicit ev: ClassTag[T]): EstLin[T] = {            // realiza o comportamento descrito acima
    val estLin = instantiate[T]()                            // instancia uma nova EstImpl
    foreach (
      (value: T) => if (foo(value)) estLin.push(value)    // enche a nova estrutura linear com os valores adequados
    )
    estLin                                                // retorna ela
  }
}