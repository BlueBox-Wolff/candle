package de.bluebox.wolff.candle.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierNickname;
import javax.annotation.meta.When;

@Target({METHOD, PARAMETER, FIELD})
@Retention(RUNTIME)
@Nonnull(when = When.MAYBE)
@TypeQualifierNickname
public @interface Nullable {}
