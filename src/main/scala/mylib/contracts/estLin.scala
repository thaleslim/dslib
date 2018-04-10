package mylib
package contracts

/**
 *  Classe abstrata que define o contrato
 * de uma Estrutura de Dados linear.
 *
 * @author Rafael G. de Paulo
 */
abstract class EstLin[T] {
  def head: Option[T]             // retorna o valor do elemento "inicial", "primeiro" ou "cabeça" da estrutura linear, se existir
  def tail: Option[T]             // retorna o valor do elemento "final", "último" ou "cauda" da estrutura linear. se existir
  def foreach(foo: T => _): Unit  // executa a função inserida em cada elemento da estrutura linear
  def size: Int                   // retorna a quantidade de elementos atualmente na estrutura linear
}