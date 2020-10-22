package mz.co.sidy.pvn.activity.login;

import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import mz.co.sidy.pvn.R;
import mz.co.sidy.pvn.util.BaseActivity;

public class ProfileScreen extends BaseActivity {
    private EditText edtEmail;
    private EditText edtTelemovel;
    private EditText edtNome;
    private ImageView imvFoto;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        edtEmail = findViewById(R.id.edtEmail);
        edtNome = findViewById(R.id.edtNome);
        edtTelemovel = findViewById(R.id.edtTelemovel);
        imvFoto = findViewById(R.id.imvPhoto);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null)
            user = auth.getCurrentUser();

        userDetails();

        findViewById(R.id.layoutGravar).setOnClickListener(v -> {
            if (user != null) {
                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(edtNome.getText().toString())
                        .setPhotoUri(Uri.parse("https://www.dropbox.com/s/zs9ljcfhkvec8ei/sid.goenha%40gmail.com.jpg?dl=0"))
                        .build();
                user.updateProfile(profileChangeRequest).addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                        mensagem(getString(R.string.msg_actualizado), this);
                });
            }
        });
    }

    private void userDetails() {
        if (user != null) {
            edtEmail.setText(user.getEmail());
            edtNome.setText(user.getDisplayName());
            edtTelemovel.setText(user.getPhoneNumber());
            imvFoto.setImageURI(user.getPhotoUrl());
        }
    }
}
