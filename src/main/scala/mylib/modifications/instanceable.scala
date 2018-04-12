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
trait instanceable[T, EstImpl <: instanceable[T, EstImpl]] extends EstLin[T] {
  def instantiate(): EstImpl
}