package com.angel.multithreading.future;

import org.junit.AfterClass;
import org.junit.Test;

import java.security.cert.LDAPCertStoreParameters;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈明
 * @date 2021/4/29 11:24
 */
public class CompleteFutureDemo
{
	/**
	 * 异步有结果返回
	 */
	@Test
	public void demo1()
	{
		CompletableFuture future = CompletableFuture.supplyAsync(() -> {
			System.out.println("电饭煲开始做饭");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "白米饭";
		}).thenAccept(result -> {
			System.out.println("开始吃米饭" + result);
		});
		System.out.println("我先去搞点牛奶和鸡蛋");
		future.join();
	}
	
	/**
	 * 异步无结果返回
	 */
	@Test
	public void demo2()
	{
		CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(
				() ->
				{
					System.out.println("hello world");
					try
					{
						TimeUnit.SECONDS.sleep(5);
					} catch ( InterruptedException e )
					{
						e.printStackTrace();
					}
				}
		).thenRun(()-> System.out.println("end"));
		
		voidCompletableFuture.join();
	}
	
	@Test
	public void thenCompose()
	{
		CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "hello")
				.thenCompose(s -> CompletableFuture.supplyAsync(() -> s +" " +  "world"));
		System.out.println(stringCompletableFuture.join());
	}
	
	@Test
	public void thenCombine()
	{
		CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "Hello")
				.thenCombine(CompletableFuture.supplyAsync(() -> "world") , (s1 , s2) -> s1 + s2);
		System.out.println(stringCompletableFuture.join());
	}
}
