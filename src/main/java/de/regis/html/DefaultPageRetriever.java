package de.regis.html;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author danix
 */
@Component
public class DefaultPageRetriever implements  PageRetriever {

    @Nonnull
    @Override
    public InputStream retrieve(@Nonnull String url) {
        try {
            return new URL(url).openStream();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid url: " + url, e);
        }
    }
}
