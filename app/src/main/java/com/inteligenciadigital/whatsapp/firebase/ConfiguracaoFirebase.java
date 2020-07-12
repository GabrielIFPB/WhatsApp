package com.inteligenciadigital.whatsapp.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {
	private static FirebaseAuth AUTH;
	private static DatabaseReference BANCO;

	public static FirebaseAuth getFirebaseAuth() {
		if (AUTH == null) AUTH = FirebaseAuth.getInstance();
		return AUTH;
	}

	public static DatabaseReference getFirebaseBanco() {
		if (BANCO == null) BANCO = FirebaseDatabase.getInstance().getReference();
		return BANCO;
	}
}
