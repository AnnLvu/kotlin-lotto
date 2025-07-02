package lotto

import java.lang.IllegalArgumentException

object InputView {
    fun inputPurchaseAmount() :Int {
        while(true) {
            try {
                println("Please enter the purchase amount.")
                val amount = readln().trim().toInt()
                require(amount >= LottoConstants.TICKET_PRICE && amount % LottoConstants.TICKET_PRICE == 0) {"Invalid amount, must be divisible by $LottoConstants.TICKET_PRICE"}
                return amount
            } catch (e: NumberFormatException) {
                println("Amount must be a number")
            } catch (e: IllegalArgumentException) {
                println("Enter a valid amount")
            }
        }
    }
}