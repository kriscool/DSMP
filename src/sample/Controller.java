package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Controller {

    @FXML
    Button featuresSelectionGetData;

    @FXML
    Button featuresSelectionSaveToFile;

    @FXML
    Button featuresSelectionCompute;

    @FXML
    ComboBox featuresSelectionFeatureNumber;

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
    ComboBox classifiersComboBoxClassifiers;

    @FXML
    ComboBox classifiersKElemnts;

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

    @FXML
    private void featureSelectionGetData(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(null);



        Scanner in;
        int iterator = 0;
        double valueTab[][]={};
        try {
            in = new Scanner(selectedFile);
            while(in.hasNextLine()){
                String zdanie = in.nextLine();
                StringTokenizer st = new StringTokenizer(zdanie);
                st.nextToken();//Pobieranie pierwszej wartosci czyli string
                while (st.hasMoreTokens()) {
                    st.nextToken();
                    iterator++;
                }

            }
            featuresSelectionTextArea.setText(Integer.toString(iterator));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    private void featureSaveFile(){}

    @FXML
    private void featureSelectionCompute(){}

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
