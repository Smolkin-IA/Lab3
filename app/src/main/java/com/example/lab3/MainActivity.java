package com.example.lab3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Нажата кнопка 1",Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),"Нажата кнопка 2",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP,0,160);
                toast.show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Нажата кнопка 3")
                        .setIcon(R.drawable.test_icon)
                        .setMessage("Поменять цвет кнопок на красный?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                btn1.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this,R.color.red));
                                btn2.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this,R.color.red));
                                btn3.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this,R.color.red));
                                btn4.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this,R.color.red));
                                dialogInterface.cancel();
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"Окно закрыто",Toast.LENGTH_SHORT).show();
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] items = {"2", "4", "1"};
                int correctAnswerIndex = 1;
                int[] selectedAnswerIndex = {-1};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("2+2=?")
                        .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedAnswerIndex[0] = which;
                            }
                        })
                        .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                if (selectedAnswerIndex[0] == correctAnswerIndex) {
                                    Toast toast = Toast.makeText(getApplicationContext(),"Ответ верный",Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER,0,0);
                                    toast.show();

                                } else {
                                    btn1.setVisibility(View.INVISIBLE);
                                    btn2.setVisibility(View.INVISIBLE);
                                    btn3.setVisibility(View.INVISIBLE);
                                    btn4.setVisibility(View.INVISIBLE);
                                }
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Отмена", null);

                builder.create().show();
            }
        });
    }
}