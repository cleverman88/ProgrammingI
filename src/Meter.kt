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
        println("Meter Reading: $meterReading")
        println("Cost: $cost")
        meterReading = 0F

        return cost
    }
}

fun main() {
    var meter = Meter("Sohaib", 10.0,0F)

    meter.report()
    meter.consumeUnits(10)
    meter.report()

    var appliance: Appliance = CyclicFixed("Fridge", 10, 25)
    appliance.setMeter(meter)

    appliance.timePasses()
    appliance.timePasses()

    meter.report()


}