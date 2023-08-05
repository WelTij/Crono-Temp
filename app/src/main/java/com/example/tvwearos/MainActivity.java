package com.example.tvwearos;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;


public class MainActivity extends FragmentActivity {

    // Cronometro
    private TextView timerTextView;
    private Button startButton;
    private Button pauseButton;
    private Button resetButton;

    //Boton parcial del cronometro
    private Button parcialButton;
    private boolean isTimerRunning = false;
    private int seconds = 0;

    // Temporizador
    private TextView temporizadorTextView;
    private Button startTemporizadorButton;
    private Button pauseTemporizadorButton;
    private Button resetTemporizadorButton;

    //Boton parcial del temporizador
    private Button parcialTemporizadorButton;
    private EditText hoursEditText;
    private EditText minutesEditText;
    private EditText secondsEditText;

    private CountDownTimer countDownTimer;
    private boolean isTemporizadorRunning = false;

    private long totalTimeMillis = 0;
    private long remainingTimeMillis = 0;

    //Fragment Temporizador
    FrameLayout fragment_container_temporizador;
    TemporizadorFragment temporizadorFragment;
    FragmentTransaction ft_temporizador;

    // Fragment
    FrameLayout fragment_container;
    ParcialFragment parcialFragment;
    FragmentTransaction ft;

    // Handler
    private Handler handler;

    // Tiempo parcial del cronometro
    String tiempoParcial = "00:00:00";

    // Tiempo parcial del temporizador
    String tiempoTemporizador = "00:01:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cronometro
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);
        //Boton parcial del cronometro
        parcialButton = findViewById(R.id.parcialButton);
        // Fragment cronometro
        fragment_container = findViewById(R.id.fragment_container);

        // Temporizador
        temporizadorTextView = findViewById(R.id.temporizadorTextView);
        startTemporizadorButton = findViewById(R.id.startTemporizadorButton);
        pauseTemporizadorButton = findViewById(R.id.pauseTemporizadorButton);
        resetTemporizadorButton = findViewById(R.id.resetTemporizadorButton);
        hoursEditText = findViewById(R.id.hoursEditText);
        minutesEditText = findViewById(R.id.minutesEditText);
        secondsEditText = findViewById(R.id.secondsEditText);
        //Boton parcial del temporizador
        parcialTemporizadorButton = findViewById(R.id.parcialTemporizadorButton);
        // Fragment temporizador
        fragment_container_temporizador = findViewById(R.id.fragment_container_temporizador);

        // Temporizador
        startTemporizadorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTemporizador();
            }
        });
        pauseTemporizadorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTemporizador();
            }
        });
        resetTemporizadorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTemporizador();
            }
        });

        // Fragment cronometro
        parcialFragment = ParcialFragment.newInstance(1);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, parcialFragment, "Cronometro");
        ft.commit();

        // Fragment temporizador
        temporizadorFragment = temporizadorFragment.newInstance(1);
        ft_temporizador = getSupportFragmentManager().beginTransaction();
        ft_temporizador.replace(R.id.fragment_container_temporizador, temporizadorFragment, "Temporizador");
        ft_temporizador.commit();


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });

        // Fragment cronometro botton reset
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTimer();
                parcialFragment.resetParciales();
            }
        });

        // Fragment temporizador botton reset
        resetTemporizadorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTemporizador();
                temporizadorFragment.resetParciales();
            }
        });

        // Fragment cronometro botton parcial y validacion que el tiempo no sea 00:00:00
        parcialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerTextView.getText().toString().equals("00:01:00")) {
                    Toast.makeText(MainActivity.this, "El tiempo no puede ser 00:00:00", Toast.LENGTH_SHORT).show();
                } else {
                    tiempoParcial = timerTextView.getText().toString();
                    parcialFragment.agregarTiempoParcial(tiempoParcial);
                }
            }
        });
//        parcialButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tiempoParcial = timerTextView.getText().toString();
//                parcialFragment.agregarTiempoParcial(tiempoParcial);
//            }
//        });

        // Fragment temporizador botton parcial y validacion que el tiempo no sea 00:00:00
        parcialTemporizadorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temporizadorTextView.getText().toString().equals("00:00:00")) {
                    Toast.makeText(MainActivity.this, "El tiempo no puede ser 00:00:00", Toast.LENGTH_SHORT).show();
                } else {
                    tiempoTemporizador = temporizadorTextView.getText().toString();
                    temporizadorFragment.agregarTiempoTemporizador(tiempoTemporizador);
                }
            }
        });
//        parcialTemporizadorButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tiempoTemporizador = temporizadorTextView.getText().toString();
//                temporizadorFragment.agregarTiempoTemporizador(tiempoTemporizador);
//            }
//        });

        handler = new Handler();

    } //final onCreate

    private void startTimer() {
        if (!isTimerRunning) {
            isTimerRunning = true;
            startButton.setVisibility(View.GONE);
            pauseButton.setVisibility(View.VISIBLE);
            resetButton.setVisibility(View.VISIBLE);
            parcialButton.setVisibility(View.VISIBLE);
            handler.postDelayed(timerRunnable, 1000);
        }
    }

    private void pauseTimer() {
        if (isTimerRunning) {
            isTimerRunning = false;
            startButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.GONE);
            resetButton.setVisibility(View.GONE);

            handler.removeCallbacks(timerRunnable);
        }
    }

    private void resetTimer() {

        isTimerRunning = false;
        startButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.GONE);
        resetButton.setVisibility(View.GONE);
        parcialButton.setVisibility(View.GONE);
        seconds = 0;


        updateTimerTextView();
        handler.removeCallbacks(timerRunnable);
    }

    private final Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            seconds++;
            updateTimerTextView();
            handler.postDelayed(this, 1000);
        }
    };

    private void updateTimerTextView() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;

        String time = String.format("%02d:%02d:%02d", hours, minutes, secs);
        timerTextView.setText(time);
    }



    // Temporizador
    // botton start temporizador
    private void startTemporizador() {
        if (!isTemporizadorRunning) {
            String hoursStr = hoursEditText.getText().toString().trim();
            String minutesStr = minutesEditText.getText().toString().trim();
            String secondsStr = secondsEditText.getText().toString().trim();

            if (hoursStr.isEmpty() && minutesStr.isEmpty() && secondsStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, ingresa un valor ", Toast.LENGTH_SHORT).show();
                return;
            }

            int hours = hoursStr.isEmpty() ? 0 : Integer.parseInt(hoursStr);
            int minutes = minutesStr.isEmpty() ? 01 : Integer.parseInt(minutesStr);
            int seconds = secondsStr.isEmpty() ? 0 : Integer.parseInt(secondsStr);

            totalTimeMillis = (hours * 3600 + minutes * 60 + seconds) * 1000;
            remainingTimeMillis = totalTimeMillis;

            if (totalTimeMillis > 0) {
                isTemporizadorRunning = true;
                startTemporizadorButton.setVisibility(View.GONE);
                pauseTemporizadorButton.setVisibility(View.VISIBLE);
                resetTemporizadorButton.setVisibility(View.VISIBLE);
                parcialTemporizadorButton.setVisibility(View.VISIBLE);

                countDownTimer = new CountDownTimer(totalTimeMillis + 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        remainingTimeMillis = millisUntilFinished;
                        updateTemporizadorTextView();
                    }

                    @Override
                    public void onFinish() {
                        isTemporizadorRunning = false;
                        pauseTemporizadorButton.setVisibility(View.GONE);
                        parcialTemporizadorButton.setVisibility(View.GONE);
                        hoursEditText.setText("");
                        minutesEditText.setText("");
                        secondsEditText.setText("");
                        totalTimeMillis = 0;
                        remainingTimeMillis = 0;
                        Toast.makeText(MainActivity.this, "El temporizador ha finalizado", Toast.LENGTH_SHORT).show();
                        updateTemporizadorTextView();
                    }
                }.start();
            }
        }
    }

    private void pauseTemporizador() {
        if (isTemporizadorRunning) {
            countDownTimer.cancel();
            isTemporizadorRunning = false;
            startTemporizadorButton.setVisibility(View.VISIBLE);
            pauseTemporizadorButton.setVisibility(View.GONE);
            resetTemporizadorButton.setVisibility(View.GONE);
        }
    }

    private void resetTemporizador() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        isTemporizadorRunning = false;
        startTemporizadorButton.setVisibility(View.VISIBLE);
        pauseTemporizadorButton.setVisibility(View.GONE);
        resetTemporizadorButton.setVisibility(View.GONE);
        parcialTemporizadorButton.setVisibility(View.GONE);
        hoursEditText.setText("");
        minutesEditText.setText("");
        secondsEditText.setText("");
        totalTimeMillis = 0;
        remainingTimeMillis = 0;
        updateTemporizadorTextView();
    }

    private void updateTemporizadorTextView() {
        int hours = (int) (Math.abs(remainingTimeMillis) / 3600000);
        int minutes = (int) ((Math.abs(remainingTimeMillis) % 3600000) / 60000);
        int seconds = (int) ((Math.abs(remainingTimeMillis) % 60000) / 1000);

        String time = String.format(Locale.getDefault(), "%s%02d:%02d:%02d", (remainingTimeMillis < 0 ? "-" : ""), hours, minutes, seconds);

        temporizadorTextView.setText(time);
    }


}

