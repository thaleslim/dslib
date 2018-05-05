package br.unb.cic.ed
package modifications

import contracts.EstLin
import scala.reflect.ClassTag

/** Trait que define a habilidade de usar map()
 * em uma Estrutura de Dados linear. map() aplica
 * uma função T => A(sendo T o tipo que a Estrutura
 * Linear guarda, e A um tipo qualquer), em todos os
 * valores da estrutura linear, e retorna uma nova
 * instancia da estrutura em A(EstLin[A]), com os
 * valores originais trocados pelos resultados obtidos
 * chamando a função inserida como argumento
 * aplicada aos valores guardados na Estrutura Linear.
 *
 * @author Rafael G. de Paulo
 * @return Nova Estrutura Linear com os valores alterados pela função foo()
 */

trait Mappable[T] extends EstLin[T] {
  def map[B: ClassTag](foo: (T) => B): EstLin[B] = {
    val estLin = instantiate[B]()
    foreach { (value: T) => estLin.push( foo(value) ) }
    estLin
  }
}
