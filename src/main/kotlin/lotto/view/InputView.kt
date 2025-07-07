package lotto.view

import lotto.model.Const
import lotto.model.Numbers

object InputView {
    fun inputPurchaseAmount(): Int {
        while (true) {
            try {
                println("Please enter the purchase amount.")
                val amount = readln().trim().toInt()
                require(amount >= Const.PRICE && amount % Const.PRICE == 0) { Const.AMOUNT_ERROR }
                return amount
            } catch (_: NumberFormatException) {
                println("Amount must be a number")
            } catch (_: IllegalArgumentException) {
                println("Enter a valid amount")
            }
        }
    }

    fun inputManualTicketCount(maxTickets: Int): Int {
        while (true) {
            try {
                println("\nEnter the number of manual tickets to purchase.")
                val input = readln()
                return InputManualParser.parseManualTicketCount(input, maxTickets)
            } catch (_: NumberFormatException) {
                println("Manual tickets must be a number")
            } catch (_: IllegalArgumentException) {
                println("Enter a valid number")
            }
        }
    }

    fun inputManualTickets(count: Int): List<Numbers> {
        println("\nEnter the numbers for manual tickets.")
        val tickets = mutableListOf<Numbers>()
        repeat(count) {
            while (true) {
                try {
                    val input = readln()
                    tickets.add(InputManualParser.parseManualTicketNumbers(input))
                    break
                } catch (_: NumberFormatException) {
                    println("Manual number should  be a numeric")
                } catch (_: IllegalArgumentException) {
                    println("Enter a valid tickets")
                }
            }
        }
        return tickets
    }

    fun inputWinningNumbers(): Numbers {
        println("\nPlease enter last week’s winning numbers.")
        while (true) {
            try {
                val input = readln().trim()
                val numbers = input.split(",").map { it.trim().toInt() }
                return Numbers(numbers)
            } catch (_: NumberFormatException) {
                println("Winning number should  be a numeric")
            } catch (_: IllegalArgumentException) {
                println("Enter a valid winning number")
            }
        }
    }

    fun inputBonusNumber(numberList: Numbers): Int {
        while (true) {
            try {
                println("Please enter the bonus number.")
                val bonusNumber = readln().trim().toInt()
                require(bonusNumber in Const.MIN_RANGE..Const.MAX_RANGE) { Const.NUM_RANGE }
                require(!numberList.contains(bonusNumber)) { Const.DISTINCT_NUM }
                return bonusNumber
            } catch (_: NumberFormatException) {
                println("bonus Number must be a numeric")
            } catch (_: IllegalArgumentException) {
                println("Enter a valid bonus number")
            }
        }
    }
}
