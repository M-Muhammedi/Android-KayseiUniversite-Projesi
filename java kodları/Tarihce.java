package mustafa.muhammedi.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
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

public class Tarihce extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tarihce);
        tamamla();


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                Tarihce.this, drawerLayout, materialToolbar, R.string.drawer_close, R.string.drawer_open);
        drawerLayout.addDrawerListener(toggle);

        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.Home) {
                    Intent anas = new Intent(Tarihce.this,MainActivity.class);
                    startActivity(anas);
                    Toast.makeText(Tarihce.this, "Anasayfa", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.home) {
                    Toast.makeText(Tarihce.this, "ANASAYFA", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent i = new Intent(Tarihce.this,MainActivity.class);
                    startActivity(i);
                    finish();
                } else if (menuItem.getItemId() == R.id.obs) {

                    Uri adres =Uri.parse("https://sis.kayseri.edu.tr");

                    Intent iIntent = new Intent(Intent.ACTION_VIEW, adres);
                    startActivity(iIntent);

                    Toast.makeText(Tarihce.this, "OBS", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);

                } else if (menuItem.getItemId() == R.id.menu_food) {
                    Toast.makeText(Tarihce.this, "YEMEK LİSTESİ", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.duyuru) {
                    Toast.makeText(Tarihce.this, "DUYURULAR", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.menu_iletisim) {
                    Toast.makeText(Tarihce.this, "İLETİŞİM", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.menu_tarih) {
                    recreate();
                    Toast.makeText(Tarihce.this, "TARİHÇE", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.menu_uygulama) {

                    Intent i = new Intent(Tarihce.this,UygulamaAbout.class);
                    startActivity(i);
                    finish();

                    Toast.makeText(Tarihce.this, "UYGULAMA HAKKINDA", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (menuItem.getItemId() == R.id.menu_cikis) {
                    Toast.makeText(Tarihce.this, "ÇIKIŞ", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);

                    AlertDialog.Builder uyareipenceresi = new AlertDialog.Builder(Tarihce.this);

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

    public void tamamla() {
        drawerLayout = findViewById(R.id.draweLayout);
        materialToolbar = findViewById(R.id.materialToolbar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);
    }
}