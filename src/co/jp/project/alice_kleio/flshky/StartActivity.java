package co.jp.project.alice_kleio.flshky;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/**
 * アプリケーション起動時の処理
 * @author alice
 *
 */
public class StartActivity extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		TextView title = (TextView)findViewById(R.id.score);
		title.setTypeface(Typeface.createFromAsset(getAssets(), "plok.ttf"));
		Button button1 = (Button) findViewById(R.id.app_finish);
		button1.setOnClickListener(this);
	}
	public void onClick(View v){
		Intent inte = new Intent(StartActivity.this,FlashKeyHoleActivity.class);
		startActivityForResult(inte,0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
