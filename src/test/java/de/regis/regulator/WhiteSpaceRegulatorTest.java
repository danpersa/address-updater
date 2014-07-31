package de.regis.regulator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WhiteSpaceRegulatorTest {

    private static final String TEXT = "some text  some other text  and   another";

    private final WhiteSpaceRegulator regulator = new WhiteSpaceRegulator();

    @Test
    public void testRegulate() throws Exception {
        final String result = regulator.regulate(TEXT);
        assertThat(result, is("some text some other text and another"));
    }
}