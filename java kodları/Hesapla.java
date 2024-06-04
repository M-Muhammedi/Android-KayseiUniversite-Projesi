package mustafa.muhammedi.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class Hesapla extends AppCompatActivity {

    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    EditText edt_vize,edt_fina;
    TextView text_sonuc,text_harfnotu;
    Button btn_hesapla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hesapla);
        tanimla();
        islemlere();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                Hesapla.this, drawerLayout, materialToolbar, R.string.drawer_close, R.string.drawer_open);
        drawerLayout.addDrawerListener(toggle);

        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.Home) {
                    Intent anas = new Intent(Hesapla.this,MainActivity.class);
                    startActivity(anas);
                    Toast.makeText(Hesapla.this, "Anasayfa", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.home) {
                    Toast.makeText(Hesapla.this, "ANASAYFA", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent i = new Intent(Hesapla.this,MainActivity.class);
                    startActivity(i);
                    finish();
                } else if (menuItem.getItemId() == R.id.obs) {

                    Uri adres =Uri.parse("https://sis.kayseri.edu.tr");

                    Intent iIntent = new Intent(Intent.ACTION_VIEW, adres);
                    startActivity(iIntent);

                    Toast.makeText(Hesapla.this, "OBS", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.menu_food) {
                    Toast.makeText(Hesapla.this, "YEMEK LİSTESİ", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.duyuru) {
                    Toast.makeText(Hesapla.this, "DUYURULAR", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.menu_iletisim) {
                    Toast.makeText(Hesapla.this, "İLETİŞİM", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.menu_tarih) {

                    Intent i = new Intent(Hesapla.this,Tarihce.class);
                    startActivity(i);

                    Toast.makeText(Hesapla.this, "TARİHÇE", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.menu_uygulama) {
                    Intent i = new Intent(Hesapla.this,UygulamaAbout.class);
                    startActivity(i);
                    Toast.makeText(Hesapla.this, "UYGULAMA HAKKINDA", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (menuItem.getItemId() == R.id.menu_cikis) {
                    Toast.makeText(Hesapla.this, "ÇIKIŞ", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);

                    AlertDialog.Builder uyareipenceresi = new AlertDialog.Builder(Hesapla.this);

                    uyareipenceresi.setTitle("Çıkış");
                    uyareipenceresi.setMessage("Çıkış yapılsın mı ? ");

                    uyareipenceresi.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                            System.exit(0);
                        }
                    });
                    uyareipenceresi.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    uyareipenceresi.show();
                }
                return false;
            }
        });

    }
    void tanimla(){
        drawerLayout = findViewById(R.id.draweLayout);
        materialToolbar = findViewById(R.id.materialToolbar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);
        edt_vize = findViewById(R.id.edt_vize);
        edt_fina = findViewById(R.id.edt_final);
        text_sonuc = findViewById(R.id.text_sonuc);
        text_harfnotu = findViewById(R.id.text_harfnotu);
        btn_hesapla = findViewById(R.id.btn_hesapla);

    }
    void islemlere(){

        btn_hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edt_vize1 = edt_vize.getText().toString();
                String edt_final1 = edt_fina.getText().toString();

                if (TextUtils.isEmpty(edt_vize1)){
                    Toast.makeText(Hesapla.this, "Email Alanı Boş Bırakılamaz...", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edt_final1)) {
                    Toast.makeText(Hesapla.this, "Ad Kısmına Boş Bırakılamaz...", Toast.LENGTH_SHORT).show();
                }
                // EditText içeriğini al ve double'a dönüştür
                double vizeNotu = Double.parseDouble(edt_vize1);
                double finalNotu = Double.parseDouble(edt_final1);

                // Ortalama hesapla
                double vizeYuzdesi = 0.4;
                double finalYuzdesi = 0.6;
                double ortalama = (vizeNotu * vizeYuzdesi) + (finalNotu * finalYuzdesi);


                // Harf notunu ve katsayısını belirlemek için değişkenler oluşturuyoruz.
               // double katsayi;


                // Ortalama notuna göre harf notunu ve katsayısını belirliyoruz.
                if (ortalama >= 90 && ortalama <= 100) {
                    String Ortalama = String.valueOf(ortalama);
                    text_sonuc.setText(Ortalama);
                    String harfnot = "AA";
                    text_harfnotu.setText(harfnot);

                } else if (ortalama >= 85 && ortalama <= 89) {
                    String Ortalama = String.valueOf(ortalama);
                    text_sonuc.setText(Ortalama);
                    String harfnot = "BA";
                    text_harfnotu.setText(harfnot);

                } else if (ortalama >= 80 && ortalama <= 84) {
                    String Ortalama = String.valueOf(ortalama);
                    text_sonuc.setText(Ortalama);
                    String harfnot = "BB";
                    text_harfnotu.setText(harfnot);

                } else if (ortalama >= 75 && ortalama <= 79) {
                    String Ortalama = String.valueOf(ortalama);
                    text_sonuc.setText(Ortalama);
                    String harfnot = "CB";
                    text_harfnotu.setText(harfnot);

                } else if (ortalama >= 70 && ortalama <= 74) {
                    String Ortalama = String.valueOf(ortalama);
                    text_sonuc.setText(Ortalama);
                    String harfnot = "CC";
                    text_harfnotu.setText(harfnot);

                } else if (ortalama >= 65 && ortalama <= 69) {
                    String Ortalama = String.valueOf(ortalama);
                    text_sonuc.setText(Ortalama);
                    String harfnot = "DC";
                    text_harfnotu.setText(harfnot);

                } else if (ortalama >= 60 && ortalama <= 64) {
                    String Ortalama = String.valueOf(ortalama);
                    text_sonuc.setText(Ortalama);
                    String harfnot = "DD";
                    text_harfnotu.setText(harfnot);

                } else if (ortalama >= 50 && ortalama <= 59) {
                    String Ortalama = String.valueOf(ortalama);
                    text_sonuc.setText(Ortalama);
                    String harfnot = "FD";
                    text_harfnotu.setText(harfnot);

                } else {
                    String Ortalama = String.valueOf(ortalama);
                    text_sonuc.setText(Ortalama);
                    String harfnot = "FF";
                    text_harfnotu.setText(harfnot);
                }

            }
        });




    }
}