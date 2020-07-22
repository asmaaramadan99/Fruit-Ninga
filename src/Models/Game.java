package Models;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Game {

	double mouseX;
	double speed;
	double falling;
	AnimationTimer timer;

	private static Game instance;

	private Game() {
		if (instance != null)
			throw new RuntimeException("use getInstance method");
	}

	public static Game getInstance() {
		if (instance == null) {
			synchronized (GameLogic.class) {
				if (instance == null) {
					instance = new Game();
				}
			}
		}
		return instance;
	}

	private GameLogic gameLogic = new GameLogic();
	private int score = 0;
	private int lives = 3;
	private ArrayList<GameObject> list = new ArrayList<GameObject>();

	public void start(Pane root) {

		EasyEquationGenerator eg = new EasyEquationGenerator(1200, 800);

		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), event -> {

			GameObject object = gameLogic.createGameObject();
			((GameObject) object).setEq(eg.generateEquation());
			list.add(object);
			root.getChildren().add(((GameObject) list.get(list.size() - 1)).getImageView());
		}));

		timeline.setCycleCount(1000);
		timeline.play();

		timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {

				gameUpdate(list);

			}

		};

		timer.start();
		
		Timeline timeline2 =new Timeline(new KeyFrame(Duration.seconds(10), event -> {
			
			GameObject object=list.get(list.size()-1);
			if(object instanceof Fruit) {
				
				Fruit f=new FruitDecorator(object);
				Fruit l=new ScoreDecorator(f);
				Fruit m=new SliceAllDecorator(f);
				Fruit n=new SlowDownDecorator(f);
			    Fruit []arr= {l,m,n};
			    int index=(int) MiscUtils.rand(0,2);
			    list.add(arr[index]);
				root.getChildren().add(((GameObject) list.get(list.size() - 1)).getImageView());

			}
			
			
		}));
		timeline2.setCycleCount(1000);
		timeline2.play();
		
		AnimationTimer timer2 = new AnimationTimer() {

			@Override
			public void handle(long arg0) {

				gameUpdate(list);

			}

		};
		timer2.start();


	}

	public void gameUpdate(ArrayList<GameObject> list) {
		for (GameObject object : list) {
			object.move(System.currentTimeMillis());
		}
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLives() {
		return lives;
	}

	public int getScore() {
		return score;
	}

	public void gameOver() {

	}

	public ArrayList<GameObject> getList() {
		return list;
	}

}