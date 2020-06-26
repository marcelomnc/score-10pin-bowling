package com.marcelomnc.score10pinbowling.validator;

public class SimpleCommandLineArgsValidator implements CommandLineArgsValidator {
    @Override
    public boolean isValid(String[] args) {
        boolean toRet = true;

        if (args.length != 1) {
            toRet = false;
        }

        return toRet;
    }
}
