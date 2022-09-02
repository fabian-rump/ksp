package de.ams

import de.ams.KoinType.FACTORY

@Domain(FACTORY)
class DashboardInteractorImpl : DashboardInteractor {

    override fun doVeryImportantStuff() {
        println("important stuff in dashboard")
    }
}