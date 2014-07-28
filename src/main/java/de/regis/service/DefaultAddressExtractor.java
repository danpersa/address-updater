package de.regis.service;

import com.google.common.collect.ImmutableList;
import de.regis.domain.Address;
import de.regis.index.DocumentIndexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author danix
 */
@Component
public class DefaultAddressExtractor implements AddressExtractor {

    @Autowired
    private DocumentIndexer documentIndexer;

    @Nonnull
    @Override
    public List<Address> extractAddresses(@Nonnull String htmlBody) {
        documentIndexer.index(htmlBody);

        // index the document and search for addresses

        return ImmutableList.of();
    }
}
