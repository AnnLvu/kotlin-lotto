package lotto.model

class WinningTicket(val winningNumbers: Numbers, val bonusNumber: Int = 1) {
    init {
        require(bonusNumber in Const.MIN_RANGE..Const.MAX_RANGE) { Const.NUM_RANGE }
        require(bonusNumber !in winningNumbers) { Const.DISTINCT_NUM }
    }
}
