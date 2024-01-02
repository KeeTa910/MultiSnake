

package directions;

import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.*;

public class Directions {
    private static final int UP = VK_W;
    private static final int DOWN = VK_S;
    private static final int LEFT = VK_A;
    private static final int RIGHT = VK_D;

    private static final int UP_ARROW = VK_UP;
    private static final int DOWN_ARROW = VK_DOWN;
    private static final int LEFT_ARROW = VK_LEFT;
    private static final int RIGHT_ARROW = VK_RIGHT;

    public Map<Integer, String> keyToPosChange = new HashMap<>();

    public Directions() {
        keyToPosChange.put(UP_ARROW, "0,-40");
        keyToPosChange.put(DOWN_ARROW, "0,40");
        keyToPosChange.put(LEFT_ARROW, "-40,0");
        keyToPosChange.put(RIGHT_ARROW, "40,0");
    }

//    public static int getUp() {
//        return UP;
//    }
//
//    public static int getDown() {
//        return DOWN;
//    }
//
//    public static int getLeft() {
//        return LEFT;
//    }
//
//    public static int getRight() {
//        return RIGHT;
//    }


    public static int getUp() {
        return UP_ARROW;
    }

    public static int getDown() {
        return DOWN_ARROW;
    }

    public static int getLeft() {
        return LEFT_ARROW;
    }

    public static int getRight() {
        return RIGHT_ARROW;
    }

    public static String getAxis(int direction) {
        if (direction == UP_ARROW || direction == DOWN_ARROW)
            return "vertical";

        if (direction == LEFT_ARROW || direction == RIGHT_ARROW)
            return "horizontal";
        return "-1";
    }
}




//package directions;
//
//        import javax.swing.*;
//        import java.util.HashMap;
//        import java.util.Map;
//
//        import static java.awt.event.KeyEvent.*;
//
//public class Directions {
//    private static final int up = VK_W;
//    private static final  int down = VK_S ;
//    private static final int left = VK_A;
//    private static final int right = VK_D;
//
//    public static int getUp() {
//        return VK_W;
//    }
//
//    public static int getDown() {
//        return VK_S;
//    }
//
//    public static int getLeft() {
//        return VK_A;
//    }
//
//    public static int getRight() {
//        return VK_D;
//    }
//
//    public static String getAxis(int direction) {
//        if (direction == up || direction == down)
//            return "vertical";
//
//        if (direction == left || direction == right)
//            return "horizontal";
//        return "-1";
//    }
//
//}