package de.ams

@Data
interface LoginRepository {
    fun doDataStuff(): List<Login>
}