package de.regis.regulator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LineBreakRegulatorTest {

    private static final String TEXT = "some text\n some other text \n and another";

    private final LineBreakRegulator regulator = new LineBreakRegulator();

    @Test
    public void testRegulate() throws Exception {

        final String result = regulator.regulate(TEXT);
        assertThat(result, is("some text  some other text   and another"));
    }
}