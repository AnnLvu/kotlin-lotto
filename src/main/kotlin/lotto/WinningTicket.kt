package lotto

class WinningTicket(val winningNumbers: Numbers, val bonusNumber: Int = 1) {
    init {
        require(bonusNumber in Const.MIN..Const.MAX) { Const.NUM_RANGE }
        require(!winningNumbers.contains(bonusNumber)) { Const.DISTINCT_NUM }
    }
}
