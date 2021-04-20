import javax.swing.*;
import java.net.URL;

public class Data {
    public static URL bodyURL = Data.class.getResource("static_pic/body.png");
    public static URL headupURL = Data.class.getResource("static_pic/headup.png");
    public static URL headdownURL = Data.class.getResource("static_pic/headdown.png");
    public static URL headleftURL = Data.class.getResource("static_pic/headleft.png");
    public static URL headrightURL = Data.class.getResource("static_pic/headright.png");
    public static URL foodURL = Data.class.getResource("static_pic/food.png");

    public static ImageIcon body = new ImageIcon(bodyURL);
    public static ImageIcon headup = new ImageIcon(headupURL);
    public static ImageIcon headdown = new ImageIcon(headdownURL);
    public static ImageIcon headleft = new ImageIcon(headleftURL);
    public static ImageIcon headright = new ImageIcon(headrightURL);
    public static ImageIcon food = new ImageIcon(foodURL);
}
