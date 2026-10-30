import java.util.Random

fun main() {
    while (true) {
        println("=============== LAB 0: Kotlin (Console) ===============")
        println("1) Сумма первой и последней цифры числа")
        println("2) Ввод чисел до 0 (кол-во, сумма, среднее)")
        println("3) Угадай число (0..10)")
        println("4) n простых чисел, начиная с 2")
        println("5) Элементы массива больше соседних (for/while/forEach)")
        println("6) Произведение, min, max (for/while/forEach/reduce/min/max)")
        println("7) Квадратное уравнение ax^2 + bx + c = 0 (функции)")
        println("8) Класс с массивом (сумма +, произведение, среднее)")
        println("9) Vector 3D (длина, скалярное, infix, operator *)")
        println("10) Vehicle + наследники (лодка/самолёт/танк)")
        println("0) Выход")
        print("Выберите задание: ")

        val choice: Int = readIntSafe()
        when (choice) {
            1 -> task1()
            2 -> task2()
            3 -> task3()
            4 -> task4()
            5 -> task5()
            6 -> task6()
            7 -> task7()
            8 -> task8()
            9 -> task9()
            10 -> task10()
            0 -> return
            else -> println("Введите число от 0 до 10.")
        }

        println("\nНажмите Enter, чтобы вернуться в меню...")
        readLine()
    }
}

// ---------- ввод ----------

private fun readLineSafe(): String = readLine() ?: ""

private fun readIntSafe(prompt: String? = null): Int {
    while (true) {
        if (prompt != null) print(prompt)
        val s = readLineSafe().trim()
        try {
            return Integer.parseInt(s)
        } catch (_: Exception) {
            println("Введите целое число.")
        }
    }
}

private fun readDoubleSafe(prompt: String? = null): Double {
    while (true) {
        if (prompt != null) print(prompt)
        val s = readLineSafe().trim().replace(',', '.')
        try {
            return java.lang.Double.parseDouble(s)
        } catch (_: Exception) {
            println("Введите число.")
        }
    }
}

// ---------- 1) сумма первой и последней цифры ----------

private fun task1() {
    println("Задание 1")
    val s: String = readPositiveIntString()

    // способ 1: по символам (без first/last)
    val firstDigit = java.lang.Character.getNumericValue(s[0])
    val lastDigit = java.lang.Character.getNumericValue(s[s.length - 1])
    val sumByChars = firstDigit + lastDigit

    // способ 2: по числу (% и /)
    var n = Integer.parseInt(s)
    val last = n % 10
    while (n >= 10) n /= 10
    val first = n
    val sumByMath = first + last

    println("По строке: сумма = $sumByChars")
    println("По числу (% и /): первая=$first, последняя=$last, сумма = $sumByMath")
}

private fun readPositiveIntString(): String {
    while (true) {
        print("Введите целое положительное число: ")
        val t = readLineSafe().trim()
        if (t.isEmpty()) {
            println("Пустая строка.")
            continue
        }
        if (t == "0") {
            println("Нужно положительное (>0).")
            continue
        }

        var ok = true
        for (ch in t) {
            if (!java.lang.Character.isDigit(ch)) {
                ok = false
                break
            }
        }
        if (!ok) {
            println("Только цифры, без знаков.")
            continue
        }
        return t
    }
}

// ---------- 2) ввод до 0 ----------

private fun task2() {
    println("Задание 2")
    var count = 0
    var sum = 0.0

    while (true) {
        val x = readDoubleSafe("Введите число (0 завершит ввод): ")
        if (x == 0.0) break
        count++
        sum += x
    }

    val avg = if (count == 0) 0.0 else sum / count
    println("Количество введённых чисел: $count")
    println("Сумма: $sum")
    println("Среднее арифметическое: $avg")
}

// ---------- 3) угадай число ----------

private fun task3() {
    println("Задание 3")
    val a: Int = Random().nextInt(11) // 0..10
    while (true) {
        val b: Int = readIntSafe("Введите число B (0..10): ")
        if (b > a) println("Много")
        else if (b < a) println("Мало")
        else {
            println("Угадал")
            return
        }
    }
}

// ---------- 4) n простых ----------

private fun task4() {
    println("Задание 4")

    var n: Int
    while (true) {
        n = readIntSafe("Введите количество простых чисел n (>=1): ")
        if (n >= 1) break
        println("n должно быть >= 1.")
    }

    var found = 0
    var candidate = 2

    while (found < n) {
        if (isPrime(candidate)) {
            found++
            println("${found}-ое число: $candidate")
        }
        candidate++
    }
}

private fun isPrime(x: Int): Boolean {
    if (x < 2) return false
    if (x == 2) return true
    if (x % 2 == 0) return false
    var d = 3
    while (d * d <= x) {
        if (x % d == 0) return false
        d += 2
    }
    return true
}


// ---------- 5) больше соседей (for/while/forEach) ----------

private fun task5() {
    println("Задание 5")
    val arr = intArrayOf(1, 5, 2, 7, 7, 3, 9, 4, 8, 2, 1)
    print("Массив: "); printIntArray(arr); println()

    // for
    val rFor = mutableListOf<Int>()
    for (i in 1 until (arr.size - 1)) {
        if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) rFor.add(arr[i])
    }

    // while
    val rWhile = mutableListOf<Int>()
    var i = 1
    while (i < arr.size - 1) {
        if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) rWhile.add(arr[i])
        i++
    }

    // forEach
    val rForEach = mutableListOf<Int>()
    forEachIndex(arr) { idx ->
        if (idx != 0 && idx != arr.size - 1) {
            val v = arr[idx]
            if (v > arr[idx - 1] && v > arr[idx + 1]) rForEach.add(v)
        }
    }

    println("for:     $rFor")
    println("while:   $rWhile")
    println("forEach: $rForEach")
}

private fun forEachIndex(arr: IntArray, action: (Int) -> Unit) {
    for (i in 0 until arr.size) action(i)
}

private fun printIntArray(arr: IntArray) {
    print("[")
    for (i in 0 until arr.size) {
        print(arr[i])
        if (i != arr.size - 1) print(", ")
    }
    print("]")
}

// ---------- 6) произведение, min, max разными способами ----------

private fun task6() {
    println("Задание 6")
    val arr = intArrayOf(2, -3, 4, 1, 4, 7, -2)
    print("Массив: "); printIntArray(arr); println()

    // for
    var prodFor = 1L
    var minFor = arr[0]
    var maxFor = arr[0]
    for (v in arr) {
        prodFor *= v.toLong()
        if (v < minFor) minFor = v
        if (v > maxFor) maxFor = v
    }

    // while
    var prodWhile = 1L
    var minWhile = arr[0]
    var maxWhile = arr[0]
    var i = 0
    while (i < arr.size) {
        val v = arr[i]
        prodWhile *= v.toLong()
        if (v < minWhile) minWhile = v
        if (v > maxWhile) maxWhile = v
        i++
    }

    // forEach
    var prodForEach = 1L
    var minForEach = arr[0]
    var maxForEach = arr[0]
    forEachIndex(arr) { idx ->
        val v = arr[idx]
        prodForEach *= v.toLong()
        if (v < minForEach) minForEach = v
        if (v > maxForEach) maxForEach = v
    }

    // reduce
    val prodReduce = reduceLong(arr) { acc, v -> acc * v.toLong() }

    // min()/max()
    val minFn = min(arr)
    val maxFn = max(arr)

    println("for:     prod=$prodFor, min=$minFor, max=$maxFor")
    println("while:   prod=$prodWhile, min=$minWhile, max=$maxWhile")
    println("forEach: prod=$prodForEach, min=$minForEach, max=$maxForEach")
    println("reduce:  prod=$prodReduce")
    println("min/max: min=$minFn, max=$maxFn")
}

private fun reduceLong(arr: IntArray, op: (Long, Int) -> Long): Long {
    var acc = arr[0].toLong()
    for (i in 1 until arr.size) acc = op(acc, arr[i])
    return acc
}

private fun min(arr: IntArray): Int {
    var m = arr[0]
    for (v in arr) if (v < m) m = v
    return m
}

private fun max(arr: IntArray): Int {
    var m = arr[0]
    for (v in arr) if (v > m) m = v
    return m
}

// ---------- 7) квадратное уравнение ----------

private fun task7() {
    println("Задание 7")
    val a = readDoubleSafe("Введите a: ")
    val b = readDoubleSafe("Введите b: ")
    val c = readDoubleSafe("Введите c: ")
    quadraticRoot(a, b, c)
}

private fun sqr(n: Double): Double = n * n

private fun discriminant(a: Double, b: Double, c: Double): Double = sqr(b) - 4.0 * a * c

private fun rootsNumber(a: Double, b: Double, c: Double): Int {
    if (a == 0.0) return -1
    val d = discriminant(a, b, c)
    return when {
        d > 0.0 -> 2
        d == 0.0 -> 1
        else -> 0
    }
}

private fun quadraticRoot(a: Double, b: Double, c: Double) {
    val rn = rootsNumber(a, b, c)
    if (rn == -1) {
        println("Это не квадратное уравнение (a = 0).")
        return
    }
    val d = discriminant(a, b, c)
    println("D = $d")
    when (rn) {
        2 -> {
            val x1 = (-b + java.lang.Math.sqrt(d)) / (2.0 * a)
            val x2 = (-b - java.lang.Math.sqrt(d)) / (2.0 * a)
            println("x1 = $x1")
            println("x2 = $x2")
        }

        1 -> {
            val x = (-b) / (2.0 * a)
            println("x = $x")
        }

        0 -> println("Корней нет (D < 0).")
    }
}

// ---------- 8) класс с массивом ----------

private fun task8() {
    println("Задание 8")
    val arr = intArrayOf(-2, 5, 0, 3, -1, 7)
    print("Массив: "); printIntArray(arr); println()

    val obj = ArrayStats(arr)
    println("Сумма положительных: ${obj.sumPositive()}")
    println("Произведение элементов: ${obj.product()}")
    println("Среднее арифметическое: ${obj.average()}")
}

class ArrayStats(private val arr: IntArray) {
    fun sumPositive(): Int {
        var s = 0
        for (v in arr) if (v > 0) s += v
        return s
    }

    fun product(): Long {
        var p = 1L
        for (v in arr) p *= v.toLong()
        return p
    }

    fun average(): Double {
        if (arr.isEmpty()) return 0.0
        var s = 0.0
        for (v in arr) s += v
        return s / arr.size
    }
}

// ---------- 9) Vector ----------

private fun task9() {
    println("Задание 9")
    val v1 = Vector(1.0, 2.0, 3.0)
    val v2 = Vector(3.0, 2.0, 1.0)

    println("v1 = $v1")
    println("v2 = $v2")
    println("Длина v1: ${v1.length()}")
    println("Скалярное (метод): ${v1.dotProduct(v2)}")
    println("Скалярное (infix): ${v1 dot v2}")
    println("Скалярное (operator *): ${v1 * v2}")
    println("Скалярное (внешняя функция): ${dotProduct(v1, v2)}")
}

data class Vector(val x: Double, val y: Double, val z: Double) {
    fun length(): Double = java.lang.Math.sqrt(x * x + y * y + z * z)

    fun dotProduct(other: Vector): Double = x * other.x + y * other.y + z * other.z

    infix fun dot(other: Vector): Double = dotProduct(other)

    operator fun times(other: Vector): Double = dotProduct(other)
}

fun dotProduct(a: Vector, b: Vector): Double = a.dotProduct(b)

// ---------- 10) Vehicle ----------

private fun task10() {
    println("Задание 10")
    val boat: Vehicle = Boat()
    val plane: Vehicle = Plane()
    val tank: Vehicle = Tank()

    boat.start()
    boat.stop()
    plane.start()
    plane.stop()
    tank.start()
    tank.stop()
}

open class Vehicle(
    open val speed: Int = 0,
    open val name: String = "Транспорт"
) {
    open fun start() {
        println("$name начал движение со скоростью $speed км/ч")
    }

    open fun stop() {
        println("$name остановился")
    }
}

class Boat : Vehicle(speed = 30, name = "Лодка") {
    override fun start() = println("$name начал движение со скоростью $speed км/ч")
    override fun stop() = println("$name остановилась")
}

class Plane : Vehicle(speed = 850, name = "Самолёт") {
    override fun start() = println("$name начал движение со скоростью $speed км/ч")
    override fun stop() = println("$name остановился")
}

class Tank : Vehicle(speed = 45, name = "Танк") {
    override fun start() = println("$name начал движение со скоростью $speed км/ч")
    override fun stop() = println("$name остановился")
}
