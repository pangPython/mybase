package cn.pangpython.mybase.designpatterns.factory;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.get("A");
    }
    public void get(String name){
        Food food = null;
        switch (name){
            case "A":{
                food = StaticFactory.getA();
                break;
            }
            case "B":{
                food = StaticFactory.getB();
                break;
            }
            case "C":{
                food = StaticFactory.getC();
                break;
            }
            default:{
                break;
            }
        }
    }
}
