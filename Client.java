package ChatUDP;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) throws Exception {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Address: ");

            String address= scan.nextLine();
            address= scan.nextLine();
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getByName(address); //args[1] "127.0.0.1"
            System.out.println("Port: ");
            String p =scan.nextLine();
            int port = Integer.parseInt(p);  //
            byte[] sendData = new byte[1024];
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
            clientSocket.send(sendPacket);

            InThread in = new InThread(clientSocket);
            OutThread out = new OutThread(ipAddress, port, clientSocket);
            in.start();
            out.start();
            out.join();
        }

        catch (RuntimeException | InterruptedException ex){}
    }

}
