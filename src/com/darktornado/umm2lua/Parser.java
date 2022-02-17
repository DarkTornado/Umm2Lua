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
        src = intmultiply(src.replace(" ", "*"));

        /* 엄
         * 변수에 값 넣기 */
        src = src.replace("엄", "어 = ");

        /* 식! & 식ㅋ
         * 출력 */
        src = print(src);

        /* 준
         * goto */
        src = jmp(src);

        /* 동탄
         * if */
        src = conditional(src);

        return src;
    }
    
    private String intmultiply (String str) {
    	String result = str;
    	Pattern pattern = Pattern.compile("(.*)([0-9])\\*([0-9])");
    	Matcher matcher = pattern.matcher(result);
    	while(matcher.find()){    // 정규식과 매칭되는 값이 있으면
    	    result = matcher.group(1).trim()+Integer.parseInt(matcher.group(2).trim())*Integer.parseInt(matcher.group(3).trim());        // 특정 단어 사이의 값을 추출한다
    	    matcher = pattern.matcher(result);
    	}
    	return result;
    }
    
    private String dot2int(String src) {
        while (src.contains(".")) {
            char[] c = src.toCharArray();
            int start = -1, count = 0;
            for (int n = 0; n < c.length; n++) {
                if (c[n] == '.') {
                    if (start == -1) start = n;
                    count++;
                }
                if (start != -1 && c[n] != '.') break;
            }
            String left = src.substring(0, start);
            src = left + (checkLeft(left) ? "+" : "") + count + src.substring(start + count);
        }
        return src;
    }

    private boolean checkLeft(String src) {
        char c = src.charAt(src.length() - 1);
        if (Character.isDigit(c)) return true;
        return c == '어';
    }

    private String comma2int(String src) {
        while (src.contains(",")) {
            char[] c = src.toCharArray();
            int start = -1, count = 0;
            for (int n = 0; n < c.length; n++) {
                if (c[n] == ',') {
                    if (start == -1) start = n;
                    count++;
                }
                if (start != -1 && c[n] != ',') break;
            }
            src = src.substring(0, start) + "-" + count + src.substring(start + count);
        }
        return src;
    }

    private String print(String src) {
        if (!src.contains("식")) return src;
        int start = src.indexOf('식');
        if (src.contains("!")) {
            int end = src.indexOf('!');
            if (start > end) return src;  //Compile Error 넣을꼬얌!
            return "print(" + src.substring(start + 1, end) + ")";
        } else if (src.endsWith("ㅋ")) {
            int end = src.indexOf('ㅋ');
            if (start > end) return src;  //Compile Error 넣을꼬얌!
            return "print(string.char(" + src.substring(start + 1, end) + "))";
        } else {
            //Compile Error 넣을꼬얌!
            return src;
        }
    }

    private String jmp(String src) {
        if (!src.contains("준")) return src;
        int start = src.indexOf('준');
        int end = -1;
        for (int n = start; n < src.length(); n++) {
            if (!Character.isDigit(src.charAt(n)) && end != -1) break;
            if (Character.isDigit(src.charAt(n))) end = n;
        }
        if (start > end) return src;  //Compile Error 넣을꼬얌!
        return src.substring(0, start) + "goto label_" + src.substring(start + 1);
    }

    private String conditional(String src) {
        if (!src.contains("동탄") || !src.contains("?")) return src; //Compile Error 넣을꼬얌!
        String[] s = src.split("\\?");
        return "if " + s[0].substring(s[0].indexOf("동탄") + 2) + " == 0 then\n" +
                s[1] + "\n" +
                "end";
    }

}
