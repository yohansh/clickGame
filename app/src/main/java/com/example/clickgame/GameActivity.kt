package com.example.clickgame

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlin.properties.Delegates
import kotlin.system.measureTimeMillis

class GameActivity : AppCompatActivity() {

    lateinit var tv_score: TextView
    lateinit var btn1: ImageButton
    lateinit var btn2: ImageButton
    lateinit var btn3: ImageButton
    lateinit var btn4: ImageButton
    lateinit var timer: CountDownTimer
    lateinit var temp: ImageButton

    var countinterva=1400
    var counttime=1400
    var flag= 0
    var realBtn by Delegates.notNull<Int>()
    var current=0
    var score=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


         btn1=findViewById(R.id.imageButton2)
         btn2=findViewById(R.id.imageButton3)
         btn3=findViewById(R.id.imageButton5)
         btn4=findViewById(R.id.imageButton6)
         tv_score=findViewById(R.id.tv_score)

         current=0
         val list= mutableListOf<ImageButton>(btn1,btn2,btn3,btn4)
         temp=btn1
         realBtn=R.id.imageButton2


        timer=object : CountDownTimer(counttime.toLong(),countinterva.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                greyColour()
            }

            override fun onFinish() {

                btn1.setImageResource(R.drawable.blue)
                btn2.setImageResource(R.drawable.yello)
                btn3.setImageResource(R.drawable.green)
                btn4.setImageResource(R.drawable.orange1)

                if((current==score+1) or (flag==1))
                {
                    flag=1
                    finalScore()
                    return
                }
                timer.start()
            }

            //change color to grey
            fun greyColour()
            {


                var temp2=temp

                list.remove(temp)
                temp = list.random()
                list.add(temp2)


                temp.setImageResource(R.drawable.grey)

                current++

                val t=measureTimeMillis {
                       GlobalScope.async { check(btn1, temp) }
                       GlobalScope.async { check(btn2, temp) }
                       GlobalScope.async { check(btn3, temp) }
                       GlobalScope.async { check(btn4, temp) }
                   }



            }

            //check if correct button is clicked or not
            fun check(btn:ImageButton,changebtn:ImageButton)
            {

                btn.setOnClickListener()
                {
                    if (changebtn == btn) {
                        ++score
                        tv_score.text="Score: $score"

                    } else {
                        flag = 1
                    }
                }
            }
        }

        timer.start()



    }



    private fun finalScore() {

        val mDialogView= LayoutInflater.from(this).inflate(R.layout.popup,null)

        val mBuilder= AlertDialog.Builder(this).setView(mDialogView)

        val mAlertDialog= mBuilder.show()

        val reset_btn:Button=mDialogView.findViewById(R.id.btn_reset)
        val tv_scor: TextView=mDialogView.findViewById(R.id.tv_score)

        tv_scor.setText("Total Score: $score")

        reset_btn.setOnClickListener()
        {
            counttime=1400
            countinterva=1400
            score=0
            current=0
            flag=0
            temp=btn1
            tv_score.setText("Total Score: $score")

            timer.start()
            mAlertDialog.dismiss()
        }
    }


}





