package tasklist
import kotlinx.datetime.*
import java.lang.Exception
import java.time.LocalTime
data class Tasklist(var index:Int, var tasklist :MutableList<String>,var priority:String,var date : String,var time:String,var mes:String)
val list=mutableListOf<Tasklist>()
var taskN=0
var CHNL=""
var time=" "
var date=" "
var mem =""
fun setpriority(){
    while(true) {
        println("Input the task priority (C, H, N, L):")
        CHNL = readln().uppercase()
        if("[C/N/H/L]".toRegex().matches(CHNL))break
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
            if(numberOfDays==0)mem="T"
            else if(numberOfDays>0)mem="I"
            else if(numberOfDays<0)mem="O"

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

}
fun add(){
    var tasklistTemp= mutableListOf<String>()
    var i=0
    println("Input a new task (enter a blank line to end):")
    add@ while (true){
        var str= readln().trim()
        if(str.isNullOrEmpty()){
            break@add
        }
        //if(i==0)  tasklistTemp.add(priority)
        tasklistTemp.add(str.trim())
        i++
    }
    if(tasklistTemp.isEmpty()) println("The task is blank")
    else{ taskN++

        list.add(Tasklist(taskN,tasklistTemp,CHNL,date,time,mem))}

}

fun edit(){
    var edt=""
    if(list.isEmpty()) println("No tasks have been input")
    else {
        fullPrint()
        while (true){
            println("Input the task number (1-${list.size}):")
            edt = readln()

            if(!("[\\d]+".toRegex().matches(edt))||"[\\s]+".toRegex().matches(edt)||edt.toInt()==0){
                println("Invalid task number")
                continue
            }
            if(edt.toInt()>list.size){
                println("Invalid task number")
                continue
            }
            break
        }
        while(true){
            println("Input a field to edit (priority, date, time, task):")
            var change = readln()
            when(change){
                "priority"->{
                    var temp=" "
                    while(true) {
                        println("Input the task priority (C, H, N, L):")

                        temp = readln()
                        if("[C/h/l/n/c/N/H/L]".toRegex().matches(CHNL))break
                        else continue
                    }
                    list[edt.toInt()-1].priority=temp
                }
                "date"->{
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
                            if(numberOfDays==0)mem="T"
                            else if(numberOfDays>0)mem="I"
                            else if(numberOfDays<0)mem="O"

                            break}
                        catch (e:Exception){
                            println("The input date is invalid")
                            continue
                        }
                    }
                    list[edt.toInt()-1]=list[edt.toInt()-1].copy(date =date, mes = mem)
                }
                "time"->{
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
                    list[edt.toInt()-1].time=time

                }
                "task"->{
                    var tasklistTemp= mutableListOf<String>()
                    var i=0
                    println("Input a new task (enter a blank line to end):")
                    add@ while (true){
                        var str= readln().trim()
                        if(str.isNullOrEmpty()){
                            break@add
                        }
                        //if(i==0)  tasklistTemp.add(priority)
                        tasklistTemp.add(str.trim())
                        i++
                    }
                    list[edt.toInt()-1]=list[edt.toInt()-1].copy(tasklist =tasklistTemp )

                }
                else->{
                    println("Invalid field")
                    continue
                }
            }
            break}
        println("The task is changed")
    }
}
fun delete(){

    if(list.isEmpty()) println("No tasks have been input")
    else{
        //print()
        fullPrint()
        while (true){
            println("Input the task number (1-${list.size}):")
            var del = readln()

            if(!("[\\d]+".toRegex().matches(del))||"[\\s]+".toRegex().matches(del)||del.toInt()==0){
                println("Invalid task number")
                continue
            }
            if(del.toInt()>list.size){
                println("Invalid task number")
                continue
            }
            list.removeAt(del.toInt()-1)
            println("The task is deleted")
            break
        }}
}
fun fullPrint(){
    if(list.isEmpty()) println("No tasks have been input")
    else{
        var colortag="\\u001B[101m \\u001B[0m"
        var i =1

        println("+----+------------+-------+---+---+--------------------------------------------+\n" +
                "| N  |    Date    | Time  | P | D |                   Task                     |\n" +
                "+----+------------+-------+---+---+--------------------------------------------+")
        for(a in list){
            print("| $i  | ${a.date} | ${a.time} | ")
            when(a.priority){
                "C"-> print("\u001B[101m \u001B[0m")
                "H"-> print("\u001B[103m \u001B[0m")
                "N"-> print("\u001B[102m \u001B[0m")
                "L"-> print("\u001B[104m \u001B[0m")
            }
            print(" | ")
            when(a.mes){
                "O"-> print("\u001B[101m \u001B[0m")
                "T"-> print("\u001B[103m \u001B[0m")
                "I"-> print("\u001B[102m \u001B[0m")
            }
            print(" |")
            smallprint(a.tasklist)
            println("+----+------------+-------+---+---+--------------------------------------------+")
            i++} }
}
fun smallprint(a:MutableList<String>){
    var max = 45
    if(a[0].length>44){
        val inputString = a[0]
        val chunkSize = 44
        val chunks = inputString.chunked(chunkSize)

        println((chunks[0]+"${"%${max-chunks[0].length}s".format("|")}"))
        for(c in 1.. chunks.size-1){
            println("|    |            |       |   |   |"+chunks[c]+"${"%${max-chunks[c].length}s".format("|")}")
        }
    } else{ println(a[0]+"${"%${max-a[0].length}s".format("|")}")}

    for(b in 1.. a.size-1)
        if(a[b].length>44){
            val inputString = a[b]
            val chunkSize = 44
            val chunks = inputString.chunked(chunkSize)
            println(("|    |            |       |   |   |"+chunks[0]+"${"%${max-chunks[0].length}s".format("|")}"))
            for(c in 1.. chunks.size-1){
                println("|    |            |       |   |   |"+chunks[c]+"${"%${max-chunks[c].length}s".format("|")}")
            }

        }
        else println("|    |            |       |   |   |"+a[b]+"${"%${max-a[b].length}s".format("|")}")
}
fun main(){
    while (true){
        println("Input an action (add, print, edit, delete, end):")
        var command= readln()
        when(command){
            "add"-> {
                setpriority()
                add()
            }
            "full"-> fullPrint()
            "print"->fullPrint()
            "end"->break
            "delete"->delete()
            "edit"->edit()
            else-> println("The input action is invalid")
        }
    }
    println("Tasklist exiting!")
}