/**
 * Class that stores the information of a meter
 * @param utilityName Name of meter
 * @param unitCost Cost of consumption
 * @param meterReading Reading of the meter
 */
class Meter(private var utilityName: String, private var unitCost: Double, private var meterReading: Float) {

    /**
     * Method to add to the meter reading
     * @param noUnits Number of units consumed
     */
    fun consumeUnits(noUnits : Int){
        meterReading += noUnits
    }

    /**
     * Method which prints out the information of that meter
     * @return The cost of that report
     */
    fun report() : Double{
        val cost = meterReading*unitCost
        println("------METER READING------")
        println("Name: $utilityName")
        if(meterReading < 0) println("Meter Reading: 0.0")
        else println("Meter Reading: $meterReading")

        if(cost < 0) println("Cost: 0")
        else println("Cost: $cost")
        meterReading = 0F

        return cost
    }
}

fun main() {
    val Emeter = Meter("Electric", 10.0,0F)
    val Wmeter = Meter("Water", 10.0,0F)
    val house = House(Emeter, Wmeter)
    house.addElectricalAppliance(CyclicFixed("Fridge",-10,24))
    house.addWaterAppliance(CyclicFixed("Shower", 15, 1))

    val cost = house.activate()
    println(cost)


}