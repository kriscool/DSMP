package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import application.DataObject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tasks.LoadFromFile;
import tasks.Zadanie_1;

public class SPMDController {

    @FXML
    Button featuresSelectionGetData;

    @FXML
    Button featuresSelectionSaveToFile;

    @FXML
    Button featuresSelectionCompute;

    @FXML
    ComboBox<Integer> featuresSelectionFeatureNumber;

    @FXML
    TextArea featuresSelectionTextArea;

    @FXML
    Button preprocessingSelectFolder;

    @FXML
    Button classifiersTrain;

    @FXML
    Button classifiersGetData;

    @FXML
    Button classifiersSaveFile;

    @FXML
    Button classifiersExecute;

    @FXML
    TextField classifiersTrainingPart;

    @FXML
    TextArea classifiersTextArea;

    @FXML
    ComboBox<Integer> classifiersComboBoxClassifiers;

    @FXML
    ComboBox<Integer> classifiersKElemnts;
    
    private LoadFromFile db = new LoadFromFile();
    private String results[][] ;
    
    public void initialize(){
        for(int i=1;i<65;i++){
            featuresSelectionFeatureNumber.getItems().add(i);
        }

        for(int i=1;i<4;i++){
            classifiersComboBoxClassifiers.getItems().add(i);
        }

        for(int i=1;i<4;i++){
            classifiersKElemnts.getItems().add(i);
        }

    }
    List<DataObject> listOfObjectAcer = new ArrayList<DataObject>();
    List<DataObject> listOfObjectQuerus = new ArrayList<DataObject>();
    double[][] datasetAcer;
    double[][] datasetQuerus;
    
    @FXML
    private void featureSelectionGetData() throws FileNotFoundException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(null);
        results =db.load(selectedFile);
    }



    @FXML
    private void featureSaveFile(){}

    @FXML
    private void featureSelectionCompute() throws FileNotFoundException{
    	if(featuresSelectionFeatureNumber.getValue()==1){
    	Zadanie_1 zad1 = new Zadanie_1();
    	featuresSelectionTextArea.setText(zad1.fisherImpl_1D(results).get(0));
    	}else{
    		
    	}
    }

    @FXML
    private void preprocessingSelectFodler(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(new Stage());
    }

    @FXML
    private void classifersGetData(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(new Stage());
    }

    @FXML
    private void classifiersSaveFile(){}

    @FXML
    private void classifiersTrain(){}

    @FXML
    private void classifiersExecute(){}



}
