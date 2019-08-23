/**
 * CyclixFixed appliance that inherits from the Abstract appliance class
 * @param name Name of the appliance
 * @param unitsConsumed The amount of units the appliance consumes per hour
 * @param timeOn The amount of time that the appliance is on a day
 */
class CyclicFixed(var name: String, var unitsConsumed: Float, var timeOn: Int) : Appliance(name) {
    var time : Int

    //Initialises time and checks that the timeOn is not out of range
    init{
        if(timeOn < 1 || timeOn > 24) timeOn = 1
        time  = 0
    }

    /**
     * Function to simulate time passing on an appliance
     */
    override fun timePasses() {
        time++
        if(time <= timeOn) tellMeterToConsumeUnits(unitsConsumed)

        time %= 24
    }
}