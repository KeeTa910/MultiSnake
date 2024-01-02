package client.main.SingleplayerGame;

import client.inputs.KeyboardInputs;
import client.inputs.MouseInputs;
import client.ui.GameOver;
import directions.Directions;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import objects.Snake;

import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JPanel;
import java.awt.*;
import static client.main.SingleplayerGame.Game.snake;
/**
 * Content of the game screen
 * Receive client.inputs from gamer
 */
public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private KeyboardInputs keyboardInputs;
    public int lastKeyPressed;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        keyboardInputs = new KeyboardInputs(this);
        addKeyListener(keyboardInputs);
        lastKeyPressed = Directions.getRight();
    }

    public void receiveKeyPressed(int direction) {
        lastKeyPressed = direction;
    }

    public void updateSnakeDirection() {
        Game.snake.changeDirection(lastKeyPressed);
    }
    /** Sound */
    private static class SoundThread extends Thread {
        private final String soundPath;

        public SoundThread(String soundPath) {
            this.soundPath = soundPath;
        }

        @Override
        public void run() {
            try {
                FileInputStream fileInputStream = new FileInputStream(soundPath);

                AdvancedPlayer player = new AdvancedPlayer(fileInputStream);
                player.setPlayBackListener(new PlaybackListener() {
                    @Override
                    public void playbackFinished(PlaybackEvent evt) {
                        // Xử lý sau khi âm thanh kết thúc
                    }
                });

                player.play();

            } catch (JavaLayerException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void playLoseSound() {
        String path = "src/assets/lose.mp3"; // Đường dẫn tới file MP3

        // Tạo và start một thread mới để phát âm thanh
        new GamePanel.SoundThread(path).start();
    }



    /**
     * Draw images on panel
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(!Game.snake.isGameOver()) {
            Game.snake.draw(g);
            //Draw food
            Game.food.draw(g);
            //Print score in GameWindow
            g.setColor(Color.blue);
            g.setFont(new Font("Ink Free", Font.BOLD, 25));
            FontMetrics fontMetrics = getFontMetrics(g.getFont());
            g.drawString("Score:" + snake.getScore(), 800, 30);
        }
        else {
            //Print string "Game Over" and point
            new GameOver(snake.getScore(), g);
            playLoseSound();
        }
    }
}
