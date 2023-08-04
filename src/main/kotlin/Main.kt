package tasklist
import kotlinx.datetime.*
import java.lang.Exception
import java.time.LocalTime



//Create a LocalDateTime instance for 2021-12-21 16:57
//val dateTime = LocalDateTime(2021, 12, 3, 16, 57)
//val year = dateTime.year         // Get year as an integer
//val month = dateTime.monthNumber // Get month as an integer
//val day = dateTime.dayOfMonth    // Get day as an integer
//val hour = dateTime.hour         // Get hour as an integer
//val minutes = dateTime.minute    // Get minutes as an integer


class taskList(){
    var tasklist= mutableListOf<MutableList<String>>()
    var command=""
    var task =0
    var priority=""
    init{
        all@ while (true){
            println("Input an action (add, print, end):")
            command= readln()
            when(command){
                "add"->{
                    setpriority()
                    add()
                }
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
        var i=0
        println("Input a new task (enter a blank line to end):")
        add@ while (true){
            str= readln().trim()
            if(str.isNullOrEmpty()){
                break@add
            }
            if(i==0)  tasklistTemp.add(priority)
            tasklistTemp.add(str.trim())
            i++
        }
        if(tasklistTemp.isEmpty()) println("The task is blank")

        else task++
        tasklist.add(tasklistTemp)
    }
    fun setpriority(){
        var CHNL=""
        var time=" "
        var date=" "
        while(true) {
            println("Input the task priority (C, H, N, L):")

            CHNL = readln()
            if("[C/h/l/n/c/N/H/L]".toRegex().matches(CHNL))break
            else continue
        }
        while (true) {
            println("Input the date (yyyy-mm-dd):")
            date = readln()
            if(date.isNullOrEmpty()){
                continue
            }
            if("[\\w]+".toRegex().matches(date)){
                println("The input date is invalid")
                continue
            }

            var a = date.split("-")

            var date1=a[0]
            date1+="-"

            date1+=String.format("%02d",a[1].toInt())

            date1+="-"

            date1+=String.format("%02d",a[2].toInt())
            date =date1
            date1+="T00:00:00Z"
            try{
                val dateTime = Instant.parse(date1)
                break}
            catch (e:Exception){
                println("The input date is invalid")
                continue
            }
            break
        }
        while(true){
            println("Input the time (hh:mm):")
            time = readln()
            var a = time.split(":")
            var time1=String.format("%02d",a[0].toInt())
            time1+=":"
            time1+=String.format("%02d",a[1].toInt())
            try{
                val dateTime = LocalTime.parse(time1)
                break}
            catch (e:Exception){
                println("The input time is invalid")
                continue
            }
            time=time1

//            if("[0-12]:0?[0-59]".toRegex().matches(time))break
//            else continue
        }
        priority= date+" "+time+" "+CHNL.toUpperCase()
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


