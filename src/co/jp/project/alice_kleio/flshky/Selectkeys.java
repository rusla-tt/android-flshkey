package co.jp.project.alice_kleio.flshky;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Selectkeys extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		ListView lv = new ListView(this);
		setContentView(lv);
		final String[] mString = new String[5];
		Intent i = getIntent();
		mString[0] = "Dammy";
		mString[1] = i.getStringExtra("result");
		mString[2] = "Dammy2";
		mString[3] = "Dammy3";
		mString[4] = "Dammy4";
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mString);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String get_parent = (String)parent.getClass().getSimpleName();
				String get_position = String.valueOf(position);
				String get_id = String.valueOf(id);
				if(mString[Integer.valueOf(get_id)].equals("Anser_OK")){
					System.out.print(get_parent+" "+get_position);
				}else{
					System.out.print(get_parent+":"+get_position+":"+get_id);
				}
			}
		});
		lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String get_parent = (String)parent.getClass().getSimpleName();
				String get_position = String.valueOf(position);
				String get_id = String.valueOf(id);
				Log.v("tag",String.format("onItemClick %s", get_parent));
				Log.v("tag",String.format("onItemClick %s", get_position));
				Log.v("tag",String.format("onItemClick %s", get_id));
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				Log.v("tag","NothingSelected");
			}
			
		});
	}
}
