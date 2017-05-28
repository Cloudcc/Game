package com.cloudcc.harmanworld.graphics;

/**
 * Created by Tobias on 28.05.2017.
 */
public class Screen {

    private int width, height;
    public int[] pixels;


    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

}
