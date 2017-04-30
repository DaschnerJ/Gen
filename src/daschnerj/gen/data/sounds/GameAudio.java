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
	
	public synchronized Sound playSound(float volume, float direction) {
		Sound sound = new Sound(file, volume, direction);
		sound.start();
		return sound;
	}
	
	public synchronized Sound playSound(float volume, float direction, boolean loop) {
		Sound sound = new Sound(file, volume, direction, loop);
		sound.start();
		return sound;
	}
	
	public class Sound extends Thread{
		
		private AudioInputStream ais;
		private Clip clip;
		private long clipTime;
		private boolean loop;
		private float totalVolume;
		private float normalVolume;
		private float setVolume;
		private float normalDirection;
		private FloatControl volumeController;
		private FloatControl directionController;
		
		public Sound(File file, float volume, float balance) {
			try {
				ais = AudioSystem.getAudioInputStream(file);
				loop = false;
				clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
				clip.stop();
				clip.setFramePosition(0);
				volumeController = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				totalVolume = Math.abs(volumeController.getMaximum()) + Math.abs(volumeController.getMinimum());
				normalVolume = Math.abs(volumeController.getMinimum());
				setVolume(volume);
				setVolume = volume;
				directionController = (FloatControl) clip.getControl(FloatControl.Type.BALANCE);
				normalDirection = directionController.getValue();
				directionController.setValue(balance);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		
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
				normalVolume = totalVolume - Math.abs(volumeController.getMinimum());
				setVolume(volume);
				setVolume = volume;
				directionController = (FloatControl) clip.getControl(FloatControl.Type.BALANCE);
				normalDirection = directionController.getValue();
				directionController.setValue(balance);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			
		}
		
		public void run() {
			clip.start();
			clip.addLineListener(new AudioLineListener(this));
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
			volumeController.setValue(Math.min(Math.max(((totalVolume*0.7f)*volume)-(Math.abs(volumeController.getMinimum())) + (0.3f*totalVolume) ,volumeController.getMinimum()),volumeController.getMaximum()));
			if(volumeController.getValue() < -50)
				volumeController.setValue(0);
		}
		
		public void resetVolume()
		{
			volumeController.setValue(normalVolume);
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
							System.out.println("Looped sound");
							clip.start();
						}
					else
						if(!(sound.clip.getLongFramePosition() < sound.clip.getFrameLength()))
							clip.close();
			}
			
		}
		
	}
	
}
