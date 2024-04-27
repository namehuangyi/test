package practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MyPanel extends JPanel implements KeyListener, ActionListener {

    int len = 3;
    int[] snakeX = new int[1008];
    int[] snakeY = new int[1008];

    ImageIcon right = new ImageIcon("image/right.jpg");
    ImageIcon left = new ImageIcon("image/left.jpg");
    ImageIcon up = new ImageIcon("image/top.jpg");
    ImageIcon down = new ImageIcon("image/bottom.jpg");
    ImageIcon body = new ImageIcon("image/body.jpg");

    ImageIcon food = new ImageIcon("image/food.jpg");

    boolean isStart = false;

    Direction direction = Direction.right;

    Timer timer = new Timer(100, this);

    Random random = new Random();

    int foodX;
    int foodY;
    public MyPanel() {
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
        foodX = 25 * random.nextInt(28);
        foodY = 25 * random.nextInt(36);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.red);
        g.fillRect(0, 0, 700, 900);

        switch (direction){
            case right:
                right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case left:
                left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case up:
                up.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case down:
                down.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }

        for (int i = 1; i < len; i++) {
            body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        if (!isStart){
            g.setColor(Color.white);
            g.setFont(new Font("宋体", Font.BOLD, 50));
            g.drawString("请按空格键开始游戏", 50, 500);
        }
        food.paintIcon(this, g, foodX, foodY);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 32){
            isStart = !isStart;
            repaint();
        }else if (keyCode == KeyEvent.VK_UP){
            direction = Direction.up;
        }else if (keyCode == KeyEvent.VK_DOWN){
            direction = Direction.down;
        }else if (keyCode == KeyEvent.VK_LEFT){
            direction = Direction.left;
        }else if (keyCode == KeyEvent.VK_RIGHT){
            direction = Direction.right;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart){
            for (int i = len - 1; i > 0; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            switch (direction){
                case down:
                    snakeY[0] += 25;
                    if (snakeY[0] >= 900){
                        snakeY[0] = 0;
                    }
                    break;
                case up:
                    snakeY[0] -= 25;
                    if (snakeY[0] <= 0){
                        snakeY[0] = 900;
                    }
                    break;
                case left:
                    snakeX[0] -= 25;
                    if (snakeX[0] <= 0){
                        snakeX[0] = 700;
                    }
                    break;
                case right:
                    snakeX[0] += 25;
                    if (snakeX[0] >= 700){
                        snakeX[0] = 0;
                    }
                    break;
            }
            if (snakeX[0] == foodX && snakeY[0] == foodY){
                len++;
                foodX = 25 * random.nextInt(28);
                foodY = 25 * random.nextInt(36);
            }
            repaint();
        }
    }
}
