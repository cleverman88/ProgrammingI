/**
 * Abstract class which indicates core properties of Appliances
 * @param superName Name of the appliance
 */
abstract class Appliance(var superName: String) {
    var m: Meter? = null

    /**
     * Setter method for the appliance
     * @param meter The meter which you wish to add
      */
    fun setMeter(meter : Meter){
        this.m = meter
    }

    /**
     * Abstract method which will be used by the respective appliance
     */
    abstract fun timePasses()

    /**
     * Method which communicates information between the appliance and the meter
     * @param units The amount of units that the appliance has consumed
     */
    protected fun tellMeterToConsumeUnits(units: Float){
        m?.consumeUnits(units)
    }
}