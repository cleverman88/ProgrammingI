import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random
/**
 * RandomVaries appliance that inherits from the Abstract appliance class
 * @param name Name of the appliance
 * @param min Minimum power it can potentially consume (inclusive)
 * @param max Maximum power it can potentially consume (inclusive)
 * @param probability The probability that the appliance will switch on
 */
class RandomVaries(var name : String, var min: Int, var max: Int, var probability: Int) : Appliance(name){

    /**
     * Function to simulate time passing on an appliance
     */
    override fun timePasses() {
        val minimum = min(min, max)
        val maximum = max(min, max)
        var unitsConsumed = Random.nextInt(minimum,maximum)
        if(Random.nextInt(0,probability) == 1) tellMeterToConsumeUnits(unitsConsumed.toFloat())
    }

}