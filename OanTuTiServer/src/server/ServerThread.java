/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Admin
 */
public class ServerThread implements Runnable {

    private Socket connect;
    private int clientID;
    private BufferedReader is;
    private BufferedWriter os;
    private boolean isClosed;

    public BufferedReader getIs() {
        return is;
    }

    public BufferedWriter getOs() {
        return os;
    }

    public int getClientNumber() {
        return clientID;
    }

    public ServerThread(Socket connect, int clientNumber) {
        this.connect = connect;
        this.clientID = clientID;
        System.out.println("Server thread number " + clientID + " Started");
        isClosed = false;
    }

    @Override
    public void run() {
        try {
            // Mở luồng vào ra trên Socket tại Server.
            is = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
            System.out.println("Khời động luông mới thành công, ID là: " + clientID);
            write("get-id" + "," + this.clientID);
            String message;
            while (!isClosed) {
                message = is.readLine();
                if (message == null) {
                    break;
                }
                String[] messageSplit = message.split(",");
                //
            }
        } catch (IOException e) {
            isClosed = true;
        }
    }
    public void write(String message) throws IOException{
        os.write(message);
        os.newLine();
        os.flush();
    }
}
