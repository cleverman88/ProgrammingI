/**
 * Class that stores the information of a meter
 * @param utilityName Name of meter
 * @param unitCost Cost of consumption
 * @param meterReading Reading of the meter
 */
open class Meter(protected var utilityName: String, protected var unitCost: Double, protected var meterReading: Float) {

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
    open fun report() : Double{
        println("------METER READING------")
        println("Name: $utilityName")
        if(meterReading < 0) meterReading = 0F
        println("Meter Reading: $meterReading")

        var cost = meterReading*unitCost
        println("Cost: $cost")
        meterReading = 0F

        return cost
    }
}

fun main() {
    val Emeter = BatteryMeter("Electric", 10.0,0F)
    val Wmeter = Meter("Water", 10.0,0F)
    val house = House(Emeter, Wmeter)
    //val house = House()
    val appliance = CyclicVaries("Fridge",-5,-20,24)
    val appliance2 = CyclicFixed("Fridge",15,24)

    house.addElectricalAppliance(appliance)
    house.addElectricalAppliance(appliance2)

    house.addWaterAppliance(CyclicFixed("Shower", 15, 1))

    house.activate(10)

}