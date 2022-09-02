package de.ams.general

import com.google.devtools.ksp.*
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import kotlin.math.log

class GeneralProcessor(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getAllFiles().toList().forEach {
            it.declarations.toList().forEach {declaration ->
                if(declaration.isOpen()) {
                    // is interface
                    declaration.accept(GeneralVisitor(logger, resolver), Unit)
                }
            }
        }

        return listOf()
    }
}