package com.example.qjh.cloudrecognition.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qjh.cloudrecognition.R;

import java.util.List;

public class Search_Adapter  extends RecyclerView.Adapter<Search_Adapter.ViewHolder> {
    private  List<Items>  items_List;
    private  OnItemClickListener listener;
    public Search_Adapter(List<Items> items )
    {
        this.items_List=items;
    }
    public void setOnItemClick(OnItemClickListener listener){
        this.listener=listener;
    }
    /**
     * 设置数据集
     * @param data
     */
    public void setData(List<Items> data){
        this.items_List = data;
    }
    /*
    接口回调
     */
    public interface OnItemClickListener{
        /*注意参数*/
        public void OnItemClick(Items items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item,viewGroup,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                {
                    int postition=viewHolder.getAdapterPosition();
                    Items items=items_List.get(postition);
                    listener.OnItemClick(items);
                }


            }
        });

        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Items items=items_List.get(i);  //获取点击位置
        viewHolder.textView.setText(items.getMsg());
        viewHolder.imageView.setImageResource(items.getImageId());
    }

    @Override
    public int getItemCount() {
        return items_List.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            textView=(TextView)itemView.findViewById(R.id.text);
            imageView=(ImageView)itemView.findViewById(R.id.img);

        }
    }




}
