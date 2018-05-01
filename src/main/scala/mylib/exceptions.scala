package mylib
package exceptions

class EmptyEDIterator     (msg: String) extends Exception(msg)
class IndexOutOfBounds    (msg: String) extends Exception(msg)
class NegativeIndex       (msg: String) extends Exception(msg)
class IteratorOutOfBounds (msg: String) extends Exception(msg)
class PoorlyImplemented   (msg: String) extends Exception(msg)
class NotSuported         (msg: String) extends Exception(msg)