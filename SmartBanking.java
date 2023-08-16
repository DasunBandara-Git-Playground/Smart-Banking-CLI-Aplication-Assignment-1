import java.util.Scanner;

public class SmartBanking {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String GREEN = "\033[32;1m";
        final String RESET = "\033[0;0m";
        final String BLUE = "\033[34;1m";
        final String RED = "\033[31;1m";

        final String DB = "Welcome to Smart Banking";
        final String CNA = "Create New Account";
        final String DEPO = "Cash Deposit";
        final String WD = "Cash Withdrawals";
        final String TRANS = "Transfer Money";
        final String CAB = "Check Account Balance";
        final String DA = "Delete Account";
        String screen = DB;

        String ER_MSG = String.format("\t%s%s%s\n", RED,"%s",RESET);
        String SUCSS_MSG = String.format("\t%s%s%s\n", GREEN,"%s",RESET);

        int[] ids = new int[0];
        String[] names = new String[0];
        double[] amounts = new double[0];

        mainLoop:
        do{
            String title = String.format("\n\t\t%s%s%s",BLUE,screen,RESET);
            System.out.println(CLEAR);
            System.out.println(title);

            String name;
            double amount;
            String accNo;

            switch(screen){
                case DB:
                    System.out.println("\n\n\t[1] Create New Account\n");
                    System.out.println("\t[2] Deposits\n");
                    System.out.println("\t[3] Withdrawals\n");
                    System.out.println("\t[4] Transfer\n");
                    System.out.println("\t[5] Check Account Balance\n");
                    System.out.println("\t[6] Delete Account\n");
                    System.out.println("\t[7] Exit\n");
                    System.out.print("\n\tEnter the option to continue: ");
                    int option = scan.nextInt();
                    scan.nextLine();

                    switch(option){
                        case 1: screen = CNA; break;
                        case 2: screen = DEPO; break;
                        case 3: screen = WD; break;
                        case 4: screen = TRANS; break;
                        case 5: screen = CAB; break;
                        case 6: screen = DA; break;
                        case 7: 
                            System.out.println(CLEAR);
                            System.exit(0);
                        default:continue;
                    }
                    break;
                
                case CNA:
                    String idFormat = String.format("SDB-%05d",ids.length+1);
                    System.out.printf("\n\n\tID: %s\n",idFormat);

                    //Name Validation
                    
                    
                    loop1:
                    do{
                        System.out.print("\n\tName: ");
                        name = scan.nextLine().strip();

                        if(name.isBlank()){
                            System.out.printf(ER_MSG,"Name can,t be empty");
                            continue;
                        }else{
                            for (int i = 0; i < name.length(); i++) {
                                if(!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i)))){
                                    System.out.printf(ER_MSG,"Invalid name");
                                    continue loop1;
                                }
                            }
                        }
                        break;

                    }while(true);

                    
                    
                    do{
                        System.out.print("\n\tInitial Deposit: ");
                        amount = scan.nextDouble();
                        scan.nextLine();
                        if(amount < 5000){
                            System.out.printf(ER_MSG,"Insufficient Amount");
                            continue;
                        }
                        break;
                    }while(true);

                    int[] newIds = new int[ids.length+1];
                    String[] newNames = new String[names.length+1];
                    double[] newAmounts = new double[amounts.length+1];
                    for (int i = 0; i < ids.length; i++) {
                        newIds[i] = ids[i];
                        newNames[i] = names[i];
                        newAmounts[i] = amounts[i];
                    }
                    newIds[newIds.length-1] = ids.length+1;
                    newNames[newNames.length-1] = name;
                    newAmounts[newAmounts.length-1] = amount;
                    ids = newIds;
                    names = newNames;
                    amounts = newAmounts;

                    System.out.printf("\n\t"+SUCSS_MSG,idFormat+":"+name+" has been created successfully");

                    System.out.print("\n\tDo you want to continue(Y/n): ");
                    if(scan.nextLine().strip().toUpperCase().equals("Y")){
                        continue;
                    }
                    screen = DB;
                    break;
                
                case DEPO:
                    
                    loop2:
                    do{
                        
                        System.out.print("\n\tEnter A/C No: ");
                        accNo = scan.nextLine().strip();
                        //currentId = Integer.valueOf(accNo.substring(5));
                        if(accNo.isBlank()){
                            System.out.printf(ER_MSG,"A/C Number can,t be empty");
                            System.out.print("\n\tDo you want to continue(Y/n): ");
                            if(scan.nextLine().strip().toUpperCase().equals("Y")){
                                continue;
                            }
                            screen = DB;
                            continue mainLoop;
    
                        }else{ 
                            if(!(accNo.startsWith("SDB-") && accNo.length() == 9)){
                                System.out.printf(ER_MSG,"Invalid A/C Number");
                                System.out.print("\n\tDo you want to continue(Y/n): ");
                                if(scan.nextLine().strip().toUpperCase().equals("Y")){
                                    continue;
                                }
                                screen = DB;
                                continue mainLoop;
                            }else{
                                for (int i = 5; i < accNo.length(); i++) {
                                    if(!(Character.isDigit(accNo.charAt(i)))){
                                        System.out.printf(ER_MSG,"Invalid A/C Number");
                                        System.out.print("\n\tDo you want to continue(Y/n): ");
                                        if(scan.nextLine().strip().toUpperCase().equals("Y")){
                                            continue loop2;
                                        }
                                        screen = DB;
                                        continue mainLoop;
                                    }
                                }

                                int count = 0;
                                for (int i = 0; i < ids.length; i++) {
                                    if(ids[i] == Integer.valueOf(accNo.substring(5))){
                                        count++;
                                        break loop2;
                                    }
                                }
                                
                                if(count==0){
                                    System.out.printf(ER_MSG,"A/C Number not available in the List");
                                    System.out.print("\n\tDo you want to continue(Y/n): ");
                                    if(scan.nextLine().strip().toUpperCase().equals("Y")){
                                        continue;
                                    }
                                    screen = DB;
                                    continue mainLoop;
                                    
                                }
                                
                            }
                        }
                        break;
                        
                    }while(true);
                    int currentId = Integer.valueOf(accNo.substring(5));
                    System.out.printf("\n\tCurrent Balance : Rs. %,.2f\n",amounts[currentId-1]);
                    double dipositAmount;
                    do{
                        System.out.print("\n\tDeposit Amount: ");
                        dipositAmount = scan.nextDouble();
                        scan.nextLine();
                        if(dipositAmount<500){
                            continue;
                        }
                        break;
                    }while (true);

                    amounts[currentId-1] += dipositAmount;
                    System.out.printf("\n\tNew Balance : Rs. %,.2f\n",amounts[currentId-1]);
                    System.out.print("\n\tDo you want to continue(Y/n): ");
                    if(scan.nextLine().strip().toUpperCase().equals("Y")){
                        continue;
                    }
                    screen = DB;
                    break;


            }
                        
        }while(true);

    }
}
