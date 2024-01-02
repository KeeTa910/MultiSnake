package client.main.MultiplayerGame;

import directions.Directions;

import javax.swing.JPanel;
import java.awt.*;

import static client.main.MultiplayerGame.MultiplayerGame.enemySnake;
import static client.main.MultiplayerGame.MultiplayerGame.snake;

/**
 * Content of the game screen
 * Receive client.inputs from gamer
 */
public class MultiplayerGamePanel extends JPanel {
    private MultiplayerKeyboardInputs multiplayerKeyboardInputs;
    int lastKeyPressed;

    public MultiplayerGamePanel() {
        multiplayerKeyboardInputs = new MultiplayerKeyboardInputs(this);
        addKeyListener(multiplayerKeyboardInputs);
        lastKeyPressed = Directions.getRight();
    }

    public void receiveKeyPressed(int direction) {
        lastKeyPressed = direction;
    }

    public void updateSnakeDirection(){
        MultiplayerGame.snake.changeDirection(lastKeyPressed);
    }

    public void updateEnemySnakeDirection(int direction ){
        MultiplayerGame.enemySnake.changeDirection(direction);
    }

    /**
     * Draw images on panel
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //both snake die -> player has higher score win
        if(enemySnake.isMultiplayerOver(snake) && snake.isMultiplayerOver(enemySnake)){
            if(snake.getScore() > enemySnake.getScore())
                new MultiplayerGameOver(snake.getScore(),"You win", g);
            else if(snake.getScore() < enemySnake.getScore())
                new MultiplayerGameOver(snake.getScore(),"You lose",g);
            else
                new MultiplayerGameOver(snake.getScore(),"Draw",g);
        }
        //enemy snakes die and player's snake still alive
        else if(enemySnake.isMultiplayerOver(snake) && !snake.isMultiplayerOver(enemySnake)){
                new MultiplayerGameOver(snake.getScore(),"You win", g);
        }
        //enemy snake still alive and player's snake dies
        else if(!enemySnake.isMultiplayerOver(snake) && snake.isMultiplayerOver(enemySnake)){
            new MultiplayerGameOver(snake.getScore(),"You lose", g);
        }
        //both alive
        else{
            //Draw food
            MultiplayerGame.food.draw(g);
            //draw snakes
            MultiplayerGame.snake.draw(g);
            MultiplayerGame.enemySnake.draw(g);
            //Print score in GameWindow
            g.setColor(Color.blue);
            g.setFont(new Font("Ink Free", Font.BOLD, 25));
            FontMetrics fontMetrics = getFontMetrics(g.getFont());
            g.drawString("Score:" + snake.getScore(), 800, 30);
        }
    }
}
