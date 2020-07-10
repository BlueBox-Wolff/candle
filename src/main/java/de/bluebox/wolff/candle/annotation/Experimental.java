package de.bluebox.wolff.candle.annotation;

import javax.annotation.meta.TypeQualifierNickname;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Indicates that the mentioned class or method is in an experimental state.
 *
 * @author Jerome Wolff
 * @since 1.0.0
 */
@Documented
@Target({METHOD, TYPE})
@Retention(CLASS)
@TypeQualifierNickname
public @interface Experimental {}
