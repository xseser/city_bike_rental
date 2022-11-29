package Zbior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Wypozyczalnia_wypozyczenie extends JFrame{
    private JTextField textField1;
    private JButton OKButton;
    private JPanel Wypozyczalnia_panel;
    zbior_rowerow r1 = new zbior_rowerow();

    public void window(){
        czyszczenie_wierszy();
        setContentPane(Wypozyczalnia_panel);
        setTitle("WYPOZYCZALNIA ROWEROW");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(320,150));
        setResizable(false);
        setVisible(true);
    }

    Wypozyczalnia_wypozyczenie(){
        textField1.addActionListener(e -> OKButton.grabFocus());
        OKButton.addActionListener((this::actionPerformed));
        OKButton.addActionListener(e -> wypozyczenie_roweru());
    }

    public void czyszczenie_wierszy(){
        textField1.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp); // funkcja do zamykania okna dodawania w aplikacji.
        win.dispose();
    }

    public void wypozyczenie_roweru(){
        r1.dodanie_rowerow_z_pliku(r1.odczytanie_z_pliku("zapis.txt"));
        int ID;
        ID = Integer.parseInt(textField1.getText());
        if(ID<0){
            JOptionPane.showMessageDialog(null, "ID NIE MOZE BYC MNIEJSZE OD 0");
            return;
        }
        r1.wypozycz_rower(ID);
        r1.zapis_do_pliku("zapis.txt");
    }
}



