package com.zg.burgerjoint.instrumentationtests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.persistence.BurgerJointDatabase
import com.zg.burgerjoint.persistence.daos.BurgerDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DatabaseTest {

    private lateinit var burgerDao: BurgerDao
    private lateinit var db: BurgerJointDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context,
            BurgerJointDatabase::class.java
        ).build()

        burgerDao = db.getBurgerDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertIntoDatabaseTest() {
        val burger = BurgerVO()
        burger.burgerId = 1
        burger.burgerName = "Chicken Burger"
        burger.burgerImageUrl =
            "https://www.kimscravings.com/wp-content/uploads/2022/06/Chicken-Burgers-5-640x960.jpg"
        burger.burgerDescription =
            "It’s not summer without juicy burgers piled high with your favorite toppings! Am I right? And these chicken burgers are going to be your new favorite! They are easy to make with everyday ingredients and they taste just as good as a beefy hamburger! You only need about 10 minutes prep time and 10 minutes cook time, so you can have dinner ready in 20 minutes, talk about winning the dinner game!"

        burgerDao.insert(burger)
        assert(burgerDao.findBurgerById(burger.burgerId).value?.burgerId == burger.burgerId)
    }
}