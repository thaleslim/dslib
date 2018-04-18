package mylib
package modifications
import contracts.{EstLin, Node}

/**
 *  Trait que define a habilidade de usar foreach()
 * em uma Estrutura de Dados Linear Encadeada.
 * 
 *  Executa a função inserida em cada elemento da estrutura linear
 *  T: o tipo de dado guardado pela Estrutura de Dados Linear
 *  EstImpl: o tipo de Estrutura de dados linear que será retornado por map()
 *
 * @author Rafael G. de Paulo
 */

trait foreach[T, ImpNode <: Node[T, ImpNode]] extends linked[T, ImpNode] {

  override def foreach(foo: (T) => _) {
    def iterate(node: Option[ImpNode]): Unit = node match {
      /**
      * Chegou ao fim da estrutura linear, não faça nada
      */
      case None        =>
      /**
      * Ainda não está no fim, execute foo e siga
      */
      case Some(aNode) => {
        /**
        * Executando foo
        */      
        foo(aNode.value)
        /**
        * Indo ao próximo node
        */          
        iterate(next(aNode))  // 
      } 
    }
    /** 
    * Chama a recursão pra usar o 'foreach'
    */
    iterate(firstNode)
  }
}
