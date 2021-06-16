package Client;

import Client.util.Entrance;
import Client.util.NewConsole;
import Client.util.User;
import Common.Request;
import Common.exceptions.ConnectionErrorException;
import Common.Response;
import Common.ResponseType;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.NoSuchElementException;

public class Client {
    private String host;
    private int port;
    private NewConsole console;
    private int reconnecting;
    private boolean connection;
    private boolean run;
    private final int maxconnect=5;
    private DatagramSocket socket;
    private SocketAddress address;
    private Entrance entrance;
    private User user;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;



    public Client(String host, int port, NewConsole console, Entrance entrance){
        this.host=host;
        this.port=port;
        this.console=console;
        this.entrance=entrance;
    }


    public void connect() {
        try{
            address=new InetSocketAddress(this.host,this.port);
            socket=new DatagramSocket();
            System.out.println("Подключение с сервером установлено");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при попытке подключения с сервером");
            System.exit(0);
        }

    }

    public void sendToServer(Request request) throws IOException{
        user=request.getUser();
        byte[] sendBuffer = serialization(request);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);
    }

    public Response recieveFromServer() throws IOException, ClassNotFoundException {
        byte[] getBuffer = new byte[socket.getReceiveBufferSize()];
        DatagramPacket getPacket = new DatagramPacket(getBuffer, getBuffer.length);
        socket.receive(getPacket);
        return deserialization(getPacket);
    }


    public byte[] serialization(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        byte[] byteRequest=byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return byteRequest;
    }


    public Response deserialization(DatagramPacket byteResponse) throws IOException, ClassNotFoundException,ClassCastException {
        Response response=null;
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteResponse.getData());
        ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
        response=(Response)objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return response;
    }


    public User getUser(){
        return user;
    }
}
