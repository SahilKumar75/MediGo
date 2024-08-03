package com.example.musical.common.utils

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MedicineViewModel : ViewModel() {
    val prescribedMedicines = mutableStateListOf<PrescribedMedicine>()
}
