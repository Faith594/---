package com.example.faith.faith2;

import android.content.Intent;
import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{
    Button number0,number1,number2,number3,number4,number5,number6,number7,number8,number9,buttonPt;
    Button buttonMul,buttonDiv,buttonAdd,buttonSub;
    Button buttonAC,buttonDel,buttonResult;
    Button Goto;
    EditText TextInput;
    boolean TextEmpty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Goto=(Button) findViewById(R.id.buttonGoto);
        Goto.setOnClickListener(this);
        number0= (Button) findViewById(R.id.number0);         //实例化对象
        number0.setOnClickListener(this);                     //设置按钮的点击事件
        number1= (Button) findViewById(R.id.number1);
        number1.setOnClickListener(this);
        number2= (Button) findViewById(R.id.number2);
        number2.setOnClickListener(this);
        number3= (Button) findViewById(R.id.number3);
        number3.setOnClickListener(this);
        number4= (Button) findViewById(R.id.number4);
        number4.setOnClickListener(this);
        number5= (Button) findViewById(R.id.number5);
        number5.setOnClickListener(this);
        number6= (Button) findViewById(R.id.number6);
        number6.setOnClickListener(this);
        number7= (Button) findViewById(R.id.number7);
        number7.setOnClickListener(this);
        number8= (Button) findViewById(R.id.number8);
        number8.setOnClickListener(this);
        number9= (Button) findViewById(R.id.number9);
        number9.setOnClickListener(this);
        buttonPt= (Button) findViewById(R.id.buttonPt);
        buttonPt.setOnClickListener(this);
        buttonAdd= (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);
        buttonSub= (Button) findViewById(R.id.buttonSub);
        buttonSub.setOnClickListener(this);
        buttonMul= (Button) findViewById(R.id.buttonMul);
        buttonMul.setOnClickListener(this);
        buttonDiv= (Button) findViewById(R.id.buttonDiv);
        buttonDiv.setOnClickListener(this);
        buttonAC= (Button) findViewById(R.id.buttonAC);
        buttonAC.setOnClickListener(this);
        buttonDel= (Button) findViewById(R.id.buttonDel);
        buttonDel.setOnClickListener(this);
        buttonResult= (Button) findViewById(R.id.buttonResult);
        buttonResult.setOnClickListener(this);
        TextInput= (EditText) findViewById(R.id.TextInput);
    }

    @Override
    public void onClick(View v) {



        String str=TextInput.getText().toString();
        switch (v.getId()){
            case   R.id.number0:
            case   R.id.number1:
            case   R.id.number2:
            case   R.id.number3:
            case   R.id.number4:
            case   R.id.number5:
            case   R.id.number6:
            case   R.id.number7:
            case   R.id.number8:
            case   R.id.number9:
            case   R.id.buttonPt:
                if(TextEmpty){
                    TextEmpty=false;
                    str="";
                    TextInput.setText("");
                }
                TextInput.setText(str+((Button)v).getText());
                break;
            case R.id.buttonAdd:
            case R.id.buttonSub:
            case R.id.buttonMul:
            case R.id.buttonDiv:
                if(TextEmpty){
                    TextEmpty=false;
                    str="";
                    TextInput.setText("");
                }
                if(str.contains("+")||str.contains("-")||str.contains("×")||str.contains("÷")) {
                    str=str.substring(0,str.indexOf(" "));
                }
                TextInput.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.buttonAC:
                if(TextEmpty)
                    TextEmpty=false;
                str="";
                TextInput.setText("");
                break;
            case R.id.buttonDel: //判断是否为空，然后在进行删除
                if(TextEmpty){
                    TextEmpty=false;
                    str="";
                    TextInput.setText("");
                }
                else if(str!=null&&!str.equals("")){
                    TextInput.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.buttonResult: //单独运算最后结果
                getResult();
                break;
            case R.id.buttonGoto:
                Intent intent=
                        new Intent(MainActivity.this,AnotherActivity.class);
                startActivity(intent);
        }

    }

    private void getResult(){
        String exp=TextInput.getText().toString();
        if(exp==null||exp.equals("")) return ;
        //因为没有运算符所以不用运算
        if(!exp.contains(" ")){
            return ;
        }
        if(TextEmpty){
            TextEmpty=false;
            return;
        }
        TextEmpty=true;
        //截取运算符前面的字符串
        String s1=exp.substring(0,exp.indexOf(" "));
        //截取的运算符
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //截取运算符后面的字符串
        String s2=exp.substring(exp.indexOf(" ")+3);
        double cnt=0;
        if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d1+d2;
            }
            if(op.equals("-")){
                cnt=d1-d2;
            }
            if(op.equals("×")){
                cnt=d1*d2;
            }
            if(op.equals("÷")){
                if(d2==0) cnt=0;
                else cnt=d1/d2;
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")) {
                int res = (int) cnt;
                TextInput.setText(res+"");
            }else {
                TextInput.setText(cnt+"");}
        }
        //s1不为空但s2为空
        else if(!s1.equals("")&&s2.equals("")){
            double d1=Double.parseDouble(s1);
            if(op.equals("+")){
                cnt=d1;
            }
            if(op.equals("-")){
                cnt=d1;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s1.contains(".")) {
                int res = (int) cnt;
                TextInput.setText(res+"");
            }else {
                TextInput.setText(cnt+"");}
        }
        //s1是空但s2不是空
        else if(s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d2;
            }
            if(op.equals("-")){
                cnt=0-d2;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s2.contains(".")) {
                int res = (int) cnt;
                TextInput.setText(res+"");
            }else {
                TextInput.setText(cnt+"");}
        }
        else {
            TextInput.setText("");
        }

    }

}
