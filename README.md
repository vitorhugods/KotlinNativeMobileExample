
### Goal

To provide a way of developing a library for both both Android and iOS projects without minimal mention of Kotlin/Native whatsoever. This should be just added as any other dependency for the target platform.

**Make iOS adoption of shared code seamless**

##### iOS Requirements

 - No Java at all to build iOS App
 
This project should generate iOS artifacts that could be used without any need for Java or Kotlin/Native compiling. 
Ideally, just something like
```ruby
pod 'KNativeArtifacts', :git => 'https://github.com/vitorhugods/KotlinNativeMobileArtifacts'
```

##### Android Requirements

I don't have high expectations compared with iOS, as having Java in an Android project is already a must, and deploying a Kotlin/JVM library to a Maven repository is something trivial. I'll think about this later.


## Implementation Idea

When pushing/tagging this library, a build should be triggered. This build will be responsible for creating all the needed artifacts and store it as a fat `xcframework` that the iOS development team can use without any Java.

See the [builds](https://github.com/vitorhugods/KotlinNativeMobileExample/actions) and the [workflows](https://github.com/vitorhugods/KotlinNativeMobileExample/tree/master/.github/workflows).

This is being built using Github Actions.


## TODO:
* Store `xcframework` with `podspec` in another repository automatically.
