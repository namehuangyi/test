package practice;

import javax.swing.*;

public class MySnake {
    public static void main(String[] args) {
        // 创建窗口及相关设置
        JFrame jFrame = new JFrame();
        jFrame.setBounds(600, 100, 700, 900);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 添加画布
        jFrame.add(new MyPanel());
        jFrame.setVisible(true);
    }
}
