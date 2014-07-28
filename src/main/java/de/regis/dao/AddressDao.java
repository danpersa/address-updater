package de.regis.dao;

import de.regis.domain.Address;
import de.regis.domain.Company;

import javax.annotation.Nonnull;

/**
 * @author danix
 */
public interface AddressDao {

    @Nonnull
    Address lastAddress(@Nonnull Company company);
}
