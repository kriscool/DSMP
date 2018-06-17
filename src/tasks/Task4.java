package tasks;

public class Task4 {
	 public double getSimpleClassifierKroswalidation(int beginA,int endA,int beginB,int endB,String choose,double[][] matrixA,double[][] matrixB,double[][] matrixToTrainingA,double[][] matrixToTrainingB,int knnValue){
	    	int countA=0;
	    	int countB=0;
	    	Task3 task = new Task3();
	    	int sum=0;
	    	int sumB=0;
	    	if(choose.equals("KNN")){
	    		for(int i=0;i<matrixA[0].length ;i++){
	    			if(i<beginA || (i>endA && endA<matrixA[0].length )){
		    			sum++;
		    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, knnValue, changeColumnToRow(matrixA,i)).equals("a")){
		    				countA++;
		    			}
		    		}
	    		}
	    		for(int i=0; i<matrixB[0].length;i++){
	    			if(i<beginB || (i>endB && endB<matrixB[0].length)){
	    			sumB++;
	    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, knnValue, changeColumnToRow(matrixB,i)).equals("b")){
	        			countB++;
	    			}}
	    		}
	    	}else if(choose.equals("NN")){
	    		for(int i=0; i<matrixA[0].length;i++){
	    			if(i<beginA || (i>endA && endA<matrixA[0].length )){
		    			sum++;
		    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, 1, changeColumnToRow(matrixA,i)).equals("a")){
		    				countA++;
		    			}
	    			}
	    		}
	    		for(int i=0; i<matrixB[0].length;i++){
	    			if(i<beginB || (i>endB && endB<matrixB[0].length)){
	    			sumB++;
	    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, 1, changeColumnToRow(matrixB,i)).equals("b")){
	        			countB++;
	    			}
	    			}
	    		}
	    	}else{
	    		for(int i=0;i<matrixA[0].length;i++){
	    			if(i<beginA || (i>endA && endA<matrixA[0].length )){
		    			sum++;
		    			if(task.MN(matrixToTrainingA, matrixToTrainingB, changeColumnToRow(matrixA,i)).equals("a")){
		    				countA++;
		    			}
	    			}
	    		}
	    		for(int i=0;i<matrixB[0].length;i++){
	    			if(i<beginB || (i>endB && endB<matrixB[0].length)){
		    			sumB++;
		    			if(task.MN(matrixToTrainingA, matrixToTrainingB, changeColumnToRow(matrixB,i)).equals("b")){
		        			countB++;
		    			}
	    			}
	    		}
	    	}
	    	double skutecznoscA=countA/(double)sum*100;
	    	double skutecznoscB=countB/(double)sumB*100;
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
	    
	    private Boolean isInIndex(int index,int[] tab){
	    	for(int i=0;i<tab.length;i++){
	    		if(tab[i]==index){
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    
	    public double getSimpleClassifierBootstrap(String choose,double[][] matrixA,double[][] matrixB,double[][] matrixToTrainingA,double[][] matrixToTrainingB,int knnValue,int[] trainIndex,int[] trainIndexB){
	    	int countA=0;
	    	int countB=0;
	    	Task3 task = new Task3();
	    	int sum=0;
	    	int sumB=0;
	    	if(choose.equals("KNN")){
	    		for(int i=0;i<matrixA[0].length ;i++){
	    			if(!isInIndex(i,trainIndex)){
	    				sum++;
		    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, knnValue, changeColumnToRow(matrixA,i)).equals("a")){
		    				countA++;
		    			}
	    			}
	    		}
	    		for(int i=0;i<matrixB[0].length;i++){
	    			if(!isInIndex(i,trainIndexB)){
	    				sumB++;
	    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, knnValue, changeColumnToRow(matrixB,i)).equals("b")){
	        			countB++;
	    			}
	    			}
	    		}
	    	}else if(choose.equals("NN")){
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
	    
	    
	    public double getSimpleClassifier(int beginA,int endA,int beginB,int endB,String choose,double[][] matrixA,double[][] matrixB,double[][] matrixToTrainingA,double[][] matrixToTrainingB,int knnValue){
	    	int difference=matrixA[0].length-matrixToTrainingA[0].length;
	    	int differenceB=matrixB[0].length-matrixToTrainingB[0].length;
	    	int countA=0;
	    	int countB=0;
	    	Task3 task = new Task3();
	    	if(choose.equals("KNN")){
	    		for(int i=beginA;i<endA;i++){
	    			if(task.KNN(matrixToTrainingA, matrixToTrainingB, knnValue, changeColumnToRow(matrixA,i)).equals("a")){
	    				countA++;
	    			}
	    		}
	    		for(int i=beginB;i<endB;i++){
	    			if(task.KNN(matrixToTrainingA, matrixToTrainingB,knnValue, changeColumnToRow(matrixB,i)).equals("b")){
	        			countB++;
	    			}
	    		}
	    	}else if(choose.equals("NN")){
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
	    
}
