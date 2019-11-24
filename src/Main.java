import controller.Controller;
import model.Structure;
import registerview.MainView;

public class Main{
    public static void main(String[] args) {
        Controller c=new Controller(new Structure(),new MainView()); 
    }
}