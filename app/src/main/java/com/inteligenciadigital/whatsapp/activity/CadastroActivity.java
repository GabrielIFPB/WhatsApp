package com.inteligenciadigital.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.inteligenciadigital.whatsapp.R;
import com.inteligenciadigital.whatsapp.firebase.ConfiguracaoFirebase;
import com.inteligenciadigital.whatsapp.models.Usuario;

public class CadastroActivity extends AppCompatActivity {

	private TextInputEditText nome, email, senha;
	private Usuario usuario;
	private FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAuth();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);

		this.nome = findViewById(R.id.nome_id);
		this.email = findViewById(R.id.email_id);
		this.senha = findViewById(R.id.senha_id);
	}

	private boolean validarCampos(String nome, String email, String senha) {
		if (!nome.isEmpty()) {
			if (!email.isEmpty()) {
				if (!senha.isEmpty()) {
					return true;
				} else {
					this.toast("Preencha a senha!");
				}
			} else {
				this.toast("Preencha o e-mail!");
			}
		} else {
			this.toast("Preencha o nome!");
		}
		return false;
	}

	public void cadastrar(View view) {
		String nome = this.nome.getText().toString();
		String email = this.email.getText().toString();
		String senha = this.senha.getText().toString();
		if (this.validarCampos(nome, email, senha)) {
			this.usuario = new Usuario(nome, email, senha);
			this.salvarUsuario();
		}
	}

	private void salvarUsuario() {
		this.auth.createUserWithEmailAndPassword(
				this.usuario.getEmail(), this.usuario.getSenha()
		).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(@NonNull Task<AuthResult> task) {
				validarCadastro(task);
			}
		});
	}

	private void validarCadastro(Task<AuthResult> task) {
		if (task.isSuccessful()) {
			usuario.setId(task.getResult().getUser().getUid());
			usuario.salvar();
			this.toast("Sucesso ao cadastrar Usuário!");
			finish();
		} else {
			try {
				throw task.getException();
			} catch (FirebaseAuthWeakPasswordException e) {
				this.toast("Digite uma senha mais forte!");
			} catch (FirebaseAuthUserCollisionException e) {
				this.toast("Está conta já foi cadastrado!");
			} catch (FirebaseAuthInvalidCredentialsException e) {
				this.toast("Por favor, digite um e-mail válido!");
			} catch (Exception e) {
				this.toast("Erro ao cadastrar usuário: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private void toast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}