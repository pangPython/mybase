package cn.pangpython.mybase.cor;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class Command3 implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        System.out.println("Command3:"+context.size());
        context.remove("model0");
        return false;
    }
}
