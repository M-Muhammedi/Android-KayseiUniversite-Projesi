package mustafa.muhammedi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    TextView giris_unutum,kayitol_text;
    Button btn_giris;
    EditText giris_email,giris_password;
    ImageView sifregoster,sifregizle;
    FirebaseAuth nAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        tamamla();
        islemler();



    }
    public void tamamla(){
        giris_unutum = findViewById(R.id.giris_unuttun);
        kayitol_text = findViewById(R.id.giris_kayit);
        giris_email = findViewById(R.id.giris_email);
        giris_password = findViewById(R.id.giris_password);
        sifregoster = findViewById(R.id.sifregoster);
        sifregizle = findViewById(R.id.sifregizle);
        btn_giris = findViewById(R.id.btn_giris);

        nAuth = FirebaseAuth.getInstance();




    }
    public void islemler(){

        sifregoster.setOnClickListener(v -> {
            sifregoster.setVisibility(View.INVISIBLE);
            giris_password.setTransformationMethod(null);
            sifregizle.setVisibility(View.VISIBLE);
        });

        sifregizle.setOnClickListener(v -> {
            sifregizle.setVisibility(View.INVISIBLE);
            giris_password.setTransformationMethod(new PasswordTransformationMethod());
            sifregoster.setVisibility(View.VISIBLE);
        });
        kayitol_text.setOnClickListener(v -> {
            Intent i = new Intent(Login.this, KayitSayfa.class);
            startActivity(i);
        });

        giris_unutum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Unutum.class);
                startActivity(i);

            }
        });

        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = giris_email.getText().toString();
                String password = giris_password.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Lütfen Bilgilerinize Kontrol Edin ....", Toast.LENGTH_SHORT).show();
                }else {
                    login();
                }
            }

            private void login() {
                String email = giris_email.getText().toString();
                String password = giris_password.getText().toString();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Giriş başarılı
                                    Toast.makeText(Login.this, "Giriş Başarlı..", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                    finish();
                                } else {
                                    // Giriş başarısız
                                    Toast.makeText(Login.this, "Giriş Başarsiz..", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });



    }
}