package de.regis.repository;

import de.regis.domain.Address;
import de.regis.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import javax.annotation.Nonnull;

/**
 * @author danix
 */
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

    @Nonnull
    @Query("select a from #{#entityName} a where a.company = ?1 order by a.id desc")
    Page<Address> findAllByCompany(@Nonnull Company company, @Nonnull Pageable pageable);
}
