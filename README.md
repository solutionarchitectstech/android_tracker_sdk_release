# Documentation

Please see detailed documentation below or in the following link:

[https://docs.ad4tech.net/sdk-tracker-android.html](https://docs.ad4tech.net/sdk-tracker-android.html)

# Tracking Demo app

Go to [tracking-demo-app](tracking-demo-app/) folder, here in the repo.

# Tracking SDK Android

Tracking SDK is used to track targeted user actions in the mobile app and further analyze this information.

**Before you start**

You should be aware of the following options required to initialize and use the SDK. Ask the SDK team to give them to you.

- partnerId - partner ID. For example: `123`
- baseUrl - URL API of advertising system. For example: `"https://my.server.com/"`


**SDK version**: 0.1.0

**Demo**: [tracking-demo-app](tracking-demo-app/)

**Requirements**

- Kotlin version >= 1.6.20
- Android 5.0+ (API Level >= 21)

## Installation

```-vue
dependencies {
    //... other dependencies
    implementation 'com.github.solutionarchitectstech:android_tracker_sdk_release:0.1.0'
    
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
// REGULAR EVENT
val event = AdImp(
        placementId = "1234",
        width = 320,
        height = 240,
        href = "www.google.com",
        category = "category",
        subcategory = "subcategory"
)
event.page = "MainActivity"
tracker.event(event)

// THE SAME EVENT with customParams
val event = AdImp(
        placementId = "1234",
        width = 320,
        height = 240,
        href = "www.google.com",
        category = "category",
        subcategory = "subcategory",
        customParams = mapOf("sample1" to "value1")
)
event.page = "MainActivity"
tracker.event(event)
```

- `placementId` - placement id
- `width` - placement width
- `height` - placement height
- `href` - banner click link
- `category` - (optional) product category
- `subcategory` - (optional) product subcategory
- `page` - (optional) the name of the screen the user is on
- `customParams` - (optional) `Map<String, String>` custom parameters to add to tracking event

#### Scroll

Scrolling content (for example, a list of products).


```kotlin
// REGULAR EVENT
val event = Scroll(
        value = 0.4,
        category = "category",
        subcategory = "subcategory"
)
event.page = "MainActivity"
tracker.event(event)

// THE SAME EVENT with customParams
val event = Scroll(
        value = 0.4,
        category = "category",
        subcategory = "subcategory",
        customParams = mapOf("sample1" to "value1")
)
event.page = "MainActivity"
tracker.event(event)
```

- `category` - (optional) product category
- `subcategory` - (optional) product subcategory
- `value` - a value between 0 and 1 describing how much content was scrolled as a percentage
- `page` - (optional) the name of the screen the user is on
- `customParams` - (optional) `Map<String, String>` custom parameters to add to tracking event

#### AdClick

Click on an advertising banner.


```kotlin
// REGULAR EVENT
val event = AdClick(
        placementId = "1234",
        width = 320,
        height = 240,
        href = "www.google.com",
        category = "category",
        subcategory = "subcategory"
)
event.page = "MainActivity"
tracker.event(event)

// THE SAME EVENT with customParams
val event = AdClick(
        placementId = "1234",
        width = 320,
        height = 240,
        href = "www.google.com",
        category = "category",
        subcategory = "subcategory",
        customParams = mapOf("sample1" to "value1")
)
event.page = "MainActivity"
tracker.event(event)
```

- `placementId` - placement id
- `width` - placement width
- `height` - placement height
- `href` - banner click link
- `category` - (optional) product category
- `subcategory` - (optional) product subcategory
- `page` - (optional) the name of the screen the user is on
- `customParams` - (optional) `Map<String, String>` custom parameters to add to tracking event

#### AddToCart

User click on the add to cart button.

```kotlin
val event = AddToCart()
event.page = "MainActivity"

// REGULAR EVENT
event.add(AddToCartItem(
        id = "1",
        name = "box",
        price = 5.99,
        currency = "RUB",
        category = "category",
        subcategory = "subcategory",
))

// THE SAME EVENT with customParams
event.add(AddToCartItem(
        id = "2",
        name = "pizza",
        price = 399.99,
        currency = "RUB",
        category = "category",
        subcategory = "subcategory",
        quantity = 2.0F,
        customParams = mapOf("sample1" to "value1") 
))

tracker.event(event)
```

- `id` - product id
- `name` - product name
- `currency` - currency
- `price` - product price
- `category` - (optional) product cateogry
- `subcategory` - (optional) product subcateogry
- `page` - (optional) the name of the screen the user is on
- `customParams` - (optional) `Map<String, String>` custom parameters to add to tracking event
- `quantity` - (optional, default: 1.0) the quantity of the product
#### Purchase

User purchases goods in the system.


```kotlin
val event = Purchase()
event.page = "MainActivity"

// REGULAR EVENT
event.add(PurchaseItem(
        id = "1",
        name = "box",
        price = 5.99,
        currency = "RUB",
        category = "category",
        subcategory = "subcategory",
))

// THE SAME EVENT with customParams
event.add(PurchaseItem(
        id = "2",
        name = "pizza",
        price = 399.99,
        currency = "RUB",
        category = "category",
        subcategory = "subcategory",
        quantity = 2.0F,
        customParams = mapOf("sample1" to "value1")
))

tracker.event(event)
```

- `id` - product id
- `name` - product name
- `currency` - currency
- `price` - product price
- `category` - (optional) product cateogry
- `subcategory` - (optional) product subcateogry
- `page` - (optional) the name of the screen the user is on
- `customParams` - (optional) `Map<String, String>` custom parameters to add to tracking event
- `quantity` - (optional, default: 1.0) the quantity of the product

#### Search

Search (for example, search for a product).

```kotlin
// REGULAR EVENT
val event = Search(
        value = "search something"
)
event.page = "MainPage"
tracker.event(event)

// THE SAME EVENT with customParams
val event = Search(
        value = "search something",
        customParams = mapOf("sample1" to "value1")
)
event.page = "MainPage"
tracker.event(event)
```

- `value` - search phrase
- `page` - (optional) the name of the screen the user is on
- `customParams` - (optional) `Map<String, String>` custom parameters to add to tracking event

#### StartView

The user starts viewing the product card.


```kotlin
// REGULAR EVENT
val event = StartView(
        id = "1",
        name = "Football",
        category = "sport",
        subcategory = "boys"
)
event.page = "MainPage"
tracker.event(event)

// THE SAME EVENT with customParams
val event = StartView(
        id = "1",
        name = "Football",
        category = "sport",
        subcategory = "boys",
        customParams = mapOf("sample1" to "value1")
)
event.page = "MainPage"
tracker.event(event)
```

- `id` - product id
- `name` - product name
- `category` - (optional) product cateogry
- `subcategory` - (optional) product subcateogry
- `page` - (optional) the name of the screen the user is on
- `customParams` - (optional) `Map<String, String>` custom parameters to add to tracking event

#### StopView

User finished content viewing.

```kotlin
// REGULAR EVENT
val event = StopView(
        id = "1",
        name = "Football",
        value = 0.3,
        category = "sport",
        subcategory = "boys"
)
event.page = "MainPage"
tracker.event(event)

// THE SAME EVENT with customParams
val event = StopView(
        id = "1",
        name = "Football",
        value = 0.3,
        category = "sport",
        subcategory = "boys",
        customParams = mapOf("sample1" to "value1")
)
event.page = "MainPage"
tracker.event(event)
```

- `id` - product id
- `name` - product name
- `category` - (optional) product cateogry
- `subcategory` - (optional) product subcateogry
- `page` - (optional) the name of the screen the user is on
- `value` - a value between 0 and 1 describing how much content has been reviewed as a percentage
- `customParams` - (optional) `Map<String, String>` custom parameters to add to tracking event

#### Viewing

The user continues viewing the content.

```kotlin
// REGULAR EVENT
val event = Viewing(
        id = 1, 
        name = "Football", 
        value = 0.3, 
        category = "sport", 
        subcategory = "boys"
)
event.page = "MainPage"
tracker.event(event)

// THE SAME EVENT with customParams
val event = Viewing(
        id = 1,
        name = "Football",
        value = 0.3,
        category = "sport",
        subcategory = "boys",
        customParams = mapOf("sample1" to "value1")
)
event.page = "MainPage"
tracker.event(event)
```

- `id` - product id
- `name` - product name
- `category` - (optional) product cateogry
- `subcategory` - (optional) product subcateogry
- `page` - (optional) the name of the screen the user is on
- `value` - a value between 0 and 1 describing how much content has been reviewed as a percentage
- `customParams` - (optional) `Map<String, String>` custom parameters to add to tracking event

#### Click

Click on a significant element in the interface ( links, buttons, product card in the product list, etc.).

```kotlin
// REGULAR EVENT
val event = Click(
        value = "Click value"
)
event.page = "MainPage"
tracker.event(event)

// THE SAME EVENT with customParams
val event = Click(
        value = "Click value",
        customParams = mapOf("sample1" to "value1")
)
event.page = "MainPage"
tracker.event(event)
```

- `value` - a string with a description of which element was clicked ( url for external links )
- `page` - (optional) the name of the screen the user is on
- `customParams` - (optional) `Map<String, String>` custom parameters to add to tracking event