package de.regis.service;

import de.regis.repository.CompanyRepository;
import de.regis.domain.Address;
import de.regis.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author danix
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Nonnull
    public Page<Company> getCompanies(int page, int size) {
        return companyRepository.findAll(new PageRequest(page, size));
    }

    public long totalCompanies() {
        return companyRepository.count();
    }

    public void addAddresses(@Nonnull Company company, @Nonnull List<Address> addresses) {
        // add the addresses as addresses for the company
    }
}
