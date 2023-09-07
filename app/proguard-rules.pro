# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Platform calls Class.forName on types which do not exist on Android to determine platform.


-keep public class * extends java.lang.Exception
-keep class androidx.* {*;}
-dontwarn retrofit.**
-keep class retrofit.* { *; }
-keep class com.google.android.* {*;}
-keep class androidx.core.app.CoreComponentFactory { *; }
-keep class android.content.Context.*{*;}
-keep class android.content.Intent.*{*;}
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keepattributes Exceptions,InnerClasses,Signature

# Room classes and annotations
-keep class androidx.room.** { *; }
-keep class androidx.arch.core.** { *; }
-keep @androidx.room.Entity class *
-keep @androidx.room.Dao class *

# Preserve entities' fields
-keepclassmembers @androidx.room.Entity class * {
    <fields>;
}

# Preserve DAOs' methods
-keepclassmembers @androidx.room.Dao class * {
    <methods>;
}

# Prevent obfuscation of column names
-keepclassmembers @androidx.room.* class * {
   @androidx.room.* <fields>;
}



