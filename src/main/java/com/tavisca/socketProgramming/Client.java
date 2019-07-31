package com.tavisca.socketProgramming;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost",8888);
            System.out.println("Connected with Server");

            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            Scanner in = new Scanner(System.in);
            String message = "";

            while(true){

                message = dataInputStream.readUTF();
                System.out.println("Message From Server "+message);

                if(message.equals("bye"))
                    break;

                String messageToBeSent = in.nextLine();
                dataOutputStream.writeUTF(messageToBeSent);
                dataOutputStream.flush();
            }
            in.close();
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();


        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
