package mylib

/*
 *  Trait que define o contrato de um
 * Node que contém um valor de T e uma
 * referencia ao próximo Node
 * (Rafael G. de Paulo)
 *
*/
trait Node[T, NodeType <: Node[T, NodeType]] {
    var next: Option[NodeType]
    protected val _value: T

    def value: T = _value
}
