package tasklist

class taskList(){
    var tasklist= mutableListOf<String>()
    var command=""
    init{
        all@ while (true){
            println("Input an action (add, print, end):")
            command= readln()
            when(command){
                "add"->add()
                "print"->printlist()
                "end"->break@all
                else-> println("The input action is invalid")
            }
        }
        println("Tasklist exiting!")
    }

    fun add(){
        var str=""
        println("Input a new task (enter a blank line to end):")
        add@ while (true){
            str= readln().trim()
            if(str.isNullOrEmpty()){
                break@add
            }
            tasklist.add(str.trim())
        }

    }


    fun printlist(){
        if(tasklist.isEmpty()) println("No tasks have been input")
        var i =1
        for (a in tasklist){
            if(i<10) println("$i  $a")
            else  println("$i $a")
            i++}

    }
}

fun main() {

    var start = taskList()

}