package de.ams

@Data
interface DashboardRepository {
    fun doDashboardStuff(): List<Dashboard>
}