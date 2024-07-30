plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.musical"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.musical"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            pickFirsts += "/META-INF/LICENSE.txt"
            pickFirsts += "/META-INF/NOTICE.txt"
            // Add more excludes or pickFirsts as needed
        }
    }
}

dependencies {
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.accompanist.pager.v0300)
    implementation(libs.accompanist.pager.indicators.v0300)
    implementation(libs.androidx.material)
    implementation(libs.ui)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx.v231)
    implementation(libs.androidx.activity.compose.v140)
    implementation(libs.androidx.core.ktx.v170)
    implementation(libs.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.navigation.compose.v253)
    implementation(libs.androidx.material3.v110)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.androidx.benchmark.macro)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.navigation.compose.v275)
    implementation(libs.androidx.ui.v160alpha08)
    implementation(libs.androidx.material.v160alpha08)
    implementation(libs.ui.tooling.preview)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)
    implementation(libs.protolite.well.known.types)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.foundation)
    implementation(libs.ui.graphics)
    implementation (libs.androidx.material.v130)
    implementation (libs.androidx.material3.v100alpha01)
    implementation (libs.androidx.navigation.compose.v240alpha06)

}
