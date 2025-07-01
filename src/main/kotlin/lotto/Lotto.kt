package lotto

class Lotto(val amount: Int) {
    init{
        require(amount <= 1000) { "Amount should be greater than or equal to 1000" }
        require(amount % 1000 == 0) {"Amount should be divisible by 1000"}
    }
}