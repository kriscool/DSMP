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
    List<DataObject> listOfObjectAcer = new ArrayList<DataObject>();
    List<DataObject> listOfObjectQuerus = new ArrayList<DataObject>();
    double[][] datasetAcer;
    double[][] datasetQuerus;
    @FXML
    private void featureSelectionGetData(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(null);



        Scanner in;

        int iteratorRow = 0;
        try {

            in = new Scanner(selectedFile);
            while(in.hasNextLine()){
                DataObject data = new DataObject();
                String zdanie = in.nextLine();
                StringTokenizer st = new StringTokenizer(zdanie, ",");
                data.setName(st.nextToken());
                while (st.hasMoreTokens()) {
                    data.setDataInColumn(Double.parseDouble(st.nextToken()), iteratorRow);
                    iteratorRow++;
                }
                iteratorRow=0;
                if(data.getName().contains("Acer")){
                    listOfObjectAcer.add(data);
                }else{
                    listOfObjectQuerus.add(data);
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        datasetAcer= new double[listOfObjectAcer.size()][64];
        datasetQuerus=new double[listOfObjectQuerus.size()][64];
        int indexTabAcer=0;
        for(DataObject object:listOfObjectAcer){
            for(int i=0;i<64;i++){
                datasetAcer[indexTabAcer][i]=object.getData()[i];
            }
            indexTabAcer++;
        }
        int indexTabQuerus=0;
        for(DataObject object:listOfObjectQuerus){
            for(int i=0;i<64;i++){
                datasetQuerus[indexTabQuerus][i]=object.getData()[i];
            }
            indexTabQuerus++;

        }

       // Matrix a = new Matrix(datasetAcer);

    //    datasetAcer= a.transpose().getArray();;
   //     Matrix b = new Matrix(datasetQuerus);
//
    //    datasetQuerus= a.transpose().getArray();;

    }



    @FXML
    private void featureSaveFile(){}

    @FXML
    private void featureSelectionCompute(){
      //  Fischer f = new Fischer();
      //  featuresSelectionTextArea.setText(Double.toString(f.fisher(datasetAcer,datasetQuerus,new int[]{featuresSelectionFeatureNumber.getValue()})));
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
