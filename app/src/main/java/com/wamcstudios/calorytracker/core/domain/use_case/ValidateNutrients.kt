package com.wamcstudios.calorytracker.core.domain.use_case

import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.utils.UiText

class ValidateNutrients {
    operator fun invoke(
        carbsRatio: String,
        proteinsRatio: String,
        fatsRatio: String,
    ): ValidateNutrientsResult {

        val carbs = carbsRatio.toIntOrNull()
        val proteins = proteinsRatio.toIntOrNull()
        val fats = fatsRatio.toIntOrNull()


        if (carbs == null || proteins == null || fats == null) {
            return ValidateNutrientsResult.Error(message = UiText.StringResource(R.string.error_invalid_values))
        }

        if ((carbs + proteins + fats) != 100) {
            return ValidateNutrientsResult.Error(message = UiText.StringResource(R.string.msg_error_nutrients_total_percent))
        }

        return ValidateNutrientsResult.Success(
            carbsRatio = carbs / 100f,
            proteinsRatio = proteins / 100f,
            fatsRatio = fats / 100f
        )
    }

    sealed class ValidateNutrientsResult {
        data class Success(val carbsRatio: Float, val proteinsRatio: Float, val fatsRatio: Float) :
            ValidateNutrientsResult()

        data class Error(val message: UiText) : ValidateNutrientsResult()
    }
}