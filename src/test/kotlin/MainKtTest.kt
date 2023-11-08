import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun shouldAddCommissionForMasterAndMaestroIfLimitExceed() {
        val type = "Mastercard"
        val previousTransfers = 76_000
        val transferNow = 10_000

        val result = calculateCommission(type, previousTransfers, transferNow)

        assertEquals(80, result)
    }

    @Test
    fun shouldAddCommissionForSubtractionForMasterAndMaestroIfLimitExceedNow() {
        val previousTransfers = 73_000
        val transferNow = 10_000

        val result = checkForLimitAndCalculate(previousTransfers, transferNow)

        assertEquals(68, result)
    }


    @Test
    fun commissionShouldBeZeroForMasterAndMaestroIfLimitNotExceed() {
        val type = "Mastercard"
        val previousTransfers = 0
        val transferNow = 10_000

        val result = calculateCommission(type, previousTransfers, transferNow)

        assertEquals(0, result)

    }

    @Test
    fun commissionForVkPayShouldBeZero(){
        val type = "VK Pay"
        val previousTransfers = 0
        val transferNow = 10_000

        val result = checkCommissionForType(type, previousTransfers, transferNow)

        assertEquals(0, result)
    }

    @Test
    fun commissionForVisaOrMirShouldBeAtLeast35(){
        val transferNow = 100

        val result = calculateCommissionForVisaOrMir(transferNow)

        assertEquals(35, result)
    }

    @Test
    fun commissionForVisaOrMirShouldnotBeLessThan35(){
        val type = "Mir"
        val previousTransfers = 0
        val transferNow = 10_000

        val result = calculateCommission(type, previousTransfers, transferNow)

        assertEquals(75, result)
    }

}