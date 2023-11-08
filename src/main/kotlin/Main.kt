fun main() {
    val totalCommission = calculateCommission("Mastercard", 73000, 10000)
    println("Комиссия: " + totalCommission)
}

fun calculateCommission(type: String, previousTransfers: Int, transferNow: Int): Int {
    return checkCommissionForType(type, previousTransfers, transferNow)
}

fun checkCommissionForType(type: String, previousTransfers: Int, transferNow: Int) = when (type) {
    "VK Pay" -> 0
    "Visa", "Mir" -> calculateCommissionForVisaOrMir(transferNow)
    "Mastercard", "Maestro" -> checkForLimitAndCalculate(previousTransfers, transferNow)
    else -> 0
}

fun checkForLimitAndCalculate(previousTransfers: Int, transferNow: Int): Int {
    var commissionForMasterOrMaestro = 0
    if (previousTransfers <= 75_000 && previousTransfers+transferNow<75_000){
        commissionForMasterOrMaestro
    } else if (previousTransfers+transferNow>75_000 && previousTransfers <75000) {
        commissionForMasterOrMaestro = ((((previousTransfers+transferNow)-75_000)* 0.006) + 20).toInt()

    } else {
        commissionForMasterOrMaestro = ((transferNow * 0.006) + 20).toInt()
    }
    return commissionForMasterOrMaestro
}

fun calculateCommissionForVisaOrMir(transferNow: Int): Int {
    val percentOfCommission = 0.0075
    var commissionForVisaOrMir = (transferNow * percentOfCommission).toInt()
    if (commissionForVisaOrMir < 35) {
        commissionForVisaOrMir = 35
    }
    return commissionForVisaOrMir
}
