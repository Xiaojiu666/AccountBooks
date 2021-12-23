package com.gx.accountbooks

object TestActivity {

    @JvmStatic
    fun main(args: Array<String>) {
//        println(calculator(1, 2, ::addition))
//        println(calculator(1, 2, ::subtraction))

        calculator(1, 2) { n1, n2 ->
            n1 - n2
        }

        val testStr = "abc"
        val sum = testStr.sumBy() { name ->
            println(name)
            name.toInt()
        }
        println(sum)
    }

    fun calculator(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
        return operation(num1, num2)
    }

    fun addition(num1: Int, num2: Int): Int {
        return num1 + num2
    }

    fun subtraction(num1: Int, num2: Int): Int {
        return num1 - num2
    }

    fun CharSequence.sumBy(selector: (Char) -> Int): Int {
        var sum: Int = 0
        for (element in this) {
            sum += selector(element)
        }
        return sum
    }
}