import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map.Entry;

class Vaccine{

    String name;
    int doseRequired = 0;
    int gapBetween = 0;

    Vaccine(String name, int doseRequired, int gap){
        this.name = name;
        this.doseRequired = doseRequired;
        this.gapBetween = gap;
    }

    Vaccine(String name, int doseRequired){
        this.name = name;
        this.doseRequired = doseRequired;
        this.gapBetween = 0;
    }

    void printDetails(){
        System.out.println("Vaccine Name: "+name+", Number of Doses: "+doseRequired+", Gap Between Doses: "+gapBetween);
    }

    int getdoseRequired(){
        return doseRequired;
    }

    String getName(){
        return name;
    }

    int getGap(){
        return gapBetween;
    }

}

class Register{
    static int n = 0;
    String name;
    int pinCode = 0;
    int ID = 0;

    Register(String name, int pinCode){
        this.pinCode = pinCode;
        this.name = name;
        this.ID = 100000 + n;
        n++;
    }

    void printDetails(){
        System.out.println("Hospital Name: "+ name+ ", PinCode: "+pinCode+", Unique ID: "+ID);
    }

    int getPinCode(){
        return pinCode;
    }

    int getID(){
        return ID;
    }

    String getName(){
        return name;
    }
}

class Citizen{
    String name;
    int age = 0;
    String ID;
    String doseTaken = null ;
    int daysToBe = 0;
    int numOfDoseTaken = 0;

    Citizen(String name, int age, String iD2){
        this.name = name;
        this.age = age;
        this.ID = iD2;
    }
    void printDetails(){
        System.out.println("Citizen Name: "+name+", Age: "+age+", Unique ID: "+ID);
    }

    String getName(){
        return name;
    }

    int getAge(){
        return age;
    }

    int getDaysToBe(){
        return daysToBe;
    }

    int getNumOfDoseTaken(){
        return numOfDoseTaken;
    }

    String getID(){
        return ID;
    }

    String getDoseTaken(){
        return doseTaken;
    }

}

class Slot{
    int HospitalId = 0;
    int numOfSlot = 0;
    int day = 0;
    int quantity = 0;
    String vaccine = null;

    Slot(int HospitalId, int numOfSlot, int day,int quantity, String vaccine){
        this.HospitalId = HospitalId;
        this.numOfSlot = numOfSlot;
        this.day = day;
        this.quantity = quantity;
        this.vaccine = vaccine;
    }

    void printDetails(){
        System.out.println("Slot added by Hospital "+ HospitalId+" for Days: "+day+", Available Quantity: " +quantity+" of Vaccine " + vaccine
        );
    }

    int getHospitalID(){
        return HospitalId;
    }

    int getNumOfSlot(){
        return numOfSlot;
    }

    int getDay(){
        return day;
    }
    int getQuantity(){
        return quantity;
    }

    String getVaccine(){
        return vaccine;
    }
}

class CoWin{

    static HashMap<String, Vaccine> vaccineName = new HashMap<>();  // name     vaccine

    static HashMap<Integer, Register> hospitalID = new HashMap<>();  // ID     register

    static HashMap<String, Citizen> citizenID = new HashMap<>();    //  ID      citizen

    static ArrayList<Slot> slotVaccine = new ArrayList<>();  //  Slot


    // int dayIssue = a;
    
    void addVaccine(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Vaccine Name: ");
        String name = sc.nextLine();
        if(vaccineName.containsKey(name)){
            System.out.println("Vaccine already exist!!");
            return;
        }
        System.out.print("Number of Doses: ");
        int doses = sc.nextInt();
        Vaccine vac;
        if(doses>1){
            System.out.print("Gap between doses: ");
            int gap = sc.nextInt();
            vac = new Vaccine(name, doses, gap);
        }
        else{
            vac = new Vaccine(name, doses);
        }
        vaccineName.put(name, vac);
        vac.printDetails();
    }

    void registerHospital(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Hospital Name: ");
        String name = sc.nextLine();
        System.out.print("PinCode: ");
        int pincode = sc.nextInt();

        Register hospital = new Register(name, pincode);

        hospitalID.put(hospital.ID, hospital);
        hospital.printDetails();
    }

    void registerCitizen(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Citizen Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = sc.nextInt();

        System.out.print("Unique ID: ");
        String cc = sc.nextLine();
        String ID = sc.nextLine();
        
        if(citizenID.containsKey(ID)){
            System.out.println("User alreqqady exist");
            return;
        }
        else{
            if(ID.length()==12){
                Citizen cit = new Citizen(name, age, ID);
                cit.printDetails();
                if(age>=18){
                    citizenID.put(ID, cit);
                }
                else{
                    System.out.println("Only 18 above are allowed");
                }
            }
            else{
                System.out.println("Invalid User ID!!");
            }
        }

    }


    void addSlot(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Hospital ID: ");
        int HospitalId = sc.nextInt();
        if(hospitalID.containsKey(HospitalId)){
            System.out.print("Enter Number of Slots to be added: ");
            int numOfSlot = sc.nextInt();
            int i = 0;
            int n = numOfSlot;
            while(i<n){
                System.out.print("Enter Day Number: ");
                int days = sc.nextInt();
                System.out.print("Enter Quantity: ");
                int quantity = sc.nextInt();
                System.out.println("Select Vaccine");
                int d= 0;
                for(Entry<String, Vaccine> ep : vaccineName.entrySet()){
                    String str = ep.getKey();
                    System.out.println(d+". "+str);
                    d++;
                }
                int selectedVaccine = sc.nextInt();
                int b = 0;
                String vaccine = "" ;
                for(Entry<String, Vaccine> erp : vaccineName.entrySet()){
                    if(b==selectedVaccine){
                        vaccine = vaccine + erp.getKey();
                        break;
                    }
                    b++;
                }
                Slot sv = new Slot(HospitalId, numOfSlot, days, quantity, vaccine);
                sv.printDetails();
                slotVaccine.add(sv);
                i++;
            }
        }

    }

    int isVaccinated(String uniqueID){
        Citizen ID = citizenID.get(uniqueID);
        if(ID.getDoseTaken()==null){
            return -1;
        }
        int totalDoseNeedToBeVaccinated = vaccineName.get(ID.getDoseTaken()).getdoseRequired();
        return totalDoseNeedToBeVaccinated-citizenID.get(uniqueID).getNumOfDoseTaken();
    }

  
    void getDone(int enterHospitalID, String uniqueID, String vac){
        Scanner sc = new Scanner(System.in);
        ArrayList<Slot> arr = new ArrayList<>();
        int a = 0;
        for(Slot s : slotVaccine){
            if(s.getHospitalID()==enterHospitalID){
                if(vac == null && s.quantity>0){
                    System.out.println(a+"-> Day: "+s.getDay()+ " Available Qty: "+s.getQuantity()+" Vaccine: "+s.getVaccine());
                    arr.add(s);
                    a++;
                }
                else if(vac!=null&&s.quantity>0){
                    if(s.vaccine.equals(vac)){
                        System.out.println(a+"-> Day: "+s.getDay()+ " Available Qty: "+s.getQuantity()+" Vaccine: "+s.getVaccine());
                        arr.add(s);
                        a++;
                    }
                }
            }
        }
        if(arr.size()==0){
            System.out.print("No vaccine is available!!!");
            return;
        }
        else{
            System.out.print("Choose Slot: ");
            int chooseSlot = sc.nextInt();
            Slot toBeOpted = arr.get(chooseSlot);


            Citizen toBeVaccinated = citizenID.get(uniqueID);
            toBeVaccinated.doseTaken = toBeOpted.getVaccine();
            if(toBeVaccinated.daysToBe<=toBeOpted.day){
                Vaccine vacDetails = vaccineName.get(toBeOpted.vaccine);
                toBeVaccinated.daysToBe = vacDetails.getGap() + toBeOpted.getDay();

                toBeVaccinated.doseTaken = toBeVaccinated.getDoseTaken();

                toBeVaccinated.numOfDoseTaken = toBeVaccinated.getNumOfDoseTaken() +1;

                toBeOpted.quantity = toBeOpted.getQuantity() - 1;

                System.out.println(toBeVaccinated.getName()+" vaccinated with "+toBeVaccinated.getDoseTaken());
            }
            else{
                System.out.println(toBeVaccinated.getName()+" your have to vaccinate on "+ toBeVaccinated.getDaysToBe());
            }
            
        }
    }



    void check(String uniqueID, String vac, int selectedOption){
        Scanner sc = new Scanner(System.in);
        if(selectedOption==3){
            return ;
        }
        else if (selectedOption==1){
            System.out.print("Enter PinCode: ");
            int pin = sc.nextInt();
            ArrayList<Register> handleError = new ArrayList<>();
            for(Entry<Integer, Register> ep : hospitalID.entrySet()){
                Register rr = ep.getValue();
                if(rr.pinCode==pin){
                    handleError.add(rr);
                    System.out.println(rr.getID()+" "+rr.getName());
                    // a++; 
                }
            }

            if(handleError.size()==0){
                System.out.println("Not available!!!");
                return;
            }

            else{
                System.out.print("Enter hospital id: ");
                int enterHospitalID = sc.nextInt();
                getDone(enterHospitalID, uniqueID, null);
            }
        }
        else if(selectedOption==2){
            System.out.print("Enter Vaaccine name: ");
            String name = sc.nextLine();
            for(Slot s: slotVaccine){
                if(s.getVaccine().equals(name)){
                    System.out.println(s.getHospitalID()+" "+hospitalID.get(s.getHospitalID()).name);
                }
            }
            System.out.print("Enter hospital id: ");
            int enterHospitalID = sc.nextInt();
            getDone(enterHospitalID, uniqueID, name);
        }
    }


    void bookSlot(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter patient Unique ID: ");
        String patient = sc.nextLine();

        if(citizenID.containsKey(patient)){
            Citizen cc = citizenID.get(patient);
            String patientID = cc.getID();
            int totaldoses =isVaccinated(patientID);
            if(totaldoses==0){
                System.out.println("You are Fully Vaccinated, No need!!");
                return;
            }
            else if (totaldoses==-1){  // citizenID.get(patientID).numOfDoseTaken == 0
                System.out.println("1. Search by area");
                System.out.println("2. Search by Vaccine");
                System.out.println("3. Exit");
                
                System.out.print("Enter option: ");
                int selectedOption = sc.nextInt();
                check(patientID, null, selectedOption);
            }
            else if(totaldoses>0){
                System.out.println("1. Search by area");
                System.out.println("2. Search by Vaccine");
                System.out.println("3. Exit");
                
                System.out.print("Enter option: ");
                int selectedOption = sc.nextInt();
                if(selectedOption==3){
                    return;
                }
                else if(selectedOption==1){
                    System.out.print("Enter PinCode: ");
                    int pin = sc.nextInt();
                    ArrayList<Register> handleError = new ArrayList<>();
                    for(Entry<Integer, Register> ep : hospitalID.entrySet()){
                        Register rr = ep.getValue();

                        for(Slot s: slotVaccine){
                            if(rr.getPinCode()==pin && s.getVaccine()==citizenID.get(patientID).getDoseTaken()){
                                handleError.add(rr);
                                System.out.println(rr.getID()+" "+rr.getName());
                                // a++; 
                            }
                        }
                        
                    }
                    if(handleError.size()==0){
                        System.out.println("NO centre is available for vaccination in this area!!!");
                        return;
                    }
        
                    else{
                        System.out.print("Enter hospital id: ");
                        int enterHospitalID = sc.nextInt();
                        getDone(enterHospitalID, patientID, null);
                    }
                }

                else if(selectedOption==2){
                    String vName = citizenID.get(patientID).getDoseTaken();
                    for(Slot s: slotVaccine){
                        if(s.vaccine.equals(vName)){
                            System.out.println(s.getHospitalID()+" "+hospitalID.get(s.getHospitalID()).getName());
                        }
                    }
                    System.out.print("Enter hospital id: ");
                    int enterHospitalID = sc.nextInt();
                    getDone(enterHospitalID, patientID, vName);
                }
            }
        }        
    }


    void CheckVaccinationStatus(){
        Scanner sc = new Scanner(System.in);
        System.out.println("ENter Patient ID: ");
        String patientID = sc.nextLine();

        if(citizenID.containsKey(patientID)){
            Citizen cit = citizenID.get(patientID);
            if(cit.doseTaken==null){
                System.out.println("Not vaccinated!!");
            }
            else{
                String vac = cit.doseTaken;
                int takenDoses = isVaccinated(cit.getID());
                if(takenDoses==0){
                    System.out.println("Fully Vaccinated!!!");
                    System.out.println("Vaccine Given: "+vac);
                    System.out.println("Number of Doses given: "+cit.getNumOfDoseTaken());
                }
                else{
                    System.out.println("Partially Vaccinated!!");
                    System.out.println("Vaccine Given: "+vac);
                    System.out.println("Number of Doses given: "+cit.getNumOfDoseTaken());
                }
            }
        }
        else{
            System.out.println("Register yourSelf First!!!");
        }
    }

    void listSlotForHospital(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Hospital ID: ");
        int hospital = sc.nextInt();
        int i = 0;
        int n = slotVaccine.size();
        while(i<n){
            Slot s = slotVaccine.get(i);
            if(s.getHospitalID() == hospital){
                System.out.println("Day: "+s.getDay()+" Vaccine: "+s.getVaccine()+" Available Qty: "+s.getQuantity());
            }
            i++;
        } 
    }
}




public class Assignmment1{

    public static void menu() {
        System.out.println("...............................");
        System.out.println("1 Add Vaccine");
        System.out.println("2 Register Hospital");
        System.out.println("3 Register Citizen");
        System.out.println("4 Add Slot for Vaccination");
        System.out.println("5 Book Slot for Vaccination");
        System.out.println("6 List all slots for a hospital");
        System.out.println("7 Check Vaccination Status");
        System.out.println("8 Exit"); 
        System.out.println("...............................");

    } 

    public static void main(String[] args) {
        System.out.println("Cowin Portal intialized.......");
        Scanner sc = new Scanner(System.in);
        menu();
        CoWin covin = new CoWin();
        int inp =  sc.nextInt();
        while(inp!=8){
            if(inp==1){
                covin.addVaccine();
            }
            else if(inp==2){
                covin.registerHospital();
            }
            else if(inp==3){
                covin.registerCitizen();
            }
            else if(inp==4){
                covin.addSlot();
            }
            else if(inp==5){
                covin.bookSlot();
                // covin.ex();
            }
            else if(inp==6){
                covin.listSlotForHospital();
            }
            else if(inp==7){
                covin.CheckVaccinationStatus();
            }
            else if(inp==8){
                System.out.println("{End of Test Case}");
                break;
            }
            
            menu();
            inp = sc.nextInt();
        }

        sc.close();
    }
}
