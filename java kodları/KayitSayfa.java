package mustafa.muhammedi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class KayitSayfa extends AppCompatActivity {

    ImageView sifregizlesin2,sifregostersin2;
    EditText kayit_email,kayit_ad,kayit_telfon,kayit_paswword;
    Button btn_kayitet;
    FirebaseAuth nAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kayit_sayfa);
        tanimlama();
        islemler2();


    }
    public void tanimlama(){
        sifregostersin2 = findViewById(R.id.sifregoster2);
        sifregizlesin2 = findViewById(R.id.sifregizle2);
        kayit_email = findViewById(R.id.kayit_email);
        kayit_ad = findViewById(R.id.kayit_ad);
        kayit_telfon = findViewById(R.id.kayit_telefon);
        kayit_paswword = findViewById(R.id.kayit_password);
        btn_kayitet = findViewById(R.id.btn_kayitet);

        nAuth = FirebaseAuth.getInstance();
    }
    public void islemler2(){
        sifregostersin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sifregostersin2.setVisibility(View.INVISIBLE);
                kayit_paswword.setTransformationMethod(null);
                sifregizlesin2.setVisibility(View.VISIBLE);
            }
        });
        sifregizlesin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sifregizlesin2.setVisibility(View.INVISIBLE);
                kayit_paswword.setTransformationMethod(new PasswordTransformationMethod());
                sifregostersin2.setVisibility(View.VISIBLE);
            }
        });
        btn_kayitet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = kayit_email.getText().toString();
                String name = kayit_ad.getText().toString();
                String telefon = kayit_telfon.getText().toString();
                String password = kayit_paswword.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(KayitSayfa.this, "Email Alanı Boş Bırakılamaz...", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(name)) {
                    Toast.makeText(KayitSayfa.this, "Ad Kısmına Boş Bırakılamaz...", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(telefon)) {
                    Toast.makeText(KayitSayfa.this, "Lütfen Telefon Numaranıza Giriniz.. ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(KayitSayfa.this, "Şifre Alanı Boş Bırakılamaz...", Toast.LENGTH_SHORT).show();
                }else {
                    registe();
                }
            }

            private void registe() {
                String email = kayit_email.getText().toString();
                String password = kayit_paswword.getText().toString();

                nAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Intent main = new Intent(KayitSayfa.this,MainActivity.class);
                                    startActivity(main);
                                    finish();
                                }else {
                                    Toast.makeText(KayitSayfa.this, "İşlem Sırasında Bir Hata Oluştu !!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}