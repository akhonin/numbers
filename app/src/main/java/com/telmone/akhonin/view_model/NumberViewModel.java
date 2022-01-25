package com.telmone.akhonin.view_model;

import android.app.Application;
import android.text.Editable;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.telmone.akhonin.model.NumberResponse;
import com.telmone.akhonin.repository.Repository;

import java.util.Date;
import java.util.Random;


public class NumberViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<NumberResponse> numberLiveData;
    private MutableLiveData<String> inputValue = new MutableLiveData<>();

    public NumberViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public LiveData<NumberResponse> getNumberInfo(String number, String type){
        if(type.equals("date")) {
            if(number!=null) {
                Integer day = Integer.parseInt(number.split("\\.")[0]);
                Integer month = Integer.parseInt(number.split("\\.")[1]);
                this.numberLiveData = repository.getDate(day, month);
            }
        }else{
            this.numberLiveData = repository.getNumbers(number, type);
        }


        return this.numberLiveData;
    }

    public void selectedItem(String page,Editable textEdit){
        String oldValue = String.valueOf(textEdit);
        String value;
        if(!page.equals("date")){
            if(oldValue.contains(".")){
                value = oldValue.split("\\.")[0];
            }else {
                value = String.valueOf(new Random().nextInt((1000) + 1));
            }
        }else{
            value  = String.valueOf(DateFormat.format("dd.MM", new Date()));
        }
        inputValue.postValue(value);
    }

    public MutableLiveData<String> getRandom(){
        return inputValue;
    }
}
