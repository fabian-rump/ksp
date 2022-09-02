package de.ams

@Data
class TestRepositoryImpl : TestRepository {

    override fun doTestStuff(): List<Login> {
        return listOf()
    }
}