package com.integrity.check;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MyMain extends Application{

	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));
		VBox rootNode = loader.load();

		MenuBar menuBar = createMenu();

		rootNode.getChildren().add(0,menuBar);

		Scene scene = new Scene(rootNode,600,400);

		primaryStage.setScene(scene);
		primaryStage.setTitle("File Integrity Checker");
		primaryStage.show();
	}

	private MenuBar createMenu(){

		SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
		// File Menu
		Menu fileMenu = new Menu("File");

		MenuItem newMenuItem = new MenuItem("New");


		MenuItem quitMenuItem = new MenuItem("Quit");

		quitMenuItem.setOnAction(event -> {
			Alert confirmExit = new Alert(Alert.AlertType.CONFIRMATION);
			confirmExit.setHeaderText("Do you Want to Exit");

			ButtonType yesButton = new ButtonType("Yes");
			ButtonType noButton = new ButtonType("NO");
			confirmExit.getButtonTypes().setAll(yesButton,noButton);
			Optional<ButtonType> buttonClick = confirmExit.showAndWait();

			if(buttonClick.isPresent() && buttonClick.get() == yesButton)
			{
				Platform.exit();
				System.exit(0);
			}

		});

		fileMenu.getItems().addAll(newMenuItem,separatorMenuItem,quitMenuItem);

		// Help Menu
		Menu helpMenu = new Menu("Help");
		MenuItem aboutMenuItem = new MenuItem("About");
		aboutMenuItem.setOnAction(event -> aboutApp()); // About app button clicked

		helpMenu.getItems().addAll(aboutMenuItem);

		// MenuBar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu,helpMenu);


		return menuBar;
	}

	private void aboutApp() {     // About app function or method
		Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
		alertDialog.setTitle("About This Application");
		alertDialog.setHeaderText("Information...");
		alertDialog.setContentText("This is a Tool Build By \"Hariom Singh Rajput\" and \"Sameer Chighariya\". This Tool is use for the Checking of integrity of any file.");

		ButtonType close = new ButtonType("Close");
		alertDialog.getButtonTypes().setAll(close);

		alertDialog.show();

	}


	@Override
	public void stop() throws Exception {
		super.stop();
	}
}

