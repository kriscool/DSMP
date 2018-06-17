package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

import application.DataObject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tasks.LoadFromFile;
import tasks.LosowanieBezPotworzen;
import tasks.Task3;
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
    ComboBox<Integer> classifiersIterationNumberBootstrap;
    @FXML
    Button classifiersExecute;

    @FXML
    TextField classifiersTrainingPart;

    @FXML
    TextArea classifiersTextArea;

    @FXML
    ComboBox<String> classifiersComboBoxClassifiers;

    @FXML
    ComboBox<Integer> classifiersKElemnts;
    
    @FXML
    ComboBox<String> classifiersMethodToClissiferComboBox;
    
    private LoadFromFile db = new LoadFromFile();
    private String results[][] ;
    private double[][] matrixToTrainingA;
    private double[][] matrixToTrainingB;
    private double[][] matrixA;
    private double[][] matrixB;
    public void initialize(){
        for(int i=1;i<65;i++){
            featuresSelectionFeatureNumber.getItems().add(i);
        }
        
        for(int i=1;i<5;i++){
            classifiersIterationNumberBootstrap.getItems().add(i);
        }
        classifiersComboBoxClassifiers.getItems().add("KNN");

        classifiersComboBoxClassifiers.getItems().add("NN");

        classifiersComboBoxClassifiers.getItems().add("NM");

        classifiersMethodToClissiferComboBox.getItems().add("Normal");
        classifiersMethodToClissiferComboBox.getSelectionModel().select(0);
        classifiersMethodToClissiferComboBox.getItems().add("Bootstrap");
        classifiersMethodToClissiferComboBox.getItems().add("Kroswalidation");
        
        for(int i=1;i<4;i++){
            classifiersKElemnts.getItems().add(i);
        }

    }

    
    @FXML
    private void featureSelectionGetData() throws FileNotFoundException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(null);
        results =db.load(selectedFile);
        matrixA = crtMatrixClassA(results);
        matrixB = crtMatrixClassB(results);
    }



    @FXML
    private void featureSaveFile(){}

    @FXML
    private void featureSelectionCompute() throws Exception{
    	Zadanie_1 zad1 = new Zadanie_1();
    	if(featuresSelectionFeatureNumber.getValue()==1){
    	featuresSelectionTextArea.setText(zad1.fisherImpl_1D(results).get(0));
    	}else{
    		List<String> a= zad1.fisherImpl_ND(featuresSelectionFeatureNumber.getValue(),results);
    		String output="";
    		for(String aS:a){
    			output+=aS;
    		}
    		featuresSelectionTextArea.setText(output);
    	}
    }

    @FXML
    private void preprocessingSelectFodler(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(new Stage());
    }

    @FXML
    private void classifersGetData() throws FileNotFoundException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(null);
        results =db.load(selectedFile);
        matrixA = crtMatrixClassA(results);
        matrixB = crtMatrixClassB(results);
        System.out.println(matrixA[63].length);
    }

    @FXML
    private void classifiersSaveFile(){}
    private int iteration;
    private double percent;
    @FXML
    private void classifiersTrain(){
    	percent = Integer.parseInt(classifiersTrainingPart.getText())/100.0f;
    	int howManyToTrainA=(int) (matrixA[0].length*percent);
    	int howManyToTrainB=(int) (matrixB[0].length*percent);
    	matrixToTrainingA=new double[64][howManyToTrainA];
    	matrixToTrainingB=new double[64][howManyToTrainB];
    	iteration=(int) (100/(percent*100));
    	for(int i=0;i<64;i++){
    		for(int j=0;j<howManyToTrainA;j++){
    			matrixToTrainingA[i][j]=matrixA[i][j];
    		}
    	}
    	
    	for(int i=0;i<64;i++){
    		for(int j=0;j<howManyToTrainB;j++){
    			matrixToTrainingB[i][j]=matrixB[i][j];
    		}
    	}
    }

    @FXML
    private void classifiersExecute(){
    	if(classifiersMethodToClissiferComboBox.getValue().equals("Kroswalidation")){
    		double sum=0.0;
    		int beginA=0;
    		int beginB=0;
    		for(int i=0;i<iteration;i++){
    		
    		int howManyToTrainA=(int) (matrixA[0].length*percent);
        	int howManyToTrainB=(int) (matrixB[0].length*percent);
    		matrixToTrainingA=new double[64][howManyToTrainA];
        	matrixToTrainingB=new double[64][howManyToTrainB];
        	int endA=matrixToTrainingA[0].length;
        	int endB=matrixToTrainingB[0].length;
        	for(int j=0;j<64;j++){
        		for(int a=beginA;a<endA;a++){
        			matrixToTrainingA[j][a]=matrixA[j][a];
        		}
        	}
        	
        	for(int k=0;k<64;k++){
        		for(int j=beginB;j<endB;j++){
        			matrixToTrainingB[k][j]=matrixB[k][j];
        		}
        	}
        	beginA+=endA;
        	endA+=matrixToTrainingA[0].length;
        	beginB+=endB;
        	endB=matrixToTrainingB[0].length;
    			sum+=getSimpleClassifierKroswalidation(beginA,endA,beginB,endB);
    			System.out.println("Suma"+sum);
    		}
    	   	classifiersTextArea.setText("Skutecznoœæ clasyfikatora " + (int)sum/iteration +"%");
    	}else if(classifiersMethodToClissiferComboBox.getValue().equals("Bootstrap")){
    		double sum=0.0;
    		for(int j=0;j<classifiersIterationNumberBootstrap.getValue();j++){
    			
    	
    		int howManyToTrainA=(int) (matrixA[0].length*percent);
        	int howManyToTrainB=(int) (matrixB[0].length*percent);
        	int[] indexTabA = new int[howManyToTrainA];
        	int[] indexTabB = new int[howManyToTrainB];
        	Random rand = new Random();
        	
        	List<Integer> wylosowane = new ArrayList<Integer>();
    		Random r = new Random();
    		while(wylosowane.size()!=howManyToTrainA){
    			int a = r.nextInt(matrixA[0].length) + 1;
    			if(!wylosowane.contains(a)){
    				wylosowane.add(a);
    			}
    		}
    		
    		for(int i = 0; i < wylosowane.size(); i++) indexTabA[i] = wylosowane.get(i);
    		wylosowane.clear();
    		while(wylosowane.size()!=howManyToTrainB){
    			int a = r.nextInt(matrixB[0].length) + 1;
    			if(!wylosowane.contains(a)){
    				wylosowane.add(a);
    			}
    		}
    		
    		for(int i = 0; i < wylosowane.size(); i++) indexTabB[i] = wylosowane.get(i);
    		wylosowane.clear();
    		
    		 sum+=getSimpleClassifierBootstrap(indexTabA,indexTabB);
    		 System.out.println(sum);
    		}

    	   	classifiersTextArea.setText("Skutecznoœæ clasyfikatora " + (int)sum/classifiersIterationNumberBootstrap.getValue() +"%");
    	}else{
    	   	classifiersTextArea.setText("Skutecznoœæ clasyfikatora " + (int)getSimpleClassifier(matrixToTrainingA[0].length,matrixA[0].length,matrixToTrainingB[0].length,matrixB[0].length) +"%");
    	}
    	
   }
    
    private double getSimpleClassifierKroswalidation(int beginA,int endA,int beginB,int endB){
    	int difference=matrixA[0].length-matrixToTrainingA[0].length;
    	int differenceB=matrixB[0].length-matrixToTrainingB[0].length;
    	int countA=0;
    	int countB=0;
    	Task3 task = new Task3();
    	if(classifiersComboBoxClassifiers.getValue().equals("KNN")){
    		for(int i=0;i<beginA || (i>endA && endA<matrixA[0].length) || i<matrixA[0].length ;i++){
    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, classifiersKElemnts.getValue(), changeColumnToRow(matrixA,i)).equals("a")){
    				countA++;
    			}
    		}
    		for(int i=0;i<beginB || (i>endB && endB<matrixB[0].length) || i<matrixB[0].length;i++){
    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, classifiersKElemnts.getValue(), changeColumnToRow(matrixB,i)).equals("b")){
        			countB++;
    			}
    		}
    	}else if(classifiersComboBoxClassifiers.getValue().equals("NN")){
    		for(int i=0;i<beginA || (i>endA && endA<matrixA[0].length) || i<matrixA[0].length;i++){
    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, 1, changeColumnToRow(matrixA,i)).equals("a")){
    				countA++;
    			}
    		}
    		for(int i=0;i<beginB || (i>endB && endB<matrixB[0].length) || i<matrixB[0].length;i++){
    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, 1, changeColumnToRow(matrixB,i)).equals("b")){
        			countB++;
    			}
    		}
    	}else{
    		for(int i=0;i<beginA || (i>endA && endA<matrixA[0].length )	 || i<matrixA[0].length;i++){
    			if(task.MN(matrixToTrainingA, matrixToTrainingB, changeColumnToRow(matrixA,i)).equals("a")){
    				countA++;
    			}
    		}
    		for(int i=0;i<beginB || (i>endB && endB<matrixB[0].length) || i<matrixB[0].length;i++){
    			if(task.MN(matrixToTrainingA, matrixToTrainingB, changeColumnToRow(matrixB,i)).equals("b")){
        			countB++;
    			}
    		}
    	}
    	double skutecznoscA=countA/(double)difference*100;
    	double skutecznoscB=countB/(double)differenceB*100;
    	System.out.println((skutecznoscA+skutecznoscB)/2);
    	return (skutecznoscA+skutecznoscB)/2;
    }
    
    
    private Boolean isInIndex(int index,int[] tab){
    	for(int i=0;i<tab.length;i++){
    		if(tab[i]==index){
    			return true;
    		}
    	}
    	return false;
    }
    
    
    
    private double getSimpleClassifierBootstrap(int[] trainIndex,int[] trainIndexB){
    	int difference=matrixA[0].length-matrixToTrainingA[0].length;
    	int differenceB=matrixB[0].length-matrixToTrainingB[0].length;
    	int countA=0;
    	int countB=0;
    	Task3 task = new Task3();
    	int sum=0;
    	int sumB=0;
    	if(classifiersComboBoxClassifiers.getValue().equals("KNN")){
    		for(int i=0;i<matrixA[0].length ;i++){
    			if(!isInIndex(i,trainIndex)){
    				sum++;
	    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, classifiersKElemnts.getValue(), changeColumnToRow(matrixA,i)).equals("a")){
	    				countA++;
	    			}
    			}
    		}
    		for(int i=0;i<matrixB[0].length;i++){
    			if(!isInIndex(i,trainIndexB)){
    				sumB++;
    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, classifiersKElemnts.getValue(), changeColumnToRow(matrixB,i)).equals("b")){
        			countB++;
    			}
    			}
    		}
    	}else if(classifiersComboBoxClassifiers.getValue().equals("NN")){
    		for(int i=0; i<matrixA[0].length ;i++){
	    			if(isInIndex(i,trainIndex)==false){
	    				sum++;
		    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, 1, changeColumnToRow(matrixA,i)).equals("a")){
		    				countA++;
	    			}
    			}
    		}
    		for(int i=0;i<matrixB[0].length;i++){
    			if(isInIndex(i,trainIndexB)==false){
    				sumB++;
	    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, 1, changeColumnToRow(matrixB,i)).equals("b")){
	        			countB++;
	    			}
    			}
    		}
    	}else{
    		for(int i=0; i<matrixA[0].length ;i++){
    			if(isInIndex(i,trainIndex)==false){
    				sum++;
	    			if(task.MN(matrixToTrainingA, matrixToTrainingB, changeColumnToRow(matrixA,i)).equals("a")){
	    				countA++;
	    			}
    			}
    		}
    		for(int i=0;i<matrixB[0].length;i++){
    			if(isInIndex(i,trainIndexB)==false){
    				sumB++;
	    			if(task.MN(matrixToTrainingA, matrixToTrainingB, changeColumnToRow(matrixA,i)).equals("b")){
	        			countB++;
	    			}
    			}
    		}
    	}
    	double skutecznoscA=countA/(double)sum*100;
    	double skutecznoscB=countB/(double)sumB*100;
    	return (skutecznoscA+skutecznoscB)/2;
    }
    private double getSimpleClassifier(int beginA,int endA,int beginB,int endB){
    	int difference=matrixA[0].length-matrixToTrainingA[0].length;
    	int differenceB=matrixB[0].length-matrixToTrainingB[0].length;
    	int countA=0;
    	int countB=0;
    	Task3 task = new Task3();
    	if(classifiersComboBoxClassifiers.getValue().equals("KNN")){
    		for(int i=beginA;i<endA;i++){
    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, classifiersKElemnts.getValue(), changeColumnToRow(matrixA,i)).equals("a")){
    				countA++;
    			}
    		}
    		for(int i=beginB;i<endB;i++){
    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, classifiersKElemnts.getValue(), changeColumnToRow(matrixB,i)).equals("b")){
        			countB++;
    			}
    		}
    	}else if(classifiersComboBoxClassifiers.getValue().equals("NN")){
    		for(int i=beginA;i<endA;i++){
    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, 1, changeColumnToRow(matrixA,i)).equals("a")){
    				countA++;
    			}
    		}
    		for(int i=beginB;i<endB;i++){
    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, 1, changeColumnToRow(matrixB,i)).equals("b")){
        			countB++;
    			}
    		}
    	}else{
    		for(int i=beginA;i<endA;i++){
    			if(task.MN(matrixToTrainingA, matrixToTrainingB, changeColumnToRow(matrixA,i)).equals("a")){
    				countA++;
    			}
    		}
    		for(int i=beginB;i<endB;i++){
    			if(task.MN(matrixToTrainingA, matrixToTrainingB, changeColumnToRow(matrixB,i)).equals("b")){
        			countB++;
    			}
    		}
    	}
    	double skutecznoscA=countA/(double)difference*100;
    	double skutecznoscB=countB/(double)differenceB*100;
    	return (skutecznoscA+skutecznoscB)/2;
    }
    private double[] changeColumnToRow(double[][] a,int column){
    	double[] temp=new double[a.length];
    	for(int i=0;i<a.length;i++){
    		for(int j=0;j<a[0].length;j++){
    			if(j==column){
    				temp[i]=a[i][j];
    			}
    		}
    	}
    	return temp;
    }
    
    double[][] crtMatrixClassA(String[][] baza) {

		int licznosc_klasy_a = 0;

		for (int i = 0; i < baza[0].length; i++) {
			if (baza[0][i].matches("Acer(.*)")) {
				licznosc_klasy_a++;
			}
		}
		double[][] matrixClassA = new double[baza.length - 1][licznosc_klasy_a];
		for (int i = 1; i < baza.length; i++) {

			for (int j = 0; j < licznosc_klasy_a; j++) {
				matrixClassA[i - 1][j] = Double.parseDouble(baza[i][j]);
			}
		}
		return matrixClassA;
	}

	double[][] crtMatrixClassB(String[][] baza) {
		int licznosc_klasy_b = 0;
		for (int i = 0; i < baza[0].length; i++) {
			if (!(baza[0][i].matches("Acer(.*)"))) {
				licznosc_klasy_b++;
			}
		}
		double[][] matrixClassB = new double[baza.length - 1][licznosc_klasy_b];
		for (int i = 1; i < baza.length; i++) {
			for (int j = licznosc_klasy_b; j > 0; j--) {
				matrixClassB[i - 1][licznosc_klasy_b - j] = Double.parseDouble(baza[i][baza[0].length - j]);
			}
		}
		return matrixClassB;
	}


}
