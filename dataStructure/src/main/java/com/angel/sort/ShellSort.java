package com.angel.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 希尔排序
 * 时间复杂度：O(nlogn)
 * 稳定性：不稳定
 * 链接地址：https://github.com/hustcc/JS-Sorting-Algorithm/blob/master/4.shellSort.md
 */
public class ShellSort implements IArraySort {

    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int gap = 1;
        while (gap < arr.length) {
            gap = gap * 3 + 1;
        }

        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3);
        }

        return arr;
    }

    /**
     * 希尔排序是把记录按下标的一定增量分组，
     * 对每组使用直接插入排序算法排序；随着增量逐渐减少，
     * 每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
     * @param arr
     */
    public static void shellSort(Integer arr[]){
        int i, j, r, tmp;
        // 划组排序
        for(r = arr.length / 2; r >= 1; r = r / 2) {
            for(i = r; i < arr.length; i++) {
                tmp = arr[i];
                j = i-r;
                // 一轮排序
                while(j>=0 && tmp < arr[j] ) {
                    arr[j+r] = arr[j];
                    j -= r;
                }
                arr[j+r] = tmp;
                List<Integer> value = new ArrayList<Integer>(arr.length);
                Collections.addAll(value,arr);
                System.out.println(value);
            }

        }
    }

    public static void main(String[] args)
    {
         Integer[] arr = {12,45,85,10,3,0};
         shellSort(arr);
    }
}
