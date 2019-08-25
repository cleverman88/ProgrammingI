/**
 * Class that stores the information of a meter
 * @param utilityName Name of meter
 * @param unitCost Cost of consumption
 * @param meterReading Reading of the meter
 */
open class Meter(var utilityName: String, protected var unitCost: Double, protected var meterReading: Float) {

    /**
     * Method to add to the meter reading
     * @param noUnits Number of units consumed
     */
    fun consumeUnits(noUnits: Float){
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