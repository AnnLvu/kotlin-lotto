package lotto

object LottoMachine {
    fun start() {
        val amount = InputView.inputPurchaseAmount()
        val tickets = Tickets.generate(amount)
        OutputView.displayTickets(tickets)
        val winningNumbers = InputView.inputWinningNumbers()
        val bonusNumber = InputView.inputBonusNumber(winningNumbers)
        val winningTicket = WinningTicket(winningNumbers, bonusNumber)
        val winStats = tickets.calculateStats(winningTicket)

        OutputView.displayResults(winStats, tickets.calculateReturnRate(amount, winStats))
    }
}
