package lotto

class Numbers(private val numbers: List<Int>) {
    init {
        require(numbers.size == Const.NUMBER_COUNT) { Const.DISTINCT_NUM }
        require(numbers.toSet().size == Const.NUMBER_COUNT) { Const.DISTINCT_NUM }
        require(numbers.all { it in Const.MIN..Const.MAX }) { Const.NUM_RANGE }
    }

    fun getNumbers(): List<Int> = numbers
    fun contains(number: Int): Boolean = numbers.contains(number)
    fun countMatches(other: Numbers): Int = numbers.count { other.contains(it) }
}