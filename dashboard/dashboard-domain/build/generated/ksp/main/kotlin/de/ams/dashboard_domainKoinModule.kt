package de.ams

import org.koin.dsl.module

val dashboard_domainModule = module {
	factory<DashboardInteractor> { DashboardInteractorImpl() }
}
