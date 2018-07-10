package com.lv.text;

import java.util.ArrayList;
import java.util.List;

public class Text {

	public static void main(String[] args) {

		List<String> lists = new ArrayList<>();
		/*lists.add("aa");
		lists.add("bb");
		lists.add("cc");
		lists.add("dd");*/
		
		/*String[] strs = lists.toArray(new String[10]);*/
		Object[] strs = lists.toArray();
		for (int i = 0; i < strs.length; i++) {
			System.out.println("===" + strs[i]);
			
		}
		
		
		
		int i = strs.length;
		
		/*paramsList.toArray()*/
		System.out.println("strs : " + strs);
		System.out.println("length : " + i);
	}

}
