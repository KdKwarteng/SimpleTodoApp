package kdk.k.simpletodoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.*;

//Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnClickListener {
        void onItemClicked(int position);
    }

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    List<String> tasks;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;



    public ItemsAdapter(List<String> tasks, OnLongClickListener longClickListener, OnClickListener clickListener) {
        this.tasks = tasks;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    /**
     * Creates each view
     * @param viewGroup group of views
     * @param i position index
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Use layout inflater to inflate a view
        View todoView = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        //wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }


    /**
     * Binds data to a particular View Holder
     * @param viewHolder    container
     * @param i position index
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //Grab the item at the position
        String task = tasks.get(i);
        //Bind the item into the specified view holder
        viewHolder.bind(task);
    }

    /**
     * The number of tasks in the holder
     * @return
     */
    @Override
    public int getItemCount() {
        return tasks.size();
    }

    // Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        /**
         * Update the view inside of the view holder with this data
         * @param task  the string to be put in the view holder
         */
        public void bind(String task) {
            tvItem.setText(task);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked((getAdapterPosition()));
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify listener which position was pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });

        }
    }

}
