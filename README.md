
### Goal

To provide a way of developing a library for both both Android and iOS projects without minimal mention of Kotlin/Native whatsoever. This should be just added as any other dependency for the target platform.

##### iOS Requirements

 - No Java at all to build iOS App
 
This project should generate iOS artifacts that could be used without any need for Java or Kotlin/Native compiling. 
Ideally, just something like
```ruby
pod 'KNativeArtifacts', :git => 'https://github.com/vitorhugods/KotlinNativeMobileArtifacts'
```


##### Android Goal

I don't have high expectations compared with iOS, as having Java in an Android project is already a must, and deploying a Kotlin/JVM library to a Maven repository is something trivial.
