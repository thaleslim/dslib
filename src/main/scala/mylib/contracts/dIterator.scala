package mylib
import mylib.contracts._

/**
 *  Classe abstrata que define o contrato de um iterator
 * bidimensional de uma Estrutura de Dados linear.
 *
 *  Um Iterator pode ser usado para percorrer uma
 * Estrutura Linear, indo de um elemento para o próximo
 * ou para o anterior, e recebendo os dados deles.
 *
 * @author Rafael G. de Paulo
 */
abstract class DIterator[T](private val estLin: EstLin[T]) extends Iterator[T](estLin) {
  def hasPrev: Boolean                    // checa se tem um elemento anterior
  def prev: Option[Iterator[T]]  // pega o elemento anterior
}