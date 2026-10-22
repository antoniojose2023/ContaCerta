package br.com.devmobile.contacerta.util

import java.text.NumberFormat
import java.util.Locale


val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

fun Double.toReal(): String = formatador.format( this )