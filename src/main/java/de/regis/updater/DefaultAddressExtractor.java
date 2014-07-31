package de.regis.updater;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import de.regis.domain.Address;
import de.regis.domain.Company;
import de.regis.service.CityService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.index.memory.MemoryIndex;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.*;

/**
 * @author danix
 */
@Component
public class DefaultAddressExtractor implements AddressExtractor {

    private static final Version LUCENE_49 = Version.LUCENE_4_9;

    @Autowired
    private CityService cityService;

    @Nonnull
    @Override
    public List<Address> extractAddresses(@Nonnull String text, @Nonnull Company company) {
        checkNotNull(text);
        checkNotNull(company);
        Analyzer analyzer = new SimpleAnalyzer(LUCENE_49);
        // index the document and search for addresses

        MemoryIndex index = new MemoryIndex();
        index.addField("content", text, analyzer);

        QueryParser parser = new QueryParser(LUCENE_49, "content", analyzer);

        final ImmutableList.Builder<Address> builder = ImmutableList.builder();

        cityService.getCityNames().forEach((city) -> {
            try {
                // we use lucene to identify the cities for the page
                final float score = index.search(parser.parse(city));
                if (score > 0) {
                    // we found a city for the address
                    Pattern MY_PATTERN = Pattern.compile("([A-Za-z]+) ([A-Za-z0-9]+) ([A-Za-z0-9]+) (" + city + ")");
                    // we search for the address pattern
                    Matcher m = MY_PATTERN.matcher(text);
                    while (m.find()) {
                        // we create a new address
                        builder.add(new Address(m.group(1), m.group(2), m.group(3), m.group(4), company));
                    }
                }
            } catch (ParseException e) {
                throw new IllegalStateException(e);
            }
        });

        return builder.build();
    }
}
