package com.example.oilex

import android.app.Application
import android.content.Context
import com.kakao.auth.*


class GlobalApplication : Application() {


//    companion object {
//        @Volatile
//        private var instance: GlobalApplication? = null
//
//        @JvmStatic
//        fun getInstance(): GlobalApplication =
//            instance ?: synchronized(this) {
//                instance ?: GlobalApplication().also {
//                    instance = it
//                }
//            }
//    }
//
//    class KakaoSDKAdapter : KakaoAdapter() {
//        override fun getApplicationConfig(): IApplicationConfig? {
//            return IApplicationConfig {
//                getInstance().applicationContext
//            }
//        }
//
//        override fun getSessionConfig(): ISessionConfig {
//            return object : ISessionConfig {
//                override fun isSaveFormData(): Boolean {
//                    return true
//                }
//
//                override fun getAuthTypes(): Array<AuthType> {
//                    return arrayOf(AuthType.KAKAO_LOGIN_ALL)
//                }
//
//                override fun isSecureMode(): Boolean {
//                    return false
//                }
//
//                override fun getApprovalType(): ApprovalType {
//                    return ApprovalType.INDIVIDUAL
//                }
//
//                override fun isUsingWebviewTimer(): Boolean {
//                    return false
//                }
//            }
//        }
//
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        KakaoSDK.init(KakaoSDKAdapter())
//    }

}