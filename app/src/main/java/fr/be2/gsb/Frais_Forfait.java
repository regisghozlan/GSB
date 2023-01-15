package fr.be2.gsb;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;

import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;

import java.util.Calendar;

public class Frais_Forfait extends MainActivity {

    //declaration des variables
    EditText txtQte1;
    LinearLayout layoutverification;
    Spinner listeForfait1;
    String[] Valeurs;
    double montantCalcule;
    TextView mSomme;

    Button btnAjouter1;

    BDDHelper database; //variable qui permet d'acceder à la classe sqlhelperfraisforfait
    // pr po²uvoir acceder a ses methodes
    //declaration d'un tableau avec les montants des frais au forfait
   // String[] Valeurs = getResources().getStringArray(R.array.ValeurForfait)

//    Float[] FraisAuForfait=new Float [] {0.0, 110.0, 0.62, 80.0, 25.0};
    TextView maDate;
    TextView id;
    TextView dateActu;
    DatePickerDialog picker;

    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_forfait);

        txtQte1=findViewById(R.id.txtQuantite);
        listeForfait1=findViewById(R.id.listeForfait);
        mSomme = findViewById(R.id.txtSomme);
        Valeurs = getResources().getStringArray(R.array.MesValeur);

        //on appelle la methode init
        init();

        //on initialise la variable database en instanciant la classe SQLHelper
        database= new BDDHelper(this);
database.open();
           txtQte1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This is where we'll check the user input
                Integer q1 = Integer.parseInt(String.valueOf("0"+txtQte1.getText()));
                //String f1 = listeForfait1.getSelectedItem().toString();
                int posF1 = listeForfait1.getSelectedItemPosition();
                Float s1 = q1 * Float.parseFloat(Valeurs[posF1]);

                mSomme.setText(s1.toString());
            }
        }

        );

    }

    public static Float[] convertStringArraytoFloatArray(String[] sarray) {
        Float[] intarray = null;/*from   w ww . ja va 2 s  .  c  o  m*/

        if (sarray != null) {
            intarray = new Float[sarray.length];

            try {
                for (int i = 0; i < sarray.length; i++) {
                    intarray[i] = Float.parseFloat(sarray[i]);
                }
            } catch (NumberFormatException e) {

            }
        }

        return intarray;
    }
    /**
     * Initialise les variables pr les relier aux objets graphiques correspondants
     **/
    public void init(){
        //txtQte1=findViewById(R.id.txtQte);
        listeForfait1=findViewById(R.id.listeForfait);
        btnAjouter1=findViewById(R.id.btnAjouter);
        maDate=findViewById(R.id.datefrais);
        id = findViewById(R.id.idFrais);

        dateActu=findViewById(R.id.dateActu);

    }

    /**
     * Affiche le calendrier comportant les dates mises à jour
     * @param vv
     */
    public void ShowCal(View vv)
    {
        picker = new DatePickerDialog(Frais_Forfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        maDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //date qu'on recupere une fois qu'on a selectionne
                    }
                },aaaa, mm, jj);//date qui s'affiche sur le calendrier
        picker.show();
    }

    /**
     * Effectue l'action du bouton ajouter, cad vérifie que les champs soient renseiegnés et valides
     * et enregistre le frais dans la bdd en affichant un message de confirmation
     * @param v
     */

    public void MonClick(View v ) {
        switch (v.getId()) {
            if (database.insertData("Nuité hotel", 10,"20230103", 522.12, "Nuité Hotal")) {
                afficherMessage("Succès", "Valeur ajoutée. " + "Montant= " + montantCalcule);
                return;

                case R.id.btnAjouter:
                if (txtQte1.getText().toString().trim().length() == 0 || listeForfait1.getSelectedItem().toString().length() == 0
                        || maDate.getText().toString().trim().length()==0) {
                    //teste si le champ quantite est renseigné ou si le champ type n'est pas vide
                    // et qu'on a selectionne l'une des 4 possibilités et si la date est renseignée
                    afficherMessage("Erreur!", "Champ vide");
                    return;
                } else if (maDate.getText().toString().trim().length()>10 || maDate.getText().toString().trim().length()<8 ) {
                    //test sur la validité du champ date
                    afficherMessage("Erreur!", "Date invalide");
                    return;
                } else if (Integer.parseInt(txtQte1.getText().toString())<1){ //teste si la quantite est au moins 1
                    afficherMessage("Erreur!", "Quantité invalide");
                    return;
                } else {
                    Integer quantite = Integer.parseInt(String.valueOf(txtQte1.getText()));
                    String forfait = listeForfait1.getSelectedItem().toString();
                    int posForfait = listeForfait1.getSelectedItemPosition();
                    montantCalcule = quantite * Float.parseFloat(Valeurs[posForfait]);
                    if (database.insertData(forfait, quantite, null, montantCalcule, forfait)) {
                        afficherMessage("Succès", "Valeur ajoutée. " + "Montant= " + montantCalcule);
                        return;
                    }
                }
                break;
        }

    }

    /**
     * Finit l'action en cours, donc retourne à l'action précédente
     * @param view
     */
    public void clique_retour(View view) {
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}