package com.example.assesmentproject.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


class UsersViewModel(application: Application) : AndroidViewModel(application) {
    var mRepo = UserRepository(application)

    fun processeddorderlist() {
        mRepo.processeddorderlistdeatils()
    }


    fun observetopList(): MutableLiveData<ArrayList<Users>> {
        return mRepo.observeGetlist()
    }

    fun observeErrorprocessedlist(): MutableLiveData<String> {
        return mRepo.observeErrorprocessedlist()
    }

    fun observerSuccessprocessedlist(): MutableLiveData<String> {
        return mRepo.observerSuccessprocessedlist()
    }

}