package com.aura.portfolio.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double.toPrice(): String = DecimalFormat("#,###.##", DecimalFormatSymbols(Locale.US)).format(this)