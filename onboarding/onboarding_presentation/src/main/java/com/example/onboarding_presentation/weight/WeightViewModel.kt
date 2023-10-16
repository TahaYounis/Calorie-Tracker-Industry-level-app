package com.example.onboarding_presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.Preferences
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {

    var weight by mutableStateOf("80.0")
        private set

    // channel used for send one time event to our UI
    // to send UiEvent from viewModel to our composable for example if you want to navigate or show snackBar or show validation
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightEnter(weight:String){
        if(weight.length <= 5){
            this.weight = weight
        }
    }

    fun onNextClick(){
        viewModelScope.launch {
            val weightNumber = weight.toFloatOrNull() ?: kotlin.run {
                // if age is null
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(com.example.core.R.string.error_weight_cant_be_empty)
                    )
                )
                return@launch // return after send the event
            }
            // if ageNumber not null
            preferences.saveWeight(weightNumber)
            _uiEvent.send(UiEvent.Success)
        }
    }
}