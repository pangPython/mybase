package cn.pangpython.mybase.rmi;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    public static void main(String[] args) {
        try {
            RemoteHelloWord remoteHelloWord = new RemoteHelloWordImpl();
            RemoteHelloWord stub = (RemoteHelloWord) UnicastRemoteObject.exportObject(remoteHelloWord, 9999);
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();

            registry.bind("helloworld", stub);

        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
