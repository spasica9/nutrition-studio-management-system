/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;


import domen.Dan;
import domen.Nutricionista;
import domen.Pacijent;
import domen.PlanIshrane;
import domen.VremeObroka;
import forme.modeli.ModelTabeleStavkaPlanaIshrane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author anas
 */
public class KreirajPlanIshraneForma extends javax.swing.JFrame {

    /**
     * Creates new form DodajPlanIshraneForma
     */
    
    PlanIshrane planIshrane;
    
    public KreirajPlanIshraneForma() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Plan ishrane");
        tblStavke.setModel(new ModelTabeleStavkaPlanaIshrane(new ArrayList<>()));
        planIshrane = new PlanIshrane();
    }

    public PlanIshrane getPlanIshrane() {
        return planIshrane;
    }

    public void setPlanIshrane(PlanIshrane planIshrane) {
        this.planIshrane = planIshrane;
    }

    
    
    
    public JButton getBtnObrisi() {
        return btnObrisi;
    }

    public void setBtnObrisi(JButton btnObrisi) {
        this.btnObrisi = btnObrisi;
    }

    public JButton getBtnSacuvaj() {
        return btnSacuvaj;
    }

    public void setBtnSacuvaj(JButton btnSacuvaj) {
        this.btnSacuvaj = btnSacuvaj;
    }

    public JComboBox<Nutricionista> getCmbNutricionista() {
        return cmbNutricionista;
    }

    public void setCmbNutricionista(JComboBox<Nutricionista> cmbNutricionista) {
        this.cmbNutricionista = cmbNutricionista;
    }

    public JComboBox<Pacijent> getCmbPacijent() {
        return cmbPacijent;
    }

    public void setCmbPacijent(JComboBox<Pacijent> cmbPacijent) {
        this.cmbPacijent = cmbPacijent;
    }

    public JLabel getLblIznos() {
        return lblIznos;
    }

    public void setLblIznos(JLabel lblIznos) {
        this.lblIznos = lblIznos;
    }

    public JTable getTblStavke() {
        return tblStavke;
    }

    public void setTblStavke(JTable tblStavke) {
        this.tblStavke = tblStavke;
    }

    public JTextField getTfCenaPlana() {
        return tfCenaPlana;
    }

    public void setTfCenaPlana(JTextField tfCenaPlana) {
        this.tfCenaPlana = tfCenaPlana;
    }

    public JTextField getTfNazivPlana() {
        return tfNazivPlana;
    }

    public void setTfNazivPlana(JTextField tfNazivPlana) {
        this.tfNazivPlana = tfNazivPlana;
    }

    public JButton getBtnIzmeni() {
        return btnIzmeni;
    }
    
      public JComboBox<Dan> getCmbDan() {
        return cmbDan;
    }

    public JComboBox<VremeObroka> getCmbVreme() {
        return cmbVreme;
    }
    

    public JButton getBtnDodaj1() {
        return btnDodaj1;
    }

    public void setBtnDodaj1(JButton btnDodaj1) {
        this.btnDodaj1 = btnDodaj1;
    }

    public JButton getBtnIzaberi() {
        return btnIzaberi;
    }

    public void setBtnIzaberi(JButton btnIzaberi) {
        this.btnIzaberi = btnIzaberi;
    }

    public JButton getBtnPretrazi() {
        return btnPretrazi;
    }

    public void setBtnPretrazi(JButton btnPretrazi) {
        this.btnPretrazi = btnPretrazi;
    }

    public JButton getBtnResetuj() {
        return btnResetuj;
    }

    public void setBtnResetuj(JButton btnResetuj) {
        this.btnResetuj = btnResetuj;
    }

    public JLabel getLblIznos1() {
        return lblIznos1;
    }

    public void setLblIznos1(JLabel lblCena) {
        this.lblIznos1 = lblCena;
    }

    public JLabel getLblKcal() {
        return lblKcal;
    }

    public void setLblKcal(JLabel lblKcal) {
        this.lblKcal = lblKcal;
    }

    public JTable getTblJela() {
        return tblJela;
    }

    public void setTblJela(JTable tblJela) {
        this.tblJela = tblJela;
    }



    public JTextField getTfJelo() {
        return tfJelo;
    }

    public void setTfJelo(JTextField tfJelo) {
        this.tfJelo = tfJelo;
    }

    public JTextField getTfKol() {
        return tfKol;
    }

    public void setTfKol(JTextField tfKol) {
        this.tfKol = tfKol;
    }


    public JTextField getTfjedCena() {
        return tfjedCena;
    }

    public void setTfjedCena(JTextField tfjedCena) {
        this.tfjedCena = tfjedCena;
    }

    public JTextField getTfjedKcal() {
        return tfjedKcal;
    }

    public void setTfjedKcal(JTextField tfjedKcal) {
        this.tfjedKcal = tfjedKcal;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSacuvaj = new javax.swing.JButton();
        tfNazivPlana = new javax.swing.JTextField();
        cmbNutricionista = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cmbPacijent = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStavke = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnObrisi = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tfCenaPlana = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblIznos = new javax.swing.JLabel();
        btnIzmeni = new javax.swing.JButton();
        lblIznos1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnDodaj1 = new javax.swing.JButton();
        cmbVreme = new javax.swing.JComboBox<>();
        tfKol = new javax.swing.JTextField();
        btnIzaberi = new javax.swing.JButton();
        tfJelo = new javax.swing.JTextField();
        lblKcal = new javax.swing.JLabel();
        tfjedKcal = new javax.swing.JTextField();
        cmbDan = new javax.swing.JComboBox<>();
        btnPretrazi = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        tfjedCena = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblJela = new javax.swing.JTable();
        btnResetuj = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSacuvaj.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSacuvaj.setText("Sačuvaj plan ishrane");

        tfNazivPlana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNazivPlanaActionPerformed(evt);
            }
        });

        cmbNutricionista.setEnabled(false);

        jLabel7.setText("Naziv plana:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Nutricionista:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Pacijent:");

        tblStavke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblStavke.setRowHeight(22);
        jScrollPane1.setViewportView(tblStavke);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel1.setText("Dodate stavke plana");

        btnObrisi.setText("Obrisi stavku");

        jLabel4.setText("Cena plana:");

        jLabel6.setText("Iznos stavki:");

        lblIznos.setText("0");

        btnIzmeni.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIzmeni.setText("Izmeni plan ishrane");

        lblIznos1.setText("0");

        jLabel10.setText("Ukupno kcal:");

        jLabel8.setText("Kcal/100g:");

        jLabel5.setText("Vreme obroka:");

        jLabel9.setText("Cena/100g: ");

        jLabel11.setText("Dan:");

        jLabel12.setText("Pretraga jela po nazivu:");

        jLabel13.setText("Iznos:");

        btnDodaj1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDodaj1.setText("Dodaj stavku");

        tfKol.setText("0");
        tfKol.setPreferredSize(new java.awt.Dimension(100, 22));
        tfKol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKolActionPerformed(evt);
            }
        });

        btnIzaberi.setText("Izaberi jelo");

        lblKcal.setText("0");

        tfjedKcal.setEnabled(false);

        btnPretrazi.setText("Pretraži");
        btnPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziActionPerformed(evt);
            }
        });

        jLabel14.setText("Količina (x*100g):");

        tfjedCena.setEnabled(false);

        tblJela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblJela);

        btnResetuj.setText("Resetuj pretragu");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel15.setText("STAVKE PLANA ISHRANE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfNazivPlana, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPacijent, javax.swing.GroupLayout.Alignment.LEADING, 0, 205, Short.MAX_VALUE)
                    .addComponent(cmbNutricionista, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfCenaPlana))
                .addGap(300, 300, 300)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfjedKcal, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfjedCena, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(tfKol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(119, 119, 119)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(cmbVreme, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmbDan, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(253, 253, 253))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addGap(462, 462, 462)))
                                    .addComponent(btnIzaberi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblKcal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblIznos1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(74, 74, 74)
                                    .addComponent(btnDodaj1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfJelo, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(btnPretrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(btnResetuj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSacuvaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnIzmeni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(169, 169, 169))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnObrisi))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblIznos, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(119, 119, 119))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbNutricionista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPacijent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNazivPlana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tfCenaPlana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel15))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblIznos))))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(tfJelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnPretrazi)
                            .addComponent(btnResetuj))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIzaberi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(cmbDan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbVreme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(tfKol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDodaj1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblKcal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lblIznos1))))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(6, 6, 6))
                    .addComponent(tfjedKcal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfjedCena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNazivPlanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNazivPlanaActionPerformed

    }//GEN-LAST:event_tfNazivPlanaActionPerformed

    private void tfKolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKolActionPerformed

    private void btnPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPretraziActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj1;
    private javax.swing.JButton btnIzaberi;
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnPretrazi;
    private javax.swing.JButton btnResetuj;
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JComboBox<Dan> cmbDan;
    private javax.swing.JComboBox<Nutricionista> cmbNutricionista;
    private javax.swing.JComboBox<Pacijent> cmbPacijent;
    private javax.swing.JComboBox<VremeObroka> cmbVreme;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIznos;
    private javax.swing.JLabel lblIznos1;
    private javax.swing.JLabel lblKcal;
    private javax.swing.JTable tblJela;
    private javax.swing.JTable tblStavke;
    private javax.swing.JTextField tfCenaPlana;
    private javax.swing.JTextField tfJelo;
    private javax.swing.JTextField tfKol;
    private javax.swing.JTextField tfNazivPlana;
    private javax.swing.JTextField tfjedCena;
    private javax.swing.JTextField tfjedKcal;
    // End of variables declaration//GEN-END:variables
    
    
     public void obrisiAddActionListener(ActionListener actionListener) {
        btnObrisi.addActionListener(actionListener);
    }
      public void sacuvajAddActionListener(ActionListener actionListener) {
        btnSacuvaj.addActionListener(actionListener);
    }

    public void izmeniAddActionListener(ActionListener actionListener) {
        btnIzmeni.addActionListener(actionListener);
    }
    
    public void dodaj1AddActionListener(ActionListener actionListener) {
        btnDodaj1.addActionListener(actionListener);
    }
    
    public void pretraziAddActionListener(ActionListener actionListener) {
        btnPretrazi.addActionListener(actionListener);
    }

     public void resetujAddActionListener(ActionListener actionListener) {
        btnResetuj.addActionListener(actionListener);
    }

     public void izaberiAddActionListener(ActionListener actionListener) {
        btnIzaberi.addActionListener(actionListener);
    }
    
}
