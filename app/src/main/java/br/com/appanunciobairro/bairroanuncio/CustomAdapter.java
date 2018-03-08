package br.com.appanunciobairro.bairroanuncio;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter
{

    private Activity mActivity;
    private ArrayList<ListModel> mList;
    private static LayoutInflater mInflater=null;
    private ListModel tempValues=null;
    private int i= 0;



    @Override
    public int getCount()
    {

        if (mList.size() <= 0)
            return 1;
            return mList.size();
        // TODO Auto-generated method stub
       // return images.length;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    //@Override
    //public Object getItem(int position) {
    //    return position;
   // }

    //@Override
    //public long getItemId(int position) {
    //    return position;
    //}

    public static class ViewHolder
    {
        public TextView txtFirstName;
        public TextView txtLastName;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup arg2)
    {
        View view = convertView;
        ViewHolder holder;
        if (convertView == null)
        {
            view = mInflater.inflate(R.layout.custom_list,null);
            holder = new ViewHolder();
            holder.txtFirstName = view.findViewById(R.id.textView1);
            holder.txtLastName = view.findViewById(R.id.textView2);
            view.setTag(holder);
        }
        else
        {
            holder=(ViewHolder) view.getTag();
        }
        try{
            if(mList.size()<=0)
            {
               holder.txtFirstName.setText("No Data");
               holder.txtLastName.setText("No Data");
            }
            else
            {
                tempValues=null;
                tempValues=(ListModel) mList.get(position);
                holder.txtFirstName.setText(tempValues.getName());
                holder.txtLastName.setText(tempValues.getLastName());
            }

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            return view;
    }
}
