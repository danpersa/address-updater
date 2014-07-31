package de.regis.updater;

import de.regis.domain.Address;
import de.regis.domain.Company;
import de.regis.html.HtmlBodyExtractor;
import de.regis.html.PageRetriever;
import de.regis.regulator.Regulator;
import de.regis.service.AddressService;
import de.regis.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(DefaultAddressUpdater.class);

    @Autowired
    private PageRetriever pageRetriever;

    @Autowired
    private HtmlBodyExtractor htmlBodyExtractor;

    @Autowired
    private AddressExtractor addressExtractor;

    @Autowired
    private AddressService addressService;

    @Autowired
    private List<Regulator> regulators;

    @Override
    public void updateAddresses(@Nonnull Company company) {
        final String companyUrl = company.getUrl();

        LOG.info("Start AddressUpdater for url {}", companyUrl);
        final InputStream htmlPageStream = pageRetriever.retrieve(companyUrl);

        final String htmlBody = htmlBodyExtractor.extractBody(htmlPageStream);

        String result = htmlBody;
        for (Regulator regulator : regulators) {
            result = regulator.regulate(result);
        }
        final String regulatedHtmlBody = result;
        LOG.info("regulated HTML body for the url {}, is: {}", companyUrl, regulatedHtmlBody);

        final List<Address> addresses = addressExtractor.extractAddresses(regulatedHtmlBody, company);
        LOG.info("The list of addresses found for {} is: {}", companyUrl, addresses);

        // TODO instead of directly saving, verify if they already exists, and if they don't
        // TODO post a message to a JMS with the found addresses, so that
        // TODO a job for the email could get the message and send an email
        // TODO and another job for saving the addresses, would save them
        addressService.saveIfNotExisting(addresses);
    }
}
