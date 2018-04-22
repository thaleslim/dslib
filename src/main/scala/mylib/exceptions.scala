package mylib
package exceptions

case class EmptyEDIterator(msg: String) extends Exception(msg)