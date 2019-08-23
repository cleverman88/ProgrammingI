import java.io.File
import java.lang.Exception
import kotlin.collections.HashMap

/**
 * Class that deals with file I/O
 * @param fileName Name of file you wish to read
 * @param house The instance of the house you wish to add the results to
 */
class FileParser(private val fileName : String, private val house : House) {
    var line :Int = 1

    init{
        readFile()
    }

    private fun getInformation(str : String) : String{
        return str.split(":")[1].replace(" ", "")
    }

    private fun readFile(){
        val map = HashMap<String,String>()
        var reset = false
        File(fileName).forEachLine{
            //Checks what the first word of the line is and adds it to it's respective map location
            when(it.split(" ")[0].replace(":","")){
                "name" -> {
                    if(reset) valid(null)
                    reset = true
                    map["name"] = getInformation(it)
                }
                "subclass" -> map["subclass"] = getInformation(it)
                "meter" -> map["meter"] = getInformation(it)
                "Min" -> map["min"] = getInformation(it)
                "Max" -> map["max"] = getInformation(it)
                "Fixed" -> map["unitsConsumed"] = getInformation(it)
                "Probability" -> map["probability"] = if(getInformation(it).length > 1) getInformation(it).split("in")[1] else getInformation(it)
                "Cycle" -> {
                    map["timeOn"] = getInformation(it).split("/")[0]
                    addAppliance(map)
                    val map = HashMap<String,String>()
                    line += 8
                    reset = false
                }
            }
        }
    }

    private fun addAppliance(map : HashMap<String,String>){
        //Checks what the mapping is and
        when{
            map["subclass"] == "CyclicFixed" ->{
                if(map["meter"] == "electric") house addElectricalAppliance CyclicFixed(valid(map["name"]!!), (valid(map["unitsConsumed"]))?.toFloat()!!,Integer.parseInt(valid(map["timeOn"])))
                else house addWaterAppliance CyclicFixed(valid(map["name"]!!), (valid(map["unitsConsumed"]))?.toFloat()!!,Integer.parseInt(valid(map["timeOn"])))
            }
            map["subclass"] == "CyclicVaries" ->{
                if(map["meter"] == "electric") house addElectricalAppliance CyclicVaries(valid(map["name"]!!), Integer.parseInt(valid(map["min"])),Integer.parseInt(valid(map["max"])),Integer.parseInt(valid(map["timeOn"])))
                else house addWaterAppliance CyclicVaries(valid(map["name"]!!), Integer.parseInt(valid(map["min"])),Integer.parseInt(valid(map["max"])),Integer.parseInt(valid(map["timeOn"])))
            }
            map["subclass"] == "RandomFixed" ->{
                if(map["meter"] == "electric") house addElectricalAppliance RandomFixed(valid(map["name"]!!), (valid(map["unitsConsumed"]))?.toFloat()!!,Integer.parseInt(valid(map["probability"])))
                else house addWaterAppliance RandomFixed(valid(map["name"]!!), (valid(map["unitsConsumed"]))?.toFloat()!!,Integer.parseInt(valid(map["probability"])))
            }
            map["subclass"] == "RandomVaries" ->{
                if(map["meter"] == "electric") house addElectricalAppliance RandomVaries(valid(map["name"]!!), Integer.parseInt(valid(map["min"])), Integer.parseInt(valid(map["max"])),Integer.parseInt(valid(map["probability"])))
                else house addWaterAppliance RandomVaries(valid(map["name"]!!), Integer.parseInt(valid(map["min"])), Integer.parseInt(valid(map["max"])),Integer.parseInt(valid(map["probability"])))
            }
        }
    }

    /**
     * Error checking on the format of the file
     * @param v The string being checked
     */
    private fun valid(v : String?) : String{
        println(v)
        if(v == null)  throw Exception("The config file is not in the correct format:\n Error on located on lines after $line and before line "+(line+8))
        else return v
    }
}
