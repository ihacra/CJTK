package com.hacra.cjtk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		int a = 0;
		do {
			a++;
			System.out.print(a);
		} while(true);
	}
	
    /**
     * 首先生成一个不重复的数集（0~9999），然后每次从这个集合中随机的取出一个数字作为生成的随机数（并且从集合中移除它）
     *
     * @param sz
     */
    public static int[] testC(int sz) {
        long startTime = System.currentTimeMillis(); //开始测试时间
        Random rd = new Random();
        int[] rds = new int[sz];//随机数数组
        List<Integer> lst = new ArrayList<Integer>();//存放有序数字集合
        int index = 0;//随机索引
        for (int i = 0; i < sz; i++) {
            lst.add(i);
        }
        for (int i = 0; i < sz; i++) {
            index = rd.nextInt(sz - i);
            rds[i] = lst.get(index);
            lst.remove(index);
        }
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("testC运行时间： " + (endTime - startTime) + "ms");
        return rds;
    }
}
