package br.unb.cic.ed
package modifications

import contracts.EstLin
import scala.reflect.ClassTag

/** Trait que define a habilidade de usar filter()
 * em uma Estrutura de Dados linear. 
 * 
 * @author Rafael G. de Paulo
 * @return Nova Estrutura Linear com os valores adequados ao filtro utilizado
 */

trait Filter[T] extends EstLin[T] {
  def filter(foo: (T) => Boolean)(implicit ev: ClassTag[T]): EstLin[T] = {
    val estLin = instantiate[T]()
    foreach {
      (value: T) => if (foo(value)) estLin.push(value)
    estLin
		}
  }

  def filterNot(foo: (T) => Boolean)(implicit ev: ClassTag[T]): EstLin[T] = filter(!foo(_))
}
