package de.regis.repository;

import de.regis.domain.Company;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author danix
 */
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
}
