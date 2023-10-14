@file:Suppress("SpellCheckingInspection")

package ru.mirea.moretech2023.network.models

enum class ServiceTypeByClient {
    FOR_BUSINESS,
    FOR_INDIVIDUALS
}

data class VtbService(
    val type: ServiceTypeByClient,
    val id: Int,
    val name: String,
)

object VtbServices {
    val listOfServices = listOf(
        VtbService(
            ServiceTypeByClient.FOR_INDIVIDUALS,
            1,
            "Снять или внести наличные"
        ),
        VtbService(
            ServiceTypeByClient.FOR_INDIVIDUALS,
            2,
            "Открыть счёт"
        ),
        VtbService(
            ServiceTypeByClient.FOR_INDIVIDUALS,
            3,
            "Выпустить новую кредитную или дебетовую карту"
        ),
        VtbService(
            ServiceTypeByClient.FOR_INDIVIDUALS,
            3,
            "Получить справку или выписку"
        ),
        VtbService(
            ServiceTypeByClient.FOR_INDIVIDUALS,
            4,
            "Оформить ипотеку"
        ),
        VtbService(
            ServiceTypeByClient.FOR_INDIVIDUALS,
            5,
            "Страхование"
        ),
        VtbService(
            ServiceTypeByClient.FOR_BUSINESS,
            10,
            "Открытие и обслуживание расчетного счета"
        ),
        VtbService(
            ServiceTypeByClient.FOR_BUSINESS,
            11,
            "Формирование и отправка платежных поручений"
        ),
        VtbService(
            ServiceTypeByClient.FOR_BUSINESS,
            12,
            "Кассовое обслуживание"
        ),
        VtbService(
            ServiceTypeByClient.FOR_BUSINESS,
            13,
            "Выпуск корпоративных карт"
        ),
    )
}