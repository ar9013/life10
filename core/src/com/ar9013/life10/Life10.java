package com.ar9013.life10;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

public class Life10 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	Music mp3_zoo,mp3_moana;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		mp3_zoo = Gdx.audio.newMusic(Gdx.files.internal("music/try_everything.mp3"));
		mp3_moana = Gdx.audio.newMusic(Gdx.files.internal("music/a_lin_moana.mp3"));

		mp3_zoo.play();

		mp3_zoo.setOnCompletionListener(new Music.OnCompletionListener() {
			@Override
			public void onCompletion(Music music) {
				mp3_moana.play();
			}
		});

		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				if(mp3_zoo.isPlaying()){
					if(mp3_zoo.getPosition()>= 180){
						mp3_zoo.setVolume(mp3_zoo.getVolume()-0.2f);
					}

				}
			}
		},3,1,4);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		mp3_moana.dispose();
		mp3_zoo.dispose();
	}
}
