package tasklist

class taskList(){
    var tasklist= mutableListOf<MutableList<String>>()
    var command=""
    var task =0
    init{
        all@ while (true){
            println("Input an action (add, print, end):")
            command= readln()
            when(command){
                "add"->add()
                "print"->printlistMAin()
                "end"->break@all
                else-> println("The input action is invalid")
            }
        }
        println("Tasklist exiting!")
    }
    fun add(){
        var str=""
        var tasklistTemp= mutableListOf<String>()

        println("Input a new task (enter a blank line to end):")
        add@ while (true){
            str= readln().trim()
            if(str.isNullOrEmpty()){
                break@add
            }
            tasklistTemp.add(str.trim())
        }
        if(tasklistTemp.isEmpty()) println("The task is blank")

        else task++
        tasklist.add(tasklistTemp)
    }
    fun printlistMAin(){
        if(task==0) println("No tasks have been input")
        else {
            var i = 1
            for (a in tasklist) {
                if (i < 10) {
                    print("$i  ")
                    printlistsmall(a)
                } else {
                    print("$i ")
                    printlistsmall(a)
                }
                println()
                i++
            }
        }
    }
    fun printlistsmall(tasklist:MutableList<String>){
        var s=0
        for (a in tasklist){
            if(s==0) println(a)
            else   println("   $a")
            s++
        }
    }
}
fun main() {
    var start = taskList()

}


