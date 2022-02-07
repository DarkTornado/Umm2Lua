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
		for(int n = 1; n < src.length - 1; n++) {
			result.append("::label_" + (n + 1) + "::\n" + parser.parse(src[n]) + "\n");
		}
		String _result = result.toString();
		for(int n = 2; n < src.length; n++) {
			if (!_result.contains("goto label_" + n)) {
				_result = _result.replace("::label_" + n + "::\n", "");
			}
		}
		return _result.trim();
	}

}
