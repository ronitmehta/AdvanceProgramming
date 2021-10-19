package AdvanceProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


import java.util.Map;



interface commonFunctions{
    void viewAssessments();
    void viewLectureMaterials();
    void viewComments();
    void addComments();
    void logout();
    void menu();
}

class Student implements commonFunctions{
    private String name;
    private int ID ;
    static int temp =0;
    private static ArrayList<Student> students = new ArrayList<>();
    protected static Assessment assessment  = new Assessment();
    public static ArrayList<material> classMaterail = new ArrayList<>();
    private static Comments comment = new Comments();
    protected static Submission  submission = new Submission();

    Student(String name){
        setName(name);
        setID(temp);
        temp++;
    }

    Student(){}

    void setComment(Comments comments){
        Student.comment = comments;
    }

    Comments getComments(){
        return comment;
    }

    void addingClassMaterial(material mat){
        classMaterail.add(mat);
    }

    void setClassMaterial(){
         
    }

    public void addClassMaterial(){
        
    }

    ArrayList<material> getClassMaterial(){
        return classMaterail;
    }

    public void setID(int temp){
        this.ID = temp;
    }

    public int getID(){
        return this.ID;
    }

    public void saveStudent(Student ss){
        students.add(ss);
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    // ***************************************************************************************
    protected Student stu;
    @Override
    public void viewLectureMaterials(){
        if(stu.getClassMaterial()!=null){
            for(int i = 0; i<stu.getClassMaterial().size() ; i++){
                material mat = stu.getClassMaterial().get(i);
                if(mat.getSlides()!=null){
                    if(mat.getSlides().getSlides()!=null){
                        HashMap<Slide, ArrayList<String> > erp = mat.getSlides().getSlides();
                        for(Map.Entry<Slide, ArrayList<String> > ep : erp.entrySet()){
                            System.out.println("Title: "+ep.getKey().getSlide());
                            if(ep.getValue()!=null){
                                int j = 0;
                                for(String str: ep.getValue()){
                                    System.out.println("Slide "+j+": "+str);
                                }
                            }
                            System.out.println("Date Of Uploaded" + ep.getKey().getDate());
                        }
                    }
                }
                if(mat.getvid()!=null){
                    if(mat.getvid().getVideos()!=null){
                        HashMap<videos, String > erp = mat.getvid().getVideos();
                        for(Map.Entry<videos, String> ep: erp.entrySet()){
                            System.out.println("Title of video: " +ep.getKey().getSlide());
                            System.out.println("Video flie: "+ep.getValue());
                            System.out.println("Date of Upload: "+ep.getKey().getDate());
                        }
    
                    }
                }
            }
        }

    }

    @Override
    public void viewAssessments(){
        Assessment temp = new Assessment();
        assessment = temp;
        for(Assessment ass: temp.getAssessments()){
            if(ass.getType().equals("Assignment")){
                System.out.println("ID: "+ass.getID()+" "+ass.getType()+": "+ass.getQuestion()+" Max Marks: "+ass.getMaxMarks());
            }
            else if(ass.getType().equals("Question") && ass.getStatus()==true){
                System.out.println("ID: "+ass.getID()+" "+ass.getType()+": "+ass.getQuestion());
            }
            System.out.println("------------------");
        }

    }

    void submitAssessments(){
       Scanner sc  = new Scanner(System.in);
       Assessment ass = new Assessment();
       Submission sub = new Submission();
       sub.printPendingAssessment(this);
       System.out.println("Enter ID of Assignment: ");
       int ID = sc.nextInt();
       ArrayList<Assessment> arr = ass.getAssessments();
       for(Assessment a: arr){
            if(a.getID()==ID){
                System.out.print(a.getQuestion()+": ");
            }
       }
       String str = sc.nextLine();
       String ans = sc.nextLine();
       Submission ss = new Submission(ass, this, ans, ID);

    }

    void viewGrades(){
        Submission sub = new Submission();
        sub.viewGrades(this);
    }

    @Override
    public void viewComments(){
        Comments cc = new Comments();
        cc.PrintComments();

    }

    @Override
    public void addComments(){
        Comments cc = new Comments(stu.name);
        setComment(cc);
    }

    @Override
    public void logout(){
        return;
    }

    @Override
    public void menu(){
        System.out.println("1. View lecture materials");
        System.out.println("2. View assessments");
        System.out.println("3. Submit assessments");
        System.out.println("4. View grades");
        System.out.println("5. View Comments");
        System.out.println("6. Add Comments");
        System.out.println("7. Logout");
    }

    void help(Student stu) {
        this.stu = stu;
    }
}

class Instructor implements commonFunctions{
    private String name = null;
    static int n = 0;
    private int id = 0;
    private static ArrayList<Instructor> instructors = new ArrayList<>();

    private ArrayList<material> classMaterail = new ArrayList<>();

    private Comments comments = new Comments();

    protected Assessment assessment = new Assessment();


    Instructor(String name){
        name(name);
        id(n);
        n++;
    }

    Instructor(){}

    void setComment(Comments comments){
        this.comments = comments;
    }

    Comments getComments(){
        return comments;
    }

    void addingClassMaterial(material mat){
        classMaterail.add(mat);
    }

    ArrayList<material> getClassMaterial(){
        return classMaterail;
    }

    void name(String name){
        this.name = name;
    }

    void id(int n){
        this.id = n;
    }

    String getName(){
        return name;
    }

    int getID(){
        return id;
    }

    void instructors(Instructor ins){
        instructors.add(ins);
    }

    ArrayList<Instructor> getInstructores(){
        return instructors;
    }

    // **********************************************************************************************************

    @Override
    public void viewAssessments(){
        Assessment temp = new Assessment();
        assessment = temp;
        for(Assessment ass: temp.getAssessments()){
            if(ass.getType().equals("Assignment")){
                System.out.println("ID: "+ass.getID()+" "+ass.getType()+": "+ass.getQuestion()+" Max Marks: "+ass.getMaxMarks());
            }
            else if(ass.getType().equals("Question") && ass.getStatus()==true){
                System.out.println("ID: "+ass.getID()+" "+ass.getType()+": "+ass.getQuestion());
            }
            System.out.println("------------------");
        }
    }


    protected Instructor ins;
    @Override
    public void viewLectureMaterials(){

        if(ins.getClassMaterial()!=null){
            for(int i = 0; i<ins.getClassMaterial().size() ; i++){
                material mat = ins.getClassMaterial().get(i);
                if(mat.getSlides()!=null){
                    if(mat.getSlides().getSlides()!=null){
                        HashMap<Slide, ArrayList<String> > erp = mat.getSlides().getSlides();
                        for(Map.Entry<Slide, ArrayList<String> > ep : erp.entrySet()){
                            System.out.println("Title: "+ep.getKey().getSlide());
                            if(ep.getValue()!=null){
                                int j = 0;
                                for(String str: ep.getValue()){
                                    System.out.println("Slide "+j+": "+str);
                                }
                            }
                            System.out.println("Date Of Uploaded" + ep.getKey().getDate());
                        }
                    }
                }
                if(mat.getvid()!=null){
                    if(mat.getvid().getVideos()!=null){
                        HashMap<videos, String > erp = mat.getvid().getVideos();
                        for(Map.Entry<videos, String> ep: erp.entrySet()){
                            System.out.println("Title of video: " +ep.getKey().getSlide());
                            System.out.println("Video flie: "+ep.getValue());
                            System.out.println("Date of Upload: "+ep.getKey().getDate());
                        }
    
                    }
                }
            }
        }
    }

    void help(Instructor ins){
        this.ins = ins;
    }

    @Override
    public void viewComments(){
        Comments cc = new Comments();
        cc.PrintComments();

    }

    @Override
    public void addComments(){
        Comments cc = new Comments( ins.name);
        setComment(cc);
    }

    @Override
    public void logout(){
        return;
    }

    @Override
    public void menu(){
        System.out.println("1. Add class material");
        System.out.println("2. Add assessments");
        System.out.println("3. View lecture materials");
        System.out.println("4. View assessments");
        System.out.println("5. Grade assessments");
        System.out.println("6. Close assessments");
        System.out.println("7. View comments");
        System.out.println("8. Add comments");
        System.out.println("9. Logout");
    }

    public void addAssessments(){

        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add Assignment");
        System.out.println("2. Add Quiz");
        int opt = sc.nextInt();
        String temp = sc.nextLine();
        if(opt==1){
            System.out.println("Enter problem statement: ");
            String question = sc.nextLine();
            System.out.println("Enter max marks: ");
            int marks = sc.nextInt();
            Assessment ass = new Assessment(marks, question);
        }
        else if(opt==2){
            System.out.println("Enter quiz question: ");
            String question = sc.nextLine();
            Assessment ass = new Assessment(question);
        }

    }

    public void addClassMaterial(){
        material mat = new material();
        addingClassMaterial(mat);
        mat.addClassMaterial();
        Student.classMaterail = getClassMaterial();
    }

    public void gradeAssessmenst(Instructor ins){
        Submission sub = new Submission();
        sub.gradeAssessment(ins);
    }

    public void closeAssessment(){
        Assessment ass = new Assessment();
        ass.closeAssessment();
    }
}

class Submission{
    private Assessment ass ;
    protected Student s;

    private static HashMap<Student, ArrayList<Assessment> > hMap= new HashMap<>();
    
    Submission(){}
    Submission(Assessment ass, Student s, String ans, int ID){
        setAssessment(ass);
        addHmap(s);
        done(s, ans, ID);
    }

    public void setAssessment(Assessment ass){
        this.ass = ass;
    }

    public Assessment getAssessment(){
        return this.ass;
    }

    public void addHmap(Student s){
        if(!hMap.containsKey(s)){
            return;
        }
        
        hMap.put(s, addAssessmentList());
    }

    public HashMap<Student, ArrayList<Assessment> >  getHMap(){
        return hMap;
    }

    public void updateHMap(Student s){
        Assessment ass = new Assessment();
        ArrayList<Assessment> arr = ass.getAssessments();
        hMap.put(s, arr); 
    }

    public ArrayList<Assessment> addAssessmentList(){
        ArrayList<Assessment> arr = getAssessment().getAssessments();
        return arr;
    }

    protected void done(Student s, String ans, int ID){
        updateHMap(s);
        ArrayList<Assessment> arr = hMap.get(s);
        for(Assessment a: arr){
            if(a.getID()==ID){
                if(a.getStatus()==true){
                    a.setAns(ans);
                    a.setSubmitted(true);
                }
                else{
                    System.out.println("Submission is close");
                    System.out.println("No longer accepting responses");
                }
            }
        }
    }

    protected void printPendingAssessment(Student s){
        updateHMap(s);
        ArrayList<Assessment> arr = hMap.get(s);
        System.out.println("Pending assessments");
        for(Assessment a: arr){
            if(a.getSubmiited()==false && a.getStatus()==true){
                System.out.println("ID: "+a.getID()+" "+a.getType()+": "+a.getQuestion()+" Max Marks: "+a.getMaxMarks());
                System.out.println("----------------------------------");

            }
        }
    }

    protected void viewGrades(Student s){
        updateHMap(s);
        ArrayList<Assessment> arr = hMap.get(s);
        System.out.println("Graded Submission");
        for(Assessment a: arr){
            if(a.getSubmiited()==true){
                System.out.println("ID: "+a.getID()+" "+a.getType()+": "+a.getQuestion()+" Ans given:  "+a.getAns());
                System.out.println("Marks Obtained: "+a.getMakrs()+" Out Off: "+a.getMaxMarks());
                System.out.println("Graded by: "+a.gradedBy);
                System.out.println("----------------------------------");
            }
        }
        System.out.println("Ungraded Submissions");
        for(Assessment a: arr){
            if(a.getSubmiited()==false){
                System.out.println("ID: "+a.getID()+" "+a.getType()+": "+a.getQuestion()+" Ans given:  "+a.getAns());
                System.out.println("----------------------------------");
            }
        }
    }

    protected void gradeAssessment(Instructor ins){
        System.out.println("List Of Assignment");
        HashMap<Integer, Boolean> hmp =  new HashMap<>(); 
        HashMap<Student, ArrayList<Assessment>> hm = new HashMap<>();
        ArrayList<Assessment> arr = new ArrayList<>();
        for(Map.Entry<Student, ArrayList<Assessment> > ep : hMap.entrySet()){
            for(Assessment a: ep.getValue()){
                if(!hmp.containsKey(a.getID())){
                    hmp.put(a.getID(), true);
                    arr.add(a);
                    hm.put(ep.getKey(), arr);
                    System.out.println("ID: "+a.getID()+" "+a.getType()+": "+a.getQuestion()+" Max Marks: "+a.getMaxMarks());
                    System.out.println("----------------------------------");
                }
            }
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID of assessment to view submission: ");
        int n = sc.nextInt();
        System.out.println("Choose ID from these ungradedd submissions");
        for(Map.Entry<Student, ArrayList<Assessment>> ep: hm.entrySet()){
            for(Assessment a: ep.getValue()){
                if(a.getID()==n){
                    System.out.println(ep.getKey().getID()+"  "+ep.getKey().getName());
                }
            }
        }
        System.out.println("Choose Student ID: ");
        int sID = sc.nextInt();
        for(Map.Entry<Student, ArrayList<Assessment>>ep: hMap.entrySet()){
            if(ep.getKey().getID()==sID){
                System.out.print("Submission: ");
                for(Assessment a: ep.getValue()){
                    if(a.getID()==n){
                        System.out.print(a.getAns());
                        System.out.println("-----------------------------");
                        System.out.println("Max Marks: "+a.getMaxMarks());
                        System.out.print("Marks Scored: ");
                        int marksScored = sc.nextInt();
                        a.setMarks(marksScored);
                        a.gradedBy = ins.getName();
                    }
                }
            }
        }   
    }
}

class Assessment{
    private String type; 
    private int Maxmarks ;
    private String question;
    private boolean submitted = false;
    private int marks = -1;
    private String ans;
    protected String gradedBy ;
    private boolean status = true;  // open or close
    private static ArrayList<Assessment> assessments = new ArrayList<>();
    private int ID;
    private static int spare = 0;

    Assessment(){
    }

    Assessment(int marks, String question){
        setType("Assignment");
        setID();
        setMaxMarks(marks);
        setQuestion(question);
        addAssessment(this);
        spare++;

    }

    Assessment(String question){
        setType("Question");
        setID();
        setMaxMarks(1);
        setQuestion(question);
        addAssessment(this);
        spare++;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean getStatus(){
        return this.status;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setMaxMarks(int Maxmarks){
        this.Maxmarks = Maxmarks;
    }
    public int getMaxMarks(){
        return this.Maxmarks;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public String getQuestion(){
        return question;
    }

    public void addAssessment(Assessment ass){
        assessments.add(ass);
    }

    public ArrayList<Assessment> getAssessments(){
        return assessments;
    }

    public int getSpare(){
        return spare;
    }

    public void setID(){
        this.ID = getSpare();
    }

    public int getID(){
        return ID;
    }

    public void setMarks(int marks){
        this.marks = marks;
    }

    public int getMakrs(){
        return this.marks;
    }

    public void setAns(String ans){
        
        if(this.getType()=="Assignment"){
            if(isValid(ans)){
                this.ans = ans;
                setSubmitted(true);
            }
        }
        else if(this.getType()=="Question"){
            this.ans = ans;
        }
    }

    public String getAns(){
        return this.ans;
    }
    
    public void setSubmitted(boolean submitted){
        this.submitted = submitted;
    }

    public boolean getSubmiited(){
        return this.submitted;
    }

    protected boolean isValid(String ans){
        if(ans==null){
            return false;
        }
        else if(ans.length()>4){
            String s = ans.substring(ans.length()-4);
            if(s.equals(".zip")){
                return true;
            }
            else{
                System.out.println("Invalid!!!");
                System.out.println("Only zip file is allowed!!");
                return false;
            }
        }
        else{
            return false;
        }
    }

    public void closeAssessment(){
        System.out.println("List of Open Assignments");
        for(Assessment a : getAssessments()){
            if(a.getStatus()==true){
                System.out.println("ID: "+a.getID()+" "+a.getType()+": "+a.getQuestion()+" Max Marks: "+a.getMaxMarks());
                System.out.println("----------------------------------");
            }
        }
        System.out.println("Enter ID of assignment to close: ");
        Scanner sc = new Scanner(System.in);
        int ID = sc.nextInt();
        for(Assessment as: getAssessments()){
            if(as.getID()==ID){
                as.setStatus(false);
            }
        }
    }

}



class Comments{
    private String name;
    private String date;
    private String comment;
    private static ArrayList<Comments> comments = new ArrayList<>();

    Comments(){

    }

    Comments(String name){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Comment: ");
        String str = sc.nextLine();
        setName(name);
        setComment(str);
        setDate();
        saveComments(this);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setDate(){
        java.util.Date n=new java.util.Date();  
        String  date = n.toString();
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }

    public void saveComments(Comments cc){
        comments.add(cc);
    }

    public ArrayList<Comments> getComments(){
        return comments;
    }

    public void PrintComments(){
        for(Comments cc : getComments()){
            System.out.println(cc.comment+" - "+cc.name);
            System.out.println(cc.date);
            System.out.println();
        }
    }
}

class Slide{
    private String slide;
    private String date;
    private static HashMap<Slide, ArrayList<String>> slides = new HashMap<>();


    Slide(){}

    Slide(String topic){
        slide(topic);
        date();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of slides: ");
        int n = sc.nextInt();
        String str = sc.nextLine();
        for(int i = 0; i<n; i++){
            System.out.print("Enter content of slide "+i+": ");
            String s = sc.nextLine();
            putContentTOSlide(this, s);
        }

    }

    void date(){
        java.util.Date n=new java.util.Date();  
        String  date = n.toString();
        this.date = date;
    }

    String getDate(){
        return date;
    }

    void slide(String slide){
        this.slide = slide;
    }

    String getSlide(){
        return slide;
    }

    void putNewSlide(Slide mat){
        slides.put(mat, new ArrayList<String>());
    }

    void putContentTOSlide(Slide mat, String content){
        if(slides.get(mat)==null){
            ArrayList<String> arr = new ArrayList<>();
            arr.add(content);
            slides.put(mat, arr);
            return;
        }
        ArrayList<String> save = slides.get(mat);
        save.add(content);
        slides.put(mat, save);
    }

    HashMap<Slide, ArrayList<String>> getSlides(){
        return slides;
    }

}

class videos{
    private String topic;
    private String date;
    private static HashMap<videos, String> video = new HashMap<>();

    videos(){}
    videos(String topic){
        date();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter filename of video: ");
        while(true){
            String str = sc.nextLine();
            if(isValid(str)){
                slide(topic);
                setVideos(this, str);
                break;
            }
        }
    }

    private boolean isValid(String str) {
        if(str.length()>4){
            String s = str.substring(str.length()-4);
            if(s.equals(".mp4")){
                return true;
            }
        }
        return false;
    }

    void date(){
        java.util.Date n=new java.util.Date();  
        String  date = n.toString();
        this.date = date;
    }

    String getDate(){
        return date;
    }

    void setVideos(videos vid, String mp4){
        video.put(vid, mp4);
    }

    HashMap<videos, String> getVideos(){
        return video;
    }

    void slide(String topic){
        this.topic = topic;
    }

    String getSlide(){
        return topic;
    }

}



class material{

    private static Slide slides;
    private static videos vid;

    public void slides(Slide slides){
        material.slides = slides; 
    }

    public Slide getSlides(){
        return slides;
    }

    public void vid(videos vid){
        material.vid = vid;
    }

    public videos getvid(){
        return vid;
    }
    
    public void addClassMaterial() {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("1. Add Lecture Slide");
            System.out.println("2. Add Lecture Video");
            int n = sc.nextInt();
            if(n==1){
                System.out.println("Enter topic of slides: ");
                String str = sc.nextLine();
                String topic = sc.nextLine();
                Slide sid = new Slide(topic);
                slides = sid;
                return;
            }
            if(n==2){
                System.out.println("Enter topic of videos: ");
                String str = sc.nextLine();
                String topic = sc.nextLine();
                videos video = new videos(topic);
                vid = video;
                return;
            }
        }

    }
    
}

class helper{
    void CalledInstructor(Instructor ins){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Welcome "+ins.getName());
            ins.menu();
            int inp = sc.nextInt();
            if(inp==1){
                ins.addClassMaterial();
            }

            else if(inp==3){
                ins.help(ins);
                ins.viewLectureMaterials();
            }
            else if(inp==9){
                ins.logout();
                return;
            }
            else if(inp==8){
                ins.help(ins);
                ins.addComments();
            }
            else if(inp==7){
                ins.viewComments();
            }
            else if(inp==2){
                ins.addAssessments();
            }
            else if(inp==4){
                ins.viewAssessments();
            }
            else if(inp==5){
                ins.gradeAssessmenst(ins);
            }
            else if(inp==6){
                ins.closeAssessment();
            }
        }
    }

    void CalledStudent(Student stu){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Wwlcome "+stu.getName());
            stu.menu();
            int inp = sc.nextInt();
            if(inp==1){
                stu.help(stu);
                stu.viewLectureMaterials();
            }

            else if(inp==2){
                stu.viewAssessments();
            }
            else if(inp==7){
                stu.logout();
                return;
            }
            else if(inp==6){
                stu.help(stu);
                stu.addComments();
            }
            else if(inp==5){
                stu.viewComments();
            }
            else if(inp==3){
                stu.submitAssessments();
            }
            else if(inp==4){
                stu.viewGrades();
            }

        }

    }
}

public class Assignment2 {

    private static void menu() {
        System.out.println("Welcome to Backpack");
        System.out.println("1. Enter as Instructor");
        System.out.println("2. Enter as student");
        System.out.println("3. Exit");
    }
    public static void main(String[] args) {
        menu();
        Scanner sc = new Scanner(System.in);
        int inp = sc.nextInt();
        Instructor obj0 = new Instructor("I0");
        Instructor obj1 = new Instructor("I1");
        obj0.instructors(obj0);
        obj0.instructors(obj1);

        Student obj2 = new Student("S0");
        Student obj3 = new Student("S1");
        Student obj4 = new Student("S2");
        obj2.saveStudent(obj2);
        obj2.saveStudent(obj3);
        obj2.saveStudent(obj4);

        helper bp = new helper();
        while(true){

            if(inp==1){
                System.out.println("You Entered as an Instructor");
                for(Instructor ins : obj0.getInstructores()){
                    System.out.println(ins.getID()+"  "+ins.getName());
                }
                System.out.println("Choose ID: ");
                int chooseID = sc.nextInt();
                Instructor ins = obj0.getInstructores().get(chooseID);
                bp.CalledInstructor(ins);
            }

            else if(inp==2){
                System.out.println("You Entered as a Student");
                for(Student stu : obj2.getStudents()){
                    System.out.println(stu.getID()+"  "+stu.getName());
                }
                System.out.println("Choose ID: ");
                int chooseID = sc.nextInt();
                Student stu = obj2.getStudents().get(chooseID);
                bp.CalledStudent(stu);

            }
            else if(inp==3){
                System.out.println("End Of Test Cases");
                break;
            }
            else{
                System.out.println("You have Entered the wrong number");
            }
            menu();
            inp = sc.nextInt();
        }
        sc.close();
    }
    
}
