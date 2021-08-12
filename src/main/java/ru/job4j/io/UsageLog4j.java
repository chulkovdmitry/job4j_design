package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Peter";
        int age = 35;
        boolean validate = true;
        char sex = 'M';
        float weight = 75.5f;
        byte iq = 95;
        double experience = 355.55;
        long height = 185;
        LOG.debug("User info name : {}, age : {}, validate : {}, male : {}"
                        + " weight : {}, iq : {}, experience : {}, height : {}", name, age, validate,
                sex, weight, iq, experience, height);
    }
}