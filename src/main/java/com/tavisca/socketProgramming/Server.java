package com.tavisca.socketProgramming;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server Started!!!!");
            Socket socket = serverSocket.accept();
            System.out.println("New Client Connected!!!");

            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            Scanner in = new Scanner(System.in);

            String message = "";

            while(true){
                String messageToBeSent = in.nextLine();
                dataOutputStream.writeUTF(messageToBeSent);
                dataOutputStream.flush();

                message = dataInputStream.readUTF();
                System.out.println("Message from Client "+message);

                if(message.equals("bye"))
                    break;
            }

            socket.close();
            in.close();
            dataInputStream.close();
            dataOutputStream.close();
            serverSocket.close();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
