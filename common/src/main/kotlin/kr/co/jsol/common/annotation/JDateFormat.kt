package kr.co.jsol.common.annotation

import org.springframework.format.annotation.DateTimeFormat

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
annotation class JDateFormat
