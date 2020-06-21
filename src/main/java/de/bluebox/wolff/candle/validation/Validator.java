package de.bluebox.wolff.candle.validation;

import java.util.Optional;

@FunctionalInterface
public interface Validator<T> {
  Optional<String> validate(T t);
}
