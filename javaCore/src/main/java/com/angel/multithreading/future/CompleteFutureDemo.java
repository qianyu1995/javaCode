package com.angel.multithreading.future;

import org.junit.AfterClass;
import org.junit.Test;

import java.security.cert.LDAPCertStoreParameters;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author ����
 * @date 2021/4/29 11:24
 */
public class CompleteFutureDemo
{
	/**
	 * �첽�н������
	 */
	@Test
	public void demo1()
	{
		CompletableFuture future = CompletableFuture.supplyAsync(() -> {
			System.out.println("�緹�ҿ�ʼ����");
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "���׷�";
		}).thenAccept(result -> {
			System.out.println("��ʼ���׷�" + result);
		});
		System.out.println("����ȥ���ţ�̺ͼ���");
		future.join();
	}
	
	/**
	 * �첽�޽������
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
