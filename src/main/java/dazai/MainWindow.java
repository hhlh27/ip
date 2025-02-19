package dazai;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private DazAi dazAi;
    private Ui ui;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image dazImage = new Image(this.getClass().getResourceAsStream("/images/DazAi.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    public void setDazAi(DazAi d) {
        dazAi = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDazAiDialog(dazAi.showWelcome(), dazImage)
        );

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dazAi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDazAiDialog(response, dazImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {

            System.exit(0); // Close app on "bye" command
        }
    }
}