package ru.netology

fun main() {

    // Объявляем переменные
    // --------------------
    val limitSutki = 150_000
    val limitMonth = 600_000

    var typeCard = "Мир" // тип карты (по умолчанию Мир)

    val limitMastercard = 75000 // с карты Mastercard комиссия не взимается, пока не превышен месячный лимит
    val comissionMastercard = 0.6 / 100 // 0,6%
    val comissionMastercard20 = 20 // 20 руб

    val comissionVisa = 0.75 / 100 // 0,75%
    val minComissionVisa = 35 // минимальная сумма комиссии 35 руб

    val comissionMir = 0 // с карты Мир комиссия не взимается
    // --------------------

    // --------------------
    fun calculationAlgorithmComission(typeCard: String, previousTransfer: Int, currentTransfer: Int): Int {

        var itogComission = 0 // Объявляем <itogComission> локальной переменноЙ, чтобы
                                // комиссия пересчитывалась заново при каждом вызове функции.
        var limitSutkiCount = 0  // Считаем сумму перевода за сутки
        var limitMonthCount = 0  // Считаем сумму перевода за месяц

        if (typeCard == "Мир") {
            if ((limitSutkiCount + previousTransfer + currentTransfer) > limitSutki) {
                println("Операция отклонена -> Превышен лимит переводов за сутки !")
                return 0 // Прерываем выполнение функции
            } else if (limitMonthCount + previousTransfer + currentTransfer > limitMonth) {
                println("Операция отклонена -> Превышен лимит переводов за месяц !")
                return 0
            } else {
                limitSutkiCount = limitSutkiCount + currentTransfer
                limitMonthCount = limitMonthCount + currentTransfer
                itogComission = comissionMir
            }
        }

        if (typeCard == "Mastercard") {
            if ((limitSutkiCount  + previousTransfer + currentTransfer) > limitSutki) {
                println("Операция отклонена -> Превышен лимит переводов за сутки !")
                return 0 // Прерываем выполнение функции
            } else if (limitMonthCount  + previousTransfer + currentTransfer > limitMonth) {
                println("Операция отклонена -> Превышен лимит переводов за месяц !")
                return 0
            } else {
                limitSutkiCount = limitSutkiCount + currentTransfer
                limitMonthCount = limitMonthCount + currentTransfer
                if (limitSutkiCount > limitMastercard) {
                    val limitUp = limitSutkiCount - limitMastercard
                    itogComission = (limitUp * comissionMastercard).toInt() + comissionMastercard20
                } else {
                    itogComission = 0
                }
            }
        }

        if (typeCard == "Visa") {
            if ((limitSutkiCount  + previousTransfer + currentTransfer) > limitSutki) {
                println("Операция отклонена -> Превышен лимит переводов за сутки !")
                return 0 // Прерываем выполнение функции
            } else if ((limitMonthCount  + previousTransfer + currentTransfer) > limitMonth) {
                println("Операция отклонена -> Превышен лимит переводов за месяц !")
                return 0
            } else {
                limitSutkiCount = limitSutkiCount + currentTransfer
                limitMonthCount = limitMonthCount + currentTransfer

                itogComission = (currentTransfer * comissionVisa).toInt()

                if (itogComission < minComissionVisa) itogComission = minComissionVisa
            }
        }

        return itogComission

    }
    // --------------------

    // Проверка данных
    // --------------------
    // карта Мир
    val comission = calculationAlgorithmComission("Мир", 0, 20000)
    println("Комиссия за перевод = $comission руб.")

    // карта Mastercard
    val comission2 = calculationAlgorithmComission("Mastercard", 0, 80000)
    println("Комиссия за перевод = $comission2 руб.")
    val comission22 = calculationAlgorithmComission("Mastercard", 0, 150_000)
    println("Комиссия за перевод = $comission22 руб.")

    // карта Visa
    val comission3 = calculationAlgorithmComission("Visa", 0, 200_000)
    println("Комиссия за перевод = $comission3 руб.")
    // --------------------

}