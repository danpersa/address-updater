package de.regis.html;

import javax.annotation.Nonnull;
import java.io.InputStream;

/**
 * @author danix
 */
public interface PageRetriever {

    @Nonnull
    InputStream retrieve(@Nonnull String url);
}
