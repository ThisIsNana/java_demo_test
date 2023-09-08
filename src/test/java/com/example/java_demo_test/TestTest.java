package com.example.java_demo_test;

import org.junit.jupiter.api.Test;

public class TestTest {

//	@Test
//	public void TestTestTest() {
//		
//		String str = "a,b,c,d,e,f,g";
//		String newStr = "";
//		String[] strList = str.split(",");
//		for(String strr : strList) {
//			newStr = strr + 1;
//			System.out.println(newStr);
//		}
//		
//	}
	
	@Test
	public void TestTestTest() {
		int rows = 5;

        for (int i = 1; i <= rows; i++) {
            // 印出每行的空格
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }

            // 印出每行的星號
            for (int k = 1; k <= i; k++) {
                System.out.print("*");
            }

            // 換行
            System.out.println();
        }
	}
}
