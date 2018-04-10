package mylib

/*
 *  Trait que define o contrato de uma
 * Estrutura de Dados linear encadeada.
 * (Rafael G. de Paulo)
 *
*/
trait EstLinEnc[T] {
  def head: Option[T]       // retorna o valor do elemento "inicial", "primeiro" ou "cabeça" da estrutura linear
  def tail: Option[T]       // retorna o valor do elemento "final", "último" ou "cauda" da estrutura linear
  def size: Int             // retorna a quantidade de elementos atualmente na estrutura linear
}