import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random
/**
 * CyclixVaries appliance that inherits from the Abstract appliance class
 * @param name Name of the appliance
 * @param min Minimum power it can potentially consume (inclusive)
 * @param max Maximum power it can potentially consume (inclusive)
 * @param timeOn The amount of time that the appliance is on a day
 */
class CyclicVaries(var name : String, var min : Int, var max: Int, var timeOn: Int) : Appliance(name) {
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
        val minimum = min(min, max)
        val maximum = max(min, max)
        var unitsConsumed = Random.nextInt(minimum,maximum)
        if(time <= timeOn) tellMeterToConsumeUnits(unitsConsumed.toFloat())

        time %= 24

    }


}