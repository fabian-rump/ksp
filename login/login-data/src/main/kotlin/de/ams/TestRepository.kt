package de.ams

@Data
interface TestRepository {
    fun doTestStuff(): List<Login>
}