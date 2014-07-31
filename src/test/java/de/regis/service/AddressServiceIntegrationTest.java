package de.regis.service;

import de.regis.ServiceIntegrationTestConfig;
import de.regis.domain.Address;
import de.regis.domain.Company;
import de.regis.repository.AddressRepository;
import de.regis.repository.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceIntegrationTestConfig.class)
public class AddressServiceIntegrationTest {

    public static final String STREET1 = "Greifswalder Strasse";
    public static final String STREET2 = "Greifswalder Strasse";

    @Autowired
    private AddressRepository repository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressService addressService;

    @Test
    public void testLatestAddressByCompany() throws Exception {
        final Company company = companyRepository.save(new Company("http://www.regis24.de/"));
        final Address address1 = new Address(STREET1, "122", "10405", "Berlin", company);
        final Address address2 = new Address(STREET2, "123", "10405", "Berlin", company);
        repository.save(address1);
        repository.save(address2);

        final Optional<Address> addressOptional = addressService.latestAddressByCompany(company);
        assertThat(addressOptional.isPresent(), is(true));
        assertThat(addressOptional.get(), is(address2));
    }
}