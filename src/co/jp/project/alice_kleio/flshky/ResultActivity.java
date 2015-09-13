package co.jp.project.alice_kleio.flshky;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity implements OnClickListener{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Intent inte = getIntent();
		String view_score = inte.getStringExtra("score");
		setContentView(R.layout.act_result);
		//:TODO view_scoreの値をViewにはめ込む処理。button押下時の処理
		TextView score_view = (TextView)findViewById(R.id.score);
		TextView score_label = (TextView)findViewById(R.id.score_label);
		score_view.setText(view_score);
		score_view.setTypeface(Typeface.createFromAsset(getAssets(), "plok.ttf"));
		score_label.setTypeface(Typeface.createFromAsset(getAssets(), "plok.ttf"));
		Button button = (Button)findViewById(R.id.app_finish);
		button.setOnClickListener(this);
	}
	public void onClick(View v){
		Intent inte = new Intent(ResultActivity.this,StartActivity.class);
		startActivityForResult(inte,0);
		ResultActivity.this.finish();
	}
}
