package ru.netology

fun main() {

    // Объявляем переменные
    // --------------------
    val limitSutki = 150_000
    val limitMonth = 600_000

    val limitMastercard = 75000 // с карты Mastercard комиссия не взимается, пока не превышен месячный лимит
    val comissionMastercard = 0.6 / 100 // 0,6%
    val comissionMastercard20 = 20 // 20 руб

    val comissionVisa = 0.75 / 100 // 0,75%
    val minComissionVisa = 35 // минимальная сумма комиссии 35 руб

    val comissionMir = 0 // с карты Мир комиссия не взимается
    // --------------------

    // Функция о превышении лимитов
    // --------------------
    fun limitOver(previousTransfer: Int, currentTransfer: Int): Boolean {
        if (previousTransfer + currentTransfer > limitMonth) {
            println("Операция отклонена -> Превышен лимит переводов за месяц!")
            return false
        }
        if (currentTransfer > limitSutki) {
            println("Операция отклонена -> Превышен лимит переводов за сутки!")
            return false
        }
        return true
    }

    // --------------------

    // --------------------
    fun calculationAlgorithmComission(typeCard: String = "Мир", previousTransfer: Int = 0, currentTransfer: Int = 0): Int {

        // Проверяем превышение лимитов
        if  (!limitOver(previousTransfer, currentTransfer)) {
            return 0
        }

        if (typeCard == "Мир") {
            return comissionMir

        } else if (typeCard == "Mastercard") {
            // Льготный порог превышен ранее
            if (previousTransfer > limitMastercard) {
                return (currentTransfer * comissionMastercard).toInt() + comissionMastercard20
            }
            // Льготный порог превышается текущим платежом
            else if (previousTransfer + currentTransfer > limitMastercard) {
                val limitUp = (previousTransfer + currentTransfer) - limitMastercard
                return (limitUp * comissionMastercard).toInt() + comissionMastercard20
            }
            // Льготный порог не превышается
            else {
                return 0
            }

        } else if (typeCard == "Visa") {
            var itogComission = (currentTransfer * comissionVisa).toInt()
            if (itogComission < minComissionVisa) itogComission = minComissionVisa
            return itogComission

        } else {
            print("Ошибка -> Карта в системе не найдена !")
            return 0
        }
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