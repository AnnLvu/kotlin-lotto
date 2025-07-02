package lotto

class Lotto(val amount: Int) {
    init {
        require(amount <= LottoConstants.TICKET_PRICE) { "Amount should be greater than or equal to 1000" }
        require(amount % LottoConstants.TICKET_PRICE == 0) { "Amount should be divisible by 1000" }
    }
}