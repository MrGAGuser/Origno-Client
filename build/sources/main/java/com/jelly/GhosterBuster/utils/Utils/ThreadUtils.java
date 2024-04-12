package com.jelly.GhosterBuster.utils.Utils;

public class ThreadUtils {
    public static void sleep(int time){
        try{
            Thread.sleep(time);
        } catch (InterruptedException ignored){
        }
    }
}
