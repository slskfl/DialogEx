package com.example.dialogex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnEnd, btnMsg, btnChoice, btnCalc;
    String pet[]={"강아지", "고양이", "원숭이"};
    ImageView imgPet;
    int petID[]={R.drawable.dog, R.drawable.cat, R.drawable.mon};
    int position;
    String product[]={"노트", "볼펜", "샤프"};
    boolean checkArray[]={false, false, false};
    TextView tvResult;
    String check[]={"선택", "취소"};
    int price[]={2000, 1000, 1500};
    int total, count;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnd=findViewById(R.id.btnEnd);
        btnMsg=findViewById(R.id.btnMsg);
        btnChoice=findViewById(R.id.btnChoice);
        imgPet=findViewById(R.id.imgPet);
        btnCalc=findViewById(R.id.btnCalc);
        tvResult=findViewById(R.id.tvResult);

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //대화상자 만들기
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("종료");
                builder.setIcon(R.drawable.minion);
                builder.setMessage("종료하시겠습니까?");
                //오른쪽 버튼
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("아니오", null);
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("전달사항");
                builder.setIcon(R.drawable.minion);
                builder.setMessage("안녕하세요.");
                builder.setPositiveButton("확인", null);
                //아무곳이나 클릭할 경우 대화상자 사라짐 >>true
                builder.setCancelable(false);
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
        btnChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("좋아하는 동물은?");
                //목록형태
               /* builder.setItems(pet, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // pet[which] 배열 항목
                        btnChoice.setText(pet[which]+"를 선택하셨습니다.");
                        imgPet.setImageResource(petID[which]);
                    }
                });*/
               //라디오버튼 목록형태
               builder.setSingleChoiceItems(pet, 0, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                        position=which;
                   }
               });
               builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       imgPet.setImageResource(petID[position]);
                   }
               });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("구입하고 싶은 물건을 모두 선택하시오.");
                builder.setIcon(R.drawable.minion);
                builder.setMultiChoiceItems(product, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        // 체크박스 선택
                        if(isChecked==true){
                            showToast(check[0]);
                        } else{
                            showToast(check[1]);
                        }
                    }
                });
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //확인 버튼 클릭 시 결과확인 나타내기
                        result="";
                        count=0;
                        total=0;
                        for(int i=0; i<product.length; i++){
                            if(checkArray[i]==true){
                                count++;
                                total+=price[i];
                                result+=product[i] + ":" + price[i] + "원\n";
                            }
                        }
                        if(count==0)
                            result="구입물품 없음.";
                        else
                            result+="총 구매가 : " + total +"원";
                        tvResult.setText(result);
                    }

                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    void showToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}