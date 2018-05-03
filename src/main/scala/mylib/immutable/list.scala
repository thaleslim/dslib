package mylib
package immutable

import mylib.exceptions._

/**
 *  Classe que define e implementa uma Lista Imutável,
 * com métodos implementados usando recursão
 *
 *  Por causa de um problema com covariancia e
 * invariancia de tipo, List[T] não pode extender
 * EstLin[T], infelizmente.
 *
 * @author Rafael G. de Paulo
 */
sealed abstract class List[+T] {
  // pega o primeiro elemento da lista
  def head: T

  // pega o resto da lista, excluindo o primeiro elemento
  def tail: List[T]   

  // checa se a lista está vazia
  def isEmpty: Boolean

  // pega o n-ésizmo próximo elemento da lista
  def apply(ind: Int): T =
    if (ind < 0)      throw new NegativeIndex("indice negativo em List.apply()")
    else if (ind > 0) tail.apply(ind - 1)
    else              head

  // pega o tamanho da lista
  def size(): Int = 1 + tail.size 

  // retorna uma nova lista, que é pref concatenado à essa lista
  def ::[T1 >: T](pref: T1): List[T1] = new ::(pref, this)  

  // retorna uma lista identica a essa, só que invertida
  def invert(): List[T] = {                                   
    def getInvert(in: List[T], out: List[T] = EmptyList): List[T] = {
      in match {
        case EmptyList => out
        case ::(h, t)  => getInvert(t, h :: out)
      }
    }
    getInvert(this)
  }

  // retorna uma nova lista, que é a concatenação dessa lista com uma anterior
  def :::[T1 >: T](pref: List[T1]): List[T1] =              
    if (isEmpty) pref
    else if (pref isEmpty) this
    else {
      val prefInv = pref.invert

      def append(pre: List[T1], pos: List[T1]): List[T1] = pre match {
        case EmptyList => pos
        case ::(h, t)  => append(t, new ::(h, pos))
      }

      append(prefInv, this.asInstanceOf[List[T1]])
    }

  // checa se a lista possui um valor ou não
  def hasValue[T1 >: T](value: T1): Boolean =   
    if (isEmpty) false
    else if (head == value) true
    else tail.hasValue(value)

  // representa a lista como uma String
  override def toString =                       
    if (isEmpty) "List()"
    else if (size == 1) "List(" + head.toString + ")"
    else "List(" + head + tail.giveString
  
  // funcção auxiliar de toString  
  private def giveString(): String = ", " + head.toString + 
    (tail match {
      case EmptyList => ")"
      case _         => tail.giveString
    })

  // retorna um iterator da lista
  def getIterator[T1 >: T](): ListIterator[T1] = new ListIterator[T1](this)

  // executa uma função T => Unit em todos os elementos da lista
  def foreach(foo: (T) => Unit) {
    val myIter = getIterator()
    var continue = false
    if (!isEmpty)
      do {
        foo(myIter.value)
        if (myIter.hasNext()) {
          continue = true
          myIter.next()
        }
        else continue = false
      } while (continue)
  }
}

// usada para pattern matching (casamento de padrões)
private case class ::[T](val h: T, private var t: List[T]) extends List[T] {
  def head = h
  def tail = t
  def isEmpty = false
}

// usado para a criação de listas, usando a forma "val l = List(a, b, c, d)"
object List {
  def apply[T](values: T*): List[T] = 
    if (values isEmpty) EmptyList
    else               ::(values.head, apply(values.tail: _*))
}

// usado para iterar em Listas
class ListIterator[T](var list: List[T]) {
  def value = list.head
  def next() =
    if (list.tail.isEmpty) throw new IteratorOutOfBounds("tentando dar next() em um iterator no ultimo elemento")
    else list = list.tail
  def hasNext() = !list.tail.isEmpty
}

// objeto que representa uma Lista vazia
case object EmptyList extends List[Nothing] {
  def tail: List[Nothing]                        = throw new NotSuported("tail não suportado para EmptyList()")  
  def head: Nothing                              = throw new NotSuported("head não suportado para EmptyList()") 
  def isEmpty: Boolean                           = true
  override def apply(ind: Int): Nothing          = throw new IndexOutOfBounds("indice em List.apply() maior que o tamanho da Lista")
  override def size: Int                         = 0
}