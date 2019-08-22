/**
 * BatteryMeter extends Meter class, has ability to store charge
 * @param utilityName Name of meter
 * @param unitCost Cost of consumption
 * @param meterReading Reading of the meter
 */
class BatteryMeter(utilityName : String, unitCost : Double, meterReading : Float) : Meter(utilityName,unitCost,meterReading) {
    var battery : Battery = Battery(10F);

    /**
     * Overridden report method which stores excess charge in the battery
     * @return The cost of that report
     */
    override fun report() : Double{
        println("------BATTERY METER READING------")
        println("Name: $utilityName")
        if(meterReading < 0){
            println("Meter Reading: $meterReading")
            meterReading *= -1
            battery.chargeBattery(meterReading)
            println("Charged battery with $meterReading units")
            meterReading = 0F
        }
        else{
            println("Meter Reading: $meterReading")
            meterReading = battery.useAmount(meterReading)
        }

        var cost = meterReading*unitCost

        if(cost < 0) cost = 0.0
        else println("Cost: $cost")
        meterReading = 0F

        return cost
    }
}