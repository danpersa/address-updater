package de.regis.service;

import de.regis.domain.Address;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author danix
 */
public interface AddressExtractor {

    @Nonnull
    List<Address> extractAddresses(@Nonnull String url);
}
