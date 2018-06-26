Forecast Five

Design Pattern and General Overview:
I have used MVVM pattern for this project utilising ViewModel and LiveData from Android Architecture Components and Databinding. This approach has enabled me to leverage the usual advantages of MVVM with all the other benefits provided by these libraries. To be specific, Databinding has reduced the view setup and handling code drastically and architecture components made it seamless to persist data across orientation changes and adhering to the lifecycle of activities. There is also a unit test that is written for the ViewModel utilising helper classes from architecture components and PowerMock.
The app contacts the API endpoint and gets the data in Kelvin so the conversions to other units can be done easily in the future. Right now, it supports metric units. Recyclerview is used to show the results and different data models are used for entities and DAO to enable future extensions. I have used several third-party libraries and the reasoning for their use is listed below:

Realm
Realm is used for persisting data locally. Due to the nature of the app (weather forecast), the primary emphasis is on getting the freshest data possible. I tweaked the Repository pattern a little to accommodate this change, I have prioritised getting API data first, and on successful retrieval it will be stored in Realm, and on occasions when there is error accessing API data, if the cached data is present it would be shown instead. All the operations of Realm are handled via background threads and a wrapper class is used to make injection easier. 

Dagger
Dagger is used for Dependency Injection. I have used scopes and subcomponents to further limit the visibility and lifecycle of injected dependencies. Dependencies are separated into different modules based on their nature and singleton modules are created based on their need across the entire app.  

RxJava
I’ve utilised RxJava to manage threads and perform on-the-fly manipulations of data. Long running operations and transformations are done on worker threads and UI thread’s occupancy is kept as minimal as possible. 

Android Architecture Components
I have used LiveData and ViewModel from the architecture components. I took advantage of ViewModel’s native support for configuration changes and LiveData’s lifecycle awareness to manage these otherwise relatively complex tasks. 

PowerMock
I used PowerMock to utilise its support for mocking final and static classes/methods. 

Glide
I used Glide to manage image loading, memory handling and caching 

Possible Improvements:
Due to the time limitation I had to choose across several implementation/design choices and this list is inclusive of things I could have done and other possible improvements down the line to enhance the features and usability of the app
- Using MapView to allow the user to select a location and find it’s weather forecast. Or use GPS/location info to automatically detect the user’s location
- Options to switch between metric and other units
- Using placeholder images for Glide
- Making the error message actionable (allowing refresh)
- Using transformations API of LiveData instead of mapping via RxJava as the former is lazily executed
- Writing instrumentation tests and more unit tests in general as well
- Using timestamps to indicate the freshness/staleness of data
- Having different colours for the temperature (since databinding supports expressions, this would be a case of if  temperature > threshold colour for warmth else colour for cold)

Build Instructions:
1. Clone from Github.
2. Import into Android Studio
3. Build and Run, (Minimum SDK is 19, No other special dependancies or configuration needed).
