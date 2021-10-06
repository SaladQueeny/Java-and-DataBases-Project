package application;
	


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
//Êîëïàêîâ Åãîð

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {//àêòèâèðîâàíèå îêíà
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("StartWindow.fxml"));
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image("application/MainIcon.png"));//Èçìåíåíèå èêîíêè ïðèëîæåíèÿ
			primaryStage.setTitle("Access control system");//èçìåíåíèå íàçâàíèÿ ïðèëîæåíèÿ
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);//çàïóñê ïðèëîæåíèÿ
	}
}
