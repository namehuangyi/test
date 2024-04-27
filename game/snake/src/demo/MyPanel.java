package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * 画布类
 */
public class MyPanel extends JPanel implements KeyListener, ActionListener {

    // 声明蛇头和身体图片
    ImageIcon right = new ImageIcon("image/right.jpg");
    ImageIcon left = new ImageIcon("image/left.jpg");
    ImageIcon bottom = new ImageIcon("image/bottom.jpg");
    ImageIcon top = new ImageIcon("image/top.jpg");
    ImageIcon body = new ImageIcon("image/body.jpg");
    //声明一个枚举变量来指定蛇头的方向
    Direction direction = Direction.right;
    // 蛇的初始长度
    int len = 3;
    // 声明两个数组来存放x和y的坐标
    int[] snakeX = new int[1008]; // 最大值 = 宽度格子 * 高度格子
    int[] snakeY = new int[1008];

    // 声明一个boolean类型的变量，标记游戏是否开始,true表示开始，false表示没有开始
    boolean isStart = false;

    // 创建一个定时器对象
    Timer timer = new Timer(100, this);

    // 声明食物图片
    ImageIcon food = new ImageIcon("image/food.jpg");
    // 生成一个随机数
    Random random = new Random();

    int footX;
    int footY;
    public MyPanel() {
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;

        // 获取键盘事件
        this.setFocusable(true);
        // 添加监听
        this.addKeyListener(this);

        // 启动定时器
        timer.start();

        // 食物的位置
        footX = 25 + 25 * random.nextInt(20);
        footY = 25 + 25 * random.nextInt(30);
    }
    // g参数相当于画笔
    @Override
    public void paintComponent(Graphics g) {
        // 调用父类方法做一些基本工作
        super.paintComponent(g);
        // 设置背景颜色
        this.setBackground(Color.red);
        // 在画布上添加游戏区域
        g.fillRect(0, 0, 700, 900);
        // 添加蛇头
        switch (direction){
            case top:
                top.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case bottom:
                bottom.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case left:
                left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case right:
                right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }
        // 添加身体
        for (int i = 1; i < len; i++) {
            body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        // 添加开始提示信息，并设置字体大小和颜色
        if (!isStart){
            g.setColor(Color.white);
            g.setFont(new Font("宋体", Font.BOLD, 50));
            g.drawString("请按空格键开始游戏", 50, 500);
        }

        // 添加食物
        food.paintIcon(this, g, footX, footY);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 32){
            //标记游戏状态的取反值
            isStart = !isStart;
            // 重新画组件
            repaint();
        }else if (keyCode == KeyEvent.VK_UP){
            direction = Direction.top;
        }else if (keyCode == KeyEvent.VK_DOWN){
            direction = Direction.bottom;
        }else if (keyCode == KeyEvent.VK_LEFT){
            direction = Direction.left;
        }else if (keyCode == KeyEvent.VK_RIGHT){
            direction = Direction.right;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // 当够100毫秒就调用这个方法
    @Override
    public void actionPerformed(ActionEvent e) {

        if (isStart){
            // 改变身体的位置
            for (int i = len - 1; i > 0; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }

            switch (direction){
                case right:
                    // 假如头是水平向右移动，则蛇头的值+25
                    snakeX[0] += 25;
                    if (snakeX[0] >= 700){
                        snakeX[0] = 0;
                    }
                    break;
                case left:
                    snakeX[0] -= 25;
                    if (snakeX[0] <=0){
                        snakeX[0] = 700;
                    }
                    break;
                case bottom:
                    snakeY[0] += 25;
                    if (snakeY[0] >= 900){
                        snakeY[0] = 0;
                    }
                    break;
                case top:
                    snakeY[0] -= 25;
                    if (snakeY[0] <= 0){
                        snakeY[0] = 900;
                    }
                    break;
            }
            // 吃掉食物，长度加1
            if (snakeX[0] == footX && snakeY[0] == footY){
                len++;
                footX = 25 + 25 * random.nextInt(20);
                footY = 25 + 25 * random.nextInt(30);
            }
            // 重新画组件
            repaint();
        }
    }
}
