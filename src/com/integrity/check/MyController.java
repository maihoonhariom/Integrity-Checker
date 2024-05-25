package com.integrity.check;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MyController implements Initializable {

	@FXML
	public Label pathlabel;

	@FXML
	public Label hashlabel;

	@FXML
	public TextField pathTextField;

	@FXML
	public Button fileChooser;

	@FXML
	public TextField originalSHA;

	@FXML
	public Button checkHash;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		fileChooser.setOnAction(event -> {
			
		});
	}
}
