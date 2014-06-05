package nao.cycledev.todolist.desk;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import nao.cycledev.todolist.desk.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskDialogController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(TaskDialogController.class);

    private Stage dialogStage;
    private Task task;
    private boolean okClicked = false;

	@FXML private TextField txtTaskName;
    @FXML private ComboBox cbTaskPriority;
    @FXML private CheckBox cbTaskIsFinished;
    @FXML private DatePicker dpExpirationDate;
	@FXML private TextArea txtTaskRemark;
	
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;

    }
	
	@FXML
	private void handleOKEvent() {
		
	    logger.debug("handleOKEvent");
        //project.setProjectTitle(txtEventTitle.getText().trim());
		//project.setProjectDesc(txtEventDesc.getText().trim());

       // dataManager.saveEvent(event);

		okClicked = true;
		dialogStage.close();

	}
	
	@FXML
	private void handleCancelEvent() {
		
		logger.debug("handleCancelEvent");
        okClicked = false;
		dialogStage.close();
	 
	}
	
	public boolean isOkClicked() {

		return okClicked;

	}
	
	public void setTask(Task task) {

		this.task = task;
		txtTaskName.setText(this.task.getTaskTitle());
        txtTaskRemark.setText(this.task.getTaskDesc());
        cbTaskIsFinished.setSelected(this.task.getTaskStatus());
        //cbTaskPriority.;


	}
}
