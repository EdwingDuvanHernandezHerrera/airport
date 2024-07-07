package org.vuelosGlobales.helpers;

import java.util.Optional;

public interface Validator <T>{
    Optional<T> validate(String input);
}
