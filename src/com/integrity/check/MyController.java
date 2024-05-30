package com.integrity.check;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class MyController implements Initializable {

	@FXML
	public Label pathlabel;

	@FXML
	public Label hashlabel;

	@FXML
	public TextField pathTextField;

	@FXML
	public Button fileChooserbtn;

	@FXML
	public TextField originalSHA;

	@FXML
	public Button checkHash;

	private File selectedFile;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Add event handler to the fileChooserButton
		fileChooserbtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Create a FileChooser
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Select File");

				// Get the stage from any UI element in the scene
				Stage stage = (Stage) fileChooserbtn.getScene().getWindow();

				// Show the file chooser dialog
				selectedFile = fileChooser.showOpenDialog(stage);

				// If a file was selected, set the path to the pathTextField
				if (selectedFile != null) {
					pathTextField.setText(selectedFile.getAbsolutePath());
				}
			}
		});

		// Add event handler to the checkHash button
		checkHash.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (selectedFile != null) {
					try {
						String fileHash = computeSHA256Hash(selectedFile);
						String originalHash = originalSHA.getText().trim();

						if (fileHash.equalsIgnoreCase(originalHash)) {
							showAlert(Alert.AlertType.INFORMATION, "Hash Match", "The file's SHA-256 hash matches the original hash.");
						} else {
							showAlert(Alert.AlertType.ERROR, "Hash Mismatch", "The file's SHA-256 hash does not match the original hash.");
						}
					} catch (NoSuchAlgorithmException | IOException e) {
						showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while computing the hash: " + e.getMessage());
					}
				} else {
					showAlert(Alert.AlertType.WARNING, "No File Selected", "Please select a file first.");
				}
			}
		});
	}

	private String computeSHA256Hash(File file) throws NoSuchAlgorithmException, IOException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		FileInputStream fis = new FileInputStream(file);
		byte[] byteArray = new byte[1024];
		int bytesCount = 0;

		// Read the file data and update in message digest
		while ((bytesCount = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}
		fis.close();

		// Get the hash's bytes
		byte[] bytes = digest.digest();

		// Convert bytes to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
