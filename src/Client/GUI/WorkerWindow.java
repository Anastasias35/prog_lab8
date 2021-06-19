package Client.GUI;

import Client.Client;
import Client.util.User;
import Common.Request;
import Common.Response;
import Common.ResponseType;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Client.Main1.openWindow;

public class WorkerWindow extends JPanel {
    private JPanel jPanel;
    private Client client;
    private User user;
    private JLabel userName;
    private JButton exit;
    private JButton add;
    private JButton info;
    private JButton help;
    private JButton clear;
    private JButton vizualization;
    private JButton script;
    private JButton addIfMax;
    private JButton addIfMin;
    private JButton printDescending;
    private JButton printFieldAscendingSalary;
    private JButton updateId;
    private JLabel id1;
    private JTextField id1Text;
    private JButton executeScript;
    private JLabel filename;
    private JTextField file;
    private JButton removeById;
    private JLabel id2;
    private JTextField id2Text;
    private JButton countLessThanPosition;
    private JComboBox position;

    public WorkerWindow(Client client, User user){
        this.client=client;
        this.user=user;
        loadingWindow();
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   Request request=new Request("exit","",null,user);
                   client.sendToServer(request);
                   Response response=client.recieveFromServer();
                   JOptionPane.showMessageDialog(null,response.getInf());
                } catch (IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                } finally {
                    OpenWindow.jFrame.setContentPane(openWindow.getJPanel());
                    OpenWindow.jFrame.validate();
                }

            }
        });
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Request request = new Request("help", "", user);
                    client.sendToServer(request);
                    Response response=client.recieveFromServer();
                    JOptionPane.showMessageDialog(null,response.getInf());
                } catch (IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
        });
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Request request = new Request("info", "", user);
                    client.sendToServer(request);
                    Response response=client.recieveFromServer();
                    JOptionPane.showMessageDialog(null,response.getInf());
                } catch (IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Request request = new Request("clear", "", user);
                    client.sendToServer(request);
                    Response response=client.recieveFromServer();
                    JOptionPane.showMessageDialog(null,response.getInf());
                } catch (IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                }

            }
        });
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddWindow addWindow=new AddWindow(client,user);
                OpenWindow.jFrame.setContentPane(addWindow.getJpanel());  //доделать
                OpenWindow.jFrame.validate();
            }
        });
        printFieldAscendingSalary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Request request=new Request("print_field_ascending_salary","",user);
                    client.sendToServer(request);
                    Response response=client.recieveFromServer();
                    JOptionPane.showMessageDialog(null,response.getInf());
                } catch (IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
        });
        addIfMin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddifMinWindow addifMinWindow=new AddifMinWindow(client,user);
                OpenWindow.jFrame.setContentPane(addifMinWindow.getJpanel());
                OpenWindow.jFrame.validate();
            }
        });
        addIfMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddifMaxWindow addifMaxWindow=new AddifMaxWindow(client,user);
                OpenWindow.jFrame.setContentPane(addifMaxWindow.getJpanel());
                OpenWindow.jFrame.validate();
            }
        });
        updateId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateByIdWindow updateByIdWindow=new UpdateByIdWindow(client,user);
                OpenWindow.jFrame.setContentPane(updateByIdWindow.getJpanel());
                OpenWindow.jFrame.validate();
            }
        });
        executeScript.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Request request=new Request("execute_script",file.getText(),user);
                    file.setText("");
                    client.sendToServer(request);
                    Response response=client.recieveFromServer();
                    JOptionPane.showMessageDialog(null,response.getInf());
                } catch (IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
        });
        removeById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Request request=new Request("remove_by_id",id2Text.getText(),null,user);
                    id2Text.setText("");
                    client.sendToServer(request);
                    Response response=client.recieveFromServer();
                    JOptionPane.showMessageDialog(null,response.getInf());
                } catch (IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
        });
    }


    public void loadingWindow(){
        jPanel=new JPanel();
        jPanel.setBackground(new Color(0xFFBEF4));
        jPanel.setLayout(new MigLayout(
                        "insets 0,hidemode 3,align center center",
                        // columns
                       "[30,grow,fill]" +
                               "[5,grow,fill]" +
                               "[20,grow,fill]"+
                               "[20,grow,fill]" +
                               "[20,grow,fill]" +
                               "[20,grow,fill]" +
                               "[20,grow,fill]" +
                               "[20,grow,fill]" +
                               "[20,grow,fill]" +
                               "[20,grow,fill]",

                        // rows
                        "[20,fill]" +
                                "[10,grow,fill]" +
                                "[10,grow,fill]" +
                                "[10,grow,fill]" +
                                "[10,grow,fill]" +
                                "[10,grow,fill]" +
                                "[10,grow,fill]" +
                                "[10,grow,fill]" +
                                "[10,grow,fill]" +
                                "[10,grow,fill]" +
                                "[10,grow,fill]" +
                                "[10,grow,fill]" +
                                "[10,grow,fill]" +
                                 "[400,grow,fill]"));
        //имя текущего пользователя
        userName=new JLabel();
        userName.setText(client.getUser().getLogin());
        userName.setFont(new Font("TimesRoman",Font.BOLD,18));
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setVerticalAlignment(SwingConstants.CENTER);
        userName.setForeground(new Color(0xF816A5E7, true));
        userName.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        jPanel.add(userName,"cell 0 0 2 0");

        //кнопка выхода на поле входа
        exit=new JButton("exit");
        exit.setForeground(new Color(0xFFFFFF));
        exit.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        exit.setBackground(new Color(0xF816A5E7));
        exit.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(exit,"cell 11 0,align center center");

        //кнопка добавления элемента + возможно добавить с пунктами больше или меньше в виде выпадающего списка
        add=new JButton("add");
        add.setForeground(new Color(0xFFFFFF));
        add.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        add.setBackground(new Color(0xF816A5E7));
        add.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(add,"cell 6 0,align center center");

        //кнопка информации
        info=new JButton("info");
        info.setForeground(new Color(0xFFFFFF));
        info.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        info.setBackground(new Color(0xF816A5E7));
        info.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(info,"cell 2 0,align center center");

        //кнопка справочника
        help=new JButton("help");
        help.setForeground(new Color(0xFFFFFF));
        help.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        help.setBackground(new Color(0xF816A5E7));
        help.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(help,"cell 3 0,align center center");

        //кнопка очистка
        clear=new JButton("clear");
        clear.setForeground(new Color(0xFFFFFF));
        clear.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        clear.setBackground(new Color(0xF816A5E7));
        clear.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(clear,"cell 5 0,align center center");

        //кнопка визуализации
        vizualization=new JButton("vizualization");
        vizualization.setForeground(new Color(0xFFFFFF));
        vizualization.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        vizualization.setBackground(new Color(0xF816A5E7));
        vizualization.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(vizualization,"cell 4 0,align center center");

        //добавить если больше
        addIfMax=new JButton("add if max");
        addIfMax.setForeground(new Color(0xFFFFFF));
        addIfMax.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        addIfMax.setBackground(new Color(0xF816A5E7));
        addIfMax.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(addIfMax,"cell 7 0,align center center");

        //добавить если меньше
        addIfMin=new JButton("add if min");
        addIfMin.setForeground(new Color(0xFFFFFF));
        addIfMin.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        addIfMin.setBackground(new Color(0xF816A5E7));
        addIfMin.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(addIfMin,"cell 8 0,align center center");

        //сортировка по полю salary
        printFieldAscendingSalary=new JButton("print field ascending salary");
        printFieldAscendingSalary.setForeground(new Color(0xFFFFFF));
        printFieldAscendingSalary.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        printFieldAscendingSalary.setBackground(new Color(0xF816A5E7));
        printFieldAscendingSalary.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(printFieldAscendingSalary,"cell 10 0,align center center");

        //обновление по id
        updateId=new JButton("update id");
        updateId.setForeground(new Color(0xFFFFFF));
        updateId.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        updateId.setBackground(new Color(0xF816A5E7));
        updateId.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(updateId,"cell 0 1 2 0,align center center");
/*
        //id
        id1=new JLabel("id");
        id1.setFont(new Font("TimesRoman",Font.BOLD,15));
        id1.setHorizontalAlignment(SwingConstants.CENTER);
        id1.setVerticalAlignment(SwingConstants.CENTER);
        id1.setForeground(new Color(0xF816A5E7, true));
        jPanel.add(id1,"cell 0 2,align center center,grow 0 0,width 10");

        //поле для ввода id1
        id1Text=new JTextField();
        id1Text.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(id1Text,"cell 1 2,width 20 ");

 */

        //кнопка execute_script
        executeScript=new JButton("execute script");
        executeScript.setForeground(new Color(0xFFFFFF));
        executeScript.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        executeScript.setBackground(new Color(0xF816A5E7));
        executeScript.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(executeScript,"cell 0 3 2 0,align center center");

        //file
        filename=new JLabel("file");
        filename.setFont(new Font("TimesRoman",Font.BOLD,15));
        filename.setHorizontalAlignment(SwingConstants.CENTER);
        filename.setVerticalAlignment(SwingConstants.CENTER);
        filename.setForeground(new Color(0xF816A5E7, true));
        jPanel.add(filename,"cell 0 4,align center center,grow 0 0,width 10");

        //поле для ввода имени файла
        file=new JTextField();
        file.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(file,"cell 1 4,width 20 ");

        //кнопка remove_by_id
        removeById=new JButton("remove by id");
        removeById.setForeground(new Color(0xFFFFFF));
        removeById.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        removeById.setBackground(new Color(0xF816A5E7));
        removeById.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(removeById,"cell 0 5 2 0,align center center");

        //id
        id2=new JLabel("id");
        id2.setFont(new Font("TimesRoman",Font.BOLD,15));
        id2.setHorizontalAlignment(SwingConstants.CENTER);
        id2.setVerticalAlignment(SwingConstants.CENTER);
        id2.setForeground(new Color(0xF816A5E7, true));
        jPanel.add(id2,"cell 0 6,align center center,grow 0 0,width 10");

        //поле для ввода id
        id2Text=new JTextField();
        id2Text.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(id2Text,"cell 1 6,width 20 ");

        //кнопка countLessThanPosition
        removeById=new JButton("count less then position");
        removeById.setForeground(new Color(0xFFFFFF));
        removeById.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        removeById.setBackground(new Color(0xF816A5E7));
        removeById.setFont(new Font("TimesRoman",Font.BOLD,15));
        jPanel.add(removeById,"cell 0 7 2 0,align center center");

        //выбор позиции
        String[] items={
            "DIRECTOR",
            "CLEANER",
            "ENGINEER",
            "LEAD_DEVELOPER"
        };
        position=new JComboBox(items);
        position.setForeground(new Color(0xFFFFFF));
        position.setBackground(new Color(0xF816A5E7));
        position.setBorder(new OutLinesBoarder(5, new Color(0x0F81B8)));
        position.setFont(new Font("TimesRoman", Font.BOLD, 20));
        jPanel.add(position, "cell 0 8 2 0,align center center,grow 0 0");



    }

    public JPanel getJPanel(){
        return jPanel;
    }
}
