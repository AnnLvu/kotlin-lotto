package lotto

object OutputView {
    fun displayTickets(ticketCount: Int, ticketsList: List<Ticket>) {
        println("You have purchased $ticketCount tickets.")
        ticketsList.forEach { ticket ->
            println(ticket.numbers.joinToString(prefix = "[", postfix = "]", separator = ","))
        }
    }
}
