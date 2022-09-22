package com.gx.accountbooks

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.gx.base.base.BaseAppCompatActivity

import com.sn.libaray.log.LogUtils
import com.sn.libaray.log.logger
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.*
import java.io.IOException


@AndroidEntryPoint
class HomeActivity : BaseAppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun initData() {
        var url = "http://192.168.1.24:8080/";
        var okHttpClient = OkHttpClient.Builder().build()
        var request = Request.Builder().url(url).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
               // LogUtil.d(TAG, response!!.body()!!.string().toString())
            }

            override fun onFailure(call: Call?, e: IOException?) {
               // LogUtil.d(TAG, e!!.message)
            }
        })
        testKotlin()
    }

    fun testKotlin() {
        var test = "aaa"
        println(T::x) // property x
        LogUtils.v(T::x)
        LogUtils.v(test)
        LogUtils.v(xd)
//        LogUtil.d(xd)
        val kClass = T::class
//        println(kClass.java) // 打印：class com.gx.accountbooks.HomeActivity$T
//        println(T(10).javaClass.) //class com.gx.accountbooks.HomeActivity$T
//        println(D().javaClass) //class com.gx.accountbooks.HomeActivity$T
//        println(T(10).javaClass.kotlin) //class com.gx.accountbooks.HomeActivity$T (Kotlin reflection is not available)
//        println(String::class.java) // class java.lang.String
//        val message = test.javaClass
//        println(message) // class java.lang.String
//        val kProperty0 = test::javaClass
//        println(kProperty0.get()) // class java.lang.String
//        println(String::class.java) // class java.lang.String
//        println(String.javaClass) //class kotlin.jvm.internal.StringCompanionObject
//        println(Double::class.java) //class kotlin.jvm.internal.StringCompanionObject

    }

    class T(val x: Int)


    /**
     * 另外一个类
     */
    class Test {
        fun test(): Boolean {
            print("测试：：")
            return false
        }
    }


    override fun initView() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // 为导航组件配置NavigationUI
//        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.menu_home,
                R.id.menu_account,
                R.id.menu_diary,
                R.id.menu_task,
                R.id.menu_setting
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        // 动态修改左上角title
        //TODO : BUG，如果监听放在set之前，监听无效
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.e("navController", destination.label.toString());
            Log.e("navController", destination.toString());
        }

    }

    override fun getLayoutView(): View = createView(R.layout.activity_main)

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    var xd =
        "{'students':[{'stu_id':'1001','stu_name':'十一郎'},{'stu_id':'1002','stu_name':'十二郎'}],'flag':'1','teacher':{'tea_id':'2001','tea_name':'晓春'}}";


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        LogUtil.d(TAG, "onOptionsItemSelected ${item.itemId}")

        when (item.itemId) {
            R.id.action_chart -> {
                logger.debug(String::class.java.toString())
//                logger.debug(T::x.Get)
                logger.debug(String.javaClass.toString())
//                LogUtils.verbose("AAAAAAA")
//                logger.debug(TAG)
//                logger.verbose(TAG)
//                XLogService().verbose(TAG)
//                logger.warn(TAG)
//                logger.info(TAG)
//                logger.error(JsonLoggerLayout().formatDataFromJson(xd)!!)
//                var startTime = System.currentTimeMillis()
//                for (name in 0..10000) {
//                    logger.debug(TAG + "onOptionsItemSelected name $name")
//                }
//                var end = System.currentTimeMillis()
//                logger.error(TAG + "onOptionsItemSelected time ${end - startTime}")
            }
            R.id.action_favorite -> {
//                var startTime = System.currentTimeMillis()
//                for (name in 0..10000) {
//                    Log.d(TAG, "onOptionsItemSelected name $name")
//                }
//                var end = System.currentTimeMillis()
//                logger.error(TAG + "onOptionsItemSelected time ${end - startTime}")
            }
            R.id.action_test -> {
//                var startTime = System.currentTimeMillis()
//                for (name in 0..10000) {
//                    LogUtil.d(TAG, "onOptionsItemSelected name $name")
//                }
//                var end = System.currentTimeMillis()
//                logger.error(TAG + "onOptionsItemSelected time ${end - startTime}")
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun isHome(): Boolean {
        return true
    }
}


