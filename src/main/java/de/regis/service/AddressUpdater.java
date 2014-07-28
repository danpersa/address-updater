package de.regis.service;

import de.regis.domain.Company;

import javax.annotation.Nonnull;

/**
 * @author danix
 */
public interface AddressUpdater {

    void updateAddresses(@Nonnull Company company);
}
