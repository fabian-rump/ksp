package de.ams.general

import com.google.devtools.ksp.getClassDeclarationByName
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*

class GeneralVisitor(
    private val logger: KSPLogger,
    private val resolver: Resolver
) : KSVisitorVoid() {

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        super.visitClassDeclaration(classDeclaration, data)
        classDeclaration.verifyInterfaceAnnotation()
        classDeclaration.verifyImplementationExistence()
        classDeclaration.verifyInterfaceImplementationAnnotation()
    }

    private fun KSClassDeclaration.verifyInterfaceImplementationAnnotation() {
        resolver.getClassDeclarationByName("${qualifiedName?.asString()}$IMPL_SUFFIX")?.let {
            if (!it.annotations.hasAnnotation())
                logger.error("$MISSING_INTERFACE_IMPL_ANNOTATION_MESSAGE ${simpleName.asString()}$IMPL_SUFFIX")
        } ?: logger.error("$INTERFACE_IMPL_NOT_FOUND_MESSAGE ${simpleName.asString()}$IMPL_SUFFIX")
    }

    private fun KSClassDeclaration.verifyImplementationExistence() {
        if (!resolver.doesTheImplementationExist(simpleName.asString()))
            logger.error("$MISSING_IMPL_WITH_INTERFACE_MESSAGE ${simpleName.asString()}")
    }

    private fun KSClassDeclaration.verifyInterfaceAnnotation() {
        if (!annotations.hasAnnotation()) logger.error("$MISSING_INTERFACE_ANNOTATION_MESSAGE ${simpleName.asString()}")
    }

    private fun Sequence<KSAnnotation>.hasAnnotation() = toList().isNotEmpty()

    private fun Resolver.doesTheImplementationExist(name: String) = getAllFiles().toList().any {
        it.fileName == name.plus("$IMPL_SUFFIX$KT_SUFFIX")
    }

    private companion object {
        private const val IMPL_SUFFIX = "Impl"
        private const val KT_SUFFIX = ".kt"
        private const val MISSING_INTERFACE_ANNOTATION_MESSAGE = "Fehlende Annotation bei Interface:"
        private const val MISSING_INTERFACE_IMPL_ANNOTATION_MESSAGE = "Fehlende Annotation bei Implementierung:"
        private const val INTERFACE_IMPL_NOT_FOUND_MESSAGE = "Kann nicht gefunden werden:"
        private const val MISSING_IMPL_WITH_INTERFACE_MESSAGE = "Interface ohne Implementierung:"
    }
}
