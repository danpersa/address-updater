package de.regis.task;

import de.regis.service.AddressExtractor;
import de.regis.domain.Address;
import de.regis.domain.Company;
import de.regis.service.AddressUpdater;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.Callable;

import static com.google.common.base.Preconditions.*;

/**
 * @author danix
 */
public class AddressExtractorTask implements Runnable {

    private final List<Company> companies;

    private final AddressUpdater addressUpdater;

    public AddressExtractorTask(@Nonnull List<Company> companies, @Nonnull AddressUpdater addressUpdater) {
        this.companies = checkNotNull(companies);
        this.addressUpdater = checkNotNull(addressUpdater);
    }

    @Override
    public void run() {
        companies.forEach(addressUpdater::updateAddresses);
    }
}
