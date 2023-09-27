package com.example.artgallery

import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.artgallery.ui.theme.ArtGalleryTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ArtGalleryInstrumentedTests {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.artgallery", appContext.packageName)
    }

    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun seeAllMasterpiecesInOrder(){
        composeTestRule.setContent {
            ArtGalleryTheme {
                ArtGalleryLayout()
            }
        }
        composeTestRule.apply {
            repeat(5){
                onNodeWithText(collections[it].title)
                    .assertExists("No node with this text was found.")
                onNodeWithText(collections[it].author)
                    .assertExists("No node with this text was found.")
                onNodeWithText("Next").performClick()
            }
        }
    }

    @Test
    fun seeAllMasterpiecesInReverseOrder(){
        composeTestRule.setContent {
            ArtGalleryTheme {
                ArtGalleryLayout()
            }
        }
        composeTestRule.apply {
            for (i in intArrayOf(0, 4, 3, 2, 1)){
                onNodeWithText(collections[i].title)
                    .assertExists("No node with this text was found.")
                onNodeWithText(collections[i].author)
                    .assertExists("No node with this text was found.")
                onNodeWithText("Previous").performClick()
            }
        }
    }
}