package com.loginid.cryptodid.presentation.home.vc.VCViewModel

import com.loginid.cryptodid.utils.Status
import java.util.*

data class VCEnteryState(
    val experationDate: Date? = null,
    val issuerName: String="Linquit",
    val VCType: String = "Persenal document",
    val VCTitle: String = "Age",
    val VCContentOverview: String="Age > 18",
    val VCAttribute: Int = 0,
    //val VCId: String,
    val status: Status=Status.NO_ACTION
) {
}