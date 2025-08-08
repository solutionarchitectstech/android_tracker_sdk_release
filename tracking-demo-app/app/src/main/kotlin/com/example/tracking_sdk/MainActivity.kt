/*
 * Copyright © Gusev Andrew, Emelianov Andrew, Spinov Dmitry [collectively referred as the Authors], 2017 - All Rights Reserved
 * [NOTICE: All information contained herein is, and remains the property of the Authors.](notice: All information contained herein is, and remains the property of the Authors.)
 * The intellectual and technical concepts contained herein are proprietary to the Authors
 * and may be covered by any existing patents of any country in the world, patents in
 * process, and are protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden unless prior
 * written permission is obtained from the Authors. Access to the source code contained herein is hereby forbidden
 * to anyone except persons (natural person, corporate or unincorporated body, whether or not having a separate
 * legal personality, and that person’s personal representatives, and successors)
 * the Authors have granted permission and who have executed Confidentiality and Non-disclosure agreements
 * explicitly covering such access.
 *
 * The copyright notice above does not provide evidence of any actual or intended publication or disclosure
 * of this source code, which in
 */

package com.example.tracking_sdk

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import tech.solutionarchitects.tracking_sdk.api.TechTracker
import tech.solutionarchitects.tracking_sdk.api.TrackerOptions
import tech.solutionarchitects.tracking_sdk.api.event
import tech.solutionarchitects.tracking_sdk.api.events.AdClick
import tech.solutionarchitects.tracking_sdk.api.events.AdImp
import tech.solutionarchitects.tracking_sdk.api.events.AdType
import tech.solutionarchitects.tracking_sdk.api.events.AddToCart
import tech.solutionarchitects.tracking_sdk.api.events.AddToCartItem
import tech.solutionarchitects.tracking_sdk.api.events.Category
import tech.solutionarchitects.tracking_sdk.api.events.Click
import tech.solutionarchitects.tracking_sdk.api.events.Purchase
import tech.solutionarchitects.tracking_sdk.api.events.PurchaseItem
import tech.solutionarchitects.tracking_sdk.api.events.SKU
import tech.solutionarchitects.tracking_sdk.api.events.Scroll
import tech.solutionarchitects.tracking_sdk.api.events.Search
import tech.solutionarchitects.tracking_sdk.api.events.StartView
import tech.solutionarchitects.tracking_sdk.api.events.StopView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val options = TrackerOptions(
            bundle = "my.company.example.app",
            partnerId = "YOUR_PARTNER_ID",
            sessionId = "YOUR_SESSION_ID",
            endpointUrl = "YOUR_ENDPOINT",
            debugMode = true,
            headers = mapOf("Authorization" to {
                "Bearer YOUR_AUTHORIZATION_TOKEN"
            }, "User-Agent" to {
                "YOUR_CUSTOM_USER_AGENT"
            })
        )
        val tracker = TechTracker.initialize(options = options)
        tracker.uid = "YOUR_UID"

        findViewById<Button>(R.id.button_add_to_cart).setOnClickListener {
            val event = AddToCart()
            event.add(
                AddToCartItem(
                    sku = SKU(
                        skuId = "1",
                        skuName = "Lego",
                        price = 35.0,
                        currency = "RUB"
                    ),
                    deltaQuantity = 1.0,
                    quantity = 2.0,
                    category = listOf(
                        Category(
                            categoryId = "1",
                            categoryName = "Category Name",
                            children = listOf(
                                Category(
                                    categoryId = "11",
                                    categoryName = "SubCategory Name"
                                )
                            )
                        )
                    )
                )
            )
            event.add(
                AddToCartItem(
                    sku = SKU(
                        skuId = "2",
                        skuName = "Ozone"
                    ),
                    deltaQuantity = 1.0,
                    quantity = 2.0
                )
            )

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_add_to_cart_custom_params).setOnClickListener {
            val event = AddToCart()
            event.add(
                AddToCartItem(
                    sku = SKU(
                        skuId = "1",
                        skuName = "Lego",
                        price = 35.0,
                        currency = "RUB"
                    ),
                    deltaQuantity = 1.0,
                    quantity = 2.0,
                    category = listOf(
                        Category(
                            categoryId = "1",
                            categoryName = "Category Name",
                            children = listOf(
                                Category(
                                    categoryId = "11",
                                    categoryName = "SubCategory Name"
                                )
                            )
                        )
                    ),
                    customParams = mapOf(
                        "custom_param1" to "value1",
                        "custom_param2" to "value2"
                    )
                )
            )
            event.add(
                AddToCartItem(
                    sku = SKU(
                        skuId = "2",
                        skuName = "Ozone"
                    ),
                    deltaQuantity = 1.0,
                    quantity = 2.0
                )
            )

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_purchase).setOnClickListener {
            val event = Purchase()
            event.add(
                PurchaseItem(
                    sku = SKU(
                        skuId = "1",
                        skuName = "Lego",
                        price = 35.0,
                        currency = "RUB"
                    ),
                    quantity = 2.0,
                    category = listOf(
                        Category(
                            categoryId = "1",
                            categoryName = "Category Name",
                            children = listOf(
                                Category(
                                    categoryId = "11",
                                    categoryName = "SubCategory Name"
                                )
                            )
                        )
                    ),
                )
            )
            event.add(
                PurchaseItem(
                    sku = SKU(
                        skuId = "2",
                        skuName = "Ozone"
                    ),
                    quantity = 2.0
                )
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_purchase_custom_params).setOnClickListener {
            val event = Purchase()
            event.add(
                PurchaseItem(
                    sku = SKU(
                        skuId = "1",
                        skuName = "Lego",
                        price = 35.0,
                        currency = "RUB"
                    ),
                    quantity = 2.0,
                    category = listOf(
                        Category(
                            categoryId = "1",
                            categoryName = "Category Name",
                            children = listOf(
                                Category(
                                    categoryId = "11",
                                    categoryName = "SubCategory Name"
                                )
                            )
                        )
                    ),
                    customParams = mapOf(
                        "custom_param1" to "value1",
                        "custom_param2" to "value2"
                    )
                )
            )
            event.add(
                PurchaseItem(
                    sku = SKU(
                        skuId = "2",
                        skuName = "Ozone"
                    ),
                    quantity = 2.0
                )
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_start_view).setOnClickListener {
            val event = StartView(
                contentId = "1",
                contentName = "Lego",
                sku = SKU(
                    skuId = "1",
                    skuName = "Lego",
                    price = 35.0,
                    currency = "RUB"
                ),
                category = listOf(
                    Category(
                        categoryId = "1",
                        categoryName = "Category Name",
                        children = listOf(
                            Category(
                                categoryId = "11",
                                categoryName = "SubCategory Name"
                            )
                        )
                    )
                ),
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_start_view_custom_params).setOnClickListener {
            val event = StartView(
                contentId = "1",
                contentName = "Lego",
                sku = SKU(
                    skuId = "1",
                    skuName = "Lego",
                    price = 35.0,
                    currency = "RUB"
                ),
                category = listOf(
                    Category(
                        categoryId = "1",
                        categoryName = "Category Name",
                        children = listOf(
                            Category(
                                categoryId = "11",
                                categoryName = "SubCategory Name"
                            )
                        )
                    )
                ),
                customParams = mapOf(
                    "custom_param1" to "value1",
                    "custom_param2" to "value2"
                )
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_stop_view).setOnClickListener {
            val event = StopView(
                contentId = "1",
                contentName = "Lego",
                value = 0.5,
                sku = SKU(
                    skuId = "1",
                    skuName = "Lego",
                    price = 35.0,
                    currency = "RUB"
                ),
                category = listOf(
                    Category(
                        categoryId = "1",
                        categoryName = "Category Name",
                        children = listOf(
                            Category(
                                categoryId = "11",
                                categoryName = "SubCategory Name"
                            )
                        )
                    )
                ),
            )
            tracker.event(event)

        }

        findViewById<Button>(R.id.button_stop_view_custom_params).setOnClickListener {
            val event = StopView(
                contentId = "1",
                contentName = "Lego",
                value = 0.5,
                sku = SKU(
                    skuId = "1",
                    skuName = "Lego",
                    price = 35.0,
                    currency = "RUB"
                ),
                category = listOf(
                    Category(
                        categoryId = "1",
                        categoryName = "Category Name",
                        children = listOf(
                            Category(
                                categoryId = "11",
                                categoryName = "SubCategory Name"
                            )
                        )
                    )
                ),
                customParams = mapOf(
                    "custom_param1" to "value1",
                    "custom_param2" to "value2"
                )
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_search).setOnClickListener {
            val event = Search(
                value = "Pampers",
                filter = mapOf(
                    "age" to listOf("0-1", "1-3"),
                    "sex" to listOf("m")
                ),
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_search_custom_params).setOnClickListener {
            val event = Search(
                value = "Pampers",
                filter = mapOf(
                    "age" to listOf("0-1", "1-3"),
                    "sex" to listOf("m")
                ),
                customParams = mapOf(
                    "custom_param1" to "value1",
                    "custom_param2" to "value2"
                )
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_ad_imp).setOnClickListener {
            val event = AdImp(
                placementId = "1",
                width = 240.0,
                height = 300.0,
                clickURL = "https://test.com",
                adType = AdType.banner,
                contentId = "1",
                contentName = "Lego",
                sku = SKU(
                    skuId = "1",
                    skuName = "Lego",
                    price = 35.0,
                    currency = "RUB"
                ),
                category = listOf(
                    Category(
                        categoryId = "1",
                        categoryName = "Category Name",
                        children = listOf(
                            Category(
                                categoryId = "11",
                                categoryName = "SubCategory Name"
                            )
                        )
                    )
                ),
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_ad_imp_custom_params).setOnClickListener {
            val event = AdImp(
                placementId = "1",
                width = 240.0,
                height = 300.0,
                clickURL = "https://test.com",
                adType = AdType.banner,
                contentId = "1",
                contentName = "Lego",
                sku = SKU(
                    skuId = "1",
                    skuName = "Lego",
                    price = 35.0,
                    currency = "RUB"
                ),
                category = listOf(
                    Category(
                        categoryId = "1",
                        categoryName = "Category Name",
                        children = listOf(
                            Category(
                                categoryId = "11",
                                categoryName = "SubCategory Name"
                            )
                        )
                    )
                ),
                customParams = mapOf(
                    "custom_param1" to "value1",
                    "custom_param2" to "value2"
                )
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_ad_click).setOnClickListener {
            val event = AdClick(
                placementId = "1",
                width = 240.0,
                height = 300.0,
                clickURL = "https://test.com",
                adType = AdType.banner,
                contentId = "1",
                contentName = "Lego",
                sku = SKU(
                    skuId = "1",
                    skuName = "Lego",
                    price = 35.0,
                    currency = "RUB"
                ),
                category = listOf(
                    Category(
                        categoryId = "1",
                        categoryName = "Category Name",
                        children = listOf(
                            Category(
                                categoryId = "11",
                                categoryName = "SubCategory Name"
                            )
                        )
                    )
                ),
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_ad_click_custom_params).setOnClickListener {
            val event = AdClick(
                placementId = "1",
                width = 240.0,
                height = 300.0,
                clickURL = "https://test.com",
                adType = AdType.banner,
                contentId = "1",
                contentName = "Lego",
                sku = SKU(
                    skuId = "1",
                    skuName = "Lego",
                    price = 35.0,
                    currency = "RUB"
                ),
                category = listOf(
                    Category(
                        categoryId = "1",
                        categoryName = "Category Name",
                        children = listOf(
                            Category(
                                categoryId = "11",
                                categoryName = "SubCategory Name"
                            )
                        )
                    )
                ),
                customParams = mapOf(
                    "custom_param1" to "value1",
                    "custom_param2" to "value2"
                )
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_click).setOnClickListener {
            val event = Click(
                value = "start registration",
                contentId = "1",
                contentName = "Lego",
                sku = SKU(
                    skuId = "1",
                    skuName = "Lego",
                    price = 35.0,
                    currency = "RUB"
                ),
                category = listOf(
                    Category(
                        categoryId = "1",
                        categoryName = "Category Name",
                        children = listOf(
                            Category(
                                categoryId = "11",
                                categoryName = "SubCategory Name"
                            )
                        )
                    )
                ),
            )
            tracker.event(event)

        }

        findViewById<Button>(R.id.button_click_custom_params).setOnClickListener {
            val event = Click(
                value = "start registration",
                contentId = "1",
                contentName = "Lego",
                sku = SKU(
                    skuId = "1",
                    skuName = "Lego",
                    price = 35.0,
                    currency = "RUB"
                ),
                category = listOf(
                    Category(
                        categoryId = "1",
                        categoryName = "Category Name",
                        children = listOf(
                            Category(
                                categoryId = "11",
                                categoryName = "SubCategory Name"
                            )
                        )
                    )
                ),
                customParams = mapOf(
                    "custom_param1" to "value1",
                    "custom_param2" to "value2"
                )
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_scroll).setOnClickListener {
            val event = Scroll(
                value = 0.6,
                contentId = "1",
                contentName = "Lego",
                sku = SKU(
                    skuId = "1",
                    skuName = "Lego",
                    price = 35.0,
                    currency = "RUB"
                ),
                category = listOf(
                    Category(
                        categoryId = "1",
                        categoryName = "Category Name",
                        children = listOf(
                            Category(
                                categoryId = "11",
                                categoryName = "SubCategory Name"
                            )
                        )
                    )
                ),
            )
            tracker.event(event)
        }

        findViewById<Button>(R.id.button_scroll_custom_params).setOnClickListener {
            val event = Scroll(
                value = 0.6,
                contentId = "1",
                contentName = "Lego",
                sku = SKU(
                    skuId = "1",
                    skuName = "Lego",
                    price = 35.0,
                    currency = "RUB"
                ),
                category = listOf(
                    Category(
                        categoryId = "1",
                        categoryName = "Category Name",
                        children = listOf(
                            Category(
                                categoryId = "11",
                                categoryName = "SubCategory Name"
                            )
                        )
                    )
                ),
                customParams = mapOf(
                    "custom_param1" to "value1",
                    "custom_param2" to "value2"
                )
            )
            tracker.event(event)
        }
    }
}
