package com.wamcstudios.calorytracker.core.domain.use_case

class FilterOutDigitsDecimal {


    operator fun invoke(text: String): String {
        // Inicializamos un contador para los puntos
        var pointCount = 0

        // Filtramos el texto
        return text.filter {
            when {
                // Si el caracter es un dígito, lo permitimos
                it.isDigit() -> true

                // Si el caracter es un punto y es la primera que encontramos, la permitimos
                it == '.' && pointCount == 0 -> {
                    // Incrementamos el contador de puntos
                    pointCount++
                    true
                }

                // Si el caracter no es un dígito ni el primer punto, no lo permitimos
                else -> false
            }
        }
    }


}