/**
 * House class which will maintain information on appliances and meters
 * @param electricMeter Represents the electric meter of the house
 * @param waterMeter Represents the water meter od the house
 */
class House() {

    private var electricMeter : Meter = Meter("Electric meter", 0.013,0F)
    private var waterMeter : Meter = Meter("Water meter", 0.002, 0F)

    constructor(electricMeter : BatteryMeter, waterMeter : Meter) : this(){
        this.electricMeter = electricMeter
        this.waterMeter = waterMeter
    }
    private var applianceList = ArrayList<Appliance>()

    /**
     * Adds an appliance to the list and assigns it to the electrical meter
     * @param appliance The appliance you wish to add
     */
    infix fun addElectricalAppliance(appliance : Appliance){
        appliance.setMeter(electricMeter)
        applianceList.add(appliance)
    }

    /**
     * Adds an appliance to the list and assigns it to the water meter
     * @param appliance The appliance you wish to add
     */
    infix fun addWaterAppliance(appliance : Appliance){
        appliance.setMeter(waterMeter)
        applianceList.add(appliance)
    }

    /**
     * Removes an appliance from the list
     * @param appliance The appliance you wish to remove
     */
    infix fun removeAppliance(appliance : Appliance){
        applianceList.remove(appliance)
    }

    /**
     * The number of appliance in the house
     * @return Number of appliances
     */
    fun numAppliance() : Int{
        return applianceList.size
    }

    /**
     *  Method which simulates one hour passing with all the appliances
     *  @return The cost of both meters in that one hour
     */
    fun activate() : Double{
        var cost = 0.0;
        for(app in applianceList){
            app.timePasses()
        }
        cost += electricMeter.report()
        cost += waterMeter.report()
        return cost
    }

    fun activate(noHours: Int) : Double {

        var cost = 0.0;
        for(i in 0..noHours) {
            println("=========================== HOUR $i ===========================")
            for (app in applianceList) {
                app.timePasses()
            }
            cost += electricMeter.report()
            cost += waterMeter.report()
        }
        println("Final cost: $cost")
        return cost

    }
}