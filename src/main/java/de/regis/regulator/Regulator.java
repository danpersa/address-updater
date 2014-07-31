package de.regis.regulator;

import javax.annotation.Nonnull;

/**
 * @author danix
 */
public interface Regulator {

    @Nonnull
    String regulate(@Nonnull String string);
}
