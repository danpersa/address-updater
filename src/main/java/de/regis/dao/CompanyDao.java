package de.regis.dao;

import de.regis.domain.Company;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author danix
 */
public interface CompanyDao {

    @Nonnull
    List<Company> select(int start, int limit);

    int count();
}
