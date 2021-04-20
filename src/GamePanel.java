import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int length;
    int[] snakeX = new int[1000];
    int[] snakeY = new int[1000];
    String direction;
    int foodX;
    int foodY;
    Random random = new Random();
    boolean isStart = false;
    boolean isFail = false;
    int score;
    //定时器
    Timer timer = new Timer(100,this);
    //构造器
    public GamePanel(){
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    public void init(){
        length = 3;
        snakeX[0] = 100;snakeY[0] = 100;
        snakeX[1] = 75;snakeY[1] = 100;
        snakeX[2] = 50;snakeY[2] = 100;
        direction = "R";
        foodX = 25 + 25 * random.nextInt(37);
        foodY = 25 + 25 * random.nextInt(24);
        score = 0;
    }



    @Override
    protected void paintComponent(Graphics g) {
        //清屏
        super.paintComponent(g);
        this.setBackground(Color.white);
        //默认的游戏界面
        g.fillRect(18,25,950,625);
        Data.food.paintIcon(this,g,foodX,foodY);
        //绘制小蛇
        if(direction.equals("R")){
            Data.headright.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if(direction.equals("L")){
            Data.headleft.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if(direction.equals("U")){
            Data.headup.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if(direction.equals("D")){
            Data.headdown.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        for (int i = 1 ; i < length ; i++){
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("当前分数为："+ score,20,20);

        if (!isStart){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格键继续",350,300);
        }
        if (isFail){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格键重新开始",300,300);
        }


    }




    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE){
            if(isFail){
                isFail = false;
                init();
            }
            else {
                isStart = !isStart;
            }
            repaint();
        }
        if ((keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) && !direction.equals("D")){
            direction = "U";
        }else if ((keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) && !direction.equals("U")){
            direction = "D";
        }else if ((keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) && !direction.equals("R")){
            direction = "L";
        }else if ((keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) && !direction.equals("L")){
            direction = "R";
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && !isFail){
            //吃食
            if (snakeX[0] == foodX && snakeY[0] == foodY){
                length++;
                score += 10;
                foodX = 25 + 25 * random.nextInt(37);
                foodY = 25 + 25 * random.nextInt(24);
            }
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            if (direction.equals("R")){
                snakeX[0] = snakeX[0] + 25;
                if (snakeX[0] > 925){
                    snakeX[0] = 25;
                }
            }else if (direction.equals("L")){
                snakeX[0] = snakeX[0] - 25;
                if (snakeX[0] < 25){
                    snakeX[0] = 925;
                }
            }else if (direction.equals("U")){
                snakeY[0] = snakeY[0] - 25;
                if (snakeY[0] < 25){
                    snakeY[0] = 625;
                }
            }else if (direction.equals("D")){
                snakeY[0] = snakeY[0] + 25;
                if (snakeY[0] > 625){
                    snakeY[0] = 25;
                }
            }
            //失败判定
            for (int i = 1;i < length;i++){
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                    break;
                }
            }
            repaint();
        }
        timer.start();
    }




    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
