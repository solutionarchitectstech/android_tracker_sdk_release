# Tracking SDK Demo app



# Tracking SDK Android

Tracking SDK is used to track targeted user actions in the mobile app and further analyze this information.

**Before you start**

You should be aware of the following options required to initialize and use the SDK. Ask the SDK team to give them to you.

- partnerId - partner ID. For example: `123`
- baseUrl - URL API of advertising system. For example: `"https://my.server.com/"`


**SDK version**: 0.0.13

**Demo**: [tracking-demo-app](tracking-demo-app/README.md)

**Requirements**

- Kotlin version >= 1.6.20
- Android 5.0+ (API Level >= 21)

## Installation

```-vue
dependencies {
    //... other dependencies
    implementation 'com.github.solutionarchitectstech:android_tracker_sdk_release:0.0.13'
    
    //... other dependencies
}
```

## Tracker initialization

The library is initialized by the developer when the application is started or when the user is authorized by the method call `TechTracker.initialize(options: Options?)` and transfer of a prepared object `Options`.

```kotlin
data class Options(
    val bundle: String,
    val partnerId: String,
    val uid: String,
    val baseUrl: String, 
    val debugMode: Boolean,
    val headers: Map<String, () -> String>?
)
```

- `bundle` - (required) unique app identifier from the market.
- `partnerId` - (required) your personal identifier, issued by the SDK team
- `uid` - unique user ID. The SDK itself **does not** request standard platform user IDs.
- `baseUrl` - (required) addresses for sending requests,
- `debugMode` - (optional) debug mode,
- `headers` - (optional) dictionary that generates HTTP specific headers.

### Example

```kotlin
val options = Options(
    bundle = "com.example",
    partnerId = "1234",
    uid = "1234",
    baseUrl = "https://my-server.com/",
    debugMode = false, 
    headers = mapOf("Authorization" to {
        "Bearer Test"
    }
)
val tracker = Tracker.initialize(options)
```

## Trackable events

The Tracking SDK provides an interface for sending a specific list of events. To send events, you must pass the event object to the method `event()` of class `TechTracker`:

```kotlin
tracker.event(event)
```
Each time an event is dispatched, the Tracking SDK passes a set of parameters:

- `bundle` - passed to the API during initialization.
- `partnerId` - passed to the API during initialization.
- `uid` - passed to the API during initialization.
- `sdkVersion` - set in the SDK.
- `currentTime` - current time in milliseconds, requested through the standard interface of the platform.
- `page` - the name of the screen the user is on is passed to the event object (see below).
- `eventType` - event type.
- `event` - event object, see below.

#### AdImp

Displaying an advertising banner to the user.

```kotlin
val event = AdImp(
    placementId = “MainPage”,
    width = 210,
    height = 210,
    href = “https://site.com”,
    category = “category”,
    subcategory = “subcategory”
)
event.page = “MainPage”

tracker.event(event)
```

- `placementId` - placement id
- `width` - placement width
- `height` - placement height
- `href` - banner click link
- `category` - product category
- `subcategory` - product subcategory
- `page` - the name of the screen the user is on

#### Scroll

Scrolling content (for example, a list of products).


```kotlin
val event = Scroll(
    value = 0.4,
    category = “category”,
    subcategory = “subcategory”
)
event.page = “MainPage”

tracker.event(event)
```

- `category` - product category
- `subcategory` - product subcategory
- `value` - a value between 0 and 1 describing how much content was scrolled as a percentage
- `page` - the name of the screen the user is on

#### AdClick

Click on an advertising banner.


```kotlin
val event = AdClick(
    placementId = “MainPage”,
    width = 210,
    height = 210,
    href = “https://site.com”,
    category = “category”,
    subcategory = “subcategory”
)
event.page = “MainPage”

tracker.event(event)
```

- `placementId` - placement id
- `width` - placement width
- `height` - placement height
- `href` - banner click link
- `category` - product category
- `subcategory` - product subcategory
- `page` - the name of the screen the user is on

#### AddToCart

User click on the add to cart button.

```kotlin
val event = AddToCart()
event.add(id = "1",
        name = "Football",
        price = 5.99,
        currency = "RUB",
        category = "sport",
        subcategory = "boys")
event.page = “MainPage”

tracker.event(event)
```

- `id` - product id
- `name` - product name
- `currency` - currency
- `price` - product price
- `category` - product cateogry
- `subcategory` - product subcateogry
- `page` - the name of the screen the user is on

#### Purchase

User purchases goods in the system.


```kotlin
val event = CartPurchase()
event.add(id = "1",
    name = "Football",
    price = 5.99,
    currency = "RUB",
    category = "sport",
    subcategory = "boys")
event.page = “MainPage”

tracker.event(event)
```

- `id` - product id
- `name` - product name
- `currency` - currency
- `price` - product price
- `category` - product cateogry
- `subcategory` - product subcateogry
- `page` - the name of the screen the user is on

#### Search

Search (for example, search for a product).

```kotlin
val event = Search(value = “search something”)
event.page = “MainPage”

tracker.event(event)
```

- `value` - search phrase
- `page` - the name of the screen the user is on

#### StartView

The user starts viewing the product card.


```kotlin
val event = StartView(id = "1", name = "Football", category = "sport", subcategory = "boys")
event.page = “MainPage”

tracker.event(event)
```

- `id` - product id
- `name` - product name
- `category` - product cateogry
- `subcategory` - product subcateogry
- `page` - the name of the screen the user is on

#### StopView

User finished content viewing.

```kotlin
val event = StopView(id = "1",
                    name = "Football",
                    value = 0.3,
                    category = "sport",
                    subcategory = "boys")
event.page = “MainPage”

tracker.event(event)
```

- `id` - product id
- `name` - product name
- `category` - product cateogry
- `subcategory` - product subcateogry
- `page` - the name of the screen the user is on
- `value` - a value between 0 and 1 describing how much content was scrolled as a percentage

#### Viewing

The user continues viewing the content.

```kotlin
val event = Viewing(id = 1, name = "Football", value = 0.3, category = "sport", subcategory = "boys")
event.page = “MainPage”

tracker.event(event)
```

- `id` - product id
- `name` - product name
- `category` - product cateogry
- `subcategory` - product subcateogry
- `page` - the name of the screen the user is on
- `value` - a value between 0 and 1 describing how much content was scrolled as a percentage

#### Click

Click on a significant element in the interface ( links, buttons, product card in the product list, etc.).

```kotlin
val event = Click(value = "Click value")
event.page = “MainPage”

tracker.event(event)
```

- `value` - a string with a description of which element was clicked ( url for external links )
- `page` - the name of the screen the user is on