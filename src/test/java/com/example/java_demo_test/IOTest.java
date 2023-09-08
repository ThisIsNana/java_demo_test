package com.example.java_demo_test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.apache.coyote.http11.filters.BufferedInputFilter;
import org.junit.jupiter.api.Test;

public class IOTest {

//	byte[] buffer = new byte[512];//�إߤ@�ӥe512�Ŷ���byte
	@Test
	public void fileOutputStreamTest() {
		//這樣寫就會自動close，不需再寫fos.close();      //參數為檔名 或 路徑+檔名, 是否append附加在後面(不寫為false)                
		try(FileOutputStream fos = new FileOutputStream("0705檔案.txt", true)) { 
			String str = "Helloooooo 嗨!@#$%^&*("; //注意檔案編碼要為UTF-8
			fos.write(str.getBytes());
			fos.write("70".getBytes()); //ASCII
			System.out.println("檔案寫出成功"); //檔案會出現在src根目錄下
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fileOutputStreamTest4() {    
		//在try中，需要close()多個，用(";")來分割
		try(FileOutputStream fos = new FileOutputStream("0705檔案.txt", true);
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) { 
																//讀取時指定的編碼 ↙兩種寫法皆可
//			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF_8");
			String str = "byeeeeee 掰!AAA@@@"; //注意檔案編碼要為UTF-8
			osw.append(str);
			System.out.println("檔案寫出成功"); //檔案會出現在src根目錄下
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void fileInputStreamText() {
		try (FileInputStream fis = new FileInputStream("F:\\JAVA\\kirby.png");
				FileOutputStream fos = new FileOutputStream("kirby_BB.png")){
			
			//每次讀取多少byte
			byte[] buffer = new byte[512];
			
			int count = 0;
			//讀到檔案結束回傳-1
			while(fis.available() > 0) {
				fis.read(buffer); //從fis讀取512bytes(讀出0-511) 並存入buffer此位元陣列中 
				fos.write(buffer); //將buffer陣列的位元寫入到fos中(寫入0-511)
				count++;
				System.out.println(count);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Test
	public void bufferFileOutputStreamTest() {              
		try(FileOutputStream fos = new FileOutputStream("0705檔案.txt", true);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) { 
			String str = "Helloooooo 嗨!@#$%^&*("; 
			bos.write(str.getBytes());
			bos.flush(); //將資料內容寫入到檔案內
			System.out.println("檔案寫出成功");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test // 使用緩衝區的寫法
	public void bufferInputStreamTest() { 
		try (FileInputStream fis = new FileInputStream("F:\\JAVA\\kirby.png");
				BufferedInputStream bis = new BufferedInputStream(fis);
				FileOutputStream fos = new FileOutputStream("kirby_BB.png");
				BufferedOutputStream bos = new BufferedOutputStream(fos)){
			
			//每次讀取多少byte
			byte[] buffer = new byte[512];
			
			int count = 0;
			//read()讀到檔案結束回傳-1
			while(bis.available() > 0) {
				bis.read(buffer); //從緩衝區bis讀取512bytes(讀出0-511) 並存入buffer此位元陣列中 
				bos.write(buffer); //將buffer陣列的位元寫入到bos中(寫入0-511)
				bos.flush();
				count++;
				System.out.println(count);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
