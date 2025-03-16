package model;

import java.util.Random;

public class RandomNumber {

	public String make_num(int n){ //지정한 길이 난수 생성
		Random rd = new Random();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<n; i++) {
			sb.append(rd.nextInt(10)); //0~9까지의 숫자 추가
		}
		return sb.toString();
	}
}
