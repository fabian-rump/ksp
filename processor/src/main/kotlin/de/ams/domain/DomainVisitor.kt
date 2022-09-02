package de.ams.domain

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*

class DomainVisitor(private val logger: KSPLogger) : KSVisitorVoid() {

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        super.visitClassDeclaration(classDeclaration, data)

        if(!classDeclaration.simpleName.asString().isValidDomainName())
            logger.error("Domain Name falsch verwendet: ${classDeclaration.simpleName.asString()}")
    }

    private fun String.isValidDomainName() = endsWith("Interactor") || endsWith("InteractorImpl")
}
