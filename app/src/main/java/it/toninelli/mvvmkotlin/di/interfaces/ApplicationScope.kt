package it.toninelli.mvvmkotlin.di.interfaces

import java.lang.annotation.RetentionPolicy
import java.security.Policy
import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope {
}