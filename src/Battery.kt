/**
 * Battery class which stores excess charge
 * @param max The maximum storage of the battery
 */
class Battery(var max: Float) {
    private var storage: Float = 0F

    /**
     * Method which charges the battery by in inputted amount
     * @param amount The amount you wish to charge
     */
    infix fun chargeBattery(amount: Float){
        storage += amount;
        if(storage > max) storage = max;
    }

    fun getStorage() : Float = storage

    /**
     * Determins the correct amount of charge to take from the battery given the amount required from the house
     * @param amount The amount required from the house
     */
    fun useAmount(amount : Float) : Float{
        val temp = storage
        storage -= amount;
        if(storage < 0) {
            println("Taken "+ (-storage) +" from the mains and $temp from the battery")
            storage = 0F
            return amount - storage
        }
        println("Taken $amount from battery")
        return 0F
    }

}