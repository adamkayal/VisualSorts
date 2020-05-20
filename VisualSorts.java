import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;

public class VisualSorts extends Application {

	public static final int WIDTH = 640, HEIGHT = 480;
	int k = 0, i = 0;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		Controler controler = new Controler();
		VBox root = new VBox();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		ToggleGroup group = new ToggleGroup();

		primaryStage.setTitle("Visual Sorts");
		primaryStage.getIcons().add(new Image("icon.png"));
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();

		Background background = new Background(new BackgroundFill(Color.hsb(15,0.75,1),CornerRadii.EMPTY,Insets.EMPTY));
		Background background2 = new Background(new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY,Insets.EMPTY));

		HBox menu = new HBox();
		menu.setSpacing(12);
		menu.setAlignment(Pos.TOP_LEFT);
		menu.setPadding(new Insets(10, 5, 5, 5));
		menu.setBackground(background);

		Text select = new Text("Select\nsorting method:");
		ToggleButton insertion = new ToggleButton("Insertion Sort");
		ToggleButton selection = new ToggleButton("Selection Sort");
		ToggleButton bubble = new ToggleButton("Bubble Sort");
		ToggleButton merge = new ToggleButton("Merge Sort");
		ToggleButton quick = new ToggleButton("QuickSort");
		ToggleButton heap = new ToggleButton("HeapSort");
		insertion.setToggleGroup(group);
		selection.setToggleGroup(group);
		bubble.setToggleGroup(group);
		merge.setToggleGroup(group);
		quick.setToggleGroup(group);
		heap.setToggleGroup(group);
		insertion.setSelected(true);
		menu.getChildren().addAll(select,insertion,selection,bubble,merge,quick,heap);

		HBox menu2 = new HBox();
		menu2.setAlignment(Pos.TOP_LEFT);
		menu2.setSpacing(10);
		menu2.setPadding(new Insets(10, 5, 5, 5));
		menu2.setBackground(background);

		Text select2 = new Text("Select number of elements:");
		select2.setFont(new Font(15));
		ChoiceBox<Integer> cb = new ChoiceBox<Integer>();
		cb.getItems().addAll(5,10,20,50,100,200);
		cb.getSelectionModel().selectFirst();
		Text select3 = new Text("Select speed:");
		select3.setFont(new Font(15));
		ChoiceBox<String> cb2 = new ChoiceBox<String>();
		cb2.getItems().addAll("Fast","Slow");
		cb2.getSelectionModel().selectFirst();
		Button sort = new Button("Start sorting");
		sort.setStyle("-fx-font-size: 1.25em;");
		menu2.getChildren().addAll(select2,cb,select3,cb2,sort);

		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().addAll(menu,menu2,canvas);
		root.setBackground(background2);

		GraphicsContext context = canvas.getGraphicsContext2D();

		controler.createRect(context,cb.getValue());

		Timeline timelineFast = new Timeline(
			new KeyFrame(Duration.millis(100), e -> {
				if (!controler.isSorted()) {
					controler.draw(context);
					controler.sort(k,i);
					i++;
				}
			})
		);
		timelineFast.setCycleCount(Animation.INDEFINITE);

		Timeline timelineSlow = new Timeline(
			new KeyFrame(Duration.millis(500), e -> {
				if (!controler.isSorted()) {
					controler.draw(context);
					controler.sort(k,i);
					i++;
				}
			})
		);
		timelineSlow.setCycleCount(Animation.INDEFINITE);

		cb.setOnAction(e -> {controler.createRect(context,cb.getValue());});

		insertion.setOnAction(e -> {k=0;});
		selection.setOnAction(e -> {k=1;});
		bubble.setOnAction(e -> {k=2;});
		merge.setOnAction(e -> {k=3;});
		quick.setOnAction(e -> {k=4;});
		heap.setOnAction(e -> {k=5;});
		sort.setOnAction(e -> {
			i=0;
			controler.setSorted(false);
			if (cb2.getValue().equals("Fast")) {
				timelineSlow.stop();
				timelineFast.play();
			}
			else if (cb2.getValue().equals("Slow")) {
				timelineFast.stop();
				timelineSlow.play();
			}
		});

		scene.setOnKeyPressed((value) -> {
			if (value.getCode() == KeyCode.ENTER) {
				i=0;
				controler.setSorted(false);
				if (cb2.getValue().equals("Fast")) {
					timelineSlow.stop();
					timelineFast.play();
				}
				else if (cb2.getValue().equals("Slow")) {
					timelineFast.stop();
					timelineSlow.play();
				}
			}
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}