class Dinosaur {
    private var age: Int
        set(value){
            if(value>=0)
                field = value
        }
    private var length: Double
        set(value){
            if(value>=0)
                field = value
        }
    private val isPredator: Boolean

    private val name: String

    var info: String
        get(){
            val type=if(isPredator)"Хищник" else "Травоядное"
            return """------------------------
                |Имя: $name 
                |Тип: $type 
                |Возраст: $age 
                |Рост: $length""".trimMargin()
        }

    constructor(_name: String, _age: Int, _length: Double, _isPredator: Boolean){
        name = _name
        age = _age
        length = _length
        isPredator = _isPredator
        info = ""
    }
}