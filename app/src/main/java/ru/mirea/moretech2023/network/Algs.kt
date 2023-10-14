package ru.mirea.moretech2023.network

import android.content.Context
import java.io.File
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.json.JSONArray
import java.io.Console
import java.io.IOException

class Algs {

    data class BankOffice(
        val pointName: String,
        val address: String,
        val latitude: String,
        val longitude: String,
        val metroStation: String,
        val officeType: String,
        val salePointFormat: String,
        val hasRko: String,
        val weekWorkHoursData: List<String>,
        val businessWorkHoursData: List<String>
    )

//    data class WorkHours(
//        val day: String,
//        val openingHour: String,
//        val closingHour: String
//    )

    fun readJSONFromAssets(context: Context, fileName: String): String? {
        try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            return String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun parseJsonArray(jsonString: String) {
        try {


            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)

                val salePointName = jsonObject.getString("salePointName")
                val address = jsonObject.getString("address")
                val status = jsonObject.getString("status")

                println("Sale Point Name: $salePointName")
                println("Address: $address")
                println("Status: $status")

                // Обработка массива 'openHours'
                val openHoursArray = jsonObject.getJSONArray("openHours")
                for (j in 0 until openHoursArray.length()) {
                    val openHoursObject = openHoursArray.getJSONObject(j)
                    val days = openHoursObject.getString("days")
                    val hours = openHoursObject.getString("hours")
                    println("Open Hours #$j: $days - $hours")
                }

                // Другие ключи и данные, которые вы хотите обработать

                println("-----")
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getOfficesList(context: Context): MutableList<BankOffice> {
        val result: MutableList<BankOffice> = mutableListOf()
        try {
            val jsonArray = JSONArray(readJSONFromAssets(context, "offices.json"))

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)

                val pointName = jsonObject.getString("salePointName")
                val address = jsonObject.getString("address")
                val latitude = jsonObject.getString("latitude")
                val longitude = jsonObject.getString("longitude")
                val metroStation = jsonObject.getString("metroStation")
                val officeType = jsonObject.getString("officeType")
                val salePointFormat = jsonObject.getString("salePointFormat")
                val hasRko = jsonObject.getString("rko")

                val weekWorkHoursData = jsonObject.getString("openHoursIndividual") // Физлица
                val businessWorkHoursData = jsonObject.getString("openHours") // Юрлица

                result.add(BankOffice(pointName, address, latitude, longitude, metroStation, officeType, salePointFormat, hasRko, weekWorkHoursData.split(",").map { it.trim() }.toList(), businessWorkHoursData.split(",").map { it.trim() }.toList()))

                // Обработка массива 'openHours'
//                val openHoursArray = jsonObject.getJSONArray("openHours")
//                for (j in 0 until openHoursArray.length()) {
//                    val openHoursObject = openHoursArray.getJSONObject(j)
//                    val days = openHoursObject.getString("days")
//                    val hours = openHoursObject.getString("hours")
//
//                }

                // Другие ключи и данные, которые вы хотите обработать


            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    constructor(context: Context){
////        val result = readJSONFromAssets(context, "offices.json")
//        val jsonstr = readJSONFromAssets(context, "offices.json")
////        val result = parseJsonArray(jsonstr.toString())
//        val office_list = getOfficesList(jsonstr.toString())
//        val result = office_list[0].address
//        println("GORA"+ result.toString())

    }
}