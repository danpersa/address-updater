package de.regis.service;

import de.regis.dao.CompanyDao;
import de.regis.domain.Address;
import de.regis.domain.Company;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author danix
 */
@Service
public class CompanyService {

    private CompanyDao companyDao;

    @Nonnull
    public List<Company> getCompanies(int start, int limit) {
        return companyDao.select(start, limit);
    }

    public int totalCompanies() {
        return companyDao.count();
    }

    public void addAddresses(@Nonnull Company company, @Nonnull List<Address> addresses) {
        // add the addresses as addresses for the company
    }
}
