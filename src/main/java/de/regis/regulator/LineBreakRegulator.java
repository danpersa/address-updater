package de.regis.regulator;

import com.google.common.base.CharMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author danix
 */
@Order(1)
@Component
class LineBreakRegulator implements Regulator {

    private static final Logger LOG = LoggerFactory.getLogger(LineBreakRegulator.class);

    private static final CharMatcher WHITESPACE_OR_SEPARATORS = CharMatcher.BREAKING_WHITESPACE.or(CharMatcher.anyOf(",;"));

    public String regulate(@Nonnull String text) {
        checkNotNull(text);
        LOG.info("start LineBreakRegulator");
        // remove commas, whitespace, line breaks from the body
        final String noBreaksHtmlBody = WHITESPACE_OR_SEPARATORS.replaceFrom(text, " ");
        return noBreaksHtmlBody;
    }
}
