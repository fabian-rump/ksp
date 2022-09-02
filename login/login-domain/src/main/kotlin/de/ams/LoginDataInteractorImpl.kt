package de.ams

import de.ams.KoinType.SINGLETON

@Domain(SINGLETON)
class LoginDataInteractorImpl(
    private val loginRepository: LoginRepository,
    private val login: Login,
    private val testRepository: TestRepository
) : LoginDataInteractor {

    override fun doLoginDataStuff() {
        loginRepository.doDataStuff()
    }
}