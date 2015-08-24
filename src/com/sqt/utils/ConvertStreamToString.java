package com.sqt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConvertStreamToString {
	public static String GetStreamToString(InputStream is){
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try{
			while((line = reader.readLine()) != null){
				sb.append(line + "/n");
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				is.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
