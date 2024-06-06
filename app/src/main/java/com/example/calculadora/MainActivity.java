package com.example.calculadora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = findViewById(R.id.entrar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, tela3.class);
                startActivity(intent);
                    }

            public void Tela3(View view) {
                Intent in = new Intent(MainActivity.this, Tela3().class);
                startActivity(in);
            }

        });
            }
        }
public class MainActivity extends AppCompatActivity {

    Usuario usuario; // Objeto Usuario que armazena as informações do usuário
    FirebaseAuth autenticacao; // Objeto FirebaseAuth para autenticação com Firebase
    EditText campoNome, campoEmail, camposSnha; // Campos de entrada para nome, email e senha
    Button botaoCadastrar; // Botão para acionar o cadastro

    public void Recuperar(View view){
        Intent in = new Intent(MainActivity.this, tela2.class);
        startActivity(in);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Compiler EdgeToEdge = null;
        EdgeToEdge.enable(); // Habilita layout de borda a borda
        setContentView(R.layout.activity_main); // Define o layout da atividade
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Ajusta o padding para suportar áreas de recorte da tela
            inicializar(); // Inicializa os elementos da interface

            return insets;
        });
    }

    // Inicializa os elementos da interface com seus respectivos IDs
    @SuppressLint("WrongViewCast")
    public void inicializar() {
        View campotelefone = findViewById(R.id.email);
        campoEmail = findViewById(R.id.TextLogin);
        camposSnha = findViewById(R.id.textSenha);
        botaoCadastrar = findViewById(R.id.buttonCadastrar);
    }

    // Valida os campos de entrada e, se válidos, chama o método para cadastrar o usuário
    public void validarCampos(View view) {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String pass = camposSnha.getText().toString();

        if (!nome.isEmpty()) { // Verifica se o campo nome não está vazio
            if (!email.isEmpty()) { // Verifica se o campo email não está vazio
                if (!pass.isEmpty()) { // Verifica se o campo senha não está vazio

                    usuario = new Usuario(); // Cria um novo objeto Usuario
                    usuario.setNome(nome); // Define o nome do usuário
                    usuario.setEmail(email); // Define o email do usuário
                    usuario.setPass(pass); // Define a senha do usuário

                    // Chama o método para cadastrar o usuário
                    cadastrarUsuario();

                } else {
                    Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show(); 
                }
            } else {
                Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show(); 
            }
        } else {
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show(); 
        }
    }

    // Método para cadastrar o usuário utilizando Firebase Authentication
    private void cadastrarUsuario() {
        Object ConfiguraBd = null;
        autenticacao = ConfiguraBd.Fireautenticacao(); 

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getPass()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Sucesso ao Cadastrar o usuario", Toast.LENGTH_SHORT).show(); 
                }else{
                    Toast.makeText(MainActivity.this, "Deu ruim!", Toast.LENGTH_SHORT).show(); 
                }
            }
        });
    }
}