package mustafa.muhammedi.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    ImageButton btn_bilgi_sistemi, btn_kayuzem,btn_iletisim,image_rezer,imageduturu,btn_yemekliste,btn_tum_duyuru,btn_hesaplama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tamamla();
        btn_tamamla();
        menukallanma();


    }

    //Tanımlamalar
    public void tamamla() {
        drawerLayout = findViewById(R.id.draweLayout);
        materialToolbar = findViewById(R.id.materialToolbar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);
        btn_bilgi_sistemi = findViewById(R.id.btn_bilgi_sistemi);
        btn_kayuzem = findViewById(R.id.btn_alms);
        btn_iletisim = findViewById(R.id.btn_iletisim1);
        image_rezer= findViewById(R.id.image_staj);
        imageduturu = findViewById(R.id.image_btn_duyuru);
        btn_yemekliste =findViewById(R.id.image_btn_yemekliste);
        btn_tum_duyuru = findViewById(R.id.image_btn_tum_duyuru);
        btn_hesaplama = findViewById(R.id.image_btn_hesaplama);
    }

    // ImageView buttonları kullanımı
    public void btn_tamamla(){
        btn_bilgi_sistemi.setOnClickListener(v -> {
            Uri adres =Uri.parse("https://sis.kayseri.edu.tr");

            Intent iIntent = new Intent(Intent.ACTION_VIEW, adres);
            startActivity(iIntent);
        });
        btn_kayuzem.setOnClickListener(v -> {
            Uri adres =Uri.parse("https://kayseriedu.almscloud.com/Account/LoginBefore");

            Intent iIntent = new Intent(Intent.ACTION_VIEW, adres);
            startActivity(iIntent);
        });
        btn_tum_duyuru.setOnClickListener(v -> {
            Uri adres =Uri.parse("https://www.kayseri.edu.tr/tr/d/duyuru/tum-duyurular");

            Intent iIntent = new Intent(Intent.ACTION_VIEW, adres);
            startActivity(iIntent);
        });
        btn_iletisim.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this,Iletisim.class);
            startActivity(i);
        });
        image_rezer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, staj.class);
                startActivity(i);
            }
        });
        imageduturu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Duyurular.class);
                startActivity(i);
            }
        });
        btn_yemekliste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,YemekListe.class);
                startActivity(i);
                finish();
            }
        });
        btn_hesaplama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hhh = new Intent(MainActivity.this,Hesapla.class);
                startActivity(hhh);
            }
        });
    }

    // Menu kullanımı ve yönlendırmeleri
    public void menukallanma(){
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    MainActivity.this, drawerLayout, materialToolbar, R.string.drawer_close, R.string.drawer_open);
            drawerLayout.addDrawerListener(toggle);

            materialToolbar.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.Home) {
                    Toast.makeText(MainActivity.this, "Anasayfa", Toast.LENGTH_SHORT).show();
                    recreate();
                }
                return false;
            });
            navigationView.setNavigationItemSelectedListener(menuItem -> {
                if (menuItem.getItemId() == R.id.home) {
                    Toast.makeText(MainActivity.this, "ANASAYFA", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.obs) {

                    Uri adres =Uri.parse("https://sis.kayseri.edu.tr");

                    Intent iIntent = new Intent(Intent.ACTION_VIEW, adres);
                    startActivity(iIntent);

                    Toast.makeText(MainActivity.this, "OBS", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.menu_food) {
                    Toast.makeText(MainActivity.this, "YEMEK LİSTESİ", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(MainActivity.this,YemekListe.class);
                    startActivity(i);
                    finish();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.duyuru) {
                    Toast.makeText(MainActivity.this, "DUYURULAR", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(MainActivity.this,Duyurular.class);
                    startActivity(i);

                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.menu_iletisim) {

                    Intent iletisim = new Intent(MainActivity.this,Iletisim.class);
                    startActivity(iletisim);

                    Toast.makeText(MainActivity.this, "İLETİŞİM", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);

                } else if (menuItem.getItemId() == R.id.menu_tarih) {

                    Toast.makeText(MainActivity.this, "TARİHÇE", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);

                    Intent t = new Intent(MainActivity.this,Tarihce.class);
                    startActivity(t);
                    finish();

                } else if (menuItem.getItemId() == R.id.menu_uygulama) {
                    Toast.makeText(MainActivity.this, "UYGULAMA HAKKINDA", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);

                    Intent i = new Intent(MainActivity.this,UygulamaAbout.class);
                    startActivity(i);
                    finish();
                }
                else if (menuItem.getItemId() == R.id.menu_cikis) {
                    Toast.makeText(MainActivity.this, "ÇIKIŞ", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);

                    AlertDialog.Builder uyareipenceresi = getBuilder();
                    uyareipenceresi.show();
                }
                return false;
            });


        }

        @NonNull
        private AlertDialog.Builder getBuilder() {
            AlertDialog.Builder uyareipenceresi = new AlertDialog.Builder(MainActivity.this);

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