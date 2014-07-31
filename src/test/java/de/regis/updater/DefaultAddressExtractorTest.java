package de.regis.updater;

import com.google.common.collect.ImmutableList;
import de.regis.domain.Address;
import de.regis.domain.Company;
import de.regis.service.CityService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAddressExtractorTest {

    private static final Company COMPANY = new Company("url");

    @Mock
    private CityService cityService;

    @InjectMocks
    private DefaultAddressExtractor addressExtractor = new DefaultAddressExtractor();

    @Test
    public void testExtractAddresses() throws Exception {
        when(cityService.getCityNames()).thenReturn(ImmutableList.of("Berlin"));
        final List<Address> addresses = addressExtractor.extractAddresses("Some Text Hansa 202 13088 Berlin Some Other Text", COMPANY);
        assertThat(addresses, hasItems(new Address("Hansa", "202", "13088", "Berlin", COMPANY)));
    }
}