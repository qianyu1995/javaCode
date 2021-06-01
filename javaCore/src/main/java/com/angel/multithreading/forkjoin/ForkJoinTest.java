package com.angel.multithreading.forkjoin;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author 陈明
 * @date 2021/5/24 16:40
 */
public class ForkJoinTest
{
    @Test
    public void demo() throws ExecutionException, InterruptedException
    {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinTask<String> submit = forkJoinPool.submit(
               () -> "hello"
        );
        System.out.println(submit.get());
        forkJoinPool.shutdown();
    }
    
    @Test
    public void demo2()
    {
        System.out.println(String.class.getName());
    }
}
