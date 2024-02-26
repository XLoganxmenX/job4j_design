package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 7;
        short shrt = 20;
        int integer = 87;
        long lng = 744998L;
        float flt = 6.4445f;
        double dble = 4.774446d;
        boolean bool = true;
        char chr = 'f';

        LOG.debug("byte = {}, short = {}, int = {}, long = {}, float = {}, double = {}, boolean = {}, char = {}",
                    b, shrt, integer, lng, flt, dble, bool, chr);
    }
}