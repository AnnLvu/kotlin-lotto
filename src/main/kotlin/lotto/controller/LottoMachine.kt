package lotto.controller

import lotto.model.Const
import lotto.model.Tickets
import lotto.model.WinningTicket
import lotto.view.InputView
import lotto.view.OutputView

object LottoMachine {
    fun start() {
        val (tickets, manualTicketCount, amount) = purchaseTickets()
        OutputView.displayTickets(tickets, manualTicketCount)
        val winningNumbers = InputView.inputWinningNumbers()
        val bonusNumber = InputView.inputBonusNumber(winningNumbers)
        val winningTicket = WinningTicket(winningNumbers, bonusNumber)
        val winStats = tickets.calculateStats(winningTicket)
        OutputView.displayResults(winStats, tickets.calculateReturnRate(amount, winStats))
    }

    private fun purchaseTickets(): Triple<Tickets, Int, Int> {
        val amount = InputView.inputPurchaseAmount()
        val maxTickets = amount / Const.PRICE
        val manualTicketCount = InputView.inputManualTicketCount(maxTickets)
        val manualTickets = InputView.inputManualTickets(manualTicketCount)
        val tickets = Tickets.generate(amount, manualTickets)
        return Triple(tickets, manualTicketCount, amount)
    }
}
