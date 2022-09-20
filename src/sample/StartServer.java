package sample;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class StartServer {
    public static final String UNIQUE_BINDING_NAME = "server.databasehandler";
    /**
     * <b>Main method</b>
     * <pre>{@code
     * Registry registry = LocateRegistry.getRegistry(port);
     * DataBaseServerHandler server = new DataBaseServerHandler()
     * Remote stub = UnicastRemoteObject.exportObject(server, 0);
     * registry.bind(UNIQUE_BINDING_NAME, stub);
     * }</pre>
     *
     * @param args args
     */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException, SQLException {

        final DataBaseServerHandler server = new DataBaseServerHandler();

        final Registry registry = LocateRegistry.createRegistry(2732);

        Remote stub = UnicastRemoteObject.exportObject(server, 0);
        registry.bind(UNIQUE_BINDING_NAME, stub);

        Thread.sleep(Integer.MAX_VALUE);

    }
}
