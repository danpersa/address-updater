package de.regis.html;

import javax.annotation.Nonnull;
import java.io.InputStream;

/**
 * @author danix
 */
public interface HtmlBodyExtractor {

    @Nonnull
    String extractBody(@Nonnull InputStream htmlPageStream);
}
