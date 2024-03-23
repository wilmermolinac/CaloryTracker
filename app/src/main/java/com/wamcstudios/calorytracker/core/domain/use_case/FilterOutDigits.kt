package com.wamcstudios.calorytracker.core.domain.use_case

class FilterOutDigits {

    operator fun invoke(text:String):String{
        // Filter only Digit Number
        return text.filter {
            it.isDigit()
        }
    }
}