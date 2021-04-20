package com.example.timersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: TimerViewModel
    lateinit var countDown: TextView
    lateinit var startTimer: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countDown = findViewById(R.id.timer_text)
        startTimer = findViewById(R.id.start_timer)

        viewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        startTimer.isEnabled = viewModel.temp
        startTimer.setOnClickListener {
            viewModel.temp = false
            startTimer.isEnabled = viewModel.temp
            viewModel.startTimerFun()
        }

        viewModel.countDown.observe(this, Observer{ timerValue ->
            countDown.setText(timerValue)
            if ((timerValue.substring(3)).equals("00")){
                viewModel.temp = true
                startTimer.isEnabled = viewModel.temp
            }
        }
        )

    }

}