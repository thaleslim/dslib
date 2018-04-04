package mylib

object Aaaa {
    def main(args: Array[String]) {
        val q = new mylib.LQueue[Int]()

        q.push(1)
        q.push(2)
        q.push(3)

        q.pop()
        q.pop()
        q.pop()
    }
}