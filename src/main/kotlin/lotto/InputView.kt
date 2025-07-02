package lotto

import java.lang.IllegalArgumentException

object InputView {
    fun inputPurchaseAmount(): Int {
        while (true) {
            try {
                println("Please enter the purchase amount.")
                val amount = readln().trim().toInt()
                require(amount >= LottoConstants.TICKET_PRICE && amount % LottoConstants.TICKET_PRICE == 0) { "Invalid amount, must be divisible by $LottoConstants.TICKET_PRICE" }
                return amount
            } catch (e: NumberFormatException) {
                println("Amount must be a number")
            } catch (e: IllegalArgumentException) {
                println("Enter a valid amount")
            }
        }
    }

    fun inputWinningNumbers(): List<Int> {
        println("\nPlease enter last week’s winning numbers.")
        while (true) {
            try {
                val input = readln().trim()
                val numbers = input.split(",").map { it.trim().toInt() }
                require(numbers.size == LottoConstants.NUMBER_COUNT) { "Numbers have to be distinct" }
                require(numbers.toSet().size == LottoConstants.NUMBER_COUNT) { "Number must be unique" }
                require(numbers.all { it in 1..45 }) { "Numbers should be between 1 to 45" }
                return numbers
            } catch (e: NumberFormatException) {
                println("Winning number should  be a numeric")
            } catch (e: IllegalArgumentException) {
                println("Enter a valid winning number")
            }
        }
    }

    fun inputBonusNumber(numberList: List<Int>): Int {
        while (true) {
            try {
                println("Please enter the bonus number.")
                val bonusNumber = readln().trim().toInt()
                require(bonusNumber in 1..45) {"Numbers should be between 1 to 45"}
                require(bonusNumber !in numberList){"Number  should not be in winning list"}
                return bonusNumber
            } catch (e: NumberFormatException) {
                println("bonus Number must be a numeric")
            } catch (e: IllegalArgumentException) {
                println("Enter a valid bonus number")
            }
        }
    }
}