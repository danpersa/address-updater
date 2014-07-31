package de.regis.html;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultHtmlBodyExtractorTest {
    private final static String HTML =
            "<html>" +
                "<header><header>" +
                "<body>" +
                    "<div>Some Text</div>" +
                "</body>" +
            "</html>";


    private final DefaultHtmlBodyExtractor defaultHtmlBodyExtractor = new DefaultHtmlBodyExtractor();

    @Test
    public void testExtractBody() throws Exception {
        InputStream stream = new ByteArrayInputStream(HTML.getBytes(StandardCharsets.UTF_8));
        final String result = defaultHtmlBodyExtractor.extractBody(stream);
        assertThat(result, is("Some Text\n"));
    }
}