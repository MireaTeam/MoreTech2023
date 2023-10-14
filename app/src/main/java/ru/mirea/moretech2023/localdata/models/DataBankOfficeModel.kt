package ru.mirea.moretech2023.localdata.models

data class DataBankOfficeModel(
    val pointName: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val metroStation: String,
    val officeType: String,
    val salePointFormat: String,
    val hasRko: String,
    val weekWorkHoursData: List<WorkHours>,
    val businessWorkHoursData: List<WorkHours>
)

data class WorkHours(
    val day: String,
    val openingHour: String,
    val closingHour: String
)
