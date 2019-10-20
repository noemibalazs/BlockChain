package com.example.blockchain

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.blockchain.ui.MainActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.blockchain.adapter.EntityAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentationTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun onRvClick() {
        onView(withId(R.id.currencyRV)).perform(
            RecyclerViewActions.actionOnItemAtPosition<EntityAdapter.EntityVH>(
                1,
                 click()
            )
        )
    }

}