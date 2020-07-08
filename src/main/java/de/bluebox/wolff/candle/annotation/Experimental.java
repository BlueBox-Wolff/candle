package de.bluebox.wolff.candle.annotation;

import javax.annotation.meta.TypeQualifierNickname;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Target({METHOD, PARAMETER, FIELD, TYPE})
@Retention(SOURCE)
@TypeQualifierNickname
public @interface Experimental {
}
