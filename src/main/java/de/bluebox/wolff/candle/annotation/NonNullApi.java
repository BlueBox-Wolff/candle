package de.bluebox.wolff.candle.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;

@Target(PACKAGE)
@Retention(RUNTIME)
@Nonnull
@TypeQualifierDefault({METHOD, PARAMETER})
public @interface NonNullApi {}
