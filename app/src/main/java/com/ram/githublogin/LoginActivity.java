package com.ram.githublogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.OAuthProvider;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout userNameInput , passwordInput;
    FirebaseAuth mAuth;
    MaterialButton button ;
    OAuthProvider.Builder provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            FirebaseUser firebaseUser = mAuth.getCurrentUser();
            Intent intent = new Intent(getApplicationContext() ,HomeActivity.class);
            intent.putExtra("githubUserName", firebaseUser.getDisplayName());

            startActivity(intent);
            finish();
        }
         provider = OAuthProvider.newBuilder("github.com");

        userNameInput = findViewById(R.id.inputUser);

        String userName = userNameInput.getEditText().getText().toString();

        provider.addCustomParameter("login" ,userName);

        // Request read access to a user's email addresses.
// This must be preconfigured in the app's API permissions.
        List<String> scopes =
                new ArrayList<String>() {
                    {
                        add("user:email");
                    }
                };
        provider.setScopes(scopes);

        button = findViewById(R.id.continueBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGithubProvider();
            }
        });

     

    }

    private void signInWithGithubProvider() {
        Task<AuthResult> pendingResultTask = mAuth.getPendingAuthResult();
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    // User is signed in.
                                    // IdP data available in
                                    // authResult.getAdditionalUserInfo().getProfile().
                                    // The OAuth access token can also be retrieved:
                                    // authResult.getCredential().getAccessToken().
                                    Toast.makeText(LoginActivity.this, "User Exist", Toast.LENGTH_SHORT).show();
                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle failure.
                                }
                            });
        } else {
            // There's no pending result so you need to start the sign-in flow.
            // See below.

            mAuth
                    .startActivityForSignInWithProvider(/* activity= */ this, provider.build())
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    // User is signed in.
                                    // IdP data available in
                                    // authResult.getAdditionalUserInfo().getProfile().
                                    // The OAuth access token can also be retrieved:
                                    // authResult.getCredential().getAccessToken().
                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    Toast.makeText(LoginActivity.this, "Successfully login", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext() ,HomeActivity.class);
                                    intent.putExtra("githubUserName", firebaseUser.getDisplayName());
                                    startActivity(intent);
                                    finish();
                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle failure.
                                }
                            });
        }
    }


}