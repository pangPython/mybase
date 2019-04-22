package cn.pangpython.mybase.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry localhost = LocateRegistry.getRegistry("localhost");
            RemoteHelloWord helloworld = (RemoteHelloWord) localhost.lookup("helloworld");
            System.out.println(helloworld.sayHello());


        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
