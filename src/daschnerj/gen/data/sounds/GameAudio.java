package daschnerj.gen.data.sounds;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameAudio {
	
	private File file;
	
	public GameAudio(File file)
	{
		this.file = file;
	}
	
	public synchronized Sound playSound(float volume, float direction, boolean loop) {
		Sound sound = new Sound(file, volume, direction, loop);
		sound.play();
		return sound;
	}
	
	public synchronized LocationSound playLocationSound(float volume, float direction, boolean loop, SoundVector location, SoundVector listener, float radius) {
		LocationSound sound = new LocationSound(file, volume, direction, loop, location, listener, radius);
		sound.play();
		return sound;
	}
	
	public class Sound{
		
		private SoundThread thread;
		private AudioInputStream ais;
		private Clip clip;
		private long clipTime;
		private boolean loop;
		private float totalVolume;
		private float setVolume;
		private float normalDirection;
		private FloatControl volumeController;
		private FloatControl directionController;
		
		public Sound(File file, float volume, float balance, boolean loop) {
			try {
				ais = AudioSystem.getAudioInputStream(file);
				this.loop = loop;
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
				clip.stop();
				clip.setFramePosition(0);
				volumeController = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				totalVolume = Math.abs(volumeController.getMaximum()) + Math.abs(volumeController.getMinimum());
				setVolume(volume);
				setVolume = volume;
				directionController = (FloatControl) clip.getControl(FloatControl.Type.BALANCE);
				normalDirection = directionController.getValue();
				directionController.setValue(balance);
				thread = new SoundThread(this);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}	
		}
		
		public void pause()
		{
			clipTime = clip.getMicrosecondPosition();
			clip.stop();
		}
		
		public float getVolume()
		{
			return setVolume;
		}
		
		public void setVolume(float volume)
		{
			setVolume = volume;
			if(volume <= 0.0f)
				volumeController.setValue(volumeController.getMinimum());
			else
				volumeController.setValue(Math.min(Math.max(((totalVolume*0.57f)*volume)-(Math.abs(volumeController.getMinimum())) + (0.43f*totalVolume) ,volumeController.getMinimum()),volumeController.getMaximum()));
				
			if(volumeController.getValue() < -50)
				volumeController.setValue(volumeController.getMinimum());
		}
		
		protected void setVolumeDiscreet(float volume)
		{
			if(volume <= 0.0f)
				volumeController.setValue(volumeController.getMinimum());
			else
				volumeController.setValue(Math.min(Math.max(((totalVolume*0.57f)*volume)-(Math.abs(volumeController.getMinimum())) + (0.43f*totalVolume) ,volumeController.getMinimum()),volumeController.getMaximum()));
				
			if(volumeController.getValue() < -50)
				volumeController.setValue(volumeController.getMinimum());
		}
		
		public void resetVolume()
		{
			volumeController.setValue(1.0f);
		}
		
		public float getDirection()
		{
			return directionController.getValue();
		}
		
		public void setDirection(float direction)
		{
			directionController.setValue(Math.min(Math.max(direction, directionController.getMinimum()),directionController.getMaximum()));
		}
		
		public void resetDirection()
		{
			directionController.setValue(normalDirection);
		}
		
		public void end()
		{
			loop = false;
			clip.stop();
			clip.flush();
			clip.close();
		}
		
		public void go()
		{
			try {
				clip.open();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			clip.setMicrosecondPosition(clipTime);
			clip.start();
		}
		
		public long getTime()
		{
			if(clip.isRunning())
				clipTime = clip.getMicrosecondPosition();
			return clipTime;
		}
		
		public void play()
		{
			thread.start();
		}
		
		private class AudioLineListener implements LineListener
		{
			Sound sound;
			public AudioLineListener(Sound sound)
			{
				this.sound = sound;
			}
			@Override
			public void update(LineEvent event) {
				if(event.getType().equals(LineEvent.Type.STOP))
					if(sound.loop)
						if(!(sound.clip.getLongFramePosition() < sound.clip.getFrameLength()))
						{
							clip.setFramePosition(0);
							clip.start();
						}
					else
						if(!(sound.clip.getLongFramePosition() < sound.clip.getFrameLength()))
							clip.close();
			}
			
		}
		
		private class SoundThread extends Thread
		{
			private Sound sound;
			public SoundThread(Sound sound)
			{
				this.sound = sound;
			}
			
			public void run() {
				clip.start();
				clip.addLineListener(new AudioLineListener(sound));
			}
		}
		
	}
	
	public class LocationSound extends Sound{
		
		private SoundVector location;
		private float radius;
		
		public LocationSound(File file, float volume, float balance, boolean loop, SoundVector location, SoundVector listener, float radius) {
			super(file, volume, balance, loop);
			this.location = location;
			this.radius = radius;
			adjustSoundToListener(listener);
		}
		
		public void adjustSoundToListener(SoundVector listenerLocation)
		{
			this.setVolumeDiscreet((super.setVolume/radius) * (radius - location.subtract(listenerLocation).getMagnitude()));
			this.setDirection(location.subtract(listenerLocation).normalize().getX());
		}
		
		public void setLocation(SoundVector location)
		{
			this.location = location;
		}
	}
}
