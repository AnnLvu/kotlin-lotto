package lotto

object LottoMachine {
    fun start() {
        val amount = InputView.inputPurchaseAmount()
        generateTickets(amount)
    }

    fun generateTickets(amount: Int) {
        val ticketNumbers = amount/LottoConstants.TICKET_PRICE
        val ticket = Ticket(generateTicketNumbers())
    }

    private fun generateTicketNumbers(): List<Int> {
        return(1..45).shuffled().take(6)
    }
}
