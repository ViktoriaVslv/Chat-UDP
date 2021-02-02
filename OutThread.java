package ChatUDP;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class OutThread extends  Thread{
    private DatagramSocket socket;
    private int port;
    private InetAddress ip;

    public OutThread(InetAddress ipAddress, int port, DatagramSocket socket){
        this.socket=socket;
        this.port=port;
        this.ip=ipAddress;
    }
    @Override
    public void run(){
        try{
            String name = "I";
            while (true) {

                Scanner scan = new Scanner(System.in);
                System.out.println(name+": ");
                byte[] sendData = new byte[1024];
                String sentence = scan.nextLine();
                if (sentence.equals("@name")) {
                    System.out.println("input your name: ");
                    name = scan.nextLine();
                    DatagramPacket sendPacket = new DatagramPacket("@name".getBytes(),"@name".getBytes().length, ip, port);
                    socket.send(sendPacket);
                    sendPacket = new DatagramPacket(name.getBytes(),name.getBytes().length, ip, port);
                    socket.send(sendPacket);
                    continue;
                }
                if (sentence.equals("@quit")) {
                    String end = "Dialog over";
                    sendData = end.getBytes();
                    DatagramPacket res = new DatagramPacket(sendData, sendData.length, ip, port);
                    socket.send(res);
                    System.exit(0);
                    socket.close();
                }

                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);


                socket.send(sendPacket);
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

