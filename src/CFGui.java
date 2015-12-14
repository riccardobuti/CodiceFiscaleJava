import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CFGui extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = -1990733913585687883L;

// Variabili di istanza
  // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  private CFGenerator cfg;
  
  @SuppressWarnings("rawtypes")
private JComboBox mesi,sessoText;
  private JLabel nome, cognome, comune, giorno, anno,mese, codiceFiscale, sesso;
  private JTextField nomeText, cognomeText, comuneText, giornoText, annoText, codiceFiscaleText;
  private JButton generaCodice;
  
  // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
CFGui() {
    super("Generatore di Codici Fiscali | (c) Riccardo Buti |");
    Object[] elenco = {"","Gennaio","Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
                       "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"
                      };
    
    // Pannello che copre il frame
    JPanel generale = new JPanel(new BorderLayout());
    generale.setBackground(Color.WHITE);
    
    // Pannello con la descrizione sull'applicazione
    JPanel pannelloDescrizione = new JPanel();
    pannelloDescrizione.setBackground(Color.WHITE);
    JLabel descrizione = new JLabel("Calcola il codice Fiscale");
    pannelloDescrizione.add(descrizione);
    
    
    // Pannello che contiene gli elementi a griglia - centrati [se da problemi, inserirlo in un flowlayout]
    JPanel griglia1 = new JPanel();
    griglia1.setBackground(Color.WHITE);
    JPanel griglia = new JPanel(new GridLayout(7,2));
    griglia.setBackground(Color.WHITE);
    
    nome = new JLabel("Nome:");
    nomeText = new JTextField(10);
    cognome = new JLabel("Cognome:");
    cognomeText = new JTextField(10);
    comune = new JLabel("Comune:");
    comuneText = new JTextField(10);
    giorno = new JLabel("Giorno:");
    giornoText = new JTextField(10);
    mese = new JLabel("mese:");
    mesi = new JComboBox(elenco);
    anno = new JLabel("Anno:");
    annoText = new JTextField(10);
    sesso = new JLabel("Sesso:");
    sessoText = new JComboBox(new Object[] {"","M","F"});
    
    
    griglia.add(nome);
    griglia.add(nomeText);
    griglia.add(cognome);
    griglia.add(cognomeText);
    griglia.add(comune);
    griglia.add(comuneText);
    griglia.add(giorno);
    griglia.add(giornoText);
    griglia.add(mese);
    griglia.add(mesi);
    griglia.add(anno);
    griglia.add(annoText);
    griglia.add(sesso);
    griglia.add(sessoText);
    
    griglia1.add(griglia);
    
    
    JPanel fondo1 = new JPanel();
    fondo1.setBackground(Color.WHITE);
    JPanel fondo = new JPanel(new GridLayout(2,2));
    fondo.setBackground(Color.WHITE);
    codiceFiscale = new JLabel("Codice Fiscale:");
    codiceFiscaleText = new JTextField(15);
    generaCodice = new JButton("Genera C.F.");
    
    fondo.add(codiceFiscale);
    fondo.add(codiceFiscaleText);
    fondo.add(generaCodice);
    
    fondo1.add(fondo);
    
    generale.add(pannelloDescrizione,BorderLayout.NORTH);
    generale.add(griglia1,BorderLayout.CENTER);
    generale.add(fondo1, BorderLayout.SOUTH);
    add(generale);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    generaCodice.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        calcolaCodice();
      }
    });
    
  }
  
  // Pressione sul bottone
  // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  private void calcolaCodice() {
    String n = nomeText.getText();
    String c = cognomeText.getText();
    String cc = comuneText.getText();
    String g = giornoText.getText();
    String a = annoText.getText();
    String m1 = (String) mesi.getSelectedItem();
    String s = (String) sessoText.getSelectedItem();
    
    if((n.length() != 0) && (c.length() != 0) && (cc.length() != 0) && (g.length() != 0) &&
       (m1.length() != 0) && (a.length() != 0) && (!m1.equals("")) && (!s.equals(""))) {
       
       try {
         cfg = new CFGenerator(n,c,cc,m1,Integer.parseInt(a),Integer.parseInt(g),s);
       } catch(NumberFormatException e) {
         JOptionPane.showMessageDialog(null,"Anno e Giorno devono essere numerici!","Errore!",JOptionPane.ERROR_MESSAGE);
         return;
       }
       codiceFiscaleText.setText(cfg.getCodiceFiscale());
    }
  }
  // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  
  
  public static void makeGUI() {
    CFGui cf = new CFGui();
    cf.setSize(700,350);
    cf.setResizable(false);
    cf.setVisible(true);
  }
  
  public static void main(String[] args) {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        public void run() {
          makeGUI();
        }
      });
    } catch(Exception e) {}
  }
}