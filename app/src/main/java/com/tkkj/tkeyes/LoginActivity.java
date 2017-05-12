package com.tkkj.tkeyes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tkkj.tkeyes.utils.MyVolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    static final String TAG = "日志打印";
    EditText etaccount;
    EditText etpassword;
    CheckBox ckremember;
    Button btlogin;
    TextView tvreg;
    TextView tvpasswordforgotten;
    String account = "";
    String password = "";
    String loginurl = "";
    String regurl = "";
    DataOpenhelper dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etaccount = (EditText) findViewById(R.id.account);
        etpassword = (EditText) findViewById(R.id.password);
        ckremember = (CheckBox) findViewById(R.id.remember);
        btlogin = (Button) findViewById(R.id.login);
        tvreg = (TextView) findViewById(R.id.reg);
        tvpasswordforgotten = (TextView) findViewById(R.id.passwordforgotten);
        loginurl = getString(R.string.baseurl) + "login/isLogin/isLogin?";
        regurl = getString(R.string.baseurl) + "register/isRegister/isRegister?";
        dh = new DataOpenhelper(this);
        //SQLiteDatabase adb = dh.getWritableDatabase();
        etpassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (validate()) {
                        login();
                    }

                    Log.d(TAG, "login");
                }
                return false;
            }
        });
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login:
                if (validate()) {
                    login();
                }

                Log.d(TAG, "login");
                break;
            case R.id.remember:
                if (ckremember.isChecked()) {
                    Log.d(TAG, "checked");
                } else {
                    Log.d(TAG, "notchecked");
                }
                break;
            case R.id.reg:
                Log.d(TAG, "reg");
                break;
        }
    }

    public boolean validate() {
        account = etaccount.getText().toString().trim();
        password = etpassword.getText().toString().trim();
        boolean flag = true;
        if (account.equals("")) {
            etaccount.setError("账号不能为空");
            etaccount.requestFocus();
            flag = false;
            return flag;
        }
        if (password.equals("")) {
            etpassword.setError("密码不能为空");
            etpassword.requestFocus();
            flag = false;
            return flag;
        }
        return flag;
    }

    void login() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, loginurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError) {
                    Log.d(TAG, "TimeoutError");
                } else if (error instanceof NetworkError) {
                    Log.d(TAG, "NetworkError");
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("name_login", account);
                map.put("password_login", password);
                Log.d(TAG, account + password);
                return map;
            }
        };


        MyVolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}
