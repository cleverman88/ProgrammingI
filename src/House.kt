/**
 * House class which will maintain information on appliances and meters
 * @param electricMeter Represents the electric meter of the house
 * @param waterMeter Represents the water meter od the house
 */
class House(private var electricMeter : Meter,private var waterMeter : Meter) {
    private var applianceList = ArrayList<Appliance>()

    /**
     * Adds an appliance to the list and assigns it to the electrical meter
     * @param appliance The appliance you wish to add
     */
    fun addElectricalAppliance(appliance : Appliance){
        appliance.setMeter(electricMeter)
        applianceList.add(appliance)
    }

    /**
     * Adds an appliance to the list and assigns it to the water meter
     * @param appliance The appliance you wish to add
     */
    fun addWaterAppliance(appliance : Appliance){
        appliance.setMeter(waterMeter)
        applianceList.add(appliance)
    }

    /**
     * Removes an appliance from the list
     * @param appliance The appliance you wish to remove
     */
    fun removeAppliance(appliance : Appliance){
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
}