package lotto

object LottoMachine {
    fun start() {
        val amount = InputView.inputPurchaseAmount()
        generateTickets(amount)
        val winningNumbers = InputView.inputWinningNumbers()
        val bonusNumber = InputView.inputBonusNumber(winningNumbers)
    }

    fun generateTickets(amount: Int) {
        val ticketCount = amount/LottoConstants.TICKET_PRICE
        val ticketsList = mutableListOf<Ticket>()
        repeat(ticketCount) {
            val ticket = Ticket(generateTicketNumbers())
            ticketsList.add(ticket)
        }
        OutputView.displayTickets(ticketCount, ticketsList)
    }

    private fun generateTicketNumbers(): List<Int> {
        return(1..45).shuffled().take(6).sorted()
    }
}
