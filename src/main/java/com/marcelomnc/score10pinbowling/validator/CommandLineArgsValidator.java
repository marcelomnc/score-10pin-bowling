package com.marcelomnc.score10pinbowling.validator;

public class CommandLineArgsValidator implements ICommandLineArgsValidator {
    @Override
    public boolean isValid(String[] args) {
        //The application expects one and only one argument (the input file path)
        return args.length == 1;
    }
}
