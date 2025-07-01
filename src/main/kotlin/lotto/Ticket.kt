package lotto

class Ticket(numbers:List<Int>) {
    init {
        require(numbers.all{it in 1..45 }) {"Numbers should be between 1 to 45"}
    }
}