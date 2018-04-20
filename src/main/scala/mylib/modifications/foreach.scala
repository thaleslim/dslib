package mylib
package modifications
import contracts.{EstLin, Node}

/**
 *  Trait que implementa foreach() automaticamente em
 * uma Estrutura de Dados Linear Encadeada.
 * 
 * @author Rafael G. de Paulo
 */
// T: o tipo de dado guardado pela Estrutura de Dados Linear
// ImpNode: o tipo de do nó que a estrutura de dados linear usa
trait foreach[T, ImpNode <: Node[T, ImpNode]] extends linked[T, ImpNode] {
  // executa a função inserida em cada elemento da estrutura linear
  override def foreach(foo: (T) => Unit) {
    def iterate(node: Option[ImpNode]): Unit = node match {
      case None        =>     // chegou ao fim da estrutura linear, não faça nada
      case Some(aNode) => {   // ainda não está no fim, execute foo e siga
        foo(aNode.value)      // executando foo
        iterate(next(aNode))  // indo ao próximo node
      } 
    }
    // faz literalmente tudo
    iterate(firstNode)
  }
}