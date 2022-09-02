package de.ams

import org.koin.dsl.module

val login_domainModule = module {
	single<LoginDataInteractor> { LoginDataInteractorImpl(get(), get(), get()) }
	single<LoginInteractor> { LoginInteractorImpl() }
}
