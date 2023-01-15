package fr.be2.gsb;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Class mc;
    }



    /**
     * Execute l'action des différents boutons de la page d'accueil
     * @param view
     */

    public void onClick (View view) {
        Class mc = null;
        if (view.getId() == R.id.btnfraisforfait){
            //Intent intent = new Intent(getApplicationContext(), Frais_Forfait.class); //intent est une variable
            // de type intent(classe) pr faire passer d'1 classe à l'autre
            mc= Frais_Forfait.class;
           // startActivity(intent);
        }

        if (view.getId() == R.id.btnfraishorsforfait){
            mc= Frais_Hors_Forfait.class;

            //Intent intent = new Intent(getApplicationContext(), Frais_Hors_Forfait.class);
            //startActivity(intent);
        }


        if (view.getId() == R.id.btnsynthese){
            //Intent intent = new Intent(getApplicationContext(), Synthese_Mois.class);
            //startActivity(intent);
        }
        if (view.getId() == R.id.btnparamtres){
            //Intent intent = new Intent(getApplicationContext(), parametres.class);

        }

        Intent intent = new Intent(MainActivity.this,mc); //intent est une variable
        startActivity(intent);
    }

    void secure(){
         String cvisiteur getSharedPreferences("monfichierdeparamatre", MODE_PRIVATE).getString("NOM");
        Toast.makeText(this,"cvisiteur="+cvisiteur,Toast.LENGTH_LONG).show();

        if (cvisiteur.equals("zero")) {
            Intent intent = new Intent(MainActivity.this,login.class);
        }
    }

    /**
     * Affiche un message comportant un titre et un contenu,  via une boite de dialogue
     * @param titre
     * @param message
     */
    public void afficherMessage(String titre, String message){

        Toast.makeText(this,"code="+a.toString(),Toast.LENGTH_SHORT);

        AlertDialog.Builder builder=new AlertDialog.Builder(this); //classe qui constuit une boite de dialogue
        builder.setCancelable(true); //pr que la boite de dialogue soit refermable
        builder.setTitle(titre);
        builder.setMessage(message);
        builder.show();

        Toast.makeText(this,"",Toast.LENGTH_LONG);
    }

}
