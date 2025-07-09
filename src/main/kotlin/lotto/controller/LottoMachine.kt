package lotto.controller

import lotto.model.Const
import lotto.model.Tickets
import lotto.model.WinningTicket
import lotto.view.InputView
import lotto.view.OutputView

object LottoMachine {
    fun start() {
        val purchaseResult = purchaseTickets()
        OutputView.displayTickets(purchaseResult.tickets, purchaseResult.manualTicketCount)
        val winningNumbers = InputView.inputWinningNumbers()
        val bonusNumber = InputView.inputBonusNumber(winningNumbers)
        val winningTicket = WinningTicket(winningNumbers, bonusNumber)
        val winStats = purchaseResult.tickets.calculateStats(winningTicket)
        OutputView.displayResults(winStats, purchaseResult.tickets.calculateReturnRate(purchaseResult.amount, winStats))
    }

    private fun purchaseTickets(): PurchaseResult {
        val amount = InputView.inputPurchaseAmount()
        val maxTickets = amount / Const.PRICE
        val manualTicketCount = InputView.inputManualTicketCount(maxTickets)
        val manualTickets = InputView.inputManualTickets(manualTicketCount)
        val tickets = Tickets.generate(amount, manualTickets)
        return PurchaseResult(tickets, manualTicketCount, amount)
    }
}
