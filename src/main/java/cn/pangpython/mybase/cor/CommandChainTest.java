package cn.pangpython.mybase.cor;

import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.impl.ContextBase;

/**
 * command中返回true就停止向下执行
 *
 */
public class CommandChainTest extends ChainBase {


    public CommandChainTest() {
        addCommand(new Command1());
        addCommand(new Command2());
        addCommand(new Command3());
    }

    public static void main(String[] args) throws Exception {
        CommandChainTest commandChainTest = new CommandChainTest();
        ContextBase contextBase = new ContextBase();
        contextBase.put("model0","123");
        contextBase.put("model1","123");
        contextBase.put("model2","123");
        contextBase.put("model3","123");
        commandChainTest.execute(contextBase);
    }
}
