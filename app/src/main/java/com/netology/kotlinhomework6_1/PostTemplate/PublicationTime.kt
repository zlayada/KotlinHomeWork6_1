package com.netology.kotlinhomework6_1.PostTemplate



fun publicationTime(publishedAgoInMillis : Long) : String {

    val publishedAgoInSec : Int = publishedAgoInMillis.toInt() / 1000

    fun getYearsString(years : Int) : String = when (years) {
        1 -> "год"
        in 2..4 -> "$years года"
        5 -> "$years лет"
        else -> "несколько лет"
    }

    fun getMonthsString(months : Int) : String = when (months) {
        1 -> "месяц"
        in 2..4 -> "$months месяца"
        in 5..6 -> "$months месяцев"
        else -> "несколько месяцев"
    }

    fun getDaysString(days : Int) : String = when (days) {
        1 -> "1 день"
        in 2..4 -> "$days дня"
        in 5..6 -> "$days дней"
        in 7..13 -> "неделю"
        in 14..20 -> "2 недели"
        else -> "3 недели"
    }

    fun getHoursString(hours : Int) : String = when (hours) {
        1 -> "час"
        in 2..4 -> "$hours часа"
        in 5..6 -> "$hours часов"
        else -> "несколько часов"
    }

    fun getMinutesString(minutes : Int) : String = when (minutes) {
        1 -> "минуту"
        21 -> "$minutes минуту"
        in 2..4, in 22..24 -> "$minutes минуты"
        in 5..20, in 25..30 -> "$minutes минут"
        else -> "полчаса"
    }

    val years = publishedAgoInSec / 31_536_000 // 365 days in sec.
    val residueWithoutYears = publishedAgoInSec - years * 31_536_000
    val months = residueWithoutYears / 2_592_000 // 30 days in sec.
    val residueWithoutMonths = residueWithoutYears - months * 2_592_000
    val days = residueWithoutMonths / 86_400 // 1 day in sec.
    val residueWithoutDays = residueWithoutMonths - days * 86_400
    val hours = residueWithoutDays / 3_600 // 1 hour in sec.
    val residueWithoutHours = residueWithoutDays - hours * 3_600
    val minutes = residueWithoutHours / 60 // 1 min.

    if (years > 0) return "${getYearsString(years)} назад"
    if (months > 0) return "${getMonthsString(months)} назад"
    if (days > 0) return "${getDaysString(days)} назад"
    if (hours > 0) return "${getHoursString(hours)} назад"
    if (minutes > 0) return "${getMinutesString(minutes)} назад"
    return "менее минуты назад"
}