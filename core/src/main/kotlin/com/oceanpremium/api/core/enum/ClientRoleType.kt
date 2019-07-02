package com.oceanpremium.api.core.enum

import com.google.gson.annotations.SerializedName

enum class ClientRoleType(val type: String) {
    @SerializedName("Charter Broker / Manager") CHARTER_BROKER_OR_MANAGER("Charter Broker / Manager"),
    @SerializedName("Captain / Crew") CAPTAIN_OR_CREW("Captain / Crew"),
    @SerializedName("Other") OTHER("Other")
}
