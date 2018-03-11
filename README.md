# AndroidKotlinExercise
Coding exercise of an android app using Kotlin

The app has just one screen. It launches one GET request against an HTTP API and shows the result on screen.

## Architecture
The app is written using a clean architecture:

- The business logic and domain objects don't depend on the HTTP API:
    - The app declares the domain object `CreditScore`
    - The response from the HTTP server is mapped to `CreditScore`, 
    - The presentation logic uses a `CreditScoreRepository` to get the `CredirScore`
    - The HTTP API is adapted to `CreditScoreRepository`
    - As we inverse the dependencies, the JSON format and API implementation details are concealed in the adaptation of the platform specific classes to objects
- The presentation logic is written used MVP
    - The presenter does not depend on Android
    
### Repository pattern using multiple DataSources
The presenter uses one interactor to get the `CreditScore`, which in turn delegates into `CreditScoreRepository`
The implementation of the `CreditScoreRepository` features some extra behaviour:
   - it first fetches the `CreditScore` from the network
   - to avoid re-fetching upon screen rotation, it catches the retrieved data into a memory cache
   - to allow showing any data when in offline mode, it persists the retrieved data into local storage

This is implemented used three `DataSources` (remote, local storage, memory cache) that the implementation
of the `CreditScoreRepository` delegates into and coordinates.
This complexity is concealed in the implementation of the `CreditScoreRepository` and is not leaked to the presentation logic.

### Over-engineering
For an exercise as simple as this, this is ridiculously over engineered:

- using Retrofit for such as simple GET request without parameters is overkill
- the Dagger2 graph can be simplified. It declares two components, but we could have used just one, as there is only one screen (hence, one scope)
- Using clean architecture with presentation, domain and adaptation layers is excessive for such a simple exercise

I just wanted to showcase an example of clean architecture and usage of common patterns in Android 

## Tests
The app has been written using TDD, and it contains:
- unit tests based on JUnit: test the logic that is framework-independent
- integration test: there is one instrumentation test that tests the GET request of the HTTP client with the API 
     
I'd liked to write one Espresso test for the Activity but I run out of time...
 
## Libraries used
gradle.build imports some libraries and documents what are they used for. In a nutshell:

- RXJava2: to expose a RX interface in the `CreditScoreRepository` and handle the threading concerns
- Dagger2: to inject dependencies so that everything can be unit tested
- Retrofit2 and Retrofit RX adapters: to implement the HTTP API 
- Moshi: to parse the JSON response into a Kotlin data class