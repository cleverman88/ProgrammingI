import kotlin.random.Random
/**
 * RandomVaries appliance that inherits from the Abstract appliance class
 * @param name Name of the appliance
 * @param unitsConsumed The amount of units the appliance consumes per hour
 * @param probability The probability that the appliance will switch on
 */
class RandomFixed(name : String, var unitsConsumed: Float, var probability :Int) :Appliance(name){


    /**
     * Function to simulate time passing on an appliance
     */
    override fun timePasses() {
        if(Random.nextInt(0,probability) == 1) tellMeterToConsumeUnits(unitsConsumed)
    }

}