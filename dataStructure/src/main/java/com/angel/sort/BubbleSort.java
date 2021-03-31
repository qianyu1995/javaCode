package com.angel.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * <p>
 * 稳定性：稳定()
 * <p>
 * 时间复杂度：O(n2)
 * <p>
 * 假定在待排序的记录序列中，存在多个具有相同的关键字的记录，
 * 若经过排序，这些记录的相对次序保持不变，即在原序列中，r[i]=r[j]，且r[i]在r[j]之前，
 * 而在排序后的序列中，r[i]仍在r[j]之前，则称这种排序算法是稳定的；否则称为不稳定的。
 * <p>
 * 链接地址：https://github.com/hustcc/JS-Sorting-Algorithm/blob/master/1.bubbleSort.md
 */
public class BubbleSort implements IArraySort
{
	
	@Override
	public int[] sort(int[] sourceArray) throws Exception
	{
		// 对 arr 进行拷贝，不改变参数内容
		int[] arr = Arrays.copyOf(sourceArray , sourceArray.length);
		
		for ( int i = 1; i < arr.length; i++ )
		{
			// 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
			boolean flag = true;
			
			for ( int j = 0; j < arr.length - i; j++ )
			{
				if ( arr[j] > arr[j + 1] )
				{
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
					flag = false;
				}
			}
			
			if ( flag )
			{
				break;
			}
		}
		return arr;
	}
	
	/**
	 * 鸡尾酒排序算法
	 * <p>
	 * 鸡尾酒算法：就是把一组数字，分别向前和向后将每个最大（最小）的数字分别往前后推。每一次推的结果一定是，最大（最小）的一定会在最前或者最后。
	 */
	//示例入参数组; src = {5,2,4,1,3}
	public int[] cocktail_sort(int[] src)
	{
		long timeS = System.currentTimeMillis();
		//该算法是只需要向左或右推数组长度的一半次数就够了
		for ( int i = 0; i < src.length / 2; i++ )
		{
			//第一个for循环是将数组中最大的数，向最后推。结果最大的数总会在最右边
			//例如上面的数组
			//第一次：5因为最大，所以在执行下面循环之后，一定位于数组最后面。（以此类推，第二次，是4最大，会位于5的前面）
			for ( int j = i; j < src.length - i - 1; j++ )
			{
				if ( src[j] > src[j + 1] )
				{
					int temp = src[j];
					src[j] = src[j + 1];
					src[j + 1] = temp;
				}
				System.out.println("swap biggest number : " + Arrays.toString(src));
			}
			//第二个循环是将数组中最小的数，往最前面推。结果最小的数总会在最右边
			//例如上面的数组
			//第一次往右，1是最小，循环执行完毕后，1一定位于数组最前面。（类似地，第二次就会把第二小的数字2，向左推到1的后面）
			for ( int j = src.length - 1 - (i + 1); j >= i; j-- )
			{
				if ( src[j] > src[j + 1] )
				{
					int temp = src[j];
					src[j] = src[j + 1];
					src[j + 1] = temp;
				}
				System.out.println("swap smallest number : " + Arrays.toString(src));
			}
			//当执行完一轮循环之后，向右和向左推的数就不会再参与下一轮的循环了，不然就浪费资源了。
		}
		long timeE = System.currentTimeMillis();
		System.out.println("take time = " + (timeE - timeS));
		System.out.println("final result : " + Arrays.toString(src));
		return src;
	}
	
	
	public static void main(String[] args)
	{
	}
}
