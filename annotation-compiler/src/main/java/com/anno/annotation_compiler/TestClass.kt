package com.anno.annotation_compiler

import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
@SupportedAnnotationTypes("com.anno.annotation_compiler.RouteAnnotation")
class TestClass : AbstractProcessor() {
    private var messager: Messager? = null
    @Synchronized
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        messager = processingEnv.messager
    }

    override fun process(set: Set<TypeElement?>, roundEnvironment: RoundEnvironment): Boolean {
        messager!!.printMessage(Diagnostic.Kind.NOTE, "Hello Word TestClass")
        return false
    }
}