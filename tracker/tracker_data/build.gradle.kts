plugins{
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.example.tracker_data"
}

dependencies{
    // to make sure that we are able to access dependencies from our core module
    // and use core module classes and functions to onboarding_presentation module
    implementation(project(Modules.core))
    implementation(project(Modules.trackerDomain)) // to access to onboarding_domain module

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)

    "kapt"(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)
}