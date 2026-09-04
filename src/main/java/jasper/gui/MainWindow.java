package jasper.gui;

import jasper.Jasper;
import jasper.command.CommandResult;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Provides controller logic for the main window of the GUI.
 */
public class MainWindow {
    /** List view containing the conversational dialog boxes */
    @FXML
    private ListView<DialogBox> dialogs;
    /** Text field for the user to input commands */
    @FXML
    private TextField userInput;
    /** Button used to send the user's input */
    @FXML
    private Button sendButton;
    /** Main application logic instance */
    private Jasper jasper;

    /** Image representing the user's avatar */
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/user.jpg"));
    /** Image representing Jasper's avatar */
    private final Image jasperImage = new Image(getClass().getResourceAsStream("/images/jasper.jpg"));

    /**
     * Initializes controller by disabling default list view selection behavior and styling.
     */
    @FXML
    public void initialize() {
        dialogs.setFocusTraversable(false);
    }

    /**
     * Sets the main Jasper instance used to handle application logic.
     *
     * @param j Jasper application instance.
     */
    public void setJasper(Jasper j) {
        jasper = j;
    }

    /**
     * Processes user input, generates a response and appends both to dialog container. Also, clears the user input
     * field after processing and schedules application termination if quit command is executed.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResult result = jasper.getResult(input);
        dialogs.getItems().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJasperDialog(result, jasperImage)
        );
        dialogs.scrollTo(dialogs.getItems().size() - 1);
        userInput.clear();

        if (result.isQuit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
