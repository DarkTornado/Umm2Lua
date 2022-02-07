package com.darktornado.umm2lua;

public class Compiler {
	
	private String source;
	
	public void setSource(String source) {
		this.source = source.trim();
	}
	
	public String execute() {
		String[] src = source.split("\n");
		Parser parser = new Parser();
		StringBuilder result = new StringBuilder();
		
		/* 모든 줄 사이에 label 넣기 */
		for (int n = 1; n < src.length - 1; n++) {
			result.append("::label_" + (n + 1) + "::\n" + parser.parse(src[n]) + "\n");
		}
		
		String _result = result.toString();

		/* 없어도 되는 label들 빼기 */
		for(int n = 2; n < src.length; n++) {
			if (!_result.contains("goto label_" + n)) {
				_result = _result.replace("::label_" + n + "::\n", "");
			}
		}
		return changeVariableNames(_result.trim());
	}
	
	private String changeVariableNames(String src) {
		int max = getMaxUmm(src);
		if (max == 0) return src;
		if (max == 1) return src.replace("어", "var");
		StringBuilder _umm = new StringBuilder();
		for (int n = 0; n < max; n++) {
			_umm.append("어");
		}
		String umm = _umm.toString();
		int count = max;
		while (count > 0) {
			src = src.replace(umm, "var" + count);
			umm = umm.substring(1);
			count--;
		}
		return src;		
	}
	
	private int getMaxUmm(String src) {
		StringBuilder umm = new StringBuilder("어");
		for (int n = 0; ; n++) {
			if (!src.contains(umm.toString())) return n;
			umm.append("어");
		}
	}

}
