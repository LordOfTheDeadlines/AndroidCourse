fun main() {
    val generator = DinosaurGroupGenerator()
    println("Минимальное количество объектов: ")
    val min = checkInput()
    println("Максимальное количество объектов: ")
    val max = checkInput()
    val list = if(min<=max)generator.createGroup(min, max) else generator.createGroup(max, min)
    for(l in list){
        println(l.info)
    }
}
fun checkInput(): Int {
    val num = readLine()?.toIntOrNull()
    return if(num!=null && num>0) num else 1
}