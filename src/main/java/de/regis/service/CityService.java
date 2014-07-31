package de.regis.service;

import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * In memory service for retrieving the cities. In a real application this should be backed up by a database.
 *
 * We consider all cities to be in Germany.
 *
 * @author danix
 */
@Service
public class CityService {

    private static final ImmutableList<String> CITIES = ImmutableList.of("Berlin", "Frankfurt");

    @Nonnull
    public List<String> getCityNames() {
        return CITIES;
    }
}
