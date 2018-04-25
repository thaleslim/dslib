package mylib
package exceptions

class EmptyEDIterator(msg: String)     extends Exception(msg)
class NegativeIndex(msg: String)       extends Exception(msg)
class IteratorOutOfBounds(msg: String) extends Exception(msg)
class ShouldntExecute(msg: String)     extends Exception(msg)