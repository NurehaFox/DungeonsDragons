package com.softwareproject.dungeonsdragons;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class DMActivity extends Activity {
    private EditText Email, Password, UserName;
    private Button SignIn, SignUp;
    private ProgressBar progressBar;
    private FirebaseAuth Authen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm);
        //Firebase Users

        //Initialise database
        DatabaseReference MyDatabase = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference UsernameRef = MyDatabase.child("DMUsers/Username");
        final DatabaseReference EmailRef = MyDatabase.child("DMEmail/Email");
        Authen = FirebaseAuth.getInstance();

        SignIn = (Button) findViewById(R.id.SignIn);
        SignUp = (Button) findViewById(R.id.Register);
        Email = (EditText) findViewById(R.id.Email);
        UserName = (EditText) findViewById(R.id.UserName);
        Password = (EditText) findViewById(R.id.Password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpenActivity = new Intent(getApplicationContext(), DMMainScreenActivity.class);
                startActivity(OpenActivity);
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String UserNames = UserName.getText().toString().trim();
                String UserEmail = Email.getText().toString().trim();
                String UserPassword = Password.getText().toString().trim();

                if (TextUtils.isEmpty(UserEmail)) {
                    Toast.makeText(getApplicationContext(), "Enter your Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Send to database
                UsernameRef.push().setValue(UserName.getText().toString());
                if (TextUtils.isEmpty(UserPassword)) {
                    Toast.makeText(getApplicationContext(), "Enter your Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Send to database
                EmailRef.push().setValue(Email.getText().toString());
                if (UserPassword.length() < 5) {
                    Toast.makeText(getApplicationContext(), "Please Enter a Minimum of 5 Characters", Toast.LENGTH_SHORT).show();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);
                //create user
                Authen.createUserWithEmailAndPassword(UserEmail, UserPassword)
                        .addOnCompleteListener(DMActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(DMActivity.this, "Sign Up Successful:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(DMActivity.this, "Authentication Failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(DMActivity.this, DMMainScreenActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}