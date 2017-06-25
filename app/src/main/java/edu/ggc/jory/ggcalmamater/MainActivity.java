package edu.ggc.jory.ggcalmamater;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private SeekBar progress;
    private int next;

    public final String TAG = "OMNI";

    public String file = "";
    TextView currentLyric;
    TextView nextLyric;
    TextView oldLyric;

    public String flat =  "\u266d";
    public String sharp = "\u266f";
    public String sub0 = "\u2080";
    public String sub1 = "\u2081";
    public String sub2 = "\u2082";
    public String sub3 = "\u2083";
    public String sub4 = "\u2084";
    public String sub5 = "\u2085";
    public String sub6 = "\u2086";
    public String sub7 = "\u2087";
    public String sub8 = "\u2088";

    public ArrayList<Note> notes = new ArrayList<>();
    {
        notes.add(new Note(16.35,"C" + sub0));
        notes.add(new Note (17.32,"C" + sharp + sub0 + "/D" + flat + sub0));
        notes.add(new Note(18.35,"D" + sub0));
        notes.add(new Note(19.45,"D" + sharp + sub0 + "/E" + flat + sub0));
        notes.add(new Note(20.60,"E" + sub0));
        notes.add(new Note(21.83,"F" + sub0));
        notes.add(new Note(23.12,"F" + sharp + sub0 + "/G" + flat + sub0));
        notes.add(new Note(24.50,"G" + sub0));
        notes.add(new Note(25.96,"G" + sharp + sub0 +"/A" + flat + sub0));
        notes.add(new Note(27.50,"A" + sub0));
        notes.add(new Note(29.14,"A" + sharp + "/B" + flat + sub0));
        notes.add(new Note(30.87,"B" + sub0));
        notes.add(new Note(32.70,"C" + sub1));
        notes.add(new Note(34.65,"C" +sharp + sub1 + "/D" + flat + sub1));
        notes.add(new Note(36.71,"D" + sub1));
        notes.add(new Note(38.89,"D" + sharp + sub1 + "/E" + flat + sub1));
        notes.add(new Note(41.20,"E" + sub1));
        notes.add(new Note(43.65,"F" + sub1));
        notes.add(new Note(46.25,"F" + sharp + sub1 + "/G" + flat + sub1));
        notes.add(new Note(49.00,"G" + sub1));
        notes.add(new Note(51.91,"G" + sharp + sub1 + "/A" + flat + sub1));
        notes.add(new Note(55.00,"A" + sub1));
        notes.add(new Note(58.27,"A" + sharp + sub1 + "/B" + flat + sub1));
        notes.add(new Note(61.74,"B" + sub1));
        notes.add(new Note(65.41,"C" + sub2));
        notes.add(new Note(69.30,"C" +sharp + sub2 + "/D" + flat + sub2));
        notes.add(new Note(73.42,"D" + sub2));
        notes.add(new Note(77.78,"D" + sharp + sub2 + "/E" + flat + sub2));
        notes.add(new Note(82.41,"E" + sub2));
        notes.add(new Note(87.31,"F" + sub2));
        notes.add(new Note(92.50,"F" + sharp + sub2 + "/G" + flat + sub2));
        notes.add(new Note(98.00,"G" + sub2));
        notes.add(new Note(103.83,"G" + sharp + sub2 + "/A" + flat + sub2));
        notes.add(new Note(110.00,"A" + sub2));
        notes.add(new Note(116.54,"A" + sharp + sub2 +"/B" + flat + sub2));
        notes.add(new Note(123.47,"B" + sub2));
        notes.add(new Note(130.81,"C" + sub3));
        notes.add(new Note(138.59,"C" +sharp + sub3 + "/D" + flat + sub3));
        notes.add(new Note(146.83,"D" + sub3));
        notes.add(new Note(155.56,"D" + sharp + sub3 + "/E" + flat + sub3));
        notes.add(new Note(164.81,"E" + sub3));
        notes.add(new Note(174.61,"F" + sub3));
        notes.add(new Note(185.00,"F" + sharp + sub3 + "/G" + flat + sub3));
        notes.add(new Note(196.00,"G" + sub3));
        notes.add(new Note(207.65,"G" + sharp + sub3 + "/A" + flat + sub3));
        notes.add(new Note(220.00,"A" + sub3));
        notes.add(new Note(233.08,"A" + sharp + sub3 + "/B" + flat + sub3));
        notes.add(new Note(246.94,"B" + sub3));
        notes.add(new Note(261.63,"C" + sub4));
        notes.add(new Note(277.18,"C" +sharp + sub4 + "/D" + flat + sub4));
        notes.add(new Note(293.66,"D" + sub4));
        notes.add(new Note(311.13,"D" + sharp + sub4 + "/E" + flat + sub4));
        notes.add(new Note(329.63,"E" + sub4));
        notes.add(new Note(349.23,"F" + sub4));
        notes.add(new Note(369.99,"F" + sharp + sub4 + "/G" + flat + sub4));
        notes.add(new Note(392.00,"G" + sub4));
        notes.add(new Note(415.30,"G" + sharp + sub4 + "/A" + flat + sub4));
        notes.add(new Note(440.00,"A" + sub4));
        notes.add(new Note(466.16,"A" + sharp + sub4 + "/B" + flat + sub4));
        notes.add(new Note(493.88,"B" + sub4));
        notes.add(new Note(523.25,"C" + sub5));
        notes.add(new Note(554.37,"C" +sharp + sub5 + "/D" + flat + sub5));
        notes.add(new Note(587.33,"D" + sub5));
        notes.add(new Note(622.25,"D" + sharp + sub5 + "/E" + flat + sub5));
        notes.add(new Note(659.25,"E" + sub5));
        notes.add(new Note(698.46,"F" + sub5));
        notes.add(new Note(739.99,"F" + sharp + sub5 + "/G" + flat + sub5));
        notes.add(new Note(783.99,"G" + sub5));
        notes.add(new Note(830.61,"G" + sharp + sub5 + "/A" + flat + sub5));
        notes.add(new Note(880.00,"A" + sub5));
        notes.add(new Note(932.33,"A" + sharp + sub5 + "/B" + flat + sub5));
        notes.add(new Note(987.77,"B" + sub5));
        notes.add(new Note(1046.50,"C" + sub6));
        notes.add(new Note(1108.73,"C" +sharp + sub6 + "/D" + flat + sub6));
        notes.add(new Note(1174.66,"D" + sub6));
        notes.add(new Note(1244.51,"D" + sharp + sub6 + "/E" + flat + sub6));
        notes.add(new Note(1318.51,"E" + sub6));
        notes.add(new Note(1396.91,"F" + sub6));
        notes.add(new Note(1479.98,"F" + sharp + sub6 + "/G" + flat + sub6));
        notes.add(new Note(1567.98,"G" + sub6));
        notes.add(new Note(1661.22,"G" + sharp + sub6 + "/A" + flat + sub6));
        notes.add(new Note(1760.00,"A" + sub6));
        notes.add(new Note(1864.66,"A" + sharp + sub6 + "/B" + flat + sub6));
        notes.add(new Note(1975.53,"B" + sub6));
        notes.add(new Note(2093.00,"C" + sub7));
        notes.add(new Note(2217.46,"C" +sharp + sub7 + "/D" + flat + sub7));
        notes.add(new Note(2349.32,"D" + sub7));
        notes.add(new Note(2489.02,"D" + sharp + sub7 + "/E" + flat + sub7));
        notes.add(new Note(2637.02,"E" + sub7));
        notes.add(new Note(2793.83,"F" + sub7));
        notes.add(new Note(2959.96,"F" + sharp + sub7 + "/G" + flat + sub7));
        notes.add(new Note(3135.96,"G" + sub7));
        notes.add(new Note(3322.44,"G" + sharp + sub7 + "/A" + flat + sub7));
        notes.add(new Note(3520.00,"A" + sub7));
        notes.add(new Note(3729.31,"A" + sharp + sub7 + "/B" + flat + sub7));
        notes.add(new Note(3951.07,"B" + sub7));
        notes.add(new Note(4186.01,"C" + sub8));
        notes.add(new Note(4434.92,"C" +sharp + sub8 + "/D" + flat + sub8));
        notes.add(new Note(4698.63,"D" + sub8));
        notes.add(new Note(4978.03,"D" + sharp + sub8 + "/E" + flat + sub8));
        notes.add(new Note(5274.04,"E" + sub8));
        notes.add(new Note(5587.65,"F" + sub8));
        notes.add(new Note(5919.91,"F" + sharp + sub8 + "/G" + flat + sub8));
        notes.add(new Note(6271.93,"G" + sub8));
        notes.add(new Note(6644.88,"G" + sharp + sub8 + "/A" + flat + sub8));
        notes.add(new Note(7040.00,"A" + sub8));
        notes.add(new Note(7458.62,"A" + sharp + sub8 + "/B" + flat + sub8));
        notes.add(new Note(7902.13,"B" + sub8));
    }

    private Lyric[] lyrics ={
            new Lyric( 3, "[introduction]"),
            new Lyric(10, "We have gained wisdom and honor"),
            new Lyric( 15, "From our home of green and grey"),
            new Lyric(18,  "We will go forth and remember"),
            new Lyric(23,  "All we've learned along the way"),
            new Lyric(27,  "And with knowledge and compassion"),
            new Lyric(30,  "We will build comunities"),
            new Lyric(36,  "Leading by example"),
            new Lyric(40,  "And with dignity"),
            new Lyric(44,  "Georgia Gwinnett, we'll never forget"),
            new Lyric(52, "How we've grown, and those that we met"),
            new Lyric(60,  "Georgia Gwinnett, with love and respect"),
            new Lyric(68,  "Our alma mater, Georgia Gwinnett"),
            new Lyric(76, "Our alma mater, Georgia Gwinnett")
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = MediaPlayer.create(this, R.raw.high);

        final TextView fileName = (TextView) findViewById(R.id.fileName);
        final TextView duration = (TextView) findViewById(R.id.totalDuration);
        final TextView current = (TextView) findViewById(R.id.current);
        View play = (Button) findViewById(R.id.play);
        View pause = (Button) findViewById(R.id.pause);
        View stop = (Button) findViewById(R.id.stop);
        progress = (SeekBar) findViewById(R.id.seekBar);
        currentLyric = (TextView) findViewById(R.id.currentLyric);
        nextLyric = (TextView) findViewById(R.id.nextLyric);
        oldLyric = (TextView) findViewById(R.id.oldLyric);


        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b) {
                    player.seekTo(i);
                    updateLyric(i/1000.0);

                }
                else
                    current.setText("" + i/1000.0);
                    updateLyric(i/1000.0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adjustMeida(spinner.getSelectedItem().toString());

                if(spinner.getSelectedItem().toString().equals("Hi Vocals")) {
                    fileName.setText("high.mp3");
                    progress.setMax(player.getDuration());
                    duration.setText("" + player.getDuration() / 1000.0);

                }
                else if(spinner.getSelectedItem().toString().equals("Low Vocals")) {
                    fileName.setText("low.mp3");
                    progress.setMax(player.getDuration());
                    duration.setText("" + player.getDuration() / 1000.0);

                }
                else if(spinner.getSelectedItem().toString().equals("Piano Only")) {
                    fileName.setText("piano.mp3");
                    progress.setMax(player.getDuration());
                    duration.setText("" + player.getDuration() / 1000.0);

                }
                else if(spinner.getSelectedItem().toString().equals("Vocals Only")) {
                    fileName.setText("vocals.mp3");
                    progress.setMax(player.getDuration());
                    duration.setText("" + player.getDuration() / 1000.0);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();


                ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
                service.scheduleWithFixedDelay(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        int x = player.getCurrentPosition();
                        progress.setProgress(x);
                    }
                }, 1, 1, TimeUnit.SECONDS);


            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()) {
                player.pause();}
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()) {
                    player.stop();
                    adjustMeida(spinner.getSelectedItem().toString());
                    currentLyric.setText("-");
                    nextLyric.setText("-");
                    oldLyric.setText("-");
                    next = 0;
                }
            }
        });



        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050,1024,0);
        dispatcher.addAudioProcessor(new PitchProcessor(PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, new PitchDetectionHandler() {

            @Override
            public void handlePitch(PitchDetectionResult pitchDetectionResult,
                                    AudioEvent audioEvent) {
                final float pitchInHz = pitchDetectionResult.getPitch();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView text = (TextView) findViewById(R.id.note);
                        text.setText(determineNote(pitchInHz));

                    }
                });

            }
        }));
        new Thread(dispatcher,"Audio Dispatcher").start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.start();
    }

    public void adjustMeida(String selected)
    {
        if(selected.equals("Hi Vocals")) {
            Log.i(TAG,"Hi Vocals Selected");
            player.reset();
            player = MediaPlayer.create(this, R.raw.high);
        }
        else if(selected.equals("Low Vocals")) {
            Log.i(TAG,"Low Vocals Selected");
            file = "low";
            player.reset();
            player = MediaPlayer.create(this, R.raw.low);
        }
        else if(selected.equals("Piano Only")) {
            Log.i(TAG,"Piano Only Selected");
            player.reset();
            player = MediaPlayer.create(this, R.raw.piano);
        }
        else if(selected.equals("Vocals Only")) {
            Log.i(TAG,"Vocals Only Selected");
            player.reset();
            player = MediaPlayer.create(this, R.raw.vocals);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.about) {
            startActivity(new Intent(this, about.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String determineNote(float pitch){
        String finalNote = "";
        if(pitch == -1.0) {
            finalNote = "-";
        }
        else {
            for (Note note : notes) {
                if (note.getFrequency() < pitch) {
                    finalNote = note.getNoteString();
                }
            }
        }
        return finalNote;

    }

    public void updateLyric(double position) {

        for(int i = 0; i < lyrics.length; i++) {
            if(Math.floor(position) == lyrics[i].getTime())
            {
                currentLyric.setText(lyrics[i].getLyric());
                if(i > 0) {
                    oldLyric.setText(lyrics[i -1].getLyric());
                }
                if(i < 13) {
                    nextLyric.setText(lyrics[i +1].getLyric());
                }
            }
        }
    }


}
