package mylib
package contracts

/**
 *  Classe abstrata que define o contrato
 * de uma Estrutura de Dados linear.
 *
 * @author Rafael G. de Paulo
 */
abstract class EstLin[T] {
  /**
  * Retorna o valor do elemento "inicial", "primeiro" ou "cabeça" da estrutura linear, se existir
  */
  def head: Option[T]             
  /**
  * Retorna o valor do elemento "final", "último" ou "cauda" da estrutura linear. se existir
  */
  def tail: Option[T] 
  /**
  * Retorna a quantidade de elementos atualmente na estrutura linear
  */
  def size: Int  
  /**
  * Insere um valor na estrutura linear. retorna true se conseguiu inserir, false se não deu, por não ter mais espaço (só acontece em EstLin não-encadeadas)
  */
  def push(value: T): Boolean
  /**
  * Remove um valor da estrutura linear
  */
  def pop(): Option[T]
  /**
  * Executa a função inserida em cada elemento da estrutura linear 
  */
  def foreach(foo: (T) => _): Unit
}