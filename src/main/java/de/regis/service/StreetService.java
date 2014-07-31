package de.regis.service;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * In memory service to get the streets for a city. In a real application this should be backed up by a database.
 *
 * @author danix
 */
@Service
public class StreetService {

    private static ImmutableMap<String, ImmutableList<String>> STREETS_FOR_CITY
            = ImmutableMap.<String, ImmutableList<String>>builder()
            .put("Berlin", ImmutableList.of("Zehdenicker", "Gubener", "Zionskirch", "Hansa"))
            .put("Frankfurt", ImmutableList.of(""))
            .build();


    @Nonnull
    public List<String> streetsForCity(@Nonnull String city) {
        checkNotNull(city);
        return Objects.firstNonNull(STREETS_FOR_CITY.get(city), ImmutableList.<String>of());
    }
}
