package chnp.common.timer;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chngzhen@outlook.com
 * @date 2019/1/8
 */
public class TaskTimer {

	static class Inner {
		static int one = 5;
		static final int two = 5;
		static final int three = new Integer(5);
	}

	public static void example1() {
		System.out.println(Inner.one);
		System.out.println(Inner.two);
		System.out.println(Inner.three);
	}

	public static void example2() {
		int j = 0;
		for (int i=0; i<100; i++) j = j++;
		System.out.println(j);
	}

	public static void example3() {
		int[] temp = new int[] {1,2,3,4};
		List array = Arrays.asList(temp);
		System.out.println(array.size());
	}

	public static Integer i=1;

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 0);
		System.out.println(calendar.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		System.out.println(calendar.get(Calendar.YEAR));
//		example3();
//		long now = System.currentTimeMillis();
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:00:00").format(new Date(now)));
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:00:00").format(new Date(now - 3600000)));
	}

	private static void inc(Integer num) {
		num += 1;
	}

}