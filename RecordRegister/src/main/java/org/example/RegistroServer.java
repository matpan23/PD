package org.example;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Logger;
public class RegistroServer {
    static Logger logger = Logger.getLogger("global");

    public static void main(String[] args){
        HashMap<String, RecordRegister> hash = new HashMap<String, RecordRegister>();
        Socket socket = null;
        System.out.println("In attesa...");
        try{
            ServerSocket serverSocket = new ServerSocket(3330);
            while(true){
                socket = serverSocket.accept();
                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                RecordRegister record = (RecordRegister) instream.readObject();
                if(record.getIndirizzo()!=null){ //scrittura in HashMap
                    hash.put(record.getNome(), record);
                }
                else { //ricerca in HashMap
                    RecordRegister res = hash.get(record.getNome());
                    ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                    outstream.writeObject(res);
                    outstream.flush();
                }
                socket.close();
            }
        }catch(EOFException e) {
            logger.severe(e.getMessage());
            e.printStackTrace();
        }catch(Throwable t) {
            logger.severe(t.getMessage());
            t.printStackTrace();
        }
        finally{
            try{ socket.close();
            }catch(IOException e){
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
}
