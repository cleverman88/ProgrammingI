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

    /**
     *  Method which simulates one noHours passing with all the appliances
     *  @param noHours The amount of hours you wish the simulation to run for
     *  @return The cost of both meters in that noHours hour
     */
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

fun main(args: Array<String>) {
    val Emeter = BatteryMeter("Electric", 0.013,0F)
    val Wmeter = Meter("Water", 0.002,0F)
    val house = House(Emeter, Wmeter)
    //val house = House()
    if(args.isNotEmpty()){
        val p = FileParser(args[0],house)
        if(args.size > 1){
            house.activate(Integer.parseInt(args[1]))
            return
        }
    }
    house.activate(168)
    println(house.numAppliance())
}
