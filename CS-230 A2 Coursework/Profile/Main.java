    public class Main {

    public static void main (String args[]) {
    System.out.println(" Creat a profile which called Hao Wu");    
    Profile player1 = new Profile ("Hao Wu");
    System.out.println("add the data Hao Wu and Bob");
    player1.saveProfile("Hao Wu", 0);
    player1.saveProfile("Bob", 10);
    System.out.println("get the score of Hao Wu");
    player1.loadProfile("Hao Wu");
    System.out.println("Change the score to 10 of Hao Wu");
    player1.saveProfile("Hao Wu", 10);
    System.out.println("get the score of Hao Wu");
    player1.loadProfile("Hao Wu");
    player1.loadProfile("Bob");
    System.out.println("change the name from Hao Wu to Lambert");
    player1.changeName("Hao Wu", "Lambert");
    System.out.println("get the score of  Lambert");
    player1.loadProfile("Lambert");
    System.out.println("delete the Bob");
    player1.deleteProfile("Bob");
    player1.loadProfile("Bob");
    System.out.println("reset the player profile");
    player1.reset();
    player1.loadProfile("Hao Wu");
}
    }

    
