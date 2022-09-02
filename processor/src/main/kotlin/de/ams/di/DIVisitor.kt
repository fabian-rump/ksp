package de.ams.di

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.*
import de.ams.KoinType
import java.io.OutputStream

class DIVisitor(
    private val file: OutputStream,
    private val logger: KSPLogger,
    private val resolver: Resolver
) : KSVisitorVoid() {

    override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
        super.visitClassDeclaration(classDeclaration, data)

        val annotation: KSAnnotation = classDeclaration.annotations.first { it.shortName.asString() == "Domain" }
        val koinTypeArgument: KSValueArgument = annotation.arguments.first()
        val koinTypeValue = koinTypeArgument.value as KSType
        val koinType = enumValueOf<KoinType>(koinTypeValue.declaration.simpleName.asString())

        classDeclaration.toKoinDependency(koinType.koinName)
    }

    private fun KSClassDeclaration.toKoinDependency(koinName: String) {
        if (simpleName.asString().endsWith("Impl")) {
            file += "\t${koinName}<${simpleName.asString().toInterfaceName()}> { "
            file += "${simpleName.asString()}(${primaryConstructor?.parameters?.toKoinDependencies()})"
            file += " }\n"
        }
    }

    private fun String.toInterfaceName() = removeSuffix("Impl")

    private fun List<KSValueParameter>.toKoinDependencies(): String = joinToString {
        "get()"
    }
}
