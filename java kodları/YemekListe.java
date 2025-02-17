package mustafa.muhammedi.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class YemekListe extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dateList;

    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_yemekliste);
        tanimlama();
        islemler();

        dateList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,R.layout.listview_kullan1, dateList);
        listView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        db.collection("YemekListe")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String duyur = documentSnapshot.getString("yemekliste1"); // Tarih alanının adını buraya yazın
                            dateList.add(duyur);

                            String duyura = documentSnapshot.getString("yemekliste2"); // Tarih alanının adını buraya yazın
                            dateList.add(duyura);

                            String duyurb = documentSnapshot.getString("yemekliste3"); // Tarih alanının adını buraya yazın
                            dateList.add(duyurb);

                            String duyurc = documentSnapshot.getString("yemekliste4"); // Tarih alanının adını buraya yazın
                            dateList.add(duyurc);

                            String duyurd = documentSnapshot.getString("yemekliste5"); // Tarih alanının adını buraya yazın
                            dateList.add(duyurd);
                        }
                        adapter.notifyDataSetChanged();
                    }
                })


                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(YemekListe.this, "Veri okuma hatası: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });
    }



    public void tanimlama(){
        drawerLayout = findViewById(R.id.draweLayout);
        materialToolbar = findViewById(R.id.materialToolbar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);
        listView = findViewById(R.id.listView_yemekliste);
    }

    // menu işlemleri
    public void islemler(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                YemekListe.this, drawerLayout, materialToolbar, R.string.drawer_close, R.string.drawer_open);
        drawerLayout.addDrawerListener(toggle);

        materialToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.Home) {
                Intent anas = new Intent(YemekListe.this,MainActivity.class);
                startActivity(anas);
                Toast.makeText(YemekListe.this, "Anasayfa", Toast.LENGTH_SHORT).show();
            }
            return false;
        });
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.home) {
                Toast.makeText(YemekListe.this, "ANASAYFA", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent i = new Intent(YemekListe.this,MainActivity.class);
                startActivity(i);
                finish();
            } else if (menuItem.getItemId() == R.id.obs) {

                Uri adres =Uri.parse("https://sis.kayseri.edu.tr");

                Intent iIntent = new Intent(Intent.ACTION_VIEW, adres);
                startActivity(iIntent);

                Toast.makeText(YemekListe.this, "OBS", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);

            } else if (menuItem.getItemId() == R.id.menu_food) {
                Toast.makeText(YemekListe.this, "YEMEK LİSTESİ", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                recreate();
            } else if (menuItem.getItemId() == R.id.duyuru) {
                Toast.makeText(YemekListe.this, "DUYURULAR", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(YemekListe.this,Duyurular.class);
                startActivity(i);
                finish();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (menuItem.getItemId() == R.id.menu_iletisim) {
                Intent ilet = new Intent(YemekListe.this,Iletisim.class);
                startActivity(ilet);
                Toast.makeText(YemekListe.this, "İLETİŞİM", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (menuItem.getItemId() == R.id.menu_tarih) {
                Intent tarih = new Intent(YemekListe.this,Tarihce.class);
                startActivity(tarih);
                Toast.makeText(YemekListe.this, "TARİHÇE", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (menuItem.getItemId() == R.id.menu_uygulama) {

                Intent i = new Intent(YemekListe.this,UygulamaAbout.class);
                startActivity(i);
                finish();

                Toast.makeText(YemekListe.this, "UYGULAMA HAKKINDA", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if (menuItem.getItemId() == R.id.menu_cikis) {
                Toast.makeText(YemekListe.this, "ÇIKIŞ", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);

                AlertDialog.Builder uyareipenceresi = getBuilder();
                uyareipenceresi.show();
            }
            return false;
        });

    }

    @NonNull
    private AlertDialog.Builder getBuilder() {
        AlertDialog.Builder uyareipenceresi = new AlertDialog.Builder(YemekListe.this);

        uyareipenceresi.setTitle("Çıkış");
        uyareipenceresi.setMessage("Çıkış yapılsın mı ? ");

        uyareipenceresi.setPositiveButton("EVET", (dialog, which) -> {
            finishAffinity();
            System.exit(0);
        });
        uyareipenceresi.setNegativeButton("HAYIR", (dialog, which) -> dialog.dismiss());
        return uyareipenceresi;
    }

}