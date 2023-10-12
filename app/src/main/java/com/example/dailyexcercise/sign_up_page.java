
package com.example.dailyexcercise;

        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.example.fitnesstracker.R;
        import com.google.android.material.textfield.TextInputLayout;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class sign_up_page extends AppCompatActivity {

    // Variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        // Initialize Firebase database reference
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        // Hooks to UI elements
        regName = findViewById(R.id.reg_Name);
        regUsername = findViewById(R.id.reg_Username);
        regEmail = findViewById(R.id.reg_Email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_Password);
        regBtn = findViewById(R.id.reg_Btn);
        regToLoginBtn = findViewById(R.id.reg_Login_Btn);

        // Set click listener for the "Already Have an Account? Login" button
        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the login activity when the button is clicked
                Intent intent = new Intent(sign_up_page.this, loginpage.class);
                startActivity(intent);
            }
        });
    }

    // Your validation methods here..
    // .

    private boolean validateName(){
        String  val =regName.getEditText().getText().toString();
        if(val.isEmpty()){
            regName.setError("field cannot be empty");
            return false;
        }
        else{
            regName.setError(null);
            return true;
        }
    }
    private boolean validateUserName(){
        String val=regUsername.getEditText().getText().toString();
        String noWhiteSpace="\\A\\W{4,20}\\z";
        if(val.isEmpty()){
            regUsername.setError("field cannot be empty");
            return false;
        }
        else if(val.length()>=15){
            regUsername.setError("use 15 words");
            return false;
        }
        else if(val.matches(noWhiteSpace )){
            return false;
        }
        else{
            regUsername.setError(null);

            return true;
        }



    }
    private boolean validateEmail() {
        String val=regEmail.getEditText().getText().toString();
        String emailPattern="[a-zA-z0-9.-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            regEmail.setError("field cannot be empty");
            return false;
        }
        else if(!val.matches(emailPattern)){
            regEmail.setError("invalid email address");
            return false;
        }
        else{
            regEmail.setError(null);

            return true;
        }
    }
    private boolean validatePhoneNo() {
        String val=regPhoneNo.getEditText().getText().toString();

        if(val.isEmpty()){
            regPhoneNo.setError("field cannot be empty");
            return false;
        }
        else{
            regEmail.setError(null);

            return true;
        }
    }
    private boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
//        String passwordVal = "^(?=.*[a-zA-Z])(?=.*[@#$%^&*])(?=\\s+$).{8,}$";

        if (val.isEmpty()) {
            regPassword.setError("Password cannot be empty");
            return false;
//        } else if (!val.matches(passwordVal)) {
//            regPassword.setError("Password is too weak");
//            return false;
        }
        else if (val.length()<6){
            regPassword.setError("length is too short");
            return false;
        }
        else {
            regPassword.setError(null);

            return true;
        }

    }


    public void registerUser(View view) {
        if (!validateName() || !validatePassword() || !validateEmail() || !validateUserName() || !validatePhoneNo()) {
            return;
        }

        // Get user input values
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        // Create a userHelperClass instance
        userHelperClass helperClass = new userHelperClass(name, username, email, phoneNo, password);

        // Store user data in the Firebase Realtime Database
        reference.child(phoneNo).setValue(helperClass);

        // Redirect to the dashboard activity

        Intent intent = new Intent(sign_up_page.this, dashboard.class);
        startActivity(intent);
    }
}
