
import java.util.Scanner;

public class CinemaRoomManager {

    static int rows;
    static int col;
    static char[][] arr;

    static int currentIncome;
    static int purchasedSeats;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        col = scanner.nextInt();

        arr = new char[rows+1][col+1];

        int userInput;
        buildCinemaSeats();

        do {
            printMenu();
            userInput = scanner.nextInt();
            switch (userInput){
                case 1:
                    printCinemaSeats();
                    break;
                case 2:
                    selectSeat();
                    break;
                case 3:
                    stat();
                    break;
            }
        } while (userInput != 0);
    }

    private static void stat(){
//        int remining
        int totalSeats = calculateTotalSeats();
//        purchasedSeats = 1;

        int totalIncome = totalIncome();

        double percentage = (Double.valueOf(purchasedSeats) / Double.valueOf(totalSeats)) * 100;

        System.out.println("\nNumber of purchased tickets: " + purchasedSeats);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);

    }

    private static void selectSeat(){

        System.out.println();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a row number:");
        int r = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();

//        System.out.println();
        if ((r > rows) || (r <= 0) || (seat > col) || (seat <= 0)) {
            System.out.println("\nWrong input!");
            selectSeat();
        } else {
            boolean status = reserveUserSeat(r, seat);
            if (status == true) {
                int price = ticketPrice(r, seat);
                System.out.println("\nTicket price: $" + price);
                System.out.println();

            }
        }


    }

    private static boolean reserveUserSeat(int r, int seat) {
        char b = 'B';


        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= arr[i].length; j++) {
                if ((r == i) && (seat == j)) {
                    if (arr[i][j] == b) {
                        System.out.println("\nThat ticket has already been purchased!");
                        selectSeat();
                    } else {
                        arr[i][j] = b;
                        purchasedSeats++;
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private static int totalIncome() {
        int firstHalf = 0;
        int secondHalf = 0;
        int halfRow;

        int totalIncome = 0;
        int totalSeats = calculateTotalSeats();

        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else if (totalSeats > 60) {
            if (rows % 2 == 0){
                halfRow = rows /2;
                firstHalf = halfRow * col * 10;
                secondHalf = halfRow * col * 8;
            } else if (rows % 2 != 0) {
                halfRow = rows / 2;
                firstHalf = halfRow * col * 10;
                secondHalf = (halfRow+1) * col * 8;
            }
            totalIncome = firstHalf + secondHalf;
        }
        return totalIncome;
    }

    private static int ticketPrice(int r, int seat) {
        int ticketPrice = 0;
        int totalSeats = calculateTotalSeats();
//        System.out.println(r + "--" + seat +"--" + rows);

        if (totalSeats <= 60){
            ticketPrice = 10;
        } else if (totalSeats > 60) {
            if (r <= (rows / 2)) {
                ticketPrice = 10;
            } else if (r > (rows / 2)){
                ticketPrice = 8;
            }
        }

        currentIncome += ticketPrice;

        return ticketPrice;
    }

    private static int calculateTotalSeats() {
        int totalSeats = rows * col;

        return totalSeats;
    }

    private static void buildCinemaSeats(){
        char s = 'S';

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[i].length; j++) {
                arr[i][j] = s;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    private static void printCinemaSeats() {
        System.out.println("\nCinema:");

        System.out.print(" ");
        for (int i = 1; i < col + 1; i++) {
            System.out.print(" " + i);
        }

        System.out.println();

        for (int i = 1; i < arr.length; i++) {
            System.out.print(i + " ");
            for (int j = 1; j < arr[i].length; j++) {
                char s = arr[i][j];
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }


}
