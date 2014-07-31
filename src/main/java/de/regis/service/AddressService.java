package de.regis.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import de.regis.domain.Address;
import de.regis.domain.Company;
import de.regis.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.*;

/**
 * @author danix
 */
@Service
public class AddressService {

    private static final Logger LOG = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    private AddressRepository addressRepository;

    public Optional<Address> latestAddressByCompany(@Nonnull Company company) {
        checkNotNull(company);
        LOG.info("latestAddressByCompany {}", company);
        final List<Address> addressesByCompany = addressRepository.findAllByCompany(company, new PageRequest(0, 1)).getContent();

        return Optional.ofNullable(Iterables.getFirst(addressesByCompany, null));
    }

    public void saveIfNotExisting(@Nonnull List<Address> addresses) {
        checkNotNull(addresses);
        LOG.info("Save a batch of addresses: " + addresses);
        // TODO check if the adddresses already exist
        addressRepository.save(addresses);
    }
}
