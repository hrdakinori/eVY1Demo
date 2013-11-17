package com.hrdapp.android.evy1demo;



import com.hrdapp.android.evy1demo.R;

import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;
import android.util.Log;
import android.widget.Toast;

import jp.kshoji.driver.midi.activity.AbstractSingleMidiActivity;
import jp.kshoji.driver.midi.device.MidiInputDevice;
import jp.kshoji.driver.midi.device.MidiOutputDevice;

public class MainActivity extends AbstractSingleMidiActivity {

//                        0      1      2      3      4
	String paData[][] = {{"a",   "i",   "u",   "e",   "o"  },	// 0
						 {"k a", "k' i","k M", "k e", "k o"},	// 1
						 {"s a", "S i", "s M", "s e", "s o"},	// 2
						 {"t a", "tS i","ts M","t e", "t o"},	// 3
						 {"n a", "J i", "n M", "n e", "n o"},	// 4
						 {"h a", "C i", "p\\ M", "h e", "h o"},	// 5
						 {"m a", "m' i","m M", "m e", "m o"},	// 6
						 {"j a", "i", "j M", "e", "j o"},		// 7
						 {"4 a", "4' i","4 M", "4 e", "4 o"},	// 8
						 {"w a", "w i", "M", "w e", "o"},		// 9
						 {"g a", "g' i", "g M", "g e", "g o"},	// 10
						 {"dz a", "dZ i", "dz M", "dz e", "dz o"},	// 11
						 {"d a", "dZ i", "dz M", "d e", "d o"},	// 12
						 {"b a", "b' i", "b M", "b e", "b o"},	// 13
						 {"p a", "p' i", "p M", "p e", "p o"},	// 14
	};

	int subpos = 0;
	boolean append = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		OnTouchListener onButtonTouchListener = new OnTouchListener() {

			/*
			 * (non-Javadoc)
			 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
			 */
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				MidiOutputDevice midiOutputDevice = getMidiOutputDevice();
				if (midiOutputDevice == null) {
					return false;
				}
				int pos = Integer.parseInt((String) v.getTag());
				if(pos >= 1000)
				{
					int note = 60 + (pos - 1000);
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						midiOutputDevice.sendMidiNoteOn(0, 0, note, 127);
						break;
					case MotionEvent.ACTION_UP:
						midiOutputDevice.sendMidiNoteOff(0, 0, note, 127);
						break;
					default:
						// do nothing.
						break;
					}
				}else if(pos >= 1 && pos < 1000){
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						if(pos >= 1 && pos < 10){
							byte roma[] = CnvKnaPa(subpos, pos); 
							sendVoice(midiOutputDevice, roma);
							subpos = 0;
						}else{
							subpos = pos / 10;
						}
			    		break;
					case MotionEvent.ACTION_UP:
						break;
					default:
						// do nothing.
						break;
					}
				}
				return false;
			}
		};
		((Button) findViewById(R.id.button1)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button2)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button3)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button4)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button5)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button6)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button7)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button8)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button9)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button10)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button11)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button12)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button13)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button14)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button15)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button16)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button17)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button18)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button19)).setOnTouchListener(onButtonTouchListener);

		((Button) findViewById(R.id.button20)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button21)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button22)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button23)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button24)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button25)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button26)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button27)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button28)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button29)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button30)).setOnTouchListener(onButtonTouchListener);
		((Button) findViewById(R.id.button31)).setOnTouchListener(onButtonTouchListener);
		
		OnCheckedChangeListener onButtonListener = new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				append = isChecked;
			}
		};

		ToggleButton tb = (ToggleButton)findViewById(R.id.toggleButton1);
		tb.setOnCheckedChangeListener(onButtonListener);
		tb.setChecked(append);
	
	}
	
	private byte [] CnvKnaPa(int subpos, int pos)
	{
		Log.d("evy1demo",paData[subpos][pos-1]);
		return paData[subpos][pos-1].getBytes();
	}
	
	private void sendVoice(MidiOutputDevice midiOutputDevice, byte[] roma) {
		byte systemExclusive[] = new byte[9 + 128];
		byte sysExHead[] = {(byte)0xf0, (byte)0x43, (byte)0x79, (byte)0x09, (byte)0x00, (byte)0x50, (byte)0x10};	
		if(append == true){
			sysExHead[6] = (byte)0x11;	
		}
		byte sysExEnd[] = {(byte)0x00, (byte)0xf7};
		System.arraycopy(sysExHead, 0, systemExclusive, 0, sysExHead.length);
		System.arraycopy(roma, 0, systemExclusive, sysExHead.length, roma.length);
		System.arraycopy(sysExEnd, 0, systemExclusive, (sysExHead.length + roma.length), sysExEnd.length);
		midiOutputDevice.sendMidiSystemExclusive(0, systemExclusive);
    }

	@Override
    protected void onDestroy() {
        super.onDestroy();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onDeviceDetached(UsbDevice usbDevice) {
		Toast.makeText(this, "USB MIDI Device " + usbDevice.getDeviceName() + " has been detached.", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDeviceAttached(UsbDevice usbDevice) {
        Toast.makeText(this, "USB MIDI Device " + usbDevice.getDeviceName() + " has been attached.", Toast.LENGTH_LONG).show();		
	}

	@Override
	public void onMidiMiscellaneousFunctionCodes(MidiInputDevice sender,
			int cable, int byte1, int byte2, int byte3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiCableEvents(MidiInputDevice sender, int cable, int byte1,
			int byte2, int byte3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiSystemCommonMessage(MidiInputDevice sender, int cable,
			byte[] bytes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiSystemExclusive(MidiInputDevice sender, int cable,
			byte[] systemExclusive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiNoteOff(MidiInputDevice sender, int cable, int channel,
			int note, int velocity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiNoteOn(MidiInputDevice sender, int cable, int channel,
			int note, int velocity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiPolyphonicAftertouch(MidiInputDevice sender, int cable,
			int channel, int note, int pressure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiControlChange(MidiInputDevice sender, int cable,
			int channel, int function, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiProgramChange(MidiInputDevice sender, int cable,
			int channel, int program) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiChannelAftertouch(MidiInputDevice sender, int cable,
			int channel, int pressure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiPitchWheel(MidiInputDevice sender, int cable,
			int channel, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMidiSingleByte(MidiInputDevice sender, int cable, int byte1) {
		// TODO Auto-generated method stub
		
	}

}
