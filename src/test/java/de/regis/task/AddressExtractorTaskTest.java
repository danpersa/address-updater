package de.regis.task;

import com.google.common.collect.ImmutableList;
import de.regis.domain.Company;
import de.regis.updater.AddressUpdater;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddressExtractorTaskTest {

    private static final Company FIRST = new Company("first");
    private static final Company SECOND = new Company("second");
    private static final ImmutableList<Company> COMPANIES = ImmutableList.of(FIRST, SECOND);

    @Mock
    private AddressUpdater addressUpdater;

    @InjectMocks
    private final AddressExtractorTask addressExtractorTask = new AddressExtractorTask();

    @Test
    public void testRun() throws Exception {
        addressExtractorTask.run(COMPANIES);
        verify(addressUpdater).updateAddresses(FIRST);
        verify(addressUpdater).updateAddresses(SECOND);
    }
}