package co.jp.project.alice_kleio.flshky;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
/**
 * 鍵穴画像を出力するための処理
 * @author alice
 *
 */
@SuppressLint("Recycle")
public class FlashKeyHoleActivity extends Activity implements OnClickListener {
	private int anser;
	private int GameTime = 30;
	private Timer mTimer = null;
	Handler hdl = new Handler();
	private int selecter_result;
	private HashMap<String, String> result = new HashMap<String, String>();
	private int mondai_counter = 0;
	ImageButton selecter_key1;
	ImageButton selecter_key2;
	ImageButton selecter_key3;
	ImageButton selecter_key4;
	ImageButton selecter_key5;
	/**
	 * main文
	 */
	private static int RANDOM_RANGE = 19;
	private static int SELECTER_RANGE = 5;
	private static int RESULT_ONE = 1;
	private static int RESULT_TWO = 2;
	private static int RESULT_THREE = 3;
	private static int RESULT_FOUR = 4;
	private static int RESULT_FIVE = 5;
	ImageView keyhole;
	int mondai[] = new int[RANDOM_RANGE];
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Timer();
		Setting();
	}
	class splashHandler implements Runnable{
		public void run(){
			doorClosed();
		}
	}
	class splashHandler2 implements Runnable{
		public void run(){
			Select();
		}
	}
	class splashHandler3 implements Runnable{
		public void run(){
			Setting();
		}
	}
	class splashHandler4 implements Runnable{
		public void run(){
			resultEnd();
		}
	}
	class splashHandler5 implements Runnable{
		public void run(){
			Result();
		}
	}
	protected void Timer(){
		if(mTimer != null){
			return;
		}
		mTimer = new Timer(true);
		mTimer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				hdl.post(new Runnable(){
					public void run(){
						GameTime--;
						if(GameTime<=0){
							resultEnd();
						}
					}
				});
			}
		}, 1000, 1000);
	}
	protected void Setting(){
		setMondaiCounterAdd(getMondaiCounterAdd());
		setContentView(R.layout.activity_flashkeyhole);
		keyhole = (ImageView)findViewById(R.id.mondai_key);
		TypedArray typedArray = getResources().obtainTypedArray(R.array.mondai_key);
        int i =(int)(Math.floor(Math.random()*(RANDOM_RANGE-1+1))+1);
        setAnser(i);
        Drawable drawable = typedArray.getDrawable(i);
        keyhole.setImageDrawable(drawable);
		hdl.postDelayed(new splashHandler(), 200);
	}
	protected void Result(){
		mTimer.cancel();
		mTimer = null;
		Intent inte = new Intent(getApplication(),ResultActivity.class);
		int score = ScoreMaker.score_make(getResult());
		String send_score = String.valueOf(score);
		inte.putExtra("score", send_score);
		startActivityForResult(inte, score);
		FlashKeyHoleActivity.this.finish();
	}
	protected void Select(){
		super.onResume();
		setContentView(R.layout.list_selecter);
		selecter_key1 = (ImageButton)findViewById(R.id.selecter_key1);
		selecter_key2 = (ImageButton)findViewById(R.id.selecter_key2);
		selecter_key3 = (ImageButton)findViewById(R.id.selecter_key3);
		selecter_key4 = (ImageButton)findViewById(R.id.selecter_key4);
		selecter_key5 = (ImageButton)findViewById(R.id.selecter_key5);
		Random rnd = new Random();
		int i = rnd.nextInt(SELECTER_RANGE);
		if(i == 0){
			selecter_key1.setImageDrawable(makeResultDrawable(R.array.selecter_key1,getAnser()));
			setSelecterResult(RESULT_ONE);
			makeDrawableSelecter(selecter_key2,selecter_key3,selecter_key4,selecter_key5);
		}else if(i == 1){
			selecter_key2.setImageDrawable(makeResultDrawable(R.array.selecter_key1,getAnser()));
			setSelecterResult(RESULT_TWO);
			makeDrawableSelecter(selecter_key1,selecter_key3,selecter_key4,selecter_key5);
		}else if(i == 2){
			selecter_key3.setImageDrawable(makeResultDrawable(R.array.selecter_key1,getAnser()));
			setSelecterResult(RESULT_THREE);
			makeDrawableSelecter(selecter_key2,selecter_key1,selecter_key4,selecter_key5);
		}else if(i == 3){
			selecter_key4.setImageDrawable(makeResultDrawable(R.array.selecter_key1,getAnser()));
			setSelecterResult(RESULT_FOUR);
			makeDrawableSelecter(selecter_key2,selecter_key3,selecter_key1,selecter_key5);
		}else{
			selecter_key5.setImageDrawable(makeResultDrawable(R.array.selecter_key1,getAnser()));
			setSelecterResult(RESULT_FIVE);
			makeDrawableSelecter(selecter_key2,selecter_key3,selecter_key4,selecter_key1);
		}
		selecter_key1.setOnClickListener(this);
		selecter_key2.setOnClickListener(this);
		selecter_key3.setOnClickListener(this);
		selecter_key4.setOnClickListener(this);
		selecter_key5.setOnClickListener(this);
	}
	private void makeDrawableSelecter(ImageButton selecter_key2,
			ImageButton selecter_key3, ImageButton selecter_key4,
			ImageButton selecter_key5) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for( int i = 0; i < RANDOM_RANGE; i++ ){
			if(i!=getAnser()){
				list.add(i);
				}
			}
	        Collections.shuffle(list);
	        TypedArray typedArray = getResources().obtainTypedArray(R.array.selecter_key1);
	        Drawable drawable = typedArray.getDrawable(list.get(0));
	        Drawable drawable1 = typedArray.getDrawable(list.get(2));
	        Drawable drawable2 = typedArray.getDrawable(list.get(5));
	        Drawable drawable3 = typedArray.getDrawable(list.get(8));
	        selecter_key3.setImageDrawable(drawable);
	        selecter_key4.setImageDrawable(drawable2);
	        selecter_key5.setImageDrawable(drawable3);
	        selecter_key2.setImageDrawable(drawable1);
	}
	protected void doorClosed(){
		super.onStart();
		ImageView door = (ImageView)findViewById(R.id.door);
		Drawable drawable = getResources().getDrawable(R.drawable.door_color);
		door.setImageDrawable(drawable);
		AnimationSet set = new AnimationSet(true);
		TranslateAnimation translate = new TranslateAnimation(1000, 300, 0, 0);
		translate.setDuration(250);
		translate.setFillAfter(true);
		set.addAnimation(translate);
		door.startAnimation(set);
		hdl.postDelayed(new splashHandler2(),750);
	}
	private void resultEnd(){
		ImageView door = (ImageView)findViewById(R.id.door);
		Drawable drawable = getResources().getDrawable(R.drawable.door_color);
		door.setImageDrawable(drawable);
		AnimationSet set = new AnimationSet(true);
		TranslateAnimation translate = new TranslateAnimation(1000, 300, 0, 0);
		translate.setDuration(250);
		translate.setFillAfter(true);
		set.addAnimation(translate);
		door.startAnimation(set);
		hdl.postDelayed(new splashHandler5(),750);

	}
	private Drawable makeResultDrawable(int id, int result){
		TypedArray typedArray = getResources().obtainTypedArray(id);
		Drawable drawable = typedArray.getDrawable(result);
		return drawable;
	}
	private void setAnser(int ans){
		this.anser = ans;
	}
	private int getAnser(){
		return this.anser;
	}
	private void setMondaiCounterAdd(int mondai_count){
		this.mondai_counter = mondai_count+1;
	}
	private int getMondaiCounterAdd(){
		return this.mondai_counter;
	}
	//アニメーション
	private void NgResult() {
		this.result.put(String.valueOf(getMondaiCounterAdd()),"×");
		ImageView door = (ImageView)findViewById(R.id.door);
		Drawable drawable = getResources().getDrawable(R.drawable.door_monocro);
		door.setImageDrawable(drawable);
		AnimationSet set = new AnimationSet(true);
		TranslateAnimation translate = new TranslateAnimation(300, 1000, 0, 0);
		translate.setDuration(250);
		translate.setFillAfter(true);
		set.addAnimation(translate);
		door.startAnimation(set);
		hdl.postDelayed(new splashHandler3(),750);
	}
	//アニメーション
	private void OkResult() {
		this.result.put(String.valueOf(getMondaiCounterAdd()),"○");
		ImageView door = (ImageView)findViewById(R.id.door);
		Drawable drawable = getResources().getDrawable(R.drawable.door_color);
		door.setImageDrawable(drawable);
		AnimationSet set = new AnimationSet(true);
		TranslateAnimation translate = new TranslateAnimation(300, 1000, 0, 0);
		translate.setDuration(250);
		translate.setFillAfter(true);
		set.addAnimation(translate);
		door.startAnimation(set);
		hdl.postDelayed(new splashHandler3(),750);
	}
	private HashMap<String, String> getResult(){
		return this.result;
	}
	private int SelecterResult(){
		return this.selecter_result;
	}
	private void setSelecterResult(int num){
		this.selecter_result = num;
	}
	@Override
	public void onClick(View v) {
			int select = 0;
			switch(v.getId()){
				case R.id.selecter_key1:
					select = 1;
					break;
				case R.id.selecter_key2:
					select = 2;
					break;
				case R.id.selecter_key3:
					select = 3;
					break;
				case R.id.selecter_key4:
					select = 4;
					break;
				case R.id.selecter_key5:
					select = 5;
					break;
			}
			if(select == SelecterResult()){
				releaseImageView();
				OkResult();
			}else{
				releaseImageView();
				NgResult();
			}
	}
	private void releaseImageView(){
        if(selecter_key1 != null){
            selecter_key1.setImageDrawable(null);
        }
        if(selecter_key2 != null){
            selecter_key2.setImageDrawable(null);
        }
        if(selecter_key3 != null){
            selecter_key3.setImageDrawable(null);
        }
        if(selecter_key4 != null){
            selecter_key4.setImageDrawable(null);
        }
        if(selecter_key5 != null){
            selecter_key5.setImageDrawable(null);
        }
    }
}