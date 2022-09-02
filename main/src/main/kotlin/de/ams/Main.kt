package de.ams

import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

fun main() {
    startKoin {
        modules(login_domainModule, dashboard_domainModule)
    }

    val interactor : LoginInteractor by inject(LoginInteractor::class.java)
    val interactor2 : DashboardInteractor by inject(DashboardInteractor::class.java)
    interactor.doVeryImportantStuff()
    interactor2.doVeryImportantStuff()
    println("Test")
}