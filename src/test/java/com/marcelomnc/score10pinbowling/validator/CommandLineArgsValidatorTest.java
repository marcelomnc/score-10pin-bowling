package com.marcelomnc.score10pinbowling.validator;

import org.junit.Assert;
import org.junit.Test;

public class CommandLineArgsValidatorTest {
    private final ICommandLineArgsValidator toTest = new CommandLineArgsValidator();

    @Test
    public void testInvalidOnEmptyArgs() {
        final String[] args = new String[0];
        Assert.assertFalse(this.toTest.isValid(args));
    }

    @Test
    public void testInvalidOnMoreThanOneArg() {
        final String[] args = new String[]{"arg1", "arg2"};
        Assert.assertFalse(this.toTest.isValid(args));
    }

    @Test
    public void testValidOnOneArg() {
        final String[] args = new String[]{"arg1"};
        Assert.assertTrue(this.toTest.isValid(args));
    }
}