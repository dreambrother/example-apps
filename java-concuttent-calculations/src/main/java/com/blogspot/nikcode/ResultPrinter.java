package com.blogspot.nikcode;

/**
 *
 * @author nik
 */
public class ResultPrinter {

    private long startTime;
    
    public void submitStart() {
        this.startTime = System.nanoTime();
    }
    
    public void submitFinish(long result) {
        final long endTime = System.nanoTime();
        System.out.println("Result: " + result);
        System.out.println("Time: " + (endTime - startTime));
    }
}
