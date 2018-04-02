package br.unb.cic.ed

object ProgramaPrincipal {
  def main(args: Array[String]) {
    if(args.size > 0) {
      println(args(0)) 
    }
    val l1 = new ArrayList[Int](3)
    l1.insert(0, 5)
    print(l1.elementAt(0))
  }
}
