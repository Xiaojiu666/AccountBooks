package com.anno.annotation_compiler

import com.google.auto.service.AutoService
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import java.io.IOException
import java.security.Provider
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@SupportedAnnotationTypes("com.anno.annotation_compiler.RouteAnnotation")
@AutoService(Processor::class)
class MyClass : AbstractProcessor() {
    private var mFiler: Filer? = null
    var message: Messager? = null

    @Synchronized
    override fun init(processingEnvironment: ProcessingEnvironment) {
        super.init(processingEnvironment)
        mFiler = processingEnvironment.filer
        message = processingEnvironment.messager
        printMsg("MyClass init")
    }

    override fun getSupportedSourceVersion(): SourceVersion {

        return SourceVersion.latestSupported()
    }

    fun printMsg(msg: String) {
        message!!.printMessage(Diagnostic.Kind.OTHER, msg)
    }

    override fun getSupportedAnnotationTypes(): Set<String> {
        val types = LinkedHashSet<String>()
        types.add(RouteAnnotation::class.java.canonicalName)
        return types
    }

    override fun process(set: Set<TypeElement?>, roundEnvironment: RoundEnvironment): Boolean {
        printMsg("MyClass process")
        val nameMap = HashMap<String, String>()
        val annotationElements = roundEnvironment.getElementsAnnotatedWith(
            RouteAnnotation::class.java
        )
        for (element in annotationElements) {
            val annotation = element.getAnnotation(
                RouteAnnotation::class.java
            )
            val name = annotation.name
            nameMap[name] = element.simpleName.toString()
            //nameMap.put(element.getSimpleName().toString(), name);//MainActiviy-RouteName_MainActivity
        }

        //generate Java File
//        generateJavaFile(nameMap)
        return true
    }

    private fun generateJavaFile(nameMap: Map<String, String>) {
        printMsg("MyClass generateJavaFile")
        //generate constructor
        val constructorBuidler = MethodSpec.constructorBuilder()
            .addModifiers(Modifier.PUBLIC)
            .addStatement("routeMap = new \$T<>()", HashMap::class.java)
        for (key in nameMap.keys) {
            val name = nameMap[key]
            constructorBuidler.addStatement("routeMap.put(\"\$N\", \"\$N\")", key, name)
        }
        val constructorName = constructorBuidler.build()

        //generate getActivityRouteName method
        val routeName = MethodSpec.methodBuilder("getActivityName")
            .addModifiers(Modifier.PUBLIC)
            .returns(String::class.java)
            .addParameter(String::class.java, "routeName")
            .beginControlFlow("if (null != routeMap && !routeMap.isEmpty())")
            .addStatement("return (String)routeMap.get(routeName)")
            .endControlFlow()
            .addStatement("return \"\"")
            .build()

        //generate class
        val typeSpec = TypeSpec.classBuilder("AnnotationRoute\$Finder")
            .addModifiers(Modifier.PUBLIC)
            .addMethod(constructorName)
            .addMethod(routeName)
            .addSuperinterface(Provider::class.java)
            .addField(HashMap::class.java, "routeMap", Modifier.PRIVATE)
            .build()
        val javaFile =
            JavaFile.builder("com.example.juexingzhe.annotaioncompiletest", typeSpec).build()
        try {
            javaFile.writeTo(mFiler)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}