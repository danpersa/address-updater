package de.regis.job;

import de.regis.domain.Company;
import de.regis.updater.AddressUpdater;
import de.regis.service.CompanyService;
import de.regis.task.AddressExtractorTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author danix
 */
@Component
public class AddressExtractorJob {

    private static final Logger LOG = LoggerFactory.getLogger(AddressExtractorJob.class);
    private static final int COMPANIES_PER_PAGE = 10;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AddressExtractorTask addressExtractorTask;

    public void run() {
        final long totalCompanies = companyService.totalCompanies();

        LOG.info("Start AddressExtractorJob for {} companies", totalCompanies);

        for (int i = 0; i <= totalCompanies / COMPANIES_PER_PAGE; ++i) {
            final Page<Company> companiesPage = companyService.getCompanies(i, COMPANIES_PER_PAGE);
            final List<Company> companies = companiesPage.getContent();
            LOG.info("Batch of companies: {}", companies);
            addressExtractorTask.run(companies);
        }
    }
}
