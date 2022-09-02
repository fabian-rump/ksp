package de.ams.di

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import java.io.OutputStream
import kotlin.math.log

class DIProcessor(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation("de.ams.Domain").filterIsInstance<KSClassDeclaration>()
        if (!symbols.iterator().hasNext()) return emptyList()

        codeGenerator.createNewFile(
            dependencies = Dependencies(false, *resolver.getAllFiles().toList().toTypedArray()),
            packageName = "de.ams",
            fileName = "${resolver.moduleName()}KoinModule"
        ).apply {
            this += "package de.ams\n\n"
            this += "import org.koin.dsl.module\n\n"
            this += "val ${resolver.moduleName()}Module = module {\n"
            symbols.forEach { it.accept(DIVisitor(this, logger, resolver), Unit) }
            this += "}\n"
        }.close()

        return symbols.filterNot { it.validate() }.toList()
    }

    private fun Resolver.moduleName(): String {
        val moduleDescriptor = this::class.java
            .getDeclaredField("module")
            .apply { isAccessible = true }
            .get(this)
        val rawName = moduleDescriptor::class.java
            .getMethod("getName")
            .invoke(moduleDescriptor)
            .toString()
        return rawName.removeSurrounding("<", ">").replace("-","_")
    }
}