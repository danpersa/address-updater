package de.regis.repository;

import com.google.common.collect.ImmutableList;
import de.regis.RepositoryIntegrationTestConfig;
import de.regis.domain.Address;
import de.regis.domain.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RepositoryIntegrationTestConfig.class)
public class AddressRepositoryIntegrationTest {

    public static final String STREET1 = "Greifswalder";
    public static final String STREET2 = "Greifswalder";
    @Autowired
    private AddressRepository repository;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void testFindAllByCompany() throws Exception {

        final Company company = companyRepository.save(new Company("http://www.regis24.de/"));
        final Address address1 = new Address(STREET1, "122", "10405", "Berlin", company);
        final Address address2 = new Address(STREET2, "123", "10405", "Berlin", company);
        repository.save(address1);
        repository.save(address2);

        Page<Address> addresses = repository.findAllByCompany(company, new PageRequest(0, 10));
        assertThat(ImmutableList.copyOf(addresses.getContent()), is(ImmutableList.of(address2, address1)));
    }
}