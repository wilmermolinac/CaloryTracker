package com.wamcstudios.calorytracker.onboarding.domain.use_cases

import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.utils.UiText

class ValidateAge {
    operator fun invoke(age: String): ValidateAgeResult {

        /*val ageFilter = age.filter {
            // Filter only Digit Number
            it.isDigit()
        }*/

        if (age.isBlank()) {
            return ValidateAgeResult.IsInvalid(message = UiText.StringResource(R.string.error_age_cant_be_empty))
        }

        if (age.length > 3) {
            return ValidateAgeResult.IsInvalid(message = UiText.StringResource(R.string.error_age_invalid_range))
        }

        return ValidateAgeResult.IsValid(age)

    }

    sealed class ValidateAgeResult {
        data class IsValid(val data: String) : ValidateAgeResult()
        data class IsInvalid(val message: UiText) : ValidateAgeResult()
    }
}