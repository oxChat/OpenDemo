package com.example.a68opendemo

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity


class AuthLoginActivity : ComponentActivity() {

    var prod = "customoctopusauth://page/auth"
    var uat = "customoctopusauthuat://page/auth"
    var test = "customoctopusauthtest://page/auth"

    var schemePath = "customoctopusauthtest://page/auth"
    var clientId = ""
    var code_challenge = "CR66p8UOrWo2ge8GdG0ft9Aqbwp9sLPYvSkFms_eRh0"
    var code_challengeMethod = "S256"
    var scope = ""
    var unique_id = ""
    var redirectUri = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_login)
        if (Intent.ACTION_VIEW == intent.action) {
            val data = intent.data

            if (data != null) {
                findViewById<TextView>(R.id.tv_authInfo).text =
                    "当前状态：授权登录成功:\n\ncode为\n\n" + data.getQueryParameter("code") + "\n\nunique_id为\n\n" + data.getQueryParameter(
                        "unique_id"
                    )
            }
        }

        findViewById<Button>(R.id.btnAuthLogin).setOnClickListener {
            if (findViewById<EditText>(R.id.et_clientId).text.toString().trim()
                    .isEmpty() || findViewById<EditText>(R.id.et_scope).text.toString().trim()
                    .isEmpty() || findViewById<EditText>(R.id.et_unique_id).text.toString().trim()
                    .isEmpty() || findViewById<EditText>(R.id.et_redirectUri).text.toString().trim()
                    .isEmpty()
            ) {
                Toast.makeText(this, "数据不能为空", Toast.LENGTH_SHORT).show()
            } else if (!getPackageCode()) {
                Toast.makeText(
                    this, "当前版本过低或未找到应用程序，请下载最新App使用", Toast.LENGTH_SHORT
                ).show()
                val uri = Uri.parse("https://68chat3.com/cn")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } else {
                clientId = findViewById<EditText>(R.id.et_clientId).text.toString().trim()
                scope = findViewById<EditText>(R.id.et_scope).text.toString().trim()
                unique_id = findViewById<EditText>(R.id.et_unique_id).text.toString().trim()
                redirectUri = findViewById<EditText>(R.id.et_redirectUri).text.toString().trim()

                val path =
                    schemePath + "?clientId=" + clientId + "&code_challenge=" + code_challenge + "&code_challengeMethod=" + code_challengeMethod + "&scope=" + scope + "&unique_id=" + unique_id + "&redirectUri=" + redirectUri
                val uri = Uri.parse(path)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        findViewById<TextView>(R.id.tvProd).setOnClickListener {
            schemePath = prod
            findViewById<TextView>(R.id.tvProd).setTextColor(resources.getColor(R.color.fb2826))
            findViewById<TextView>(R.id.tvUAT).setTextColor(resources.getColor(R.color.black))
            findViewById<TextView>(R.id.tvTest).setTextColor(resources.getColor(R.color.black))
        }
        findViewById<TextView>(R.id.tvUAT).setOnClickListener {
            schemePath = uat

            findViewById<TextView>(R.id.tvProd).setTextColor(resources.getColor(R.color.black))
            findViewById<TextView>(R.id.tvUAT).setTextColor(resources.getColor(R.color.fb2826))
            findViewById<TextView>(R.id.tvTest).setTextColor(resources.getColor(R.color.black))
        }
        findViewById<TextView>(R.id.tvTest).setOnClickListener {
            schemePath = test
            findViewById<TextView>(R.id.tvProd).setTextColor(resources.getColor(R.color.black))
            findViewById<TextView>(R.id.tvUAT).setTextColor(resources.getColor(R.color.black))
            findViewById<TextView>(R.id.tvTest).setTextColor(resources.getColor(R.color.fb2826))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        if (Intent.ACTION_VIEW == intent?.action) {
            val data = intent.data

            if (data != null) {
                findViewById<TextView>(R.id.tv_authInfo).text =
                    "当前状态：授权登录成功:\n\ncode为\n\n" + data.getQueryParameter("code") + "\n\nunique_id为\n\n" + data.getQueryParameter(
                        "unique_id"
                    )
            }
        }
    }

    fun getPackageCode(): Boolean {
        try {
            val pm: PackageManager = getPackageManager()
            val packageName = "liuba.client.android.telephone.international"
            val versionCode = pm.getPackageInfo(packageName, 0).versionCode

            if (versionCode >= 512) {
                return true
            } else {
                return false
            }
        } catch (e: PackageManager.NameNotFoundException) {
            // 应用未安装或者包名错误
            return false
        }
    }
}