package lotto.controller

import lotto.model.Tickets

data class PurchaseResult(
    val tickets: Tickets,
    val manualTicketCount: Int,
    val amount: Int,
)
