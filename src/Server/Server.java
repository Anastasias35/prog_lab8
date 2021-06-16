package Server;
import Common.Request;
import Common.Response;
import Common.ResponseType;
import Server.utilitka.ProcessingOfRequest;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private int port;
    private ProcessingOfRequest processingOfRequest;
    private DatagramSocket socket;
    private InetAddress address;
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
    private Request request;
    private Scanner scanner;

    public Server(int port, ProcessingOfRequest processingOfRequest) {
        this.port = port;
        this.processingOfRequest=processingOfRequest;
    }

    public void work() {
        System.out.println("Запуск сервера");
        System.out.println("Сервер успешно запущен");
        Scanner scanner =new Scanner(System.in);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Завершаю программу.");
        }));
        try {
            socket = new DatagramSocket(this.port);
            while (true) {
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request=getRequest();
                            System.out.println(request.getCommand());
                            Runnable runnable1=new Runnable() {
                                @Override
                                public void run() {
                                    if (request!=null) {
                                        Response response = processingOfRequest.getResponse(request);
                                        fixedThreadPool.submit(() -> {
                                            try {
                                                System.out.println("34567");
                                                sendResponse(response);
                                                if (response.getResponseType().equals(ResponseType.EXIT)) {
                                                    System.out.println("Работа с клиентом завершена");
                                                }
                                            } catch (IOException exception) {
                                                exception.printStackTrace();
                                            }
                                        });
                                    }
                                }
                            };
                            Thread thread1=new Thread(runnable1);
                            thread1.start();
                            thread1.join();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        } catch (ClassNotFoundException exception) {
                            exception.printStackTrace();
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }
                    }
                };
                Thread thread=new Thread(runnable);
                thread.start();
                thread.join();
            }
        }catch (IOException exception){
            System.out.println();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    private void sendResponse(Response response) throws IOException {
        byte[] sendBuffer = serialization(response);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
        socket.send(sendPacket);
    }

    private Request getRequest() throws IOException, ClassNotFoundException {
        byte[] getBuffer = new byte[socket.getReceiveBufferSize()];
        DatagramPacket getPacket = new DatagramPacket(getBuffer,getBuffer.length);
        socket.receive(getPacket);
        address = getPacket.getAddress();
        port = getPacket.getPort();
        return deserialization(getPacket, getBuffer);
    }

    private Request deserialization(DatagramPacket getPacket, byte[] buffer) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getPacket.getData());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Request request = (Request) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return request;
    }


    public byte[] serialization(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        byte[] byteRequest=byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return byteRequest;
    }

}














/*


    public void sendResponse(Response response) throws IOException {
        ByteBuffer byteBuffer=ByteBuffer.allocate(65536);
        byte[]  byte1;
        socketChannel=null;
        selector.select();
        Set<SelectionKey> keys=selector.selectedKeys();
        for(SelectionKey selectionKey:keys){
            if(selectionKey.isWritable()){
                socketChannel=(SocketChannel) selectionKey.channel();
                byte1=serialization(response);
                byteBuffer.put(byte1);
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                System.out.println("Отправка ответа клиенту");
                byteBuffer.clear();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector,SelectionKey.OP_READ);
                keys.remove(selectionKey);
            }
        }
    }

    public void work() {
        try {
            System.out.println("Запуск сервера");
            System.out.println("Сервер успешно запущен");
            Scanner scanner =new Scanner(System.in);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Завершаю программу.");
            }));


            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress("localhost", port));
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            serverSocketChannel.configureBlocking(false);
            while (true) {
                ExecutorService fixedThreadPool= Executors.newFixedThreadPool(3);
                connection(serverSocketChannel);
                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request=getRequest();
                            Runnable runnable1=new Runnable() {
                                @Override
                                public void run() {
                                    if (request!=null) {
                                        Response response = processingOfRequest.getResponse(request);
                                        fixedThreadPool.submit(() -> {
                                            try {
                                                sendResponse(response);
                                                if (response.getResponseType().equals(ResponseType.EXIT)) {
                                                    System.out.println("Работа с клиентом завершена");
                                                    socketChannel.close();
                                                }
                                            } catch (IOException exception) {
                                                exception.printStackTrace();
                                            }
                                        });
                                        fixedThreadPool.shutdown();
                                    }
                                }
                            };
                            Thread thread1=new Thread(runnable1);
                            thread1.start();
                            thread1.join();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        } catch (ClassNotFoundException exception) {
                            exception.printStackTrace();
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }
                    }
                };
                Thread thread=new Thread(runnable);
                thread.start();
                thread.join();
            }
        }catch (IOException exception){
            System.out.println();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

   /* public byte[] serialization(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        byte[] byteRequest=byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return byteRequest;
    }

    */

   /* public Request deserialization(byte[] byteRequest) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteRequest);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Request request = (Request) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return request;
    }



    */
