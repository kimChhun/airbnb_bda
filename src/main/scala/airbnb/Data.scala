package airbnb

class Data {
}

object Data {
  def map[T] (value: T, list: List[T]) : Double = list.indexOf(value)
  
  def unmap[T] (index: Double, list: List[T]) : Option[T] = {
    val i = index.round.toInt
    if (index == -1) None else Some(list(i))
  }
}