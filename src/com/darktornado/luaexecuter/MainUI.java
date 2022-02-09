package com.darktornado.luaexecuter;

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

    private JMenuBar createTitleBar(final JTextArea input) {
        JMenuBar title = new JMenuBar();

        JMenu menu = new JMenu("실행 & 컴파일");
        JMenuItem item1 = new JMenuItem("엄랭 실행");
        item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        title.add(menu);
        return title;
    }

    private void onCreate() {
        JFrame window = new JFrame("엄준식은 살아있다!");
        window.setLayout(new GridLayout(1, 2, 10, 10));

        JTextArea input = new JTextArea();
        input.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        input.setLineWrap(true);
        input.setWrapStyleWord(true);
        window.add(input);
        output = new JTextArea();
        output.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        window.add(output);

        JMenuBar title = createTitleBar(input);
        window.setJMenuBar(title);

        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 400);
        window.setVisible(true);
    }





}
