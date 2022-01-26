package com.bestbuy.utils;

import net.bytebuddy.utility.RandomString;

import java.util.Random;

/**
 * By Dimple Patel
 **/
public class TestUtils
{
    public static String getRandomValue(){
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt);
    }
    public static String getRandomText() {
        String randomTxt;
        RandomString random = new RandomString(12);
        return randomTxt = random.nextString();
    }


}
