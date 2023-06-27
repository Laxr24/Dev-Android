package dbHandler;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.database.User;
import com.example.database.myDatabase;

import java.util.List;

public class myDb {

    LiveData<List<User>> data;
    Context context;
    public myDb(Context context) {
        this.context = context;
    }

    public void insertData(String name) {
        new insertData(name).start();
    }

    public void deleteAlldata(){
        new deleteAlldata().start();
    }


    public LiveData<List<User>> fetchAll(){
        new fetchAll().start();
        return data;
    }

    private class insertData extends Thread{

        private String name;
        public insertData(String name){
            this.name = name;
        }
        @Override
        public void run(){
                super.run();
                    User user = new User();
                    user.setName(name);
                    myDatabase.getInstance(context).userDao().insert(user);
                }


    }

    private class deleteAlldata extends Thread{
        @Override
        public void run(){
            myDatabase.getInstance(context).userDao().deleteAll();
        }
    }

    private class fetchAll extends Thread{
        @Override
        public void run(){
            super.run();
            data = myDatabase.getInstance(context).userDao().fetchAll();
        }
    }



}
