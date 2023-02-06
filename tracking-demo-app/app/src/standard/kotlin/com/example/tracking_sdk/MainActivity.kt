/*
 * Copyright © Gusev Andrew, Emelianov Andrew, Spinov Dmitry [collectively referred as the Authors], 2017-2022 - All Rights Reserved
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
import tech.solutionarchitects.tracking_sdk.TechTracker
import tech.solutionarchitects.tracking_sdk.TrackerOptions
import tech.solutionarchitects.tracking_sdk.events.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val options = TrackerOptions(
            bundle = "com.example",
            partnerId = "1234",
            uid = "1234",
            endpointUrl = "https://my-server.com/",
            debugMode = true,
            headers = mapOf("Authorization" to {
                "Bearer Test"
            })
        )
        val tracker = TechTracker.initialize(options)

        findViewById<Button>(R.id.button_add_to_cart).setOnClickListener {
            val event = AddToCart()
            event.page = "MainActivity"

            event.add(AddToCartItem(
                id = "1",
                name = "box",
                price = 5.99,
                currency = "RUB",
                category = "category",
                subcategory = "subcategory",
            ))
            event.add(AddToCartItem(
                id = "2",
                name = "pizza",
                price = 399.99,
                currency = "RUB",
                category = "category",
                subcategory = "subcategory",
                quantity = 2.0F,
            ))

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_add_to_cart_custom_params).setOnClickListener {
            val event = AddToCart()
            event.page = "MainActivity"

            event.add(AddToCartItem(
                id = "1",
                name = "box",
                price = 5.99,
                currency = "RUB",
                category = "category",
                subcategory = "subcategory",
                customParams = mapOf("sample1" to "value1")
            ))
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
        }

        findViewById<Button>(R.id.button_purchase).setOnClickListener {
            val event = Purchase()
            event.page = "MainActivity"

            event.add(PurchaseItem(
                id = "1",
                name = "box",
                price = 5.99,
                currency = "RUB",
                category = "category",
                subcategory = "subcategory",
            ))
            event.add(PurchaseItem(
                id = "2",
                name = "pizza",
                price = 399.99,
                currency = "RUB",
                category = "category",
                subcategory = "subcategory",
                quantity = 2.0F,
            ))

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_purchase_custom_params).setOnClickListener {
            val event = Purchase()
            event.page = "MainActivity"

            event.add(PurchaseItem(
                id = "1",
                name = "box",
                price = 5.99,
                currency = "RUB",
                category = "category",
                subcategory = "subcategory",
                customParams = mapOf("sample1" to "value1")
            ))
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
        }

        findViewById<Button>(R.id.button_start_view).setOnClickListener {
            val event = StartView(
                id = "1",
                name = "Ads"
            )
            event.page = "MainActivity"

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_start_view_custom_params).setOnClickListener {
            val event = StartView(
                id = "1",
                name = "Ads",
                customParams = mapOf("sample1" to "value1")
            )
            event.page = "MainActivity"

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_stop_view).setOnClickListener {
            val event = StopView(
                id = "1",
                name = "Ads",
                value = 0.4,
                category = "category",
                subcategory = "subcategory"
            )
            event.page = "MainActivity"

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_stop_view_custom_params).setOnClickListener {
            val event = StopView(
                id = "1",
                name = "Ads",
                value = 0.4,
                category = "category",
                subcategory = "subcategory",
                customParams = mapOf("sample1" to "value1")
            )
            event.page = "MainActivity"

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_viewing).setOnClickListener {
            val event = Viewing(
                id = "1",
                name = "Ads",
                value = 0.4,
                category = "category",
                subcategory = "subcategory"
            )
            event.page = "MainActivity"

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_viewing_custom_params).setOnClickListener {
            val event = Viewing(
                id = "1",
                name = "Ads",
                value = 0.4,
                category = "category",
                subcategory = "subcategory",
                customParams = mapOf("sample1" to "value1")
            )
            event.page = "MainActivity"

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_search).setOnClickListener {
            val event = Search("Search Value")
            event.page = "MainActivity"

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_search_custom_params).setOnClickListener {
            val event = Search("Search Value", customParams = mapOf("sample1" to "value1"))
            event.page = "MainActivity"

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_ad_imp).setOnClickListener {
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
        }

        findViewById<Button>(R.id.button_ad_imp_custom_params).setOnClickListener {
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
        }

        findViewById<Button>(R.id.button_ad_click).setOnClickListener {
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
        }

        findViewById<Button>(R.id.button_ad_click_custom_params).setOnClickListener {
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
        }

        findViewById<Button>(R.id.button_click).setOnClickListener {
            val event = Click("Click Value")
            event.page = "MainActivity"

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_click_custom_params).setOnClickListener {
            val event = Click("Click Value", customParams = mapOf("sample1" to "value1"))
            event.page = "MainActivity"

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_scroll).setOnClickListener {
            val event = Scroll(
                value = 1.2,
                category = "category",
                subcategory = "subcategory",
            )
            event.page = "MainActivity"

            tracker.event(event)
        }

        findViewById<Button>(R.id.button_scroll_custom_params).setOnClickListener {
            val event = Scroll(
                value = 1.2,
                category = "category",
                subcategory = "subcategory",
                customParams = mapOf("sample1" to "value1")
            )
            event.page = "MainActivity"

            tracker.event(event)
        }
    }
}