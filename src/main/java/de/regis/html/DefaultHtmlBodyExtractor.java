package de.regis.html;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author danix
 */
@Component
public class DefaultHtmlBodyExtractor implements HtmlBodyExtractor {

    @Nonnull
    @Override
    public String extractBody(@Nonnull InputStream htmlPageStream) {
        BodyContentHandler handler = new BodyContentHandler(100000);

        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        Parser parser = new HtmlParser();

        try {
            parser.parse(htmlPageStream, handler, metadata, parseContext);
        } catch (IOException | SAXException | TikaException e) {
            throw new IllegalStateException("Can't parse the html page");
        }


        return handler.toString();
    }
}
