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
    //  priority= date+" "+time+" "+CHNL.uppercase()+mem
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
    // tasklist.add(tasklistTemp)
}

fun edit(){
    var edt=""
    if(list.isEmpty()) println("No tasks have been input")
    else {
        print()
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
                    list[edt.toInt()-1]=list[edt.toInt()-1].copy(priority =temp)
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
                    list[edt.toInt()-1]=list[edt.toInt()-1].copy(time=time)

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
        print()

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


fun printlistsmall(tasklist:MutableList<String>){
    var s=0
    for (a in tasklist){
//        if(s==0) println(a)
//        else
        println("   $a")
        s++
    }
}
fun print(){
    if(taskN==0) println("No tasks have been input")
    else {
        var i = 1
        for (a in list) {
            if (i < 10) {
                print("$i  ")
                println("${a.date } ${a.time} ${a.priority} ${a.mes}")
                printlistsmall(a.tasklist)
            } else {
                print("$i ")
                println("${a.date } ${a.time} ${a.priority} ${a.mes}")
                printlistsmall(a.tasklist)
            }
            println()
            i++
        }
    }
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
            "print"->print()
            "end"->break
            "delete"->delete()
            "edit"->edit()
            else-> println("The input action is invalid")
        }
    }
    println("Tasklist exiting!")

}