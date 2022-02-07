package com.darktornado.umm2lua;

public class Parser {
	
	public String parse(String src) {
		if (src.equals("")) return "";
		
		/* .
		 * 양수 및 더하기 */
		src = dot2int(src);
		
		/* ,
		 * 음수 및 빼기 */
		src = comma2int(src);
		
		/* 띄어쓰기
		 * 곱하기 */
		src = src.replace(" ", "*");
		
		/* 엄
		 * 변수에 값 넣기 */
		src = src.replace("엄", "어 = ");

		/* 식! & 식ㅋ
		 * 출력 */
		src = print(src);

		/* 준
		 * goto */
		src = jmp(src);
		
		return src;
	}

	private String dot2int(String src) {
		while (src.contains(".")) {
			char[] c = src.toCharArray();
			int start = -1, count = 0;
			for(int n = 0; n < c.length;n++) {
				if (c[n]=='.') {
					if (start == -1) start = n;
					count++;
				}
				if (start !=-1 && c[n]!='.') break;
			}
			String left = src.substring(0, start);
			src = left + (checkLeft(left) ? "+" : "") + count + src.substring(start + count);
		}
		return src;
	}
	
	private boolean checkLeft(String src) {
		char c = src.charAt(src.length() - 1);
		if (Character.isDigit(c)) return true;
		if (c == '어') return true;
		return false;
	}

	private String comma2int(String src) {
		while (src.contains(",")) {
			char[] c = src.toCharArray();
			int start = -1, count = 0;
			for(int n = 0; n < c.length;n++) {
				if (c[n]==',') {
					if (start == -1) start = n;
					count++;
				}
				if (start !=-1 && c[n]!=',') break;
			}
			src = src.substring(0, start) + "-" + count + src.substring(start + count);
		}
		return src;
	}
	
	private String print(String src) {
		if (!src.startsWith("식")) return src;
		if (src.endsWith("!")) {
			return "print(" + src.substring(1, src.length() - 1) + ")";
		} else if(src.endsWith("ㅋ")) {
			return "print(string.char(" + src.substring(1, src.length() - 1) + "))";
		} else {
			//Compile Error 넣을꼬얌!
			return src;
		}
	}
	
	private String jmp(String src) {
		if (!src.startsWith("준")) return src;
		return "goto label"+src.substring(1);		
	}

	

}
