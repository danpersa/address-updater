package de.regis.index;

import javax.annotation.Nonnull;

/**
 * @author danix
 */
public interface DocumentIndexer {

    void index(@Nonnull String htmlBody);
}
