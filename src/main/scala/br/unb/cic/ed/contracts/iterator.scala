package br.unb.cic.ed

import contracts._

/** Classe abstrata que define o contrato
 * de um iterator de uma Estrutura de Dados linear.
 *
 *  Um Iterator pode ser usado para percorrer uma
 * Estrutura Linear, indo de um elemento para o próximo,
 * recebendo os dados deles.
 *
 * @author Rafael G. de Paulo
 */
abstract class Iterator[T](private val estLin: EstLin[T]) {

	def value: T
  def hasNext: Boolean
  def next: Unit
}
