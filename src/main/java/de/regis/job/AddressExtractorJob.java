package de.regis.job;

import de.regis.service.AddressUpdater;
import de.regis.domain.Company;
import de.regis.service.CompanyService;
import de.regis.task.AddressExtractorTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author danix
 */
@Component
public class AddressExtractorJob {

    private static final int COMPANIES_PER_PAGE = 10;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private AddressUpdater addressUpdater;

    public void run() {
        int totalCompanies = companyService.totalCompanies();

        for (int i = 0; i < totalCompanies / COMPANIES_PER_PAGE; ++i) {
            List<Company> companies = companyService.getCompanies(i * COMPANIES_PER_PAGE, COMPANIES_PER_PAGE);
            executorService.submit(new AddressExtractorTask(companies, addressUpdater));
        }
    }

}
