package com.darktornado.luaexecutor;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaExecutor {
	
	public static StringBuilder stdout = new StringBuilder();
	
	public void clearStdout() {
		stdout.setLength(0);
	}

	public String getStdout() {
		return stdout.toString().trim();
	}
	
	public void execute(String src) {
        Globals globals = JsePlatform.standardGlobals();
        globals.set("print", CoerceJavaToLua.coerce(new LuaApi.Print()));
        LuaValue chunk = globals.load(src);
        chunk.call();
	}

}
