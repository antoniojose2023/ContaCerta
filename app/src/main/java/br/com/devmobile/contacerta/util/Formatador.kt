package br.com.devmobile.contacerta.util

import java.text.NumberFormat
import java.util.Locale


val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

fun Double.toReal(): String = formatador.format( this )

fun String.toValorDouble(): Double{
    val textoLimpo = this.replace(Regex("""[^0-9.,-]"""), "")
    if (textoLimpo.isEmpty()) return 0.0

    val normalizado = if (textoLimpo.contains(",")) {
                              textoLimpo.replace(".", "").replace(",", ".")
                      } else {
                             textoLimpo
                      }

        return normalizado.toDoubleOrNull() ?: 0.0
    }
