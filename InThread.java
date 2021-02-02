package ChatUDP;

import java.net.*;
import java.io.*;


public class InThread extends Thread{
    private DatagramSocket socket;


    public InThread(DatagramSocket socket) {
        this.socket=socket;
    }
    @Override
    public void run(){
        try{
            String name = "Server";
            while (true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

                int length;
                for(length = 0; receiveData[length] != 0; length++);
                byte[] correctReceive = new byte[length];
                for(int j = 0; j < length; j++)
                {
                    correctReceive[j] = receiveData[j];
                }


            String sentence = new String(correctReceive);
                if(sentence.equals("@name")){
                    byte[] receiveDataName = new byte[1024];
                    DatagramPacket receivePacketName = new DatagramPacket(receiveDataName, receiveDataName.length);
                    socket.receive(receivePacketName);

                    int len;
                    for(len = 0; receiveDataName[len] != 0; len++);
                    byte[] correctReceive1 = new byte[len];
                    for(int j = 0; j < len; j++)
                    {
                        correctReceive1[j] = receiveDataName[j];
                    }
                    String sentence1 = new String(correctReceive1);
                    name=sentence1;
                }
                else{
                System.out.println(name+": " +sentence);}
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }

}
