package mylib
package modifications
import contracts.{Node, EstLin}

/**
 *  Trait que define um método que instancia 
 * a si mesmo, criando uma Estrutura de Dados
 * vazia do mesmo tipo
 *
 * @author Rafael G. de Paulo
 */
trait Instanceable[T, EstImpl <: Instanceable[T, EstImpl]] extends EstLin[T] {
  /**
  * Instancia um EstImpl e o retorna
  */ 
  def instantiate(): EstImpl
}