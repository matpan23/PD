package it.unisa.mpanza;
import java.io. *;
import java.net. *;
import java.util.logging.Logger;

public class Server {
    static Logger logger = Logger.getLogger("global");

    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(3330);
            logger.info("Socket ok, accetto connessioni");
            Socket socket = serverSocket.accept();
            logger.info("Accettata connessione con il client");
            ObjectOutputStream outs = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ins = new ObjectInputStream(socket.getInputStream());
            String nome = (String) ins.readObject();
            logger.info("Ricevuto:"+nome);
            outs.writeObject("Hello "+nome);
            socket.close();
        } catch (EOFException e) {
            logger.severe("Problemi con la connessione: "+ e.getMessage());
            e.printStackTrace();
        } catch (Throwable t){
            logger.severe("Lanciata Throwable: "+ t.getMessage());
            t.printStackTrace();
        }
    }
}
