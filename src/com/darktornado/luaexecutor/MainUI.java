package com.darktornado.luaexecutor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import com.darktornado.umm2lua.Umm2Lua;

public class MainUI {

    public static JTextArea output = null;

    public static void main(String[] args) {
        new MainUI().onCreate();
    }

    private void onCreate() {
        JFrame window = new JFrame("엄준식은 살아있다!");
        window.setLayout(new GridLayout(1, 2, 10, 10));

        JTextArea input = new JTextArea();
        input.setFont(input.getFont().deriveFont(17f));
        input.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        input.setLineWrap(true);
        input.setWrapStyleWord(true);
        window.add(input);
        output = new JTextArea();
        output.setFont(input.getFont().deriveFont(17f));
        output.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        window.add(output);

        JMenuBar title = new JMenuBar();
        title.add(createFileMenu());
        title.add(createBuildMenu(input));
        title.add(createHelpMenu());
        window.setJMenuBar(title);

        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 400);
        window.setVisible(true);
    }

    private JMenu createFileMenu() {
        JMenu menu = new JMenu("파일");
        JMenuItem item1 = new JMenuItem("종료");
        item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(item1);
        return menu;
    }

    private JMenu createBuildMenu(final JTextArea input) {
        JMenu menu = new JMenu("실행 & 컴파일");
        JMenuItem item1 = new JMenuItem("엄랭 실행");
        item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String src = input.getText();
                String result = execute(src);
                output.setText(result);
            }
        });
        menu.add(item1);
        JMenuItem item2 = new JMenuItem("루아로 변환");
        item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String src = input.getText();
                String result = Umm2Lua.compile(src);
                output.setText(result);
            }
        });
        menu.add(item2);
        return menu;
    }

    private JMenu createHelpMenu() {
        JMenu menu = new JMenu("도움말");
        JMenuItem item1 = new JMenuItem("도움말");
        item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(item1);
        return menu;
    }

    private String execute(String src) {
        LuaExecutor le = new LuaExecutor();
        le.clearStdout();
        le.execute(Umm2Lua.compile(src));
        return le.getStdout();
    }

}