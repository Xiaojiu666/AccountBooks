package com.anno.annotation_compiler

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Mark a page can be route by router.
 *
 * @author Alex [Contact me.](mailto:zhilong.liu@aliyun.com)
 * @version 1.0
 * @since 16/8/15 下午9:29
 */
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class Route(
    /**
     * Path of route
     */
    val path: String,
    /**
     * Used to merger routes, the group name MUST BE USE THE COMMON WORDS !!!
     */
    val group: String = "",
    /**
     * Name of route, used to generate javadoc.
     */
    val name: String = "",
    /**
     * Extra data, can be set by user.
     * Ps. U should use the integer num sign the switch, by bits. 10001010101010
     */
    val extras: Int = Int.MIN_VALUE,
    /**
     * The priority of route.
     */
    val priority: Int = -1
)