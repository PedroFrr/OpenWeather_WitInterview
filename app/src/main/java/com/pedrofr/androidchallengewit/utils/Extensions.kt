package com.pedrofr.androidchallengewit.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    view?.let { activity?.toast(message, length) }
}

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

/**
 * Helper functions for the View layer of the app.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Timestamp to date helper functions
 */
//Converts timestamp to String day (SUN, MON, ......)
//Not using java.time because it requires API 26
fun Long.unixTimestampToDayOfWeek(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this*1000

    val outputDateFormat = SimpleDateFormat("EEE", Locale.ENGLISH)
    return outputDateFormat.format(calendar.time)

}

fun Long.unixTimestampToDayOfMonth(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this*1000

    val outputDateFormat = SimpleDateFormat("d", Locale.ENGLISH)
    return outputDateFormat.format(calendar.time)

}


