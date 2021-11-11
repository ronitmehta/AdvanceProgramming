package Assignment3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class matrix{
    private int row;
    private int column;
    private double [][] mat;
    private int id;
    private static int carryID = 0;
    private int isOneOrIdentity = 0 ;  // if less than zero then identity if greater than zero then ones matrix 
    private ArrayList<Object> store = new ArrayList<>();
    static HashMap<Integer, matrix> Matrix= new HashMap<>();
    helper hp = new helper();


    matrix(){} // pseudo constructor 

    matrix(double [][]mat){
        int row = mat.length;
        int column = mat[0].length ;
        setRow(row);
        setColumn(column);
        setMatrix(mat);
        setID(carryID);
        carryID++;
        updateLabel(this);
        Matrix.put(getID(), this);
    }

    matrix(double [][]mat, int n){
        int row = mat.length;
        int column = mat[0].length ;
        setRow(row);
        setColumn(column);
        setMatrix(mat);
        updateLabel(this);
    }

    public void setList(ArrayList<Object> arr){
        this.store = arr;
    }

    public void updateLabel(matrix mat){
        hp.addLabels(this);
    }

    public void setIsOneOrIdentiy(int c){
        this.isOneOrIdentity = c;
    }

    public int getIsOneOrIdentity(){
        return this.isOneOrIdentity;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return this.id;
    }

    public void addElements(Object ob){
        this.store.add(ob);
    }

    public ArrayList<Object> getArrayList(){
        return this.store;
    }

    public void setRow(int row){
        this.row = row;
    }

    public int getRow(){
        return this.row;
    }

    public void setColumn(int column){
        this.column = column;
    }

    public int getColumn(){
        return this.column;
    }

    public void setMatrix(double [][]mat ){
        this.mat = mat;
    }

    public double[][] getMatrix(){
        return this.mat;
    }

    public boolean equal(double[][] transpose, double[][] ds){
        if(transpose.length == ds.length && transpose[0].length == ds[0].length ){
            for(int i = 0; i<transpose.length ; i++){
                for(int j = 0; j<transpose[0].length ; j++){
                    if(transpose[i][j]!=ds[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}

class rectunglarMatrix extends matrix{
    static int rc = 1;
    // private matrix mat;
    // rectunglarMatrix(matrix mat){
    //     this.mat = mat;
    // }


    rectunglarMatrix(){}

    // public void setMatrix(matrix mat){
    //     this.mat = mat;
    // }
    // public matrix getMatrix(){
    //     return this.mat;
    // }

    public boolean isRectangularMatrix(matrix mat){
        return mat.getRow()!=mat.getColumn();
    }

    public boolean isRectangularMatrix(){
        return super.getRow()!=super.getColumn();
    }

    public double[][] create(int row, int column){
        double [][] arr = new double[row][column];
        if(row==1 && column==2){
            arr[0][0]=rc;
            rc++;
            arr[0][1]=rc;
            rc++;
        }
        else if(row==2 && column ==1){
            arr[0][0]=rc++;
            arr[1][0]=rc++;
        }

        else if(row ==1 && column==3){
            arr[0][0]=rc++;
            arr[0][1]=rc++;
            arr[0][2]=rc++;
        }
        else if(row==2 && column==3){
            arr[0][0]=rc++;
            arr[0][1]=rc++;
            arr[0][2]=rc++;
            arr[1][0]=rc++;
            arr[1][1]=rc++;
            arr[1][2]=rc++;
        }

        else if(row==3 && column==1){
            arr[0][0] =rc++;
            arr[1][0] =rc++;
            arr[2][0] =rc++;
        }

        else if(row==3 && column==2){
            arr[0][0] =rc++;
            arr[1][0] =rc++;
            arr[2][0] =rc++;
            arr[0][1] =rc++;
            arr[1][1] =rc++;
            arr[2][1] =rc++;
        }
        return arr;
    }

    @Override
    public String toString(){
        return "Rectangular Matrix";
    }
}

class rowMatrix extends rectunglarMatrix{
    rowMatrix() {}
    static int rr = 1;

    public boolean isRowMatrix(matrix mat){
        return mat.getRow()==1;
    }

    public double[][] create(int column){
        double arr[][] = new double[1][column];
        if(column ==2){
            arr[0][0] = rr++;
            arr[0][1] = rr++;
        }
        else if(column ==3){
            arr[0][0] = rr++;
            arr[0][1] = rr++;
            arr[0][2] = rr++;
        }

        return arr;
    }

    @Override
    public String toString(){
        return "Row Marix";
    }
}

class columnMatrix extends rectunglarMatrix{

    static int rrc=1;

    columnMatrix() {}

    public boolean isColumnMatrix(matrix mat){
        return mat.getColumn()==1;
    }

    public double[][] create(int row){
        double arr[][] = new double[row][1];
        if(row ==2){
            arr[0][0] = rrc++;
            arr[1][0] = rrc++;
        }
        else if(row ==3){
            arr[0][0] = rrc++;
            arr[1][0] = rrc++;
            arr[1][0] = rrc++;
        }

        return arr;
    }

    @Override
    public String toString(){
        return "Column Marix";  
    }
}

class squareMatrix extends matrix{
    static int sq;
    // private int row;

    // public void setRow(int row){
    //     this.row = row;
    // }

    // public int getRow(){
    //     return this.row;
    // }

    squareMatrix(){}

    public boolean isSquareMatrix(matrix mat){
        return mat.getRow()==mat.getColumn();
    }

    public boolean isSquareMatrix(){
        return super.getRow() == super.getColumn();
    }

    @Override
    public String toString(){
        return "Square Matrix";
    }

    public double[][] getTranspose(matrix mat){
        double [][] transpose = new double[mat.getColumn()][mat.getColumn()];
        for(int i=0;i<mat.getColumn();i++){    
            for(int j=0;j<mat.getColumn();j++){    
            transpose[i][j]=mat.getMatrix()[j][i];  
            }    
        } 
        return transpose;
    }

    public double[][] getTranspose(double mat[][]){
        double transpose [] [] = new double[mat.length][mat.length];
        for(int i=0;i<mat.length;i++){    
            for(int j=0;j<mat.length;j++){    
            transpose[i][j]=mat[j][i];  
            }    
        } 
        return transpose;
    }

    public double getDeterminant(matrix mat){
        helper hp = new helper();
        double arr [][]= hp.check(mat);
        if(mat.getRow()==3){
            double a = arr[0][0]*((arr[1][1]*arr[2][2]) - (arr[1][2]*arr[2][1]));
            double b = arr[0][1]*((arr[1][0]*arr[2][2]) - (arr[1][2]*arr[2][0]));
            double c = arr[0][2]*((arr[1][0]*arr[2][1]) - (arr[1][1]*arr[2][0]));

            return (a -b + c);
        }
        else if (mat.getRow()==2){
            double a = ((arr[0][0]*arr[1][1]) - (arr[1][0]*arr[0][1]));
            return a; 
        }

        else if(mat.getRow()==1){
            return arr[0][0];
        }

        return 0;
    }

    public double getDeterminant(double arr[][]){
        if(arr.length==3){
            double a = arr[0][0]*((arr[1][1]*arr[2][2]) - (arr[1][2]*arr[2][1]));
            double b = arr[0][1]*((arr[1][0]*arr[2][2]) - (arr[1][2]*arr[2][0]));
            double c = arr[0][2]*((arr[1][0]*arr[2][1]) - (arr[1][1]*arr[2][0]));

            return (a -b + c);
        }
        else if (arr.length==2){
            double a = ((arr[0][0]*arr[1][1]) - (arr[1][0]*arr[0][1]));
            return a; 
        }

        else if(arr.length==1){
            return arr[0][0];
        }

        return 0;
    }

    public double[][] create(int row){

        double[][] arr =  new double[row][row];
        if(row==1){
            arr[0][0] = sq++;
        }
        if(row ==2){
            arr[0][0] = sq++;
            arr[0][1] = sq++;
            arr[1][0] = sq++;
            arr[1][1] = sq++;
        }

        if(row==3){
            arr[0][0] = sq++;
            arr[0][1] = sq++;
            arr[0][2] = sq++;

            arr[1][0] = sq++;
            arr[1][1] = sq++;
            arr[1][2] = sq++;

            arr[2][2] = sq++;
            arr[2][1] = sq++;
            arr[2][0] = sq++;
        }
        return arr;
    }

}

class symmetricMatrix extends squareMatrix{
    //   At = transpose of a
    //   A = At

    symmetricMatrix(){}

    public boolean isSymmetricMatrix(matrix mat){
        if(isSquareMatrix(mat)){
            double [][] transpose = getTranspose(mat);
            if(mat.equal(transpose, mat.getMatrix())){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    public double[][] create(int row){

        double[][] arr =  new double[row][row];
        if(row==1){
            arr[0][0] = 10;
        }
        if(row ==2){
            arr[0][0] = 1;
            arr[0][1] = 3;
            arr[1][0] = 3;
            arr[1][1] = 3;
        }
    
        if(row==3){
            arr[0][0] = 2;
            arr[0][1] = 0;
            arr[0][2] = 0;
    
            arr[1][0] = 0;
            arr[1][1] = 4;
            arr[1][2] = 0;
    
            arr[2][2] = 3;
            arr[2][1] = 0;
            arr[2][0] = 0;
        }

        return arr;
    }

    @Override
    public String toString(){
        return "Symmetric Matrix";
    }
}

class skewSymmetricMatrix extends squareMatrix{
    skewSymmetricMatrix(){}

    public boolean isSkewSymmetricMatrix(matrix mat){
        if(isSquareMatrix(mat)){
            double [][] transpose = getTranspose(mat);
            for(int i = 0; i<mat.getRow(); i++){
                for(int j = 0; j<mat.getRow(); j++){
                    transpose[i][j] = - transpose[i][j];
                }
            }
            if(mat.equal(transpose, mat.getMatrix())){
                return true;
            }
        }
        return false;
    }

    public double[][] create(int row){

        double[][] arr =  new double[row][row];
        if(row==1){
            arr[0][0] = 10;
        }
        if(row ==2){
            arr[0][0] = 1;
            arr[0][1] = 0;
            arr[1][0] = 0;
            arr[1][1] = 2;
        }
    
        if(row==3){
            arr[0][0] = 2;
            arr[0][1] = 0;
            arr[0][2] = 0;
    
            arr[1][0] = 0;
            arr[1][1] = 4;
            arr[1][2] = 0;
    
            arr[2][2] = 3;
            arr[2][1] = 0;
            arr[2][0] = 0;
        }

        return arr;
    }

    @Override
    public String toString(){
        return "Skew-symmetric Matrix";
    }
}


class upperTriangularMatrix extends squareMatrix{
    upperTriangularMatrix(){}

    public boolean isUpperTriangularMatrix(matrix mat){
        nullMatrix nm = new nullMatrix();
        // nullMatrix nm = new nullMatrix();
        // if(!nm.isNullMatrix(mat)){
        //     for(int i = 0; i<mat.getRow(); i++){
        //         for(int j = 0; j<i; j++){
        //             if(mat.getMatrix()[i][j]!=0){
        //                 return false;
        //             }
        //             if(i==j+1){

        //             }
        //         }
        //     }
        //     return true;
        // }
        // return false;

        if(isSquareMatrix(mat) && !nm.isNullMatrix(mat)){
            double [][] arr = mat.getMatrix();
            if(arr.length==3){
                if(arr[1][0]==0 && arr[2][1]== 0 && arr[2][0]==0){
                    return true;
                }
            }
            if(arr.length==2){
                if(arr[1][0]==0){
                    return true;
                }
            }
            if(arr.length ==1){
                if(arr[0][0]!=0) return true;
            }
        }
        return false;
    }

    public double[][] create(int row){

        double[][] arr =  new double[row][row];
        if(row==1){
            arr[0][0] = 10;
        }
        if(row ==2){
            arr[0][0] = 1;
            arr[0][1] = 4;
            arr[1][0] = 0;
            arr[1][1] = 2;
        }
    
        if(row==3){
            arr[0][0] = 2;
            arr[0][1] = 4;
            arr[0][2] = 0;
    
            arr[1][0] = 0;
            arr[1][1] = 4;
            arr[1][2] = 5;
    
            arr[2][2] = 3;
            arr[2][1] = 0;
            arr[2][0] = 0;
        }

        return arr;
    }

    @Override
    public String toString(){
        return "Upper-Triangular Matrix";
    }

}

class lowerTriangularMatrix extends squareMatrix{
    lowerTriangularMatrix(){}

    public boolean isLowerTriangularMatrix(matrix mat){
        nullMatrix nm = new nullMatrix();
        if(isSquareMatrix(mat) && !nm.isNullMatrix(mat)){
            double [][] arr = mat.getMatrix();
            if(arr.length==3){
                if(arr[0][1]==0 && arr[0][2]== 0 && arr[1][2]==0){
                    return true;
                }
            }
            if(arr.length==2){
                if(arr[0][1]==0){
                    return true;
                }
            }
            if(arr.length ==1){
                if(arr[0][0]!=0) return true;
            }
        }
        return false;
    }

    public double[][] create(int row){

        double[][] arr =  new double[row][row];
        if(row==1){
            arr[0][0] = 10;
        }
        if(row ==2){
            arr[0][0] = 1;
            arr[0][1] = 0;
            arr[1][0] = 2;
            arr[1][1] = 1;
        }
    
        if(row==3){
            arr[0][0] = 2;
            arr[0][1] = 0;
            arr[0][2] = 0;
    
            arr[1][0] = 0;
            arr[1][1] = 4;
            arr[1][2] = 0;
    
            arr[2][2] = 3;
            arr[2][1] = 0;
            arr[2][0] = 0;
        }

        return arr;
    }

    @Override
    public String toString(){
        return "Lower-Triangular Matrix";
    }

}

class singularMatrix extends squareMatrix{
    singularMatrix(){}

    public boolean isSingularMatrix(matrix mat){
        if(isSquareMatrix(mat)){
            if(getDeterminant(mat)==0){
                return true;
            }
        }
        return false;
    }

    public double[][] create(int row){

        double[][] arr =  new double[row][row];
        if(row==1){
            arr[0][0] = 0;
        }
        if(row ==2){
            arr[0][0] = 3;
            arr[0][1] = 6;
            arr[1][0] = 2;
            arr[1][1] = 4;
        }
    
        if(row==3){
            arr[0][0] = 1;
            arr[0][1] = 2;
            arr[0][2] = 2;
    
            arr[1][0] = 1;
            arr[1][1] = 2;
            arr[1][2] = 2;
    
            arr[2][2] = -1;
            arr[2][1] = 2;
            arr[2][0] = 3;
        }

        return arr;
    }

    @Override
    public String toString(){
        return "Singular Matrix";
    }
}

class diagonalmatrix extends squareMatrix{

    diagonalmatrix(){}

    public boolean isDiagonalMatrix(matrix mat){
        // nullMatrix nm = new nullMatrix();
        // boolean flag1 = false;
        // int count = 0;
        // for(int i = 0; i<mat.getRow(); i++){
        //     for(int j = 0; j<mat.getColumn(); j++){
        //         if(i==j){
        //             if(count==mat.getColumn() && mat.getMatrix()[i][i]==0 && flag1==false){
        //                 return false;
        //             }
        //             if(mat.getMatrix()[i][i]==0 && flag1==false){
        //                 count++;
        //             }
        //             else{
        //                 flag1 = true;
        //             }
        //         }
        //         else{
        //             if(mat.getMatrix()[i][j]!=0){
        //                 return false;
        //             }
        //         }
        //     }
        // }
        // return true;
        
        double arr [][] = mat.getMatrix();
        if(arr.length==3){
            if(arr[1][0]==0 && arr[2][0]==0 && arr[2][1]==0 && arr[0][2]==0 && arr[1][2]==0 && arr[0][1]==0){
                if(arr[0][0]!=0 && arr[1][1]!=0 && arr[2][2]!=0){
                    return true;
                }
            }
        }

        if(arr.length==2){
            if(arr[0][0]!=0 && arr[1][1]!=0){
                if(arr[0][1]==0 && arr[1][0]==0){
                    return true;
                }
            }
        }

        return false;
        
    }

    public double[][] create(int row){
        double[][] arr =  new double[row][row];
        if(row==1){
            arr[0][0] = 10;
        }
        if(row ==2){
            arr[0][0] = 1;
            arr[0][1] = 0;
            arr[1][0] = 0;
            arr[1][1] = 2;
        }
        if(row==3){
            arr[0][0] = 2;
            arr[0][1] = 0;
            arr[0][2] = 0;
    
            arr[1][0] = 0;
            arr[1][1] = 4;
            arr[1][2] = 0;
    
            arr[2][2] = 3;
            arr[2][1] = 0;
            arr[2][0] = 0;
        }
        return arr;
    }

    @Override
    public String toString(){
        return "Diagonal Matrix";
    }
}

class scalarMatrix extends squareMatrix{
    scalarMatrix(){}

    public boolean isScalarMatrix(matrix mat){
        if(isSquareMatrix(mat)){
            diagonalmatrix dm = new diagonalmatrix();
            if(dm.isDiagonalMatrix(mat)){
                // int n = mat.getRow();
                // int toBeSame = mat.getMatrix()[0][0];
                // for(int i = 1 ; i<n ; i++){
                //     if(toBeSame!=mat.getMatrix()[i][i]){
                //         return false;
                //     }
                // }
                // return true;

                double arr [][] = mat.getMatrix();
                if(arr.length==3){
                    if(arr[0][0]==arr[1][1] && arr[1][1]==arr[2][2]){
                        return true;
                    }
                    return false;
                }

                if(arr.length==2){
                    if(arr[0][0]==arr[1][1]){
                        return true;
                    }
                    return false;
                }

                if(arr.length==1){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public double[][] create(int row){
        double[][] arr =  new double[row][row];
        if(row==1){
            arr[0][0] = 10;
        }
        if(row ==2){
            arr[0][0] = 2;
            arr[0][1] = 0;
            arr[1][0] = 0;
            arr[1][1] = 2;
        }
        if(row==3){
            arr[0][0] = 4;
            arr[0][1] = 0;
            arr[0][2] = 0;
    
            arr[1][0] = 0;
            arr[1][1] = 4;
            arr[1][2] = 0;
    
            arr[2][2] = 4;
            arr[2][1] = 0;
            arr[2][0] = 0;
        }
        return arr;
    }
    
    @Override
    public String toString(){
        return "Scalar Matrix";
    }
}

class identityMatrix extends squareMatrix{

    identityMatrix(){}


    public boolean isIdentityMatrix(matrix mat){
        diagonalmatrix dm = new diagonalmatrix();
        if(isSquareMatrix(mat)){
            if(dm.isDiagonalMatrix(mat)){
                for(int i = 0; i<mat.getRow(); i++){
                    if(mat.getMatrix()[i][i]!=1){
                        return false;
                    }
                }
                double [][] arr = new double[1][1];
                arr[0][0] = 1;
                mat.setMatrix(arr);
                mat.setIsOneOrIdentiy(-1);
                return true;
            }
        }
        return false;
    }

    public double[][] create(int row){
        double[][] arr =  new double[row][row];
        if(row==1){
            arr[0][0] = 1;
        }
        if(row ==2){
            arr[0][0] = 1;
            arr[0][1] = 0;
            arr[1][0] = 0;
            arr[1][1] = 1;
        }
        if(row==3){
            arr[0][0] = 1;
            arr[0][1] = 0;
            arr[0][2] = 0;
    
            arr[1][0] = 0;
            arr[1][1] = 1;
            arr[1][2] = 0;
    
            arr[2][2] = 1;
            arr[2][1] = 0;
            arr[2][0] = 0;
        }
        return arr;
    }

    @Override
    public String toString(){
        return "Identity Matrix";
    }
    
}

class singletonMatrix extends squareMatrix{

    singletonMatrix(){}

    static int increasement = 1;

    public boolean isSingletonMatrix(matrix mat){
        if(mat.getRow()==1 && mat.getColumn()==1){
            return true;
        }
        return false;
    }

    public double[][] create(){
        double arr[][] = new double [1][1];
        arr[0][0] = increasement++;
        return arr;
    }

    @Override
    public String toString(){
        return "Singleton Matrix";
    }

}

class onesMatrix extends matrix{
    onesMatrix(){}

    public boolean isOnesMatrix(matrix mat){
        for(int i = 0;  i<mat.getRow(); i++){
            for(int j = 0; j<mat.getColumn(); j++){
                if(mat.getMatrix()[i][j]!=1){
                    return false;
                }
            }
        }
        double arr[][] = new double[2][2];
        arr[0][0] = 1;
        mat.setMatrix(arr);
        mat.setIsOneOrIdentiy(1);
        return true;
    }

    public double[][] create(int row, int column){
        double arr[][] = new double[row][column];
        for(int i = 0 ; i<row; i++){
            for(int j = 0; j<column; j++){
                arr[i][j] = 1;
            }
        }
        return arr;
    }

    @Override
    public String toString(){
        return "Ones Matrix";
    }

}

class nullMatrix extends matrix{
    nullMatrix(){}

    public boolean isNullMatrix(matrix mat){
        for(int i = 0; i<mat.getRow(); i++){
            for(int j = 0; j<mat.getColumn(); j++){
                if(mat.getMatrix()[i][j]!=0){
                    return false;
                }
            }
        }
        return true;
    }

    public double[][] create(int row, int column){
        double arr[][] = new double[row][column];
        for(int i = 0 ; i<row; i++){
            for(int j = 0; j<column; j++){
                arr[i][j] = 0;
            }
        }
        return arr;
    }

    @Override
    public String toString(){
        return "Null Matrix";
    }
}


class helper{

    static rectunglarMatrix rectangular = new rectunglarMatrix();
    static rowMatrix rowmatrix = new rowMatrix();
    static columnMatrix columnmatrix = new columnMatrix();
    static squareMatrix square = new squareMatrix();
    static symmetricMatrix symmetric = new symmetricMatrix();
    static skewSymmetricMatrix skewSymmetric = new skewSymmetricMatrix();
    static upperTriangularMatrix upperTriangular = new upperTriangularMatrix();
    static lowerTriangularMatrix lowerTriangular = new lowerTriangularMatrix();
    static singularMatrix singular = new singularMatrix();
    static diagonalmatrix diagonal = new diagonalmatrix();
    static scalarMatrix scalar = new scalarMatrix();
    static identityMatrix identity = new identityMatrix();
    static singletonMatrix singleton = new singletonMatrix();
    static onesMatrix ones = new onesMatrix();
    static nullMatrix nullmatrix = new nullMatrix();
    static matrix mainMatrix = new matrix();

    public void addLabels(matrix mat){

        if(ones.isOnesMatrix(mat)){
            mat.addElements(ones);
            if(rectangular.isRectangularMatrix(mat)){
                mat.addElements(rectangular);
                if(rowmatrix.isRowMatrix(mat)){
                    mat.addElements(rowmatrix);
                }
    
                if(columnmatrix.isColumnMatrix(mat)){
                    mat.addElements(columnmatrix);
                }
            }
            if(square.isSquareMatrix(mat)){
                mat.addElements(square);
                mat.addElements(symmetric);
            }
        }

        else if(nullmatrix.isNullMatrix(mat)){
            mat.addElements(nullmatrix);
            if(rectangular.isRectangularMatrix(mat)){
                mat.addElements(rectangular);
                if(rowmatrix.isRowMatrix(mat)){
                    mat.addElements(rowmatrix);
                }
    
                if(columnmatrix.isColumnMatrix(mat)){
                    mat.addElements(columnmatrix);
                }
            }
            if(square.isSquareMatrix(mat)){
                mat.addElements(square);
                mat.addElements(symmetric);
                mat.addElements(skewSymmetric);
            }
        }

        else{
            if(rectangular.isRectangularMatrix(mat)){
                mat.addElements(rectangular);
                if(rowmatrix.isRowMatrix(mat)){
                    mat.addElements(rowmatrix);
                }
    
                if(columnmatrix.isColumnMatrix(mat)){
                    mat.addElements(columnmatrix);
                }
            }
    
            if(square.isSquareMatrix(mat)){
                mat.addElements(square);
                if(symmetric.isSymmetricMatrix(mat)){
                    mat.addElements(symmetric);
                }
    
                if(skewSymmetric.isSkewSymmetricMatrix(mat)){
                    mat.addElements(skewSymmetric);
                }
    
                if(upperTriangular.isUpperTriangularMatrix(mat)){
                    mat.addElements(upperTriangular);
                }
                
                if(lowerTriangular.isLowerTriangularMatrix(mat)){
                    mat.addElements(lowerTriangular);
                }
    
                if(singular.isSingularMatrix(mat)){
                    mat.addElements(singular);
                }
    
                if(diagonal.isDiagonalMatrix(mat)){
                    mat.addElements(diagonal);
                }
    
                if(scalar.isScalarMatrix(mat)){
                    mat.addElements(scalar);
                }
    
                if(identity.isIdentityMatrix(mat)){
                    mat.addElements(identity);
                }
    
                if(singleton.isSingletonMatrix(mat)){
                    mat.addElements(singleton);
                }
    
            }
        }
    }

    public boolean checkToLabel(matrix mat, double arr[][]){
        ArrayList<Object> list = mat.getArrayList();
        matrix m1 = new matrix(arr, 1);
        ArrayList<Object> list0 = m1.getArrayList();

        for(Object ob0: list){
            int count = 0;
            for(Object ob1: list0){
                if(ob1.toString().equals(ob0.toString())){
                    count++;
                }
            }
            if(count==0){
                return false;
            }
        }
        return true;

        // int count = 0;
        // for(Object ob: list){
        //     if(ob.toString().equals("Rectangular Matrix")){
        //         if(rectangular.isRectangularMatrix(m1)){
        //             count++;
        //         }
        //     }
        //     else if(ob.toString().equals("Row Marix")){
        //         if(rowmatrix.isRowMatrix(m1)) count++;
        //     }
        //     else if(ob.toString().equals("Column Marix")){
        //         if(columnmatrix.isColumnMatrix(m1)) count++;
        //     }
        //     else if(ob.toString().equals("Square Marix")){
        //         if(square.isSquareMatrix(m1)){
        //             count++;
        //         }
        //     }
        //     else if(ob.toString().equals("Symmetric Marix")){
        //         if(symmetric.isSymmetricMatrix(m1)){
        //             count++;
        //         }
        //     }
        //     else if(ob.toString().equals("Skew-symmetric Matrix")){
        //         if(skewSymmetric.isSkewSymmetricMatrix(m1)){
        //             count++;
        //         }
        //     }
        //     else if(ob.toString().equals("Upper-Triangular Matrix")){
        //         if(upperTriangular.isUpperTriangularMatrix(m1)) count++;
        //     }
        //     else if(ob.toString().equals("Lower-Triangular Matrix")){
        //         if(lowerTriangular.isLowerTriangularMatrix(m1)) count++;
        //     }
        //     else if(ob.toString().equals("Singular Matrix")){
        //         if(singular.isSingularMatrix(m1)) count++;
        //     }
        //     else if(ob.toString().equals("Diagonal Matrix")){
        //         if(diagonal.isDiagonalMatrix(m1)) count++;
        //     }
        //     else if(ob.toString().equals("Scalar Matrix")){
        //         if(scalar.isScalarMatrix(m1)) count++;
        //     }
        //     else if(ob.toString().equals("Identity Matrix")){
        //         if(identity.isIdentityMatrix(m1)) count++;
        //     }
        //     else if(ob.toString().equals("Singleton Matrix")){
        //         if(singleton.isSingletonMatrix(m1)) count++;
        //     }
        //     else if(ob.toString().equals("Ones Matrix")){
        //         if(ones.isOnesMatrix(m1)) count++;
        //     }
        //     else if(ob.toString().equals("Null Matrix")){
        //         if(nullmatrix.isNullMatrix(m1)) count++;
        //     }
        // }

        // if(count==list.size()){

        // }


    }

    public void printLabel(matrix mat){
        ArrayList<Object> arr = mat.getArrayList();

        for(int i = 0; i<arr.size() ; i++){
            System.out.println(arr.get(i).toString());
        }
    }

    public double[][] makeIdentityMatrix(matrix mat){
        double [][] arr = new double[mat.getRow()][mat.getColumn()];
        for(int i = 0; i<mat.getRow(); i++){
            for(int j = 0; j<mat.getColumn(); j++){
                if(i==j){
                    arr[i][j] = 1;
                }
                else{
                    arr[i][j] = 0;
                }
            }
        }
        return arr;
    }

    public double[][] makeOnesMatrix(matrix mat){
        double [][] arr = new double[mat.getRow()][mat.getColumn()];
        for(int i = 0; i<mat.getRow(); i++){
            for(int j = 0; j<mat.getColumn(); j++){
                arr[i][j] = 1;
            }
        }
        return arr;
    }

    public void printMatrix(matrix mat){
        int a = mat.getIsOneOrIdentity();
        double [][] arr = new double[mat.getRow()][mat.getColumn()];
        if(a==-1){
            arr = makeIdentityMatrix(mat);
        }

        else if(a == 1){
            arr = makeOnesMatrix(mat);
        }

        else{
            arr=mat.getMatrix();
        }
        for(int i = 0; i<mat.getRow(); i++){
            for(int j = 0; j<mat.getColumn(); j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void printAllMatrixOfSpecificLabelType(String str){

        HashMap<Integer, matrix> stored = matrix.Matrix;
        for(Map.Entry<Integer, matrix> hmp : stored.entrySet()){
            for(Object ob: hmp.getValue().getArrayList()){
                if(ob.toString().equals(str)){
                    System.out.println(hmp.getValue().getID());
                    printMatrix(hmp.getValue());
                    System.out.println();
                }
            }
        }   
    }

    public String ChooseTypeOfMatrix(int opt){
        String str = null;
        if(opt == 1){
            str = "Rectangular Matrix";
        }
        else  if(opt == 2){
            str = "Row Marix";
        }

        else if (opt ==3){
            str = "Column Marix";
        }

        else if(opt==4){
            str = "Square Matrix";
        }

        else if(opt==5){
            str = "Symmetric Matrix";
        }

        else if(opt==6){
            str = "Skew-symmetric Matrix";
        }
        
        else if(opt==7){
            str = "Upper-Triangular Matrix";
        }

        else if(opt==8){
            str = "Lower-Triangular Matrix";
        }

        else if(opt==9){
            str = "Singular Matrix";
        }

        else if(opt==10){
            str = "Diagonal Matrix";
        }

        else if(opt==11){
            str = "Scalar Matrix";
        }
        
        else  if(opt==12){
            str =  "Identity Matrix";
        }

        else if(opt==13){
            str = "Singleton Matrix";
        }

        else if(opt==14){
            str = "Ones Matrix";
        }

        else if(opt==15){
            str = "Null Matrix";
        }

        return str;
    }

    public void printAllMatrix(){
        HashMap<Integer, matrix> stored = matrix.Matrix;
        for(Map.Entry<Integer, matrix> hp: stored.entrySet()){
            System.out.println(hp.getKey());
            printMatrix(hp.getValue());
            System.out.println();
        }
    }

    public double[][] check(matrix mat){
        int a = mat.getIsOneOrIdentity();
        // int b = mat.
        double [][] arr = new double[mat.getRow()][mat.getColumn()];
        if(a==-1){
            arr = makeIdentityMatrix(mat);
        }

        else if(a == 1){
            arr = makeOnesMatrix(mat);
        }

        else{
            arr=mat.getMatrix();
        }

        return arr;
    }

    public void performAddition(matrix mat1, matrix mat2){

        double arr0[][] = check(mat1);
        double arr1[][] = check(mat2);
        
        if(mat1.getRow()==mat2.getRow() && mat1.getColumn()==mat2.getColumn()){
            double finalMatrix [][]= new double[mat1.getRow()][mat1.getColumn()];
            for(int i = 0; i<mat1.getRow(); i++){
                for(int j = 0; j<mat1.getColumn(); j++){
                    finalMatrix[i][j] = arr0[i][j] + arr1[i][j];
                }
            }
            printMatrix(finalMatrix);
        }
        else{
            System.out.println("Matrix are not eligible as their diminsions are not same!!!");
        }

    }

    public void performSubtraction(matrix mat1, matrix mat2){

        double arr0[][] = check(mat1);
        double arr1[][] = check(mat2);

        if(mat1.getRow()==mat2.getRow() && mat1.getColumn()==mat2.getColumn()){
            double finalMatrix [][]= new double[mat1.getRow()][mat1.getColumn()];
            for(int i = 0; i<mat1.getRow(); i++){
                for(int j = 0; j<mat1.getColumn(); j++){
                    finalMatrix[i][j] = arr0[i][j] - arr1[i][j];
                }
            }
            printMatrix(finalMatrix);

        }
        else{
            System.out.println("Matrix are not eligible as their diminsions are not same!!!");
        }

    }

    public void performMultiplication(matrix mat1, matrix mat2){

        double arr0[][] = check(mat1);
        double arr1[][] = check(mat2);

        if(mat1.getColumn()==mat2.getRow()){
            double finalMatrix [][] = new double[mat1.getRow()][mat2.getColumn()];
            for (int i = 0; i < mat1.getRow(); i++) {
                for (int j = 0; j < mat2.getColumn(); j++) {
                    for (int k = 0; k < mat2.getRow(); k++)
                        finalMatrix[i][j] += arr0[i][k] * arr1[k][j];
                }
            }
            printMatrix(finalMatrix);
        }
        else{
            System.out.println("Matrix are not eligible as their diminsions are not same!!!");
        }
    }

    public void performMultiplication(matrix mat1, double mat2[][]){
        double arr0[][] = check(mat1);
        
        if(mat1.getColumn()==mat2.length){
            double finalMatrix [][] = new double[mat1.getRow()][mat2[0].length];
            for (int i = 0; i < mat1.getRow(); i++) {
                for (int j = 0; j < mat2[0].length; j++) {
                    for (int k = 0; k < mat2.length; k++)
                        finalMatrix[i][j] += arr0[i][k] * mat2[k][j];
                }
            }
            printMatrix(finalMatrix);
        }
        else{
            System.out.println("Matrix are not eligible as their diminsions are not same!!!");
        }
    }

    public void performMultiplication(double mat1[][], double mat2[][]){        
        if(mat1[0].length==mat2.length){
            double finalMatrix [][] = new double[mat1.length][mat2[0].length];
            for (int i = 0; i < mat1.length; i++) {
                for (int j = 0; j < mat2[0].length; j++) {
                    for (int k = 0; k < mat2.length; k++)
                        finalMatrix[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
            printMatrix(finalMatrix);
        }
        else{
            System.out.println("Matrix are not eligible as their diminsions are not same!!!");
        }
    }
    

    public void performDivision(matrix mat1, matrix mat2){
        double m1 [][] = check(mat1);
        double tobeMultiply[][] = getInverse(mat2);
        if(tobeMultiply==null){
            System.out.println("Matrix 1 Can't be divisible with matrix 2!!");

        }
        else{
            performMultiplication(m1, tobeMultiply);
        }

    }

    public void printMatrix(double[][] arr) {
        for(int i = 0; i<arr.length ; i++){
            for(int j = 0; j<arr[0].length; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public double [][] getAdjoint(matrix mat){
        double arr[][] = check(mat);
        int row = mat.getRow();
        double adjoint [][] = new double[row][row];
        if(row==2){
            adjoint[1][1] = arr[0][0];
            adjoint[0][0] = arr[1][1];
            adjoint[1][0] = -arr[1][0];
            adjoint[0][1] = -arr[0][1];

            return adjoint;
        }

        if(row == 3){
            double a = arr[0][0];
            double b = arr[0][1];
            double c = arr[0][2];

            double d = arr[1][0];
            double e = arr[1][1];
            double f = arr[1][2];

            double g = arr[2][0];
            double h = arr[2][1];
            double i = arr[2][2];

            double forA = e*i - f*h;
            double forB = -(d*i - f*g);
            double forC = d*h - g*e;

            double forD = -(b*i - h*c);
            double forE = a*i - g*c;
            double forF = -(a*h - g*b);

            double forG = b*f - e*c;
            double forh = -(a*f - d*c);
            double forI = a*e - d*b;
            
            double temp[][] = new double[3][3];
            temp[0][0] = forA;
            temp[0][1] = forB;
            temp[0][2] = forC;

            temp[1][0] = forD;
            temp[1][1] = forE;
            temp[1][2] = forF;

            temp[2][0] = forG;
            temp[2][1] = forh;
            temp[2][2] = forI;

            adjoint = square.getTranspose(temp);
            return adjoint;
        }
        else if(row==1){
            adjoint[0][0] = 1;
        }
        return adjoint;
    }

    public double [][] getscalarMultiplication(double[][] mat, double scalar){
        double toBeReturned [][] = mat;
        for(int i = 0; i<mat.length; i++){
            for(int j = 0; j<mat[0].length; j++){
                toBeReturned[i][j] = scalar*mat[i][j];
            }
        }
        return toBeReturned;
    }

    public double[][] getInverse(matrix mat){
        double arr[][] = check(mat);
        double det = square.getDeterminant(arr);
        if(det==0){
            System.out.println("Determinant is zero so, inverse doesnot exist");
            return null;
        }
        else{
            double adjoint[][] = getAdjoint(mat);
            double inverse[][] = getscalarMultiplication(adjoint, 1/det);
            printMatrix(inverse);
            return inverse;
        }
    }

    public double getDet(matrix mat){
        return square.getDeterminant(mat);
    }

    public void eigen(double arr[][]){
        double a = arr[0][0];
        double b = arr[0][1];
        double c = arr[1][0];
        double d = arr[1][1];

        double x = -(a + d);
        double y = ((a*d) - (b*c));

        double D = ((x*x) - (4*y));

        double z1 = 0;
        double z2 = 0;

        if(D==0){
            z1 = ( (-x) ) / 2;
            z2 = z1;
        }
        else if(D<0){
            System.out.println("No Eigen value and Eigen Vector exist!!!");
            return;
        }
        else if(D>0){
            z1 = ( (-x) + Math.pow(D, 0.5) ) / 2;
            z2 = ( (-x) - Math.pow(D, 0.5) ) / 2;

        }

        System.out.println("Eigen Values: "+ z1+"  "+z2);
        // System.out.println("Eigen Vectors: ");
        // eigenector(arr, z1);
        // eigenector(arr, z2);

    }

    public void rowWiseOperation(double arr[][], double opetration, String toBedone, int row){
        for(int i = 0; i<arr[0].length ; i++){
            if(toBedone.equals("*")){
                arr[row][i] = arr[row][i] * opetration;
            }
            else if(toBedone.equals("-")){
                arr[row][i] = arr[row][i] - opetration;
            }
            else if(toBedone.equals("/")){
                arr[row][i] = arr[row][i] / opetration;
            }
        }
    }

    public void eigenector(double arr[][], double z){
        double i = arr[0][0] = arr[0][0] - z;
        double j = arr[1][1] = arr[1][1] - z;
        double x = arr[0][1];
        double y = arr[1][0];
        
        if(i!=0 && y!=0){
            rowWiseOperation(arr, y, "*", 0);
            rowWiseOperation(arr, i, "*", 1);
            rowWiseOperation(arr, arr[0][0], "-", 1);

            if(arr[1][1]==0 && arr[0][1]==0){
                System.out.println("1  0");
            }
            else if(arr[1][1]!=0 && arr[0][1]!=0){
                double w = arr[1][1];
                double k = arr[0][1];
                rowWiseOperation(arr, w, "*", 0);
                rowWiseOperation(arr, k, "*", 1);
                rowWiseOperation(arr, arr[1][1], "-", 0);
                System.out.println(arr[0][0]/arr[1][1]+"  "+1);
            }
            else if(arr[1][1]==0 && arr[0][1]!=0){
                System.out.println(arr[0][0]/arr[0][1]+"  "+1);
            }
            else if(arr[1][1]!=0 && arr[0][1]==0){
                System.out.println(arr[0][0]/arr[1][1]+"  "+1);
            }
        }

        else if(i!=0 && y==0){

            if(arr[1][1]==0 && arr[0][1]==0){
                System.out.println("1  0");
            }
            else if(arr[1][1]!=0 && arr[0][1]!=0){
                double w = arr[1][1];
                double k = arr[0][1];
                rowWiseOperation(arr, w, "*", 0);
                rowWiseOperation(arr, k, "*", 1);
                rowWiseOperation(arr, arr[1][1], "-", 0);
                System.out.println(arr[0][0]/arr[1][1]+"  "+1);
            }
            else if(arr[1][1]==0 && arr[0][1]!=0){
                System.out.println(arr[0][0]/arr[0][1]+"  "+1);
            }
            else if(arr[1][1]!=0 && arr[0][1]==0){
                System.out.println(arr[0][0]/arr[1][1]+"  "+1);
            }
        }

        else if(i==0 && y!=0){
            if(arr[1][1]==0 && arr[0][1]==0){
                System.out.println("1  0");
            }
            else if(arr[1][1]!=0 && arr[0][1]!=0){
                double k = arr[1][1];
                double w = arr[0][1];
                rowWiseOperation(arr, w, "*", 1);
                rowWiseOperation(arr, k, "*", 0);
                rowWiseOperation(arr, arr[0][1], "-", 1);
                System.out.println(arr[1][0]/arr[0][1]+"  "+1);
            }
            else if(arr[1][1]==0 && arr[0][1]!=0){
                System.out.println(arr[1][0]/arr[0][1]+"  "+1);
            }
            else if(arr[1][1]!=0 && arr[0][1]==0){
                System.out.println(arr[1][0]/arr[1][1]+"  "+1);
            }
        }

        else if(i==0 && y==0){
            System.out.println("0  1");
        }
    }
}


public class Assignment{

    public static void optedScalar(){
        helper hp = new helper();
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Multiplication");
        System.out.println("2. Division");
        System.out.println("Choose operation to be perform: ");
        int optedFunction = sc.nextInt();
        hp.printAllMatrix();
        System.out.println("Choose ID of a matrix: ");
        int optedID0 = sc.nextInt();
        System.out.println("Now, Choose singleton Matrix: ");
        hp.printAllMatrixOfSpecificLabelType("Singleton Matrix");
        System.out.println("if no singleton Matrix is mention then enter 100: ");
        int optedID1 = sc.nextInt();
        if(optedID1==100){
            return;
        }
        else{
            if(optedFunction==1){
                double arr[][] = hp.check(matrix.Matrix.get(optedID0));
                System.out.println(hp.getscalarMultiplication(arr, matrix.Matrix.get(optedID1).getMatrix()[0][0]));
            }
            else if(optedFunction==2){
                double arr[][] = hp.check(matrix.Matrix.get(optedID0));
                System.out.println(hp.getscalarMultiplication(arr, 1/matrix.Matrix.get(optedID1).getMatrix()[0][0]));
            }
        }
    }

    public static void arthematic(){
        helper hp = new helper();
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose function to perform: ");
        int optFunction = sc.nextInt();

        hp.printAllMatrix();
        System.out.println("Choose Matrix 1: ");
        int optMatrix1 = sc.nextInt();
        System.out.println("Choose Matrix 2: ");
        int optMatrix2 = sc.nextInt();

        matrix mat1 = matrix.Matrix.get(optMatrix1);
        matrix mat2 = matrix.Matrix.get(optMatrix2);
        if(optFunction==1){
            hp.performAddition(mat1, mat2);
        }
        else if(optFunction==2){
            hp.performSubtraction(mat1, mat2);
        }
        else if(optFunction==3){
            hp.performMultiplication(mat1, mat2);
        }
        else if(optFunction==4){
            hp.performDivision(mat1, mat2);
        }
    }

    public static void typeOfMatrix(){
        System.out.println("1. Rectangular Matrix");
        System.out.println("2. Row Matrix");
        System.out.println("3. Column Matrix");
        System.out.println("4. Square Matrix");
        System.out.println("5. Symmetric Matrix");
        System.out.println("6. Skew-Symmetric Matrix");
        System.out.println("7. Upper-Triangular Matrix");
        System.out.println("8. Lower-Triangular Matrix");
        System.out.println("9. Singular Matrix");
        System.out.println("10. Diagonal Matrix");
        System.out.println("11. Scalar Matrix");
        System.out.println("12. Identity Matrix");
        System.out.println("13. Singleton Matrix");
        System.out.println("14. Ones Matrix");
        System.out.println("15. Null Matrix");

    }

    public static void menu(){
        System.out.println("1. Enter matrices and label them with the appropriate type");
        System.out.println("2. Create matrices of your own type");
        System.out.println("3. Change the element of matrix");
        System.out.println("4. Display all the matrix of choosen type");
        System.out.println("5. Perform operations addition, subtraction, multiplication and division");
        System.out.println("6. Perform element wise operation");
        System.out.println("7. Transpose matrix");
        System.out.println("8. Inverse matrix");
        System.out.println("9. Compute mean");
        System.out.println("10. Compute Determinants");
        System.out.println("11. Use Singleton matrices as scalars, if requested.");
        System.out.println("12 Compute A + AT for a matrix");
        System.out.println("13. Compute Eigen vectors and values");
        System.out.println("14. solve set of linear equation using matrices");
        System.out.println("15. Get all the matrix of particular type");
        System.out.println("16. Exit");

    }
    public static void main(String[] args) {
        // helper hp = new helper();
        // menu();
        // Scanner scn = new Scanner(System.in);
        // menu();
        // System.out.print("Enter number: ");
        // int n = scn.nextInt();
        // if(n==1){
        //     Scanner sc = new Scanner(System.in);
        //     System.out.println("Enter number of row: ");
        //     int row = sc.nextInt();
        //     System.out.println("Enter number of column: ");
        //     int column = sc.nextInt();
        //     int mat[][] = new int[row][column];
        //     for(int i = 0; i<row; i++){
        //         for(int j = 0; j<column; j++){
        //             mat[i][j] = sc.nextInt();
        //         }
        //     }
        //     matrix stored = new matrix(mat);
        //     hp.printLabel(stored);
        // }

        helper hp = new helper();
        
        Scanner scn = new Scanner(System.in);
        while(true){
            menu();
            System.out.print("Enter number: ");
            int n = scn.nextInt();

            if(n==1){
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter number of row: ");
                int row = sc.nextInt();
                System.out.println("Enter number of column: ");
                int column = sc.nextInt();
                double mat[][] = new double[row][column];
                for(int i = 0; i<row; i++){
                    for(int j = 0; j<column; j++){
                        mat[i][j] = sc.nextInt();
                    }
                }
                matrix stored = new matrix(mat);
                hp.printLabel(stored);
            }

            if(n==2){
                Scanner sc = new Scanner(System.in);
                typeOfMatrix();
                System.out.println("Choose which type of matrix you want to create: ");
                int opt = sc.nextInt();
                matrix mat = null;
                if(opt == 1){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    System.out.println("Enter number of column: ");
                    int column = sc.nextInt();
                    rectunglarMatrix rc = new rectunglarMatrix();
                    double arr [][] = rc.create(row, column);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt==2){
                    System.out.println("Enter number of column: ");
                    int column = sc.nextInt();
                    rowMatrix rm = new rowMatrix();
                    double arr[][] = rm.create(column);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt==3){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    columnMatrix rc = new columnMatrix();
                    double arr [][] = rc.create(row);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt==4){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    squareMatrix rc = new squareMatrix();
                    double arr [][] = rc.create(row);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt==5){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    symmetricMatrix scr = new symmetricMatrix();
                    double arr [][] = scr.create(row);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt==6){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    skewSymmetricMatrix rc = new skewSymmetricMatrix();
                    double arr [][] = rc.create(row);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt==7){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    upperTriangularMatrix rc = new upperTriangularMatrix();
                    double arr [][] = rc.create(row);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt==8){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    lowerTriangularMatrix rc = new lowerTriangularMatrix();
                    double arr [][] = rc.create(row);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt==9){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    singularMatrix rc = new singularMatrix();
                    double arr [][] = rc.create(row);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt==10){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    diagonalmatrix rc = new diagonalmatrix();
                    double arr [][] = rc.create(row);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt==11){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    scalarMatrix rc = new scalarMatrix();
                    double arr [][] = rc.create(row);
                    mat = new matrix(arr);
                    hp.printLabel(mat);

                }
                
                else if(opt==12){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    identityMatrix rc = new identityMatrix();
                    double arr [][] = rc.create(row);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt==13){
                    singletonMatrix rc = new singletonMatrix();
                    double arr [][] = rc.create();
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt == 14){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    System.out.println("Enter number of column: ");
                    int column = sc.nextInt();
                    onesMatrix rc = new onesMatrix();
                    double arr [][] = rc.create(row, column);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                }

                else if(opt==15){
                    System.out.println("Enter number of row: ");
                    int row = sc.nextInt();
                    System.out.println("Enter number of column: ");
                    int column = sc.nextInt();
                    rectunglarMatrix rc = new rectunglarMatrix();
                    double arr [][] = rc.create(row, column);
                    mat = new matrix(arr);
                    hp.printLabel(mat);
                } 
                matrix.Matrix.put(mat.getID(), mat);             
            }

            if(n==3){
                hp.printAllMatrix();
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose ID: ");
                int optID = sc.nextInt();
                matrix mat = matrix.Matrix.get(optID);
                double arr[][] = mat.getMatrix();
                hp.printMatrix(mat);
                System.out.println("enter row in which you want to make chaange: ");
                int optedRow = sc.nextInt();
                System.out.println("enter column in which you want to make chaange: ");
                int optedColumn = sc.nextInt();
                System.out.println("Enter change : ");
                int optedChange = sc.nextInt();
                arr[optedRow][optedColumn]=optedChange;
                matrix m1 = new matrix(arr, 1);
                if(hp.checkToLabel(mat, arr)){
                    mat.setMatrix(arr);
                    mat.setList(m1.getArrayList());
                    System.out.println("Changes made sucessfully !!");
                }
                else{
                    System.out.println("Invalid Change !!");
                }
            }

            if(n==4){
                typeOfMatrix();
                Scanner sc = new Scanner(System.in);
                int opt = sc.nextInt();
                String str = hp.ChooseTypeOfMatrix(opt);
                hp.printAllMatrixOfSpecificLabelType(str);
            }

            if(n==5){
                arthematic();

            }

            if(n==6){
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Addition");
                System.out.println("2. Subtraction");
                System.out.println("3. Multiplication");
                System.out.println("4. Division");
                System.out.println("Choose Operation: ");
                int optedOperation = sc.nextInt();
                hp.printAllMatrix();
                System.out.println("Choose Matrix 1: ");
                int m1 = sc.nextInt();
                System.out.println("Choose Matrix 2: ");
                int m2 = sc.nextInt();
                matrix mat1 = matrix.Matrix.get(m1);
                matrix mat2 = matrix.Matrix.get(m2);
                if(mat1.getRow()==mat2.getRow() && mat2.getColumn()==mat1.getColumn()){
                    if(optedOperation==1){
                        hp.performAddition(mat1, mat2);
                    }
                    else if(optedOperation==2){
                        hp.performSubtraction(mat1, mat2);
                    }
                    
                    else if(optedOperation==3){
                        for(int i = 0; i<mat1.getRow(); i++){
                            for(int j = 0; j<mat1.getColumn(); j++){
                                System.out.print(mat1.getMatrix()[i][j]*mat2.getMatrix()[i][j]+"  ");
                            }
                            System.out.println();
                        }
                    }
    
                    else if(optedOperation==4){
                        for(int i = 0; i<mat1.getRow(); i++){
                            for(int j = 0; j<mat1.getColumn(); j++){
                                System.out.print(mat1.getMatrix()[i][j]/mat2.getMatrix()[i][j]+"  ");
                            }
                            System.out.println();
                        }
                    }
                }
                else{
                    System.out.println("InCompatible matrix!!");
                }

            }

            if(n==7){
                hp.printAllMatrixOfSpecificLabelType("Square Matrix");
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose id of a matrix: ");
                int optID = sc.nextInt();
                squareMatrix sq = new squareMatrix();
                double arr [][] = sq.getTranspose(matrix.Matrix.get(optID));
                hp.printMatrix(arr);
            }

            if(n==8){
                hp.printAllMatrixOfSpecificLabelType("Square Matrix");
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose id of a matrix: ");
                int optID = sc.nextInt();
                hp.getInverse(matrix.Matrix.get(optID));
            }

            if(n==9){
                System.out.println("1. Row-wise mean");
                System.out.println("2. Column-wise mean");
                System.out.println("3. mean of all the elements");
                Scanner sc = new Scanner(System.in);
                int optedFunction = sc.nextInt();
                hp.printAllMatrix();
                System.out.println("Enter ID of the matrix chossen ");
                int matrixOpted = sc.nextInt();
                if(optedFunction==1){
                    System.out.print("Row-wise mean of the matrix is: ");
                    matrix optedMatrix =  matrix.Matrix.get(matrixOpted);
                    double arr[][] = hp.check(optedMatrix);
                    for(int i = 0; i<arr.length; i++){
                        double sum = 0;
                        for(int j =  0; j<arr[0].length; j++){
                            sum = sum + arr[i][j];
                        }
                        System.out.print(sum/arr[0].length+"  ");
                    }

                }
                else if(optedFunction==2){
                    System.out.print("Column-wise mean of the matrix is: ");
                    matrix optedMatrix =  matrix.Matrix.get(matrixOpted);
                    double arr[][] = hp.check(optedMatrix);
                    for(int i = 0; i<arr[0].length; i++){
                        double sum = 0;
                        for(int j =  0; j<arr.length; j++){
                            sum = sum + arr[j][i];
                        }
                        System.out.print(sum/arr[0].length+"  ");
                    }
                }
                else if(optedFunction==3){
                    System.out.print("Mean of the matrix is: ");
                    matrix optedMatrix =  matrix.Matrix.get(matrixOpted);
                    double arr[][] = hp.check(optedMatrix);
                    double sum = 0;
                    for(int i = 0; i<arr.length; i++){
                        for(int j =  0; j<arr[0].length; j++){
                            sum = sum + arr[i][j];
                        }
                    }
                    int totalElements = arr.length *  arr[0].length;
                    System.out.print(sum/totalElements);
                }

                System.out.println();
            }

            if(n==10){
                hp.printAllMatrixOfSpecificLabelType("Square Matrix");
                Scanner  sc = new Scanner(System.in);
                int optedMatrixID = sc.nextInt();
                System.out.println(hp.getDet(matrix.Matrix.get(optedMatrixID)));
            }

            if(n==11){
                System.out.println("Use singleton Matrix in Matrix operation. Do you allow it if you do enter 1.");
                Scanner sc = new Scanner(System.in);
                int opted = sc.nextInt();
                if(opted==1){
                    optedScalar();
                }
                else{
                    arthematic();
                }

            }

            if(n==13){
                hp.printAllMatrixOfSpecificLabelType("Square Matrix");
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose ID: ");
                int optID = sc.nextInt();
                matrix mat = matrix.Matrix.get(optID);
                hp.eigen(hp.check(mat));
            }

            if(n==12){
                squareMatrix sq = new squareMatrix();
                hp.printAllMatrixOfSpecificLabelType("Square Matrix");
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose Matrix ID: ");
                int optedID = sc.nextInt();
                matrix mat = matrix.Matrix.get(optedID);
                double arr0[][] = hp.check(mat);
                double arr1[][] = sq.getTranspose(arr0);

                double finalMatrix [][]= new double[mat.getRow()][mat.getColumn()];
                for(int i = 0; i<arr0.length; i++){
                    for(int j = 0; j<arr0.length; j++){
                        finalMatrix[i][j] = arr0[i][j] + arr1[i][j];
                    }
                }
                hp.printMatrix(finalMatrix);
            }

            if(n==14){
                hp.printAllMatrixOfSpecificLabelType("Square Matrix");
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose Matrix ID: ");
                int optedID = sc.nextInt();
                matrix mat0 = matrix.Matrix.get(optedID);

                hp.printAllMatrixOfSpecificLabelType("Column Marix");
                System.out.println("Choose matrix ID: ");
                int optedID1 = sc.nextInt();
                matrix mat1 = matrix.Matrix.get(optedID1);

                if(mat1.getRow()==mat0.getRow()){
                    double arr [][] = hp.getInverse(mat0);
                    if(arr!=null){
                        double arr1[][] = hp.check(mat1);
                        hp.performMultiplication(arr, arr1);
                    }
                    else{
                        System.out.println("Cannot perform this function!!");
                    }
                }
                else{
                    System.out.println("Cannot perform this function!!");
                }
                
            }

            if(n==15){
                typeOfMatrix();
                Scanner sc  = new Scanner(System.in);
                int opt = sc.nextInt();
                String str = hp.ChooseTypeOfMatrix(opt);
                hp.printAllMatrixOfSpecificLabelType(str);
            }


            if(n==16){
                break;
            }

            if(n==17){
                hp.printAllMatrix();
            }
        }

        
    }
}
