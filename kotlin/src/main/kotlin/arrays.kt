private data class Struct(var valueA: Int, var valueB: Int, var valueC: Long)

class Arrays {
    companion object {
        const val NUMBER_OF_ARRAY_ELEMENTS = 1_000_000
    }

    private val structArray = Array(NUMBER_OF_ARRAY_ELEMENTS) { Struct(0, 0, 0) }
    private val sumArray = LongArray(NUMBER_OF_ARRAY_ELEMENTS) { 0L }

    fun fillStructArray() {
        for (i in structArray.indices) {
            val struct = structArray[i]
            struct.valueA = i
            struct.valueB = i + 1
            struct.valueC = i + 2L
        }
    }

    fun fillSumArray() {
        for (i in structArray.indices) {
            val struct = structArray[i]
            with(struct) {
                sumArray[i] = valueA + valueB + valueC
            }
        }
    }

    fun sumOfArray() = sumArray.sum()
}