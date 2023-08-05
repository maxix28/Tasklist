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


val taskDate = LocalDate(2022, 1, 9)
val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0")).date
val numberOfDays = currentDate.daysUntil(taskDate)

class taskList(){
    var tasklist= mutableListOf<MutableList<String>>()
    var command=""
    var task =0
    var priority=""
    init{
        all@ while (true){
            println("Input an action (add, print, edit, delete, end):")
            command= readln()
            when(command){
                "add"->{
                    setpriority()
                    add()
                }
                "print"->printlistMAin()
                "end"->break@all
                "delete"->delete()
                "edit"->edit()
                else-> println("The input action is invalid")
            }
        }
        println("Tasklist exiting!")
    }
    fun edit(){

        if(tasklist.isEmpty()) println("No tasks have been input")
        else {
            printlistMAin()
            while (true){
                println("Input the task number (1-${tasklist.size}):")
                var edt = readln()

                if(!("[\\d]+".toRegex().matches(edt))||"[\\s]+".toRegex().matches(edt)||edt.toInt()==0){
                    println("Invalid task number")
                    continue
                }
                if(edt.toInt()>tasklist.size){
                    println("Invalid task number")
                    continue
                }
                break
            }
            println("Input a field to edit (priority, date, time, task):")
            while(true){
                var change = readln()
                when(change){
                    "priority"->{}
                    "date"->{}
                    "time"->{}
                    "task"->{}
                    else->{
                        println("Invalid field")
                        continue
                    }
                }
                break}
        }
    }
    fun delete(){

        if(tasklist.isEmpty()) println("No tasks have been input")
        else{
            printlistMAin()

            while (true){
                println("Input the task number (1-${tasklist.size}):")
                var del = readln()

                if(!("[\\d]+".toRegex().matches(del))||"[\\s]+".toRegex().matches(del)||del.toInt()==0){
                    println("Invalid task number")
                    continue
                }
                if(del.toInt()>tasklist.size){
                    println("Invalid task number")
                    continue
                }
                tasklist.removeAt(del.toInt()-1)
                println("The task is deleted")
                break
            }}


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
        var mem =""
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
                println("The input date is invalid")
                continue
            }
            if("[\\w]+".toRegex().matches(date)||"[\\s]+".toRegex().matches(date)){
                println("The input date is invalid")
                continue
            }

            val a = date.split("-")

            var date1=String.format("%04d",a[0].toInt())
            date1+="-"

            date1+=String.format("%02d",a[1].toInt())

            date1+="-"

            date1+=String.format("%02d",a[2].toInt())
            date =date1
            date1+="T00:00:00Z"
            try{
                val taskDate = LocalDate(String.format("%04d",a[0].toInt()).toInt(), String.format("%02d",a[1].toInt()).toInt(),  String.format("%02d",a[2].toInt()).toInt())
                val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0")).date
                val numberOfDays = currentDate.daysUntil(taskDate)
                if(numberOfDays==0)mem=" T"
                else if(numberOfDays>0)mem=" I"
                else if(numberOfDays<0)mem=" O"

                break}
            catch (e:Exception){
                println("The input date is invalid")
                continue
            }
        }
        while(true){
            println("Input the time (hh:mm):")
            time = readln()
            if(!"[\\d]+:[\\d]+".toRegex().matches(time))  {   println("The input time is invalid")
                continue}
            var a = time.split(":")
            var time1=String.format("%02d",a[0].toInt())
            time1+=":"
            time1+=String.format("%02d",a[1].toInt())
            try{
                val dateTime = LocalTime.parse(time1)
                time=time1
                break}
            catch (e:Exception){
                println("The input time is invalid")
                continue
            }

        }
        priority= date+" "+time+" "+CHNL.uppercase()+mem
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


