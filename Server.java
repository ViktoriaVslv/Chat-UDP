package ChatUDP;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {

    public static void main(String args[]) throws Exception {

        Scanner scan = new Scanner(System.in);
        System.out.println("Port: ");

        String p= scan.nextLine();

        DatagramSocket serverSocket = new DatagramSocket(Integer.parseInt(p)); //Integer.parseInt(args[0])
        byte[] receiveData = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);

        InetAddress ipAddress = receivePacket.getAddress();
        int port = receivePacket.getPort();
        //System.out.println("Welcome!");
        DatagramPacket sendPacket = new DatagramPacket("Welcome!".getBytes(),"Welcome!".getBytes().length, ipAddress, port);
        serverSocket.send(sendPacket);
        InThread in = new InThread(serverSocket);
        OutThread out = new OutThread(ipAddress,port,new DatagramSocket());
        in.start();
        out.start();

    }
}