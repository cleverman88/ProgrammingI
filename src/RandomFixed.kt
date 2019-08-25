import kotlin.random.Random
/**
 * RandomVaries appliance that inherits from the Abstract appliance class
 * @param name Name of the appliance
 * @param unitsConsumed The amount of units the appliance consumes per hour
 * @param probability The probability that the appliance will switch on
 */
class RandomFixed(var name : String, var unitsConsumed: Float, var probability :Int) :Appliance(name){


    /**
     * Function to simulate time passing on an appliance
     */
    override fun timePasses() {
        if(Random.nextInt(0,probability) == 1) tellMeterToConsumeUnits(unitsConsumed)
    }

    override fun toString(): String {
        return "name: $name\n" +
                "subclass: RandomFixed\n" +
                "meter: "+ (this.m?.utilityName ?: String) +"\n" +
                "Min units consumed: \n" +
                "Max units consumed: \n" +
                "Fixed units consumed: $unitsConsumed\n" +
                "Probability switched on: 1 in $probability\n" +
                "Cycle length:"
    }

}