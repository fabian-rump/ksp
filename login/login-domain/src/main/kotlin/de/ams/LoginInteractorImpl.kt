package de.ams

@Domain(KoinType.SINGLETON)
class LoginInteractorImpl : LoginInteractor {

    override fun doVeryImportantStuff() {
        println("important stuff in login")
    }
}