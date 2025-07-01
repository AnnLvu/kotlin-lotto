package lotto

class Lotto(val amount: Int) {
    init {
        require(amount <= 1000) { "Amount should be greater than or equal to 1000" }
        require(amount % 1000 == 0) { "Amount should be divisible by 1000" }
    }
    fun generateTickets() {
        val ticketNumbers = amount/TICKET_PRICE
        val ticket = Ticket(generateTicketNumbers())
    }

    private fun generateTicketNumbers(): List<Int> {
        return(1..45).shuffled().take(6)
    }

    companion object Price {
        private const val TICKET_PRICE = 1000
    }
}