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

    public WorkerWindow(Client client, User user){
        this.client=client;
        this.user=user;
        loadingWindow();
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenWindow.jFrame.setContentPane(openWindow.getJPanel());
                OpenWindow.jFrame.validate();
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
    }


    public void loadingWindow(){
        jPanel=new JPanel();
        jPanel.setBackground(new Color(0xFFBEF4));
        jPanel.setLayout(new MigLayout(
                        "insets 0,hidemode 3,align center center",
                        // columns
                       "[30,grow,fill]" +
                               "[15,grow,fill]" +
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
                                "[20,grow,fill]" +
                                "[10,grow,fill]" +
                                 "[650,grow,fill]"));
        //имя текущего пользователя
        userName=new JLabel();
        userName.setText(client.getUser().getLogin());
        userName.setFont(new Font("TimesRoman",Font.BOLD,20));
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setVerticalAlignment(SwingConstants.CENTER);
        userName.setForeground(new Color(0xF816A5E7, true));
        userName.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        jPanel.add(userName,"cell 0 0 1 0");

        //кнопка выхода на поле входа
        exit=new JButton("exit");
        exit.setForeground(new Color(0xFFFFFF));
        exit.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        exit.setBackground(new Color(0xF816A5E7));
        exit.setFont(new Font("TimesRoman",Font.BOLD,20));
        jPanel.add(exit,"cell 11 0,align center center");

        //кнопка добавления элемента + возможно добавить с пунктами больше или меньше в виде выпадающего списка
        add=new JButton("add");
        add.setForeground(new Color(0xFFFFFF));
        add.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        add.setBackground(new Color(0xF816A5E7));
        add.setFont(new Font("TimesRoman",Font.BOLD,20));
        jPanel.add(add,"cell 6 0,align center center");

        //кнопка информации
        info=new JButton("info");
        info.setForeground(new Color(0xFFFFFF));
        info.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        info.setBackground(new Color(0xF816A5E7));
        info.setFont(new Font("TimesRoman",Font.BOLD,20));
        jPanel.add(info,"cell 2 0,align center center");

        //кнопка справочника
        help=new JButton("help");
        help.setForeground(new Color(0xFFFFFF));
        help.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        help.setBackground(new Color(0xF816A5E7));
        help.setFont(new Font("TimesRoman",Font.BOLD,20));
        jPanel.add(help,"cell 3 0,align center center");

        //кнопка очистка
        clear=new JButton("clear");
        clear.setForeground(new Color(0xFFFFFF));
        clear.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        clear.setBackground(new Color(0xF816A5E7));
        clear.setFont(new Font("TimesRoman",Font.BOLD,20));
        jPanel.add(clear,"cell 5 0,align center center");

        //кнопка визуализации
        vizualization=new JButton("vizualization");
        vizualization.setForeground(new Color(0xFFFFFF));
        vizualization.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        vizualization.setBackground(new Color(0xF816A5E7));
        vizualization.setFont(new Font("TimesRoman",Font.BOLD,20));
        jPanel.add(vizualization,"cell 4 0,align center center");

        //добавить если больше
        addIfMax=new JButton("add if max");
        addIfMax.setForeground(new Color(0xFFFFFF));
        addIfMax.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        addIfMax.setBackground(new Color(0xF816A5E7));
        addIfMax.setFont(new Font("TimesRoman",Font.BOLD,20));
        jPanel.add(addIfMax,"cell 7 0,align center center");

        //добавить если меньше
        addIfMin=new JButton("add if min");
        addIfMin.setForeground(new Color(0xFFFFFF));
        addIfMin.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        addIfMin.setBackground(new Color(0xF816A5E7));
        addIfMin.setFont(new Font("TimesRoman",Font.BOLD,20));
        jPanel.add(addIfMin,"cell 8 0,align center center");

        //сортировка по убыванию
        printDescending=new JButton("print descending");
        printDescending.setForeground(new Color(0xFFFFFF));
        printDescending.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        printDescending.setBackground(new Color(0xF816A5E7));
        printDescending.setFont(new Font("TimesRoman",Font.BOLD,20));
        jPanel.add(printDescending,"cell 9 0,align center center");

        //сортировка по полю salary
        printFieldAscendingSalary=new JButton("print field ascending salary");
        printFieldAscendingSalary.setForeground(new Color(0xFFFFFF));
        printFieldAscendingSalary.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        printFieldAscendingSalary.setBackground(new Color(0xF816A5E7));
        printFieldAscendingSalary.setFont(new Font("TimesRoman",Font.BOLD,20));
        jPanel.add(printFieldAscendingSalary,"cell 10 0,align center center");

        //обновление по id
        updateId=new JButton("update id");
        updateId.setForeground(new Color(0xFFFFFF));
        updateId.setBorder(new OutLinesBoarder(5,new Color(0x0F81B8)));
        updateId.setBackground(new Color(0xF816A5E7));
        updateId.setFont(new Font("TimesRoman",Font.BOLD,20));
        jPanel.add(updateId,"cell 0 1 1 0,align center center");

        //id
        id1=new JLabel("id");
        id1.setFont(new Font("TimesRoman",Font.BOLD,15));
        id1.setHorizontalAlignment(SwingConstants.CENTER);
        id1.setVerticalAlignment(SwingConstants.CENTER);
        id1.setForeground(new Color(0xF816A5E7, true));
        jPanel.add(id1,"cell 0 2,grow 0 0");

        //поле для ввода id1
        id1Text=new JTextField(10);
        id1Text.setFont(new Font("TimesRoman",Font.BOLD,18));
        jPanel.add(id1Text,"cell 1 2, align center center,grow 0 0 ");
    }

    public JPanel getJPanel(){
        return jPanel;
    }
}
