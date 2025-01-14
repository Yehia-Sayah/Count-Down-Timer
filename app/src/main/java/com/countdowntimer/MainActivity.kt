package com.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.ResourceCursorTreeAdapter
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val START_TIME_IN_MILI : Long = 45 * 60 * 1000
    var remainingTime      : Long = START_TIME_IN_MILI
    val count_down : Long  = 1 * 1000
    var myTimer    : CountDownTimer? = null
    var isruningTimer = false
    lateinit var start_btn : Button
    lateinit var reset_tv  : TextView
    lateinit var title_tv  : TextView
    lateinit var timer_tv  : TextView
    lateinit var pb        : ProgressBar





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initial_view()
        TimerText()

        start_btn.setOnClickListener {

       if (isruningTimer == false){
           startTimer()
           title_tv.setText(R.string.keep_going)
       }
        }


        reset_tv.setOnClickListener{
            resetTimer()
           updateTimerText()
        }














    }

fun initial_view(){
    start_btn = findViewById(R.id.start_btn)
    reset_tv = findViewById(R.id.reset_tv)
    title_tv  = findViewById(R.id.title_tv)
    timer_tv  = findViewById(R.id.timer_tv)
    pb = findViewById(R.id.progressBar)
}

    fun startTimer(){
       myTimer = object : CountDownTimer( START_TIME_IN_MILI,count_down){

            override fun onTick(timeLeft: Long) {
                remainingTime = timeLeft
                updateTimerText()
                pb.progress = remainingTime.toDouble().div(START_TIME_IN_MILI.toDouble()).times(100).toInt()


            }

            override fun onFinish() {

                Toast.makeText(this@MainActivity,"Finsh",Toast.LENGTH_SHORT).show()
                isruningTimer = false

            }

        }.start()
        isruningTimer = true
    }

    fun resetTimer(){
        myTimer?.cancel()
        remainingTime = START_TIME_IN_MILI
        updateTimerText()
        title_tv.setText(R.string.tak_a_Pomodoro)
        isruningTimer = false
        pb.progress = 100
    }

    private fun updateTimerText(){
        val minute = remainingTime.div(1000).div(60)
        val seconde = remainingTime.div(1000) % 60
        val formattedTime = String.format("%02d:%02d",minute,seconde)
        timer_tv.setText(formattedTime)
    }
    private fun TimerText(){
        val minute = START_TIME_IN_MILI.div(1000).div(60)
        val seconde = START_TIME_IN_MILI.div(1000) % 60
        val formattedTime = String.format("%02d:%02d",minute,seconde)
        timer_tv.setText(formattedTime)
    }

}