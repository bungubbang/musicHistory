package com.fevi.music.top100.controller.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Minyumi on 2015. 12. 22..
 */
public class MusicApiControllerTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void ran() throws Exception {
        int i = randBetween(1, 12);
        System.out.println("i = " + i);
    }


    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}