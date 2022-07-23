package com.learn.zip

import org.gradle.api.Plugin
import org.gradle.api.Project

// 参考资料:https://blog.csdn.net/u010982507/article/details/104875115
class ZipPlugin : Plugin<Project?> {

    override fun apply(target: Project?) {
        println("apply start")

        target!!.afterEvaluate { project ->
            println("apply start")
            var type = HashMap<String, Class<*>>()
            for (key in target.rootProject.childProjects.keys) {
                println("apply key: $key")
                val child = target.rootProject.childProjects[key]!!
                println("apply child: $child")
            }
        }
        target.task("hello") {
            println("apply child: ${it.name}")
        }
    }
}