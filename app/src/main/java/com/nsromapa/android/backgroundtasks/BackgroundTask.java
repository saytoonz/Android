package com.nsromapa.android.backgroundtasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;


import com.nsromapa.android.runners.AccountVerificationActivity;
import com.nsromapa.android.runners.CheckNetwork;
import com.nsromapa.android.runners.CompleteVerificationActivity;
import com.nsromapa.android.HomeActivity;
import com.nsromapa.android.sessions.UserSessionManager;
import com.nsromapa.android.fogetpass.ConfirmForgetEmailActivity;
import com.nsromapa.android.fogetpass.ForgetPassActivity2;
import com.nsromapa.android.signup_login.RegistrationActivity2;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by SAY on 11/08/2018.
 */

public class BackgroundTask extends AsyncTask<String, Void, String> {

//        String ImageUrl = "https://nsromapa.000webhostapp.com/";
//        String LoginUrl = "https://nsromapa.000webhostapp.com/android/androidLogin.php";
//        String resendVerif = "https://nsromapa.000webhostapp.com/android/androidJoin_app.php?action=resendVeif";
//        String VerifyUrl = "https://nsromapa.000webhostapp.com/android/androidVerify_.php";
//        String Like_user_Url = "https://nsromapa.000webhostapp.com/android/androidpostsangGets_general.php?action=uL";
//        String username_email_CheckSignup = "https://nsromapa.000webhostapp.com/android/androidJoin_app.php?action=uname_mail_check";
//        String SignUpUrl = "https://nsromapa.000webhostapp.com/android/androidJoin_app.php?action=try_signup_account";
//        String FogPassSearchU_Url = "https://nsromapa.000webhostapp.com/android/androidJoin_app.php?action=FogPassSearchU";
//        String FogPassVerifUrl = "https://nsromapa.000webhostapp.com/android/androidJoin_app.php?action=FogPassVerif";

    String ImageUrl = "http://192.168.43.22/nsromapa2";
    String LoginUrl = "http://192.168.43.22/nsromapa2/android/androidLogin.php";
    String resendVerif = "http://192.168.43.22/nsromapa2/android/androidJoin_app.php?action=resendVeif";
    String VerifyUrl = "http://192.168.43.22/nsromapa2/android/androidVerify_.php";
    String Like_user_Url = "http://192.168.43.22/nsromapa2/android/androidpostsangGets_general.php?action=uL";
    String username_email_CheckSignup = "http://192.168.43.22/nsromapa2/android/androidJoin_app.php?action=uname_mail_check";
    String SignUpUrl = "http://192.168.43.22/nsromapa2/android/androidJoin_app.php?action=try_signup_account";
    String FogPassSearchU_Url = "http://192.168.43.22/nsromapa2/android/androidJoin_app.php?action=FogPassSearchU";
    String FogPassVerifUrl = "http://192.168.43.22/nsromapa2/android/androidJoin_app.php?action=FogPassVerif";

    Context ctx;
    ProgressDialog progressDialog;
    Activity activity;
    AlertDialog.Builder builder;

    UserSessionManager session;

    public BackgroundTask(Context ctx) {
        this.ctx = ctx;
        activity = (Activity) ctx;
    }


    @Override
    protected void onPreExecute() {
        CheckNetwork checkNetwork = new CheckNetwork();
        if (checkNetwork.isNetworkAvailable(ctx)) {
            builder = new AlertDialog.Builder(activity);
            progressDialog = new ProgressDialog(ctx);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Connecting to server...");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected String doInBackground(String... params) {
        String method = params[0];

        //////Login
        if (method.equals("try_entry")) {
            try {
                URL url = new URL(LoginUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String username, password;
                username = params[1];
                password = params[2];

                String data = URLEncoder.encode("usaname", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("usaPass", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(5000);
                Log.d("Test", "Test 3 pass");

                return stringBuilder.toString().trim();


            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }


            ////Resend New User Verification Code
        } else if (method.equals("Resend_Account_Verification_Code")) {

            URL url = null;
            try {

                url = new URL(resendVerif);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String uid = params[1];

                String data = URLEncoder.encode("u", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(5000);
                Log.d("Test", "Test 3 pass");

                return stringBuilder.toString().trim();


            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }


            //Send Virification Code to Verify my new account
        } else if (method.equals("Verify_my_new_account")) {
            URL url = null;
            try {

                url = new URL(VerifyUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String code = params[1];
                String uid = params[2];

                String data = URLEncoder.encode("u", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8") + "&" +
                        URLEncoder.encode("vfcode", "UTF-8") + "=" + URLEncoder.encode(code, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(5000);
                Log.d("Test", "Test 3 pass");

                return stringBuilder.toString().trim();


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


            //Like user Profile
        } else if (method.equals("Like_user_as_you_want_to_follow")) {
            URL url = null;
            try {

                url = new URL(Like_user_Url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String uid = params[1];

                String data = URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(5000);
                Log.d("Test", "Test 3 pass");

                return stringBuilder.toString().trim();


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


        } else if (method.equals("check_username_and_email_for_signup")) {
            URL url = null;
            try {

                url = new URL(username_email_CheckSignup);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String username = params[1];
                String email = params[2];

                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(5000);
                Log.d("Test", "Test 3 pass");

                return stringBuilder.toString().trim();


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


            //Sign Up
        } else if (method.equals("SendSignUp_registration")) {
            try {
                URL url = new URL(SignUpUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String newUsername, newEmail, newFName, newLName, newDOB, newGender, newpass, newpass2;

                newUsername = params[1];
                newEmail = params[2];
                newFName = params[3];
                newLName = params[4];
                newDOB = params[5];
                newGender = params[6];
                newpass = params[7];
                newpass2 = params[8];

                String data = URLEncoder.encode("newUsername", "UTF-8") + "=" + URLEncoder.encode(newUsername, "UTF-8") + "&" +
                        URLEncoder.encode("newEmail", "UTF-8") + "=" + URLEncoder.encode(newEmail, "UTF-8") + "&" +
                        URLEncoder.encode("newFName", "UTF-8") + "=" + URLEncoder.encode(newFName, "UTF-8") + "&" +
                        URLEncoder.encode("newLName", "UTF-8") + "=" + URLEncoder.encode(newLName, "UTF-8") + "&" +
                        URLEncoder.encode("newDOB", "UTF-8") + "=" + URLEncoder.encode(newDOB, "UTF-8") + "&" +
                        URLEncoder.encode("newGender", "UTF-8") + "=" + URLEncoder.encode(newGender, "UTF-8") + "&" + URLEncoder.encode("newEmail", "UTF-8") + "=" + URLEncoder.encode(newEmail, "UTF-8") + "&" +
                        URLEncoder.encode("newpass", "UTF-8") + "=" + URLEncoder.encode(newpass, "UTF-8") + "&" +
                        URLEncoder.encode("newpass2", "UTF-8") + "=" + URLEncoder.encode(newpass2, "UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(5000);
                Log.d("Test", "Test 3 pass");

                return stringBuilder.toString().trim();


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


            //Search for user when want to reset password with
            //username and email
        } else if (method.equals("FogPassSearchU")) {
            try {
                URL url = new URL(FogPassSearchU_Url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String Username, Email;

                Username = params[1];
                Email = params[2];

                String data = URLEncoder.encode("u", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8") + "&" +
                        URLEncoder.encode("m", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(5000);
                Log.d("Test", "Test 3 pass");

                return stringBuilder.toString().trim();


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


            //Send Verification code for Forget Password Verifications
        } else if (method.equals("Send_Verification_code_for_Forget_Password")) {
            try {
                URL url = new URL(FogPassVerifUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String Email;
                ;
                Email = params[1];

                String data = URLEncoder.encode("m", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(5000);
                Log.d("Test", "Test 3 pass");

                return stringBuilder.toString().trim();


            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


        }


        return null;
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPostExecute(String json) {

        super.onPostExecute(json);

        CheckNetwork checkNetwork = new CheckNetwork();
        if (checkNetwork.isNetworkAvailable(ctx))
            progressDialog.dismiss();


        if (json == null) {
            // null response or Exception occur
        } else {
            try {

                JSONObject jsonObject = new JSONObject(json);
                JSONObject JO = jsonObject.getJSONObject("server_response");

                String ResponseCode = JO.getString("ResponseCode");
                String message = JO.getString("message");
                String uname = JO.getString("username");

                /////User has successfully logged in
                if (ResponseCode.equals("loggedin_true")) {
                    session = new UserSessionManager(activity);
                    session.createUserLoginSession(uname, message);
                    Intent homeIntent = new Intent(activity, HomeActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(homeIntent);
                    activity.finish();


                    /////User Account not verified Yet
                } else if (ResponseCode.equals("loggedin_not_verify")) {

                    //Redirect user to verification intent
                    Intent Verif_Intent = new Intent(activity, AccountVerificationActivity.class);
                    Verif_Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Verif_Intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Verif_Intent.putExtra("uid", message);
                    activity.startActivity(Verif_Intent);
                    activity.finish();


                    //Get response when Resend Verification button is clicked
                    //At the verify account page
                } else if (ResponseCode.equals("successfullySentReverification")) {

                    showDialog("Successful", "Verification code has been successfully sent to your email!", ResponseCode);


                    //Account Verification is successful
                } else if (ResponseCode.equals("newVerificationSuccessful")) {

                    session = new UserSessionManager(activity);
                    session.createUserLoginSession(uname, message);

                    //Redirect user to complete_verification intent
                    Intent comp_Verif_Intent = new Intent(activity, CompleteVerificationActivity.class);
                    comp_Verif_Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    comp_Verif_Intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    comp_Verif_Intent.putExtra("uid", message);
                    activity.startActivity(comp_Verif_Intent);
                    activity.finish();


                    //User liked Nsromapa is being liked successfully
                } else if (ResponseCode.equals("liked_user")) {
                    //Close the Complete verification page when Nsromapa is being liked successfully
                    activity.finish();


                    //Get response when Resend Verification button is clicked
                    //At the verify account page
                } else if (ResponseCode.equals("Username_available_for_use")) {


                    //Redirect user to Register page 2  intent
                    Intent register2 = new Intent(activity, RegistrationActivity2.class);
                    register2.putExtra("newUsername", uname);
                    register2.putExtra("newEmail", message);
                    activity.startActivity(register2);


                    //User liked Nsromapa is being liked successfully
                } else if (ResponseCode.equals("sign_up_is_true")) {

                    //Redirect user to verification intent
                    Intent Verif_Intent = new Intent(activity, AccountVerificationActivity.class);
                    Verif_Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Verif_Intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Verif_Intent.putExtra("uid", message);
                    activity.startActivity(Verif_Intent);
                    activity.finish();


                    //Forget Password search user found....
                } else if (ResponseCode.contains("FogPassSearchU_found_successfully")) {

                    String imgLink = ImageUrl + ResponseCode.replace("FogPassSearchU_found_successfully", "");

                    //Redirect user to Search result intent that's ForgetPassActivity 2
                    Intent forgPass2 = new Intent(activity, ForgetPassActivity2.class);
                    forgPass2.putExtra("imageLink", imgLink);
                    forgPass2.putExtra("full_name", message);
                    forgPass2.putExtra("email", uname);


                    activity.startActivity(forgPass2);


                    //Forget Password Verificaiton sent successfully
                } else if (ResponseCode.contains("FogPassVerif_successful")) {

                    Intent verifyActivity = new Intent(activity, ConfirmForgetEmailActivity.class);
                    verifyActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    verifyActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    verifyActivity.putExtra("email", message);
                    verifyActivity.putExtra("username", uname);
                    activity.startActivity(verifyActivity);


                    //User liked Nsromapa is being liked successfully
                } else {
                    showDialog("Error", message, ResponseCode);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private void showDialog(String title, String Message, String ResponseCode) {
        CheckNetwork checkNetwork = new CheckNetwork();
      if (checkNetwork.isNetworkAvailable(ctx)){
          builder.setTitle(title);

          if (ResponseCode.equals("loggedin_false") ||
                  ResponseCode.equals("successfullySentReverification") ||
                  ResponseCode.equals("newVerificationFailed") ||
                  ResponseCode.equals("username_email_Check_false") ||
                  ResponseCode.equals("sign_up_is_false") ||
                  ResponseCode.equals("FogPassSearchU_not_found") ||
                  ResponseCode.equals("FogPassVerif_failed")) {

              builder.setMessage(Message);

              builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      dialog.dismiss();
                  }
              });
          }


          AlertDialog alertDialog = builder.create();
          alertDialog.show();
      }
    }


}
