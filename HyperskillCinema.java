import java.util.Scanner;

public class HyperskillCinema {

    static Scanner scanner = new Scanner(System.in);
    static char[][] cinema;
    static int selectedRow;
    static int selectedColumn;
    static int rows;
    static int columns;
    static int totalIncome;

    //Method to print the cinema layout
    public static void cinemaLayout(char[][] cinema){
        for(char[] row: cinema){
            for(char seat: row){
                System.out.print(seat + " ");
            }
            System.out.println();
        }
    }

    //Showing the options
    public static void menu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    
    //For user to choose row and seat number
    public static void chooseSeat(){
        System.out.println("Enter a row number:");
        selectedRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        selectedColumn = scanner.nextInt();
        
        if(selectedRow<1 ||selectedRow>rows||selectedColumn<1||selectedColumn>columns){
            System.out.println("Wrong input!");
            chooseSeat();
        }
    }
    
    public static void calculateTotalIncome(){
        for(int i=1 ; i<=rows ; i++){
            for(int j=1 ; j<=columns ; j++){
                totalIncome += calculateTicketPrice(i);
            }
        }

    }

    public static int calculateTicketPrice(int a){
        int totalSeats = rows*columns;
        int firstHalf = rows/2;
        int secondHalf = rows/2 + 1;
        int ticketPrice;

        if(totalSeats<=60){
            ticketPrice = 10;
        } else if(a<=firstHalf){
            ticketPrice = 10;
        } else{
            ticketPrice = 8;
        }

        return ticketPrice;
    }


    public static void main(String[] args) {


        //Scanning initial input of how many r and c the cinema has
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        columns = scanner.nextInt();
        
        calculateTotalIncome();

        //Creation of 2D array to store the seats data
        cinema = new char[rows+1][columns+1];
        for(int i=0;i<cinema.length;i++){
            for(int j=0;j<cinema[i].length;j++) {
                if (i == 0 && j != 0) {
                    cinema[0][j] = (char) (j + 48);
                } else if (j == 0 && i != 0) {
                    cinema[i][0] = (char) (i + 48);
                } else if (i == 0 && j == 0) {
                    cinema[i][j] = ' ';
                } else {
                    cinema[i][j] = 'S';
                }
                //System.out.print(cinema[i][j] + " ");
            }
            //System.out.println();
        }


//        //Initialize and show menu first time
//        menu();

//        //Reading the input for which option they chose
//        int optionChose = scanner.nextInt();

        int optionChose;
        int noOfPurchasedTickets = 0;
        int currentIncome = 0;


        do {
            menu();
            optionChose = scanner.nextInt();
            switch (optionChose) {
                case 1: //Show the seats
                    System.out.println("Cinema:");
                    cinemaLayout(cinema);
                    break;
                case 2: //Buy a ticket
                    chooseSeat();
                    while(cinema[selectedRow][selectedColumn] == 'B'){
                        System.out.println("That ticket has already been purchased!");

                        //User to re-enter a row and seat number
                        chooseSeat();
                    }

                    cinema[selectedRow][selectedColumn] = 'B';


                    //Printing ticket price of that seat
                    int ticketPrice = calculateTicketPrice(selectedRow);

                    noOfPurchasedTickets++;
                    currentIncome += ticketPrice;

                    System.out.println("Ticket price: $" + ticketPrice);
                    break;
//                case 0: //Exit
//                    break;

                case 3: //Statistics
                    System.out.println("Number of purchased tickets: "  + noOfPurchasedTickets);
                    System.out.printf("Percentage: %.2f%% %n", ((float)noOfPurchasedTickets/(rows*columns))*100);
                    System.out.println("Current income: $" + currentIncome);
                    System.out.println("Total income: $" + totalIncome);

            }
        } while (optionChose != 0);

    }
}