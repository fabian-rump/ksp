package de.ams.data

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*

class DataVisitor(private val logger: KSPLogger) : KSVisitorVoid() {

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        super.visitClassDeclaration(classDeclaration, data)

        if(!classDeclaration.simpleName.asString().isValidDataName())
            logger.error("Data Name falsch verwendet: ${classDeclaration.simpleName.asString()}")
    }

    private fun String.isValidDataName() = endsWith("Repository") || endsWith("RepositoryImpl")
}
