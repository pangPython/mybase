package cn.pangpython.mybase.designpatterns.abstractfactory;

public class AbstractFactory {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new AbstractFactory();
        Food a = abstractFactory.get("A");
        System.out.printf(a.toString());
    }
    public Food get(String name){
        Food food = null;
        switch (name){
            case "A":{
                food = new AFactory().get();
                break;
            }
            case "B":{
                food = new BFactory().get();
                break;
            }
            default:{
                break;
            }
        }
        return food;
    }
}
