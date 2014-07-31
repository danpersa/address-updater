package de.regis.job;

import com.google.common.collect.ImmutableList;
import de.regis.domain.Company;
import de.regis.service.CompanyService;
import de.regis.task.AddressExtractorTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressExtractorJobTest {

    public static final List<Company> COMPANIES_FIRST_BATCH = companies(10);
    public static final List<Company> COMPANIES_SECOND_BATCH = companies(5);
    @Mock
    private CompanyService companyService;

    @Mock
    private AddressExtractorTask addressExtractorTask;

    @InjectMocks
    private final AddressExtractorJob addressExtractorJob = new AddressExtractorJob();

    @Test
    public void testRun() throws Exception {
        // given
        when(companyService.totalCompanies()).thenReturn(15l);
        when(companyService.getCompanies(0, 10)).thenReturn(new PageImpl<>(COMPANIES_FIRST_BATCH));
        when(companyService.getCompanies(1, 10)).thenReturn(new PageImpl<>(COMPANIES_SECOND_BATCH));
        // when
        addressExtractorJob.run();
        // then
        verify(companyService).totalCompanies();
        verify(companyService).getCompanies(0, 10);
        verify(companyService).getCompanies(1, 10);
        verify(addressExtractorTask).run(COMPANIES_FIRST_BATCH);
        verify(addressExtractorTask).run(COMPANIES_SECOND_BATCH);
    }

    private static List<Company> companies(Integer numberOfCompanies) {
        final ImmutableList.Builder<Company> builder = ImmutableList.<Company>builder();
        IntStream.rangeClosed(0, numberOfCompanies)
                .forEach((x) -> {
                    Company company = new Company("http://www.yahoo.com");
                    builder.add(company);
                });
        return builder.build();
    }
}