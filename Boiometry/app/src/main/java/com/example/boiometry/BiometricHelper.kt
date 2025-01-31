package com.example.boiometry

import android.content.Context
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.os.Build


class BiometricHelper {
    companion object{
        fun isBiometricAvailable(context: Context):Boolean{
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
                return false
            }

            when (BiometricManager.Authenticators(BIOMETRIC_STRONG)) {
                BiometricManager.BIOMETRIC_SUCCESS -> return true
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> return false
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> return false
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> return false
            }
            return false
            }
        }
    }
