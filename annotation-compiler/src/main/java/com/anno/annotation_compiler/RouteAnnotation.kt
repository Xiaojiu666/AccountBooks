package com.anno.annotation_compiler

@kotlin.annotation.Target(AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class RouteAnnotation( val name: String)
