package com.distribike.modules

object ScopesName {
    // During a logged session, if user loggout : clear children jobs
    const val PROFILE_SESSION_SCOPE = "PROFILE_SESSION_SCOPE"

    // Exists as long as the app lives
    const val ALWAYS_ALIVE_APP_SCOPE = "ALWAYS_ALIVE_APP_SCOPE"
}