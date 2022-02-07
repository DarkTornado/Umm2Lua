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
			src[n] = "::label" + n + "::\n" + parser.parse(src[n]);
			result.append(src[n]).append("\n");
		}
		return result.toString().trim();
	}

}
