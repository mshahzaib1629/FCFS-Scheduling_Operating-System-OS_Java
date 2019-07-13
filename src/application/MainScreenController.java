package application;


import java.util.ArrayList;

import org.omg.CORBA.INITIALIZE;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class MainScreenController {
	
	
	@FXML
	Text pID;
	@FXML
	TextField arrivalTimeField;
	@FXML
	TextField burstTimeField;
	@FXML
	Text timeError;
	@FXML
	Button addButton;
	@FXML
	Button doneButton;
	@FXML
	Button resetButton;
	
	@FXML
	TableView<Process> inputTable;
	@FXML
	TableColumn<Process, String> pIDColumn;
	@FXML
	TableColumn<Process, String> arrivalTimeColumn;
	@FXML
	TableColumn<Process, String> burstTimeColumn;
	
	@FXML
	TableView<ResultSet> outputTable;
	@FXML
	TableColumn<ResultSet, String> pIDColumn2;
	@FXML
	TableColumn<ResultSet, String> completionColumn;
	@FXML
	TableColumn<ResultSet, String> turnaroundColumn;
	@FXML
	TableColumn<ResultSet, String> waitingColumn;
	
	@FXML
	Text grantChart;
	@FXML
	Text avgTurnaround;
	@FXML
	Text avgWaiting;
	
	int idGenrator = 1;
	int quantumValue;
	
	String fName[];
	int fArrivalTime[];
	int fBurstTime[];
	
	FCFS f = new FCFS();
	
	
	@FXML
	public void initialize(){
		String id = "P" + idGenrator;
		pID.setText(id);
	}
	

	public boolean validityCheck(){
		
		for(int i = 0; i < arrivalTimeField.getText().length(); i++){
			if(!Character.isDigit(arrivalTimeField.getText().charAt(i))){
				timeError.setText("Enter integers only");
				timeError.setVisible(true);
				return false;
			}
		}
		
		for(int i = 0; i < burstTimeField.getText().length(); i++){
			if(!Character.isDigit(burstTimeField.getText().charAt(i))){
				timeError.setText("Enter integers only");
				timeError.setVisible(true);
				return false;
			}
		}
		
		if(Integer.parseInt(arrivalTimeField.getText()) < 0 || Integer.parseInt(burstTimeField.getText()) < 0){
			timeError.setText("Time can not be -ve");
			timeError.setVisible(true);
			return false;
		}
		return true;

	}
	
	@FXML
	public void addButtonPushed() {
		timeError.setVisible(false);
		if (validityCheck() == true) {
			if (!pID.getText().isEmpty() && !burstTimeField.getText().isEmpty()) {

				if (arrivalTimeField.getText().isEmpty())
					arrivalTimeField.setText("0");

				Process currentProcess = new Process(pID.getText(), Integer.parseInt(arrivalTimeField.getText()),
						Integer.parseInt(burstTimeField.getText()));
				populateInputTable(currentProcess);

				idGenrator++;
				pID.setText("P" + idGenrator);

				burstTimeField.setText("");
				arrivalTimeField.setText("");
			}
		}
	}
	
	@FXML
	public void doneButtonPushed(){
		
		
		Process process = new Process();;

		int arraySize = inputTable.getItems().size();
		fName = new String[arraySize];
		fArrivalTime = new int[arraySize];
		fBurstTime = new int[arraySize];
		
		for(int i=0; i<inputTable.getItems().size(); i++){
			fName[i] = inputTable.getItems().get(i).getId();
			fArrivalTime[i] = inputTable.getItems().get(i).getArrivalTime();
			fBurstTime[i] = inputTable.getItems().get(i).getBurstTime();
			
		}

		f.applyFCFS(fName, fArrivalTime, fBurstTime, arraySize);

		populateOutputTable();

		grantChart.setText(f.getGantChart());
		avgTurnaround.setText(Float.toString(f.getAvgTurnAround()));
		avgWaiting.setText(Float.toString(f.getAvgWait()));		

	}
	
	@FXML
	public void resetButtonPushed(){
		idGenrator = 1;
		pID.setText("P" + idGenrator);
		inputTable.getItems().clear();
		outputTable.getItems().clear();
		fName = null;
		fArrivalTime = null;
		fBurstTime = null;
		avgTurnaround.setText("0");
		avgWaiting.setText("0");
		grantChart.setText("GANTT CHART HERE");
		f.reset();
		
	}
	
	@FXML
	public void populateInputTable(Process p){
		
		pIDColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("id"));
		arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("arrivalTime"));
		burstTimeColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("burstTime"));
		
		if(p != null){
			inputTable.getItems().add(p);
		}
	}
	
	@FXML
	public void populateOutputTable(){
		pIDColumn2.setCellValueFactory(new PropertyValueFactory<ResultSet, String>("id"));
		completionColumn.setCellValueFactory(new PropertyValueFactory<ResultSet, String>("completionTime"));
		turnaroundColumn.setCellValueFactory(new PropertyValueFactory<ResultSet, String>("turnAroundTime"));
		waitingColumn.setCellValueFactory(new PropertyValueFactory<ResultSet, String>("waitingTime"));
		
		
			outputTable.getItems().addAll(f.getResult());
		
	}
	

}
