package mustafa.muhammedi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Unutum extends AppCompatActivity {
    Button btn_sifirla,btn_sayfa_giris;
    EditText Email;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_unutum);
        tamamla();

        btn_sayfa_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Unutum.this,Login.class);
                startActivity(i);
                finish();
            }
        });
        btn_sifirla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress = Email.getText().toString();

                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Unutum.this, "Doğrulama Email Hesabınıze Gönderildi", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Unutum.this, "Bir Hata Oluştu", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });

    }
    public void tamamla(){
        btn_sifirla = findViewById(R.id.btn_unutum_giris);
        Email = findViewById(R.id.kayit_email);
        mAuth = FirebaseAuth.getInstance();
        btn_sayfa_giris = findViewById(R.id.btn_sayfa_giris);

    }

}