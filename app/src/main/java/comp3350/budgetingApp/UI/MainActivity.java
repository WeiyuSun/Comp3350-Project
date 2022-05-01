package comp3350.budgetingApp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.budgetingApp.Application.Main;
import comp3350.budgetingApp.Application.Services;
import comp3350.budgetingApp.R;
import comp3350.budgetingApp.controller.AccessUsers;
import comp3350.budgetingApp.objects.User;
import comp3350.budgetingApp.persistence.hsqldb.GeneralPersistence;

public class MainActivity extends AppCompatActivity {
    public AccessUsers accessUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addDBtoDevice();
        GeneralPersistence gp = Services.getGeneralPersistence();
        gp.createTable();
        User user = new User("w", 24, "w", "s", "1234567890", "weiyu@gmail.com", "a", "a");
        accessUsers = new AccessUsers();
        AccessUsers.user = null;
        if(accessUsers.getUser("w") == null) {accessUsers.addUser(user);}
        else{ System.out.println("Already Exist");}
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(login);
    }

    private void addDBtoDevice() {
        String path = "db";
        Context context = getApplicationContext();
        File dataDR = context.getDir(path, Context.MODE_PRIVATE);

        String[] assetNames;
        AssetManager assetManager = getAssets();

        try {
            assetNames = assetManager.list(path);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = path + "/" + assetNames[i];
            }
            copyAssetsToDirectory(assetNames, dataDR);
            Main.setDatabasePath(dataDR.toString() + "/" + Main.getDatabaseName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyAssetsToDirectory(String [] assets, File dR) throws IOException{
        AssetManager assetManager = getAssets();
        for (String asset : assets) {
            String[] components = asset.split("/");
            int componentSize = components.length - 1;
            String addPath = dR.toString() + "/" + components[componentSize];

            int number = 0;
            char[] line = new char[1024];
            File fp = new File(addPath);

            if(!fp.exists()) {
                InputStreamReader read = new InputStreamReader(assetManager.open(asset));
                FileWriter write = new FileWriter(fp);

                number = read.read(line);
                while(number != -1) {
                    write.write(line, 0, number);
                    number = read.read(line);
                }

                write.close();
                read.close();
            }
        }
    }
}