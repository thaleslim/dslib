package br.unb.cic.ed
package modifications

import contracts.{EstLin, Node}

/** Trait que implementa foreach() automaticamente em
 * uma Estrutura de Dados Linear, usando seu Iterator.
 * 
 *  Executa a função inserida em cada elemento da estrutura linear.
 *
 * @author Rafael G. de Paulo
 */

trait Foreach[T] extends EstLin[T] {

  override def foreach(foo: (T) => Unit) {

    def iterate(iter: Iterator[T]) {
      foo(iter.value)
      if (iter.hasNext) {
        iter.next
        iterate(iter)
      }
    }

    if (!isEmpty)
      iterate(getIterator())
  }
}
