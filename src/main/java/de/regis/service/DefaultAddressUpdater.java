package de.regis.service;

import de.regis.domain.Address;
import de.regis.domain.Company;
import de.regis.html.HtmlBodyExtractor;
import de.regis.html.PageRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.util.List;

/**
 * @author danix
 */
@Component
public class DefaultAddressUpdater implements AddressUpdater {

    @Autowired
    private PageRetriever pageRetriever;

    @Autowired
    private HtmlBodyExtractor htmlBodyExtractor;

    @Autowired
    private AddressExtractor addressExtractor;

    @Autowired
    private CompanyService companyService;


    @Override
    public void updateAddresses(@Nonnull Company company) {
        final String companyUrl = company.getUrl();

        final InputStream htmlPageStream = pageRetriever.retrieve(companyUrl);

        final String htmlBody = htmlBodyExtractor.extractBody(htmlPageStream);

        final List<Address> addresses = addressExtractor.extractAddresses(htmlBody);

        companyService.addAddresses(company, addresses);
    }
}
