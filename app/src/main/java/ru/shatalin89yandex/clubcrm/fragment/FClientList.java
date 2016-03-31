package ru.shatalin89yandex.clubcrm.fragment;

import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import ru.shatalin89yandex.clubcrm.R;
import ru.shatalin89yandex.clubcrm.WorkActivity;

public class FClientList extends ListFragment {

    public ArrayAdapter<String> clinfo;
    String data[] = new String[] { "" };


      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

          View v = inflater.inflate(R.layout.fclient_list, container, false);
         // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        //  setListAdapter(adapter);

          return v;

        //return inflater.inflate(R.layout.fclient_list, container, false);

    }

    @Override
    public void onStart(){
         super.onStart();

        clinfo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), data[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

}
