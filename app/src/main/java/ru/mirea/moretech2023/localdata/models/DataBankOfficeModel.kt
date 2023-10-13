package ru.mirea.moretech2023.localdata.models

data class DataBankOfficeModel(
    val pointName: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val metroStation: String,
    val officeType: String,
    val salePointFormat: String,
    val weekWorkHoursData: List<WorkHours>
)

enum class Weekday(val dayShort: String) {
    MONDAY("пн"),
    TUESDAY("вт"),
    WEDNESDAY("ср"),
    THURSDAY("чт"),
    FRIDAY("пт"),
    SATURDAY("сб"),
    SUNDAY("вс")
}

data class WorkHours(
    val day: Weekday,
    val openingHour: String,
    val closingHour: String
)
