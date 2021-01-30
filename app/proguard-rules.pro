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

# 指定代码的压缩级别 0 - 7(指定代码进行迭代优化的次数，在Android里面默认是5，这条指令也只有在可以优化时起作用。)
-optimizationpasses 5
## 混淆时不会产生形形色色的类名(混淆时不使用大小写混合类名)
-dontusemixedcaseclassnames
## 指定不去忽略非公共的库类(不跳过library中的非public的类)
#-dontskipnonpubliclibraryclasses
## 指定不去忽略包可见的库类的成员
#-dontskipnonpubliclibraryclassmembers
##不进行优化，建议使用此选项，
-dontoptimize
# # 不进行预校验,Android不需要,可加快混淆速度。
#-dontpreverify
## 屏蔽警告
-ignorewarnings
## 指定混淆是采用的算法，后面的参数是一个过滤器
## 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
## 保护代码中的Annotation不被混淆
-keepattributes *Annotation*
## 避免混淆泛型, 这在JSON实体映射时非常重要
#-keepattributes Signature
## 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
# #优化时允许访问并修改有修饰符的类和类的成员，这可以提高优化步骤的结果。
## 比如，当内联一个公共的getter方法时，这也可能需要外地公共访问。
## 虽然java二进制规范不需要这个，要不然有的虚拟机处理这些代码会有问题。当有优化和使用-repackageclasses时才适用。
##指示语：不能用这个指令处理库中的代码，因为有的类和类成员没有设计成public ,而在api中可能变成public
#-allowaccessmodification
##当有优化和使用-repackageclasses时才适用。
#-repackageclasses ''
# # 混淆时记录日志(打印混淆的详细信息)
# # 这句话能够使我们的项目混淆后产生映射文件
# # 包含有类名->混淆后类名的映射关系
-verbose
-flattenpackagehierarchy
-allowaccessmodification
-dontusemixedcaseclassnames
-keepattributes Exceptions
#-dontskipnonpubliclibraryclasses
#-dontskipnonpubliclibraryclassmembers
#
##
## ----------------------------- 默认保留 -----------------------------
##
##----------------------------------------------------
## 保持哪些类不被混淆
##继承activity,application,service,broadcastReceiver,contentprovider....不进行混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends androidx.multidex.MultiDexApplication
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class * extends java.lang.Throwable {*;}
-keep public class * extends java.lang.Exception {*;}
-keep class com.google.android.material.* {*;}
-keep class androidx.* {*;}
-keep public class * extends androidx.*
-keep interface androidx.* {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**
#-keep class android.support.** {*;}## 保留support下的所有类及其内部类

#-keep public class com.google.vending.licensing.ILicensingService
#-keep public class com.android.vending.licensing.ILicensingService
##表示不混淆上面声明的类，最后这两个类我们基本也用不上，是接入Google原生的一些服务时使用的。
##----------------------------------------------------
#
##表示不混淆任何包含native方法的类的类名以及native方法名，这个和我们刚才验证的结果是一致
-keepclasseswithmembernames class * {
    native <methods>;
}
#
##这个主要是在layout 中写的onclick方法android:onclick="onClick"，不进行混淆
##表示不混淆Activity中参数是View的方法，因为有这样一种用法，在XML中配置android:onClick=”buttonClick”属性，
##当用户点击该按钮时就会调用Activity中的buttonClick(View view)方法，如果这个方法被混淆的话就找不到了
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
#
##表示不混淆任何一个View中的setXxx()和getXxx()方法，
##因为属性动画需要有相应的setter和getter的方法实现，混淆了就无法工作了。
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

## 这指定了继承Serizalizable的类的如下成员不被移除混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
## 保留R下面的资源
-keep class **.R.* {
 *;
}
##不混淆资源类下static的
-keepclassmembers class **.R.* {
    public static <fields>;
}
#
## 保留我们自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#
##
##----------------------------- WebView(项目中没有可以忽略) -----------------------------
##
##webView需要进行特殊处理
#-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
#   public *;
#}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#项目bean不被混淆
-keep class com.istone.testbuy.ui.entity.* {*;}
-keep class net.sourceforge.pinyin4j.* {*;}
-keep class com.hp.hpl.sparta.* {*;}
# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions.**

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.* { *;}
-dontwarn okhttp3.*
-dontwarn okio.**

-keep class com.google.gson.* {*;}
-keep class com.google.*{*;}
-keep class com.google.gson.stream.* { *; }
#微信支付
-keep class com.tencent.mm.opensdk.* {*;}
-keep class com.tencent.wxop.* {*;}
-keep class com.tencent.mm.sdk.* {*;}
#支付宝支付
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
-keep class com.alipay.sdk.app.H5PayCallback {
    <fields>;
    <methods>;
}
-keep class com.alipay.android.phone.mrpc.core.* { *; }
-keep class com.alipay.apmobilesecuritysdk.* { *; }
-keep class com.alipay.mobile.framework.service.annotation.* { *; }
-keep class com.alipay.mobilesecuritysdk.face.* { *; }
-keep class com.alipay.tscenter.biz.rpc.* { *; }
-keep class org.json.alipay.* { *; }
-keep class com.alipay.tscenter.* { *; }
-keep class com.ta.utdid2.* { *;}
-keep class com.ut.device.* { *;}
#蒲公英检测更新
-dontwarn com.pgyersdk.**
-keep class com.pgyersdk.* { *; }
-keep class com.pgyersdk.**$* { *; }
#饺子视频
-keep public class cn.jzvd.JZMediaSystem {*; }
#-keep public class cn.jzvd.demo.CustomMedia.CustomMedia {*; }
#-keep public class cn.jzvd.demo.CustomMedia.JZMediaIjk {*; }
#-keep public class cn.jzvd.demo.CustomMedia.JZMediaSystemAssertFolder {*; }
#
#-keep class tv.danmaku.ijk.media.player.** {*; }
#-dontwarn tv.danmaku.ijk.media.player.*
#-keep interface tv.danmaku.ijk.media.player.** { *; }
#Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}
# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
#百度统计
-keep class com.baidu.mobstat.* { *; }
-keep class com.baidu.bottom.* { *; }
#AgentWeb
-keep class com.just.agentweb.* {*;}
-dontwarn com.just.agentweb.**
-keepclassmembers class com.istone.testbuy.ui.listener.AndroidInterface{ *; }
#android-shape-imageview
-dontwarn com.github.siyamed.**
-keep class com.github.siyamed.shapeimageview.*{ *; }
#PictureSelector 2.0
-keep class com.luck.picture.lib.* { *; }
#Ucrop
-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }
#Okio
-dontwarn org.codehaus.mojo.animal_sniffer.*
#友盟
-dontwarn com.umeng.**
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**
-dontwarn com.meizu.**

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class com.meizu.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}

-dontwarn com.vivo.push.**
-keep class com.vivo.push.*{*; }
-keep class com.vivo.vms.*{*; }
-keep class xxx.xxx.xxx.PushMessageReceiverImpl.*{*;}

-keep class org.android.agoo.xiaomi.MiPushBroadcastReceiver {*;}
#可以防止一个误报的 warning 导致无法成功编译，如果编译使用的 Android 版本是 23。
-dontwarn com.xiaomi.push.**

-keep class com.hianalytics.android.*{*;}
-keep class com.huawei.updatesdk.*{*;}
-keep class com.huawei.hms.*{*;}

#shareSDK
-ignorewarnings
#关闭某个运营商功能时编译通不过可以加上下面的代码
#-dontpreverify

# for SecVerify
-keep class com.mob.**{*;}
# for CTCC
-keep class cn.com.chinatelecom.account.**{*;}
# for CUCC
-keep class com.sdk.**{*;}
# for CMCC
-keep class com.cmic.sso.sdk.**{*;}
-keep class com.unicom.xiaowo.account.shield.**{*;}

-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn cn.sharesdk.**
-dontwarn **.R$*
-dontwarn com.tencent.**
-keep class com.tencent.** {*;}

#阿里云OSS
-keep class com.alibaba.sdk.android.oss.** { *; }
-dontwarn okio.**
-dontwarn org.apache.commons.codec.binary.**