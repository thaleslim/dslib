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
  def head: T         // pega o primeiro elemento da lista
  def tail: List[T]   // pega o resto da lista, excluindo o primeiro elemento

  def isEmpty: Boolean        // checa se a lista está vazia
  def apply(ind: Int): T =    // pega o n-ésizmo próximo elemento da lista
    if (ind < 0)      throw new NegativeIndex("indice negativo em List.apply()")
    else if (ind > 0) tail.apply(ind - 1)
    else              head

  def size(): Int = 1 + tail.size // pega o tamanho da lista

  def ::[T1 >: T](pref: T1): List[T1] = new ::(pref, this)  // adiciona um elemento ao início da lista

  def invert(): List[T] = {                                   // retorna uma lista identica a essa, só que invertida
    def getInvert(in: List[T], out: List[T] = EmptyList): List[T] = {
      in match {
        case EmptyList => out
        case ::(h, t)  => getInvert(t, h :: out)
      }
    }
    getInvert(this)
  }

  def :::[T1 >: T](pref: List[T1]): List[T1] =              // concatena duas listas
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

  def hasValue[T1 >: T](value: T1): Boolean =
    if (isEmpty) false
    else if (head == value) true
    else tail match {
      case EmptyList => false
      case _         => tail.hasValue(value)
    }

  override def toString =
    if (isEmpty) "List()"
    else if (size == 1) "List(" + head.toString + ")"
    else "List(" + head + tail.giveString
    
  private def giveString(): String = ", " + head.toString + 
    (tail match {
      case EmptyList => ")"
      case _         => tail.giveString
    })

  def getIterator[T1 >: T](): ListIterator[T1] = new ListIterator[T1](this)

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
case object EmptyList extends List[Nothing]{
  def tail: List[Nothing]                        = throw new NotSuported("tail não suportado para EmptyList()")  
  def head: Nothing                              = throw new NotSuported("head não suportado para EmptyList()") 
  def isEmpty: Boolean                           = true
  override def apply(ind: Int): Nothing          = throw new IndexOutOfBounds("indice em List.apply() maior que o tamanho da Lista")
  override def size: Int                         = 0
}