package com.marcelomnc.score10pinbowling.validator;

public class CommandLineArgsValidator implements ICommandLineArgsValidator {
    @Override
    public boolean isValid(String[] args) {
        boolean toRet = true;

        if (args.length != 1) {
            toRet = false;
        }

        return toRet;
    }
}
