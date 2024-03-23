package com.wamcstudios.calorytracker.core.domain.use_case

data class CoreUseCases(
    val filterOutDigits: FilterOutDigits,
    val filterOutDigitsDecimal: FilterOutDigitsDecimal, val validateNutrients: ValidateNutrients,
)