package de.regis.task;

import de.regis.domain.Company;
import de.regis.updater.AddressUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author danix
 */
@Component
public class AddressExtractorTask {

    private static final Logger LOG = LoggerFactory.getLogger(AddressExtractorTask.class);

    @Autowired
    private AddressUpdater addressUpdater;

    // @Async - commented in order to see the ordering in the logs
    public void run(@Nonnull List<Company> companies) {
        LOG.info("Start AddressExecutorTask as an async task");
        checkNotNull(companies).forEach((company) -> {
            LOG.info("Process {} company", company);
            addressUpdater.updateAddresses(company);
        });
    }
}
