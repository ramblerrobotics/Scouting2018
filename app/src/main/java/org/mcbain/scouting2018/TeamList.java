package org.mcbain.scouting2018;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TeamList extends AppCompatActivity {
    String nums[] = new String[Globals.teams.length];
    String names [] = new String [Globals.teams.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        for(int i = 0; i < Globals.teams.length; i++) {
            nums[i] = Integer.toString(Globals.teams[i].getNum());
            names[i] = Globals.teams[i].getName();
        }
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                //android.R.layout.simple_list_item_1, nums);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.teamlistslayout);

        ListView listView=(ListView)findViewById(R.id.listView);

        CustomAdapter customAdapter=new CustomAdapter();

        listView.setAdapter(customAdapter);
    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return nums.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout,null);

            TextView textView =(TextView)view.findViewById(R.id.editText);
            TextView textView2 = (TextView)view.findViewById(R.id.editText2);
            textView2.setInputType(InputType.TYPE_NULL);
            textView.setInputType(InputType.TYPE_NULL);

            textView.setText(nums[i]);
            textView2.setText(names[i]);
            return view;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
