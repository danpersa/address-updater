package de.regis.regulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * @author danix
 */
@Order(2)
@Component
class StreetRegulator implements Regulator {

    private static final Logger LOG = LoggerFactory.getLogger(StreetRegulator.class);

    @Nonnull
    @Override
    public String regulate(@Nonnull String string) {
        LOG.info("Start StreetRegulator");
        return string.replaceAll("[Ss](tr)((aße)|(asse)|(.))", "");
    }
}
