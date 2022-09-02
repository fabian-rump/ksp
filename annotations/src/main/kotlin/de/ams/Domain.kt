package de.ams

@Target(AnnotationTarget.CLASS)
annotation class Domain(val koinType: KoinType = KoinType.NONE)
