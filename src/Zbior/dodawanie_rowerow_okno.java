package Zbior;
import pl.Rower.Rower;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class dodawanie_rowerow_okno extends JFrame {
    private JTextPane textPane1;
    private JPanel panel_dodawania;
    private JButton dodajButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    zbior_rowerow r1 = new zbior_rowerow();


    public dodawanie_rowerow_okno() {
        textField3.addActionListener(e -> textField2.grabFocus());
        textField2.addActionListener(e -> textField1.grabFocus());
        textField1.addActionListener(e -> dodajButton.grabFocus());
        dodajButton.addActionListener(e -> dodajButton.grabFocus());
        dodajButton.addActionListener((this::actionPerformed));
        dodajButton.addActionListener((e -> button_dodaj()));
    }

    public void window(){
        czyszczenie_wierszy();
        setContentPane(panel_dodawania);
        setTitle("WYPOZYCZALNIA ROWEROW");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300,250));
        setResizable(false);
        setVisible(true);
    }

    public void button_dodaj(){
        try{
        r1.dodanie_rowerow_z_pliku(r1.odczytanie_z_pliku("zapis.txt"));
        int id = Integer.parseInt(textField3.getText());
        int rozmiar = Integer.parseInt(textField2.getText());
        int cena = Integer.parseInt(textField1.getText());
        if((cena <0 || id<0 ||cena<0)){
            JOptionPane.showMessageDialog(null, "ZŁE DANE!");
            return;
        }
        Rower rower = new Rower(id, rozmiar, cena, false);
        r1.dodaj_rower(rower);
        r1.zapis_do_pliku("zapis.txt");
        }
        catch(Exception exception){
            System.out.println("Błąd danych");
        }
    }

    public void actionPerformed(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp); // funkcja do zamykania okna dodawania w aplikacji.
        win.dispose();
    }

    public void czyszczenie_wierszy(){
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
    }
}




