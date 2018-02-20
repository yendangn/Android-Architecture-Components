# Android Architecture Components
This repository contains a detailed sample app that implements Android Architecture Components using Dagger2.

## The architecture
The following diagram shows all the modules in our recommended architecture and how they interact with one another:
![architecture diagram](https://github.com/yendangn/AndroidMVVM/blob/master/image/Android_Architecture_Components.png)
 
## The Android Architecture Components 
1. [Android Architecture Components Overview](https://developer.android.com/topic/libraries/architecture/guide.html)
2. [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html)
3. [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html)

## Network module
1. [Retrofit](http://square.github.io/retrofit/)
2. [Okhttp](http://square.github.io/okhttp/)

## Dependency injection Framework
I'm using [Dagger 2](https://google.github.io/dagger/) library to implementing dependency injection in Android apps. Dagger 2 automatically constructs objects by walking the dependency tree and provides compile time guarantees on dependencies.


## Next version
I will intergrate with local database. You can learn more at : [Saving Data Using the Room Persistence Library](https://developer.android.com/training/data-storage/room/index.html)

## How to run demo app?
1. First you should go to this page: https://www.themoviedb.org/documentation/api.
2. Register a account and get your API key.
3. Then update this API key into gradle file.
![](https://github.com/yendangn/Android-Architecture-Components/blob/master/image/api_key.png)

## Reference
https://medium.com/@marco_cattaneo/integrate-dagger-2-with-room-persistence-library-in-few-lines-abf48328eaeb
https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-2-di-1a6b1f96d84b
https://medium.com/@Miqubel/understanding-dagger-2-367ff1bd184f
https://developer.android.com/topic/libraries/architecture/guide.html
http://www.thomaskioko.com/android-architecture-components-part-2-dependency-injection/
