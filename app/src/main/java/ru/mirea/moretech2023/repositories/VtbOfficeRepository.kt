package ru.mirea.moretech2023.repositories

import ru.mirea.moretech2023.localdata.models.WorkHours

data class VtbOfficeRepository(
    val imageUrl: String,
    val pointName: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val metroStation: String?,
    val officeType: String?,
    val salePointFormat: String?,
    val hasRko: Boolean,
    val weekWorkHoursData: List<WorkHours>,
    val businessWorkHoursData: List<WorkHours>
)

object VtbOfficeRepositoryDataSource {
    val listOfVtbOffices = listOf<VtbOfficeRepository>(
        VtbOfficeRepository(
            "https://avatars.mds.yandex.net/get-altay/9284964/2a000001898e9e7b211b16848856bfe3f82c/XXXL",
            "ДО «Солнечногорский» Филиала № 7701 Банка ВТБ (ПАО)",
            "141506, Московская область, г. Солнечногорск, ул. Красная, д. 60",
            "56.184479",
            "36.984314",
            null,
            "Да (Зона Привилегия)",
            "Универсальный",
            true,
            listOf(WorkHours("Пн", "10:00", "19:00")),
            listOf(WorkHours("Пн", "8:00", "18:00"))
        )
    )
}