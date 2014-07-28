package de.regis.domain;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.*;

/**
 * @author danix
 */
public class Company {
    private final String url;

    public Company(@Nonnull String url) {
        this.url = checkNotNull(url);
    }

    public String getUrl() {
        return url;
    }
}
