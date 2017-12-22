package cn.pangpython.mybase.cor;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class Command2 implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        System.out.println("Command2:"+context.size());
        return true;
    }
}
