package com.example.android.news

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import fragments.BusinessFragment
import fragments.FilmFragment
import fragments.HomeFragment
import fragments.MusicFragment
import fragments.PoliticsFragment
import fragments.SportFragment
import fragments.TechnologyFragment

/**
 * Created by HP on 2/7/2019.
 */

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            HOME -> return HomeFragment()
            SPORT -> return SportFragment()
            MUSIC -> return MusicFragment()
            FILM -> return FilmFragment()
            POLITICS -> return PoliticsFragment()
            TECHNOLOGY -> return TechnologyFragment()
            BUSINESS -> return BusinessFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 7
    }

    companion object {
        private val HOME = 0
        private val SPORT = 1
        private val MUSIC = 2
        private val FILM = 3
        private val POLITICS = 4
        private val TECHNOLOGY = 5
        private val BUSINESS = 6
    }

}


