package de.regis.updater;

import de.regis.domain.Address;
import de.regis.domain.Company;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author danix
 */
public interface AddressExtractor {

    @Nonnull
    List<Address> extractAddresses(@Nonnull String text, Company company);
}
