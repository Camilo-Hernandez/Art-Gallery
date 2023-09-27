package com.example.artgallery

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ArtGalleryUnitTests {
    @Test
    fun `test onNextClicked and onPreviousClicked increases and decreases by 1, respectively`() {
        assertEquals(0, onPreviousClicked(1))
        assertEquals(1, onNextClicked(0))
        assertEquals(4, onPreviousClicked(0))
        assertEquals(0, onNextClicked(4))
    }

    @Test
    fun `test collection list is type of List of Masterpiece `() {
        assertIs<List<Masterpiece>>(collections)
    }
}