package com.darktornado.luaexecutor;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class LuaApi {

    static class Print extends OneArgFunction {
        @Override
        public LuaValue call(final LuaValue msg) {
        	LuaExecutor.stdout.append(msg).append("\n");
        	return msg;
        }
    }

}
