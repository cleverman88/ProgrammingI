import java.io.File
/**
 * Class that deals with file I/O
 * @param fileName Name of file you wish to read
 * @param house The instance of the house you wish to add the results to
 */
class FileParser(private val fileName : String, private val house : House) {
    init{
        readFile()
    }

    private fun getInformation(str : String) : String{
        return str.split(":")[1].replace(" ", "")
    }

    private fun readFile(){
        val map = HashMap<String,String>()
        File(fileName).forEachLine{
            //Checks what the first word of the line is and adds it to it's respective map location
            when(it.split(" ")[0].replace(":","")){
                "name" -> map["name"] = getInformation(it)
                "subclass" -> map["subclass"] = getInformation(it)
                "meter" -> map["meter"] = getInformation(it)
                "Min" -> map["min"] = getInformation(it)
                "Max" -> map["max"] = getInformation(it)
                "Fixed" -> map["unitsConsumed"] = getInformation(it)
                "Probability" -> map["probability"] = if(getInformation(it).length > 1) getInformation(it).split("in")[1] else getInformation(it)
                "Cycle" -> {
                    map["timeOn"] = getInformation(it).split("/")[0]
                    addAppliance(map)
                }
            }
        }
    }

    private fun addAppliance(map : HashMap<String,String>){
        //Checks what the mapping is and
        when{
            map["subclass"] == "CyclicFixed" ->{
                if(map["meter"] == "electric") house addElectricalAppliance CyclicFixed(map["name"]!!, (map["unitsConsumed"])?.toFloat()!!,Integer.parseInt(map["timeOn"]))
                else house addWaterAppliance CyclicFixed(map["name"]!!, (map["unitsConsumed"])?.toFloat()!!,Integer.parseInt(map["timeOn"]))
            }
            map["subclass"] == "CyclicVaries" ->{
                if(map["meter"] == "electric") house addElectricalAppliance CyclicVaries(map["name"]!!, Integer.parseInt(map["min"]),Integer.parseInt(map["max"]),Integer.parseInt(map["timeOn"]))
                else house addWaterAppliance CyclicVaries(map["name"]!!, Integer.parseInt(map["min"]),Integer.parseInt(map["max"]),Integer.parseInt(map["timeOn"]))
            }
            map["subclass"] == "RandomFixed" ->{
                if(map["meter"] == "electric") house addElectricalAppliance RandomFixed(map["name"]!!, (map["unitsConsumed"])?.toFloat()!!,Integer.parseInt(map["probability"]))
                else house addWaterAppliance RandomFixed(map["name"]!!, (map["unitsConsumed"])?.toFloat()!!,Integer.parseInt(map["probability"]))
            }
            map["subclass"] == "RandomVaries" ->{
                if(map["meter"] == "electric") house addElectricalAppliance RandomVaries(map["name"]!!, Integer.parseInt(map["min"]), Integer.parseInt(map["max"]),Integer.parseInt(map["probability"]))
                else house addWaterAppliance RandomVaries(map["name"]!!, Integer.parseInt(map["min"]), Integer.parseInt(map["max"]),Integer.parseInt(map["probability"]))
            }
        }
    }
}