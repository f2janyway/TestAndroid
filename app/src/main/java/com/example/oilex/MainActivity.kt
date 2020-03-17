package com.example.oilex

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.log.Logger
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var callback: SessionCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //barcode implement
        val text = "jyg"
        val multiFormatWriter = MultiFormatWriter()
        val bitMatrix =
            multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300)
        val barcodeEncoder = BarcodeEncoder()
        val bitmap = barcodeEncoder.createBitmap(bitMatrix)
        barcord_imageView.setImageBitmap(bitmap)

        /**
         * 로그인 버튼을 클릭 했을시 access token을 요청하도록 설정한다.
         *
         * @param savedInstanceState 기존 session 정보가 저장된 객체
         */
        callback = SessionCallback()
        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().checkAndImplicitOpen()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(Session.getCurrentSession().handleActivityResult(requestCode,resultCode,data))
            return
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }


    inner class SessionCallback : ISessionCallback {
        override fun onSessionOpened() {
            redirectSignupActivity()
        }

        override fun onSessionOpenFailed(exception: KakaoException) {
            if (exception != null) {
                Logger.e(exception)
            }
        }

    }

    fun redirectSignupActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
