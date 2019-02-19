package com.example.android.news

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewPager: ViewPager
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout

    private val iconIds = intArrayOf(R.mipmap.ic_ho, R.mipmap.ic_spo, R.mipmap.ic_mu, R.mipmap.ic_mov, R.mipmap.ic_po, R.mipmap.ic_tec, R.mipmap.ic_bu)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewpager)
        tabLayout = findViewById(R.id.sliding_tabs)
        for (i in 0..6) {
            tabLayout!!.addTab(tabLayout!!.newTab().setIcon(iconIds[i]))
        }
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout_id)
        val toggle_btn = ActionBarDrawerToggle(this@MainActivity, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle_btn)
        toggle_btn.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view_id)
        assert(navigationView != null)
        navigationView!!.setNavigationItemSelectedListener(this)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager!!.adapter = viewPagerAdapter
        //change Tab selection when swipe ViewPager
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        //change ViewPager page when tab selected
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        if (savedInstanceState == null) {
            navigationView.menu.performIdentifierAction(R.id.home, 0)
        }

    }

    override fun onBackPressed() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout_id)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.home -> {
                viewPager!!.currentItem = 0
                supportActionBar!!.setTitle(R.string.home)
            }
            R.id.sport -> {
                viewPager!!.currentItem = 1
                supportActionBar!!.setTitle(R.string.sport)
            }
            R.id.music -> {
                viewPager!!.currentItem = 2
                supportActionBar!!.setTitle(R.string.music)
            }
            R.id.film -> {
                viewPager!!.currentItem = 3
                supportActionBar!!.setTitle(R.string.film)
            }
            R.id.politics -> {
                viewPager!!.currentItem = 4
                supportActionBar!!.setTitle(R.string.politics)
            }
            R.id.tech -> {
                viewPager!!.currentItem = 5
                supportActionBar!!.setTitle(R.string.tech)
            }
            R.id.business -> {
                viewPager!!.currentItem = 6
                supportActionBar!!.setTitle(R.string.business)
            }
        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout_id)
        item.isChecked = true
        assert(drawer != null)
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }

}
