package mustafa.muhammedi.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class Iletisim extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    TextView tel_ara,tel_ara2,email_gonder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_iletisim);
        tamamla();
        menukullanma();
        sayfayapisi();



    }

    public void tamamla() {
        drawerLayout = findViewById(R.id.draweLayout);
        materialToolbar = findViewById(R.id.materialToolbar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);
        tel_ara = findViewById(R.id.tel_ara);
        tel_ara2 = findViewById(R.id.tel_ara2);
        email_gonder = findViewById(R.id.eposta_gonder);
    }

    // Sayfa da ki işlemler

    public void sayfayapisi(){

        tel_ara.setOnClickListener(v -> {
            Uri telno = Uri.parse("tel:"+tel_ara.getText().toString());
            Intent iIntent = new Intent(Intent.ACTION_DIAL,telno);
            startActivity(iIntent);
        });

        tel_ara2.setOnClickListener(v -> {
            Uri telno = Uri.parse("tel:"+tel_ara2.getText().toString());
            Intent iIntent = new Intent(Intent.ACTION_DIAL,telno);
            startActivity(iIntent);
        });
        email_gonder.setOnClickListener(v -> {
            String emailAdresi = email_gonder.getText().toString();
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + emailAdresi));
            startActivity(Intent.createChooser(emailIntent, "E-posta gönder..."));
        });


    }
                                // menu işlemleri
    public void menukullanma(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                Iletisim.this, drawerLayout, materialToolbar, R.string.drawer_close, R.string.drawer_open);
        drawerLayout.addDrawerListener(toggle);

        materialToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.Home) {
                Intent anas = new Intent(Iletisim.this,MainActivity.class);
                startActivity(anas);
                Toast.makeText(Iletisim.this, "Anasayfa", Toast.LENGTH_SHORT).show();
            }
            return false;
        });
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.home) {
                Toast.makeText(Iletisim.this, "ANASAYFA", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent i = new Intent(Iletisim.this,MainActivity.class);
                startActivity(i);
                finish();
            } else if (menuItem.getItemId() == R.id.obs) {

                Uri adres =Uri.parse("https://sis.kayseri.edu.tr");

                Intent iIntent = new Intent(Intent.ACTION_VIEW, adres);
                startActivity(iIntent);

                Toast.makeText(Iletisim.this, "OBS", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);

            } else if (menuItem.getItemId() == R.id.menu_food) {
                Toast.makeText(Iletisim.this, "YEMEK LİSTESİ", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (menuItem.getItemId() == R.id.duyuru) {
                Toast.makeText(Iletisim.this, "DUYURULAR", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (menuItem.getItemId() == R.id.menu_iletisim) {
                recreate();
                Toast.makeText(Iletisim.this, "İLETİŞİM", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (menuItem.getItemId() == R.id.menu_tarih) {
                Intent tarih = new Intent(Iletisim.this,Tarihce.class);
                startActivity(tarih);
                Toast.makeText(Iletisim.this, "TARİHÇE", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (menuItem.getItemId() == R.id.menu_uygulama) {

                Intent i = new Intent(Iletisim.this,UygulamaAbout.class);
                startActivity(i);
                finish();

                Toast.makeText(Iletisim.this, "UYGULAMA HAKKINDA", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
            else if (menuItem.getItemId() == R.id.menu_cikis) {
                Toast.makeText(Iletisim.this, "ÇIKIŞ", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);

                AlertDialog.Builder uyareipenceresi = getBuilder();
                uyareipenceresi.show();
            }
            return false;
        });

    }

    @NonNull
    private AlertDialog.Builder getBuilder() {
        AlertDialog.Builder uyareipenceresi = new AlertDialog.Builder(Iletisim.this);

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